package com.tiburela.qsercom.activities.formulariosPrev;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static android.view.View.GONE;

import android.Manifest;
import android.app.DatePickerDialog;
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
import android.os.Handler;
import android.os.Looper;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.PdfMaker.PdfMakerCamionesyCarretas;
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
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.PromedioLibriado;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperEditAndPreviewmode;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PreviewCalidadCamionesyCarretas extends AppCompatActivity implements View.OnClickListener  {
    ImageView imgAtachVinculacion;
    TextView txtNumReportsVinclds;
    Spinner spinnerExportadora;
    Uri urix;
    String horientacionImg4;

    boolean esprimervezEntra=true;
    ArrayList<String> listImagesToDelete = new ArrayList<>();

    MiTarea tare;
    EditText ediCandadoName1;
    EditText ediCandadoName2;
    EditText ediCandadoName3;



    ReportCamionesyCarretas objecActivityCaminsCarretas;

    TextInputEditText ediNombreRevisa;
    TextInputEditText ediCodigoRevisa;
    Button btnGENERARpdf;

    ImageView imgVAtachProcesoFrutaFinca;
    ImageView imbTakePicProcesoFrutaFinca;
  //  ImageView imgVAtachLlegadaContenedor;
   // ImageView imbTakePicLllegadaContenedor;
  //  ImageView imgVAtachSellosLlegada;
  //  ImageView imbTakePicSellosLlegada;
    // ImageView imgVAtachPuertaAbiertaContenedor;
   // ImageView imbTakePicPuertaAbiertaContenedor;
 //   ImageView imgVAtachFotosPallet;
  //  ImageView imbTakePicPallet;
    ImageView imgVAtachCierreContenedor;
    ImageView imbTakePicCierreContenedor;

    ImageView imgVAtachDocumentacionss;
    ImageView imbTakePicDocuementacionxx;

    private TextInputEditText ediExportadoraProcesada;
    private TextInputEditText ediExportadoraSolicitante;
   private TextInputEditText ediMarca;
    private TextInputEditText ediClienteNombreReporte;

    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    public static Context context;
    int contadorIterador=0;
    ProductPostCosecha productxGlobal;
    ImageView imgUpdatecAlfrutaEnfunde;

    FloatingActionButton fab2;
    private final int CODE_TWO_PERMISIONS = 12;
    LinearLayout layoutPesobrutoPorClusterSolo;

    boolean isModEdicionFields;
    HashMap<String,Float> hasmapPesoBrutoClosters2y3L;
    private int currentTypeImage=0;
    ProgressBar progressBarFormulario;

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
    TextInputEditText ediNViaje;
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;


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


    TextInputEditText ediNombreChofer;
    TextInputEditText ediCedula;
    TextInputEditText ediCelular;
    TextInputEditText ediPLaca;

    TextInputEditText ediCondicionBalanza;
    TextInputEditText ediTipodeCaja;
    TextInputEditText ediTipoPlastico;
    TextInputEditText ediTipoBalanza;
    TextInputEditText editipbalanzaRepeso;
    TextInputEditText ediUbicacionBalanza;

    TextInputEditText ediUbicacion1;
    TextInputEditText ediRuma1;
    TextInputEditText ediTermofrafo2;
    TextInputEditText ediHoraEncendido2;
    TextInputEditText ediUbicacion2;


    TextInputEditText ediExtCalid;
    TextInputEditText ediExtRodillo;
    TextInputEditText ediExtGancho;

    TextInputEditText ediExtCalidCi;
    TextInputEditText ediExtRodilloCi;
    TextInputEditText ediExtGanchoCi;

    TextInputEditText p2pbCluster01;


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
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza ;
    Spinner spinnertipodeBlanzaRepeso ;
   Spinner spinnerubicacionBalanza ;

    Spinner spFuenteAgua ;
    Spinner spFumigaCorL1 ;
    Spinner spTipoBoquilla ;


    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;
    Switch switchLavdoRacimos;
    Switch swAguaCorrida;




    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;
    ArrayList<View> listViewsClickedUser;



    @Override
    protected void onStart() {
        super.onStart();

        Auth.initAuth(PreviewCalidadCamionesyCarretas.this);
        Auth.signInAnonymously(PreviewCalidadCamionesyCarretas.this);

        RealtimeDB.initDatabasesRootOnly();



        if(hayUnformularioIcompleto){



            Map<String, ImagenReport> mapImagesReport = Utils.loadMapiMAGEData(PreviewCalidadCamionesyCarretas.this);


            ArrayList<ImagenReport> listImagesToSaVE = new ArrayList<ImagenReport>(mapImagesReport.values());


            //if el formulario no es nulo

            if(listImagesToSaVE!=null ) {

                addInfotomap(listImagesToSaVE);
                createlistsForReciclerviewsImages(listImagesToSaVE);

            }





        }



        // Check if user is signed in (non-null) and update UI accordingly.
        // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
        //  updateUI(currentUs bver)

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_calidad_camio_carret_pewv);
        context=getApplicationContext();



        TextView txtTitle=findViewById(R.id.txtTitle);
        txtTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                copiamosHere();
                return false;

            }
        });

        RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString = "";//reseteamos
        RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado = "";

        RecyclerViewAdapLinkage.mapWhitIdsCuadroMuestreo = new HashMap<>();
        RecyclerViewAdapLinkage.mapWhitIDScontrolCaldVinclds= new HashMap<>();


        Variables.copiamosData=false;

        Variables.activityCurrent=Variables.FormCamionesyCarretasActivityPreview;

        ImagenReport.hashMapImagesData=new HashMap<>();

       // hideSomeElemtosAnexosAndChangeValues();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            hayUnformularioIcompleto = extras.getBoolean("ActivitymenuKey");

            //The key argument here must match that used in the other activity
        }


        UNIQUE_ID_iNFORME=Variables.currenReportCamionesyCarretas.getUniqueIDinforme(); /// AQUI OBTENEMOS EL ID



        Log.i("latypeimage","es succces bien "+UNIQUE_ID_iNFORME);


        Auth.initAuth(this);

      //  StorageData. initStorageReference();


        findViewsIds();

        getExportadorasAndSetSpinner();


        hideViewsIfUserISCampo();


        ocultaoTherVIEWs();
        configCertainSomeViewsAliniciar();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennersSpinners();

        eventCheckdata();
        //creaFotos();
        checkModeVisualitY();

    }



    private void getExportadorasAndSetSpinner(){
        //tenemos exportadoras de prefrencias//

        Utils.hasmpaExportadoras = SharePref.getMapExpotadoras(SharePref.KEY_EXPORTADORAS);
        ArrayList<String>nombresExportadoras= new ArrayList<>();

        for(Exportadora exportadora: Utils.hasmpaExportadoras.values()){
            nombresExportadoras.add(exportadora.getNameExportadora());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nombresExportadoras);
        spinnerExportadora.setAdapter(arrayAdapter);


        ///vamos a descrgar desde la base de datos...

    }


    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(PreviewCalidadCamionesyCarretas.this,
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






                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }



                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }


    void selecionaFecha(){

        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog  picker = new DatePickerDialog(PreviewCalidadCamionesyCarretas.this,
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

      //  btnGENERARpdf.setEnabled(false);

      //   LinearLayout lyUbicacionBalanza=findViewById(R.id.lyUbicacionBalanza);
       // lyUbicacionBalanza.setVisibility(LinearLayout.GONE);



        disableEditText(ediRacimosRecha);

        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);


        disableEditText(ediContenedor);

        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);



    }

    public void scroollElementoFaltante(View vistFocus){

        // View targetView = findViewById(R.id.DESIRED_VIEW_ID);
        vistFocus.getParent().requestChildFocus(vistFocus,vistFocus);



    }
    private void  showToast(){


        Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Falta Imagen", Toast.LENGTH_SHORT).show();

    }

    boolean cehckFaltanImagenes() {


        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PROCESO_FRUTA_FINCA)){

            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotosSellosInstalados=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotosSellosInstalados.clearFocus();
            ediFotosSellosInstalados.setError(null);
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
            ediFotosSellosInstalados.setError(null);

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
            ediFotosSellosInstalados.setError(null);

        }

        return true;


    }



    private void findViewsIds( ) { //configuraremos algos views al iniciar

        ediCandadoName1=findViewById(R.id.ediCandadoName1);
        ediCandadoName2=findViewById(R.id.ediCandadoName2);
        ediCandadoName3=findViewById(R.id.ediCandadoName3);

        spinnerExportadora=findViewById(R.id.spinnerExportadora);


        txtNumReportsVinclds = findViewById(R.id.txtNumReportsVinclds);
        imgAtachVinculacion = findViewById(R.id.imgAtachVinculacion);



        ediNombreRevisa=findViewById(R.id.ediNombreRevisa);
        ediCodigoRevisa=findViewById(R.id.ediCodigoRevisa);

        btnGENERARpdf = findViewById(R.id.btnGENERARpdf);

        ediExportadoraProcesada=findViewById(R.id.ediExportadoraProcesada);
        ediExportadoraSolicitante=findViewById(R.id.ediExportadoraSolicitante);
        ediMarca=findViewById(R.id.ediMarca);
        ediClienteNombreReporte=findViewById(R.id.ediClienteNombreReporte);

        imgVAtachProcesoFrutaFinca=findViewById(R.id.imgVAtachProcesoFrutaFinca);
        imbTakePicProcesoFrutaFinca=findViewById(R.id.imbTakePicProcesoFrutaFinca);

       /*
        imgVAtachLlegadaContenedor = findViewById(R.id.imgVAtachLlegadaContenedor);
        imbTakePicLllegadaContenedor= findViewById(R.id.imbTakePicLllegadaContenedor);
        imgVAtachSellosLlegada= findViewById(R.id.imgVAtachSellosLlegada);
        imbTakePicSellosLlegada= findViewById(R.id.imbTakePicSellosLlegada);
        imgVAtachPuertaAbiertaContenedor= findViewById(R.id.imgVAtachPuertaAbiertaContenedor);
        imbTakePicPuertaAbiertaContenedor= findViewById(R.id.imbTakePicPuertaAbiertaContenedor);
        imgVAtachFotosPallet= findViewById(R.id.imgVAtachFotosPallet);
        imbTakePicPallet= findViewById(R.id.imbTakePicPallet);
       */

        imgVAtachCierreContenedor= findViewById(R.id.imgVAtachCierreContenedor);
        imgVAtachDocumentacionss = findViewById(R.id.imgVAtachDocumentacionss);
        imbTakePicCierreContenedor= findViewById(R.id.imbTakePicCierreContenedor);
        imbTakePicDocuementacionxx = findViewById(R.id.imbTakePicDocuementacionxx);


        fab2=findViewById(R.id.fab2);

        imgUpdatecAlfrutaEnfunde=findViewById(R.id.imgUpdatecAlfrutaEnfunde);
        layoutPesobrutoPorClusterSolo=findViewById(R.id.layoutPesobrutoPorClusterSolo);

        ediEmpacadora=findViewById(R.id.ediEmpacadora);
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


         ediExtCalid=findViewById(R.id.ediExtCalid);
         ediExtRodillo=findViewById(R.id.ediExtRodillo);
         ediExtGancho= findViewById(R.id.ediExtGancho);

         ediExtCalidCi=findViewById(R.id.ediExtCalidCi);
         ediExtRodilloCi=findViewById(R.id.ediExtRodilloCi);
         ediExtGanchoCi =findViewById(R.id.ediExtGanchoCi);



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

       // p2pbCluster01=findViewById(R.id.p2pbCluster01);

        ediNViaje=findViewById(R.id.ediNViaje);
        ediCondicionBalanza=findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja=findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico=findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza=findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso=findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza=findViewById(R.id.ediUbicacionBalanza);


        ediUbicacion1=findViewById(R.id.ediCod2);
        ediRuma1=findViewById(R.id.edinCajas3);
        ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
        ediUbicacion2=findViewById(R.id.ediUbicacion2);


        ediNombreChofer=findViewById(R.id.ediNombreChofer);
        ediCedula=findViewById(R.id.ediCedula);
        ediCelular=findViewById(R.id.ediCelular);
        ediPLaca=findViewById(R.id.ediPLaca);






        spinnerCondicionBalanza=  findViewById(R.id.spinnerCondicionBalanza);
        spinnertipodePlastico = findViewById(R.id.spinnertipodePlastico);
        spinnertipodeBlanza =  findViewById(R.id.spinnertipodeBlanza);
        spinnertipodeBlanzaRepeso =  findViewById(R.id.spinnertipodeBlanzaRepeso);
        spinnerubicacionBalanza =  findViewById(R.id.spinnerubicacionBalanza);

        switchHaybalanza=findViewById(R.id.switchHaybalanza);
        switchHayEnsunchado=findViewById(R.id.switchHayEnsunchado);
        switchBalanzaRep=findViewById(R.id.switchBalanzaRep);



    }


    private void addClickListeners( ) {
        imgAtachVinculacion.setOnClickListener(this);


        /**todos add a todos clicklistener de la implemntacion*/
        imgVAtachDocumentacionss.setOnClickListener(this);//ultimo
        imgVAtachProcesoFrutaFinca.setOnClickListener(this);
        imbTakePicProcesoFrutaFinca.setOnClickListener(this);

        imgVAtachCierreContenedor.setOnClickListener(this);
        imbTakePicCierreContenedor.setOnClickListener(this);
        imbTakePicDocuementacionxx.setOnClickListener(this);
        btnGENERARpdf.setOnClickListener(this);



    //    p2pbCluster01.setOnClickListener(this);

        layoutPesobrutoPorClusterSolo.setOnClickListener(this);

        imgUpdatecAlfrutaEnfunde.setOnClickListener(this);




        fab2.setOnClickListener(this);




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


        if(view.getId() ==R.id.p2pbCluster01 || view.getId() ==R.id.p2pbCluster02 || view.getId() ==R.id.p2pbCluster03|| view.getId() ==R.id.p2pbCluster04) {

            String keyCurrent=Integer.toString(view.getId());
            showBottomSheetDialog(keyCurrent);

            return ;

        }


        if(!checkPermission()){

            requestPermission();
            //   Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            // checkPermission2();

            /****por aqui pedir permisos antes **/

        }

        String data[]={"image/*"};
        Log.i("miclickimg","hemos hecho click");

        int idCurrent= view.getId();


        if(idCurrent==R.id.imgVAtachProcesoFrutaFinca || idCurrent==R.id.imgVAtachCierreContenedor ||
                idCurrent == R.id.imgVAtachDocumentacionss){ //si es atach//si es atach

            currentTypeImage=Integer.parseInt(view.getTag().toString());


            activityResultLauncher.launch("image/*");


            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);



        }

        else if(idCurrent==R.id.imbTakePicProcesoFrutaFinca
                || idCurrent==R.id.imbTakePicCierreContenedor ||
                idCurrent==R.id.imbTakePicDocuementacionxx ){  //si es tajke pic con camara

            currentTypeImage=Integer.parseInt(view.getTag().toString());


            takepickNow();

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);


        }



          else{

            switch (view.getId()) {

                case R.id.btnGENERARpdf:

                    ///cuandole da en genear obtenmos nuevamente la data


                     try {
                         getResultDatCalibCalEnfundes();

                     } catch (Exception e) {
                         throw new RuntimeException(e);
                     }


                   if(!cehckFaltanImagenes()){
                       return; //
                   }
                    ;


                    checkDataToCreatePdf();

                    //creamosel pdf con la data actual... excepto las imagenes...

                    break;


                case R.id.imgAtachVinculacion:

                    showEditDialogAndSendData();


                    break;





                case R.id.imgUpdatecAlfrutaEnfunde:
                    Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");
                    getResultDatCalibCalEnfundes();
                    break;



                case  R.id.ediFecha:
                    selecionaFecha();
                    break;



                case R.id.fab2: //si pulas en btn chekear en que modo esta ...si el modo cambia...
                    TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);

                    if(isModEdicionFields){ //si es modo edicion..
                        fab2.setImageResource(R.drawable.ic_baseline_edit_24aa);

                        txtModeAdviser.setText("Modo Visualizacion ");



                        //cambiamos al modo visualizacion
                        isModEdicionFields=false;
                        activateModePreview();


                    }else{ //SI NO ES MODO VISUZALIZACION
                        fab2.setImageResource(R.drawable.ic_baseline_preview_24jhj);
                        txtModeAdviser.setText("Modo Edicion ");

                        isModEdicionFields=true;
                        activateModeEdit();


                        //CAMABIAMOS EL MODO

                    }



                    break; //



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



                case R.id.linLayoutHeader5:
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
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);

                    selecionaFecha();

                    break; //



                case R.id.ediHoraInicio:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);

                    showingTimePicker(view);

                    break; //

                case R.id.ediHoraTermino:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //




                case R.id.ediTipoEmp2:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //

                case R.id.ediHoraEncendido2:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //




            }

        }




    }

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

    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = PreviewCalidadCamionesyCarretas.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY

    }


    ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // There are no request codes

                        try {

                            Bitmap bitmap=   HelperImage.handleSamplingAndRotationBitmap(PreviewCalidadCamionesyCarretas.this,cam_uri);
                            String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                            //creamos un nuevo objet de tipo ImagenReport
                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage, Utils.getFileNameByUri(PreviewCalidadCamionesyCarretas.this,cam_uri),horientacionImg);
                            obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);
                            showImagesPicShotOrSelectUpdateView(false,Variables.NINGUNO);


                        } catch (IOException e) {
                              e.printStackTrace();
                          }

                        Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewCalidadCamionesyCarretas.this);
                        showImagesPicShotOrSelectUpdateView(false,Variables.NINGUNO);



                    }
                }
            });

    private void resultatachImages() {


        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.GetMultipleContents(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        if (result != null) {

                            tare= new MiTarea();
                            tare.execute(result);



                        }
                      }
                });
    }



    class MiTarea extends AsyncTask<List<Uri>, Void, Void> {

        @Override
        protected Void doInBackground(List<Uri>... lists) {
            List<Uri>  result = lists[0];

            for(int indice=0; indice<result.size(); indice++){

                urix = result.get(indice);
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(PreviewCalidadCamionesyCarretas.this)
                            .asBitmap()
                            .load(urix)
                            .sizeMultiplier(0.6f)
                            .submit().get();
                }


                catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }



                horientacionImg4 = HelperImage.devuelveHorientacionImg(bitmap);
               // Log.i("cuandoexecuta", "la horientacion 4 es " + horientacionImg4);
                PreviewCalidadCamionesyCarretas.this.getContentResolver().takePersistableUriPermission(urix, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                ImagenReport obcjImagenReport =new ImagenReport("",urix.toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(PreviewCalidadCamionesyCarretas.this,urix)),horientacionImg4);
                obcjImagenReport.setIdReportePerteence(UNIQUE_ID_iNFORME);
                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                if(ImagenReport.hashMapImagesData.size()>0){
                    Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewCalidadCamionesyCarretas.this);

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


                /*
                if(textSelect.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                   // ediTipoBoquilla.setText("");
                  //  actualizaListStateView("spTipoBoquilla",false) ;
                }else {
                  //  actualizaListStateView("spTipoBoquilla",true) ;
                }
*/

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
                 //   actualizaListStateView("spFuenteAgua",false) ;
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
                 //   actualizaListStateView("spinnerZona",true) ;
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
                  ///  actualizaListStateView("ediCondicionBalanza",false) ;
                }else {
                 //   actualizaListStateView("ediCondicionBalanza",true) ;
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
                  //  actualizaListStateView("ediTipoPlastico",false) ;
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
                //    actualizaListStateView("ediTipoBalanza",true) ;
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


    private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg,int posicionionBorrar){

        //si es eliminar comprobar aqui

        if(isDeleteImg){

            currentTypeImage=Variables.typeoFdeleteImg;
        }


        Log.i("latypeimage","el currenttupe imagen es xxc "+currentTypeImage);
        ArrayList<ImagenReport> filterListImagesData=new ArrayList<ImagenReport>(); //LISTA FILTRADA QUE REPRESENTARA EL RECICLERVIEW
        RecyclerView recyclerView=null;
        RecyclerViewAdapter adapter;
        RecyclerViewAdapter aadpaterRecuperadoOFrView=null; //aqui almacenaremo
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);


        switch(currentTypeImage){

            case Variables.FOTO_PROCESO_FRUTA_FINCA:
                recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
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
                    Log.i("mispiggix", "el tipo es  " +imagenObjec.getTipoImagenCategory());
                    if(imagenObjec.getTipoImagenCategory()==currentTypeImage){
                        filterListImagesData.add(imagenObjec);
                        Log.i("mispiggi", "el size de filterListImagesData es " + filterListImagesData.size());
                    }
                }

                aadpaterRecuperadoOFrView.addItems(filterListImagesData); //le agremos los items

                aadpaterRecuperadoOFrView.notifyDataSetChanged(); //notificamos  no se si hace falta porque la clase del objeto ya lo tiene...
                Log.i("adpatertt","adpasternotiff");


            }

            else

            {
                aadpaterRecuperadoOFrView. listImagenData.remove(posicionionBorrar);
                aadpaterRecuperadoOFrView.notifyItemRemoved(posicionionBorrar);
                aadpaterRecuperadoOFrView.notifyItemRangeChanged(posicionionBorrar, aadpaterRecuperadoOFrView.listImagenData.size());
                // holder.itemView.setVisibility(View.GONE);
                Log.i("ADPATERXX","vamos a borrar EL SIZE DE ESTE ADPATER LIST despues de borrar es  "+aadpaterRecuperadoOFrView.listImagenData.size());
            }

            Log.i("adpatertt","es difrentede nulo");

        }

        /*
        else{

            Log.i("ADPATERXX","agregamos adpater");

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
        */



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


        if (!checkQueexistminim()) {
            Log.i("test001", "no esta lleno  checkDataCalibFrutaCalEnfn");

            return;
        } else {

            Log.i("test001", "si  esta lleno  checkDataCalibFrutaCalEnfn");


        }




        if(! cehckFaltanImagenes()){
            return;

        }


        if(! chekDaTaEvaluador()){
            Log.i("test001","no esta lleno  chekDaTaEvaluador");

            return;
        }else{

            Log.i("test001","si  esta lleno  chekDaTaEvaluador");


        }


        if (!Utils.checkifAtach()) {
            Log.i("test001", "no esta lleno  checkifAtach");
            FragmentManager fm = getSupportFragmentManager();
            DialogConfirmNoAtach alertDialog = DialogConfirmNoAtach.newInstance(Constants.PREV_CONTENEDORES);
            // alertDialog.setCancelable(false);
            alertDialog.show(fm, "duialoffragment_alertZ");
            return;
        }



        openBottomSheetConfirmCreateNew();





    }
    private void openBottomSheetConfirmCreateNew(){

        DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(Variables.FormCamionesyCarretasActivity);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
    }



    private void createObjcInformeAndUpdate(){

        ReportCamionesyCarretas informe = new ReportCamionesyCarretas(UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
                ediNhojaEvaluacion.getText().toString(),ediZona.getText().toString(),ediProductor.getText().toString(),
                ediCodigo.getText().toString(), ediPemarque.getText().toString(),
                 ediNguiaRemision.getText().toString(),ediHacienda.getText().toString(),edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediSemana.getText().toString(),ediEmpacadora.getText().toString(),
                ediNombreChofer.getText().toString(),ediCedula.getText().toString(),ediCelular.getText().toString(),ediPLaca.getText().toString(),
                   ediTipoPlastico.getText().toString(),ediTipodeCaja.getText().toString(),switchHayEnsunchado.isChecked(),switchHaybalanza.isChecked(),
                   ediCondicionBalanza.getText().toString(),ediTipoBalanza.getText().toString(),switchBalanzaRep.isChecked(),editipbalanzaRepeso.getText().toString(),
                ediFuenteAgua.getText().toString(),swAguaCorrida.isChecked(),switchLavdoRacimos.isChecked(),ediFumigacionClin1.getText().toString(),
                Integer.parseInt(ediRacimosCosech.getText().toString()) ,Integer.parseInt(ediRacimosRecha.getText().toString()),Integer.parseInt(ediRacimProces.getText().toString()),
                Integer .parseInt(ediCajasProcDesp.getText().toString()), ediExtCalid.getText().toString(),ediExtRodillo.getText().toString(),
                ediExtGancho.getText().toString(), ediExtCalidCi.getText().toString(),ediExtRodilloCi.getText().toString(),ediExtGanchoCi.getText().toString()
                ,FieldOpcional.observacionOpcional,Variables.currenReportCamionesyCarretas.getNodoQueContieneMapPesoBrutoCloster2y3l()

                ,ediClienteNombreReporte.getText().toString(),ediTipoBoquilla.getText().toString(),  ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString()
                ,ediMarca.getText().toString(),   ediCandadoName1.getText().toString(),ediCandadoName2.getText().toString(), ediCandadoName3.getText().toString()
        ) ;




        HashMap<String, Float> miMapLbriado = generateMapLibriadoIfExistAndUpload(false);


        Log.i("simener","el size de lista es "+miMapLbriado.size());


        String keyWhereLocaleHashMapLibriado = "";
        if (miMapLbriado.size() > 0) {
            if(!Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs().trim().isEmpty() &&
                    Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs().length()>1){

                keyWhereLocaleHashMapLibriado = Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs();

                Log.i("simener","el keyWhereLocaleHashMapLibriado ES  "+keyWhereLocaleHashMapLibriado);

            }
            else  { //pero si esta vacio
                keyWhereLocaleHashMapLibriado=RealtimeDB.rootDatabaseReference.push().getKey();
                Log.i("simener","ESTA VACIO Y AHORA ES "+keyWhereLocaleHashMapLibriado);

            }


            RealtimeDB.addNewhasmapPesoBrutoClosters2y3L(miMapLbriado, keyWhereLocaleHashMapLibriado);
        }


        Log.i("simener","el keyWhereLocaleHashMapLibriado ES AQUI ES  "+keyWhereLocaleHashMapLibriado);

        informe.setKeyOrNodeLibriadoSiEs(keyWhereLocaleHashMapLibriado);

        informe.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        informe.setAtachControCuadroMuestreo(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado); //LE BORRAMOS MASS

        //agregamos algunas propiedades unicas que ya tenia este informe omo faceha,etc
        informe.setSimpleDataFormat(Variables.currenReportCamionesyCarretas.getSimpleDataFormat());
        informe.setFechaCreacionInf(Variables.currenReportCamionesyCarretas.getFechaCreacionInf());

        RealtimeDB.updateCalidaCamionCarrretas(informe,Variables.currenReportCamionesyCarretas,PreviewCalidadCamionesyCarretas.this);



        addCalibracionFutaC_enfAndUpload(Variables.calEnfundeGLOB);
        addProdcutsPostCosechaAndUpload(); //agregamos y subimos los productos postcosecha..


    }

    HashMap<String, Float> generateMapLibriadoIfExistAndUpload(boolean isGeneratePdf) {
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

    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar

                Log.i("CLICKKATER","el TAG en activity  ES "+v.getTag().toString());
               // Variables.typeoFdeleteImg = ImagenReport.hashMapImagesData.get(v.getTag().toString()).getTipoImagenCategory();

// esed659357-2a32-4614-b4eb-9733ef5570f9.jpg
                try {

                    listImagesToDelete.add(v.getTag().toString());//agregamos ea imagen para borrarla
                     ImagenReport.hashMapImagesData.remove(v.getTag().toString());

                    Log.i("ADPATERXX","el size despues de eliminar hasmpa size es "+ ImagenReport.hashMapImagesData.size());

                    Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, PreviewCalidadCamionesyCarretas.this);
                    showImagesPicShotOrSelectUpdateView(true,position);

                    Log.i("ADPATERXX","el size despues de eliminar hasmpa size 2   es "+ ImagenReport.hashMapImagesData.size());


                }

                catch (Exception e) {
                    e.printStackTrace();
                }


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

/*
        AlertDialog.Builder builder = new AlertDialog.Builder(PreviewCalidadCamionesyCarretas.this);
        builder.setCancelable(false); // if you want user to wait for some process to finish,
        builder.setView(R.layout.layout_loading_dialog);
        AlertDialog dialog = builder.create();
        dialog.show();
*/
        Task2 tarea0 =new Task2();
        tarea0.run();






    }
    private class Task2 implements Runnable {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
       // AlertDialog  dialog;

         Task2() {
            // this.dialog=dialog;
          //   this.dialog.show(); // to show this dialog

         }


        @Override
        public void run() { //bacground
            //boorara desee aqui
            if(  !Variables.hashMapImagesStart.keySet().equals(ImagenReport.hashMapImagesData.keySet())){ //si no son iguales

                Log.i("sertila","alguno o toos son diferentes images llamaos metodo filtra");

                StorageData.counTbucle = 0; //resetemoa esta variable que sera indice en la reflexion

                ArrayList<ImagenReport> list2 = Utils.mapToArrayList(Utils.creaHahmapNoDuplicado());

                /*
                try {
                  //  StorageData.uploaddImagesAndDataImages(list2,PreviewCalidadCamionesyCarretas.this);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                */

            }


            Utils.updateImageReportObjec(); //asi actualizamos la propiedad sortPositionImage,



            runOnUiThread(new Runnable() {
                @Override
                public void run() { //ui .,hilo principal

                  //  Toast.makeText(PreviewCalidadCamionesyCarretas.this, "task2", Toast.LENGTH_SHORT).show();
                    Log.i("sertila","hermos terminado.,.");
                  //  dialog.dismiss();


                }
            });
        }




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
            int result = ContextCompat.checkSelfPermission(PreviewCalidadCamionesyCarretas.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(PreviewCalidadCamionesyCarretas.this, WRITE_EXTERNAL_STORAGE);
            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
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

        Log.i("ADPATERXX","numiages minimo encootrado en la categoria "+categoriaImagenToSearch +"  es  :"+numImagesEcontradas);


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



        if(ediExportadoraProcesada.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExportadoraProcesada.requestFocus();
            ediExportadoraProcesada.setError("Este espacio es obligatorio");
            return false;
            //obtiene el padre del padre

        }
        if(ediExportadoraSolicitante.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediExportadoraProcesada.requestFocus();
            ediExportadoraProcesada.setError("Este espacio es obligatorio");
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

        //


        return true;
    }



    private boolean  checkcantidadPostcosechaIsLleno(){
        LinearLayout layoutContainerSeccion2=findViewById(R.id.layoutContainerSeccion2);
        Log.i("camisad" ,"se llamo este ");


        if(layoutContainerSeccion2.getVisibility()== GONE){
            layoutContainerSeccion2.setVisibility(LinearLayout.VISIBLE);
        }

        if(ediPPC01.getText().toString().isEmpty() && ediPPC02.getText().toString().isEmpty()&& ediPPC03.getText().toString().isEmpty()
                && ediPPC04.getText().toString().isEmpty()&& ediPPC05.getText().toString().isEmpty()&& ediPPC06.getText().toString().isEmpty()
                && ediPPC07.getText().toString().isEmpty()&& ediPPC08.getText().toString().isEmpty() && ediPPC09.getText().toString().isEmpty()
                && ediPPC010.getText().toString().isEmpty() && ediPPC011.getText().toString().isEmpty() && ediPPC012.getText().toString().isEmpty()
                && ediPPC013.getText().toString().isEmpty() && ediPPC014.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty()
                && ediPPC016.getText().toString().isEmpty()
        ){ //chekamos que no este vacia
            ediPPC01.requestFocus();
            ediPPC01.setError("Inserte al menos 1 producto");

            Log.i("camisad" ,"se eejcuto este");

            return false;

        }



        if(! ediPPC015.getText().toString().isEmpty() && ediPPC016.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC016.requestFocus();
            ediPPC016.setError("Inserte cantidad");

            return false ;

        }


        if(! ediPPC016.getText().toString().isEmpty() && ediPPC015.getText().toString().isEmpty() ){ //chekamos que no este vacia
            ediPPC015.requestFocus();
            ediPPC015.setError("inserte nombre producto");

            return false;

        }




        return  true;

    }







    private boolean checkDatosTransportistaIsLleno(){

        LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR
        if(layoutContainerSeccion6.getVisibility()== GONE){
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);
        }



        if(ediCandadoName1.getText().toString().trim().isEmpty() && ediCandadoName2.getText().toString().trim().isEmpty()&&
                ediCandadoName3.getText().toString().trim().isEmpty()){

            ediCandadoName1.requestFocus();
            ediCandadoName1.setError("Inserte al menos un candado ");
            return false;

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

/*
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

*/


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

    private void  addCalibracionFutaC_enfAndUpload(  CalibrFrutCalEnf calibrFrutCalEnf){

        //si no chekeamos el key que contiene ycunado la decsrga

      ///  CalibrFrutCalEnf calibrFrutCalEnf=new CalibrFrutCalEnf(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        //editext here
        EditText ediColorSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10,ediColortSem9;
        EditText  ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9;
        EditText  ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9;

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

         ediPorc14 = findViewById(R.id.ediPorc14);
         ediPorc13 = findViewById(R.id.ediPorc13);
         ediPorc12 = findViewById(R.id.ediPorc12);
         ediPorc11 = findViewById(R.id.ediPorc11);
         ediPorc10 = findViewById(R.id.ediPorc10);
         ediPsgddsorc9 = findViewById(R.id.ediPorc9);


        EditText [] editextArrayColorSeman = {ediColorSem14,ediColortSem13,ediColortSem12,ediColortSem11,ediColortSem10,ediColortSem9} ;
        EditText [] editextNumRacimsArray =  {ediNumRcim14,ediNumRcim13,ediNumRcim12,ediNumRcim11,ediNumRcim10,ediNumRac9} ;
        EditText [] editextPorcentajes =  {ediPorc14,ediPorc13,ediPorc12,ediPorc11,ediPorc10,ediPsgddsorc9} ;


        for (int indice =0; indice<editextArrayColorSeman.length; indice++) {
            EditText currentEditextColorSem=editextArrayColorSeman[indice];
            EditText currentEditextNumRacims=editextNumRacimsArray[indice];

            EditText currentEditextPorcenaje=editextPorcentajes[indice];

            if (!currentEditextColorSem.getText().toString().isEmpty()){ //si no esta vacioo
                if (!currentEditextColorSem.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditextColorSem.getId()){


                        case R.id.ediColortSem14:
                            calibrFrutCalEnf.setColorSemana14(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem14(currentEditextNumRacims.getText().toString());

                            calibrFrutCalEnf.setPorc14(currentEditextPorcenaje.getText().toString());

                            break;
                        case R.id.ediColortSem13:
                            calibrFrutCalEnf.setColorSemana13(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem13(currentEditextNumRacims.getText().toString());
                            calibrFrutCalEnf.setPorc13(currentEditextPorcenaje.getText().toString());


                            break;

                        case R.id.ediColortSem12:
                            calibrFrutCalEnf.setColorSemana12(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem12(currentEditextNumRacims.getText().toString());
                            calibrFrutCalEnf.setPorc12(currentEditextPorcenaje.getText().toString());

                            break;

                        case R.id.ediColortSem11:
                            calibrFrutCalEnf.setColorSemana11(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem11(currentEditextNumRacims.getText().toString());
                            calibrFrutCalEnf.setPorc11(currentEditextPorcenaje.getText().toString());

                            break;
                        case R.id.ediColortSem10:
                            calibrFrutCalEnf.setColorSemana10(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem10(currentEditextNumRacims.getText().toString());
                            calibrFrutCalEnf.setPorc10(currentEditextPorcenaje.getText().toString());

                            break;
                        case R.id.ediColortSem9:
                            calibrFrutCalEnf.setColorSemana9(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem9(currentEditextNumRacims.getText().toString());
                            calibrFrutCalEnf.setPorc9(currentEditextPorcenaje.getText().toString());

                            break;

                    }

                }


            }
            

        }



        //actualizamos solo si ya existe un objeto descargado Variables.calEnfundeGLOB.. si es nulo ,signica que no existe

        RealtimeDB.UpdateCalibracionFrutCal(calibrFrutCalEnf);

        /*
        if(Variables.calEnfundeGLOB!=null){
            Log.i("somewrlals","es difrente de nulo  lo actualizamos Y EL KEY ES "+Variables.calEnfundeGLOB.getKeyFirebase());




        } else{
            Log.i("somewrlals","es nulo crearemos now ");

            RealtimeDB.UploadCalibracionFrutCal(calibrFrutCalEnf);


        }
*/


    }





//upload data...

    //descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad...


    void addInfotomap(ArrayList<ImagenReport>listImagenReports){
        ImagenReport.hashMapImagesData= new HashMap<>();

        //agregamos adata al mapusnado un bucle

        for(int indice2=0; indice2<listImagenReports.size(); indice2++){

            ImagenReport currentImareportObj=listImagenReports.get(indice2);

            ImagenReport.hashMapImagesData.put(currentImareportObj.getUniqueIdNamePic(),currentImareportObj);

        }



        Variables.hashMapImagesStart=new HashMap<String, ImagenReport>();



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

    void addImagesInRecyclerviews(ArrayList<ImagenReport>listImagenReports){

        //agregamos data al map
        Collections.sort(listImagenReports, new Comparator<ImagenReport>()
        {
            @Override
            public int compare(ImagenReport lhs, ImagenReport rhs) {
                return lhs.getSortPositionImage() - rhs.getSortPositionImage();

                //  return Integer.compare(lhs.getSortPositionImage(), rhs.getSortPositionImage());
            }
        });


        RecyclerView recyclerView= null;
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);



        switch(currentTypeImage){
            case Variables.FOTO_PROCESO_FRUTA_FINCA:
                recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
                break;



            case Variables.FOTO_CIERRE_CONTENEDOR:
                recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
                break;





            case Variables.FOTO_DOCUMENTACION:
                recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
                break;


        }
        RecyclerViewAdapter  adapter=new RecyclerViewAdapter(listImagenReports,this);
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



    void createlistsForReciclerviewsImages(ArrayList<ImagenReport>listImagenReports){

        ArrayList<ImagenReport>lisFiltrada;

        int []arrayTiposImagenes={Variables.FOTO_PROCESO_FRUTA_FINCA,Variables.FOTO_CIERRE_CONTENEDOR,Variables.FOTO_DOCUMENTACION};


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







    void dowLoadProducsPostC(String idAlquePERTENECE){

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


                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    productxGlobal=ds.getValue(ProductPostCosecha.class);
                }


                if (productxGlobal != null) {


                    Variables.currenProductPostCosecha = productxGlobal;
                    setProductosPostcosecha(productxGlobal);


                }



                // createlistsForReciclerviewsImages(listImagenData);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    void setProductosPostcosecha( ProductPostCosecha objProducto) {


       // Log.i("dowloadxs","el producto postocescha yea "+objProducto.);

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



    }



    void dowLoadMapPesoBrutoCloster2y3l(String nodeLocationMap) {
            ///Log.i("hameha","el NODEKey es : "+nodeKePackinGList);

            ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("MapsPesoBrutoCloster2y3l").child(nodeLocationMap).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    hasmapPesoBrutoClosters2y3L =new HashMap<>();


                    for (DataSnapshot dss : dataSnapshot.getChildren()) {
                        String key = dss.getKey();

                        Float  fieldData =dss.getValue(Float.class);


                        if (fieldData!=null) {///

                            hasmapPesoBrutoClosters2y3L.put(key,fieldData);



                        }

                        Log.i("simener","el size de lis now es "+hasmapPesoBrutoClosters2y3L.size());

                        setDataLibriado(hasmapPesoBrutoClosters2y3L);

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.i("misadhd","el error es "+ databaseError.getMessage());



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


    private TextInputEditText getTexImputEditextByidORkey( TextInputEditText[] allArrayViewsTextIMPUTe,int idViewSearch ){

        TextInputEditText textInputEditText=null;
        Log.i("midata","hay en totak "+allArrayViewsTextIMPUTe.length + "textimput editext");


        for(int indice=0; indice<allArrayViewsTextIMPUTe.length ; indice++){  //iteramos el mapa

            Log.i("midata","el id de este view es es "+allArrayViewsTextIMPUTe[indice].getId());

            if(allArrayViewsTextIMPUTe[indice].getId()==idViewSearch){


                textInputEditText= allArrayViewsTextIMPUTe[indice];
                break;
            }

        }

        return textInputEditText;
    }


    void dowloadImagesDataReport(String reportUNIQUEidtoSEARCH){ //DESCRAGAMOS EL SEGUNDO

        Log.i("dowloadxs","el reporunique id es : "+reportUNIQUEidtoSEARCH);

        RealtimeDB.initDatabasesRootOnly();
        RealtimeDB.initDatabasesReferenceImagesData();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("idReportePerteence").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ImagenReport>listImagenData=new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    ImagenReport imagenReport=ds.getValue(ImagenReport.class);

                    if(!Utils.containsName(listImagenData,imagenReport.getUniqueIdNamePic())) {
                        listImagenData.add(imagenReport);

                    }

                    Log.i("cuandoexecuta","la  img xx es "+imagenReport.getHorientacionImage());
                    Log.i("cuandoexecuta","la  url es "+imagenReport.getUrlStoragePic());


                }


                Variables.listImagenDataGlobalCurrentReport =listImagenData;
              //  dowloadAllImages2AddCallRecicler(Variables.listImagenData);


                Log.i("dowloadxs","el size de lista es "+Variables.listImagenDataGlobalCurrentReport.size());


                addInfotomap(Variables.listImagenDataGlobalCurrentReport);

                createlistsForReciclerviewsImages(Variables.listImagenDataGlobalCurrentReport);



                //este metodo lo llamaremos ahora
                //al objeto imagen report le agregaremos una propiedad llamada bitmap...o crearemos un map de bitmaps que usraemos para cargarlos desde el
                //el adpater del recicler view y asiu no alteramos el objeto imagereport...solo que ya no descragremos la imagen nuevamente...


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }

/*
    public   void dowloadAllImages2AddCallRecicler(ArrayList<ImagenReport>miLisAllImages){
        //lllamos a este metodo unicamente si la lista es 0....si no
        HelperImage.ImagesToPdfMap=new HashMap<>();

        for(int i = 0; i <miLisAllImages.size() ;i++ ){

            String pathImage =miLisAllImages.get(i).getUniqueIdNamePic();
            int categoYCurrentImg=miLisAllImages.get(i).getTipoImagenCategory();
            String uniqueId=miLisAllImages.get(i).getUniqueIdNamePic();
            String descripcionImage=miLisAllImages.get(i).getDescripcionImagen();
            StorageReference storageRef = StorageData.rootStorageReference.child("imagenes_all_reports/"+pathImage);


            try {
                final File localFile = File.createTempFile("Images", "bmp");
                storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener< FileDownloadTask.TaskSnapshot >() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                        Bitmap  bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                        ImagesToPdf imgsObect=new ImagesToPdf(horientacionImg,bitmap,categoYCurrentImg,uniqueId,descripcionImage);
                        HelperImage.imAGESpdfSetGlobal.add(imgsObect);
                        HelperImage.ImagesToPdfMap.put(uniqueId,imgsObect);

                        Log.i("hamiso","el size de la lista de Variables.listImagenData es "+Variables.listImagenData.size());
                        Log.i("hamiso","el size del map es "+HelperImage.ImagesToPdfMap.size());

                        ///llamamos a este otro metodo .......
                        contadorIterador++;
                        Log.i("hamiso","el contador iteradopr "+ contadorIterador);




                        if(contadorIterador == miLisAllImages.size() ) {

                            createlistsForReciclerviewsImages(Variables.listImagenData);
                            Log.i("hamiso","se llamokkk");
                            Utils.objsIdsDecripcionImgsMOreDescripc =new ArrayList<>();
                          //  btnGENERARpdf.setEnabled(true);

                        }



                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("hamiso","se produjo un error");

                        // Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();

            }





        }


        Log.i("hamiso","llamos a recicler create y el size de map es  "+HelperImage.ImagesToPdfMap.size());



    }
*/

    private void checkModeVisualitY(){

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            isModEdicionFields = extras.getBoolean(Variables.KEYEXTRAPREVIEW);

            Log.i("extra","el modo es "+isModEdicionFields);
            //The key argument here must match that used in the other activity
        }


        isModEdicionFields=false;

        if(isModEdicionFields){
            TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);
            activateModeEdit();
            txtModeAdviser.setText("Modo Edicion ");

            Log.i("isclkiel","es clickeable es "+ Variables.isClickable);

        }

        else

        {
            fab2.setImageResource(R.drawable.ic_baseline_edit_24aa);
            activateModePreview();
            Log.i("isclkiel","es clickeable es "+ Variables.isClickable);

        }


        Variables.modoRecicler=Variables.DOWLOAD_IMAGES;


        addDataEnFields(Variables.currenReportCamionesyCarretas);

        UNIQUE_ID_iNFORME=Variables.currenReportCamionesyCarretas.getUniqueIDinforme();

        addDataENfiledsoTHERviews(Variables.currenReportCamionesyCarretas);


        StorageData.initStorageReference();


        dowloadImagesDataReport(Variables.currenReportCamionesyCarretas.getUniqueIDinforme());

        dowLoadProducsPostC(Variables.currenReportCamionesyCarretas.getUniqueIDinforme());

        dowloadCalibracionCalendario(Variables.currenReportCamionesyCarretas.getUniqueIDinforme());



        Log.i("simener","el nodo que contiene es: "+Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs());



        if(!Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs().trim().isEmpty()){

            dowLoadMapPesoBrutoCloster2y3l((Variables.currenReportCamionesyCarretas.getKeyOrNodeLibriadoSiEs()));

        }



    }

    private void addDataEnFields(ReportCamionesyCarretas currenReport) {

        ediNombreRevisa.setText(currenReport.getNombreRevisa());
        ediCodigoRevisa.setText(currenReport.getCodigonRevisa());
        ediMarca.setText(currenReport.getMarca());

        ediExportadoraProcesada.setText(currenReport.getExportadoraProcesada());
        ediExportadoraSolicitante.setText(currenReport.getExportadoraSolicitante());

        Log.i("jamisama","la semana es "+currenReport.getSemana());
        ediClienteNombreReporte.setText(currenReport.getClienteReporte());
        ediSemana.setText(currenReport.getSemana());
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(currenReport.getFechaCreacionInf());
        ediFecha.setText(fechaString);

        ediProductor.setText(currenReport.getProductor());
        ediHacienda.setText(currenReport.getHacienda());
        ediCodigo.setText(currenReport.getCodigo());
        ediCandadoName1.setText(currenReport.getCandadoName1());
        ediCandadoName2.setText(currenReport.getCandadoName2());
        ediCandadoName3.setText(currenReport.getCandadoName3());



        ediInscirpMagap.setText(currenReport.getInscirpMagap());
        ediPemarque.setText(currenReport.getPemarque());
        ediZona.setText(currenReport.getZona());

        ediTipoBoquilla.setText(currenReport.getTipoBoquilla());

        ediHoraInicio.setText(currenReport.getHoraInicio());

        ediHoraTermino.setText(currenReport.getHoraTermino());

        ediNguiaRemision.setText(currenReport.getNguiaRemision());
        edi_nguia_transporte.setText(currenReport.get_nguia_transporte());
        ediNtargetaEmbarque.setText(currenReport.getNtargetaEmbarque());
        ediNhojaEvaluacion.setText(currenReport.getEdiNhojaEvaluacion());
        ediObservacion.setText(currenReport.getObservacionOpc());
        ediEmpacadora.setText(currenReport.getEmpacadora());
        ediContenedor.setText(currenReport.getContenedor());

        /*faltan los prouctos postcosecha agregamos usando un for**/

          ediPLaca.setText(currenReport.getPlaca());




        ediNombreChofer.setText(currenReport.getNombredeChofer());
        ediCedula.setText(String.valueOf(currenReport.getCedula()));
        ediCelular.setText(String.valueOf(currenReport.getCelular()));

        ediCondicionBalanza.setText(currenReport.getCondicionBalanza());
        ediTipodeCaja.setText(currenReport.getTipoDeCaja());
        ediTipoPlastico.setText(currenReport.getTipoDePlastico());
        ediTipoBalanza.setText(currenReport.getTipoBalanza());
        editipbalanzaRepeso.setText(currenReport.getTipoBalanzaRepesa());


        //configuramos los ultimos datos
        ediExtCalid.setText(currenReport.getExtensionistaEnCalidad());
        ediExtGancho.setText(currenReport.getExtensionistaEnGancho());
        ediExtRodillo.setText(currenReport.getExtensionistaEnRodillo());

        ediExtCalidCi.setText(currenReport.getCalidadCi());

      //  Log.i("misganas","el extensionista ci es "+//cure);

        ediExtGanchoCi.setText(currenReport.getGanchoCi());
        ediExtRodilloCi.setText(currenReport.getExtRodilloCi());


        //control de gancho

        ediCajasProcDesp.setText(String.valueOf(currenReport.getCajasProcesadasDespachadas()));
        ediRacimosCosech.setText(String.valueOf(currenReport.getRacimosCosechados()));
        ediRacimosRecha.setText(String.valueOf(currenReport.getRacimosRechazados()));
        ediRacimProces.setText(String.valueOf(currenReport.getRacimosProcesados()));


        /**inicilizamos variable global Utils.numReportsVinculadsAll ,map de vinuclads,etc */
        Utils.initializeAndGETnuMSvinuclads(currenReport.getAtachControCalidadInfrms(), currenReport.getAtachControCuadroMuestreo());

        txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculadsAll));



    }


    private  void addDataENfiledsoTHERviews(ReportCamionesyCarretas info1Object) {


        selectValue(spinnerExportadora,info1Object.getExportadoraProcesada()) ;


        Log.i("mizonasss","la exportadora procesada  es  "+ info1Object.getExportadoraProcesada());



        Log.i("mizona","la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  "+info1Object.getZona());

        selectValue(spinnerSelectZona,info1Object.getZona()) ;

        selectValue(spTipoBoquilla,info1Object.getTipoBoquilla()) ;

      //  Log.i("mizona","la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  "+info1Object.ge);


        selectValue(spinnerCondicionBalanza,info1Object.getCondicionBalanza()) ;
        selectValue(spinnertipodePlastico,info1Object.getTipoDePlastico()) ;
        selectValue(spinnertipodeBlanza,info1Object.getTipoBalanza()) ;
        selectValue(spinnertipodeBlanzaRepeso,info1Object.getTipoBalanzaRepesa()) ;
        //selectValue(spinnerubicacionBalanza,info1Object.getUbicacionBalanza()) ;
        selectValue(spFuenteAgua,info1Object.getFuenteAgua()) ;

        selectValue(spFuenteAgua,info1Object.getFuenteAgua()) ;
        selectValue(spFumigaCorL1,info1Object.getFumigacionClin1()) ;
      //  selectValue(spTipoBoquilla,info1Object.getT()) ;


        switchHaybalanza.setChecked(info1Object.isHayBalanza());
        switchHayEnsunchado.setChecked(info1Object.isHayEnsunchado());
        switchBalanzaRep.setChecked(info1Object.isHayBalanzaRepesa());

        swAguaCorrida.setChecked(info1Object.isHayAguaCorrida());
        switchLavdoRacimos.setChecked(info1Object.isLavadoRacimos());


    }

    private void selectValue(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {


            if (spinner.getItemAtPosition(i).toString().toUpperCase().equals(value)) {
                spinner.setSelection(i);
                Log.i("mizona","existe hurra"+value);
                break;

            }else

            {

                Log.i("mizona","no exiwste "+value);

            }
        }

    }


    private void activateModePreview() {
        //Cremoas un super array list de views...
        Variables.isClickable = false;

        //cremoas un super array...de todo tipos de Views

        View [] arrayAllFields={ediCandadoName1,ediCandadoName2,ediCandadoName3,   ediMarca,ediExportadoraSolicitante,ediClienteNombreReporte,
                ediSemana, ediFecha, ediProductor, ediHacienda, ediCodigo, ediInscirpMagap, ediPemarque, ediZona, ediHoraInicio, ediHoraTermino,
                ediNguiaRemision, edi_nguia_transporte, ediNtargetaEmbarque, ediNhojaEvaluacion, ediObservacion, ediEmpacadora,
                ediContenedor, ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07, ediPPC08, ediPPC09, ediPPC010, ediPPC011,
                ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016, ediEnsunchado, ediBalanzaRepeso,
                ediBalanza, ediFuenteAgua, ediAguaCorrida, ediLavadoRacimos, ediFumigacionClin1, ediTipoBoquilla, ediCajasProcDesp, ediRacimosCosech,
                ediRacimosRecha, ediRacimProces, ediNombreChofer, ediCedula, ediCelular, ediPLaca, ediCondicionBalanza,
                ediTipodeCaja, ediTipoPlastico, ediTipoBalanza, editipbalanzaRepeso, ediExtCalid, ediExtRodillo,
                ediExtGancho, ediExtCalidCi, ediExtRodilloCi, ediExtGanchoCi, spinnerSelectZona, spinnerCondicionBalanza,
                spinnertipodePlastico, spinnertipodeBlanza , spinnertipodeBlanzaRepeso  , spFuenteAgua ,
                spFumigaCorL1 , spTipoBoquilla , switchHaybalanza, switchHayEnsunchado, switchBalanzaRep,
                switchLavdoRacimos, swAguaCorrida

        };

    HelperEditAndPreviewmode.diseableViewsByTipe(arrayAllFields);




    }

    private void activateModeEdit() {
        Variables.isClickable = true;


        View [] arrayAllFields={ediCandadoName1,ediCandadoName2,ediCandadoName3,

                ediMarca, ediExportadoraSolicitante,ediClienteNombreReporte,

                ediSemana, ediFecha, ediProductor, ediHacienda, ediCodigo, ediInscirpMagap, ediPemarque, ediZona, ediHoraInicio, ediHoraTermino,
                ediNguiaRemision, edi_nguia_transporte, ediNtargetaEmbarque, ediNhojaEvaluacion, ediObservacion, ediEmpacadora,
                ediContenedor, ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07, ediPPC08, ediPPC09, ediPPC010, ediPPC011,
                ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016, ediEnsunchado, ediBalanzaRepeso,
                   ediCajasProcDesp, ediRacimosCosech,
                ediRacimosRecha, ediRacimProces, ediNombreChofer, ediCedula, ediCelular, ediPLaca,
                    ediExtCalid, ediExtRodillo,
                ediExtGancho, ediExtCalidCi, ediExtRodilloCi, ediExtGanchoCi, spinnerSelectZona, spinnerCondicionBalanza,
                spinnertipodePlastico, spinnertipodeBlanza , spinnertipodeBlanzaRepeso  , spFuenteAgua ,
                spFumigaCorL1 , spTipoBoquilla , switchHaybalanza, switchHayEnsunchado, switchBalanzaRep,
                switchLavdoRacimos, swAguaCorrida

        };

        HelperEditAndPreviewmode.activateViewsByTypeView(arrayAllFields);


        //igual usamos un super array list de views....
    }

    private void showBottomSheetDialog(String  keyCurrentView) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(PreviewCalidadCamionesyCarretas.this);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_selector);

        //NumberPicker numberPicker1=bottomSheetDialog.findViewById(R.id.numberPicker1);
       // NumberPicker numberPicker2=bottomSheetDialog.findViewById(R.id.numberPicker2);
      //  numberPicker1.setMaxValue(5);

      //  numberPicker2.setMaxValue(10);

       //crenado number poicker



        Button  buttonSelecionar=bottomSheetDialog.findViewById(R.id.buttonSelecionar);



        buttonSelecionar.setOnClickListener(new View.OnClickListener() { //editar
            @Override
            public void onClick(View v) {

                //aqui ya obtendremos el peso..

                bottomSheetDialog.dismiss();


            }
        });

        bottomSheetDialog.show();
    }
    private void updatePostionImegesSortOnlyNewsImages(){

        RecyclerView recyclerView=null;
        recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
        Utils.updatePositionObjectImagenReport(recyclerView);

        recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
        Utils.updatePositionObjectImagenReport(recyclerView);

        recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
        Utils.updatePositionObjectImagenReport(recyclerView);



    }

    ///peso bruto por clsters
private void setCalibrCalEndInViews(CalibrFrutCalEnf currentObject){


    EditText ediColorSem14=findViewById(R.id.ediColortSem14);
    EditText ediColorSem13=findViewById(R.id.ediColortSem13);
    EditText ediColorSem12=findViewById(R.id.ediColortSem12);
    EditText ediColorSem11=findViewById(R.id.ediColortSem11);
    EditText ediColorSem10=findViewById(R.id.ediColortSem10);
    EditText ediColorSem9=findViewById(R.id.ediColortSem9);

    EditText ediNumRcim14=findViewById(R.id.ediNumRcim14);
    EditText ediNumRcim13=findViewById(R.id.ediNumRcim13);
    EditText ediNumRcim12=findViewById(R.id.ediNumRcim12);
    EditText ediNumRcim11=findViewById(R.id.ediNumRcim11);
    EditText ediNumRcim10=findViewById(R.id.ediNumRcim10);
    EditText ediNumRac9=findViewById(R.id.ediNumRac9);

    EditText ediPorc14 = findViewById(R.id.ediPorc14);
    EditText ediPorc13 = findViewById(R.id.ediPorc13);
    EditText ediPorc12 = findViewById(R.id.ediPorc12);
    EditText ediPorc11 = findViewById(R.id.ediPorc11);
    EditText ediPorc10 = findViewById(R.id.ediPorc10);
    EditText ediPsgddsorc9 = findViewById(R.id.ediPorc9);


    ediColorSem14.setText(currentObject.getColorSemana14());
    ediColorSem13.setText(currentObject.getColorSemana13());
    ediColorSem12.setText(currentObject.getColorSemana12());
    ediColorSem11.setText(currentObject.getColorSemana11());
    ediColorSem10.setText(currentObject.getColorSemana10());
    ediColorSem9.setText(currentObject.getColorSemana9());



    ediPorc14.setText(currentObject.getPorc14());
    ediPorc13.setText(currentObject.getPorc13());
    ediPorc12.setText(currentObject.getPorc12());
    ediPorc11.setText(currentObject.getPorc11());
    ediPorc10.setText(currentObject.getPorc10());
    ediPsgddsorc9.setText(currentObject.getPorc9());


    ediNumRcim14.setText(currentObject.getNumeracionRacimosSem14());

        ediNumRcim13.setText(currentObject.getNumeracionRacimosSem13());


        ediNumRcim12.setText( currentObject.getNumeracionRacimosSem12());

        ediNumRcim11.setText( currentObject.getNumeracionRacimosSem11());


        ediNumRcim10.setText(currentObject.getNumeracionRacimosSem10() );


        ediNumRac9.setText(currentObject.getNumeracionRacimosSem9());



}



    void dowloadCalibracionCalendario(String idAlquePERTENECE){
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").
                child("listCalibracionFtutsCal").
                orderByChild("idPertenece").equalTo(idAlquePERTENECE);


        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Map<String, Object> map = null;
                //  Map<String, String> map = dataSnapshot.getValue(Map.class);
                //  Log.i("sliexsa","el size de map es "+map.size());


                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Variables.calEnfundeGLOB=ds.getValue(CalibrFrutCalEnf.class);
                }



                if(Variables.calEnfundeGLOB!=null){

                    Log.i("somewrlals","es difrente de nulo");

                    setCalibrCalEndInViews(Variables.calEnfundeGLOB);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }



    public void saveInfo() {

        updatePostionImegesSortOnlyNewsImages();
        RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

        uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

        for (int i = 0; i < listImagesToDelete.size(); i++) {

            geTidAndDelete(listImagesToDelete.get(i));

        }


        createObjcInformeAndUpdate(); //CREAMOS LOS IN

    }
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

                                Log.i("eliminamos","aqui se elimino esto");

                                //Toast.makeText(OfertsAdminActivity.this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });

                } catch (Exception e) {
                    Log.i("eliminamos","aqui hay una expecion y es "+e.getMessage());

                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    void ocultaoTherVIEWs(){

        btnGENERARpdf.setEnabled(false);
        btnGENERARpdf.setVisibility(View.GONE);

        if(Variables.usuarioQserconGlobal!=null && Variables.usuarioQserconGlobal.isUserISaprobadp() && Variables.usuarioQserconGlobal.getTiposUSUARI()==Utils.INSPECTOR_OFICINA){

            btnGENERARpdf.setEnabled(true);
            btnGENERARpdf.setVisibility(View.VISIBLE);

        }


        // ediMarca.setVisibility(View.GONE);
        ediUbicacionBalanza.setVisibility(View.GONE);
        spinnerubicacionBalanza.setVisibility(View.GONE);


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
        float resultpercente;
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
                    resultpercente= (Float.parseFloat(miArrayNUmrACIMOS[i].getText().toString())/numRacimosCosechados)*100;

                    String promDecim=format.format(resultpercente)   ;

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


        if (!checkDatosGeneralesIsLleno()) {

            Log.i("test001", "no esta lleno  checkDatosGeneralesIsLleno");
            return;
        }


        if (!checkcantidadPostcosechaIsLleno()) {
            Log.i("test001", "no esta lleno  checkcantidadPostcosechaIsLleno");
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

        if (!getResultDatCalibCalEnfundes()) {
            Log.i("test001", "no esta lleno  getResultDatCalibCalEnfundes");

            return;
        } else {

            Log.i("test001", "si  esta lleno  getResultDatCalibCalEnfundes");


        }


        if (!checkExisteMiumReportsVINCULADOS()) {
            Log.i("test001", "no esta lleno  cehckExisteMiumReportsVINCULADOSx");
            return;
        }


        updatePostionImegesSortOnlyNewsImages();




        Log.i("test001", "se eejcuto esto tambienx");


        generateMapLibriadoIfExistAndUpload(true);


        updateInformeWhitCurrentDataOfViews();

        updaTeProductsPostCosecha(); //actualizamos estetambien


        Variables.currenReportCamionesyCarretas.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);


        if (RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.trim().length()==0){

            Log.i("atachxxc","es cero en idsFormsVinucladosControlCalidadString");

            Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Agrega al menos un reporte control calidad", Toast.LENGTH_LONG).show();
            return;

        }

        if (RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.trim().length()==0){

            Log.i("atachxxc","es cero en idCudroMuestreoStringVinuclado");

            Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Agrega al menos un reporte cuadro muestreo", Toast.LENGTH_LONG).show();
            return;
        }



        DowloadControlcalidadVinculadosandDecideIRpdfMAKER(Variables.currenReportCamionesyCarretas.getAtachControCalidadInfrms());


    }
    private void updateInformeWhitCurrentDataOfViews() {

        String atachViucladoControlCalidad = Variables.currenReportCamionesyCarretas.getAtachControCalidadInfrms();
        String atachViucladoCuadroX = Variables.currenReportCamionesyCarretas.getAtachControCuadroMuestreo();



        Variables.currenReportCamionesyCarretas= new ReportCamionesyCarretas(UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
                ediNhojaEvaluacion.getText().toString(),ediZona.getText().toString(),ediProductor.getText().toString(),
                ediCodigo.getText().toString(), ediPemarque.getText().toString(),
                ediNguiaRemision.getText().toString(),ediHacienda.getText().toString(),edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediSemana.getText().toString(),ediEmpacadora.getText().toString(),
                ediNombreChofer.getText().toString(),ediCedula.getText().toString(),ediCelular.getText().toString(),ediPLaca.getText().toString(),
                ediTipoPlastico.getText().toString(),ediTipodeCaja.getText().toString(),switchHayEnsunchado.isChecked(),switchHaybalanza.isChecked(),
                ediCondicionBalanza.getText().toString(),ediTipoBalanza.getText().toString(),switchBalanzaRep.isChecked(),editipbalanzaRepeso.getText().toString(),
                ediFuenteAgua.getText().toString(),swAguaCorrida.isChecked(),switchLavdoRacimos.isChecked(),ediFumigacionClin1.getText().toString(),
                Integer.parseInt(ediRacimosCosech.getText().toString()) ,Integer.parseInt(ediRacimosRecha.getText().toString()),Integer.parseInt(ediRacimProces.getText().toString()),
                Integer .parseInt(ediCajasProcDesp.getText().toString()), ediExtCalid.getText().toString(),ediExtRodillo.getText().toString(),
                ediExtGancho.getText().toString(), ediExtCalidCi.getText().toString(),ediExtRodilloCi.getText().toString(),ediExtGanchoCi.getText().toString()
                ,FieldOpcional.observacionOpcional,Variables.currenReportCamionesyCarretas.getNodoQueContieneMapPesoBrutoCloster2y3l()

                ,ediClienteNombreReporte.getText().toString(),ediTipoBoquilla.getText().toString(),  ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString()
                ,ediMarca.getText().toString(),   ediCandadoName1.getText().toString(),ediCandadoName2.getText().toString(), ediCandadoName3.getText().toString()  );


        Log.i("atachxxc","el key firebase es "+Variables.currenReportCamionesyCarretas.getKeyFirebase());


      //  Variables.currenReportCamionesyCarretas.setKeyFirebase(Variables.currenReportCamionesyCarretas.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto
        Variables.currenReportCamionesyCarretas.setAtachControCalidadInfrms(atachViucladoControlCalidad);
        Variables.currenReportCamionesyCarretas.setAtachControCuadroMuestreo(atachViucladoCuadroX);


        Variables.currenReportCamionesyCarretas.setNombreRevisa(ediNombreRevisa.getText().toString());
        Variables.currenReportCamionesyCarretas.setCodigonRevisa(ediCodigoRevisa.getText().toString());




        if (RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.length() > 0) {
            Variables.currenReportCamionesyCarretas.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        }


        if (RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.length() > 0) {
            Variables.currenReportCamionesyCarretas.setAtachControCuadroMuestreo(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

        }


        Log.i("eldtatashd", "el string atch es " + RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);


            ////CONVERTIMOS A SIMPLE DATE FORMAT
            Format formatter = new SimpleDateFormat("dd-MM-yyyy");
            String fechaString = formatter.format(Variables.currenReportCamionesyCarretas.getFechaCreacionInf());
            Variables.currenReportCamionesyCarretas.setSimpleDataFormat(fechaString);
            Variables.currenReportCamionesyCarretas.setFechaCreacionInf(Variables.currenReportCamionesyCarretas.getFechaCreacionInf());



        updateCaledarioEnfunde(Variables.calEnfundeGLOB);


    }

    private boolean updateCaledarioEnfunde(CalibrFrutCalEnf informe) {

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
                        informe.setColorSemana14(value);
                        break;

                    case R.id.ediColortSem13:
                        informe.setColorSemana13(value);
                        break;

                    case R.id.ediColortSem12:
                        informe.setColorSemana12(value);
                        break;


                    case R.id.ediColortSem11:
                        informe.setColorSemana11(value);
                        break;


                    case R.id.ediColortSem10:
                        informe.setColorSemana10(value);

                        break;


                    case R.id.ediColortSem9:
                        informe.setColorSemana9(value);

                        break;


                    case R.id.ediNumRcim14:
                        informe.setNumeracionRacimosSem14(value);
                        break;


                    case R.id.ediNumRcim13:

                        informe.setNumeracionRacimosSem13(value);
                        break;

                    case R.id.ediNumRcim12:
                        informe.setNumeracionRacimosSem12(value);
                        break;


                    case R.id.ediNumRcim11:
                        informe.setNumeracionRacimosSem11(value);
                        break;

                    case R.id.ediNumRcim10:
                        informe.setNumeracionRacimosSem11(value);
                        break;

                    case R.id.ediNumRac9:
                        informe.setNumeracionRacimosSem11(value);
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


    private void updaTeProductsPostCosecha() {

        productxGlobal = new ProductPostCosecha(UNIQUE_ID_iNFORME);
        //creamos un array de editext
        productxGlobal.keyFirebase = productxGlobal.keyFirebase;

        EditText[] editextArray = {ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07,
                ediPPC08, ediPPC09, ediPPC010, ediPPC011, ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016};


        for (int indice = 0; indice < editextArray.length; indice++) {
            EditText currentEditext = editextArray[indice];
            if (!currentEditext.getText().toString().isEmpty()) { //si no esta vacioo
                if (!currentEditext.getText().toString().trim().isEmpty())  //si no es un espacio vacio
                {

                    switch (currentEditext.getId()) {

                        case R.id.ediPPC01:
                            productxGlobal.alumbre = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC02:
                            productxGlobal.bc100 = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC03:
                            productxGlobal.sb100 = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC04:
                            productxGlobal.eclipse = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC05:
                            productxGlobal.acido_citrico = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC06:
                            productxGlobal.biottol = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC07:
                            productxGlobal.bromorux = currentEditext.getText().toString();
                            break;
                        case R.id.ediPPC08:
                            productxGlobal.ryzuc = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC09:
                            productxGlobal.mertec = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC010:
                            productxGlobal.sastifar = currentEditext.getText().toString();
                            break;

                        case R.id.ediPPC011:
                            productxGlobal.xtrata = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC012:
                            productxGlobal.nlarge = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC013:
                            productxGlobal.gib_bex = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC014:
                            productxGlobal.cloro = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC015:
                            productxGlobal.otro_especifique = currentEditext.getText().toString();
                            break;


                        case R.id.ediPPC016:
                            productxGlobal.cantidadOtro = currentEditext.getText().toString();
                            break;


                    }

                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }


    }

    private void DowloadControlcalidadVinculadosandDecideIRpdfMAKER(String valueVinculds) {

        // Utils.generateLISTbyStringVinculados
        ArrayList<String> listIdSvINCULADOS = Utils.generateLISTbyStringVinculados(valueVinculds, "");

        if (listIdSvINCULADOS.size() > 0) {  //si existen vinuclados DESCRAGAMOS los informes viculados usando los ids uniqe id

            //  showReportsAndSelectOrDeleteVinuclados(ActivityContenedoresPrev.this,true);
            int contadorx = 0;

            for (String value : listIdSvINCULADOS) {
                contadorx++;

                Log.i("comnadaer", "se ejecuto esto veces, buscamos este " + value);

                dowloadReportsVinucLADSAndGOcREATEpdf(value, contadorx, listIdSvINCULADOS.size());

            }
        } else {

            Toast.makeText(PreviewCalidadCamionesyCarretas.this, "No Hay reportes vinculados ", Toast.LENGTH_SHORT).show();

        }

    }

    private void dowloadReportsVinucLADSAndGOcREATEpdf(String reportidToSearch, int contador, int sizeListIterate) {

        Variables.listReprsVinculads = new ArrayList<>();

        Log.i("salero", "bsucando este reporte con este id  " + reportidToSearch);


        RealtimeDB.initDatabasesRootOnly();

        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("listControCalidad").orderByChild("uniqueId").equalTo(reportidToSearch);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ControlCalidad user = ds.getValue(ControlCalidad.class);

                    if (user != null) {

                        Variables.listReprsVinculads.add(user);

                    }
                }

                if (sizeListIterate == contador) {


                    //String nameFilePdf = Variables.currenReportCamionesyCarretas.getNumcionContenedor()+" "+Variables.currenReportCamionesyCarretas.getProductor();

                  String [] dateCreate=Variables.currenReportCamionesyCarretas.getSimpleDataFormat().split("-");

                    String nameFilePdf=""+dateCreate[0]+"_"+dateCreate[1]+" "+Variables.currenReportCamionesyCarretas.getProductor();


                    Log.i("comnadaer", "bien vamos a activity pdf maker");


                    int numsPriodcutsPost = cuentaProdcutosposTcosechaAndUpdateGlobaProducPost();

                    Intent intent = new Intent(PreviewCalidadCamionesyCarretas.this, PdfMakerCamionesyCarretas.class);
                    intent.putExtra(Variables.KEY_PDF_MAKER, Variables.FormPreviewContenedores);
                    intent.putExtra(Variables.KEY_PDF_MAKER_PDF_NAME, nameFilePdf);
                    intent.putExtra(Variables.KEY_PDF_MAKER_PDF_NUM_PR_POST, numsPriodcutsPost);


                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void decideaAtachReport(boolean userSelecion) {


        if (userSelecion) { //SELECIONO ATCH
            Log.i("test001", " seleciono 200");

            ScrollView scrollView2 = findViewById(R.id.scrollView2);

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

    private int cuentaProdcutosposTcosechaAndUpdateGlobaProducPost() {
        EditText[] editextArray = {ediPPC01, ediPPC02, ediPPC03, ediPPC04, ediPPC05, ediPPC06, ediPPC07,
                ediPPC08, ediPPC09, ediPPC010, ediPPC011, ediPPC012, ediPPC013, ediPPC014, ediPPC015, ediPPC016};

        int contadorpRODUCTS = 0;

        for (EditText editext : editextArray) {

            if (!editext.getText().toString().trim().isEmpty() && editext.getText().toString().length() > 1) {

                contadorpRODUCTS++;
            }


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

    private boolean checkExisteMiumReportsVINCULADOS() {
        //   int contadroInformsControCalidad=0;
        //  int contadroInformsCuadroMuetreo=0;


        //  String [] allInformCuadroMuetreo =RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.split(",");
        /// String [] allInformcONTROLcALIDA=RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.split(",");


        if (RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.trim().isEmpty()) {
            Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Agrega al menos un reporte Cuadro de muestreo", Toast.LENGTH_LONG).show();
            return false;

        }


        if (RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString.trim().isEmpty()) {
            Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Agrega al menos un reporte Control calidad", Toast.LENGTH_LONG).show();

            return false;

        }


        return true;
    }

    private void showEditDialogAndSendData() {


        Log.i("cinuoados","el id vinuclados es "+RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);

        Bundle bundle = new Bundle();
        bundle.putString(Variables.KEY_CONTROL_CALIDAD_ATACHEDS, RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        bundle.putString(Variables.KEY_CUADRO_MUETREO_ATACHED, RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);


        FragmentManager fm = getSupportFragmentManager();
        BottonSheetDfragmentVclds alertDialog = BottonSheetDfragmentVclds.newInstance(Constants.PREV_CAMIONES_Y_CARRETAS);
        // alertDialog.setCancelable(false);

        alertDialog.setArguments(bundle);
        alertDialog.show(fm, "duialoffragment_alert");


    }

    public void updateVinucladosObject() {

        TextView txtNumReportsVinclds = findViewById(R.id.txtNumReportsVinclds);

        txtNumReportsVinclds.setText(String.valueOf(Utils.numReportsVinculadsAll));


        if (!RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado.trim().isEmpty()) { //lodecsrgamos y seteamos info

            DowloadUniqeuRechazadosObjectCUADROMuestreoAndSetNumRechzados(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);

        }


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

                    if (informe != null) {

                        Variables.currenReportCamionesyCarretas.setRacimosRechazados(informe.getTotalRechazadosAll());

                        ediRacimosRecha.setText(String.valueOf(informe.getTotalRechazadosAll()));
                      //  btnGENERARpdf.setEnabled(true);

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

    private void copiamosHere(){

        Utils. miMapCopiar.clear();
        Utils.miMapCopiar.put("semana",ediSemana.getText().toString());
        Utils.miMapCopiar.put("fecha",ediFecha.getText().toString());
        Utils.miMapCopiar.put("productor",ediProductor.getText().toString());
        Utils.miMapCopiar.put("hacienda",ediHacienda.getText().toString());
        Utils.miMapCopiar.put("codigo",ediCodigo.getText().toString());
        Utils.miMapCopiar.put("inscripcionMagap",ediInscirpMagap.getText().toString());
        Utils.miMapCopiar.put("horaDeTermino",ediHoraTermino.getText().toString());
        //   Utils.miMapCopiar.put("numeracionContenedor",ediNumContenedor.getText().toString());

        /// Utils.miMapCopiar.put("vapor",edivapo.getText().toString());

        Toast.makeText(PreviewCalidadCamionesyCarretas.this, "Copiado", Toast.LENGTH_SHORT).show();


    }

}
