package com.duckbuddy.hangout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Cafe> cafeList;
    private LayoutInflater layoutInflater;
    private MainActivity mainActivity;

    CafeAdapter(Context context, ArrayList<Cafe> cafeList,MainActivity mainActivity){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.cafeList = cafeList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {//ilk açılış inflate
        View view = layoutInflater.inflate(R.layout.list_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {//tıklanan öğenin değerleri gelicek
        Cafe cafe = cafeList.get(position);
        holder.setCafe(cafe,position);
    }

    @Override
    public int getItemCount() {//listenin eleman sayısı
        return cafeList.size();
    }

    public void ekle(){

    }

    public void cikar(){

    }

    class MyViewHolder extends RecyclerView.ViewHolder{//xmlden javaya findviewbyid
        TextView cafeTittle, cafeSubTittle;
        ImageView cafePicture,iconfavourite;
        int position;

        private MyViewHolder(final View itemView) {
            super(itemView);
            cafeTittle = itemView.findViewById(R.id.cafeTitle);
            cafeSubTittle = itemView.findViewById(R.id.cafeSubTitle);
            cafePicture = itemView.findViewById(R.id.cafePicture);
            iconfavourite = itemView.findViewById(R.id.iconFavourite);


            iconfavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Animator animator = AnimatorInflater.loadAnimator(context, R.animator.imageview_animation);
                    animator.setTarget(iconfavourite);
                    if (MainActivity.veritabani.cafeFavoriMi(position) == 0) {
                        iconfavourite.setImageResource(R.drawable.ic_favourite_fill);
                        animator.start();
                        MainActivity.veritabani.cafeFavoriDegistir(MainActivity.veritabani.cafeAl(position),position);
                        Snackbar snackbar = Snackbar.make(view, cafeTittle.getText() + " Favorilerinize Eklendi !", Snackbar.LENGTH_SHORT);
                        snackbar.setAction("Geri Al", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) { Snackbar.make(view, cafeTittle.getText() + " Favorilerinizden Çıkarıldı !", Snackbar.LENGTH_SHORT).show();
                                MainActivity.veritabani.cafeFavoriDegistir(MainActivity.veritabani.cafeAl(position),position);
                                iconfavourite.setImageResource(R.drawable.ic_favourite_border);
                                animator.start();
                                }
                            });
                        snackbar.show();
                        ekle();
                    } else {
                        Snackbar.make(view, cafeTittle.getText() + " Favorilerinizden Çıkarıldı !", Snackbar.LENGTH_SHORT).show();
                        MainActivity.veritabani.cafeFavoriDegistir(MainActivity.veritabani.cafeAl(position),position);
                        iconfavourite.setImageResource(R.drawable.ic_favourite_border);
                        animator.start();
                        cikar();
                        }
                    }
                });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pair [] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(cafePicture,"iv_effect");
                    pairs[1] = new Pair<View, String>(cafeTittle,"tv_effect");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mainActivity,pairs);
                    Intent intent = new Intent (v.getContext(), Main2Activity.class);
                    intent.putExtra("Pozisyon",position);
                    v.getContext().startActivity(intent,options.toBundle());
                }
            });
        }

        private void setCafe(Cafe cafe, int position) {
            if(cafe.getFavori() == 1) iconfavourite.setImageResource(R.drawable.ic_favourite_fill);
            this.cafeTittle.setText(cafe.getCafeIsmi());
            this.cafeSubTittle.setText(cafe.getCafeAdresi());
            this.cafePicture.setImageResource(cafe.getCafeFotografId());
            this.position = position;
        }
    }

}