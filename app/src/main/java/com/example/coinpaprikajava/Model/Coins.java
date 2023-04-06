package com.example.coinpaprikajava.Model;

public class Coins {
    private int id;
    private String coin_id;
    private String name;
    private String symbol;
    private String rank;
    private String is_new;
    private String is_active;
    private String type;

    public Coins(int id,String coin_id, String name, String symbol, String rank, String is_new, String is_active, String type) {
        this.id = id;
        this.coin_id = coin_id;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.is_new = is_new;
        this.is_active = is_active;
        this.type = type;
    }

    public Coins() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Coins{" +
                "id=" + id +
                ", coin_id='" + coin_id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", rank='" + rank + '\'' +
                ", is_new='" + is_new + '\'' +
                ", is_active='" + is_active + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
