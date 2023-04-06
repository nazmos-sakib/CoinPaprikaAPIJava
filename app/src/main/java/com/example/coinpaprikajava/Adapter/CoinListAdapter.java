package com.example.coinpaprikajava.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coinpaprikajava.Model.Coins;
import com.example.coinpaprikajava.R;
import com.example.coinpaprikajava.RecyclerViewClickListener;

import java.util.ArrayList;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.ViewHolder> {

    private ArrayList<Coins> coinsArrayList = new ArrayList<>();
    //private Context context;
    private RecyclerViewClickListener recyclerViewClickListener;


    public CoinListAdapter(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    public CoinListAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list,parent,false);

        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coins coin = coinsArrayList.get(position);
        //holder.tv_id.setText(String.valueOf(coinsArrayList.get(position).getId()));
        holder.tv_isActive.setText(coinsArrayList.get(position).getIs_active());
        String text = String.valueOf(coinsArrayList.get(position).getId())+".  "+coinsArrayList.get(position).getName()+"("+coinsArrayList.get(position).getSymbol()+")";
        holder.tv_coinNameSymbol.setText(text);

        int index = position;

        //setting click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing the clicked position to the interface
                recyclerViewClickListener.onRecViewItemClick(index);
            }
        });

    }

    @Override
    public int getItemCount() {
        return coinsArrayList.size();
    }

    //getting clicked data
    public Coins getItemData(int position) {
        return coinsArrayList.get(position);
    }



    //updating the data of the recView
    public void setAdapterData(ArrayList<Coins> coins) {
        this.coinsArrayList = coins;
        notifyDataSetChanged();
    }

    public static class  ViewHolder extends RecyclerView.ViewHolder{

        private  TextView tv_id,tv_coinNameSymbol,tv_isActive;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //tv_id = itemView.findViewById(R.id.tv_id_coinList);
            tv_coinNameSymbol = itemView.findViewById(R.id.tv_coinNameSymbol_coinList);
            tv_isActive = itemView.findViewById(R.id.tv_isActive_coinList);

        }
    }
}
