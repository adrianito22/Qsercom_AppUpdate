package com.tiburela.qsercom.utils;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tiburela.qsercom.SharePref.SharePref;

import java.util.ArrayList;
import java.util.HashMap;

public class PerecentHelp {

public static HashMap<String,Boolean>estateForm=new HashMap<>();

public static final int NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS=100;
    public static final int NUMERO_ITEMS_FORM_CONTROL_CALIDAD=110;
   // public static final int NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS=120;
   // public static final int NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS=120;
    public static ArrayList<View> listViewsClickedUser;




    public static void addValueOrEditItemHasMap(String key, boolean esFieldComplete){

    estateForm.put(key,esFieldComplete);



}


public static int generatePercetProgress(int tipoFormulario){

    int NUMERO_TOTAL_ITEMS_OF_FORM=0;
    int numeroItemsllenos=0;
    int porcentajeDeProgreso=0;


    //iteramos el hasmpa

    for (Boolean itemISlleno : estateForm.values()) {

        if(itemISlleno){
            numeroItemsllenos++;
            Log.i("miperecje","el progreso es "+porcentajeDeProgreso);
        }

        // ...
    }


    if(tipoFormulario==Variables.FormCantrolCalidad){
        NUMERO_TOTAL_ITEMS_OF_FORM=NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS;
    }

    else  if(tipoFormulario==Variables.FormPreviewContenedores){
        NUMERO_TOTAL_ITEMS_OF_FORM=NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS;

    }
    else  if(tipoFormulario==Variables.FormatDatsContAcopi){

        NUMERO_TOTAL_ITEMS_OF_FORM=NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS;

    }

    else  if(tipoFormulario==Variables.FormCamionesyCarretasActivity){//
        NUMERO_TOTAL_ITEMS_OF_FORM=NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS;

    }


    return  numeroItemsllenos*100/NUMERO_TOTAL_ITEMS_OF_FORM;



}


    public static void checkeamosSiFieldViewIScompletedAndSavePref(View view, String KeyTOsaveShare) {

        //revismaos si el usuario lleno el file o completo la tarea solictada

        Log.i("miodata","el id del selecionado anterior es "+view.getResources().getResourceName(view.getId()));


        if (view instanceof EditText) { //asi es un editex compobamos si esta lleno
            EditText editText = (EditText) view; //asi lo convertimos
            Log.i("miodata","el id es "+view.getResources().getResourceName(view.getId()));

            if ( view.getResources().getResourceName(view.getId()).contains("ediPPC0")){ //asi comprobamos que es un fiel opcional
                if (editText.getText().toString().length() > 0) {
                    if (!editText.getText().toString().equals("0")) {


                        //add VALUES IN TO MAP
                        Variables.currentMapPreferences.put(String.valueOf(view.getId()),editText.getText().toString());

                        //lo guardamos en esa key
                        SharePref.saveMapPreferFields(Variables.currentMapPreferences,KeyTOsaveShare);



                    }
                }

            }

            else if(editText.getText().toString().isEmpty()) {

                Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta vacio");


            }




            else if(! editText.getText().toString().isEmpty()) { //si esta lleno


                //add VALUES IN TO MAP
                Variables.currentMapPreferences.put(String.valueOf(view.getId()),editText.getText().toString());

                //lo guardamos en esa key
                SharePref.saveMapPreferFields(Variables.currentMapPreferences,KeyTOsaveShare);


            }



        }



/*
        else if (view.getResources().getResourceName(view.getId()).contains("imbAtach")  ||  view.getResources().getResourceName(view.getId()).contains("imbTakePic")){ //imBtakePic

            //COMPORBAQMOS SI EXISTE AL ME4NOS UN IMAGEN URI LIST..

            if(ImagenReport.hashMapImagesData.size()> 0 ) {
                actualizaListStateView("imbAtach/imbTakePic",true) ;

                Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y contiene al menos una foto");


            }else {

                actualizaListStateView("imbAtach/imbTakePic",false) ;
                Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y no contiene fotos");



            }

*/



        }






        //seran mas comprobacion para verificar si imagenes por ejemplo fiueron completadas..
        //otra para radiobutton y otr para otro tipo de view..tec



    public static  View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(PerecentHelp.listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
            PerecentHelp.listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

        }
        Log.i("casnasd","el size aqui en metodo es "+PerecentHelp.listViewsClickedUser.size());




        View vistAnterior = PerecentHelp.listViewsClickedUser.get(0);
        //  Log.i("soeobjetc","el objeto anterioR TAG ES "+vistAnterior.getTag().toString());



        return   vistAnterior;

    }


    }



