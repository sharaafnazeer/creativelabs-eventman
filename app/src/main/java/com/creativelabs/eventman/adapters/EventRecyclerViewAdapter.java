package com.creativelabs.eventman.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.creativelabs.eventman.EventActivity;
import com.creativelabs.eventman.R;
import com.creativelabs.eventman.RegisterActivity;
import com.creativelabs.eventman.classes.EventItem;
import com.creativelabs.eventman.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder> {

    private List<EventItem> listItems = new ArrayList<>();
    private String type;

    public EventRecyclerViewAdapter(String type) {
        this.type = type;
    }

    public void setListItems(List<EventItem> listItems) {
        this.listItems = listItems;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom_view, parent, false);

//        if (type.equals("List")) {
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_custom_view, parent, false);
//        } else {
//            // Some different view;
//        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventItem item = listItems.get(position);

        // access all view holder class functions;
        holder.getTvEventName().setText(item.getEventName());
        holder.getTvEventDesc().setText(item.getEventDesc());
        holder.getTvStartDate().setText(item.getStartDate().toString());
        holder.getTvStartTime().setText(item.getStartTime());

        holder.getCvRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventActivity = new Intent(v.getContext(), EventActivity.class);
                eventActivity.putExtra(Constants.EVENT_ID, item.getId());
                v.getContext().startActivity(eventActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvEventName;
        private TextView tvEventDesc;
        private TextView tvStartDate;
        private TextView tvStartTime;

        private CardView cvRoot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvEventName = itemView.findViewById(R.id.tvEventName);
            tvEventDesc = itemView.findViewById(R.id.tvEventDesc);
            tvStartDate = itemView.findViewById(R.id.tvStartDate);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
            cvRoot = itemView.findViewById(R.id.cvRoot);
        }

        public TextView getTvEventName() {
            return tvEventName;
        }

        public TextView getTvEventDesc() {
            return tvEventDesc;
        }

        public TextView getTvStartDate() {
            return tvStartDate;
        }

        public TextView getTvStartTime() {
            return tvStartTime;
        }

        public CardView getCvRoot() {
            return cvRoot;
        }
    }
}
