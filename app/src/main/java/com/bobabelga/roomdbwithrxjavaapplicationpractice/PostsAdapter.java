package com.bobabelga.roomdbwithrxjavaapplicationpractice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder> {
    private ArrayList<Post> postArrayList= new ArrayList<>();
    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.title.setText(postArrayList.get(position).getTitle());
        holder.body.setText(postArrayList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public void setPostArrayList(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView title,body;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title_textView);
            body = itemView.findViewById(R.id.item_body_textView);
        }
    }
}
