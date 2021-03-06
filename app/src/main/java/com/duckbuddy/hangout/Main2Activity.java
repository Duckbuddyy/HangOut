package com.duckbuddy.hangout;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements OnMapReadyCallback {

    ConstraintLayout constraintLayout;
    RatingBar ratingBar;
    TextView urunIsim,urunFiyat;
    ImageView toolbarImage,imageView2,imageView3;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbarCafe;
    int position;
    GoogleMap mMap;
    Cafe cafe;
    String urunIsimleri = "",urunFiyatlari = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        position = getIntent().getIntExtra("Pozisyon", 0);
        cafe = MainActivity.veritabani.cafeAl(position);

        layoutAyarla();
        toolbarAyarla();

    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng konum = new LatLng(cafe.getCafeEnlem(),cafe.getCafeBoylam());
        mMap.addMarker(new MarkerOptions().position(konum).title(cafe.getCafeIsmi()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(konum,(float)17.5));
    }

    private void layoutAyarla() {
        constraintLayout = findViewById(R.id.constraintLayout);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        ratingBar = findViewById(R.id.ratingBar);
        MainActivity.animationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.recycler_animation_top);
        MainActivity.animationController.setDelay((float)0.25);
        constraintLayout.setLayoutAnimation(MainActivity.animationController);
        imageView2.setImageResource(cafe.getCafeFotografId2());
        imageView3.setImageResource(cafe.getCafeFotografId3());
        ratingBar.setRating((float) cafe.getCafeYildizi());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        urunIsim = findViewById(R.id.urunIsim);
        urunFiyat = findViewById(R.id.urunFiyat);
        ArrayList<Urun> kafeUrunleri = MainActivity.veritabani.cafeUrunleriAl(position);
        for(int i = 0; i < kafeUrunleri.size();i++){
            urunIsimleri += kafeUrunleri.get(i).getUrunIsmi()+"\n";
            urunFiyatlari += kafeUrunleri.get(i).getUrunFiyati()+"\n";
        }
        urunIsim.setText(urunIsimleri);
        urunFiyat.setText(urunFiyatlari);

    }

    private void toolbarAyarla(){
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        collapsingToolbarLayout.setTitle(cafe.getCafeIsmi());
        toolbarCafe = findViewById(R.id.toolbarCafe);
        toolbarCafe.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbarImage = findViewById(R.id.toolbarImage);
        toolbarImage.setImageResource(cafe.getCafeFotografId());
    }

}
