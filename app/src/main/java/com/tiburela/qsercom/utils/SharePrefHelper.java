package com.tiburela.qsercom.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.HashMap;

public class SharePrefHelper {

    //   guardamos asi
    ////creamos un super array con todas las views...


    private void viewsSaveInfo(View[] misViews ){
        HashMap<String ,String> miMapToSave= new HashMap<>() ;

        String keyViewID;

        for(View vistCurrent: misViews){

            if (vistCurrent instanceof EditText) { //asi es un editex compobamos si esta lleno
                EditText editText = (EditText) vistCurrent;

                if(!editText.getText().toString().trim().isEmpty()){ //si contiene texto
                    keyViewID=String.valueOf(vistCurrent.getId());
                    miMapToSave.put(keyViewID,editText.getText().toString() );


                }


            }



           else  if (vistCurrent instanceof Spinner) { //asi es un editex compobamos si esta lleno
                Spinner spinner = (Spinner) vistCurrent;

                if(!spinner.getSelectedItem().toString().trim().isEmpty()){ //si contiene texto
                    keyViewID=String.valueOf(vistCurrent.getId());
                    miMapToSave.put(keyViewID,spinner.getSelectedItem().toString() );


                }


            }


            else  if (vistCurrent instanceof RadioButton) { //asi es un editex compobamos si esta lleno
                RadioButton radiB = (RadioButton) vistCurrent;
                keyViewID=String.valueOf(vistCurrent.getId());

                if(radiB.isChecked()){ //si contiene texto
                    miMapToSave.put(keyViewID,"true");


                }else{
                    miMapToSave.put(keyViewID, "false");

                }


            }


        }



        ///aqui guardamos este....
        //creamos un objeto set informe y otro para gaurdar....
        //gaurdamos 2 en share prefrencias...

    }





}
