package com.example.e_waste_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    Context context;
    List<UserPost> list;

    public PostAdapter(Context context, List<UserPost> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserPost post = list.get(position);

        holder.txtName.setText(post.getUserName());

        Picasso.get()
                .load(post.getProfileImage())
                .placeholder(R.drawable.ic_person)
                .into(holder.imgProfile);

        Picasso.get()
                .load(post.getPostImage())
                .placeholder(R.drawable.ic_image)
                .into(holder.imgPost);

        // Click listener on post image
        holder.imgPost.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetailActivity.class);
            intent.putExtra("name", post.getUserName());
            intent.putExtra("email", post.getUserEmail());
            intent.putExtra("phone", post.getUserPhone());
            intent.putExtra("avatarUrl", post.getProfileImage()); // matching key
            intent.putExtra("imageUrl", post.getPostImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        ImageView imgProfile, imgPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            imgPost = itemView.findViewById(R.id.imgPost);
        }
    }
}
