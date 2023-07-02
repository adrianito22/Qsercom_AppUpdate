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
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.callbacks.ContenedoresCallback;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.RegisterTest;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageDataAndRdB;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BottonSheetCallUploading extends BottomSheetDialogFragment {
        public static final String TAG = "ActionBottomDialog";
        private View vista;
    public static StorageReference rootStorageReference;
    static Bitmap bitmapOriginal;
    static UploadTask uploadTask;
    TrheadUploadImages thread1;

    String textoImagenesPorSubir="";

    static StorageReference imagename;
    static ByteArrayOutputStream stream;
    static InputStream inputStream;
    static     ImagenReport currenImageReport;
    static final StorageReference ImageFolderReferenceImagesAll =  FirebaseStorage.getInstance().getReference().child("imagenes_all_reports");//esta iniiclizarla antes

    static   byte[] data;
 //  public  TrheadUploadImages thread2;


    private static String keyPrefrencesIfUserSaveReportLocale="";
          static  Thread thread;
   static  Handler handler1 = new Handler();
    CoordinatorLayout lineaLyaout;
    int indicex=0;
    static ProgressBar progressBar;
    static TextView txtTitle;
    static ImageView imgIcon;
    static Button btnOkButton;


    /**IMAGENES LIST*/
    static  ArrayList<ImagenReport>listImagesx;




   static int activityIdx;
    static  TextView txtSubTitle;
    ArrayList<String>allkeys= new ArrayList<>();
   static Context context;

   Activity ContenedoresObject;

    public static ContenedoresCallback callbackContenedores;

    static RegisterTest register1object1x,  register1object2x,  register1object3x;




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



    public static BottonSheetCallUploading newInstance(Context contexta, SetInformEmbarque1 informEmbq1, SetInformEmbarque2 informEmbq2,
                                                       SetInformDatsHacienda datoshda, InformRegister informRegisterx,
                                                       ProductPostCosecha productos, ArrayList<ImagenReport>listImages, HashMap<String, Float> miMapLbriadox,int ActivityId) {
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

    /***pARA CONTENEDORES*/
    public static BottonSheetCallUploading newInstance(Context contexta, RegisterTest register1object1, RegisterTest register1object2, RegisterTest register1object3,
                                                       ArrayList<ImagenReport>listImages,int ActivityId) {
        context=contexta;

        register1object1x=register1object1;
        register1object2x=register1object2;
        register1object3x=register1object3;
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


               if(Utils.esNuevoReport){

                    if(activityIdx==Variables.FormCantrolCalidad){
                        UploadControlCalidad(Variables.CONTROL_CALIDAD_OBJECT);
                    }else {
                        uploadInsertClassQuevamosSubir(Variables.OBJECT_SetInformEmbarque1);
                    }
               }
               else
               {

                   Utils.contadorTareasCompletadas=0;

                   UpdateReportAndCallThreads(Variables.SEVERAL_INFORMS_UPDATE);

                   //aqui llamos el nuevo metodo
                 //  f
                  // UpdateReportThread();

               }

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









    private void setProgres(ProgressBar bar,int numUploadsTOUpload, int numUploaded){
             if(numUploadsTOUpload==0){
                 return;
             }
        //cual es el porciento que reperesenta esta cantidad ..
        int percent=(numUploaded*100)/numUploadsTOUpload;
        bar.setProgress(percent);

        Log.i("misdatagsdf","el percent es "+percent);

    }



    public static void uploadInsertClassQuevamosSubir(int tipoObjectoQueSubiremosNow){
     /**en llamos a este metodo agreghando el tipo de objeto class que subiremos */
        final int[] valuePercent = {0};


         if(thread!=null && thread.isAlive()){
          Log.i("isalivebbd","is alive aqui");

         }

         thread = new Thread(new Runnable() {

            @Override
            public void run() {//esto en BACGROUND

                if(activityIdx==Variables.FormContenedores){

                    if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformEmbarque1){
                        valuePercent[0] =10;

                         if(Utils.esNuevoReport){
                             RealtimeDB.addNewDSetinformEmarque1(informe1);
                         }else{
                             RealtimeDB.updateSetinformEmbarq1(informe1);
                         }

                        Log.i("finalizando","FISRT");

                    }
                    else if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformEmbarque2){

                        valuePercent[0] =20;

                        if(Utils.esNuevoReport){

                            RealtimeDB.addNewInformeEmbarque2(context,informe2); //addNewInformeEmbarque2

                        }else{
                            RealtimeDB.actualizaInformePart2(informe2); //es dedcion
                        }


                        Log.i("finalizando","SECOND");

                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformDatsHacienda){


                        valuePercent[0] =30;
                        Log.i("finalizando","THIRD");

                        if(Utils.esNuevoReport){

                            RealtimeDB.addNewDSetinformEmarque1(informe3); //por ejempo en este metodo cuando suba el refiter form/..

                        }else{
                            RealtimeDB.actualizaInformePart3(informe3); //es dedcion
                        }



                        //cuando llamemos a register terminamos todo...
                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.LIBRIADO_IF_EXIST){


                        valuePercent[0] =40;



                        if(Utils.esNuevoReport){

                            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs()); //por ejempo en este metodo cuando suba el refiter form/..

                        }else{
                            RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs()); //es dedcion
                        }




                        Log.i("finalizando","THIRD");

                        //cuando llamemos a register terminamos todo...
                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.PRODUCTS_POST_COSECHA){
                        valuePercent[0] =75;




                        if(Utils.esNuevoReport){

                            RealtimeDB.UploadProductosPostCosecha(productosPoscosecha); //por ejempo en este metodo cuando suba el refiter form/..

                        }else{

                         //   RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha,productosPoscosecha.keyFirebase); //es dedcion


                        }




                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.INFORM_REGISTER){  //PENULTIMO
                        valuePercent[0] =80;
                        RealtimeDB.addNewRegistroInforme(context,informRegister);


                        //CUANDO SUBAMOS INFORM REGISTER TERMINAMOS...
                    }


                    else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){ //ANTEPNULTIMO
                        valuePercent[0] =85;

                        Log.i("finalizando","THOURT");

                        try {

                            StorageDataAndRdB.initImagenesAllAndArrayListAndContext(listImagesx,context);
                            StorageDataAndRdB.uploaddImagesAndDataImages(0);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.FINISH_ALL_UPLOAD){ // ULTIMO .ESTE LLAMOS DESPUES DE SUBIR TODOS EN INFORM RESGITER
                        valuePercent[0] =100;

                        Log.i("finalizando","ok hemos terminado 100%");


                        if(!keyPrefrencesIfUserSaveReportLocale.equals("")){
                              SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);
                          }
                           //AQUI GUARDAMOS UNICAMNTE SI ESTE OBJETO CONTIENE PREFRENCIAS..

                        //CUANDO SUBAMOS INFORM REGISTER TERMINAMOS...
                    }

                }








                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        if(progressBar!=null){
                            progressBar.setProgress(valuePercent[0]); //esto en interfas
                            if(valuePercent[0]==85){

                                txtSubTitle.setText("Espere");
                                txtSubTitle.setText("Subiendo imagenes...");

                            }

                           else if(valuePercent[0]==100 ){

                                Log.i("finalizando","value percent es igual a 100");

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

            }
        });   //call it
        thread.start();





    }





    public void UpdateReportInform(){

            Log.i("IMAGESTASKEdit","FINISH_ONLY_UPLOAD_REPORT llanado");
            ///mirtad en uno y la mitad en ek resto

            ArrayList<ImagenReport>images1 = new ArrayList<>();
            ArrayList<ImagenReport>images2 = new ArrayList<>();

            int resto=listImagesx.size() % 2;

            if(resto==0){ //quiere decir que es mitad exacta y no sobra nada
                for(int indice=0; indice< listImagesx.size(); indice++){  //tine 10 items
                    if(indice<listImagesx.size()/2){
                        images1.add(listImagesx.get(indice));
                    }
                    else{
                        images2.add(listImagesx.get(indice));
                    }
                }
            }else{  ///no es mitad exacta

                for(int indice=0; indice< listImagesx.size(); indice++){  //tine 10 items
                    if(indice<(listImagesx.size()-1)/2){
                        images1.add(listImagesx.get(indice));
                    }
                    else{
                        images2.add(listImagesx.get(indice));
                    }
                }
            }


            /**las imagenes por subir y contador de imagenes subidas reseteamos las subidas*/
            Variables.numImagenesSubirTotal=listImagesx.size();
            Variables.contadorImagenesSubidasSumaAll =0;
            StorageDataAndRdB.initContexta(context);

            Log.i("IMAGESTASKEdit","el list 1 tiene size de  "+images1.size());
            Log.i("IMAGESTASKEdit","el list 2 tiene size de "+images2.size());
            Log.i("IMAGESTASKEdit","la cantidad de imagenes a subir es: "+Variables.numImagenesSubirTotal);

             /***si no usaremos estos objetos de forma global*/

           // thread1=new TrheadUploadImages(listImagesx);  //dejemoslo en un solo hilo aqui
            //thread1.startThreadMismoObject(thread1);
//
                  textoImagenesPorSubir= ""+Variables.contadorImagenesSubidasSumaAll+" imagenes subidas de "+Variables.numImagenesSubirTotal;
                 txtTitle.setText(textoImagenesPorSubir);

         thread1=new TrheadUploadImages(listImagesx,1);
         thread1.start();


    }

 public  static void UpdateReportAndCallThreads(int reportTiPOSubidoAndState){

     if(reportTiPOSubidoAndState== Variables.SEVERAL_INFORMS_UPDATE){
         RealtimeDB.updateSetinformEmbarq1(informe1);
         RealtimeDB.actualizaInformePart2(informe2);
         RealtimeDB.actualizaInformePart3(informe3);
         RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs());
         RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha);
     }

     else if(reportTiPOSubidoAndState== Variables.IMAGENES_SET_DE_REPORTE){  //ahora rtocan las imagenes

         BottonSheetCallUploading BTON= new BottonSheetCallUploading();
         BTON.UpdateReportInform();

     }






}


public   void treadImagesx(int  tipoObjectoQueSubiremosNow){

        //divismos la lista en 2 y llamaos dos suprocesos o 3 paraver que pasa....
       //cuando
       ///enviar 2 apps para ver.....
    //probar si necesidad de convertir en otro bitmap parce que puede ir mas rapido..

    final int[] valuePercent = {0};

    thread = new Thread(new Runnable() {
        @Override
        public void run() {
             if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){
                Log.i("updatexxxx","IMAGENES_SET_DE_REPORTE");
                valuePercent[0] =50;
                try {
                    StorageDataAndRdB.initImagenesAllAndArrayListAndContext(listImagesx, context);
                    StorageDataAndRdB.uploaddImagesAndDataImages(0);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

            else if(tipoObjectoQueSubiremosNow== Variables.FINISH_ALL_UPLOAD){
                Log.i("updatexxxx","FINISH_ALL_UPLOAD");
                valuePercent[0] =100;

            }

//es dedcion
            handler1.post(new Runnable() {
                @Override
                public void run() {

                    if(progressBar!=null){

                        if(valuePercent[0]==50) {
                            progressBar.setProgress(20); //esto en interfas
                            Log.i("finalizando", "value percent es igual a 100");

                             /*
                             txtSubTitle.setText("Hurra, se subio");
                             txtTitle.setText("100% COMPLETADO");

                             btnOkButton.setVisibility(View.VISIBLE);
                             // imgIcon.setVisibility(View.VISIBLE);
                             imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                             btnOkButton.setEnabled(true);

                              */

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




    public  static void UploadControlCalidad(int  tipoObjectoQueSubiremosNow){
        final int[] valuePercent = {0};

        thread = new Thread(new Runnable() {
            @Override
            public void run() {//esto en BACGROUND

                if(tipoObjectoQueSubiremosNow== Variables.CONTROL_CALIDAD_OBJECT){
                    valuePercent[0] =20;

                    RealtimeDB.UploadControlcalidadInform(controlCalidadX);

                    Log.i("superff","el size de map 1 es "+hasHmapOtherFieldsEditxsx.size()+" y el key donde estar es "+ controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());

                    Log.i("superff","el size de map 1 es "+hasMapitemsSelecPosicRechazToUploadx.size()+" y el key donde estar es "+ controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());

                    RealtimeDB.addNewHashMapControlCalidad(hasHmapOtherFieldsEditxsx, controlCalidadX.getKeyWhereLocateasHmapFieldsRecha());
                    RealtimeDB.uploadHasmapDefectSelec(hasMapitemsSelecPosicRechazToUploadx,controlCalidadX.getKeyDondeEstaraHasmapDefecSelec());
                    RealtimeDB.addNewRegistroInforme(context,informRegister);

                    //le decimos  que subido..




                }

                else if(tipoObjectoQueSubiremosNow== Variables.FINISH_ALL_UPLOAD){
                    Log.i("elformasd","FINISH_ALL_UPLOAD  Y EL KEY ES "+keyPrefrencesIfUserSaveReportLocale);
                    SharePrefHelper.UpdateRegisterLOCALEMarcaSubido(true,keyPrefrencesIfUserSaveReportLocale);


                    valuePercent[0] =100;

                }




                else if(tipoObjectoQueSubiremosNow== Variables.ERROR_SUBIDA){
                    Log.i("updatexxxx","FINISH_ALL_UPLOAD");


                    valuePercent[0] =100;

                }


//es dedcion
                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        if(progressBar!=null){

                            if(valuePercent[0]==20) {
                                progressBar.setProgress(20); //esto en interfas
                                Log.i("finalizando", "value percent es igual a 100");
                             }


                             else if(tipoObjectoQueSubiremosNow==Variables.ERROR_SUBIDA){
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
                    throw new RuntimeException(e);
                }
                // start();


            }



            final int[] finalPorcentajeX = {porcentajeX};


            handler1.post(new Runnable() {
                @Override
                public void run() {

                    if(progressBar!=null){

                        progressBar.setProgress(finalPorcentajeX[0]); //esto en interfas

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
                        Variables.ErrorSubirImage=true;

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


    private static void uploadFS(final File[] files, final StorageReference certRef, int startIndex, int concurrent) {
        int cnt = 0;
        File file;
        Uri uriFile;
        StorageReference ref;
        while (cnt < concurrent) {
            file = files[startIndex];
            startIndex++;
            if (file.exists() && file.length() > 0) { // local cert file exits
                uriFile = Uri.fromFile(file);
                final int sIdx = startIndex;
                ref = certRef.child("cert/" + uriFile.getLastPathSegment());
                ref.putFile(uriFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        uploadFS(files, certRef, sIdx, 1);

                    }
                });
            }
            cnt++;
        }
    }

    public static void uploadCert() {

    }

}

