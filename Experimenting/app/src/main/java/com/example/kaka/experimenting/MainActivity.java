package com.example.kaka.experimenting;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends Activity {


    //ArrayList<AllList> myList = new ArrayList<>();



    static ArrayList<String> itemList=new ArrayList<String>();
    static ArrayList<String> numList=new ArrayList<String>();
    static ArrayList<String> msgList=new ArrayList<String>();


    public static String myNumber = "";
    public static String myMessage = "";
    //public static String listDatenTime = "";


    static DatePicker datePicker;
    static TimePicker timePicker;

    Context context;

    Button start, add, show, loki, newstart;


    EditText num, msg;

    // final static int RQS_1 = 1;


    SharedPreferences sharedPreferences;

    public static final String NUMBER = "number";
    public static final String MESSAGE = "message";
    //public static final String LISTDATENTIME = "listdate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;



        datePicker = findViewById(R.id.dateid);
        timePicker = findViewById(R.id.timeid);

        num = findViewById(R.id.number);
        msg = findViewById(R.id.msgg);

        start = findViewById(R.id.startid);
        add = findViewById(R.id.add);
        show = findViewById(R.id.show);
        loki = findViewById(R.id.lokibtn);
        newstart = findViewById(R.id.newstart);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        if (sharedPreferences.contains(NUMBER)) {
            num.setText(sharedPreferences.getString(NUMBER, ""));
        }

        if (sharedPreferences.contains(MESSAGE)) {
            msg.setText(sharedPreferences.getString(MESSAGE, ""));
        }


        Calendar now = Calendar.getInstance();
        datePicker.init(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                null);

        timePicker.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(now.get(Calendar.MINUTE));


//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                myNumber = (num.getText().toString());
//                myMessage = msg.getText().toString();
//                SharedPreferences.Editor editor=sharedPreferences.edit();
//
//                editor.putString(NUMBER,myNumber);
//                editor.putString(MESSAGE,myMessage);
//                editor.commit();
//
//
//                Calendar current = Calendar.getInstance();
//
//                Calendar cal = Calendar.getInstance();
//                cal.set(datePicker.getYear(),
//                        datePicker.getMonth(),
//                        datePicker.getDayOfMonth(),
//                        timePicker.getCurrentHour(),
//                        timePicker.getCurrentMinute(),
//                        00);
//
//                if (cal.compareTo(current) <= 0) {
//                    //The set Date/Time already passed
//                    Toast.makeText(getApplicationContext(),
//                            "Invalid Date/Time",
//                            Toast.LENGTH_LONG).show();
//                } else {
//                    setAlarm(cal);
//                   // setdatelist(cal);
//                }
//
//
//            }
//        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myNumber = (num.getText().toString());
                myMessage = msg.getText().toString();

                String nnnn=myNumber;
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(NUMBER, myNumber);
                editor.putString(MESSAGE, myMessage);
                editor.commit();

                Calendar current2 = Calendar.getInstance();

                Calendar cal2 = Calendar.getInstance();
                cal2.set(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute(),
                        00);




                if (cal2.compareTo(current2) <= 0 ) {
                    //The set Date/Time already passed
                    Toast.makeText(getApplicationContext(),
                            "Invalid Date/Time",
                            Toast.LENGTH_SHORT).show();
                } else {
                    setdatelist(cal2);

                }


            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(MainActivity.this, AddingInList.class);
                startActivity(intent1);
            }
        });


        newstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar current3 = Calendar.getInstance();
                for (int i = 0; i < itemList.size(); i++) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");

                    try {
                        Date date = sdf.parse(itemList.get(i));
                        Calendar newcal = Calendar.getInstance();
                        newcal.setTime(date);

                        if (newcal.compareTo(current3) <= 0) {
                            //The set Date/Time already passed
                            Toast.makeText(getApplicationContext(),
                                    "Invalid Date/Time",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            setAlarm(newcal);

                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }


    private void setAlarm(Calendar targetCal) {


        Intent intent = new Intent(getBaseContext(), MyReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), _id, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Reminder has set", Toast.LENGTH_SHORT).show();


        //Intent broadcastedIntent=new Intent(this, MyReceiver.class);

    }

    private void setdatelist(Calendar cal2) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd   HH:mm");
        //        myNumber = (num.getText().toString());
//        myMessage = msg.getText().toString();
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.putString(NUMBER,myNumber);
//        editor.putString(MESSAGE,myMessage);
//        editor.commit();

        //itemList.add(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(MainActivity.NUMBER,"No NUMBER Found"));
        itemList.add(sdf.format(cal2.getTime()));

        //itemList.add(PreferenceManager.getDefaultSharedPreferences(context).getString(MainActivity.LISTDATENTIME, "No NUMBER Found"));
        numList.add(PreferenceManager.getDefaultSharedPreferences(context).getString(MainActivity.NUMBER, "No NUMBER Found"));
        msgList.add(PreferenceManager.getDefaultSharedPreferences(context).getString(MainActivity.MESSAGE, "No MESSAGE Found"));




//        AllList allList = new AllList(listDatenTime, PreferenceManager.getDefaultSharedPreferences(context).getString(MainActivity.NUMBER, "No NUMBER Found"));
//        myList.add(allList);
//
//        Collections.sort(myList);

    }



//    public void setAlarmLoki(View v) {
//
//        Log.i("MA","loki method called");
//
//        myNumber = (num.getText().toString());
//        myMessage = msg.getText().toString();
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//
//        editor.putString(NUMBER,myNumber);
//        editor.putString(MESSAGE,myMessage);
//        editor.commit();
//
//
//        Log.i("MA",myNumber+"<--number, messagge--->"+myMessage);
//        Calendar targetCal = Calendar.getInstance();
//
//        Calendar cal = Calendar.getInstance();
//        cal.set(datePicker.getYear(),
//                datePicker.getMonth(),
//                datePicker.getDayOfMonth(),
//                timePicker.getCurrentHour(),
//                timePicker.getCurrentMinute(),
//                30);
//
//        Intent intent = new Intent(getBaseContext(), MyReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
//        Toast.makeText(this, "Reminder has set", Toast.LENGTH_SHORT).show();
//    }


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this, "ondestroy", Toast.LENGTH_SHORT).show();
//
//
//
//    }
}



