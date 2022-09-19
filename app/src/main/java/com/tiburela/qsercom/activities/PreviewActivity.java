package com.tiburela.qsercom.activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.auth.Auth;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.ProductPostCosecha;
import com.tiburela.qsercom.models.SetInformEmbarque1;
import com.tiburela.qsercom.models.SetInformEmbarque2;
import com.tiburela.qsercom.storage.StorageData;
import com.tiburela.qsercom.utils.FieldOpcional;
import com.tiburela.qsercom.utils.Permisionx;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PreviewActivity extends AppCompatActivity implements View.OnClickListener , View.OnTouchListener {
    private static final int PERMISSION_REQUEST_CODE=100;
    private String UNIQUE_ID_iNFORME;
    ProductPostCosecha productxGlobal=null;
    ProgressDialog pd;

    private boolean isModEdicionFields=false;

ProgressDialog progressDialog;
    private int currentTypeImage=0;
    ProgressBar progressBarFormulario;


    FloatingActionButton fab ;

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
    TextInputEditText ediEnsunchado;
    TextInputEditText ediBalanzaRepeso;





    LinearLayout linLayoutHeader1;
    LinearLayout linLayoutHeader2;
    LinearLayout linLayoutHeader3;
    LinearLayout linLayoutHeader4;
    LinearLayout linLayoutHeader5;
    LinearLayout linLayoutHeader6;
    LinearLayout linLayoutHeader7;



    Spinner spinnerSelectZona;
    Spinner spinnerCondicionBalanza;
    Spinner spinnertipoCaja;
    Spinner spinnertipodePlastico;
    Spinner spinnertipodeBlanza ;
    Spinner spinnertipodeBlanzaRepeso ;
    Spinner spinnerubicacionBalanza ;


    Switch switchContenedor;
    Switch switchHaybalanza;
    Switch switchHayEnsunchado;
    Switch switchBalanzaRep;

    ArrayList<View> listViewsClickedUser;

    ActivityResultLauncher activityResultLauncher;
    Uri cam_uri;

    ImageView imBtakePic;
    ImageView imBatach;

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

        Variables.VienedePreview=true;

        Auth.initAuth(PreviewActivity.this);
        Auth.signInAnonymously(PreviewActivity.this);


        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = Auth.mAuth.getCurrentUser();
      //  updateUI(currentUs bver)

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // progressDialog=progressDialog
        setContentView(R.layout.activity_preview);
        findViewsIds();



        UNIQUE_ID_iNFORME= Variables.CurrenReportPart1.getUniqueIDinforme();


      // FirebaseApp.initializeApp(this);
      //  DatabaseReference rootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //anterior

        Auth.initAuth(this);

        StorageData. initStorageReference();


        listViewsClickedUser=new ArrayList<>();

        addClickListeners();
        resultatachImages();
        listennersSpinners();

        EstateFieldView.adddataList();
        addOnTouchaMayoriaDeViews();
        eventCheckdata();
        //creaFotos();
        listennersSpinners();
        checkModeVisualitY();

        configCertainSomeViewsAliniciar();




    }





    void showingTimePicker( View vista){


        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog  picker = new TimePickerDialog(PreviewActivity.this,
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


                        else if (vista.getId()== R.id.ediHoraEncendido1) {
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
        DatePickerDialog  picker = new DatePickerDialog(PreviewActivity.this,
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

         fab = (FloatingActionButton) findViewById(R.id.fab);
        ediEmpacadora=findViewById(R.id.ediEmpacadora);

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



        linLayoutHeader1 =findViewById(R.id.linLayoutHeader1);
        linLayoutHeader2 =findViewById(R.id.linLayoutHeader2);
        linLayoutHeader3 =findViewById(R.id.linLayoutHeader3);
        linLayoutHeader4 =findViewById(R.id.linLayoutHeader4);
        linLayoutHeader5 =findViewById(R.id.linLayoutHeader5);
        linLayoutHeader6 =findViewById(R.id.linLayoutHeader6);
        linLayoutHeader7 =findViewById(R.id.linLayoutHeader7);





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




        fab.setOnClickListener(this);

        imBtakePic.setOnClickListener(this);
        imBatach.setOnClickListener(this);

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




       switch (view.getId()) {


           case R.id.fab: //si pulas en btn chekear en que modo esta ...si el modo cambia...
               TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);

                if(isModEdicionFields){ //si es modo edicion..
                    fab.setImageResource(R.drawable.ic_baseline_edit_24aa);

                    txtModeAdviser.setText("Modo Visualizacion ");



                    //cambiamos al modo visualizacion
                    isModEdicionFields=false;
                    activateModePreview();


                }else{ //SI NO ES MODO VISUZALIZACION
                    fab.setImageResource(R.drawable.ic_baseline_preview_24jhj);
                    txtModeAdviser.setText("Modo Edicion ");

                    isModEdicionFields=true;
                    activateModeEdit();


                  //CAMABIAMOS EL MODO

                }



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
               LinearLayout layoutContainerSeccion7=findViewById(R.id.layoutContainerSeccion7);

               if(layoutContainerSeccion7.getVisibility() == View.GONE) {
                   muestraLinearLayout(layoutContainerSeccion7);
               }
               else{

                   oucultaLinearLayout(layoutContainerSeccion7);
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

        Permisionx.checkPermission(Manifest.permission.CAMERA,1,this, PreviewActivity.this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "AppQsercom");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");

             cam_uri = PreviewActivity.this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
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
                            ImagenReport obcjImagenReport =new ImagenReport("",cam_uri.toString(),currentTypeImage,UNIQUE_ID_iNFORME, Utils.getFileNameByUri(PreviewActivity.this,cam_uri));

                            //agregamos este objeto a la lista
                            ImagenReport.hashMapImagesData.put(obcjImagenReport.getUniqueIdNamePic(), obcjImagenReport);


                            showImagesPicShotOrSelectUpdateView(false);

                        }
                    }
                });



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {


        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN ){

            //agregamos esta vista clickada a la lista

            Log.i("casnasd","se llamo on touch ");

            listViewsClickedUser.add(view);


            Log.i("casnasd","el size de la lista es "+listViewsClickedUser.size());

            if(listViewsClickedUser.size()>1) {
                //obtenemos la lista anterior y verficamos si esta completada;
                View vistFieldAnterior = getVistaAnteriorClick();
                checkeamosSiFieldViewIScompleted(vistFieldAnterior);
                //actualizamos


            }



        }
        return false;
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


                            actualizaListStateView("ediPPC/someProductPostCosecha",true) ;



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

                                ImagenReport imagenReportObjc =new ImagenReport("",result.get(indice).toString(),currentTypeImage,UNIQUE_ID_iNFORME,Utils.getFileNameByUri(PreviewActivity.this,result.get(indice)));



                                ImagenReport.hashMapImagesData.put(imagenReportObjc.getUniqueIdNamePic(), imagenReportObjc);


                            }



                            showImagesPicShotOrSelectUpdateView(false);



                            // creaFotos(result);


                            //Do What you Want Here ................
                            //Do What you Want Here ................

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


                Log.i("mizona","la zona aen listenner spinner es"+zonaEelejida);

                if(zonaEelejida.equals("Ninguna")){
                    //actualizamos
                    Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                    ediZona.setText("");
                    actualizaListStateView("spinnerZona",false) ;
                }else {
                    actualizaListStateView("spinnerZona",true) ;
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
                ediZona.setText("");
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
            editipbalanzaRepeso.setText(" "+zonaEelejida+" ");
            if(zonaEelejida.equals("Ninguna")){
                //actualizamos
                Log.i("maswiso","eSPINNER ZONA SELECIONO NINGUNO ");
                editipbalanzaRepeso.setText("");
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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
                actualizaListStateView("addetiquetaaqui",false) ;
            }else {
                actualizaListStateView("addetiquetaaqui",true) ;
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

  //  checkDatosGeneralesIsLleno();


    //ES ETEST




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

    Log.i("test001","toda la data esta completa HUrra ");


    pd = new ProgressDialog(PreviewActivity.this);
    pd.setMessage("Actualizando data ");
    pd.show();

    uploadImagesInStorageAndInfoPICS(); //subimos laS IMAGENES EN STORAGE Y LA  data de las imagenes EN R_TDBASE
    createObjcInformeAndUpload(); //CREAMOS LOS INFORMES Y LOS SUBIMOS...





}


private void createObjcInformeAndUpload(){

        //aplicamos la logica PARA CREAR UN NUEVO INFORME
//SI LA DATA ES OPCIONAL EN EL FIELD LE AGREGAMOS UN "";en editex comprobacion le agragmos para que el texto no sea nulo

    SetInformEmbarque1 informe = new SetInformEmbarque1(UNIQUE_ID_iNFORME,ediCodigo.getText().toString(),
            Integer.parseInt(ediNhojaEvaluacion.getText().toString()), ediZona.getText().toString()
            ,ediProductor.getText().toString(),ediCodigo.getText().toString()
            ,ediPemarque.getText().toString(),ediNguiaRemision.getText().toString(),ediHacienda.getText().toString()
            ,edi_nguia_transporte.getText().toString(),ediNtargetaEmbarque.getText().toString(),
            ediInscirpMagap.getText().toString(),ediHoraInicio.getText().toString(),ediHoraTermino.getText().toString()
            ,ediSemana.getText().toString(),ediEmpacadora.getText().toString(),ediContenedor.getText().toString(),
            FieldOpcional.observacionOpcional,ediHoraLLegadaContenedor.getText().toString(),ediHoraSalidaContenedor.getText().toString()
            ,ediDestino.getText().toString(),ediNViaje.getText().toString(),ediVapor.getText().toString(),
            ediTipoContenedor.getText().toString(),ediTare.getText().toString(),ediBooking.getText().toString(),ediMaxGross.getText().toString(),
            ediNumSerieFunda.getText().toString(),stikVentolerExterna.getText().toString(),
            ediCableRastreoLlegada.getText().toString(),ediSelloPlasticoNaviera.getText().toString(),FieldOpcional.otrosSellosLLegaEspecif);


    informe.setKeyFirebase( Variables.CurrenReportPart1.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto









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

    informe2.setKeyFirebase( Variables.CurrenReportPart2.getKeyFirebase()); //agregamos el mismo key qe tenia este objeto


    //Agregamos un nuevo informe
    RealtimeDB.initDatabasesReference(); //inicilizamos la base de datos

    //agr5egamos la data finalemente


    RealtimeDB.actualizaInformePart1(informe);


  RealtimeDB.actualizaInformePart2(informe2);


    addProdcutsPostCosechaAndUpload(); //agregamos y subimos los productos postcosecha..


}

    private void eventoBtnclicklistenerDelete(RecyclerViewAdapter adapter) {

        adapter.setOnItemClickListener(new RecyclerViewAdapter.ClickListener() {


            @Override
            public void onItemClick(int position, View v) {  //este para eminar
                Variables.typeoFdeleteImg=  ImagenReport.hashMapImagesData.get(v.getTag().toString()).getTipoImagenCategory();
                Log.i("camisax","el size antes de eliminar es "+ ImagenReport.hashMapImagesData.size());

                ImagenReport.hashMapImagesData.remove(v.getTag().toString());
                Log.i("camisax","el size despues de eliminar es "+ ImagenReport.hashMapImagesData.size());

                showImagesPicShotOrSelectUpdateView(true);


            }

        });
    }



    void uploadImagesInStorageAndInfoPICS() {
   //una lista de Uris


        if(ImagenReport.hashMapImagesData.size() ==0 ){

             return;
        }


        if(  !Variables.hashMapImagesStart.keySet().equals(ImagenReport.hashMapImagesData.keySet())){ //si no son iguales

            StorageData.uploadImage(PreviewActivity.this, Utils.creaHahmapNoDuplicado());
            Log.i("debugasd","alguno o toos son diferentes images");


        }else{

           Log.i("debugasd","son iguales las imagenes");

        }


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
         //   Uri uri = Uri.parse("package:" + BuildConfig.APPLICATION_ID);
         //  startActivity(new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri));
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


    private boolean checkPermission2() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int result = ContextCompat.checkSelfPermission(PreviewActivity.this, READ_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(PreviewActivity.this, WRITE_EXTERNAL_STORAGE);
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



        if(editipbalanzaRepeso.getText().toString().isEmpty()){ //si no esta vacia

          // FieldOpcional.tipoDeBalanzaRepesoOpcnal AQUI GAURDAMOS EL VALOR DEL EDITEXT

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
        producto.keyFirebase=productxGlobal.keyFirebase;

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



        RealtimeDB.UpdateProductosPostCosecha(producto);


        pd.dismiss();

        Toast.makeText(this, "Informe Actualizado", Toast.LENGTH_SHORT).show();
        finish();


    }


//upload data...

    private void diseableViewsByTipe(View view) {

        if (view instanceof TextInputEditText ) { //asi es un editex compobamos si esta lleno

            TextInputEditText editText = (TextInputEditText) view; //asi lo convertimos

            // editText.setFocusable(false);
             editText.setEnabled(false);
           // editText.setCursorVisible(false);
           // editText.setKeyListener(null);
            //  editText.setBackgroundColor(Color.TRANSPARENT);

        }

        else if(view instanceof Spinner){
            Spinner spinner = (Spinner) view; //asi lo convertimos
            spinner.setEnabled(false);
            spinner.setClickable(false);

        }



        else if(view instanceof Switch) {
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
            swichtView.setEnabled(false);
            swichtView.setClickable(false);

        }
        else if(view instanceof ImageView) {
            ImageView imagev = (ImageView) view; //asi lo convertimos
            imagev.setEnabled(false);
            imagev.setClickable(false);


        }
        else if(view instanceof Button) {
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

        }

        else if(view instanceof Spinner){
            Spinner spinner = (Spinner) view; //asi lo convertimos
            spinner.setEnabled(true);
            spinner.setClickable(true);

        }



        else if(view instanceof Switch) {
            @SuppressLint("UseSwitchCompatOrMaterialCode") Switch swichtView = (Switch) view; //asi lo convertimos
            swichtView.setEnabled(true);
            swichtView.setClickable(true);

        }


        else if(view instanceof Button) {
            Button btn = (Button) view; //asi lo convertimos
            btn.setEnabled(true);
            btn.setClickable(true);


        }



    }


    private void activateModePreview( ) {

        Log.i("extra","se llamo activateModePreview descativamos ");


        diseableViewsByTipe(    ediSemana);
        diseableViewsByTipe(    ediFecha);
        diseableViewsByTipe(    ediProductor);
        diseableViewsByTipe(    ediHacienda);
        diseableViewsByTipe(    ediCodigo);
        diseableViewsByTipe(    ediInscirpMagap);
        diseableViewsByTipe(    ediPemarque);
        diseableViewsByTipe(    ediZona);
        diseableViewsByTipe(    ediHoraInicio);
        diseableViewsByTipe(    ediHoraTermino);
        diseableViewsByTipe(    ediHoraLLegadaContenedor);
        diseableViewsByTipe(    ediHoraSalidaContenedor);
        diseableViewsByTipe(    ediNguiaRemision);
        diseableViewsByTipe(    edi_nguia_transporte);
        diseableViewsByTipe(    ediNtargetaEmbarque);
        diseableViewsByTipe(    ediNhojaEvaluacion);
        diseableViewsByTipe(    ediObservacion);
        diseableViewsByTipe(    ediEmpacadora);
        diseableViewsByTipe(    ediFotosLlegada);
        diseableViewsByTipe(    ediContenedor);
        diseableViewsByTipe(    ediPPC01);
        diseableViewsByTipe(    ediPPC02);
        diseableViewsByTipe(    ediPPC03);
        diseableViewsByTipe(    ediPPC04);
        diseableViewsByTipe(    ediPPC05);
        diseableViewsByTipe(    ediPPC06);
        diseableViewsByTipe(    ediPPC07);
        diseableViewsByTipe(    ediPPC08);
        diseableViewsByTipe(    ediPPC09);
        diseableViewsByTipe(    ediPPC010);
        diseableViewsByTipe(    ediPPC011);
        diseableViewsByTipe(    ediPPC012);
        diseableViewsByTipe(    ediPPC013);
        diseableViewsByTipe(    ediPPC014);
        diseableViewsByTipe(    ediPPC015);
        diseableViewsByTipe(    ediPPC016);
        diseableViewsByTipe(    ediDestino);
        diseableViewsByTipe(    ediNViaje);
        diseableViewsByTipe(    ediTipoContenedor);
        diseableViewsByTipe(    ediVapor);
        diseableViewsByTipe(    ediFotoContenedor);
        diseableViewsByTipe(    ediFotosPposcosecha);
        diseableViewsByTipe(    ediCompaniaTransporte);
        diseableViewsByTipe(    ediNombreChofer);
        diseableViewsByTipe(    ediCedula);
        diseableViewsByTipe(    ediCelular);
        diseableViewsByTipe(    ediPLaca);
        diseableViewsByTipe(    ediMarcaCabezal);
        diseableViewsByTipe(    ediColorCabezal);
        diseableViewsByTipe(    ediFotosLlegadaTransport);
        diseableViewsByTipe(    ediTare);
        diseableViewsByTipe(    ediBooking);
        diseableViewsByTipe(    ediMaxGross);
        diseableViewsByTipe(    ediNumSerieFunda);
        diseableViewsByTipe(    stikVentolerExterna);
        diseableViewsByTipe(    ediCableRastreoLlegada);
        diseableViewsByTipe(    ediSelloPlasticoNaviera);
        diseableViewsByTipe(    ediOtroSellosLlegada);
        diseableViewsByTipe(    ediFotosSellosLLegada);
        diseableViewsByTipe(    ediCondicionBalanza);
        diseableViewsByTipe(    ediTipodeCaja);
        diseableViewsByTipe(    ediTipoPlastico);
        diseableViewsByTipe(    ediTipoBalanza);
        diseableViewsByTipe(    editipbalanzaRepeso);
        diseableViewsByTipe(    ediUbicacionBalanza);
        diseableViewsByTipe(    ediTermofrafo1);
        diseableViewsByTipe(    ediHoraEncendido1);
        diseableViewsByTipe(    ediUbicacion1);
        diseableViewsByTipe(    ediRuma1);
        diseableViewsByTipe(    ediTermofrafo2);
        diseableViewsByTipe(    ediHoraEncendido2);
        diseableViewsByTipe(    ediUbicacion2);
        diseableViewsByTipe(    ediRuma2);
        diseableViewsByTipe(    ediCandadoqsercon);
        diseableViewsByTipe(    ediSelloNaviera);
        diseableViewsByTipe(    ediCableNaviera);
        diseableViewsByTipe(    ediSelloPlastico);
        diseableViewsByTipe(    ediCandadoBotella);
        diseableViewsByTipe(    ediCableExportadora);
        diseableViewsByTipe(    ediSelloAdesivoexpor);
        diseableViewsByTipe(    esiSelloAdhNaviera);
        diseableViewsByTipe(    ediOtherSellos);
        diseableViewsByTipe(ediEnsunchado);
        diseableViewsByTipe(    ediBalanzaRepeso);




        //SPINNERS
        diseableViewsByTipe(  spinnerSelectZona);
        diseableViewsByTipe(  spinnerCondicionBalanza);
        diseableViewsByTipe( spinnertipoCaja);
        diseableViewsByTipe(  spinnertipodePlastico);
        diseableViewsByTipe( spinnertipodeBlanza) ;
        diseableViewsByTipe( spinnertipodeBlanzaRepeso) ;
        diseableViewsByTipe( spinnerubicacionBalanza) ;

        //SWITCHSÇ
        diseableViewsByTipe( switchContenedor);
        diseableViewsByTipe( switchHaybalanza);
        diseableViewsByTipe( switchHayEnsunchado);
        diseableViewsByTipe( switchBalanzaRep);


//iMAGEVIEWS
        diseableViewsByTipe( imBtakePic);
        diseableViewsByTipe( imBatach);
        diseableViewsByTipe( imbAtach_transportista);
        diseableViewsByTipe( imbTakePicTransportista);
        diseableViewsByTipe( imbAtachSellosLlegada);
        diseableViewsByTipe( imbTakePicSellosLLegada);
        diseableViewsByTipe( imbAtachDatosContenedor);
        diseableViewsByTipe( imbTakePicDatosContenedor);
        diseableViewsByTipe( imbAtachPrPostcosecha);
        diseableViewsByTipe( imbTakePicPrPostcosecha);


        //Buttons
        Button  btnCheck=findViewById(R.id.btnCheck);
        diseableViewsByTipe( btnCheck);


    }

    private void activateModeEdit() {
        activateViewsByTypeView(    ediSemana);
        activateViewsByTypeView(    ediFecha);
        activateViewsByTypeView(    ediProductor);
        activateViewsByTypeView(    ediHacienda);
        activateViewsByTypeView(    ediCodigo);
        activateViewsByTypeView(    ediInscirpMagap);
        activateViewsByTypeView(    ediPemarque);
        activateViewsByTypeView(    ediZona);
        activateViewsByTypeView(    ediHoraInicio);
        activateViewsByTypeView(    ediHoraTermino);
        activateViewsByTypeView(    ediHoraLLegadaContenedor);
        activateViewsByTypeView(    ediHoraSalidaContenedor);
        activateViewsByTypeView(    ediNguiaRemision);
        activateViewsByTypeView(    edi_nguia_transporte);
        activateViewsByTypeView(    ediNtargetaEmbarque);
        activateViewsByTypeView(    ediNhojaEvaluacion);
        activateViewsByTypeView(    ediObservacion);
        activateViewsByTypeView(    ediEmpacadora);
        activateViewsByTypeView(    ediFotosLlegada);
        activateViewsByTypeView(    ediContenedor);
        activateViewsByTypeView(    ediPPC01);
        activateViewsByTypeView(    ediPPC02);
        activateViewsByTypeView(    ediPPC03);
        activateViewsByTypeView(    ediPPC04);
        activateViewsByTypeView(    ediPPC05);
        activateViewsByTypeView(    ediPPC06);
        activateViewsByTypeView(    ediPPC07);
        activateViewsByTypeView(    ediPPC08);
        activateViewsByTypeView(    ediPPC09);
        activateViewsByTypeView(    ediPPC010);
        activateViewsByTypeView(    ediPPC011);
        activateViewsByTypeView(    ediPPC012);
        activateViewsByTypeView(    ediPPC013);
        activateViewsByTypeView(    ediPPC014);
        activateViewsByTypeView(    ediPPC015);
        activateViewsByTypeView(    ediPPC016);
        activateViewsByTypeView(    ediDestino);
        activateViewsByTypeView(    ediNViaje);
        activateViewsByTypeView(    ediTipoContenedor);
        activateViewsByTypeView(    ediVapor);
        activateViewsByTypeView(    ediFotoContenedor);
        activateViewsByTypeView(    ediFotosPposcosecha);
        activateViewsByTypeView(    ediCompaniaTransporte);
        activateViewsByTypeView(    ediNombreChofer);
        activateViewsByTypeView(    ediCedula);
        activateViewsByTypeView(    ediCelular);
        activateViewsByTypeView(    ediPLaca);
        activateViewsByTypeView(    ediMarcaCabezal);
        activateViewsByTypeView(    ediColorCabezal);
        activateViewsByTypeView(    ediFotosLlegadaTransport);
        activateViewsByTypeView(    ediTare);
        activateViewsByTypeView(    ediBooking);
        activateViewsByTypeView(    ediMaxGross);
        activateViewsByTypeView(    ediNumSerieFunda);
        activateViewsByTypeView(    stikVentolerExterna);
        activateViewsByTypeView(    ediCableRastreoLlegada);
        activateViewsByTypeView(    ediSelloPlasticoNaviera);
        activateViewsByTypeView(    ediOtroSellosLlegada);
        activateViewsByTypeView(    ediFotosSellosLLegada);
        activateViewsByTypeView(    ediCondicionBalanza);
        activateViewsByTypeView(    ediTipodeCaja);
        activateViewsByTypeView(    ediTipoPlastico);
        activateViewsByTypeView(    ediTipoBalanza);
        activateViewsByTypeView(    editipbalanzaRepeso);
        activateViewsByTypeView(    ediUbicacionBalanza);
        activateViewsByTypeView(    ediTermofrafo1);
        activateViewsByTypeView(    ediHoraEncendido1);
        activateViewsByTypeView(    ediUbicacion1);
        activateViewsByTypeView(    ediRuma1);
        activateViewsByTypeView(    ediTermofrafo2);
        activateViewsByTypeView(    ediHoraEncendido2);
        activateViewsByTypeView(    ediUbicacion2);
        activateViewsByTypeView(    ediRuma2);
        activateViewsByTypeView(    ediCandadoqsercon);
        activateViewsByTypeView(    ediSelloNaviera);
        activateViewsByTypeView(    ediCableNaviera);
        activateViewsByTypeView(    ediSelloPlastico);
        activateViewsByTypeView(    ediCandadoBotella);
        activateViewsByTypeView(    ediCableExportadora);
        activateViewsByTypeView(    ediSelloAdesivoexpor);
        activateViewsByTypeView(    esiSelloAdhNaviera);
        activateViewsByTypeView(    ediOtherSellos);


        //SPINNERS
        activateViewsByTypeView(  spinnerSelectZona);
        activateViewsByTypeView(  spinnerCondicionBalanza);
        activateViewsByTypeView( spinnertipoCaja);
        activateViewsByTypeView(  spinnertipodePlastico);
        activateViewsByTypeView( spinnertipodeBlanza) ;
        activateViewsByTypeView( spinnertipodeBlanzaRepeso) ;
        activateViewsByTypeView( spinnerubicacionBalanza) ;

        //SWITCHSÇ
        activateViewsByTypeView( switchContenedor);
        activateViewsByTypeView( switchHaybalanza);
        activateViewsByTypeView( switchHayEnsunchado);
        activateViewsByTypeView( switchBalanzaRep);


//iMAGEVIEWS
     //   activateViewsByTypeView( imBtakePic);
      //  activateViewsByTypeView( imBatach);
      //  activateViewsByTypeView( imbAtach_transportista);
      //  activateViewsByTypeView( imbTakePicTransportista);
      //  activateViewsByTypeView( imbAtachSellosLlegada);
     //   activateViewsByTypeView( imbTakePicSellosLLegada);
      //  activateViewsByTypeView( imbAtachDatosContenedor);
     //   activateViewsByTypeView( imbTakePicDatosContenedor);
      //  activateViewsByTypeView( imbAtachPrPostcosecha);
     //   activateViewsByTypeView( imbTakePicPrPostcosecha);


        //Buttons
        Button  btnCheck=findViewById(R.id.btnCheck);
        activateViewsByTypeView( btnCheck);

    }



    private  void addDataENfiledsoTHERviews(SetInformEmbarque1 info1Object,SetInformEmbarque2 info2Object) {



        Log.i("mizona","la zona obtenida en addDataENfiledsoTHERviews (data descargada ) es  "+info1Object.getZona());

        selectValue(spinnerSelectZona,info1Object.getZona()) ;

        selectValue(spinnerCondicionBalanza,info2Object.getCondicionBalanza()) ;
        selectValue(spinnertipoCaja,info2Object.getTipoCaja()) ;
        selectValue(spinnertipodePlastico,info2Object.getTipoPlastico()) ;
        selectValue(spinnertipodeBlanza,info2Object.getTipoDeBalanza()) ;
        selectValue(spinnertipodeBlanzaRepeso,info2Object.getTipoDeBalanzaRepeso()) ;
        selectValue(spinnerubicacionBalanza,info2Object.getUbicacionBalanza()) ;


        if(info1Object.getContenedor().equals(" SI ")) {

            switchContenedor.setChecked(true);


        }else{
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
                Log.i("mizona","existe hurra"+value);
                break;

            }else

            {

                Log.i("mizona","no exiwste "+value);

            }
        }

    }


    private  void addDataEnFields(SetInformEmbarque1 info1Object,SetInformEmbarque2 info2Object)  {
        //usamos los 2 objetos para establecer esta data..

        Log.i("jamisama","la semana es "+info1Object.getSemana());

        ediSemana.setText(info1Object.getSemana());
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        String fechaString = formatter.format(info1Object.getFechaCreacionInf());
        ediFecha.setText(fechaString);

        ediProductor.setText(info1Object.getProductor());
                ediHacienda.setText(info1Object.getHacienda());
        ediCodigo.setText(info1Object.getCodigo());
                ediInscirpMagap.setText(info1Object.getInscirpMagap());
        ediPemarque.setText(info1Object.getPemarque());
                ediZona.setText(info1Object.getZona());
        ediHoraInicio.setText(info1Object.getHoraInicio());

                ediHoraTermino.setText(info1Object.getHoraTermino());
        ediHoraLLegadaContenedor.setText(info1Object.getHoraLlegadaContenedor());

                ediHoraSalidaContenedor.setText(info1Object.getHoraSalidadContenedor());
        ediNguiaRemision.setText(info1Object.getNguiaRemision());
                edi_nguia_transporte.setText(info1Object.get_nguia_transporte());
        ediNtargetaEmbarque.setText(info1Object.getNtargetaEmbarque());
                ediNhojaEvaluacion.setText(String.valueOf(info1Object.getEdiNhojaEvaluacion()));
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
        ediCandadoqsercon.setText(info2Object.getCandadoQsercom());
        ediSelloNaviera.setText(info2Object.getSelloNaviera());
        ediCableNaviera.setText(info2Object.getCableNaviera());
        ediSelloPlastico.setText(info2Object.getSelloPlastico());
        ediCandadoBotella.setText(info2Object.getCandadoBotella());
        ediCableExportadora.setText(info2Object.getCableExportadora());
        ediSelloAdesivoexpor.setText(info2Object.getSelloAdhesivoExportadora());
        esiSelloAdhNaviera.setText(info2Object.getSelloNaviera());
        ediOtherSellos.setText(info2Object.getOtrosSellosEspecif());





    }


private void checkModeVisualitY(){

    Bundle extras = getIntent().getExtras();
    if (extras != null) {
        isModEdicionFields = extras.getBoolean(Variables.KEYEXTRAPREVIEW);

        Log.i("extra","el modo es "+isModEdicionFields);
        //The key argument here must match that used in the other activity
    }


    if(isModEdicionFields){
        activateModeEdit();
        TextView txtModeAdviser=findViewById(R.id.txtModeAdviser);
        txtModeAdviser.setText("Modo Edicion ");
        Variables.isClickable=false;

    }else{
        fab.setImageResource(R.drawable.ic_baseline_edit_24aa);
        Variables.isClickable=false;
        activateModePreview();

    }



    //AGREGMOS LA DATA EN LOS FILEDS
    addDataEnFields(Variables.CurrenReportPart1,Variables.CurrenReportPart2);

    addDataENfiledsoTHERviews(Variables.CurrenReportPart1,Variables.CurrenReportPart2);



     Variables.modoRecicler=Variables.DOWLOAD_IMAGES;

    //inicializamos STORAGE..
    StorageData.initStorageReference();
    dowloadImagesDataReport(Variables.CurrenReportPart1.getUniqueIDinforme());

    dowLoadProducsPostC(Variables.CurrenReportPart1.getUniqueIDinforme());



}


//descargamos las imagenes path....



    //descargamos prodcutos postcosecha...

    void createlistsForReciclerviewsImages(ArrayList<ImagenReport>listImagenReports){


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


            addInfotomap(listImagenReports);


    }


    void addInfotomap(ArrayList<ImagenReport>listImagenReports){
        ImagenReport.hashMapImagesData= new HashMap<>();

        //agregamos adata al mapusnado un bucle

        for(int indice2=0; indice2<listImagenReports.size(); indice2++){

          ImagenReport currentImareportObj=listImagenReports.get(indice2);

          ImagenReport.hashMapImagesData.put(currentImareportObj.getUniqueIdNamePic(),currentImareportObj);

        }


        Variables.hashMapImagesStart =ImagenReport.hashMapImagesData;

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





    void dowLoadProducsPostC(String idAlquePERTENECE){

        RealtimeDB.initDatabasesReference();
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

              //  Log.i("sliexsa","existe"+product.cantidadOtro);


                         if(productxGlobal!=null){
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

     TextInputEditText [] editextArray = {  ediPPC01,ediPPC02,ediPPC03,ediPPC04,ediPPC05,ediPPC06,ediPPC07,
             ediPPC08,ediPPC09, ediPPC010,ediPPC011,ediPPC012,ediPPC013,ediPPC014,ediPPC015,ediPPC016} ;

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







    void dowloadImagesDataReport(String reportUNIQUEidtoSEARCH){ //DESCRAGAMOS EL SEGUNDO
        RealtimeDB.initDatabasesReference();
        // DatabaseReference midatabase=rootDatabaseReference.child("Informes").child("listInformes");
        Query query = RealtimeDB.rootDatabaseReference.child("Informes").child("ImagesData").orderByChild("idReportePerteence").equalTo(reportUNIQUEidtoSEARCH);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<ImagenReport>listImagenData=new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    ImagenReport imagenReport=ds.getValue(ImagenReport.class);
                    listImagenData.add(imagenReport);

                }


                     Variables.listImagenData=listImagenData;

                createlistsForReciclerviewsImages(listImagenData);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i("sliexsa","el error es "+error.getMessage());

            }
        });


    }




}
