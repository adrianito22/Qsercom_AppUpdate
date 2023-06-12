package com.tiburela.qsercom.dialog_fragment;

import static com.google.android.gms.tasks.Tasks.whenAll;
import static com.itextpdf.kernel.pdf.PdfName.Collection;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.callbacks.ContenedoresCallback;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.RegisterTest;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class BottonSheetCallUploading extends BottomSheetDialogFragment {
        public static final String TAG = "ActionBottomDialog";
        private View vista;

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
            CoordinatorLayout lineaLyaout =vista.findViewById(R.id.lineaLyaout);
            txtSubTitle =vista.findViewById(R.id.txtSubheader);

              imgIcon=vista.findViewById(R.id.imgIcon);
              btnOkButton=vista.findViewById(R.id.btnOkButton);

            Log.i("lamundo","el size upload es "+allkeys.size());
            btnOkButton.setEnabled(false);

            btnOkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dismiss();

                }
            });


               if(Utils.esNuevoReport){
                   uploadInsertClassQuevamosSubir(Variables.OBJECT_SetInformEmbarque1);

               }else{

                   //aqui llamos el nuevo metodo
                   f
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
                            RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha); //es dedcion
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

                            StorageData.initImagenesAllAndArrayListAndContext(listImagesx,context);
                            StorageData.uploaddImagesAndDataImages(0);

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



 public  static void UpdateReportThread(int  tipoObjectoQueSubiremosNow){

    // addOnCompleteListener //podemos llmar esto cuando terminemos de subir todas las imagenes, si nos da pronelas ocularlo
     // cuando los 5 0 6 informes se subiron llamra esta funcion con numero imagenes ... y cuando termine llamar esta funcion con int ginish...
     //desecnadenar vamos imagenes alli mimso donde desencadena la finalizacion de task que creamos anteriomente..

     final int[] valuePercent = {0};

     Task<String> task = Utils.sourceTareas.getTask();

     thread = new Thread(new Runnable() {
         @Override
         public void run() {//esto en BACGROUND

                   if(tipoObjectoQueSubiremosNow== Variables.SEVERAL_INFORMS_UPDATE){
                     valuePercent[0] =20;


                       RealtimeDB.updateSetinformEmbarq1(informe1);
                       RealtimeDB.actualizaInformePart2(informe2); //es dedcion
                       RealtimeDB.actualizaInformePart3(informe3); //es dedcion
                       RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs()); //es dedcion
                       RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha);
                       Log.i("finalizando","SECOND");

                       }
                  else if(tipoObjectoQueSubiremosNow== Variables.IMAGENES_SET_DE_REPORTE){
                       valuePercent[0] =50;


                       try {

                           StorageData.initImagenesAllAndArrayListAndContext(listImagesx, context);
                           StorageData.uploaddImagesAndDataImages(0);

                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }

                   }else if(tipoObjectoQueSubiremosNow== Variables.FINISH_ALL_UPLOAD){

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

                             /*
                             txtSubTitle.setText("Hurra, se subio");
                             txtTitle.setText("100% COMPLETADO");

                             btnOkButton.setVisibility(View.VISIBLE);
                             // imgIcon.setVisibility(View.VISIBLE);
                             imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                             btnOkButton.setEnabled(true);

                              */
                         }
                         } else if (valuePercent[0]==100) {

                         progressBar.setProgress(100); //esto en interfas
                         Log.i("finalizando", "value percent es igual a 100");


                             txtSubTitle.setText("Hurra, se subio");
                             txtTitle.setText("100% COMPLETADO");

                             btnOkButton.setVisibility(View.VISIBLE);
                             // imgIcon.setVisibility(View.VISIBLE);
                             imgIcon.setImageResource(R.drawable.baseline_check_circle_24);
                             btnOkButton.setEnabled(true);



                         }


                 }
             });




              //cuando termine esto vamos a darle..

         }
     });   //call it
     thread.start();

/*
     new Thread(new Runnable() {
        @Override
        public void run() {

            RealtimeDB.updateSetinformEmbarq1(informe1);
            RealtimeDB.actualizaInformePart2(informe2); //es dedcion
            RealtimeDB.actualizaInformePart3(informe3); //es dedcion
            RealtimeDB.UpdateHasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs()); //es dedcion
            RealtimeDB.UpdateProductosPostCosecha(productosPoscosecha); //es dedcion

        }
    }).start();
*/

    task.addOnCompleteListener(new OnCompleteListener<String>() {
        @Override
        public void onComplete(@NonNull Task<String> task) {
            //por cada teara completada aumentamos
            task.addOnSuccessListener(new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String result) {
                    Log.i("updatexxxx","es succces result es: "+result);

                  //  progressBar.setProgress(50);

                   // UploadImages(); //vamos a subir imagenes
                    // Task completed successfully
                    // ...
                }
            });

        }
    });



     task.addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
             // Task failed with an exception
             // ...
         }
     });
}


    private static void UploadImages() {

     //   Task<String> taskxx = Utils.sourceTareaSubirIMAGENES.getTask();


        thread = new Thread(new Runnable() {

            @Override
            public void run() {//esto en BACGROUND

                try {

                    StorageData.initImagenesAllAndArrayListAndContext(listImagesx, context);
                    StorageData.uploaddImagesAndDataImages(0);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });   //call it
        thread.start();


        //
/*
        Task<String> taskxx = Utils.sourceTareaSubirIMAGENES.getTask();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    StorageData.initImagenesAllAndArrayListAndContext(listImagesx,context);
                    StorageData.uploaddImagesAndDataImages(0);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
*/

/** para firebase mutitread que devulven obejtos cuando todad terminan...https://firebase.blog/posts/2016/10/become-a-firebase-taskmaster-part-4*/
    }




}

