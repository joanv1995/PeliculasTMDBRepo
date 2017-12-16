package com.example.usuario.peliculastmdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.usuario.peliculastmdb.R;
import com.example.usuario.peliculastmdb.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by usuario on 15/12/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>  {

    public static final String IMAGE_URL_BASE_PATH="http://image.tmdb.org/t/p/w342//";
    private List<Movie> movies;
    private int rowLayout;
    private Context context;

    public MoviesAdapter(List<Movie> pl, int rowLayout, Context context) {
        this.movies = pl;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {

        if (movies.size()!=0)
        {
            holder.title.setText(movies.get(position).getTitle());
            String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();

            Picasso.with(context)
                    .load(image_url)
                    .placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }





    //A view holder inner class where we get reference to the views in the layout using their ID
    public static class MoviesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView title;
        ImageView poster;



        public MoviesViewHolder (View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            title = (TextView) v.findViewById(R.id.titulo);
            poster = (ImageView) v.findViewById(R.id.movieImage);
        }
    }
}
