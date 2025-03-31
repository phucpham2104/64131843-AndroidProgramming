package vn.PhamMacHoangPhuc;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ItemActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        textView = findViewById(R.id.textView);

        // Nhận dữ liệu từ Intent
        String data = getIntent().getStringExtra("data");
        textView.setText("Bạn đã chọn: " + data);
    }
}
