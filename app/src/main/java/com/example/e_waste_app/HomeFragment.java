package com.example.e_waste_app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.*;
import java.util.*;

public class HomeFragment extends Fragment {

    RecyclerView rv;
    CardAdapter adapter;
    List<CardItem> list;

    public HomeFragment() {}

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup c, Bundle s) {
        View v = inflater.inflate(R.layout.fragment_home, c, false);
        rv = v.findViewById(R.id.rvCards);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        list = generateSample();
        adapter = new CardAdapter(list, getContext());
        rv.setAdapter(adapter);
        return v;
    }

    private List<CardItem> generateSample() {
        List<CardItem> items = new ArrayList<>();
        items.add(new CardItem("John Doe","Collects old electronics for recycling.", "https://picsum.photos/600/300"));
        items.add(new CardItem("Alice Smith","Grows sustainable produce.", "https://picsum.photos/601/301"));
        items.add(new CardItem("Bob Johnson","Creates eco friendly gifts.", "https://picsum.photos/602/302"));
        return items;
    }
}
