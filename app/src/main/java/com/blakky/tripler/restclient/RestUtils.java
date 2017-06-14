package com.blakky.tripler.restclient;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sangrambankar on 6/13/17.
 */

public class RestUtils {

    public static final String BASE_URL  = "https://api.learn2crack.com/";
    //public static final String API_KEY = BuildConfig.API_KEY;

    public static IRestClient createRestClient(){


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Log.d("MyTAG", "OkHttp: " + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient okClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okClient)
                .build();

        return retrofit.create(IRestClient.class);
    }


}
