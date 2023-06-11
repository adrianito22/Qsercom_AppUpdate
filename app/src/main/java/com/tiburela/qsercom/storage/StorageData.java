package com.tiburela.qsercom.storage;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
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
import com.tiburela.qsercom.dialog_fragment.BottonSheetCallUploading;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class StorageData {
   public static  StorageReference rootStorageReference;
    static Bitmap bitmapOriginal;
   static UploadTask uploadTask;
   static StorageReference imagename;
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
      //  terminamosUploadAllImages =false;


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



     private static void callIterateUploadData(int indice){

       //  ImagenReport  currenImageReport=  ImageListToUploadd.get(indice);

       //  uploaddata
     }



    public static void uploaddImagesAndDataImages(int indice) throws IOException {

          if(indice<imageListToUploadd.size()){
              currenImageReport= imageListToUploadd.get(indice);
          }

          else{

               /**quiere decir que ya hemos subido todos o fue fail ++
                * seria bueno algun contador para contar los fails..*/

              Log.i("finalizando","ok in uploaddImagesAndDataImages");

               //Ahora vamos a subir register inform
            //  StorageData.terminamosUploadAllImages=true;
              BottonSheetCallUploading.uploadInsertClassQuevamosSubir(Variables.FINISH_ALL_UPLOAD);

               return;
          }

        /**SI HAY PROBELASM DE URI PERMISOS ASEGURARSE QUE EL URI CONTENGA UNA PROPIEDAD QUE HACER QUE LE DE PERMISOS DE
         * LECTURA ALGO AS..ESO EN INTENT AL SELECIONAR IMAGENES*/
          Log.i("imagheddd", "el size de ImageList es "+imageListToUploadd.size());
            Uri uriImage  = Uri.parse(currenImageReport.geturiImage());
              imagename = ImageFolderReferenceImagesAll.child(currenImageReport.getUniqueIdNamePic());
             bitmapOriginal = MediaStore.Images.Media.getBitmap(contextaMiCiela.getContentResolver(), uriImage);

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmapOriginal.compress(Bitmap.CompressFormat.WEBP,95,stream);//0=lowe
            data = stream.toByteArray();

            //
             uploadTask = imagename.putBytes(data);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {

                    indiceCurrentOFlistIamges++;
                    try {
                        uploaddImagesAndDataImages(indiceCurrentOFlistIamges);
                    } catch (IOException e) {
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

                            Log.i("imagestorage", "id pertenece a: "+currenImageReport.getIdReportePerteence());

                            Log.i("imagestorage","info es on success  y path es  "+iconPathFirebase);
                           //  indiceCurrentOFlistIamges++;

                            /**aumnetamos el valor del indice en ek on succes dek siguiente metodo*/
                            RealtimeDB.addNewSetPicsInforme(currenImageReport,contextaMiCiela,indiceCurrentOFlistIamges);


                        }




                    });
            }



        });


     //   }
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
}
