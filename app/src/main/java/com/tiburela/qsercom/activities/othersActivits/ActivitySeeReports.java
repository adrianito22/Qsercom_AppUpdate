package com.tiburela.qsercom.activities.othersActivits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.formulariosPrev.PackingListPreviewActivity;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewsFormDatSContersEnAc;
import com.tiburela.qsercom.adapters.RecyclerVAdapterReportsList;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.PackingListMod;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.models.ReportsAllModel;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.utils.Variables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivitySeeReports extends AppCompatActivity {
    RecyclerView recyclerVReports;
    Spinner  spinnerDatesSelector;
    ArrayList<SetInformEmbarque1> reportsListPart1;
    ProgressDialog pd;
    DatabaseReference rootDatabaseReference ; //anterior
    TextView txtConfirmExitenciaData ;

    public final int REPORTE_CONTENEDORES_EN_HCDA=1100;
    public final int CONTENEDORES=1200;
    public final int PACKINGLIST=1201;
    public final int OTRO_REPORTE=1202;
    public final int REPORTE_CONTENEDORES_EN_ACOPIO=1203;
    public final int REPORTE_CAMIONES_y_CARRETAS=1204;
    public final int REPORT_CONTROL_CALIDAD=1205;




    ArrayList<ReportsAllModel>allReportFiltB;


    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_reports);

        //Auth.initAuth(this);
        context = getApplicationContext();

        findViewsIDs();
        listenenrSpinner();

        Variables.listImagesToDelete=new ArrayList<String>();

        RealtimeDB.initDatabasesRootOnly();

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference();




    }

    @Override
    protected void onStart() {
        super.onStart();


        try {
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
                    dowloadinformesby_CONTENEDORES(fechaToSearch);
                    //dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);

                    Log.d("dateis ","el dat today is "+fechaToSearch) ;


                }
                else if (timeSelecionado.equals("AYER")){

                    fechaToSearch=generaFechaToSearch(Variables.AYER);
                    dowloadinformesby_CONTENEDORES(fechaToSearch);
                    // dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);


                }

                else if (timeSelecionado.equals("ANTEAYER")){

                    fechaToSearch=generaFechaToSearch(Variables.ANTEAYER);
                    dowloadinformesby_CONTENEDORES(fechaToSearch);
                    //dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);


                }else if (timeSelecionado.equals("FECHA ESPECIFICA")){

                    String fecheEspecifica ="14-09-2022";//aqui va la fecha que obtengamos

                    dowloadinformesby_CONTENEDORES(fecheEspecifica);
                    // dowloadinformesby_CONTENEDORES_EN_ACOPIO(fechaToSearch);



                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


    void dowloadinformesby_CONTENEDORES(String dateSelecionado){
        allReportFiltB=new ArrayList<>();

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("listInformes").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        //reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque1 informEmbarque1=ds.getValue(SetInformEmbarque1.class);

                    //  reportsListPart1.add(informEmbarque1);


                    allReportFiltB.add(new ReportsAllModel(CONTENEDORES,false,false,false,"Contenedores"
                            , informEmbarque1.getSimpleDataFormat(),informEmbarque1.getUniqueIDinforme()));

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


        Log.i("sliexsa","el size de lista here call es  "+allReportFiltB.size());

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("contenedoresAcopio").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        //    reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ContenedoresEnAcopio contenedoresEnAcopio=ds.getValue(ContenedoresEnAcopio.class);

                    //  reportsListPart1.add(informEmbarque1);

                    allReportFiltB.add(new ReportsAllModel(REPORTE_CONTENEDORES_EN_ACOPIO,false,false,false,"Contenedores Acopio"
                            , contenedoresEnAcopio.getSimpleDataFormat(),contenedoresEnAcopio.getUniqueIDinforme()));

                }

                Log.i("sliexsa","el size de lista 222es  "+allReportFiltB.size());
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


        Log.i("sliexsa","el size de lista here call es  "+allReportFiltB.size());

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

                    allReportFiltB.add(new ReportsAllModel(PACKINGLIST,false,false,false,"Packing List"
                            , packinList.getSimpledatFormt(),packinList.getUniqueIDinforme()));

                }


                Log.i("sliexsa","el size de lista 222es  "+allReportFiltB.size());
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


        Log.i("sliexsa","el size de lista here call es  "+allReportFiltB.size());

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("informeCamionesYcarretas").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        //    reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ContenedoresEnAcopio contenedoresEnAcopio=ds.getValue(ContenedoresEnAcopio.class);

                    //  reportsListPart1.add(informEmbarque1);

                    allReportFiltB.add(new ReportsAllModel(REPORTE_CAMIONES_y_CARRETAS,false,false,false,"Camiones Y Carretas"
                            , contenedoresEnAcopio.getSimpleDataFormat(),contenedoresEnAcopio.getUniqueIDinforme()));

                }

                Log.i("sliexsa","el size de lista 222es  "+allReportFiltB.size());
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
        Log.i("sliexsa","el size de lista here call es  "+allReportFiltB.size());



        Query query = rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ControlCalidad controlcalidad=ds.getValue(ControlCalidad.class);
                    allReportFiltB.add(new ReportsAllModel(REPORT_CONTROL_CALIDAD,false,false,false,"Control Calidad",
                            controlcalidad.getSimpleDate(),controlcalidad.getUniqueId()));
                }

                Log.i("sliexsa","el size de lista 222es  "+allReportFiltB.size());
                ///  dowloadinformesby_PACKE_lIST(dateSelecionado);
                //setAdapaterDataAndShow(reportsListPart1);


                setAdapaterDataAndShow(allReportFiltB);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }





    void setAdapaterDataAndShow(ArrayList<ReportsAllModel>reports ) {


        if (reports.size()>0 )
        {txtConfirmExitenciaData.setVisibility(TextView.INVISIBLE);

        }
        else
        {txtConfirmExitenciaData.setVisibility(TextView.VISIBLE);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivitySeeReports.this);

        RecyclerVAdapterReportsList  adapter = new RecyclerVAdapterReportsList(reports,ActivitySeeReports.this);

        recyclerVReports.setLayoutManager(layoutManager);

        recyclerVReports.setAdapter(adapter);


        eventoBtnclicklistener(adapter);



    }



    private void eventoBtnclicklistener(RecyclerVAdapterReportsList adapter) {

        adapter.setOnItemClickListener(new RecyclerVAdapterReportsList.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {  //este para eminar

                // Variables.CurrenReportPart1=  reportsListPart1.get(position);

                showBottomSheetDialog(allReportFiltB.get(position).getReporteTipo(),allReportFiltB.get(position).getIdInforme());


                Log.i("midaclick","el click es here, el informe es "+allReportFiltB.get(position).getIdInforme());


            }

        });
    }



    private void DowloadReportPart1(String uniqeuIDiNFORME,int modoReporte){ //para informe contenedores

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
                        break;

                    }



                }

                Log.i("isclkiel","el data es cc "+ Variables.CurrenReportPart1.getContenedor());




                //   Log.i("isclkiel","el data es cc "+ Variables.CurrenReportPart1.getContenedor());

                dowloadSecondPART_Report(Variables.CurrenReportPart1.getUniqueIDinforme(),modoReporte);//y despues vamos a a la activity preview



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("isclkiel","el error es  "+ error.getMessage());


            }
        } );




    }



    private void DowloadReportCamionesYcarretas(String uniqeuIDiNFORME,int modoReporte){ //para informe contenedores

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

                    startActivity(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);
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

                Intent intencion= new Intent(ActivitySeeReports.this, PreviewsFormDatSContersEnAc.class);


                if(modoReporte==Variables.MODO_EDICION ){

                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,true);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEYEXTRA_CONTEN_EN_ACP,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);
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

                    startActivity(intencion);


                }else{


                    intencion.putExtra(Variables.KEY_PACKING_LIST,false);

                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);

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
                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEY_PACKING_LIST,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);
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

                    startActivity(intencion);

                    //pdialogff.dismiss();

                    //finish();
                }else{


                    intencion.putExtra(Variables.KEYEXTRAPREVIEW,false);
                    //si queremos deciion le ponemos true;
                    Log.i("verdura","ahora se llamo intent");

                    startActivity(intencion);
                    // pdialogff.dismiss();
                    // finish();



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

        LinearLayout lyRevisar = bottomSheetDialog.findViewById(R.id.lyRevisar);
        LinearLayout lyEditar = bottomSheetDialog.findViewById(R.id.lyEditar);
        LinearLayout layOtherOpcion = bottomSheetDialog.findViewById(R.id.layOtherOpcion);




        lyRevisar.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {



                if(reportTipo==CONTENEDORES){
                    ///descragamos la parte uno del reporte
                    DowloadReportPart1(idReport,Variables.MODO_VISUALIZACION);

                }

                else if(reportTipo==REPORTE_CONTENEDORES_EN_ACOPIO){


                    DowloadReportContersAcopio(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==PACKINGLIST){


                    DowloadPackingList(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }


                else if(reportTipo==REPORTE_CAMIONES_y_CARRETAS){


                    DowloadReportCamionesYcarretas(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==REPORT_CONTROL_CALIDAD){

                    DowloadControlCalidad(idReport,Variables.MODO_VISUALIZACION);
                    //Descargamos un objeto contenedores object...

                }




                bottomSheetDialog.dismiss();


            }
        });



        lyEditar.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {

                //  Toast.makeText(getActivity(), "Share is Clicked", Toast.LENGTH_LONG).show();
                if(reportTipo==CONTENEDORES){
                    ///descragamos la parte uno del reporte
                    DowloadReportPart1(idReport,Variables.MODO_EDICION);

                }else if(reportTipo==REPORTE_CONTENEDORES_EN_ACOPIO){


                    DowloadReportContersAcopio(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...

                }

                else if(reportTipo==PACKINGLIST){


                    DowloadPackingList(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...

                }


                else if(reportTipo==REPORTE_CAMIONES_y_CARRETAS){


                    DowloadReportCamionesYcarretas(idReport,Variables.MODO_EDICION);
                    //Descargamos un objeto contenedores object...


                }


                else if(reportTipo==REPORT_CONTROL_CALIDAD){


                    DowloadControlCalidad(idReport,Variables.MODO_EDICION);
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

}