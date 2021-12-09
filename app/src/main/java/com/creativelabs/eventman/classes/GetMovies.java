package com.creativelabs.eventman.classes;

import android.os.AsyncTask;
import android.util.Log;

import com.creativelabs.eventman.interfaces.IMovies;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class GetMovies extends AsyncTask<String, Void, String> {

    IMovies iMovies;

    public GetMovies(IMovies iMovies) {
        this.iMovies = iMovies;
    }

    @Override
    protected String doInBackground(String... strings) {
        return getAllMovies(strings[0]);
    }

    @Override
    protected void onPostExecute(String results) {
        List<Movie> movies = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(results);

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);

                //implementation 'com.google.code.gson:gson:2.8.9'
                // GSON

                Gson gson = new Gson();
                Movie movie = gson.fromJson(object.toString(), Movie.class);
                movies.add(movie);
            }

            iMovies.getMoviesCompleted(movies);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getAllMovies(String urlString) {

        InputStream inputStream = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();

        } catch (Exception ex) {
            Log.d("ERROR", ex.getMessage());
            return null;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
