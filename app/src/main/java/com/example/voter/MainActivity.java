package com.example.voter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    java.util.Timer Timer;
private String logtag= MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timer = new Timer();
        Timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               Log.d(logtag,"sent to intro activity after 3000ms");
                               Intent intent = (new Intent(MainActivity.this, check.class));
                               startActivity(intent);
                               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                               finish();
                           }
                       },
                3000);
    }
}