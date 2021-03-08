package com.example.voter;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class verify extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
    }



    public void scanfinger(View view) {
        Toast.makeText(this, "okay", Toast.LENGTH_SHORT).show();
    }

    public void scanqr(View view) {
    }
}