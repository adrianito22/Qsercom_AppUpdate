package com.tiburela.qsercom.storage;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StorageData {


   public static  StorageReference rootStorageReference;

 public static    StorageReference stoRefToUpload ;

 public static String uniqueIDImagesSetAndUInforme="";



public static int counTbucle=0;



    public static void initStorageReference()  {

        FirebaseStorage rootFirebaseStorage = FirebaseStorage.getInstance();


        rootStorageReference = rootFirebaseStorage.getReference();

    }


    public static void callADnnNow (HashMap<String, ImagenReport> hasmapImagenData){

    }


    public static void uploadImage(Context context,  HashMap<String, ImagenReport> hasmapImagenData) {

            //iteramos el mapa
            for (ImagenReport value : hasmapImagenData.values()) {
               //  value = entry.getValue();

                Log.i("imagheddd","info se llamo aqui  ");


                //edityamos donde va ir estasiamgenes

                String uriFilePath =value.geturiImage();
                Uri myUri = Uri.parse(uriFilePath);
                ///

                //por aqui comprimir la imagen para subir

                counTbucle++;

                // Defining the child of storageReference
               // stoRefToUpload = rootStorageReference.child("imagenes_all_reports/"+value.getUniqueIdNamePic());
                stoRefToUpload = rootStorageReference.child("imagenes_all_reports/");


                // adding listeners on upload
                // or failure of image

                stoRefToUpload.child(value.getUniqueIdNamePic()).putFile(myUri)
                        .addOnSuccessListener(
                                new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                    @Override
                                    public void onSuccess(
                                            UploadTask.TaskSnapshot taskSnapshot) {


                                      stoRefToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                          @Override
                                          public void onSuccess(Uri uri) {

                                               String iconPathFirebase = uri.toString();

                                              value.setUrlStoragePic(iconPathFirebase);
                                             // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);

                                              Log.i("imagheddd","info es on success  "+iconPathFirebase);

                                              Log.i("imagheddd","info "+counTbucle+" = "+hasmapImagenData.size());
                                              RealtimeDB.addNewSetPicsInforme(value);
                                          }
                                      });


                                        //subimos el registro

                                       /// Uri downloadUri = task.getResult();

                                       // return stoRefToUpload.getDownloadUrl();
                                       //Log.i("comidair","la url es : "+url);




                                        //    startActivity(new Intent(AddNewOfertCupon.this,OfertsAdminActivity.class)) ;


                                    }
                                })

                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {

                                Log.i("imagheddd","el error es  "+e.getMessage());


                                Log.i("imagheddd","la data es "+e.getMessage());

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


                // [START download_via_url]
                stoRefToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // Got the download URL for 'users/me/profile.png'
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Log.i("imagheddd","el error es  "+exception.getMessage());

                        // Handle any errors
                    }
                });
                // [END download_via_url]


            }



    }

    public static void uploadImages(Context context, ArrayList< ImagenReport> listImageReport) {



         ImagenReport currenImageReport= listImageReport.get(counTbucle);
        String uriFilePath =currenImageReport.geturiImage();
        Uri myUri = Uri.parse(uriFilePath);
        //por aqui comprimir la imagen para subir

       // counTbucle++;

        // Defining the child of storageReference
//        stoRefToUpload = rootStorageReference.child("imagenes_all_reports/"+listImageReport.get(counTbucle).getUniqueIdNamePic());


        Log.i("imagheddd","vamos a subir el file con este name  "+currenImageReport.getUniqueIdNamePic());


        stoRefToUpload = rootStorageReference.child("imagenes_all_reports/");

            stoRefToUpload.child(currenImageReport.getUniqueIdNamePic()).putFile(myUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                    stoRefToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {

                                            String iconPathFirebase = uri.toString();

                                            currenImageReport.setUrlStoragePic(iconPathFirebase);
                                            // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);

                                            Log.i("imagheddd","info es on success  "+iconPathFirebase);


                                            if(counTbucle < listImageReport.size()) {
                                                counTbucle++;
                                                uploadImages(context,listImageReport);
                                            }


                                           // Log.i("imagheddd","info "+counTbucle+" = "+hasmapImagenData.size());
                                            RealtimeDB.addNewSetPicsInforme(currenImageReport);


                                        }
                                    });


                                    //subimos el registro

                                    /// Uri downloadUri = task.getResult();

                                    // return stoRefToUpload.getDownloadUrl();
                                    //Log.i("comidair","la url es : "+url);




                                    //    startActivity(new Intent(AddNewOfertCupon.this,OfertsAdminActivity.class)) ;


                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            Log.i("imagheddd","el error es  "+e.getMessage());


                            Log.i("imagheddd","la data es "+e.getMessage());

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


            // [START download_via_url]
            stoRefToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {


                    Log.i("imagheddd","el succes here es"+uri.toString());

                    // Got the download URL for 'users/me/profile.png'
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    Log.i("imagheddd","el error es  "+exception.getMessage());

                    // Handle any errors
                }
            });
            // [END download_via_url]






    }

    public static void uploadFile(Context context, ArrayList< ImagenReport> listImageReport){

        if(counTbucle ==listImageReport.size()){
            return;

        }


        ImagenReport currenImageReport= listImageReport.get(counTbucle);
        String uriFilePath =currenImageReport.geturiImage();
     //   Uri myUri = Uri.parse(uriFilePath);

       Uri myUri= Utils.mapUris.get(currenImageReport.getUniqueIdNamePic());

        Log.i("imagheddd","el size de map uris es   "+Utils.mapUris.size());

        //por aqui comprimir la imagen para subir

        // counTbucle++;

        // Defining the child of storageReference
       stoRefToUpload = rootStorageReference.child("imagenes_all_reports/"+listImageReport.get(counTbucle).getUniqueIdNamePic());


        Log.i("imagheddd","vamos a subir el file con este name  "+currenImageReport.getUniqueIdNamePic());


       // stoRefToUpload = rootStorageReference.child("imagenes_all_reports/");

           //le decimos que si existe uri




            if(myUri!=null){
                stoRefToUpload.putFile(myUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
                {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        return stoRefToUpload.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            Uri downloadUri = task.getResult();
                            Log.e("TAG", "then: " + downloadUri.toString());
                            Log.i("imagheddd","es succces vamos ");


                            if(counTbucle<listImageReport.size()) {
                                counTbucle++;

                                Log.i("imagheddd","info ");
                                RealtimeDB.addNewSetPicsInforme(currenImageReport);

                                uploadFile(context,listImageReport);

                            }





                            //    Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        } else
                        {
                            // Toast.makeText(MainActivity.this, "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                // Toast.makeText(this, "Please upload image!", Toast.LENGTH_SHORT).show();
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
                                  //  RealtimeDB.addNewSetPicsInforme(context,value);

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


    public static void uploaddata(ArrayList<ImagenReport>ImageList) {

        /**SI HAY PROBELASM DE URI PERMISOS ASEGURARSE QUE EL URI CONTENGA UNA PROPIEDAD QUE HACER QUE LE DE PERMISOS DE
         * LECTURA ALGO AS..ESO EN INTENT AL SELECIONAR IMAGENES*/

        Log.i("imagheddd", "el size de ImageList es "+ImageList.size());


        final StorageReference ImageFolder =  FirebaseStorage.getInstance().getReference().child("imagenes_all_reports");
        for (int uploads=0; uploads < ImageList.size(); uploads++) {

       //     String curreNTkEY=mimap.get(ImageList.get(uploads).getUniqueIdNamePic());

                 ImagenReport currenImageReport= ImageList.get(uploads);

            Uri uriImage  = Uri.parse(currenImageReport.geturiImage());

            final StorageReference imagename = ImageFolder.child(ImageList.get(uploads).getUniqueIdNamePic());

            //Bitmap bitmap,
            //

            imagename.putFile(uriImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {


                            Log.i("latypeimage","es succces bien");

                          //  String url = String.valueOf(uri);

                            String iconPathFirebase = uri.toString();

                            currenImageReport.setUrlStoragePic(iconPathFirebase);
                            // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);

                            Log.i("pertenence", "id pertenece a: "+currenImageReport.getIdReportePerteence());

                            Log.i("pertenence","info es on success  y path es  "+iconPathFirebase);



                            RealtimeDB.addNewSetPicsInforme(currenImageReport);


                            // SendLink(url);
                        }
                    });

                }
            });


        }




    }
}
