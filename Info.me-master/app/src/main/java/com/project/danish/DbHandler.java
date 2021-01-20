package com.project.danish;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 4;
    private static final String DB_NAME = "usersdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_uname = "username";
    private static final String KEY_WA = "wanumber";
    private static final String KEY_IG = "igname";
    private static final String KEY_FB = "fbname";
    private static final String KEY_SC = "scname";

    public DbHandler(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_uname + " TEXT,"
                + KEY_WA + " TEXT,"
                + KEY_IG + " TEXT,"
                + KEY_FB + " TEXT,"
                + KEY_SC + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }
    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    void insertUserDetails(String keyname, String username, String wanumber, String igname , String fbname, String scname){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
//        cValues.put(KEY_ID,keyId);
        cValues.put(KEY_NAME, keyname);
        cValues.put(KEY_uname, username);
        cValues.put(KEY_WA, wanumber);
        cValues.put(KEY_IG, igname);
        cValues.put(KEY_FB, fbname);
        cValues.put(KEY_SC, scname);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }
    // Get User Details
    public ArrayList<HashMap<String, String>> GetUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, keyname, username, wanumber, igname, fbname, scname FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);

        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("id",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("keyname",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("username",cursor.getString(cursor.getColumnIndex(KEY_uname)));
            user.put("wanumber",cursor.getString(cursor.getColumnIndex(KEY_WA)));
            user.put("igname",cursor.getString(cursor.getColumnIndex(KEY_IG)));
            user.put("fbname",cursor.getString(cursor.getColumnIndex(KEY_FB)));
            user.put("scname",cursor.getString(cursor.getColumnIndex(KEY_SC)));
            userList.add(user);
        }
        return  userList;
    }
    // Get User Details based on userid
    public ArrayList<HashMap<String, String>> GetUserByUserId(int userid){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT keyname, username, wanumber, igname, fbname, scname FROM "+ TABLE_Users;
        Cursor cursor = db.query(TABLE_Users, new String[]{KEY_NAME, KEY_WA, KEY_IG, KEY_FB, KEY_SC}, KEY_ID+ "=?",new String[]{String.valueOf(userid)},null, null, null, null);
        if (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("keyid",cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("keyname",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.put("username",cursor.getString(cursor.getColumnIndex(KEY_uname)));
            user.put("wanumber",cursor.getString(cursor.getColumnIndex(KEY_WA)));
            user.put("igname",cursor.getString(cursor.getColumnIndex(KEY_IG)));
            user.put("fbname",cursor.getString(cursor.getColumnIndex(KEY_FB)));
            user.put("scname",cursor.getString(cursor.getColumnIndex(KEY_SC)));
            userList.add(user);
        }
        return  userList;
    }
    // Delete User Details
    public void DeleteUser(String userid){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_ID+" = ?",new String[]{userid});
        db.close();
    }
    // Update User Details
    public int UpdateUserDetails(String wanumber, String igname , String fbname, String scname, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_WA, wanumber);
        cVals.put(KEY_IG, igname);
        cVals.put(KEY_FB, fbname);
        cVals.put(KEY_SC, scname);
        int count = db.update(TABLE_Users, cVals, KEY_ID+" = ?",new String[]{String.valueOf(id)});
        return  count;
    }

    public Integer deletData(String id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return  db.delete(TABLE_Users, KEY_ID+" = ?",new String[] {id});
    }

}
