package com.duckbuddy.hangout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);

        position = getIntent().getIntExtra("Pozisyon", 0);
        Cafe cafe = MainActivity.veritabani.cafeAl(position);

        textView.setText(cafe.getCafeIsmi());
        imageView.setImageResource(cafe.getCafeFotografId());

    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }

}
