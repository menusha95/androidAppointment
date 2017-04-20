package com.example.menusha.appointmentmanager;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by menusha on 4/8/17.
 */

public class ViewList extends AppCompatActivity {

    Database myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        ListView listViewDes=(ListView)findViewById(R.id.listViewDes);
        myDB = new Database(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getList();

        if(data.getCount()==0) {
            Toast.makeText(ViewList.this, "Database is Empty", Toast.LENGTH_LONG).show();
        }else{
            while (data.moveToNext()) {
                theList.add(data.getString(2));
            }
                ListAdapter lsitAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listViewDes.setAdapter(lsitAdapter);

                listViewDes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String name =  adapterView.getItemAtPosition(position).toString();

                        Cursor data = myDB.getTitle(name);
                            Toast.makeText(ViewList.this, "Showing "+name, Toast.LENGTH_LONG).show();
                            Intent editDeleteView = new Intent(ViewList.this, editDeleteView.class);
                            editDeleteView.putExtra("TITLE",name);
                            startActivity(editDeleteView);



                    }
                });

            }

        }
    }

