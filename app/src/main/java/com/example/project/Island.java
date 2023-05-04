package com.example.project;

// Item for RecyclerView
public class Island {

    private String name;
    private int population;
    private String government;
    private String ocean;
    private String wikiUrl;
    private String capital;
    private int area;
    private String imageUrl;

    public Island(String name, int population, String government, String ocean, String wikiUrl, String capital, int area, String imageUrl) {
        this.name = name;
        this.population = population;
        this.government = government;
        this.ocean = ocean;
        this.wikiUrl = wikiUrl;
        this.capital = capital;
        this.area = area;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getGovernment() {
        return government;
    }

    public String getOcean() {
        return ocean;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public String getCapital() {
        return capital;
    }

    public int getArea() {
        return area;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
