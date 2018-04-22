package com.duckbuddy.hangout;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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

    class MyViewHolder extends RecyclerView.ViewHolder{//xmlden javaya findviewbyid
        TextView cafeTittle, cafeSubTittle;
        ImageView cafePicture;
        int position;

        private MyViewHolder(final View itemView) {
            super(itemView);
            cafeTittle = itemView.findViewById(R.id.cafeTitle);
            cafeSubTittle = itemView.findViewById(R.id.cafeSubTitle);
            cafePicture = itemView.findViewById(R.id.cafePicture);

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
            this.cafeTittle.setText(cafe.getCafeIsmi());
            this.cafeSubTittle.setText(cafe.getCafeAdresi());
            this.cafePicture.setImageResource(cafe.getCafeFotografId());
            this.position = position;
        }
    }

}