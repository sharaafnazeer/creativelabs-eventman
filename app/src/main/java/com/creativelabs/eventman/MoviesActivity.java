package com.creativelabs.eventman;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.creativelabs.eventman.adapters.MoviesAdapter;
import com.creativelabs.eventman.classes.GetMovies;
import com.creativelabs.eventman.classes.Movie;
import com.creativelabs.eventman.interfaces.IMovies;

import java.util.ArrayList;
import java.util.List;

public class MoviesActivity extends AppCompatActivity implements IMovies {

    RecyclerView rvMovies;
    MoviesAdapter adapter;

    List<Movie> movieList = new ArrayList<>();

    AlertDialog.Builder builder;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        rvMovies = findViewById(R.id.rvMovies);
        adapter = new MoviesAdapter();

        rvMovies.setAdapter(adapter);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        adapter.setMovieList(movieList);
        adapter.notifyDataSetChanged();


        progressDialog =  new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("I am fetching your data");
        progressDialog.show();

        new GetMovies(this).execute("http://demo3114135.mockable.io/movies");
    }

    @Override
    public void getMoviesCompleted(List<Movie> movies) {
        adapter.setMovieList(movies);
        adapter.notifyDataSetChanged();
        progressDialog.dismiss();
    }
}