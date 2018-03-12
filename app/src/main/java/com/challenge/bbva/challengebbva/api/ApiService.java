package com.challenge.bbva.challengebbva.api;

import com.challenge.bbva.challengebbva.rest.ProductoLista;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by clazoe
 */

public interface ApiService {

    String BASE_URL = "https://api.myjson.com/bins/12d94h";

    @GET("bins/12d94h")
    Call<ProductoLista> getProductos();
}