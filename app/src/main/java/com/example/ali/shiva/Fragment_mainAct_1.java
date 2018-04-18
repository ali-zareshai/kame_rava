package com.example.ali.shiva;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;



public class Fragment_mainAct_1 extends Fragment {
    RelativeLayout relativeLayout;
    public Fragment_mainAct_1() {
        // Required empty public constructor
    }

    public static Fragment_mainAct_1 newInstance() {
        Fragment_mainAct_1 fragment = new Fragment_mainAct_1();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_main_act_1, container, false);
        TextView textView=(TextView)view.findViewById(R.id.textViewsun_ver2);
        relativeLayout=(RelativeLayout)view.findViewById(R.id.msgtoday);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ScrollingActivity.class));
            }
        });
        DatabaseHandler db;
        db = new DatabaseHandler(getContext());
                try {
                    textView.setText("سلام خوش آمدید");
            if (!db.get_header().equals("0")){
                if (!db.get_header().equals("")){
                    textView.setText(db.get_header());
                }
            }

        }catch (Exception e){

        }


        return view;
    }








}
