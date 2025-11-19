package com.example.e_waste_app;

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
    PostAdapter adapter;
    List<UserPost> list;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        rv = v.findViewById(R.id.rvCards);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        list = generateSamplePosts();

        adapter = new PostAdapter(getContext(), list);
        rv.setAdapter(adapter);

        return v;
    }

    private List<UserPost> generateSamplePosts() {

        List<UserPost> items = new ArrayList<>();

        items.add(new UserPost(
                "John Doe",
                "john@gmail.com",
                "9876543210",
                "https://picsum.photos/200",
                "https://picsum.photos/600/300"
        ));

        items.add(new UserPost(
                "Alice Smith",
                "alice@gmail.com",
                "9876543211",
                "https://picsum.photos/240",
                "https://picsum.photos/601/301"
        ));

        items.add(new UserPost(
                "Bob Johnson",
                "bob@gmail.com",
                "9876543212",
                "https://picsum.photos/260",
                "https://picsum.photos/602/302"
        ));

        return items;
    }
}
