package com.tiburela.qsercom.dialog_fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formularios.ActivityCamionesyCarretas;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.CuadMuestreoCalibAndRechazPrev;
import com.tiburela.qsercom.activities.formulariosPrev.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.CheckedAndAtatch;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BottonSheetDfragmentVclds extends BottomSheetDialogFragment {
        public static final String TAG = "ActionBottomDialog";
        private View vista;
        Context context;
        static int idDondevinoActivity=0;
    ProgressDialog      progress;

    RecyclerView mirecyclerViewAtach;
    Spinner spinnerSelectrodate;
    TextView txtAdviseer;
    TextView txtAdviserDesvicunlar;
    ImageView imgClose;
    TextView txtNumReportsVinclds;
 boolean esReportsVinculadosMod=false;

   HashMap <String,ControlCalidad> mapControlCalidad= new HashMap<>();
   HashMap <String,CheckedAndAtatch> mapCheckedListForms = new HashMap<>();
   CuadroMuestreo cuadroMuestreoVinculado;

   ArrayList<String> idsOfReportsVincldsList = new ArrayList<>();




    public static BottonSheetDfragmentVclds newInstance(int idDondevinoActivityx) {
        idDondevinoActivity=idDondevinoActivityx;
           // formullarioSelect=tipoFormulario;
            return new BottonSheetDfragmentVclds();
        }
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            vista= inflater.inflate(R.layout.bottom_sheet_ver_atachx, container, false);


            mirecyclerViewAtach =vista.findViewById(R.id.mirecyclerViewAtach);
            spinnerSelectrodate =vista.findViewById(R.id.spinnerSelectrodate);

            txtAdviseer =vista.findViewById(R.id.txtAdviseer);
            txtAdviserDesvicunlar =vista.findViewById(R.id.txtAdviserDesvicunlar);
            imgClose=vista.findViewById(R.id.imgClose);
            txtNumReportsVinclds=vista.findViewById(R.id.txtNumReportsVinclds);

//        bundle.putString(Variables.KEY_CUADRO_MUETREO_ATACHED,RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);
//        bundle.putString(Variables.KEY_CONTROL_CALIDAD_ATACHEDS,RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
            RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado = getArguments().getString(Variables.KEY_CUADRO_MUETREO_ATACHED);
            RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString = getArguments().getString(Variables.KEY_CONTROL_CALIDAD_ATACHEDS);




            Log.i("viculados","idCudroMuestreoStringVinuclado es "+ RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);
            Log.i("viculados","idsFormsVinucladosControlCalidadString es "+ RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);



            // context = getActivity();
            Log.i("ontatch","se ejecuto onCreateView");

            listternSpinner();


            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //en la ac56ividad actualizamos elnume reportes vinculados y actualisamos el txt de rechzados y el iobjeto report parte 1 al;goasi


                    dismiss();


                }
            });


            RealtimeDB.initDatabasesRootOnly();



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
        public void onDetach() {
            super.onDetach();


        }





/*
        public interface ItemClickListener {
            void onItemClick(boolean esNEW);
        }


*/

    @Override
    public void onStart() {
        super.onStart();


        try {
            progress.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }




        Log.i("ontatch","se ejecuto onStart");

    }







private void listternSpinner(){


    spinnerSelectrodate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String selecionado=spinnerSelectrodate.getSelectedItem().toString();
            Calendar cal = Calendar.getInstance();

            Calendar cald2 = Calendar.getInstance();


            //   idsFormsControlCalidVinculados=generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idsFormsVinucladosCntres);

            if(selecionado.equalsIgnoreCase("Hoy")) {
                esReportsVinculadosMod=false;

                // long timeCurrent = new Date().getTime();
                cal.add(Calendar.DATE, -0);
                cald2.add(Calendar.DATE,0);

                dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());


            }

            else if(selecionado.equalsIgnoreCase("Ayer")) {
                esReportsVinculadosMod=false;


                cal.add(Calendar.DATE, -1);
                cald2.add(Calendar.DATE,0);

                dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());



            }





            else if(selecionado.equalsIgnoreCase("Ultimos 7 dias")) {

                Log.i("heurrr","es reportes 7 dias ");

                esReportsVinculadosMod=false;



                cal.add(Calendar.DATE, -7);
                cald2.add(Calendar.DATE,0);


                dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());



                //  dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado));



            }


            else if(selecionado.equalsIgnoreCase("Ultimos 15 dias")) {
                esReportsVinculadosMod=false;


                cal.add(Calendar.DATE, -15);
                cald2.add(Calendar.DATE,0);

                dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());


            }

            else if(selecionado.equalsIgnoreCase("Ultimos 30 dias")) {
                Log.i("heurrr","es reportes 30dias ");

                esReportsVinculadosMod=false;


                cal.add(Calendar.DATE, -30);
                cald2.add(Calendar.DATE,0);
                dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());

                //  dowloadinformesby_RangeDateAndCallAndCALLdOWLODCuadroMuestreo(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString),generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado));


            }


            else if(selecionado.equalsIgnoreCase("Reportes Vinculados")) {




                Log.i("viculados","se ejecuto reportes vinuclados ");


                esReportsVinculadosMod=true;

                Log.i("viculados","2 idCudroMuestreoStringVinuclado es "+ RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);
                Log.i("viculados","2 idsFormsVinucladosControlCalidadString es "+ RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);




                idsOfReportsVincldsList=  Utils.generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString, RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);



                Log.i("midusiod","sidee "+idsOfReportsVincldsList.size());


                if(idsOfReportsVincldsList.size()>0){
                    geOlyReportsVinuclados(idsOfReportsVincldsList);


                }else{

                    txtAdviseer.setText("No existe Ningun Reporte Vinculado");
                    txtAdviseer.setVisibility(TextView.VISIBLE);

                }




            }


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

}









    private void dowloadInformRegistrosByDateRange(long desdeFecha, long hastFecha){

        final boolean[] formEstaMarcadoComoVinclada = {false};

        mapCheckedListForms=new HashMap<>();//resetemaos cada vez que se llame este metodop

        Query query = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros").
                orderByChild("dateUploadByinspCampoIformeMillisecond").startAt(desdeFecha).endAt(hastFecha);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    InformRegister informRegister=ds.getValue(InformRegister.class);


                    //agregamos solo los que no esten en esta lista..
                    if(informRegister!=null){  //creamos un objeto

                                //CHEKEAMOS QUE EXISTA ESTE ID SI EXISTE


                        if(idsOfReportsVincldsList.contains(informRegister.getInformUniqueIdPertenece())){
                            formEstaMarcadoComoVinclada[0] =true;

                        }

                        else

                        {

                            formEstaMarcadoComoVinclada[0] =false;

                        }

                        //soplo cuadro muestreio Y CONTROLCALIDAD

                        if(informRegister.getTypeInform()==Constants.CONTROL_CALIDAD  || informRegister.getTypeInform()==Constants.CUADRO_MUESTRO_CAL_RECHZDS    ){

                            mapCheckedListForms.put(informRegister.getInformUniqueIdPertenece(),new CheckedAndAtatch(informRegister.getSimpleDateForm(),
                                    informRegister.getNombreQUienSubio(),   informRegister.getTypeReportString(),formEstaMarcadoComoVinclada[0],informRegister.getInformUniqueIdPertenece()) );




                        }




                    }


                }


                setDataInRecyclerOfBottomSheet(mapCheckedListForms);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });



    }







    private void geOlyReportsVinuclados(ArrayList<String>idsOfReportsVincldsListx){
        mapCheckedListForms=new HashMap<>();//resetemaos cada vez que se llame este metodop
        String currenTid = "";

        for(int indice=0;indice<idsOfReportsVincldsListx.size(); indice++){


             currenTid= idsOfReportsVincldsListx.get(indice);
            Query query = RealtimeDB.rootDatabaseReference.child("Registros").
                    child("InformesRegistros").
                    orderByChild("informUniqueIdPertenece").equalTo(currenTid);


            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {

                        InformRegister informRegister=ds.getValue(InformRegister.class);


                        //agregamos solo los que no esten en esta lista..
                        if(informRegister!=null){  //creamos un objeto

                            //CHEKEAMOS QUE EXISTA ESTE ID SI EXISTE

                            mapCheckedListForms.put(informRegister.getInformUniqueIdPertenece(),new CheckedAndAtatch(informRegister.getSimpleDateForm(),
                                    informRegister.getNombreQUienSubio(),   informRegister.getTypeReportString(),true,informRegister.getInformUniqueIdPertenece()) );


                        }




                    }



                     //conveertimos mapa en aay list..
                    setDataInRecyclerOfBottomSheet(mapCheckedListForms);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.i("sliexsa","el error es "+error.getMessage());

                }
            });



        }



    }


    private void setDataInRecyclerOfBottomSheet(HashMap<String,CheckedAndAtatch>mapa) {

        ///cobnvertimos mapaen aray lisr
        ArrayList<CheckedAndAtatch> lista = new ArrayList<>(mapa.values());


        if (esReportsVinculadosMod && lista.size() == 0) {
            txtAdviseer.setText("No hay Reportes Vinculados");

        }


        if (lista.size() == 0) {

            txtAdviseer.setVisibility(TextView.VISIBLE);
            txtAdviseer.setText("No hay Reportes en este periodo, selecione otro");

            txtAdviserDesvicunlar.setVisibility(TextView.GONE);
            //  btnSaveCambiosxxx.setVisibility(TextView.GONE);

            Log.i("samerr", "se ejeduto el if ");

        }

        else

        {
            Log.i("samerr", "se ejeduto el else ");
            txtAdviserDesvicunlar.setVisibility(TextView.VISIBLE);
            // btnSaveCambiosxxx.setVisibility(TextView.VISIBLE);

            txtAdviseer.setVisibility(TextView.GONE);
        }



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());


        RecyclerViewAdapLinkage adapter = new RecyclerViewAdapLinkage(getActivity(), lista);


        //  this.adapter.setPlayPauseClickListener(this);

        mirecyclerViewAtach.setLayoutManager(layoutManager);
        mirecyclerViewAtach.setAdapter(adapter);


        adapter.setOnItemClickListener(new RecyclerViewAdapLinkage.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                //mejor ponemos el key en un


                // seria un mapa
                Log.i("dbuhehjr","el tag selecionado es  es "+v.getTag(R.id.tagImgCategory).toString());


                if( v.getTag(R.id.tagImgCategory).toString().equals("CUADRO MUESTREO")){
                    Variables.idCuadroMuestreoToDowload=v.getTag(R.id.tagImgUniqueIdItem).toString();

                    Log.i("dbuhehjr","el id selecte  es "+v.getTag(R.id.tagImgUniqueIdItem));

                     showPRogressAndStartActivity(new Intent(getActivity(), CuadMuestreoCalibAndRechazPrev.class));


                }


                else

                {
                    Log.i("dbuhehjr","el id selecte es "+v.getTag(R.id.tagImgUniqueIdItem));


                    Variables.idControCalidadToDowload=v.getTag(R.id.tagImgUniqueIdItem).toString();

                    Log.i("cinuoados","el id preess es  "+ Variables.idControCalidadToDowload);



                    // showPRogressAndStartActivity(new Intent(ActivityContenedoresPrev.this, FormularioControlCalidadPreview.class));

                    Intent intencion= new Intent(getActivity(),FormularioControlCalidadPreview.class);
                    intencion.putExtra("ShowAtach",true);

               showPRogressAndStartActivity(intencion);


                }




            dismiss();

            }
        });

    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        Log.i("dbuhehjr","el user a dado en dismiss ");

    checkActivityDondeVinoAndCaLLmETHODofActivity(idDondevinoActivity);

        try {
            progress.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }


        super.onDismiss(dialog);

        //
//aqwuillmoasel metodo actualizarr

     //  checkActivityDondeVinoAndCaLLmETHODofActivity(idDondevinoActivity);

        // this works fine but fires one time too often for my use case, it fires on screen rotation as well, although this is a temporarily dismiss only
      ///  Toast.makeText(MainApp.get(), "DISMISSED", Toast.LENGTH_SHORT).show();
    }


    private void checkActivityDondeVinoAndCaLLmETHODofActivity(int idActivityDndeVino){
        switch(idActivityDndeVino){

            case Constants.CONTENEDORES:

                Log.i("derrd","sellamo here");

                ((ActivityContenedores)getActivity()).updateVinucladosObject();

                break;

            case Constants.PREV_CONTENEDORES:
                ((ActivityContenedoresPrev)getActivity()).updateVinucladosObject();

                break;


            case Constants.CAMIONES_Y_CARRETAS:
                ((ActivityCamionesyCarretas)getActivity()).updateVinucladosObject();

                break;



            case Constants.PREV_CAMIONES_Y_CARRETAS:
                ((PreviewCalidadCamionesyCarretas)getActivity()).updateVinucladosObject();

                break;




        }



    }

    private void showPRogressAndStartActivity(Intent i) {
              progress =new ProgressDialog(getActivity());

        progress.setMessage("Cargando Formulario....");
        // progress.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
        progress.setIndeterminate(true);
        progress.show();


        new Thread ( new Runnable()
        {
            public void run()
            {

                startActivity(i);
                // finish();
                // progress.dismiss();


            }
        }).start();



        @SuppressLint("HandlerLeak") Handler progressHandler = new Handler()
        {

            public void handleMessage(Message msg1)
            {

                progress.dismiss();

              //  dismiss();
            }
        };


    }



}

