package com.tiburela.qsercom.databaseHelper;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

import java.util.HashMap;
import java.util.Map;

public class RealtimeDB {

 static   DatabaseReference rootDatabaseReference ;

static   DatabaseReference mibasedataPathImages;





    //ESTA VCLASED VA A ENCRAGARSE DE CREAR MODIFICAR ,BORRAR DATOS DE LA BASE DE DATOS REALTIME


    public static  void initDatabaseReference(){

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImgsPhatStorage");


    }

    public static  void initDatabaseReferenceImgsPhatStorage(){

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior
         mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImgsPhatStorage");



    }

    private void editInform(){


    }




    public static void addNewInforme(Context context, SetInformEmbarque1 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");
            Map<String, Object> mapValues = informeObjct.toMap();

        //SUBE MAPA
        mibasedata.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                   // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void addNewInforme(Context context, SetInformEmbarque2 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");
        Map<String, Object> mapValues = informeObjct.toMap();

        //SUBE MAPA
        mibasedata.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void addNewSetPicsInforme(Context context, ImagenReport objecImageReport ) {

       if(mibasedataPathImages==null ) {
           initDatabaseReferenceImgsPhatStorage();

       }


        Map<String, Object> mapValues = objecImageReport.toMap();

        //SUBE MAPA
        mibasedataPathImages.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(context, "Se subioron Las IMAGENES", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }



    public static void UploadProductosPostCosecha(Context context, HashMap<String,String> dataToHasmapProdcuts) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha");
       // Map<String, Object> mapValues = informeObjct.toMap();

        //SUBE MAPA
        mibasedata.push().setValue(dataToHasmapProdcuts).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }




}
