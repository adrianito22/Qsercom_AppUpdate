package com.tiburela.qsercom.dialog_fragment;

import static com.google.android.gms.tasks.Tasks.whenAll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageDataAndRdB;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BottonSheetCallUploading extends BottomSheetDialogFragment {

    static Query query;
    static DatabaseReference usersdRef;
   static TextView txtImagenesfAIL;

        public static final String TAG = "ActionBottomDialog";
        private View vista;
        static ArrayList<String>idDeleteIMgesList;
        static ContenedoresEnAcopio contenedoresAcx;
        static HashMap<String, DatosDeProceso>miMaProcesoX;
        static CalibrFrutCalEnf calendariox;

    public static StorageReference rootStorageReference;
    static Bitmap bitmapOriginal;
    static UploadTask uploadTask;
    TrheadUploadImages thread1;
    static ReportCamionesyCarretas camionesyCarretasx;
    String textoImagenesPorSubir="";

    static StorageReference imagename;
    static ByteArrayOutputStream stream;
    static InputStream inputStream;
    static     ImagenReport currenImageReport;
    static final StorageReference ImageFolderReferenceImagesAll =  FirebaseStorage.getInstance().getReference().child("imagenes_all_reports");//esta iniiclizarla antes

    static   byte[] data;


    private static String keyPrefrencesIfUserSaveReportLocale="";
          static  Thread thread;
   static  Handler handler1 = new Handler();
    static ProgressBar progressBar;
    static TextView txtTitle;
    static ImageView imgIcon;
    static Button btnOkButton;


    /**IMAGENES LIST*/
    static  ArrayList<ImagenReport>listImagesx;


   static int activityIdx;
    static  TextView txtSubTitle;
    static Context context;





    /**CONTEENDORES*/
    static SetInformEmbarque1 informe1;
    static SetInformEmbarque2 informe2;
    static SetInformDatsHacienda informe3;
    static InformRegister informRegister;
    static ProductPostCosecha productosPoscosecha;

   static HashMap<String, Float> miMapLbriado;

    /**CONTEENDORES CONSTRUCTOR*/


    static  ControlCalidad controlCalidadX;
   static  HashMap<String, String> hasHmapOtherFieldsEditxsx;
   static  HashMap<String,String>hasMapitemsSelecPosicRechazToUploadx;


   /***CONTROL CALIDAD*/
    public static BottonSheetCallUploading newInstance(Context contexta, ControlCalidad controlCalidad, HashMap<String, String> hasHmapOtherFieldsEditxs,
                                                          HashMap<String,String>hasMapitemsSelecPosicRechazToUpload, InformRegister informRegisterx, int ActivityId) {
        context=contexta;
        controlCalidadX=controlCalidad;
        hasMapitemsSelecPosicRechazToUploadx=hasMapitemsSelecPosicRechazToUpload;
        hasHmapOtherFieldsEditxsx=hasHmapOtherFieldsEditxs;
        informRegister=informRegisterx;
        activityIdx=ActivityId;
        return new BottonSheetCallUploading();

    }


    /***CONTENEDORES*/
    public static BottonSheetCallUploading newInstance(Context contexta, SetInformEmbarque1 informEmbq1, SetInformEmbarque2 informEmbq2,
                                                       SetInformDatsHacienda datoshda, InformRegister informRegisterx, ProductPostCosecha productos,
                                                       ArrayList<ImagenReport>listImages, HashMap<String, Float> miMapLbriadox,int ActivityId) {
        context=contexta;
        informe1=informEmbq1;
        informe2=informEmbq2;
        informe3=datoshda;
        informRegister=informRegisterx;
        productosPoscosecha=productos;
        listImagesx=listImages;
        activityIdx=ActivityId;
        miMapLbriado=miMapLbriadox;
        return new BottonSheetCallUploading();

    }

    /***CONTENEDORES EN ACOPIO*/
    public static BottonSheetCallUploading newInstance(Context contexta, ContenedoresEnAcopio contenedoresAc,
                                                       InformRegister informRegisterx,HashMap<String, DatosDeProceso>miMap,
                                                       ArrayList<ImagenReport>listImages, int ActivityId) {

        contenedoresAcx=contenedoresAc;
        context=contexta;
        miMaProcesoX=miMap;
        informRegister=informRegisterx;
        listImagesx=listImages;
        activityIdx=ActivityId;
        return new BottonSheetCallUploading();

    }



    /***CAMIONES Y CARRETAS*/
    public static BottonSheetCallUploading newInstance(Context contexta, ReportCamionesyCarretas camionesyCarretas,
                                                       CalibrFrutCalEnf calendario,
                                                       InformRegister informRegisterx, ProductPostCosecha productos,
                                                       ArrayList<ImagenReport>listImages, int ActivityId) {

        calendariox=calendario;
        camionesyCarretasx=camionesyCarretas;
        context=contexta;
        informRegister=informRegisterx;
        productosPoscosecha=productos;
        listImagesx=listImages;
        activityIdx=ActivityId;
        return new BottonSheetCallUploading();

    }


    /***EDIT CAMIONES Y CARRETAS*/
    public static BottonSheetCallUploading newInstance(Context contexta, ReportCamionesyCarretas camionesyCarretas,
                                                       CalibrFrutCalEnf calendario, ProductPostCosecha productos,
                                                       ArrayList<ImagenReport>listImages, int ActivityId) {

        calendariox=calendario;
        camionesyCarretasx=camionesyCarretas;
        context=contexta;
        productosPoscosecha=productos;
        listImagesx=listImages;
        activityIdx=ActivityId;
        return new BottonSheetCallUploading();

    }





    @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            keyPrefrencesIfUserSaveReportLocale   = getArguments().getString("keyPrefrencesReportCurrent","");


            vista= inflater.inflate(R.layout.layout_botton_sheetcd, container, false);
            progressBar =vista.findViewById(R.id.progressBar);
            txtTitle =vista.findViewById(R.id.txtAdviser);
            txtSubTitle =vista.findViewById(R.id.txtSubheader);
            imgIcon=vista.findViewById(R.id.imgIcon);
            btnOkButton=vista.findViewById(R.id.btnOkButton);
           txtImagenesfAIL=vista.findViewById(R.id.txtImagenesfAIL);

            btnOkButton.setEnabled(false);
            Variables.contador=0;
            StorageDataAndRdB.indiceCurrentOFlistIamges=0;


            btnOkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Activity thisActivity = getActivity();
                    if (thisActivity != null) {
                        thisActivity.finish();
                    }

                     try {
                         dismiss();

                     } catch (Exception e) {
                         Log.i("misfafa","la expecion es "+e.getMessage());

                     }

                }
            });


        decideMethodCallUpdate(activityIdx);



            return  vista;


        }
        @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            Log.i("ontatch","se ejecuto onViewCreated");

            //  view.findViewById(R.id.textView4).setOnClickListener(this);
        }


        @Override
        public void onAttach(Context context) {
            super.onAttach(context);

        }




    @Override
    public void onStart() {
        super.onStart();




        Log.i("ontatch","se ejecuto onStart");

    }



    public static void uploadConteendoresForm(int tipoObjectoQueSubiremosNow){
     /**en llamos a este metodo agreghando el tipo de objeto class que subiremos */

            if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformEmbarque1){
                RealtimeDB.addNewDSetinformEmarque1(informe1);
            }
            else if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformEmbarque2){
                RealtimeDB.addNewInformeEmbarque2(context,informe2); //addNewInformeEmbarque2
                Log.i("finalizando","SECOND");
            }
            else if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformDatsHacienda){
                RealtimeDB.addNewDSetinformEmarque1(informe3); //por ejempo en este metodo cuando suba el refiter form/..
                //cuando llamemos a register terminamos todo...
            }

            else if(tipoObjectoQueSubiremosNow== Variables.LIBRIADO_IF_EXIST){
                RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs()); //por ejempo en este metodo cuando suba el refiter form/..
            }

            else if(tipoObjectoQueSubiremosNow== Variables.PRODUCTS_POST_COSECHA){
                RealtimeDB.UploadProductosPostCosecha(productosPoscosecha); //por ejempo en este metodo cuando suba el refiter form/..
            }

            else if(tipoObjectoQueSubiremosNow== Variables.INFORM_REGISTER){  //PENULTIMO
                RealtimeDB.addNewRegistroInforme(context,informRegister);
            }


            else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){ //ANTEPNULTIMO
                BottonSheetCallUploading BTON= new BottonSheetCallUploading();
                BTON.callThreadImagenesUpload();

                if(!keyPrefrencesIfUserSaveReportLocale.equals("")){
                    SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
                }
            }

    }



    /**probar camiones y carretas flujo ,pero antes chekear que este todo bien en codigo y depsues testear
     * depues seguirira preview camiones y carretas mas o menos simsilar al de subida de camiones y carretas asi es mas facil..
     * ,, depsues conteendoires en acopio y conteenedores en acopio preview..*/


    public static void uploadCamionesYcarretas(int tipoObjectoQueSubiremosNow){

        if(tipoObjectoQueSubiremosNow== Variables.OBJECT_CAMIONESYCARRETAS){
            RealtimeDB.addNewReportCalidaCamionCarrretas(camionesyCarretasx);
        }

        else if(tipoObjectoQueSubiremosNow== Variables.PRODUCTS_POST_COSECHA){
            RealtimeDB.UploadProductosPostCosecha(productosPoscosecha); //por ejempo en este metodo cuando suba el refiter form/..
        }

        else if(tipoObjectoQueSubiremosNow== Variables.CALIBRACIONES_CALENDARIO_ENFUNDE){
            RealtimeDB.UploadCalibracionFrutCal(calendariox); //por ejempo en este metodo cuando suba el refiter form/..
        }


        else if(tipoObjectoQueSubiremosNow== Variables.INFORM_REGISTER){  //PENULTIMO
            RealtimeDB.addNewRegistroInforme(context,informRegister);
        }


        else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){ //ANTEPNULTIMO
            BottonSheetCallUploading BTON= new BottonSheetCallUploading();
            BTON.callThreadImagenesUpload();

            if(!keyPrefrencesIfUserSaveReportLocale.equals("")){
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
            }
        }

    }


    public static void updateCamionesYcarretas(int tipoObjectoQueSubiremosNow){

         /**ARHORA REVISAMOS EL FLUJO */

          Log.i("samisas","update camiones y c");

        if(tipoObjectoQueSubiremosNow== Variables.OBJECT_CAMIONESYCARRETAS){
            Log.i("samisas","update camiones y c  1");
            RealtimeDB.updateCalidaCamionCarrretas(camionesyCarretasx);
        }


        else if(tipoObjectoQueSubiremosNow== Variables.PRODUCTS_POST_COSECHA){
            Log.i("samisas","update camiones y c  2");

            RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha); //por ejempo en este metodo cuando suba el refiter form/..


        }


        else if(tipoObjectoQueSubiremosNow== Variables.CALIBRACIONES_CALENDARIO_ENFUNDE){
            Log.i("samisas","update camiones y c  3");

            RealtimeDB.UpdateCalibracionFrutCal(calendariox); //por ejempo en este metodo cuando suba el refiter form/..
        }

        else if(tipoObjectoQueSubiremosNow== Variables.ELIMNAR_IMAGENES){ //update info images
            /*ESTO PARA ELIMNAR LA SIMAGENES Id**/
            geTidAndDelete(0);
        }

        else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){ //ANTEPNULTIMO
            Log.i("samisas","update camiones y c  4");
            BottonSheetCallUploading BTON= new BottonSheetCallUploading();
            BTON.callThreadImagenesUpload();

        }

        //aqui actualizamos




    }

    private static void  geTidAndDelete(int indiceIterador) { //busca el que tenga esa propieda y obtiene el id node child

         int[] idIterador = {indiceIterador};

        if(indiceIterador>=Variables.listImagesToDelete.size()){

            if(Variables.activityCurrent==Variables.FormCamionesyCarretasActivityPreview){

                updateCamionesYcarretas(Variables.IMAGENES_SET_DE_REPORTE);


            }else if(Variables.activityCurrent==Variables.FormPreviewContenedores){

                UpdateConteendores(Variables.IMAGENES_SET_DE_REPORTE);


            }


            else if(Variables.activityCurrent==Variables.FormatDatsContAcopiPREVIEW){
                updateContenedresEnAcopio(Variables.IMAGENES_SET_DE_REPORTE);


            }
            //hemos terminado;;;

        }
        else {
            String idUniqueToDelete=  Variables.listImagesToDelete.get(indiceIterador);
             query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("uniqueIdNamePic").equalTo(idUniqueToDelete);
             usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData");
            //  Query query = usersdRef.orderByChild("uniqueIdNamePic").equalTo(Variables.currentCuponObjectGlob.getUniqueIdCupòn());

            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){
                    try {

                        DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                        String key = nodeShot.getKey();

                        usersdRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                  //  indiceIterador++;
                                    idIterador[0]++;

                                    geTidAndDelete(idIterador[0]);
                                    Log.i("eliminamos","aqui se elimino esto");

                                    //Toast.makeText(OfertsAdminActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                    } catch (Exception e) {
                        Log.i("eliminamos","aqui hay una expecion y es "+e.getMessage());

                        e.printStackTrace();
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }




    }

    public static void uploadConteendoresEnAcopio(int tipoObjectoQueSubiremosNow){
        /**en llamos a este metodo agreghando el tipo de objeto class que subiremos */

        if(tipoObjectoQueSubiremosNow== Variables.FormatDatsContAcopi){
              RealtimeDB.addNewInformContenresAcopio(contenedoresAcx);

        }
        else if(tipoObjectoQueSubiremosNow== Variables.DATOS_PROCESOXX){
            RealtimeDB.addDatosProceso(miMaProcesoX,contenedoresAcx.getDatosProcesoContenAcopioKEYFather());  //subimos

        }

        else if(tipoObjectoQueSubiremosNow== Variables.INFORM_REGISTER){  //PENULTIMO
            RealtimeDB.addNewRegistroInforme(context,informRegister);
        }


        else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){ //ANTEPNULTIMO
            BottonSheetCallUploading BTON= new BottonSheetCallUploading();
            BTON.callThreadImagenesUpload();

            if(!keyPrefrencesIfUserSaveReportLocale.equals("")){
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
            }
        }

    }


    public static void updateContenedresEnAcopio(int tipoObjectoQueSubiremosNow){
        /**en llamos a este metodo agreghando el tipo de objeto class que subiremos */

        if(tipoObjectoQueSubiremosNow== Variables.FormatDatsContAcopiPREVIEW){
            RealtimeDB.updateInformContenresAcopio(contenedoresAcx);
        }

        else if(tipoObjectoQueSubiremosNow== Variables.DATOS_PROCESOXX){
            RealtimeDB.UpadateDatosProceso(miMaProcesoX,contenedoresAcx.getDatosProcesoContenAcopioKEYFather());  //subimos
             }


        else if(tipoObjectoQueSubiremosNow== Variables.ELIMNAR_IMAGENES){
                geTidAndDelete(0);
        }

        else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){ //ANTEPNULTIMO
            BottonSheetCallUploading BTON= new BottonSheetCallUploading();
            BTON.callThreadImagenesUpload();

            if(!keyPrefrencesIfUserSaveReportLocale.equals("")){
                SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
            }
        }

    }


    public void callThreadImagenesUpload(){

            /**las imagenes por subir y contador de imagenes subidas reseteamos las subidas*/
            Variables.numImagenesSubirTotal=listImagesx.size();
            Variables.contadorImagenesSubidasSumaAll =0;
            Variables.contadorImagenesFall =0;

        StorageDataAndRdB.initContexta(context);

            Log.i("IMAGESTASKEdit","la cantidad de imagenes a subir es: "+Variables.numImagenesSubirTotal);

                  textoImagenesPorSubir= ""+Variables.contadorImagenesSubidasSumaAll+" imagenes subidas de "+Variables.numImagenesSubirTotal;
                 txtTitle.setText(textoImagenesPorSubir);

         thread1=new TrheadUploadImages(listImagesx,1);
         thread1.start();


    }

 public  static void UpdateConteendores(int reportTiPOSubidoAndState){

     if(reportTiPOSubidoAndState== Variables.SEVERAL_INFORMS_UPDATE){
         RealtimeDB.updateSetinformEmbarq1(informe1);}


     else if(reportTiPOSubidoAndState== Variables.UPDATEINform_2){
         RealtimeDB.actualizaInformePart2(informe2);
     }

     else if(reportTiPOSubidoAndState== Variables.UPDATEINform_3){
         RealtimeDB.actualizaInformePart3(informe3);

     }
     else if(reportTiPOSubidoAndState== Variables.LIBRIADO_IF_EXIST){
         RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs());


     }
     else if(reportTiPOSubidoAndState== Variables.PRODUCTS_POST_COSECHA){
         RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha);

     }
     else if(reportTiPOSubidoAndState== Variables.ELIMNAR_IMAGENES){
         geTidAndDelete(0);
     }

     else if(reportTiPOSubidoAndState== Variables.IMAGENES_SET_DE_REPORTE){

         BottonSheetCallUploading BTON= new BottonSheetCallUploading();
         BTON.callThreadImagenesUpload();

     }

}


    public  static void UploadControlCalidad(int  tipoObjectoQueSubiremosNow){
        final int[] valuePercent = {0};

        thread = new Thread(new Runnable() {
            @Override
            public void run() {//esto en BACGROUND

                if(tipoObjectoQueSubiremosNow== Variables.CONTROL_CALIDAD_OBJECT) {
                    RealtimeDB.UploadControlcalidadInform(controlCalidadX);
                    valuePercent[0] =20;


                }


                else if(tipoObjectoQueSubiremosNow== Variables.HASMPA_CONTROL_CALIDAD){
                    RealtimeDB.addNewHashMapControlCalidad(hasHmapOtherFieldsEditxsx, controlCalidadX.getKeyWhereLocateasHmapFieldsRecha());
                    valuePercent[0] =30;

                }


                else if(tipoObjectoQueSubiremosNow== Variables.DEFECT_SELECIONADO_MAP){
                    RealtimeDB.uploadHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx,controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());
                    valuePercent[0] =40;

                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                   // RealtimeDB.updateHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx, controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());4
                }

                else if(tipoObjectoQueSubiremosNow== Variables.INFORM_REGISTER){
                  //  RealtimeDB.uploadHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx,controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());
                    RealtimeDB.addNewRegistroInforme(context,informRegister);
                    valuePercent[0] =50;

                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                    // RealtimeDB.updateHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx, controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());4
                }



                else if(tipoObjectoQueSubiremosNow== Variables.FINISH_ALL_UPLOAD){
                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                  //  SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                    SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
                    valuePercent[0] =100;
                }


                else if(tipoObjectoQueSubiremosNow== Variables.ERROR_SUBIDA){

                    valuePercent[0]=1000;
                    Log.i("updatexxxx","FINISH_ALL_UPLOAD");

                }

                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        if(progressBar!=null){

                            if(valuePercent[0]==20) {
                                progressBar.setProgress(20); //esto en interfas
                                Log.i("finalizando", "value percent es igual a 100");
                             }


                             else if(tipoObjectoQueSubiremosNow==Variables.ERROR_SUBIDA || valuePercent[0]==1000){
                                progressBar.setProgress(0); //esto en interfas
                                Log.i("updatexxxx", ":( error ");

                                txtSubTitle.setText("Se produjo un error :(");
                                txtTitle.setText("0% COMPLETADO ");
                                btnOkButton.setVisibility(View.VISIBLE);
                                // imgIcon.setVisibility(View.VISIBLE);
                                imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                                btnOkButton.setEnabled(true);
                            }


                            else if (valuePercent[0]==100) {

                                progressBar.setProgress(100); //esto en interfas
                                Log.i("updatexxxx", "update todos hurra");

                                txtSubTitle.setText("Hurra, se subio");
                                txtTitle.setText("100% COMPLETADO");

                                btnOkButton.setVisibility(View.VISIBLE);
                                // imgIcon.setVisibility(View.VISIBLE);
                                imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                                btnOkButton.setEnabled(true);



                            }
                        }


                    }
                });

                //cuando termine esto vamos a darle..

            }
        });   //call it
        thread.start();


    }

    public  static void updatControlCalidad(int  tipoObjectoQueSubiremosNow){
        final int[] valuePercent = {0};

        thread = new Thread(new Runnable() {
            @Override
            public void run() {//esto en BACGROUND

                if(tipoObjectoQueSubiremosNow== Variables.CONTROL_CALIDAD_OBJECT) {
                    valuePercent[0]=20;

                    RealtimeDB.UpdateControlcalidadInform(controlCalidadX, controlCalidadX.getKeyDondeEstarThisInform());
                }


                else if(tipoObjectoQueSubiremosNow== Variables.HASMPA_CONTROL_CALIDAD){
                    valuePercent[0]=30;

                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                    RealtimeDB.updateHashMapControlCalidad(hasHmapOtherFieldsEditxsx, controlCalidadX.getKeyWhereLocateasHmapFieldsRecha());
                }


                else if(tipoObjectoQueSubiremosNow== Variables.DEFECT_SELECIONADO_MAP){
                    valuePercent[0]=40;

                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                        RealtimeDB.updateHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx, controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());}



                else if(tipoObjectoQueSubiremosNow== Variables.FINISH_ALL_UPLOAD){
                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                   // SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
                    valuePercent[0]=100;

                }


                else if(tipoObjectoQueSubiremosNow== Variables.ERROR_SUBIDA){

                    valuePercent[0]=1000;
                    Log.i("updatexxxx","FINISH_ALL_UPLOAD");

                }


//es dedcion
                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        if(progressBar!=null){


                             if(tipoObjectoQueSubiremosNow==Variables.ERROR_SUBIDA || valuePercent[0]==1000){
                                progressBar.setProgress(0); //esto en interfas
                                Log.i("updatexxxx", ":( error ");

                                txtSubTitle.setText("Se produjo un error :(");
                                txtTitle.setText("0% COMPLETADO ");
                                btnOkButton.setVisibility(View.VISIBLE);
                                // imgIcon.setVisibility(View.VISIBLE);
                                imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                                btnOkButton.setEnabled(true);
                            }


                            else if (valuePercent[0]==100) {

                                progressBar.setProgress(100); //esto en interfas
                                Log.i("updatexxxx", "update todos hurra");

                                txtSubTitle.setText("Hurra, se subio");
                                txtTitle.setText("100% COMPLETADO");

                                btnOkButton.setVisibility(View.VISIBLE);
                                // imgIcon.setVisibility(View.VISIBLE);
                                imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                                btnOkButton.setEnabled(true);



                            }
                        }


                    }
                });

                //cuando termine esto vamos a darle..

            }
        });   //call it
        thread.start();


    }



    public class TrheadUploadImages extends Thread {   //esta clase contiene un object de la clase storage data
        public int threadNUm;
       ArrayList<ImagenReport> arrayListx;
        public  int indiceCurrentObjectx;
        public  int contadorImagenesSubidasThisObject;
        boolean seSubioAlLImagenesSet;

        public TrheadUploadImages(ArrayList <ImagenReport> arrayListx, int threadNUm){
            this.arrayListx=arrayListx;
            indiceCurrentObjectx=0;
            contadorImagenesSubidasThisObject =0;
            this.threadNUm = threadNUm;
            seSubioAlLImagenesSet =false;

        }



        @Override
        public void run() {//esto en BACGROUND
            Log.i("subprocesa", "el rhread num num " + threadNUm);


            ImagenReport  currenImageReport;

            float porcentajeXFlotante=((float) Variables.contadorImagenesSubidasSumaAll /  (float)Variables.numImagenesSubirTotal)*100;
            int porcentajeX=(int)porcentajeXFlotante;


             textoImagenesPorSubir=""+Variables.contadorImagenesSubidasSumaAll+" imagenes subidas de "+Variables.numImagenesSubirTotal;
              txtTitle.setText(textoImagenesPorSubir);


            if(Variables.contadorImagenesSubidasSumaAll == thread1.arrayListx.size() ||
                    thread1. indiceCurrentObjectx>=thread1.arrayListx.size() ||
                    Variables.contadorImagenesSubidasSumaAll==Variables.numImagenesSubirTotal ){
                //significa que  terminamos una seccion al menos

                thread1.seSubioAlLImagenesSet =true;
                Log.i("IMAGESTASKEdit","la lista 1 READY OK BIEN ");
                porcentajeX=100;

            }

            else{  //aun no hemos terminadp

                currenImageReport = thread1.arrayListx.get(thread1.indiceCurrentObjectx);
                try {
                    uploaddImagesAndDataImages1(currenImageReport);
                } catch (IOException e) {
                    Log.i("ege","excpecion es "+e.getMessage());
                   // throw new RuntimeException(e);
                }
                // start();


            }



            final int[] finalPorcentajeX = {porcentajeX};


            handler1.post(new Runnable() {
                @Override
                public void run() {

                        progressBar.setProgress(finalPorcentajeX[0]); //esto en interfas

                        txtImagenesfAIL.setText("Error imagenes subidas: "+Variables.contadorImagenesFall);

                        if(finalPorcentajeX[0] ==0){
                            txtSubTitle.setText("Espere");
                            txtSubTitle.setText("Subiendo imagenes...");
                            progressBar.setProgress(20); //esto en interfas

                        }


                        if(finalPorcentajeX[0] ==100  ){
                            Log.i("finalizando","value percent es igual a 100");
                            txtSubTitle.setText("Hurra, se subió exitosamente");
                            txtTitle.setText("100% COMPLETADO");
                            btnOkButton.setVisibility(View.VISIBLE);
                            // imgIcon.setVisibility(View.VISIBLE);
                            imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                            btnOkButton.setEnabled(true);

                        }




                }
            });

        }


        public   void uploaddImagesAndDataImages1( ImagenReport currenImageReport) throws IOException {

            /**SI HAY PROBELASM DE URI PERMISOS ASEGURARSE QUE EL URI CONTENGA UNA PROPIEDAD QUE HACER QUE LE DE PERMISOS DE
             * LECTURA ALGO AS..ESO EN INTENT AL SELECIONAR IMAGENES*/
            Log.i("IMAGESTASKEdit","vamos a subir para el hilo "+1);

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

                Log.i("IMAGESTASKEdit", "bitmap original here ");

                bitmapOriginal = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uriImage);
                stream = new ByteArrayOutputStream();
                bitmapOriginal.compress(Bitmap.CompressFormat.WEBP,95,stream);//0=lowe


                data = stream.toByteArray();
                uploadTask = imagename.putBytes(data);

                Log.i("IMAGESTASKEdit", "empezandoupload task");

                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Log.i("IMAGESTASKEdit","es falilure");

                        /***BIEN NOTE : EL METODO DE ABAJO DEBERIA LLEVAR UN PAREMTRO QUE INDENTIFCQUE EL OBJETO GLOBAL
                         * DE BOOTOMSHEETCALLUPLOADING..
                         * AHORA INVOCAMOS EL METODO DE BOTTOM SHEET OTRA VEZ Y LE PASAMOS EL NUEVO INDICE
                         * */

                        Variables.contadorImagenesSubidasSumaAll++;
                        Variables.contadorImagenesFall++;

                        updateObjectGCurrentIndiceAndContadorUpload1();


                        callThreadByNumHilo1();

                        Log.i("imagestorage", "existe una exepecion y es "+exception.getMessage());

                        // Handle unsuccessful uploads
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        //  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("IMAGESTASKEdit","es succes");


                                String iconPathFirebase = uri.toString();
                                currenImageReport.setUrlStoragePic(iconPathFirebase);
                                // value.setIdReportePerteence(uniqueIDImagesSetAndUInforme);
                                Log.i("superstorage","se subio imagen y el url esd  al informe "+currenImageReport.getUrlStoragePic());

                                /**aumnetamos el valor del indice en ek on succes dek siguiente metodo*/
                                  addNewSetPicsInforme1(currenImageReport);

                            }
                        });
                    }
                });

            } else {

                Log.i("IMAGESTASKEdit","se eejcuto el else aqui ");
                Variables.contadorImagenesSubidasSumaAll++;
                Log.i("IMAGESTASKEdit","el contador imagenes subidas es "+ Variables.contadorImagenesSubidasSumaAll);

                Variables.contadorImagenesFall++;

                updateObjectGCurrentIndiceAndContadorUpload1();
                callThreadByNumHilo1();
                Log.i("exepciopmx","no existe valores");

            }



        }
        public  void addNewSetPicsInforme1(ImagenReport objecImageReport ) {

            if(RealtimeDB.mibasedataPathImages==null ) {
                RealtimeDB.initDatabasesReferenceImagesData();
            }



            Map<String, Object> mapValues = objecImageReport.toMap();
            RealtimeDB. mibasedataPathImages.push().setValue(mapValues).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    //   int porcentajeX= (Variables.contadorImagenesSubidas/Variables.numImagenesSubirTotal)*100;

                    if (task.isSuccessful()) {

                        Log.i("IMAGESTASKEdit","se subio imagen report "+objecImageReport.getUrlStoragePic());
                        Variables.contadorImagenesSubidasSumaAll++;
                        Log.i("IMAGESTASKEdit","el contador imagenes subidas es "+ Variables.contadorImagenesSubidasSumaAll);
                        Log.i("IMAGESTASKEdit","llamamos tread otravez ");


                        updateObjectGCurrentIndiceAndContadorUpload1();
                        callThreadByNumHilo1();



                    }else
                    {
                        Variables.contadorImagenesFall++;

                        Variables. ErrorSubirImage=true;
                        updateObjectGCurrentIndiceAndContadorUpload1();
                        callThreadByNumHilo1();


                    }
                }
            });


        }


        public  void updateObjectGCurrentIndiceAndContadorUpload1(){

            thread1.contadorImagenesSubidasThisObject = thread1.contadorImagenesSubidasThisObject +1;
            thread1.indiceCurrentObjectx = thread1.indiceCurrentObjectx +1;

        }

        public   void callThreadByNumHilo1( ){
            thread1.run();
            //  thread1.startThreadMismoObject(objectThread);

        }


    }



    static void decideMethodCallUpdate(int idActivity){

     //   Utils.contadorTareasCompletadas=0;

        Log.i("idcurrent","el id current es "+idActivity);

        switch(idActivity){

            case Variables.FormCantrolCalidad:
                UploadControlCalidad(Variables.CONTROL_CALIDAD_OBJECT);

                break;
            case Variables.FormCantrolCalidadPreview:
                updatControlCalidad(Variables.CONTROL_CALIDAD_OBJECT);

                break;

            case Variables.FormContenedores:
                uploadConteendoresForm(Variables.OBJECT_SetInformEmbarque1);
                break;

            case Variables.FormPreviewContenedores:
                UpdateConteendores(Variables.SEVERAL_INFORMS_UPDATE);
                break;


            case Variables.FormCamionesyCarretasActivity:
                uploadCamionesYcarretas(Variables.OBJECT_CAMIONESYCARRETAS);
                break;


            case Variables.FormCamionesyCarretasActivityPreview:
                updateCamionesYcarretas(Variables.OBJECT_CAMIONESYCARRETAS);
                break;


            case Variables.FormatDatsContAcopi:
                uploadConteendoresEnAcopio(Variables.FormatDatsContAcopi);
                break;


            case Variables.FormatDatsContAcopiPREVIEW:
                updateContenedresEnAcopio(Variables.FormatDatsContAcopiPREVIEW);
                break;


        }


    }




}

