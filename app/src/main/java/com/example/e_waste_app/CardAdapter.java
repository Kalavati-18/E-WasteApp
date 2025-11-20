package com.example.e_waste_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.VH> {

    List<CardItem> list;
    Context ctx;

    public CardAdapter(List<CardItem> list, Context ctx){
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_post, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        CardItem item = list.get(position);
        holder.txtName.setText(item.getUserName());
        holder.imgPost.setImageBitmap(item.getImage());
        // Optional: set default profile image in imgProfile if needed
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VH extends RecyclerView.ViewHolder {
        ImageView imgPost, imgProfile;
        TextView txtName;

        public VH(@NonNull View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.imgPost);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
