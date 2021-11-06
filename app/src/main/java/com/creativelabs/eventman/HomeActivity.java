package com.creativelabs.eventman;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.creativelabs.eventman.adapters.EventListViewAdapter;
import com.creativelabs.eventman.adapters.EventRecyclerViewAdapter;
import com.creativelabs.eventman.classes.EventItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvEvents;
    List<EventItem> listItems = new ArrayList<>();
    EventRecyclerViewAdapter adapter;

    Button btnView;

    //https://abhiandroid.com/ui/gridview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvEvents = findViewById(R.id.rvEvents);
        btnView = findViewById(R.id.btnView);
        btnView.setText("Grid");

        listItems.add(new EventItem("Lecture", "Abc", new Date(), "10:00"));
        listItems.add(new EventItem("Wedding", "Abcdef", new Date(), "20:00"));
        listItems.add(new EventItem("Classes", "Abdfac", new Date(), "12:00"));
        listItems.add(new EventItem("Webinar", "Abdfc", new Date(), "10:00"));
        listItems.add(new EventItem("Farewell", "Affbc", new Date(), "11:00"));
        listItems.add(new EventItem("Engagement", "Amlmbc", new Date(), "15:00"));

        adapter = new EventRecyclerViewAdapter("List");
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        rvEvents.setAdapter(adapter);

        listItems.add(new EventItem("Test", "Amlmbc", new Date(), "15:00"));
        adapter.setListItems(listItems);
        adapter.notifyDataSetChanged();

        btnView.setOnClickListener(v -> {
            if (btnView.getText().toString().equals("List")) {
                btnView.setText("Grid");
                adapter.setType("Grid");
                rvEvents.setLayoutManager(new LinearLayoutManager(this));
            } else {
                btnView.setText("List");
                adapter.setType("List");
                rvEvents.setLayoutManager(new GridLayoutManager(this, 2));

            }
        });
    }
}