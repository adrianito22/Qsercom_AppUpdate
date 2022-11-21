package com.tiburela.qsercom.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiburela.qsercom.EditextSupreme;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Utils {
public static int numReportsVinculads=0;

    //aqui agudaremos el alto con el que tendremos que dibujar cada fila..







public static float generaAlturaDeTabla(ArrayList<Float>altoQueContendraCadaFila){

        float altoTabl=0;

    Log.i("slamanraxxx", "el size de list here es " + altoQueContendraCadaFila.size());


    for(int indice=0; indice<altoQueContendraCadaFila.size(); indice++ ){

        altoTabl=altoTabl+altoQueContendraCadaFila.get(indice);

        Log.i("slamanraxxx", "la altura de esta fila es  " + altoQueContendraCadaFila.get(indice));


    }

      return altoTabl;
}










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

    public static void saveMapImagesDataPreferences(Map<String, ImagenReport> inputMap, Context context) {
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

 public static  void addDataOfPrefrencesInView(TextInputEditText[] arraytxtImpEditext, HashMap<String, String> hashMaPDePrefer) {

        //recorremos el array de editext

        for(int indice=0; indice<arraytxtImpEditext.length; indice++)  {

            TextInputEditText currentTxImpEditext =arraytxtImpEditext[indice] ;

            String key =String.valueOf(currentTxImpEditext.getId());

            if(hashMaPDePrefer.get(key) != null)  {

                currentTxImpEditext.setText(hashMaPDePrefer.get(key));

            }



        }





    }


    public static  void addDataOfPrefrencesInViewX(EditextSupreme[] arraytxtImpEditext, HashMap<String, String> hashMaPDePrefer) {

        //recorremos el array de editext

        for(int indice=0; indice<arraytxtImpEditext.length; indice++)  {

            EditextSupreme currentTxImpEditext =arraytxtImpEditext[indice] ;

            String key =String.valueOf(currentTxImpEditext.getId());

            if(hashMaPDePrefer.get(key) != null)  {

                currentTxImpEditext.setText(hashMaPDePrefer.get(key));

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


    public static  ArrayList<String> objsIdsDecripcionImgsMOreDescripc =new ArrayList<>();


    public static  boolean checkIFaltaunDatoLlenoAndFocus(TextInputEditText [] arrayNmbresProd, TextInputEditText [] arrayTiposEmpaque,
                                                       TextInputEditText [] arrayCodigos, TextInputEditText [] arraynCajas){

        //le decimos que falta data
        boolean isReady=true;

          int numero_fieldVacios =0;

          ArrayList<TextInputEditText>ediTEXTVacios;


        for(int indice=0; indice<arrayNmbresProd.length; indice++){
            ediTEXTVacios=new ArrayList<>();

            if(arrayNmbresProd[indice].getText().toString().isEmpty()){ //si esta vacio le restamos uno
                numero_fieldVacios=numero_fieldVacios+1;

                ediTEXTVacios.add(arrayNmbresProd[indice]);

            }



            if(arrayCodigos[indice].getText().toString().isEmpty()){
                numero_fieldVacios=numero_fieldVacios+1;
                ediTEXTVacios.add(arrayCodigos[indice]);

            }



           if(arrayTiposEmpaque[indice].getText().toString().isEmpty()){
                numero_fieldVacios=numero_fieldVacios+1;
               ediTEXTVacios.add(arrayTiposEmpaque[indice]);


           }



           if(arraynCajas[indice].getText().toString().isEmpty()){
                numero_fieldVacios=numero_fieldVacios+1;

               ediTEXTVacios.add(arraynCajas[indice]);

           }




                //numero_fieldVacios

            if (numero_fieldVacios > 0 && numero_fieldVacios < 4) {

               Log.i("caramba","el id  error focus es "+ediTEXTVacios.get(0).getId());
               ediTEXTVacios.get(0).requestFocus();
               ediTEXTVacios.get(0).setError("Este dato es requerido");
                isReady=false;
                break;

                ///sdile que falta data...y focush primer editext...solo guardamos uno....

           }

        }

        Log.i("caramba","retornamos: "+isReady);


        return isReady;

    }




    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }




    public static EditextSupreme getEditextSupreme(EditextSupreme[] allArrayViewsTextIMPUTe, int idViewSearch ){

        EditextSupreme textInputEditText=null;
        Log.i("midata","hay en totak "+allArrayViewsTextIMPUTe.length + "textimput editext");



        for(int indice=0; indice<allArrayViewsTextIMPUTe.length ; indice++){  //iteramos el mapa

            Log.i("midata","el id de este view es es "+allArrayViewsTextIMPUTe[indice].getId());

            if(allArrayViewsTextIMPUTe[indice].getId()==idViewSearch){

                Log.i("midata","se cumplio ss");


                textInputEditText= allArrayViewsTextIMPUTe[indice];
                break;
            }

        }

        return textInputEditText;
    }



    public static TextInputEditText getTextImpuEditex(TextInputEditText[] allArrayViewsTextIMPUTe, int idViewSearch ){

        TextInputEditText textInputEditText=null;
        Log.i("midata","hay en totak "+allArrayViewsTextIMPUTe.length + "textimput editext");


        for(int indice=0; indice<allArrayViewsTextIMPUTe.length ; indice++){  //iteramos el mapa

            Log.i("midata","el id de este view es es "+allArrayViewsTextIMPUTe[indice].getId());

            if(allArrayViewsTextIMPUTe[indice].getId()==idViewSearch){

                Log.i("midata","se cumplio ss");


                textInputEditText= allArrayViewsTextIMPUTe[indice];
                break;
            }

        }

        return textInputEditText;
    }




    public static int generaNumsInformsAtach(String data) {

        String arrayRepports [] =data.split(",") ;

        int numsReports =0;

        if(arrayRepports  ==null){
            numsReports=0;
        }


        else if(arrayRepports.length ==0 ){
            numsReports=0;
        }


        else{
            numsReports=arrayRepports.length;
        }


        return numsReports;

    }



    public static  int generateNumRadom6Digits() {

            Random r = new Random( System.currentTimeMillis() );
        Log.i("elnumber","el numero generado es "+((1 + r.nextInt(2)) * 10000 + r.nextInt(10000)));

        return ((1 + r.nextInt(2)) * 100000 + r.nextInt(100000)); //estab en 5 ceros

    }



    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager.getActiveNetworkInfo() : null;
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



}
