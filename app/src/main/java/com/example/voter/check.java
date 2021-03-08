package com.example.voter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class check extends AppCompatActivity {
    private String logtag= check.class.getSimpleName();
    Button btagree, btcancel;
    String or;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        {
            btagree = findViewById(R.id.btagree);
            btcancel = findViewById(R.id.btcancel);

        }
    }
    public void checkagree(View view) {
        Log.d(logtag,"Agreed button is clicked");
        //GETTING THE ORIENTATION OF THE SCREEN
        int orientation = getResources().getConfiguration().orientation;
        //check the different conditions are satisfied or not
        if(orientation == Configuration.ORIENTATION_PORTRAIT)
            startActivity(new Intent(check.this, intro.class));
    }

    public void checkcancel(View view) {
        Log.d(logtag,"Cancel button is clicked");
        check.this.finish();
        System.exit(0);
    }


    @Override
    public void onConfigurationChanged (@NonNull Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d(logtag,"orientation is Landscape");
            Toast.makeText(this, "change screen orientation", Toast.LENGTH_SHORT).show();
            check.this.finish();
            System.exit(0);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(logtag,"orientation is portrait");
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            or="true";
        }
    }



}

