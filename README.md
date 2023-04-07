# CoinPaprikaAPIJava

This project extract data from ### [**_CoinpapriKa_**](https://coinpaprika.com/)

Their API documentation can be found [Here](https://api.coinpaprika.com/#section/Rate-limit) 

[Link to get all listed coins as JSON array can be found here](https://api.coinpaprika.com/v1/coins)

[Link to get all available details of a coins as JSON object can be found here](https://api.coinpaprika.com/v1/coins/steth-lido-staked-ether)


## [Volley](https://google.github.io/volley/request.html)

>Volley is an HTTP library that makes networking for Android apps easier and most importantly, faster.

Volley dependencies for **_Groovy_**
```
dependencies {
    implementation 'com.android.volley:volley:1.2.1'
}
```

## RecyclerView Implementation

### RecyclerViewClickListener.java  //interface

```
public interface RecyclerViewClickListener {
    void onRecViewItemClick(int position);
}
```

### Adapter.java 

```
public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.ViewHolder> {
    //array to hold coins
    private ArrayList<Coins> coinsArrayList = new ArrayList<>();
    //interface to recieve clicked coin
    private RecyclerViewClickListener recyclerViewClickListener;

    //constructor that recieve clicked listener interface as parameter 
    public CoinListAdapter(RecyclerViewClickListener recyclerViewClickListener) {
        this.recyclerViewClickListener = recyclerViewClickListener;
    }

    public CoinListAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ViewHolder(View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coin_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        .
        .
        .
        //setting click listener to parent view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing the clicked position to the interface
                recyclerViewClickListener.onRecViewItemClick(position);
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
        //view variable declaration
        public ViewHolder(@NonNull View itemView){  
            super(itemView);
            //views to variable mapping(findViewById)
            .
            .
        }
    }
}

```

### MainActivity


```
public class MainActivity extends AppCompatActivity implements RecyclerViewClickListener { //implement to get clicked position
    private CoinListAdapter coinListAdapter;
    private RecyclerView coinRecView;
    .
    .
    .
    
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

