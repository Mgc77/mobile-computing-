package com.example.myapplicationforuni;

import static com.example.myapplicationforuni.CalenderUtils.daysInMonthArray;
import static com.example.myapplicationforuni.CalenderUtils.daysInWeekArray;
import static com.example.myapplicationforuni.CalenderUtils.monthYearFromDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WeekActivity extends AppCompatActivity implements CalenderAdapter.OnItemListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        initWidgets();
        setWeekView();
    }

    private TextView monthYearText;
    private RecyclerView calenderRecyclerView;
    private ListView eventsListView;

    private void initWidgets()
    {
        calenderRecyclerView = findViewById(R.id.calenderRecyclerview);
        monthYearText = findViewById(R.id.monthAndYear);
        eventsListView = findViewById(R.id.eventsListView);
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalenderUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalenderUtils.selectedDate);


        CalenderAdapter calenderAdapter = new CalenderAdapter(days,this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calenderRecyclerView.setLayoutManager(layoutManager);
        calenderRecyclerView.setAdapter(calenderAdapter);
        setEventAdapter();
    }


    public void previousWeeks(View view)
    {
        CalenderUtils.selectedDate = CalenderUtils.selectedDate.minusWeeks(1);
        setWeekView();

    }

    public void nextWeek(View view)
    {
        CalenderUtils.selectedDate = CalenderUtils.selectedDate.plusWeeks(1);
        setWeekView();

    }
    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalenderUtils.selectedDate = date;
        setWeekView();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalenderUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(),  dailyEvents);
        eventsListView.setAdapter(eventAdapter);
    }

    public void NewEventAction(View view)
    {
        startActivity(new Intent(this, EventActivity.class));

    }
}