package com.afrins.craftsbeer.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.afrins.craftsbeer.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartListAdapter adapter;
    private Toolbar toolbar;
//    public List<BeerModel> cartList= new ArrayList<>();
    String [] beerArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        beerArray = intent.getStringArrayExtra("string-array");
        setToolBar();
        setRecyclerView();
    }

    public void setToolBar(){
        toolbar = findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.cart);
        }
    }

    public void setRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_cart);
        adapter = new CartListAdapter(beerArray, this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter.notifyDataSetChanged();
    }

    public void placeOrder(View v){
        Toast.makeText(this, R.string.order_placed, Toast.LENGTH_SHORT).show();
    }
}
