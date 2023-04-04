package com.tiburela.qsercom.activities.formularios;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

import static com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.adapters.SimpleItemTouchHelperCallback;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.callbacks.CallbackUploadNewReport;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.BottonSheetDfragmentVclds;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmNoAtach;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.ConnectionReceiver;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tiburela.qsercom.R;


public class ActivityContenedores extends AppCompatActivity implements View.OnClickListener  ,
        ConnectionReceiver.ReceiverListener , CallbackUploadNewReport {
    boolean esPrimeravezQueadd=true;

    public static CallbackUploadNewReport callbackUploadNewReport;
    boolean userCreoRegisterForm=false;
      Spinner spinnerExportadora;
    RecyclerViewAdapter adapterFrutas;

    ImageView imgVAtachProcesoFrutaFinca;
    ImageView imbTakePicProcesoFrutaFinca;
    ImageView imgVAtachLlegadaContenedor;
    ImageView imbTakePicLllegadaContenedor;
    ImageView imgVAtachSellosLlegada;
    ImageView imbTakePicSellosLlegada;
    ImageView imgVAtachPuertaAbiertaContenedor;
    ImageView imbTakePicPuertaAbiertaContenedor;
    ImageView imgVAtachFotosPallet;
    ImageView imbTakePicPallet;
    ImageView imgVAtachCierreContenedor;
    ImageView imbTakePicCierreContenedor;
    ImageView imgVAtachDocumentacionss;
    ImageView imbTakePicDocuementacionxx;

   // ImageView imgVAtachFotosPallet;

    private static final int PERMISSION_REQUEST_CODE = 100;
    private String UNIQUE_ID_iNFORME;
    public static Context context;
    ArrayList<ControlCalidad> listFormsControlCalidad = new ArrayList<>();
    ProgressDialog progress;
    Button btnCheck;
    String currentKeySharePrefrences="";
    boolean seSubioform=false;

    private final int CODE_TWO_PERMISIONS = 12;

    LinearLayout layoutContainerDatsProceso;


    private ImageView imgUpdatecAlfrutaEnfunde;

//o
    TextInputEditText ediClienteNombreReporte;



     ImageView imgAtachVinculacion;
    private int currentTypeImage=0;
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


    TextInputEditText ediTare;
    TextInputEditText ediBooking;
    TextInputEditText ediMaxGross;
    TextInputEditText ediNumSerieFunda;
    TextInputEditText stikVentolerExterna;
    TextInputEditText ediCableRastreoLlegada;
    TextInputEditText ediSelloPlasticoNaviera;
    TextInputEditText ediOtroSellosLlegada;

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

    LinearLayout layoutPesobrutoPorClusterSolo;

    LinearLayout layPesoBruto2;
    LinearLayout layPesoBruto1;


    Button btnSaveLocale;

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


    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;
    ////////



    @Override
    protected void onStart() {
        super.onStart();

       // Auth.initAuth(ActivityContenedores.this);
     //   Auth.signInAnonymously(ActivityContenedores.this);

Log.i("hellosweer","se ehjecitp onstart");




        try {
            progress.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void AddDataFormOfSharePrefeIfExistPrefrencesMap() {

        View [] arrrayAllViews=creaArryOfViewsAll();
        EditText [] arrayEdiText=creaArrayOfEditextCalendario();
        EditText [] arrayEdiTextLirbiado=generateArrayOfEditTextLibriado();


        try {
            Log.i("preferido","el currentKeySharePrefrences es  "+currentKeySharePrefrences);

            HashMap<String, String> currentMapPreferences= (HashMap<String, String>) SharePref.loadMap(currentKeySharePrefrences);
            Log.i("preferido","el size de mapa es "+currentMapPreferences.size());
            Utils.addDataOfPrefrencesInView(arrrayAllViews,currentMapPreferences);


            Map<String, String> currentMapPreferencesCalendario= SharePref.loadMap(currentKeySharePrefrences+"Calendario");
            Log.i("preferido","el size de mapa 2 es "+currentMapPreferencesCalendario.size());
            Utils.addDataOfPrefrencesInEditText(arrayEdiText,currentMapPreferencesCalendario);


            Map<String, String> currentMapPreferencesLibriado= SharePref.loadMap(currentKeySharePrefrences+"Libriado");
            Log.i("preferido","el size de mapa 3 es "+currentMapPreferencesLibriado.size());
            Utils.addDataOfPrefrencesInEditText(arrayEdiTextLirbiado,currentMapPreferencesLibriado);


        }


        catch (Exception e) {

            Log.i("preferido","la expecion es "+e.getMessage());
            e.printStackTrace();


        }









        /**aqui las imagenes */

         //descrgamos info de imagenes //todavia no muy lista aun

        ImagenReport.hashMapImagesData  =  SharePref.getMapImagesData(currentKeySharePrefrences);

        Log.i("dineroa","se eejcto este value   ");


        ArrayList<ImagenReport> listImagesToSaVE = new ArrayList<>(ImagenReport.hashMapImagesData .values());

        //if el formulario no es nulo

        if(listImagesToSaVE!=null && listImagesToSaVE.size()>0 ) {

           createlistsForReciclerviewsImages(listImagesToSaVE);


        }




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        callbackUploadNewReport = this;


        context=getApplicationContext();
        Variables.activityCurrent=Variables.FormContenedores;
        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString ="";//reseteamos
        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado ="";
        RecyclerViewAdapLinkage.mapWhitIdsCuadroMuestreo = new HashMap<>();
        RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds= new HashMap<>();

        Utils.userDecidioNoVincularAhora =false;

        Log.i("imagheddd","estamos debugeando");

        ImagenReport.hashMapImagesData=new HashMap<>();

        SharePref.init(ActivityContenedores.this);



        UNIQUE_ID_iNFORME="";

      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        Auth.initAuth(this);

        StorageData. initStorageReference();


        findViewsIds();

        hideViewsIfUserISCampo();
        configCertainSomeViewsAliniciar();
        addClickListeners();
        resultatachImages();
        listennersSpinners();

        getExportadorasAndSetSpinner();
        eventCheckdata();
        //creaFotos();

        ediRacimosRecha.setEnabled(false);
        ediRacimosRecha.setText(null);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            currentKeySharePrefrences=extras.getString(Variables.KEY_FORM_EXTRA);

            AddDataFormOfSharePrefeIfExistPrefrencesMap() ;
        }



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        callbackUploadNewReport = null;

    }

    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);

        String amPm = new SimpleDateFormat("aa").format(cldr.getTimeInMillis());

        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(ActivityContenedores.this,
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


                        if(minutes.length()==1){

                            minutes="0"+minutes;
                        }


                        if(vista.getId()==R.id.ediHoraInicio) {

                            ediHoraInicio.setText(sHour + ":" + minutes+" "+AM_PM);


                        }


                        else if (vista.getId()== R.id.ediHoraTermino) {

                            ediHoraTermino.setText(sHour + ":" + minutes+" "+AM_PM);



                        }



                        else if (vista.getId()== R.id.ediHoraLLegadaContenedor) {

                            ediHoraLLegadaContenedor.setText(sHour + ":" + minutes+" "+AM_PM);

                        }



                        else if (vista.getId()== R.id.ediHoraSalidaContenedor) {

                            ediHoraSalidaContenedor.setText(sHour + ":" + minutes+" "+AM_PM);

                        }


                        else if (vista.getId()== R.id.ediHoraEncendido1) {
                            ediHoraEncendido1.setText(sHour + ":" + minutes+" "+AM_PM);


                        }


                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+AM_PM);


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

                        String dateSelec=i2+"/"+(i1+1)+"/"+i;

                        ediFecha.setText(dateSelec);

                       // ediFecha.setText(daySemana+"/"+mes+"/"+year);

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
       // disableEditText(ediFotosLlegada);

        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);

        disableEditText(ediHoraEncendido1);
        disableEditText(ediHoraEncendido2);


    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar

        spinnerExportadora=findViewById(R.id.spinnerExportadora);

        imgVAtachProcesoFrutaFinca=findViewById(R.id.imgVAtachProcesoFrutaFinca);
        imbTakePicProcesoFrutaFinca=findViewById(R.id.imbTakePicProcesoFrutaFinca);
        imgVAtachLlegadaContenedor = findViewById(R.id.imgVAtachLlegadaContenedor);
        imbTakePicLllegadaContenedor= findViewById(R.id.imbTakePicLllegadaContenedor);
        imgVAtachSellosLlegada= findViewById(R.id.imgVAtachSellosLlegada);
        imbTakePicSellosLlegada= findViewById(R.id.imbTakePicSellosLlegada);
        imgVAtachPuertaAbiertaContenedor= findViewById(R.id.imgVAtachPuertaAbiertaContenedor);
        imbTakePicPuertaAbiertaContenedor= findViewById(R.id.imbTakePicPuertaAbiertaContenedor);
        imgVAtachFotosPallet= findViewById(R.id.imgVAtachFotosPallet);
        imbTakePicPallet= findViewById(R.id.imbTakePicPallet);
        imgVAtachCierreContenedor= findViewById(R.id.imgVAtachCierreContenedor);
        imgVAtachDocumentacionss = findViewById(R.id.imgVAtachDocumentacionss);
        imbTakePicCierreContenedor= findViewById(R.id.imbTakePicCierreContenedor);
        imbTakePicDocuementacionxx = findViewById(R.id.imbTakePicDocuementacionxx);



         layoutContainerDatsProceso=findViewById(R.id.layoutContainerDatsProceso);
         layoutPesobrutoPorClusterSolo=findViewById(R.id.layoutPesobrutoPorClusterSolo);
         layPesoBruto2=findViewById(R.id.layPesoBruto2);
         layPesoBruto1=findViewById(R.id.layPesoBruto1);
         ediClienteNombreReporte=findViewById(R.id.ediClienteNombreReporte);
         ediExportadoraProcesada = findViewById(R.id.ediExportadoraProcesada);
         ediExportadoraSolicitante = findViewById(R.id.ediExportadoraSolicitante);
         ediMarca = findViewById(R.id.ediMarca);
         imgUpdatecAlfrutaEnfunde=findViewById(R.id.imgUpdatecAlfrutaEnfunde);
         btnSaveLocale =findViewById(R.id.btnSaveLocale);


         ediExtCalid=findViewById(R.id.ediExtCalid);
          ediExtRodillo=findViewById(R.id.ediExtRodillo);
         ediExtGancho=findViewById(R.id.ediExtGancho);
         ediExtCalidCi=findViewById(R.id.ediExtCalidCi);
         ediExtRodilloCi=findViewById(R.id.ediExtRodilloCi);
        ediExtGanchoCi=findViewById(R.id.ediExtGanchoCi);



        ediEmpacadora=findViewById(R.id.ediEmpacadora);

        imgAtachVinculacion=findViewById(R.id.imgAtachVinculacion);

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




        ediTare=findViewById(R.id.ediTare);
        ediBooking=findViewById(R.id.ediBooking);
        ediMaxGross=findViewById(R.id.ediMaxGross);
        ediNumSerieFunda=findViewById(R.id.ediNumSerieFunda);
        stikVentolerExterna=findViewById(R.id.stikVentolerExterna);
        ediCableRastreoLlegada=findViewById(R.id.ediCableRastreoLlegada);
        ediSelloPlasticoNaviera=findViewById(R.id.ediSelloPlasticoNaviera);
        ediOtroSellosLlegada=findViewById(R.id.ediOtroSellosLlegada);

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
      //  layoutContainerDatsProceso.setOnClickListener(this);
        imgVAtachDocumentacionss.setOnClickListener(this);//ultimo
        imgVAtachProcesoFrutaFinca.setOnClickListener(this);
         imbTakePicProcesoFrutaFinca.setOnClickListener(this);
         imgVAtachLlegadaContenedor.setOnClickListener(this);
         imbTakePicLllegadaContenedor.setOnClickListener(this);
         imgVAtachSellosLlegada.setOnClickListener(this);
         imbTakePicSellosLlegada.setOnClickListener(this);
         imgVAtachPuertaAbiertaContenedor.setOnClickListener(this);
         imbTakePicPuertaAbiertaContenedor.setOnClickListener(this);
         imgVAtachFotosPallet.setOnClickListener(this);
         imbTakePicPallet.setOnClickListener(this);
         imgVAtachCierreContenedor.setOnClickListener(this);
         imbTakePicCierreContenedor.setOnClickListener(this);
         imbTakePicDocuementacionxx.setOnClickListener(this);




        btnSaveLocale.setOnClickListener(this);



        imgAtachVinculacion.setOnClickListener(this);
        ediHoraEncendido1.setOnClickListener(this);
        ediHoraEncendido2.setOnClickListener(this);

        imgUpdatecAlfrutaEnfunde.setOnClickListener(this);



        linLayoutHeader2.setOnClickListener(this);
        linLayoutHeader1.setOnClickListener(this);
        linLayoutHeader3.setOnClickListener(this);
        linLayoutHeader4.setOnClickListener(this);
        linLayoutHeader5.setOnClickListener(this);
        linLayoutHeader6.setOnClickListener(this);
        linLayoutHeader7.setOnClickListener(this);
        linLayoutHeader8.setOnClickListener(this);

        layoutPesobrutoPorClusterSolo.setOnClickListener(this);

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

        String data[]={"image/*"};
        Log.i("miclickimg","hemos hecho click");

        int idCurrent= view.getId();

        if(idCurrent==R.id.imgVAtachProcesoFrutaFinca || idCurrent==R.id.imgVAtachLlegadaContenedor || idCurrent==R.id.imgVAtachSellosLlegada ||
                idCurrent==R.id.imgVAtachPuertaAbiertaContenedor
                || idCurrent==R.id.imgVAtachFotosPallet || idCurrent==R.id.imgVAtachCierreContenedor ||
                idCurrent == R.id.imgVAtachDocumentacionss){  ///si es atach


            currentTypeImage=Integer.parseInt(view.getTag().toString());

            activityResultLauncher.launch(data);


            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);

        }

        else if(idCurrent==R.id.imbTakePicProcesoFrutaFinca || idCurrent==R.id.imbTakePicLllegadaContenedor
                || idCurrent==R.id.imbTakePicSellosLlegada ||
                idCurrent==R.id.imbTakePicPuertaAbiertaContenedor || idCurrent==R.id.imbTakePicPallet
                || idCurrent==R.id.imbTakePicCierreContenedor || idCurrent==R.id.imbTakePicDocuementacionxx ){ //si es tajke pic con camara

            currentTypeImage=Integer.parseInt(view.getTag().toString());


            takepickNow();

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);


        }

        else{
            switch (view.getId()) {

                case R.id.layoutPesobrutoPorClusterSolo:

                    if(layPesoBruto1.getVisibility()==View.VISIBLE){
                        layPesoBruto1.setVisibility(view.GONE);
                        layPesoBruto2.setVisibility(view.GONE);

                    }else{

                        layPesoBruto2.setVisibility(view.VISIBLE);
                        layPesoBruto1.setVisibility(view.VISIBLE);


                    }

                    break;



                case R.id.btnSaveLocale:

                    callPrefrencesSaveAndImagesData();


                    break; //


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
                    //LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerSeccion7);


                    if(layoutContainerDatsProceso.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerDatsProceso);
                    }



                    else{
                        oucultaLinearLayout(layoutContainerDatsProceso);
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

                case R.id.ediFecha:
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

                case R.id.ediHoraEncendido1:
                    // Utils.closeKeyboard(ActivityContenedores.this);
                    showingTimePicker(view);

                    break; //




                case R.id.ediHoraEncendido2:
                    // Utils.closeKeyboard(ActivityContenedores.this);
                    showingTimePicker(view);

                    break; //

                case R.id.imgUpdatecAlfrutaEnfunde:
                    Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");
                    getResultDatCalibCalEnfundes();
                    break;



                case R.id.imgAtachVinculacion:
                    Log.i("miclickimgddd","sellamo este");
                    showEditDialogAndSendData();
                    break;

            }


        }



    }




    private boolean getResultDatCalibCalEnfundes(){



        if(ediRacimosCosech.getText().toString().trim().isEmpty()){
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este valor es necesesario");

            return false;

        }


        EditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        EditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        EditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        EditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        EditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        EditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        EditText ediPorc14=findViewById(R.id.ediPorc14);
        EditText ediPorc13=findViewById(R.id.ediPorc13);
        EditText ediPorc12=findViewById(R.id.ediPorc12);
        EditText ediPorc11=findViewById(R.id.ediPorc11);
        EditText ediPorc10=findViewById(R.id.ediPorc10);
        EditText ediPorc9 =findViewById(R.id.ediPorc9);




        int numRacimosCosechados=Integer.parseInt(ediRacimosCosech.getText().toString());
        double resultpercente;
        DecimalFormat format = new DecimalFormat("#.##");

        int numeroRacimosContador=0;

        //numero de raCimos
        EditText [] miArrayNUmrACIMOS ={ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9};

        EditText [] miArraypORCENTAHJES ={ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPorc9};

        for(int i=0; i<miArrayNUmrACIMOS.length; i++){

            if(!miArrayNUmrACIMOS[i].getText().toString().trim().isEmpty())
                    {        ///tiene que ser mayor a cero
                        if(Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString())>0)
                        {  //operamoss
                            resultpercente= (Double.parseDouble(miArrayNUmrACIMOS[i].getText().toString())/numRacimosCosechados)*100;

                            String promDecim=format.format(resultpercente)   ;
                            Log.i("elvalues","el value es "+promDecim);

                            if(promDecim.contains(".")){
                                String [] array=promDecim.split("\\."); //tendremos un valor asi 58.50 normal


                                if(array[1].length()==1){ //tiene solo un valor.
                                    promDecim=promDecim+"0";
                                }


                            }

                            miArraypORCENTAHJES[i].setText(promDecim);
                            //sumaoslos racimos totale
                            numeroRacimosContador=numeroRacimosContador+Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString());



                        }

            }


        }



        //calculo aqwui

        if(numeroRacimosContador!=numRacimosCosechados){

            Snackbar.make(ediRacimosCosech, "El numero de racimos no concuerda con el numero de racimos cosechados", Snackbar.LENGTH_LONG)
                    .show();

            Log.i("dataracimos","no coincide");

            return false;




        }

        else {
            Log.i("dataracimos","SI coincide");

            return true;



        }



    }






    private ArrayList<String> generateLISTbyStringVinculados(String ValueLineViculados ){
        RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds = new HashMap<>();

                 ArrayList<String>listIdSvINCULADOS= new ArrayList<>();



                 if(! ValueLineViculados.trim().isEmpty() &&  ValueLineViculados.length()>1){
                     String [] miarrayiNFORMESvinc = ValueLineViculados.split(",");
                     Log.i("comerciales","el size de aara es "+miarrayiNFORMESvinc.length);

                     if(miarrayiNFORMESvinc.length >0) {

                         for(String value : miarrayiNFORMESvinc){
                             listIdSvINCULADOS.add(value);
                             Log.i("debfggf","sellamoeste este el key es  "+value);

                            RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds.put(value,value);

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

/*
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
    RecyclerViewAdapLinkage adapter = new RecyclerViewAdapLinkage(ActivityContenedores.this, lista, generateLISTbyStringVinculados(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString));
    //  this.adapter.setPlayPauseClickListener(this);
    reciclerView.setLayoutManager(layoutManager);
    reciclerView.setAdapter(adapter);


    adapter.setOnItemClickListener(new RecyclerViewAdapLinkage.ClickListener() {
        @Override
        public void onItemClick(int position, View v) {





            if( v.getTag(R.id.tagImgCategory).toString().equals("Cuadro Muestreo")){


                // String tipoInforme = (String) holder.checkBx.getTag(R.id.tipoInforme);


                ///    Variables.currentcuadroMuestreo=listCuadrosMuestreos.get(position)

                Variables.currentcuadroMuestreo=mapCudroMuestreo.get(String. valueOf(v.getTag(R.id.tagImgUniqueIdItem)));


                Log.i("dbuhehjr","el id selecte  es "+v.getTag(R.id.tagImgUniqueIdItem));

                showPRogressAndStartActivity(new Intent(ActivityContenedores.this, CuadMuestreoCalibAndRechazPrev.class));


            }else{

                Log.i("dbuhehjr","el id selecte es "+v.getTag(R.id.tagImgUniqueIdItem));



                //  Variables.currenControlCalReport= listFormsControlCalidad.get(position);

                Variables.currenControlCalReport=mapControlCalidad.get(String.valueOf(v.getTag(R.id.tagImgUniqueIdItem)));


                showPRogressAndStartActivity(new Intent(ActivityContenedores.this, FormularioControlCalidadPreview.class));


            }







            // Variables.currenControlCalReport= listFormsControlCalidad.get(position);

     // startActivity(new Intent(ActivityContenedores.this, FormularioControlCalidadPreview.class));


           bottomSheetDialog.dismiss();

        }
    });

}
*/

    private void showPRogressAndStartActivity(Intent i) {
        progress =new ProgressDialog(ActivityContenedores.this);

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
            }
        };


    }






    //informes especificos pueden servir para crgar solo unos especificos.......
    //y si queremos s
    private void dowloadReportsVinucladosLISTcONTROLcalidad(String reportidToSearch, int SizeArray , ArrayList<String>idsFormsControlCalidVinculados,String selecionado) {

        RealtimeDB.initDatabasesRootOnly();
        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").child(reportidToSearch);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listFormsControlCalidad = new ArrayList<>();


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ControlCalidad  user=ds.getValue(ControlCalidad.class);

                    listFormsControlCalidad.add(user);

                }

                //cuando las descrague todos
                if(listFormsControlCalidad.size() ==SizeArray){

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
/*
    void dowloadinformesby_EspecificDate(String dateSelecionado,ArrayList<String>idsFormsControlCalidVinculados){

        RealtimeDB.initDatabasesRootOnly();
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("simpleDate").equalTo(dateSelecionado);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ControlCalidad controlcalidad=ds.getValue(ControlCalidad.class);

                    if(controlcalidad!=null){
                        listFormsControlCalidad.add(controlcalidad);
                    }

                }

                     boolean existValues=false;

                    if(listFormsControlCalidad.size()>0){

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
        listFormsControlCalidad = new ArrayList<>();
        checkedListForms = new ArrayList<>();

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
                        listFormsControlCalidad.add(controlcalidad);

                        if(idsFormsControlCalidVinculadosOmit !=null){

                            if(idsFormsControlCalidVinculadosOmit.contains(controlcalidad.getUniqueId())){

                                checkedListForms.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",true,String.valueOf(controlcalidad.getUniqueId())));


                            }else {

                                checkedListForms.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",false,String.valueOf(controlcalidad.getUniqueId())));

                            }




                        }else{
                            checkedListForms.add(new CheckedAndAtatch(controlcalidad.getSimpleDate(),controlcalidad.getHacienda(),"Control calidad",false,String.valueOf(controlcalidad.getUniqueId())));


                        }


                    }


                }

                  //ceramos el anterior ..mostramos este...

                Log.i("samerr","se llamo sedatauncrecicler en dowloadinformesby_RangeDateAndCallShowSheetB ");
                Log.i("samerr","y el size es  "+ checkedListForms.size());

                setDataInRecyclerOfBottomSheet(reciclerViewBottomSheet, checkedListForms,false);




             //   showReportsAndSelectOrDeleteVinuclados(ActivityContenedores.this,existValues);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }




    private void SHOWamGHidenByNumsReportsVinculados(ArrayList<ControlCalidad>list, LinearLayout arraylayouts[],
                                                     CheckBox arrayCheckBox[],TextView arraytextViwsFila1x[],TextView arraytextViwsFila2x[]){


      int numReportsVinculads =4;


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


*/






    private void takepickNow() {

        if (android.os.Build.VERSION.SDK_INT >Build.VERSION_CODES.R && //adnroid 11
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED ){ //ANDROID 11

            takePickCamera();
        }

        else

        {

            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                takePickCamera();

                Log.i("codereister","permiso CONDEIDOIOTOMAMOS FOTO ES IF") ;
            }else{


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                        CODE_TWO_PERMISIONS);
            }

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

                                String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                              //  ActivityContenedores.this.getContentResolver().takePersistableUriPermission(cam_uri, Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                //        Intent.FLAG_GRANT_WRITE_URI_PERMISSION);



                              //  ActivityContenedores.this.getContentResolver().takePersistableUriPermission(cam_uri, Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                //      Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                                ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage,
                                        UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityContenedores.this,cam_uri))
                                        ,horientacionImg);

                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData,ActivityContenedores.this);

                                showImagesPicShotOrSelectUpdateView(false);



                            }

                         catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                           // showImagesPicShotOrSelectUpdateView(false);

                        }
                    }
                });











/*
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

*/




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




      private void resultatachImages() {
         // activityResultLauncher.getContract().
        activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.OpenMultipleDocuments(), new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    if (result != null) {

                        //creamos un objeto

                        for(int indice=0; indice<result.size(); indice++){
                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedores.this.getContentResolver(),result.get(indice));

                                String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);
                                Uri myUri = result.get(indice);


                                ActivityContenedores.this.getContentResolver().takePersistableUriPermission(myUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);


                                ImagenReport obcjImagenReport =new ImagenReport("",myUri.toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityContenedores.this,result.get(indice))),horientacionImg);
                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData,ActivityContenedores.this);



                            } catch (FileNotFoundException e) {
                                Log.i("imagheddd","lA EXCEPCION ES "+e.getMessage());

                                e.printStackTrace();
                            } catch (IOException e) {

                                Log.i("imagheddd","lA EXCEPCION ES "+e.getMessage());

                                e.printStackTrace();
                            }


                        }



                        showImagesPicShotOrSelectUpdateView(false);



                    }
                }
                  });
      }

/*
    private void resultatachImages2() {
        // activityResultLauncher.getContract().
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.OpenMultipleDocuments(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        if (result != null) {

                            //creamos un objeto

                            for(int indice=0; indice<result.size(); indice++){
                                try {

                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedores.this.getContentResolver(),result.get(indice));


                                    //  Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                                    String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);
                                    Uri myUri = result.get(indice);


                                    ActivityContenedores.this.getContentResolver().takePersistableUriPermission(myUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);


                                    // ActivityContenedores.this.getContentResolver().takePersistableUriPermission(myUri, Intent.FLAG_GRANT_READ_URI_PERMISSION |
                                    //   Intent.FLAG_GRANT_WRITE_URI_PERMISSION);



                                    ImagenReport obcjImagenReport =new ImagenReport("",myUri.toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityContenedores.this,result.get(indice))),horientacionImg);

                                    //obcjImagenReport.setImagenPathNow(Utils.getRealPathFromURI(myUri,ActivityContenedores.this));

                                    //   String pathFinal=  Utils.getPathFromUri(ActivityContenedores.this,myUri);

                                    ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                                    Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData,ActivityContenedores.this);

                                    //  Log.i("sabumaa","el payh de esta iamgen es "+pathFinal);




                                } catch (FileNotFoundException e) {
                                    Log.i("imagheddd","lA EXCEPCION ES "+e.getMessage());

                                    e.printStackTrace();
                                } catch (IOException e) {

                                    Log.i("imagheddd","lA EXCEPCION ES "+e.getMessage());

                                    e.printStackTrace();
                                }


                            }



                            showImagesPicShotOrSelectUpdateView(false);



                        }
                    }
                });
    }
*/

    private void listennersSpinners() {




        spinnerExportadora .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String textSelect= spinnerExportadora.getSelectedItem().toString();
                ediExportadoraProcesada.setText(textSelect);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    spTipoBoquilla .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String textSelect= spTipoBoquilla.getSelectedItem().toString();
            ediTipoBoquilla.setText(textSelect);
            if(textSelect.equalsIgnoreCase("Ninguna")){
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
            if(textSelect.equalsIgnoreCase("Ninguna")){
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
            if(textSelect.equalsIgnoreCase("Ninguna")){
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
                if(zonaEelejida.equalsIgnoreCase("Ninguna")){
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
            if(condicion.equalsIgnoreCase("Ninguna")){
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
            if(zonaEelejida.equalsIgnoreCase("Ninguna")){
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
            if(zonaEelejida.equalsIgnoreCase("Ninguna")){
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

            if(zonaEelejida.equalsIgnoreCase("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                ediTipoBalanza.setText("");
              //  actualizaListStateView("ediTipoBalanza",false) ;
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
            if(zonaEelejida.equalsIgnoreCase("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                editipbalanzaRepeso.setText("");
               // actualizaListStateView("addetiquetaaqui",false) ;
            }




              //  actualizaListStateView("addetiquetaaqui",true) ;


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

            if(zonaEelejida.equalsIgnoreCase("Ninguna")){
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
                switchLavdoRacimos.setText(" SI ");
            }else {
                ediLavadoRacimos.setText(" NO ") ;
                switchLavdoRacimos.setText(" NO ");


            }
        }
    });




    swAguaCorrida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if(swAguaCorrida.isChecked()){

                swAguaCorrida.setText(" SI ") ;
                ediAguaCorrida.setText(" SI ") ;

            }else {
                swAguaCorrida.setText(" NO ") ;
                ediAguaCorrida.setText(" NO ") ;

            }
        }
    });

}


private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg){


          if(isDeleteImg){

        currentTypeImage=Variables.typeoFdeleteImg;
    }




    ArrayList<ImagenReport> filterListImagesData=new ArrayList<ImagenReport>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW
    RecyclerView recyclerView=null;



    for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) {

        String key = set.getKey();

        ImagenReport value = set.getValue();

        if(value.getTipoImagenCategory()==currentTypeImage){

            filterListImagesData.add(ImagenReport.hashMapImagesData.get(key));

        }


    }


    GridLayoutManager layoutManager=new GridLayoutManager(this,2);

    RecyclerViewAdapter adapter;
    RecyclerViewAdapter aadpaterRecuperadoOFrView=null; //aqui almacenaremos  el adpater en caso que contrnga el reciclerview..
    switch(currentTypeImage){
        case Variables.FOTO_PROCESO_FRUTA_FINCA:
            recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();
            break;

        case Variables.FOTO_LLEGADA_CONTENEDOR:
            recyclerView= findViewById(R.id.recyclerFotollegadaContenedor);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();

            break;

        case Variables.FOTO_SELLO_LLEGADA:
            recyclerView= findViewById(R.id.recyclerFotoSellosLlegada);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();

            break;

        case Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR:
            recyclerView= findViewById(R.id.recyclerFotoPuertaAbrContedor);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();

            break;

        case Variables.FOTO_PALLETS:
            recyclerView= findViewById(R.id.recyclerFotoPallets);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();

            break;

        case Variables.FOTO_CIERRE_CONTENEDOR:
            recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();

            break;

        case Variables.FOTO_DOCUMENTACION:
            recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
            aadpaterRecuperadoOFrView= (RecyclerViewAdapter) recyclerView.getAdapter();

            break;
    }





    if(aadpaterRecuperadoOFrView!=null){ //el adpater no es nulo esta presente en algun reciclerview

        if(!isDeleteImg){
            //  aadpater.notifyItemInserted(filterListImagesData.size() - 1);
            ///   aadpater.notifyDataSetChanged();
            aadpaterRecuperadoOFrView.addItems(filterListImagesData); //le agremos los items

            aadpaterRecuperadoOFrView.notifyDataSetChanged(); //notificamos  no se si hace falta porque la clase del objeto ya lo tiene...

            // aadpater.notifyItemRangeInserted(0,filterListImagesData.size());
            // aadpater. notifyItemRangeChanged(position, listImagenData.size());

            Log.i("adpatertt","adpasternotiff");

        }

        Log.i("adpatertt","es difrentede nulo");

    }else{

        adapter=new RecyclerViewAdapter(filterListImagesData,this);
        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        eventoBtnclicklistenerDelete(adapter);

        Log.i("adpatertt","el adpater es nulo");


        Log.i("adpatertt","el adpater es nulo");
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);


    }


    Log.i("superemasisa","el size de lista aqui before "+filterListImagesData.size());



}



private void eventCheckdata(){// verificamos que halla llenado toda la info necesaria..


    btnCheck=findViewById(R.id.btnCheck);


    btnCheck.setOnClickListener(new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onClick(View view) {
            btnCheck.setEnabled(false);



            checkDataFields();



        }
    });





}

void checkDataFields(){ //


       if(Variables.usuarioQserconGlobal==null){
           Toast.makeText(ActivityContenedores.this, "No puedes subir hasta que inicies sesión, ¡Guárdalo  localmente", Toast.LENGTH_LONG).show();
           return;
       }



    if(! checkDatosGeneralesIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkDatosGeneralesIsLleno");
        return;
    }



    else{
        Log.i("test001","si esta lleno checkDatosGeneralesIsLleno ");

    }



    if(! checkcantidadPostcosechaIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkcantidadPostcosechaIsLleno");
        return;
    }else{
        Log.i("test001","si esta lleno checkcantidadPostcosechaIsLleno ");

    }


    if(! checkDatosContenedorIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkDatosContenedorIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDatosContenedorIsLleno");
    }


    if(! checkDataSellosLlegadaIsLleno()){
       btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkDataSellosLlegadaIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkDataSellosLlegadaIsLleno");


    }


    if(! checkSellosInstaladosIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkSellosInstaladosIsLleno");

        return;
    }else{

        Log.i("test001","si  esta lleno  checkSellosInstaladosIsLleno");


    }


    if(! checkDatosTransportistaIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkDatosTransportistaIsLleno");

        return;
    }

    else{

        Log.i("test001","si  esta lleno  checkDatosTransportistaIsLleno");


    }


    if(! checkDatosProcesoIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkDatosProcesoIsLleno");

        return;
    }

    else
    {

        Log.i("test001","si  esta lleno  checkDatosProcesoIsLleno");


    }



    if(! checkDatosHaciendaIsLleno()){
        btnCheck.setEnabled(true);

        Log.i("test001","no esta lleno  checkDatosHaciendaIsLleno");

        return;
    }

    else

    {

        Log.i("test001","si  esta lleno  checkDatosHaciendaIsLleno");


    }


    if(! checkQueexistminim()){
        Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");
        btnCheck.setEnabled(true);

        return;
    }




    else
    {

        Log.i("test001","si  esta lleno  checkDataCalibFrutaCalEnfn");


    }





    if(! getResultDatCalibCalEnfundes()){
        Log.i("test001","no esta lleno  getResultDatCalibCalEnfundes");
        btnCheck.setEnabled(true);

        return;

    }

    /*
    else

    {
        Log.i("test001","si  esta lleno  getResultDatCalibCalEnfundes");
    }
*/

    if(! cehckFaltanImagenes()){
        Log.i("test001","no esta lleno  cehckFaltanImagenes");
        btnCheck.setEnabled(true);

        return;
    }



    /*
    else

    {
        Log.i("test001","si  esta lleno  cehckFaltanImagenes");
    }
*/



    if(!Utils.checkifAtach()){
        btnCheck.setEnabled(true);
        Log.i("test001","no esta lleno  checkifAtach");
        FragmentManager fm = getSupportFragmentManager();
        DialogConfirmNoAtach alertDialog = DialogConfirmNoAtach.newInstance(Constants.CONTENEDORES);
        alertDialog.show(fm, "duialoffragment_alertZ");
        return;
    }



    //asi guardamos la posiscion de las imagenes para prdenar.......
    updatePostionImegesSort();


    createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...


}

private void updatePostionImegesSort(){
    RecyclerView recyclerView=null;
    recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
    Utils.updatePositionObjectImagenReport(recyclerView);

    recyclerView= findViewById(R.id.recyclerFotollegadaContenedor);
    Utils.updatePositionObjectImagenReport(recyclerView);

    recyclerView= findViewById(R.id.recyclerFotoSellosLlegada);
    Utils.updatePositionObjectImagenReport(recyclerView);

    recyclerView= findViewById(R.id.recyclerFotoPuertaAbrContedor);
    Utils.updatePositionObjectImagenReport(recyclerView);

    recyclerView= findViewById(R.id.recyclerFotoPallets);
    Utils.updatePositionObjectImagenReport(recyclerView);

    recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
    Utils.updatePositionObjectImagenReport(recyclerView);

    recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
    Utils.updatePositionObjectImagenReport(recyclerView);



}


private void createObjcInformeAndUpload(){


        //cremaos un hasmpa con los libriados
    HashMap<String,Float>miMapLbriado=generateMapLibriadoIfExistAndUpload();
    String keyWhereLocaleHashMapLibriado="";


    if(miMapLbriado.size()>0){

         keyWhereLocaleHashMapLibriado=RealtimeDB.rootDatabaseReference.push().getKey();

        RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado,keyWhereLocaleHashMapLibriado);


    }


//aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo

    SetInformEmbarque1 informe = new SetInformEmbarque1(ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString(),
            ediMarca.getText().toString(), UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),

            ediNhojaEvaluacion.getText().toString(), ediZona.getText().toString()
            ,ediProductor.getText().toString(),ediCodigo.getText().toString()
            ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
            ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
            ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
            ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),
            FieldOpcional.observacionOpcional,ediHoraLLegadaContenedor.getText().toString(),ediHoraSalidaContenedor.getText().toString()
            ,ediDestino.getText().toString(),ediNViaje.getText().toString(),ediNumContenedor.getText().toString(),ediVapor.getText().toString(),
            ediTipoContenedor.getText().toString(),ediTare.getText().toString(),ediBooking.getText().toString(),ediMaxGross.getText().toString(),
            ediNumSerieFunda.getText().toString(),stikVentolerExterna.getText().toString(),
            ediCableRastreoLlegada.getText().toString(),ediSelloPlasticoNaviera.getText().toString(),FieldOpcional.otrosSellosLLegaEspecif,ediClienteNombreReporte.getText().toString());


            informe.setKeyOrNodeLibriadoSiEs(keyWhereLocaleHashMapLibriado);




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
            switchBalanzaRep.isChecked(),spinnerubicacionBalanza.getSelectedItem().toString(),ediTipoBalanza.getText().toString(),ediBalanzaRepeso.getText().toString());


    SetInformDatsHacienda informe3= new SetInformDatsHacienda(spFuenteAgua.getSelectedItem().toString(),swAguaCorrida.isChecked(), switchLavdoRacimos.isChecked(),
            spFumigaCorL1.getSelectedItem().toString(),
            spTipoBoquilla.getSelectedItem().toString()


            ,ediCajasProcDesp.getText().toString(),
            ediRacimosCosech.getText().toString(),ediRacimosRecha.getText().toString(),ediRacimProces.getText().toString(),UNIQUE_ID_iNFORME
            ,ediExtCalid.getText().toString(),ediExtCalidCi.getText().toString());

    updateDatosEvaludoresOFinforme3(informe3);

    getResultDatCalibCalEnfundes();//CLVAE


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
    addProdcutsPostCosechaAndUpload(informe.getUniqueIDinforme()); //agregamos y subimos los productos postcosecha..




}


    private void updateDatosEvaludoresOFinforme3(SetInformDatsHacienda informe3) {

        if(!ediExtGancho.getText().toString().trim().isEmpty()){
            informe3.setExtensionistEnGancho(ediExtGancho.getText().toString());
            informe3.setCI_extensionistEnGancho(ediExtGanchoCi.getText().toString());

        }


        if(!ediExtRodillo.getText().toString().trim().isEmpty()){
            informe3.setExtensionistDeRodillo(ediExtRodillo.getText().toString());
            informe3.setCI_extensionistDeRodillo(ediExtRodilloCi.getText().toString());

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

        Log.i("imagheddd","se llamometodoel size de lista es "+ImagenReport.hashMapImagesData.size());

        if(ImagenReport.hashMapImagesData.size() ==0 ){
            Log.i("imagheddd","es igual a cero");

            Toast.makeText(this, "esta vacia ", Toast.LENGTH_SHORT).show();
             return;
        }

        Log.i("imagheddd","es difrente de cero");




            ImagenReport.updateIdPerteence(StorageData.uniqueIDImagesSetAndUInforme,ImagenReport.hashMapImagesData);
           ArrayList<ImagenReport>list=Utils.mapToArrayList(ImagenReport.hashMapImagesData);
         StorageData.uploaddata(list);

       //  Utils.updateImageReportObjec(); //asi actualizamos la propiedad sortPositionImage,



        // StorageData.uploadImage(ActivityContenedores.this, ImagenReport.hashMapImagesData);
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


        if(ImagenReport.hashMapImagesData==null){

            ImagenReport.hashMapImagesData=new HashMap<>();

            Log.i("dineroa","es nulo .hashMapImagesData");


        }



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


        if(ediExportadoraProcesada.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExportadoraProcesada.requestFocus();
            ediExportadoraProcesada.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }


        if(ediExportadoraSolicitante.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExportadoraSolicitante.requestFocus();
            ediExportadoraSolicitante.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }

        if(ediMarca.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediMarca.requestFocus();
            ediMarca.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre
        }



        if(ediClienteNombreReporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediClienteNombreReporte.requestFocus();
            ediClienteNombreReporte.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre
        }




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
            scroollElementoFaltante(ediNhojaEvaluacion);
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
              scroollElementoFaltante(ediObservacion);

              FieldOpcional.observacionOpcional=ediObservacion.getText().toString();

        }


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







        return true;


    }


    private boolean checkSellosInstaladosIsLleno(){

        LinearLayout layoutContainerSeccion5=findViewById(R.id.layoutContainerSeccion5);
/*

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


        */


        if(ediCandadoqsercon.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCandadoqsercon.requestFocus();
            ediCandadoqsercon.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        /*
        if(ediSelloNaviera.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediSelloNaviera.requestFocus();
            ediSelloNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        */


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






      return true;

    }




    private boolean checkDatosHaciendaIsLleno(){
        LinearLayout layoutContainerSeccion8=findViewById(R.id.layoutContainerSeccion8);


        if(ediFuenteAgua.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFuenteAgua.requestFocus();
            ediFuenteAgua.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);


            return false;

        }else{

            ediFuenteAgua.setError(null);


        }


/*
        if(ediAguaCorrida.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediAguaCorrida.requestFocus();
            ediAguaCorrida.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }else{

            ediAguaCorrida.setError(null);

        }

*/

        if(ediFumigacionClin1.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediFumigacionClin1.requestFocus();
            ediFumigacionClin1.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        else{

            ediFumigacionClin1.setError(null);

        }



        if(ediTipoBoquilla.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBoquilla.requestFocus();
            ediTipoBoquilla.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        else{

            ediTipoBoquilla.setError(null);

        }


        if(ediCajasProcDesp.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        else{

            ediCajasProcDesp.setError(null);

        }





        if(ediRacimosCosech.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        else{

            ediRacimosCosech.setError(null);

        }


        if(! Utils.checkifAtach()){
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Vincula Un Reporte C.muestro Rechazados");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            Log.i("test001","no esta lleno  checkifAtach");
            FragmentManager fm = getSupportFragmentManager();
            DialogConfirmNoAtach alertDialog = DialogConfirmNoAtach.newInstance(Constants.PREV_CONTENEDORES);
            // alertDialog.setCancelable(false);
            alertDialog.show(fm, "duialoffragment_alertZ");
            return false;
        }



/*
        if(ediRacimosRecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Vincula Un Reporte C.muestro Rechazados");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;
        }
*/




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
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(ediCondicionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediCondicionBalanza);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }else{

           // ediCondicionBalanza.setError(null);
            ediCondicionBalanza.setError(null);


        }

        if(ediTipodeCaja.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediTipodeCaja);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        else{

           // ediTipodeCaja.setError(null);
            ediTipodeCaja.setError(null);


        }




        if(ediTipoPlastico.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediTipoPlastico);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        else{

            ediTipoPlastico.setError(null);

        }



        if(ediTipoBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediTipoBalanza);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        else{

            ediTipoBalanza.setError(null);

        }


        if(switchBalanzaRep.isChecked()  && editipbalanzaRepeso.getText().toString().trim().isEmpty() ){

             editipbalanzaRepeso.requestFocus();
            editipbalanzaRepeso.setError("Selecione el tipo de balanza");
            scroollElementoFaltante(editipbalanzaRepeso);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
             return false;
        }




/*
        if(editipbalanzaRepeso.getText().toString().isEmpty()){ //chekamos que no este vacia
           // editipbalanzaRepeso.requestFocus();
            //editipbalanzaRepeso.setError("Este espacio es obligatorio");
            //scroollElementoFaltante(editipbalanzaRepeso);

            //layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
           // return false;

        }

*/
        if(ediUbicacionBalanza.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediUbicacionBalanza.requestFocus();
            ediUbicacionBalanza.setError("Este espacio es obligatorio");
            scroollElementoFaltante(ediUbicacionBalanza);

            layoutContainerDatsProceso.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        else{

            ediUbicacionBalanza.setError(null);

        }


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


//upload data...

//descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad..



    private View[] creaArryOfViewsAll() {



        View [] arrayViews = {
                ediExportadoraProcesada,
                ediExportadoraSolicitante,
                ediMarca,
                ediClienteNombreReporte,
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
                // ediFotosLlegada,
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
                ediNumContenedor,
                //ediFotoContenedor,
                // ediFotosPposcosecha,
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
                // ediFotosLlegadaTransport,

                ediTare,
                ediBooking,
                ediMaxGross,
                ediNumSerieFunda,
                stikVentolerExterna,
                ediCableRastreoLlegada,
                ediSelloPlasticoNaviera,
                ediOtroSellosLlegada,
                //ediFotosSellosLLegada,

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



                spinnerExportadora,
                spinnerSelectZona,
                spinnerCondicionBalanza,
                spinnertipoCaja,
                spinnertipodePlastico,
                spinnertipodeBlanza ,
                spinnertipodeBlanzaRepeso ,
                spinnerubicacionBalanza ,


                spFuenteAgua ,
                spFumigaCorL1 ,
                spTipoBoquilla ,

                switchContenedor,
                switchHaybalanza,
                switchHayEnsunchado,
                switchBalanzaRep,
                switchLavdoRacimos,
                swAguaCorrida,

                ediExtCalid,
                ediExtCalidCi,
                ediExtGancho,
                ediExtGanchoCi,
                ediExtRodillo,
                ediExtRodilloCi

        } ;


        return arrayViews;
    }



    private EditText[] creaArrayOfEditextCalendario () {

          EditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        EditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        EditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        EditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        EditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        EditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        EditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        EditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        EditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        EditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        EditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        EditText ediNumRac9 = findViewById(R.id.ediNumRac9);

        EditText ediPorc14=findViewById(R.id.ediPorc14);
        EditText ediPorc13=findViewById(R.id.ediPorc13);
        EditText ediPorc12=findViewById(R.id.ediPorc12);
        EditText ediPorc11=findViewById(R.id.ediPorc11);
        EditText ediPorc10=findViewById(R.id.ediPorc10);
        EditText ediPsgddsorc9 =findViewById(R.id.ediPorc9);



         EditText [] arrayEditText= {

                 ediColortSem14, ediColortSem13, ediColortSem12,
                 ediColortSem11, ediColortSem10, ediColortSem9,


                 ediNumRcim14, ediNumRcim13, ediNumRcim12,
                 ediNumRcim11, ediNumRcim10, ediNumRac9, ediPorc14,
                 ediPorc13, ediPorc12, ediPorc11, ediPorc10, ediPsgddsorc9

         };


         return arrayEditText;

    }



    void addImagesInRecyclerviews(ArrayList<ImagenReport>listImagenReports){


        RecyclerView recyclerView= null;

        switch(currentTypeImage){
            case Variables.FOTO_PROCESO_FRUTA_FINCA:
                recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
                break;

            case Variables.FOTO_LLEGADA_CONTENEDOR:
                recyclerView= findViewById(R.id.recyclerFotollegadaContenedor);
                break;

            case Variables.FOTO_SELLO_LLEGADA:
                recyclerView= findViewById(R.id.recyclerFotoSellosLlegada);
                break;

            case Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR:
                recyclerView= findViewById(R.id.recyclerFotoPuertaAbrContedor);
                break;

            case Variables.FOTO_PALLETS:
                recyclerView= findViewById(R.id.recyclerFotoPallets);
                break;

            case Variables.FOTO_CIERRE_CONTENEDOR:
                recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
                break;

            case Variables.FOTO_DOCUMENTACION:
                recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
                break;
        }


        RecyclerViewAdapter adapter=new RecyclerViewAdapter(listImagenReports,this);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.

            if(recyclerView!=null){
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                eventoBtnclicklistenerDelete(adapter);

             //   Utils.drawImages(adapter,recyclerView,listImagenReports,ActivityContenedores.this);



                ItemTouchHelper.Callback callback =
                        new SimpleItemTouchHelperCallback(adapter);
                ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
                touchHelper.attachToRecyclerView(recyclerView);



            }




    }




    void createlistsForReciclerviewsImages(ArrayList<ImagenReport>listImagenReports){

        ArrayList<ImagenReport>lisFiltrada;

        int [] arrayTiposImagenes = {Variables.FOTO_PROCESO_FRUTA_FINCA,Variables.FOTO_LLEGADA_CONTENEDOR,Variables.FOTO_SELLO_LLEGADA,
                                     Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR,Variables.FOTO_PALLETS,
                Variables.FOTO_CIERRE_CONTENEDOR, Variables.FOTO_DOCUMENTACION};


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


    }




    private boolean checkQueexistminim() {

        EditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        EditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        EditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        EditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        EditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        EditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        EditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        EditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        EditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        EditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        EditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        EditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        EditText ediPorc14=findViewById(R.id.ediPorc14);
        EditText ediPorc13=findViewById(R.id.ediPorc13);
        EditText ediPorc12=findViewById(R.id.ediPorc12);
        EditText ediPorc11=findViewById(R.id.ediPorc11);
        EditText ediPorc10=findViewById(R.id.ediPorc10);
        EditText ediPsgddsorc9 =findViewById(R.id.ediPorc9);


        EditText [] array = {ediColortSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10, ediColortSem9,
                ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9,
                ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9};


        int indice=0;


        for(int i=0; i<array.length; i++){

            EditText current =array [i];

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

                    case R.id.ediPorc9:
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

        EditText ediColortSem14 = findViewById(R.id.ediColortSem14);
        EditText ediColortSem13 = findViewById(R.id.ediColortSem13);
        EditText ediColortSem12 = findViewById(R.id.ediColortSem12);
        EditText ediColortSem11 = findViewById(R.id.ediColortSem11);
        EditText ediColortSem10 = findViewById(R.id.ediColortSem10);
        EditText ediColortSem9 = findViewById(R.id.ediColortSem9);

        EditText ediNumRcim14 = findViewById(R.id.ediNumRcim14);
        EditText ediNumRcim13 = findViewById(R.id.ediNumRcim13);
        EditText ediNumRcim12 = findViewById(R.id.ediNumRcim12);
        EditText ediNumRcim11 = findViewById(R.id.ediNumRcim11);
        EditText ediNumRcim10 = findViewById(R.id.ediNumRcim10);
        EditText ediNumRac9 = findViewById(R.id.ediNumRac9);


        EditText ediPorc14=findViewById(R.id.ediPorc14);
        EditText ediPorc13=findViewById(R.id.ediPorc13);
        EditText ediPorc12=findViewById(R.id.ediPorc12);
        EditText ediPorc11=findViewById(R.id.ediPorc11);
        EditText ediPorc10=findViewById(R.id.ediPorc10);
        EditText ediPsgddsorc9 =findViewById(R.id.ediPorc9);


        EditText [] array = {ediColortSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10, ediColortSem9,
                ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9,
                ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9};


        int indice=0;


        for(int i=0; i<array.length; i++){

            EditText current =array [i];

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

                    case R.id.ediPorc9:
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

        if(!seSubioform){
            checkIfExistIdAndUpload(uniqueId,informe,informe2,informe3);

        }




    }



    private void checkIfExistIdAndUpload (String currenTidGenrate, SetInformEmbarque1 informe,SetInformEmbarque2 informe2, SetInformDatsHacienda informe3){

      //  private void checkIfExistIdAndUpload(String currenTidGenrate ) {
      //  Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);

        Query query = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros").equalTo(currenTidGenrate);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InformRegister  user=null;

                for (DataSnapshot ds : snapshot.getChildren()) {
                      user=ds.getValue(InformRegister.class);
                }


                if(user == null && ! seSubioform) { //quiere decir que no existe

                    Log.i("imagebrr","elunique id informe es "+currenTidGenrate);

                    StorageData.uniqueIDImagesSetAndUInforme=currenTidGenrate;


                    informe.setUniqueIDinforme(currenTidGenrate);
                    informe2.setUniqueIDinformePart2(currenTidGenrate);
                    informe3.setUniqueIDinformeDatsHda(currenTidGenrate);//ELMISMO ID ,,PERO DIFRENTEPROPIEDAD


                    if( RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString!=null){
                        informe.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
                    }


                    if( RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado !=null){
                        informe.setAtachControCuadroMuestreo(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

                    }


                    uploadInformeToDatabase(informe,informe2,informe3);

                    user= new InformRegister(currenTidGenrate,Constants.CONTENEDORES,
                            Variables.usuarioQserconGlobal.getNombreUsuario(),
                            Variables.usuarioQserconGlobal.getUniqueIDuser()
                            , "CONTENEDORES ",ediExportadoraProcesada.getText().toString(),Utils.hasmpaExportadoras.get(ediExportadoraProcesada.getText().toString()).getNameExportadora());


                    //informe register
                    Log.i("imagebrr","elsize es "+ImagenReport.hashMapImagesData.size());


                    uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

                    RealtimeDB.addNewRegistroInforme(ActivityContenedores.this,user);

                    //aqui subimos..

                }else {  //si exite creamos otro value...

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
       // cameraIntent.addCategory(Intent.CATEGORY_OPENABLE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

       /// cameraIntent.setData(cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION),cam_uri);


        cameraIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                | Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);



        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY

    }




    private void DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(String currentIDoBJECTvinuc ){

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(currentIDoBJECTvinuc);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe=ds.getValue(CuadroMuestreo.class);
                    Log.i("holerd","aqui se encontro un cuadro muestreo......");

                    if(informe!=null){

                       // Variables.CurrenReportPart3.setEdiRacimosRecha(  String.valueOf(informe.getTotalRechazadosAll()));

                        ediRacimosRecha.setText(String.valueOf(informe.getTotalRechazadosAll()));

                        //actualizamos el objeto..\\


                     //   if( Variables.CurrenReportPart1!=null){


                     //   }
                      ///  Variables.CurrenReportPart3.setEdiRacimosRecha(String.valueOf(informe.getTotalRechazadosAll()));


                      //  btnGENERARpdf.setEnabled(true);

                        break;
                    }


                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());

            }
        } );

    }




    private void showEditDialogAndSendData() {

        Bundle bundle = new Bundle();
        bundle.putString(Variables.KEY_CONTROL_CALIDAD_ATACHEDS, RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        bundle.putString(Variables.KEY_CUADRO_MUETREO_ATACHED, RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);



        //


        FragmentManager fm = getSupportFragmentManager();
        BottonSheetDfragmentVclds alertDialog = BottonSheetDfragmentVclds.newInstance(Constants.CONTENEDORES);
       // alertDialog.setCancelable(false);

        alertDialog.setArguments(bundle);
        alertDialog.show(fm, "duialoffragment_alert");



    }




    public void updateVinucladosObject(){

        TextView txtNumReportsVinclds=findViewById(R.id.txtNumReportsVinclds);

        txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculadsAll));


        if(!RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.trim().isEmpty()){ //lodecsrgamos y seteamos info

            DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

        }


    }



public void decideaAtachReport(boolean userSelecion){




      if(userSelecion){ //SELECIONO ATCH
            Log.i("test001"," seleciono 200");

            ScrollView scrollView2 =findViewById(R.id.scrollView2);

            scrollView2.post(new Runnable() {
                public void run() {
                    scrollView2.scrollTo(0, imgAtachVinculacion.getBottom());
                }
            });

        }


/*
        else { //USUARIO SELECION OMITR TODS
            //AQUI VAMOS A SUBIR DATA..

          //gaurdamops  aqui
          createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...

          Log.i("test001"," seleciono 300");


        }

*/




}


private void callPrefrencesSaveAndImagesData(){

       View [] arrayAllViewsData=creaArryOfViewsAll();
       EditText [] arrayEdiTextCalendario =creaArrayOfEditextCalendario();
       EditText [] arrayEdiTextLibriado=generateArrayOfEditTextLibriado();



    Log.i("preferido","el current key es "+currentKeySharePrefrences);


    if(!currentKeySharePrefrences.equals("") || userCreoRegisterForm){  //si no contiene
      Log.i("saberrr","se ejecuto el if ");

        SharePrefHelper.viewsSaveInfo(arrayAllViewsData,currentKeySharePrefrences,ActivityContenedores.this);
        SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextCalendario,currentKeySharePrefrences+"Calendario");
        SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextLibriado,currentKeySharePrefrences+"Libriado");



        SharePref.saveHashMapImagesData(ImagenReport.hashMapImagesData,currentKeySharePrefrences);


        Toast.makeText(ActivityContenedores.this, "Guardado Localmente", Toast.LENGTH_SHORT).show();


        //significa que tenemos un key de un objeto obtneido de prefrencias

    }

    else
    { //no existe creamos un nuevo register..
        Log.i("saberrr","se ejecuto el else ");


        Map<String, InformRegister>miMpaAllrRegisters=SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);


        currentKeySharePrefrences=UUID.randomUUID().toString();

        InformRegister inform= new InformRegister(currentKeySharePrefrences,Constants.CONTENEDORES,"Usuario", "","Contenedores" ,ediExportadoraProcesada.getText().toString(),
                Utils.hasmpaExportadoras.get(ediExportadoraProcesada.getText().toString()).getNameExportadora()
                );


        //gudramos oejto en el mapa
        miMpaAllrRegisters.put(inform.getInformUniqueIdPertenece(),inform);

        SharePref.saveHashMapOfHashmapInformRegister(miMpaAllrRegisters,SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

        //guardamos info de  views en un mapa usnado el nismo id delobejto creado
        SharePrefHelper.viewsSaveInfo(arrayAllViewsData,currentKeySharePrefrences,ActivityContenedores.this);
        SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextCalendario,currentKeySharePrefrences+"Calendario");
        SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextLibriado,currentKeySharePrefrences+"Libriado");

        Toast.makeText(ActivityContenedores.this, "Guardado Localmente", Toast.LENGTH_SHORT).show();


        if(ImagenReport.hashMapImagesData.size()>0){ //

          SharePref.saveHashMapImagesData(ImagenReport.hashMapImagesData,currentKeySharePrefrences);


        }

        userCreoRegisterForm=true;
    }






}


    public void scroollElementoFaltante(View vistFocus){

       // View targetView = findViewById(R.id.DESIRED_VIEW_ID);
        vistFocus.getParent().requestChildFocus(vistFocus,vistFocus);



    }




    //Y AHORA SET DATA YN IEWS...




    /***iniciamos find view id en peso burto por clusters... **/

    HashMap<String, Float> generateMapLibriadoIfExistAndUpload(){

        EditText        ediMarcaCol1 = findViewById(R.id.ediMarcaCol1);
        EditText        ediMarcaCol2 = findViewById(R.id.ediMarcaCol2);
        EditText        ediMarcaCol3 = findViewById(R.id.ediMarcaCol3);
        EditText        ediMarcaCol4 = findViewById(R.id.ediMarcaCol4);
        EditText        ediMarcaCol5 = findViewById(R.id.ediMarcaCol5);




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

                ediMarcaCol1,ediMarcaCol2,ediMarcaCol3,ediMarcaCol4,ediMarcaCol5,

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

                if(currentEdit.getId()==R.id.ediMarcaCol1 || currentEdit.getId()==R.id.ediMarcaCol2 || currentEdit.getId()==R.id.ediMarcaCol3
                ||currentEdit.getId()==R.id.ediMarcaCol4||currentEdit.getId()==R.id.ediMarcaCol5){

                    miMapData.put(currentEdit.getText().toString() + "-" +currentEdit.getTag() , 9.999f);


                }else{

                    miMapData.put(currentEdit.getId()+"-"+currentEdit.getTag(),Float.parseFloat(currentEdit.getText().toString()));

                }




            }


        }






        return  miMapData;

    }







    boolean cehckFaltanImagenes() {

        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PROCESO_FRUTA_FINCA)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();
            return false;

        }
        else
        {

            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotosSellosInstalados.clearFocus();

        }



        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_LLEGADA_CONTENEDOR)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoLLegadaContenedor);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoLLegadaContenedor);
            ediFotosSellosInstalados.clearFocus();
        }



        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_SELLO_LLEGADA)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoSellosLLegada);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoSellosLLegada);
            ediFotosSellosInstalados.clearFocus();

        }



        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR)){
            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtFotoPuertacontenedor);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotosSellosInstalados=findViewById(R.id.txtFotoPuertacontenedor);
            ediFotosSellosInstalados.clearFocus();
        }





        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PALLETS)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtFotosPallets);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.txtFotosPallets);
            ediFotosSellosInstalados.clearFocus();

        }







        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_CIERRE_CONTENEDOR)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtCierreContenedor);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.txtCierreContenedor);
            ediFotosSellosInstalados.clearFocus();

        }



        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_DOCUMENTACION)){
            TextView ediFotoProcesoEnFruta=findViewById(R.id.txtFotosDocumentacion);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{

            TextView ediFotosSellosInstalados=findViewById(R.id.txtFotosDocumentacion);
            ediFotosSellosInstalados.clearFocus();

        }


        return true;
    }




    private void  showToast(){

        Toast.makeText(ActivityContenedores.this, "Falta Imagen", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void uploadNewForm() {
        btnCheck.setEnabled(false);

        seSubioform=true;


        if(!currentKeySharePrefrences.equals("")){

            try {
                Map<String,  InformRegister> mapAllReportsRegister = SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

                InformRegister objec= mapAllReportsRegister.get(currentKeySharePrefrences);



                Log.i("dineroa","el currentKeySharePrefrences es : "+currentKeySharePrefrences);

                Log.i("dineroa","el obec vaue is  es : "+objec.isSeSubioFormAlinea());

                objec.setSeSubioFormAlinea(true);
                mapAllReportsRegister.put(currentKeySharePrefrences,objec);

                SharePref.saveHashMapOfHashmapInformRegister(mapAllReportsRegister,SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

            }

            catch (Exception e) {
                e.printStackTrace();

                Log.i("dineroa","hello haaxxx");

            }



        }



    }








    EditText [] generateArrayOfEditTextLibriado(){

        EditText        ediMarcaCol1 = findViewById(R.id.ediMarcaCol1);
        EditText        ediMarcaCol2 = findViewById(R.id.ediMarcaCol2);
        EditText        ediMarcaCol3 = findViewById(R.id.ediMarcaCol3);
        EditText        ediMarcaCol4 = findViewById(R.id.ediMarcaCol4);
        EditText        ediMarcaCol5 = findViewById(R.id.ediMarcaCol5);



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
                ediMarcaCol1,ediMarcaCol2,ediMarcaCol3,ediMarcaCol4,ediMarcaCol5,

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









        return  miArray;

    }



    private void hideViewsIfUserISCampo(){
        TextInputEditText  ediNombreRevisa =findViewById(R.id.ediNombreRevisa);
        TextInputEditText  ediCodigoRevisa =findViewById(R.id.ediCodigoRevisa);

        if(SharePref.getQserconTipoUser()==Utils.INSPECTOR_CAMPO || SharePref.getQserconTipoUser()==Utils.NO_DEFINIDO ){


            ediNombreRevisa.setEnabled(false);
            ediCodigoRevisa.setEnabled(false);
        }


        if(Variables.usuarioQserconGlobal!=null){

            if(Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_CAMPO || Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.NO_DEFINIDO){
                ediNombreRevisa.setEnabled(false);
                ediCodigoRevisa.setEnabled(false);
            }

        }


    }


    //obteniendo nombres de exrpotadoras....

    private void getExportadorasAndSetSpinner(){
         //tenemos exportadoras de prefrencias//



        if(Utils.hasmpaExportadoras.size()==0){
            Utils.hasmpaExportadoras = SharePref.getMapExpotadoras(SharePref.KEY_EXPORTADORAS);

        }


         ArrayList<String>nombresExportadoras= new ArrayList<>();

            for(Exportadora exportadora: Utils.hasmpaExportadoras.values()){
                nombresExportadoras.add(exportadora.getNameExportadora());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresExportadoras);
            spinnerExportadora.setAdapter(arrayAdapter);


    }





}
