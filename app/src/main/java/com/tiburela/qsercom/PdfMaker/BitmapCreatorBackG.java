package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

public class BitmapCreatorBackG  implements Runnable{

    private volatile Bitmap myBitmap;
    private Context contexto;
    private String urlImage;



    void iniValuesParams(Context contexto,String urlImage){
        this.contexto=contexto;
        this.urlImage=urlImage;

    }




    @Override
    public void run() {

        Log.i("ladtastor","la url storage pick es "+urlImage);


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


    public Bitmap getValueBitmap() {
        return myBitmap;
    }
}
