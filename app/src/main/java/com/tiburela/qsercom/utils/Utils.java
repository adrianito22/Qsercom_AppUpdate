package com.tiburela.qsercom.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;

import com.tiburela.qsercom.models.ImagenReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {


    public static void closeKeyboard( Activity activity)
    {//Aahora nos toca mostrar las imagenes....en los recicler views...
        //necesitamos ,,, call recicler metodo...
        //Le pasamos los 4 recicler views de imagenes....y la data de las imagenes




    }


    public static String getFileNameByUri(Context context, Uri uri) //obtiene el nombre y la extension,, al menos..

    {
        Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
        int nameColumnIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String fileName = returnCursor.getString(nameColumnIndex);
        return fileName;
    }



    public static String getFormate(String ImageName) {

        return (ImageName.substring(ImageName.indexOf('.'),ImageName.length()));
    }


    public static String getFormate2(String ImageName) {
       String finallyz;

        finallyz=    ImageName.substring(ImageName.lastIndexOf(".")); // Extension with dot .jpg, .png

        return finallyz;
    }







    public static  HashMap<String, ImagenReport> creaHahmapNoDuplicado ( ) {  //le pasmaos un hashmap

        ArrayList<ImagenReport> miListaUno=new ArrayList<>();

        for (Map.Entry<String, ImagenReport> entry : Variables.hashMapImagesStart.entrySet()) { //creamos una lista
            //  String key = entry.getKey();
            ImagenReport imageRoBject = entry.getValue();
            miListaUno.add(imageRoBject);
        }


        ArrayList<ImagenReport> miListaDos=new ArrayList<>(); //lista2

        for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) { //creamos otra lista
            //  String key = entry.getKey();
            ImagenReport imageRoBject = entry.getValue();
            miListaDos.add(imageRoBject);
        }





        HashMap<String, ImagenReport> hashMapImagesDataFilter= new HashMap<>(); //creamos el hasmap donde vamos a devolver los items no duplicados

        for (int indice=0; indice<miListaDos.size(); indice++) { //la ultima lista sera
          //  String key = entry.getKey();
            ImagenReport imageRoBject=miListaDos.get(indice);


            if(!miListaUno.contains(imageRoBject)){
                hashMapImagesDataFilter.put(imageRoBject.getUniqueIdNamePic(),imageRoBject);
            }

        }

        return hashMapImagesDataFilter;

    }


}
