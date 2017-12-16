package com.example.usuario.peliculastmdb.service;

import com.example.usuario.peliculastmdb.model.Movie;
import com.example.usuario.peliculastmdb.model.ResponseMovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by usuario on 14/12/2017.
 */

public interface ApiService {
    @GET("search/movie?")
    Call<ResponseMovies> getSearch(@Query("api_key") String apiKey, @Query("query") String queryString);
    @GET("movie/top_rated")
    Call<ResponseMovies> getTopRatedMovies(@Query("api_key") String apiKey);

}
