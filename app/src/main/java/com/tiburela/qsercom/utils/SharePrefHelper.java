package com.tiburela.qsercom.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.models.InformRegister;

import java.util.HashMap;
import java.util.Map;

public class SharePrefHelper {

    //   guardamos asi
    ////creamos un super array con todas las views...


    public static void viewsSaveInfo(View[] misViews, String keyToSaveMap , Context context){

        HashMap<String ,String> miMapToSave= new HashMap<>() ;

        String keyViewID;

        int contador=0;
        for(View vistCurrent: misViews){

            if(vistCurrent==null){

                Log.i("solerma","es nulo contador es  "+contador);


            }

            contador++;


            if (vistCurrent instanceof EditText) { //asi es un editex compobamos si esta lleno
                EditText editText = (EditText) vistCurrent;

                if(!editText.getText().toString().trim().isEmpty()){ //si contiene texto
                    keyViewID=String.valueOf(vistCurrent.getId());
                    miMapToSave.put(keyViewID,editText.getText().toString() );


                }


            }

/*
            if (vistCurrent instanceof TextInputEditText) { //asi es un editex compobamos si esta lleno
                TextInputEditText editText = (TextInputEditText) vistCurrent;

                if(!editText.getText().toString().trim().isEmpty()){ //si contiene texto
                    keyViewID=String.valueOf(vistCurrent.getId());
                    miMapToSave.put(keyViewID,editText.getText().toString() );


                }


            }
*/



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
            miMapToSave.put("Adrianito","");
            Toast.makeText(context, "No existe Data para Guardar", Toast.LENGTH_SHORT).show();


        }


        Log.i("solerma","el sise de map es "+miMapToSave.size()+" y el key es "+keyToSaveMap);

        SharePref.saveMapPreferFields(miMapToSave,keyToSaveMap);

    }


    public static void viewsSaveInfoEditText(EditText[] misViews, String keyToSaveMap ){

       //chekear haber que pasa si se guarda la data... y si el key existe

        HashMap<String ,String> miMapToSave= new HashMap<>() ;

        String keyViewID;

        for(View vistCurrent: misViews){

                EditText editText = (EditText) vistCurrent;
                if(!editText.getText().toString().trim().isEmpty()){ //si contiene texto
                    keyViewID=String.valueOf(vistCurrent.getId());
                    miMapToSave.put(keyViewID,editText.getText().toString() );

                }
        }


        Log.i("preferido","el key save map es "+keyToSaveMap);

        SharePref.saveMapPreferFields(miMapToSave,keyToSaveMap);

    }


    public static void UpdateRegisterLOCALEMarcaSubido(boolean seSubioFile,String keyPrefrencesIfUserSaveReportLocale) {

        Map<String, InformRegister> mapAllReportsRegister = SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

        if (mapAllReportsRegister.containsKey(keyPrefrencesIfUserSaveReportLocale)) {

            Log.i("superkey", "si contiene el key");

            InformRegister objec = mapAllReportsRegister.get(keyPrefrencesIfUserSaveReportLocale);

            assert objec != null;
            objec.setSeSubioFormAlinea(seSubioFile);
            mapAllReportsRegister.put(keyPrefrencesIfUserSaveReportLocale, objec);

            Log.i("superkey", "se subio a linea?" + objec.isSeSubioFormAlinea());

            SharePref.saveHashMapOfHashmapInformRegister(mapAllReportsRegister, SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);


        }
    }
    ///aqui guardamos este....
        //creamos un objeto set informe y otro para gaurdar....
        //gaurdamos 2 en share prefrencias...


}
