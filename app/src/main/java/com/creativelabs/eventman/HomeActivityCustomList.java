package com.creativelabs.eventman;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.creativelabs.eventman.adapters.EventListViewAdapter;
import com.creativelabs.eventman.classes.EventItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivityCustomList extends AppCompatActivity {
    ListView lvEvents;
    List<EventItem> listItems = new ArrayList<>();
    EventListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_lsit);
        lvEvents = findViewById(R.id.lvEvents);

        listItems.add(new EventItem("Lecture", "Abc", new Date(), "10:00"));
        listItems.add(new EventItem("Wedding", "Abcdef", new Date(), "20:00"));
        listItems.add(new EventItem("Classes", "Abdfac", new Date(), "12:00"));
        listItems.add(new EventItem("Webinar", "Abdfc", new Date(), "10:00"));
        listItems.add(new EventItem("Farewell", "Affbc", new Date(), "11:00"));
        listItems.add(new EventItem("Engagement", "Amlmbc", new Date(), "15:00"));

        adapter = new EventListViewAdapter(this, listItems);
        lvEvents.setAdapter(adapter);
    }
}