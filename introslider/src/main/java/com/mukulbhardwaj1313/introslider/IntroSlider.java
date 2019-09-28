package com.mukulbhardwaj1313.introslider;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import static com.mukulbhardwaj1313.introslider.Constants.one_dp;


public class IntroSlider extends RelativeLayout {


    private ViewPager viewPager;
    private ImageView title_image1,title_image2, bg_image1, bg_image2;
    private ViewGroup bottomPages;

    private int delay_duration =300;
    private int animation_duration =900;
    private float translation_length =-300f;
    private float bottomDotsRadius=8;
    private boolean showBottomDots=true;
    private ImageView.ScaleType scaleType= ImageView.ScaleType.FIT_XY;

    private OnSlideChangeListner onSlideChangeListner;

    private Context context;

    private int[] titles ;
    private int[] messages;
    private int[] textColor;
    private int textColorInt;
    private boolean titleInCenter=false;

    private int[] title_image_logo ;

    private int[] bg_image;

    private FrameLayout[] dots;

    int totalPages;

    public IntroSlider(Context context)  {
        super(context);
        this.context=context;
        build();


    }
    public IntroSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context=context;
        init(attrs);
        build();
    }
    public IntroSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(attrs);
        build();
    }



    private void animateViews(){


        ObjectAnimator animation = ObjectAnimator
                .ofFloat(title_image1, "translationY", translation_length);
        animation.setDuration(animation_duration).setStartDelay(delay_duration);
        animation.start();

        ObjectAnimator animation2 = ObjectAnimator
                .ofFloat(title_image2, "translationY", translation_length);
        animation2.setDuration(animation_duration).setStartDelay(delay_duration);
        animation2.start();

        ObjectAnimator animation6 = ObjectAnimator
                .ofFloat(bg_image1, "alpha", 1f);
        animation6.setDuration(animation_duration).setStartDelay(delay_duration);
        animation6.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadViewPager();
            }
        }, delay_duration + animation_duration);

        ObjectAnimator animation7 = ObjectAnimator
                .ofFloat(viewPager, "alpha", 1f);
        animation7.setDuration(animation_duration).setStartDelay(delay_duration + animation_duration);
        animation7.start();


    }

    private void loadViewPager(){

        final int screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;

        if (showBottomDots){
            bottomPages.setVisibility(View.VISIBLE);
        }


        final IntroAdapter introAdapter=new IntroAdapter(context,titles,messages);
        introAdapter.setTextColor(textColor);
        introAdapter.setTextColorInt(textColorInt);
        introAdapter.setTitleInCenter(titleInCenter);

        viewPager.setAdapter(introAdapter);
        if (onSlideChangeListner!=null){
            onSlideChangeListner.onPageChanged(0);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                if (position%2==0 && position<totalPages-1){

                    setTitleImage1(position);
                    setTitleImage2(position+1);

                    setBackGroundImage1(position);
                    setBackGroundImage2(position+1);

                    title_image1.setAlpha(1-positionOffset);
                    title_image2.setAlpha(positionOffset);
                    bg_image1.setAlpha(1-positionOffset);
                    bg_image2.setAlpha(positionOffset);


                    title_image1.setTranslationX(-positionOffsetPixels/3);
                    title_image2.setTranslationX((screenWidth-positionOffsetPixels)/3);

                }else if (position%2==1 && position<totalPages-1){

                    setTitleImage2(position);
                    setTitleImage1(position+1);

                    setBackGroundImage2(position);
                    setBackGroundImage1(position+1);

                    title_image2.setAlpha(1-positionOffset);
                    title_image1.setAlpha(positionOffset);
                    bg_image2.setAlpha(1-positionOffset);
                    bg_image1.setAlpha(positionOffset);


                    title_image2.setTranslationX(-positionOffsetPixels/3);
                    title_image1.setTranslationX((screenWidth-positionOffsetPixels)/3);

                }

            }

            @Override
            public void onPageSelected(int i) {


                if (onSlideChangeListner!=null){
                    onSlideChangeListner.onPageChanged(i);
                }
                for (int a = 0; a < introAdapter.getCount(); a++) {
                    View child = bottomPages.getChildAt(a);
                    if (a == i) {
                        child.setBackground(getResources().getDrawable(R.drawable.bg_indicator_selected,null));
                    } else {
                        child.setBackground(getResources().getDrawable(R.drawable.bg_indicator,null));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void addBottomDots() {
        bottomPages.removeAllViews();
        for (int i = 0; i < totalPages; i++) {
            dots[i] = new FrameLayout(context);
            dots[i].setBackground(context.getResources().getDrawable(R.drawable.bg_indicator,null));
            FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams((int) (one_dp(context)*bottomDotsRadius),(int) (one_dp(context)*bottomDotsRadius));
            layoutParams.setMarginEnd((int) (one_dp(context)*bottomDotsRadius));

            dots[i].setLayoutParams(layoutParams);
            bottomPages.addView(dots[i]);
        }
        bottomPages.getChildAt(0).setBackground(getResources().getDrawable(R.drawable.bg_indicator_selected,null));
    }
    private void setBackGroundImage1(int position){
        try {


            bg_image1.setImageDrawable(getResources().getDrawable(bg_image[position], null));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            bg_image1.setImageDrawable(getResources().getDrawable(R.drawable.transparent, null));
        } catch (Resources.NotFoundException e){
            throw new Resources.NotFoundException(context.getResources().getString(R.string.resource_not_found_bg));

        }

    }
    private void setBackGroundImage2(int position){
        try {


            bg_image2.setImageDrawable(getResources().getDrawable(bg_image[position], null));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            bg_image2.setImageDrawable(getResources().getDrawable(R.drawable.transparent, null));
        } catch (Resources.NotFoundException e){
            throw new Resources.NotFoundException(context.getResources().getString(R.string.resource_not_found_bg));

        }

    }
    private void setTitleImage1(int position){
        try {


            title_image1.setImageDrawable(getResources().getDrawable(title_image_logo[position], null));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            title_image1.setImageDrawable(getResources().getDrawable(R.drawable.transparent, null));
        } catch (Resources.NotFoundException e){
            throw new Resources.NotFoundException(context.getResources().getString(R.string.resource_not_found));

        }

    }
    private void setTitleImage2(int position){
        try {



            title_image2.setImageDrawable(getResources().getDrawable(title_image_logo[position], null));
        }catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            title_image2.setImageDrawable(getResources().getDrawable(R.drawable.transparent, null));
        }
        catch (Resources.NotFoundException e){
            throw new Resources.NotFoundException(context.getResources().getString(R.string.resource_not_found));

        }

    }


    public void setOnSlideChangeListner(OnSlideChangeListner onSlideChangeListner){
        this.onSlideChangeListner=onSlideChangeListner;
    }

    private void build()  {


        this.addView(new SliderLayout(context));
        View v=this.getRootView();


        title_image1 = v.findViewById(R.id.title_image1);
        title_image2 = v.findViewById(R.id.title_image2);
        bg_image1 = v.findViewById(R.id.bg_image1);
        bg_image2 = v.findViewById(R.id.bg_image2);
        viewPager = v.findViewById(R.id.viewPager);
        bottomPages = v.findViewById(R.id.bottom_pages);


        bg_image1.setAlpha(0f);
        bg_image2.setAlpha(0f);
        viewPager.setAlpha(0f);
        title_image2.setAlpha(1f);
        title_image1.setAlpha(1f);


        setBackGroundImageScaleType(scaleType);

    }

    private void init(@Nullable AttributeSet set){

        if(set == null){
            return;
        }

        TypedArray ta = getContext().obtainStyledAttributes(set, R.styleable.IntroSlider);

        delay_duration=ta.getInt(R.styleable.IntroSlider_delay_duration,300);
        animation_duration=ta.getInt(R.styleable.IntroSlider_animation_duration,900);
        translation_length=-ta.getFloat(R.styleable.IntroSlider_title_image_translation,500);
        bottomDotsRadius=ta.getDimension(R.styleable.IntroSlider_bottom_dot_radius,8);
        showBottomDots=ta.getBoolean(R.styleable.IntroSlider_show_bottom_dots,true);
        titleInCenter=ta.getBoolean(R.styleable.IntroSlider_title_in_center,false);
        scaleType=ImageView.ScaleType.values()[ta.getInt(R.styleable.IntroSlider_backGround_image_scaleType,1)];



        int BG_ArrayResourceId = ta.getResourceId(R.styleable.IntroSlider_background_images, 0);
        if (BG_ArrayResourceId != 0) {
            final TypedArray resourceArray = getResources().obtainTypedArray(BG_ArrayResourceId);
            bg_image=new int[resourceArray.length()];
            for (int i = 0; i < resourceArray.length(); i++) {
                bg_image[i]=resourceArray.getResourceId(i, 0);
            }
            resourceArray.recycle();
        }


        int titleArrayResourceId = ta.getResourceId(R.styleable.IntroSlider_titles, 0);
        if (titleArrayResourceId != 0) {
            final TypedArray resourceArray = getResources().obtainTypedArray(titleArrayResourceId);
            titles=new int[resourceArray.length()];
            for (int i = 0; i < resourceArray.length(); i++) {
                titles[i]=resourceArray.getResourceId(i, 0);
            }
            totalPages=titles.length;
            dots = new FrameLayout[totalPages];
            resourceArray.recycle();
        }


        int subTitleArrayResourceId = ta.getResourceId(R.styleable.IntroSlider_sub_titles, 0);
        if (subTitleArrayResourceId != 0) {
            final TypedArray resourceArray = getResources().obtainTypedArray(subTitleArrayResourceId);
            messages=new int[resourceArray.length()];
            for (int i = 0; i < resourceArray.length(); i++) {
                messages[i]=resourceArray.getResourceId(i, 0);
            }
            resourceArray.recycle();
        }


        int titleImageArrayResourceId = ta.getResourceId(R.styleable.IntroSlider_title_images, 0);
        if (titleImageArrayResourceId != 0) {
            final TypedArray resourceArray = getResources().obtainTypedArray(titleImageArrayResourceId);
            title_image_logo=new int[resourceArray.length()];
            for (int i = 0; i < resourceArray.length(); i++) {
                title_image_logo[i]=resourceArray.getResourceId(i, 0);
            }
            setTitleImage1(0);
            setBackGroundImage1(0);
            resourceArray.recycle();
        }



        ta.recycle();
    }



    public void initiateAnimation(){

        if (totalPages!=0) {
            addBottomDots();
            animateViews();
        }else {
            throw new RuntimeException("initiateAnimation() method called before provideTitles()");
        }
    }
    public IntroSlider provideBackgroundImages(int[] bg_image){
        this.bg_image=bg_image;
        setTitleImage1(0);
        setBackGroundImage1(0);
        return this;
    }
    public IntroSlider provideTitleImages(int[] title_image_logo){
        this.title_image_logo=title_image_logo;
        setTitleImage1(0);
        setBackGroundImage1(0);
        return this;
    }
    public IntroSlider provideTitles(int[] titles){
        this.titles=titles;
        totalPages=titles.length;
        dots = new FrameLayout[totalPages];
        return this;
    }
    public IntroSlider provideSubTitles(int[] messages){
        this.messages=messages;
        return this;
    }
    public IntroSlider provideTextColor(int[] textColor){
        this.textColor=textColor;
        return this;
    }
    public IntroSlider provideTextColor(int textColor){
        this.textColorInt=textColor;
        return this;
    }



    public IntroSlider setDelayDuration(int delay_duration){
        this.delay_duration=delay_duration;

        return this;
    }
    public IntroSlider setAnimationDuration(int animation_duration) {
        this.animation_duration = animation_duration;
        return this;
    }
    public IntroSlider setTranslationUpwards(float translation_length) {
        this.translation_length = -translation_length;
        return this;
    }
    public IntroSlider setBackGroundImageScaleType(ImageView.ScaleType scaleType){
        bg_image1.setScaleType(scaleType);
        bg_image2.setScaleType(scaleType);
        return this;
    }
    public IntroSlider setbottomDotsRadiusInDP(float radiusInDP){
        bottomDotsRadius=radiusInDP;
        return this;
    }
    public IntroSlider showbottomDots(boolean show){
        showBottomDots=show;
        return  this;
    }
    public IntroSlider setTitleInCenter(boolean titleInCenter) {
        this.titleInCenter = titleInCenter;
        return this;
    }
}






