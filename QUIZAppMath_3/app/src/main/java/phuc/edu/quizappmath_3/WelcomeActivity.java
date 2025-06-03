package phuc.edu.quizappmath_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private EditText etName;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        etName = findViewById(R.id.etName);
        btnStart = findViewById(R.id.btnStart);

        btnStart.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                SharedPrefManager.saveUserName(this, name);
                startActivity(new Intent(this, LevelSelectActivity.class));
                finish();
            }
        });
    }
}
