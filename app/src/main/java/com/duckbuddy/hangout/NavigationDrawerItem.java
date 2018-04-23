package com.duckbuddy.hangout;

import java.util.ArrayList;

public class NavigationDrawerItem {
    String baslik;
    int iconId;

    public NavigationDrawerItem(){}

    public NavigationDrawerItem(String baslik, int iconId) {
        this.baslik = baslik;
        this.iconId = iconId;
    }

    public String getBaslik() { return baslik; }

    public int getIconId() { return iconId; }

    public static ArrayList<NavigationDrawerItem> getItemList(){
        ArrayList<NavigationDrawerItem> itemList = new ArrayList<>();
        for(int i = 0; i < 2;i++){
            NavigationDrawerItem item = new NavigationDrawerItem(baslikDizi()[i],iconDizi()[i]);
            itemList.add(item);
        }
        return itemList;
    }

    private static String[] baslikDizi(){
        return new String[]{"Anasayfa","Favori Kafeler"};
    }

    private static int[] iconDizi(){
        return new int[]{R.drawable.ic_home,R.drawable.ic_favourite_border};
    }

}
