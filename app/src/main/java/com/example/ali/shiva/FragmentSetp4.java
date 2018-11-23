package com.example.ali.shiva;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentSetp4 extends Fragment {
    Button btnexict;
    private FragmentSetp4() {
    }
    private static FragmentSetp4 fragmentSetp4=new FragmentSetp4();
    public static Fragment getInstance(){
        return fragmentSetp4;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_fragment_setp4,container,false);

        btnexict=(Button)view.findViewById(R.id.entertoapp);

        btnexict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getFragmentManager().popBackStack();
                Intent i = new Intent(getContext(), Main2Activity.class);
                startActivity(i);
            }
        });



        return view;

    }
}
