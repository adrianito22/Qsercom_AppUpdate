package com.tiburela.qsercom.utils;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;

import java.util.HashMap;

public class SharePrefHelper {

    //   guardamos asi
    ////creamos un super array con todas las views...


    public static void viewsSaveInfo(View[] misViews, String keyToSaveMap , Context context){
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




            else  if (vistCurrent instanceof Switch) { //asi es un editex compobamos si esta lleno
                Switch switchz = (Switch) vistCurrent;
                keyViewID=String.valueOf(vistCurrent.getId());

                if(switchz.isChecked()){ //si contiene texto
                    miMapToSave.put(keyViewID,"true");


                }else{
                    miMapToSave.put(keyViewID, "false");

                }


            }



        }

        /*****Recuerda iniiclizar share preferencias en la actividad donde vamos a lllmar este metodo**********/
//        //currentKeySharePrefrences //si esa key emptu o leng =0 ...
        //le pasmoas con un intent en activity see mism reports local prefrences...
        /** desues guardar.....vamos.s... entonces es asi...*/

        //SharePref.init();


        ///aqui guardamos este....
        //creamos un objeto set informe y otro para gaurdar....
        //gaurdamos 2 en share prefrencias...


        if(miMapToSave.size() > 0){
            Toast.makeText(context, "Se guardo Reporte", Toast.LENGTH_SHORT).show();

        }

        else{
            Toast.makeText(context, "No existe Data para Guardar", Toast.LENGTH_SHORT).show();


        }


        SharePref.saveMapPreferFields(miMapToSave,keyToSaveMap);

    }



        ///aqui guardamos este....
        //creamos un objeto set informe y otro para gaurdar....
        //gaurdamos 2 en share prefrencias...


}
