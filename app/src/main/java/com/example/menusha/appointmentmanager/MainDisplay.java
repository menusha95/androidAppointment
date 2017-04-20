package com.example.menusha.appointmentmanager;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class MainDisplay extends AppCompatActivity {
    private TextView dateText;
    private Button btnDone;
    Button btnTime,btnSave;
    static final int DIALOG_ID=0;
    int hour;
    int minutes;
    TextView timeText;
    EditText editDes,editTitle;
    Database myDB;


   // @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maindisplay);
        timePickerdialog();

      //  btnDone = (Button)findViewById(R.id.btnDone);
        dateText=(TextView)findViewById(R.id.dateText);
        dateText.setText(getIntent().getExtras().getString("me"));
        timeText = (TextView)findViewById(R.id.timeText);
        editDes = (EditText)findViewById(R.id.editDes);
        editTitle = (EditText)findViewById(R.id.editTitle);
        btnSave = (Button)findViewById(R.id.btnSave);
        editDes.setMovementMethod(new ScrollingMovementMethod());
        myDB = new Database(this);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newDates = dateText.getText().toString();
                String newTime = timeText.getText().toString();
                String newTitle = editTitle.getText().toString();
                String newDesc = editDes.getText().toString();

                if(editTitle.length()!=0) {
                    AddInfo(newDates,newTime,newTitle,newDesc);
                    dateText.setText("");
                    timeText.setText("");
                    editTitle.setText("");
                    editDes.setText("");

                }else {
                    Toast.makeText(MainDisplay.this,"You must enter a Description",Toast.LENGTH_LONG).show();

                }
            }
        });
        }
    public void AddInfo(String newDates, String newTime, String newTitle, String newDesc){
        boolean insertData = myDB.addInfo(newDates,newTime,newTitle,newDesc);

        if(insertData==true){
            Toast.makeText(MainDisplay.this,"Successfull!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainDisplay.this,"Something went wrong!",Toast.LENGTH_LONG).show();

        }

    }
    public void timePickerdialog(){
        btnTime = (Button)findViewById(R.id.btnTime);
        btnTime.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(DIALOG_ID);
                    }
                }
        );
    }
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOG_ID)
            return new TimePickerDialog(MainDisplay.this,Timepicker,hour,minutes,false);
        return null;
    }

    protected TimePickerDialog.OnTimeSetListener Timepicker = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour = hourOfDay;
            minutes = minute;
           // Toast.makeText(MainDisplay.this,hour+" : "+minutes,Toast.LENGTH_LONG).show();
            timeText.setText(hour+" : "+minutes);
        }
    };
}


