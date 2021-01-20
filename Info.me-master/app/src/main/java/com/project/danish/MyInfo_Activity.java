package com.project.danish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class MyInfo_Activity extends AppCompatActivity implements View.OnClickListener {
    EditText UserName, WhatsappInput, WhatsappCode, InstaInput, FbInput, SnapInput;
    Button WhatsAppButton, InstaButton, FbButton, wrapbutton, editButton, SnapButton, editDone, details;
    Switch switchwa, switchig, switchfb, switchsc;
    boolean bwa, bfb, bsc, big;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String User = "userKey";
    public static final String Wa1 = "wa1Key";
    public static final String Wa2 = "wa2Key";
    public static final String Insta = "instaKey";
    public static final String Fb = "fbKey";
    public static final String Snap = "snapKey";

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        UserName = findViewById(R.id.username);
        UserName.setEnabled(false);


        WhatsappInput = findViewById(R.id.WAInput);
        WhatsAppButton = findViewById(R.id.WAButton);
        switchwa = findViewById(R.id.switchwa);
        WhatsAppButton.setOnClickListener(this);
        WhatsappInput.setEnabled(false);

        editDone = findViewById(R.id.EditDone);
        editDone.setOnClickListener(this);

        WhatsappCode = findViewById(R.id.WAInputCode);
        WhatsappCode.setEnabled(false);

        InstaInput = findViewById(R.id.InstaTextId);
        InstaButton = findViewById(R.id.InstaButton);
        switchig = findViewById(R.id.switchig);

        InstaButton.setOnClickListener(this);
        InstaInput.setEnabled(false);

        SnapInput = findViewById(R.id.SnapText);
        SnapButton = findViewById(R.id.SnapButton);
        switchsc = findViewById(R.id.switchsc);
        SnapButton.setOnClickListener(this);
        SnapInput.setEnabled(false);


        FbButton = findViewById(R.id.FBButton);
        FbButton.setOnClickListener(this);
        switchfb = findViewById(R.id.switchfb);
        FbInput = findViewById(R.id.FbTextId);
        FbInput.setEnabled(false);
        wrapbutton = findViewById(R.id.wrappingbutton);
        wrapbutton.setOnClickListener(this);

        editButton = findViewById(R.id.EditBut);
        editButton.setOnClickListener(this);

        details = findViewById(R.id.details);
        details.setOnClickListener(this);

        // username = UserName.getText().toString();
        //insta = InstaName.getText().toString();
        //fb = FbName.getText().toString();
        //WA = WhatsappName.getText().toString();

//      label.setText("Saved");

    }


    @Override
    protected void onStart() {
        super.onStart();
        String userDef = sharedpreferences.getString(User, "");
        UserName.setText(userDef);

        String wa1Def = sharedpreferences.getString(Wa1, "");
        WhatsappCode.setText(wa1Def);
        String wa2Def = sharedpreferences.getString(Wa2, "");
        WhatsappInput.setText(wa2Def);
        String instaDef = sharedpreferences.getString(Insta, "");
        InstaInput.setText(instaDef);

        String snapDef = sharedpreferences.getString(Snap, "");
        SnapInput.setText(snapDef);

        String fbDef = sharedpreferences.getString(Fb, "");
        FbInput.setText(fbDef);


    }

    @Override
    public void onClick(View v) {
        String user = UserName.getText().toString();
        String waBar1 = WhatsappCode.getText().toString();
        String waBar2 = WhatsappInput.getText().toString();
        String waBar = "";
        String instaBar = "";
        String fbBar = "";
        String snapBar = "";

        if (switchwa.isChecked()) {
            waBar = waBar1 + waBar2;
        }
        if (switchig.isChecked()) {
            instaBar = InstaInput.getText().toString();
        }
        if (switchfb.isChecked()) {
            fbBar = FbInput.getText().toString();
        }
        if (switchsc.isChecked()) {
            snapBar = SnapInput.getText().toString();

        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        switch (v.getId()) {


            case R.id.EditDone:
                editButton.setVisibility(View.VISIBLE);
                editDone.setVisibility(View.GONE);


                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(User, user);
                editor.putString(Insta, instaBar);
                editor.putString(Fb, fbBar);
                editor.putString(Snap, snapBar);
                editor.putString(Wa1, waBar1);
                editor.putString(Wa2, waBar2);
                editor.commit();


                InstaInput.setEnabled(false);
                FbInput.setEnabled(false);
                SnapInput.setEnabled(false);
                WhatsappInput.setEnabled(false);
                WhatsappCode.setEnabled(false);
                UserName.setEnabled(false);

                WhatsAppButton.setEnabled(true);
                InstaButton.setEnabled(true);
                FbButton.setEnabled(true);
                SnapButton.setEnabled(true);
                wrapbutton.setEnabled(true);
                details.setEnabled(true);


                break;
            case R.id.EditBut:
                editButton.setVisibility(View.GONE);
                editDone.setVisibility(View.VISIBLE);
                InstaInput.setEnabled(true);
                FbInput.setEnabled(true);
                SnapInput.setEnabled(true);
                WhatsappInput.setEnabled(true);
                UserName.setEnabled(true);
                WhatsappCode.setEnabled(true);

                WhatsAppButton.setEnabled(false);
                InstaButton.setEnabled(false);
                FbButton.setEnabled(false);
                SnapButton.setEnabled(false);
                wrapbutton.setEnabled(false);
                details.setEnabled(false);

                break;

            case R.id.WAButton:
//                String WAID1 = WhatsappCode.getText().toString();
//                String WAID2 = WhatsappName.getText().toString();
//                String WAID = WAID1+WAID2;
                Toast.makeText(getApplicationContext(), "The value is " + waBar, Toast.LENGTH_SHORT).show();
                intent.setData(Uri.parse("https://wa.me/" + waBar));
                startActivity(intent);
                break;
            case R.id.InstaButton:
//                String InstaID = InstaName.getText().toString();
                Toast.makeText(getApplicationContext(), "The value is " + instaBar, Toast.LENGTH_SHORT).show();
                intent.setData(Uri.parse("https://www.instagram.com/" + instaBar));
                startActivity(intent);
                break;

            case R.id.FBButton:
//                String FbID = FbName.getText().toString();
                Toast.makeText(getApplicationContext(), "The value is " + instaBar, Toast.LENGTH_SHORT).show();
                intent.setData(Uri.parse("https://facebook.com/" + fbBar));
                startActivity(intent);
                break;


            case R.id.SnapButton:
//                String SnapId = SnapName.getText().toString();
                Toast.makeText(getApplicationContext(), "The value is " + snapBar, Toast.LENGTH_SHORT).show();
                intent.setData(Uri.parse("https://snapchat.com/add/" + snapBar));
                startActivity(intent);
                break;


            case R.id.wrappingbutton:
                String BarVal = "UserInfo:" + user.toUpperCase() + "\n WaInfo:" + waBar + "\n IgInfo:" + instaBar + "\n FbInfo:" + fbBar + "\n SnapInfo:" + snapBar;
                Toast.makeText(getApplicationContext(), "The value is " + BarVal, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, QrCode_Activity.class);
                intent1.putExtra("BarValue", BarVal);
                startActivity(intent1);
                break;


            case R.id.details:
                String keyname = "key";
                String username = UserName.getText().toString().toUpperCase() + "\n";

                DbHandler dbHandler = new DbHandler(MyInfo_Activity.this);
                dbHandler.insertUserDetails(keyname, username, waBar, instaBar, fbBar, snapBar);
                intent = new Intent(MyInfo_Activity.this, DetailsListActivity.class);
                startActivity(intent);

        }

    }


}







/*    public Intent newInstagramProfileIntent(PackageManager pm,String url) {


        final Intent intent1 = new Intent(Intent.ACTION_VIEW);
        try {
            if (pm.getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent1.setData(Uri.parse("http://instagram.com/_u/" + InstaName.getText()));
                intent1.setPackage("com.instagram.android");
                return intent1;
            }
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        intent1.setData(Uri.parse(url));
        return intent1;
    }*/


