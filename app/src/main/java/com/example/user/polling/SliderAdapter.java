package com.example.user.polling;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by user on 16/4/18.
 */

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    RelativeLayout layout_slide;
    public SliderAdapter(Context context){

        this.context=context;
    }
    public int[] slide_images={
            R.drawable.voting2,
            R.drawable.voting1,
            R.drawable.voting3
    };

    public String[] slide_headings={"VOTE","AS A CITIZEN","READY TO VOTE"};

    public String[] slide_descs={"\"The Ballot is Stronger than Bullet\"",
            "\"It's very important to vote.People \n             died for this right\"",
            "\"Your vote is your voice\""};
    @Override
    public int getCount() {

        return this.slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(RelativeLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        //layout_slide=(RelativeLayout) view.findViewById(R.id.relative_layout);

        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=(ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);
        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
