package com.tiburela.qsercom.activities.formularios;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formulariosPrev.FormularioControlCalidadPreview;
import com.tiburela.qsercom.adapters.CustomAdapter;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.CheckedAndAtatch;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformsRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.ConnectionReceiver;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.PerecentHelp;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tiburela.qsercom.R;


public class ActivityContenedores extends AppCompatActivity implements View.OnClickListener , View.OnTouchListener ,ConnectionReceiver.ReceiverListener{
    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    public static Context context;
    ArrayList<ControlCalidad>listForms=new ArrayList<>();
    ArrayList<CheckedAndAtatch>listForms2=new ArrayList<>();

    private final int CODE_TWO_PERMISIONS =12;

    private final int WRITE_EXTERNAL_STORAGE=13;


    BottomSheetDialog bottomSheetDialog;
     ImageView imgAtachVinculacion;
    RecyclerView reciclerViewBottomSheet;
    private int currentTypeImage=0;
     TextView    txtAdviseer;
     TextView txtAdviserDesvicunlar;
    ProgressBar progressBarFormulario;
    TextInputEditText ediExportadoraProcesada ;
    TextInputEditText ediExportadoraSolicitante;
    TextInputEditText ediMarca ;

    TextInputEditText ediSemana;

    TextInputEditText ediFecha;
    TextInputEditText ediProductor;
    TextInputEditText ediHacienda;
    TextInputEditText ediCodigo;
    TextInputEditText ediInscirpMagap;
    TextInputEditText ediPemarque;
    TextInputEditText ediZona;
    TextInputEditText ediHoraInicio;
    TextInputEditText ediHoraTermino;
    TextInputEditText ediHoraLLegadaContenedor;
    TextInputEditText ediHoraSalidaContenedor;
    TextInputEditText ediNguiaRemision;
    TextInputEditText edi_nguia_transporte;
    TextInputEditText ediNtargetaEmbarque;
    TextInputEditText ediNhojaEvaluacion;
    TextInputEditText ediObservacion;
    TextInputEditText ediEmpacadora;
    TextInputEditText ediContenedor;
    TextInputEditText ediPPC01;
    TextInputEditText ediPPC02;
    TextInputEditText ediPPC03;
    TextInputEditText ediPPC04;
    TextInputEditText ediPPC05;
    TextInputEditText ediPPC06;
    TextInputEditText ediPPC07;
    TextInputEditText ediPPC08;
    TextInputEditText ediPPC09;
    TextInputEditText ediPPC010;
    TextInputEditText ediPPC011;
    TextInputEditText ediPPC012;
    TextInputEditText ediPPC013;
    TextInputEditText ediPPC014;
    TextInputEditText ediPPC015;
    TextInputEditText ediPPC016;
    TextInputEditText ediDestino;
    TextInputEditText ediNViaje;
    TextInputEditText ediTipoContenedor;
    TextInputEditText ediVapor;
    TextInputEditText ediFotoContenedor;
    TextInputEditText ediFotosPposcosecha;
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;
    TextInputEditText ediNumContenedor;

    TextInputEditText ediExtCalid;
    TextInputEditText  ediExtRodillo;
    TextInputEditText ediExtGancho;
    TextInputEditText ediExtCalidCi;
    TextInputEditText ediExtRodilloCi;
    TextInputEditText ediExtGanchoCi;


    TextInputEditText ediBalanza;
    TextInputEditText ediFuenteAgua;
    TextInputEditText ediAguaCorrida;
    TextInputEditText ediLavadoRacimos;
    TextInputEditText ediFumigacionClin1;
    TextInputEditText ediTipoBoquilla;
    TextInputEditText ediCajasProcDesp;
    TextInputEditText ediRacimosCosech;
    TextInputEditText ediRacimosRecha;
    TextInputEditText ediRacimProces;


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

    TextInputEditText ediCondicionBalanza;
    TextInputEditText ediTipodeCaja;
    TextInputEditText ediTipoPlastico;
    TextInputEditText ediTipoBalanza;
    TextInputEditText editipbalanzaRepeso;
    TextInputEditText ediUbicacionBalanza;

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
    LinearLayout linLayoutHeader2;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;
    LinearLayout linLayoutHeader8;



    Spinner spinnerSelectZona;
    Spinner spinnerCondicionBalanza;
    Spinner spinnertipoCaja;
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza ;
    Spinner spinnertipodeBlanzaRepeso ;
    Spinner spinnerubicacionBalanza ;

    Spinner spFuenteAgua ;
    Spinner spFumigaCorL1 ;
    Spinner spTipoBoquilla ;

    Switch switchContenedor;
    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;
    Switch switchLavdoRacimos;
    Switch swAguaCorrida;


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



    @Override
    protected void onStart() {
        super.onStart();

        Auth.initAuth(ActivityContenedores.this);
        Auth.signInAnonymously(ActivityContenedores.this);

Log.i("hellosweer","se ehjecitp onstart");

        if(Variables.hayUnFormIncompleto){
            AddDataFormOfSharePrefe() ;
            Variables.hayUnFormIncompleto=false;

        }



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
        setContentView(R.layout.activity_formulario);


        context=getApplicationContext();
        Variables.activityCurrent=Variables.FormContenedores;
        CustomAdapter.idsFormsVinucladosCntres=null;//reseteamos

        ImagenReport.hashMapImagesData=new HashMap<>();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            hayUnformularioIcompleto = extras.getBoolean("ActivitymenuKey");

            //The key argument here must match that used in the other activity
        }




        UNIQUE_ID_iNFORME= UUID.randomUUID().toString();

      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        Auth.initAuth(this);

        StorageData. initStorageReference();


        findViewsIds();
        configCertainSomeViewsAliniciar();
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
        TimePickerDialog  picker = new TimePickerDialog(ActivityContenedores.this,
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
    void selecionaFecha(){


        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog  picker = new DatePickerDialog(ActivityContenedores.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        ediFecha.setText(daySemana+"/"+mes+"/"+year);

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

        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

        disableEditText(ediHoraLLegadaContenedor);//here
        disableEditText(ediHoraSalidaContenedor);


        disableEditText(ediContenedor);
        disableEditText(ediFotosLlegada);

        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);

        disableEditText(ediHoraEncendido1);
        disableEditText(ediHoraEncendido2);


    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar

        ediExportadoraProcesada=findViewById(R.id.ediExportadoraProcesada);
        ediExportadoraSolicitante =findViewById(R.id.ediExportadoraSolicitante);
        ediMarca=findViewById(R.id.ediMarca);

         ediExtCalid=findViewById(R.id.ediExtCalid);
          ediExtRodillo=findViewById(R.id.ediExtRodillo);
         ediExtGancho=findViewById(R.id.ediExtGancho);
         ediExtCalidCi=findViewById(R.id.ediExtCalidCi);
         ediExtRodilloCi=findViewById(R.id.ediExtRodilloCi);
        ediExtGanchoCi=findViewById(R.id.ediExtGanchoCi);



        ediEmpacadora=findViewById(R.id.ediEmpacadora);

        imgAtachVinculacion=findViewById(R.id.imgAtachVinculacion);

         ediSemana=findViewById(R.id.ediCajas3);
         ediFecha=findViewById(R.id.ediCajas7);
         ediProductor=findViewById(R.id.ediCodigoN2);
         ediHacienda=findViewById(R.id.ediCodigoN8);
         ediCodigo=findViewById(R.id.ediCodigoN7);
         ediInscirpMagap=findViewById(R.id.ediCajas10);
         ediPemarque=findViewById(R.id.ediProductor10);
        ediNtargetaEmbarque=findViewById(R.id.ediNtargetaEmbarque);
         ediZona=findViewById(R.id.ediZona);
         ediHoraInicio=findViewById(R.id.ediHoraInicio);
         ediHoraTermino=findViewById(R.id.ediHoraTermino);
         ediNguiaRemision=findViewById(R.id.ediNguiaRemision);
         edi_nguia_transporte=findViewById(R.id.edi_nguia_transporte);
        edi_nguia_transporte=findViewById(R.id.edi_nguia_transporte);
         ediNhojaEvaluacion=findViewById(R.id.ediNhojaEvaluacion);
        spinnerSelectZona = findViewById(R.id.spinnerZona);
        ediObservacion = findViewById(R.id.ediObservacion);
        ediFotosLlegada=findViewById(R.id.ediFotosLlegada);




        ediTare=findViewById(R.id.ediTare);
        ediBooking=findViewById(R.id.ediBooking);
        ediMaxGross=findViewById(R.id.ediMaxGross);
        ediNumSerieFunda=findViewById(R.id.ediNumSerieFunda);
        stikVentolerExterna=findViewById(R.id.stikVentolerExterna);
        ediCableRastreoLlegada=findViewById(R.id.ediCableRastreoLlegada);
        ediSelloPlasticoNaviera=findViewById(R.id.ediSelloPlasticoNaviera);
        ediOtroSellosLlegada=findViewById(R.id.ediOtroSellosLlegada);
        ediFotosSellosLLegada=findViewById(R.id.ediFotosSellosLLegada);

        ediEnsunchado=findViewById(R.id.ediEnsunchado);
        ediBalanzaRepeso=findViewById(R.id.ediBalanzaRepeso);


        ediBalanza=findViewById(R.id.ediBalanza);
        ediFuenteAgua=findViewById(R.id.ediFuenteAgua);
        ediAguaCorrida=findViewById(R.id.ediAguaCorrida);
        ediLavadoRacimos=findViewById(R.id.ediLavadoRacimos);
        ediFumigacionClin1=findViewById(R.id.ediFumigacionClin1);
        ediTipoBoquilla=findViewById(R.id.ediTipoBoquilla);
        ediCajasProcDesp=findViewById(R.id.ediCajasProcDesp);
        ediRacimosCosech=findViewById(R.id.ediRacimosCosech);

        ediRacimosRecha=findViewById(R.id.ediRacimosRecha);
        ediRacimProces=findViewById(R.id.ediRacimProces);
        ediNumContenedor=findViewById(R.id.ediNumContenedor);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader2 =findViewById(R.id.linLayoutHeader2);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);

        linLayoutHeader8 =findViewById(R.id.linLayoutHeader8);

         spFuenteAgua =findViewById(R.id.spFuenteAgua);
         spFumigaCorL1=findViewById(R.id.spFumigaCorL1) ;
         spTipoBoquilla=findViewById(R.id.spTipoBoquilla) ;
         switchLavdoRacimos =findViewById(R.id.switchLavdoRacimos);
        swAguaCorrida=findViewById(R.id.swAguaCorrida);

        switchContenedor=findViewById(R.id.switchContenedor);
        ediContenedor=findViewById(R.id.ediContenedor);

         imBatach=findViewById(R.id.imbAtach);
         imBtakePic=findViewById(R.id.imbTakePic);

        ediPPC01=findViewById(R.id.ediPPC01);
        ediPPC02=findViewById(R.id.ediPPC02);
        ediPPC03=findViewById(R.id.ediPPC03);
        ediPPC04=findViewById(R.id.ediPPC04);
        ediPPC05=findViewById(R.id.ediPPC05);
        ediPPC06=findViewById(R.id.ediPPC06);
        ediPPC07=findViewById(R.id.ediPPC07);
        ediPPC08=findViewById(R.id.ediPPC08);
        ediPPC09=findViewById(R.id.ediPPC09);
        ediPPC010=findViewById(R.id.ediPPC010);
        ediPPC011=findViewById(R.id.ediPPC011);
        ediPPC012=findViewById(R.id.ediPPC012);
        ediPPC013=findViewById(R.id.ediPPC013);
        ediPPC014=findViewById(R.id.ediPPC014);
        ediPPC015=findViewById(R.id.ediPPC015);
        ediPPC016=findViewById(R.id.ediPPC016);


          ediDestino=findViewById(R.id.ediDestino);
          ediNViaje=findViewById(R.id.ediNViaje);
          ediVapor=findViewById(R.id.ediVapor);

         // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
          //ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
        ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);


        ediTipoContenedor=findViewById(R.id.ediTipoContenedor);

        ediFotoContenedor=findViewById(R.id.ediFotoContenedor);

        progressBarFormulario=findViewById(R.id.progressBarFormulario);

        ediFotosPposcosecha=findViewById(R.id.ediFotosPposcosecha);

        ediCondicionBalanza=findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja=findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico=findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza=findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso=findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza=findViewById(R.id.ediUbicacionBalanza);



        ediTermofrafo1=findViewById(R.id.ediTermofrafo1);
        ediHoraEncendido1=findViewById(R.id.ediHoraEncendido1);
        ediUbicacion1=findViewById(R.id.ediUbicacion1);
        ediRuma1=findViewById(R.id.ediRuma1);
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


         spinnerCondicionBalanza=  findViewById(R.id.spinnerCondicionBalanza);
         spinnertipoCaja =  findViewById(R.id.spinnertipoCaja);
         spinnertipodePlastico = findViewById(R.id.spinnertipodePlastico);
         spinnertipodeBlanza =  findViewById(R.id.spinnertipodeBlanza);
         spinnertipodeBlanzaRepeso =  findViewById(R.id.spinnertipodeBlanzaRepeso);
         spinnerubicacionBalanza =  findViewById(R.id.spinnerubicacionBalanza);

         switchHaybalanza=findViewById(R.id.switchHaybalanza);
         switchHayEnsunchado=findViewById(R.id.switchHayEnsunchado);
         switchBalanzaRep=findViewById(R.id.switchBalanzaRep);



    }


    private void addClickListeners( ) {

        /**todos add a todos clicklistener de la implemntacion*/


        imBtakePic.setOnClickListener(this);
        imBatach.setOnClickListener(this);

        imgAtachVinculacion.setOnClickListener(this);

         imbAtach_transportista.setOnClickListener(this);
         imbTakePicTransportista.setOnClickListener(this);
         imbAtachSellosLlegada.setOnClickListener(this);
         imbTakePicSellosLLegada.setOnClickListener(this);
         imbAtachDatosContenedor.setOnClickListener(this);
         imbTakePicDatosContenedor.setOnClickListener(this);
         imbAtachPrPostcosecha.setOnClickListener(this);
         imbTakePicPrPostcosecha.setOnClickListener(this);

        ediHoraEncendido1.setOnClickListener(this);
        ediHoraEncendido2.setOnClickListener(this);



        linLayoutHeader2.setOnClickListener(this);
        linLayoutHeader1.setOnClickListener(this);
        linLayoutHeader3.setOnClickListener(this);
        linLayoutHeader4.setOnClickListener(this);
        linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
        linLayoutHeader8.setOnClickListener(this);

        ediFecha.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);

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



      /*
        if(!checkPermission()){

            requestPermission();
            // checkPermission2();


        }
      */

       switch (view.getId()) {


           case R.id.linLayoutHeader1:
               LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);

               if(layoutContainerSeccion1.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion1);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion1);
               }
               break; //



           case R.id.linLayoutHeader2:
               LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);

               if(layoutContainerSeccion2.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion2);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion2);
               }
               break; //

           case R.id.linLayoutHeader3:
               LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);

               if(layoutContainerSeccion3.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion3);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion3);
               }
               break; //


           case R.id.linLayoutHeader4:
               LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);

               if(layoutContainerSeccion4.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion4);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion4);
               }
               break; //



           case R.id.linLayoutHeader5:
               LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

               if(layoutContainerSeccion5.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion5);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion5);
               }
               break; //

           case R.id.linLayoutHeader6:
               LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);

               if(layoutContainerSeccion6.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion6);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion6);
               }
               break; //


           case R.id.linLayoutHeader7:
               LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerSeccion7);

               if(layoutContainerSeccion7.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion7);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion7);
               }
               break; //
           case R.id.linLayoutHeader8:
               LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);

               if(layoutContainerSeccion8.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion8);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion8);
               }
               break; //

           case R.id.ediCajas7:
              // Utils.closeKeyboard(ActivityContenedores.this);

               selecionaFecha();

               break; //



           case R.id.ediHoraInicio:
              // Utils.closeKeyboard(ActivityContenedores.this);

               showingTimePicker(view);

               break; //

           case R.id.ediHoraTermino:
             // Utils.closeKeyboard(ActivityContenedores.this);
               showingTimePicker(view);

               break; //
           case R.id.ediHoraSalidaContenedor:
               // Utils.closeKeyboard(ActivityContenedores.this);
               showingTimePicker(view);

               break; //


           case R.id.ediHoraLLegadaContenedor:
               // Utils.closeKeyboard(ActivityContenedores.this);
               showingTimePicker(view);

               break; //

           case R.id.ediTipoEmp2:
               // Utils.closeKeyboard(ActivityContenedores.this);
               showingTimePicker(view);

               break; //

           case R.id.ediHoraEncendido2:
               // Utils.closeKeyboard(ActivityContenedores.this);
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


           case R.id.imgAtachVinculacion:

               ArrayList<String>listIdSvINCULADOS;
               listIdSvINCULADOS=generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres);
               listForms= new ArrayList<>();
               listForms2= new ArrayList<>();

               if(listIdSvINCULADOS.size()>0 ){  //si existen vinuclados DESCRAGAMOS los informes viculados usando los ids uniqe id

                   showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,true);

                        for(String value: listIdSvINCULADOS){

                             Log.i("salero","se ejecuto esto veces");
                            dowloadReportsVinucladosAndSetinRecycler(value,listIdSvINCULADOS.size(),listIdSvINCULADOS);
                        }


                }else{
                   showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,false);
               }

               break;



       }

        //aqui o


    }

    private ArrayList<String> generateLISTbyStringVinculados(String ValueLineViculados ){
        CustomAdapter.idOFfORMScontrolCaldVds = new HashMap<>();

                 ArrayList<String>listIdSvINCULADOS= new ArrayList<>();



                 if(ValueLineViculados!=null){
                     String [] miarrayiNFORMESvinc = ValueLineViculados.split(",");
                     Log.i("comerciales","el size de aara es "+miarrayiNFORMESvinc.length);

                     if(miarrayiNFORMESvinc.length >0) {

                         for(String value : miarrayiNFORMESvinc){
                             listIdSvINCULADOS.add(value);
                             Log.i("comerciales","el key sera "+value);

                            CustomAdapter.idOFfORMScontrolCaldVds.put(value,value);

                     }


                     }

                     /*
                     else{

                         listIdSvINCULADOS.add(ValueLineViculados);
                         Log.i("datamapitkka","no es mayor a 1 y el value es "+listIdSvINCULADOS.size());

                     }
*/

                     Log.i("comerciales","el numero de reposrtes vincualdos es: "+listIdSvINCULADOS.size());


                 }




                   return listIdSvINCULADOS;
    }


private void setDataInRecyclerOfBottomSheet(RecyclerView reciclerView, ArrayList<CheckedAndAtatch>lista,boolean esReportsVinculadosMod) {
    Log.i("samerr", "se llamo setDataInRecyclerOfBottomSheet y esl zie es  " + lista.size());

    //Button  btnSaveCambiosxxx=bottomSheetDialog.findViewById(R.id.btnSaveCambiosxxx);

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

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityContenedores.this);
    CustomAdapter adapter = new CustomAdapter(ActivityContenedores.this, lista, generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));
    //  this.adapter.setPlayPauseClickListener(this);
    reciclerView.setLayoutManager(layoutManager);
    reciclerView.setAdapter(adapter);


    adapter.setOnItemClickListener(new CustomAdapter.ClickListener() {
        @Override
        public void onItemClick(int position, View v) {
            Variables.currenControlCalReport=listForms.get(position);

      startActivity(new Intent(ActivityContenedores.this, FormularioControlCalidadPreview.class));


           bottomSheetDialog.dismiss();

        }
    });

}


    private void showReportsAndSelectOrDeleteVinuclados(Context context,boolean existeValues) {


        bottomSheetDialog = new BottomSheetDialog(context);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_ver_atachx);
        bottomSheetDialog.setCancelable(false);

//        CheckBox checkBx1 = bottomSheetDialog.findViewById(R.id.checkBx1);
        reciclerViewBottomSheet =bottomSheetDialog.findViewById(R.id.mirecyclerViewAtach);
        Spinner spinner=bottomSheetDialog.findViewById(R.id.spinnerSelectrodate);
        // spinner.setSelection(posicionSelectedSpinnerx);


        // reciclerView.setHasFixedSize(true);
           txtAdviseer=bottomSheetDialog.findViewById(R.id.txtAdviseer);
           txtAdviserDesvicunlar=bottomSheetDialog.findViewById(R.id.txtAdviserDesvicunlar);
           ImageView imgClose=bottomSheetDialog.findViewById(R.id.imgClose);

           TextView txtNumReportsVinclds=findViewById(R.id.txtNumReportsVinclds);


        if(existeValues){
            txtAdviseer.setVisibility(TextView.GONE);

        }




        bottomSheetDialog.show();


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculads));

                bottomSheetDialog.dismiss();

            }
        });





          /////
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selecionado=spinner.getSelectedItem().toString();
                Calendar cal = Calendar.getInstance();

                Calendar cald2 = Calendar.getInstance();


             //   idsFormsControlCalidVinculados=generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres);

                if(selecionado.equals("Hoy")) {

                   // long timeCurrent = new Date().getTime();
                    cal.add(Calendar.DATE, -0);
                    cald2.add(Calendar.DATE,0);

                  //  dowloadEspecificReprtControCalidadVinculados();

                    /**tambien chekamos que si estan chekeeds...*/

                    dowloadinformesby_RangeDateAndCallShowSheetB(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));

                    //  System.out.println("Date = "+ cal.getTimeInMillis());

                }

               else if(selecionado.equals("Ayer")) {

                    cal.add(Calendar.DATE, -1);
                    cald2.add(Calendar.DATE,0);

                    dowloadinformesby_RangeDateAndCallShowSheetB(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));

                    //  System.out.println("Date = "+ cal.getTimeInMillis());

                }





                else if(selecionado.equals("Ultimos 7 dias")) {

                    cal.add(Calendar.DATE, -7);
                    cald2.add(Calendar.DATE,0);


                    dowloadinformesby_RangeDateAndCallShowSheetB(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));



                    Log.i("sabemosd","la data "+cal.getTimeInMillis());

                    //  System.out.println("Date = "+ cal.getTimeInMillis());

                }


                else if(selecionado.equals("Ultimos 15 dias")) {

                    cal.add(Calendar.DATE, -15);
                    cald2.add(Calendar.DATE,0);

                    dowloadinformesby_RangeDateAndCallShowSheetB(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));

                    Log.i("sabemosd","la data "+cal.getTimeInMillis());

                }

                else if(selecionado.equals("Ultimos 30 dias")) {

                    cal.add(Calendar.DATE, -30);
                    cald2.add(Calendar.DATE,0);

                    dowloadinformesby_RangeDateAndCallShowSheetB(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));

                    Log.i("sabemosd","la data "+cal.getTimeInMillis());
                }


                else if(selecionado.equals("Reportes Vinculados")) {


                    if(CustomAdapter.idsFormsVinucladosCntres!=null){  //si existen vinuclados DESCRAGAMOS ESTOS Y OTTRO BY DATE
                        if(CustomAdapter.idsFormsVinucladosCntres.length()>0){


                            dowloadinformesby_RangeDateAndCallShowSheetB(cal.getTimeInMillis(),cald2.getTimeInMillis(),generateLISTbyStringVinculados(CustomAdapter.idsFormsVinucladosCntres));


                        }
                    }else{
                        txtAdviseer.setText("No existe Ningun Reporte Vinculado");
                        txtAdviseer.setVisibility(TextView.VISIBLE);
                    }


                    Log.i("sabemosd","la data "+cal.getTimeInMillis());
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






    }



    private void dowloadReportsVinucladosAndSetinRecycler(String reportidToSearch, int SizeArray, ArrayList<String>listIdSvINCULADOS ) {
        Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);

        RealtimeDB.initDatabasesRootOnly();


          //  Query query = mDatabase2.child("Clientes").orderByChild("userIdUnique").equalTo(userCurrent.getUserIdUnique());

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("uniqueId").equalTo(reportidToSearch);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ControlCalidad  user=ds.getValue(ControlCalidad.class);
                    Log.i("salero","encontrado uno ");

                    listForms.add(user);
                    listForms2.add(new CheckedAndAtatch(user.getSimpleDate(),user.getHacienda(),"Control calidad",true,String.valueOf(user.getUniqueId())));
                }



                //cuando las descrague todos
                Log.i("salero","el list forms size es "+listForms.size());

                Log.i("salero","el listIdSvINCULADOS size es "+listIdSvINCULADOS.size());

                if(listForms2.size() ==listIdSvINCULADOS.size()){

                    //cargamos la info en el sheet cargado
                    setDataInRecyclerOfBottomSheet(reciclerViewBottomSheet,listForms2,true);

                    //  showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,true);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

                /*
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ControlCalidad  user=ds.getValue(ControlCalidad.class);
                    Log.i("salero","encontrado uno ");

                    listForms.add(user);

                }

                //cuando las descrague todos
                Log.i("salero","el list forms size es "+listForms.size());

                Log.i("salero","el listIdSvINCULADOS size es "+listIdSvINCULADOS.size());

                if(listForms.size() ==listIdSvINCULADOS.size()){


                    //cargamos la info en el sheet cargado
                    setDataInRecyclerOfBottomSheet(reciclerViewBottomSheet,listForms,true);


                  //  showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,true);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersdRef.addValueEventListener(eventListener);
*/


    }


    private void dowloadEspecificReprtControCalidadVinculadosONLY(String idInformesVinculadosContCald ) {

        String inicialStringCheked =idInformesVinculadosContCald;

        String [] miarrayiNFORMESvinc = inicialStringCheked.split(",");
        RealtimeDB.initDatabasesRootOnly();


        ArrayList<String>listIdSvINCULADOS= new ArrayList<>();

        //creamos una minilista con los ids vinculados....
        for( String value: miarrayiNFORMESvinc){
            listIdSvINCULADOS.add(value);

        }


        //reocorremos
        for(String idCurrentElement : miarrayiNFORMESvinc){

            dowloadReportsVinucladosAndSetinRecycler(idCurrentElement,miarrayiNFORMESvinc.length,listIdSvINCULADOS);

        }


    }



    private void dowloadEspecificReprtControCalidadVinculados(String idInformesVinculadosContCald ,String rangeSearch ) {

        String inicialStringCheked =idInformesVinculadosContCald;

        String [] miarrayiNFORMESvinc = inicialStringCheked.split(",");
        RealtimeDB.initDatabasesRootOnly();


        ArrayList<String>listIdSvINCULADOS= new ArrayList<>();

        //creamos una minilista con los ids vinculados....
        for( String value: miarrayiNFORMESvinc){
            listIdSvINCULADOS.add(value);

        }


        //reocorremos
        for(String idCurrentElement : miarrayiNFORMESvinc){

            dowloadReportsVinucladosLISTcONTROLcalidad(idCurrentElement,miarrayiNFORMESvinc.length,listIdSvINCULADOS,rangeSearch);

        }


    }


    //informes especificos pueden servir para crgar solo unos especificos.......
    //y si queremos s
    private void dowloadReportsVinucladosLISTcONTROLcalidad(String reportidToSearch, int SizeArray , ArrayList<String>idsFormsControlCalidVinculados,String selecionado) {

        RealtimeDB.initDatabasesRootOnly();
        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").child(reportidToSearch);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listForms= new ArrayList<>();


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ControlCalidad  user=ds.getValue(ControlCalidad.class);

                    listForms.add(user);

                }

                //cuando las descrague todos
                if(listForms.size() ==SizeArray){

                //dscrgamos otros de los ultimos 7 dias...
                    Calendar cal = Calendar.getInstance();
                    Calendar cald2 = Calendar.getInstance();



                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        usersdRef.addValueEventListener(eventListener);



    }

    void dowloadinformesby_EspecificDate(String dateSelecionado,ArrayList<String>idsFormsControlCalidVinculados){

        RealtimeDB.initDatabasesRootOnly();
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ControlCalidad controlcalidad=ds.getValue(ControlCalidad.class);

                    if(controlcalidad!=null){
                        listForms.add(controlcalidad);
                    }

                }

                     boolean existValues=false;

                    if(listForms.size()>0){

                        existValues=true;
                    }

              //  showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,listForms,idsFormsControlCalidVinculados,existValues,posicionSelectedSpinner);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

    void dowloadinformesby_RangeDateAndCallShowSheetB(long desdeFecha, long hastFecha, ArrayList<String>idsFormsControlCalidVinculadosOmit){
        listForms= new ArrayList<>();
        listForms2= new ArrayList<>();

        RealtimeDB.initDatabasesRootOnly();
       // Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("timeDateMillis").startAt(desdeFecha).endAt(hastFecha);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ControlCalidad controlcalidad=ds.getValue(ControlCalidad.class);


                      //agregamos solo los que no esten en esta lista..
                    if(controlcalidad!=null){
                        listForms.add(controlcalidad);

                        if(idsFormsControlCalidVinculadosOmit !=null){

                            if(idsFormsControlCalidVinculadosOmit.contains(controlcalidad.getUniqueId())){

                                listForms2.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",true,String.valueOf(controlcalidad.getUniqueId())));


                            }else {

                                listForms2.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",false,String.valueOf(controlcalidad.getUniqueId())));

                            }




                        }else{
                            listForms2.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",false,String.valueOf(controlcalidad.getUniqueId())));


                        }


                    }


                }

                  //ceramos el anterior ..mostramos este...

                Log.i("samerr","se llamo sedatauncrecicler en dowloadinformesby_RangeDateAndCallShowSheetB ");
                Log.i("samerr","y el size es  "+listForms2.size());

                setDataInRecyclerOfBottomSheet(reciclerViewBottomSheet,listForms2,false);




             //   showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,existValues);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }


    //si queremos solo informes by fecha.............
      //


    private void SHOWamGHidenByNumsReportsVinculados(ArrayList<ControlCalidad>list, LinearLayout arraylayouts[],
                                                     CheckBox arrayCheckBox[],TextView arraytextViwsFila1x[],TextView arraytextViwsFila2x[]){


      int numReportsVinculads =list.size();


        if(numReportsVinculads==0){ //ocultamos los otros 5 restantes
            arraylayouts[0].setVisibility(View.GONE);
            arraylayouts[1].setVisibility(View.GONE);
            arraylayouts[2].setVisibility(View.GONE);
            arraylayouts[3].setVisibility(View.GONE);
            arraylayouts[4].setVisibility(View.GONE);
            arraylayouts[5].setVisibility(View.GONE);


        }



        if(numReportsVinculads==1){ //ocultamos los otros 5 restantes
            arrayCheckBox[0].setChecked(true);
            arrayCheckBox[0].setText(""+"Control Calidad"+"Export.. :"+ list.get(0).getExportadora());
            arraytextViwsFila1x[0].setText(""+"FECHA: "+ list.get(0).getFecha());
            arraytextViwsFila2x[0].setText(""+"HDA Proc: "+ list.get(0).getHacienda());

            //colocamos el primer value..



            arraylayouts[1].setVisibility(View.GONE);
            arraylayouts[2].setVisibility(View.GONE);
            arraylayouts[3].setVisibility(View.GONE);
            arraylayouts[4].setVisibility(View.GONE);
            arraylayouts[5].setVisibility(View.GONE);


        }



        else if(numReportsVinculads==2){

            arrayCheckBox[0].setChecked(true);
            arrayCheckBox[0].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[0].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[0].setText(""+"HDA Proc: "+ list.get(1).getHacienda());

            arrayCheckBox[1].setChecked(true);
            arrayCheckBox[1].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[1].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[1].setText(""+"HDA Proc: "+ list.get(1).getHacienda());



            arraylayouts[2].setVisibility(View.GONE);
            arraylayouts[3].setVisibility(View.GONE);
            arraylayouts[4].setVisibility(View.GONE);
            arraylayouts[5].setVisibility(View.GONE);

        }

        else if(numReportsVinculads==3){


            arrayCheckBox[0].setChecked(true);
            arrayCheckBox[0].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[0].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[0].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[1].setChecked(true);
            arrayCheckBox[1].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[1].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[1].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[2].setChecked(true);
            arrayCheckBox[2].setText(""+"Control Calidad"+"Export.. :"+ list.get(2).getExportadora());
            arraytextViwsFila1x[2].setText(""+"FECHA: "+ list.get(2).getFecha());
            arraytextViwsFila2x[2].setText(""+"HDA Proce: "+ list.get(2).getHacienda());




            arraylayouts[3].setVisibility(View.GONE);
            arraylayouts[4].setVisibility(View.GONE);
            arraylayouts[5].setVisibility(View.GONE);

        }

        else if(numReportsVinculads==4){

            arrayCheckBox[0].setChecked(true);
            arrayCheckBox[0].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[0].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[0].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[1].setChecked(true);
            arrayCheckBox[1].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[1].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[1].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[2].setChecked(true);
            arrayCheckBox[2].setText(""+"Control Calidad"+"Export.. :"+ list.get(2).getExportadora());
            arraytextViwsFila1x[2].setText(""+"FECHA: "+ list.get(2).getFecha());
            arraytextViwsFila2x[2].setText(""+"HDA Proce: "+ list.get(2).getHacienda());



            arrayCheckBox[3].setChecked(true);
            arrayCheckBox[3].setText(""+"Control Calidad"+"Export.. :"+ list.get(3).getExportadora());
            arraytextViwsFila1x[3].setText(""+"FECHA: "+ list.get(3).getFecha());
            arraytextViwsFila2x[3].setText(""+"HDA Proce: "+ list.get(3).getHacienda());




            arraylayouts[4].setVisibility(View.GONE);
            arraylayouts[5].setVisibility(View.GONE);
        }

        else if(numReportsVinculads==5){

            arrayCheckBox[0].setChecked(true);
            arrayCheckBox[0].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[0].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[0].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[1].setChecked(true);
            arrayCheckBox[1].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[1].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[1].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[2].setChecked(true);
            arrayCheckBox[2].setText(""+"Control Calidad"+"Export.. :"+ list.get(2).getExportadora());
            arraytextViwsFila1x[2].setText(""+"FECHA: "+ list.get(2).getFecha());
            arraytextViwsFila2x[2].setText(""+"HDA Proce: "+ list.get(2).getHacienda());



            arrayCheckBox[3].setChecked(true);
            arrayCheckBox[3].setText(""+"Control Calidad"+"Export.. :"+ list.get(3).getExportadora());
            arraytextViwsFila1x[3].setText(""+"FECHA: "+ list.get(3).getFecha());
            arraytextViwsFila2x[3].setText(""+"HDA Proce: "+ list.get(3).getHacienda());


            arrayCheckBox[4].setChecked(true);
            arrayCheckBox[4].setText(""+"Control Calidad"+"Export.. :"+ list.get(4).getExportadora());
            arraytextViwsFila1x[4].setText(""+"FECHA: "+ list.get(4).getFecha());
            arraytextViwsFila2x[4].setText(""+"HDA Proce: "+ list.get(4).getHacienda());



            arraylayouts[5].setVisibility(View.GONE);

        }

        else if(numReportsVinculads==6){  //NJO OLCUTAMOS NINGUNO...


            arrayCheckBox[0].setChecked(true);
            arrayCheckBox[0].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[0].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[0].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[1].setChecked(true);
            arrayCheckBox[1].setText(""+"Control Calidad"+"Export.. :"+ list.get(1).getExportadora());
            arraytextViwsFila1x[1].setText(""+"FECHA: "+ list.get(1).getFecha());
            arraytextViwsFila2x[1].setText(""+"HDA Proce: "+ list.get(1).getHacienda());

            arrayCheckBox[2].setChecked(true);
            arrayCheckBox[2].setText(""+"Control Calidad"+"Export.. :"+ list.get(2).getExportadora());
            arraytextViwsFila1x[2].setText(""+"FECHA: "+ list.get(2).getFecha());
            arraytextViwsFila2x[2].setText(""+"HDA Proce: "+ list.get(2).getHacienda());



            arrayCheckBox[3].setChecked(true);
            arrayCheckBox[3].setText(""+"Control Calidad"+"Export.. :"+ list.get(3).getExportadora());
            arraytextViwsFila1x[3].setText(""+"FECHA: "+ list.get(3).getFecha());
            arraytextViwsFila2x[3].setText(""+"HDA Proce: "+ list.get(3).getHacienda());


            arrayCheckBox[4].setChecked(true);
            arrayCheckBox[4].setText(""+"Control Calidad"+"Export.. :"+ list.get(4).getExportadora());
            arraytextViwsFila1x[4].setText(""+"FECHA: "+ list.get(4).getFecha());
            arraytextViwsFila2x[4].setText(""+"HDA Proce: "+ list.get(4).getHacienda());

            arrayCheckBox[5].setChecked(true);
            arrayCheckBox[5].setText(""+"Control Calidad"+"Export.. :"+ list.get(5).getExportadora());
            arraytextViwsFila1x[5].setText(""+"FECHA: "+ list.get(5).getFecha());
            arraytextViwsFila2x[5].setText(""+"HDA Proce: "+ list.get(5).getHacienda());

        }







    }

    private void takepickNow() {

       // Permisionx.checkPermission(Manifest.permission.CAMERA,1,this, ActivityContenedores.this)

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

            ){
                takePickCamera();

                Log.i("codereister","permiso CONDEIDOIOTOMAMOS FOTO ES IF") ;


            }

            else

            {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                        CODE_TWO_PERMISIONS);



             //   ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE,CODE_TWO_PERMISIONS}
              //          );


                Log.i("codereister","permiso DENEGADO SOLICTAMOS PERMISO") ;

               // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                     //   CODE_TWO_PERMISIONS);

            }




    }

        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {


                            try {


                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedores.this.getContentResolver(),cam_uri);


                               // Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();


                                String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                                showImagesPicShotOrSelectUpdateView(false);

                                //creamos un nuevo objet de tipo ImagenReport
                                ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage,UNIQUE_ID_iNFORME, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityContenedores.this,cam_uri)),horientacionImg);

                                //agregamos este objeto a la lista
                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData,ActivityContenedores.this);


                            }

                         catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


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
                PerecentHelp.checkeamosSiFieldViewIScompletedAndSavePref(vistFieldAnterior,SharePref.KEY_CONTENEDORES);

            }

        }
        return false;
    }



    public void makePermissionRequescAMERA() {


        ActivityCompat.requestPermissions(ActivityContenedores.this, new String[]{Manifest.permission.CAMERA},
                CODE_TWO_PERMISIONS);




    }


    @SuppressLint("ClickableViewAccessibility")
    private void addOnTouchaMayoriaDeViews(){
        ediObservacion.setOnTouchListener(this);
        ediSemana.setOnTouchListener(this);
        ediFecha.setOnTouchListener(this);
        ediProductor.setOnTouchListener(this);
        ediHacienda.setOnTouchListener(this);
        ediCodigo.setOnTouchListener(this);
        ediInscirpMagap.setOnTouchListener(this);
        ediPemarque.setOnTouchListener(this);
        ediHoraInicio.setOnTouchListener(this);
        ediHoraTermino.setOnTouchListener(this);
        ediNguiaRemision.setOnTouchListener(this);
        edi_nguia_transporte.setOnTouchListener(this);
        ediNtargetaEmbarque.setOnTouchListener(this);
        ediNhojaEvaluacion.setOnTouchListener(this);
        spinnerSelectZona.setOnTouchListener(this);

         spinnerCondicionBalanza.setOnTouchListener(this);
         spinnertipoCaja.setOnTouchListener(this);
         spinnertipodePlastico.setOnTouchListener(this);
         spinnertipodeBlanza.setOnTouchListener(this);
         spinnertipodeBlanzaRepeso .setOnTouchListener(this);
         spinnerubicacionBalanza.setOnTouchListener(this);


        switchContenedor.setOnTouchListener(this);
        ediEmpacadora.setOnTouchListener(this);
          imBatach.setOnTouchListener(this);

        imBtakePic.setOnTouchListener(this);

        ediPPC01.setOnTouchListener(this);
        ediPPC02.setOnTouchListener(this);
        ediPPC03.setOnTouchListener(this);
        ediPPC04.setOnTouchListener(this);
        ediPPC05.setOnTouchListener(this);
        ediPPC06.setOnTouchListener(this);
        ediPPC07.setOnTouchListener(this);
        ediPPC08.setOnTouchListener(this);
        ediPPC09.setOnTouchListener(this);
        ediPPC010.setOnTouchListener(this);
        ediPPC011.setOnTouchListener(this);
        ediPPC012.setOnTouchListener(this);
        ediPPC013.setOnTouchListener(this);
        ediPPC014.setOnTouchListener(this);
        ediPPC015.setOnTouchListener(this);
        ediPPC016.setOnTouchListener(this);


        ediHoraLLegadaContenedor.setOnTouchListener(this);
        ediHoraSalidaContenedor.setOnTouchListener(this);
        ediDestino.setOnTouchListener(this);
        ediNViaje.setOnTouchListener(this);

        ediVapor.setOnTouchListener(this);
        ediTipoContenedor.setOnTouchListener(this);

        //HAST AQUI.setOnTouchListener(this);
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



        ediCondicionBalanza.setOnTouchListener(this);
        ediTipodeCaja.setOnTouchListener(this);
        ediBalanza.setOnTouchListener(this);
        ediEnsunchado.setOnTouchListener(this);


        ediTipoPlastico.setOnTouchListener(this);
        ediTipoBalanza.setOnTouchListener(this);


        ediFuenteAgua.setOnTouchListener(this);
        ediAguaCorrida.setOnTouchListener(this);
        ediLavadoRacimos.setOnTouchListener(this);
        ediFumigacionClin1.setOnTouchListener(this);

        ediTipoBoquilla.setOnTouchListener(this);
        ediCajasProcDesp.setOnTouchListener(this);
        ediRacimosCosech.setOnTouchListener(this);


        ediRacimosRecha.setOnTouchListener(this);
        ediRacimProces.setOnTouchListener(this);


    }



/*
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


                            //add VALUES IN TO MAP
                              Variables.currentMapPreferences.put(String.valueOf(view.getId()),editText.getText().toString());

                             //lo guardamos en esa key
                              SharePref.saveMapPreferFields(Variables.currentMapPreferences,SharePref.KEY_CONTENEDORES);

                           //  Utils.addDataMapPreferences(String.valueOf(view.getId()),editText.getText().toString() ,"iduniquehere",ActivityContenedores.this);


                        }
                    }

                }

                else if(editText.getText().toString().isEmpty()) {


                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta vacio");


                    actualizaListStateView(view.getResources().getResourceName(view.getId()),false) ;

                }




                ////si existe lo cambiamos a tru



                else if(! editText.getText().toString().isEmpty()) { //si esta lleno

                    Log.i("idCheck","la data del editext anterior : "+view.getResources().getResourceName(view.getId() )+" esta lleno");

                    actualizaListStateView(view.getResources().getResourceName(view.getId()),true) ;


                    //add VALUES IN TO MAP
                    Variables.currentMapPreferences.put(String.valueOf(view.getId()),editText.getText().toString());

                    //lo guardamos en esa key
                    SharePref.saveMapPreferFields(Variables.currentMapPreferences,SharePref.KEY_CONTENEDORES);



                }



        }




        else if (view.getResources().getResourceName(view.getId()).contains("imbAtach")  ||  view.getResources().getResourceName(view.getId()).contains("imbTakePic")){ //imBtakePic

             //COMPORBAQMOS SI EXISTE AL ME4NOS UN IMAGEN URI LIST..

            if(ImagenReport.hashMapImagesData.size()> 0 ) {
                actualizaListStateView("imbAtach/imbTakePic",true) ;

               Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y contiene al menos una foto");


            }else {

                actualizaListStateView("imbAtach/imbTakePic",false) ;
                Log.i("miodata","el slecionado anteruior es imbAtach/imbTakePic y no contiene fotos");



            }


        }






        //seran mas comprobacion para verificar si imagenes por ejemplo fiueron completadas..
        //otra para radiobutton y otr para otro tipo de view..tec


        actualizaProgressBar();

            }

*/

    private void actualizaListStateView(String idSearch,boolean isEstaLleno){
///


        final String  idview = idSearch.replace(Variables.paqueteName+":id/","");

       //com.tiburela.qsercom:id/ediCodigo") ;

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
                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedores.this.getContentResolver(),result.get(indice));


                              //  Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                                String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                                ImagenReport obcjImagenReport =new ImagenReport("",result.get(indice).toString(),currentTypeImage,UNIQUE_ID_iNFORME, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityContenedores.this,result.get(indice))),horientacionImg);

                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                                showImagesPicShotOrSelectUpdateView(false);


                                //agregamos este objeto a la lista

                                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData,ActivityContenedores.this);


                            } catch (FileNotFoundException e) {
                                Log.i("dateer","lA EXCEPCION ES "+e.getMessage());

                                e.printStackTrace();
                            } catch (IOException e) {

                                Log.i("dateer","lA EXCEPCION ES "+e.getMessage());

                                e.printStackTrace();
                            }


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


    spTipoBoquilla .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String textSelect= spTipoBoquilla.getSelectedItem().toString();
            ediTipoBoquilla.setText(textSelect);
            if(textSelect.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediTipoBoquilla.setText("");
               // actualizaListStateView("spTipoBoquilla",false) ;
            }else {
               // actualizaListStateView("spTipoBoquilla",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });


    spFumigaCorL1 .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String textSelect= spFumigaCorL1.getSelectedItem().toString();
            ediFumigacionClin1.setText(textSelect);
            if(textSelect.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediFumigacionClin1.setText("");
              //  actualizaListStateView("spFumigaCorL1",false) ;
            }else {
              //  actualizaListStateView("spFumigaCorL1",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });



    spFuenteAgua .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String textSelect= spFuenteAgua.getSelectedItem().toString();
            ediFuenteAgua.setText(textSelect);
            if(textSelect.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediFuenteAgua.setText("");
              //  actualizaListStateView("spFuenteAgua",false) ;
            }else {
              //  actualizaListStateView("spFuenteAgua",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });









    spinnerSelectZona .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerSelectZona.getSelectedItem().toString();
                ediZona.setText(zonaEelejida);
                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                 //   actualizaListStateView("spinnerZona",false) ;
                }else {
                  //  actualizaListStateView("spinnerZona",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    spinnerCondicionBalanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String condicion= spinnerCondicionBalanza.getSelectedItem().toString();
            ediCondicionBalanza.setText(condicion);
            if(condicion.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediCondicionBalanza.setText("");
              //  actualizaListStateView("ediCondicionBalanza",false) ;
            }else {
              //  actualizaListStateView("ediCondicionBalanza",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });





    spinnertipoCaja .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String zonaEelejida= spinnertipoCaja.getSelectedItem().toString();
            ediTipodeCaja.setText(zonaEelejida);
            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediTipodeCaja.setText("");
               // actualizaListStateView("ediTipodeCaja",false) ;
            }else {
               // actualizaListStateView("ediTipodeCaja",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });



    spinnertipodePlastico .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String zonaEelejida= spinnertipodePlastico.getSelectedItem().toString();
            ediTipoPlastico.setText(zonaEelejida);
            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediTipoPlastico.setText("");
               // actualizaListStateView("ediTipoPlastico",false) ;
            }else {

              //  actualizaListStateView("ediTipoPlastico",true) ;

            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

    spinnertipodeBlanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String zonaEelejida= spinnertipodeBlanza.getSelectedItem().toString();
            ediTipoBalanza.setText(zonaEelejida);

            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediTipoBalanza.setText("");
              //  actualizaListStateView("ediTipoBalanza",false) ;
            }else {
               // actualizaListStateView("ediTipoBalanza",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });


    spinnertipodeBlanzaRepeso .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String zonaEelejida= spinnertipodeBlanzaRepeso.getSelectedItem().toString();
            editipbalanzaRepeso.setText(zonaEelejida);
            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                editipbalanzaRepeso.setText("");
               // actualizaListStateView("addetiquetaaqui",false) ;
            }else {
              //  actualizaListStateView("addetiquetaaqui",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });



    spinnerubicacionBalanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String zonaEelejida= spinnerubicacionBalanza.getSelectedItem().toString();

            ediUbicacionBalanza.setText(zonaEelejida);

            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediUbicacionBalanza.setText("");
               // actualizaListStateView("ediUbicacionBalanza",false) ;
            }else {
              ///  actualizaListStateView("ediUbicacionBalanza",true) ;
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });


    switchContenedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(switchContenedor.isChecked()){

                ediContenedor.setText(" SI ") ;

            }else {
                ediContenedor.setText(" NO ") ;

            }
        }
    });


    switchLavdoRacimos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(switchLavdoRacimos.isChecked()){

                ediLavadoRacimos.setText(" SI ") ;

            }else {
                ediLavadoRacimos.setText(" NO ") ;

            }
        }
    });




    swAguaCorrida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(swAguaCorrida.isChecked()){

                ediAguaCorrida.setText(" SI ") ;

            }else {
                ediAguaCorrida.setText(" NO ") ;

            }
        }
    });

}


private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg){

        //si es eliminar comprobar aqui
    if(isDeleteImg){

        currentTypeImage=Variables.typeoFdeleteImg;
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



    if(! checkcantidadPostcosechaIsLleno()){

        Log.i("test001","no esta lleno  checkcantidadPostcosechaIsLleno");
        return;
    }else{
        Log.i("test001","si esta lleno checkcantidadPostcosechaIsLleno ");

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


    if(! checkDatosProcesoIsLleno()){
        Log.i("test001","no esta lleno  checkDatosProcesoIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDatosProcesoIsLleno");


    }



    if(! checkDatosHaciendaIsLleno()){
        Log.i("test001","no esta lleno  checkDatosHaciendaIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDatosHaciendaIsLleno");


    }


    if(! checkQueexistminim()){
        Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");

        return;
    }else

    {

        Log.i("test001","si  esta lleno  checkDataCalibFrutaCalEnfn");


    }




    Log.i("test001","toda la data esta completa HUrra ");

    uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

    createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...





}


private void createObjcInformeAndUpload(){

//aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo

    SetInformEmbarque1 informe = new SetInformEmbarque1(ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString(),
            ediMarca.getText().toString(),

            UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
            Integer.parseInt(ediNhojaEvaluacion.getText().toString()), ediZona.getText().toString()
            ,ediProductor.getText().toString(),ediCodigo.getText().toString()
            ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
            ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
            ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
            ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),
            FieldOpcional.observacionOpcional,ediHoraLLegadaContenedor.getText().toString(),ediHoraSalidaContenedor.getText().toString()
            ,ediDestino.getText().toString(),ediNViaje.getText().toString(),ediNumContenedor.getText().toString(),ediVapor.getText().toString(),
            ediTipoContenedor.getText().toString(),ediTare.getText().toString(),ediBooking.getText().toString(),ediMaxGross.getText().toString(),
            ediNumSerieFunda.getText().toString(),stikVentolerExterna.getText().toString(),
            ediCableRastreoLlegada.getText().toString(),ediSelloPlasticoNaviera.getText().toString(),FieldOpcional.otrosSellosLLegaEspecif);


    SetInformEmbarque2 informe2 = new SetInformEmbarque2(UNIQUE_ID_iNFORME,ediTermofrafo1.getText().toString(),ediTermofrafo2.getText().toString()
            ,ediHoraEncendido1.getText().toString(),ediHoraEncendido2.getText().toString(),
            ediUbicacion1.getText().toString(),ediUbicacion2.getText().toString(),ediRuma1.getText().toString(),ediRuma2.getText().toString()
            ,ediCandadoqsercon.getText().toString(),ediSelloNaviera.getText().toString(),ediCableNaviera.getText().toString(),
            ediSelloPlasticoNaviera.getText().toString(),ediCandadoBotella.getText().toString(),ediCableExportadora.getText().toString(),
            ediSelloAdesivoexpor.getText().toString(),esiSelloAdhNaviera.getText().toString(),FieldOpcional.otrosSellosInstalaEsp,
            ediCompaniaTransporte.getText().toString(), ediNombreChofer.getText().toString(),ediCedula.getText().toString(),
            ediCedula.getText().toString(),ediPLaca.getText().toString(),ediMarcaCabezal.getText().toString(),
            ediColorCabezal.getText().toString(),ediCondicionBalanza.getText().toString(),ediTipodeCaja.getText().toString()
            ,switchHaybalanza.isChecked(),switchHayEnsunchado.isChecked(),spinnertipodePlastico.getSelectedItem().toString(),
            switchBalanzaRep.isChecked(),spinnerubicacionBalanza.getSelectedItem().toString(),ediTipoBalanza.getText().toString(),FieldOpcional.tipoDeBalanzaRepesoOpcnal);


    SetInformDatsHacienda informe3= new SetInformDatsHacienda(spFuenteAgua.getSelectedItem().toString(),swAguaCorrida.isChecked(), switchLavdoRacimos.isChecked(),
            spFumigaCorL1.getSelectedItem().toString(),
            spTipoBoquilla.getSelectedItem().toString()


            ,ediCajasProcDesp.getText().toString(),
            ediRacimosCosech.getText().toString(),ediRacimosRecha.getText().toString(),ediRacimProces.getText().toString(),UNIQUE_ID_iNFORME
            ,ediExtCalid.getText().toString(),ediExtCalidCi.getText().toString());

    updateDatosEvaludoresOFinforme3(informe3);




    //agr5egamos la data finalemente
    RealtimeDB.initDatabasesRootOnly();

    generateUniqueIdInformeAndContinuesIfIdIsUnique( informe,informe2,informe3);



}

private void uploadInformeToDatabase( SetInformEmbarque1 informe,SetInformEmbarque2 informe2, SetInformDatsHacienda informe3){

    //Agregamos un nuevo informe
    RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos
    RealtimeDB.addNewInforme(ActivityContenedores.this,informe);
    RealtimeDB.addNewInforme(ActivityContenedores.this,informe2);
    updateCaledarioEnfunde(informe3);
    RealtimeDB.addNewInforme(informe3);
    addProdcutsPostCosechaAndUpload(); //agregamos y subimos los productos postcosecha..


}


    private void updateDatosEvaludoresOFinforme3(SetInformDatsHacienda informe3) {

        if(!ediExtRodillo.getText().toString().trim().isEmpty()){
            informe3.setExtensionistDeRodillo(ediExtRodillo.getText().toString());
            informe3.setCI_extensionistCalid(ediExtRodilloCi.getText().toString());

        }


        if(!ediExtGancho.getText().toString().trim().isEmpty()){
            informe3.setExtensionistDeRodillo(ediExtGancho.getText().toString());
            informe3.setCI_extensionistCalid(ediExtGanchoCi.getText().toString());

        }


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
                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData,ActivityContenedores.this);


                Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);


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
       StorageData.uploadImage(ActivityContenedores.this, ImagenReport.hashMapImagesData);

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







      //  startActivity(new Intent(ActivityContenedores.this,PdfPreviewActivity.class));

        //generamos un pdf con la data que tenemos()

        /*

        PdfMaker.generatePdfReport1(ActivityContenedores.this,ediCodigo.getText().toString(),Integer.parseInt(ediNhojaEvaluacion.getText().toString()),
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
       // int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
      //  return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;


   return true;
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
        if (requestCode == CODE_TWO_PERMISIONS) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("codereisterxcc","permisos concedidos") ;
                    takePickCamera();

                       }

                else{
                    Log.i("codereisterxcc","no se concedieron") ;

                }


    }

    }






    private boolean chekIsLLenoDataContenedor() {

        TextInputEditText  ediDestino=findViewById(R.id.ediDestino);
        TextInputEditText  ediNViaje=findViewById(R.id.ediNViaje);
        TextInputEditText  ediVapor=findViewById(R.id.ediVapor);
        TextInputEditText  ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
       // TextInputEditText  ediHoraSalida=findViewById(R.id.ediHoraSalida);




        return true;

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

        LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);

        if(ediSemana.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSemana.requestFocus();
            ediSemana.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }

         if (ediFecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFecha.requestFocus();
            ediFecha.setError("Debe selecionar una fecha");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }







         if(ediProductor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediProductor.requestFocus();
            ediProductor.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




         if(ediHacienda.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHacienda.requestFocus();
            ediHacienda.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }






         if(ediCodigo.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCodigo.requestFocus();
            ediCodigo.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediInscirpMagap.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediInscirpMagap.requestFocus();
            ediInscirpMagap.setError("Este espacio es obligatorio");
            ediInscirpMagap.setText("_");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediPemarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPemarque.requestFocus();
            ediPemarque.setError("Este espacio es obligatorio");

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

         if(edi_nguia_transporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            edi_nguia_transporte.requestFocus();
            edi_nguia_transporte.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


         if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




           if(ediNhojaEvaluacion.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNhojaEvaluacion.requestFocus();
            ediNhojaEvaluacion.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }



        if(ediEmpacadora.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediEmpacadora.requestFocus();
            ediEmpacadora.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


          if(!ediObservacion.getText().toString().isEmpty()){ //si esta lleno

              FieldOpcional.observacionOpcional=ediObservacion.getText().toString();

        }


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_LLEGADA)){
            ediFotosLlegada.requestFocus();

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            ediFotosLlegada.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;
        }else{

            ediFotosLlegada.clearFocus();
            ediFotosLlegada.setError(null);

        }

        //


        return true;
    }



    private boolean  checkcantidadPostcosechaIsLleno(){
        LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);
        Log.i("camisad" ,"se llamo este ");

        if(ediPPC01.getText().toString().isEmpty() && ediPPC02.getText().toString().isEmpty()&& ediPPC03.getText().toString().isEmpty()
                && ediPPC04.getText().toString().isEmpty()&& ediPPC05.getText().toString().isEmpty()&& ediPPC06.getText().toString().isEmpty()
                && ediPPC07.getText().toString().isEmpty()&& ediPPC08.getText().toString().isEmpty() && ediPPC09.getText().toString().isEmpty()
                && ediPPC010.getText().toString().isEmpty() && ediPPC011.getText().toString().isEmpty() && ediPPC012.getText().toString().isEmpty()
                && ediPPC013.getText().toString().isEmpty() && ediPPC014.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()
                && ediPPC016.getText().toString().isEmpty()
        ){ //chekamos que no este vacia
            ediPPC01.requestFocus();
            ediPPC01.setError("Inserte al menos 1 producto");
            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);

            Log.i("camisad" ,"se eejcuto este");

            return false;

        }



        if(! ediPPC015.getText().toString().isEmpty() && ediPPC016.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC016.requestFocus();
            ediPPC016.setError("Inserte cantidad");

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            return false ;

        }


        if(! ediPPC016.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC015.requestFocus();
            ediPPC015.setError("inserte nombre producto");

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        ///y chekeamos al menos una imagen del pridcutp
        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PROD_POSTCOSECHA)){
            ediFotosPposcosecha.requestFocus();

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            ediFotosPposcosecha.setError("Agregue al menos "+Variables.MINIMO_FOTOS_ALL_CATEGORY+" foto");
            return false;

        }else{

            ediFotosPposcosecha.clearFocus();
            ediFotosPposcosecha.setError(null);

        }


return  true;

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


        if(ediNViaje.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNViaje.requestFocus();
            ediNViaje.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediVapor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediVapor.requestFocus();
            ediVapor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoContenedor.requestFocus();
            ediTipoContenedor.setError("Este espacio es obligatorio");

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




    private boolean checkDatosHaciendaIsLleno(){
        LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);




        if(ediFuenteAgua.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFuenteAgua.requestFocus();
            ediFuenteAgua.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediAguaCorrida.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediAguaCorrida.requestFocus();
            ediAguaCorrida.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediLavadoRacimos.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediLavadoRacimos.requestFocus();
            ediLavadoRacimos.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediFumigacionClin1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFumigacionClin1.requestFocus();
            ediFumigacionClin1.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoBoquilla.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBoquilla.requestFocus();
            ediTipoBoquilla.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediCajasProcDesp.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediRacimosCosech.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediRacimosRecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;
        }

        if(ediRacimProces.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimProces.requestFocus();
            ediRacimProces.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediExtCalid.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalid.requestFocus();
            ediExtCalid.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;
        }



        if(ediExtCalidCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalidCi.requestFocus();
            ediExtCalidCi.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        ///LOS DEMAS DATOS OPCIONALES

        if(!ediExtRodillo.getText().toString().isEmpty() && ediExtRodilloCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtRodilloCi.requestFocus();
            ediExtRodilloCi.setError("Se requiere La C.I");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        if(ediExtRodillo.getText().toString().isEmpty() && !ediExtRodilloCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtRodillo.requestFocus();
            ediExtRodillo.setError("Se requiere el nombre");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(!ediExtGancho.getText().toString().isEmpty() && ediExtGanchoCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtGanchoCi.requestFocus();
            ediExtGanchoCi.setError("Se requiere La C.I");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        if(ediExtGancho.getText().toString().isEmpty() && !ediExtGanchoCi.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediExtGancho.requestFocus();
            ediExtGancho.setError("Se requiere el nombre");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        ///vamos con los datos de semananas y eso




        return true;

    }

    private boolean checkDataCalibFrutaCalEnfn(){

        //le decimos que esta todo bien y omitiremos estos datos....
        return true;
    }

    private boolean checkDatosProcesoIsLleno(){
        LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerSeccion7);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediCondicionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediTipodeCaja.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediCondicionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if(ediTipoPlastico.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediTipoBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(editipbalanzaRepeso.getText().toString().isEmpty()){ //chekamos que no este vacia
            editipbalanzaRepeso.requestFocus();
            editipbalanzaRepeso.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediUbicacionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacionBalanza.requestFocus();
            ediUbicacionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


return true;

    }


private void  addProdcutsPostCosechaAndUpload(){

    ProductPostCosecha producto=new ProductPostCosecha(UNIQUE_ID_iNFORME);
    //creamos un array de editext

    EditText [] editextArray = {ediPPC01,ediPPC02,ediPPC03,ediPPC04,ediPPC05,ediPPC06,ediPPC07,
            ediPPC08,ediPPC09, ediPPC010,ediPPC011,ediPPC012,ediPPC013,ediPPC014,ediPPC015,ediPPC016} ;


    for (int indice =0; indice<editextArray.length; indice++) {
        EditText currentEditext=editextArray[indice];
        if (!currentEditext.getText().toString().isEmpty()){ //si no esta vacioo
            if (!currentEditext.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                 {

                    switch (currentEditext.getId()){

                        case R.id.ediPPC01:
                            producto.alumbre=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC02:
                            producto.bc100=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC03:
                            producto.sb100=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC04:
                            producto.eclipse=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC05:
                            producto.acido_citrico=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC06:
                            producto.biottol=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC07:
                            producto.bromorux=currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC08:
                            producto.ryzuc=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC09:
                            producto.mertec=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC010:
                            producto.sastifar=currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC011:
                            producto.xtrata=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC012:
                            producto.nlarge=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC013:
                            producto.gib_bex=currentEditext.getText().toString();
                            break;



                        case R.id.ediPPC014:
                            producto.cloro=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC015:
                            producto.otro_especifique=currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC016:
                            producto.cantidadOtro=currentEditext.getText().toString();
                            break;


                    }

            }


        }

        //si el editext tiene data lo corregimos usando la propiedad hint


    }



    RealtimeDB.UploadProductosPostCosecha(producto);


}


//upload data...

//descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad...
private TextInputEditText[] creaArryOfTextInputEditText() {

    TextInputEditText [] arrayEditex = {

            ediSemana,
            ediFecha,
            ediProductor,
            ediHacienda,
            ediCodigo,
            ediInscirpMagap,
            ediPemarque,
            ediZona,
            ediHoraInicio,
            ediHoraTermino,
            ediHoraLLegadaContenedor,
            ediHoraSalidaContenedor,
            ediNguiaRemision,
            edi_nguia_transporte,
            ediNtargetaEmbarque,
            ediNhojaEvaluacion,
            ediObservacion,
            ediEmpacadora,
            ediFotosLlegada,
            ediContenedor,
            ediPPC01,
            ediPPC02,
            ediPPC03,
            ediPPC04,
            ediPPC05,
            ediPPC06,
            ediPPC07,
            ediPPC08,
            ediPPC09,
            ediPPC010,
            ediPPC011,
            ediPPC012,
            ediPPC013,
            ediPPC014,
            ediPPC015,
            ediPPC016,
            ediDestino,
            ediNViaje,
            ediTipoContenedor,
            ediVapor,
            ediFotoContenedor,
            ediFotosPposcosecha,
            ediEnsunchado,
            ediBalanzaRepeso,


            ediBalanza,
            ediFuenteAgua,
            ediAguaCorrida,
            ediLavadoRacimos,
            ediFumigacionClin1,
            ediTipoBoquilla,
            ediCajasProcDesp,
            ediRacimosCosech,
            ediRacimosRecha,
            ediRacimProces,


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

            ediCondicionBalanza,
            ediTipodeCaja,
            ediTipoPlastico,
            ediTipoBalanza,
            editipbalanzaRepeso,
            ediUbicacionBalanza,

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


    private boolean checkQueexistminim() {

        TextInputEditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        TextInputEditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        TextInputEditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        TextInputEditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        TextInputEditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        TextInputEditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        TextInputEditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        TextInputEditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        TextInputEditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        TextInputEditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        TextInputEditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        TextInputEditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        TextInputEditText ediPorc14=findViewById(R.id.ediPorc14);
        TextInputEditText ediPorc13=findViewById(R.id.ediPorc13);
        TextInputEditText ediPorc12=findViewById(R.id.ediPorc12);
        TextInputEditText ediPorc11=findViewById(R.id.ediPorc11);
        TextInputEditText ediPorc10=findViewById(R.id.ediPorc10);
        TextInputEditText ediPsgddsorc9 =findViewById(R.id.ediPsgddsorc9);


        TextInputEditText [] array = {ediColortSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10, ediColortSem9,
                ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9,
                ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9};


        int indice=0;


        for(int i=0; i<array.length; i++){

            TextInputEditText current =array [i];

            if(!current.getText().toString().trim().isEmpty()){
                String value=current.getText().toString();
                indice++;

                switch(current.getId()){
                    case R.id.ediColortSem14:
                        break;

                    case R.id.ediColortSem13:
                        break;

                    case R.id.ediColortSem12:
                        break;


                    case R.id.ediColortSem11:
                        break;


                    case R.id.ediColortSem10:

                        break;


                    case R.id.ediColortSem9:

                        break;



                    case R.id.ediNumRcim14:
                        break;


                    case R.id.ediNumRcim13:

                        break;

                    case R.id.ediNumRcim12:
                        break;


                    case R.id.ediNumRcim11:
                        break;

                    case R.id.ediNumRcim10:
                        break;

                    case R.id.ediNumRac9:
                        break;

                    ////ediPorc14
                    case R.id.ediPorc14:
                        break;

                    case R.id.ediPorc13:
                        break;

                    case R.id.ediPorc12:
                        break;

                    case R.id.ediPorc11:
                        break;

                    case R.id.ediPorc10:
                        break;

                    case R.id.ediPsgddsorc9:
                        break;




                }

            }

        }

        if(indice>2) {
            return true;

        }else{
            ediPsgddsorc9.requestFocus();
            ediPsgddsorc9.setError("Inserte al menos un valor en este cuadro");

            return false;

        }

    }

    private boolean updateCaledarioEnfunde(SetInformDatsHacienda informe) {

        TextInputEditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        TextInputEditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        TextInputEditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        TextInputEditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        TextInputEditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        TextInputEditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        TextInputEditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        TextInputEditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        TextInputEditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        TextInputEditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        TextInputEditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        TextInputEditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        TextInputEditText ediPorc14=findViewById(R.id.ediPorc14);
        TextInputEditText ediPorc13=findViewById(R.id.ediPorc13);
        TextInputEditText ediPorc12=findViewById(R.id.ediPorc12);
        TextInputEditText ediPorc11=findViewById(R.id.ediPorc11);
        TextInputEditText ediPorc10=findViewById(R.id.ediPorc10);
        TextInputEditText ediPsgddsorc9 =findViewById(R.id.ediPsgddsorc9);


        TextInputEditText [] array = {ediColortSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10, ediColortSem9,
                ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9,
                ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9};


        int indice=0;


        for(int i=0; i<array.length; i++){

            TextInputEditText current =array [i];

            if(!current.getText().toString().trim().isEmpty()){
                String value=current.getText().toString();
                indice++;

                switch(current.getId()){
                    case R.id.ediColortSem14:
                        informe.setColortSem14(value);
                        break;

                    case R.id.ediColortSem13:
                        informe.setColortSem13(value);
                        break;

                    case R.id.ediColortSem12:
                        informe.setColortSem12(value);
                        break;


                    case R.id.ediColortSem11:
                        informe.setColortSem11(value);
                        break;


                    case R.id.ediColortSem10:
                        informe.setColortSem10(value);

                        break;


                    case R.id.ediColortSem9:
                        informe.setColortSem9(value);

                        break;



                    case R.id.ediNumRcim14:
                        informe.setNumRcim14(value);
                        break;


                    case R.id.ediNumRcim13:

                        informe.setNumRcim13(value);
                        break;

                    case R.id.ediNumRcim12:
                        informe.setNumRcim12(value);
                        break;


                    case R.id.ediNumRcim11:
                        informe.setNumRcim11(value);
                        break;

                    case R.id.ediNumRcim10:
                        informe.setNumRcim10(value);
                        break;

                    case R.id.ediNumRac9:
                        informe.setNumRcim9(value);
                        break;

                    ////ediPorc14
                    case R.id.ediPorc14:
                        informe.setPorc14(value);
                        break;

                    case R.id.ediPorc13:
                        informe.setPorc13(value);
                        break;

                    case R.id.ediPorc12:
                        informe.setPorc12(value);
                        break;

                    case R.id.ediPorc11:
                        informe.setPorc11(value);
                        break;

                    case R.id.ediPorc10:
                        informe.setPorc10(value);
                        break;

                    case R.id.ediPsgddsorc9:
                        informe.setPorc9(value);
                        break;




                }

            }

        }

        if(indice>2) {
            return true;

        }else{
            ediPsgddsorc9.requestFocus();
            ediPsgddsorc9.setError("Inserte al menos un valor en este cuadro");

            return false;

        }

    }






    private void checkConnection() {

        // initialize intent filter
        IntentFilter intentFilter = new IntentFilter();

        // add action
        intentFilter.addAction("android.new.conn.CONNECTIVITY_CHANGE");

        // register receiver
        registerReceiver(new ConnectionReceiver(), intentFilter);

        // Initialize listener
        ConnectionReceiver.Listener = this;

        // Initialize connectivity manager
        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Initialize network info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        // get connection status
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();

        // display snack bar
        showSnackBar(isConnected);
    }

    private void showSnackBar(boolean isConnected) {

        // initialize color and message
        String message;
        int color;

        // check condition
        if (isConnected) {

            // when internet is connected
            // set message
            message = "Connected to Internet";

            // set text color
            color = Color.WHITE;

        } else {

            // when internet
            // is disconnected
            // set message
            message = "Not Connected to Internet";

            // set text color
           // color = Color.RED;
        }

        // initialize snack bar
      //  Snackbar snackbar = Snackbar.make(findViewById(R.id.btnSi), message, Snackbar.LENGTH_LONG);
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

        // initialize view
       // View view = snackbar.getView();
       // Snackbar snackbar = Snackbar.make(coordinatorLayout,"Custom Snackbar",Toast.LENGTH_SHORT);
        snackbar.setActionTextColor(ContextCompat.getColor(context, R.color.red));


        // Assign variable
       // TextView textView = view.findViewById(R.id.txtTotal1);

        // set text color
      //  textView.setTextColor(color);

        // show snack bar
        snackbar.show();
    }

    @Override
    public void onNetworkChange(boolean isConnected) {
        // display snack bar
        showSnackBar(isConnected);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // call method
       // checkConnection();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // call method
      //  checkConnection();
    }


    private void generateUniqueIdInformeAndContinuesIfIdIsUnique( SetInformEmbarque1 informe,SetInformEmbarque2 informe2, SetInformDatsHacienda informe3){

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss"+uniqueId);

        checkIfExistIdAndUpload(uniqueId,informe,informe2,informe3);


    }






    private void checkIfExistIdAndUpload (String currenTidGenrate, SetInformEmbarque1 informe,SetInformEmbarque2 informe2, SetInformDatsHacienda informe3){

      //  private void checkIfExistIdAndUpload(String currenTidGenrate ) {
      //  Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("informsData").equalTo(currenTidGenrate);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ControlCalidad  user=null;
                for (DataSnapshot ds : snapshot.getChildren()) {
                      user=ds.getValue(ControlCalidad.class);
                }


                if(user == null) {

                    informe.setUniqueIDinforme(currenTidGenrate);
                    uploadInformeToDatabase(informe,informe2,informe3);
                    //dfghdfh

                     RealtimeDB.addNewRegisterUploadInform(new InformsRegister(currenTidGenrate,Variables.FormContenedores));

                    //aqui subimos..

                }else {

                    generateUniqueIdInformeAndContinuesIfIdIsUnique(informe,informe2,informe3);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }






    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = ActivityContenedores.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY

    }

}
