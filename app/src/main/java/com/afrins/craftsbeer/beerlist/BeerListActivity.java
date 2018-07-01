package com.afrins.craftsbeer.beerlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afrins.craftsbeer.R;
import com.afrins.craftsbeer.cart.CartActivity;
import com.afrins.craftsbeer.utils.Constants;
import com.afrins.craftsbeer.utils.SortBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BeerListActivity extends AppCompatActivity implements  BeerListView, SearchView.OnQueryTextListener{

    private List<BeerModel> beerList = new ArrayList<>();
    private RecyclerView recyclerView;
    private BeerListAdapter adapter;
    private ProgressBar progressBar;
    private BeerListPresenterImpl beerListPresenter;
    private Toolbar toolbar;
    private TextView badgeNotification;
    public int itemCount = 0;
    public static List<BeerModel> cartList= new ArrayList<>();
    public List<String> cartListNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolBar();
        setRecyclerView();

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        beerListPresenter = new BeerListPresenterImpl(this);
        beerListPresenter.fetchBeerList();
    }

    public void setToolBar(){
        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
        badgeNotification = findViewById(R.id.badge_notification);
//        toolbar.inflateMenu(R.menu.action_bar_settings_menu);
//        Menu menu = toolbar.getMenu();
//        final MenuItem item = menu.findItem(R.id.action_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
//        searchView.setOnQueryTextListener(this);
    }

    public void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);

        adapter = new BeerListAdapter(beerList, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showAlert(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BeerListActivity.this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                })  ;
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void updateList(List<BeerModel> list) {
        if (list != null){
            beerList.clear();
            beerList.addAll(list);
            adapter.notifyDataSetChanged();
        }
        hideProgress();

    }

    @Override
    public void updateBadge() {
        if (itemCount>0) {
            badgeNotification.setVisibility(View.VISIBLE);
            badgeNotification.setText(String.valueOf(itemCount));
        }
    }

    @Override
    public void showToast(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_settings_menu, menu);
        // Retrieve the SearchView and plug it into SearchManager
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return  true;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        // Here is where we are going to implement the filter logic
        final List<BeerModel> filteredModelList = beerListPresenter.filter(beerList, query);
        adapter.setFilter(filteredModelList);
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    public void sortByAscending(View v) {
        Collections.sort(beerList, new SortBy(Constants.ASCENDING));
        adapter.notifyDataSetChanged();
    }

    public void sortByDescending(View v) {
        Collections.sort(beerList, new SortBy(Constants.DESCENDING));
        adapter.notifyDataSetChanged();
    }

    public void viewCart(View v){
        Log.d("BeerListActivity, ","result - "+cartList.size());
        String[] stringArray = cartListNames.toArray(new String[0]);
        Intent myIntent = new Intent(BeerListActivity.this, CartActivity.class);
        myIntent.putExtra("string-array", stringArray);
        BeerListActivity.this.startActivity(myIntent);

    }
}
