package com.duckbuddy.hangout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static Database veritabani;
    Toolbar toolbar;
    RecyclerView recyclerView;
    CafeAdapter cafeAdapter, cafeAdapter2;
    LayoutAnimationController animationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setAllowReturnTransitionOverlap(false);

        veritabaniKur();
        recyclerViewAyarla();
        drawerAyarla();
        toolbarAyarla();
    }

    @Override
    public void onBackPressed() {
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
                .create().show();
    }

    private void drawerAyarla() {
        NavigationDrawerFragment navigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.fragment);
        DrawerLayout drawerLayout= findViewById(R.id.drawerLayout);

        navigationDrawerFragment.setUpNavigationDrawer(drawerLayout,toolbar);
    }

    private void recyclerViewAyarla() {
        recyclerView = findViewById(R.id.recyclerView);
        cafeAdapter = new CafeAdapter(this,veritabani.tumKafeleriAl(),this);

        recyclerView.setAdapter(cafeAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        animationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.recycler_animation_top);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLayoutAnimation(animationController);
    }

    private void toolbarAyarla() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ara:

                        break;
                }
                return true;
            }
        });
    }

    private void veritabaniKur() {
        veritabani = new Database(getApplicationContext());
        veritabani.cafeEkle(new Cafe("Leman Kültür",
                "1. Murat Mahallesi, Zübeyde Hanım Cad. No:28, 22030 Merkez/Edirne",
                "(0284) 225 69 71",
                R.drawable.indir,
                R.drawable.indir2,
                R.drawable.indir3,
                4,0,41.667454, 26.576247));
        veritabani.cafeEkle(new Cafe("David People",
                "1. Murat Mahallesi, Zübeyde Hanım Cad. No:22, 22030 Merkez/Edirne",
                "(0284) 225 6971",
                R.drawable.indir2,
                R.drawable.indir2,
                R.drawable.indir3,
                5,0,41.667398,26.5760578));
        veritabani.cafeEkle(new Cafe("Kada",
                "1. Murat Mahallesi, Bülent Alamut Cd. No:9, 22030 Merkez/Edirne",
                "(0533) 607 8999",
                R.drawable.indir3,
                R.drawable.indir2,
                R.drawable.indir3,
                3,0,41.6613592,26.5834329));
        veritabani.cafeEkle(new Cafe("Leman Kültür2",
                "1. Murat Mahallesi, Zübeyde Hanım Cad. No:28, 22030 Merkez/Edirne",
                "(0284) 225 69 71",
                R.drawable.indir4,
                R.drawable.indir2,
                R.drawable.indir3,
                4,0,41.667454, 26.576247));
        veritabani.cafeEkle(new Cafe("David People2",
                "1. Murat Mahallesi, Zübeyde Hanım Cad. No:22, 22030 Merkez/Edirne",
                "(0284) 225 6971",
                R.drawable.indir,
                R.drawable.indir2,
                R.drawable.indir3,
                5,0,41.667398,26.5760578));
        veritabani.cafeEkle(new Cafe("Kada2",
                "1. Murat Mahallesi, Bülent Alamut Cd. No:9, 22030 Merkez/Edirne",
                "(0533) 607 8999",
                R.drawable.indir2,
                R.drawable.indir2,
                R.drawable.indir3,
                3,0,41.6613592,26.5834329));
    }
}
