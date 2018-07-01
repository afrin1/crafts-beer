package com.afrins.craftsbeer.beerlist;

import java.util.ArrayList;
import java.util.List;

public interface BeerListPresenter {
    void fetchBeerList();

    void receivedList(List<BeerModel> list);

    void receivedError(String msg, String title);

    List<BeerModel> filter(List<BeerModel> models, String query);

}
