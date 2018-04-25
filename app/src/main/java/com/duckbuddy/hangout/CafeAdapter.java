package com.duckbuddy.hangout;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
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

import java.util.ArrayList;

public class CafeAdapter extends RecyclerView.Adapter<CafeAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Cafe> cafeList;
    private LayoutInflater layoutInflater;
    private Activity activity;

    CafeAdapter(Context context, ArrayList<Cafe> cafeList,Activity activity){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.cafeList = cafeList;
        this.activity = activity;
    }

    @NonNull @Override
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

    public void deleteFavouriteDatabase(int position){
        cafeList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,cafeList.size());
    }

    class MyViewHolder extends RecyclerView.ViewHolder{//xmlden javaya findviewbyid
        Cafe cafe;
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
                    final Animator animator = AnimatorInflater.loadAnimator(context, R.animator.favourite_animation);
                    animator.setTarget(iconfavourite);
                    if (MainActivity.veritabani.cafeFavoriMi(position) == 0) {
                        iconfavourite.setImageResource(R.drawable.ic_favourite_fill);
                        MainActivity.veritabani.cafeFavoriDegistir(position);
                        Snackbar snackbar = Snackbar.make(view, cafeTittle.getText() + " Favorilerinize Eklendi !", Snackbar.LENGTH_SHORT);
                        snackbar.setAction("Geri Al", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                iconfavourite.setImageResource(R.drawable.ic_favourite_border);
                                Snackbar.make(view, cafeTittle.getText() + " Favorilerinizden Çıkarıldı !", Snackbar.LENGTH_SHORT).show();
                                MainActivity.veritabani.cafeFavoriDegistir(position);
                                animator.start();
                                }
                            });
                        snackbar.show();
                        animator.start();
                    } else {
                        MainActivity.veritabani.cafeFavoriDegistir(position);
                        Snackbar.make(view, cafeTittle.getText() + " Favorilerinizden Çıkarıldı !", Snackbar.LENGTH_SHORT).show();
                        iconfavourite.setImageResource(R.drawable.ic_favourite_border);
                        animator.start();
                    }
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Pair pair = new Pair<View, String>(cafePicture,"iv_effect");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity,pair);
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
            this.cafe = cafe;
        }
    }

}