package com.tiburela.qsercom.Customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

public class SelectAgainSpinner extends androidx.appcompat.widget.AppCompatSpinner {

    private int lastSelected = 0;

    public SelectAgainSpinner(Context context)
    { super(context); }

    public SelectAgainSpinner(Context context, AttributeSet attrs)
    { super(context, attrs); }

    public SelectAgainSpinner(Context context, AttributeSet attrs, int defStyle)
    { super(context, attrs, defStyle); }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(this.lastSelected == this.getSelectedItemPosition() && getOnItemSelectedListener() != null)
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), this.getSelectedItemPosition(), getSelectedItemId());
        if(!changed)
            lastSelected = this.getSelectedItemPosition();

        super.onLayout(changed, l, t, r, b);
    }
}