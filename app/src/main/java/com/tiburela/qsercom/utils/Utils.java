package com.tiburela.qsercom.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {

  public static final String KEY_IIMAGES_SHARE ="MIMAGEKEYSAHRE";

  public static   ArrayList<ImagenReport> listImagesToSaVE; //lista2

    public static void closeKeyboard(Activity activity) {//Aahora nos toca mostrar las imagenes....en los recicler views...
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

        return (ImageName.substring(ImageName.indexOf('.'), ImageName.length()));
    }


    public static String getFormate2(String ImageName) {
        String finallyz;

        finallyz = ImageName.substring(ImageName.lastIndexOf(".")); // Extension with dot .jpg, .png

        return finallyz;
    }


    public static HashMap<String, ImagenReport> creaHahmapNoDuplicado() {  //le pasmaos un hashmap

        ArrayList<ImagenReport> miListaUno = new ArrayList<>();

        for (Map.Entry<String, ImagenReport> entry : Variables.hashMapImagesStart.entrySet()) { //creamos una lista
            //  String key = entry.getKey();
            ImagenReport imageRoBject = entry.getValue();
            miListaUno.add(imageRoBject);
        }


        ArrayList<ImagenReport> miListaDos = new ArrayList<>(); //lista2

        for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) { //creamos otra lista
            //  String key = entry.getKey();
            ImagenReport imageRoBject = entry.getValue();
            miListaDos.add(imageRoBject);
        }


        HashMap<String, ImagenReport> hashMapImagesDataFilter = new HashMap<>(); //creamos el hasmap donde vamos a devolver los items no duplicados

        for (int indice = 0; indice < miListaDos.size(); indice++) { //la ultima lista sera
            //  String key = entry.getKey();
            ImagenReport imageRoBject = miListaDos.get(indice);


            if (!miListaUno.contains(imageRoBject) && imageRoBject.geturiImage() != null) {
                Log.i("elfile", "el file uri es " + imageRoBject.geturiImage());

                hashMapImagesDataFilter.put(imageRoBject.getUniqueIdNamePic(), imageRoBject);
            }

        }

        Log.i("elfile", "retornamos un size de " + hashMapImagesDataFilter.size());

        return hashMapImagesDataFilter;

    }


    public static HashMap<String, String> creMapByObject(ProductPostCosecha productPostC) {

        HashMap<String, String> mapProductP = new HashMap<String, String>();

        if (productPostC.alumbre.length() > 0 && !productPostC.alumbre.trim().isEmpty()) {

            mapProductP.put("ALUMBRE", productPostC.alumbre);
        }


        if (productPostC.bc100.length() > 0 && !productPostC.bc100.trim().isEmpty()) {

            mapProductP.put("BC100", productPostC.bc100);
        }

        if (productPostC.sb100.length() > 0 && !productPostC.sb100.trim().isEmpty()) {

            mapProductP.put("SB100", productPostC.sb100);
        }


        if (productPostC.eclipse.length() > 0 && !productPostC.eclipse.trim().isEmpty()) {

            mapProductP.put("ECLIPSE", productPostC.eclipse);
        }

        if (productPostC.acido_citrico.length() > 0 && !productPostC.acido_citrico.trim().isEmpty()) {

            mapProductP.put("ACIDO CITRICO", productPostC.acido_citrico);
        }

        if (productPostC.biottol.length() > 0 && !productPostC.biottol.trim().isEmpty()) {

            mapProductP.put("BIOTTOL", productPostC.biottol);
        }


        if (productPostC.bromorux.length() > 0 && !productPostC.bromorux.trim().isEmpty()) {

            mapProductP.put("BROMORUX", productPostC.bromorux);
        }


        if (productPostC.ryzuc.length() > 0 && !productPostC.ryzuc.trim().isEmpty()) {

            mapProductP.put("RYZUC", productPostC.ryzuc);
        }


        if (productPostC.mertec.length() > 0 && !productPostC.mertec.trim().isEmpty()) {

            mapProductP.put("MERTEC", productPostC.mertec);
        }

        if (productPostC.sastifar.length() > 0 && !productPostC.sastifar.trim().isEmpty()) {

            mapProductP.put("SASTISFAR", productPostC.sastifar);
        }


        if (productPostC.xtrata.length() > 0 && !productPostC.xtrata.trim().isEmpty()) {

            mapProductP.put("XTRATA", productPostC.xtrata);
        }


        if (productPostC.nlarge.length() > 0 && !productPostC.nlarge.trim().isEmpty()) {

            mapProductP.put("NLARGE", productPostC.nlarge);
        }


        if (productPostC.gib_bex.length() > 0 && !productPostC.gib_bex.trim().isEmpty()) {

            mapProductP.put("GIB-BEX", productPostC.gib_bex);
        }


        if (productPostC.cloro.length() > 0 && !productPostC.cloro.trim().isEmpty()) {

            mapProductP.put("CLORO", productPostC.cloro);
        }

        if (productPostC.otro_especifique.length() > 0 && !productPostC.otro_especifique.trim().isEmpty()) {

            mapProductP.put(productPostC.otro_especifique, productPostC.otro_especifique);
        }


        return mapProductP;
    }

    //SEGUIMOS CON LA BARRA DE PROGRESO Y GUARDA data.. despues del metodo,,
    // usamos este


    public static HashMap<String, String> dataFieldsPreferencias;


    public static void addDataMapPreferences(String key, String value, String idFormularioUniqueId, Context context) {
        //agregamos la primera......


        if (dataFieldsPreferencias.size() == 0) {
            dataFieldsPreferencias.put("estaSubido", "no"); ///cuando subamos el infor5me lo cambiaremos a si

        }


        dataFieldsPreferencias.put(key, value);
        Log.i("xdebuge", "el nuevo valor a introducir es " + value + " yel id es " + key);

        Log.i("xdebuge", "el size de esta lista es " + dataFieldsPreferencias.size());

        //guardamos este en preferencias....
        saveMapPreferFields(dataFieldsPreferencias, context);


    }
    public static void addDataImagesPref( ImagenReport value, Activity activity) {

        listImagesToSaVE.add(value);

        //guardamos este en preferencias....
        saveArrayList(listImagesToSaVE,activity);

    }

    public static void saveMapPreferFields(Map<String, String> inputMap, Context context) {
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            pSharedPref.edit()
                    //.remove("My_map")
                    .putString("My_map", jsonString)
                    .apply();
        }
    }

    public static void deleteMap(Context context) {
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            pSharedPref.edit()
                    .remove("My_map")
                    //.putString("My_map", jsonString)
                    .apply();
        }
    }

    public static Map<String, String> loadMap(Context context) {
        Map<String, String> outputMap = new HashMap<>();
        SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        try {
            if (pSharedPref != null) {
                String jsonString = pSharedPref.getString("My_map", (new JSONObject()).toString());
                if (jsonString != null) {
                    JSONObject jsonObject = new JSONObject(jsonString);
                    Iterator<String> keysItr = jsonObject.keys();
                    while (keysItr.hasNext()) {
                        String key = keysItr.next();
                        String value = jsonObject.getString(key);
                        outputMap.put(key, value);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return outputMap;
    }






    public static Map<String, ImagenReport> loadMapiMAGEData(Context context) { //PARACE

        SharedPreferences shared;
        SharedPreferences.Editor editor;
        Gson gson = new Gson();
        shared = context.getSharedPreferences("MIPREFERT", Context.MODE_PRIVATE);
        editor = shared.edit();
        HashMap<String, ImagenReport> listDayItems = gson.fromJson(
                shared.getString(KEY_IIMAGES_SHARE, ""),
                new TypeToken<HashMap<String, ImagenReport>>() {
                }.getType());

       return listDayItems;


    }

    public static void saveMapImagesData(Map<String, ImagenReport> inputMap, Context context) {
        SharedPreferences pSharedPref = context.getSharedPreferences("MIPREFERT",
                Context.MODE_PRIVATE);
        if (pSharedPref != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            SharedPreferences.Editor editor = pSharedPref.edit();
           // editor.remove(mapKey).apply();
            editor.putString(KEY_IIMAGES_SHARE, jsonString);
            editor.commit();
        }
    }

 public static  void addDataOfPrefrencesInView(TextInputEditText[] arraytxtImpEditext) {

        //recorremos el array de editext

        for(int indice=0; indice<arraytxtImpEditext.length; indice++)  {

            TextInputEditText currentTxImpEditext =arraytxtImpEditext[indice] ;

            String key =String.valueOf(currentTxImpEditext.getId());

            if(dataFieldsPreferencias.get(key) != null)  {

                currentTxImpEditext.setText(dataFieldsPreferencias.get(key));

            }



        }





    }

    public static void saveArrayList(ArrayList<ImagenReport> list,Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_IIMAGES_SHARE, json);
        editor.apply();

    }

    public static ArrayList<ImagenReport> getArrayList(Activity activity){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        Gson gson = new Gson();
        String json = prefs.getString(KEY_IIMAGES_SHARE , null);
        Type type = new TypeToken<ArrayList<ImagenReport>>() {}.getType();
        return gson.fromJson(json, type);
    }

/*
    public static Map<String, Object> mapToArrYlist(Context context) { //PARACE
        Map<String, Object> outputMap = new HashMap<>();

return

*/

}
