package com.example.e_waste_app;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView rv;
    CardAdapter adapter;
    List<CardItem> list;
    DatabaseHelper db;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rv = v.findViewById(R.id.rvCards);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        db = new DatabaseHelper(getContext());
        list = new ArrayList<>();

        // Load posts from DB
        Cursor cursor = db.getPosts();
        while(cursor.moveToNext()){
            int userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            byte[] imgBytes = cursor.getBlob(cursor.getColumnIndexOrThrow("image"));
            Bitmap bitmap = DatabaseHelper.getBitmapFromBytes(imgBytes);
            String userName = db.getUserNameById(userId); // method to get username by ID
            list.add(new CardItem(userName, bitmap));
        }
        cursor.close();

        adapter = new CardAdapter(list, getContext());
        rv.setAdapter(adapter);

        return v;
    }
}
