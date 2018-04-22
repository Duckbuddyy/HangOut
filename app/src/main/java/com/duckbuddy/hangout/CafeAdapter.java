package com.duckbuddy.hangout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

    CafeAdapter(Context context, ArrayList<Cafe> cafeList){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.cafeList = cafeList;
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

        private MyViewHolder(View itemView) {
            super(itemView);
            cafeTittle = itemView.findViewById(R.id.cafeTitle);
            cafeSubTittle = itemView.findViewById(R.id.cafeSubTitle);
            cafePicture = itemView.findViewById(R.id.cafePicture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Deneme" + position, Toast.LENGTH_SHORT).show();
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
