package com.duckbuddy.hangout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_main, container, false);

        recyclerViewAyarla(view);
        return view;
    }


    private void recyclerViewAyarla(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        MainActivity.cafeAdapter = new CafeAdapter(getContext(),MainActivity.veritabani.tumKafeleriAl(),getActivity());
        recyclerView.setAdapter(MainActivity.cafeAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        MainActivity.animationController = AnimationUtils.loadLayoutAnimation(getContext(),R.anim.recycler_animation_top);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLayoutAnimation(MainActivity.animationController);
    }

}
