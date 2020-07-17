package com.example.tab_application;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ItemViewHolder> {
    private static final Object TAG = "FILTERING";
    private static final Object TAG1 = "FILTERING getDate";
    private ArrayList<Schedule> listData;
    private LayoutInflater inflater;
    private Context context;
    ArrayList<Schedule> unFilteredlist;
    ArrayList<Schedule> filteredList;

    public CalendarAdapter(ArrayList<Schedule> data) {
        this.listData = data;
        this.unFilteredlist = data;
        this.filteredList = data;
    }

    @NonNull
    @Override
    public CalendarAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_list, parent, false);
        CalendarAdapter.ItemViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ItemViewHolder holder, int position) {
        holder.title.setText(listData.get(position).getTitle());
        holder.time.setText(listData.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView time;
        //protected TextView date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.time = (TextView) itemView.findViewById(R.id.time);
            this.title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
