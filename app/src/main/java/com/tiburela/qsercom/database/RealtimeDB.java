package com.tiburela.qsercom.database;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.activities.formularios.ActivityCamionesyCarretas;
import com.tiburela.qsercom.activities.formularios.ActivityContersEnAcopio;
import com.tiburela.qsercom.activities.formularios.ActivityControlCalidad;
import com.tiburela.qsercom.activities.formularios.ActivityCuadMuestCalibAndRechaz;
import com.tiburela.qsercom.activities.formularios.ActivityPackingList;
import com.tiburela.qsercom.dialog_fragment.BottonSheetCallUploading;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.RegisterTest;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.models.UsuarioQsercon;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RealtimeDB {

    public static String keyFirebaselOACTIONCuadroMuestreo="";

    static  public DatabaseReference rootDatabaseReference ;

    static  public  DatabaseReference mibasedataPathImages;

    public static  Context myContext;


    //ESTA VCLASED VA A ENCRAGARSE DE CREAR MODIFICAR ,BORRAR DATOS DE LA BASE DE DATOS REALTIME


    public static void  initContext(Context context){
        myContext=context;

    }

    public static  void initDatabasesReferenceImagesData(){

        mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImagesData");


    }


    public static  void initDatabasesRootOnly(){

        if(rootDatabaseReference==null){
            rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        }


        // mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImagesData");


    }

    private void editInform(){


    }

    public static void addNewInformContenresAcopio( ContenedoresEnAcopio informeObjct) {


        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("contenedoresAcopio");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);
        // Map<String, Object> mapValues = informeObjct.toMap();


        mibasedata.child(PuskEY).setValue(informeObjct).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    BottonSheetCallUploading.uploadConteendoresEnAcopio(Variables.DATOS_PROCESOXX);

                    Log.i("COMENMZAR","es succes");

                    //  ((Activity)myContext).finish();


                }else  {

                    Log.i("COMENMZAR","es else");


                    //   Toast.makeText(myContext, "Se produjo un erro   r", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }



    public void  closeActivity(){

        ((Activity)myContext).finish();


    }


    public RealtimeDB (Context context){

        this.myContext = context;

    }

    public static void updateInformContenresAcopio( ContenedoresEnAcopio informeObjct) {

        Log.i("elkeyfirebase ","el keyfirebase es "+informeObjct.getKeyFirebase());

        if(informeObjct.getKeyFirebase()!=null && informeObjct.getKeyFirebase().length()>0){

            DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("contenedoresAcopio").child(informeObjct.getKeyFirebase());

            mibasedata.setValue(informeObjct).addOnCompleteListener(new OnCompleteListener<Void>() {


                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                       BottonSheetCallUploading.updateContenedresEnAcopio(Variables.DATOS_PROCESOXX);

                        // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                    }else  {


                    }
                }
            });

        }



    }


    public static void addNewDSetinformEmarque1(SetInformEmbarque1 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);

        Map<String, Object> mapValues = informeObjct.toMap();
        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    BottonSheetCallUploading.uploadConteendoresForm( Variables.OBJECT_SetInformEmbarque2);


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {



                }
            }});


    }



    public static void addNewDSetinformEmarque1(SetInformDatsHacienda informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);
        Map<String, Object> mapValues = informeObjct.toMap();

        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    BottonSheetCallUploading.uploadConteendoresForm( Variables.LIBRIADO_IF_EXIST);



                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }




    public static void addNewReportCalidaCamionCarrretas( ReportCamionesyCarretas informeObjct) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("informeCamionesYcarretas");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();
        informeObjct.setKeyFirebase(PuskEY);
        //   Map<String, Object> mapValues = informeObjct.toMap();

        mibasedata.child(PuskEY).setValue(informeObjct).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                     BottonSheetCallUploading.uploadCamionesYcarretas(Variables.PRODUCTS_POST_COSECHA);


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void updateCalidaCamionCarrretas( ReportCamionesyCarretas informeObjct) {
          RealtimeDB.initDatabasesRootOnly();

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("informeCamionesYcarretas");

        Log.i("samisas","update el get key firebase es "+informeObjct.getKeyFirebase());

        if(informeObjct.getKeyFirebase().length()>0){

            Log.i("samisas","se ejecuto el if aqui");

            mibasedata.child(informeObjct.getKeyFirebase()).setValue(informeObjct).addOnCompleteListener(new OnCompleteListener<Void>() {


                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.i("samisas","es succces hurra");
                        BottonSheetCallUploading.updateCamionesYcarretas(Variables.PRODUCTS_POST_COSECHA);


                    }
                    else  {

                        Log.i("samisas","se produjo un aexepcion y es "+task.getException());
                     //   Toast.makeText(myContext, "Ocurrio un error :(", Toast.LENGTH_SHORT).show();

                    }

                    if (task.isCanceled()){

                        Log.i("samisas","task es caneleed");

                    }

                }
            });

        }



    }


    public static void addNewhasmapPesoBrutoClosters2y3L( HashMap<String, Float> miMapa,String keyOrNodeToUpload) {


       /*
        if(miMapa.size()==0){
            BottonSheetCallUploading.uploadConteendoresForm(Variables.PRODUCTS_POST_COSECHA);

            return;
        }
*/

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("MapsPesoBrutoCloster2y3l");

        mibasedata.child(keyOrNodeToUpload).setValue(miMapa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    if(Variables.activityCurrent==Variables.FormContenedores){
                        BottonSheetCallUploading.uploadConteendoresForm(Variables.PRODUCTS_POST_COSECHA);

                    }else if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivity){

                    //    BottonSheetCallUploading.uploadCamionesYcarretas(Variables.PRODUCTS_POST_COSECHA);

                    }



                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();
                }else  {


                }
            }
        });


    }


    public static void UpdateHasmapPesoBrutoClosters2y3L( HashMap<String, Float> miMapa,String keyOrNodeToUpdate) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("MapsPesoBrutoCloster2y3l");

        mibasedata.child(keyOrNodeToUpdate).setValue(miMapa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("updatexxxx","es succes UpdateHasmapPesoBrutoClosters2y3L");
                    BottonSheetCallUploading.UpdateConteendores(Variables.PRODUCTS_POST_COSECHA);


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();
                }else  {


                }
            }
        });


    }



    public static void updateSetinformEmbarq1(SetInformEmbarque1 informeObjct) {

        Log.i("somerliker", "llamamos actualiza informe parte 1 bb ");


        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child(informeObjct.getKeyFirebase());
        Map<String, Object> mapValues = informeObjct.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("updatexxxx","es succes updateSetinformEmbarq1");


                        BottonSheetCallUploading.UpdateConteendores(Variables.UPDATEINform_2);

                        // Utils.sourceTareas.setResult(Utils.TAREACOMPETADA);




                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void actualizaInformePart2( SetInformEmbarque2 informeObjc2) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child(informeObjc2.getKeyFirebase());

        Map<String, Object> mapValues = informeObjc2.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("updatexxxx","es  actualizaInformePart2");
                    BottonSheetCallUploading.UpdateConteendores(Variables.UPDATEINform_3);

                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void actualizaInformePart3( SetInformDatsHacienda informeObjc3) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes").child(informeObjc3.getKeyFirebase());

        Map<String, Object> mapValues = informeObjc3.toMap(); //lo convertimos en maP

        mibasedata.updateChildren(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("updatexxxx","es  actualizaInformePart3");
                  //  Utils.contadorTareasCompletadas++;
                    BottonSheetCallUploading.UpdateConteendores(Variables.LIBRIADO_IF_EXIST);

                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void addNewCuadroMuestreoObject( CuadroMuestreo informeObjct) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("CuadrosMuestreo");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();
        informeObjct.setKeyFirebaseLocation(PuskEY);

        keyFirebaselOACTIONCuadroMuestreo=informeObjct.getKeyFirebaseLocation();

        // informeObjct.setKeyFirebase(PuskEY);
        //   Map<String, Object> mapValues = informeObjct.toMap();

        mibasedata.child(PuskEY).setValue(informeObjct).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    ///decide


                    Log.i("saber"," se subio la data ocmplete  ");

                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {

                    Log.i("saber"," no es succes"+task.getException().getMessage());

                }
            }
        });


    }



    public static void updateCuadroMuestreoObject( CuadroMuestreo newinformeObjct, CuadroMuestreo objecAntiguo) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("CuadrosMuestreo");

        newinformeObjct.setDateInMillisecond(objecAntiguo.getDateInMillisecond());
        newinformeObjct.setNodoKyDondeEstaHasmap(objecAntiguo.getNodoKyDondeEstaHasmap());
        newinformeObjct.setUniqueIdObject(objecAntiguo.getUniqueIdObject());
        newinformeObjct.setSimpleDateFormat(objecAntiguo.getSimpleDateFormat());
        newinformeObjct.setKeyFirebaseLocation(objecAntiguo.getKeyFirebaseLocation());


        mibasedata.child(objecAntiguo.getNodoKyDondeEstaHasmap()).setValue(newinformeObjct).addOnCompleteListener(new OnCompleteListener<Void>() {



            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }

    public static void addNewCuadroMuestreoHasMap(HashMap <String , ColorCintasSemns> mapCuadroMuestreo, String nododDondeEstaraEsteHasmap) {
        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("CuadroMuestreoMaps");
        mibasedata2.child(nododDondeEstaraEsteHasmap).setValue(mapCuadroMuestreo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Log.i("saber"," se subio mapa ");

                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {

                    Log.i("saber"," no es succes mapa"+task.getException().getMessage());

                }


            }
        });  //subimos el packing list mapa
    }




    public static void updateCuadroMuestreoHasMap(HashMap <String , ColorCintasSemns> mapCuadroMuestreo, String nododDondeEstaraEsteHasmap) {
        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("CuadroMuestreoMaps");
        mibasedata2.child(nododDondeEstaraEsteHasmap).setValue(mapCuadroMuestreo);  //subimos el packing list mapa



    }




    public static void addNewInformeEmbarque2(  Context context,SetInformEmbarque2 informeObjct) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listInformes");

        //agregamos la propiedad keyFirebase a al objeto
        String PuskEY = mibasedata.push().getKey();

        informeObjct.setKeyFirebase(PuskEY);

        Map<String, Object> mapValues = informeObjct.toMap();

        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    //lo borramos...

                    BottonSheetCallUploading.uploadConteendoresForm( Variables.OBJECT_SetInformDatsHacienda);


                    try {
                        Utils.deleteMap(context);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void addDatosProceso( HashMap <String ,DatosDeProceso > datosProcesoMap,String Pushkey) {

        DatabaseReference mibasedata = RealtimeDB.rootDatabaseReference.child("Informes").child("datosProcesoContenAcopio");

        mibasedata.child(Pushkey).setValue(datosProcesoMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                   BottonSheetCallUploading. uploadConteendoresEnAcopio( Variables.INFORM_REGISTER);


                }else  {
                    Log.i("samamf","task is else no succes ");

                    Toast.makeText( myContext, "Se produjo un error", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    public static void addNewPackingListHasMap(HashMap <String ,String > packinListMap) {

        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("PackingListMaps");
        String nododDondeEstaraEsteHasmap = mibasedata2.push().getKey();
        // objPacking.setKeyOrNodeContaineHashMap(nododDondeEstaraEsteHasmap);//editamos el valor del nodo donde estara el hasmap

        mibasedata2.child(nododDondeEstaraEsteHasmap).setValue(packinListMap);  //subimos el packing list mapa


        Variables.nodoDondeEstaHashMapQueReciensubimos=nododDondeEstaraEsteHasmap;

        //   mibasedata2.child(nododDondeEstaraEsteHasmap).setValue(packinListMap);  //subimos el packing list


    }


    public static void AddNewPackingListObject( PackingListMod objPacking ) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("PackingListDescripcion"); //nodo para packing list object
        objPacking.setUniqueIDinforme(UUID.randomUUID().toString()); //le damos un id unico
        String keyNodoDondeEstaObjectPacking = mibasedata.push().getKey(); //creamos un nodo o rtua o key
        objPacking.setNodoPadreThisObect(keyNodoDondeEstaObjectPacking);

        objPacking.setKeyOrNodeContaineHashMap( Variables.nodoDondeEstaHashMapQueReciensubimos);//editamos el valor del nodo donde estara el hasmap
        mibasedata.child(keyNodoDondeEstaObjectPacking).setValue(objPacking);  //subimos el packing list mapa



    }




    public static void  updateNewPackingListHasMap(HashMap <String ,String > packinListMap,PackingListMod antiguoPackinListOb) {

        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("PackingListMaps");
        String nododDondeEstaraEsteHasmap =antiguoPackinListOb.getKeyOrNodeContainsMapPli();
        // objPacking.setKeyOrNodeContaineHashMap(nododDondeEstaraEsteHasmap);//editamos el valor del nodo donde estara el hasmap

        mibasedata2.child(nododDondeEstaraEsteHasmap).setValue(packinListMap);  //subimos el packing list mapa

        //Variables.nodoDondeEstaHashMapQueReciensubimos=nododDondeEstaraEsteHasmap;

        //   mibasedata2.child(nododDondeEstaraEsteHasmap).setValue(packinListMap);  //subimos el packing list

    }


    public static void updatePackingListObject( PackingListMod newOBjecPacking, PackingListMod entiguoObjectPacking ) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("PackingListDescripcion"); //nodo para packing list object

        newOBjecPacking.setUniqueIDinforme(entiguoObjectPacking.getUniqueIDinforme()); //le damos un id unico
        String keyNodoDondeEstaObjectPacking =entiguoObjectPacking.getNodoPadreThisObect() ; //creamos un nodo o rtua o key
        newOBjecPacking.setNodoPadreThisObect(keyNodoDondeEstaObjectPacking);

        newOBjecPacking.setKeyOrNodeContaineHashMap(entiguoObjectPacking.getKeyOrNodeContainsMapPli() );//editamos el valor del nodo donde estara el hasmap
        mibasedata.child(keyNodoDondeEstaObjectPacking).setValue(newOBjecPacking);  //subimos el packing list mapa



    }



    public static void UpadateDatosProceso( HashMap <String ,DatosDeProceso > datosProcesoMap,String keYNodeData) {


        if(datosProcesoMap ==null || datosProcesoMap.size()==0){
            Log.i("saer","el data es nuell o es 0");

            return;

        }

        //  Log.i("saer","el size del mapa es "+datosProcesoMap.size());

        Log.i("saer","el node key data es "+keYNodeData);


        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("datosProcesoContenAcopio").child(keYNodeData);

        // String PuskEY = mibasedata.push().getKey(); //que que cotienen este nodo
        //SUBE MAPA

        mibasedata.setValue(datosProcesoMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                     BottonSheetCallUploading.updateContenedresEnAcopio( Variables.IMAGENES_SET_DE_REPORTE);
                    //lo borramos...
                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }





    public static void updateImagenReport( ImagenReport objecImageReport ) {

        if(mibasedataPathImages==null ) {
            initDatabasesReferenceImagesData();

        }

        Map<String, Object> mapValues = objecImageReport.toMap();

        //SUBE MAPA
        mibasedataPathImages.child(objecImageReport.getImagenPathNow()).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("latypeimage","se subio set data y el id pertence es "+objecImageReport.getIdReportePerteence());


                    //  Toast.makeText(context, "HECHO", Toast.LENGTH_SHORT).show();

                }else  {

                    Log.i("imagheddd","no se subio SEDATA y elerro es "+task.getException());

                }
            }
        });


    }


    public static void UploadProductosPostCosecha( ProductPostCosecha producto) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha");
        // Map<String, Object> mapValues = informeObjct.toMap();
        String PuskEY = mibasedata.push().getKey();

        producto.keyFirebase=PuskEY;
        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {



                    if(Variables.activityCurrent==Variables.FormContenedores){
                        BottonSheetCallUploading.uploadConteendoresForm( Variables.INFORM_REGISTER);


                    } else if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivity){
                        BottonSheetCallUploading.uploadCamionesYcarretas( Variables.CALIBRACIONES_CALENDARIO_ENFUNDE);

                    }


                    }else{

                    //no se subio
                }


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();





            }
        });


    }


    public static void UploadControlcalidadInform(ControlCalidad producto) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listControCalidad");
        String keyDonDeEstaraThisInform=mibasedata.push().getKey();
        producto.setKeyDondeEstarThisInform(keyDonDeEstaraThisInform);
        //SUBE MAPA
        mibasedata.child(keyDonDeEstaraThisInform).setValue(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    BottonSheetCallUploading.UploadControlCalidad(Variables.HASMPA_CONTROL_CALIDAD);

                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {
                    BottonSheetCallUploading.UploadControlCalidad(Variables.ERROR_SUBIDA);


                }
            }
        });


    }


    public static void UpdateControlcalidadInform(ControlCalidad producto, String keyDondeActualizarEsteInform) {
        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listControCalidad");
        //SUBE MAPA
        mibasedata.child(keyDondeActualizarEsteInform).setValue(producto).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    BottonSheetCallUploading.updatControlCalidad(Variables.HASMPA_CONTROL_CALIDAD);

                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }






    public static void addNewHashMapControlCalidad(HashMap <String ,String > hasmapControlCalid,String dondeEstaraThisHasmap) {

        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("ControCalidHasmap");
        // String nododDondeEstaraEsteHasmap = mibasedata2.push().getKey();
        // objPacking.setKeyOrNodeContaineHashMap(nododDondeEstaraEsteHasmap);//editamos el valor del nodo donde estara el hasmap
        mibasedata2.child(dondeEstaraThisHasmap).setValue(hasmapControlCalid).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    BottonSheetCallUploading.UploadControlCalidad(Variables.DEFECT_SELECIONADO_MAP);

                }else{
                    BottonSheetCallUploading.UploadControlCalidad(Variables.ERROR_SUBIDA);


                }


            }
        });


    }


    public static void updateHashMapControlCalidad(HashMap <String ,String > hasmapControlCalid,String dondeActualizarThishasmap) {

        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("ControCalidHasmap");
        mibasedata2.child(dondeActualizarThishasmap).setValue(hasmapControlCalid).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    BottonSheetCallUploading.updatControlCalidad(Variables.DEFECT_SELECIONADO_MAP);

                } else{
                    BottonSheetCallUploading.updatControlCalidad(Variables.ERROR_SUBIDA);


                }



            }
        }) ;

    }


    public static void uploadHasmapDefectSelec(HashMap <String ,String > hasmapDefectsSelec,String dondeEstaraThisHasmap) {

        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("DefectoSelecionadosHashmap");

        mibasedata2.child(dondeEstaraThisHasmap).setValue(hasmapDefectsSelec).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                      if(task.isSuccessful()){
                              BottonSheetCallUploading.UploadControlCalidad(Variables.INFORM_REGISTER);


                      }else{
                          BottonSheetCallUploading.UploadControlCalidad(Variables.ERROR_SUBIDA);


                      }

            }
        }); //subimos el packing list mapa


    }

    public static void updateHasmapDefectSelec(HashMap <String ,String > hasmapDefectsSelec,String dondeRemplzar) {

        DatabaseReference mibasedata2 = rootDatabaseReference.child("Informes").child("DefectoSelecionadosHashmap");

        mibasedata2.child(dondeRemplzar).setValue(hasmapDefectsSelec).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    BottonSheetCallUploading.updatControlCalidad(Variables.FINISH_ALL_UPLOAD);

                }
                else
                BottonSheetCallUploading.updatControlCalidad(Variables.ERROR_SUBIDA);

            }
        });

    }




    public static void UploadCalibracionFrutCal( CalibrFrutCalEnf calibrFrutCalEnf) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listCalibracionFtutsCal");
        // Map<String, Object> mapValues = informeObjct.toMap();
        String PuskEY = mibasedata.push().getKey();

        calibrFrutCalEnf.setKeyFirebase(PuskEY);
        //SUBE MAPA
        mibasedata.child(PuskEY).setValue(calibrFrutCalEnf).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                      if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivity){

                          BottonSheetCallUploading.  uploadCamionesYcarretas(Variables.INFORM_REGISTER);

                      }




                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void AddExportadora( Exportadora exportadora) {

        DatabaseReference mibasedata = rootDatabaseReference.child("exportadoras").child("listExportadoras");
        // Map<String, Object> mapValues = informeObjct.toMap();
        String PuskEY = mibasedata.push().getKey();

        // calibrFrutCalEnf.setKeyFirebase(PuskEY);
        //SUBE MAPA
        mibasedata.push().setValue(exportadora).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void UpdateCalibracionFrutCal( CalibrFrutCalEnf calibrFrutCalEnf) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listCalibracionFtutsCal").child(calibrFrutCalEnf.getKeyFirebase());
        // Map<String, Object> mapValues = informeObjct.toMap();
        //SUBE MAPA
        mibasedata.setValue(calibrFrutCalEnf).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivityPreview){
                        BottonSheetCallUploading.updateCamionesYcarretas(Variables.ELIMNAR_IMAGENES);

                    }



                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {


                }
            }
        });


    }


    public static void UpdateProductosPostCosecha( ProductPostCosecha productosObject) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("listProductosPostCosecha").child(productosObject.keyFirebase);
        // Map<String, Object> mapValues = informeObjct.toMap();


        //SUBE MAPA
        mibasedata.setValue(productosObject).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("updatexxxx","es  UpdateProductosPostCosecha");

                     if(Variables.activityCurrent==Variables.FormPreviewContenedores){
                             BottonSheetCallUploading.UpdateConteendores(Variables.ELIMNAR_IMAGENES);
                     }

                     else if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivityPreview){
                         BottonSheetCallUploading.updateCamionesYcarretas(Variables.CALIBRACIONES_CALENDARIO_ENFUNDE);


                     }









                }

                    Log.i("updatexxxx","no es  succes producto poscosecha");


            }
        });


    }



    private static void getKeyOfImagenDataObjecRTD(String NameImageunique,String nuevaDescripcion){
//        mibasedataPathImages = rootDatabaseReference.child("Informes").child("ImagesData");
        mibasedataPathImages .child(NameImageunique)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String KeyNodeFatheroFObject = snapshot.getKey(); // will be efg

                        if(KeyNodeFatheroFObject !=null){

                            ///editamos el objeto

                            editValue(KeyNodeFatheroFObject,nuevaDescripcion);



                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }


                });
    }





//un bucle que contendra una lista que recorremos

    public static void actualizaDescripcionIms(ArrayList<String>listKeyTosearch){

        try {

            for(int indice=0; indice<listKeyTosearch.size(); indice++){

                String[] array = listKeyTosearch.get(indice).split("@");


                String keyCurrentToEdit=array[0]; //key primero /depsues el content
                String contentDescripcion=array[1];

                //  getKeyOfImagenDataObjecRTD();

                //si esta key esta esta en la lista copia start

                if(Variables.hashMapImagesStart.containsKey(keyCurrentToEdit)){ ///si esta en esta lista esta en la db

                    getkeyActualizaDescripcion(keyCurrentToEdit,contentDescripcion);

                }



            }

        } catch (Exception e) {
            Log.i("samasu","el excepcion es "+e.getMessage().toString());


            e.printStackTrace();
        }




    }


    private static void editValue(String keyAtoUpdate, String descripcion ){

        try{

            HashMap<String, Object> update = new HashMap<>(); //CAMBIAMOS LA PROPIEDA TITULO  Y LE GRAGAMOS EL NUEVO TITULO..

            update.put("descripcionImagen", descripcion);//


            mibasedataPathImages.child(keyAtoUpdate).updateChildren(update);//

        } catch (Exception e) {
            e.printStackTrace();
        }




    }

    private static void editValue(String keyAtoUpdate, int sortvalue ){

        try{

            HashMap<String, Object> update = new HashMap<>(); //CAMBIAMOS LA PROPIEDA TITULO  Y LE GRAGAMOS EL NUEVO TITULO..

            update.put("sortPositionImage", sortvalue);//


            mibasedataPathImages.child(keyAtoUpdate).updateChildren(update);//

        } catch (Exception e) {
            e.printStackTrace();
        }




    }



    private static void getkeyActualizaDescripcion(String NameImageunique, String nuevaDescripcion){

        Query query = mibasedataPathImages.orderByChild("uniqueIdNamePic").equalTo(NameImageunique);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                String key = nodeShot.getKey();
                //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){

                // String KeyNodeFatheroFObject = snapshot.getKey(); // will be efg

                if(key !=null){

                    ///editamos el objeto

                    editValue(key,nuevaDescripcion);



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public static void getkeyActualizaSortNum(String NameImageunique, int numSort){

        Query query = mibasedataPathImages.orderByChild("uniqueIdNamePic").equalTo(NameImageunique);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                try{
                    DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                    String key = nodeShot.getKey();
                    //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){

                    // String KeyNodeFatheroFObject = snapshot.getKey(); // will be efg

                    if(key !=null){
                        ///editamos el objeto

                        //   editValue(key,numSort);

                    }

                } catch (Exception e) {
                    // throw new NoSuchElementException();

                    //  throw new RuntimeException(e);
                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public static void addNewUser(Context contexto, UsuarioQsercon user) {

        Variables.usuarioQserconGlobal =user;


        DatabaseReference mibasedata = rootDatabaseReference.child("Usuarios").child("Colaboradores");

        Map<String, Object> mapValues = user.toMap(); //lo convertimos en mapa

        mibasedata.child(user.getKeyLocaliceUser()).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(contexto, "Iniciaste sesión exitosamente", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {

                    Toast.makeText(contexto, "Ocurrio un Error", Toast.LENGTH_SHORT).show();



                }
            }
        });


    }




    public static void addNewRegistroInforme( Context context ,InformRegister registroInforme) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Registros").child("InformesRegistros");

        String keyThisLoactionForm=mibasedata.push().getKey();

        registroInforme.setKeyLoactionThisForm(keyThisLoactionForm);


        Map<String, Object> mapValues = registroInforme.toMap(); //lo convertimos en mapa



        mibasedata.child(keyThisLoactionForm).setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    switch(Variables.activityCurrent){
                        case Variables.FormContenedores:
                            BottonSheetCallUploading.uploadConteendoresForm(Variables.IMAGENES_SET_DE_REPORTE);
                            break;



                        case Variables.FormCamionesyCarretasActivity:
                            BottonSheetCallUploading.uploadCamionesYcarretas(Variables.IMAGENES_SET_DE_REPORTE);
                            break;

                        case Variables.FormatDatsContAcopi:
                            BottonSheetCallUploading.uploadConteendoresEnAcopio(Variables.IMAGENES_SET_DE_REPORTE);
                            break;


                        case Variables.FormMuestreoRechaz:
                            if(ActivityCuadMuestCalibAndRechaz.callbackUploadNewReport !=null){
                                ActivityCuadMuestCalibAndRechaz.callbackUploadNewReport.uploadNewForm();}
                            break;


                        case Variables.FormCantrolCalidad:
                            BottonSheetCallUploading.UploadControlCalidad(Variables.FINISH_ALL_UPLOAD);
                            break;


                       }


                    }

                else { //si es un error de subida..

                        BottonSheetCallUploading.UploadControlCalidad(Variables.ERROR_SUBIDA);
                }
            }
        });


    }


    public static void decideCallbackHere(){

        ///fgfg
        Log.i("dineroa","hel activityCurrent  es "+Variables.activityCurrent);
        Log.i("dineroa","hel FormContenedores es "+Variables.FormContenedores);
/*
        if(Variables.activityCurrent==Variables.FormContenedores){

            if(ActivityContenedores.callbackUploadNewReport !=null){
                ActivityContenedores.callbackUploadNewReport.uploadNewForm();
            }

        }
*/
         if (Variables.activityCurrent==Variables.FormatDatsContAcopi ){
            if(ActivityContersEnAcopio.callbackUploadNewReport !=null){
                ActivityContersEnAcopio.callbackUploadNewReport.uploadNewForm();
            }

        }

        else if (Variables.activityCurrent==Variables.FormCamionesyCarretasActivity ){

            if(ActivityCamionesyCarretas.callbackUploadNewReport !=null){
                ActivityCamionesyCarretas.callbackUploadNewReport.uploadNewForm();
            }

        }


        else if (Variables.activityCurrent==Variables.FormMuestreoRechaz ){


            if(ActivityCuadMuestCalibAndRechaz.callbackUploadNewReport !=null){
                ActivityCuadMuestCalibAndRechaz.callbackUploadNewReport.uploadNewForm();
            }
            else{

                Log.i("dineroa","es nulo now ");

            }


        }


        else if (Variables.activityCurrent==Variables.FormPackingList ){

            if(ActivityPackingList.callbackUploadNewReport !=null){
                ActivityPackingList.callbackUploadNewReport.uploadNewForm();
            }
            else{

                Log.i("dineroa","es nulo now ");

            }


        }


        else if (Variables.activityCurrent==Variables.FormCantrolCalidad ){

            if(ActivityControlCalidad.callbackUploadNewReport !=null){
                ActivityControlCalidad.callbackUploadNewReport.uploadNewForm();
            }
            else{

                Log.i("dineroa","es nulo now ");

            }


        }






    }


    public static void marckComoRevisadoInformRegister( Context context ,String keyLOactionThisObject,String nombreQuienReviso) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Registros").child("InformesRegistros").child(keyLOactionThisObject);

        Map<String, Object> values = new HashMap<>();
        values.put("seRevisoForm", true);
        values.put("nombreQUienRevisoForm", nombreQuienReviso);



        mibasedata.updateChildren(values).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    Toast.makeText(context, "Se Actualizo Correctamente", Toast.LENGTH_SHORT).show();
                    // Toast.makeText(context, "Se subio", Toast.LENGTH_SHORT).show();

                }else  {

                    Toast.makeText(context, "Ocurrio un Error, revisa tu conexion Internet", Toast.LENGTH_SHORT).show();



                }
            }
        });


    }



    /*
        private void checkIfExistIdAndUpload (String currenTidGenrate, SetInformEmbarque1 informe,SetInformEmbarque2 informe2, SetInformDatsHacienda informe3){

            //  private void checkIfExistIdAndUpload(String currenTidGenrate ) {
            //  Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);

            Query query = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros").equalTo(currenTidGenrate);

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    ControlCalidad  user=null;
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        user=ds.getValue(ControlCalidad.class);
                    }


                    if(user == null) {


                        informe.setUniqueIDinforme(currenTidGenrate);
                        uploadInformeToDatabase(informe,informe2,informe3);
                        //dfghdfh

                        RealtimeDB.addNewRegisterUploadInform(new InformsRegister(currenTidGenrate,Variables.FormContenedores));


                    }else {

                        generateUniqueIdInformeAndContinuesIfIdIsUnique(informe,informe2,informe3);

                    }



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }

    */
    private void generateUniqueIdInformeAndContinuesIfIdIsUnique( SetInformEmbarque1 informe,SetInformEmbarque2 informe2, SetInformDatsHacienda informe3){

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss"+uniqueId);

        //  checkIfExistIdAndUpload(uniqueId,informe,informe2,informe3);


    }

    public static void addNewRegistroInforme2( RegisterTest registroInforme) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Registros2test").child("InformesRegistros");
        String keyThisLoactionForm=mibasedata.push().getKey();

        // registroInforme.setKeyLoactionThisForm(keyThisLoactionForm);

        //   Map<String, Object> mapValues = registroInforme.toMap(); //lo convertimos en mapa
        mibasedata.child(keyThisLoactionForm).setValue(registroInforme).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    /**vamos a llamar a otro*/

                    BottonSheetCallUploading.uploadConteendoresForm(Variables.OBJECT_SetInformEmbarque2);

                    Log.i("hurraterminamos","aqui hemos terminado hurra");


                }else  {

                    //   Toast.makeText(context, "Ocurrio un Error, revisa tu conexion Internet", Toast.LENGTH_LONG).show();

                }
            }
        });


    }

    public static void addNewRegistroInforme3( RegisterTest registroInforme) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Registros2test").child("InformesRegistros");

        String keyThisLoactionForm=mibasedata.push().getKey();




        mibasedata.child(keyThisLoactionForm).setValue(registroInforme).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                    BottonSheetCallUploading.uploadConteendoresForm( Variables.OBJECT_SetInformDatsHacienda);

                }else  {

                    //    Toast.makeText(context, "Ocurrio un Error, revisa tu conexion Internet", Toast.LENGTH_LONG).show();



                }


            }
        });


    }


    public static void addNewRegistroInforme4( RegisterTest registroInforme) {

        DatabaseReference mibasedata = rootDatabaseReference.child("Registros2test").child("InformesRegistros");

        String keyThisLoactionForm=mibasedata.push().getKey();






        mibasedata.child(keyThisLoactionForm).setValue(registroInforme).addOnCompleteListener(new OnCompleteListener<Void>() {


            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                    BottonSheetCallUploading.uploadConteendoresForm( Variables.IMAGENES_SET_DE_REPORTE);



                    Log.i("hemos terminado de llenar todo","");


                }else  {

                    //    Toast.makeText(context, "Ocurrio un Error, revisa tu conexion Internet", Toast.LENGTH_LONG).show();



                }
            }
        });


    }
    /*
    public   void uploaddImagesAndDataImages2SinComprimir(ImagenReport currenImageReport, BottonSheetCallUploading.TrheadUploadImages objectThread) throws IOException {




        Uri uriImage  = Uri.parse(currenImageReport.geturiImage());



        imagename = ImageFolderReferenceImagesAll.child(currenImageReport.getUniqueIdNamePic());


        boolean existValue=false;

        if(null != uriImage) {
            try {
                inputStream = context.getContentResolver().openInputStream(uriImage);
                inputStream.close();
                existValue = true;
            } catch (Exception e) {
                Log.i("IMAGESTASKEdit","exepcion aqui y exist value es");
            }
        }

        if(existValue){
            imagename.putFile(uriImage).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>()
            {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                {
                    return imagename.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>()
            {
                @Override
                public void onComplete(@NonNull Task<Uri> task)
                {
                    if (task.isSuccessful())
                    {
                        Uri downloadUri = task.getResult();
                        Log.e("TAG", "then: " + downloadUri.toString());
                        Log.i("imagheddd","es succces vamos ");




                        String iconPathFirebase = downloadUri.toString();
                        currenImageReport.setUrlStoragePic(iconPathFirebase);
                        Log.i("superstorage","se subio imagen y el url esd  al informe "+currenImageReport.getUrlStoragePic());


                        addNewSetPicsInforme(currenImageReport,objectThread);


                    }else{
                        Variables.contadorImagenesSubidasSumaAll++;
                        Variables.ErrorSubirImage=true;

                        updateObjectGCurrentIndiceAndContadorUpload(objectThread);
                        callThreadByNumHilo(objectThread);

                    }



                }
            });


            Log.i("IMAGESTASKEdit", "empezandoupload task");



        }



    }
*/

}