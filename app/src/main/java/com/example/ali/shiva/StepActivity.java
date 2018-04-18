package com.example.ali.shiva;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.viewpagerindicator.CirclePageIndicator;

public class StepActivity extends AppCompatActivity {
//    FrameLayout frameLayout;
    ViewPager viewPager;
    CirclePageIndicator circlePageIndicator;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
//        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);
        viewPager=(ViewPager)findViewById(R.id.fragment_container);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

        circlePageIndicator=(CirclePageIndicator)findViewById(R.id.pagerIndicator);
        circlePageIndicator.setViewPager(viewPager);

        // Begin the transaction
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_in_up,R.anim.slide_out_up);
//        ft.addToBackStack(null);
////        ft.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);
//// Replace the contents of the container with the new fragment
//        ft.replace(R.id.fragment_container, new FragmentSetp1());
// or ft.add(R.id.your_placeholder, new FooFragment());
//// Complete the changes added above
//        ft.commit();

    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        startActivity(new Intent(StepActivity.this,Main2Activity.class));
    }
}
