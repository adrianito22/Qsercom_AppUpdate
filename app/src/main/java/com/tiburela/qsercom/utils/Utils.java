package com.tiburela.qsercom.utils;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tiburela.qsercom.Customviews.EditextSupreme;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.BottonSheetCallUploading;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.DefectsAndNumber;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.PromedioLibriado;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.models.UsuarioQsercon;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {

  public static  int contadorTareasCompletadas=0;

   public static boolean esNuevoReport=false;

    public static HashMap<String,String>miMapCopiar= new HashMap<>();


    public static HashMap<String, Exportadora>hasmpaExportadoras= new HashMap<>();
    public static ArrayList<String>nombresExportadoras= new ArrayList<>();


    public static final int NOPOSITION_DEFINIDA=2000;


    public static final int INSPECTOR_OFICINA=100;
    public static final int INSPECTOR_CAMPO=101;

    public static final int NO_DEFINIDO=102;


    public static boolean isOfflineReport=false;


 ///cada item usaremos esa data para crear un cuadro...
public static HashMap<String,ArrayList<PromedioLibriado>>hashMappromedioLibriado= new HashMap<>();

public static int numReportsVinculadsAll =0;

  public static   HashMap<String , ArrayList<DefectsAndNumber>> HashMapOfListWhitStatesCHeckb = new HashMap<>(); //serian unas dies listas...
  public static   HashMap<String , ArrayList<DefectsAndNumber>> HashMapOfListWhitStatesCHeckb2 = new HashMap<>(); //serian unas dies listas...






    public static boolean userDecidioNoVincularAhora =false;
public static boolean userDecidioNoVincularCuadroMuestreo  =false;

public static String textoShow="";
public static HashMap<String, Uri>mapUris= new HashMap<>();




public static ControlCalidad devulveControlCalidadByKey( ArrayList<ControlCalidad> listReprsVinculads,String keyUNIQUEID){
    for(ControlCalidad controlCalidad: listReprsVinculads){
        if(controlCalidad.getUniqueId().equals(keyUNIQUEID)){

             return  controlCalidad;
        }

    }
    return  new ControlCalidad();

}
    public static HashMap<String,String>devulveHasmapRechzadoscheckdByKey( ArrayList< HashMap <String, String>> ListWhitHashMapsRechzadosChekeed,String keyUNIQUEID){

        for(HashMap<String,String>map: ListWhitHashMapsRechzadosChekeed){

            for(String valueID: map.values()){

                if(valueID.equals(keyUNIQUEID)){
                    return  map;

                }

            }




        }
        return  new HashMap<String,String >();

    }


    public static HashMap<String,String>devulveHasmapControClidadData( ArrayList<HashMap<String, String>>ListWhitHashMapsControlCalidad,String keyUNIQUEID){

        for(HashMap<String,String>map: ListWhitHashMapsControlCalidad){

            for(String valueID: map.values()){

                if(valueID.equals(keyUNIQUEID)){
                    return  map;

                }

            }




        }
        return  new HashMap<String,String >();

    }


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




    public static Map<String, ImagenReport> loadMapiMAGEData(Context context,String keySharePref) { //PARACE

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


            if(currentTxImpEditext!=null){
                String key =String.valueOf(currentTxImpEditext.getId());

                if(hashMaPDePrefer.get(key) != null)  {

                    currentTxImpEditext.setText(hashMaPDePrefer.get(key));

                }
            }





        }





    }


    public static  void addDataOfPrefrencesInView(View[] arrayAllViews, @NonNull HashMap<String, String> hashMaPDePrefer) {

        /***nos quedamos aqui ,mirar activity previews como set data*/


        for (Map.Entry<String, String> entry : hashMaPDePrefer.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            Log.i("misdader","se ejextuo esto");

          View vistax=  getViewById(key,arrayAllViews);


          if(vistax==null){

              Log.i("misdader","es nulo por aqui vergax");

          }




          if(vistax!=null){

                 if(vistax instanceof EditextSupreme){
                  EditextSupreme spn=(EditextSupreme)vistax ;

                  spn.setText(value);

                  //aqui colamos el texto como en preview
              }

              else if(vistax instanceof TextInputEditText){
                  TextInputEditText spn=(TextInputEditText)vistax ;

                  spn.setText(value);

                  //aqui colamos el texto como en preview

              }

              if(vistax instanceof EditText ){ //si es un editext
                  EditText editText = (EditText) vistax; //asi lo convertimos
                  editText.setText(value);

              }



              else if(vistax instanceof Spinner){
                  Spinner spn=(Spinner)vistax ;

                  selectValue(spn,value);


              }


              else if(vistax instanceof Switch){

                  Switch switchz=(Switch)vistax ;

                   if(value.equalsIgnoreCase("true")){
                       switchz.setChecked(true);
                   }


                  //aqui colamos el texto como en preview

              }


          }

        }



    }


    public static  void addDataOfPrefrencesInEditText(EditText[] arrayEditText, @NonNull Map<String, String> hashMaPDePrefer) {

        /***nos quedamos aqui ,mirar activity previews como set data*/


        Log.i("sabeirr","se ejextuo esto "+hashMaPDePrefer.size());

        for (Map.Entry<String, String> entry : hashMaPDePrefer.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();


            EditText vistax=  getEditextById(key,arrayEditText);


            if(vistax!=null){
                vistax.setText(value);

                Log.i("misdader","es nulo por aqui vergax");

            }


        }


    }



    private static void selectValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                Log.i("mizona", "existe hurra" + value);
                break;

            } else {

                Log.i("mizona", "no exiwste " + value);

            }
        }

    }


    private  static View getViewById(String idSearch,View[] arrayAllViews){
        View vista=null;
        for(View vistaCurrent: arrayAllViews){

            if(vistaCurrent!=null){
                if(String.valueOf(vistaCurrent.getId()).equals(idSearch)){
                    vista=vistaCurrent;
                    break;
                }

            }



        }

       return vista;
    }


    private  static EditText getEditextById(String idSearch,EditText[] arrayAllViews){
        EditText vista=null;
        for(EditText vistaCurrent: arrayAllViews){

            if(String.valueOf(vistaCurrent.getId()).equals(idSearch)){
                vista=vistaCurrent;
                break;
            }

        }

        return vista;
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


            if(allArrayViewsTextIMPUTe[indice]==null){
                Log.i("comenzamos","es nulo la posicion : "+indice);
             break;
            }


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




    public static void initializeAndGETnuMSvinuclads(String controlCalidad, String cuadroMuestro) {

        Utils.numReportsVinculadsAll=0;

        String[] arrayRepportsCONTROLcALIDAD =controlCalidad.split(",") ;
        String[] arryCuadroMuestro =cuadroMuestro.split(",") ;



        RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds = new HashMap<>();
        RecyclerViewAdapLinkage.mapWhitIdsCuadroMuestreo = new HashMap<>();

        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString=controlCalidad;
        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado=cuadroMuestro;




            for(String value : arrayRepportsCONTROLcALIDAD){
                Log.i("picacins","el key sera "+value);
                if(! value.trim().isEmpty()){
                    RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds.put(value,value);

                    Utils.numReportsVinculadsAll++;
                }
            }


            for(String value : arryCuadroMuestro){

                if(!value.trim().isEmpty()){

                    RecyclerViewAdapLinkage.mapWhitIdsCuadroMuestreo.put(value,value);
                    Utils.numReportsVinculadsAll++;


                }
                Log.i("picacins","el key sera "+value);
            }





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






    public static  AtomicInteger   checkIFuserIsActivate(String mailGoogleUser){
        final AtomicBoolean done = new AtomicBoolean(false);
        final AtomicInteger message1 = new AtomicInteger(0);

       // Log.i("hahsger","vamos a metodo check ");

      //  final TaskCompletionSource<UsuarioQsercon> tcs = new TaskCompletionSource<>();


      //  CountDownLatch done = new CountDownLatch(1);
       // final boolean isUserAptobadoAccount[] = {false};

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Usuarios").child("ColaboradoresQsercom");

        Query query = usersdRef.orderByChild("mailGooglaUser").equalTo(mailGoogleUser);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                  Log.i("hahsger","el value de snpatshot es "+snapshot.getValue());
                UsuarioQsercon currentObect = null;
                  if(snapshot.exists()){

                      for (DataSnapshot ds : snapshot.getChildren()) {
                          currentObect=ds.getValue(UsuarioQsercon.class);
                      }



                   //   isUserAptobadoAccount[0] = currentObect.isUserISaprobadp() ;
                      //  System.out.println("worked");

                      Log.i("hahsger","el object es  "+ currentObect.isUserISaprobadp());

                      Log.i("hahsger","se a llmado ondata change ");

                    //

                      if(currentObect.isUserISaprobadp()){

                          message1.set(0);

                      }else{
                          message1.set(1);



                      }

                      done.set(true);

                    //  done.countDown();

                  }else{


                      Log.i("hahsger","no existe ");

                  }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("isclkiel","el error es  "+ error.getMessage());


            }
        } );









/*

        try {
          //  done.await(); //it will wait till the response is received from firebase.
        } catch(InterruptedException e) {
            Log.i("hahsger","la excepcion es  "+e.getMessage());

            e.printStackTrace();
        }
*/

      //  return isUserAptobadoAccount[0];


        while (!done.get());



        return message1;

    }

    public static ArrayList<String> generateLISTofIdControlCALIDAD(String controlCalidStrin ){

        ArrayList<String>listIdSvINCULADOS= new ArrayList<>();


        if(controlCalidStrin!=null){
            String [] miarrayiNFORMESvinc = controlCalidStrin.split(",");
            Log.i("datamapitkka","el size de aara es "+miarrayiNFORMESvinc.length);

            if(miarrayiNFORMESvinc.length >0) {

                for(String value : miarrayiNFORMESvinc){

                    if(!value.trim().isEmpty()){

                        listIdSvINCULADOS.add(value);

                    }
                }

                Log.i("datamapitkka","es mayor a 1"+listIdSvINCULADOS.size());

            }


        }


        return listIdSvINCULADOS;
    }




    public static  boolean checkifAtach(){ //chekeamos ambos haber si estan en atch


        if(Utils.userDecidioNoVincularAhora){
           return true;
        } ///si tenemos data en mabos tambien retorna true


          else if(RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds.size()==0 &&
                RecyclerViewAdapLinkage.mapWhitIdsCuadroMuestreo.size()==0){

            textoShow= "No tienes vinculado ningun reporte Control calidad y Cuadro Muestreo";

            return false;

        }


        else if(RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds.size()==0){
            textoShow=        "No tienes vinculado ningun reporte control Calidad";

            return false;
        }

        else if(RecyclerViewAdapLinkage.mapWhitIdsCuadroMuestreo.size()==0){
            textoShow=        "No tienes vinculado ningun reporte cuadro Muestreo";

            return false;

        }







return true;

    }


    public static ArrayList<ImagenReport>mapToArrayList(HashMap<String,ImagenReport> map){
        ArrayList<ImagenReport>list= new ArrayList<>();

        for(ImagenReport currnet: map.values()){

            list.add(currnet);

        }


        return  list;

    }



    public static String getRealPathFromURI (Uri contentUri,Context context) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        Log.i("mipathe","el path aqui es "+path);

        return path;
    }


    public static  String getRealPathFromURIx(Uri uri,Context context) {
       // Uri uri = contentURI.getData();
        String yourRealPath="";
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        if(cursor.moveToFirst()){
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
             yourRealPath = cursor.getString(columnIndex);
        }

        else {


            ///boooo, cursor doesn't have rows ...


        }
        cursor.close();
        Log.i("mipathe","el path aqui 2 es "+yourRealPath);

        return yourRealPath;


    }



    public static String getPathFromUri(final Context context, final Uri uri) {

       final boolean isKitKat = true;

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {

            Log.i("mipathe","es el if ");

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }





    public static void showSheetBtFaltaImagen(int tipoImageFalta){

        String textoFalta="";

        switch(tipoImageFalta){

            case Variables.FOTO_PROCESO_FRUTA_FINCA:
                textoFalta="proceso de fruta en finca";
                break;


            case Variables.FOTO_LLEGADA_CONTENEDOR:
                textoFalta="llegada contenedor";

                break;


            case Variables.FOTO_SELLO_LLEGADA:
                textoFalta="sello llegada";

                break;


            case Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR:
                textoFalta="puerta abierta del contenedor";

                break;



            case Variables.FOTO_PALLETS:
                textoFalta="puerta abierta del contenedor";

                break;




            case Variables.FOTO_CIERRE_CONTENEDOR:
                textoFalta="puerta abierta del contenedor";

                break;



            case Variables.FOTO_DOCUMENTACION:
                textoFalta="documentacion";

                break;

        }

         //aqui mostramos un sheet...

    }


    public static  boolean checkPermission(Context contexto) {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(contexto, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(contexto, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }


    public static void drawImages(   RecyclerViewAdapter adapter,RecyclerView rec,ArrayList <ImagenReport>list,Context contexto){
// Create an `ItemTouchHelper` and attach it to the `RecyclerView`

        Log.i("imagesaddd","en drW IMAGES METHOS EL SIZE ES "+list.size());
// Extend the Callback class

        ItemTouchHelper.Callback _ithCallback=null;

         _ithCallback = new ItemTouchHelper.Callback() {
            //and in your imlpementaion of
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Log.i("imagesaddd","aqui se llamo onMove y size lista es "+list.size());

             //   Log.i("imagesaddd","aqui se llamo onMove");


                // get the viewHolder's and target's positions in your adapter data, swap them
                Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());

               // RecyclerViewAdapter adapter=new RecyclerViewAdapter(list,contexto);
               // rec.setAdapter(adapter);


                // and notify the adapter that its dataset has changed
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.i("imagesaddd","aqui se llamo onSwiped");

                //TODO
            }

            //defines the enabled move directions in each state (idle, swiping, dragging).
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                Log.i("imagesaddd654","aqui se llamo este");

                return makeFlag(ItemTouchHelper.ACTION_STATE_DRAG,
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.START | ItemTouchHelper.END);


            }
        };


        ItemTouchHelper ith = new ItemTouchHelper(_ithCallback);
        ith.attachToRecyclerView(rec);

    }



    public static void updatePositionObjectImagenReport(RecyclerView reciclerView){

        for (int i = 0; i < reciclerView.getChildCount(); ++i) {

            RecyclerView.ViewHolder holder = reciclerView.getChildViewHolder(reciclerView.getChildAt(i));
             ImageView img = holder.itemView.findViewById(R.id.imvClose);

            // Log.i("superemasisa","ell idtag es "+img.getTag());

             if(ImagenReport.hashMapImagesData.containsKey(img.getTag())){
                 ImagenReport objec= ImagenReport.hashMapImagesData.get(img.getTag());
                 assert objec != null;
                 objec.setSortPositionImage(i);
                  Log.i("superemasisass","esta imagen "+objec.getUrlStoragePic()+" esta en posicion"+i);


                 ImagenReport.hashMapImagesData.put(img.getTag().toString(),objec);


             }




        }

    }


    public static void  updateImageReportObjec(){

        Log.i("","");

        for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) { //creamos otra lista
            //  String key = entry.getKey();
            ImagenReport imageRoBject = entry.getValue();
            RealtimeDB.getkeyActualizaSortNum(imageRoBject.getUniqueIdNamePic(),imageRoBject.getSortPositionImage());


        }


    }

    public static boolean containsName(final List<ImagenReport> list, final String name){
        return list.stream().map(ImagenReport::getUniqueIdNamePic).anyMatch(name::equals);
    }


    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }


    public static void show_AND_UPLOADContenedores(Activity activity, Context contexta, SetInformEmbarque1 informEmbq1, SetInformEmbarque2 informEmbq2,
                                                   SetInformDatsHacienda datoshda, InformRegister informRegisterx,
                                                   ProductPostCosecha productos, ArrayList<ImagenReport>listImages,
                                                   HashMap<String, Float> miMapLbriadox, int ActivityId,String keyPrefrencesx)
    {
        FragmentManager fm =((FragmentActivity)activity). getSupportFragmentManager();
        BottonSheetCallUploading alertDialog =  BottonSheetCallUploading.newInstance(contexta,informEmbq1,informEmbq2,datoshda,informRegisterx,productos,listImages,miMapLbriadox,ActivityId);

        Bundle bundle = new Bundle();
        bundle.putString("keyPrefrencesReportCurrent", keyPrefrencesx);
        alertDialog.setArguments(bundle);


        alertDialog.setCancelable(false);
       // alertDialog.setc(false);
        alertDialog.show(fm, "duialoffragment_alert");



    }

    public static void show_AND_UPLOAD_CamionesyCarretas(Activity activity, Context contexta, ReportCamionesyCarretas camionesyCarretas,
                                                   CalibrFrutCalEnf calibracEnfunde, InformRegister informRegisterx, ProductPostCosecha productos, ArrayList<ImagenReport>listImages,
                                                         int ActivityId,String keyPrefrencesx)
    {
        FragmentManager fm =((FragmentActivity)activity). getSupportFragmentManager();



        BottonSheetCallUploading alertDialog =  BottonSheetCallUploading.newInstance(contexta,camionesyCarretas,calibracEnfunde  ,informRegisterx,productos,listImages,ActivityId);

        Bundle bundle = new Bundle();
        bundle.putString("keyPrefrencesReportCurrent", keyPrefrencesx);
        alertDialog.setArguments(bundle);


        alertDialog.setCancelable(false);
        // alertDialog.setc(false);
        alertDialog.show(fm, "duialoffragment_alert");



    }





    public static void update_CamionesyCarretas(Activity activity, Context contexta, ReportCamionesyCarretas camionesyCarretas,
                                                         CalibrFrutCalEnf calibracEnfunde, ProductPostCosecha productos, ArrayList<ImagenReport>listImages,
                                                         int ActivityId)
    {
        FragmentManager fm =((FragmentActivity)activity). getSupportFragmentManager();



        BottonSheetCallUploading alertDialog =  BottonSheetCallUploading.newInstance(contexta,camionesyCarretas,calibracEnfunde  ,productos,listImages,ActivityId);

        Bundle bundle = new Bundle();
       // bundle.putString("keyPrefrencesReportCurrent", keyPrefrencesx);
        alertDialog.setArguments(bundle);


        alertDialog.setCancelable(false);
        // alertDialog.setc(false);
        alertDialog.show(fm, "duialoffragment_alert");



    }

    public static void show_AND_UPLOAD_ConetendoresAcopio(Activity activity, Context contexta, ContenedoresEnAcopio contenedoresEnAcopio,
                                                          InformRegister informRegisterx,
                                                          ArrayList<ImagenReport>listImages,HashMap<String, DatosDeProceso>miMap,
                                                         int ActivityId,String keyPrefrencesx)
    {
        FragmentManager fm =((FragmentActivity)activity). getSupportFragmentManager();



        BottonSheetCallUploading alertDialog =  BottonSheetCallUploading.newInstance(contexta,contenedoresEnAcopio  ,informRegisterx,
                miMap,listImages,ActivityId);

        Bundle bundle = new Bundle();
        bundle.putString("keyPrefrencesReportCurrent", keyPrefrencesx);
        alertDialog.setArguments(bundle);


        alertDialog.setCancelable(false);
        // alertDialog.setc(false);
        alertDialog.show(fm, "duialoffragment_alert");



    }


    public static void show_AND_UPLOADCOntrolcalidad(Activity activity, Context contexta, ControlCalidad controlCalidad,
                                                     InformRegister informRegisterx,
                                                     HashMap<String, String> hasHmapOtherFieldsEditxs
                                                     ,HashMap<String,String>hasMapitemsSelecPosicRechazToUpload
                                                     ,int ActivityIdx,String keyPrefrencesx) {

        FragmentManager fm =((FragmentActivity)activity). getSupportFragmentManager();

        BottonSheetCallUploading alertDialog =  BottonSheetCallUploading.newInstance(contexta,controlCalidad,
                hasHmapOtherFieldsEditxs,hasMapitemsSelecPosicRechazToUpload,informRegisterx,ActivityIdx);

        Bundle bundle = new Bundle();
        bundle.putString("keyPrefrencesReportCurrent", keyPrefrencesx);
        alertDialog.setArguments(bundle);

        alertDialog.setCancelable(false);
        alertDialog.show(fm, "duialoffragment_alert");

    }


    public static  TaskCompletionSource<String> sourceTareas = new TaskCompletionSource<>();

    public static  TaskCompletionSource<String> sourceTareaSubirIMAGENES = new TaskCompletionSource<>();



    public static final String TAREACOMPETADA="TAREACOMPETADA";
    public static final String TAREACOMPETADA_IMAGENS="TAREACOMPETADA_IMAGENES_SUBIDAS";
    public static int  PorcientoTarea=0;

    public static int indiceControlCalidad=0;


    public static boolean checkIfReportSeSubio(String currentKeyAndSharePrefrences ){

        Map<String,  InformRegister> mapAllReportsRegister = SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);
        InformRegister objec= mapAllReportsRegister.get(currentKeyAndSharePrefrences);
            if (objec!=null && objec.isSeSubioFormAlinea()){
                return true;
            }
            return false;


    }


}

