package com.example.kaka.experimenting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by kaka on 12/30/2017.
 */

public class MyReceiver extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "Your message has been sent", Toast.LENGTH_SHORT).show();

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(1000);

         // context.startService(new Intent(context,MyService.class));

//        String number = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getAll().get("number").toString();//MainActivity.myNumber;

        String number = PreferenceManager.getDefaultSharedPreferences(context).getString(MainActivity.NUMBER,"No NUMBER Found");
        //String number=MainActivity.numList.get(j++);
        String message = PreferenceManager.getDefaultSharedPreferences(context).getString(MainActivity.MESSAGE,"No MESSAGE Found");


//        Log.i("MA",MainActivity.myNumber+"<--number, messagge--->"+MainActivity.myMessage);

       // Toast.makeText(context, number+"ending"+message, Toast.LENGTH_SHORT).show();


       // for(int i=0;i<MainActivity.numList.size();i++) {
            try {
               // Log.i("MA",MainActivity.numList.get(0)+"<--number,before messagge--->"+message);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(MainActivity.numList.get(0), "", MainActivity.msgList.get(0), null, null);
               // Log.i("MA",MainActivity.numList.size()+" before size");
                if(MainActivity.numList.size()>0 && MainActivity.msgList.size()>0) {
                    MainActivity.numList.remove(0);
                    MainActivity.msgList.remove(0);
                    MainActivity.itemList.remove(0);

                }
               // Log.i("MA",MainActivity.numList.size()+" after size");
               // Log.i("MA",MainActivity.numList.get(0)+"<--number, after messagge--->"+message);
                //sms.sendTextMessage();


            } catch (Exception e) {
                Toast.makeText(context, MainActivity.numList.get(0) + " " + MainActivity.msgList.get(0), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

      //  }

    }
}
