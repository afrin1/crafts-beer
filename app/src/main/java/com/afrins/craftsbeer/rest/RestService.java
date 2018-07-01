package com.afrins.craftsbeer.rest;

import com.afrins.craftsbeer.beerlist.BeerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestService {

    @GET("/beercraft")
    Call<List<BeerModel>> getBeerList();
}
