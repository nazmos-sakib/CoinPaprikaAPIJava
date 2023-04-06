package com.example.coinpaprikajava.Model;

import java.util.ArrayList;

public class CoinsDetails {
    private int id;
    private String coinId;
    private String name;
    private String symbol;
    private String rank;
    private boolean is_new;
    private boolean is_active;
    private String type;
    private String logo;
    private ArrayList<CoinTag> tags;
    private ArrayList<CoinTeam> team;

    public CoinsDetails(int id, String coinId, String name, String symbol, String rank, boolean is_new, boolean is_active, String type, String logo, ArrayList<CoinTag> tags, ArrayList<CoinTeam> team) {
        this.id = id;
        this.coinId = coinId;
        this.name = name;
        this.symbol = symbol;
        this.rank = rank;
        this.is_new = is_new;
        this.is_active = is_active;
        this.type = type;
        this.logo = logo;
        this.tags = tags;
        this.team = team;
    }

    public CoinsDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
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

    public boolean isIs_new() {
        return is_new;
    }

    public void setIs_new(boolean is_new) {
        this.is_new = is_new;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public ArrayList<CoinTag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<CoinTag> tags) {
        this.tags = tags;
    }

    public ArrayList<CoinTeam> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<CoinTeam> team) {
        this.team = team;
    }
}
