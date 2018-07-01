package com.afrins.craftsbeer.beerlist;

import com.google.gson.annotations.SerializedName;

public class BeerModel {

    @SerializedName("abv")
    private String  abv;
    @SerializedName("ibu")
    private String ibu;
    @SerializedName("id")
    private double id;
    @SerializedName("name")
    private String name;
    @SerializedName("style")
    private String style;
    @SerializedName("ounces")
    private double ounces;

    public BeerModel() {}

    public BeerModel(String abv, String ibu, double id, String name, String style, double ounces) {
        this.abv = abv;
        this.ibu = ibu;
        this.id = id;
        this.name = name;
        this.style = style;
        this.ounces = ounces;
    }

    public String getAbv() {
        return abv;
    }

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getOunces() {
        return ounces;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public void setId(double id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setOunces(double ounces) {
        this.ounces = ounces;
    }
}
