package com.tiburela.qsercom.storage;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.BottonSheetCallUploading;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class StorageDataAndRdB {



   public static  StorageReference rootStorageReference;
    static Bitmap bitmapOriginal;
   static UploadTask uploadTask;
   static StorageReference imagename;
   static ByteArrayOutputStream stream;
  static  InputStream inputStream;
static     ImagenReport currenImageReport;
    static   byte[] data;
    static final StorageReference ImageFolderReferenceImagesAll =  FirebaseStorage.getInstance().getReference().child("imagenes_all_reports");//esta iniiclizarla antes

    public static    StorageReference stoRefToUpload ;

 public static String uniqueIDImagesSetAndUInforme="";

   static ArrayList<ImagenReport> imageListToUploadd = new ArrayList<>();

public static int counTbucle=0;
  public   static  int indiceCurrentOFlistIamges=0;

    static Context contextaMiCiela;



    public static void initImagenesAllAndArrayListAndContext(ArrayList<ImagenReport>ImageList,Context contexto){

          imageListToUploadd =ImageList;
          contextaMiCiela=contexto;
          indiceCurrentOFlistIamges=0;
        // Utils.sourceTareas = new TaskCompletionSource<>();
      //  Utils.sourceTareaSubirIMAGENES = new TaskCompletionSource<>();
      //  terminamosUploadAllImages =false;


      }

    public static void initContexta(Context contexto){

        contextaMiCiela=contexto;

    }


    public static void initStorageReference()  {

        FirebaseStorage rootFirebaseStorage = FirebaseStorage.getInstance();

        rootStorageReference = rootFirebaseStorage.getReference();

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


    /**opcion 2 creamos un objeto de */


/*
    public static void uploaddImagesAndDataImages( ImagenReport currenImageReport) throws IOException {



        Log.i("imagheddd", "el size de ImageList es "+imageListToUploadd.size());
        Uri uriImage  = Uri.parse(currenImageReport.geturiImage());
        imagename = ImageFolderReferenceImagesAll.child(currenImageReport.getUniqueIdNamePic());


        boolean existValue=false;

        if(null != uriImage) {
            try {
                inputStream = contextaMiCiela.getContentResolver().openInputStream(uriImage);
                inputStream.close();
                existValue = true;
            } catch (Exception e) {
                Log.i("IMAGESTASKEdit","exepcion aqui y exist value es "+existValue);
            }
        }


        if(existValue){

            Log.i("IMAGESTASKEdit", "bitmap original here ");

            bitmapOriginal = MediaStore.Images.Media.getBitmap(contextaMiCiela.getContentResolver(), uriImage);
            stream = new ByteArrayOutputStream();
            bitmapOriginal.compress(Bitmap.CompressFormat.WEBP,95,stream);//0=lowe


            data = stream.toByteArray();
            uploadTask = imagename.putBytes(data);

            Log.i("IMAGESTASKEdit", "empezandoupload task");

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    Log.i("IMAGESTASKEdit","es falilure");


                    Variables.contadorImagenesSubidas++;



                    Variables.ErrorSubirImage=true;
                    threadx.indiceCurrentObjectx++;


                    threadx.startThreadMismoObject(threadx.indiceCurrentObjectx);




                    Log.i("imagestorage", "existe una exepecion y es "+exception.getMessage());

                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.i("IMAGESTASKEdit","es succes");


                            String iconPathFirebase = uri.toString();
                            currenImageReport.setUrlStoragePic(iconPathFirebase);
                            Log.i("superstorage","se subio imagen y el url esd  al informe "+currenImageReport.getUrlStoragePic());




                        }




                    });
                }
            });

        } else {
            Log.i("IMAGESTASKEdit","se eejcuto el else aqui ");
            Variables.contadorImagenesSubidas++;
            Log.i("IMAGESTASKEdit","el contador imagenes subidas es "+ Variables.contadorImagenesSubidas);
            threadx.indiceCurrentObjectx++;
            threadx.startThreadMismoObject(  threadx.indiceCurrentObjectx);


            Log.i("exepciopmx","no existe valores");

        }





    }

   */

    public  static void uploaddImagesAndDataImages( ImagenReport currenImageReport,int hIloNUm) throws IOException {

        /**SI HAY PROBELASM DE URI PERMISOS ASEGURARSE QUE EL URI CONTENGA UNA PROPIEDAD QUE HACER QUE LE DE PERMISOS DE
         * LECTURA ALGO AS..ESO EN INTENT AL SELECIONAR IMAGENES*/

        Log.i("imagheddd", "el size de ImageList es "+imageListToUploadd.size());
        Uri uriImage  = Uri.parse(currenImageReport.geturiImage());
        imagename = ImageFolderReferenceImagesAll.child(currenImageReport.getUniqueIdNamePic());


        boolean existValue=false;

        if(null != uriImage) {
            try {
                inputStream = contextaMiCiela.getContentResolver().openInputStream(uriImage);
                inputStream.close();
                existValue = true;
            } catch (Exception e) {
                Log.i("IMAGESTASKEdit","exepcion aqui y exist value es");
            }
        }


        if(existValue){

            Log.i("IMAGESTASKEdit", "bitmap original here ");

            bitmapOriginal = MediaStore.Images.Media.getBitmap(contextaMiCiela.getContentResolver(), uriImage);
            stream = new ByteArrayOutputStream();
            bitmapOriginal.compress(Bitmap.CompressFormat.WEBP,95,stream);//0=lowe


            data = stream.toByteArray();
            uploadTask = imagename.putBytes(data);

            Log.i("IMAGESTASKEdit", "empezandoupload task");

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    Log.i("IMAGESTASKEdit","es falilure");

                    /***BIEN NOTE : EL METODO DE ABAJO DEBERIA LLEVAR UN PAREMTRO QUE INDENTIFCQUE EL OBJETO GLOBAL
                     * DE BOOTOMSHEETCALLUPLOADING..
                     *
                     * AHORA INVOCAMOS EL METODO DE BOTTOM SHEET OTRA VEZ Y LE PASAMOS EL NUEVO INDICE
                     *
                     * */
                    Variables.contadorImagenesSubidasSumaAll++;
                    Variables.ErrorSubirImage=true;


                    updateObjectGCurrentIndiceAndContadorUpload(hIloNUm);
                    //callThreadByNumHilo(hIloNUm);

                    Log.i("imagestorage", "existe una exepecion y es "+exception.getMessage());

                    // Handle unsuccessful uploads
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.i("IMAGESTASKEdit","es succes");


                            String iconPathFirebase = uri.toString();
                            currenImageReport.setUrlStoragePic(iconPathFirebase);
                            // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);
                            Log.i("superstorage","se subio imagen y el url esd  al informe "+currenImageReport.getUrlStoragePic());


                            /**aumnetamos el valor del indice en ek on succes dek siguiente metodo*/
                            addNewSetPicsInforme(currenImageReport,hIloNUm);


                        }




                    });
                }
            });

        } else {
            Log.i("IMAGESTASKEdit","se eejcuto el else aqui ");
            Variables.contadorImagenesSubidasSumaAll++;
            Log.i("IMAGESTASKEdit","el contador imagenes subidas es "+ Variables.contadorImagenesSubidasSumaAll);

            updateObjectGCurrentIndiceAndContadorUpload(hIloNUm);
            //callThreadByNumHilo(hIloNUm);
            Log.i("exepciopmx","no existe valores");


        }





    }

    public static void uploaddImagesAndDataImages(int indice) throws IOException {



        if(indice<imageListToUploadd.size()){ //indice  0 size 0 //el indice es 6  lenth 6
              currenImageReport= imageListToUploadd.get(indice);

          //  Variables.theadImagesImages1.start();
        }


          else{

               /**quiere decir que ya hemos subido todos o fue fail ++
                * seria bueno algun contador para contar los fails..*/


              if(Utils.esNuevoReport){

                  BottonSheetCallUploading.uploadConteendoresForm(Variables.FINISH_ALL_UPLOAD);
                  Log.i("finalizando","se eejcuto el if ");
              }

              else

              {
                //  BottonSheetCallUploading.


                  //Log.i("updatexxxx","se eejcuto erl else llamois metodo con finish value");
                 // BottonSheetCallUploading.UpdateReportThread(Variables.FINISH_ALL_UPLOAD);

              }

            return;


        }

        /**SI HAY PROBELASM DE URI PERMISOS ASEGURARSE QUE EL URI CONTENGA UNA PROPIEDAD QUE HACER QUE LE DE PERMISOS DE
         * LECTURA ALGO AS..ESO EN INTENT AL SELECIONAR IMAGENES*/


            Log.i("imagheddd", "el size de ImageList es "+imageListToUploadd.size());
            Uri uriImage  = Uri.parse(currenImageReport.geturiImage());
            imagename = ImageFolderReferenceImagesAll.child(currenImageReport.getUniqueIdNamePic());


        boolean existValue=false;

        if(null != uriImage) {
            try {
                 inputStream = contextaMiCiela.getContentResolver().openInputStream(uriImage);
                inputStream.close();
                existValue = true;
            } catch (Exception e) {
                Log.i("exepciopmx","exepcion aqui y exist value es "+existValue);
            }
        }


        if(existValue){

            Log.i("debugimagenes", "bitmap original here ");

                     bitmapOriginal = MediaStore.Images.Media.getBitmap(contextaMiCiela.getContentResolver(), uriImage);
                      stream = new ByteArrayOutputStream();
                     bitmapOriginal.compress(Bitmap.CompressFormat.WEBP,95,stream);//0=lowe


                     data = stream.toByteArray();
                     uploadTask = imagename.putBytes(data);

            Log.i("debugimagenes", "empezandoupload task");

            uploadTask.addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception exception) {

                             indiceCurrentOFlistIamges++;
                             try {
                                 uploaddImagesAndDataImages(indiceCurrentOFlistIamges);
                             }

                             catch (IOException e) {
                                 throw new RuntimeException(e);
                             }

                             Log.i("imagestorage", "existe una exepecion y es "+exception.getMessage());

                             // Handle unsuccessful uploads
                         }
                     }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                             //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                             imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                 @Override
                                 public void onSuccess(Uri uri) {

                                     Log.i("imagestorage", "es succes");

                                     String iconPathFirebase = uri.toString();
                                     currenImageReport.setUrlStoragePic(iconPathFirebase);
                                     // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);
                                     Log.i("superstorage","se subio imagen y el url esd  al informe "+currenImageReport.getUrlStoragePic());

                                     /**aumnetamos el valor del indice en ek on succes dek siguiente metodo*/
                                  //   RealtimeDB.addNewSetPicsInforme(currenImageReport);


                                 }




                             });
                         }
                     });

                 } else {
                     Log.i("exepciopmx","no existe valores");
                     StorageDataAndRdB.indiceCurrentOFlistIamges++;
                     uploaddImagesAndDataImages(StorageDataAndRdB.indiceCurrentOFlistIamges);

                 }





    }

    public static Bitmap compress(Bitmap yourBitmap){
        //converted into webp into lowest quality
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        yourBitmap.compress(Bitmap.CompressFormat.WEBP,100,stream);//0=lowest, 100=highest quality
        //byte[] byteArray = stream.toByteArray();


        //convert your byteArray into bitmap
      //  Bitmap yourCompressBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        return yourBitmap;
    }
    public static byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBuffer);
        byteBuffer.rewind();
        return byteBuffer.array();
    }


    public static void addNewSetPicsInforme( ImagenReport objecImageReport ,int hiloNum) {

        if(RealtimeDB.mibasedataPathImages==null ) {
            RealtimeDB.initDatabasesReferenceImagesData();
        }



        Map<String, Object> mapValues = objecImageReport.toMap();
        RealtimeDB. mibasedataPathImages.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

             //   int porcentajeX= (Variables.contadorImagenesSubidas/Variables.numImagenesSubirTotal)*100;

                if (task.isSuccessful()) {

                    Log.i("IMAGESTASKEdit","se subio imagen report "+objecImageReport.getUrlStoragePic());
                    Variables.contadorImagenesSubidasSumaAll++;
                    Log.i("IMAGESTASKEdit","el contador imagenes subidas es "+ Variables.contadorImagenesSubidasSumaAll);
                    Log.i("IMAGESTASKEdit","llamamos tread otravez ");


                    updateObjectGCurrentIndiceAndContadorUpload(hiloNum);
                   // callThreadByNumHilo(hiloNum);



                }else
                {

                    Variables. ErrorSubirImage=true;
                    updateObjectGCurrentIndiceAndContadorUpload(hiloNum);

                  //  callThreadByNumHilo(hiloNum);


                }
            }
        });


    }



    public static void updateObjectGCurrentIndiceAndContadorUpload(int numHilo){

        if(numHilo==1){



        }else if(numHilo==2){

        }




    }




}
