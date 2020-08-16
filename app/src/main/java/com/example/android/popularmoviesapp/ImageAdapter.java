package com.example.android.popularmoviesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

//SOURCE 1: https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the
//SOURCE 2: Sunshine App code has been used.


    public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

        private Movie[] movieData;
        private LayoutInflater mInflater;
        private final ImageAdapterOnClickHandler mClickHandler;

        public interface ImageAdapterOnClickHandler {
            void onClick(int position);
        }

        // data is passed into the constructor
        public ImageAdapter(Movie[] movie, ImageAdapterOnClickHandler clickHandler) {
            movieData=movie;
            mClickHandler = clickHandler;
        }

        // inflates the cell layout from xml when needed
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.movies_list_item, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each cell
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Picasso.get()
                    .load(movieData[position].getImage())
                    .into(holder.movieImageView);
        }

        // total number of cells
        @Override
        public int getItemCount()  {
            if (null == movieData) return 0;
        return movieData.length;
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView movieImageView;

            ViewHolder(View itemView) {
                super(itemView);
                movieImageView = (ImageView) itemView.findViewById(R.id.movieListId);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                mClickHandler.onClick(getAdapterPosition());
            }


        }



        // parent activity will implement this method to respond to click events
        //

    }

