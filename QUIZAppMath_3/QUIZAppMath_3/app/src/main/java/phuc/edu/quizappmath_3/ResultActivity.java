package phuc.edu.quizappmath_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        String name = SharedPrefManager.getUserName(this);

        TextView tvResult = findViewById(R.id.tvResult);
        TextView tvMessage = findViewById(R.id.tvMessage);
        tvResult.setText(name + " đạt " + score + "/" + total + " câu!");

        float percent = (float) score / total * 100;
        if (percent >= 75) {
            tvMessage.setText("🎉 Bạn thật giỏi!");
        } else {
            tvMessage.setText("✨ Cố gắng hơn nhé!");
        }

        HighScoreManager.saveHighScore(this, score);

        findViewById(R.id.btnPlayAgain).setOnClickListener(v ->
                startActivity(new Intent(this, LevelSelectActivity.class))
        );
        findViewById(R.id.btnShare).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, name + " vừa đạt " + score + "/" + total + " trong quiz Toán lớp 3!");
            startActivity(Intent.createChooser(intent, "Chia sẻ với bạn bè"));
        });
    }
}
