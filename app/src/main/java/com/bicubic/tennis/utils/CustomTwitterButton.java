package com.bicubic.tennis.utils;


import android.content.Context;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;

import com.bicubic.tennis.R;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class CustomTwitterButton extends TwitterLoginButton {
    public CustomTwitterButton(Context context) {
        super(context);
        init();
    }

    public CustomTwitterButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTwitterButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (isInEditMode()){
            return;
        }
        setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable
                .twitter), null, null, null);
        setBackgroundResource(R.drawable.twitter);
        setTextSize(20);
        setPadding(30, 0, 10, 0);
        setTextColor(getResources().getColor(R.color.tw__blue_default));
//        setTypeface(App.getInstance().getTypeface());
    }
}