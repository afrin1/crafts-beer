package com.afrins.craftsbeer.beerlist;

import java.util.ArrayList;
import java.util.List;

public class BeerListPresenterImpl implements BeerListPresenter {

    private BeerListActivity activity;
    private BeerListInteractorImpl interactor;

    public BeerListPresenterImpl(BeerListActivity activity) {
        this.activity = activity;
        interactor = new BeerListInteractorImpl(this);
    }

    @Override
    public void fetchBeerList() {
        activity.showProgress();
        interactor.fetchList();
        //call to n/w
    }

    @Override
    public void receivedList(List<BeerModel> list) {
        activity.updateList(list);
    }

    @Override
    public void receivedError(String msg, String title) {
        activity.showAlert(msg, title);
    }

    @Override
    public List<BeerModel> filter(List<BeerModel> models, String query) {
        query = query.toLowerCase();
        final List<BeerModel> filteredModelList = new ArrayList<>();
        for (BeerModel model : models) {
            final String text1 = model.getName().toLowerCase();
            final String text2 = model.getStyle().toLowerCase();
            if (text1.contains(query)) {
                filteredModelList.add(model);
            }
            else if (text2.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }


}
