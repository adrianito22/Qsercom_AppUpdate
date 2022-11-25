package com.tiburela.qsercom.Customviews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.tiburela.qsercom.R;

public class EditextSupreme extends androidx.appcompat.widget.AppCompatEditText {

    public EditextSupreme(@NonNull Context context) {
        super(context);
    }

    public EditextSupreme(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

      //  ContextCompat.getDrawable(getContext(), R.drawable.btnxxx);
        setBackgroundDrawable( ContextCompat.getDrawable( context, R.drawable.back_edit) );

          //setBackgroundDrawable(AppCompatResources.getDrawable(R.drawable.btnxxx));


      //  ResourcesCompat.getDrawable(getResources(), R.drawable.btnxxx,getContext().getTheme());
       // ContextCompat.getDrawable(getApplicationContext(),R.drawable.example);

      //  ContextCompat.getDrawable(getActivity(), R.drawable.name);

        //  ContextCompat.getDrawable(, R.drawable.btnxxx);
     //  setBackgroundDrawable(getResources().getDrawable(R.drawable.btnxxx));
        //FYI, there is a setTextColor method that accepts a color ID.
      //  setTextColor(myButtonTextCol
    }




    public EditextSupreme(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
