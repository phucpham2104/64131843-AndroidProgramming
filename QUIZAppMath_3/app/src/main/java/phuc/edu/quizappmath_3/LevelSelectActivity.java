package phuc.edu.quizappmath_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LevelSelectActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_select);

        findViewById(R.id.btnEasy).setOnClickListener(v -> startQuiz("easy"));
        findViewById(R.id.btnMedium).setOnClickListener(v -> startQuiz("medium"));
        findViewById(R.id.btnHard).setOnClickListener(v -> startQuiz("hard"));
    }

    private void startQuiz(String level) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("level", level);
        startActivity(i);
    }
}
