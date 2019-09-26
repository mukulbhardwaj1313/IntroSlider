package com.mukulbhardwaj1313.introslider;


import android.content.Context;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;

public class IntroAdapter extends PagerAdapter {


    private Context context;
    private int[] titles;
    private int[] messages;
    private int[] textColor;
    private int textColorInt;
    private boolean isTitleInCenter =false;
    private RelativeLayout.LayoutParams layoutParams;

    IntroAdapter(Context context, int[] titles, int[] messages) {
        this.context = context;
        this.titles = titles;
        this.messages = messages;
    }

    void setTextColor(int[] textColor) {
        this.textColor = textColor;
    }
    void setTextColorInt(int textColorInt) {
        this.textColorInt = textColorInt;
    }
    void setTitleInCenter(boolean titleInCenter) {
        isTitleInCenter = titleInCenter;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @androidx.annotation.NonNull
    @Override
    public Object instantiateItem(@androidx.annotation.NonNull ViewGroup container, int position) {
        View view = View.inflate(container.getContext(), R.layout.fragment_intro_view_layout, null);
        container.addView(view);
        AppCompatTextView headerTextView = view.findViewById(R.id.header_text);
        AppCompatTextView messageTextView = view.findViewById(R.id.message_text);
        try{
            headerTextView.setText(context.getString(titles[position]));
        }catch (Exception ignored){ }
        try {
            messageTextView.setText(Html.fromHtml(context.getString(messages[position])));
        }catch (Exception ignored){}
        try {
            headerTextView.setTextColor(context.getResources().getColor(textColor[position]));
            messageTextView.setTextColor(context.getResources().getColor(textColor[position]));
        }catch (Exception ignored){}
        try {

            headerTextView.setTextColor(context.getResources().getColor(textColorInt));
            messageTextView.setTextColor(context.getResources().getColor(textColorInt));
        }catch (Exception ignored){}

        layoutParams = (RelativeLayout.LayoutParams) headerTextView.getLayoutParams();
        if (isTitleInCenter) {
            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        }else {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }
        headerTextView.setLayoutParams(layoutParams);

        return view;
    }



    @Override
    public void destroyItem(@androidx.annotation.NonNull ViewGroup container, int position, @androidx.annotation.NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(@androidx.annotation.NonNull ViewGroup container, int position, @androidx.annotation.NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(@androidx.annotation.NonNull View view, @androidx.annotation.NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }







}