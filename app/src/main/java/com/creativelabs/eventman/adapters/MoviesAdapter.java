package com.creativelabs.eventman.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.creativelabs.eventman.MovieActivity;
import com.creativelabs.eventman.R;
import com.creativelabs.eventman.classes.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movieList = new ArrayList<>();

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_layout, parent, false);
        return new MoviesAdapter.ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = movieList.get(position);

        //Image - do it finally using piccaso
        holder.getTvMovieTitleYear().setText(movie.getTitle());
        holder.getTvCasts().setText(movie.getCast());
        holder.getTvDirectors().setText(movie.getDirector());

        Picasso.get()
                .load(movie.getImage())
                .error(R.drawable.image_not_available)
                .into(holder.getIvImage());

        holder.getCvRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movieIntent = new Intent(v.getContext(), MovieActivity.class);
                movieIntent.putExtra("MOVIE", movie);
                v.getContext().startActivity(movieIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        TextView tvMovieTitleYear;
        TextView tvCasts;
        TextView tvDirectors;
        CardView cvRoot;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = itemView.findViewById(R.id.ivImage);
            tvMovieTitleYear = itemView.findViewById(R.id.tvMovieTitleYear);
            tvCasts = itemView.findViewById(R.id.tvCasts);
            tvDirectors = itemView.findViewById(R.id.tvDirectors);
            cvRoot = itemView.findViewById(R.id.cvRoot);
        }

        public ImageView getIvImage() {
            return ivImage;
        }

        public TextView getTvMovieTitleYear() {
            return tvMovieTitleYear;
        }

        public TextView getTvCasts() {
            return tvCasts;
        }

        public TextView getTvDirectors() {
            return tvDirectors;
        }

        public CardView getCvRoot() {
            return cvRoot;
        }
    }
}
