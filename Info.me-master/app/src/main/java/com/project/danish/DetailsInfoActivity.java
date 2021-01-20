package com.project.danish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class DetailsInfoActivity extends AppCompatActivity implements View.OnClickListener {
TextView UserName, WhatsappName, InstaName, FbName, SnapName;
ImageView delete;
CardView Cwa,Cfb,Csnap,Cig;
String wa,fb,sc,ig;
String id;
     DbHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_info);
        Intent intentExtras = getIntent();
//        String user = intentExtras.getStringExtra("username");
//        text.setText(user);
//
        delete = findViewById(R.id.delete);
        UserName = findViewById(R.id.usern);
        WhatsappName = findViewById(R.id.watext);
        InstaName = findViewById(R.id.igtext);
        FbName = findViewById(R.id.fbtext);
        SnapName = findViewById(R.id.sctext);
        Cwa = findViewById(R.id.waCard);
        Cwa.setOnClickListener(this);
        Cfb = findViewById(R.id.fbCard);
        Cfb.setOnClickListener(this);
        Cig = findViewById(R.id.igCard);
        Cig.setOnClickListener(this);
        Csnap = findViewById(R.id.scCard);
        Csnap.setOnClickListener(this);
        delete.setOnClickListener(this);
        HashMap<String,String> hashMap = (HashMap<String, String>) getIntent().getExtras().get("user");
//        Log.d("Key is"+hashMap.get("KEY_NAME"));
        wa = hashMap.get("wanumber");
        fb = hashMap.get("fbname");
        sc = hashMap.get("scname");
        ig = hashMap.get("igname");
        id = hashMap.get("id");
        UserName.setText(hashMap.get("username"));
        WhatsappName.setText(wa);
        InstaName.setText(ig);
        FbName.setText(fb);
        SnapName.setText(sc);
        db = new DbHandler(this);
    }

    @Override
    public void onClick(View v) {
        HashMap<String,String> hashMap = (HashMap<String, String>) getIntent().getExtras().get("user");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);

        switch(v.getId()){

            case R.id.delete:
                db.DeleteUser(id);
                startActivity(new Intent(DetailsInfoActivity.this,DetailsListActivity.class));
                finish();

                break;

        case R.id.waCard:
//            Toast.makeText(getApplicationContext(), "The value is " + wa, Toast.LENGTH_SHORT).show();
            intent.setData(Uri.parse("https://wa.me/" + wa));
            startActivity(intent);

            break;
        case R.id.fbCard:
            intent.setData(Uri.parse("https://facebook.com/" + fb));
            startActivity(intent);

            break;
        case R.id.scCard:
            intent.setData(Uri.parse("https://snapchat.com/add/" + sc));
            startActivity(intent);

            break;
        case R.id.igCard:
            intent.setData(Uri.parse("https://instagram.com/" + ig));
            startActivity(intent);

            break;
    }
    }

}
