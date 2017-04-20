package com.example.menusha.appointmentmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    TextView displayDate;
    CalendarView calendarView;
    Button btnView,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView)findViewById(R.id.calendarView);
        displayDate = (TextView)findViewById(R.id.displayDate);
        btnView = (Button)findViewById(R.id.btnView);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                displayDate.setText("You Selected : " + dayOfMonth +" - " + (month+1) + " - " + year);

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewList.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenti = new Intent(MainActivity.this,ViewList.class);
                startActivity(intenti);
            }
        });

    }



    public void appbutton(View view)
    {
        Intent intent = new Intent(MainActivity.this, MainDisplay.class);
        intent.putExtra("me",displayDate.getText().toString());
        startActivity(intent);
    }

}
