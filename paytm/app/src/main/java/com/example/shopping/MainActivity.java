package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.paytm.pgsdk.PaytmOrder;
import com.paytm.pgsdk.PaytmPaymentTransactionCallback;
import com.paytm.pgsdk.TransactionManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    String midString;
    String txnAmountString;
    String orderIdString;
    String txnTokenString;
    Button btnPayNow;

    Integer ActivityRequestCode = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnPayNow = (Button) findViewById(R.id.txnProcessBtn);
        btnPayNow.setOnClickListener(MainActivity.this);
    }

    public void btnProcessEvent (){
        progressBar.setVisibility(View.VISIBLE);

        EditText mid = (EditText) findViewById(R.id.midId);
        EditText orderId = (EditText) findViewById(R.id.orderId);
        EditText txnToken = (EditText) findViewById(R.id.txnTokenId);
        EditText txnAmount = (EditText) findViewById(R.id.txnAmountId);
        CheckBox environment = (CheckBox) findViewById(R.id.environmentCheckbox);



        txnAmountString = txnAmount.getText().toString();
        midString = mid.getText().toString();
        orderIdString = orderId.getText().toString();
        txnTokenString = txnToken.getText().toString();

        String host = "https://securegw-stage.paytm.in/";
        if(environment.isChecked()){
            host = "https://securegw.paytm.in/";
        }
        String errors = "";

        if(midString.equalsIgnoreCase("")){
            errors +="Enter valid MID here\n";
        }
        if(orderIdString.equalsIgnoreCase("")){
            errors +="Enter valid Order ID here\n";
        }
        if(txnTokenString.equalsIgnoreCase("")){
            errors +="Enter valid Txn Token here\n";
        }
        if(txnAmountString.equalsIgnoreCase("")){
            errors +="Enter valid Amount here\n";
        }
        Toast.makeText(this, errors, Toast.LENGTH_SHORT).show();


        progressBar.setVisibility(View.GONE);
        if(errors.equalsIgnoreCase("")){
            String orderDetails = "MID: " + midString + ", OrderId: " + orderIdString + ", TxnToken: " + txnTokenString + ", Amount: " + txnAmountString;
            Toast.makeText(this, orderDetails, Toast.LENGTH_SHORT).show();



            String callBackUrl = host + "theia/paytmCallback?ORDER_ID="+orderIdString;
            PaytmOrder paytmOrder = new PaytmOrder(orderIdString, midString, txnTokenString, txnAmountString, callBackUrl);
            TransactionManager transactionManager = new TransactionManager(paytmOrder, new PaytmPaymentTransactionCallback(){

                @Override
                public void onTransactionResponse(Bundle bundle) {
                    Toast.makeText(MainActivity.this, "Response (onTransactionResponse) : "+bundle.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void networkNotAvailable() {
                    Toast.makeText(MainActivity.this, "No Network Available Make Sure You Have Turned on Your Internet Connection ", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onErrorProceed(String s) {
                    Toast.makeText(MainActivity.this, "Error in payment", Toast.LENGTH_LONG).show();

                }

                @Override
                public void clientAuthenticationFailed(String s) {
                    Toast.makeText(MainActivity.this, "Client Authentication Failed", Toast.LENGTH_LONG).show();

                }

                @Override
                public void someUIErrorOccurred(String s) {
                    Toast.makeText(MainActivity.this, "U.I error", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onErrorLoadingWebPage(int i, String s, String s1) {
                    Toast.makeText(MainActivity.this, "Error Loading WebPage", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onBackPressedCancelTransaction() {
                    Toast.makeText(MainActivity.this, "Transaction Cancelled Due to Back Pressed", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onTransactionCancel(String s, Bundle bundle) {
                    Toast.makeText(MainActivity.this, "Transaction Cancelled", Toast.LENGTH_LONG).show();

                }
            });

            transactionManager.setShowPaymentUrl(host + "theia/api/v1/showPaymentPage");
            transactionManager.setAppInvokeEnabled(false);
            transactionManager.startTransaction(this, ActivityRequestCode);
        }



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityRequestCode && data != null) {
            Toast.makeText(this, data.getStringExtra("nativeSdkForMerchantMessage") + data.getStringExtra("response"), Toast.LENGTH_SHORT).show();
        }
    }

    public void onTransactionResponse(Bundle inResponse) {
        /*Display the message as below */
        Toast.makeText(getApplicationContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txnProcessBtn :
                btnProcessEvent();
                break;
        }
    }
}
