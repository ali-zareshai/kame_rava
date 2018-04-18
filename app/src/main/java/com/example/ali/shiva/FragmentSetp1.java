package com.example.ali.shiva;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentSetp1 extends Fragment{
//    Button btny,btnn;

    private FragmentSetp1() {
    }
    private static FragmentSetp1 fragmentSetp1=new FragmentSetp1();
    public static Fragment getInstance(){
        return fragmentSetp1;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_setp1,container,false);
//        btny=(Button)view.findViewById(R.id.btnyesset1);
//        btnn=(Button)view.findViewById(R.id.btnnoset1);
//        btny.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("ResourceType")
//            @Override
//            public void onClick(View v) {
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up);
//                ft.addToBackStack(null);
////                ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);
//
//                ft.replace(R.id.fragment_container, new FragmentSetp2());
//                ft.commit();
//            }
//        });
//        btnn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getActivity().getFragmentManager().popBackStack();
//                Intent i = new Intent(getContext(), Main2Activity.class);
//                startActivity(i);
//            }
//        });
        return view;

//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
