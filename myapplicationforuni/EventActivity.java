package com.example.myapplicationforuni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initWidgets();
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalenderUtils.formattedDate(CalenderUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalenderUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view)
    {
        SQLLiteManager sqLiteManager = SQLLiteManager.instanceOfDatabase(this);
        String eventName = eventNameET.getText().toString();;
        Event newEvent = new Event(eventName,CalenderUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
        sqLiteManager.addNoteToDatabase(newEvent);
        finish();
    }
}