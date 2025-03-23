package phuc.edu.ex7_loginintent;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signupActivity extends AppCompatActivity {

    private EditText edtName, edtEmail, edtPassword, edtRePassword;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtName = findViewById(R.id.editTextName);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);
        edtRePassword = findViewById(R.id.editTextRePassword);
        btnSignup = findViewById(R.id.buttonSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                String rePassword = edtRePassword.getText().toString();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                    Toast.makeText(signupActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(rePassword)) {
                    Toast.makeText(signupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(signupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(signupActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}

