package com.tiburela.qsercom.activities.othersActivits;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formularios.ActivityContenedores;
import com.tiburela.qsercom.activities.formularios.ActivityContersEnAcopio;
import com.tiburela.qsercom.activities.formularios.ActivityControlCalidad;
import com.tiburela.qsercom.activities.formularios.ActivityCuadMuestCalibAndRechaz;
import com.tiburela.qsercom.activities.formularios.ActivityPackingList;
import com.tiburela.qsercom.activities.formularios.ActivityReporteCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.CuadMuestreoCalibAndRechazPrev;
import com.tiburela.qsercom.activities.formulariosPrev.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.formulariosPrev.PackingListPreviewActivity;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewsFormDatSContersEnAc;
import com.tiburela.qsercom.adapters.AdapterAllReports;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.adapters.RecyclerVAdapterReportsList;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.ReportsAllModel;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.utils.Variables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivitySeeReportsOffline extends AppCompatActivity  implements   View.OnTouchListener{
    RecyclerView recyclerVReports;
    Spinner  spinnerDatesSelector;
    ProgressDialog pd;
    TextView txtConfirmExitenciaData ;
    ArrayList<InformRegister> listReportCurrents = new ArrayList<>();
    private ProgressDialog progress;
    public final int CONTENEDORES=1200;
    HashMap<String, ReportsAllModel> allReportFiltBMap=new HashMap<>();
    Map<String,  InformRegister> mapAllReportsRegister =new HashMap<>();
    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_reports);

        context = getApplicationContext();

        findViewsIDs();

    }




    @Override
    protected void onStart() {
        super.onStart();

        mapAllReportsRegister = SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

        listenenrSpinner();


        try {

        progress.dismiss();


            if(Variables.VienedePreview) {
                pd.dismiss();
                Variables.VienedePreview =false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void findViewsIDs() {

        recyclerVReports=findViewById(R.id.recyclerVReports);
        spinnerDatesSelector=findViewById(R.id.spinnerDatesSelector);
        txtConfirmExitenciaData=findViewById(R.id.txtConfirmExitenciaData);


    }


    void listenenrSpinner() {


        spinnerDatesSelector .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String timeSelecionado= spinnerDatesSelector.getSelectedItem().toString();
                String fechaToSearch="";



                //  ediZona.setText("Zona "+zonaEelejida+" ");
                if(timeSelecionado.equals("HOY")){

                    fechaToSearch=generaFechaToSearch(Variables.HOY);

                    getReportLocalStorageBYdate(fechaToSearch);
                   // dowloadinformesby_CONTENEDORES(fechaToSearch);
                    //dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);

                    Log.d("dateis ","el dat today is "+fechaToSearch) ;


                }
                else if (timeSelecionado.equals("AYER")){

                    fechaToSearch=generaFechaToSearch(Variables.AYER);
                    getReportLocalStorageBYdate(fechaToSearch);

                    // dowloadinformesby_CONTENEDORES(fechaToSearch);
                    // dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);


                }

                else if (timeSelecionado.equals("ANTEAYER")){

                    fechaToSearch=generaFechaToSearch(Variables.ANTEAYER);
                    getReportLocalStorageBYdate(fechaToSearch);

                    //dowloadinformesby_CONTENEDORES(fechaToSearch);
                    //dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);

                    // Log.i("sumares","la fecha to search es "+fechaToSearch);


                }

                else if (timeSelecionado.equals("FECHA ESPECIFICA")){

                    selecionaFecha();

                }





            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("dateis ","nothin selected") ;

            }
        });




    }

















    void setAdapaterDataAndShow() {


        if (listReportCurrents.size()>0 )
        {txtConfirmExitenciaData.setVisibility(TextView.INVISIBLE);

        }
        else
        {txtConfirmExitenciaData.setVisibility(TextView.VISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivitySeeReportsOffline.this);


        //convertimos este hasmape


        AdapterAllReports adapter = new AdapterAllReports( listReportCurrents, ActivitySeeReportsOffline.this);

        recyclerVReports.setLayoutManager(layoutManager);

        recyclerVReports.setAdapter(adapter);


        eventoBtnclicklistener(adapter);



    }



    void setAdapaterDataAndShow(  HashMap<String, ReportsAllModel> reports ) {


        if (reports.size()>0 )
        {txtConfirmExitenciaData.setVisibility(TextView.INVISIBLE);

        }
        else
        {txtConfirmExitenciaData.setVisibility(TextView.VISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivitySeeReportsOffline.this);


        //convertimos este hasmape


        RecyclerVAdapterReportsList  adapter = new RecyclerVAdapterReportsList( createAarrayListBYmap(reports), ActivitySeeReportsOffline.this);

        recyclerVReports.setLayoutManager(layoutManager);

        recyclerVReports.setAdapter(adapter);


        eventoBtnclicklistener(adapter);



    }



    private  ArrayList<ReportsAllModel>  createAarrayListBYmap(
            HashMap<String, ReportsAllModel>map){
        ArrayList<ReportsAllModel>miList=new ArrayList<>();

        for(ReportsAllModel report: map.values()){

            miList.add(report);


        }



        return miList;

    }


    private void eventoBtnclicklistener(AdapterAllReports adapter) {

        adapter.setOnItemClickListener(new AdapterAllReports.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {  //este para eminar

                // Variables.CurrenReportPart1=  reportsListPart1.get(position);

              //  Variables.currentInformRegisterSelected=listReportCurrents.get(position);


                showBottomSheetDialog(listReportCurrents.get(position).getTypeInform(), listReportCurrents.get(position).getInformUniqueIdPertenece());


             //   Log.i("midaclick","el click es here, el informe es "+ allReportFiltBMap.get(String.valueOf(v.getTag())).getIdInforme());


            }

        });
    }






    private void eventoBtnclicklistener(RecyclerVAdapterReportsList adapter) {

        adapter.setOnItemClickListener(new RecyclerVAdapterReportsList.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {  //este para eminar

                // Variables.CurrenReportPart1=  reportsListPart1.get(position);

                showBottomSheetDialog(allReportFiltBMap.get(String.valueOf(v.getTag())).getReporteTipo(), allReportFiltBMap.get(String.valueOf(v.getTag())).getIdInforme());


                Log.i("midaclick","el click es here, el informe es "+ allReportFiltBMap.get(String.valueOf(v.getTag())).getIdInforme());


            }

        });
    }



    private void DowloadReportPart1(String uniqeuIDiNFORME,int modoReporte){

        //to fetch all the users of firebase Auth app
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("listInformes");

        Query query = usersdRef.orderByChild("uniqueIDinforme").equalTo(uniqeuIDiNFORME);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                //  String key = nodeShot.getKey();



                for (DataSnapshot ds : snapshot.getChildren()) {
                    SetInformEmbarque1 currentObect= ds.getValue(SetInformEmbarque1.class);

                    if(currentObect!=null){
                        Variables.CurrenReportPart1=currentObect;

                        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString =Variables.CurrenReportPart1.getAtachControCalidadInfrms();
                        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado =Variables.CurrenReportPart1.getAtachControCuadroMuestreo();


                        break;

                    }



                }


                Intent intencion= new Intent(ActivitySeeReportsOffline.this, ActivityContenedoresPrev.class);


                if(modoReporte==Variables.MODO_EDICION ){

                    intencion.putExtra(Variables.KEYEXTRAPREVIEW,true);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    // startActivity(intencion);

                    showPRogress(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEYEXTRAPREVIEW,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");


                    // startActivity(intencion);
                    // pdialogff.dismiss();
                    // finish();
                    showPRogress(intencion);



                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("isclkiel","el error es  "+ error.getMessage());


            }
        } );




    }

    private void DowloadReportCamionesYcarretas(String uniqeuIDiNFORME,int modoReporte){

        //to fetch all the users of firebase Auth app
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("informeCamionesYcarretas");

        Query query = usersdRef.orderByChild("uniqueIDinforme").equalTo(uniqeuIDiNFORME);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    ReportCamionesyCarretas informe=ds.getValue(ReportCamionesyCarretas.class);

                    if(informe!=null){
                        Variables.currenReportCamionesyCarretas=informe;
                        Log.i("elcarreta","el value es "+ Variables.currenReportCamionesyCarretas.get_nguia_transporte());

                        break;

                    }


                }

                Intent intencion= new Intent(ActivitySeeReportsOffline.this, PreviewCalidadCamionesyCarretas.class);


                if(modoReporte==Variables.MODO_EDICION ){

                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                   // startActivity(intencion);
                    showPRogress(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                  //  startActivity(intencion);
                    // pdialogff.dismiss();
                    // finish();
                    showPRogress(intencion);



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());


            }
        } );




    }



    private void DowloadEspecificReportCalbAndRechazados(String uniqeuIDiNFORME,int modoReporte){
        Log.i("hsmpadat","descrgamos este value  es svc");




        //to fetch all the users of firebase Auth app
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(uniqeuIDiNFORME);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe=ds.getValue(CuadroMuestreo.class);

                    if(informe!=null){
                        Variables.currentcuadroMuestreo=informe;
                     //   Log.i("hsmpadat","es nulo");

                        Log.i("hsmpadat","el value es "+ Variables.currentcuadroMuestreo.getProductor());

                        break;

                    }


                }


              //  Log.i("hsmpadat","el value es aqui es  "+ Variables.currentcuadroMuestreo.getProductor());



                Intent intencion= new Intent(ActivitySeeReportsOffline.this, CuadMuestreoCalibAndRechazPrev.class);


                if(modoReporte==Variables.MODO_EDICION ){

                  //  intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    // startActivity(intencion);
                    showPRogress(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                   // intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    //  startActivity(intencion);
                    // pdialogff.dismiss();
                    // finish();
                    showPRogress(intencion);



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());


            }
        } );




    }




    private void DowloadReportContersAcopio(String uniqeuIDiNFORME,int modoReporte){ //para informe contenedores

        //to fetch all the users of firebase Auth app
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("contenedoresAcopio");

        Query query = usersdRef.orderByChild("uniqueIDinforme").equalTo(uniqeuIDiNFORME);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    ContenedoresEnAcopio informe=ds.getValue(ContenedoresEnAcopio.class);

                    if(informe!=null){
                        Variables.CurrenReportContensEnACp=informe;
                        Log.i("verdura","el value es "+ Variables.CurrenReportContensEnACp.getAgenciaNaviera());

                        break;

                    }


                }

                Intent intencion= new Intent(ActivitySeeReportsOffline.this, PreviewsFormDatSContersEnAc.class);


                if(modoReporte==Variables.MODO_EDICION ){

                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                   // startActivity(intencion);
                    showPRogress(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                  //  startActivity(intencion);
                    // pdialogff.dismiss();
                    // finish();

                    showPRogress(intencion);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());


            }
        } );




    }




    private void DowloadPackingList(String uniqeuIDiNFORME,int modoReporte){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("PackingListDescripcion");

        Query query = usersdRef.orderByChild("uniqueIDinforme").equalTo(uniqeuIDiNFORME);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    PackingListMod informe=ds.getValue(PackingListMod.class);

                    if(informe!=null){
                        Variables.currenReportPackinList=informe;
                        Log.i("verdura","el value es "+ Variables.currenReportPackinList.getKeyOrNodeContainsMapPli());

                        break;

                    }


                }

                Intent intencion= new Intent(ActivitySeeReportsOffline.this, PackingListPreviewActivity.class);


                if(modoReporte==Variables.MODO_EDICION ){

                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);

                    Log.i("verdura","ahora se llamo intent");

                   // startActivity(intencion);

                    showPRogress(intencion);


                }else{


                    intencion.putExtra(Variables.KEY_PACKING_LIST,false);

                    Log.i("verdura","ahora se llamo intent");

                  //  startActivity(intencion);

                    showPRogress(intencion);


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());


            }
        } );




    }



    private void DowloadControlCalidad(String uniqueId,int modoReporte){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("listControCalidad");

        Query query = usersdRef.orderByChild("uniqueId").equalTo(uniqueId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    ControlCalidad informe=ds.getValue(ControlCalidad.class);

                    if(informe!=null){

                        Variables.currenControlCalReport=informe;

                        //  Log.i("verdura","el value es "+ currenControlCalReport);

                        break;

                    }


                }

                Intent intencion= new Intent(ActivitySeeReportsOffline.this, FormularioControlCalidadPreview.class);


                if(modoReporte==Variables.MODO_EDICION ){

                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);
                    //si queremos deciion le ponemos true;
                    Log.i("csamirrs","se llamo ciotnrocalida");

                 //   startActivity(intencion);

                    showPRogress(intencion);


                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEY_PACKING_LIST,false);
                    //si queremos deciion le ponemos true;
                    Log.i("csamirrs","ahora se llamo intent");

                    showPRogress(intencion);


                  //  startActivity(intencion);
                    // pdialogff.dismiss();
                    // finish();



                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());


            }
        } );




    }



    String  generaFechaToSearch( int dateId) {
        String fecha="";
        if    (dateId == Variables.HOY) {

            fecha= toDate(0);



        }else if(dateId == Variables.AYER){
            fecha= toDate(1);



        }else if(dateId== Variables.ANTEAYER ){
            fecha= toDate(2);

        }

        else if(dateId== Variables.ESPECIFIC_DATE ){



        }



        return fecha;

    }

    private String toDate(int dias) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        // Create a calendar object with today date. Calendar is in java.util pakage.
        Calendar calendar = Calendar.getInstance();

        // Move calendar to yesterday
        calendar.add(Calendar.DATE, -dias);  // -1 para el dia de ayer

        // Get current date of calendar which point to the yesterday now
        Date yesterday = calendar.getTime();

        return dateFormat.format(yesterday).toString();
    }




    private void showBottomSheetDialog(int reportTipo,String idReport) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivitySeeReportsOffline.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_edit_cpn);

        LinearLayout lyRevisar = bottomSheetDialog.findViewById(R.id.lyhh);


Log.i("puslado","el value es "+idReport);

        lyRevisar.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {



                if(reportTipo== Constants.CONTENEDORES){

                    Intent intencion=new Intent(ActivitySeeReportsOffline.this, ActivityContenedores.class);
                    intencion.putExtra(Variables.KEY_FORM_EXTRA,idReport);

                    startActivity(intencion);


                }

                else if(reportTipo==Constants.CONTENEDORES_EN_ACOPIO){

                    Intent intencion=new Intent(ActivitySeeReportsOffline.this, ActivityContersEnAcopio.class);
                    intencion.putExtra(Variables.KEY_FORM_EXTRA,idReport);

                    startActivity(intencion);

                }

                else if(reportTipo==Constants.CAMIONES_Y_CARRETAS){

                    Intent intencion=new Intent(ActivitySeeReportsOffline.this, ActivityReporteCalidadCamionesyCarretas.class);
                    intencion.putExtra(Variables.KEY_FORM_EXTRA,idReport);

                    startActivity(intencion);

                }


                else if(reportTipo==Constants.PACKING_lIST){

                    Intent intencion=new Intent(ActivitySeeReportsOffline.this, ActivityPackingList.class);
                    intencion.putExtra(Variables.KEY_FORM_EXTRA,idReport);

                    startActivity(intencion);

                }

                else if(reportTipo==Constants.CONTROL_CALIDAD){

                    Intent intencion=new Intent(ActivitySeeReportsOffline.this, ActivityControlCalidad.class);
                    intencion.putExtra(Variables.KEY_FORM_EXTRA,idReport);

                    startActivity(intencion);
                }

                else if(reportTipo==Constants.CUADRO_MUESTRO_CAL_RECHZDS){

                    Intent intencion=new Intent(ActivitySeeReportsOffline.this, ActivityCuadMuestCalibAndRechaz.class);
                    intencion.putExtra(Variables.KEY_FORM_EXTRA,idReport);

                    startActivity(intencion);


                }




                bottomSheetDialog.dismiss();


            }
        });




        bottomSheetDialog.show();
    }


    private void showPRogress(Intent i) {
        progress =new ProgressDialog(ActivitySeeReportsOffline.this);

        progress.setMessage("Cargando Datos....");
       // progress.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
       progress.setIndeterminate(true);
        progress.show();


        new Thread ( new Runnable()
        {
            public void run()
            {
                  startActivity(i);
                  finish();

               // progress.dismiss();

            }
        }).start();

        @SuppressLint("HandlerLeak") Handler progressHandler = new Handler()
        {

            public void handleMessage(Message msg1)
            {

                progress.dismiss();
            }
        };


}

    void selecionaFecha( ){

        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog picker = new DatePickerDialog(ActivitySeeReportsOffline.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                     //   ediFecha.setText(daySemana+"/"+mes+"/"+year);

                        Log.i("datesdf","el date picker 1 es "+i+" "+i1+" "+i2);

                        //02-10-2022

                        //convertimos a mili

                        String dia =String.valueOf(i2);
                        String mes =String.valueOf(i1);
                         i1++;

                        Log.i("sumares","el mes es :  "+i1);


                        if(i2<10){ //dia es menor a 10
                            dia = "0"+i2;

                        }
                        else{
                            dia =  String.valueOf(i2);

                        }


                        if(i1<10){ //mes
                            mes = "0"+i1;

                        }else{
                            mes =  String.valueOf(i1);

                        }



                        String fechaToSearch= dia+"-"+mes+"-"+i;

                        Log.i("sumares","el date picker to search es "+fechaToSearch);


                        getReportLocalStorageBYdate(fechaToSearch);


                      //  dowloadinformesby_CONTENEDORES(fechaToSearch);


                    }
                }, year,  mes, daySemana);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);


        picker.show();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }







    void getReportLocalStorageBYdate(String dateSelecionado){

  /***recuerd  buscamos by date iterando el hasmpa si contiene esta faecha agregarlo al listReportCurrents list *********/
        //crear una variable global para la key que contedra siemore los all reporest offline ...
        //crear un metodo que guarde el mapa con objetos  InformRegister ,,, tierra fettil parec ontner algun metodo...
        //y con eso ya lo tiene 30 minutos maximo
        // aqui emvairle un intent con el key cuando el usuario selecione los items y dirigja a contenedor....
        ///y asi parece facil
       //posiblemente obtengamos el mapa otra vez aqui


        listReportCurrents = new ArrayList<>();

          for(InformRegister objecCurrent : mapAllReportsRegister.values()){

          if(objecCurrent.getSimpleDateForm().equals(dateSelecionado)){

                  listReportCurrents.add(objecCurrent);
          }

          }



        setAdapaterDataAndShow();


           //en caso que no halla nada en este periodo..




    }




}