package com.afrins.craftsbeer.beerlist;

import android.util.Log;

import com.afrins.craftsbeer.rest.RestService;
import com.afrins.craftsbeer.rest.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeerListInteractorImpl  implements  BeerListInteractor{

    private BeerListPresenterImpl context;

    public BeerListInteractorImpl(BeerListPresenterImpl context) {
        this.context = context;
    }

    @Override
    public void fetchList() {
        /*Create handle for the RetrofitInstance interface*/
        RestService service = RetrofitClient.getRetrofitInstance().create(RestService.class);
        Call<List<BeerModel>> call = service.getBeerList();
        call.enqueue(new Callback<List<BeerModel>>() {
            @Override
            public void onResponse(Call<List<BeerModel>> call, Response<List<BeerModel>> response) {
                context.receivedList(response.body());
            }

            @Override
            public void onFailure(Call<List<BeerModel>> call, Throwable t) {
                Log.d("BeerListPresenterImpl", "Data :: error : "+t.getMessage());
                context.receivedError("Beer list fetch failed", "BEER LIST");
            }
        });
    }
}
