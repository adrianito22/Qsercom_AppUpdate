package com.tiburela.qsercom.SharePref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SharePref {
 //al menos 5 keys.. UNA POR CADA FORMULARIO

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


}
