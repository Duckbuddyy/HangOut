package com.duckbuddy.hangout;


import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static Database veritabani;
    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        veritabani = new Database(getApplicationContext());

        veritabaniKur();
        toolbarAyarla();
        recyclerViewAyarla();
        drawerAyarla();

    }

    private void drawerAyarla() {
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment);
        DrawerLayout drawerLayout= findViewById(R.id.drawerLayout);

        navigationDrawerFragment.setUpNavigationDrawer(drawerLayout,toolbar);

    }

    private void recyclerViewAyarla() {
        recyclerView = findViewById(R.id.recyclerView);
        CafeAdapter cafeAdapter = new CafeAdapter(this,veritabani.tumKafeleriAl());
        recyclerView.setAdapter(cafeAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void toolbarAyarla() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ara:
                        Toast.makeText(MainActivity.this,"Ara Tıklandı",Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void veritabaniKur() {
        veritabani.cafeEkle(new Cafe("Leman Kültür",
                "1. Murat Mahallesi, Zübeyda Hanım Cad. No:28, 22030 Merkez/Edirne",
                "(0284) 225 69 71",
                R.drawable.indir,
                R.drawable.indir2,
                R.drawable.indir3,
                4,41.667454, 26.576247));
        veritabani.cafeEkle(new Cafe("David People",
                "1. Murat Mahallesi, Zübeyde Hanım Cad. No:22, 22030 Merkez/Edirne",
                "(0284) 225 6971",
                R.drawable.indir,
                R.drawable.indir2,
                R.drawable.indir3,
                5,41.667398,26.5760578));
        veritabani.cafeEkle(new Cafe("Kada",
                "1. Murat Mahallesi, Bülent Alamut Cd. No:9, 22030 Merkez/Edirne",
                "(0533) 607 8999",
                R.drawable.indir,
                R.drawable.indir2,
                R.drawable.indir3,
                3,41.6613592,26.5834329));

    }
}
