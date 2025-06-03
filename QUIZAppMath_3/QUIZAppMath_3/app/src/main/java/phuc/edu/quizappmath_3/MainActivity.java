package phuc.edu.quizappmath_3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvTimer;
    private Button btnA, btnB, btnC, btnD, btnTrue, btnFalse, btnSubmitFill;
    private EditText etAnswer;
    private ProgressBar progressBar;
    private LinearLayout trueFalseLayout;

    private List<Question> questionList = new ArrayList<>();
    private int currentIndex = 0, score = 0;
    private CountDownTimer countDownTimer;
    private Question currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setAnswerButtons();

        String level = getIntent().getStringExtra("level");
        if (level == null) level = "easy";

        loadQuestionsFromFirebase(level);
    }

    private void initViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        tvTimer = findViewById(R.id.tvTimer);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        etAnswer = findViewById(R.id.etAnswer);
        btnSubmitFill = findViewById(R.id.btnSubmitFill);
        btnTrue = findViewById(R.id.btnTrue);
        btnFalse = findViewById(R.id.btnFalse);
        trueFalseLayout = findViewById(R.id.trueFalseLayout);
        progressBar = findViewById(R.id.progressBar);
    }

    private void loadQuestionsFromFirebase(String level) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(level);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questionList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Question q = ds.getValue(Question.class);
                    if (q != null) questionList.add(q);
                }
                if (!questionList.isEmpty()) {
                    progressBar.setMax(questionList.size());
                    loadQuestion();
                } else {
                    Toast.makeText(MainActivity.this, "Không có câu hỏi!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi tải dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadQuestion() {
        if (currentIndex >= questionList.size()) {
            goToResult();
            return;
        }

        currentQuestion = questionList.get(currentIndex);
        tvQuestion.setText(currentQuestion.getContent());
        progressBar.setProgress(currentIndex + 1);
        tvScore.setText("Điểm: " + score);
        resetButtons();
        startTimer();

        String type = currentQuestion.getType();
        if (type == null) type = "mcq";

        switch (type) {
            case "mcq":
                showMCQ();
                break;
            case "truefalse":
                showTrueFalse();
                break;
            case "fill":
                showFill();
                break;
            default:
                Toast.makeText(this, "Loại câu hỏi không hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetButtons() {
        btnA.setVisibility(View.GONE);
        btnB.setVisibility(View.GONE);
        btnC.setVisibility(View.GONE);
        btnD.setVisibility(View.GONE);
        btnSubmitFill.setVisibility(View.GONE);
        trueFalseLayout.setVisibility(View.GONE);
        etAnswer.setVisibility(View.GONE);

        resetButtonStyle(btnA);
        resetButtonStyle(btnB);
        resetButtonStyle(btnC);
        resetButtonStyle(btnD);
        resetButtonStyle(btnTrue);
        resetButtonStyle(btnFalse);
    }

    private void resetButtonStyle(Button btn) {
        btn.setEnabled(true);
        btn.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
    }

    private void showMCQ() {
        btnA.setText(currentQuestion.getOptionA());
        btnB.setText(currentQuestion.getOptionB());
        btnC.setText(currentQuestion.getOptionC());
        btnD.setText(currentQuestion.getOptionD());

        btnA.setVisibility(View.VISIBLE);
        btnB.setVisibility(View.VISIBLE);
        btnC.setVisibility(View.VISIBLE);
        btnD.setVisibility(View.VISIBLE);
    }

    private void showTrueFalse() {
        trueFalseLayout.setVisibility(View.VISIBLE);
    }

    private void showFill() {
        etAnswer.setText("");
        etAnswer.setVisibility(View.VISIBLE);
        btnSubmitFill.setVisibility(View.VISIBLE);
    }

    private void startTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(15000, 1000) {
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("⏳ " + millisUntilFinished / 1000 + "s");
            }

            public void onFinish() {
                tvTimer.setText("⏰ Hết giờ!");
                nextQuestion(false);
            }
        }.start();
    }

    private void setAnswerButtons() {
        View.OnClickListener choiceListener = v -> {
            String answer = ((Button) v).getText().toString().trim();
            checkAnswer(answer, (Button) v);
        };

        btnA.setOnClickListener(choiceListener);
        btnB.setOnClickListener(choiceListener);
        btnC.setOnClickListener(choiceListener);
        btnD.setOnClickListener(choiceListener);
        btnTrue.setOnClickListener(v -> checkAnswer("Đúng", btnTrue));
        btnFalse.setOnClickListener(v -> checkAnswer("Sai", btnFalse));
        btnSubmitFill.setOnClickListener(v -> {
            String answer = etAnswer.getText().toString().trim();
            checkAnswer(answer, null);
        });
    }

    private void checkAnswer(String userAnswer, Button selectedButton) {
        if (countDownTimer != null) countDownTimer.cancel();

        if (currentQuestion == null || currentQuestion.getCorrectAnswer() == null) {
            Toast.makeText(this, "Dữ liệu câu hỏi bị lỗi!", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isCorrect = userAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer());

        if (isCorrect) {
            score++;
            if (selectedButton != null) selectedButton.setBackgroundColor(Color.GREEN);
        } else {
            if (selectedButton != null) selectedButton.setBackgroundColor(Color.RED);
            highlightCorrectAnswer();
        }

        disableAllButtons();
        new Handler().postDelayed(() -> nextQuestion(isCorrect), 800);
    }

    private void highlightCorrectAnswer() {
        String correct = currentQuestion.getCorrectAnswer().trim();
        for (Button b : new Button[]{btnA, btnB, btnC, btnD, btnTrue, btnFalse}) {
            if (b.getVisibility() == View.VISIBLE && b.getText().toString().trim().equalsIgnoreCase(correct)) {
                b.setBackgroundColor(Color.GREEN);
            }
        }
    }

    private void disableAllButtons() {
        btnA.setEnabled(false);
        btnB.setEnabled(false);
        btnC.setEnabled(false);
        btnD.setEnabled(false);
        btnTrue.setEnabled(false);
        btnFalse.setEnabled(false);
        btnSubmitFill.setEnabled(false);
    }

    private void nextQuestion(boolean correct) {
        currentIndex++;
        loadQuestion();
    }

    private void goToResult() {
        Intent i = new Intent(MainActivity.this, ResultActivity.class);
        i.putExtra("score", score);
        i.putExtra("total", questionList.size());
        startActivity(i);
        finish();
    }

    @Override
    protected void onDestroy() {
        if (countDownTimer != null) countDownTimer.cancel();
        super.onDestroy();
    }
}
