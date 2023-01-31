package com.tiburela.qsercom.activities.formularios;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ContenedoresEnAcopio;
import com.tiburela.qsercom.models.DatosDeProceso;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.HelperImage;
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


public class ActivityDatosContersEnAcopio extends AppCompatActivity implements View.OnClickListener  {

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


    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    boolean hayUnformularioIcompleto ;
    private final int CODE_TWO_PERMISIONS = 12;
    TextInputEditText ediClienteNombreReporte;
    HashMap<String, DatosDeProceso> mimapaDatosProcesMap=new HashMap<>();



    private int currentTypeImage=0;
    ProgressBar progressBarFormulario;
    TextInputEditText ediFechaInicio;
    TextInputEditText fechDetermino;
    TextInputEditText ediExpSolicitante;
    TextInputEditText ediExpProcesada;
    TextInputEditText ediMarca;
    TextInputEditText ediPuerto;
    TextInputEditText ediAgenciaNav;
    TextInputEditText ediCedulaI;

    TextInputEditText ediCjasProcesDespacha;
    TextInputEditText ediInspectorAcopio;


    TextInputEditText ediZona;
    TextInputEditText ediHoraInicio;
    TextInputEditText ediHoraTermino;
    TextInputEditText ediHoraLLegadaContenedor;
    TextInputEditText ediHoraSalidaContenedor;
    TextInputEditText ediNguiaRemision;
    TextInputEditText ediNtargetaEmbarque;
    TextInputEditText ediDestino;
    TextInputEditText ediVapor;
    TextInputEditText ediNumContenedor;

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

    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;


    public static Context context;


    @Override
    protected void onStart() {
        super.onStart();

      //  Auth.initAuth(ActivityDatosContersEnAcopio.this);
       // Auth.signInAnonymously(ActivityDatosContersEnAcopio.this);



        if(Variables.esUnFormularioOfflienSharePref){


            AddDataFormOfSharePrefe() ;

            //
            Variables.esUnFormularioOfflienSharePref =false;

        }


/*
        if(hayUnformularioIcompleto){

             TextInputEditText [] arrayEditex =creaArryOfTextInputEditText();

            Utils.addDataOfPrefrencesInView(arrayEditex);

            Map<String, ImagenReport> mapImagesReport = Utils.loadMapiMAGEData(ActivityDatosContersEnAcopio.this);


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

        ImagenReport.hashMapImagesData=new HashMap<>();

        UNIQUE_ID_iNFORME= UUID.randomUUID().toString();

      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior


        Variables.activityCurrent=Variables.FormatDatsContAcopi;
        Auth.initAuth(this);

        StorageData.initStorageReference();


        findViewsIds();

        configCertainSomeViewsAliniciar();
        ocultaCertainViews();

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
        TimePickerDialog  picker = new TimePickerDialog(ActivityDatosContersEnAcopio.this,
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


                        else if (vista.getId()== R.id.ediHoraEncendido1) {
                            ediHoraEncendido1.setText(sHour + ":" + sMinute);


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
    void selecionaFecha(int idView){

        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog  picker = new DatePickerDialog(ActivityDatosContersEnAcopio.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        String dateSelec=i2+"/"+(i1+1)+"/"+i;

                        if(idView==R.id.ediFechaInicio){

                            ediFechaInicio.setText(dateSelec);

                        }else{
                            fechDetermino.setText(dateSelec);
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

        ediNtargetaEmbarque=findViewById(R.id.ediNtargetaEmbarque);
         ediZona=findViewById(R.id.ediZona);
         ediHoraInicio=findViewById(R.id.ediHoraInicio);
         ediHoraTermino=findViewById(R.id.ediHoraTermino);
         ediNguiaRemision=findViewById(R.id.ediNguiaRemision);
        spinnerSelectZona = findViewById(R.id.spinnerZona);



         fechDetermino=findViewById(R.id.fechDetermino);
         ediExpSolicitante=findViewById(R.id.ediExpSolicitante);
         ediExpProcesada =findViewById(R.id.ediExpProcesada);
         ediMarca =findViewById(R.id.ediMarca);
         ediPuerto =findViewById(R.id.ediPuerto);
        ediAgenciaNav=findViewById(R.id.ediAgenciaNav);

        ediInspectorAcopio=findViewById(R.id.ediInspectorAcopio);
        ediCjasProcesDespacha=findViewById(R.id.ediCjasProcesDespacha);
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




        ediNumContenedor=findViewById(R.id.ediNumContenedor);


        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);


         spFumigaCorL1=findViewById(R.id.spFumigaCorL1) ;
         spTipoBoquilla=findViewById(R.id.spTipoBoquilla) ;





          ediDestino=findViewById(R.id.ediDestino);
          ediVapor=findViewById(R.id.ediVapor);

         // ediHOraLllegada=findViewById(R.id.ediHoraLLegadaContenedor);
          //ediHoraSalida=findViewById(R.id.ediHoraSalida);

        ediHoraLLegadaContenedor=findViewById(R.id.ediHoraLLegadaContenedor);
        ediHoraSalidaContenedor=findViewById(R.id.ediHoraSalidaContenedor);




        progressBarFormulario=findViewById(R.id.progressBarFormulario);




        ediTermofrafo1=findViewById(R.id.ediTermofrafo1);
        ediUbicacion1=findViewById(R.id.ediUbicacion1);
        ediRuma1=findViewById(R.id.ediRuma1);
        ediTermofrafo2=findViewById(R.id.ediTermofrafo2);
        ediHoraEncendido1=findViewById(R.id.ediHoraEncendido1);
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





    }


    private void addClickListeners( ) {

        /**todos add a todos clicklistener de la implemntacion*/


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

        String data[]={"image/*"};


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

                case R.id.ediHoraEncendido1:
                    // Utils.closeKeyboard(FormularioActivity.this);
                    showingTimePicker(view);

                    break; //


                case R.id.ediHoraEncendido2:
                    // Utils.closeKeyboard(FormularioActivity.this);
                    showingTimePicker(view);

                    break; //

            }

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






        }




    }

        ActivityResultLauncher<Intent> startCamera = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {

                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),cam_uri);


                               // Bitmap bitmap= Glide.with(context).asBitmap().load(cam_uri).submit().get();
                                String horientacionImg= HelperImage.devuelveHorientacionImg(bitmap);

                                //creamos un nuevo objet de tipo ImagenReport
                                ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage, Utils.getFileNameByUri(ActivityDatosContersEnAcopio.this,cam_uri),horientacionImg);

                                //agregamos este objeto a la lista
                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);

                                //aqui guardamos para preferencias...

                                showImagesPicShotOrSelectUpdateView(false);

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









    private View getVistaAnteriorClick() { //el estado puede ser lleno o vacio isEstaLleno


        if(listViewsClickedUser.size() ==3) { //SOLO GUARDAMOS DOS NUMEROS para ahorra memoria
            listViewsClickedUser.remove(0);   //ya no queremoes el primer objeto de la lista siempre y cuando la lista contnega 3 objetos

        }
        Log.i("casnasd","el size aqui en metodo es "+listViewsClickedUser.size());




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

                        //creamos un objeto

                        for(int indice=0; indice<result.size(); indice++){

                            try {

                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(ActivityDatosContersEnAcopio.this.getContentResolver(),result.get(indice));

                                String horientacionImg=HelperImage.devuelveHorientacionImg(bitmap);

                                Uri myUri = result.get(indice);
                                ActivityDatosContersEnAcopio.this.getContentResolver().takePersistableUriPermission(myUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);


                                ImagenReport obcjImagenReport =new ImagenReport("",myUri.toString(),currentTypeImage, UUID.randomUUID().toString()+Utils.getFormate2(Utils.getFileNameByUri(ActivityDatosContersEnAcopio.this,result.get(indice))),horientacionImg);


                                //agregamos este objeto a la lista
                                ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                                showImagesPicShotOrSelectUpdateView(false);

                            }
                                catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
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

    RecyclerView recyclerView=null;


    for (Map.Entry<String, ImagenReport> set : ImagenReport.hashMapImagesData.entrySet()) {

        String key = set.getKey();

        ImagenReport value = set.getValue();

        if(value.getTipoImagenCategory()==currentTypeImage){

            filterListImagesData.add(ImagenReport.hashMapImagesData.get(key));

        }


    }


    //buscamos este


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

    RecyclerViewAdapter adapter=new RecyclerViewAdapter(filterListImagesData,this);
    GridLayoutManager layoutManager=new GridLayoutManager(this,2);

    // at last set adapter to recycler view.
    assert recyclerView != null;
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


         //   Log.i("misdtaxxss","el value es "+ediCjasProcesDespacha.getText().toString());

           // generatePDFandImport();

           // RealtimeDB realtime= new RealtimeDB(ActivityDatosContersEnAcopio.this);
            //  realtime.closeActivity();


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
    }


    if(! checkDataSellosLlegadaIsLleno()){
        Log.i("test001","no esta lleno  checkDataSellosLlegadaIsLleno");

        return;
    }


    if(! checkSellosInstaladosIsLleno()){
        Log.i("test001","no esta lleno  checkSellosInstaladosIsLleno");

        return;
    }


    if(! checkDatosTransportistaIsLleno()){
        Log.i("test001","no esta lleno  checkDatosTransportistaIsLleno");

        return;
    }

    if(! checkaDatosProcesoISllENO()){
        Log.i("test001","no esta lleno  checkDataCalibFrutaCalEnfn");

        return;
    }

    if(ediCjasProcesDespacha.getText().toString().trim().isEmpty()){
        ediCjasProcesDespacha.requestFocus();
        ediCjasProcesDespacha.setError("Este espacio es obligatorio");

        return;
    }


    if(ediInspectorAcopio.getText().toString().trim().isEmpty()){
        ediInspectorAcopio.requestFocus();
        ediInspectorAcopio.setError("Este espacio es obligatorio");

        return;
    }

    if(ediCedulaI.getText().toString().trim().isEmpty()){
        ediCedulaI.requestFocus();
        ediCedulaI.setError("Este espacio es obligatorio");

        return;
    }

    Log.i("test001","toda la data esta completa HUrra ");


    createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...



}



private boolean checkaDatosProcesoISllENO(){


        return true;
}




private boolean creaAcMapDatosProcesoAndCheck(String informePertenece, String PuskEY, DatabaseReference mibasedata){

    Log.i("samamf","se llamo creaDatosProcesoMapAndUpload");
    boolean isReady=true;


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
    int numeroCajas=0;

    for(int indice=0; indice<arraynCajas.length; indice++){

          String KeyDataIdOfView=String.valueOf(arrayNmbresProd[indice].getId()) ;
         String tipoEmpaque=arrayTiposEmpaque[indice].getText().toString();
         String cod=arrayCodigos[indice].getText().toString();
        String nombreProd=arrayNmbresProd[indice].getText().toString();



          if(!arraynCajas[indice].getText().toString().trim().isEmpty()){

              numeroCajas=Integer.parseInt(arraynCajas[indice].getText().toString());

          }



        if(! Utils.checkIFaltaunDatoLlenoAndFocus(arrayNmbresProd,arrayTiposEmpaque,arrayCodigos,arraynCajas)){ //si ha llenado un  value de los 3 y el siguiente esta vacio...
             Log.i("samamf","es return aqui");

            return false;
         }

             if(indice==0 & tipoEmpaque.trim().isEmpty()  & cod.trim().isEmpty()  & nombreProd.trim().isEmpty()
                     & arraynCajas[indice].getText().toString().trim().isEmpty()){

                 tipoEmpaque="";
                 cod="";
                 numeroCajas=0;
                 nombreProd="";

                 //String InformePertenece;  //subimos el primero al menos..
                 DatosDeProceso midatosProceso= new DatosDeProceso(nombreProd,tipoEmpaque,cod,numeroCajas,informePertenece,KeyDataIdOfView);
                 //midatosProceso.setKeyFirebase(PuskEY);
                 mimapaDatosProcesMap.put(KeyDataIdOfView,midatosProceso);

             }



          else if(!tipoEmpaque.trim().isEmpty()  & !  cod.trim().isEmpty()  &  ! nombreProd.trim().isEmpty()
                   & ! arraynCajas[indice].getText().toString().trim().isEmpty()  ) {  //si es diferente de 0

               //entonces subimos la data.....

               //String InformePertenece;
               DatosDeProceso midatosProceso= new DatosDeProceso(nombreProd,tipoEmpaque,cod,numeroCajas,informePertenece,KeyDataIdOfView);
              // midatosProceso.setKeyFirebase(PuskEY);

               mimapaDatosProcesMap.put(KeyDataIdOfView,midatosProceso);

           }




    }

   // RealtimeDB.initContext(ActivityDatosContersEnAcopio.this); //inicilizamos el contexto actual en la clase realtimeDB

    Log.i("samamf","subimos en este nodo "+PuskEY);
    Log.i("samamf","el size de map now ahora es "+mimapaDatosProcesMap.size());
    Log.i("samamf","vamos a subir datos procesoen este nodo  "+PuskEY);




    return isReady;

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
            ,ediCelular.getText().toString(),ediPLaca.getText().toString(),ediMarcaCabezal.getText().toString(),ediColorCabezal.getText().toString(),
            Integer.parseInt(ediCjasProcesDespacha.getText().toString()), ediInspectorAcopio.getText().toString(), Integer.parseInt(ediCedulaI.getText().toString() ),
            ediClienteNombreReporte.getText().toString()
    );




    //Agregamos un nuevo informe
    RealtimeDB.initDatabasesReferenceImagesData(); //inicilizamos la base de datos

    //agr5egamos la data finalemente
    //obtenemos el pushkey




    generateUniqueIdInformeAndContinuesIfIdIsUnique(informe);





}






    private void generateUniqueIdInformeAndContinuesIfIdIsUnique( ContenedoresEnAcopio contenedores_acopio){

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss" +uniqueId);

        checkIfExistIdAndUpload(uniqueId,contenedores_acopio);


    }



    private void checkIfExistIdAndUpload (String currenTidGenrate, ContenedoresEnAcopio conetnedoresEnAcopioForm){

        //  private void checkIfExistIdAndUpload(String currenTidGenrate ) {
        //  Log.i("salero","bsucando este reporte con este id  "+reportidToSearch);

        Query query = RealtimeDB.rootDatabaseReference.child("Registros").child("InformesRegistros").equalTo(currenTidGenrate);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                InformRegister informRegister=null;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    informRegister=ds.getValue(InformRegister.class);
                }


                if(informRegister == null) { //quiere decir que no existe

                    informRegister= new InformRegister(currenTidGenrate,Constants.CONTENEDORES_EN_ACOPIO,
                            Variables.usuarioQsercomGlobal.getNombreUsuario(),
                            Variables.usuarioQsercomGlobal.getUniqueIDuser()
                            , "CONTENEDORES ACOPIO ");


                    //informe register
                    RealtimeDB.addNewRegistroInforme(ActivityDatosContersEnAcopio.this,informRegister);
                    StorageData.uniqueIDImagesSetAndUInforme=currenTidGenrate;

                    conetnedoresEnAcopioForm.setUniqueIDinforme(currenTidGenrate);

                    DatabaseReference mibasedata = RealtimeDB.rootDatabaseReference.child("Informes").child("datosProcesoContenAcopio");
                    String PuskEY = mibasedata.push().getKey(); //que que cotienen este nodo
                    conetnedoresEnAcopioForm.setDatosProcesoContenAcopioKEYFather(PuskEY); //le agregamos esa propiedad


                    Log.i("samamf","el objec conetnedoresEnAcopioForm getDatosProcesoContenAcopio es  "+conetnedoresEnAcopioForm.getDatosProcesoContenAcopioKEYFather());


                     RealtimeDB.initContext(ActivityDatosContersEnAcopio.this);

                    Log.i("samamf","vamos a crea datos proceso");


                    if(creaAcMapDatosProcesoAndCheck(currenTidGenrate,PuskEY,mibasedata)){

                        RealtimeDB.addDatosProceso(mimapaDatosProcesMap,mibasedata,PuskEY);  //subimos

                        uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE

                        RealtimeDB.addNewInformContenresAcopio(conetnedoresEnAcopioForm,currenTidGenrate);

                        Log.i("samamf","hemos pasado add new inform");

                        finish();

                    }


                    else{

                        Log.i("samamf","HAY UN DATO INCOMPLETO HEN DATOS PROCESO");

                        Toast.makeText(ActivityDatosContersEnAcopio.this, "Hay un dato incompleto en datos de Proceso", Toast.LENGTH_LONG).show();

                    }


                    //aqui subimos..

                }else {  //si exite creamos otro value...

                    generateUniqueIdInformeAndContinuesIfIdIsUnique(conetnedoresEnAcopioForm);

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

                Log.i("midaclick","el click es here, posicion es "+position);

               ///elimnar el hasmap
               //vamos a ver el tipo del objeto removivo
               Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag()).getTipoImagenCategory();

                Log.i("camisax","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());


                ImagenReport.hashMapImagesData.remove(v.getTag().toString());
                Utils.saveMapImagesDataPreferences(ImagenReport.hashMapImagesData, ActivityDatosContersEnAcopio.this);


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


        if(ediClienteNombreReporte.getText().toString().isEmpty()){ //chekamos que no este vacia
            ediClienteNombreReporte.requestFocus();
            ediClienteNombreReporte.setError("Este espacio es obligatorio");

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
            ediDestino,
            ediVapor,
            ediCompaniaTransporte,
            ediNombreChofer,
            ediCedula,
            ediCelular,
            ediPLaca,
            ediMarcaCabezal,
            ediColorCabezal,
            ediTare,
            ediBooking,
            ediMaxGross,
            ediNumSerieFunda,
            stikVentolerExterna,
            ediCableRastreoLlegada,
            ediSelloPlasticoNaviera,
            ediOtroSellosLlegada,
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
        assert recyclerView != null;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        eventoBtnclicklistenerDelete(adapter);


    }




    void createlistsForReciclerviewsImages(ArrayList<ImagenReport>listImagenReports){

        //  addInfotomap(listImagenReports);


        ArrayList<ImagenReport>lisFiltrada;

        int []arrayTiposImagenes={Variables.FOTO_LLEGADA,Variables.FOTO_TRANSPORTISTA,Variables.FOTO_SELLO_LLEGADA,Variables.FOTO_CONTENEDOR};

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

    void takePickCamera() {


        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

        cam_uri = ActivityDatosContersEnAcopio.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cam_uri);

        //startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE); // OLD WAY
        startCamera.launch(cameraIntent);                // VERY NEW WAY

    }



    private void ocultaCertainViews(){

        ediRuma1.setVisibility(View.GONE);
        ediRuma2.setVisibility(View.GONE);
        ediHoraEncendido1.setVisibility(View.GONE);
        ediHoraEncendido2.setVisibility(View.GONE);
        ediUbicacion1.setVisibility(View.GONE);
        ediUbicacion2.setVisibility(View.GONE);

    }


}
