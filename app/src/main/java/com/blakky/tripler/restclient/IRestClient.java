package com.blakky.tripler.restclient;

import com.blakky.tripler.model.Android;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by sangrambankar on 6/13/17.
 */

public interface IRestClient {

    @GET("android/jsonarray/")
    Observable<List<Android>> register();
}
