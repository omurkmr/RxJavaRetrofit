package com.example.omurkumru.rxjava.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.omurkumru.rxjava.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    TextView titleTV,contentTV,authorTV;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        titleTV = itemView.findViewById(R.id.title_TV);
        contentTV = itemView.findViewById(R.id.content_TV);
        authorTV = itemView.findViewById(R.id.author_TV);
    }
}
