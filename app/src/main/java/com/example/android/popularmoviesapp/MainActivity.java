package com.example.android.popularmoviesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements ImageAdapter.ImageAdapterOnClickHandler {
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private Movie[] simplemovieData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SOURCE: https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the
        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewid);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        new MovieTask().execute("popular");



    }

    @Override
    public void onClick(int position) {
        Context context = this;
        Class destinationClass = DetailActivity.class;

        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, position);
        intentToStartDetailActivity.putExtra("title", simplemovieData[position].getTitle());
        intentToStartDetailActivity.putExtra("poster", simplemovieData[position].getImage());
        intentToStartDetailActivity.putExtra("rate", simplemovieData[position].getRate());
        intentToStartDetailActivity.putExtra("release", simplemovieData[position].getDate());
        intentToStartDetailActivity.putExtra("overview", simplemovieData[position].getOverview());

        startActivity(intentToStartDetailActivity);

    }

    public class MovieTask extends AsyncTask<String, Void, Movie[]>
    {

        @Override
        protected Movie[] doInBackground(String... strings) {
            if (strings.length == 0) {
                return null;
            }
            URL movieUrl = NetworkUtils.buildUrl(strings[0]);
            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieUrl);

                simplemovieData = NetworkUtils
                        .parseMoviesJson(jsonMovieResponse);

                return simplemovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
            if (movies != null) {

                recyclerView.setAdapter(new ImageAdapter(movies,MainActivity.this));
            }
        }
    }
}