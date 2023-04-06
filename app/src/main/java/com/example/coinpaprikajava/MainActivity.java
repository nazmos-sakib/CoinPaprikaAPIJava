package com.example.coinpaprikajava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coinpaprikajava.Adapter.CoinListAdapter;
import com.example.coinpaprikajava.Model.Coins;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener { //implement to get clicked position
    private static final String TAG = "MainActivity->";
    //views
    private TextView tv_coiList;
    private RecyclerView coinRecView;
    private ProgressBar progressBar;

    private String coinListUrl = "https://api.coinpaprika.com/v1/coins";

    private CoinListAdapter coinListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar_mainActivity);

        setRecViewAdapter();

        fetchDataFromAPI();

    }

    private void fetchDataFromAPI() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, coinListUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: no error : "+response.length());
                        ArrayList<Coins> coinsArrayList = new ArrayList<>();
                        try {

                            for (int i=0;i<350;i++){
                                Coins coin = new Coins();

                                coin.setId(i+1);
                                coin.setCoin_id(response.getJSONObject(i).getString("id"));
                                coin.setName(response.getJSONObject(i).getString("name"));
                                coin.setIs_active(response.getJSONObject(i).getBoolean("is_active")?"active":"not active");
                                coin.setSymbol(response.getJSONObject(i).getString("symbol"));

                                coinsArrayList.add(coin);

                            }

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        // visible the progress bar
                        progressBar.setVisibility(View.INVISIBLE);
                        coinListAdapter.setAdapterData(coinsArrayList);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: error occurred");
                        error.printStackTrace();
                        coinListAdapter.setAdapterData(new ArrayList<>());

                    }
                }
        ); //end of function call

        Volley.newRequestQueue(this).add(jsonArrayRequest);
    } //end of function fetchDataFromAPI

    private void setRecViewAdapter() {
        coinRecView = findViewById(R.id.recView_mainActivity);

        coinListAdapter = new CoinListAdapter(this);
        coinRecView.setAdapter(coinListAdapter);
        coinRecView.setLayoutManager(new LinearLayoutManager(this));


    }

    //implementation of the RecyclerViewClickListener
    //it holds the current clicked position
    @Override
    public void onRecViewItemClick(int position) {
        // Handle item click event
        Coins clickedCoin = coinListAdapter.getItemData(position);
        Toast.makeText(this, "Clicked: " + clickedCoin.getName(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, CoinDetailsActivity.class);
        intent.putExtra("position",String.valueOf(position+1));
        intent.putExtra("coin_id",clickedCoin.getCoin_id());
        startActivity(intent);
    }
}