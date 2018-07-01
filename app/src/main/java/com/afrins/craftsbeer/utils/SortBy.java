package com.afrins.craftsbeer.utils;

import com.afrins.craftsbeer.beerlist.BeerModel;

import java.util.Comparator;

public class SortBy implements Comparator<BeerModel> {

    public int order;

    public  SortBy(int order){
        this.order = order;
    }

    public int compare(BeerModel a, BeerModel b) {
        if (order == Constants.ASCENDING) {
            return  (a.getAbv().compareTo(b.getAbv()) );
        }
        else {
            return  (b.getAbv().compareTo(a.getAbv()) );
        }
    }
}

