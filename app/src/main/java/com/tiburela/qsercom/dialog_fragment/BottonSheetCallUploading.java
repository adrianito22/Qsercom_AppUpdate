package com.tiburela.qsercom.dialog_fragment;

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

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class BottonSheetCallUploading extends BottomSheetDialogFragment {
        public static final String TAG = "ActionBottomDialog";
        private View vista;
        private static String keyPrefrencesIfUserSaveReportLocale="";

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



            uploadInsertClassQuevamosSubir(Variables.OBJECT_SetInformEmbarque1);


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
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {//esto en BACGROUND

                if(activityIdx==Variables.FormContenedores){

                    if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformEmbarque1){

                        RealtimeDB.addNewDatosHacienda(informe1);

                        valuePercent[0] =10;

                        Log.i("finalizando","FISRT");

                    }
                    else if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformEmbarque2){

                        RealtimeDB.addNewInformeEmbarque2(context,informe2); //addNewInformeEmbarque2
                        valuePercent[0] =20;
                        Log.i("finalizando","SECOND");

                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.OBJECT_SetInformDatsHacienda){


                        RealtimeDB.addNewDatosHacienda(informe3); //por ejempo en este metodo cuando suba el refiter form/..
                        valuePercent[0] =30;
                        Log.i("finalizando","THIRD");

                        //cuando llamemos a register terminamos todo...
                    }

                    else if(tipoObjectoQueSubiremosNow== Variables.LIBRIADO_IF_EXIST){


                        RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado,informe1.getKeyOrNodeLibriadoSiEs()); //por ejempo en este metodo cuando suba el refiter form/..
                        valuePercent[0] =40;
                        Log.i("finalizando","THIRD");

                        //cuando llamemos a register terminamos todo...
                    }



                    else if(tipoObjectoQueSubiremosNow== Variables.PRODUCTS_POST_COSECHA){
                        valuePercent[0] =75;

                        RealtimeDB.UploadProductosPostCosecha(productosPoscosecha); //por ejempo en este metodo cuando suba el refiter form/..

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
        t.start();





    }








}

