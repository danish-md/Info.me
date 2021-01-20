package com.project.danish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class DetailsListActivity extends AppCompatActivity {
    Intent intent;
     ArrayList<HashMap<String, String>> userList;
      ListAdapter adapter;
    ListView lv;
    //TextView username, whatsnumber,igramname,snapname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        username = findViewById(R.id.name);
//        whatsnumber = findViewById(R.id.wanumber);
//        igramname = findViewById(R.id.igname);
//        snapname = findViewById(R.id.scname);

        final DbHandler db = new DbHandler(this);
        userList = db.GetUsers();
        Collections.reverse(userList);
        adapter = new SimpleAdapter(DetailsListActivity.this, userList, R.layout.listrow,new String[]{"username","wanumber","igname","fbname","scname"}, new int[]{R.id.name, R.id.wanumber, R.id.igname, R.id.fbname,R.id.scname});
         lv = findViewById(R.id.user_list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(DetailsListActivity.this,DetailsInfoActivity.class);
                intent.putExtra("user", userList.get(position));
                startActivity(intent);
                finish();
            }
        });
        ImageView delete = lv.findViewById(R.id.delete);

        lv.deferNotifyDataSetChanged();

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                adapter.
//            }
//        });
//        delete.setItem(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                HashMap<String,String> user = userList.get(position);
//                user.get("id");
//                db.DeleteUser(Integer.parseInt(userList.get(position).get("id")));
//
//            }
//        });
        Button back = findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}