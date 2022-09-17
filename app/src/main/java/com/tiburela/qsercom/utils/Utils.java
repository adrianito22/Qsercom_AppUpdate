package com.tiburela.qsercom.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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



}
