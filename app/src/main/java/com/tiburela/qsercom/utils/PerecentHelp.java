package com.tiburela.qsercom.utils;

import android.util.Log;

import com.tiburela.qsercom.activities.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.CuadMuestreoCalibAndRechazPrev;
import com.tiburela.qsercom.activities.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.PackingListPreviewActivity;
import com.tiburela.qsercom.activities.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.PreviewsFormDatSContersEnAc;
import com.tiburela.qsercom.callbacks.CallBtoActityFormControlCalid;

import java.util.HashMap;

public class PerecentHelp {
public static HashMap<String,Boolean>estateForm=new HashMap<>();

public static final int NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS=10;
    public static final int NUMERO_ITEMS_FORM_CONTROL_CALIDAD=110;
   // public static final int NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS=120;
   // public static final int NUMERO_ITEMS_FORM_CAMIONS_y_CARRETAS=120;




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


}
