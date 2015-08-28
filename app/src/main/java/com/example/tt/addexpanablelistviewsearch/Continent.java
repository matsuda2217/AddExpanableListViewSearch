package com.example.tt.addexpanablelistviewsearch;

import java.util.ArrayList;

/**
 * Created by TT
 */
public class Continent {
    private String name = "";
    private ArrayList<Contry> contryList = new ArrayList<Contry>();

    public Continent(String name, ArrayList<Contry> contryList) {
        this.name = name;
        this.contryList = contryList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Contry> getContryList() {
        return contryList;
    }

    public void setContryList(ArrayList<Contry> contryList) {
        this.contryList = contryList;
    }
}
