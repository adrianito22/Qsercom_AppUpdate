package com.tiburela.qsercom.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tiburela.qsercom.R;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {




    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypeface(context);


    }


    private void initTypeface(Context context) {

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "m_bold.ttf");
        this.setTypeface(tf);


    }



}
