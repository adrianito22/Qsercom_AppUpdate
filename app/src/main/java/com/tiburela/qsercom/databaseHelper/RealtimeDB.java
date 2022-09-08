package com.tiburela.qsercom.databaseHelper;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tiburela.qsercom.models.InformEmbarque;

import java.util.Map;

public class RealtimeDB {

 static   DatabaseReference rootDatabaseReference ;







    //ESTA VCLASED VA A ENCRAGARSE DE CREAR MODIFICAR ,BORRAR DATOS DE LA BASE DE DATOS REALTIME


    public static  void initDatabaseReference(){

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior



    }


    private void editInform(){


    }




    public static void addNewInforme(Context context, String codeInforme, int ediNhojaEvaluacion, String zona, String productor, String codigo, String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte, String ntargetaEmbarque, String inscirpMagap, String horaInicio, String horaTermino, String semana, String empacadora, String contenedor, String cbservacion) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

        InformEmbarque  informeObjct= new InformEmbarque( codeInforme, ediNhojaEvaluacion ,  zona,  productor,  codigo,  pemarque,  nguiaRemision,  hacienda,  _nguia_transporte,  ntargetaEmbarque,  inscirpMagap,  horaInicio,  horaTermino,  semana,  empacadora,  contenedor,  cbservacion) ;

            Map<String, Object> mapValues = informeObjct.toMap();


        //SUBE MAPA
        mibasedata.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


}
