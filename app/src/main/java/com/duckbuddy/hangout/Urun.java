package com.duckbuddy.hangout;

public class Urun {
    private String urunIsmi;
    private double urunFiyati;
    private int kafeID;

    Urun(){ }

    Urun(String urunIsmi, double urunFiyati, int kafeID){
        this.urunIsmi = urunIsmi;
        this.urunFiyati = urunFiyati;
        this.kafeID = kafeID;
    }

    public String getUrunIsmi() { return urunIsmi; }

    public double getUrunFiyati() { return urunFiyati; }

    public int getKafeID() { return kafeID; }
}