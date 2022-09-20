package com.tiburela.qsercom.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;

import java.util.Map;

public class RealtimeDB {

 static  public DatabaseReference rootDatabaseReference ;

static  public  DatabaseReference mibasedataPathImages;





    //ESTA VCLASED VA A ENCRAGARSE DE CREAR MODIFICAR ,BORRAR DATOS DE LA BASE DE DATOS REALTIME


    public static  void initDatabasesReference(){

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImagesData");


    }



    private void editInform(){


    }




    public static void addNewInforme(Context context, SetInformEmbarque1 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

           //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);
            Map<String, Object> mapValues = informeObjct.toMap();



        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                   // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void actualizaInformePart1( SetInformEmbarque1 informeObjct) {

        ///"-NCHQnVUUyMat8l_SSwh"
        Log.i("elides","el key o child al que perteence este objeto es "+informeObjct.getKeyFirebase());

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child("-NCHQnVUUyMat8l_SSwh");

        Map<String, Object> mapValues = informeObjct.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void actualizaInformePart2( SetInformEmbarque2 informeObjc2) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child(informeObjc2.getKeyFirebase());

        Map<String, Object> mapValues = informeObjc2.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

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

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);

        Map<String, Object> mapValues = informeObjct.toMap();

        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
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
           initDatabasesReference();

       }


        Map<String, Object> mapValues = objecImageReport.toMap();

        //SUBE MAPA
        mibasedataPathImages.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(context, "HECHO", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }



    public static void UploadProductosPostCosecha( ProductPostCosecha producto) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha");
       // Map<String, Object> mapValues = informeObjct.toMap();
        String PuskEY = mibasedata.push().getKey();

        producto.keyFirebase=PuskEY;
        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }
    public static void UpdateProductosPostCosecha( ProductPostCosecha productosObject) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha").child(productosObject.keyFirebase);
        // Map<String, Object> mapValues = informeObjct.toMap();


        //SUBE MAPA
        mibasedata.setValue(productosObject).addOnCompleteListener(new OnCompleteListener<Void>() {
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
