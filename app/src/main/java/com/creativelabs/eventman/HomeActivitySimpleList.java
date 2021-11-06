package com.creativelabs.eventman;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivitySimpleList extends AppCompatActivity {

    String [] listItems = {
            "Lecture",
            "Wedding",
            "Classes",
            "Webinar",
            "Farewell",
            "Engagement"
    };

    ListView lvEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_lsit);

        lvEvents = findViewById(R.id.lvEvents);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItems);

        lvEvents.setAdapter(adapter);

        lvEvents.setOnItemClickListener((parent, view, position, id) -> {
            String currentValue = adapter.getItem(position);
            Toast.makeText(getApplicationContext(), currentValue, Toast.LENGTH_LONG).show();
        });

    }
}