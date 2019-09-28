package com.mukulbhardwaj1313.introslider;

import android.content.Context;
import android.view.Gravity;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;

import static com.mukulbhardwaj1313.introslider.Constants.one_dp;


public class AdapterRelativeLayoutCell extends RelativeLayout {
    public AdapterRelativeLayoutCell(Context context) {
        super(context);

        LayoutParams rlp = new LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        this.setLayoutParams(rlp);

        AppCompatTextView headerTextView = new AppCompatTextView(context);
        headerTextView.setId(R.id.headerTextView);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins((int) (16*one_dp(context)), (int) (0*one_dp(context)), (int) (16*one_dp(context)), (int) (200*one_dp(context)));
        lp.addRule(Gravity.CENTER);
        headerTextView.setTextSize(20);
        headerTextView.setGravity(Gravity.CENTER);
        headerTextView.setLayoutParams(lp);

        AppCompatTextView messageTextView = new AppCompatTextView(context);
        messageTextView.setId(R.id.messageTextView);
        LayoutParams lp2 = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp2.setMargins((int) (16*one_dp(context)), (int) (-180*one_dp(context)), (int) (16*one_dp(context)), (int) (0*one_dp(context)));
        lp2.addRule(Gravity.CENTER);
        lp2.addRule(RelativeLayout.BELOW,headerTextView.getId());
        messageTextView.setTextSize(13);
        messageTextView.setGravity(Gravity.CENTER);
        messageTextView.setLayoutParams(lp2);


        this.addView(headerTextView);
        this.addView(messageTextView);

    }

}
