package com.example.usuario.peliculastmdb.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.usuario.peliculastmdb.R;
import com.example.usuario.peliculastmdb.adapter.MoviesAdapter;
import com.example.usuario.peliculastmdb.model.Movie;
import com.example.usuario.peliculastmdb.model.ResponseMovies;
import com.example.usuario.peliculastmdb.service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    private TextView totalMovies;
    private List<Movie> movies = new ArrayList<>();
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = "935f1bab4e9fedf4228be83b325da942";
    private String queryString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent in = getIntent();
        queryString = in.getStringExtra("clave");
        totalMovies = (TextView) findViewById(R.id.total);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectApi();

    }
        public void connectApi(){
            final ProgressDialog pd;
            pd = new ProgressDialog(ResultActivity.this);
            pd.setMax(100);
            pd.setMessage("Carregant resultats...");
            //pd.setTitle("");
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            // show it
            pd.show();
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            ApiService service = retrofit.create(ApiService.class);
            Call<ResponseMovies> call = service.getSearch(API_KEY,queryString);

            //Call<ResponseMovies> call = service.getTopRatedMovies(API_KEY);
            call.enqueue(new Callback<ResponseMovies>() {
                @Override
                public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
                    movies = response.body().getResults();
                    pd.dismiss();
                    totalMovies.setText("S'han trobat un total de "+movies.size()+" películes");
                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movies_layout, getApplicationContext()));

                    Log.d(TAG, "Number of movies recived: "+movies.size());
                   //movies = response.body().getResults();

                }

                @Override
                public void onFailure(Call<ResponseMovies> call, Throwable t) {
                    Log.e(TAG,t.toString());
                }
            });
            Log.d(TAG, "Number of movies recived: "+movies.size());






        }
}
