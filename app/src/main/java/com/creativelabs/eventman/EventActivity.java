package com.creativelabs.eventman;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.creativelabs.eventman.classes.EventItem;
import com.creativelabs.eventman.entity.EventItemEntity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Date;

public class EventActivity extends AppCompatActivity {

    public static TextInputEditText etEventName, etDescription, etStartTime, etStartDate;
    TextInputLayout tilEventName, tilDescription, tilStartDate, tilStartTime;
    Button btnSave;
    private final static String DATE_FRAGMENT = "date_fragment";
    private final static String TIME_FRAGMENT = "time_fragment";
    EventItemEntity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        etEventName = findViewById(R.id.tieEventName);
        etDescription = findViewById(R.id.tieDescription);
        etStartDate = findViewById(R.id.tieStartDate);
        etStartTime = findViewById(R.id.tieStartTime);

        tilEventName = findViewById(R.id.tilEventName);
        tilStartDate = findViewById(R.id.tilStartDate);
        tilStartTime = findViewById(R.id.tilStartTime);

        btnSave = findViewById(R.id.btnSave);

        etStartDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showDatePickerDialog(v);
                //Toast.makeText(getApplicationContext(), "Focused", Toast.LENGTH_LONG).show();
            }
        });

        etStartDate.setOnClickListener(v -> {
            showDatePickerDialog(v);
            //Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
        });

        etStartTime.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                showTimePicker(v);
            }
        });

        etStartTime.setOnClickListener(v -> {
            showTimePicker(v);
        });

        btnSave.setOnClickListener(v -> {
            if (validateInputs()) {
                clearErrors();

                EventItem item = new EventItem(etEventName.getText().toString(),
                        etDescription.getText().toString(),
                        etStartDate.getText().toString(),
                        etStartTime.getText().toString());

                entity.create(item);
            }
        });
    }

    private void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), DATE_FRAGMENT);
    }

    private void showTimePicker(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), TIME_FRAGMENT);
    }

    private boolean validateInputs() {

        boolean status = true;
        if (TextUtils.isEmpty(etEventName.getText())) {
            tilEventName.setError("Please enter event name");
            status = false;
        }
        if (TextUtils.isEmpty(etStartDate.getText())) {
            tilStartDate.setError("Please select start date");
            status = false;
        }
        if (TextUtils.isEmpty(etStartTime.getText())) {
            tilStartTime.setError("Please select start time");
            status = false;
        }

        return status;
    }

    private void clearErrors () {
        tilEventName.setError(null);
        tilStartDate.setError(null);
        tilStartTime.setError(null);
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            String timeString = hourOfDay + ":" + minute;
            etStartTime.setText(timeString);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            String dateString = year + "-" + (month + 1) + "-" + day;
            etStartDate.setText(dateString);
        }
    }
}