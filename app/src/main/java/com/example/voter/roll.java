package com.example.voter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class roll extends AppCompatActivity implements View.OnClickListener{
ImageView ivbox,iiitlogo;
TextView tvroll;
ImageButton btconfirm;
    private String logtag= roll.class.getSimpleName();
public static String roll;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);
        ivbox = findViewById(R.id.ivbox);
        tvroll = findViewById(R.id.tvroll);
        btconfirm = findViewById(R.id.btconfirm);
        iiitlogo = findViewById(R.id.iiitlogo);
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(logtag,"confirm button is clicked for roll submission");
                startActivity(new Intent(roll.this, verify.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}