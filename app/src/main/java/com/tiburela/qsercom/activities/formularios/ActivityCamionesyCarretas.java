package com.tiburela.qsercom.activities.formularios;

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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import com.tiburela.qsercom.models.CalibrFrutCalEnf;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.Exportadora;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.ReportCamionesyCarretas;
import com.tiburela.qsercom.storage.StorageDataAndRdB;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.tiburela.qsercom.R;


public class ActivityCamionesyCarretas extends AppCompatActivity implements View.OnClickListener, CallbackUploadNewReport {
    public static CallbackUploadNewReport callbackUploadNewReport;
    Spinner spinnerExportadora;
    String currentKeySharePrefrences="";
    boolean userCreoRegisterForm=false;
    ImageView imgAtachVinculacion;
    Uri urix;
    String horientacionImg4;

    boolean seSubioform=false;

    ImageView imgVAtachProcesoFrutaFinca;
    ImageView imbTakePicProcesoFrutaFinca;
   // ImageView imgVAtachLlegadaContenedor;
   // ImageView imbTakePicLllegadaContenedor;
  //  ImageView imgVAtachSellosLlegada;
   // ImageView imbTakePicSellosLlegada;
  //  ImageView imgVAtachPuertaAbiertaContenedor;
  // ImageView imbTakePicPuertaAbiertaContenedor;
   // ImageView imgVAtachFotosPallet;
 //   ImageView imbTakePicPallet;
    ImageView imgVAtachCierreContenedor;
    ImageView imbTakePicCierreContenedor;
    ImageView imgVAtachDocumentacionss;
    ImageView imbTakePicDocuementacionxx;

    Button btnSaveLocale;
    Button btnCheck;

    private TextInputEditText ediExportadoraProcesada;
    private TextInputEditText ediExportadoraSolicitante;
    private TextInputEditText ediMarca;
    private TextInputEditText ediClienteNombreReporte;




    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    public static Context context;
    private final int CODE_TWO_PERMISIONS = 12;
    LinearLayout layoutPesobrutoPorClusterSolo;

    HashMap<String,Float> hasmapPesoBrutoClosters2y3L;
    private int currentTypeImage=0;

    TextInputEditText ediSemana;

    ImageView imgUpdatecAlfrutaEnfunde;

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
   // TextInputEditText ediDestino;
    TextInputEditText ediNViaje;
    TextInputEditText ediTipoContenedor;
   // TextInputEditText ediVapor;
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;
    TextInputEditText ediNumContenedor;


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

  //  TextInputEditText ediTare;
    //TextInputEditText ediBooking;
  //  TextInputEditText ediMaxGross;
  //  TextInputEditText ediNumSerieFunda;
//    TextInputEditText stikVentolerExterna;
  //  TextInputEditText ediCableRastreoLlegada;
  //  TextInputEditText ediSelloPlasticoNaviera;
//    TextInputEditText ediOtroSellosLlegada;

    TextInputEditText ediCondicionBalanza;
    TextInputEditText ediTipodeCaja;
    TextInputEditText ediTipoPlastico;
    TextInputEditText ediTipoBalanza;
    TextInputEditText editipbalanzaRepeso;
   TextInputEditText ediUbicacionBalanza;

  //  TextInputEditText ediTermofrafo1;
  //  TextInputEditText ediHoraEncendido1;
   // TextInputEditText ediUbicacion1;
   // TextInputEditText ediRuma1;
 //   TextInputEditText ediTermofrafo2;
    TextInputEditText ediHoraEncendido2;
  //  TextInputEditText ediUbicacion2;
  ///  TextInputEditText ediRuma2;
   // TextInputEditText ediCandadoqsercon;
  //  TextInputEditText ediSelloNaviera;
 //   TextInputEditText ediCableNaviera;
  //  TextInputEditText ediSelloPlastico;
  //  TextInputEditText ediCandadoBotella;
  //  TextInputEditText ediCableExportadora;
 //   TextInputEditText ediSelloAdesivoexpor;
  //  TextInputEditText esiSelloAdhNaviera;
   // TextInputEditText ediOtherSellos;

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
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza ;
    Spinner spinnertipodeBlanzaRepeso ;
    Spinner spinnerubicacionBalanza ;

    Spinner spFuenteAgua ;
    Spinner spFumigaCorL1 ;
    Spinner spTipoBoquilla ;


      EditText ediCandadoName1;
    EditText ediCandadoName2;
    EditText ediCandadoName3;


    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;
    Switch switchLavdoRacimos;
    Switch swAguaCorrida;

    ArrayList<View> listViewsClickedUser;

    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;


    ////////



    @Override
    protected void onStart() {
        super.onStart();

        Auth.initAuth(ActivityCamionesyCarretas.this);
        Auth.signInAnonymously(ActivityCamionesyCarretas.this);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_calidad_camio_carret);

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

        callbackUploadNewReport = this;
        context=getApplicationContext();
        Variables.activityCurrent=Variables.FormCamionesyCarretasActivity;

        ImagenReport.hashMapImagesData=new HashMap<>();

        hideSomeElemtosAnexosAndChangeValues();


        UNIQUE_ID_iNFORME= UUID.randomUUID().toString();
        Auth.initAuth(this);

        StorageDataAndRdB. initStorageReference();


        findViewsIds();


        hideViewsIfUserISCampo();
        ocultaoTherVIEWs();
        configCertainSomeViewsAliniciar();
        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();

        getExportadorasAndSetSpinner();


        listennersSpinners();


        eventButtons();


        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            currentKeySharePrefrences=extras.getString(Variables.KEY_FORM_EXTRA);

            AddDataFormOfSharePrefeIfExistPrefrencesMap() ;
            //The key argument here must match that used in the other activity
        }


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


    private void callPrefrencesSaveAndImagesData(){

        View [] arrayAllViewsData=creaArryOfViewsAll();

        EditText [] arrayEdiTextCalendario =creaArrayOfEditextCalendario();
        EditText [] arrayEdiTextLibriado=generateArrayOfEditTextLibriado();



        Log.i("preferido","el current key es "+currentKeySharePrefrences);


        if(!currentKeySharePrefrences.equals("") || userCreoRegisterForm){  //si no contiene
            Log.i("saberrr","se ejecuto el if ,ya existe este informe ,usamos el mismo key que s "+currentKeySharePrefrences);

            SharePrefHelper.viewsSaveInfo(arrayAllViewsData,currentKeySharePrefrences, ActivityCamionesyCarretas.this);
            SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextCalendario,currentKeySharePrefrences+"Calendario");
            SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextLibriado,currentKeySharePrefrences+"Libriado");



            SharePref.saveHashMapImagesData(ImagenReport.hashMapImagesData,currentKeySharePrefrences);


            Toast.makeText(ActivityCamionesyCarretas.this, "Guardado Localmente", Toast.LENGTH_SHORT).show();

          //  btnSaveLocale.setEnabled(false);

            //significa que tenemos un key de un objeto obtneido de prefrencias

        }

        else
        { //no existe creamos un nuevo register..
            Log.i("saberrr","se ejecuto el else no existe este informe generamos un nuevo key prferences ");


            Map<String, InformRegister>miMpaAllrRegisters=SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);


             currentKeySharePrefrences=UUID.randomUUID().toString();


            Log.i("saberrr","el key generado es "+currentKeySharePrefrences);


            InformRegister inform= new InformRegister(currentKeySharePrefrences,Constants.CAMIONES_Y_CARRETAS,"Usuario", "","Camiones y carretas" ,ediExportadoraProcesada.getText().toString(),Utils.hasmpaExportadoras.get(ediExportadoraProcesada.getText().toString()).getNameExportadora() );


            //gudramos oejto en el mapa
            miMpaAllrRegisters.put(inform.getInformUniqueIdPertenece(),inform);

            SharePref.saveHashMapOfHashmapInformRegister(miMpaAllrRegisters,SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

            //guardamos info de  views en un mapa usnado el nismo id delobejto creado
            SharePrefHelper.viewsSaveInfo(arrayAllViewsData,currentKeySharePrefrences, ActivityCamionesyCarretas.this);
            SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextCalendario,currentKeySharePrefrences+"Calendario");
            SharePrefHelper.viewsSaveInfoEditText(arrayEdiTextLibriado,currentKeySharePrefrences+"Libriado");

            Toast.makeText(ActivityCamionesyCarretas.this, "Guardado Localmente", Toast.LENGTH_SHORT).show();


            if(ImagenReport.hashMapImagesData.size()>0){ //

                SharePref.saveHashMapImagesData(ImagenReport.hashMapImagesData,currentKeySharePrefrences);


            }

            userCreoRegisterForm=true;
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
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(ActivityCamionesyCarretas.this,
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
                         //   ediHoraEncendido1.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }


/*
                        else if (vista.getId()== R.id.ediHoraEncendido2) {
                            ediHoraEncendido2.setText(sHour + ":" + minutes+" "+ AM_PM);


                        }
*/


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
        DatePickerDialog  picker = new DatePickerDialog(ActivityCamionesyCarretas.this,
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

         disableEditText(ediRacimosRecha);
        disableEditText(ediFecha);
        disableEditText(ediHoraInicio);
        disableEditText(ediHoraTermino);

      //  disableEditText(ediHoraLLegadaContenedor);//here
       // disableEditText(ediHoraSalidaContenedor);


        disableEditText(ediContenedor);


        disableEditText(ediZona);
        disableEditText(ediEnsunchado);
        disableEditText(ediBalanzaRepeso);

       // disableEditText(ediHoraEncendido1);
      //  disableEditText(ediHoraEncendido2);

    }

    private void findViewsIds( ) { //configuraremos algos views al iniciar

         ediCandadoName1=findViewById(R.id.ediCandadoName1);
         ediCandadoName2=findViewById(R.id.ediCandadoName2);
         ediCandadoName3=findViewById(R.id.ediCandadoName3);


        spinnerExportadora=findViewById(R.id.spinnerExportadora);
          btnSaveLocale=findViewById(R.id.btnSaveLocale);
        imgAtachVinculacion=findViewById(R.id.imgAtachVinculacion);

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




      //  ediTare=findViewById(R.id.ediTare);
     //   ediMaxGross=findViewById(R.id.ediMaxGross);
    //    ediNumSerieFunda=findViewById(R.id.ediNumSerieFunda);
    //    stikVentolerExterna=findViewById(R.id.stikVentolerExterna);
      //  ediCableRastreoLlegada=findViewById(R.id.ediCableRastreoLlegada);
      //  ediSelloPlasticoNaviera=findViewById(R.id.ediSelloPlasticoNaviera);
      //  ediOtroSellosLlegada=findViewById(R.id.ediOtroSellosLlegada);

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


       // ediDestino=findViewById(R.id.ediDestino);
    //    ediNViaje=findViewById(R.id.ediNViaje);
    //    ediVapor=findViewById(R.id.ediVapor);

        // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
        //ediHoraSalida=findViewById(R.id.ediHoraSalida);

       // ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
       // ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);


       // ediTipoContenedor=findViewById(R.id.ediTipoContenedor);

       // ediFotoContenedor=findViewById(R.id.ediFotoContenedor);



        ediCondicionBalanza=findViewById(R.id.ediCondicionBalanza);
        ediTipodeCaja=findViewById(R.id.ediTipodeCaja);
        ediTipoPlastico=findViewById(R.id.ediTipoPlastico);
        ediTipoBalanza=findViewById(R.id.ediTipoBalanza);
        editipbalanzaRepeso=findViewById(R.id.editipbalanzaRepeso);
        ediUbicacionBalanza=findViewById(R.id.ediUbicacionBalanza);



     //   ediTermofrafo1=findViewById(R.id.ediNombProd1);
       // ediHoraEncendido1=findViewById(R.id.ediTipoEmp2);
      //  ediUbicacion1=findViewById(R.id.ediCod2);
       // ediRuma1=findViewById(R.id.edinCajas3);
    ///    ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
       // ediHoraEncendido2=findViewById(R.id.ediHoraEncendido2);
    //    ediUbicacion2=findViewById(R.id.ediUbicacion2);
    //    ediRuma2=findViewById(R.id.ediRuma2);
       // ediCandadoqsercon=findViewById(R.id.ediCandadoqsercon);

      //  ediSelloNaviera=findViewById(R.id.ediSelloNaviera);
      //  ediCableNaviera=findViewById(R.id.ediCableNaviera);
     //   ediSelloPlastico=findViewById(R.id.ediSelloPlastico);
      //  ediCandadoBotella=findViewById(R.id.ediCandadoBotella);
       // ediCableExportadora=findViewById(R.id.ediCableExportadora);
      //  ediSelloAdesivoexpor=findViewById(R.id.ediSelloAdesivoexpor);
     //   esiSelloAdhNaviera=findViewById(R.id.esiSelloAdhNaviera);
     //   ediOtherSellos=findViewById(R.id.ediOtherSellos);


        ediCompaniaTransporte=findViewById(R.id.ediCompaniaTransporte);
        ediNombreChofer=findViewById(R.id.ediNombreChofer);
        ediCedula=findViewById(R.id.ediCedula);
        ediCelular=findViewById(R.id.ediCelular);
        ediPLaca=findViewById(R.id.ediPLaca);
        ediMarcaCabezal=findViewById(R.id.ediMarcaCabezal);
        ediColorCabezal=findViewById(R.id.ediColorCabezal);






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

        /**todos add a todos clicklistener de la implemntacion*/

        imgAtachVinculacion.setOnClickListener(this);


        imgVAtachDocumentacionss.setOnClickListener(this);//ultimo
        imgVAtachProcesoFrutaFinca.setOnClickListener(this);
        imbTakePicProcesoFrutaFinca.setOnClickListener(this);

        imgVAtachCierreContenedor.setOnClickListener(this);
        imbTakePicCierreContenedor.setOnClickListener(this);
        imbTakePicDocuementacionxx.setOnClickListener(this);

        imgUpdatecAlfrutaEnfunde.setOnClickListener(this);

        layoutPesobrutoPorClusterSolo.setOnClickListener(this);


      //  imbAtachSellosLlegada.setOnClickListener(this);
      //  imbTakePicSellosLLegada.setOnClickListener(this);
       // imbAtachDatosContenedor.setOnClickListener(this);
      //  imbTakePicDatosContenedor.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {

        if(!checkPermission()){

            requestPermission();

          //  return;
            /****por aqui pedir permisos antes **/

        }


        Log.i("darterlo", "is selñecieo,ages");

        String data[]={"image/*"};
        Log.i("miclickimg","hemos hecho click");

        int idCurrent= view.getId();

        if(idCurrent==R.id.imgVAtachProcesoFrutaFinca || idCurrent==R.id.imgVAtachCierreContenedor ||
                idCurrent == R.id.imgVAtachDocumentacionss){ //si es atach


            currentTypeImage=Integer.parseInt(view.getTag().toString());

            activityResultLauncher.launch(data);


            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);

        }

        else if(idCurrent==R.id.imbTakePicProcesoFrutaFinca
                || idCurrent==R.id.imbTakePicCierreContenedor || idCurrent==R.id.imbTakePicDocuementacionxx ){ //si es tajke pic con camara

            currentTypeImage=Integer.parseInt(view.getTag().toString());


            takepickNow();

            Log.i("miclickimg","es foto es type selected es "+currentTypeImage);
        }


        else

        {

            switch (view.getId()) {


                case R.id.imgAtachVinculacion:
                    Log.i("miclickimgddd","sellamo este");
                    showEditDialogAndSendData();
                    break;



                case R.id.imgUpdatecAlfrutaEnfunde:
                    Log.i("miclickimg","es foto es type Variables.FOTO_PROD_POSTCOSECHA");
                    getResultDatCalibCalEnfundes();
                    break;



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
                case R.id.ediHoraSalidaContenedor:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //




                case R.id.ediTipoEmp2:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //

                /*
                case R.id.ediHoraEncendido2:
                    // Utils.closeKeyboard(ActivityCamionesyCarretas.this);
                    showingTimePicker(view);

                    break; //
*/
            }

        }

    }


    private void showEditDialogAndSendData() {

        Bundle bundle = new Bundle();
        bundle.putString(Variables.KEY_CONTROL_CALIDAD_ATACHEDS, RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
        bundle.putString(Variables.KEY_CUADRO_MUETREO_ATACHED, RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);



        FragmentManager fm = getSupportFragmentManager();
        BottonSheetDfragmentVclds alertDialog = BottonSheetDfragmentVclds.newInstance(Constants.CAMIONES_Y_CARRETAS);
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

                String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
///
             //   ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA},
                   //     Log.i("permiso","vamos a solictar permiso camara") ;

                ActivityCompat.requestPermissions(this,permissions,
                        CODE_TWO_PERMISIONS);
            }

        }

    }

    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = ActivityCamionesyCarretas.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
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

                            Bitmap bitmap=   HelperImage.handleSamplingAndRotationBitmap(ActivityCamionesyCarretas.this,cam_uri);
                         //   Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityCamionesyCarretas.this.getContentResolver(),cam_uri);

                         //   Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                            String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);
                            ActivityCamionesyCarretas.this.getContentResolver().takePersistableUriPermission(urix, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                            //creamos un nuevo objet de tipo ImagenReport
                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage, Utils.getFileNameByUri(ActivityCamionesyCarretas.this,cam_uri),horientacionImg);

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                            showImagesPicShotOrSelectUpdateView(false,Variables.NINGUNO);

                        }

                      catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityCamionesyCarretas.this);


                        showImagesPicShotOrSelectUpdateView(false,Variables.NINGUNO);

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
                new ActivityResultContracts.OpenMultipleDocuments(), new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        if (result != null) {
                         MiTarea mit= new MiTarea();
                         mit.execute(result);

                        }
                    }
                });
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


    private void showImagesPicShotOrSelectUpdateView(boolean isDeleteImg,int posicionionBorrar){

        //si es eliminar comprobar aqui
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


        //buscamos este

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


                aadpaterRecuperadoOFrView.addItems(filterListImagesData); //le agremos los items
                aadpaterRecuperadoOFrView.notifyDataSetChanged(); //notificamos  no se si hace falta porque la clase del objeto ya lo tiene...


                Log.i("adpatertt","adpasternotiff");

            }else{ //borrmaos
                aadpaterRecuperadoOFrView. listImagenData.remove(posicionionBorrar);
                aadpaterRecuperadoOFrView.notifyItemRemoved(posicionionBorrar);
                aadpaterRecuperadoOFrView.notifyItemRangeChanged(posicionionBorrar, aadpaterRecuperadoOFrView.listImagenData.size());
                // holder.itemView.setVisibility(View.GONE);



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




    }
    private void updatePostionImegesSort(){

        RecyclerView recyclerView=null;
        recyclerView= findViewById(R.id.recyclerFotoProcesoFrEnFinca);
        Utils.updatePositionObjectImagenReport(recyclerView);

        recyclerView= findViewById(R.id.recyclerFotoCierreCtendr);
        Utils.updatePositionObjectImagenReport(recyclerView);

        recyclerView= findViewById(R.id.recyclerFotoDocumentacion);
        Utils.updatePositionObjectImagenReport(recyclerView);



    }


    private void eventButtons(){// verificamos que halla llenado toda la info necesaria..

        btnCheck=findViewById(R.id.btnCheck);


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!currentKeySharePrefrences.equals("") && Utils.checkIfReportSeSubio(currentKeySharePrefrences)){
                    //ya se subio anteriomente
                    Toast.makeText(     ActivityCamionesyCarretas.this, "Ya subiste este formulario", Toast.LENGTH_SHORT).show();
                    Log.i("elformasd","se subio form anteriomenmte ");
                    btnCheck.setEnabled(false);
                    return;
                }





                // generatePDFandImport();

                checkDataFields();




            }
        });

        btnSaveLocale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callPrefrencesSaveAndImagesData();

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

        if(! cehckFaltanImagenes()){
            return;

        }


        if(! chekDaTaEvaluador()){
            Log.i("test001","no esta lleno  chekDaTaEvaluador");

            return;
        }else{

            Log.i("test001","si  esta lleno  chekDaTaEvaluador");


        }


        if(!Utils.checkifAtach()){
            btnCheck.setEnabled(true);
            Log.i("test001","no esta lleno  checkifAtach");
            FragmentManager fm = getSupportFragmentManager();
            DialogConfirmNoAtach alertDialog = DialogConfirmNoAtach.newInstance(Constants.CAMIONES_Y_CARRETAS);
            alertDialog.show(fm, "duialoffragment_alertZ");
            return;
        }





        RealtimeDB.initDatabasesRootOnly();
        RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

        Log.i("test001","toda la data esta completa HUrra ");

        updatePostionImegesSort();

        createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...


    }


    private void createObjcInformeAndUpload(){

        ReportCamionesyCarretas informe = new ReportCamionesyCarretas(UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
                ediNhojaEvaluacion.getText().toString(),ediZona.getText().toString(),ediProductor.getText().toString(),
                ediCodigo.getText().toString(), ediPemarque.getText().toString(),
                 ediNguiaRemision.getText().toString(),ediHacienda.getText().toString(),edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
                ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString(),ediSemana.getText().toString(),ediEmpacadora.getText().toString(),
                ediNombreChofer.getText().toString(),ediCedula.getText().toString(),ediCelular.getText().toString(),ediPLaca.getText().toString(),
                   ediTipoPlastico.getText().toString(),ediTipodeCaja.getText().toString(),switchHayEnsunchado.isChecked(),switchHaybalanza.isChecked(),
                   ediCondicionBalanza.getText().toString(),ediTipoBalanza.getText().toString(),switchBalanzaRep.isChecked(),editipbalanzaRepeso.getText().toString(),
                ediFuenteAgua.getText().toString(),swAguaCorrida.isChecked(),switchLavdoRacimos.isChecked(),ediFumigacionClin1.getText().toString(),
                Integer.parseInt(ediRacimosCosech.getText().toString()) ,Integer.parseInt(ediRacimosRecha.getText().toString()),Integer.parseInt(ediRacimProces.getText().toString()),Integer .parseInt(ediCajasProcDesp.getText().toString()),
                ediExtCalid.getText().toString(),ediExtRodillo.getText().toString(), ediExtGancho.getText().toString(),
                ediExtCalidCi.getText().toString(),ediExtRodilloCi.getText().toString(),ediExtGanchoCi.getText().toString(),FieldOpcional.observacionOpcional,""
                ,ediClienteNombreReporte.getText().toString(),ediTipoBoquilla.getText().toString(),
                ediExportadoraProcesada.getText().toString(),ediExportadoraSolicitante.getText().toString(),ediMarca.getText().toString(),
                ediCandadoName1.getText().toString(),ediCandadoName2.getText().toString(), ediCandadoName3.getText().toString()

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





    private void generateUniqueIdInformeAndContinuesIfIdIsUnique( ReportCamionesyCarretas cmaionesyCarretasObjc){

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss"+uniqueId);

        if(!seSubioform){

            checkIfExistIdAndUpload(uniqueId,cmaionesyCarretasObjc);

        }



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
                            Variables.usuarioQserconGlobal.getNombreUsuario(),
                            Variables.usuarioQserconGlobal.getUniqueIDuser()
                           , "CAMIONES Y CARRETAS ",ediExportadoraProcesada.getText().toString(),
                            Utils.hasmpaExportadoras.get(ediExportadoraProcesada.getText().toString()).getNameExportadora());


                    //informe register
                    StorageDataAndRdB.uniqueIDImagesSetAndUInforme=currenTidGenrate;

                    objecCamionesyCarretas.setUniqueIDinforme(currenTidGenrate);


                    if( RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString!=null){
                        objecCamionesyCarretas.setAtachControCalidadInfrms(RecyclerViewAdapLinkage.idsFormsVinucladosControlCalidadString);
                    }

                    if( RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado !=null){
                        objecCamionesyCarretas.setAtachControCuadroMuestreo(RecyclerViewAdapLinkage.idCudroMuestreoStringVinuclado);
                    }


                     CalibrFrutCalEnf cali=     generateCalibracal(currenTidGenrate);
                    ProductPostCosecha producto= generateProductPost(currenTidGenrate);
                   ArrayList<ImagenReport>miList=updateAndCreateArrayListImages();


                   Utils. show_AND_UPLOAD_CamionesyCarretas(ActivityCamionesyCarretas.this,ActivityCamionesyCarretas.this,
                           objecCamionesyCarretas,cali,informRegister,producto,miList,Variables.FormCamionesyCarretasActivity
                   ,currentKeySharePrefrences);


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
                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityCamionesyCarretas.this);


                Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true,position);





            }

        });
    }



     public   ArrayList<ImagenReport> updateAndCreateArrayListImages()  {
        //una lista de Uris


        Log.i("imagheddd","se llamometodoel size de lista es "+ImagenReport.hashMapImagesData.size());


        Log.i("imagheddd","es difrente de cero");

        Log.i("imagheddd","el size de hasmpa es  "+ImagenReport.hashMapImagesData.size());


        Log.i("imagheddd","el unique id es "+ StorageDataAndRdB.uniqueIDImagesSetAndUInforme);


        ImagenReport.updateIdPerteence(StorageDataAndRdB.uniqueIDImagesSetAndUInforme,ImagenReport.hashMapImagesData);

        ArrayList<ImagenReport>list=Utils.mapToArrayList(ImagenReport.hashMapImagesData);

        return  list;

    }




    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }



    private void requestPermission() {
        // requesting permissions if not provided.

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

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
            int result = ContextCompat.checkSelfPermission(ActivityCamionesyCarretas.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(ActivityCamionesyCarretas.this, WRITE_EXTERNAL_STORAGE);
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
/*
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

*/

        if(ediHoraSalidaContenedor.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediHoraSalidaContenedor.requestFocus();
            ediHoraSalidaContenedor.setError("Este espacio es obligatorio");

            layoutContainerSeccion3.setVisibility(LinearLayout.VISIBLE);
            return false;

        }


        //chekamos que al menos exista una imagen...



        return  true;

    }







    private boolean checkDatosTransportistaIsLleno(){

        LinearLayout layoutContainerSeccion6=findViewById(R.id.layoutContainerSeccion6);
        ///CHEKEAMOS DATA seccion CONTENEDOR

        if(layoutContainerSeccion6.getVisibility()== GONE){
            layoutContainerSeccion6.setVisibility(LinearLayout.VISIBLE);


        }

        /**al menos un candado name*/

        if(ediCandadoName1.getText().toString().trim().isEmpty() && ediCandadoName2.getText().toString().trim().isEmpty()&&
                ediCandadoName3.getText().toString().trim().isEmpty()){

            ediCandadoName1.requestFocus();
            ediCandadoName1.setError("Inserte al menos un candado ");
            return false;

        }




        if(ediNombreChofer.getText().toString().trim().isEmpty()){ //chekamos que no este vacia
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


        if(ediCajasProcDesp.getText().toString().trim().isEmpty()){ //chekamos que no este vacia
            ediCajasProcDesp.requestFocus();
            ediCajasProcDesp.setError("Este espacio es obligatorio");
            return false;

        }



        if(ediRacimosCosech.getText().toString().trim().isEmpty()){ //chekamos que no este vacia
            ediRacimosCosech.requestFocus();
            ediRacimosCosech.setError("Este espacio es obligatorio");

            return false;

        }

        /*
        if(ediRacimosRecha.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediRacimosRecha.requestFocus();
            ediRacimosRecha.setError("Este espacio es obligatorio");

            return false;
        }

         */

        if(ediRacimProces.getText().toString().trim().isEmpty()){ //chekamos que no este vacia
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


    private   ProductPostCosecha generateProductPost(String uniqueIDinforme){

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

       return producto;



    }

    private CalibrFrutCalEnf generateCalibracal(String reportPerteence){

        //recorremos un array de editext y creamos un objeto de tipo CalibrFrutCalEnf..
        //si no tiene data agregamos cero u comillas...

        CalibrFrutCalEnf calibrFrutCalEnf=new CalibrFrutCalEnf(reportPerteence);
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

                            //AQUI EL PARSE

                            calibrFrutCalEnf.setNumeracionRacimosSem14(currentEditextNumRacims.getText().toString());

                            break;
                        case R.id.ediColortSem13:
                            calibrFrutCalEnf.setColorSemana13(currentEditextColorSem.getText().toString());


                            calibrFrutCalEnf.setNumeracionRacimosSem13(currentEditextNumRacims.getText().toString());

                            break;

                        case R.id.ediColortSem12:
                            calibrFrutCalEnf.setColorSemana12(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem12(currentEditextNumRacims.getText().toString());

                            break;

                        case R.id.ediColortSem11:
                            calibrFrutCalEnf.setColorSemana11(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem11(currentEditextNumRacims.getText().toString());

                            break;
                        case R.id.ediColortSem10:
                            calibrFrutCalEnf.setColorSemana10(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem10(currentEditextNumRacims.getText().toString());

                            break;
                        case R.id.ediColortSem9:
                            calibrFrutCalEnf.setColorSemana9(currentEditextColorSem.getText().toString());
                            calibrFrutCalEnf.setNumeracionRacimosSem9(currentEditextNumRacims.getText().toString());

                            break;

                    }


                }


            }

            //si el editext tiene data lo corregimos usando la propiedad hint


        }

return  calibrFrutCalEnf;



    }

//upload data...

    //descragamos el ultimo
//Si hay un formulario ... que no se envio aun.....estado subido..
//si hay un formulario obtenerlo..
    //una propiedad que diga si ya lo subio...
    ///el primer valor del map conttendra esa propiedad...
    private View[] creaArryOfViewsAll() {

        View [] arrayViewsAll = {


                ediCandadoName1,ediCandadoName2,ediCandadoName3,

                ediMarca,
                ediExportadoraProcesada,
                ediExportadoraSolicitante,
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
             //   ediHoraLLegadaContenedor,
            //    ediHoraSalidaContenedor,
                ediNguiaRemision,
                edi_nguia_transporte,
                ediNtargetaEmbarque,
                ediNhojaEvaluacion,
                ediObservacion,
                ediEmpacadora,
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
              //  ediDestino, //35
             //   ediNViaje,
              //  ediTipoContenedor,
             //   ediVapor, //38
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

           //     ediTare,//55
             //  ediBooking,
            //    ediMaxGross,
             //   ediNumSerieFunda, //58
            //    stikVentolerExterna, 59
              //  ediCableRastreoLlegada,
              //  ediSelloPlasticoNaviera,
               // ediOtroSellosLlegada,62

                ediCondicionBalanza,
                ediTipodeCaja,
                ediTipoPlastico,
                ediTipoBalanza,
                editipbalanzaRepeso,
             //   ediUbicacionBalanza,

              //  ediTermofrafo1,   //68
               // ediHoraEncendido1,
              //  ediUbicacion1,
              //  ediRuma1,
              //  ediTermofrafo2,
             //   ediHoraEncendido2,
             //   ediUbicacion2,
              //  ediRuma2,
             //   ediCandadoqsercon,
             //   ediSelloNaviera,
              //  ediCableNaviera,
             //   ediSelloPlastico,
              //  ediCandadoBotella,
             //   ediCableExportadora,
              //  ediSelloAdesivoexpor,
             //   esiSelloAdhNaviera,
              //  ediOtherSellos,

                spinnerExportadora,

                spinnerSelectZona,
         spinnerCondicionBalanza,
         spinnertipodePlastico,
         spinnertipodeBlanza ,
         spinnertipodeBlanzaRepeso,
         spinnerubicacionBalanza,
         spFuenteAgua ,
         spFumigaCorL1 ,
         spTipoBoquilla ,

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
                ediExtRodilloCi,


        } ;


          int positionull=0;
        for(View vista:arrayViewsAll){ //vamos a cnontrar los nulo

            if(vista==null){

                Log.i("misdader","la posicion del null es "+positionull);
            }

            positionull++;
        }

        return arrayViewsAll;
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

        //  addInfotomap(listImagenReports);


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
     //   ediMarca.setVisibility(View.GONE);
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

    public void scroollElementoFaltante(View vistFocus){

        // View targetView = findViewById(R.id.DESIRED_VIEW_ID);
        vistFocus.getParent().requestChildFocus(vistFocus,vistFocus);



    }
    private void  showToast(){

        Toast.makeText(ActivityCamionesyCarretas.this, "Falta Imagen", Toast.LENGTH_SHORT).show();

    }

    boolean cehckFaltanImagenes() {




        if( ! existminiumImage(Variables.MINIMO_FOTOS_ALL_CATEGORY,Variables.FOTO_PROCESO_FRUTA_FINCA)){
            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotoProcesoEnFruta.requestFocus();
            scroollElementoFaltante(ediFotoProcesoEnFruta);
            showToast();

            return false;
        }else{
            TextView ediFotoProcesoEnFruta=findViewById(R.id.ediFotoProcesoEnFruta);
            ediFotoProcesoEnFruta.clearFocus();
            ediFotoProcesoEnFruta.setError(null);
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
            TextView txtFotosDocumentacion=findViewById(R.id.txtFotosDocumentacion);
            txtFotosDocumentacion.requestFocus();
            scroollElementoFaltante(txtFotosDocumentacion);
            showToast();

            return false;
        }else{

            TextView txtFotosDocumentacion=findViewById(R.id.txtFotosDocumentacion);
            txtFotosDocumentacion.clearFocus();
            txtFotosDocumentacion.setError(null);

        }




        return true;
    }



    void hideSomeElemtosAnexosAndChangeValues(){

      //  LinearLayout  lay1x=findViewById(R.id.lay1x);
       // RecyclerView recyclerFotoProcesoFrEnFinca=findViewById(R.id.recyclerFotoProcesoFrEnFinca);



       // lay1x.setVisibility(View.GONE);


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

        Toast.makeText(ActivityCamionesyCarretas.this, "Copiado", Toast.LENGTH_SHORT).show();


    }
    class MiTarea extends AsyncTask<List<Uri>, Void, Void> {

        @Override
        protected Void doInBackground(List<Uri>... lists) {
            List<Uri>  result = lists[0];

            for(int indice=0; indice<result.size(); indice++){
                Log.i("cancionx","se ejecto este else al final  ");

                urix = result.get(indice);
                Bitmap bitmap = null;
                try {
                    bitmap = Glide.with(ActivityCamionesyCarretas.this)
                            .asBitmap()
                            .load(urix)
                            .sizeMultiplier(0.6f)
                            .submit().get();

                } catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }

                horientacionImg4 = HelperImage.devuelveHorientacionImg(bitmap);


                ActivityCamionesyCarretas.this.getContentResolver().takePersistableUriPermission(urix, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                ImagenReport obcjImagenReport =new ImagenReport("",urix.toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityCamionesyCarretas.this,result.get(indice))),horientacionImg4);
                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);
              //  showImagesPicShotOrSelectUpdateView(false);


                if(ImagenReport.hashMapImagesData.size()>0){
                    Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityCamionesyCarretas.this);

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


}
