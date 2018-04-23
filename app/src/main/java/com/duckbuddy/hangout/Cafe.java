package com.duckbuddy.hangout;

public class Cafe {
    private String cafeIsmi, cafeAdresi, cafeTelefonu;
    private int cafeFotografId , cafeFotografId2 , cafeFotografId3, cafeYildizi, favori;
    private Double cafeEnlem,cafeBoylam;

    public Cafe(){ }

    public Cafe(String cafeIsmi, String cafeAdresi, String cafeTelefonu,
                int cafeFotografId, int cafeFotografId2,
                int cafeFotografId3, int cafeYildizi, int favoriMi,
                Double cafeEnlem, Double cafeBoylam) {
        this.cafeIsmi = cafeIsmi;
        this.cafeAdresi = cafeAdresi;
        this.cafeTelefonu = cafeTelefonu;
        this.cafeFotografId = cafeFotografId;
        this.cafeFotografId2 = cafeFotografId2;
        this.cafeFotografId3 = cafeFotografId3;
        this.cafeYildizi = cafeYildizi;
        this.favori = favoriMi;
        this.cafeEnlem = cafeEnlem;
        this.cafeBoylam = cafeBoylam;
    }

    public String getCafeIsmi() { return cafeIsmi; }

    public String getCafeAdresi() { return cafeAdresi;}

    public String getCafeTelefonu() { return cafeTelefonu; }

    public int getCafeFotografId() { return cafeFotografId; }

    public int getCafeFotografId2() { return cafeFotografId2; }

    public int getCafeFotografId3() { return cafeFotografId3; }

    public int getCafeYildizi() { return cafeYildizi; }

    public int getFavori() { return favori; }

    public void setFavori(int favori) { this.favori = favori;}

    public Double getCafeEnlem() { return cafeEnlem; }

    public Double getCafeBoylam() { return cafeBoylam; }

}