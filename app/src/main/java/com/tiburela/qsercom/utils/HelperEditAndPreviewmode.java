package com.tiburela.qsercom.utils;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

public class HelperEditAndPreviewmode {

    public static  void diseableViewsByTipe(View []arrayViews) {

//String name = (v.getId() == View.NO_ID) ? "" :
//    v.getResources().getResourceName(v.getId()).split(":id/")[1];

        Log.i("cabron", "se llamo diseable by type");


        for(int indice=0; indice<arrayViews.length; indice++){

            View view=arrayViews[indice];


            if (view instanceof TextInputEditText) { //asi es un editex compobamos si esta lleno

                TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

                // editText.setFocusable(false);
                editText.setEnabled(false);
                // editText.setCursorVisible(false);
                // editText.setKeyListener(null);
                //  editText.setBackgroundColor(Color.TRANSPARENT);

            }

            else if(view instanceof Spinner){
                Spinner spinner = (Spinner) view; //asi lo convertimos
                spinner.setEnabled(false);
                spinner.setClickable(false);

            }



            else if(view instanceof Switch) {
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
                swichtView.setEnabled(false);
                swichtView.setClickable(false);

            }
            else if(view instanceof ImageView) {
                ImageView imagev = (ImageView) view; //asi lo convertimos
                imagev.setEnabled(false);
                imagev.setClickable(false);


            }
            else if(view instanceof Button) {
                Button btn = (Button) view; //asi lo convertimos
                btn.setEnabled(false);
                btn.setClickable(false);


            }


        }


    }



    public static void activateViewsByTypeView(View []arrayViews) {

        // ediProductor.setCursorVisible(true);

        //ediProductor.setFocusable(true);


        for (int indice = 0; indice < arrayViews.length; indice++) {

            View view = arrayViews[indice];


            if (view instanceof TextInputEditText) { //asi es un editex compobamos si esta lleno

                TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

                editText.setEnabled(true);


                //  editText.requestFocus();

                // editText.setFocusable(true);
                //  editText.setEnabled(true);
                // editText.setCursorVisible(true);
                // editText.setKeyListener(false);
                //  editText.setBackgroundColor(Color.TRANSPARENT);

            } else if (view instanceof Spinner) {
                Spinner spinner = (Spinner) view; //asi lo convertimos
                spinner.setEnabled(true);
                spinner.setClickable(true);

            } else if (view instanceof Switch) {
                @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
                swichtView.setEnabled(true);
                swichtView.setClickable(true);

            } else if (view instanceof Button) {
                Button btn = (Button) view; //asi lo convertimos
                btn.setEnabled(true);
                btn.setClickable(true);


            } else if (view instanceof ImageView) {
                ImageView btn = (ImageView) view; //asi lo convertimos
                btn.setEnabled(true);
                btn.setClickable(true);


            }

        }


    }






    static void selectValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                Log.i("mizona","existe hurra"+value);
                break;

            }else

            {

                Log.i("mizona","no exiwste "+value);

            }
        }

    }


}
