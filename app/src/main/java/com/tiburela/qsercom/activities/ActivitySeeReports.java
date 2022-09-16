package com.tiburela.qsercom.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.adapters.RecyclerVAdapterReportsList;
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

    DatabaseReference rootDatabaseReference ; //anterior
    TextView txtConfirmExitenciaData ;

    public static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_reports);

        //Auth.initAuth(this);
        context = getApplicationContext();

        findViewsIDs();
        listenenrSpinner();

        rootDatabaseReference = FirebaseDatabase.getInstance().getReference();




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
                    dowloadinformesbytimedate(fechaToSearch);

                    Log.d("dateis ","el dat today is "+fechaToSearch) ;


                }
                else if (timeSelecionado.equals("AYER")){

                    fechaToSearch=generaFechaToSearch(Variables.AYER);
                    dowloadinformesbytimedate(fechaToSearch);

                }

                else if (timeSelecionado.equals("ANTEAYER")){

                    fechaToSearch=generaFechaToSearch(Variables.ANTEAYER);
                    dowloadinformesbytimedate(fechaToSearch);

                }else if (timeSelecionado.equals("FECHA ESPECIFICA")){

                    String fecheEspecifica ="14-09-2022";//aqui va la fecha que obtengamos

                    dowloadinformesbytimedate(fecheEspecifica);


                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }


    void dowloadinformesbytimedate(String dateSelecionado){

       // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("listInformes").orderByChild("simpleDataFormat").equalTo(dateSelecionado);
        reportsListPart1 = new ArrayList<>();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque1 informEmbarque1=ds.getValue(SetInformEmbarque1.class);

                    reportsListPart1.add(informEmbarque1);

                }




                setAdapaterDataAndShow(reportsListPart1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }




    void setAdapaterDataAndShow(ArrayList<SetInformEmbarque1>reports ) {


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
              Variables.CurrenReportPart1=  reportsListPart1.get(position);


                dowloadSecondPART_Report(Variables.CurrenReportPart1.getUniqueIDinforme());//y despues vamos a a la activity preview

                Log.i("midaclick","el click es here, posicion es "+position);


            }

        });
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








    void dowloadSecondPART_Report(String reportUNIQUEidtoSEARCH){ //DESCRAGAMOS EL SEGUNDO REPORTE
        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinforme").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque2 informEmbarque2=ds.getValue(SetInformEmbarque2.class);
                      Variables.CurrenReportPart2=informEmbarque2;
                      Log.i("midaclick","el fist data elemetn is "+Variables.CurrenReportPart2.getUniqueIDinforme());
                }


                Intent intencion= new Intent(ActivitySeeReports.this, PreviewActivity.class);
                intencion.putExtra(Variables.KEYEXTRAPREVIEW,false);
                //si queremos deciion le ponemos true;
                 startActivity(intencion);

                //debemos tener data en el report chekemaos
                   //VAmos al activity preview...



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

}