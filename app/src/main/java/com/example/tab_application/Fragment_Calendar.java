package com.example.tab_application;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fragment_Calendar extends Fragment{
    private static final String TAG = "Calendar";
    protected ArrayList<Schedule> schedules = new ArrayList<Schedule>();
    protected CalendarAdapter adapter;
    ItemTouchHelper helper;
    protected String cur_date;
    private ArrayList<Schedule> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<Schedule>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");
        cur_date = simpleDateFormat.format(calendar.getTime());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__calendar, container, false);
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_calendar);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);

        adapter = new CalendarAdapter(schedules);
        recyclerView.setAdapter(adapter);
        performFiltering(cur_date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                // i: year, i1: month, i2: day
                cur_date = i + "/" + (i1+1) + "/" + i2;
                performFiltering(cur_date);
            }
        });

        ImageButton button = (ImageButton) view.findViewById(R.id.schedule);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddSchedule.class);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
            }
        });
        return view;
    }

    protected void performFiltering(String constraint) {
        schedules.clear();
        for (int i = 0; i < list.size(); i++){
            Log.d(TAG, list.get(i).getDate());
            if (list.get(i).getDate().contains(constraint)){
                schedules.add(list.get(i));
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            if(data.hasExtra("title") && data.hasExtra("time")){
                String title = data.getStringExtra("title");
                String time = data.getStringExtra("time");
                Log.d("tag3", cur_date);
                Schedule schedule = new Schedule(title, time, cur_date);
                list.add(schedule);
                performFiltering(cur_date);
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}