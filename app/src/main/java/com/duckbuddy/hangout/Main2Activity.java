package com.duckbuddy.hangout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    TextView textView;
    ImageView toolbarImage,imageView2,imageView3;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbarCafe;
    int position;
    Cafe cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setLayoutAnimation(MainActivity.animationController);
        Animator animator = AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.alpha_and_slide);

        position = getIntent().getIntExtra("Pozisyon", 0);
        cafe = MainActivity.veritabani.cafeAl(position);
        toolbarAyarla();
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float)cafe.getCafeYildizi());

        //textView = findViewById(R.id.textView);
        //imageView2 = findViewById(R.id.imageView2);
        //imageView3 = findViewById(R.id.imageView3);
        //animator.setTarget(textView);
        //animator.start();
        //animator.setTarget(imageView2);
        //animator.start();

        //textView.setText(cafe.getCafeIsmi());
        //imageView2.setImageResource(cafe.getCafeFotografId2());
        //imageView3.setImageResource(cafe.getCafeFotografId3());

    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
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
