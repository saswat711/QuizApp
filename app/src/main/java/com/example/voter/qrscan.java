package com.example.voter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class qrscan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scanner;
Integer n=1;
TextView tvtoast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanner = new ZXingScannerView(this);
        setContentView(scanner);
        scanner.setResultHandler(this);
        tvtoast = findViewById(R.id.tvtoast);
        tvtoast.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        scanner.setResultHandler(this);
        scanner.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanner.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        onBackPressed();
        if (result.getText().equals("SPAAClub")){
            startActivity(new Intent(qrscan.this, vote.class));
        }
        else
        {
            while(n<=3) {
                tvtoast.setVisibility(View.VISIBLE);
                Toast toast = Toast.makeText(getApplicationContext(), "Failed Authentication You Have" + n + "Times Left", Toast.LENGTH_LONG);
                toast.show();
                toast.setView(tvtoast);
            }n++;


        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
