package com.tiburela.qsercom.activities.othersActivits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.CuadMuestreoCalibAndRechazPrev;
import com.tiburela.qsercom.activities.formulariosPrev.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.formulariosPrev.PackingListPreviewActivity;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewsFormDatSContersEnAc;
import com.tiburela.qsercom.adapters.AdapterAllReports;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.adapters.RecyclerVAdapterReportsList;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.ReportsAllModel;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import org.slf4j.helpers.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ActivitySeeReports extends AppCompatActivity  implements   View.OnTouchListener{
    RecyclerView recyclerVReports;
    Spinner  spinnerDatesSelector;

    Spinner spinnerExportadoras;
    private String timeselecionadoSpn="";
    private String exportadoraSelecionadaSpn="";


    ArrayList<SetInformEmbarque1> reportsListPart1;
    ProgressDialog pd;
    DatabaseReference rootDatabaseReference ; //anterior
    TextView txtConfirmExitenciaData ;
    ArrayList<InformRegister> listReport= new ArrayList<>();
    private ProgressDialog progress;

    public final int REPORTE_CONTENEDORES_EN_HCDA=1100;
    public final int CONTENEDORES=1200;
    public final int PACKINGLIST=1201;
    public final int OTRO_REPORTE=1202;
    public final int REPORTE_CONTENEDORES_EN_ACOPIO=1203;
    public final int REPORTE_CAMIONES_y_CARRETAS=1204;
    public final int REPORT_CONTROL_CALIDAD=1205;
    public final int MUESTREO_CALIBRA_RECHAZ=1269;
    Calendar cal = Calendar.getInstance();
    Calendar cald2= Calendar.getInstance();


    //cal = Calendar.getInstance();
    //  cald2=Calendar.getInstance();


    HashMap<String, ReportsAllModel> allReportFiltBMap=new HashMap<>();


    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_reports);

        //Auth.initAuth(this);
        context = getApplicationContext();

        findViewsIDs();
        getExportadorasAndSetSpinner();
        Variables.listImagesToDelete=new ArrayList<String>();

        RealtimeDB.initDatabasesRootOnly();

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference();




    }

    @Override
    protected void onStart() {
        super.onStart();
        listenenrSpinner();

       // fechaToSearch=generaFechaToSearch(Variables.HOY);

      //  dowloadinformesby_EspecificDate();


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
        spinnerExportadoras=findViewById(R.id.spinnerExportadoras);

    }


    void listenenrSpinner() {
        spinnerExportadoras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                exportadoraSelecionadaSpn=spinnerExportadoras.getSelectedItem().toString();

                Log.i("itemselected","el item seecioando es "+exportadoraSelecionadaSpn);




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerDatesSelector .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String timeSelecionado= spinnerDatesSelector.getSelectedItem().toString();
                String fechaToSearch="";

                 timeselecionadoSpn=spinnerDatesSelector.getSelectedItem().toString();


                //  ediZona.setText("Zona "+zonaEelejida+" ");
                if(timeSelecionado.equals("HOY")){

                    fechaToSearch=generaFechaToSearch(Variables.HOY);

                    dowloadinformesby_EspecificDate(fechaToSearch);
                   // dowloadinformesby_CONTENEDORES(fechaToSearch);
                    //dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);

                    Log.d("dateis ","el dat today is "+fechaToSearch) ;


                }
                else if (timeSelecionado.equals("AYER")){

                    fechaToSearch=generaFechaToSearch(Variables.AYER);
                    dowloadinformesby_EspecificDate(fechaToSearch);

                    // dowloadinformesby_CONTENEDORES(fechaToSearch);
                    // dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);


                }

                else if (timeSelecionado.equals("ANTEAYER")){

                    fechaToSearch=generaFechaToSearch(Variables.ANTEAYER);
                    dowloadinformesby_EspecificDate(fechaToSearch);

                    //dowloadinformesby_CONTENEDORES(fechaToSearch);
                    //dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);

                    // Log.i("sumares","la fecha to search es "+fechaToSearch);


                }

                else if (timeSelecionado.equals("FECHA ESPECIFICA")){

                    selecionaFecha();

                }


                else if (timeSelecionado.equals("ULTIMOS 7 DIAS")){

                   cal.add(Calendar.DATE, -7);
                   cald2.add(Calendar.DATE,0);
                    dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());


                }



                else if (timeSelecionado.equals("ULTIMOS 15 DIAS")){

                    cal.add(Calendar.DATE, -15);
                    cald2.add(Calendar.DATE,0);
                    dowloadInformRegistrosByDateRange(cal.getTimeInMillis(),cald2.getTimeInMillis());

                   // selecionaFecha();

                }






            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.d("dateis ","nothin selected") ;

            }
        });




    }



    private void dowloadInformRegistrosByDateRange(long desdeFecha, long hastFecha){

        Query query = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros").
                orderByChild("dateUploadByinspCampoIformeMillisecond").startAt(desdeFecha).endAt(hastFecha);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listReport= new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    InformRegister informRegister=ds.getValue(InformRegister.class);

                    //agregamos solo los que no esten en esta lista..
                    if(informRegister!=null){  //creamos un objet

                        Log.i("filtrodata","el exportadora selecionada es"+exportadoraSelecionadaSpn);

                        Log.i("filtrodata","el tipo de usaurio es "+ Variables.usuarioQserconGlobal.getTiposUSUARI());


                        if(Variables.usuarioQserconGlobal!=null &&  Variables.usuarioQserconGlobal.getTiposUSUARI()== Utils.INSPECTOR_OFICINA){

                            if(exportadoraSelecionadaSpn.equalsIgnoreCase("TODAS")){ //si le dio en todos
                                listReport.add(informRegister);


                            }else{  //ES UNS EXPORTRADORA ESPFICIA
                                if(informRegister.getExportadoraName().equals(exportadoraSelecionadaSpn)){
                                    listReport.add(informRegister);

                                }


                            }


                        }



                        else if(Variables.usuarioQserconGlobal!=null &&  Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_CAMPO){

                            if(exportadoraSelecionadaSpn.equalsIgnoreCase("TODAS")){ //si le dio en todos

                               if(informRegister.getIdQuienSUbioForm().equals(Variables.usuarioQserconGlobal.getUniqueIDuser())){
                                   listReport.add(informRegister);

                               }


                            }else{  //ES UNS EXPORTRADORA ESPFICIA
                                if(informRegister.getIdQuienSUbioForm().equals(exportadoraSelecionadaSpn)){
                                    if(informRegister.getIdQuienSUbioForm().equals(Variables.usuarioQserconGlobal.getUniqueIDuser())){
                                        listReport.add(informRegister);

                                    }

                                }


                            }


                        }




                    }


                }


                setAdapaterDataAndShow(listReport);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });



    }



    void dowloadinformesby_CONTENEDORES(String dateSelecionado){

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("listInformes").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        //reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque1 informEmbarque1=ds.getValue(SetInformEmbarque1.class);

                    //  reportsListPart1.add(informEmbarque1);


                    ReportsAllModel objec= new ReportsAllModel(CONTENEDORES,false,false,false,"Contenedores"
                            , informEmbarque1.getSimpleDataFormat(),informEmbarque1.getUniqueIDinforme());


                    allReportFiltBMap.put(informEmbarque1.getUniqueIDinforme(),objec);

                }


                dowloadinformesby_CONTENEDORES_EN_ACOPIO(dateSelecionado);
                Log.i("sellamos","se llamo dowload more info data ");
                //setAdapaterDataAndShow(reportsListPart1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    void dowloadinformesby_CONTENEDORES_EN_ACOPIO(String dateSelecionado){
        Log.i("sliexsa","el date selecionado es l es  "+dateSelecionado);


        Log.i("sliexsa","el size de lista here call es  "+ allReportFiltBMap.size());

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("contenedoresAcopio").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        //    reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ContenedoresEnAcopio contenedoresEnAcopio=ds.getValue(ContenedoresEnAcopio.class);

                    //  reportsListPart1.add(informEmbarque1);

                    ReportsAllModel objec=new ReportsAllModel(REPORTE_CONTENEDORES_EN_ACOPIO,false,false,false,"Contenedores Acopio"
                            , contenedoresEnAcopio.getSimpleDataFormat(),contenedoresEnAcopio.getUniqueIDinforme());




                            allReportFiltBMap.put(contenedoresEnAcopio.getUniqueIDinforme(),objec);

                }

                Log.i("sliexsa","el size de lista 222es  "+ allReportFiltBMap.size());
                dowloadinformesby_PACKE_lIST(dateSelecionado);
                //setAdapaterDataAndShow(reportsListPart1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    void dowloadinformesby_PACKE_lIST(String dateSelecionado){
        Log.i("sliexsa","el date selecionado es l es  "+dateSelecionado);


        Log.i("sliexsa","el size de lista here call es  "+ allReportFiltBMap.size());

        //        DatabaseReference mibasedata = rootDatabaseReference.child("Informes").child("PackingListDescripcion");

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("PackingListDescripcion").orderByChild("simpledatFormt").equalTo(dateSelecionado);
        //    reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    PackingListMod packinList=ds.getValue(PackingListMod.class);

                    //  reportsListPart1.add(informEmbarque1);

                    ReportsAllModel object=new ReportsAllModel(PACKINGLIST,false,false,false,"Packing List"
                            , packinList.getSimpledatFormt(),packinList.getUniqueIDinforme());



                    allReportFiltBMap.put(packinList.getUniqueIDinforme(),object);

                }


                Log.i("sliexsa","el size de lista 222es  "+ allReportFiltBMap.size());
                dowloadinformesby_CAMIONES_Y_CARRETAS(dateSelecionado);

                //setAdapaterDataAndShow(reportsListPart1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    void dowloadinformesby_CAMIONES_Y_CARRETAS(String dateSelecionado){
        Log.i("sliexsa","el date selecionado es l es  "+dateSelecionado);


        Log.i("sliexsa","el size de lista here call es  "+ allReportFiltBMap.size());

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("informeCamionesYcarretas").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        //    reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ContenedoresEnAcopio contenedoresEnAcopio=ds.getValue(ContenedoresEnAcopio.class);

                    //  reportsListPart1.add(informEmbarque1);


                    ReportsAllModel object=new ReportsAllModel(REPORTE_CAMIONES_y_CARRETAS,false,false,false,"Camiones Y Carretas"
                            , contenedoresEnAcopio.getSimpleDataFormat(),contenedoresEnAcopio.getUniqueIDinforme());



                    allReportFiltBMap.put(contenedoresEnAcopio.getUniqueIDinforme(),object);

                }

                Log.i("sliexsa","el size de lista 222es  "+ allReportFiltBMap.size());
                ///  dowloadinformesby_PACKE_lIST(dateSelecionado);
                //setAdapaterDataAndShow(reportsListPart1);

                dowloadinformesby_ControlCalidad(dateSelecionado);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }


    void dowloadinformesby_ControlCalidad(String dateSelecionado){
        /*   DatabaseReference usersdRef = rootRef.child("Informes").child("listControCalidad");
        Query query = usersdRef.orderByChild("uniqueId").equalTo(uniqueId);*/
        Log.i("sliexsa","el date selecionado es l es  "+dateSelecionado);
        Log.i("sliexsa","el size de lista here call es  "+ allReportFiltBMap.size());


        Query query = rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ControlCalidad controlcalidad=ds.getValue(ControlCalidad.class);

                    ReportsAllModel objec =  new ReportsAllModel(REPORT_CONTROL_CALIDAD,false,false,false,"Control Calidad",
                            controlcalidad.getSimpleDate(),controlcalidad.getUniqueId());

                    allReportFiltBMap.put(controlcalidad.getUniqueId(),objec);



                }

                Log.i("sliexsa","el size de lista 222es  "+ allReportFiltBMap.size());
                ///  dowloadinformesby_PACKE_lIST(dateSelecionado);
                //setAdapaterDataAndShow(reportsListPart1);
                dowloadinformesby_CuadroMuestreoCalib(dateSelecionado);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }


    void dowloadinformesby_CuadroMuestreoCalib(String dateSelecionado){

        Query query = rootDatabaseReference.child("Informes").child("CuadrosMuestreo").orderByChild("simpleDateFormat").equalTo(dateSelecionado);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    CuadroMuestreo controlcalidad=ds.getValue(CuadroMuestreo.class);

                    ReportsAllModel report = new ReportsAllModel(MUESTREO_CALIBRA_RECHAZ,false,false,false,"Cuadro Muestreo",
                            controlcalidad.getSimpleDateFormat(),controlcalidad.getUniqueIdObject());


                    allReportFiltBMap.put(controlcalidad.getUniqueIdObject(),report);



                }

                Log.i("muestrodff","el size de lista 222es  "+ allReportFiltBMap.size());
                ///  dowloadinformesby_PACKE_lIST(dateSelecionado);
                //setAdapaterDataAndShow(reportsListPart1);


                setAdapaterDataAndShow(allReportFiltBMap);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    void setAdapaterDataAndShow(ArrayList <InformRegister> reports) {


        if (reports.size()>0 )
        {txtConfirmExitenciaData.setVisibility(TextView.INVISIBLE);

        }
        else
        {txtConfirmExitenciaData.setVisibility(TextView.VISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivitySeeReports.this);


        //convertimos este hasmape


        AdapterAllReports adapter = new AdapterAllReports( reports,ActivitySeeReports.this);

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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivitySeeReports.this);
        //convertimos este hasmap...




        RecyclerVAdapterReportsList  adapter = new RecyclerVAdapterReportsList( createAarrayListBYmap(reports),ActivitySeeReports.this);

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

                Log.i("dateis","equi hay un click  ");


                Variables.currentInformRegisterSelected=listReport.get(position);


                showBottomSheetDialog(listReport.get(position).getTypeInform(), listReport.get(position).getInformUniqueIdPertenece());


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


                Intent intencion= new Intent(ActivitySeeReports.this, ActivityContenedoresPrev.class);


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

                Intent intencion= new Intent(ActivitySeeReports.this, PreviewCalidadCamionesyCarretas.class);


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



                Intent intencion= new Intent(ActivitySeeReports.this, CuadMuestreoCalibAndRechazPrev.class);


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

        Log.i("dateis","vamos a descargar  contenedores en acopio");


        //to fetch all the users of firebase Auth app
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("contenedoresAcopio");

        Query query = usersdRef.orderByChild("uniqueIDinforme").equalTo(uniqeuIDiNFORME);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ContenedoresEnAcopio informe=null;
                for (DataSnapshot ds : snapshot.getChildren()) {

                    informe =ds.getValue(ContenedoresEnAcopio.class);

                    if(informe!=null){
                        Variables.CurrenReportContensEnACp=informe;
                        Log.i("verididjf","el value es "+ Variables.CurrenReportContensEnACp.getAgenciaNaviera());


                        Log.i("dateis","tenemos un contenedores en acopio");

                        break;

                    }


                }

                Intent intencion= new Intent(ActivitySeeReports.this, PreviewsFormDatSContersEnAc.class);

                if(modoReporte==Variables.MODO_EDICION ){


                         if( informe !=null){
                             intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);
                             //si queremos deciion le ponemos true;
                             Log.i("verididjf","ahora se llamo intent");

                             // startActivity(intencion);
                             showPRogress(intencion);  //activar

                         }



                    else{

                        Toast.makeText(ActivitySeeReports.this, "Lo siento hay un problema con el informe", Toast.LENGTH_SHORT).show();


                    }
                    //pdialogff.dismiss();

                    //finish();
                }else{
                    if(informe!=null){



                        intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,false);
                        //si queremos deciion le ponemos true;
                        Log.i("verididjf","ahora se llamo intent");

                        //  startActivity(intencion);
                        // pdialogff.dismiss();
                        // finish();
                        Log.i("dateis","ahora se llamo intent");

                        showPRogress(intencion);  //activar
                    }else{

                        Toast.makeText(ActivitySeeReports.this, "Lo siento hay un problema con el informe", Toast.LENGTH_SHORT).show();

                    }




                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



                Log.i("dateis","el error es  "+ error.getMessage());


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

                Intent intencion= new Intent(ActivitySeeReports.this, PackingListPreviewActivity.class);


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

                Intent intencion= new Intent(ActivitySeeReports.this, FormularioControlCalidadPreview.class);


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








    void dowloadSecondPART_Report(String reportUNIQUEidtoSEARCH, int modo){ //DESCRAGAMOS EL SEGUNDO REPORTE
        pd = new ProgressDialog(ActivitySeeReports.this);
        pd.setMessage("Obteniendo Data");
        pd.show();

        Log.i("secondInform","el curren report id es "+reportUNIQUEidtoSEARCH);



        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinformePart2").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque2 informEmbarque2= ds.getValue(SetInformEmbarque2.class);

                    if(informEmbarque2!=null){
                        Variables.CurrenReportPart2=informEmbarque2;
                        break;
                    }
                }
                Log.i("secondInform","el id del secon resport es "+Variables.CurrenReportPart2.getUniqueIDinformePart2());
                Log.i("secondInform","el CANDAO ES "+Variables.CurrenReportPart2.getCandadoQsercom());


                dowloadThirdReportAngoActivity(reportUNIQUEidtoSEARCH,modo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    void dowloadThirdReportAngoActivity(String reportUNIQUEidtoSEARCH,int modo){ //DESCRAGAMOS EL SEGUNDO REPORTE
        pd = new ProgressDialog(ActivitySeeReports.this);
        pd.setMessage("Obteniendo Data");
        pd.show();

        Log.i("secondInform","el curren report id es "+reportUNIQUEidtoSEARCH);



        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinformeDatsHda").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformDatsHacienda inform= ds.getValue(SetInformDatsHacienda.class);

                    if(inform!=null){
                        Variables.CurrenReportPart3=inform;
                        break;

                    }


                    ///  Log.i("midaclick","el fist data elemetn is "+Variables.CurrenReportPart2.getUniqueIDinformePart2());
                }

                Log.i("CurrenReportPart3","la fuente a es "+ Variables.CurrenReportPart3.getFuenteAgua());



                //  Log.i("secondInform","el id del secon resport es "+Variables.CurrenReportPart3.getUniqueIDinformePart2());

              //  Log.i("secondInform","el CANDAO ES "+Variables.CurrenReportPart3.getCandadoQsercom());

                Intent intencion= new Intent(ActivitySeeReports.this, ActivityContenedoresPrev.class);


                if(modo==Variables.MODO_EDICION ){

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




                //debemos tener data en el report chekemaos
                //VAmos al activity preview...



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    private void showBottomSheetDialog(int reportTipo,String idReport) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(ActivitySeeReports.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_edit_cpn);

        LinearLayout lyRevisar = bottomSheetDialog.findViewById(R.id.lyhh);
        LinearLayout lyEditar = bottomSheetDialog.findViewById(R.id.lyEditar);
        LinearLayout layOtherOpcion = bottomSheetDialog.findViewById(R.id.layOtherOpcion);


Log.i("puslado","el value es "+idReport);

        lyRevisar.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {



                if(reportTipo== Constants.CONTENEDORES){
                    ///descragamos la parte uno del reporte
                    DowloadReportPart1(idReport,Variables.MODO_VISUALIZACION);

                }

                else if(reportTipo==Constants.CONTENEDORES_EN_ACOPIO){


                    Log.i("dateis","es conteendores en acopio por aqui");

                    Log.i("verididjf","es conteendores en acopio por aqui");
                    Log.i("verididjf","el id reporte es "+idReport);

                    DowloadReportContersAcopio(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==Constants.PACKING_lIST){


                    DowloadPackingList(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }


                else if(reportTipo==Constants.CAMIONES_Y_CARRETAS){


                    DowloadReportCamionesYcarretas(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==Constants.CONTROL_CALIDAD){

                    DowloadControlCalidad(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==Constants.CUADRO_MUESTRO_CAL_RECHZDS){


                    Log.i("hsmpadat","es un cuadro de muestreo ");

                    Log.i("hsmpadat","el id report es  "+idReport);



                    DowloadEspecificReportCalbAndRechazados (idReport,Variables.MODO_VISUALIZACION);
                    // DowloadControlCalidad(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...
                }




                bottomSheetDialog.dismiss();


            }
        });



        lyEditar.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {

                //  Toast.makeText(getActivity(), "Share is Clicked", Toast.LENGTH_LONG).show();
                if(reportTipo==Constants.CONTENEDORES){
                    ///descragamos la parte uno del reporte
                    DowloadReportPart1(idReport,Variables.MODO_EDICION);



                }else if(reportTipo==Constants.CONTENEDORES_EN_ACOPIO){


                    DowloadReportContersAcopio(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==Constants.PACKING_lIST){


                    DowloadPackingList(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...

                }


                else if(reportTipo==Constants.CAMIONES_Y_CARRETAS){


                    DowloadReportCamionesYcarretas(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...


                }


                else if(reportTipo==Constants.CONTROL_CALIDAD){


                    DowloadControlCalidad(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...


                }


                else if(reportTipo==Constants.CUADRO_MUESTRO_CAL_RECHZDS){

                    Log.i("hsmpadat","es un cuadro de muestreo ");

                    Log.i("hsmpadat","el id report es  "+idReport);

                            DowloadEspecificReportCalbAndRechazados (idReport,Variables.MODO_EDICION);

                   // DowloadControlCalidad(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...


                }






                bottomSheetDialog.dismiss();
            }
        });



        layOtherOpcion.setOnClickListener(new View.OnClickListener() { //editar
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();


            }
        });

        bottomSheetDialog.show();
    }


    private void showPRogress(Intent i) {
        progress =new ProgressDialog(ActivitySeeReports.this);

        progress.setMessage("Cargando Datos....");
       // progress.setProgressStyle(ProgressDialog.THEME_HOLO_LIGHT);
       progress.setIndeterminate(true);

        if (! isFinishing()) {


            try {
                progress.show();

            } catch (Exception e) {

            }




        }

        new Thread ( new Runnable()
        {
            public void run()
            {
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

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
        DatePickerDialog picker = new DatePickerDialog(ActivitySeeReports.this,
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


                        dowloadinformesby_EspecificDate(fechaToSearch);


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







    void dowloadinformesby_EspecificDate(String dateSelecionado){

        //VAMOS A LLAMRALO MULTIPLES VECES CUANDO CAMBIA LA INFO .... PILAXX

        Query query = rootDatabaseReference.child("Registros").child("InformesRegistros").orderByChild("simpleDateForm").equalTo(dateSelecionado);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                listReport= new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    InformRegister report=ds.getValue(InformRegister.class);

                    if(exportadoraSelecionadaSpn.equals("TODAS")){

                        if(Variables.usuarioQserconGlobal!=null & Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_OFICINA){ //si es user oficina

                            listReport.add(report);
                        }

                        else if(Variables.usuarioQserconGlobal!=null &
                                Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_CAMPO &
                                report.getIdQuienSUbioForm().equals(Variables.usuarioQserconGlobal.getUniqueIDuser())){
                            listReport.add(report);

                        }
                    }




                    else { //es una exportadora especificz

                        if(Variables.usuarioQserconGlobal!=null &
                                Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_OFICINA & report.getExportadoraName().equals(exportadoraSelecionadaSpn)
                        ){ //si es user oficina

                            listReport.add(report);

                        }

                        else if(Variables.usuarioQserconGlobal!=null &
                                Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_CAMPO &
                                report.getIdQuienSUbioForm().equals(Variables.usuarioQserconGlobal.getUniqueIDuser())
                        && report.getExportadoraName().equals(exportadoraSelecionadaSpn)){

                            listReport.add(report);


                        }

                    }



                }



                Log.i("dateis","el size de lista 222es  "+ listReport.size());
                ///  dowloadinformesby_PACKE_lIST(dateSelecionado);
                //setAdapaterDataAndShow(reportsListPart1);


                setAdapaterDataAndShow(listReport);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    private void getExportadorasAndSetSpinner(){
        //tenemos exportadoras de prefrencias//



        if(Utils.hasmpaExportadoras.size()==0){
            Utils.hasmpaExportadoras = SharePref.getMapExpotadoras(SharePref.KEY_EXPORTADORAS);

        }


        ArrayList<String>nombresExportadoras= new ArrayList<>();

        for(Exportadora exportadora: Utils.hasmpaExportadoras.values()){
            nombresExportadoras.add(exportadora.getNameExportadora());
        }


        nombresExportadoras.add(0,"TODAS");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresExportadoras);
        spinnerExportadoras.setAdapter(arrayAdapter);


    }



}