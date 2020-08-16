package com.example.android.popularmoviesapp;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private static final String THE_URL = "https://api.themoviedb.org/3/movie";
    private static final String TMDB_URL= "https://image.tmdb.org/t/p/w185";
    private static final String apikey = "";
    private static final String API_KEY = "api_key";


    public static URL buildUrl(String sort) {
        Uri builtUri = Uri.parse(THE_URL).buildUpon()
                .appendEncodedPath(sort)
                .appendQueryParameter(API_KEY, apikey)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    //SOURCE: Sandwich-App-Starter-Code
    public static Movie[] parseMoviesJson(String json) throws JSONException {

        JSONObject jsonObject=new JSONObject(json);
        JSONArray resultsArray=jsonObject.getJSONArray("results");

        Movie[] results = new Movie[resultsArray.length()];

        for(int i=0;i<resultsArray.length();i++){
            Movie movie = new Movie();

            movie.setTitle(resultsArray.getJSONObject(i).getString("title"));
            movie.setDate(resultsArray.getJSONObject(i).getString("release_date"));
            movie.setOverview(resultsArray.getJSONObject(i).getString("overview"));
            movie.setRate(resultsArray.getJSONObject(i).getString("vote_average"));
            movie.setImage(TMDB_URL+(resultsArray.getJSONObject(i).getString("poster_path")));

            results[i] = movie;

        }
        return results;
    }
}
