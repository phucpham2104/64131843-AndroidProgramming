package vn.PhamMacHoangPhuc;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCau1 = findViewById(R.id.btnCau1);
        Button btnCau2 = findViewById(R.id.btnCau2);
        Button btnCau3 = findViewById(R.id.btnCau3);
        Button btnCau4 = findViewById(R.id.btnCau4);
        btnCau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ActivityCau1.class);
                startActivity(Intent);
            }
        });
        btnCau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Cau2Activity.class);
                startActivity(Intent);
            }
        });
        btnCau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityCau4.class);
                startActivity(Intent);
            }
        });
    }

}
