package com.example.kaka.experimenting;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class AddingInList extends AppCompatActivity {



    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_in_list);

        lv= findViewById(R.id.listview);


       // listItems.add(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(MainActivity.LISTDATENTIME,"No NUMBER Found"));




                ArrayAdapter<String> adp=new ArrayAdapter<String>(AddingInList.this, android.R.layout.simple_list_item_1,  MainActivity.itemList);
                 lv.setAdapter(adp);


//        ArrayAdapter<String> adp=new ArrayAdapter<String>(AddingInList.this, android.R.layout.simple_list_item_1,  MainActivity.itemList);
//        lv.setAdapter(adp);

        //Collections.sort(MainActivity.itemList);


        for(int i=0;i<MainActivity.itemList.size();i++){
            for(int j=i+1;j<MainActivity.itemList.size();j++){
                if(MainActivity.itemList.get(i).compareTo(MainActivity.itemList.get(j))==1){


                    Collections.swap(MainActivity.itemList,i,j);
                    Collections.swap(MainActivity.numList,i,j);
                    Collections.swap(MainActivity.msgList,i,j);


                }
            }
        }

//        final String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(MainActivity.NUMBER,"No NUMBER Found");
//        final String message = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(MainActivity.MESSAGE,"No MESSAGE Found");



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    default:
                        Toast.makeText(getApplicationContext(),MainActivity.numList.get(i),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
