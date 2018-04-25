package com.duckbuddy.hangout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NavigationDrawerItemAdapter extends RecyclerView.Adapter<NavigationDrawerItemAdapter.MyViewHolder>{

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<NavigationDrawerItem> navigationDrawerItems;

    public NavigationDrawerItemAdapter(Context context,ArrayList<NavigationDrawerItem> navigationDrawerItems){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.navigationDrawerItems = navigationDrawerItems;

    }

    @NonNull @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.drawer_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {//xml'i alıyor
        NavigationDrawerItem item = navigationDrawerItems.get(position);
        holder.setData(item,position);
    }

    @Override
    public int getItemCount() { return navigationDrawerItems.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView drawerTitle;
        ImageView drawerIcon;
        int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            drawerTitle = itemView.findViewById(R.id.drawerTitle);
            drawerIcon = itemView.findViewById(R.id.drawerIcon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position == 1){;
                        FavorilerFragment favoriFragment = new FavorilerFragment();
                        FragmentTransaction fragmentTransaction=MainActivity.fragmentManager.beginTransaction();
                        fragmentTransaction.replace(MainActivity.frameLayout.getId(),favoriFragment);
                        fragmentTransaction.commit();
                    }
                    else{
                        MainFragment mainFragment = new MainFragment();
                        FragmentTransaction fragmentTransaction=MainActivity.fragmentManager.beginTransaction();
                        fragmentTransaction.replace(MainActivity.frameLayout.getId(),mainFragment);
                        fragmentTransaction.commit();
                    }
                    MainActivity.drawerLayout.closeDrawer(GravityCompat.START);
                }
            });
        }

        public void setData(NavigationDrawerItem item, int position) {
            this.drawerTitle.setText(item.getBaslik());
            this.drawerIcon.setImageResource(item.getIconId());
            this.position = position;
        }

    }

}