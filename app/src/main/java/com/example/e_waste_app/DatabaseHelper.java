package com.example.e_waste_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "EWasteApp.db";
    private static final int DB_VERSION = 1;

    // Table for users
    private static final String TABLE_USERS = "users";
    private static final String COL_USER_ID = "id";
    private static final String COL_USER_NAME = "username";
    private static final String COL_USER_PASS = "password";

    // Table for posts
    private static final String TABLE_POSTS = "posts";
    private static final String COL_POST_ID = "id";
    private static final String COL_POST_USER_ID = "user_id";
    private static final String COL_POST_IMAGE = "image";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Users table
        String createUsers = "CREATE TABLE " + TABLE_USERS + " (" +
                COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USER_NAME + " TEXT, " +
                COL_USER_PASS + " TEXT)";
        db.execSQL(createUsers);

        // Posts table
        String createPosts = "CREATE TABLE " + TABLE_POSTS + " (" +
                COL_POST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_POST_USER_ID + " INTEGER, " +
                COL_POST_IMAGE + " BLOB, " +
                "FOREIGN KEY(" + COL_POST_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + COL_USER_ID + "))";
        db.execSQL(createPosts);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Insert new user
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USER_NAME, username);
        cv.put(COL_USER_PASS, password);
        return db.insert(TABLE_USERS, null, cv);
    }

    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COL_USER_ID + " FROM " + TABLE_USERS + " WHERE " + COL_USER_NAME + "=?",
                new String[]{email});
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }

    // Check login
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " +
                COL_USER_NAME + "=? AND " + COL_USER_PASS + "=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Insert post with image
    public long addPost(int userId, Bitmap image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_POST_USER_ID, userId);
        cv.put(COL_POST_IMAGE, getBytesFromBitmap(image));
        return db.insert(TABLE_POSTS, null, cv);
    }

    // Convert bitmap to byte[]
    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    // Retrieve posts (optional)
    public Cursor getPosts() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_POSTS, null);
    }

    // Get username by userId
    public String getUserNameById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM users WHERE id=?", new String[]{String.valueOf(userId)});
        String name = "Unknown";
        if(cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        return name;
    }


    // Convert byte[] to bitmap
    public static Bitmap getBitmapFromBytes(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
