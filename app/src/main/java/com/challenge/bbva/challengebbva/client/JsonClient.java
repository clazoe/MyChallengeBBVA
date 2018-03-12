package com.challenge.bbva.challengebbva.client;

import com.challenge.bbva.challengebbva.api.ApiService;
import com.challenge.bbva.challengebbva.interceptor.ResponseInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by clazoe
 */

public class JsonClient {

    /********
     * URLS
     *******/
    private static final String ROOT_URL = "https://api.myjson.com//";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {

        ResponseInterceptor interceptor = new ResponseInterceptor ();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor) .build();

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
