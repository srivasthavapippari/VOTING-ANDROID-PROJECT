package com.example.user.polling;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private Button mNextBtn;
    private Button mBackBtn;
    private int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity0);
        mBackBtn= findViewById(R.id.button);
        mNextBtn= findViewById(R.id.button2);
        mSlideViewPager= findViewById(R.id.slideViewPager);
        mDotLayout= findViewById(R.id.dotsLayout);
        sliderAdapter=new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });
        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage - 1);
            }
        });
    }




    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.trasparent_white));
            mDotLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.backgroundcolor));
        }
    }
    ViewPager.OnPageChangeListener viewListener= new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {
        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            mCurrentPage = i;
            if (i == 0) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(false);
                mBackBtn.setVisibility(View.INVISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("");
            } else if (i == mDots.length - 1) {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Finish");
                mBackBtn.setText("Back");
                mNextBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, MainActivity1.class);

                        startActivity(intent);
                    }
                });

            }
            else if (i == mDots.length) {
                setContentView(R.layout.activity_main);
            }
            else {
                mNextBtn.setEnabled(true);
                mBackBtn.setEnabled(true);
                mBackBtn.setVisibility(View.VISIBLE);
                mNextBtn.setText("Next");
                mBackBtn.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };
}
