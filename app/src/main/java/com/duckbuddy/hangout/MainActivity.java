package com.duckbuddy.hangout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static FrameLayout frameLayout;
    public static FragmentManager fragmentManager;
    public static DrawerLayout drawerLayout;
    public static Database veritabani;
    public static CafeAdapter cafeAdapter;
    public static LayoutAnimationController animationController;
    public static SearchView searchView;
    Toolbar toolbar;
    MenuItem searchItem;
    String oncekiAranan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setAllowReturnTransitionOverlap(false);
        veritabani = new Database(getApplicationContext());
        SharedPreferences preferences = this.getSharedPreferences("com.duckbuddyy.hangout", Context.MODE_PRIVATE);

        if(preferences.getBoolean("First",true)) {
            preferences.edit().putBoolean("First", false).apply();
            veritabaniKur();
        }
        fragmentAyarla();
        toolbarAyarla();
        drawerAyarla();
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            new AlertDialog.Builder(this)
                    .setTitle("Çıkış")
                    .setMessage("Uygulamadan çıkmak istiyor musunuz ?")
                    .setNegativeButton("Hayır", null)
                    .setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    })
                    .create()
                    .show();

    }

    private void veritabaniKur() {
        veritabani.cafeEkle(new Cafe("Pause",
                "1. Murat Mahallesi, Zübeyde Hanım Cad. No:26, 22030 Merkez/Edirne",
                "(0284) 212 81 51",
                R.drawable.pause1,
                R.drawable.pause2,
                R.drawable.pause3,
                5,0,41.667398,26.5760578));
        veritabani.cafeEkle(new Cafe("Mado",
                "Abdurrahman Mah. Talat Paşa Cad. No:59 22100 Merkez/Edirne",
                "(0284) 225 62 36",
                R.drawable.mado1,
                R.drawable.mado2,
                R.drawable.mado3,
                3,0,41.6613592,26.5834329));
        veritabani.cafeEkle(new Cafe("İnciraltı",
                "Balkan Yerleşkesi,Yaşam Merkezi No:5 22000 Merkez/Edirne",
                "(0284) 236 8081",
                R.drawable.inciralti,
                R.drawable.inciralti2,
                R.drawable.inciralti3,
                3,0,41.6613592,26.5834329));
        veritabani.cafeEkle(new Cafe("Loca",
                "Balkan Yerleşkesi, Yaşam Merkezi 22130 Merkez/Edirne",
                "(0284) 236 10 00",
                R.drawable.loca1,
                R.drawable.loca2,
                R.drawable.loca3,
                3,0,41.6613592,26.5834329));
        veritabani.cafeEkle(new Cafe("Kada",
                "1. Murat Mahallesi, Bülent Alamut Cd. No:9, 22030 Merkez/Edirne",
                "(0533) 607 8999",
                R.drawable.kada1,
                R.drawable.kada2,
                R.drawable.kada3,
                3,0,41.6613592,26.5834329));
        veritabani.urunEkle(new Urun("Çay",2.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Kahve",6.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Portakal Suyu",8.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Limonata",7.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Kola",5.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Ayran",2.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Meyveli Soda",3.50,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Türk Kahvesi",5.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Bitki Çayı",7.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Su",2.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Espresso",5.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Mocha",8.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Cappuccino",8.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Sıcak Çikolata",8.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Salep",8.00,veritabani.cafeIdAl("Loca")));
        veritabani.urunEkle(new Urun("Çay",2.50,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Kahve",6.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Portakal Suyu",8.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Limonata",8.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Kola",6.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Ayran",3.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Meyveli Soda",4.50,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Türk Kahvesi",6.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Bitki Çayı",6.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Su",2.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Espresso",7.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Mocha",8.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Cappuccino",7.00,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Sıcak Çikolata",7.50,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Salep",7.50,veritabani.cafeIdAl("Pause")));
        veritabani.urunEkle(new Urun("Çay",2.00,veritabani.cafeIdAl("İnciraltı")));
        veritabani.urunEkle(new Urun("Kahve",4.00,veritabani.cafeIdAl("İnciraltı")));
        veritabani.urunEkle(new Urun("Portakal Suyu",7.00,veritabani.cafeIdAl("İnciraltı")));
        veritabani.urunEkle(new Urun("Limonata",7.00,veritabani.cafeIdAl("İnciraltı")));
        veritabani.urunEkle(new Urun("Kola",3.50,veritabani.cafeIdAl("İnciraltı")));
        veritabani.urunEkle(new Urun("Ayran",2.00,veritabani.cafeIdAl("İnciraltı")));
        veritabani.urunEkle(new Urun("Meyveli Soda",2.50,veritabani.cafeIdAl("Sarmaşık")));
        veritabani.urunEkle(new Urun("Türk Kahvesi",5.00,veritabani.cafeIdAl("Sarmaşık")));
        veritabani.urunEkle(new Urun("Bitki Çayı",7.00,veritabani.cafeIdAl("Sarmaşık")));
        veritabani.urunEkle(new Urun("Su",1.00,veritabani.cafeIdAl("Sarmaşık")));
        veritabani.urunEkle(new Urun("Çay",4.00,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Kahve",9.00,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Portakal Suyu",12.00,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Limonata",10.00,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Kola",6.00,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Ayran",5.50,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Meyveli Soda",4.50,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Sıcak Çikolata",11.00,veritabani.cafeIdAl("Mado")));
        veritabani.urunEkle(new Urun("Çay",2.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Kahve",5.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Limonata",6.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Kola",4.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Ayran",2.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Meyveli Soda",3.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Türk Kahvesi",3.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Bitki Çayı",5.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Su",1.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Mocha",7.00,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Sıcak Çikolata",7.50,veritabani.cafeIdAl("Kada")));
        veritabani.urunEkle(new Urun("Salep",6.00,veritabani.cafeIdAl("Kada")));
    }

    private void fragmentAyarla() {
        frameLayout = findViewById(R.id.frameLayout);
        MainFragment mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(frameLayout.getId(),mainFragment);
        fragmentTransaction.commit();
    }

    private void toolbarAyarla() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        Menu menu = toolbar.getMenu();
        searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified())
                    searchView.setIconified(true);
                searchItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String arananCafe) {
                final ArrayList<Cafe> arananListe = veritabani.cafeAra(arananCafe);
                if(oncekiAranan.length() < arananCafe.length()) {
                    cafeAdapter.filterAdapter(arananListe);
                    oncekiAranan = arananCafe;
                }
                else {
                    cafeAdapter.defilterAdapter();
                    oncekiAranan = arananCafe;
                    cafeAdapter.filterAdapter(arananListe);
                }
                return true;
            }
        });

    }

    private void drawerAyarla() {
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationDrawerFragment.setUpNavigationDrawer(drawerLayout,toolbar);
    }

}