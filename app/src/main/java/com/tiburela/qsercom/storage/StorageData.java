package com.tiburela.qsercom.storage;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ImagenReport;

import java.util.HashMap;
import java.util.Map;

public class StorageData {


   public static  StorageReference rootStorageReference;

 public static    StorageReference stoRefToUpload ;


private static int counTbucle=0;



    public static void initStorageReference()  {

        FirebaseStorage rootFirebaseStorage = FirebaseStorage.getInstance();


        rootStorageReference = rootFirebaseStorage.getReference();

    }

    public static void uploadImage(Context context,  HashMap<String, ImagenReport> hasmapImagenData) {


        if(hasmapImagenData.size() ==0) {
            Log.i("debugasd","es 0");

            return;
        }

             //iteramos el mapa
        for (Map.Entry<String, ImagenReport> entry : hasmapImagenData.entrySet()) {
            ImagenReport value = entry.getValue();
            String uriFilePath =value.geturiImage();

             Uri myUri = Uri.parse(uriFilePath);
              ///

             //por aqui comprimir la imagen para subir

            counTbucle++;

            // Defining the child of storageReference
                stoRefToUpload = rootStorageReference.child("imagenes_all_reports/"+value.getUniqueIdNamePic());


                // adding listeners on upload
                // or failure of image

                stoRefToUpload.putFile(myUri)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(
                                            UploadTask.TaskSnapshot taskSnapshot) {

                                              //subimos el registro

                                        Log.i("comoer","info "+counTbucle+" = "+hasmapImagenData.size());
                                        RealtimeDB.addNewSetPicsInforme(context,value);

                                        //    startActivity(new Intent(AddNewOfertCupon.this,OfertsAdminActivity.class)) ;


                                    }
                                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {

                                Log.i("simoa","la data es "+e.getMessage());

                                // Error, Image not uploaded
                                Toast
                                        .makeText(context,
                                                "Error " + e.getMessage(),
                                                Toast.LENGTH_SHORT)
                                        .show();
                            }
                        })
                        .addOnProgressListener(
                                new OnProgressListener<UploadTask.TaskSnapshot>() {

                                    // Progress Listener for loading
                                    // percentage on the dialog box
                                    @Override
                                    public void onProgress(
                                            UploadTask.TaskSnapshot taskSnapshot)
                                    {

                                    }
                                });






    }

    }


    public static void actualizaImagenes(Context context,  HashMap<String, ImagenReport> hasmapImagenData) {
          //chekeamos que ya existan estas imagenes...




        // Code for showing progressDialog while uploading
        ProgressDialog progressDialog
                = new ProgressDialog(context);
        progressDialog.setTitle("Subiendo...");
        progressDialog.show();



        //iteramos el mapa
        for (Map.Entry<String, ImagenReport> entry : hasmapImagenData.entrySet()) {
            ImagenReport value = entry.getValue();
            String uriFilePath =value.geturiImage();

            Uri myUri = Uri.parse(uriFilePath);


            counTbucle++;

            // Defining the child of storageReference
            stoRefToUpload = rootStorageReference.child("imagenes_all_reports/"+value.getUniqueIdNamePic());


            // adding listeners on upload
            // or failure of image

            stoRefToUpload.putFile(myUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    //subimos el registro

                                    Log.i("comoer","info "+counTbucle+" = "+hasmapImagenData.size());
                                    RealtimeDB.addNewSetPicsInforme(context,value);

                                    if(counTbucle ==hasmapImagenData.size()) {

                                        progressDialog.dismiss();
                                        Log.i("comoer","se ejecuto este if ");

                                        Toast.makeText(context, "Se subio correctamente", Toast.LENGTH_SHORT).show();






                                    }


                                    //    startActivity(new Intent(AddNewOfertCupon.this,OfertsAdminActivity.class)) ;


                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            Log.i("simoa","la data es "+e.getMessage());

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(context,
                                            "Error " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Subido "
                                                    + (int)progress + "%");
                                }
                            });






        }

    }



}
