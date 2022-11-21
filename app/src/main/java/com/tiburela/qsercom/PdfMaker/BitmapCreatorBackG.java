package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.models.ImagenReport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

public class BitmapCreatorBackG  implements Runnable{

    private volatile Bitmap myBitmap;
    private Context contexto;
    private String urlImage;
    private ImagenReport imgReport;





    void iniValuesParams(Context contexto,String urlImageOrUri,ImagenReport imgReport){
        this.contexto=contexto;
        this.urlImage=urlImageOrUri;

        this.imgReport =imgReport;

    }




    @Override
    public void run() {

     //   Log.i("vamert","la url storage o uri  pick es "+urlImage);

       // boolean exists = Files.exists(Paths.get(imgReport.geturiImage()));






        boolean existUriFile = false;
        Log.i("vamert","url de esta imagen es  es"+urlImage);

        if(null != imgReport.geturiImage()) {
            try {
                InputStream inputStream = contexto.getContentResolver().openInputStream( Uri.parse(imgReport.geturiImage()));
                inputStream.close();
                existUriFile = true;
            } catch (Exception e) {
               // Log.w(MY_TAG, "File corresponding to the uri does not exist " + uri.toString());
            }
        }


        Log.i("vamert","hemos encontrado  file  : "+existUriFile);




        if(existUriFile){  //chekeamos i hay uri y obtenemos la imagen usnadp uri

            Log.i("vamert"," si existe uri y es "+imgReport.geturiImage());



            try {
                myBitmap = MediaStore.Images.Media.getBitmap(contexto.getContentResolver(), Uri.parse(imgReport.geturiImage()));
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else {

            Log.i("vamert","no existe uri y la url es "+urlImage);

            FutureTarget<Bitmap> futureBitmap = Glide.with(contexto)
                    .asBitmap()
                    .onlyRetrieveFromCache(false).
                    load(urlImage)
                    .submit();
            try {
                myBitmap = futureBitmap.get();
                if(myBitmap !=null){


                    Log.i("ladtastor","VACAN");

                    //ByteArrayOutputStream out = new ByteArrayOutputStream();
                    //myBitmap.compress(Bitmap.CompressFormat.PNG, 70, out);


                    //descomprimos

                }

            } catch (ExecutionException e) {



                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // return myBitmap;


        }




    }


    public Bitmap getValueBitmap() {
        return myBitmap;
    }
}
