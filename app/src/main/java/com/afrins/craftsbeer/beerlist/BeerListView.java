package com.afrins.craftsbeer.beerlist;

import java.util.List;

public interface BeerListView {

    void showProgress();
    void hideProgress();
    void showAlert(String message, String title);
    void updateList(List<BeerModel> beerlist);
    void updateBadge();
    void showToast(int msg);
}
