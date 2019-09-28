package com.mukulbhardwaj1313.introslider;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import static com.mukulbhardwaj1313.introslider.Constants.one_dp;


public class SliderLayout extends RelativeLayout {
    public SliderLayout(Context context) {
        super(context);

        LayoutParams rlp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(rlp);


        LayoutParams lp_for_bg_image2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);


        ImageView bg_image2 = new ImageView(context);
        bg_image2.setId(R.id.bg_image2);
        bg_image2.setLayoutParams(lp_for_bg_image2);
        bg_image2.setScaleType(ImageView.ScaleType.FIT_XY);
        bg_image2.setAlpha(0f);
        this.addView(bg_image2);

        LayoutParams lp_for_bg_image = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);


        ImageView bg_image1 = new ImageView(context);
        bg_image1.setId(R.id.bg_image1);
        bg_image1.setLayoutParams(lp_for_bg_image);
        bg_image1.setScaleType(ImageView.ScaleType.FIT_XY);
        bg_image1.setAlpha(0f);
        this.addView(bg_image1);



        LayoutParams lp_for_title_image2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp_for_title_image2.addRule(RelativeLayout.CENTER_IN_PARENT);


        ImageView title_image2 = new ImageView(context);
        title_image2.setId(R.id.title_image2);
        title_image2.setLayoutParams(lp_for_title_image2);
        title_image2.setAlpha(0f);
        this.addView(title_image2);


        LayoutParams lp_for_title_image = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp_for_title_image.addRule(RelativeLayout.CENTER_IN_PARENT);


        ImageView title_image1 = new ImageView(context);
        title_image1.setId(R.id.title_image1);
        title_image1.setLayoutParams(lp_for_title_image);
        this.addView(title_image1);


        ViewPager viewPager=new ViewPager(context);
        viewPager.setId(R.id.viewPager);
        this.addView(viewPager);

        LayoutParams lp_for_bottom_dots = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp_for_bottom_dots.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp_for_bottom_dots.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp_for_bottom_dots.setMargins(0, 0, 0, (int) (70*one_dp(context)));

        LinearLayout linearLayout=new LinearLayout(context);
        linearLayout.setId(R.id.bottom_pages);
        linearLayout.setLayoutParams(lp_for_bottom_dots);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setVisibility(GONE);
        this.addView(linearLayout);




    }

}
