package com.tiburela.qsercom.activities.formularios;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static android.view.View.GONE;

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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
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

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.activities.formulariosPrev.ActivityContenedoresPrev;
import com.tiburela.qsercom.activities.formulariosPrev.PreviewCalidadCamionesyCarretas;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.InformsRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.PerecentHelp;
import com.tiburela.qsercom.utils.Permisionx;
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
import java.util.concurrent.ExecutionException;

import com.tiburela.qsercom.R;


public class ActivityReporteCalidadCamionesyCarretas extends AppCompatActivity implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    public static Context context;
    private final int CODE_TWO_PERMISIONS = 12;
    LinearLayout layoutPesobrutoPorClusterSolo;

    HashMap<String,Float> hasmapPesoBrutoClosters2y3L;
    private int currentTypeImage=0;

    TextInputEditText ediSemana;
    TextInputEditText ediClienteNombreReporte;

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
    TextInputEditText ediFotosLlegada;
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


    TextInputEditText ediCandadoQsercom;
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

    TextInputEditText ediExtCalid;
    TextInputEditText ediExtRodillo;
    TextInputEditText ediExtGancho;

    TextInputEditText ediExtCalidCi;
    TextInputEditText ediExtRodilloCi;
    TextInputEditText ediExtGanchoCi;


    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader2;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;
    LinearLayout linLayoutHeader8;
    LinearLayout lyEscontenedor;


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
    Spinner spinnerCandadoQsercon;


    Switch switchContenedor;
    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;
    Switch switchLavdoRacimos;
    Switch swAguaCorrida;

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



    @Override
    protected void onStart() {
        super.onStart();

        Auth.initAuth(ActivityReporteCalidadCamionesyCarretas.this);
        Auth.signInAnonymously(ActivityReporteCalidadCamionesyCarretas.this);


        if(Variables.hayUnFormIncompleto){
            AddDataFormOfSharePrefe() ;

            //
            Variables.hayUnFormIncompleto=false;



        }



        // Check if user is signed in (non-null) and update UI accordingly.
        // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
        //  updateUI(currentUs bver)

    }
    private void AddDataFormOfSharePrefe() {

        TextInputEditText [] arrayEditex =creaArryOfTextInputEditText();
        Utils.addDataOfPrefrencesInView(arrayEditex,Variables.currentMapPreferences);

      //  TextInputEditText [] arrayEditex =creaArryOfTextInputEditText();

       // Map<String, ImagenReport> mapImagesReport = Utils.loadMapiMAGEData(ActivityReporteCalidadCamionesyCarretas.this);


     //   ArrayList<ImagenReport> listImagesToSaVE = new ArrayList<ImagenReport>(mapImagesReport.values());




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
        setContentView(R.layout.report_calidad_camio_carret);
        context=getApplicationContext();
        Variables.activityCurrent=Variables.FormCamionesyCarretasActivity;

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
        ocultaoTherVIEWs();
        configCertainSomeViewsAliniciar();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennersSpinners();

        eventCheckdata();
        //creaFotos();


    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(ActivityReporteCalidadCamionesyCarretas.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {


                        String minutes=String.valueOf(sMinute);

                        String AM_PM ;

                        if(sHour < 12) {
                            AM_PM = "AM";
                        } else {
                            AM_PM = "PM";
                        }

                        if(minutes.equals("0")){
                            minutes="00";
                        }



                        if(vista.getId()==R.id.ediHoraInicio) {

                            ediHoraInicio.setText(sHour + ":" + minutes+" "+ AM_PM);
                        }


                        else if (vista.getId()== R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }



                        else if (vista.getId()== R.id.ediHoraLLegadaContenedor) {
                            ediHoraLLegadaContenedor.setText(sHour + ":" + minutes+" "+ AM_PM);
                        }



                        else if (vista.getId()== R.id.ediHoraSalidaContenedor) {
                            ediHoraSalidaContenedor.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }


                        else if (vista.getId()== R.id.ediTipoEmp2) {
                            ediHoraEncendido1.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }



                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+ AM_PM);


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
        DatePickerDialog  picker = new DatePickerDialog(ActivityReporteCalidadCamionesyCarretas.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        String dateSelec=i2+"/"+(i1+1)+"/"+i;


                        ediFecha.setText(dateSelec);

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
          ///ocutamos otro views
        lyEscontenedor.setVisibility(LinearLayout.GONE);


      //  LinearLayout lyUbicacionBalanza=findViewById(R.id.lyUbicacionBalanza);
       // lyUbicacionBalanza.setVisibility(LinearLayout.GONE);


        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

      //  disableEditText(ediHoraLLegadaContenedor);//here
       // disableEditText(ediHoraSalidaContenedor);


        disableEditText(ediContenedor);
        disableEditText(ediFotosLlegada);

        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);

       // disableEditText(ediHoraEncendido1);
      //  disableEditText(ediHoraEncendido2);


    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar
        ediClienteNombreReporte=findViewById(R.id.ediClienteNombreReporte);
        layoutPesobrutoPorClusterSolo=findViewById(R.id.layoutPesobrutoPorClusterSolo);
        ediEmpacadora=findViewById(R.id.ediEmpacadora);
        ediCandadoQsercom=findViewById(R.id.ediCandadoQsercom);
        lyEscontenedor=findViewById(R.id.lyEscontenedor);
        ediSemana=findViewById(R.id.ediSemana);
        ediFecha=findViewById(R.id.ediFecha);
        ediProductor=findViewById(R.id.ediProductor);
        ediHacienda=findViewById(R.id.ediHacienda);
        ediCodigo=findViewById(R.id.ediCodigo);
        ediInscirpMagap=findViewById(R.id.ediInscirpMagap);
        ediPemarque=findViewById(R.id.ediPemarque);
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


         ediExtCalid=findViewById(R.id.ediExtCalid);
         ediExtRodillo=findViewById(R.id.ediExtRodillo);
         ediExtGancho= findViewById(R.id.ediExtGancho);

         ediExtCalidCi=findViewById(R.id.ediExtCalidCi);
         ediExtRodilloCi=findViewById(R.id.ediExtRodilloCi);
         ediExtGanchoCi =findViewById(R.id.ediExtGanchoCi);

        spinnerCandadoQsercon=findViewById(R.id.spinnerCandadoQsercon);




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

      //  switchContenedor=findViewById(R.id.switchContenedor);
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

       // ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
       // ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);


       // ediTipoContenedor=findViewById(R.id.ediTipoContenedor);

       // ediFotoContenedor=findViewById(R.id.ediFotoContenedor);


        ediFotosPposcosecha=findViewById(R.id.ediFotosPposcosecha);

        ediCondicionBalanza=findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja=findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico=findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza=findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso=findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza=findViewById(R.id.ediUbicacionBalanza);



        ediTermofrafo1=findViewById(R.id.ediNombProd1);
       // ediHoraEncendido1=findViewById(R.id.ediTipoEmp2);
        ediUbicacion1=findViewById(R.id.ediCod2);
        ediRuma1=findViewById(R.id.edinCajas3);
        ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
       // ediHoraEncendido2=findViewById(R.id.ediHoraEncendido2);
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
        layoutPesobrutoPorClusterSolo.setOnClickListener(this);
        imbAtach_transportista.setOnClickListener(this);
        imbTakePicTransportista.setOnClickListener(this);
      //  imbAtachSellosLlegada.setOnClickListener(this);
      //  imbTakePicSellosLLegada.setOnClickListener(this);
       // imbAtachDatosContenedor.setOnClickListener(this);
      //  imbTakePicDatosContenedor.setOnClickListener(this);
        imbAtachPrPostcosecha.setOnClickListener(this);
        imbTakePicPrPostcosecha.setOnClickListener(this);

      //  ediHoraEncendido1.setOnClickListener(this);
      //  ediHoraEncendido2.setOnClickListener(this);



        linLayoutHeader2.setOnClickListener(this);
        linLayoutHeader1.setOnClickListener(this);
      //  linLayoutHeader3.setOnClickListener(this);
     //   linLayoutHeader4.setOnClickListener(this);
     //   linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
        linLayoutHeader8.setOnClickListener(this);

        ediFecha.setOnClickListener(this);
        ediHoraInicio.setOnClickListener(this);
        ediHoraTermino.setOnClickListener(this);

       // ediHoraLLegadaContenedor.setOnClickListener(this);
     //   ediHoraSalidaContenedor.setOnClickListener(this);



    }

    private void oucultaLinearLayout(LinearLayout linearLayout) { //configuraremos algos views al iniciar
        linearLayout.setVisibility(GONE);


    }


    private void muestraLinearLayout( LinearLayout linearLayout) { //configuraremos algos views al iniciar

        linearLayout.setVisibility(LinearLayout.VISIBLE);



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        if(!checkPermission()){

            requestPermission();
            //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            // checkPermission2();

            /****por aqui pedir permisos antes **/

        }



        switch (view.getId()) {


            case R.id.ediFecha:
                selecionaFecha();
                break;


            case R.id.layoutPesobrutoPorClusterSolo:

                  LinearLayout layPesoBruto1=findViewById(R.id.layPesoBruto1);

                if(layPesoBruto1.getVisibility() == GONE) {
                    muestraLinearLayout(layPesoBruto1);
                }
                else{

                    oucultaLinearLayout(layPesoBruto1);
                }
                break; //


            case R.id.linLayoutHeader1:
                LinearLayout layoutContainerSeccion1=findViewById(R.id.layoutContainerSeccion1);

                if(layoutContainerSeccion1.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion1);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion1);
                }
                break; //



            case R.id.linLayoutHeader2:
                LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);

                if(layoutContainerSeccion2.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion2);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion2);
                }
                break; //

            case R.id.linLayoutHeader3:
                LinearLayout layoutContainerSeccion3=findViewById(R.id.layoutContainerSeccion3);

                if(layoutContainerSeccion3.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion3);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion3);
                }
                break; //


            case R.id.linLayoutHeader4:
                LinearLayout layoutContainerSeccion4=findViewById(R.id.layoutContainerSeccion4);

                if(layoutContainerSeccion4.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion4);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion4);
                }
                break; //



            case R.id.layoutContainerSeccion5:
                LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);

                if(layoutContainerSeccion5.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion5);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion5);
                }
                break; //

            case R.id.linLayoutHeader6:
                LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);

                if(layoutContainerSeccion6.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion6);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion6);
                }
                break; //


            case R.id.linLayoutHeader7:
                LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerDatsProceso);

                if(layoutContainerSeccion7.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion7);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion7);
                }
                break; //
            case R.id.linLayoutHeader8:
                LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);

                if(layoutContainerSeccion8.getVisibility() == GONE) {
                    muestraLinearLayout(layoutContainerSeccion8);
                }
                else{

                    oucultaLinearLayout(layoutContainerSeccion8);
                }
                break; //

            case R.id.ediCajas7:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);

                selecionaFecha();

                break; //



            case R.id.ediHoraInicio:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);

                showingTimePicker(view);

                break; //

            case R.id.ediHoraTermino:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                showingTimePicker(view);

                break; //
            case R.id.ediHoraSalidaContenedor:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                showingTimePicker(view);

                break; //


            case R.id.ediHoraLLegadaContenedor:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                showingTimePicker(view);

                break; //

            case R.id.ediTipoEmp2:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
                showingTimePicker(view);

                break; //

            case R.id.ediHoraEncendido2:
                // Utils.closeKeyboard(ActivityReporteCalidadCamionesyCarretas.this);
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

            Log.i("codereister","permiso DENEGADO SOLICTAMOS PERMISO") ;

        }
    }

    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = ActivityReporteCalidadCamionesyCarretas.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);

    }


    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // There are no request codes

                        //  mImageView.setImageURI(cam_uri);


                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityReporteCalidadCamionesyCarretas.this.getContentResolver(),cam_uri);


                         //   Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                            String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                            //creamos un nuevo objet de tipo ImagenReport
                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage, Utils.getFileNameByUri(ActivityReporteCalidadCamionesyCarretas.this,cam_uri),horientacionImg);

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                            showImagesPicShotOrSelectUpdateView(false);

                        }

                      catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityReporteCalidadCamionesyCarretas.this);


                        showImagesPicShotOrSelectUpdateView(false);

                    }
                }
            });




    private View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
            listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

        }

        View vistAnterior = listViewsClickedUser.get(0);
        //  Log.i("soeobjetc","el objeto anterioR TAG ES "+vistAnterior.getTag().toString());



        return   vistAnterior;

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


                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityReporteCalidadCamionesyCarretas.this.getContentResolver(),result.get(indice));

                                    String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                                    //creamos un nuevo objet de tipo ImagenReport
                                    ImagenReport obcjImagenReport =new ImagenReport("",result.get(indice).toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityReporteCalidadCamionesyCarretas.this,result.get(indice))),horientacionImg);

                                    //agregamos este objeto a la lista
                                    ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                                    showImagesPicShotOrSelectUpdateView(false);

                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


//


                                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityReporteCalidadCamionesyCarretas.this);

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
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        spinnerCandadoQsercon .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String candadoQsercom= spinnerCandadoQsercon.getSelectedItem().toString();
                ediCandadoQsercom.setText(candadoQsercom);




              /*
                if(condicion.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediCondicionBalanza.setText("");
                    actualizaListStateView("ediCondicionBalanza",false) ;
                }else {
                    actualizaListStateView("ediCondicionBalanza",true) ;
                }
*/
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



        /*
        spinnerubicacionBalanza .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida= spinnerubicacionBalanza.getSelectedItem().toString();

              //  ediUbicacionBalanza.setText(zonaEelejida);

                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediUbicacionBalanza.setText("");
                    actualizaListStateView("ediUbicacionBalanza",false) ;
                }else {
                    actualizaListStateView("ediUbicacionBalanza",true) ;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
*/

        /*
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
*/

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


        if(! checkDataCalibFrutaCalEnfn()){
            Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");

            return;
        }else{

            Log.i("test001","si  esta lleno  checkDataCalibFrutaCalEnfn");


        }

        if(! chekDaTaEvaluador()){
            Log.i("test001","no esta lleno  chekDaTaEvaluador");

            return;
        }else{

            Log.i("test001","si  esta lleno  chekDaTaEvaluador");


        }





        RealtimeDB.initDatabasesRootOnly();
        RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

        Log.i("test001","toda la data esta completa HUrra ");


        createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...


    }


    private void createObjcInformeAndUpload(){

        ReportCamionesyCarretas informe = new ReportCamionesyCarretas(UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
                Integer.parseInt(ediNhojaEvaluacion.getText().toString()),ediZona.getText().toString(),ediProductor.getText().toString(),
                ediCodigo.getText().toString(), ediPemarque.getText().toString(),
                 ediNguiaRemision.getText().toString(),ediHacienda.getText().toString(),edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediSemana.getText().toString(),ediEmpacadora.getText().toString(),
                ediNombreChofer.getText().toString(),ediCedula.getText().toString(),ediCelular.getText().toString(),ediPLaca.getText().toString(),ediCandadoQsercom.getText().toString(),
                   ediTipoPlastico.getText().toString(),ediTipodeCaja.getText().toString(),switchHayEnsunchado.isChecked(),switchHaybalanza.isChecked(),
                   ediCondicionBalanza.getText().toString(),ediTipoBalanza.getText().toString(),switchBalanzaRep.isChecked(),editipbalanzaRepeso.getText().toString(),
                ediFuenteAgua.getText().toString(),swAguaCorrida.isChecked(),switchLavdoRacimos.isChecked(),ediFumigacionClin1.getText().toString(),
                Integer.parseInt(ediRacimosCosech.getText().toString()) ,Integer.parseInt(ediRacimosRecha.getText().toString()),Integer.parseInt(ediRacimProces.getText().toString()),Integer .parseInt(ediCajasProcDesp.getText().toString()),
                ediExtCalid.getText().toString(),ediExtRodillo.getText().toString(), ediExtGancho.getText().toString(),
                ediExtCalidCi.getText().toString(),ediExtRodilloCi.getText().toString(),ediExtGanchoCi.getText().toString(),FieldOpcional.observacionOpcional,""
                ,ediClienteNombreReporte.getText().toString()

        ) ;


        //cremaos un hasmpa con los libriados
        HashMap<String,Float>miMapLbriado=generateMapLibriadoIfExistAndUpload();
        String keyWhereLocaleHashMapLibriado="";


        if(miMapLbriado.size()>0){

            keyWhereLocaleHashMapLibriado=RealtimeDB.rootDatabaseReference.push().getKey();

            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado,keyWhereLocaleHashMapLibriado);

            informe.setKeyOrNodeLibriadoSiEs(keyWhereLocaleHashMapLibriado);


        }


        generateUniqueIdInformeAndContinuesIfIdIsUnique(informe);



    }

    HashMap<String, Float> generateMapLibriadoIfExistAndUpload(){


        EditText        pbCluster01 = findViewById(R.id.pbCluster01);
        EditText        pbCluster05 = findViewById(R.id.pbCluster05);
        EditText        pbCluster03 = findViewById(R.id.pbCluster03);
        EditText        pbCluster02 = findViewById(R.id.pbCluster02);
        EditText        pbCluster04 = findViewById(R.id.pbCluster04);
        EditText        pbCluster010 = findViewById(R.id.pbCluster010);
        EditText        pbCluster09 = findViewById(R.id.pbCluster09);
        EditText        pbCluster07 = findViewById(R.id.pbCluster07);
        EditText        pbCluster08 = findViewById(R.id.pbCluster08);
        EditText        pbCluster06 = findViewById(R.id.pbCluster06);
        EditText        pbCluster011 = findViewById(R.id.pbCluster011);
        EditText        pbCluster015 = findViewById(R.id.pbCluster015);
        EditText        pbCluster012 = findViewById(R.id.pbCluster012);
        EditText        pbCluster013 = findViewById(R.id.pbCluster013);
        EditText        pbCluster014 = findViewById(R.id.pbCluster014);
        EditText        pbCluster016 = findViewById(R.id.pbCluster016);
        EditText        pbCluster019 = findViewById(R.id.pbCluster019);
        EditText        pbCluster018 = findViewById(R.id.pbCluster018);
        EditText        pbCluster020 = findViewById(R.id.pbCluster020);
        EditText        pbCluster017 = findViewById(R.id.pbCluster017);
        EditText        pbCluster025 = findViewById(R.id.pbCluster025);
        EditText        pbCluster024 = findViewById(R.id.pbCluster024);
        EditText        pbCluster023 = findViewById(R.id.pbCluster023);
        EditText        pbCluster022 = findViewById(R.id.pbCluster022);
        EditText        pbCluster021 = findViewById(R.id.pbCluster021);
        EditText        pbCluster028 = findViewById(R.id.pbCluster028);
        EditText        pbCluster027 = findViewById(R.id.pbCluster027);
        EditText        pbCluster029 = findViewById(R.id.pbCluster029);
        EditText        pbCluster026 = findViewById(R.id.pbCluster026);
        EditText        pbCluster030 = findViewById(R.id.pbCluster030);
        EditText        pbCluster034 = findViewById(R.id.pbCluster034);
        EditText        pbCluster031 = findViewById(R.id.pbCluster031);
        EditText        pbCluster035 = findViewById(R.id.pbCluster035);
        EditText        pbCluster033 = findViewById(R.id.pbCluster033);
        EditText        pbCluster032 = findViewById(R.id.pbCluster032);
        EditText        pbCluster039 = findViewById(R.id.pbCluster039);
        EditText        pbCluster040 = findViewById(R.id.pbCluster040);
        EditText        pbCluster037 = findViewById(R.id.pbCluster037);
        EditText        pbCluster038 = findViewById(R.id.pbCluster038);
        EditText        pbCluster036 = findViewById(R.id.pbCluster036);
        EditText        pbCluster043 = findViewById(R.id.pbCluster043);
        EditText        pbCluster045 = findViewById(R.id.pbCluster045);
        EditText        pbCluster042 = findViewById(R.id.pbCluster042);
        EditText        pbCluster041 = findViewById(R.id.pbCluster041);
        EditText        pbCluster044 = findViewById(R.id.pbCluster044);
        EditText        pbCluster048 = findViewById(R.id.pbCluster048);
        EditText        pbCluster046 = findViewById(R.id.pbCluster046);
        EditText        pbCluster050 = findViewById(R.id.pbCluster050);
        EditText        pbCluster047 = findViewById(R.id.pbCluster047);
        EditText        pbCluster049 = findViewById(R.id.pbCluster049);
        EditText        p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        EditText        p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        EditText        p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        EditText        p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        EditText        p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        EditText        p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        EditText        p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        EditText        p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        EditText        p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        EditText        p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        EditText        p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        EditText        p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        EditText        p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        EditText        p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        EditText        p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        EditText        p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        EditText        p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        EditText        p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        EditText        p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        EditText        p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        EditText        p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        EditText        p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        EditText        p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        EditText        p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        EditText        p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        EditText        p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        EditText        p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        EditText        p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        EditText        p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        EditText        p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        EditText        p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        EditText        p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        EditText        p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        EditText        p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        EditText        p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        EditText        p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        EditText        p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        EditText        p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        EditText        p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        EditText        p2pbCluster036 = findViewById(R.id.p2pbCluster036);


        EditText [] miArray= {
                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024 ,pbCluster023, pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036

        };

        HashMap<String,Float>miMapData= new HashMap<>();


        for(EditText currentEdit: miArray){

            if(! currentEdit.getText().toString().trim().isEmpty()){

                //le agregamos un slash al id key mas o menos este fomrato idddd/fil1

                miMapData.put(String.valueOf(currentEdit.getId())+"-"+currentEdit.getTag(),Float.parseFloat(currentEdit.getText().toString()));
            }


        }






        return  miMapData;

    }



    private void generateUniqueIdInformeAndContinuesIfIdIsUnique( ReportCamionesyCarretas cmaionesyCarretasObjc){

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss"+uniqueId);

        checkIfExistIdAndUpload(uniqueId,cmaionesyCarretasObjc);


    }

    private void checkIfExistIdAndUpload (String currenTidGenrate, ReportCamionesyCarretas objecCamionesyCarretas){


        Query query = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros").equalTo(currenTidGenrate);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InformRegister informRegister=null;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    informRegister=ds.getValue(InformRegister.class);
                }


                if(informRegister == null)

                {
                    informRegister= new InformRegister(currenTidGenrate,Constants.CAMIONES_Y_CARRETAS,
                            Variables.usuarioQsercomGlobal.getNombreUsuario(),
                            Variables.usuarioQsercomGlobal.getUniqueIDuser()
                           , "CAMIONES Y CARRETAS ");


                    //informe register
                    RealtimeDB.addNewRegistroInforme(ActivityReporteCalidadCamionesyCarretas.this,informRegister);
                    StorageData.uniqueIDImagesSetAndUInforme=currenTidGenrate;

                    objecCamionesyCarretas.setUniqueIDinforme(currenTidGenrate);

                    DatabaseReference mibasedata = RealtimeDB.rootDatabaseReference;
                    String PuskEY = mibasedata.push().getKey();


                    objecCamionesyCarretas.setNodoQueContieneMapPesoBrutoCloster2y3l(PuskEY);

                     //informe actual
                    RealtimeDB.addNewReportCalidaCamionCarrretas(objecCamionesyCarretas);

                    uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

                    addCalibracionFutaC_enfAndUpload();
                    addProdcutsPostCosechaAndUpload(currenTidGenrate); //agregamos y subimos los productos postcosecha..

                    //createHashmapPesoBrutoCloster2y3lAndUpload(PuskEY);


                }



                else

                {  //si exite creamos otro value...

                    generateUniqueIdInformeAndContinuesIfIdIsUnique(objecCamionesyCarretas);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                //  Variables.currentCuponObjectGlob =listGiftCards.get(position);

               /// Log.i("midaclick","el click es here, posicion es "+position);

                Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag()).getTipoImagenCategory();

                Log.i("camisax","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());


                ImagenReport.hashMapImagesData.remove(v.getTag().toString());
                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityReporteCalidadCamionesyCarretas.this);


                Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);





            }

        });
    }



    void uploadImagesInStorageAndInfoPICS() {
        //una lista de Uris


        Log.i("imagheddd","se llamometodoel size de lista es "+ImagenReport.hashMapImagesData.size());

        if(ImagenReport.hashMapImagesData.size() ==0 ){
            Log.i("imagheddd","es igual a cero");

            Toast.makeText(this, "esta vacia ", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i("imagheddd","es difrente de cero");

        Log.i("imagheddd","el size de hasmpa es  "+ImagenReport.hashMapImagesData.size());


        Log.i("imagheddd","el unique id es "+StorageData.uniqueIDImagesSetAndUInforme);


        ImagenReport.updateIdPerteence(StorageData.uniqueIDImagesSetAndUInforme,ImagenReport.hashMapImagesData);
        ArrayList<ImagenReport>list=Utils.mapToArrayList(ImagenReport.hashMapImagesData);
        StorageData.uploaddata(list);


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







        //  startActivity(new Intent(ActivityReporteCalidadCamionesyCarretas.this,PdfPreviewActivity.class));

        //generamos un pdf con la data que tenemos()

        /*

        PdfMaker.generatePdfReport1(ActivityReporteCalidadCamionesyCarretas.this,ediCodigo.getText().toString(),Integer.parseInt(ediNhojaEvaluacion.getText().toString()),
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


    private boolean checkPermission2() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(ActivityReporteCalidadCamionesyCarretas.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(ActivityReporteCalidadCamionesyCarretas.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
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


        if(layoutContainerSeccion1.getVisibility()== GONE){
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
        }

        if(ediSemana.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSemana.requestFocus();
            ediSemana.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }

        if (ediFecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFecha.requestFocus();
            ediFecha.setError("Debe selecionar una fecha");

            return false;

        }







        if(ediProductor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediProductor.requestFocus();
            ediProductor.setError("Este espacio es obligatorio");

            return false;

        }




        if(ediHacienda.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHacienda.requestFocus();
            ediHacienda.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediClienteNombreReporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediClienteNombreReporte.requestFocus();
            ediClienteNombreReporte.setError("Este espacio es obligatorio");

            return false;

        }



        if(ediCodigo.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCodigo.requestFocus();
            ediCodigo.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediInscirpMagap.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediInscirpMagap.requestFocus();
            ediInscirpMagap.setError("Este espacio es obligatorio");
            ediInscirpMagap.setText("_");
            return false;

        }

        if(ediPemarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPemarque.requestFocus();
            ediPemarque.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediZona.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediZona.requestFocus();
            ediZona.setError("Debe selecionar una zona");
            return false;

        }


        if(ediHoraInicio.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraInicio.requestFocus();
            ediHoraInicio.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediHoraTermino.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraTermino.requestFocus();
            ediHoraTermino.setError("Este espacio es obligatorio");

            return false;

        }



        if(ediNguiaRemision.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNguiaRemision.requestFocus();
            ediNguiaRemision.setError("Este espacio es obligatorio");

            return false;

        }

        if(edi_nguia_transporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            edi_nguia_transporte.requestFocus();
            edi_nguia_transporte.setError("Este espacio es obligatorio");

            return false;

        }


        if(ediNtargetaEmbarque.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            return false;

        }




        if(ediNhojaEvaluacion.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNhojaEvaluacion.requestFocus();
            ediNhojaEvaluacion.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }



        if(ediEmpacadora.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediEmpacadora.requestFocus();
            ediEmpacadora.setError("Este espacio es obligatorio");

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





    private boolean checkDatosTransportistaIsLleno(){

        LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(layoutContainerSeccion6.getVisibility()== GONE){
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);

        }

        if(ediNombreChofer.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediNombreChofer.requestFocus();
            ediNombreChofer.setError("Este espacio es obligatorio");

            return false;

        }



        if(ediCedula.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCedula.requestFocus();
            ediCedula.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediCelular.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCelular.requestFocus();
            ediCelular.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediPLaca.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediPLaca.requestFocus();
            ediPLaca.setError("Este espacio es obligatorio");
            return false;

        }


/*
        if(ediMarcaCabezal.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediMarcaCabezal.requestFocus();
            ediMarcaCabezal.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
*/
/*
        if(ediColorCabezal.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediColorCabezal.requestFocus();
            ediColorCabezal.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
*/



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


        if(layoutContainerSeccion8.getVisibility()== GONE){
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);


        }


        if(ediFuenteAgua.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediFuenteAgua.requestFocus();
            ediFuenteAgua.setError("Selecione una opcion");

            return false;

        }




        if(ediLavadoRacimos.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediLavadoRacimos.requestFocus();
            ediLavadoRacimos.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediFumigacionClin1.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediFumigacionClin1.requestFocus();
            ediFumigacionClin1.setError("Selecione una opcion");

            return false;

        }



        if(ediTipoBoquilla.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediTipoBoquilla.requestFocus();
            ediTipoBoquilla.setError("Selecione una opcion");

            return false;

        }


        if(ediCajasProcDesp.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            return false;

        }



        if(ediRacimosCosech.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            return false;

        }

        if(ediRacimosRecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Este espacio es obligatorio");

            return false;
        }

        if(ediRacimProces.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimProces.requestFocus();
            ediRacimProces.setError("Este espacio es obligatorio");

            return false;

        }

        ///vamos con los datos de semananas y eso




        return true;

    }

    private boolean checkDataCalibFrutaCalEnfn(){

        //le decimos que esta todo bien y omitiremos estos datos....
        return true;

    }


    private boolean chekDaTaEvaluador(){


        if(ediExtCalid.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalid.requestFocus();
            ediExtCalid.setError("Este espacio es obligatorio");

          //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtCalidCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtCalidCi.requestFocus();
            ediExtCalidCi.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtRodillo.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtRodillo.requestFocus();
            ediExtRodillo.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtRodilloCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtRodilloCi.requestFocus();
            ediExtRodilloCi.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if(ediExtGancho.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtGancho.requestFocus();
            ediExtGancho.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



        if(ediExtGanchoCi.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExtGanchoCi.requestFocus();
            ediExtGanchoCi.setError("Este espacio es obligatorio");

            //  layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




        return true;
    }



    private boolean checkDatosProcesoIsLleno(){
        LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerDatsProceso);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(layoutContainerSeccion7.getVisibility()== GONE){
            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);

        }

        if(ediCondicionBalanza.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia

            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Selecione una opcion");

            return false;


        }

        if(ediTipodeCaja.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia

            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Selecione una opcion");

            return false;

        }


        if(ediTipoPlastico.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia

            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Selecione una opcion");

            return false;

        }



        if(ediTipoBalanza.getText().toString().equalsIgnoreCase("ninguna")){ //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Selecione una opcion");

            return false;

        }


/*
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
*/

        return true;

    }


    private void  addProdcutsPostCosechaAndUpload(String uniqueIDinforme){

        ProductPostCosecha producto=new ProductPostCosecha(uniqueIDinforme);
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

    private void  addCalibracionFutaC_enfAndUpload(){

        //recorremos un array de editext y creamos un objeto de tipo CalibrFrutCalEnf..
        //si no tiene data agregamos cero u comillas...

        CalibrFrutCalEnf calibrFrutCalEnf=new CalibrFrutCalEnf(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        //editext here
        EditText  ediColorSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10,ediColortSem9;
        EditText  ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9;


        //findviewsid
        ediColorSem14=findViewById(R.id.ediColortSem14);
        ediColortSem13 =findViewById(R.id.ediColortSem13);
        ediColortSem12=findViewById(R.id.ediColortSem12);
        ediColortSem11=findViewById(R.id.ediColortSem11);
        ediColortSem10=findViewById(R.id.ediColortSem10);
        ediColortSem9=findViewById(R.id.ediColortSem9);

        ediNumRcim14=findViewById(R.id.ediNumRcim14);
        ediNumRcim13=findViewById(R.id.ediNumRcim13);
        ediNumRcim12=findViewById(R.id.ediNumRcim12);
        ediNumRcim11=findViewById(R.id.ediNumRcim11);
        ediNumRcim10=findViewById(R.id.ediNumRcim10);
        ediNumRac9=findViewById(R.id.ediNumRac9);


        EditText [] editextArrayColorSeman = {ediColorSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10,ediColortSem9} ;
        EditText [] editextNumRacimsArray =  {ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9} ;


        for (int indice =0; indice<editextArrayColorSeman.length; indice++) {
            EditText currentEditextColorSem=editextArrayColorSeman[indice];
            EditText currentEditextNumRacims=editextNumRacimsArray[indice];

            if (!currentEditextColorSem.getText().toString().trim().isEmpty()){ //si no esta vacioo
                if (!currentEditextNumRacims.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditextColorSem.getId()){

                        case R.id.ediColortSem14:

                            calibrFrutCalEnf.setColorSemana14(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;
                        case R.id.ediColortSem13:
                            calibrFrutCalEnf.setColorSemana13(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;

                        case R.id.ediColortSem12:
                            calibrFrutCalEnf.setColorSemana12(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;

                        case R.id.ediColortSem11:
                            calibrFrutCalEnf.setColorSemana11(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;
                        case R.id.ediColortSem10:
                            calibrFrutCalEnf.setColorSemana10(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;
                        case R.id.ediColortSem9:
                            calibrFrutCalEnf.setColorSemana9(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(Integer.parseInt(currentEditextNumRacims.getText().toString()));

                            break;

                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }



        RealtimeDB.UploadCalibracionFrutCal(calibrFrutCalEnf);


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


               // ediCompaniaTransporte,
                ediNombreChofer,
                ediCedula,
                ediCelular,
                ediPLaca,
               // ediMarcaCabezal,
              //  ediColorCabezal,
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
             //   ediUbicacionBalanza,

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






    private void createHashmapPesoBrutoCloster2y3lAndUpload(String keyNodoToUpload ){
        //Creamos un super array teximputeditext...con todas las casillas

        EditText        pbCluster01 = findViewById(R.id.pbCluster01);
        EditText        pbCluster05 = findViewById(R.id.pbCluster05);
        EditText        pbCluster03 = findViewById(R.id.pbCluster03);
        EditText        pbCluster02 = findViewById(R.id.pbCluster02);
        EditText        pbCluster04 = findViewById(R.id.pbCluster04);
        EditText        pbCluster010 = findViewById(R.id.pbCluster010);
        EditText        pbCluster09 = findViewById(R.id.pbCluster09);
        EditText        pbCluster07 = findViewById(R.id.pbCluster07);
        EditText        pbCluster08 = findViewById(R.id.pbCluster08);
        EditText        pbCluster06 = findViewById(R.id.pbCluster06);
        EditText        pbCluster011 = findViewById(R.id.pbCluster011);
        EditText        pbCluster015 = findViewById(R.id.pbCluster015);
        EditText        pbCluster012 = findViewById(R.id.pbCluster012);
        EditText        pbCluster013 = findViewById(R.id.pbCluster013);
        EditText        pbCluster014 = findViewById(R.id.pbCluster014);
        EditText        pbCluster016 = findViewById(R.id.pbCluster016);
        EditText        pbCluster019 = findViewById(R.id.pbCluster019);
        EditText        pbCluster018 = findViewById(R.id.pbCluster018);
        EditText        pbCluster020 = findViewById(R.id.pbCluster020);
        EditText        pbCluster017 = findViewById(R.id.pbCluster017);
        EditText        pbCluster025 = findViewById(R.id.pbCluster025);
        EditText        pbCluster024 = findViewById(R.id.pbCluster024);
        EditText        pbCluster023 = findViewById(R.id.pbCluster023);
        EditText        pbCluster022 = findViewById(R.id.pbCluster022);
        EditText        pbCluster021 = findViewById(R.id.pbCluster021);
        EditText        pbCluster028 = findViewById(R.id.pbCluster028);
        EditText        pbCluster027 = findViewById(R.id.pbCluster027);
        EditText        pbCluster029 = findViewById(R.id.pbCluster029);
        EditText        pbCluster026 = findViewById(R.id.pbCluster026);
        EditText        pbCluster030 = findViewById(R.id.pbCluster030);
        EditText        pbCluster034 = findViewById(R.id.pbCluster034);
        EditText        pbCluster031 = findViewById(R.id.pbCluster031);
        EditText        pbCluster035 = findViewById(R.id.pbCluster035);
        EditText        pbCluster033 = findViewById(R.id.pbCluster033);
        EditText        pbCluster032 = findViewById(R.id.pbCluster032);
        EditText        pbCluster039 = findViewById(R.id.pbCluster039);
        EditText        pbCluster040 = findViewById(R.id.pbCluster040);
        EditText        pbCluster037 = findViewById(R.id.pbCluster037);
        EditText        pbCluster038 = findViewById(R.id.pbCluster038);
        EditText        pbCluster036 = findViewById(R.id.pbCluster036);
        EditText        pbCluster043 = findViewById(R.id.pbCluster043);
        EditText        pbCluster045 = findViewById(R.id.pbCluster045);
        EditText        pbCluster042 = findViewById(R.id.pbCluster042);
        EditText        pbCluster041 = findViewById(R.id.pbCluster041);
        EditText        pbCluster044 = findViewById(R.id.pbCluster044);
        EditText        pbCluster048 = findViewById(R.id.pbCluster048);
        EditText        pbCluster046 = findViewById(R.id.pbCluster046);
        EditText        pbCluster050 = findViewById(R.id.pbCluster050);
        EditText        pbCluster047 = findViewById(R.id.pbCluster047);
        EditText        pbCluster049 = findViewById(R.id.pbCluster049);
        EditText        p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        EditText        p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        EditText        p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        EditText        p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        EditText        p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        EditText        p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        EditText        p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        EditText        p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        EditText        p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        EditText        p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        EditText        p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        EditText        p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        EditText        p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        EditText        p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        EditText        p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        EditText        p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        EditText        p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        EditText        p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        EditText        p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        EditText        p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        EditText        p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        EditText        p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        EditText        p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        EditText        p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        EditText        p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        EditText        p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        EditText        p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        EditText        p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        EditText        p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        EditText        p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        EditText        p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        EditText        p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        EditText        p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        EditText        p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        EditText        p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        EditText        p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        EditText        p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        EditText        p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        EditText        p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        EditText        p2pbCluster036 = findViewById(R.id.p2pbCluster036);


        EditText [] miArray= {
                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024 ,pbCluster023, pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036

        };

        //creamos el array...



        hasmapPesoBrutoClosters2y3L=new HashMap<>();


        for(int indice=0; indice< miArray.length; indice++){
            String keyOfView=String.valueOf(miArray[indice].getId());
            String valor=miArray[indice].getText().toString();

            //chekea que haya contenido
            if(!valor.trim().isEmpty()){
                float valorFlotante=Float.parseFloat(valor);

                hasmapPesoBrutoClosters2y3L.put(keyOfView+"/"+miArray[indice].getTag(),valorFlotante);



            }


        }



        //SUBIMOS EL MAPA
        if(hasmapPesoBrutoClosters2y3L.size()>0){

            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(hasmapPesoBrutoClosters2y3L,keyNodoToUpload);
        }


        Log.i("debugmapa","el size de el hasmap es ; "+hasmapPesoBrutoClosters2y3L.size());

    }




    void ocultaoTherVIEWs(){
        ediUbicacionBalanza.setVisibility(View.GONE);
        spinnerubicacionBalanza.setVisibility(View.GONE);


    }

}
