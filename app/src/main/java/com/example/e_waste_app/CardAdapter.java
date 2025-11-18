package com.example.e_waste_app;

import android.content.Context;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.*;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.VH> {
    List<CardItem> list; Context ctx;
    public CardAdapter(List<CardItem> l, Context c){ list=l; ctx=c; }
    @NonNull @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_card, parent, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(@NonNull VH h, int pos) {
        CardItem it = list.get(pos);
        h.tvName.setText(it.name);
        h.tvDesc.setText(it.desc);
        Picasso.get().load(it.imageUrl).into(h.imgLarge);
    }
    @Override public int getItemCount(){ return list.size();}
    static class VH extends RecyclerView.ViewHolder {
        ImageView imgAvatar, imgLarge; TextView tvName, tvDesc;
        VH(View v){ super(v);
            imgAvatar = v.findViewById(R.id.imgAvatar);
            imgLarge = v.findViewById(R.id.imgLarge);
            tvName = v.findViewById(R.id.tvName);
            tvDesc = v.findViewById(R.id.tvDesc);
        }
    }
}
