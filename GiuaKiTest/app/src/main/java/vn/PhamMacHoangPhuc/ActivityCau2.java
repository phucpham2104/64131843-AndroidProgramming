package vn.PhamMacHoangPhuc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Cau2Activity extends AppCompatActivity {
    ListView listView;
    String[] items = {"Bài hát 1", "Bài hát 2", "Bài hát 3", "Bài hát 4", "Bài hát 5"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau2);

        listView = findViewById(R.id.listView);

        // Gán adapter cho ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        // Bắt sự kiện khi click vào item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = items[position];

                // Chuyển sang ItemActivity và truyền dữ liệu
                Intent intent = new Intent(Cau2Activity.this, Cau2Activity.class);
                intent.putExtra("data", selectedItem);
                startActivity(intent);
            }
        });
    }
}
