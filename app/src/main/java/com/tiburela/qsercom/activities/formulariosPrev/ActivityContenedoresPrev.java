package com.tiburela.qsercom.activities.formulariosPrev;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.icu.text.DecimalFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.PdfMaker.PdfMaker2_0;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.adapters.RecyclerViewAdapLinkage;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.adapters.SimpleItemTouchHelperCallback;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.BottonSheetDfragmentVclds;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmNoAtach;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.PromedioLibriado;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageDataAndRdB;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Permisionx;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class ActivityContenedoresPrev extends AppCompatActivity implements View.OnClickListener {

   /**COPIAS DE FORMS*/

   ScrollView scrollView2;

    Button btnCheck;





    HashMap<String, Exportadora> hasmpaExportadoras;

    Spinner spinnerExportadora;

     TextInputEditText ediNombreRevisa;
    TextInputEditText ediCodigoRevisa;
    HashMap<String, Float> miMapLbriado= new HashMap<>();

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






    String currentIDcUDORmUESTREO;
    final int PICK_IMG = 590;
    LinearLayout layoutPesobrutoPorClusterSolo;
    LinearLayout layPesoBruto2;
    LinearLayout layPesoBruto1;
    ProgressDialog progress;

     TextInputEditText ediClienteNombreReporte;


    private ImageView imgUpdatecAlfrutaEnfunde;




    Button btnGuardarCambiosmARKrREVISADO;

    TextInputEditText ediExportadoraProcesada;
    TextInputEditText ediExportadoraSolicitante;
    TextInputEditText ediMarca;

    private static final int PERMISSION_REQUEST_CODE = 100;
    ProductPostCosecha products;
    private String UNIQUE_ID_iNFORME;
    ProductPostCosecha productxGlobal = null;
    ProgressDialog pdialogff;
    public static Context context;
    private int contadorIterador;
    private boolean isModEdicionFields = false;
    private boolean esFirstCharge = true;
    private Switch swAguaCorrida, switchLavdoRacimos;
    private Spinner spFuenteAgua;
    private Spinner spFumigaCorL1;
    ImageView imgAtachVinculacion;

    TextView txtNumReportsVinclds;

    private long millisDateSelect = 0;

    private Spinner spTipoBoquilla;
    private static int currentTypeImage = 0;
    ProgressBar progressBarFormulario;
    private Context mContext;


    Button btnGENERARpdf;
    FloatingActionButton fab;

    TextInputEditText ediCjasProcesDespacha;
    TextInputEditText ediInspectorAcopio;
    TextInputEditText ediExtCalid;
    TextInputEditText ediExtRodillo;
    TextInputEditText ediExtGancho;
    TextInputEditText ediExtCalidCi;
    TextInputEditText ediExtRodilloCi;
    TextInputEditText ediExtGanchoCi;


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
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;
    TextInputEditText ediNumContenedor;

    TextInputEditText ediFuenteAgua;
    TextInputEditText ediAguaCorrida;
    TextInputEditText ediLavadoRacimos;
    TextInputEditText ediFumigacionClin1;
    TextInputEditText ediTipoBoquilla;
    TextInputEditText ediCajasProcDesp;
    TextInputEditText ediRacimosCosech;
    TextInputEditText ediRacimosRecha;
    TextInputEditText ediRacimProces;


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
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza;
    Spinner spinnertipodeBlanzaRepeso;
    Spinner spinnerubicacionBalanza;


    Switch switchContenedor;
    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;

    ArrayList<View> listViewsClickedUser;

    Uri cam_uri;


    @Override
    protected void onStart() {
        super.onStart();

        Log.i("usrdecideatach", "se llamo metoo onstart ");



        Variables.VienedePreview = true;

             if(progress!=null ){
                 if(progress.isShowing()){
                     progress.dismiss();
                 }
             }

             if (esFirstCharge)
             {
            findViewsIds();

            hideViewsIfUserISCampo();

            context = getApplicationContext();


            UNIQUE_ID_iNFORME = Variables.CurrenReportPart1.getUniqueIDinforme();

            StorageDataAndRdB.uniqueIDImagesSetAndUInforme = Variables.CurrenReportPart1.getUniqueIDinforme();

            esFirstCharge = false;


            // FirebaseApp.initializeApp(this);
            //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

            Auth.initAuth(this);


            RealtimeDB.initDatabasesRootOnly();
            StorageDataAndRdB.initStorageReference();


            listViewsClickedUser = new ArrayList<>();


            addClickListeners();
            // resultatachImages();

            // EstateFieldView.adddataListsStateFields();
            eventCheckdata();
            //creaFotos();
            listennersSpinners();
            checkModeVisualitY();

            configCertainSomeViewsAliniciar();


        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // progressDialog=progressDialog
        setContentView(R.layout.activity_preview);

        Utils.esNuevoReport=false;
       TextView txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Log.i("copiamos","hemos copiado");

                copiamosHere();

                return false;

            }
        });



        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString = "";//reseteamos
        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado = "";


        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString ="";//reseteamos
        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado ="";


        ImagenReport.hashMapImagesData = new HashMap<>();


        Log.i("imagebrr", "probandodebug");



        Utils.userDecidioNoVincularAhora = false;

        Variables.copiamosData = false;

        mContext = this;

        Variables.activityCurrent = Variables.FormPreviewContenedores;

        spinnerExportadora=findViewById(R.id.spinnerExportadora);

        getExportadorasAndSetSpinner();


    }


    void showingTimePicker(View vista) {


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);



        // time picker dialog
        TimePickerDialog picker = new TimePickerDialog(ActivityContenedoresPrev.this,
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




                        if (vista.getId() == R.id.ediHoraInicio) {
                            ediHoraInicio.setText(sHour + ":" + minutes+" "+ AM_PM);


                        } else if (vista.getId() == R.id.ediHoraTermino) {
                            ediHoraTermino.setText(sHour + ":" + minutes+" "+ AM_PM);


                        } else if (vista.getId() == R.id.ediHoraLLegadaContenedor) {

                            ediHoraLLegadaContenedor.setText(sHour + ":" + minutes+" "+ AM_PM);

                        } else if (vista.getId() == R.id.ediHoraSalidaContenedor) {
                            ediHoraSalidaContenedor.setText(sHour + ":" + minutes+" "+ AM_PM);


                        } else if (vista.getId() == R.id.ediHoraEncendido1) {
                            ediHoraEncendido1.setText(sHour + ":" + minutes+" "+ AM_PM);


                        } else if (vista.getId() == R.id.ediHoraEncendido2) {

                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+ AM_PM);

                        }


                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    void selecionaFecha() {

        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog picker = new DatePickerDialog(ActivityContenedoresPrev.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        String dateSelec = i2 + "/" + (i1+1) + "/" + i;

                        ediFecha.setText(dateSelec);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


                        Date date = null;
                        try {
                            date = sdf.parse(dateSelec);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        millisDateSelect = date.getTime();


                    }
                }, year, mes, daySemana);

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


    private void configCertainSomeViewsAliniciar() { //configuraremos algos views al iniciar
        btnGENERARpdf.setEnabled(false);
        btnGENERARpdf.setVisibility(View.GONE);

        if(Variables.usuarioQserconGlobal!=null && Variables.usuarioQserconGlobal.isUserISaprobadp() && Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_OFICINA){

            btnGENERARpdf.setEnabled(true);
            btnGENERARpdf.setVisibility(View.VISIBLE);

        }


        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

        disableEditText(ediHoraLLegadaContenedor);//here
        disableEditText(ediHoraSalidaContenedor);

        disableEditText(ediContenedor);
        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);

        disableEditText(ediHoraEncendido1);
        disableEditText(ediHoraEncendido2);

        //descativamos este boton
        btnGENERARpdf.setEnabled(false);

    }

    private void findViewsIds() { //configuraremos algos views al iniciar
        btnCheck = findViewById(R.id.btnCheck);
        scrollView2=findViewById(R.id.scrollView2);
         ediNombreRevisa=findViewById(R.id.ediNombreRevisa);
         ediCodigoRevisa=findViewById(R.id.ediCodigoRevisa);


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


        ediClienteNombreReporte=findViewById(R.id.ediClienteNombreReporte);
        imgUpdatecAlfrutaEnfunde=findViewById(R.id.imgUpdatecAlfrutaEnfunde);
        layPesoBruto2 = findViewById(R.id.layPesoBruto2);
        layPesoBruto1 = findViewById(R.id.layPesoBruto1);

        layoutPesobrutoPorClusterSolo = findViewById(R.id.layoutPesobrutoPorClusterSolo);
        ediExportadoraProcesada = findViewById(R.id.ediExportadoraProcesada);
        ediExportadoraSolicitante = findViewById(R.id.ediExportadoraSolicitante);
        ediMarca = findViewById(R.id.ediMarca);

        btnGuardarCambiosmARKrREVISADO = findViewById(R.id.btnGuardarCambiosmARKrREVISADO);


        ediExtCalid = findViewById(R.id.ediExtCalid);
        ediExtRodillo = findViewById(R.id.ediExtRodillo);
        ediExtGancho = findViewById(R.id.ediExtGancho);

        ediExtCalidCi = findViewById(R.id.ediExtCalidCi);
        ediExtRodilloCi = findViewById(R.id.ediExtRodilloCi);
        ediExtGanchoCi = findViewById(R.id.ediExtGanchoCi);


        txtNumReportsVinclds = findViewById(R.id.txtNumReportsVinclds);

        imgAtachVinculacion = findViewById(R.id.imgAtachVinculacion);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        ediEmpacadora = findViewById(R.id.ediEmpacadora);
        btnGENERARpdf = findViewById(R.id.btnGENERARpdf);

        spFumigaCorL1 = findViewById(R.id.spFumigaCorL1);

        ediCjasProcesDespacha = findViewById(R.id.ediCjasProcesDespacha);
        ediInspectorAcopio = findViewById(R.id.ediInspectorAcopio);
        spTipoBoquilla = findViewById(R.id.spTipoBoquilla);

        spFuenteAgua = findViewById(R.id.spFuenteAgua);
        ediSemana = findViewById(R.id.ediSemana);
        ediFecha = findViewById(R.id.ediFecha);
        ediProductor = findViewById(R.id.ediProductor);
        ediHacienda = findViewById(R.id.ediHacienda);
        ediCodigo = findViewById(R.id.ediCodigo);
        ediInscirpMagap = findViewById(R.id.ediInscirpMagap);
        ediPemarque = findViewById(R.id.ediPemarque);
        ediNtargetaEmbarque = findViewById(R.id.ediNtargetaEmbarque);
        ediZona = findViewById(R.id.ediZona);
        ediHoraInicio = findViewById(R.id.ediHoraInicio);
        ediHoraTermino = findViewById(R.id.ediHoraTermino);
        ediNguiaRemision = findViewById(R.id.ediNguiaRemision);
        edi_nguia_transporte = findViewById(R.id.edi_nguia_transporte);
        edi_nguia_transporte = findViewById(R.id.edi_nguia_transporte);
        ediNhojaEvaluacion = findViewById(R.id.ediNhojaEvaluacion);
        spinnerSelectZona = findViewById(R.id.spinnerZona);
        ediObservacion = findViewById(R.id.ediObservacion);

        ediNumContenedor = findViewById(R.id.ediNumContenedor);
        swAguaCorrida = findViewById(R.id.swAguaCorrida);
        switchLavdoRacimos = findViewById(R.id.switchLavdoRacimos);

        ediTare = findViewById(R.id.ediTare);
        ediBooking = findViewById(R.id.ediBooking);
        ediMaxGross = findViewById(R.id.ediMaxGross);
        ediNumSerieFunda = findViewById(R.id.ediNumSerieFunda);
        stikVentolerExterna = findViewById(R.id.stikVentolerExterna);
        ediCableRastreoLlegada = findViewById(R.id.ediCableRastreoLlegada);
        ediSelloPlasticoNaviera = findViewById(R.id.ediSelloPlasticoNaviera);
        ediOtroSellosLlegada = findViewById(R.id.ediOtroSellosLlegada);

        ediEnsunchado = findViewById(R.id.ediEnsunchado);
        ediBalanzaRepeso = findViewById(R.id.ediBalanzaRepeso);

        ediFuenteAgua = findViewById(R.id.ediFuenteAgua);
        ediAguaCorrida = findViewById(R.id.ediAguaCorrida);
        ediLavadoRacimos = findViewById(R.id.ediLavadoRacimos);
        ediFumigacionClin1 = findViewById(R.id.ediFumigacionClin1);
        ediTipoBoquilla = findViewById(R.id.ediTipoBoquilla);
        ediCajasProcDesp = findViewById(R.id.ediCajasProcDesp);
        ediRacimosCosech = findViewById(R.id.ediRacimosCosech);

        ediRacimosRecha = findViewById(R.id.ediRacimosRecha);
        ediRacimProces = findViewById(R.id.ediRacimProces);


        linLayoutHeader1 = findViewById(R.id.linLayoutHeader1);
        linLayoutHeader2 = findViewById(R.id.linLayoutHeader2);
        linLayoutHeader3 = findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 = findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 = findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 = findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 = findViewById(R.id.linLayoutHeader7);
        linLayoutHeader8 = findViewById(R.id.linLayoutHeader8);


        switchContenedor = findViewById(R.id.switchContenedor);
        ediContenedor = findViewById(R.id.ediContenedor);


        ediPPC01 = findViewById(R.id.ediPPC01);
        ediPPC02 = findViewById(R.id.ediPPC02);
        ediPPC03 = findViewById(R.id.ediPPC03);
        ediPPC04 = findViewById(R.id.ediPPC04);
        ediPPC05 = findViewById(R.id.ediPPC05);
        ediPPC06 = findViewById(R.id.ediPPC06);
        ediPPC07 = findViewById(R.id.ediPPC07);
        ediPPC08 = findViewById(R.id.ediPPC08);
        ediPPC09 = findViewById(R.id.ediPPC09);
        ediPPC010 = findViewById(R.id.ediPPC010);
        ediPPC011 = findViewById(R.id.ediPPC011);
        ediPPC012 = findViewById(R.id.ediPPC012);
        ediPPC013 = findViewById(R.id.ediPPC013);
        ediPPC014 = findViewById(R.id.ediPPC014);
        ediPPC015 = findViewById(R.id.ediPPC015);
        ediPPC016 = findViewById(R.id.ediPPC016);


        ediDestino = findViewById(R.id.ediDestino);
        ediNViaje = findViewById(R.id.ediNViaje);
        ediVapor = findViewById(R.id.ediVapor);

        // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
        //ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediHoraLLegadaContenedor = findViewById(R.id.ediHoraLLegadaContenedor);
        ediHoraSalidaContenedor = findViewById(R.id.ediHoraSalidaContenedor);


        ediTipoContenedor = findViewById(R.id.ediTipoContenedor);

        ediCondicionBalanza = findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja = findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico = findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza = findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso = findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza = findViewById(R.id.ediUbicacionBalanza);


        ediTermofrafo1 = findViewById(R.id.ediTermofrafo1);
        ediHoraEncendido1 = findViewById(R.id.ediHoraEncendido1);
        ediUbicacion1 = findViewById(R.id.ediUbicacion1);
        ediRuma1 = findViewById(R.id.ediRuma1);
        ediTermofrafo2 = findViewById(R.id.ediTermofrafo2);
        ediHoraEncendido2 = findViewById(R.id.ediHoraEncendido2);
        ediUbicacion2 = findViewById(R.id.ediUbicacion2);
        ediRuma2 = findViewById(R.id.ediRuma2);
        ediCandadoqsercon = findViewById(R.id.ediCandadoqsercon);

        ediSelloNaviera = findViewById(R.id.ediSelloNaviera);
        ediCableNaviera = findViewById(R.id.ediCableNaviera);
        ediSelloPlastico = findViewById(R.id.ediSelloPlastico);
        ediCandadoBotella = findViewById(R.id.ediCandadoBotella);
        ediCableExportadora = findViewById(R.id.ediCableExportadora);
        ediSelloAdesivoexpor = findViewById(R.id.ediSelloAdesivoexpor);
        esiSelloAdhNaviera = findViewById(R.id.esiSelloAdhNaviera);
        ediOtherSellos = findViewById(R.id.ediOtherSellos);


        ediCompaniaTransporte = findViewById(R.id.ediCompaniaTransporte);
        ediNombreChofer = findViewById(R.id.ediNombreChofer);
        ediCedula = findViewById(R.id.ediCedula);
        ediCelular = findViewById(R.id.ediCelular);
        ediPLaca = findViewById(R.id.ediPLaca);
        ediMarcaCabezal = findViewById(R.id.ediMarcaCabezal);
        ediColorCabezal = findViewById(R.id.ediColorCabezal);

        spinnerCondicionBalanza = findViewById(R.id.spinnerCondicionBalanza);
        spinnertipodePlastico = findViewById(R.id.spinnertipodePlastico);
        spinnertipodeBlanza = findViewById(R.id.spinnertipodeBlanza);
        spinnertipodeBlanzaRepeso = findViewById(R.id.spinnertipodeBlanzaRepeso);
        spinnerubicacionBalanza = findViewById(R.id.spinnerubicacionBalanza);

        switchHaybalanza = findViewById(R.id.switchHaybalanza);
        switchHayEnsunchado = findViewById(R.id.switchHayEnsunchado);
        switchBalanzaRep = findViewById(R.id.switchBalanzaRep);


    }


    private void addClickListeners() {


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





        /**todos add a todos clicklistener de la implemntacion*/
        imgAtachVinculacion.setOnClickListener(this);
        layoutPesobrutoPorClusterSolo.setOnClickListener(this);
        imgUpdatecAlfrutaEnfunde.setOnClickListener(this);

        btnGENERARpdf.setOnClickListener(this);

        btnGuardarCambiosmARKrREVISADO.setOnClickListener(this);


        fab.setOnClickListener(this);


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


    private void muestraLinearLayout(LinearLayout linearLayout) { //configuraremos algos views al iniciar

        linearLayout.setVisibility(LinearLayout.VISIBLE);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {

        Log.i("darterlo", "is selñecieo,ages");

        String data[]={"image/*"};
        Log.i("miclickimg","hemos hecho click");

        int idCurrent= view.getId();

        if(idCurrent==R.id.imgVAtachProcesoFrutaFinca || idCurrent==R.id.imgVAtachLlegadaContenedor || idCurrent==R.id.imgVAtachSellosLlegada ||
                idCurrent==R.id.imgVAtachPuertaAbiertaContenedor
                || idCurrent==R.id.imgVAtachFotosPallet || idCurrent==R.id.imgVAtachCierreContenedor ||
                idCurrent == R.id.imgVAtachDocumentacionss){ //si es atach


            currentTypeImage=Integer.parseInt(view.getTag().toString());

            activityResultLauncher.launch("image/*");


            // activityResultLauncher.launch("image/*");



            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);

        }

        else if(idCurrent==R.id.imbTakePicProcesoFrutaFinca || idCurrent==R.id.imbTakePicLllegadaContenedor
                || idCurrent==R.id.imbTakePicSellosLlegada ||
                idCurrent==R.id.imbTakePicPuertaAbiertaContenedor || idCurrent==R.id.imbTakePicPallet
                || idCurrent==R.id.imbTakePicCierreContenedor || idCurrent==R.id.imbTakePicDocuementacionxx){ //si es tajke pic con camara

            currentTypeImage=Integer.parseInt(view.getTag().toString());


            takepickNow();

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);


        }




else{
            switch (view.getId()) {



                case R.id.layoutPesobrutoPorClusterSolo:

                    if (layPesoBruto1.getVisibility() == View.VISIBLE) {
                        layPesoBruto1.setVisibility(view.GONE);
                        layPesoBruto2.setVisibility(view.GONE);

                    } else {

                        layPesoBruto2.setVisibility(view.VISIBLE);
                        layPesoBruto1.setVisibility(view.VISIBLE);


                    }

                    break;


                case R.id.btnGuardarCambiosmARKrREVISADO:

                    ///cuandole da en genear obtenmos nuevamente la data


                    RealtimeDB.marckComoRevisadoInformRegister(ActivityContenedoresPrev.this, Variables.currentInformRegisterSelected.getKeyLoactionThisForm(),Variables.userGoogle.getDisplayName());

                    finish();

                    //creamosel pdf con la data actual... excepto las imagenes...

                    break;


                case R.id.btnGENERARpdf:

                    ///cuandole da en genear obtenmos nuevamente la data




                    checkDataToCreatePdf();

                    //creamosel pdf con la data actual... excepto las imagenes...

                    break;


                case R.id.fab: //si pulas en btn chekear en que modo esta ...si el modo cambia...
                    TextView txtModeAdviser = findViewById(R.id.txtModeAdviser);

                    if (isModEdicionFields) { //si es modo edicion..
                        fab.setImageResource(R.drawable.ic_baseline_edit_24aa);

                        txtModeAdviser.setText("Modo Visualizacion ");


                        //cambiamos al modo visualizacion
                        isModEdicionFields = false;
                        activateModePreview();


                    } else { //SI NO ES MODO VISUZALIZACION
                        fab.setImageResource(R.drawable.ic_baseline_preview_24jhj);
                        txtModeAdviser.setText("Modo Edicion ");

                        isModEdicionFields = true;
                        activateModeEdit();


                        //CAMABIAMOS EL MODO

                    }


                    break; //


                case R.id.linLayoutHeader1:
                    LinearLayout layoutContainerSeccion1 = findViewById(R.id.layoutContainerSeccion1);

                    if (layoutContainerSeccion1.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion1);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion1);
                    }
                    break; //


                case R.id.linLayoutHeader2:
                    LinearLayout layoutContainerSeccion2 = findViewById(R.id.layoutContainerSeccion2);

                    if (layoutContainerSeccion2.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion2);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion2);
                    }
                    break; //

                case R.id.linLayoutHeader3:
                    LinearLayout layoutContainerSeccion3 = findViewById(R.id.layoutContainerSeccion3);

                    if (layoutContainerSeccion3.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion3);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion3);
                    }
                    break; //


                case R.id.linLayoutHeader4:
                    LinearLayout layoutContainerSeccion4 = findViewById(R.id.layoutContainerSeccion4);

                    if (layoutContainerSeccion4.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion4);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion4);
                    }
                    break; //


                case R.id.linLayoutHeader5:
                    LinearLayout layoutContainerSeccion5 = findViewById(R.id.layoutContainerSeccion5);

                    if (layoutContainerSeccion5.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion5);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion5);
                    }
                    break; //

                case R.id.linLayoutHeader6:
                    LinearLayout layoutContainerSeccion6 = findViewById(R.id.layoutContainerSeccion6);

                    if (layoutContainerSeccion6.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion6);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion6);
                    }
                    break; //


                case R.id.linLayoutHeader7:


                    ///AQUI VA,,,,

                    LinearLayout layoutContainerSeccion7 = findViewById(R.id.layoutContainerDatsProceso);


                    if (layoutContainerSeccion7.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion7);
                    } else {
                        oucultaLinearLayout(layoutContainerSeccion7);
                    }



                    break; //

                case R.id.linLayoutHeader8:
                    LinearLayout layoutContainerSeccion8 = findViewById(R.id.layoutContainerSeccion8);

                    if (layoutContainerSeccion8.getVisibility() == View.GONE) {
                        muestraLinearLayout(layoutContainerSeccion8);
                    } else {

                        oucultaLinearLayout(layoutContainerSeccion8);
                    }
                    break; //


                case R.id.ediFecha:
                    // Utils.closeKeyboard(FormularioActivity.this);

                    selecionaFecha();

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

                case R.id.ediHoraEncendido1:
                    // Utils.closeKeyboard(FormularioActivity.this);
                    showingTimePicker(view);

                    break; //

                case R.id.ediHoraEncendido2:
                    // Utils.closeKeyboard(FormularioActivity.this);
                    showingTimePicker(view);

                    break;

                ///////////////


                case R.id.imgUpdatecAlfrutaEnfunde:
                    Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");
                    getResultDatCalibCalEnfundes();
                    break;


                case R.id.imgAtachVinculacion:

                    showEditDialogAndSendData();


                    break;


            }

        }


        //aqui o


    }

    private void showEditDialogAndSendData() {


        Log.i("cinuoados","el id vinuclados es "+RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);

        Bundle bundle = new Bundle();
        bundle.putString(Variables.KEY_CONTROL_CALIDAD_ATACHEDS, RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        bundle.putString(Variables.KEY_CUADRO_MUETREO_ATACHED, RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);


        FragmentManager fm = getSupportFragmentManager();
        BottonSheetDfragmentVclds alertDialog = BottonSheetDfragmentVclds.newInstance(Constants.PREV_CONTENEDORES);
        // alertDialog.setCancelable(false);

        alertDialog.setArguments(bundle);
        alertDialog.show(fm, "duialoffragment_alert");


    }


    private void takepickNow() {

        Permisionx.checkPermission(Manifest.permission.CAMERA, 1, this, ActivityContenedoresPrev.this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");


            cam_uri = ActivityContenedoresPrev.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
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


                        try {

                            Bitmap bitmap=   HelperImage.handleSamplingAndRotationBitmap(ActivityContenedoresPrev.this,cam_uri);
                            String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                            ImagenReport obcjImagenReport = new ImagenReport("", cam_uri.toString(), currentTypeImage, Utils.getFileNameByUri(ActivityContenedoresPrev.this, cam_uri), horientacionImg);
                            obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                            showImagesPicShotOrSelectUpdateView(false,Variables.NINGUNO);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                }
            });


    ActivityResultLauncher activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                @Override
                public void onActivityResult(List<Uri> result) {
                    if (result != null) {

                       MiTarea tare= new MiTarea();
                        tare.execute(result);


                    }
                }
            });



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



        spinnerSelectZona.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida = spinnerSelectZona.getSelectedItem().toString();
                ediZona.setText(zonaEelejida);


                Log.i("mizona", "la zona aen listenner spinner es" + zonaEelejida);

                if (zonaEelejida.equalsIgnoreCase("Ninguna")) {
                    //actualizamos
                    Log.i("maswiso", "eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerCondicionBalanza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String condicion = spinnerCondicionBalanza.getSelectedItem().toString();
                ediCondicionBalanza.setText(condicion);
                if (condicion.equalsIgnoreCase("Ninguna")) {
                    //actualizamos
                    Log.i("maswiso", "eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnertipodePlastico.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida = spinnertipodePlastico.getSelectedItem().toString();
                ediTipoPlastico.setText(zonaEelejida);
                if (zonaEelejida.equalsIgnoreCase("Ninguna")) {
                    //actualizamos
                    Log.i("maswiso", "eSPINNER ZONA SELECIONO NINGUNO ");
                    ediTipoPlastico.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnertipodeBlanza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida = spinnertipodeBlanza.getSelectedItem().toString();
                ediTipoBalanza.setText(zonaEelejida);

                if (zonaEelejida.equalsIgnoreCase("Ninguna")) {
                    //actualizamos
                    Log.i("maswiso", "eSPINNER ZONA SELECIONO NINGUNO ");
                    ediTipoBalanza.setText("");
                }




            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnertipodeBlanzaRepeso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida = spinnertipodeBlanzaRepeso.getSelectedItem().toString();
                editipbalanzaRepeso.setText(zonaEelejida);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerubicacionBalanza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String zonaEelejida = spinnerubicacionBalanza.getSelectedItem().toString();

                ediUbicacionBalanza.setText(zonaEelejida);

                if (zonaEelejida.equalsIgnoreCase("Ninguna")) {
                    //actualizamos
                    Log.i("maswiso", "eSPINNER ZONA SELECIONO NINGUNO ");
                    ediUbicacionBalanza.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        switchContenedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (switchContenedor.isChecked()) {

                    ediContenedor.setText(" SI ");

                } else {
                    ediContenedor.setText(" NO ");

                }
            }
        });



        swAguaCorrida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (swAguaCorrida.isChecked()) {

                    swAguaCorrida.setText(" SI ");

                } else {
                    swAguaCorrida.setText(" NO ");

                }
            }
        });


        switchLavdoRacimos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (switchLavdoRacimos.isChecked()) {

                    switchLavdoRacimos.setText(" SI ");

                } else {
                    switchLavdoRacimos.setText(" NO ");

                }
            }
        });



        switchContenedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (switchContenedor.isChecked()) {

                    ediContenedor.setText(" SI ");

                } else {
                    ediContenedor.setText(" NO ");

                }
            }
        });



    }


    private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg,int posicionionBorrar) {

        //si es eliminar comprobar aqui
        if (isDeleteImg) {

            Log.i("mispiggi", "si es ly_defparte3.xml imgVERGA Y EL SIZE ES  " + ImagenReport.hashMapImagesData.size());

            currentTypeImage = Variables.typeoFdeleteImgORgire;
        }



        ArrayList<ImagenReport> filterListImagesData = new ArrayList<>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW
        RecyclerView recyclerView =null;
        Log.i("mispiggi", "el size de la  lists  hashMapImagesData HERE  es cc  es " + ImagenReport.hashMapImagesData.size());
      //  RecyclerViewAdapter adapter;
        RecyclerViewAdapter aadpaterRecuperadoOFrView=null; //aqui almacenaremo
        //GridLayoutManager layoutManager=new GridLayoutManager(this,2);

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

                for(ImagenReport imagenObjec: ImagenReport.hashMapImagesData.values()){
                    if(imagenObjec.getTipoImagenCategory()==currentTypeImage){
                        filterListImagesData.add(imagenObjec);
                        Log.i("mispiggi", "el size de filterListImagesData es " + filterListImagesData.size());
                    }
                }


                aadpaterRecuperadoOFrView.addItems(filterListImagesData); //le agremos los items
                aadpaterRecuperadoOFrView.notifyDataSetChanged(); //notificamos  no se si hace falta porque la clase del objeto ya lo tiene...

                // aadpater.notifyItemRangeInserted(0,filterListImagesData.size());
                // aadpater. notifyItemRangeChanged(position, listImagenData.size());

                Log.i("adpatertt","adpasternotiff");

            }
            else{

                aadpaterRecuperadoOFrView. listImagenData.remove(posicionionBorrar);
                aadpaterRecuperadoOFrView.notifyItemRemoved(posicionionBorrar);
                aadpaterRecuperadoOFrView.notifyItemRangeChanged(posicionionBorrar, aadpaterRecuperadoOFrView.listImagenData.size());
                // holder.itemView.setVisibility(View.GONE);

            }


            Log.i("adpatertt","es difrentede nulo");

        }



    }


    private void showImagesPicShotOrSelectUpdateViewGirar( int posicionionGirar) {

            currentTypeImage = Variables.typeoFdeleteImgORgire;

        ArrayList<ImagenReport> filterListImagesData; //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW
        RecyclerView recyclerView ;
        RecyclerViewAdapter aadpaterRecuperadoOFrView=null; //aqui almacenaremo

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


            //obtenemos la lista de estae adpatdor
            filterListImagesData=aadpaterRecuperadoOFrView.listImagenData;

            ImagenReport imagenReportupdate=Utils.updateImagenGiro(filterListImagesData.get(posicionionGirar));

            //le pasaos la imagen actualizada....
            filterListImagesData.set(posicionionGirar, imagenReportupdate);
            aadpaterRecuperadoOFrView.notifyItemChanged(posicionionGirar);



        }



    }


    private void eventCheckdata() {// verificamos que halla llenado toda la info necesaria..


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                checkDataFields();


            }
        });


    }

    void checkDataFields() {


        if(! cehckFaltanImagenes()){
            Log.i("test001","no esta lleno  cehckFaltanImagenes");
            //btnCheck.setEnabled(true);

            return;
        }



        if (!checkDatosGeneralesIsLleno()) {

            Log.i("test001", "no esta lleno  checkDatosGeneralesIsLleno");
            return;
        }


        else {

            Log.i("test001", "si esta lleno checkDatosGeneralesIsLleno ");

        }


        if (!checkcantidadPostcosechaIsLleno()) {


            Log.i("test001", "no esta lleno  checkcantidadPostcosechaIsLleno");
            return;
        } else {
            Log.i("test001", "si esta lleno checkcantidadPostcosechaIsLleno ");

        }


        if (!checkDatosContenedorIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosContenedorIsLleno");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDatosContenedorIsLleno");
        }


        if (!checkDataSellosLlegadaIsLleno()) {
            Log.i("test001", "no esta lleno  checkDataSellosLlegadaIsLleno");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDataSellosLlegadaIsLleno");


        }


        if (!checkSellosInstaladosIsLleno()) {
            Log.i("test001", "no esta lleno  checkSellosInstaladosIsLleno");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkSellosInstaladosIsLleno");


        }


        if (!checkDatosTransportistaIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosTransportistaIsLleno");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDatosTransportistaIsLleno");


        }


        if (!checkDatosProcesoIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosProcesoIsLleno");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDatosProcesoIsLleno");


        }

        if (!checkDatosHaciendaIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosHaciendaIsLleno");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDatosHaciendaIsLleno");


        }


        if (!checkQueexistminim()) {
            Log.i("test001", "no esta lleno  checkDataCalibFrutaCalEnfn");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDataCalibFrutaCalEnfn");


        }
        if (!getResultDatCalibCalEnfundes()) {
            Log.i("test001", "no esta lleno  getResultDatCalibCalEnfundes");

            return;
        } else {

            Log.i("test001", "si  esta lleno  getResultDatCalibCalEnfundes");


        }

        if (!Utils.checkifAtach()) {
            Log.i("test001", "no esta lleno  checkifAtach");
            FragmentManager fm = getSupportFragmentManager();
            DialogConfirmNoAtach alertDialog = DialogConfirmNoAtach.newInstance(Constants.PREV_CONTENEDORES);
            // alertDialog.setCancelable(false);
            alertDialog.show(fm, "duialoffragment_alertZ");
            return;
        }


        openBottomSheet();

    }


    private boolean getResultDatCalibCalEnfundes() {


        if (ediRacimosCosech.getText().toString().trim().isEmpty()) {
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


        EditText ediPorc14 = findViewById(R.id.ediPorc14);
        EditText ediPorc13 = findViewById(R.id.ediPorc13);
        EditText ediPorc12 = findViewById(R.id.ediPorc12);
        EditText ediPorc11 = findViewById(R.id.ediPorc11);
        EditText ediPorc10 = findViewById(R.id.ediPorc10);
        EditText ediPorc9 = findViewById(R.id.ediPorc9);


        int numRacimosCosechados = Integer.parseInt(ediRacimosCosech.getText().toString());
        double resultpercente;

        DecimalFormat formato = new DecimalFormat("#.##");

        int numeroRacimosContador = 0;

        //numero de raCimos
        EditText[] miArrayNUmrACIMOS = {ediNumRcim14, ediNumRcim13, ediNumRcim12, ediNumRcim11, ediNumRcim10, ediNumRac9};

        EditText[] miArraypORCENTAHJES = {ediPorc14, ediPorc13, ediPorc12, ediPorc11, ediPorc10, ediPorc9};

        for (int i = 0; i < miArrayNUmrACIMOS.length; i++) {

            if (!miArrayNUmrACIMOS[i].getText().toString().trim().isEmpty()) {        ///tiene que ser mayor a cero
                if (Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString()) > 0) {  //operamoss

                    resultpercente = (Double.parseDouble(miArrayNUmrACIMOS[i].getText().toString()) / numRacimosCosechados) * 100;

                    Log.i("habimosssd","el result percent es "+resultpercente);

                    String promDecim = formato.format(resultpercente);
                    String [] array=promDecim.split("\\."); //tendremos un valor asi 58.50 normal

                    if(promDecim.contains(".")) {

                        if (array[1].length() == 1) { //tiene solo un valor.
                            promDecim = promDecim + "0";
                        }

                    }
                    miArraypORCENTAHJES[i].setText(promDecim);
                    //sumaoslos racimos totale
                    numeroRacimosContador = numeroRacimosContador + Integer.parseInt(miArrayNUmrACIMOS[i].getText().toString());


                }

            }


        }


        //calculo aqwui

        if (numeroRacimosContador != numRacimosCosechados) {

            Snackbar.make(ediRacimosCosech, "El numero de racimos no concuerda con el numero de racimos cosechados", Snackbar.LENGTH_LONG)
                    .show();

            Log.i("dataracimos", "no coincide");

            return false;


        } else {
            Log.i("dataracimos", "SI coincide");

            return true;


        }


    }


    void checkDataToCreatePdf() {

        //  checkDatosGeneralesIsLleno();


        if(ediNombreRevisa.getText().toString().equals("")){
            ediNombreRevisa.requestFocus();
            ediNombreRevisa.setError("Agrega un nombre del que revisó");
            return;
        }



        if(ediCodigoRevisa.getText().toString().equals("")){
            ediCodigoRevisa.requestFocus();
            ediCodigoRevisa.setError("Agrega un codigo del que revisó");
            return;
        }



        if(! cehckFaltanImagenes()){
            Log.i("test001","no esta lleno  cehckFaltanImagenes");
         //   btnCheck.setEnabled(true);

            return;
        }



        if (!checkDatosGeneralesIsLleno()) {

            Log.i("test001", "no esta lleno  checkDatosGeneralesIsLleno");
            return;
        }


        if (!checkcantidadPostcosechaIsLleno()) {
            Log.i("test001", "no esta lleno  checkcantidadPostcosechaIsLleno");
            return;
        }


        if (!checkDatosContenedorIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosContenedorIsLleno");
            return;
        }


        if (!checkDataSellosLlegadaIsLleno()) {
            Log.i("test001", "no esta lleno  checkDataSellosLlegadaIsLleno");
            return;
        }


        if (!checkSellosInstaladosIsLleno()) {
            Log.i("test001", "no esta lleno  checkSellosInstaladosIsLleno");
            return;
        }


        if (!checkDatosTransportistaIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosTransportistaIsLleno");
            return;
        }


        if (!checkDatosProcesoIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosProcesoIsLleno");
            return;
        }


        if (!checkDatosHaciendaIsLleno()) {
            Log.i("test001", "no esta lleno  checkDatosHaciendaIsLleno");
            return;
        }


        if (!checkQueexistminim()) {
            Log.i("test001", "no esta lleno  checkDataCalibFrutaCalEnfn");
            return;
        }


        if (!checkExisteMiumReportsVINCULADOS()) {
            Log.i("test001", "no esta lleno  cehckExisteMiumReportsVINCULADOSx");
            return;
        }


        Log.i("test001", "se eejcuto esto tambienx");



/*
        if (!Utils.checkifAtach()) {
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Vincula Un Reporte C.muestro Rechazados");

            Log.i("test001", "no esta lleno  checkifAtach");
            FragmentManager fm = getSupportFragmentManager();
            DialogConfirmNoAtach alertDialog = DialogConfirmNoAtach.newInstance(Constants.PREV_CONTENEDORES);
            // alertDialog.setCancelable(false);
            alertDialog.show(fm, "duialoffragment_alertZ");
            return;
        }

*/


//all reportsdfgdf
        // exieste al menos un reporte generado vinuculado///

        updatePostionImegesSort();

        generateMapLibriadoIfExistAndUpload(true);


        updateInformeWhitCurrentDataOfViews();

        updaTeProductsPostCosecha(); //actualizamos estetambien


        //

      /**anyes de llamar reseteamos y chekeamos si es mayor a */
        Utils.indiceControlCalidad=0;
        Variables.listIdSvINCULADOS = new ArrayList<>();
        Variables.listControlCalidadVinculads = new ArrayList<>();
        Variables.listIdSvINCULADOS = Utils.generateLISTofIdControlCALIDAD(Variables.CurrenReportPart1.getAtachControCalidadInfrms());

        Log.i("PORYECTOxxx", "bien el size de list vinculados id es  "+Variables.listIdSvINCULADOS.size());
        if (Variables.listIdSvINCULADOS.size() > 0) {  //si existen vinuclados DESCRAGAMOS los informes viculados usando los ids uniqe i
            RealtimeDB.initDatabasesRootOnly();
            dowloadReportsVinucLdsControlCalidad();


        } else {

            Toast.makeText(ActivityContenedoresPrev.this, "No Hay reportes vinculados ", Toast.LENGTH_SHORT).show();

        }
      //  DowloadControlcalidadVinculadosandDecideIRpdfMAKER(Variables.CurrenReportPart1.getAtachControCalidadInfrms());


    }


    private boolean checkExisteMiumReportsVINCULADOS() {
        //   int contadroInformsControCalidad=0;
        //  int contadroInformsCuadroMuetreo=0;


        //  String [] allInformCuadroMuetreo =RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.split(",");
        /// String [] allInformcONTROLcALIDA=RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.split(",");


        if (RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.trim().isEmpty()) {
            Toast.makeText(ActivityContenedoresPrev.this, "Agrega al menos un reporte Cuadro de muestreo", Toast.LENGTH_LONG).show();
            return false;

        }


        if (RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.trim().isEmpty()) {
            Toast.makeText(ActivityContenedoresPrev.this, "Agrega al menos un reporte Control calidad", Toast.LENGTH_LONG).show();

            return false;

        }



        /*

        if(allInformCuadroMuetreo.length>=1 && allInformcONTROLcALIDA.length>=1){

            return true;
        }

        else{

            return false;

        }

    */


        return true;
    }


    private void openBottomSheet() {

        DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(Variables.FormPreviewContenedores);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
    }

    private void updateInformeWhitCurrentDataOfViews() {

        String atachViucladoControlCalidad = Variables.CurrenReportPart1.getAtachControCalidadInfrms();
        String atachViucladoCuadroX = Variables.CurrenReportPart1.getAtachControCuadroMuestreo();


        //aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo
        Variables.CurrenReportPart1 = new SetInformEmbarque1(
                ediExportadoraProcesada.getText().toString(), ediExportadoraSolicitante.getText().toString(),
                ediMarca.getText().toString(),


                UNIQUE_ID_iNFORME, ediCodigo.getText().toString(),
                ediNhojaEvaluacion.getText().toString(), ediZona.getText().toString()
                , ediProductor.getText().toString(), ediCodigo.getText().toString()
                , ediPemarque.getText().toString(), ediNguiaRemision.getText().toString(), ediHacienda.getText().toString()
                , edi_nguia_transporte.getText().toString(), ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(), ediHoraInicio.getText().toString(), ediHoraTermino.getText().toString()
                , ediSemana.getText().toString(), ediEmpacadora.getText().toString(), ediContenedor.getText().toString(),
                FieldOpcional.observacionOpcional, ediHoraLLegadaContenedor.getText().toString(), ediHoraSalidaContenedor.getText().toString()
                , ediDestino.getText().toString(), ediNViaje.getText().toString(), ediNumContenedor.getText().toString(), ediVapor.getText().toString(),
                ediTipoContenedor.getText().toString(), ediTare.getText().toString(), ediBooking.getText().toString(), ediMaxGross.getText().toString(),
                ediNumSerieFunda.getText().toString(), stikVentolerExterna.getText().toString(),
                ediCableRastreoLlegada.getText().toString(), ediSelloPlasticoNaviera.getText().toString(), FieldOpcional.otrosSellosLLegaEspecif,
                ediClienteNombreReporte.getText().toString());

       // Variables.CurrenReportPart1.setKeyFirebase(Variables.CurrenReportPart1.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto
        Variables.CurrenReportPart1.setAtachControCalidadInfrms(atachViucladoControlCalidad);
        Variables.CurrenReportPart1.setAtachControCuadroMuestreo(atachViucladoCuadroX);
        Variables.CurrenReportPart1.setNombreRevisa(ediNombreRevisa.getText().toString());
        Variables.CurrenReportPart1.setCodigonRevisa(ediCodigoRevisa.getText().toString());




        if (RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.length() > 0) {
            Variables.CurrenReportPart1.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        }


        if (RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.length() > 0) {
            Variables.CurrenReportPart1.setAtachControCuadroMuestreo(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

        }


        Log.i("eldtatashd", "el string atch es " + RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);


        if (millisDateSelect > 0) {

            ////CONVERTIMOS A SIMPLE DATE FORMAT
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            String fechaString = formatter.format(Variables.CurrenReportPart1.getFechaCreacionInf());
            Variables.CurrenReportPart1.setSimpleDataFormat(fechaString);
            Variables.CurrenReportPart1.setFechaCreacionInf(millisDateSelect);


        }


        Variables.CurrenReportPart2 = new SetInformEmbarque2(UNIQUE_ID_iNFORME, ediTermofrafo1.getText().toString(), ediTermofrafo2.getText().toString()
                , ediHoraEncendido1.getText().toString(), ediHoraEncendido2.getText().toString(),
                ediUbicacion1.getText().toString(), ediUbicacion2.getText().toString(), ediRuma1.getText().toString(), ediRuma2.getText().toString()
                , ediCandadoqsercon.getText().toString(), ediSelloNaviera.getText().toString(), ediCableNaviera.getText().toString(),
                ediSelloPlasticoNaviera.getText().toString(), ediCandadoBotella.getText().toString(), ediCableExportadora.getText().toString(),
                ediSelloAdesivoexpor.getText().toString(), esiSelloAdhNaviera.getText().toString(), FieldOpcional.otrosSellosInstalaEsp,
                ediCompaniaTransporte.getText().toString(), ediNombreChofer.getText().toString(), ediCedula.getText().toString(),
                ediCedula.getText().toString(), ediPLaca.getText().toString(), ediMarcaCabezal.getText().toString(),
                ediColorCabezal.getText().toString(), ediCondicionBalanza.getText().toString(), ediTipodeCaja.getText().toString()
                , switchHaybalanza.isChecked(), switchHayEnsunchado.isChecked(), spinnertipodePlastico.getSelectedItem().toString(),
                switchBalanzaRep.isChecked(), spinnerubicacionBalanza.getSelectedItem().toString(), ediTipoBalanza.getText().toString(), ediBalanzaRepeso.getText().toString());

     //   Variables.CurrenReportPart2.setKeyFirebase(Variables.CurrenReportPart2.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


        Variables.CurrenReportPart3 = new SetInformDatsHacienda(spFuenteAgua.getSelectedItem().toString(), swAguaCorrida.isChecked(), switchLavdoRacimos.isChecked(),

                spFumigaCorL1.getSelectedItem().toString(),
                spTipoBoquilla.getSelectedItem().toString()
                // ediFumigacionClin1.getText().toString()

                //ediTipoBoquilla.getText().toString()

                , ediCajasProcDesp.getText().toString(), ediRacimosCosech.getText().toString(), ediRacimosRecha.getText().toString(), ediRacimProces.getText().toString()
                , UNIQUE_ID_iNFORME, ediExtCalid.getText().toString(), ediExtCalidCi.getText().toString());


        updateDatosEvaludoresOFinforme3(Variables.CurrenReportPart3);

       // Variables.CurrenReportPart3.setKeyFirebase(Variables.CurrenReportPart3.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


        updateCaledarioEnfunde(Variables.CurrenReportPart3);


    }


    private void createObjcInformeAndUpload() {

        //aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo
        SetInformEmbarque1 informe = new SetInformEmbarque1(ediExportadoraProcesada.getText().toString(), ediExportadoraSolicitante.getText().toString(),
                ediMarca.getText().toString(),

                UNIQUE_ID_iNFORME, ediCodigo.getText().toString(),
                ediNhojaEvaluacion.getText().toString(), ediZona.getText().toString()
                , ediProductor.getText().toString(), ediCodigo.getText().toString()
                , ediPemarque.getText().toString(), ediNguiaRemision.getText().toString(), ediHacienda.getText().toString()
                , edi_nguia_transporte.getText().toString(), ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(), ediHoraInicio.getText().toString(), ediHoraTermino.getText().toString()
                , ediSemana.getText().toString(), ediEmpacadora.getText().toString(), ediContenedor.getText().toString(),
                FieldOpcional.observacionOpcional, ediHoraLLegadaContenedor.getText().toString(), ediHoraSalidaContenedor.getText().toString()
                , ediDestino.getText().toString(), ediNViaje.getText().toString(), ediNumContenedor.getText().toString(), ediVapor.getText().toString(),
                ediTipoContenedor.getText().toString(), ediTare.getText().toString(), ediBooking.getText().toString(), ediMaxGross.getText().toString(),
                ediNumSerieFunda.getText().toString(), stikVentolerExterna.getText().toString(),
                ediCableRastreoLlegada.getText().toString(), ediSelloPlasticoNaviera.getText().toString(), FieldOpcional.otrosSellosLLegaEspecif,
                ediClienteNombreReporte.getText().toString());
        informe.setKeyFirebase(Variables.CurrenReportPart1.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto

        informe.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);

        informe.setAtachControCuadroMuestreo(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado); //LE BORRAMOS MASS


        informe.setNombreRevisa(ediNombreRevisa.getText().toString());
        informe.setCodigonRevisa(ediCodigoRevisa.getText().toString());


        Log.i("HOMERAS", "el string atch CUADRO MUESTREO ESes " + RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);


      miMapLbriado = generateMapLibriadoIfExistAndUpload(false);
        String keyWhereLocaleHashMapLibriado = "";



        //SUBIMOS EL MAPA
        if (miMapLbriado.size() > 0) {

            if(!Variables.CurrenReportPart1.getKeyOrNodeLibriadoSiEs().trim().isEmpty()){
                keyWhereLocaleHashMapLibriado = Variables.CurrenReportPart1.getKeyOrNodeLibriadoSiEs();
            }
            else  { //pero si esta vacio

                keyWhereLocaleHashMapLibriado=RealtimeDB.rootDatabaseReference.push().getKey();


            }


           RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado, keyWhereLocaleHashMapLibriado);

        }

        Log.i("simasss", "el size de libriado es   " +miMapLbriado.size());
        Log.i("simasss", "el keyWhereLocaleHashMapLibriado es   " +keyWhereLocaleHashMapLibriado);



        informe.setKeyOrNodeLibriadoSiEs(keyWhereLocaleHashMapLibriado);


        if (millisDateSelect > 0) {
            ////CONVERTIMOS A SIMPLE DATE FORMAT
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            String fechaString = formatter.format(Variables.CurrenReportPart1.getFechaCreacionInf());
            informe.setSimpleDataFormat(fechaString);
            informe.setFechaCreacionInf(millisDateSelect);
        }


        SetInformEmbarque2 informe2 = new SetInformEmbarque2(UNIQUE_ID_iNFORME, ediTermofrafo1.getText().toString(), ediTermofrafo2.getText().toString()
                , ediHoraEncendido1.getText().toString(), ediHoraEncendido2.getText().toString(),
                ediUbicacion1.getText().toString(), ediUbicacion2.getText().toString(), ediRuma1.getText().toString(), ediRuma2.getText().toString()
                , ediCandadoqsercon.getText().toString(), ediSelloNaviera.getText().toString(), ediCableNaviera.getText().toString(),
                ediSelloPlasticoNaviera.getText().toString(), ediCandadoBotella.getText().toString(), ediCableExportadora.getText().toString(),
                ediSelloAdesivoexpor.getText().toString(), esiSelloAdhNaviera.getText().toString(), FieldOpcional.otrosSellosInstalaEsp,
                ediCompaniaTransporte.getText().toString(), ediNombreChofer.getText().toString(), ediCedula.getText().toString(),
                ediCedula.getText().toString(), ediPLaca.getText().toString(), ediMarcaCabezal.getText().toString(),
                ediColorCabezal.getText().toString(), ediCondicionBalanza.getText().toString(), ediTipodeCaja.getText().toString()
                , switchHaybalanza.isChecked(), switchHayEnsunchado.isChecked(), spinnertipodePlastico.getSelectedItem().toString(),
                switchBalanzaRep.isChecked(), spinnerubicacionBalanza.getSelectedItem().toString(), ediTipoBalanza.getText().toString(),
                ediBalanzaRepeso.getText().toString());


        informe2.setKeyFirebase(Variables.CurrenReportPart2.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


        Log.i("swirhchc","el swAguaCorrida upload es  es  "+swAguaCorrida.isChecked());
        Log.i("swirhchc","el switchLavdoRacimos upload es  es  "+switchLavdoRacimos.isChecked());


        SetInformDatsHacienda informe3 = new SetInformDatsHacienda(spFuenteAgua.getSelectedItem().toString(), swAguaCorrida.isChecked(), switchLavdoRacimos.isChecked(),

                spFumigaCorL1.getSelectedItem().toString(),
                spTipoBoquilla.getSelectedItem().toString()
                // ediFumigacionClin1.getText().toString()

                //ediTipoBoquilla.getText().toString()

                , ediCajasProcDesp.getText().toString(), ediRacimosCosech.getText().toString(), ediRacimosRecha.getText().toString(), ediRacimProces.getText().toString()
                , UNIQUE_ID_iNFORME, ediExtCalid.getText().toString(), ediExtCalidCi.getText().toString());


        updateDatosEvaludoresOFinforme3(informe3);


        informe3.setKeyFirebase(Variables.CurrenReportPart3.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto
        updateCaledarioEnfunde(informe3);

        RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos
        ProductPostCosecha objectProduc=   addProdcutsPostCosechaUpdate(); //agregamos y subimos los productos postcosecha..

        //no

           ArrayList<ImagenReport> listImagesToUpload=  generateistImagesToUpload(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

        uploadInformeToDatabase(informe,informe2,informe3,Variables.currentInformRegisterSelected, objectProduc, listImagesToUpload );



    }

    private void updateDatosEvaludoresOFinforme3(SetInformDatsHacienda informe3) {

        if (!ediExtRodillo.getText().toString().trim().isEmpty()) {

            informe3.setExtensionistDeRodillo(ediExtRodillo.getText().toString());
            informe3.setCI_extensionistDeRodillo(ediExtRodilloCi.getText().toString());

        }


        if (!ediExtGancho.getText().toString().trim().isEmpty()) {
            informe3.setExtensionistEnGancho(ediExtGancho.getText().toString());
            informe3.setCI_extensionistEnGancho(ediExtGanchoCi.getText().toString());

        }


    }


    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {

            @Override
            public void onItemClick(int position, View v) {

                if(v.getId()==R.id.imvClose){

                    Log.i("giranda","vamos a eliminar");



                    try {
                        Variables.typeoFdeleteImgORgire = Objects.requireNonNull(ImagenReport.hashMapImagesData.get(v.getTag().toString())).getTipoImagenCategory();
                        Log.i("mispiggi", "el size antes de eliminar es " + ImagenReport.hashMapImagesData.size());
                        Log.i("mispiggi", "OK HAY UN CLICK EN LA CATEGORIA  "+Variables.typeoFdeleteImgORgire);


                        Variables.  listImagesToDelete.add(v.getTag().toString());//agregamos ea imagen para borrarla
                        ImagenReport.hashMapImagesData.remove(v.getTag().toString());

                        showImagesPicShotOrSelectUpdateView(true,position);



                    }

                    catch (Exception e) {
                        e.printStackTrace();

                        Log.i("giranda", "error al eliminar" + e.getMessage());

                    }

                }else{  ///aqui giramos la imagen..
                    Log.i("giranda","vamos a girar hombe");

                    showImagesPicShotOrSelectUpdateViewGirar(position);



                }




            }

        });
    }


      ArrayList<ImagenReport>  generateistImagesToUpload()  {
        ArrayList<ImagenReport> list2 =new ArrayList<>();

        if (!Variables.hashMapImagesStart.keySet().equals(ImagenReport.hashMapImagesData.keySet())) { //si no son iguales
            list2 = Utils.mapToArrayList(Utils.creaHahmapNoDuplicado());
        }


        if (Utils.objsIdsDecripcionImgsMOreDescripc.size() > 0) {

             //actualizamos la descripcion
            RealtimeDB.initDatabasesReferenceImagesData();
            RealtimeDB.actualizaDescripcionIms(Utils.objsIdsDecripcionImgsMOreDescripc);

        }



        return list2;

      //  Utils.updateImageReportObjec(); //asi actualizamos la propiedad sortPositionImage,




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
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);


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
            int result = ContextCompat.checkSelfPermission(ActivityContenedoresPrev.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(ActivityContenedoresPrev.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        }
    }


    ///vamos a cehekar que exista al menos una imagen en cada categoria...
    //comprbar que exista un objeto imagen.....

    //primero chekeamos el el uri exista...


    private boolean existminiumImage(int numImagenNMinimo, int categoriaImagenToSearch) {

        Log.i("minimopics", "el size de ImagenReport.hashMapImagesData es " + ImagenReport.hashMapImagesData.size());


        int numImagesEcontradas = 0;


        for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) { //revismao en todo el map

            //   String key = set.getKey();

            ImagenReport value = set.getValue();

            if (value.getTipoImagenCategory() == categoriaImagenToSearch) {

                numImagesEcontradas++;

                if (numImagesEcontradas >= numImagenNMinimo) {
                    break;

                }

            }


        }


        if (numImagesEcontradas >= numImagenNMinimo) {
            return true;
        } else {
            return false;


        }


    }


    private boolean checkDatosGeneralesIsLleno() {

        LinearLayout layoutContainerSeccion1 = findViewById(R.id.layoutContainerSeccion1);



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



        if (Objects.requireNonNull(ediSemana.getText()).toString().isEmpty()) { //chekamos que no este vacia
            ediSemana.requestFocus();
            ediSemana.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }

        if (ediFecha.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediFecha.requestFocus();
            ediFecha.setError("Debe selecionar una fecha");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediProductor.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediProductor.requestFocus();
            ediProductor.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediHacienda.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediHacienda.requestFocus();
            ediHacienda.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediCodigo.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCodigo.requestFocus();
            ediCodigo.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediInscirpMagap.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediInscirpMagap.requestFocus();
            ediInscirpMagap.setError("Este espacio es obligatorio");
            ediInscirpMagap.setText("_");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediPemarque.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediPemarque.requestFocus();
            ediPemarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediZona.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediZona.requestFocus();
            ediZona.setError("Debe selecionar una zona");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediHoraInicio.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediHoraInicio.requestFocus();
            ediHoraInicio.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediHoraTermino.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediHoraTermino.requestFocus();
            ediHoraTermino.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediNguiaRemision.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediNguiaRemision.requestFocus();
            ediNguiaRemision.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (edi_nguia_transporte.getText().toString().isEmpty()) { //chekamos que no este vacia
            edi_nguia_transporte.requestFocus();
            edi_nguia_transporte.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediNtargetaEmbarque.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediNtargetaEmbarque.requestFocus();
            ediNtargetaEmbarque.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediNhojaEvaluacion.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediNhojaEvaluacion.requestFocus();
            ediNhojaEvaluacion.setError("Este espacio es obligatorio");
            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;
            //obtiene el padre del padre

        }


        if (ediEmpacadora.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediEmpacadora.requestFocus();
            ediEmpacadora.setError("Este espacio es obligatorio");

            layoutContainerSeccion1.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (!ediObservacion.getText().toString().isEmpty()) { //si esta lleno

            FieldOpcional.observacionOpcional = ediObservacion.getText().toString();

        }




        return true;
    }


    private boolean checkcantidadPostcosechaIsLleno() {
        LinearLayout layoutContainerSeccion2 = findViewById(R.id.layoutContainerSeccion2);
        Log.i("camisad", "se llamo este ");

        if (ediPPC01.getText().toString().isEmpty() && ediPPC02.getText().toString().isEmpty() && ediPPC03.getText().toString().isEmpty()
                && ediPPC04.getText().toString().isEmpty() && ediPPC05.getText().toString().isEmpty() && ediPPC06.getText().toString().isEmpty()
                && ediPPC07.getText().toString().isEmpty() && ediPPC08.getText().toString().isEmpty() && ediPPC09.getText().toString().isEmpty()
                && ediPPC010.getText().toString().isEmpty() && ediPPC011.getText().toString().isEmpty() && ediPPC012.getText().toString().isEmpty()
                && ediPPC013.getText().toString().isEmpty() && ediPPC014.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()
                && ediPPC016.getText().toString().isEmpty()
        ) { //chekamos que no este vacia
            ediPPC01.requestFocus();
            ediPPC01.setError("Inserte al menos 1 producto");
            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);

            Log.i("camisad", "se eejcuto este");

            return false;

        }


        if (!ediPPC015.getText().toString().isEmpty() && ediPPC016.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediPPC016.requestFocus();
            ediPPC016.setError("Inserte cantidad");

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (!ediPPC016.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediPPC015.requestFocus();
            ediPPC015.setError("inserte nombre producto");

            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
            return false;

        }





        return true;

    }


    private boolean checkDatosContenedorIsLleno() {

        LinearLayout layoutContainerSeccion3 = findViewById(R.id.layoutContainerSeccion3);


        ///CHEKEAMOS DATA seccion CONTENEDOR

        if (ediDestino.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediDestino.requestFocus();
            ediDestino.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediNViaje.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediNViaje.requestFocus();
            ediNViaje.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediVapor.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediVapor.requestFocus();
            ediVapor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediTipoContenedor.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediTipoContenedor.requestFocus();
            ediTipoContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediHoraLLegadaContenedor.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediHoraLLegadaContenedor.requestFocus();
            ediHoraLLegadaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediHoraSalidaContenedor.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediHoraSalidaContenedor.requestFocus();
            ediHoraSalidaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        //chekamos que al menos exista una imagen...




        return true;

    }


    private boolean checkDataSellosLlegadaIsLleno() {
        LinearLayout layoutContainerSeccion4 = findViewById(R.id.layoutContainerSeccion4);


        if (ediTare.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediTare.requestFocus();
            ediTare.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        ///CHEKEAMOS DATA seccion CONTENEDOR

        if (ediBooking.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediBooking.requestFocus();
            ediBooking.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediMaxGross.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediMaxGross.requestFocus();
            ediMaxGross.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediNumSerieFunda.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediNumSerieFunda.requestFocus();
            ediNumSerieFunda.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (stikVentolerExterna.getText().toString().isEmpty()) { //chekamos que no este vacia
            stikVentolerExterna.requestFocus();
            stikVentolerExterna.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediCableRastreoLlegada.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCableRastreoLlegada.requestFocus();
            ediCableRastreoLlegada.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediSelloPlasticoNaviera.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediSelloPlasticoNaviera.requestFocus();
            ediSelloPlasticoNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion4.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (!ediOtroSellosLlegada.getText().toString().isEmpty()) { //este es opcional... si esta vacio

            FieldOpcional.otrosSellosLLegaEspecif = ediOtroSellosLlegada.getText().toString();
        }





        return true;


    }


    private boolean checkSellosInstaladosIsLleno() {

        LinearLayout layoutContainerSeccion5 = findViewById(R.id.layoutContainerSeccion5);

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
        if (ediCandadoqsercon.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCandadoqsercon.requestFocus();
            ediCandadoqsercon.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        /*
        if (ediSelloNaviera.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediSelloNaviera.requestFocus();
            ediSelloNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        */

        if (ediCableNaviera.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCableNaviera.requestFocus();
            ediCableNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);

            return false;

        }

        if (ediSelloPlastico.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediSelloPlastico.requestFocus();
            ediSelloPlastico.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediCandadoBotella.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCandadoBotella.requestFocus();
            ediCandadoBotella.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if (ediCableExportadora.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCableExportadora.requestFocus();
            ediCableExportadora.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }
        if (ediSelloAdesivoexpor.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediSelloAdesivoexpor.requestFocus();
            ediSelloAdesivoexpor.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (esiSelloAdhNaviera.getText().toString().isEmpty()) { //chekamos que no este vacia
            esiSelloAdhNaviera.requestFocus();
            esiSelloAdhNaviera.setError("Este espacio es obligatorio");

            layoutContainerSeccion5.setVisibility(LinearLayout.VISIBLE);
            return false;

        }





        if (!ediOtherSellos.getText().toString().isEmpty()) { //si esta lleno
            FieldOpcional.otrosSellosInstalaEsp = ediOtherSellos.getText().toString();


        }


        return true;
    }



    private boolean checkDatosTransportistaIsLleno() {

        LinearLayout layoutContainerSeccion6 = findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if (ediCompaniaTransporte.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCompaniaTransporte.requestFocus();
            ediCompaniaTransporte.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediNombreChofer.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediNombreChofer.requestFocus();
            ediNombreChofer.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediCedula.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCedula.requestFocus();
            ediCedula.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediCelular.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCelular.requestFocus();
            ediCelular.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediPLaca.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediPLaca.requestFocus();
            ediPLaca.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediMarcaCabezal.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediMarcaCabezal.requestFocus();
            ediMarcaCabezal.setError("Este espacio es obligatorio");
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediColorCabezal.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediColorCabezal.requestFocus();
            ediColorCabezal.setError("Este espacio es obligatorio");

            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        return true;

    }


    private boolean checkDatosProcesoIsLleno() {


        LinearLayout layoutContainerSeccion7 = findViewById(R.id.layoutContainerDatsProceso);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if (ediCondicionBalanza.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediTipodeCaja.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediTipodeCaja.requestFocus();
            ediTipodeCaja.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediCondicionBalanza.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCondicionBalanza.requestFocus();
            ediCondicionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }

        if (ediTipoPlastico.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediTipoPlastico.requestFocus();
            ediTipoPlastico.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediTipoBalanza.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediTipoBalanza.requestFocus();
            ediTipoBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }




            //editipbalanzaRepeso.requestFocus();
            //editipbalanzaRepeso.setError("Este espacio es obligatorio");

            //layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            //  return false;






        if (ediUbicacionBalanza.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediUbicacionBalanza.requestFocus();
            ediUbicacionBalanza.setError("Este espacio es obligatorio");

            layoutContainerSeccion7.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        return true;

    }


    private void updaTeProductsPostCosecha() {

        products = new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        products.keyFirebase = productxGlobal.keyFirebase;

        EditText[] editextArray = {ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07,
                ediPPC08, ediPPC09, ediPPC010, ediPPC011, ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016};


        for (int indice = 0; indice < editextArray.length; indice++) {
            EditText currentEditext = editextArray[indice];
            if (!currentEditext.getText().toString().isEmpty()) { //si no esta vacioo
                if (!currentEditext.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditext.getId()) {

                        case R.id.ediPPC01:
                            products.alumbre = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC02:
                            products.bc100 = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC03:
                            products.sb100 = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC04:
                            products.eclipse = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC05:
                            products.acido_citrico = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC06:
                            products.biottol = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC07:
                            products.bromorux = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC08:
                            products.ryzuc = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC09:
                            products.mertec = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC010:
                            products.sastifar = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC011:
                            products.xtrata = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC012:
                            products.nlarge = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC013:
                            products.gib_bex = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC014:
                            products.cloro = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC015:
                            Log.i("numproducts","se eejcuto el pp015  ");

                            products.otro_especifique = currentEditext.getText().toString();
                            products.cantidadOtro = ediPPC016.getText().toString();

                            Log.i("numproducts","el cnatidad otro es  "+ediPPC016.getText().toString());

                            Log.i("numproducts","el cnatidad otro es  "+products.cantidadOtro);

                            break;

/*
                        case R.id.ediPPC016:
                            products.cantidadOtro = currentEditext.getText().toString();
                            break;
*/

                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }


        Variables.currenProductPostCosecha=products;

    }


    private  ProductPostCosecha addProdcutsPostCosechaUpdate() {

        ProductPostCosecha producto = new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        producto.keyFirebase = productxGlobal.keyFirebase;

        EditText[] editextArray = {ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07,
                ediPPC08, ediPPC09, ediPPC010, ediPPC011, ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016};


        for (int indice = 0; indice < editextArray.length; indice++) {
            EditText currentEditext = editextArray[indice];
            if (!currentEditext.getText().toString().isEmpty()) { //si no esta vacioo
                if (!currentEditext.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditext.getId()) {

                        case R.id.ediPPC01:
                            producto.alumbre = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC02:
                            producto.bc100 = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC03:
                            producto.sb100 = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC04:
                            producto.eclipse = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC05:
                            producto.acido_citrico = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC06:
                            producto.biottol = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC07:
                            producto.bromorux = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC08:
                            producto.ryzuc = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC09:
                            producto.mertec = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC010:
                            producto.sastifar = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC011:
                            producto.xtrata = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC012:
                            producto.nlarge = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC013:
                            producto.gib_bex = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC014:
                            producto.cloro = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC015:
                            producto.otro_especifique = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC016:
                            producto.cantidadOtro = currentEditext.getText().toString();
                            break;


                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }


       // RealtimeDB.UpdateProductosPostCosecha(producto,ActivityContenedoresPrev.this);


        try {
            pdialogff.dismiss();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //  startActivity(new Intent(ActivityContenedoresPrev.this, ActivitySeeReports.class));


        return producto;
    }



    private void diseableViewsByTipe(View view) {

        if (view instanceof TextInputEditText) { //asi es un editex compobamos si esta lleno

            TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

            // editText.setFocusable(false);
            editText.setEnabled(false);
            // editText.setCursorVisible(false);
            // editText.setKeyListener(null);
            //  editText.setBackgroundColor(Color.TRANSPARENT);

        } else if (view instanceof Spinner) {
            Spinner spinner = (Spinner) view; //asi lo convertimos
            spinner.setEnabled(false);
            spinner.setClickable(false);

        } else if (view instanceof Switch) {
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
            swichtView.setEnabled(false);
            swichtView.setClickable(false);

        } else if (view instanceof ImageView) {
            ImageView imagev = (ImageView) view; //asi lo convertimos
            imagev.setEnabled(false);
            imagev.setClickable(false);


        } else if (view instanceof Button) {
            Button btn = (Button) view; //asi lo convertimos
            btn.setEnabled(false);
            btn.setClickable(false);


        }


    }


    private void activateViewsByTypeView(View view) {

        // ediProductor.setCursorVisible(true);

        //ediProductor.setFocusable(true);
        ediProductor.setEnabled(true);

        if (view instanceof TextInputEditText) { //asi es un editex compobamos si esta lleno

            TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

            editText.setEnabled(true);


            //  editText.requestFocus();

            // editText.setFocusable(true);
            //  editText.setEnabled(true);
            // editText.setCursorVisible(true);
            // editText.setKeyListener(false);
            //  editText.setBackgroundColor(Color.TRANSPARENT);

        } else if (view instanceof Spinner) {
            Spinner spinner = (Spinner) view; //asi lo convertimos
            spinner.setEnabled(true);
            spinner.setClickable(true);

        } else if (view instanceof Switch) {
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
            swichtView.setEnabled(true);
            swichtView.setClickable(true);

        } else if (view instanceof Button) {
            Button btn = (Button) view; //asi lo convertimos
            btn.setEnabled(true);
            btn.setClickable(true);


        } else if (view instanceof ImageView) {
            ImageView btn = (ImageView) view; //asi lo convertimos
            btn.setEnabled(true);
            btn.setClickable(true);


        }

    }


    private void activateModePreview() {

        Log.i("extra", "se llamo activateModePreview descativamos ");
        Variables.isClickable = false;


        diseableViewsByTipe(ediClienteNombreReporte);
        diseableViewsByTipe(ediExportadoraSolicitante);
        diseableViewsByTipe(ediMarca);


        diseableViewsByTipe(ediSemana);
        diseableViewsByTipe(ediFecha);
        diseableViewsByTipe(ediProductor);
        diseableViewsByTipe(ediHacienda);
        diseableViewsByTipe(ediCodigo);
        diseableViewsByTipe(ediInscirpMagap);
        diseableViewsByTipe(ediPemarque);
        diseableViewsByTipe(ediZona);
        diseableViewsByTipe(ediHoraInicio);
        diseableViewsByTipe(ediHoraTermino);
        diseableViewsByTipe(ediHoraLLegadaContenedor);
        diseableViewsByTipe(ediHoraSalidaContenedor);
        diseableViewsByTipe(ediNguiaRemision);
        diseableViewsByTipe(edi_nguia_transporte);
        diseableViewsByTipe(ediNtargetaEmbarque);
        diseableViewsByTipe(ediNhojaEvaluacion);
        diseableViewsByTipe(ediObservacion);
        diseableViewsByTipe(ediEmpacadora);
        diseableViewsByTipe(ediContenedor);
        diseableViewsByTipe(ediPPC01);
        diseableViewsByTipe(ediPPC02);
        diseableViewsByTipe(ediPPC03);
        diseableViewsByTipe(ediPPC04);
        diseableViewsByTipe(ediPPC05);
        diseableViewsByTipe(ediPPC06);
        diseableViewsByTipe(ediPPC07);
        diseableViewsByTipe(ediPPC08);
        diseableViewsByTipe(ediPPC09);
        diseableViewsByTipe(ediPPC010);
        diseableViewsByTipe(ediPPC011);
        diseableViewsByTipe(ediPPC012);
        diseableViewsByTipe(ediPPC013);
        diseableViewsByTipe(ediPPC014);
        diseableViewsByTipe(ediPPC015);
        diseableViewsByTipe(ediPPC016);
        diseableViewsByTipe(ediDestino);
        diseableViewsByTipe(ediNViaje);
        diseableViewsByTipe(ediTipoContenedor);
        diseableViewsByTipe(ediVapor);
        diseableViewsByTipe(ediCompaniaTransporte);
        diseableViewsByTipe(ediNombreChofer);
        diseableViewsByTipe(ediCedula);
        diseableViewsByTipe(ediCelular);
        diseableViewsByTipe(ediPLaca);
        diseableViewsByTipe(ediMarcaCabezal);
        diseableViewsByTipe(ediColorCabezal);
        diseableViewsByTipe(ediTare);
        diseableViewsByTipe(ediBooking);
        diseableViewsByTipe(ediMaxGross);
        diseableViewsByTipe(ediNumSerieFunda);
        diseableViewsByTipe(stikVentolerExterna);
        diseableViewsByTipe(ediCableRastreoLlegada);
        diseableViewsByTipe(ediSelloPlasticoNaviera);
        diseableViewsByTipe(ediOtroSellosLlegada);
        diseableViewsByTipe(ediCondicionBalanza);
        diseableViewsByTipe(ediTipodeCaja);
        diseableViewsByTipe(ediTipoPlastico);
        diseableViewsByTipe(ediTipoBalanza);
        diseableViewsByTipe(editipbalanzaRepeso);
        diseableViewsByTipe(ediUbicacionBalanza);
        diseableViewsByTipe(ediTermofrafo1);
        diseableViewsByTipe(ediHoraEncendido1);
        diseableViewsByTipe(ediUbicacion1);
        diseableViewsByTipe(ediRuma1);
        diseableViewsByTipe(ediTermofrafo2);
        diseableViewsByTipe(ediHoraEncendido2);
        diseableViewsByTipe(ediUbicacion2);
        diseableViewsByTipe(ediRuma2);
        diseableViewsByTipe(ediCandadoqsercon);
        diseableViewsByTipe(ediSelloNaviera);
        diseableViewsByTipe(ediCableNaviera);
        diseableViewsByTipe(ediSelloPlastico);
        diseableViewsByTipe(ediCandadoBotella);
        diseableViewsByTipe(ediCableExportadora);
        diseableViewsByTipe(ediSelloAdesivoexpor);
        diseableViewsByTipe(esiSelloAdhNaviera);
        diseableViewsByTipe(ediOtherSellos);
        diseableViewsByTipe(ediEnsunchado);
        diseableViewsByTipe(ediBalanzaRepeso);

        diseableViewsByTipe(ediCajasProcDesp);
        diseableViewsByTipe(ediRacimosCosech);
        diseableViewsByTipe(ediRacimosRecha);
        diseableViewsByTipe(ediRacimProces);

        diseableViewsByTipe(ediExtCalid);
        diseableViewsByTipe(ediExtCalidCi);
        diseableViewsByTipe(ediExtRodillo);
        diseableViewsByTipe(ediExtRodilloCi);
        diseableViewsByTipe(ediExtGancho);
        diseableViewsByTipe(ediExtGanchoCi);


        //SPINNERS
        diseableViewsByTipe(spinnerSelectZona);
        diseableViewsByTipe(spinnerCondicionBalanza);
        diseableViewsByTipe(spinnertipodePlastico);
        diseableViewsByTipe(spinnertipodeBlanza);
        diseableViewsByTipe(spinnertipodeBlanzaRepeso);
        diseableViewsByTipe(spinnerubicacionBalanza);


        diseableViewsByTipe(spFuenteAgua);
        diseableViewsByTipe(swAguaCorrida);
        diseableViewsByTipe(switchLavdoRacimos);
        diseableViewsByTipe(spFumigaCorL1);
        diseableViewsByTipe(spTipoBoquilla);

        //SWITCHSÇ
        diseableViewsByTipe(switchContenedor);
        diseableViewsByTipe(switchHaybalanza);
        diseableViewsByTipe(switchHayEnsunchado);
        diseableViewsByTipe(switchBalanzaRep);


        //iMAGEVIEWS


        diseableViewsByTipe(imgVAtachProcesoFrutaFinca);
        diseableViewsByTipe(imbTakePicProcesoFrutaFinca);
        diseableViewsByTipe(imgVAtachLlegadaContenedor);
        diseableViewsByTipe(imbTakePicLllegadaContenedor);
        diseableViewsByTipe(imgVAtachSellosLlegada);
        diseableViewsByTipe(imbTakePicSellosLlegada);
        diseableViewsByTipe(imgVAtachPuertaAbiertaContenedor);

        diseableViewsByTipe(imbTakePicPuertaAbiertaContenedor);
        diseableViewsByTipe(imgVAtachFotosPallet);
        diseableViewsByTipe(imbTakePicPallet);
        diseableViewsByTipe(imgVAtachCierreContenedor);
        diseableViewsByTipe(imbTakePicCierreContenedor);
        diseableViewsByTipe(imgVAtachDocumentacionss);
        diseableViewsByTipe(imbTakePicDocuementacionxx);



        //Buttons
        Button btnCheck = findViewById(R.id.btnCheck);
        diseableViewsByTipe(btnCheck);


    }

    private void activateModeEdit() {
        Variables.isClickable = true;

        activateViewsByTypeView(ediExportadoraSolicitante);
        activateViewsByTypeView(ediMarca);
        activateViewsByTypeView(ediClienteNombreReporte);



        activateViewsByTypeView(ediSemana);
        activateViewsByTypeView(ediFecha);
        activateViewsByTypeView(ediProductor);
        activateViewsByTypeView(ediHacienda);
        activateViewsByTypeView(ediCodigo);
        activateViewsByTypeView(ediInscirpMagap);
        activateViewsByTypeView(ediPemarque);
        activateViewsByTypeView(ediZona);
        activateViewsByTypeView(ediHoraInicio);
        activateViewsByTypeView(ediHoraTermino);
        activateViewsByTypeView(ediHoraLLegadaContenedor);
        activateViewsByTypeView(ediHoraSalidaContenedor);
        activateViewsByTypeView(ediNguiaRemision);
        activateViewsByTypeView(edi_nguia_transporte);
        activateViewsByTypeView(ediNtargetaEmbarque);
        activateViewsByTypeView(ediNhojaEvaluacion);
        activateViewsByTypeView(ediObservacion);
        activateViewsByTypeView(ediEmpacadora);
        activateViewsByTypeView(ediContenedor);
        activateViewsByTypeView(ediPPC01);
        activateViewsByTypeView(ediPPC02);
        activateViewsByTypeView(ediPPC03);
        activateViewsByTypeView(ediPPC04);
        activateViewsByTypeView(ediPPC05);
        activateViewsByTypeView(ediPPC06);
        activateViewsByTypeView(ediPPC07);
        activateViewsByTypeView(ediPPC08);
        activateViewsByTypeView(ediPPC09);
        activateViewsByTypeView(ediPPC010);
        activateViewsByTypeView(ediPPC011);
        activateViewsByTypeView(ediPPC012);
        activateViewsByTypeView(ediPPC013);
        activateViewsByTypeView(ediPPC014);
        activateViewsByTypeView(ediPPC015);
        activateViewsByTypeView(ediPPC016);
        activateViewsByTypeView(ediDestino);
        activateViewsByTypeView(ediNViaje);
        activateViewsByTypeView(ediTipoContenedor);
        activateViewsByTypeView(ediVapor);
        activateViewsByTypeView(ediCompaniaTransporte);
        activateViewsByTypeView(ediNombreChofer);
        activateViewsByTypeView(ediCedula);
        activateViewsByTypeView(ediCelular);
        activateViewsByTypeView(ediPLaca);
        activateViewsByTypeView(ediMarcaCabezal);
        activateViewsByTypeView(ediColorCabezal);
        activateViewsByTypeView(ediTare);
        activateViewsByTypeView(ediBooking);
        activateViewsByTypeView(ediMaxGross);
        activateViewsByTypeView(ediNumSerieFunda);
        activateViewsByTypeView(stikVentolerExterna);
        activateViewsByTypeView(ediCableRastreoLlegada);
        activateViewsByTypeView(ediSelloPlasticoNaviera);
        activateViewsByTypeView(ediOtroSellosLlegada);
        activateViewsByTypeView(ediCondicionBalanza);
        activateViewsByTypeView(ediTipodeCaja);
        activateViewsByTypeView(ediTipoPlastico);
        activateViewsByTypeView(ediTipoBalanza);
        //  activateViewsByTypeView(    editipbalanzaRepeso);
        activateViewsByTypeView(ediUbicacionBalanza);
        activateViewsByTypeView(ediTermofrafo1);
        activateViewsByTypeView(ediHoraEncendido1);
        activateViewsByTypeView(ediUbicacion1);
        activateViewsByTypeView(ediRuma1);
        activateViewsByTypeView(ediTermofrafo2);
        activateViewsByTypeView(ediHoraEncendido2);
        activateViewsByTypeView(ediUbicacion2);
        activateViewsByTypeView(ediRuma2);
        activateViewsByTypeView(ediCandadoqsercon);
        activateViewsByTypeView(ediSelloNaviera);
        activateViewsByTypeView(ediCableNaviera);
        activateViewsByTypeView(ediSelloPlastico);
        activateViewsByTypeView(ediCandadoBotella);
        activateViewsByTypeView(ediCableExportadora);
        activateViewsByTypeView(ediSelloAdesivoexpor);
        activateViewsByTypeView(esiSelloAdhNaviera);
        activateViewsByTypeView(ediOtherSellos);

        activateViewsByTypeView(ediCajasProcDesp);
        activateViewsByTypeView(ediRacimosCosech);
        // activateViewsByTypeView(ediRacimosRecha);
        activateViewsByTypeView(ediRacimProces);

        activateViewsByTypeView(spFuenteAgua);
        activateViewsByTypeView(swAguaCorrida);
        activateViewsByTypeView(switchLavdoRacimos);
        activateViewsByTypeView(spFumigaCorL1);
        activateViewsByTypeView(spTipoBoquilla);


        activateViewsByTypeView(ediExtCalid);
        activateViewsByTypeView(ediExtCalidCi);
        activateViewsByTypeView(ediExtRodillo);
        activateViewsByTypeView(ediExtRodilloCi);
        activateViewsByTypeView(ediExtGancho);
        activateViewsByTypeView(ediExtGanchoCi);


        //SPINNERS
        activateViewsByTypeView(spinnerSelectZona);
        activateViewsByTypeView(spinnerCondicionBalanza);
        activateViewsByTypeView(spinnertipodePlastico);
        activateViewsByTypeView(spinnertipodeBlanza);
        activateViewsByTypeView(spinnertipodeBlanzaRepeso);
        activateViewsByTypeView(spinnerubicacionBalanza);

        //SWITCHSÇ
        activateViewsByTypeView(switchContenedor);
        activateViewsByTypeView(switchHaybalanza);
        activateViewsByTypeView(switchHayEnsunchado);
        activateViewsByTypeView(switchBalanzaRep);


//iMAGEVIEWS
        activateViewsByTypeView(imgVAtachProcesoFrutaFinca);
        activateViewsByTypeView(imbTakePicProcesoFrutaFinca);
        activateViewsByTypeView(imgVAtachLlegadaContenedor);
        activateViewsByTypeView(imbTakePicLllegadaContenedor);
        activateViewsByTypeView(imgVAtachSellosLlegada);
        activateViewsByTypeView(imbTakePicSellosLlegada);
        activateViewsByTypeView(imgVAtachPuertaAbiertaContenedor);

        activateViewsByTypeView(imbTakePicPuertaAbiertaContenedor);
        activateViewsByTypeView(imgVAtachFotosPallet);
        activateViewsByTypeView(imbTakePicPallet);
        activateViewsByTypeView(imgVAtachCierreContenedor);
        activateViewsByTypeView(imbTakePicCierreContenedor);
        activateViewsByTypeView(imgVAtachDocumentacionss);
        activateViewsByTypeView(imbTakePicDocuementacionxx);


        //Buttons
        Button btnCheck = findViewById(R.id.btnCheck);
        activateViewsByTypeView(btnCheck);

    }


    private void setDtaInOthersViews(SetInformEmbarque1 info1Object, SetInformEmbarque2 info2Object
            , SetInformDatsHacienda info3Object) {


        Log.i("mizona", "la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  " + info1Object.getZona());




        selectValue(spinnerSelectZona, info1Object.getZona());
        selectValue(spinnerCondicionBalanza, info2Object.getCondicionBalanza());
        selectValue(spinnertipodePlastico, info2Object.getTipoPlastico());
        selectValue(spinnertipodeBlanza, info2Object.getTipoDeBalanza());
        selectValue(spinnertipodeBlanzaRepeso, info2Object.getTipoDeBalanzaRepeso());
        selectValue(spinnerubicacionBalanza, info2Object.getUbicacionBalanza());
        selectValue(spFuenteAgua, info3Object.getFuenteAgua());
        selectValue(spFumigaCorL1, info3Object.getFumigacionClin1());
        selectValue(spTipoBoquilla, info3Object.getEdiTipoBoquilla());
        selectValue(spinnerExportadora, info1Object.getExportadoraProcesada());


        swAguaCorrida.setChecked(info3Object.isHayAguaCorrida());

        Log.i("swirhchc","el swAguaCorrida es  "+info3Object.isHayAguaCorrida());


        switchLavdoRacimos.setChecked(info3Object.isHayLavadoRacimos());
        Log.i("swirhchc","el switchLavdoRacimos es  "+info3Object.isHayLavadoRacimos());


        if (info1Object.getContenedor().equalsIgnoreCase(" SI ")) {
            switchContenedor.setChecked(true);
        }

        else {
            switchContenedor.setChecked(false);
        }

        switchHaybalanza.setChecked(info2Object.isHayBalanza());
        switchHayEnsunchado.setChecked(info2Object.isHayExcelnsuchado());
        switchBalanzaRep.setChecked(info2Object.getHayBalanzaRepeso());

    }


    private void selectValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {

            if (spinner.getItemAtPosition(i).equals(value)) {
                spinner.setSelection(i);
                Log.i("mizona", "existe hurra" + value);
                break;

            } else {

                Log.i("mizona", "no exiwste " + value);

            }
        }

    }


    private void setDataInfields(SetInformEmbarque1 info1Object, SetInformEmbarque2 info2Object, SetInformDatsHacienda info3Object) {
        //usamos los 2 objetos para establecer esta data..

        Log.i("jamisama", "la semana es " + info1Object.getSemana());

        ediSemana.setText(info1Object.getSemana());
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(info1Object.getFechaCreacionInf());

        Log.i("SOMERG", "el  CLIENTE RREPORTE ES " + info1Object.getClienteReporte());


        ediNombreRevisa.setText(info1Object.getNombreRevisa());
        ediCodigoRevisa.setText(info1Object.getCodigonRevisa());

        ediExportadoraProcesada.setText(info1Object.getExportadoraProcesada());
        ediClienteNombreReporte.setText(info1Object.getClienteReporte());

        ediExportadoraSolicitante.setText(info1Object.getExportadoraSolicitante());
        ediMarca.setText(info1Object.getMarrca());


        ediFecha.setText(fechaString);

        ediProductor.setText(info1Object.getProductor());
        ediHacienda.setText(info1Object.getHacienda());
        ediCodigo.setText(info1Object.getCodeInforme());
        ediInscirpMagap.setText(info1Object.getInscirpMagap());
        ediPemarque.setText(info1Object.getPemarque());
        ediZona.setText(info1Object.getZona());
        ediHoraInicio.setText(info1Object.getHoraInicio());
        ediNumContenedor.setText(info1Object.getNumcionContenedor());
        ediHoraTermino.setText(info1Object.getHoraTermino());
        ediHoraLLegadaContenedor.setText(info1Object.getHoraLlegadaContenedor());

        ediHoraSalidaContenedor.setText(info1Object.getHoraSalidadContenedor());
        ediNguiaRemision.setText(info1Object.getNguiaRemision());
        edi_nguia_transporte.setText(info1Object.get_nguia_transporte());
        ediNtargetaEmbarque.setText(info1Object.getNtargetaEmbarque());
        ediNhojaEvaluacion.setText(info1Object.getEdiNhojaEvaluacion());
        ediObservacion.setText(info1Object.getObservacion());
        ediEmpacadora.setText(info1Object.getEmpacadora());
        ediContenedor.setText(info1Object.getContenedor());

        /*faltan los prouctos postcosecha agregamos usando un for**/


        ediDestino.setText(info1Object.getDestinoContenedor());
        ediNViaje.setText(info1Object.getNumeroViajeContenedor());
        ediTipoContenedor.setText(info1Object.getTipoContenedor());
        ediVapor.setText(info1Object.getVapor());

        ediCompaniaTransporte.setText(info2Object.getCompaniaTranporte());
        ediNombreChofer.setText(info2Object.getNombreChofer());
        ediCedula.setText(String.valueOf(info2Object.getCedulaChofer()));
        ediCelular.setText(String.valueOf(info2Object.getCelularChofer()));
        ediPLaca.setText(info2Object.getPlacaChofer());
        ediMarcaCabezal.setText(info2Object.getMarcaCaebzalChofer());
        ediColorCabezal.setText(info2Object.getColorCAbezal());
        ediTare.setText(info1Object.getTare());
        ediBooking.setText(info1Object.getBooking());
        ediMaxGross.setText(info1Object.getMaxGross());
        ediNumSerieFunda.setText(info1Object.getnSerieFunda());
        stikVentolerExterna.setText(info1Object.getStickerVentoExtern());
        ediCableRastreoLlegada.setText(info1Object.getCableRastreoLlegada());
        ediSelloPlasticoNaviera.setText(info1Object.getSelloPlasticoNaviera());
        ediCondicionBalanza.setText(info2Object.getCondicionBalanza());
        ediTipodeCaja.setText(info2Object.getTipoCaja());
        ediTipoPlastico.setText(info2Object.getTipoPlastico());
        ediTipoBalanza.setText(info2Object.getTipoDeBalanza());
        editipbalanzaRepeso.setText(info2Object.getTipoDeBalanzaRepeso());
        ediUbicacionBalanza.setText(info2Object.getUbicacionBalanza());
        ediTermofrafo1.setText(info2Object.getTermografo1());
        ediHoraEncendido1.setText(info2Object.getTermografo1HoraEncendido());
        ediUbicacion1.setText(info2Object.getUbicacionPalletN1());
        ediRuma1.setText(info2Object.getRumaPalletN1());
        ediTermofrafo2.setText(info2Object.getRumaPalletN2());
        ediHoraEncendido2.setText(info2Object.getTermografo2HoraEncendido());
        ediUbicacion2.setText(info2Object.getUbicacionPalletN2());
        ediRuma2.setText(info2Object.getRumaPalletN2());

        Log.i("objec2searcg", "el value es " + info2Object.getCandadoQsercom());


        ediCandadoqsercon.setText(info2Object.getCandadoQsercom());
        ediSelloNaviera.setText(info2Object.getSelloNaviera());
        ediCableNaviera.setText(info2Object.getCableNaviera());
        ediSelloPlastico.setText(info2Object.getSelloPlastico());
        ediCandadoBotella.setText(info2Object.getCandadoBotella());
        ediCableExportadora.setText(info2Object.getCableExportadora());
        ediSelloAdesivoexpor.setText(info2Object.getSelloAdhesivoExportadora());
        esiSelloAdhNaviera.setText(info2Object.getSelloNaviera());
        ediOtherSellos.setText(info2Object.getOtrosSellosEspecif());
        ediCajasProcDesp.setText(info3Object.getEdiCajasProcDesp());
        ediRacimosCosech.setText(info3Object.getEdiRacimosCosech());

        // ediRacimosRecha.setText(info3Object.getEdiRacimosRecha());

        ediRacimProces.setText(info3Object.getEdiRacimProces());
        ediExtCalid.setText(info3Object.getExtensionistCalid());
        ediExtCalidCi.setText(info3Object.getCI_extensionistCalid());
        ediExtRodillo.setText(info3Object.getExtensionistDeRodillo());
        ediExtRodilloCi.setText(info3Object.getCI_extensionistDeRodillo());
        ediExtGancho.setText(info3Object.getExtensionistEnGancho());
        ediExtGanchoCi.setText(info3Object.getCI_extensionistEnGancho());


        /**inicilizamos variable global Utils.numReportsVinculadsAll ,map de vinuclads,etc */
        Utils.initializeAndGETnuMSvinuclads(info1Object.getAtachControCalidadInfrms(), info1Object.getAtachControCuadroMuestreo());

        txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculadsAll));



    }


    private void checkModeVisualitY() {


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isModEdicionFields = extras.getBoolean(Variables.KEYEXTRAPREVIEW);

            Log.i("extra", "el modo es " + isModEdicionFields);
            //The key argument here must match that used in the other activity
        }


        if (isModEdicionFields) {
            TextView txtModeAdviser = findViewById(R.id.txtModeAdviser);
            activateModeEdit();
            txtModeAdviser.setText("Modo Edicion ");

            Log.i("isclkiel", "es clickeable es " + Variables.isClickable);

        } else {
            fab.setImageResource(R.drawable.ic_baseline_edit_24aa);
            activateModePreview();
            Log.i("isclkiel", "es clickeable es " + Variables.isClickable);

        }


        Variables.modoRecicler = Variables.DOWLOAD_IMAGES;


        //aqui llamadmosasg
        dowloadSecondPART_Report(Variables.CurrenReportPart1.getUniqueIDinforme(), 1);

        //inicializamos STORAGE..
        StorageDataAndRdB.initStorageReference();
        dowloadImagesDataReport(Variables.CurrenReportPart1.getUniqueIDinforme());

        dowLoadProducsPostC(Variables.CurrenReportPart1.getUniqueIDinforme());


        /**si existe lirbiado lo descrfamos*/


        Log.i("libiadod", "el value de libriado es " + Variables.CurrenReportPart1.getKeyOrNodeLibriadoSiEs());

        if (Variables.CurrenReportPart1.getKeyOrNodeLibriadoSiEs().length() > 1) {

            getLibriadoMap(Variables.CurrenReportPart1.getKeyOrNodeLibriadoSiEs());
            //si existe libriado lo descragamos...

        }

    }


//descargamos las imagenes path....


    //descargamos prodcutos postcosecha...

    void createlistsForReciclerviewsImages(ArrayList<ImagenReport> listImagenReports) {


        ArrayList<ImagenReport> lisFiltrada;

        int [] arrayTiposImagenes = {Variables.FOTO_PROCESO_FRUTA_FINCA,Variables.FOTO_LLEGADA_CONTENEDOR,Variables.FOTO_SELLO_LLEGADA,
                Variables.FOTO_PUERTA_ABIERTA_DEL_CONTENENEDOR,Variables.FOTO_PALLETS,Variables.FOTO_CIERRE_CONTENEDOR,
                Variables.FOTO_DOCUMENTACION};

        for (int indice = 0; indice < arrayTiposImagenes.length; indice++) {

            lisFiltrada = new ArrayList<>();

            for (int indice2 = 0; indice2 < listImagenReports.size(); indice2++) {

                if (listImagenReports.get(indice2).getTipoImagenCategory() == arrayTiposImagenes[indice]) { //entonces usamos este

                    lisFiltrada.add(listImagenReports.get(indice2));


                }

            }

            currentTypeImage = arrayTiposImagenes[indice];
            //lalamos el recicler que
            addImagesInRecyclerviews(lisFiltrada);


        }


        Variables.modoRecicler = Variables.SELEC_AND_TAKE_iMAGES;


    }


    void addInfotomap(ArrayList<ImagenReport> listImagenReports) {

        ImagenReport.hashMapImagesData = new HashMap<>();

        //agregamos adata al mapusnado un bucle

        for (int indice2 = 0; indice2 < listImagenReports.size(); indice2++) {

            ImagenReport currentImareportObj = listImagenReports.get(indice2);

            ImagenReport.hashMapImagesData.put(currentImareportObj.getUniqueIdNamePic(), currentImareportObj);

        }

        Log.i("sucecia", "se llamo method here");

        if (!Variables.copiamosData) {
            // Variables.hashMapImagesStart =ImagenReport.hashMapImagesData;

            //CREAMOS UNA COPIA USANDO UN BUCLE

            Variables.hashMapImagesStart = new HashMap<>();


            for (Map.Entry<String, ImagenReport> entry : ImagenReport.hashMapImagesData.entrySet()) {
                String key = entry.getKey();
                ImagenReport value = entry.getValue();

                Variables.hashMapImagesStart.put(key, value);
                // ...
            }

            Variables.copiamosData = true;


        }


    }

    void addImagesInRecyclerviews(ArrayList<ImagenReport> listImagenReports) {

        /**reordenamos lista asu */
        Collections.sort(listImagenReports, new Comparator<ImagenReport>()
        {
            @Override
            public int compare(ImagenReport lhs, ImagenReport rhs) {
                return lhs.getSortPositionImage() - rhs.getSortPositionImage();

              //  return Integer.compare(lhs.getSortPositionImage(), rhs.getSortPositionImage());
            }
        });


        //agregamos data al map

        RecyclerView recyclerView= null;
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        RecyclerViewAdapter adapter;
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


            adapter=new RecyclerViewAdapter(listImagenReports,this);
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


            Log.i("adpatertt","es difrentede nulo");



    }


    void dowLoadProducsPostC(String idAlquePERTENECE) {

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").
                child("listProductosPostCosecha").
                orderByChild("idpertenece").equalTo(idAlquePERTENECE);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Map<String, Object> map = null;
                //  Map<String, String> map = dataSnapshot.getValue(Map.class);
                //  Log.i("sliexsa","el size de map es "+map.size());


                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    productxGlobal = ds.getValue(ProductPostCosecha.class);
                }

                //  Log.i("sliexsa","existe"+product.cantidadOtro);


                if (productxGlobal != null) {
                    Variables.currenProductPostCosecha = productxGlobal;
                    setProductosPostcosecha(productxGlobal);
                }


                // createlistsForReciclerviewsImages(listImagenData);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa", "el error es " + error.getMessage());

            }
        });


    }


    void setProductosPostcosecha(ProductPostCosecha objProducto) {

        ediPPC01.setText(objProducto.alumbre);
        ediPPC02.setText(objProducto.bc100);
        ediPPC03.setText(objProducto.sb100);
        ediPPC04.setText(objProducto.eclipse);
        ediPPC05.setText(objProducto.acido_citrico);
        ediPPC06.setText(objProducto.biottol);
        ediPPC07.setText(objProducto.bromorux);
        ediPPC08.setText(objProducto.ryzuc);
        ediPPC09.setText(objProducto.mertec);
        ediPPC010.setText(objProducto.sastifar);
        ediPPC011.setText(objProducto.xtrata);
        ediPPC012.setText(objProducto.nlarge);
        ediPPC013.setText(objProducto.gib_bex);
        ediPPC014.setText(objProducto.cloro);
        ediPPC015.setText(objProducto.otro_especifique);
        ediPPC016.setText(objProducto.cantidadOtro);


        // Variables.modoRecicler=Variables.SELEC_AND_TAKE_iMAGES;

    }


    void dowloadImagesDataReport(String reportUNIQUEidtoSEARCH) { //DESCRAGAMOS EL SEGUNDO

        Log.i("imagheddd", "elreport unique id es  " + reportUNIQUEidtoSEARCH);

        RealtimeDB.initDatabasesReferenceImagesData(); //borrar

        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("idReportePerteence").equalTo(reportUNIQUEidtoSEARCH);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // ArrayList<ImagenReport>listImagenData=new ArrayList<>();
                Variables.listImagenDataGlobalCurrentReport = new ArrayList<>();


                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    ImagenReport imagenReport = ds.getValue(ImagenReport.class);
                    //  listImagenData.add(imagenReport);

                    if(!Utils.containsName(Variables.listImagenDataGlobalCurrentReport,imagenReport.getUniqueIdNamePic())) {
                        Variables.listImagenDataGlobalCurrentReport.add(imagenReport);

                    }

                }


                Log.i("superstorage","el size de lista dowload es "+Variables.listImagenDataGlobalCurrentReport.size());



                createlistsForReciclerviewsImages(Variables.listImagenDataGlobalCurrentReport);
                Utils.objsIdsDecripcionImgsMOreDescripc = new ArrayList<>();


                //solo un archivo
                //s


                if (currentIDcUDORmUESTREO != null) {

                    DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

                } else {

                    btnGENERARpdf.setEnabled(true);

                }


                //  btnGENERARpdf.setEnabled(true);

                Log.i("mispiggi", "se llamo a: addInfotomap");

                addInfotomap(Variables.listImagenDataGlobalCurrentReport);


                //este metodo lo llamaremos ahora
                //al objeto imagen report le agregaremos una propiedad llamada bitmap...o crearemos un map de bitmaps que usraemos para cargarlos desde el
                //el adpater del recicler view y asiu no alteramos el objeto imagereport...solo que ya no descragremos la imagen nuevamente...


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa", "el error es " + error.getMessage());

            }
        });


    }


    //nevcesitamos un metodo para eliminar un nodo en imagenes y borrarlo de storage..
    //por ahora solo con borrarlo de rtdabase
    private void geTidAndDelete(String idUniqueToDelete) { //busca el que tenga esa propieda y obtiene el id node child

        Log.i("imagheddd", "se lamo to delete");

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("uniqueIdNamePic").equalTo(idUniqueToDelete);

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData");
        //  Query query = usersdRef.orderByChild("uniqueIdNamePic").equalTo(Variables.currentCuponObjectGlob.getUniqueIdCupòn());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //   private void editaValue(String keyAtoUpdate,String titulo, String descripcion, String direccion, String ubicacionCordenaGoogleMap, String picNameofStorage, double cuponValor, String categoria,boolean switchActivate, boolean switchDestacado){
                try {

                    DataSnapshot nodeShot = dataSnapshot.getChildren().iterator().next();
                    String key = nodeShot.getKey();

                    usersdRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                //Toast.makeText(OfertsAdminActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    void addDataHashMapTypeImagesToPdf(ArrayList<ImagenReport> miLisAllImages) {


        for (ImagenReport imageb : miLisAllImages) {


        }


    }


    private boolean checkDatosHaciendaIsLleno() {
        LinearLayout layoutContainerSeccion8 = findViewById(R.id.layoutContainerSeccion8);


        if (spFuenteAgua.getSelectedItem().toString().equalsIgnoreCase("Ninguna")) { //chekamos que no este vacia
            ediFuenteAgua.requestFocus();
            ediFuenteAgua.setError("Fuente de agua requerido");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }



/*

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
*/

        if (ediCajasProcDesp.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediRacimosCosech.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
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

        if (ediRacimProces.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediRacimProces.requestFocus();
            ediRacimProces.setError("Este espacio es obligatorio");

            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediExtCalid.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediExtCalid.requestFocus();
            ediExtCalid.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;
        }


        if (ediExtCalidCi.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediExtCalidCi.requestFocus();
            ediExtCalidCi.setError("Este espacio es obligatorio");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        ///LOS DEMAS DATOS OPCIONALES

        if (!ediExtRodillo.getText().toString().isEmpty() && ediExtRodilloCi.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediExtRodilloCi.requestFocus();
            ediExtRodilloCi.setError("Se requiere La C.I");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediExtRodillo.getText().toString().isEmpty() && !ediExtRodilloCi.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediExtRodillo.requestFocus();
            ediExtRodillo.setError("Se requiere el nombre");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (!ediExtGancho.getText().toString().isEmpty() && ediExtGanchoCi.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediExtGanchoCi.requestFocus();
            ediExtGanchoCi.setError("Se requiere La C.I");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        if (ediExtGancho.getText().toString().isEmpty() && !ediExtGanchoCi.getText().toString().isEmpty()) { //chekamos que no este vacia
            ediExtGancho.requestFocus();
            ediExtGancho.setError("Se requiere el nombre");
            layoutContainerSeccion8.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        ///vamos con los datos de semananas y esoRe


        return true;

    }


    public void saveInfo() {
        updatePostionImegesSort();

        Log.i("somerliker", "llamamos save info ");

        /*
        pdialogff = new ProgressDialog(ActivityContenedoresPrev.this);
        pdialogff.setMessage("Actualizando data ");
        pdialogff.show();
*/


        //  createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...

        //aliminamos cambios




        createObjcInformeAndUpload();

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


        EditText ediPorc14 = findViewById(R.id.ediPorc14);
        EditText ediPorc13 = findViewById(R.id.ediPorc13);
        EditText ediPorc12 = findViewById(R.id.ediPorc12);
        EditText ediPorc11 = findViewById(R.id.ediPorc11);
        EditText ediPorc10 = findViewById(R.id.ediPorc10);
        EditText ediPsgddsorc9 = findViewById(R.id.ediPorc9);


        EditText[] array = {ediColortSem14, ediColortSem13, ediColortSem12, ediColortSem11, ediColortSem10, ediColortSem9,
                ediNumRcim14, ediNumRcim13, ediNumRcim12, ediNumRcim11, ediNumRcim10, ediNumRac9,
                ediPorc14, ediPorc13, ediPorc12, ediPorc11, ediPorc10, ediPsgddsorc9};


        int indice = 0;


        for (int i = 0; i < array.length; i++) {

            EditText current = array[i];

            if (!current.getText().toString().trim().isEmpty()) {
                String value = current.getText().toString();
                indice++;

                switch (current.getId()) {
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

        if (indice > 2) {
            return true;

        } else {
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


        EditText ediPorc14 = findViewById(R.id.ediPorc14);
        EditText ediPorc13 = findViewById(R.id.ediPorc13);
        EditText ediPorc12 = findViewById(R.id.ediPorc12);
        EditText ediPorc11 = findViewById(R.id.ediPorc11);
        EditText ediPorc10 = findViewById(R.id.ediPorc10);
        EditText ediPsgddsorc9 = findViewById(R.id.ediPorc9);


        EditText[] array = {ediColortSem14, ediColortSem13, ediColortSem12, ediColortSem11, ediColortSem10, ediColortSem9,
                ediNumRcim14, ediNumRcim13, ediNumRcim12, ediNumRcim11, ediNumRcim10, ediNumRac9,
                ediPorc14, ediPorc13, ediPorc12, ediPorc11, ediPorc10, ediPsgddsorc9};


        int indice = 0;


        for (int i = 0; i < array.length; i++) {

            EditText current = array[i];

            if (!current.getText().toString().trim().isEmpty()) {
                String value = current.getText().toString();
                indice++;

                switch (current.getId()) {
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

        if (indice > 2) {
            return true;

        } else {
            ediPsgddsorc9.requestFocus();
            ediPsgddsorc9.setError("Inserte al menos un valor en este cuadro");

            return false;

        }

    }


    private void setDataInMoreViews(SetInformDatsHacienda informe) {

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
        EditText ediNumRac9   = findViewById(R.id.ediNumRac9);


        EditText ediPorc14 = findViewById(R.id.ediPorc14);
        EditText ediPorc13 = findViewById(R.id.ediPorc13);
        EditText ediPorc12 = findViewById(R.id.ediPorc12);
        EditText ediPorc11 = findViewById(R.id.ediPorc11);
        EditText ediPorc10 = findViewById(R.id.ediPorc10);
        EditText ediPsgddsorc9 = findViewById(R.id.ediPorc9);


        ediColortSem14.setText(informe.getColortSem14());
        ediColortSem13.setText(informe.getColortSem13());
        ediColortSem12.setText(informe.getColortSem12());
        ediColortSem11.setText(informe.getColortSem11());
        ediColortSem10.setText(informe.getColortSem10());
        ediColortSem9.setText(informe.getColortSem9());

        ediNumRcim14.setText(informe.getNumRcim14());
        ediNumRcim13.setText(informe.getNumRcim13());
        ediNumRcim12.setText(informe.getNumRcim12());
        ediNumRcim11.setText(informe.getNumRcim11());
        ediNumRcim10.setText(informe.getNumRcim10());
        ediNumRac9.setText(informe.getNumRcim9());


        ediPorc14.setText(informe.getPorc14());
        ediPorc13.setText(informe.getPorc13());
        ediPorc12.setText(informe.getPorc12());
        ediPorc11.setText(informe.getPorc11());
        ediPorc10.setText(informe.getPorc10());
        ediPsgddsorc9.setText(informe.getPorc9());


    }


    void dowloadSecondPART_Report(String reportUNIQUEidtoSEARCH, int modo) { //DESCRAGAMOS EL SEGUNDO REPORTE

        Log.i("secondInform", "el curren report id es " + reportUNIQUEidtoSEARCH);
        RealtimeDB.initDatabasesRootOnly();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinformePart2").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformEmbarque2 informEmbarque2 = ds.getValue(SetInformEmbarque2.class);

                    if (informEmbarque2 != null) {
                        Variables.CurrenReportPart2 = informEmbarque2;
                        break;
                    }
                }


                dowloadThirdReportAndCallSetData(reportUNIQUEidtoSEARCH, modo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa", "el error es " + error.getMessage());

            }
        });


    }

    void dowloadThirdReportAndCallSetData(String reportUNIQUEidtoSEARCH, int modo) { //DESCRAGAMOS EL SEGUNDO REPORTE

        Log.i("secondInform", "el curren report id es " + reportUNIQUEidtoSEARCH);


        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listInformes").orderByChild("uniqueIDinformeDatsHda").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    SetInformDatsHacienda inform = ds.getValue(SetInformDatsHacienda.class);

                    if (inform != null) {
                        Variables.CurrenReportPart3 = inform;
                        break;
                    }
                }


                //AGREGMOS LA DATA EN LOS FILEDS
                setDataInfields(Variables.CurrenReportPart1, Variables.CurrenReportPart2, Variables.CurrenReportPart3);
                setDataInMoreViews(Variables.CurrenReportPart3);
                setDtaInOthersViews(Variables.CurrenReportPart1, Variables.CurrenReportPart2, Variables.CurrenReportPart3);


                if (Variables.CurrenReportPart1.getAtachControCuadroMuestreo().trim().length() > 1) {
                    //descragamos cuadro muestreo adn se data..

                    DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(Variables.CurrenReportPart1.getAtachControCuadroMuestreo());


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa", "el error es " + error.getMessage());

            }
        });


    }


    @Override
    public void onBackPressed() {
        finish();
    }


    private void dowloadReportsVinucLdsControlCalidad() {

        String idControlCalidadDowload=Variables.listIdSvINCULADOS.get(Utils.indiceControlCalidad);

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("uniqueId").equalTo(idControlCalidadDowload);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ControlCalidad user = ds.getValue(ControlCalidad.class);

                    if (user != null) {

                        Variables.listControlCalidadVinculads.add(user);

                    }   ////hay uno y e indice==1
                }

                Utils.indiceControlCalidad++;//=1  y size ==1

                if (Utils.indiceControlCalidad >= Variables.listIdSvINCULADOS.size()) {
                    String nameFilePdf = Variables.CurrenReportPart1.getNumcionContenedor()+" "+Variables.CurrenReportPart1.getProductor();
                    Log.i("PORYECTOxxx", "bien vamos a activity pdf maker el size de list control calida object vinculados es "+Variables.listControlCalidadVinculads.size());

                    int numsPriodcutsPost = cuentaProdcutosposTcosechaAndUpdateGlobaProducPost();

                    Intent intent = new Intent(ActivityContenedoresPrev.this, PdfMaker2_0.class);
                    intent.putExtra(Variables.KEY_PDF_MAKER, Variables.FormPreviewContenedores);
                    intent.putExtra(Variables.KEY_PDF_MAKER_PDF_NAME, nameFilePdf);
                    intent.putExtra(Variables.KEY_PDF_MAKER_PDF_NUM_PR_POST, numsPriodcutsPost);


                    startActivity(intent);

                }else{
                    dowloadReportsVinucLdsControlCalidad();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    private void DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(String currentIDoBJECTvinuc) {

        DatabaseReference usersdRef = RealtimeDB.rootDatabaseReference.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(currentIDoBJECTvinuc);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe = ds.getValue(CuadroMuestreo.class);
                    Log.i("holerd", "aqui se encontro un cuadro muestreo......");

                    if (informe != null  &&  Variables.CurrenReportPart3!= null)  {



                        Variables.CurrenReportPart3.setEdiRacimosRecha(String.valueOf(informe.getTotalRechazadosAll()));

                        ediRacimosRecha.setText(String.valueOf(informe.getTotalRechazadosAll()));
                        btnGENERARpdf.setEnabled(true);

                        break;
                    }


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata", "el error es  " + error.getMessage());

            }
        });

    }


    public void updateVinucladosObject() {

        TextView txtNumReportsVinclds = findViewById(R.id.txtNumReportsVinclds);

        txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculadsAll));


        if (!RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.trim().isEmpty()) { //lodecsrgamos y seteamos info

            DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

        }


    }

    public void decideaAtachReport() {

      //  scrollView2 = findViewById(R.id.scrollView2);
        scrollView2.post(new Runnable() {
            public void run() {
                scrollView2.scrollTo(0, imgAtachVinculacion.getBottom());
            }
        });





    }



/*
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMG) {
            if (resultCode == RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount();

                    int CurrentImageSelect = 0;

                    while (CurrentImageSelect < count) {
                        Uri imageuri = data.getClipData().getItemAt(CurrentImageSelect).getUri();
                        //   ImageList.add(imageuri);

                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(ActivityContenedoresPrev.this.getContentResolver(), imageuri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Bitmap bitmap=Glide.with(context).asBitmap().load(result.get(indice)).submit().get();
                        String horientacionImg = HelperImage.devuelveHorientacionImg(bitmap);


                        ImagenReport obcjImagenReport = new ImagenReport("", imageuri.toString(), currentTypeImage, Utils.getFileNameByUri(ActivityContenedoresPrev.this, imageuri), horientacionImg);
                        obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);

                        //agregamos este objeto a la lista
                        ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                        Utils.mapUris.put(obcjImagenReport.getUniqueIdNamePic(), imageuri);
                        CurrentImageSelect = CurrentImageSelect + 1;
                    }
                    // textView.setVisibility(View.VISIBLE);
                    //textView.setText("You Have Selected " + ImageList.size() + " Pictures");
                    //choose.setVisibility(View.GONE);
                }

            }

        }

    }
*/

    private int cuentaProdcutosposTcosechaAndUpdateGlobaProducPost() {
        EditText[] editextArray = {ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07,
                ediPPC08, ediPPC09, ediPPC010, ediPPC011, ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016};

        int contadorpRODUCTS = 0;

        for (EditText editext : editextArray) {

            if (!editext.getText().toString().trim().isEmpty() && editext.getText().toString().length() > 1) {
                contadorpRODUCTS++;
            }
        }


        if (ediPPC015.getText().toString().trim().length() > 1) {

            contadorpRODUCTS=contadorpRODUCTS-1;
        }
        if (ediPPC015.getText().toString().trim().length() > 0) {

            contadorpRODUCTS=contadorpRODUCTS-1;
        }






        //update productos postcosechafsdfsd
        for (int indice = 0; indice < editextArray.length; indice++) {
            EditText currentEditext = editextArray[indice];
            if (!currentEditext.getText().toString().trim().isEmpty()) { //si no esta vacioo


                switch (currentEditext.getId()) {

                    case R.id.ediPPC01:
                        Variables.currenProductPostCosecha.alumbre = currentEditext.getText().toString();
                        break;
                    case R.id.ediPPC02:
                        Variables.currenProductPostCosecha.bc100 = currentEditext.getText().toString();
                        break;

                    case R.id.ediPPC03:
                        Variables.currenProductPostCosecha.sb100 = currentEditext.getText().toString();
                        break;

                    case R.id.ediPPC04:
                        Variables.currenProductPostCosecha.eclipse = currentEditext.getText().toString();
                        break;
                    case R.id.ediPPC05:
                        Variables.currenProductPostCosecha.acido_citrico = currentEditext.getText().toString();
                        break;
                    case R.id.ediPPC06:
                        Variables.currenProductPostCosecha.biottol = currentEditext.getText().toString();
                        break;
                    case R.id.ediPPC07:
                        Variables.currenProductPostCosecha.bromorux = currentEditext.getText().toString();
                        break;
                    case R.id.ediPPC08:
                        Variables.currenProductPostCosecha.ryzuc = currentEditext.getText().toString();
                        break;

                    case R.id.ediPPC09:
                        Variables.currenProductPostCosecha.mertec = currentEditext.getText().toString();
                        break;

                    case R.id.ediPPC010:
                        Variables.currenProductPostCosecha.sastifar = currentEditext.getText().toString();
                        break;

                    case R.id.ediPPC011:
                        Variables.currenProductPostCosecha.xtrata = currentEditext.getText().toString();
                        break;


                    case R.id.ediPPC012:
                        Variables.currenProductPostCosecha.nlarge = currentEditext.getText().toString();
                        break;


                    case R.id.ediPPC013:
                        Variables.currenProductPostCosecha.gib_bex = currentEditext.getText().toString();
                        break;


                    case R.id.ediPPC014:
                        Variables.currenProductPostCosecha.cloro = currentEditext.getText().toString();
                        break;


                    case R.id.ediPPC015:
                        Variables.currenProductPostCosecha.otro_especifique = currentEditext.getText().toString();
                        Variables.currenProductPostCosecha.cantidadOtro = ediPPC016.getText().toString();

                        break;


                    case R.id.ediPPC016:
                        Variables.currenProductPostCosecha.cantidadOtro = currentEditext.getText().toString();
                        break;


                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }


        return contadorpRODUCTS;
    }

    private void getLibriadoMap(String nodeLocalizeMap) {

        ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("MapsPesoBrutoCloster2y3l").
                child(nodeLocalizeMap).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        HashMap<String, Float> mimapa = new HashMap<>();


                        for (DataSnapshot dss : dataSnapshot.getChildren()) {
                            String key = dss.getKey();

                            Float fieldData = dss.getValue(Float.class);

                            if (fieldData != null) {///
                                mimapa.put(key, fieldData);

                            }
                        }


                        Log.i("libiadod", "el sz de  libriado es " + mimapa.size());

                        setDataLibriado(mimapa);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("libiadod", "el error es " + databaseError.getMessage());


                    }
                });


    }


    void setDataLibriado(HashMap<String, Float> miMap) {

        EditText        ediMarcaCol1 = findViewById(R.id.ediMarcaCol1);
        EditText        ediMarcaCol2 = findViewById(R.id.ediMarcaCol2);
        EditText        ediMarcaCol3 = findViewById(R.id.ediMarcaCol3);
        EditText        ediMarcaCol4 = findViewById(R.id.ediMarcaCol4);
        EditText        ediMarcaCol5 = findViewById(R.id.ediMarcaCol5);


        EditText pbCluster01 = findViewById(R.id.pbCluster01);
        EditText pbCluster05 = findViewById(R.id.pbCluster05);
        EditText pbCluster03 = findViewById(R.id.pbCluster03);
        EditText pbCluster02 = findViewById(R.id.pbCluster02);
        EditText pbCluster04 = findViewById(R.id.pbCluster04);
        EditText pbCluster010 = findViewById(R.id.pbCluster010);
        EditText pbCluster09 = findViewById(R.id.pbCluster09);
        EditText pbCluster07 = findViewById(R.id.pbCluster07);
        EditText pbCluster08 = findViewById(R.id.pbCluster08);
        EditText pbCluster06 = findViewById(R.id.pbCluster06);
        EditText pbCluster011 = findViewById(R.id.pbCluster011);
        EditText pbCluster015 = findViewById(R.id.pbCluster015);
        EditText pbCluster012 = findViewById(R.id.pbCluster012);
        EditText pbCluster013 = findViewById(R.id.pbCluster013);
        EditText pbCluster014 = findViewById(R.id.pbCluster014);
        EditText pbCluster016 = findViewById(R.id.pbCluster016);
        EditText pbCluster019 = findViewById(R.id.pbCluster019);
        EditText pbCluster018 = findViewById(R.id.pbCluster018);
        EditText pbCluster020 = findViewById(R.id.pbCluster020);
        EditText pbCluster017 = findViewById(R.id.pbCluster017);
        EditText pbCluster025 = findViewById(R.id.pbCluster025);
        EditText pbCluster024 = findViewById(R.id.pbCluster024);
        EditText pbCluster023 = findViewById(R.id.pbCluster023);
        EditText pbCluster022 = findViewById(R.id.pbCluster022);
        EditText pbCluster021 = findViewById(R.id.pbCluster021);
        EditText pbCluster028 = findViewById(R.id.pbCluster028);
        EditText pbCluster027 = findViewById(R.id.pbCluster027);
        EditText pbCluster029 = findViewById(R.id.pbCluster029);
        EditText pbCluster026 = findViewById(R.id.pbCluster026);
        EditText pbCluster030 = findViewById(R.id.pbCluster030);
        EditText pbCluster034 = findViewById(R.id.pbCluster034);
        EditText pbCluster031 = findViewById(R.id.pbCluster031);
        EditText pbCluster035 = findViewById(R.id.pbCluster035);
        EditText pbCluster033 = findViewById(R.id.pbCluster033);
        EditText pbCluster032 = findViewById(R.id.pbCluster032);
        EditText pbCluster039 = findViewById(R.id.pbCluster039);
        EditText pbCluster040 = findViewById(R.id.pbCluster040);
        EditText pbCluster037 = findViewById(R.id.pbCluster037);
        EditText pbCluster038 = findViewById(R.id.pbCluster038);
        EditText pbCluster036 = findViewById(R.id.pbCluster036);
        EditText pbCluster043 = findViewById(R.id.pbCluster043);
        EditText pbCluster045 = findViewById(R.id.pbCluster045);
        EditText pbCluster042 = findViewById(R.id.pbCluster042);
        EditText pbCluster041 = findViewById(R.id.pbCluster041);
        EditText pbCluster044 = findViewById(R.id.pbCluster044);
        EditText pbCluster048 = findViewById(R.id.pbCluster048);
        EditText pbCluster046 = findViewById(R.id.pbCluster046);
        EditText pbCluster050 = findViewById(R.id.pbCluster050);
        EditText pbCluster047 = findViewById(R.id.pbCluster047);
        EditText pbCluster049 = findViewById(R.id.pbCluster049);
        EditText p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        EditText p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        EditText p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        EditText p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        EditText p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        EditText p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        EditText p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        EditText p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        EditText p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        EditText p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        EditText p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        EditText p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        EditText p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        EditText p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        EditText p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        EditText p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        EditText p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        EditText p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        EditText p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        EditText p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        EditText p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        EditText p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        EditText p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        EditText p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        EditText p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        EditText p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        EditText p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        EditText p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        EditText p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        EditText p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        EditText p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        EditText p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        EditText p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        EditText p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        EditText p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        EditText p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        EditText p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        EditText p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        EditText p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        EditText p2pbCluster036 = findViewById(R.id.p2pbCluster036);


        EditText[] miArray = {
                ediMarcaCol1,ediMarcaCol2,ediMarcaCol3,ediMarcaCol4,ediMarcaCol5,

                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024, pbCluster023, pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036
        };


        for (HashMap.Entry<String, Float> entry : miMap.entrySet()) {
            String keyHashMap = entry.getKey();
            Float value = entry.getValue();


            //buscamos en el attay de editext el que contega este id
            for (EditText edi : miArray) {

                String keyOFeditextCurrent = edi.getId() + "-" + edi.getTag();

                 Log.i("value","el value es "+keyHashMap);

                  String [] key2Guion=keyHashMap.split("-");

                if(value==9.999f && edi.getTag().equals(key2Guion[1])){
                    edi.setText(key2Guion[0]);
                    break;}



                if (keyHashMap.equals(keyOFeditextCurrent)) {
                     edi.setText(String.valueOf(value));
                    break;}



            }
            ///entonces buscamos el que contenga este


        }


    }


    HashMap<String, Float> generateMapLibriadoIfExistAndUpload(boolean isGeneratePdf) {

        Log.i("hdooerr","se llamo  generateMapLibriadoIfExistAndUpload ");

        EditText        ediMarcaCol1 = findViewById(R.id.ediMarcaCol1);
        EditText        ediMarcaCol2 = findViewById(R.id.ediMarcaCol2);
        EditText        ediMarcaCol3 = findViewById(R.id.ediMarcaCol3);
        EditText        ediMarcaCol4 = findViewById(R.id.ediMarcaCol4);
        EditText        ediMarcaCol5 = findViewById(R.id.ediMarcaCol5);


        EditText pbCluster01 = findViewById(R.id.pbCluster01);
        EditText pbCluster05 = findViewById(R.id.pbCluster05);
        EditText pbCluster03 = findViewById(R.id.pbCluster03);
        EditText pbCluster02 = findViewById(R.id.pbCluster02);
        EditText pbCluster04 = findViewById(R.id.pbCluster04);
        EditText pbCluster010 = findViewById(R.id.pbCluster010);
        EditText pbCluster09 = findViewById(R.id.pbCluster09);
        EditText pbCluster07 = findViewById(R.id.pbCluster07);
        EditText pbCluster08 = findViewById(R.id.pbCluster08);
        EditText pbCluster06 = findViewById(R.id.pbCluster06);
        EditText pbCluster011 = findViewById(R.id.pbCluster011);
        EditText pbCluster015 = findViewById(R.id.pbCluster015);
        EditText pbCluster012 = findViewById(R.id.pbCluster012);
        EditText pbCluster013 = findViewById(R.id.pbCluster013);
        EditText pbCluster014 = findViewById(R.id.pbCluster014);
        EditText pbCluster016 = findViewById(R.id.pbCluster016);
        EditText pbCluster019 = findViewById(R.id.pbCluster019);
        EditText pbCluster018 = findViewById(R.id.pbCluster018);
        EditText pbCluster020 = findViewById(R.id.pbCluster020);
        EditText pbCluster017 = findViewById(R.id.pbCluster017);
        EditText pbCluster025 = findViewById(R.id.pbCluster025);
        EditText pbCluster024 = findViewById(R.id.pbCluster024);
        EditText pbCluster023 = findViewById(R.id.pbCluster023);
        EditText pbCluster022 = findViewById(R.id.pbCluster022);
        EditText pbCluster021 = findViewById(R.id.pbCluster021);
        EditText pbCluster028 = findViewById(R.id.pbCluster028);
        EditText pbCluster027 = findViewById(R.id.pbCluster027);
        EditText pbCluster029 = findViewById(R.id.pbCluster029);
        EditText pbCluster026 = findViewById(R.id.pbCluster026);
        EditText pbCluster030 = findViewById(R.id.pbCluster030);
        EditText pbCluster034 = findViewById(R.id.pbCluster034);
        EditText pbCluster031 = findViewById(R.id.pbCluster031);
        EditText pbCluster035 = findViewById(R.id.pbCluster035);
        EditText pbCluster033 = findViewById(R.id.pbCluster033);
        EditText pbCluster032 = findViewById(R.id.pbCluster032);
        EditText pbCluster039 = findViewById(R.id.pbCluster039);
        EditText pbCluster040 = findViewById(R.id.pbCluster040);
        EditText pbCluster037 = findViewById(R.id.pbCluster037);
        EditText pbCluster038 = findViewById(R.id.pbCluster038);
        EditText pbCluster036 = findViewById(R.id.pbCluster036);
        EditText pbCluster043 = findViewById(R.id.pbCluster043);
        EditText pbCluster045 = findViewById(R.id.pbCluster045);
        EditText pbCluster042 = findViewById(R.id.pbCluster042);
        EditText pbCluster041 = findViewById(R.id.pbCluster041);
        EditText pbCluster044 = findViewById(R.id.pbCluster044);
        EditText pbCluster048 = findViewById(R.id.pbCluster048);
        EditText pbCluster046 = findViewById(R.id.pbCluster046);
        EditText pbCluster050 = findViewById(R.id.pbCluster050);
        EditText pbCluster047 = findViewById(R.id.pbCluster047);
        EditText pbCluster049 = findViewById(R.id.pbCluster049);
        EditText p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        EditText p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        EditText p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        EditText p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        EditText p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        EditText p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        EditText p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        EditText p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        EditText p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        EditText p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        EditText p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        EditText p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        EditText p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        EditText p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        EditText p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        EditText p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        EditText p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        EditText p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        EditText p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        EditText p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        EditText p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        EditText p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        EditText p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        EditText p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        EditText p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        EditText p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        EditText p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        EditText p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        EditText p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        EditText p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        EditText p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        EditText p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        EditText p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        EditText p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        EditText p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        EditText p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        EditText p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        EditText p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        EditText p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        EditText p2pbCluster036 = findViewById(R.id.p2pbCluster036);


        EditText[] miArray = {
                ediMarcaCol1,ediMarcaCol2,ediMarcaCol3,ediMarcaCol4,ediMarcaCol5,

                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024, pbCluster023, pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036

        };

        HashMap<String, Float> miMapData = new HashMap<>();


        for (EditText currentEdit : miArray) {

            if (!currentEdit.getText().toString().trim().isEmpty()) {


                 try {

                     //                       String keyCompletaObentidaBySubstring=generateKeybyString("col"+(indice+1)+"-"+"fil"+indicex,miMapData);
                     //                       String keyCompletaObentidaBySubstring=generateKeybyString("col"+(indice+1)+"-"+"fil"+indicex,miMapData);
                     //col3-fil1

                     miMapData.put(currentEdit.getId() + "-" + currentEdit.getTag() , Float.parseFloat(currentEdit.getText().toString()));


                     Log.i("hdooerr","put el key es "+currentEdit+"-"+currentEdit.getTag());



                 }

                 catch (NumberFormatException e) {

                     //                String keyOFeditextCurrent = edi.getId() + "-" + edi.getTag();

                     miMapData.put(currentEdit.getText().toString() + "-" +currentEdit.getTag() , 9.999f);

                     Log.i("hdooerr","put el key de name name es "+currentEdit.getText().toString()+"-"+currentEdit.getTag());

                 }


                Log.i("miodataxx","hay texto aqui");


            }

        }


        if (isGeneratePdf) {
            //obtenemos el p

           Utils.hashMappromedioLibriado=new HashMap<>();


               EditText [] idsKeysAndNames={ ediMarcaCol1,ediMarcaCol2,ediMarcaCol3, ediMarcaCol4, ediMarcaCol5};

               String currentKeyofMap="";

            for (int indice = 0; indice <5; indice++) { //5 columnas

                currentKeyofMap=idsKeysAndNames[indice].getText().toString()+"-"+idsKeysAndNames[indice].getId();

                /***preguntar si este nombre siempre sera unico y no cambiara..*/

                ArrayList <PromedioLibriado> listPromedioLibriado= new ArrayList<>();

                   for(int indicex=1; indicex<18; indicex++){ //iteramos las 18 filas en la columna cprrepsondiente

                       String keyCompletaObentidaBySubstring=generateKeybyString("col"+(indice+1)+"-"+"fil"+indicex,miMapData);

                       if(miMapData.containsKey(keyCompletaObentidaBySubstring)){

                           float value =miMapData.get(keyCompletaObentidaBySubstring);

                           Log.i("hdooerr","hay una valor en la columna: "+(indice+1)+" y fila"+indicex);

                           listPromedioLibriado.add(new PromedioLibriado(indicex,value));

                       }

                    }


                 if(listPromedioLibriado.size()>0){
                   Log.i("hdooerr","hay una lista aqui");

                     Utils.hashMappromedioLibriado.put(currentKeyofMap,listPromedioLibriado);

                 }

            }


        }

        return miMapData;

    }



    private String generateKeybyString(String keySearch,HashMap<String,Float > miMapData){
        String key="";

          for (HashMap.Entry<String, Float> entry : miMapData.entrySet()) {  //iteramos el mapa
              String keyHashMap = entry.getKey();
              if(keyHashMap.contains(keySearch)){
                  key=keyHashMap;
                  break;
              }
          }

          return key;

    }

    private void hideViewsIfUserISCampo(){
        TextInputEditText  ediNombreRevisa =findViewById(R.id.ediNombreRevisa);
        TextInputEditText  ediCodigoRevisa =findViewById(R.id.ediCodigoRevisa);

        if(SharePref.getQserconTipoUser()==Utils.INSPECTOR_CAMPO ||
                SharePref.getQserconTipoUser()==Utils.NO_DEFINIDO ){


            ediNombreRevisa.setVisibility(View.GONE);
            ediCodigoRevisa.setVisibility(View.GONE);
        }


        if(Variables.usuarioQserconGlobal!=null){

            if(Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_CAMPO || Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.NO_DEFINIDO){
                ediNombreRevisa.setVisibility(View.GONE);
                ediCodigoRevisa.setVisibility(View.GONE);
            }else{

                ediNombreRevisa.setVisibility(View.VISIBLE);
                ediCodigoRevisa.setVisibility(View.VISIBLE);

            }

        }


    }


    private void getExportadorasAndSetSpinner(){
        //tenemos exportadoras de prefrencias//

          SharePref.init(ActivityContenedoresPrev.this);

        hasmpaExportadoras = SharePref.getMapExpotadoras(SharePref.KEY_EXPORTADORAS);
        ArrayList<String>nombresExportadoras= new ArrayList<>();

        for(Exportadora exportadora: hasmpaExportadoras.values()){
            nombresExportadoras.add(exportadora.getNameExportadora());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresExportadoras);
        spinnerExportadora.setAdapter(arrayAdapter);



        ///vamos a descrgar desde la base de datos...

    }

    private void copiamosHere(){

        Utils. miMapCopiar.clear();
        Utils.miMapCopiar.put("semana",ediSemana.getText().toString());
        Utils.miMapCopiar.put("fecha",ediFecha.getText().toString());
        Utils.miMapCopiar.put("productor",ediProductor.getText().toString());
        Utils.miMapCopiar.put("hacienda",ediHacienda.getText().toString());
        Utils.miMapCopiar.put("codigo",ediCodigo.getText().toString());
        Utils.miMapCopiar.put("inscripcionMagap",ediInscirpMagap.getText().toString());
        Utils.miMapCopiar.put("horaDeTermino",ediHoraTermino.getText().toString());
        Utils.miMapCopiar.put("numeracionContenedor",ediNumContenedor.getText().toString());
        Utils.miMapCopiar.put("destino",ediDestino.getText().toString());
        Utils.miMapCopiar.put("vapor",ediVapor.getText().toString());


        Toast.makeText(ActivityContenedoresPrev.this, "Copiado", Toast.LENGTH_SHORT).show();


    }

    class MiTarea extends AsyncTask<List<Uri>, Void, Void> {

        @Override
        protected Void doInBackground(List<Uri>... lists) {
            List<Uri>  result = lists[0];

            for(int indice=0; indice<result.size(); indice++){

              Uri  urix = result.get(indice);
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(ActivityContenedoresPrev.this)
                            .asBitmap()
                            .load(urix)
                            .sizeMultiplier(0.6f)
                            .submit().get();
                }
                catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

              String   horientacionImg4 = HelperImage.devuelveHorientacionImg(bitmap);
                // Log.i("cuandoexecuta", "la horientacion 4 es " + horientacionImg4);

                ImagenReport obcjImagenReport =new ImagenReport("",urix.toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityContenedoresPrev.this,urix)),horientacionImg4);
                obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);

                Log.i("imagestorage", "aqui el id pertence es "+obcjImagenReport.getIdReportePerteence());


                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);
                //   PreviewCalidadCamionesyCarretas.this.getContentResolver().takePersistableUriPermission(urix, Intent.FLAG_GRANT_READ_URI_PERMISSION);


                if(ImagenReport.hashMapImagesData.size()>0){
                    Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityContenedoresPrev.this);

                }

            }

            return null;



        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            showImagesPicShotOrSelectUpdateView(false,Variables.NINGUNO);

        }
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

    public void scroollElementoFaltante(View vistFocus){

        // View targetView = findViewById(R.id.DESIRED_VIEW_ID);
        vistFocus.getParent().requestChildFocus(vistFocus,vistFocus);



    }
    private void  showToast(){

        Toast.makeText(ActivityContenedoresPrev.this, "Falta Imagen", Toast.LENGTH_SHORT).show();

    }

    private void uploadInformeToDatabase(SetInformEmbarque1 informe, SetInformEmbarque2 informe2, SetInformDatsHacienda informe3,
                                         InformRegister inform, ProductPostCosecha productos, ArrayList<ImagenReport>listImagesToUpload){

        //Agregamos un nuevo informe
        RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos
        updateCaledarioEnfunde(informe3);

        Utils.show_AND_UPLOADContenedores(ActivityContenedoresPrev.this,ActivityContenedoresPrev.this,
                informe,informe2,informe3,inform,productos,listImagesToUpload,miMapLbriado,Variables.FormPreviewContenedores,UNIQUE_ID_iNFORME);

    }

}




