package com.example.menusha.appointmentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by menusha on 4/10/17.
 */

public class editDeleteView extends AppCompatActivity {

    private Button btnDelete,btnSave;
    private EditText saveEdit;
     Database myDB;
    private String selectedTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editdelete);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnSave = (Button)findViewById(R.id.btnSave);
        saveEdit = (EditText)findViewById(R.id.saveEdit);
        myDB = new Database(this);

        Intent getIntent = getIntent();
        selectedTitle = getIntent.getStringExtra("TITLE");
        saveEdit.setText(selectedTitle);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = saveEdit.getText().toString();
                if(!title.equals("")){
                    myDB.updateDB(title,selectedTitle);
                }else {
                    Toast.makeText(editDeleteView.this, "You have to enter Title", Toast.LENGTH_LONG).show();

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB.deleteDB(selectedTitle);
                saveEdit.setText("");
                Toast.makeText(editDeleteView.this, "Successfully deleted from database!", Toast.LENGTH_LONG).show();

            }
        });


    }
}
