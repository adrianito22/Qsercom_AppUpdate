package com.tiburela.qsercom.SharePref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.UsuarioQsercon;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SharePref {
 //al menos 5 keys.. UNA POR CADA FORMULARIO

    public static final String KEY_EXPORTADORAS="KEY_EXPORTADORAS_ALL";


    public static final String KEY_QSERCON_USER="KEY_QSERCON_USER";


    public static final String KEY_ALL_REPORTS_OFLINE_REGISTER="KEYALL_REPORT_OFFFLINE";


    public static final String  KEY_CALIDAD_CAMIONESY_CARRETAS="KEY_CALIDAD_CAMIONESY_CARRETAS";
    public static final String KEY_CONTENEDORES="KEY_CONTENEDORES";
    public static final String KEY_PACKING_LIST="KEY_PACKING_LIST";
    public static final String KEY_CONTENEDORES_EN_ACOPIO="KEY_CONTENEDORES_EN_ACOPIO";
    public static final String KEY_MUESTRO_RECHAZDOS="KEY_MUESTRO_RECHZDOS";
    public static final String KEY_CONTROL_CALIDAD="KEY_CONTROL_CALIDAD";
    public static final String KEY_CUADRO_MUESTRA_CALIB_RECHAZDS="KEY_CUADRO_MUESTRA_CALIB_RECHAZDS";

    public static Map<String, String>mihashMapFieldsToRecycler=new HashMap<>();


    private static SharedPreferences mSharedPrefUniqueObjc;


    public static void init(Context context)
    {
        if(mSharedPrefUniqueObjc == null)
            mSharedPrefUniqueObjc = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
    }


    public static void saveMapPreferFields(Map<String, String> inputMap,String KeyTOsAVE) {
      //  SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (mSharedPrefUniqueObjc != null) {
            JSONObject jsonObject = new JSONObject(inputMap);
            String jsonString = jsonObject.toString();
            mSharedPrefUniqueObjc.edit()
                    //.remove("My_map")
                    .putString(KeyTOsAVE, jsonString) //se guarda en una solo string..
                    .apply();

            //confomre cre le basamos guardando..
        }
    }



    public static void saveQserconTipoUser(int object){
        mSharedPrefUniqueObjc.edit()
                .putInt(KEY_QSERCON_USER, object)
                .apply();

    }



    public static int getQserconTipoUser(){
        int name = mSharedPrefUniqueObjc.getInt(KEY_QSERCON_USER, 0);
        return name;

    }





    public static void deleteMap(Context context,String keyTOremove) {
       // SharedPreferences pSharedPref = context.getSharedPreferences("MyVariables", Context.MODE_PRIVATE);
        if (mSharedPrefUniqueObjc != null) {
            mSharedPrefUniqueObjc.edit()
                    .remove(keyTOremove)
                    //.putString("My_map", jsonString)
                    .apply();
        }
    }





    public static Map<String, String> loadMap(String keyMap) {
        Map<String, String> outputMap = new HashMap<>();
        try {
            if (mSharedPrefUniqueObjc != null) {
                String jsonString = mSharedPrefUniqueObjc.getString(keyMap,(new JSONObject()).toString());
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

    public static   Map<String, InformRegister> getMapAllReportsRegister(String KeyOfItem) {

        Gson gson = new Gson();
        String response=mSharedPrefUniqueObjc.getString(KeyOfItem , "");

        Type type = new TypeToken<Map<String,InformRegister>>(){}.getType();

        Map<String,InformRegister> mapPlants;
        mapPlants = gson.fromJson(response, type);


        if(response.equals("")) {
            Log.i("lashareperf","no hay data en share plant ");

            mapPlants= new HashMap<>();
            return mapPlants;

        }else{

            Log.i("lashareperf","Si hay data en share  plant y el length es "+mapPlants.size());

            return mapPlants;


        }


    }


    public static  void saveHashMapOfHashmapInformRegister(  Map<String, InformRegister> inputMap,String keySharePref) {
        if (mSharedPrefUniqueObjc != null){
            // JSONObject jsonObject = new JSONObject(inputMap);
            //   String jsonString = jsonObject.toString();
            mSharedPrefUniqueObjc.edit()
                    //  .remove("My_map")
                    .putString(keySharePref, new Gson().toJson(inputMap))


                    // .putString(keySharePref, jsonString)
                    .apply();
        }
    }



    ////sdd
    public static   Map<String, ColorCintasSemns> getMapColorCintsSemns(String KeyOfItem) {

        Gson gson = new Gson();
        String response=mSharedPrefUniqueObjc.getString(KeyOfItem , "");

        Type type = new TypeToken<Map<String,ColorCintasSemns>>(){}.getType();

        Map<String,ColorCintasSemns> mapPlants;
        mapPlants = gson.fromJson(response, type);


        if(response.equals("")) {
            Log.i("lashareperf","no hay data en share plant ");

            mapPlants= new HashMap<>();
            return mapPlants;

        }else{

            Log.i("lashareperf","Si hay data en share  plant y el length es "+mapPlants.size());

            return mapPlants;


        }


    }

    public static  void saveHashMapColorCintsSemanas(  Map<String, ColorCintasSemns> inputMap,String keySharePref) {

        Log.i("hunejo","el size de map es  "+inputMap.size());

        for(ColorCintasSemns objec: inputMap.values()){

            Log.i("hunejo","el valu item es "+objec.getSemanNum());
            Log.i("hunejo","el valu item es "+objec.getColumFieldNUm9());


        }


        if (mSharedPrefUniqueObjc != null){
            // JSONObject jsonObject = new JSONObject(inputMap);
            //   String jsonString = jsonObject.toString();
            mSharedPrefUniqueObjc.edit()
                    //  .remove("My_map")
                    .putString(keySharePref, new Gson().toJson(inputMap))
                    // .putString(keySharePref, jsonString)

                    .apply();

            Log.i("hunejo","le hemos dado en apply el key es "+keySharePref);

        }
    }


    public static   HashMap<String, String> getMapDefects(String KeyOfItem) {

        Gson gson = new Gson();
        String response=mSharedPrefUniqueObjc.getString(KeyOfItem , "");

        Type type = new TypeToken<HashMap<String,String>>(){}.getType();

        HashMap<String , String> mapPlants;
        mapPlants = gson.fromJson(response, type);


        if(response.equals("")) {
            Log.i("lashareperf","no hay data en share plant ");

            mapPlants= new HashMap<>();
            return mapPlants;

        }else{

            Log.i("lashareperf","Si hay data en share  plant y el length es "+mapPlants.size());

            return mapPlants;


        }


    }

    public static  void saveHashMapDefects(  Map<String, String> inputMap,String keySharePref) {

        Log.i("defectoss","el size de map  es  "+inputMap.size());

        for(String objec: inputMap.values()){

            Log.i("defectoss","el valu item es "+objec);


        }


        if (mSharedPrefUniqueObjc != null){
            // JSONObject jsonObject = new JSONObject(inputMap);
            //   String jsonString = jsonObject.toString();
            mSharedPrefUniqueObjc.edit()
                    //  .remove("My_map")
                    .putString(keySharePref, new Gson().toJson(inputMap))
                    // .putString(keySharePref, jsonString)

                    .apply();

            Log.i("defectoss","le hemos dado en apply el key es "+keySharePref);

        }
    }




    public static   HashMap<String, ImagenReport> getMapImagesData(String KeyOfItem) {

        Gson gson = new Gson();
        String response=mSharedPrefUniqueObjc.getString(KeyOfItem+"images" , "");

        Type type = new TypeToken<HashMap<String,ImagenReport>>(){}.getType();

        HashMap<String,ImagenReport> mapImagesReport;
        mapImagesReport = gson.fromJson(response, type);

        if(response.equals("")) {
            Log.i("lashareperf","no hay data en share plant ");

            mapImagesReport= new HashMap<>();
            return mapImagesReport;

        }else{

            if(mapImagesReport==null){
                mapImagesReport= new HashMap<>();

            }

            Log.i("lashareperf","Si hay data en share  plant y el length es "+mapImagesReport.size());

            return mapImagesReport;
        }
    }



    public static  void saveHashMapImagesData(  HashMap<String, ImagenReport> inputMap,String keySharePref) {

        if (mSharedPrefUniqueObjc != null){
            // JSONObject jsonObject = new JSONObject(inputMap);
            //   String jsonString = jsonObject.toString();
            mSharedPrefUniqueObjc.edit()
                    //  .remove("My_map")
                    .putString(keySharePref+"images", new Gson().toJson(inputMap))
                    // .putString(keySharePref, jsonString)
                    .apply();

        }


    }

    /**exportadoras data*/

    public static   HashMap<String, Exportadora> getMapExpotadoras(String KeyOfItem) {


        Gson gson = new Gson();
        String response=mSharedPrefUniqueObjc.getString(KeyOfItem , "");

        Type type = new TypeToken<HashMap<String,Exportadora>>(){}.getType();

        HashMap<String,Exportadora> mapExportadoras;
        mapExportadoras = gson.fromJson(response, type);

        if(response.equals("")) {
            Log.i("lashareperf","no hay data en share plant ");

            mapExportadoras= addExportadorasd(); //agregamos las exportadoras


            return mapExportadoras;

        }else{

            if(mapExportadoras==null){
                mapExportadoras= new HashMap<>();

            }

            Log.i("lashareperf","Si hay data en share  plant y el length es "+mapExportadoras.size());

            return mapExportadoras;
        }
    }



    public static  void saveHashMapExpotadoras(HashMap<String, Exportadora> inputMap, String keySharePref) {

        if (mSharedPrefUniqueObjc != null){
            // JSONObject jsonObject = new JSONObject(inputMap);
            //   String jsonString = jsonObject.toString();
            mSharedPrefUniqueObjc.edit()
                    //  .remove("My_map")
                    .putString(keySharePref, new Gson().toJson(inputMap))
                    // .putString(keySharePref, jsonString)
                    .apply();

        }


    }



    private  static   HashMap<String, Exportadora> addExportadorasd(){

        HashMap<String, Exportadora>map= new HashMap<>();



        Exportadora export= new Exportadora("Exprobiologico".toUpperCase());
        map.put(export.getNameExportadora(),export);

        export= new Exportadora("Traboar".toUpperCase());
        map.put(export.getNameExportadora(),export);

        export= new Exportadora("Bandecua".toUpperCase());
        map.put(export.getNameExportadora(),export);


        export= new Exportadora("Latbio".toUpperCase());
        map.put(export.getNameExportadora(),export);

        export= new Exportadora("Exporval".toUpperCase());
        map.put(export.getNameExportadora(),export);


        export= new Exportadora("Asopratverde".toUpperCase());
        map.put(export.getNameExportadora(),export);


        export= new Exportadora("Cijoscariska".toUpperCase());
        map.put(export.getNameExportadora(),export);



        export= new Exportadora("Bagatocorp".toUpperCase());
        map.put(export.getNameExportadora(),export);


        export= new Exportadora("Bananagold".toUpperCase());
        map.put(export.getNameExportadora(),export);


        Log.i("sizeexporta","el size de exportadora map es "+map.size());

        return map;

    }



}
