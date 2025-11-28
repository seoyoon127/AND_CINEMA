package com.example.movieapplication.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TMDBApi {
    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    public static Retrofit getRetrofit() {
        System.out.println("TMDB_API_KEY: " + com.example.movieapplication.BuildConfig.TMDB_API_KEY);


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        HttpUrl url = chain.request().url().newBuilder()
                                .addQueryParameter("api_key", com.example.movieapplication.BuildConfig.TMDB_API_KEY)
                                .build();
                        Request request = chain.request().newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
