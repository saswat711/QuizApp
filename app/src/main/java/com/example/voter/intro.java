package com.example.voter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class intro extends AppCompatActivity implements View.OnClickListener{

ImageButton btadmin,btstart;
TextView tvwarn;
private String logtag= intro.class.getSimpleName();
  private int mcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btadmin= findViewById(R.id.btadmin);
        btstart= findViewById(R.id.btstart);
        tvwarn= findViewById(R.id.tvwarn);
        tvwarn.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {

    }

    public void getstarted(View view) {

        Log.d(logtag,"Get started button clicked");
        startActivity(new Intent(intro.this, roll.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    public void adminsection(View view) {
        if(mcount==0)
        {
            Log.d(logtag,"Admin button clicked for the first time");
            tvwarn.setVisibility(View.VISIBLE);
            mcount++;

        }
        else
        {
            Log.d(logtag,"Admin button clicked for the 2nd time");
            startActivity(new Intent(intro.this, admin.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            mcount=0;
        }
    }
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(logtag,"orientation changed");
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(intro.this, check.class));
        }


    }
}


