package com.tiburela.qsercom.storageHelper;

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
import com.tiburela.qsercom.models.ImagenX;

import java.util.ArrayList;
import java.util.UUID;

public class StorageData {


   public static  StorageReference rootStorageReference;

 public static    StorageReference stoRefToUpload ;

private static int counTbucle=0;

    public static void initStorageReference()  {

        FirebaseStorage rootFirebaseStorage = FirebaseStorage.getInstance();


        rootStorageReference = rootFirebaseStorage.getReference();

    }

    public static void uploadImage(Context context, ArrayList<ImagenX> listImagesData) {


        // Code for showing progressDialog while uploading
        ProgressDialog progressDialog
                = new ProgressDialog(context);
        progressDialog.setTitle("Subiendo...");
        progressDialog.show();

         //creamos una lista de nombre
        ArrayList  <String> listaNamesImages=new ArrayList<>();



        for (int i = 0; i < listImagesData.size(); i++) {

            counTbucle++;

            String picNameofStorage= UUID.randomUUID().toString();
            listaNamesImages.add(picNameofStorage);
            Uri uriFilePath =listImagesData.get(i).geturiImage();


            // Defining the child of storageReference
                stoRefToUpload = rootStorageReference.child("imagenes_all_reports/"+picNameofStorage);


                // adding listeners on upload
                // or failure of image

                stoRefToUpload.putFile(uriFilePath)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(
                                            UploadTask.TaskSnapshot taskSnapshot) {

                                        // Image uploaded successfully
                                        // Dismiss dialog
                                        //aqui subimos.....

                                            //ahora sibimos a la base de datos.... la lista de nombres


                                        if(counTbucle ==listImagesData.size()) {

                                            progressDialog.dismiss();
                                 Log.i("diald","se ejecuto este if ");

                                            Toast.makeText(context, "Se subio corectamente", Toast.LENGTH_SHORT).show();
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
