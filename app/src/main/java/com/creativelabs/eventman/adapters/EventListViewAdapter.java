package com.creativelabs.eventman.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.creativelabs.eventman.R;
import com.creativelabs.eventman.classes.EventItem;

import java.util.List;

public class EventListViewAdapter extends ArrayAdapter<EventItem> {

    public EventListViewAdapter(@NonNull Context context, @NonNull List<EventItem> listItems) {
        super(context, 0, listItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        EventItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_custom_view, parent, false);
        }

        TextView tvEventName = convertView.findViewById(R.id.tvEventName);
        TextView tvEventDesc = convertView.findViewById(R.id.tvEventDesc);
        TextView tvStartDate = convertView.findViewById(R.id.tvStartDate);
        TextView tvStartTime = convertView.findViewById(R.id.tvStartTime);

        tvEventName.setText(item.getEventName());
        tvEventDesc.setText(item.getEventDesc());
        tvStartDate.setText(item.getStartDate().toString());
        tvStartTime.setText(item.getStartTime());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(), RegisterActivity.class);
//                v.getContext().startActivity(i);
            }
        });

        return convertView;
    }
}
