package com.afrins.craftsbeer.beerlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afrins.craftsbeer.R;

import java.util.ArrayList;
import java.util.List;

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.MyViewHolder> {
    private List<BeerModel> beerList;
    private BeerListActivity context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, abv, style;
        public ImageButton addBtn;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            abv = view.findViewById(R.id.abv);
            style = view.findViewById(R.id.style);
            addBtn = view.findViewById(R.id.add);
            addBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    context.itemCount++;
                    context.cartList.add(beerList.get(position));
                    context.cartListNames.add(beerList.get(position).getName());
                    context.updateBadge();
                    context.showToast(R.string.added_to_cart);

                }
            });
        }
    }

    public BeerListAdapter(List<BeerModel> beerList, BeerListActivity context) {
        this.beerList = beerList;
        this.context = context;
    }

    public void setFilter(List<BeerModel> countryModels){
        beerList = new ArrayList<>();
        beerList.addAll(countryModels);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.beer_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BeerModel movie = beerList.get(position);
        holder.name.setText(movie.getName());
        if (movie.getAbv().equals("")){
            holder.abv.setText("Alcohol Content: NA");
        } else {
            holder.abv.setText("Alcohol Content: "+movie.getAbv());
        }

        holder.style.setText(movie.getStyle());
    }

    @Override
    public int getItemCount() {
        return beerList.size();
    }
}
