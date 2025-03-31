package vn.PhamMacHoangPhuc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityCau1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau1);

        EditText edtSoA = findViewById(R.id.edtSoA);
        EditText edtSoB = findViewById(R.id.edtSoB);
        Button btnTinh = findViewById(R.id.btnTinh);
        TextView txtKQ = findViewById(R.id.kq);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double soA = Double.parseDouble(edtSoA.getText().toString());
                    double soB = Double.parseDouble(edtSoB.getText().toString());
                    double tong = soA + soB;
                    txtKQ.setText("Kết quả: " + tong);
                } catch (NumberFormatException e) {
                    txtKQ.setText("Vui lòng nhập số hợp lệ!");
                }
            }
        });
    }
}
