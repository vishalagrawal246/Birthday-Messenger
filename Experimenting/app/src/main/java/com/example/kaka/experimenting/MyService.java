package com.example.kaka.experimenting;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kaka on 1/12/2018.
 */

public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {






        //mainActivity.on

//        String number=num.getText().toString();
//        String message=msg.getText().toString();
//        String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getAll().get("number").toString();//MainActivity.myNumber;
        String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(MainActivity.NUMBER,"No NUMBER Found");
        String message = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(MainActivity.MESSAGE,"No MESSAGE Found");
        Log.i("MA","got message= "+message+", and number= "+ number);
//        Log.i("MA",MainActivity.myNumber+"<--number, messagge--->"+MainActivity.myMessage);
        Toast.makeText(this, number+"ending"+message, Toast.LENGTH_SHORT).show();

        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number, "", message, null, null);
            //sms.sendTextMessage();


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), number+" "+message, Toast.LENGTH_SHORT).show();
        }
        return START_STICKY;
    }
}
