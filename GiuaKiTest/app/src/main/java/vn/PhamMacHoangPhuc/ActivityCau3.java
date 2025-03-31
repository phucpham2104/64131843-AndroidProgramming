package vn.PhamMacHoangPhuc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Cau3Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Item> itemList;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cau3);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Tạo danh sách item
        itemList = new ArrayList<>();
        itemList.add(new Item("Bài hát 1", "Mô tả bài hát 1", R.drawable.image1));
        itemList.add(new Item("Bài hát 2", "Mô tả bài hát 2", R.drawable.image2));
        itemList.add(new Item("Bài hát 3", "Mô tả bài hát 3", R.drawable.image3));

        adapter = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(adapter);
    }
}
