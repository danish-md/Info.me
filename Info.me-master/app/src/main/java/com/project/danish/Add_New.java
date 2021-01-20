package com.project.danish;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Add_New extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    String TAG="QRREADER";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)


        // call the alert dialog
        Alert(rawResult);

    }


    public void Alert(final Result rawResult){
        AlertDialog.Builder builder = new AlertDialog.Builder(Add_New.this);
        builder.setTitle("Qr scan result");
        builder.setMessage("Result :"+rawResult.getText()+"\nType :"+rawResult.getBarcodeFormat().toString())
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // back to previous activity
                        Intent intent = new Intent(Add_New.this, DetailsListActivity.class);
                        DbHandler dbHandler = new DbHandler(Add_New.this);

                        String addRes = rawResult.getText();
                        String[] arrayString = addRes.split("\n");
                        String username = arrayString[0];
                        String wanumber = arrayString[1];
                        String igname = arrayString[2];
                        String fbname = arrayString[3];
                        String scname = arrayString[4];

                        String keyname = "INFO.ME key"   ;
                        username = username.substring(username.indexOf("UserInfo:")+ 9, username.length()) ;
                        wanumber = wanumber.substring(wanumber.indexOf("WaInfo:") + 7, wanumber.length());
                        igname = igname.substring(igname.indexOf("IgInfo:")+ 7);
                        fbname = fbname.substring(fbname.indexOf("FbInfo:")+7);
                        scname = scname.substring(scname.indexOf("SnapInfo")+9);
                        dbHandler.insertUserDetails(keyname, username, wanumber, igname, fbname, scname);


//                        intent.putExtra("res",rawResult.getText());
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Scan Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
// If you would like to resume scanning, call this method below:
                        mScannerView.resumeCameraPreview(Add_New.this);
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();
    }
}