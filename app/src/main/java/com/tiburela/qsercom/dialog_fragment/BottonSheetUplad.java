package com.tiburela.qsercom.dialog_fragment;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.callbacks.ContenedoresCallback;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.RegisterTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BottonSheetUplad extends BottomSheetDialogFragment implements ContenedoresCallback {
        public static final String TAG = "ActionBottomDialog";
        private View vista;

    CoordinatorLayout lineaLyaout;
    int indicex=0;
    private ProgressBar progressBar;
    private TextView txtAdviser;

    TextView txtContadorArchivosPorSubir;
    ArrayList<String>allkeys= new ArrayList<>();
   static Context context;

   Activity ContenedoresObject;

    public static ContenedoresCallback callbackContenedores;

    static RegisterTest register1object1x,  register1object2x,  register1object3x;


    /***pARA CONTENEDORES*/
    public static  BottonSheetUplad newInstance(Context contexta, RegisterTest register1object1, RegisterTest register1object2, RegisterTest register1object3 ) {
        context=contexta;

        register1object1x=register1object1;
        register1object2x=register1object2;
        register1object3x=register1object3;

            return new BottonSheetUplad();

        }



        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            vista= inflater.inflate(R.layout.layout_botton_sheetcd, container, false);
            progressBar =vista.findViewById(R.id.progressBar);
            txtAdviser =vista.findViewById(R.id.txtAdviser);
            CoordinatorLayout lineaLyaout =vista.findViewById(R.id.lineaLyaout);
            txtContadorArchivosPorSubir=vista.findViewById(R.id.txtContadorArchivosPorSubir);

            callbackContenedores= this;

            Log.i("lamundo","el size upload es "+allkeys.size());

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


    public void subePrimerValue



    @Override
    public void uploadInformRegister() {  //este lo llmaos desde el activity

        Handler handler1 = new Handler();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {//esto en BACGROUND
               // Contenedores
                RealtimeDB.addNewRegistroInforme2(register1object1x);   //en succes llamamos al  //uploadContenedoresPart1
                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        progressBar.setProgress(50); //esto en interfas
                    }
                });

            }
        });   //call it
        t.start();

    }

    @Override
    public void uploadContenedoresPart1() {

        Handler handler1 = new Handler();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {//esto en BACGROUND

                RealtimeDB.addNewRegistroInforme2(register1object2x);


                handler1.post(new Runnable() {
                    @Override
                    public void run() {

                        progressBar.setProgress(100); //esto en interfas
                    }
                });

            }
        });   //call it
        t.start();

    }

    @Override
    public void uploadContenedoresPart2() {

    }

    @Override
    public void uploadContenedoresPart3() {

    }

    @Override
    public void uploadsImages() {

    }

    @Override
    public void productosPostCosecha() {

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



}

