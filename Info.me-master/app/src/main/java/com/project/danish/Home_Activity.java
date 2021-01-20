package com.project.danish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.qrcode.encoder.QRCode;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener {
    static final Integer CAMERA = 0x5;
    Button myInfo, myFriends;
    ImageView addNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        myInfo = findViewById(R.id.myinfobutn);
        addNew = findViewById(R.id.addnewbutn);
        myFriends = findViewById(R.id.myfrndbutn);

        myInfo.setOnClickListener(this);
        addNew.setOnClickListener(this);
        myFriends.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myinfobutn:
                Intent intent = new Intent(getApplicationContext(), MyInfo_Activity.class);
                startActivity(intent);

                break;
            case R.id.addnewbutn:

                askForPermission(Manifest.permission.CAMERA,CAMERA);

                break;

            case R.id.myfrndbutn:
                intent = new Intent(getApplicationContext(), DetailsListActivity.class);

                startActivity(intent);
                break;
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(Home_Activity.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Home_Activity.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Home_Activity.this, new String[]{permission}, requestCode);

            } else {
                ActivityCompat.requestPermissions(Home_Activity.this, new String[]{permission}, requestCode);
            }
        } else {
            Intent intent;
            intent = new Intent(getApplicationContext(), Add_New.class);
            startActivity(intent);

    }
    }
}
