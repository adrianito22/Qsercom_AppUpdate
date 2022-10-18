package com.tiburela.qsercom.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.PerecentHelp;
import com.tiburela.qsercom.utils.Permisionx;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class FormDatosContersEnAcopio extends AppCompatActivity implements View.OnClickListener , View.OnTouchListener {
    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;


    private int currentTypeImage=0;
    ProgressBar progressBarFormulario;
    TextInputEditText ediFechaInicio;
    TextInputEditText fechDetermino;
    TextInputEditText ediExpSolicitante;
    TextInputEditText ediExpProcesada;
    TextInputEditText ediMarca;
    TextInputEditText ediPuerto;
    TextInputEditText ediAgenciaNav;
    TextInputEditText ediInspectorAcopio;
    TextInputEditText ediCedulaI;

    TextInputEditText ediZona;
    TextInputEditText ediHoraInicio;
    TextInputEditText ediHoraTermino;
    TextInputEditText ediHoraLLegadaContenedor;
    TextInputEditText ediHoraSalidaContenedor;
    TextInputEditText ediNguiaRemision;
    TextInputEditText ediNtargetaEmbarque;
    TextInputEditText ediFotosLlegada;
    TextInputEditText ediDestino;
    TextInputEditText ediVapor;
    TextInputEditText ediFotoContenedor;
    TextInputEditText ediFotosPposcosecha;
    TextInputEditText ediNumContenedor;

    TextInputEditText ediCompaniaTransporte;
    TextInputEditText ediNombreChofer;
    TextInputEditText ediCedula;
    TextInputEditText ediCelular;
    TextInputEditText ediPLaca;
    TextInputEditText ediMarcaCabezal;
    TextInputEditText ediColorCabezal;
    TextInputEditText ediFotosLlegadaTransport;

    TextInputEditText ediTare;
    TextInputEditText ediBooking;
    TextInputEditText ediMaxGross;
    TextInputEditText ediNumSerieFunda;
    TextInputEditText stikVentolerExterna;
    TextInputEditText ediCableRastreoLlegada;
    TextInputEditText ediSelloPlasticoNaviera;
    TextInputEditText ediOtroSellosLlegada;
    TextInputEditText ediFotosSellosLLegada;



    TextInputEditText ediTermofrafo1;
    TextInputEditText ediHoraEncendido1;
    TextInputEditText ediUbicacion1;
    TextInputEditText ediRuma1;
    TextInputEditText ediTermofrafo2;
    TextInputEditText ediHoraEncendido2;
    TextInputEditText ediUbicacion2;
    TextInputEditText ediRuma2;
    TextInputEditText ediCandadoqsercon;
    TextInputEditText ediSelloNaviera;
    TextInputEditText ediCableNaviera;
    TextInputEditText ediSelloPlastico;
    TextInputEditText ediCandadoBotella;
    TextInputEditText ediCableExportadora;
    TextInputEditText ediSelloAdesivoexpor;
    TextInputEditText esiSelloAdhNaviera;
    TextInputEditText ediOtherSellos;

    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;



    Spinner spinnerSelectZona;
    Spinner spFumigaCorL1 ;
    Spinner spTipoBoquilla ;
    ArrayList<View> listViewsClickedUser;

    ImageView imBatach;
    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;

    ImageView imBtakePic;

    ////////
    ImageView imbAtach_transportista;
    ImageView imbTakePicTransportista;
    ImageView imbAtachSellosLlegada;
    ImageView imbTakePicSellosLLegada;
    ImageView imbAtachDatosContenedor;
    ImageView imbTakePicDatosContenedor;
    ImageView imbAtachPrPostcosecha;
    ImageView imbTakePicPrPostcosecha;
     ImageView imbTakePic;
    public static Context context;


    @Override
    protected void onStart() {
        super.onStart();

        Auth.initAuth(FormDatosContersEnAcopio.this);
        Auth.signInAnonymously(FormDatosContersEnAcopio.this);



        if(Variables.hayUnFormIncompleto){


            AddDataFormOfSharePrefe() ;

            //
            Variables.hayUnFormIncompleto=false;

        }


/*
        if(hayUnformularioIcompleto){

             TextInputEditText [] arrayEditex =creaArryOfTextInputEditText();

            Utils.addDataOfPrefrencesInView(arrayEditex);

            Map<String, ImagenReport> mapImagesReport = Utils.loadMapiMAGEData(FormDatosContersEnAcopio.this);


            ArrayList<ImagenReport> listImagesToSaVE = new ArrayList<ImagenReport>(mapImagesReport.values());


              //if el formulario no es nulo

            if(listImagesToSaVE!=null ) {

                addInfotomap(listImagesToSaVE);
                createlistsForReciclerviewsImages(listImagesToSaVE);

            }





        }

*/

        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
      //  updateUI(currentUs bver)

    }



    private void AddDataFormOfSharePrefe() {

        TextInputEditText [] arrayEditex =creaArryOfTextInputEditText();
        Utils.addDataOfPrefrencesInView(arrayEditex,Variables.currentMapPreferences);



/*
         //descrgamos info de imagenes //todavia no muy lista aun
        Map<String, ImagenReport> mapImagesReport = Utils.loadMapiMAGEData(ActivityContenedores.this);
        ArrayList<ImagenReport> listImagesToSaVE = new ArrayList<ImagenReport>(mapImagesReport.values());

        //if el formulario no es nulo

        if(listImagesToSaVE!=null ) {

            addInfotomap(listImagesToSaVE);
            createlistsForReciclerviewsImages(listImagesToSaVE);

        }

*/


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_datos_contene_acopio);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            hayUnformularioIcompleto = extras.getBoolean("ActivitymenuKey");

            //The key argument here must match that used in the other activity
        }

        context = getApplicationContext();


        UNIQUE_ID_iNFORME= UUID.randomUUID().toString();

      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior


        Variables.activityCurrent=Variables.FormatDatsContAcopi;
        Auth.initAuth(this);

        StorageData.initStorageReference();


        findViewsIds();
        configCertainSomeViewsAliniciar();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennersSpinners();

        addOnTouchaMayoriaDeViews();
        eventCheckdata();
        //creaFotos();


    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(FormDatosContersEnAcopio.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {

                        if(vista.getId()==R.id.ediHoraInicio) {
                            ediHoraInicio.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediHoraLLegadaContenedor) {
                            ediHoraLLegadaContenedor.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediHoraSalidaContenedor) {
                            ediHoraSalidaContenedor.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediTipoEmp2) {
                            ediHoraEncendido1.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + sMinute);


                        }



                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void selecionaFecha(int idView){


        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog  picker = new DatePickerDialog(FormDatosContersEnAcopio.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


                        if(idView==R.id.ediFechaInicio){
                            ediFechaInicio.setText(daySemana+"/"+mes+"/"+year);

                        }else{

                            fechDetermino.setText(daySemana+"/"+mes+"/"+year);

                        }


                    }
                }, year,  mes, daySemana);

       picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
       picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);


        picker.show();
    }


    private void disableEditText(EditText editText) {

       // editText.setFocusable(false);
       // editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
      //  editText.setBackgroundColor(Color.TRANSPARENT);
    }



    private void configCertainSomeViewsAliniciar( ) { //configuraremos algos views al iniciar

        disableEditText(ediFechaInicio);
        disableEditText(fechDetermino);

        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

        disableEditText(ediHoraLLegadaContenedor);//here
        disableEditText(ediHoraSalidaContenedor);


        //disableEditText(ediFotosLlegada);

        disableEditText(ediZona);

        disableEditText(ediHoraEncendido1);
        disableEditText(ediHoraEncendido2);


    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar

        ediNtargetaEmbarque=findViewById(R.id.ediNtargetaEmbarque);
         ediZona=findViewById(R.id.ediZona);
         ediHoraInicio=findViewById(R.id.ediHoraInicio);
         ediHoraTermino=findViewById(R.id.ediHoraTermino);
         ediNguiaRemision=findViewById(R.id.ediNguiaRemision);
        spinnerSelectZona = findViewById(R.id.spinnerZona);
        ediFotosLlegada=findViewById(R.id.ediFotosLlegada);



         fechDetermino=findViewById(R.id.fechDetermino);
         ediExpSolicitante=findViewById(R.id.ediExpSolicitante);
         ediExpProcesada =findViewById(R.id.ediExpProcesada);
         ediMarca =findViewById(R.id.ediMarca);
         ediPuerto =findViewById(R.id.ediPuerto);
        ediAgenciaNav=findViewById(R.id.ediAgenciaNav);

        ediInspectorAcopio=findViewById(R.id.ediInspectorAcopio);
        ediCedulaI=findViewById(R.id.ediCedulaI);


        ediFechaInicio=findViewById(R.id.ediFechaInicio);

        ediTare=findViewById(R.id.ediTare);
        ediBooking=findViewById(R.id.ediBooking);
        ediMaxGross=findViewById(R.id.ediMaxGross);
        ediNumSerieFunda=findViewById(R.id.ediNumSerieFunda);
        stikVentolerExterna=findViewById(R.id.stikVentolerExterna);
        ediCableRastreoLlegada=findViewById(R.id.ediCableRastreoLlegada);
        ediSelloPlasticoNaviera=findViewById(R.id.ediSelloPlasticoNaviera);
        ediOtroSellosLlegada=findViewById(R.id.ediOtroSellosLlegada);
        ediFotosSellosLLegada=findViewById(R.id.ediFotosSellosLLegada);




        ediNumContenedor=findViewById(R.id.ediNumContenedor);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);


         spFumigaCorL1=findViewById(R.id.spFumigaCorL1) ;
         spTipoBoquilla=findViewById(R.id.spTipoBoquilla) ;


         imBatach=findViewById(R.id.imbAtach);
         imBtakePic=findViewById(R.id.imbTakePic);



          ediDestino=findViewById(R.id.ediDestino);
          ediVapor=findViewById(R.id.ediVapor);

         // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
          //ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
        ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);



        ediFotoContenedor=findViewById(R.id.ediFotoContenedor);

        progressBarFormulario=findViewById(R.id.progressBarFormulario);

        ediFotosPposcosecha=findViewById(R.id.ediFotosPposcosecha);



        ediTermofrafo1=findViewById(R.id.ediNombProd1);
        ediHoraEncendido1=findViewById(R.id.ediTipoEmp2);
        ediUbicacion1=findViewById(R.id.ediCod2);
        ediRuma1=findViewById(R.id.edinCajas3);
        ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
        ediHoraEncendido2=findViewById(R.id.ediHoraEncendido2);
        ediUbicacion2=findViewById(R.id.ediUbicacion2);
        ediRuma2=findViewById(R.id.ediRuma2);
        ediCandadoqsercon=findViewById(R.id.ediCandadoqsercon);

        ediSelloNaviera=findViewById(R.id.ediSelloNaviera);
        ediCableNaviera=findViewById(R.id.ediCableNaviera);
        ediSelloPlastico=findViewById(R.id.ediSelloPlastico);
        ediCandadoBotella=findViewById(R.id.ediCandadoBotella);
        ediCableExportadora=findViewById(R.id.ediCableExportadora);
        ediSelloAdesivoexpor=findViewById(R.id.ediSelloAdesivoexpor);
        esiSelloAdhNaviera=findViewById(R.id.esiSelloAdhNaviera);
        ediOtherSellos=findViewById(R.id.ediOtherSellos);


        ediCompaniaTransporte=findViewById(R.id.ediCompaniaTransporte);
        ediNombreChofer=findViewById(R.id.ediNombreChofer);
        ediCedula=findViewById(R.id.ediCedula);
        ediCelular=findViewById(R.id.ediCelular);
        ediPLaca=findViewById(R.id.ediPLaca);
        ediMarcaCabezal=findViewById(R.id.ediMarcaCabezal);
        ediColorCabezal=findViewById(R.id.ediColorCabezal);
        ediFotosLlegadaTransport=findViewById(R.id.ediFotosLlegadaTransport);




        //las pics
         imbAtach_transportista=findViewById(R.id.imbAtach_transportista);
         imbTakePicTransportista=findViewById(R.id.imbTakePicTransportista);
         imbAtachSellosLlegada=findViewById(R.id.imbAtachSellosLlegada);
         imbTakePicSellosLLegada=findViewById(R.id.imbTakePicSellosLLegada);
         imbAtachDatosContenedor=findViewById(R.id.imbAtachDatosContenedor);
        imbAtachDatosContenedor=findViewById(R.id.imbAtachDatosContenedor);
         imbAtachPrPostcosecha=findViewById(R.id.imbAtachPrPostcosecha);
         imbTakePicPrPostcosecha=findViewById(R.id.imbTakePicPrPostcosecha);
        imbTakePicDatosContenedor=findViewById(R.id.imbTakePicDatosContenedor);
        imbTakePic=findViewById(R.id.imbTakePic);





    }


    private void addClickListeners( ) {

        /**todos add a todos clicklistener de la implemntacion*/


      ///  imBtakePic.setOnClickListener(this);
       // imBatach.setOnClickListener(this);

         imbAtach_transportista.setOnClickListener(this);
         imbTakePicTransportista.setOnClickListener(this);
         imbAtachSellosLlegada.setOnClickListener(this);
         imbTakePicSellosLLegada.setOnClickListener(this);
         imbAtachDatosContenedor.setOnClickListener(this);
         imbTakePicDatosContenedor.setOnClickListener(this);


        imBatach.setOnClickListener(this);
        imbTakePic.setOnClickListener(this);

        ediHoraEncendido1.setOnClickListener(this);
        ediHoraEncendido2.setOnClickListener(this);



        linLayoutHeader1.setOnClickListener(this);
        linLayoutHeader3.setOnClickListener(this);
        linLayoutHeader4.setOnClickListener(this);
        linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
     //   linLayoutHeader8.setOnClickListener(this);

        ediFechaInicio.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);
        fechDetermino.setOnClickListener(this);
        ediHoraLLegadaContenedor.setOnClickListener(this);
        ediHoraSalidaContenedor.setOnClickListener(this);



    }

    private void oucultaLinearLayout(LinearLayout linearLayout) { //configuraremos algos views al iniciar
     linearLayout.setVisibility(LinearLayout.GONE);


    }


    private void muestraLinearLayout( LinearLayout linearLayout) { //configuraremos algos views al iniciar

        linearLayout.setVisibility(LinearLayout.VISIBLE);



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {




       switch (view.getId()) {

           case R.id.linLayoutHeader1:
               LinearLayout layoutContainerSeccion=findViewById(R.id.layoutContainerSeccion7);

               if(layoutContainerSeccion.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion);
               }
               break; //



           case R.id.linLayoutHeader2:
               LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);

               if(layoutContainerSeccion3.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion3);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion3);
               }
               break; //


           case R.id.linLayoutHeader3:
               LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);

               if(layoutContainerSeccion4.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion4);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion4);
               }
               break; //

           case R.id.linLayoutHeader7:
               LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

               if(layoutContainerSeccion5.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion5);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion5);
               }
               break; //
           //linLayoutHeader7

           case R.id.linLayoutHeader4:
               LinearLayout layoutContainerSeccion3a=findViewById(R.id.layoutContainerSeccion3);

               if(layoutContainerSeccion3a.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion3a);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion3a);
               }
               break; //


           case R.id.linLayoutHeader5:
               LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);

               if(layoutContainerSeccion6.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion6);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion6);
               }
               break; //


           case R.id.linLayoutHeader6:
               LinearLayout layoutContainerSeccion9=findViewById(R.id.layoutContainerSeccion9);

               if(layoutContainerSeccion9.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion9);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion9);
               }
               break; //


           case R.id.ediFechaInicio:
              // Utils.closeKeyboard(FormularioActivity.this);
               selecionaFecha(R.id.ediFechaInicio);

               break; //


           case R.id.fechDetermino:
               // Utils.closeKeyboard(FormularioActivity.this);

               selecionaFecha(R.id.fechDetermino);

               break; //

           case R.id.ediHoraInicio:
              // Utils.closeKeyboard(FormularioActivity.this);

               showingTimePicker(view);

               break; //

           case R.id.ediHoraTermino:
             // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //
           case R.id.ediHoraSalidaContenedor:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //


           case R.id.ediHoraLLegadaContenedor:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //

           case R.id.ediTipoEmp2:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //

           case R.id.ediHoraEncendido2:
               // Utils.closeKeyboard(FormularioActivity.this);
               showingTimePicker(view);

               break; //

           case R.id.imbAtach:

               currentTypeImage=Variables.FOTO_LLEGADA;

               Log.i("miclickimg","es foto es type Variables.FOTO_LLEGADA");

               activityResultLauncher.launch("image/*");
               break;


           case R.id.imbTakePic:
               Log.i("miclickimg","es foto es type Variables.FOTO_LLEGADA");

               currentTypeImage=Variables.FOTO_LLEGADA;

               takepickNow();
               break;



           case R.id.imbAtach_transportista:
               currentTypeImage=Variables.FOTO_TRANSPORTISTA;
               Log.i("miclickimg","es foto es type Variables.FOTO_TRANSPORTISTA");

               activityResultLauncher.launch("image/*");
               break;



           case R.id.imbTakePicTransportista:
               currentTypeImage=Variables.FOTO_TRANSPORTISTA;
               Log.i("miclickimg","es foto es type Variables.FOTO_TRANSPORTISTA");


               takepickNow();
               break;



           case R.id.imbAtachSellosLlegada:
               currentTypeImage=Variables.FOTO_SELLO_LLEGADA;
               Log.i("miclickimg","es foto es type Variables.FOTO_SELLO_LLEGADA");

               activityResultLauncher.launch("image/*");
               break;



           case R.id.imbTakePicSellosLLegada:
               Log.i("miclickimg","es foto es type Variables.FOTO_SELLO_LLEGADA");

               currentTypeImage=Variables.FOTO_TRANSPORTISTA;

               takepickNow();
               break;



           case R.id.imbAtachDatosContenedor:
               currentTypeImage=Variables.FOTO_CONTENEDOR;
               Log.i("miclickimg","es foto es type Variables.FOTO_CONTENEDOR");
               activityResultLauncher.launch("image/*");


               break;


           case R.id.imbTakePicDatosContenedor:
               Log.i("miclickimg","es foto es type Variables.FOTO_CONTENEDOR");

               currentTypeImage=Variables.FOTO_CONTENEDOR;

               takepickNow();
               break;



           case R.id.imbAtachPrPostcosecha:
               currentTypeImage=Variables.FOTO_PROD_POSTCOSECHA;
               Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");

               activityResultLauncher.launch("image/*");
               break;


           case R.id.imbTakePicPrPostcosecha:
               Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");

               currentTypeImage=Variables.FOTO_PROD_POSTCOSECHA;

               takepickNow();
               break;






       }

        //aqui o


    }

    private void takepickNow() {

        Permisionx.checkPermission(Manifest.permission.CAMERA,1,this, FormDatosContersEnAcopio.this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

             cam_uri = FormDatosContersEnAcopio.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

            //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
            startCamera.launch(cameraIntent);                // VERY NEW WAY


        }




    }

        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            // There are no request codes

                          //  mImageView.setImageURI(cam_uri);

                           // showImageByUri(cam_uri);

                            //creamos un nuevo objet de tipo ImagenReport
                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage,UNIQUE_ID_iNFORME, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(FormDatosContersEnAcopio.this,cam_uri)));

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                            //Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, FormDatosContersEnAcopio.this);


                            showImagesPicShotOrSelectUpdateView(false);

                        }
                    }
                });



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN ){

            PerecentHelp.listViewsClickedUser.add(view);

            Log.i("casnasd","el size de la lista es "+ PerecentHelp.listViewsClickedUser.size());

            if( PerecentHelp.listViewsClickedUser.size()>1) {
                //obtenemos la lista anterior y verficamos si esta completada;
                View vistFieldAnterior = PerecentHelp.getVistaAnteriorClick();
                //  checkeamosSiFieldViewIScompleted(vistFieldAnterior);
                PerecentHelp.checkeamosSiFieldViewIScompletedAndSavePref(vistFieldAnterior, SharePref.KEY_CONTENEDORES);

            }


        }
        return false;


    }



    @SuppressLint("ClickableViewAccessibility")
    private void addOnTouchaMayoriaDeViews(){

        ediHoraInicio.setOnTouchListener(this);
        ediHoraTermino.setOnTouchListener(this);
        ediNguiaRemision.setOnTouchListener(this);
        ediNtargetaEmbarque.setOnTouchListener(this);
        spinnerSelectZona.setOnTouchListener(this);
        ediHoraLLegadaContenedor.setOnTouchListener(this);
        ediHoraSalidaContenedor.setOnTouchListener(this);
        ediDestino.setOnTouchListener(this);

        ediVapor.setOnTouchListener(this);
        ediTare.setOnTouchListener(this);
        ediBooking.setOnTouchListener(this);
        ediMaxGross.setOnTouchListener(this);

        ediNumSerieFunda.setOnTouchListener(this);
        stikVentolerExterna.setOnTouchListener(this);
        ediCableRastreoLlegada.setOnTouchListener(this);

        ediSelloPlasticoNaviera.setOnTouchListener(this);
        ediOtroSellosLlegada.setOnTouchListener(this);

        ediTermofrafo1.setOnTouchListener(this);

        ediHoraEncendido1.setOnTouchListener(this);
        ediUbicacion1.setOnTouchListener(this);
        ediRuma1.setOnTouchListener(this);
        ediTermofrafo2.setOnTouchListener(this);
        ediHoraEncendido2.setOnTouchListener(this);
        ediUbicacion2.setOnTouchListener(this);
        ediRuma2.setOnTouchListener(this);


        ediCandadoqsercon.setOnTouchListener(this);
        ediSelloNaviera.setOnTouchListener(this);
        ediCableNaviera.setOnTouchListener(this);

        ediSelloPlastico.setOnTouchListener(this);
        ediCandadoBotella.setOnTouchListener(this);
        ediCableExportadora.setOnTouchListener(this);
        ediSelloAdesivoexpor.setOnTouchListener(this);
        esiSelloAdhNaviera.setOnTouchListener(this);


        ediCompaniaTransporte.setOnTouchListener(this);
        ediNombreChofer.setOnTouchListener(this);
        ediCedula.setOnTouchListener(this);
        ediCelular.setOnTouchListener(this);

        ediPLaca.setOnTouchListener(this);
        ediMarcaCabezal.setOnTouchListener(this);
        ediColorCabezal.setOnTouchListener(this);



    }


    private View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
            listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

        }
        Log.i("casnasd","el size aqui en metodo es "+listViewsClickedUser.size());




        View vistAnterior = listViewsClickedUser.get(0);
      //  Log.i("soeobjetc","el objeto anterioR TAG ES "+vistAnterior.getTag().toString());



        return   vistAnterior;

    }


    private void checkeamosSiFieldViewIScompleted(View view ) {

        //revismaos si el usuario lleno el file o completo la tarea solictada

        Log.i("miodata","el id del selecionado anterior es "+view.getResources().getResourceName(view.getId()));


        if (view instanceof EditText) { //asi es un editex compobamos si esta lleno
            EditText editText = (EditText) view; //asi lo convertimos
             Log.i("miodata","el id es "+view.getResources().getResourceName(view.getId()));

                if ( view.getResources().getResourceName(view.getId()).contains("ediPPC0")){ //asi comprobamos que es un fiel opcional
                    if (editText.getText().toString().length() > 0) {
                        if (!editText.getText().toString().equals("0")) {

                            Log.i("miodata","el state ediPPC/someProductPostCosecha esta lleno ");


                          //  actualizaListStateView("ediPPC/someProductPostCosecha",true) ;
                           //  Utils.addDataMapPreferences(String.valueOf(view.getId()),editText.getText().toString() ,"iduniquehere", FormDatosContersEnAcopio.this);


                        }
                    }

                }

                else if(editText.getText().toString().isEmpty()) {


                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta vacio");


                   // actualizaListStateView(view.getResources().getResourceName(view.getId()),false) ;

                }




                ////si existe lo cambiamos a tru



                else if(! editText.getText().toString().isEmpty()) { //si esta lleno

                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta lleno");

                 //   actualizaListStateView(view.getResources().getResourceName(view.getId()),true) ;

                  //  Utils.addDataMapPreferences(String.valueOf(view.getId()),editText.getText().toString() ,"iduniquehere", FormDatosContersEnAcopio.this);



                }


        }




        else if (view.getResources().getResourceName(view.getId()).contains("imbAtach")  ||  view.getResources().getResourceName(view.getId()).contains("imbTakePic")){ //imBtakePic

             //COMPORBAQMOS SI EXISTE AL ME4NOS UN IMAGEN URI LIST..

            if(ImagenReport.hashMapImagesData.size()> 0 ) {
              //  actualizaListStateView("imbAtach/imbTakePic",true) ;

               Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y contiene al menos una foto");


            }else {

              //  actualizaListStateView("imbAtach/imbTakePic",false) ;
                Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y no contiene fotos");



            }


        }






        //seran mas comprobacion para verificar si imagenes por ejemplo fiueron completadas..
        //otra para radiobutton y otr para otro tipo de view..tec


       // actualizaProgressBar();

            }



    private void actualizaListStateView(String idSearch,boolean isEstaLleno){
///


        final String  idview = idSearch.replace(Variables.paqueteName+":id/","");


        Log.i("camisila","el id to search es "+idview) ;

        for(int i=0; i<EstateFieldView.listEstateViewField.size(); i++){

            if(EstateFieldView.listEstateViewField.get(i).getIdOfView().equals(idview)){

                EstateFieldView.listEstateViewField.get(i).setEstaLleno(isEstaLleno);


            }else  {



            }

        }







    }


    private void actualizaProgressBar(){

             int numero_itemsCompletados=0;

            final int NUMERO_FIELDS_TOTAL=EstateFieldView.listEstateViewField.size(); // 19  ahora items emn total de completar 19,, algunos son opcionales...pero siempre deben haber 19 para que todos esten llenos


            for(int i=0; i<EstateFieldView.listEstateViewField.size(); i++){

                if(EstateFieldView.listEstateViewField.get(i).isEstaLleno()){

                    numero_itemsCompletados =numero_itemsCompletados+1;


                }

            }

        Log.i("idCheck","el NUMERO ITEMScOMPLETADOS ES "+numero_itemsCompletados);


            //buscamos el porecntaje

        //int porcentajeDeProgreso= numero_itemsCompletados*NUMERO_FIELDS_TOTAL/100;



        int porcentajeDeProgreso= numero_itemsCompletados*100/NUMERO_FIELDS_TOTAL;

        progressBarFormulario.setProgress(porcentajeDeProgreso);



           Log.i("maswiso","el porciento es "+porcentajeDeProgreso);
            //un item opcional vale



        }







    private void selecImages(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        resultatachImages();



    }


      private void resultatachImages() {
        activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    if (result != null) {

                        //creamos un objeto

                        for(int indice=0; indice<result.size(); indice++){


//                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage,UNIQUE_ID_iNFORME, UUID.randomUUID().toString()+"."+Utils.getFormate(Utils.getFileNameByUri(FormularioActivity.this,cam_uri)));
                            ImagenReport imagenReportObjc =new ImagenReport("adrianitotest",result.get(indice).toString(),currentTypeImage,UNIQUE_ID_iNFORME, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(FormDatosContersEnAcopio.this,result.get(indice))));

                          Log.i("jamisama","el name id es "+imagenReportObjc.getUniqueIdNamePic());

                            ImagenReport.hashMapImagesData.put(imagenReportObjc.getUniqueIdNamePic(), imagenReportObjc);

                            //Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, FormDatosContersEnAcopio.this);

                        }



                        showImagesPicShotOrSelectUpdateView(false);



                    }
                }
                  });
      }

void showImageByUri(Uri uri )  {
   try {

        // Setting image on image view using Bitmap
        Bitmap bitmap = MediaStore
                .Images
                .Media
                .getBitmap(
                        getContentResolver(),
                      uri);



        //escalamos el bitmap
       Bitmap bitmap2=Bitmap.createScaledBitmap(bitmap, 420, 400, false);
        Log.i("registrand","los encontrado");


        ImageView imageView= new ImageView(this);


       imageView.setImageBitmap(bitmap2);






    }

                    catch (IOException e) {
        // Log the exception
        e.printStackTrace();
    }
}


private void listennersSpinners() {


    spinnerSelectZona .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerSelectZona.getSelectedItem().toString();
                ediZona.setText(zonaEelejida);
                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                   // actualizaListStateView("spinnerZona",false) ;
                }else {
                   // actualizaListStateView("spinnerZona",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



}


private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg){

        //si es eliminar comprobar aqui
    if(isDeleteImg){

        currentTypeImage=Variables.typeoFdeleteImg;

        Log.i("isdeletyin","is deleting ");


    }


     ArrayList<ImagenReport> filterListImagesData=new ArrayList<ImagenReport>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW

    RecyclerView recyclerView= findViewById(R.id.recyclerView);


    for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) {

        String key = set.getKey();

        ImagenReport value = set.getValue();

        if(value.getTipoImagenCategory()==currentTypeImage){

            filterListImagesData.add(ImagenReport.hashMapImagesData.get(key));

        }


    }


    //buscamos este



    //si la imagen es la imagen de fotos llegada INICLIZAMOS ASI
    if(currentTypeImage== Variables.FOTO_LLEGADA)  {
         recyclerView= findViewById(R.id.recyclerView);


    }

    else if (currentTypeImage==Variables.FOTO_TRANSPORTISTA){
        recyclerView = findViewById(R.id.recyclerVieDatsTransport);

    }

    else if (currentTypeImage==Variables.FOTO_CONTENEDOR){
        recyclerView = findViewById(R.id.recyclerViewDatosContenedor);
    }


    else if (currentTypeImage==Variables.FOTO_SELLO_LLEGADA){
        recyclerView = findViewById(R.id.recyclerViewSellosLlegada);

    }




    Log.i("isdeletyin","el value de first uri  items es "+filterListImagesData.get(0).geturiImage());

    RecyclerViewAdapter adapter=new RecyclerViewAdapter(filterListImagesData,this);
    GridLayoutManager layoutManager=new GridLayoutManager(this,2);


    // at last set adapter to recycler view.
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    eventoBtnclicklistenerDelete(adapter);



}


private void eventCheckdata(){// verificamos que halla llenado toda la info necesaria..

    Button btnCheck;
    btnCheck=findViewById(R.id.btnCheck);


    btnCheck.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view) {

           // generatePDFandImport();

            checkDataFields();




        }
    });





}

void checkDataFields(){ //

    if(! checkDatosGeneralesIsLleno()){

        Log.i("test001","no esta lleno  checkDatosGeneralesIsLleno");
        return;
    }


    else{
        Log.i("test001","si esta lleno checkDatosGeneralesIsLleno ");

    }



    if(! checkDatosContenedorIsLleno()){
        Log.i("test001","no esta lleno  checkDatosContenedorIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDatosContenedorIsLleno");
    }


    if(! checkDataSellosLlegadaIsLleno()){
        Log.i("test001","no esta lleno  checkDataSellosLlegadaIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDataSellosLlegadaIsLleno");


    }


    if(! checkSellosInstaladosIsLleno()){
        Log.i("test001","no esta lleno  checkSellosInstaladosIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkSellosInstaladosIsLleno");


    }


    if(! checkDatosTransportistaIsLleno()){
        Log.i("test001","no esta lleno  checkDatosTransportistaIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDatosTransportistaIsLleno");


    }

    if(! checkaDatosProcesoISllENO()){
        Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDataCalibFrutaCalEnfn");


    }



    Log.i("test001","toda la data esta completa HUrra ");

    uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

    createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...





}



private boolean checkaDatosProcesoISllENO(){


        return true;
}




private void creaDatosProcesoMapAndUpload(String informePertenece, String PuskEY, DatabaseReference mibasedata){

        TextInputEditText ediCjasProcesDespacha;

    TextInputEditText ediNombProd1;
    TextInputEditText ediNombProd2;
    TextInputEditText ediNombProd3;
    TextInputEditText ediNombProd4;
    TextInputEditText ediNombProd5;
    TextInputEditText ediNombProd6;
    TextInputEditText ediNombProd7;
    TextInputEditText ediNombProd8;

    TextInputEditText  ediTipoEmp1;
    TextInputEditText  ediTipoEmp2;
    TextInputEditText  ediTipoEmp3;
    TextInputEditText  ediTipoEmp4;
    TextInputEditText  ediTipoEmp5;
    TextInputEditText  ediTipoEmp6;
    TextInputEditText  ediTipoEmp7;
    TextInputEditText  ediTipoEmp8;

    TextInputEditText  ediCod1;
    TextInputEditText  ediCod2;
    TextInputEditText  ediCod3;
    TextInputEditText  ediCod4;
    TextInputEditText  ediCod5;
    TextInputEditText  ediCod6;
    TextInputEditText  ediCod7;
    TextInputEditText  ediCod8;


    TextInputEditText  edinCajas1;
    TextInputEditText  edinCajas2;
    TextInputEditText  edinCajas3;
    TextInputEditText  edinCajas4;
    TextInputEditText  edinCajas5;
    TextInputEditText  edinCajas6;
    TextInputEditText  edinCajas7;
    TextInputEditText  edinCajas8;




 ///vamos con findview id
     ediNombProd1=findViewById(R.id.ediNombProd1);
     ediNombProd2=findViewById(R.id.ediNombProd2);
     ediNombProd3=findViewById(R.id.ediNombProd3);
     ediNombProd4=findViewById(R.id.ediNombProd4);
     ediNombProd5=findViewById(R.id.ediNombProd5);
     ediNombProd6=findViewById(R.id.ediNombProd6);
     ediNombProd7=findViewById(R.id.ediNombProd7);
     ediNombProd8=findViewById(R.id.ediNombProd8);

      ediTipoEmp1=findViewById(R.id.ediTipoEmp1);
      ediTipoEmp2=findViewById(R.id.ediTipoEmp2);
      ediTipoEmp3=findViewById(R.id.ediTipoEmp3);
      ediTipoEmp4=findViewById(R.id.ediTipoEmp4);
      ediTipoEmp5=findViewById(R.id.ediTipoEmp5);
      ediTipoEmp6=findViewById(R.id.ediTipoEmp6);
      ediTipoEmp7=findViewById(R.id.ediTipoEmp7);
      ediTipoEmp8=findViewById(R.id.ediTipoEmp8);

      ediCod1=findViewById(R.id.ediCod1);
      ediCod2=findViewById(R.id.ediCod2);
      ediCod3=findViewById(R.id.ediCod3);
      ediCod4=findViewById(R.id.ediCod4);
      ediCod5=findViewById(R.id.ediCod5);
      ediCod6=findViewById(R.id.ediCod6);
      ediCod7=findViewById(R.id.ediCod7);
      ediCod8=findViewById(R.id.ediCod8);


      edinCajas1=findViewById(R.id.edinCajas1);
      edinCajas2=findViewById(R.id.edinCajas2);
      edinCajas3=findViewById(R.id.edinCajas3);
      edinCajas4=findViewById(R.id.edinCajas4);
      edinCajas5=findViewById(R.id.edinCajas5);
      edinCajas6=findViewById(R.id.edinCajas6);
      edinCajas7=findViewById(R.id.edinCajas7);
      edinCajas8=findViewById(R.id.edinCajas8);


  TextInputEditText [] arrayNmbresProd= {ediNombProd1, ediNombProd2, ediNombProd3, ediNombProd4, ediNombProd5, ediNombProd6, ediNombProd7, ediNombProd8

  };



    TextInputEditText [] arrayTiposEmpaque= {ediTipoEmp1, ediTipoEmp2, ediTipoEmp3, ediTipoEmp4, ediTipoEmp5, ediTipoEmp6, ediTipoEmp7, ediTipoEmp8

    };


    TextInputEditText [] arrayCodigos= {ediCod1, ediCod2, ediCod3, ediCod4, ediCod5, ediCod6, ediCod7, ediCod8

    };


    TextInputEditText [] arraynCajas= {edinCajas1, edinCajas2, edinCajas3, edinCajas4, edinCajas5, edinCajas6, edinCajas7, edinCajas8

    };


    //cremaos un mapa
    HashMap<String, DatosDeProceso> mimapaDatosProcesMap=new HashMap<>();

    for(int indice=0; indice<arraynCajas.length; indice++){

          String KeyDataIdOfView=String.valueOf(arrayNmbresProd[indice].getId()) ;
         String tipoEmpaque=arrayTiposEmpaque[indice].getText().toString();
         String cod=arrayCodigos[indice].getText().toString();
        String nombreProd=arrayNmbresProd[indice].getText().toString();
        int numeroCajas=0;

         if(Utils.checkIFaltaunDatoLlenoAndFocus(arrayNmbresProd,arrayTiposEmpaque,arrayCodigos,arraynCajas)){ //si ha llenado un  value de los 3 y el siguiente esta vacio...

           return;
         }

             if(indice==0 & tipoEmpaque.trim().isEmpty()  & cod.trim().isEmpty()  & nombreProd.trim().isEmpty()
                     & arraynCajas[indice].getText().toString().trim().isEmpty()){

                 tipoEmpaque="";
                 cod="";
                 numeroCajas=0;
                 nombreProd="";

                 //String InformePertenece;  //subimos el primero al menos..
                 DatosDeProceso midatosProceso= new DatosDeProceso(nombreProd,tipoEmpaque,cod,numeroCajas,informePertenece,KeyDataIdOfView);
                 midatosProceso.setKeyFirebase(PuskEY);
                 mimapaDatosProcesMap.put(KeyDataIdOfView,midatosProceso);

             }



           if(indice != 0 && ! tipoEmpaque.trim().isEmpty()  & !  cod.trim().isEmpty()  &  ! nombreProd.trim().isEmpty()
                   & ! arraynCajas[indice].getText().toString().trim().isEmpty()  ) {  //si es diferente de 0

               //entonces subimos la data.....

               //String InformePertenece;
               DatosDeProceso midatosProceso= new DatosDeProceso(nombreProd,tipoEmpaque,cod,numeroCajas,informePertenece,KeyDataIdOfView);
               midatosProceso.setKeyFirebase(PuskEY);

               mimapaDatosProcesMap.put(KeyDataIdOfView,midatosProceso);

           }




    }


   RealtimeDB. addDatosProceso(mimapaDatosProcesMap,mibasedata,PuskEY);  //subimos




  }





private void createObjcInformeAndUpload(){



//aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo

    ContenedoresEnAcopio informe = new ContenedoresEnAcopio(UNIQUE_ID_iNFORME,ediFechaInicio.getText().toString(),fechDetermino.getText().toString()
            ,ediExpSolicitante.getText().toString(),
            ediExpProcesada.getText().toString(),ediPuerto.getText().toString(),ediZona.getText().toString(),ediMarca.getText().toString(),
    ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediNguiaRemision.getText().toString(),ediNtargetaEmbarque.getText().toString()
            ,ediDestino.getText().toString(),ediVapor.getText().toString(),ediNumContenedor.getText().toString(),ediHoraLLegadaContenedor.getText().toString()
            ,ediHoraSalidaContenedor.getText().toString(),ediAgenciaNav.getText().toString(),ediSelloPlasticoNaviera.getText().toString()
    ,stikVentolerExterna.getText().toString(),ediNumSerieFunda.getText().toString(),ediCableRastreoLlegada.getText().toString(),ediBooking.getText().toString(),
            ediMaxGross.getText().toString(),ediTare.getText().toString(),ediOtherSellos.getText().toString(),ediTermofrafo1.getText().toString()
            ,ediTermofrafo2.getText().toString(),
            ediCandadoqsercon.getText().toString(),ediSelloNaviera.getText().toString(),ediCableNaviera.getText().toString(),ediSelloPlastico.getText().toString()
            ,ediCandadoBotella.getText().toString(),ediCableExportadora.getText().toString(),ediSelloAdesivoexpor.getText().toString(),esiSelloAdhNaviera.getText().toString()
            ,ediOtherSellos.getText().toString(),ediCompaniaTransporte.getText().toString(),ediNombreChofer.getText().toString(),ediCedula.getText().toString()
            ,ediCelular.getText().toString(),ediPLaca.getText().toString(),ediMarcaCabezal.getText().toString(),ediColorCabezal.getText().toString());




    //Agregamos un nuevo informe
    RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

    //agr5egamos la data finalemente



    //obtenemos el pushkey
    DatabaseReference mibasedata = RealtimeDB.rootDatabaseReference.child("Informes").child("datosProcesoContenAcopio");
    String PuskEY = mibasedata.push().getKey(); //que que cotienen este nodo


    informe.setDatosProcesoContenAcopioKEYFather(PuskEY); //le agregamos esa propiedad
    RealtimeDB.addNewInformContenresAcopio(informe,UNIQUE_ID_iNFORME);



    creaDatosProcesoMapAndUpload(UNIQUE_ID_iNFORME,PuskEY,mibasedata);


}

    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                //  Variables.currentCuponObjectGlob =listGiftCards.get(position);

                Log.i("midaclick","el click es here, posicion es "+position);

               ///elimnar el hasmap
               //vamos a ver el tipo del objeto removivo
               Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag()).getTipoImagenCategory();

                Log.i("camisax","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());


                ImagenReport.hashMapImagesData.remove(v.getTag().toString());
                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, FormDatosContersEnAcopio.this);


                Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);





                //   Log.i("dtaas","switch a" + "ctivate is "+Variables.currentCuponObjectGlob.isEsActivateCupon());
                //  Log.i("dtaas","switch destacado  is "+Variables.currentCuponObjectGlob.isEsDestacadoCupon());


            }

        });
    }



    void uploadImagesInStorageAndInfoPICS() {
   //una lista de Uris


        if(ImagenReport.hashMapImagesData.size() ==0 ){

            Toast.makeText(this, "esta vacia ", Toast.LENGTH_SHORT).show();
             return;
        }

        //    public static void uploadImage(Context context, ArrayList<ImagenReport> listImagesData) {

        //aqui subimos
       StorageData.uploadImage(FormDatosContersEnAcopio.this, ImagenReport.hashMapImagesData);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void generatePDFandImport(){
     //generate pdf


        if(!checkPermission()){

            requestPermission();
            //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
           // checkPermission2();

            /****por aqui pedir permisos antes **/

        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
           // Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
          // startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
        }







      //  startActivity(new Intent(FormularioActivity.this,PdfPreviewActivity.class));

        //generamos un pdf con la data que tenemos()

        /*

        PdfMaker.generatePdfReport1(FormularioActivity.this,ediCodigo.getText().toString(),Integer.parseInt(ediNhojaEvaluacion.getText().toString()),
                ediZona.getText().toString(),ediProductor.getText().toString(),ediCodigo.getText().toString()
                ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
                ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
                ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),ediObservacion.getText().toString()
                );

*/




    }


    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission() {
        // requesting permissions if not provided.

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    12);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique

            return;
        }


        try {
            ActivityCompat.requestPermissions(this, new String[]{ READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.

               /*

                boolean writeStorage = grantResults[0] == PackageManager.MANAGE;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if ( readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
                    finish();
                }

                */

            }
        }
    }











    ///vamos a cehekar que exista al menos una imagen en cada categoria...
    //comprbar que exista un objeto imagen.....

    //primero chekeamos el el uri exista...


    private boolean existminiumImage(int numImagenNMinimo, int categoriaImagenToSearch){

        int numImagesEcontradas=0;


        for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) { //revismao en todo el map

         //   String key = set.getKey();

            ImagenReport value = set.getValue();

            if(value.getTipoImagenCategory()==categoriaImagenToSearch){

                numImagesEcontradas++;

                if(numImagesEcontradas >=numImagenNMinimo){
                    break;

                }

            }


        }


       if(numImagesEcontradas>=numImagenNMinimo){
           return true;
       }else{
           return false;


       }



    }



    private boolean checkDatosGeneralesIsLleno(){

        LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion7);
        if(ediFechaInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFechaInicio.requestFocus();
            ediFechaInicio.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }

         if (fechDetermino.getText().toString().isEmpty()){ //chekamos que no este vacia
             fechDetermino.requestFocus();
             fechDetermino.setError("Debe selecionar una fecha");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



         if(ediExpSolicitante.getText().toString().isEmpty()){ //chekamos que no este vacia
             ediExpSolicitante.requestFocus();
             ediExpSolicitante.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




         if(ediExpProcesada.getText().toString().isEmpty()){ //chekamos que no este vacia
             ediExpProcesada.requestFocus();
             ediExpProcesada.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }






         if(ediMarca.getText().toString().isEmpty()){ //chekamos que no este vacia
             ediMarca.requestFocus();
             ediMarca.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediPuerto.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPuerto.requestFocus();
            ediPuerto.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }





        if(ediZona.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediZona.requestFocus();
            ediZona.setError("Debe selecionar una zona");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }





        if(ediHoraInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraInicio.requestFocus();
            ediHoraInicio.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediHoraTermino.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraTermino.requestFocus();
            ediHoraTermino.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



         if(ediNguiaRemision.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNguiaRemision.requestFocus();
            ediNguiaRemision.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }






         if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



/*
        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_LLEGADA)){
            ediFotosLlegada.requestFocus();

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            ediFotosLlegada.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosLlegada.clearFocus();
            ediFotosLlegada.setError(null);

        }
*/



        return true;
    }




    private boolean checkDatosContenedorIsLleno(){

        LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);


        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediDestino.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediDestino.requestFocus();
            ediDestino.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediNumContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNumContenedor.requestFocus();
            ediNumContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediVapor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediVapor.requestFocus();
            ediVapor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }






        if(ediHoraLLegadaContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraLLegadaContenedor.requestFocus();
            ediHoraLLegadaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediHoraSalidaContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraSalidaContenedor.requestFocus();
            ediHoraSalidaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediAgenciaNav.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediAgenciaNav.requestFocus();
            ediAgenciaNav.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        //chekamos que al menos exista una imagen...


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_CONTENEDOR)){
            ediFotoContenedor.requestFocus();

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            ediFotoContenedor.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotoContenedor.clearFocus();
            ediFotoContenedor.setError(null);

        }




return  true;

    }




    private boolean checkDataSellosLlegadaIsLleno(){
        LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);


        if(ediTare.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTare.requestFocus();
            ediTare.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediBooking.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediBooking.requestFocus();
            ediBooking.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediMaxGross.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediMaxGross.requestFocus();
            ediMaxGross.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediNumSerieFunda.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNumSerieFunda.requestFocus();
            ediNumSerieFunda.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(stikVentolerExterna.getText().toString().isEmpty()){ //chekamos que no este vacia
            stikVentolerExterna.requestFocus();
            stikVentolerExterna.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCableRastreoLlegada.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCableRastreoLlegada.requestFocus();
            ediCableRastreoLlegada.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediSelloPlasticoNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloPlasticoNaviera.requestFocus();
            ediSelloPlasticoNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(!ediOtroSellosLlegada.getText().toString().isEmpty()){ //este es opcional... si esta vacio

            FieldOpcional.otrosSellosLLegaEspecif =ediOtroSellosLlegada.getText().toString();
        }





        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_SELLO_LLEGADA)){
            ediFotosSellosLLegada.requestFocus();

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            ediFotosSellosLLegada.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosSellosLLegada.clearFocus();
            ediFotosSellosLLegada.setError(null);

        }




        return true;


    }


    private boolean checkSellosInstaladosIsLleno(){

        LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);


        if(ediTermofrafo1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTermofrafo1.requestFocus();
            ediTermofrafo1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediHoraEncendido1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraEncendido1.requestFocus();
            ediHoraEncendido1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediUbicacion1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacion1.requestFocus();
            ediUbicacion1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediRuma1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRuma1.requestFocus();
            ediRuma1.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediTermofrafo2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTermofrafo2.requestFocus();
            ediTermofrafo2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediHoraEncendido2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraEncendido2.requestFocus();
            ediHoraEncendido2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediUbicacion2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacion2.requestFocus();
            ediUbicacion2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediRuma2.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRuma2.requestFocus();
            ediRuma2.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCandadoqsercon.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCandadoqsercon.requestFocus();
            ediCandadoqsercon.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediSelloNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloNaviera.requestFocus();
            ediSelloNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediCableNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCableNaviera.requestFocus();
            ediCableNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediSelloPlastico.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloPlastico.requestFocus();
            ediSelloPlastico.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCandadoBotella.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCandadoBotella.requestFocus();
            ediCandadoBotella.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediCableExportadora.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCableExportadora.requestFocus();
            ediCableExportadora.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if(ediSelloAdesivoexpor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloAdesivoexpor.requestFocus();
            ediSelloAdesivoexpor.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(esiSelloAdhNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            esiSelloAdhNaviera.requestFocus();
            esiSelloAdhNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(! ediOtherSellos.getText().toString().isEmpty()){ //si esta lleno
        FieldOpcional.otrosSellosInstalaEsp =ediOtherSellos.getText().toString();


        }




return true;
    }



    private boolean checkDatosTransportistaIsLleno(){

        LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediCompaniaTransporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCompaniaTransporte.requestFocus();
            ediCompaniaTransporte.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediNombreChofer.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNombreChofer.requestFocus();
            ediNombreChofer.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediCedula.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCedula.requestFocus();
            ediCedula.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediCelular.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCelular.requestFocus();
            ediCelular.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediPLaca.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPLaca.requestFocus();
            ediPLaca.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediMarcaCabezal.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediMarcaCabezal.requestFocus();
            ediMarcaCabezal.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediColorCabezal.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediColorCabezal.requestFocus();
            ediColorCabezal.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        else if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_TRANSPORTISTA)){
            ediFotosLlegadaTransport.requestFocus();

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            ediFotosLlegadaTransport.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosLlegadaTransport.clearFocus();
            ediFotosLlegadaTransport.setError(null);

        }



      return true;

    }





    private boolean checkDataCalibFrutaCalEnfn(){

        //le decimos que esta todo bien y omitiremos estos datos....
        return true;
    }





//upload data...

//descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad...
private TextInputEditText[] creaArryOfTextInputEditText() {

    TextInputEditText [] arrayEditex = {
            ediZona,
            ediHoraInicio,
            ediHoraTermino,
            ediHoraLLegadaContenedor,
            ediHoraSalidaContenedor,
            ediNguiaRemision,
            ediNtargetaEmbarque,
            ediFotosLlegada,
            ediDestino,
            ediVapor,
            ediFotoContenedor,
            ediFotosPposcosecha,
            ediCompaniaTransporte,
            ediNombreChofer,
            ediCedula,
            ediCelular,
            ediPLaca,
            ediMarcaCabezal,
            ediColorCabezal,
            ediFotosLlegadaTransport,

            ediTare,
            ediBooking,
            ediMaxGross,
            ediNumSerieFunda,
            stikVentolerExterna,
            ediCableRastreoLlegada,
            ediSelloPlasticoNaviera,
            ediOtroSellosLlegada,
            ediFotosSellosLLegada,

            ediTermofrafo1,
            ediHoraEncendido1,
            ediUbicacion1,
            ediRuma1,
            ediTermofrafo2,
            ediHoraEncendido2,
            ediUbicacion2,
            ediRuma2,
            ediCandadoqsercon,
            ediSelloNaviera,
            ediCableNaviera,
            ediSelloPlastico,
            ediCandadoBotella,
            ediCableExportadora,
            ediSelloAdesivoexpor,
            esiSelloAdhNaviera,
            ediOtherSellos,


    } ;


    return arrayEditex;
}


    void addInfotomap(ArrayList<ImagenReport>listImagenReports){
        ImagenReport.hashMapImagesData= new HashMap<>();

        //agregamos adata al mapusnado un bucle

        for(int indice2=0; indice2<listImagenReports.size(); indice2++){

            ImagenReport currentImareportObj=listImagenReports.get(indice2);

            ImagenReport.hashMapImagesData.put(currentImareportObj.getUniqueIdNamePic(),currentImareportObj);

        }


    }

    void addImagesInRecyclerviews(ArrayList<ImagenReport>listImagenReports){

        //agregamos data al map


        RecyclerView recyclerView= findViewById(R.id.recyclerView);

        //si la imagen es la imagen de fotos llegada INICLIZAMOS ASI
        if(currentTypeImage== Variables.FOTO_LLEGADA)  {
            recyclerView= findViewById(R.id.recyclerView);


        }
        else if (currentTypeImage==Variables.FOTO_PROD_POSTCOSECHA){
            recyclerView= findViewById(R.id.recyclerViewPostcosecha);
            // at last set adapter to recycler view.

        }

        else if (currentTypeImage==Variables.FOTO_TRANSPORTISTA){
            recyclerView = findViewById(R.id.recyclerVieDatsTransport);

        }

        else if (currentTypeImage==Variables.FOTO_CONTENEDOR){
            recyclerView = findViewById(R.id.recyclerViewDatosContenedor);
        }


        else if (currentTypeImage==Variables.FOTO_SELLO_LLEGADA){
            recyclerView = findViewById(R.id.recyclerViewSellosLlegada);

        }





        RecyclerViewAdapter adapter=new RecyclerViewAdapter(listImagenReports,this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);


        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        eventoBtnclicklistenerDelete(adapter);


    }




    void createlistsForReciclerviewsImages(ArrayList<ImagenReport>listImagenReports){

        //  addInfotomap(listImagenReports);


        ArrayList<ImagenReport>lisFiltrada;

        int []arrayTiposImagenes={Variables.FOTO_LLEGADA,Variables.FOTO_PROD_POSTCOSECHA,Variables.FOTO_TRANSPORTISTA,Variables.FOTO_SELLO_LLEGADA,Variables.FOTO_CONTENEDOR};

        for(int indice=0; indice<arrayTiposImagenes.length; indice++){

            lisFiltrada=new ArrayList<>();

            for(int indice2=0; indice2<listImagenReports.size(); indice2++){

                if(listImagenReports.get(indice2).getTipoImagenCategory()==arrayTiposImagenes[indice]){ //entonces usamos este

                    lisFiltrada.add(listImagenReports.get(indice2));


                }

            }

            currentTypeImage=arrayTiposImagenes[indice];
            //lalamos el recicler que
            addImagesInRecyclerviews(lisFiltrada);


        }


        Variables.modoRecicler=Variables.SELEC_AND_TAKE_iMAGES;

        //  addInfotomap(listImagenReports);


    }




    private void calibracionFutaCalendarioEnfunde(){

        TextInputEditText ediCandadoqsercon;


    }


}
