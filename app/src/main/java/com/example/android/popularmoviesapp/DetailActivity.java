package com.example.android.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView mImage = (ImageView) findViewById(R.id.imageid);
        TextView mRate = (TextView) findViewById(R.id.rated_tv);
        TextView mDate = (TextView) findViewById(R.id.date_tv);
        TextView mOverview = (TextView) findViewById(R.id.overview_tv);
        TextView mTitle = (TextView) findViewById(R.id.titleId);


    }
}