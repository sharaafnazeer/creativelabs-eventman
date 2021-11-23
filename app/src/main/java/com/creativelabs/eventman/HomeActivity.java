package com.creativelabs.eventman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.creativelabs.eventman.adapters.EventRecyclerViewAdapter;
import com.creativelabs.eventman.classes.EventItem;
import com.creativelabs.eventman.classes.SharedPref;
import com.creativelabs.eventman.entity.EventItemEntity;
import com.creativelabs.eventman.services.BoundService;
import com.creativelabs.eventman.services.ForegroundService;
import com.creativelabs.eventman.services.HelloService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvEvents;
    List<EventItem> listItems = new ArrayList<>();
    EventRecyclerViewAdapter adapter;

    Button btnView, btnService;
    TextView tvNoEvents;

    AlertDialog.Builder builder;

    //https://abhiandroid.com/ui/gridview

    EventItemEntity entity;

    BoundService _service;
    boolean bound = false;

    ProgressDialog progressDialog;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("CONNECTION", "CONNECTED");
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            _service = binder.getService();
            bound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("CONNECTION", "DISCONNECTED");
            bound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar myToolbar = findViewById(R.id.homeToolbar);
        setSupportActionBar(myToolbar);

        rvEvents = findViewById(R.id.rvEvents);
        btnView = findViewById(R.id.btnView);
        tvNoEvents = findViewById(R.id.tvNoItems);
        btnView.setText("Grid");

        checkLoggedIn();

        adapter = new EventRecyclerViewAdapter("List");
        rvEvents.setLayoutManager(new LinearLayoutManager(this));

        rvEvents.setAdapter(adapter);

        listItems = getAllEvents();

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
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvEvents);

        Log.d("TRACER", "onCreate");

        progressDialog =  new ProgressDialog(this);


        btnService = findViewById(R.id.btnServiceTest);

        btnService.setOnClickListener(v -> {
//            Intent intent = new Intent(this, ForegroundService.class);
//            boolean serviceRun = SharedPref.getServiceStarted(this);
//            if (!serviceRun) {
//                startService(intent);
//            } else {
//                stopService(intent);
//            }
//            SharedPref.setServiceStarted(this, !serviceRun);

            if (bound) {
                int num = _service.getRandomNumber();
                Toast.makeText(this, "NUMBER : " + num, Toast.LENGTH_SHORT).show();
            }
        });

//        Intent intent = new Intent(this, HelloService.class);
//        startService(intent);

        progressDialog.setCancelable(true);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("I am fetching your data");
        progressDialog.show();
    }

    private void handleNoMessage() {
        if (listItems.size() == 0) {
            tvNoEvents.setVisibility(View.VISIBLE);
            btnView.setVisibility(View.GONE);
        } else {
            tvNoEvents.setVisibility(View.GONE);
            btnView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TRACER", "onStart");

        // Bind to LocalService
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TRACER", "onResume");
        listItems = getAllEvents();
        adapter.setListItems(listItems);
        adapter.notifyDataSetChanged();

        handleNoMessage();
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
        unbindService(connection);
        bound = false;
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

        MenuItem search = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                adapter.setListItems(getAllEvents());
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<EventItem> items = null;
                if (newText.length() > 0) {
                    items = search(newText);
                } else {
                    items = getAllEvents();
                }
                adapter.setListItems(items);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

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
        } else if (item.getItemId() == R.id.menuAddEvent) {
            Intent eventIntent = new Intent(this, EventActivity.class);
            startActivity(eventIntent);
        } else if (item.getItemId() == R.id.menuSearch) {
        }
        return true;
    }

    private void checkLoggedIn() {
        boolean loggedIn = SharedPref.getIsLoggedIn(this);
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

    private List<EventItem> getAllEvents() {
        entity = new EventItemEntity(this);
        return entity.getAll();
    }

    private List<EventItem> search(String query) {
        entity = new EventItemEntity(this);
        return entity.search(query);
    }

    ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            EventItem item = listItems.get(viewHolder.getAdapterPosition());
            deleteEvent(item.getId());

            listItems = getAllEvents();
            adapter.setListItems(listItems);
            adapter.notifyDataSetChanged();

            handleNoMessage();

        }
    };

    private long deleteEvent(long id) {
        entity = new EventItemEntity(this);
        return entity.delete(id);
    }
}