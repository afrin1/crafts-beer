package com.afrins.craftsbeer.cart;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afrins.craftsbeer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.MyCartViewHolder> {

    private List<String> beerList;
    private CartActivity context;

    public class MyCartViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageButton addBtn;

        public MyCartViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            addBtn = view.findViewById(R.id.add);
            addBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    List<String> newList = new ArrayList<>();
                    newList.addAll(beerList);
                    String obj= beerList.get(getAdapterPosition());
                    beerList.remove(obj);
                    notifyDataSetChanged();
                }
            });
        }
    }

    public CartListAdapter(String[] beerList, CartActivity context) {
        this.beerList = new ArrayList(Arrays.asList(beerList));
        this.context = context;
    }

    @Override
    public MyCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_list, parent, false);

        return new MyCartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyCartViewHolder holder, int position) {
        String beer = beerList.get(position);
        holder.name.setText(beer);
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }
}
