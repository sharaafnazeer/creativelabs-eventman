package com.creativelabs.eventman;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.creativelabs.eventman.classes.Movie;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MovieActivity extends AppCompatActivity {

    Movie movie;
    ImageView iVMovieImage;
    TextView tvMovieTitle, tvMovieCasts, tvMovieDirectors, tvMovieBgm, tvPlot;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        movie = (Movie) getIntent().getSerializableExtra("MOVIE");

        iVMovieImage = findViewById(R.id.iVMovieImage);

        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvMovieCasts = findViewById(R.id.tvMovieCasts);
        tvMovieDirectors = findViewById(R.id.tvMovieDirectors);
        tvMovieBgm = findViewById(R.id.tvBgm);
        tvPlot = findViewById(R.id.tvPlot);

        if (movie != null) {
            toolBarLayout.setTitle(movie.getTitle());

            Picasso.get()
                    .load(movie.getImage())
                    .fit()
                    .error(R.drawable.image_not_available)
                    .into(iVMovieImage);

            tvMovieTitle.setText(movie.getTitle() + " (" + movie.getYear() + ")");
            tvMovieCasts.setText("Casts : " + movie.getCast());
            tvMovieDirectors.setText("Director(s) : " + movie.getDirector());
            tvMovieBgm.setText("Background Score : " + movie.getBgm());

            tvPlot.setText(movie.getPlot());

        } else {
            toolBarLayout.setTitle("N/A");
        }
    }
}