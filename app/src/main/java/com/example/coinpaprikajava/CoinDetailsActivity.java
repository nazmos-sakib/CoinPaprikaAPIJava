package com.example.coinpaprikajava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.coinpaprikajava.Model.Coins;
import com.example.coinpaprikajava.Model.CoinsDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoinDetailsActivity extends AppCompatActivity {
    private static final String TAG = "CoinDetailsActivity->";


    //views
    private TextView tv_coinNameSymbol, tv_isActive,tv_coinDescription;
    private ProgressBar progressBar;
    private GridLayout gridLayoutTags;
    private ListView listViewTeamMember;
    private LinearLayout allViewContainer;


    private String coinDetailsUrl = "https://api.coinpaprika.com/v1/coins/";
    private String position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_details);

        //
        initViews();
        getIntentData();
        fetchDataFromAPI();

    }

    @Override
    protected void onResume() {
        super.onResume();
        allViewContainer.setVisibility(View.VISIBLE);
        fetchDataFromAPI();
    }

    @Override
    protected void onStart() {
        super.onStart();
        allViewContainer.setVisibility(View.VISIBLE);
        fetchDataFromAPI();
    }

    private void getIntentData(){
        Intent intent = getIntent();
        position = intent.getStringExtra("position");
        String coinId = intent.getStringExtra("coin_id");
        coinDetailsUrl+=coinId;
    }


    private void fetchDataFromAPI() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, coinDetailsUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //tv_coinDescription.setText("Response: " + response.toString());

                            tv_coinNameSymbol.setText(position+". "
                                    + response.getString("name")
                                    +" (" +response.getString("symbol") + ")");

                            tv_coinDescription.setText( response.getString("description"));

                            JSONArray tags = response.getJSONArray("tags");

                            //tv_coinDescription.setText(tags.length()+"--->"+tags.toString());

                            for (int i = 0; i < tags.length(); i++) {
                                addTagsToGridView(i,tags.getJSONObject(i).getString("name"));
                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d(TAG, "onErrorResponse: error occurred");
                        tv_coinDescription.setText("onErrorResponse: error occurred");
                        error.printStackTrace();
                        progressBar.setVisibility(View.INVISIBLE);
                        allViewContainer.setVisibility(View.GONE);

                    }
                });//end of object declaration
/*

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, coinDetailsUrl, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: no error : "+response.length());
                        ArrayList<CoinsDetails> coinsDetailsArrayList = new ArrayList<>();
                        try {

                            for (int i=0;i<350;i++){


                            }

                        } catch (Exception e){
                            e.printStackTrace();
                        }
                        // visible the progress bar
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: error occurred");
                        error.printStackTrace();
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                }
        ); //end of object declaration
*/

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    } //end of function fetchDataFromAPI


    private  void initViews(){
        tv_coinNameSymbol = findViewById(R.id.tv_coinNameSymbol_coinDetailsActivity);
        tv_isActive = findViewById(R.id.tv_isActive_coinDetailsActivity);
        tv_coinDescription = findViewById(R.id.tv_description_coinDetailsActivity);
        progressBar = findViewById(R.id.progressBar_coinDetailsActivity);
        gridLayoutTags = findViewById(R.id.tags_coinDetailsActivity);
        listViewTeamMember = findViewById(R.id.listView_coinDetailsActivity);

        allViewContainer = findViewById(R.id.allViewContainer__coinDetailsActivity);
        allViewContainer.setVisibility(View.VISIBLE);

    } //end of initView()

    private void addTagsToGridView(int i,String tagName){
        TextView textView = new TextView(CoinDetailsActivity.this);
        textView.setText(tagName);
        textView.setTextSize(14);
        textView.setTextColor(getResources().getColor(R.color.light_green));
        // Set rounded border and padding for the text view
        textView.setBackgroundResource(R.drawable.tag_textview_bg);
        int padding = getResources().getDimensionPixelSize(R.dimen.text_view_padding);
        textView.setPadding(padding, padding, padding, padding);

        // Calculate the row and column indices for the text view
        int row = i / 2;
        int col = i % 2;

        // Set the layout parameters for the text view
        GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
        layoutParams.rowSpec = GridLayout.spec(row,1,1f); //spec(row, 1f);
        layoutParams.columnSpec = GridLayout.spec(col, 2,2f); //spec(row, 1f);
        //layoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        //layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        //layoutParams.setMargins(10, 15, 0, 15); // Set margins here
        layoutParams.setGravity(Gravity.FILL);
        textView.setLayoutParams(layoutParams);

        // Set the layout parameters to wrap the content
        LinearLayout.LayoutParams LinearLayoutLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayoutLayoutParams.setMargins(0, 15, 20, 15); // Set margins here

        textView.setLayoutParams(LinearLayoutLayoutParams);


        // Add the text view to the grid layout
        gridLayoutTags.addView(textView);

    }//end of addTagsToGridView()
}