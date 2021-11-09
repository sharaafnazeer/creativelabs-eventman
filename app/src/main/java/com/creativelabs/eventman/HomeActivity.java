package com.creativelabs.eventman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.creativelabs.eventman.adapters.EventRecyclerViewAdapter;
import com.creativelabs.eventman.classes.EventItem;
import com.creativelabs.eventman.classes.SharedPref;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvEvents;
    List<EventItem> listItems = new ArrayList<>();
    EventRecyclerViewAdapter adapter;

    Button btnView;

    AlertDialog.Builder builder;

    //https://abhiandroid.com/ui/gridview

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = findViewById(R.id.homeToolbar);
        setSupportActionBar(myToolbar);

        rvEvents = findViewById(R.id.rvEvents);
        btnView = findViewById(R.id.btnView);
        btnView.setText("Grid");

        checkLoggedIn();

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

        Log.d("TRACER", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TRACER", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TRACER", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TRACER", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TRACER", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TRACER", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuLogout) {

            builder = new AlertDialog.Builder(this);

            builder.setMessage("Please confirm that you want to logout")
                    .setTitle("Are you sure?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logout();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(R.drawable.ic_baseline_person_add_alt_1_24)
                    .setCancelable(false)
                    .create();

            builder.show();

        } else if (item.getItemId() == R.id.menuAboutUs) {

            Toast.makeText(this, "About Us", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menuHelp) {

            Toast.makeText(this, "Help", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private void checkLoggedIn() {
        boolean loggedIn =  SharedPref.getIsLoggedIn(this);
        if (!loggedIn) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    }

    private void logout() {
        SharedPref.setIsLoggedIn(this, false);
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }
}