package com.tiburela.qsercom.activities.formulariosPrev;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges;
import com.tiburela.qsercom.models.ControlCalidad;
import com.tiburela.qsercom.models.SetInformDatsHacienda;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FormularioControlCalidadPreview extends AppCompatActivity implements View.OnClickListener {
    // initialize variables
    TextView textView;
    boolean[] selectedLanguage;
    HashMap <String, String> hasmapMapControlCalid;
    Button btnSaveControlC;
    TextView textView48;
    HashMap<String, String> hasHmapFieldsOtherViews= new HashMap<>();
    HashMap<String, String> hasMapitemsSelecPosicRechazToUpload;
    private TextInputEditText mEdif2LrgD1;
    private TextInputEditText mEdif2LrgD2;
    private TextInputEditText mEdif2LrgD3;
    private TextInputEditText mEdif2LrgD4;
    private TextInputEditText mEdif2LrgD5;
    private TextInputEditText mEdif2LrgD6;
    private TextInputEditText mEdif2LrgD7;
    private TextInputEditText mEdif2LrgD8;
    private TextInputEditText mEdif2LrgD9;
    private TextInputEditText mEdif2LrgD10;
    private TextInputEditText mEdif2LrgD11;
    private TextInputEditText mEdif2LrgD12;
    private TextInputEditText mEdif2LrgD13;
    private TextInputEditText mEdif2LrgD14;
    private TextInputEditText mEdif2LrgD15;
    private TextInputEditText mEdif2LrgD16;
    private TextInputEditText mEdif2LrgD17;
    private TextInputEditText mEdif2LrgD18;
    private TextInputEditText mEdif2LrgD19;
    private TextInputEditText mEdif2LrgD20;
    private TextInputEditText mEdif2LrgD21;
    private TextInputEditText mEdif2LrgD22;
    private TextInputEditText mEdif2LrgD23;
    private TextInputEditText mEdif2LrgD24;
    private TextInputEditText mEdif2LrgD25;
    private TextInputEditText mEdif2LrgD26;
    private TextInputEditText mEdif2LrgD27;
    private TextInputEditText mEdif2LrgD28;
    private TextInputEditText mEdif2LrgD29;
    private TextInputEditText mEdif2LrgD30;


    private TextInputEditText mEdiLargDeds1;
    private TextInputEditText mEdiLargDeds2;
    private TextInputEditText mEdiLargDeds3;
    private TextInputEditText mEdiLargDeds4;
    private TextInputEditText mEdiLargDeds5;
    private TextInputEditText mEdiLargDeds6;
    private TextInputEditText mEdiLargDeds7;
    private TextInputEditText mEdiLargDeds8;
    private TextInputEditText mEdiLargDeds9;
    private TextInputEditText mEdiLargDeds10;
    private TextInputEditText mEdiLargDeds11;
    private TextInputEditText mEdiLargDeds12;
    private TextInputEditText mEdiLargDeds13;
    private TextInputEditText mEdiLargDeds14;
    private TextInputEditText mEdiLargDeds15;
    private TextInputEditText mEdiLargDeds16;
    private TextInputEditText mEdiLargDeds17;
    private TextInputEditText mEdiLargDeds18;
    private TextInputEditText mEdiLargDeds19;
    private TextInputEditText mEdiLargDeds20;
    private TextInputEditText mEdiLargDeds21;
    private TextInputEditText mEdiLargDeds22;
    private TextInputEditText mEdiLargDeds23;
    private TextInputEditText mEdiLargDeds24;
    private TextInputEditText mEdiLargDeds25;
    private TextInputEditText mEdiLargDeds26;
    private TextInputEditText mEdiLargDeds27;
    private TextInputEditText mEdiLargDeds28;
    private TextInputEditText mEdiLargDeds29;
    private TextInputEditText mEdiLargDeds30;


    private TextInputEditText ediObservacioneszszz;
    // fist fields
    private TextInputEditText mEdiVaporzz;
    private TextInputEditText mEdiProductorzz;
    private TextInputEditText mEdiCodigozz;
    private TextInputEditText mEdiZonazz;
    private TextInputEditText mEdiHaciendazz;
    private TextInputEditText mEdiExportadorazz;
    private TextInputEditText mEdiCompaniazz;
    private TextInputEditText mEdiClientezz;
    private TextInputEditText mEdisemanazz;
    private TextInputEditText mEdiFechazz;
    private TextInputEditText mEdiMagapzz;
    private TextInputEditText mEdiMarcaCajazz;
    private TextInputEditText mEdiTipoEmpazz;
    private TextInputEditText mEdiDestinzz;
    private TextInputEditText mEdiTotalCajaszz;
    private TextInputEditText mEdioCalidaCampzz;
    private TextInputEditText mEdiHoraInizz;
    private TextInputEditText mEdiHoraTermizz;
    private TextInputEditText mEdiContenedorzz;
    private TextInputEditText mEdiSellosnavzz;
    private TextInputEditText mEdiSelloVerzz;
    private TextInputEditText mEdiTermografozz;
    private TextInputEditText mEdiPlacaCarrzz;
    private TextInputEditText mEdiPuertEmbzz;





    HashMap<String , ArrayList<Boolean>> HashMapOfListWhitStatesCHeckb = new HashMap<>(); //serian unas dies listas...
    HashMap<String , ArrayList<Boolean>> HashMapOfListWhitStatesCHeckb2 = new HashMap<>(); //serian unas dies listas...


    //Imageviews defects
    ImageView imgSelecDefc1;
    ImageView imgSelecDefc2;
    ImageView imgSelecDefc3;
    ImageView imgSelecDefc4;
    ImageView imgSelecDefc5;
    ImageView imgSelecDefc6;
    ImageView imgSelecDefc7;
    ImageView imgSelecDefc8;
    ImageView imgSelecDefc9;
    ImageView imgSelecDefc10;


    private ImageView  imgUpdateNumDedxClust;
    private ImageView  imgUpdateNumClusterxCaja;
    private ImageView  imgUpdateCalibBasalYapical;

    ImageView imgUpdateNumPulpaApulpa;


    TextInputEditText ediTimeHoraxx1;
    TextInputEditText ediTimeHoraxx2;;
    TextInputEditText ediTimeHoraxx3;
    TextInputEditText ediTimeHoraxx4;
    TextInputEditText ediTimeHoraxx5;
    TextInputEditText ediTimeHoraxx6;
    TextInputEditText ediTimeHoraxx7;
    TextInputEditText ediTimeHoraxx8;
    TextInputEditText ediTimeHoraxx9;
    TextInputEditText ediTimeHoraxx10;

    TextInputEditText ediPesoL1;
    TextInputEditText ediPesoL2;
    TextInputEditText ediPesoL3;
    TextInputEditText ediPesoL4;
    TextInputEditText ediPesoL5;
    TextInputEditText ediPesoL6;
    TextInputEditText ediPesoL7;
    TextInputEditText ediPesoL8;
    TextInputEditText ediPesoL9;
    TextInputEditText ediPesoL10;

    TextInputEditText ediPH1;
    TextInputEditText ediPH2;
    TextInputEditText ediPH3;
    TextInputEditText ediPH4;
    TextInputEditText ediPH5;
    TextInputEditText ediPH6;
    TextInputEditText ediPH7;
    TextInputEditText ediPH8;
    TextInputEditText ediPH9;
    TextInputEditText ediPH10;

    TextInputEditText ediNumClusInsp1;
    TextInputEditText ediNumClusInsp2;
    TextInputEditText ediNumClusInsp3;
    TextInputEditText ediNumClusInsp4;
    TextInputEditText ediNumClusInsp5;
    TextInputEditText ediNumClusInsp6;
    TextInputEditText ediNumClusInsp7;
    TextInputEditText ediNumClusInsp8;
    TextInputEditText ediNumClusInsp9;
    TextInputEditText ediNumClusInsp10;

    TextInputEditText ediNdedoXclust1;
    TextInputEditText ediNdedoXclust2 ;
    TextInputEditText ediNdedoXclust3 ;
    TextInputEditText ediNdedoXclust4 ;
    TextInputEditText ediNdedoXclust5 ;
    TextInputEditText ediNdedoXclust6 ;
    TextInputEditText ediNdedoXclust7 ;
    TextInputEditText ediNdedoXclust8 ;
    TextInputEditText ediNdedoXclust9 ;
    TextInputEditText ediNdedoXclust10 ;
    TextInputEditText ediNdedoXclust11 ;
    TextInputEditText ediNdedoXclust12 ;
    TextInputEditText ediNdedoXclust13 ;
    TextInputEditText ediNdedoXclust14 ;
    TextInputEditText ediNdedoXclust15 ;
    TextInputEditText ediNdedoXclust16 ;
    TextInputEditText ediNdedoXclust17 ;
    TextInputEditText ediNdedoXclust18 ;
    TextInputEditText ediNdedoXclust19 ;
    TextInputEditText ediNdedoXclust20 ;
    TextInputEditText ediNdedoXclust21 ;
    TextInputEditText ediNdedoXclust22 ;
    TextInputEditText ediNdedoXclust23 ;
    TextInputEditText ediNdedoXclust24 ;
    TextInputEditText ediNdedoXclust25 ;
    TextInputEditText ediNdedoXclust26 ;
    TextInputEditText ediNdedoXclust27;
    TextInputEditText ediNdedoXclust28 ;
    TextInputEditText ediNdedoXclust29 ;
    TextInputEditText ediNdedoXclust30 ;


    //FILA2
    TextInputEditText edif2NdedoXclust1;
    TextInputEditText edif2NdedoXclust2 ;
    TextInputEditText edif2NdedoXclust3 ;
    TextInputEditText edif2NdedoXclust4 ;
    TextInputEditText edif2NdedoXclust5 ;
    TextInputEditText edif2NdedoXclust6 ;
    TextInputEditText edif2NdedoXclust7 ;
    TextInputEditText edif2NdedoXclust8 ;
    TextInputEditText edif2NdedoXclust9 ;
    TextInputEditText edif2NdedoXclust10 ;
    TextInputEditText edif2NdedoXclust11 ;
    TextInputEditText edif2NdedoXclust12 ;
    TextInputEditText edif2NdedoXclust13 ;
    TextInputEditText edif2NdedoXclust14 ;
    TextInputEditText edif2NdedoXclust15 ;
    TextInputEditText edif2NdedoXclust16 ;
    TextInputEditText edif2NdedoXclust17 ;
    TextInputEditText edif2NdedoXclust18 ;
    TextInputEditText edif2NdedoXclust19 ;
    TextInputEditText edif2NdedoXclust20 ;
    TextInputEditText edif2NdedoXclust21 ;
    TextInputEditText edif2NdedoXclust22 ;
    TextInputEditText edif2NdedoXclust23 ;
    TextInputEditText edif2NdedoXclust24 ;
    TextInputEditText edif2NdedoXclust25 ;
    TextInputEditText edif2NdedoXclust26 ;
    TextInputEditText edif2NdedoXclust27;
    TextInputEditText edif2NdedoXclust28 ;
    TextInputEditText edif2NdedoXclust29 ;
    TextInputEditText edif2NdedoXclust30 ;



    ////SEGUNDO CUADRO

    TextInputEditText edif2NdedoXclustxC1;
    TextInputEditText edif2NdedoXclustxC2 ;
    TextInputEditText edif2NdedoXclustxC3 ;
    TextInputEditText edif2NdedoXclustxC4 ;
    TextInputEditText edif2NdedoXclustxC5 ;
    TextInputEditText edif2NdedoXclustxC6 ;
    TextInputEditText edif2NdedoXclustxC7 ;
    TextInputEditText edif2NdedoXclustxC8 ;
    TextInputEditText edif2NdedoXclustxC9 ;
    TextInputEditText edif2NdedoXclustxC10 ;
    TextInputEditText edif2NdedoXclustxC11 ;
    TextInputEditText edif2NdedoXclustxC12 ;
    TextInputEditText edif2NdedoXclustxC13 ;
    TextInputEditText edif2NdedoXclustxC14 ;
    TextInputEditText edif2NdedoXclustxC15 ;
    TextInputEditText edif2NdedoXclustxC16 ;
    TextInputEditText edif2NdedoXclustxC17 ;
    TextInputEditText edif2NdedoXclustxC18 ;
    TextInputEditText edif2NdedoXclustxC19 ;
    TextInputEditText edif2NdedoXclustxC20 ;



    TextInputEditText ediNdedoXclustXc1;
    TextInputEditText ediNdedoXclustXc2 ;
    TextInputEditText ediNdedoXclustXc3 ;
    TextInputEditText ediNdedoXclustXc4 ;
    TextInputEditText ediNdedoXclustXc5 ;
    TextInputEditText ediNdedoXclustXc6 ;
    TextInputEditText ediNdedoXclustXc7 ;
    TextInputEditText ediNdedoXclustXc8 ;
    TextInputEditText ediNdedoXclustXc9 ;
    TextInputEditText ediNdedoXclustXc10 ;
    TextInputEditText ediNdedoXclustXc11 ;
    TextInputEditText ediNdedoXclustXc12 ;
    TextInputEditText ediNdedoXclustXc13 ;
    TextInputEditText ediNdedoXclustXc14 ;
    TextInputEditText ediNdedoXclustXc15 ;
    TextInputEditText ediNdedoXclustXc16 ;
    TextInputEditText ediNdedoXclustXc17 ;
    TextInputEditText ediNdedoXclustXc18 ;
    TextInputEditText ediNdedoXclustXc19 ;
    TextInputEditText ediNdedoXclustXc20 ;

    TextInputEditText ediCalByA1;
    TextInputEditText ediCalByA2 ;
    TextInputEditText ediCalByA3 ;
    TextInputEditText ediCalByA4 ;
    TextInputEditText ediCalByA5 ;
    TextInputEditText ediCalByA6 ;
    TextInputEditText ediCalByA7 ;
    TextInputEditText ediCalByA8 ;
    TextInputEditText ediCalByA9 ;
    TextInputEditText ediCalByA10 ;
    TextInputEditText ediCalByA11 ;
    TextInputEditText ediCalByA12 ;
    TextInputEditText ediCalByA13 ;
    TextInputEditText ediCalByA14 ;
    TextInputEditText ediCalByA15 ;
    TextInputEditText ediCalByA16 ;
    TextInputEditText ediCalByA17 ;
    TextInputEditText ediCalByA18 ;
    TextInputEditText ediCalByA19 ;
    TextInputEditText ediCalByA20 ;
    TextInputEditText ediCalByA21 ;


    ///////////////////
    TextInputEditText edif2Calib1;
    TextInputEditText edif2Calib2 ;
    TextInputEditText edif2Calib3 ;
    TextInputEditText edif2Calib4 ;
    TextInputEditText edif2Calib5 ;
    TextInputEditText edif2Calib6 ;
    TextInputEditText edif2Calib7 ;
    TextInputEditText edif2Calib8 ;
    TextInputEditText edif2Calib9 ;
    TextInputEditText edif2Calib10 ;
    TextInputEditText edif2Calib11 ;
    TextInputEditText edif2Calib12 ;
    TextInputEditText edif2Calib13 ;
    TextInputEditText edif2Calib14 ;
    TextInputEditText edif2Calib15 ;
    TextInputEditText edif2Calib16 ;
    TextInputEditText edif2Calib17 ;
    TextInputEditText edif2Calib18 ;
    TextInputEditText edif2Calib19 ;
    TextInputEditText edif2Calib20 ;
    TextInputEditText edif2Calib21 ;
    TextInputEditText edif2Calib22 ;
    //imgvEMPAQUES



    ImageView imvEmpaque1;
    ImageView imvEmpaque2;
    ImageView imvEmpaque3;
    ImageView imvEmpaque4;
    ImageView imvEmpaque5;
    ImageView imvEmpaque6;
    ImageView imvEmpaque7;
    ImageView imvEmpaque8;
    ImageView imvEmpaque9;
    ImageView imvEmpaque10;

    Spinner spinnerDef1;
    Spinner spinnerDef2;
    Spinner spinnerDef3;
    Spinner spinnerDef4;
    Spinner spinnerDef5;
    Spinner spinnerDef6;
    Spinner spinnerDef7;
    Spinner spinnerDef8;
    Spinner spinnerDef9;
    Spinner spinnerDef10;


    TextView txtTotal1;
    TextView txtTotal2;
    TextView txtTotal3;
    TextView txtTotal4;
    TextView txtTotal5;
    TextView txtTotal6;
    TextView txtTotal7;
    TextView txtTotal8;
    TextView txtTotal9;
    TextView txtTotal10;

    TextView txtTotal;




    ArrayList<Integer> langList = new ArrayList<>();

    ArrayList<ArrayList<Boolean>> listOfLISTState = new ArrayList<>(); //serian unas dies listas...

    ArrayList<ArrayList<Boolean>> listOfLISTState2 = new ArrayList<>(); //serian unas dies listas...


    String[] arrayDefect1;

    String[] arrayDefect2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_defectos_preview);
        findviewsIds();
        addListnners();
        eventoUploadFormulario();

        arrayDefect1 = getResources().getStringArray(R.array.array_defectos_fruta);
        arrayDefect2 = getResources().getStringArray(R.array.array_defectos_empaque2);



    }

    private void inicialiceListOfListChekedItems () {

        ImageView  [] miArrayImgSelecs = {
                imgSelecDefc1,imgSelecDefc2,imgSelecDefc3,imgSelecDefc4,imgSelecDefc5,
                imgSelecDefc6, imgSelecDefc7,imgSelecDefc8, imgSelecDefc9,imgSelecDefc10 } ;


        ImageView  [] arrayImgsSelect2 = {
                imvEmpaque1,imvEmpaque2,imvEmpaque3,imvEmpaque4,imvEmpaque5,
                imvEmpaque6, imvEmpaque7,imvEmpaque8, imvEmpaque9,imvEmpaque10 } ;


        arrayDefect1 = getResources().getStringArray(R.array.array_defectos_fruta);
        arrayDefect2 = getResources().getStringArray(R.array.array_defectos_empaque2);


        for (int i = 0; i <10; i++) {

            ArrayList<Boolean> listItem = new ArrayList<>(); //serian unas dies listas...


            for (int j = 0; j < arrayDefect1.length; j++) {

                listItem.add(false);
                //agregamos valors a esta lista
            }


            HashMapOfListWhitStatesCHeckb.put(String.valueOf(miArrayImgSelecs[i].getId()),listItem);
            ///  listOfLISTState.add(listItem);

        }



        for (int i = 0; i <10; i++) {

            ArrayList<Boolean> listItem2 = new ArrayList<>(); //serian unas dies listas...

            for (int j = 0; j < arrayDefect2.length; j++) {

                listItem2.add(false);
                //agregamos valors a esta lista
            }

            HashMapOfListWhitStatesCHeckb2.put(String.valueOf(arrayImgsSelect2[i].getId()),listItem2);

        }



    }

    void  configInitialHashasmapsChekedItemsWhitDowload(HashMap<String , ArrayList<Boolean>> HashMapOfListWhitStatesCHeckb ,
                                                        HashMap<String , ArrayList<Boolean>>  HashMapOfListWhitStatesCHeckb2,
                                                        HashMap<String , String>  HashMapDowload) {



        //iteramos el hasmap descragado ....

        for (Map.Entry<String, String> entry : HashMapDowload.entrySet()) {
            String key = entry.getKey();
            String  value = entry.getValue();

            String posicionesEditarArray  []= value.split(",") ;

            if(HashMapOfListWhitStatesCHeckb.containsKey(key)){


                for(int indice2=0; indice2<posicionesEditarArray.length; indice2++) {

                    int currrentPoscionParaCambiar=Integer.parseInt(posicionesEditarArray[indice2]);

                    HashMapOfListWhitStatesCHeckb.get(key).set(currrentPoscionParaCambiar,true);

                }

                //editamos este valor



            }else if(HashMapOfListWhitStatesCHeckb2.containsKey(key)) {

                for(int indice2=0; indice2<posicionesEditarArray.length; indice2++) {

                    int currrentPoscionParaCambiar=Integer.parseInt(posicionesEditarArray[indice2]);

                    HashMapOfListWhitStatesCHeckb2.get(key).set(currrentPoscionParaCambiar,true);

                }





            }

            //BUSCAMOS EN EL ARRAY PRIMERO


            //BUSCMAOS EN SL OTRO ARRAY



        }





    }




    //determinar que posicion pulso o si pusla este hacer esto

    @Override
    protected void onStart() {
        super.onStart();

        inicialiceListOfListChekedItems();

        RealtimeDB.initDatabasesRootOnly();

        Log.i("sibmba","el text es "+Variables.currenControlCalReport.getKeyWhereLocateasHmapFieldsRecha());


        dowloadCoontrolClidadMapAndCallSetDATAINviews(Variables.currenControlCalReport.getKeyWhereLocateasHmapFieldsRecha());

        dowloadAllSelectDefectosPosiciones(Variables.currenControlCalReport.getKeyDondeEstaraHasmapDefecSelec());

        // getPakinkListMap(Variables.currenControlCalReport.getKeyWhereLocateasHmapFieldsRecha());



        ///////


    }



    //aqui ya deberiamos pasarle los dos objetos una vez descargados
    private void setDataInViews(HashMap <String,String>miMapa, ControlCalidad controlCalidad){

        mEdiVaporzz.setText(controlCalidad.getVapor());
        ediObservacioneszszz.setText(controlCalidad.getObservaciones());
        mEdiProductorzz.setText(controlCalidad.getProductor());
        mEdiCodigozz .setText(controlCalidad.getCodigo());
        mEdiZonazz .setText(controlCalidad.getZona());
        mEdiHaciendazz .setText(controlCalidad.getHacienda());
        mEdiExportadorazz.setText(controlCalidad.getExportadora());
        mEdiCompaniazz .setText(controlCalidad.getCompania());
        mEdiClientezz .setText(controlCalidad.getCliente());
        mEdisemanazz.setText(String.valueOf(controlCalidad.getSemana()));
        mEdiFechazz .setText(controlCalidad.getFecha());
        mEdiMagapzz.setText(controlCalidad.getMagap());
        mEdiMarcaCajazz .setText(controlCalidad.getMarcaCaja());
        mEdiTipoEmpazz .setText(controlCalidad.getTipoEmpaque());
        mEdiDestinzz .setText(controlCalidad.getDestino());
        mEdiTotalCajaszz.setText(String.valueOf(controlCalidad.getTotalCajas()));
        mEdioCalidaCampzz .setText(controlCalidad.getCalidaCamp());
        mEdiHoraInizz .setText(controlCalidad.getHoraIni());
        mEdiHoraTermizz .setText(controlCalidad.getHoraTermi());
        mEdiContenedorzz .setText(controlCalidad.getContenedor());
        mEdiSellosnavzz.setText(controlCalidad.getSellosnav());
        mEdiSelloVerzz .setText(controlCalidad.getSelloVerificadora());
        mEdiTermografozz.setText(controlCalidad.getTermografo());
        mEdiPlacaCarrzz .setText(controlCalidad.getPlacaCarro());
        mEdiPuertEmbzz .setText(controlCalidad.getPuertEmbarq());



        TextInputEditText arrayAllFields[] = {

                ediTimeHoraxx1, ediTimeHoraxx2, ediTimeHoraxx3, ediTimeHoraxx4, ediTimeHoraxx5, ediTimeHoraxx6, ediTimeHoraxx7, ediTimeHoraxx8,
                ediTimeHoraxx9, ediTimeHoraxx10, ediPesoL1, ediPesoL2, ediPesoL3, ediPesoL4, ediPesoL5, ediPesoL6, ediPesoL7, ediPesoL8,
                ediPesoL9, ediPesoL10, ediPH1, ediPH2, ediPH3, ediPH4, ediPH5, ediPH6, ediPH7, ediPH8, ediPH9, ediPH10, ediNumClusInsp1,
                ediNumClusInsp2, ediNumClusInsp3, ediNumClusInsp4, ediNumClusInsp5, ediNumClusInsp6, ediNumClusInsp7, ediNumClusInsp8,
                ediNumClusInsp9, ediNumClusInsp10, ediNdedoXclust1, ediNdedoXclust2 , ediNdedoXclust3 , ediNdedoXclust4 , ediNdedoXclust5 ,
                ediNdedoXclust6 , ediNdedoXclust7 , ediNdedoXclust8 , ediNdedoXclust9 , ediNdedoXclust10 , ediNdedoXclust11 , ediNdedoXclust12 ,
                ediNdedoXclust13 , ediNdedoXclust14 , ediNdedoXclust15 , ediNdedoXclust16 , ediNdedoXclust17 , ediNdedoXclust18 ,
                ediNdedoXclust19 , ediNdedoXclust20 , ediNdedoXclust21 , ediNdedoXclust22 , ediNdedoXclust23 , ediNdedoXclust24 ,
                ediNdedoXclust25 , ediNdedoXclust26 , ediNdedoXclust27, ediNdedoXclust28 , ediNdedoXclust29 , ediNdedoXclust30 ,
                edif2NdedoXclust1, edif2NdedoXclust2 , edif2NdedoXclust3 , edif2NdedoXclust4 , edif2NdedoXclust5 , edif2NdedoXclust6 ,
                edif2NdedoXclust7 , edif2NdedoXclust8 , edif2NdedoXclust9 , edif2NdedoXclust10 , edif2NdedoXclust11 , edif2NdedoXclust12 ,
                edif2NdedoXclust13 , edif2NdedoXclust14 , edif2NdedoXclust15 , edif2NdedoXclust16 , edif2NdedoXclust17 ,
                edif2NdedoXclust18 , edif2NdedoXclust19 , edif2NdedoXclust20 , edif2NdedoXclust21 , edif2NdedoXclust22 ,
                edif2NdedoXclust23 , edif2NdedoXclust24 , edif2NdedoXclust25 , edif2NdedoXclust26 , edif2NdedoXclust27,
                edif2NdedoXclust28 , edif2NdedoXclust29 , edif2NdedoXclust30 , edif2NdedoXclustxC1, edif2NdedoXclustxC2 ,
                edif2NdedoXclustxC3 , edif2NdedoXclustxC4 , edif2NdedoXclustxC5 , edif2NdedoXclustxC6 , edif2NdedoXclustxC7 ,
                edif2NdedoXclustxC8 , edif2NdedoXclustxC9 , edif2NdedoXclustxC10 , edif2NdedoXclustxC11 , edif2NdedoXclustxC12 ,
                edif2NdedoXclustxC13 , edif2NdedoXclustxC14 , edif2NdedoXclustxC15 , edif2NdedoXclustxC16 , edif2NdedoXclustxC17 ,
                edif2NdedoXclustxC18 , edif2NdedoXclustxC19 , edif2NdedoXclustxC20 , ediNdedoXclustXc1, ediNdedoXclustXc2 ,
                ediNdedoXclustXc3 , ediNdedoXclustXc4 , ediNdedoXclustXc5 , ediNdedoXclustXc6 , ediNdedoXclustXc7 ,ediNdedoXclustXc7,
                ediNdedoXclustXc9 , ediNdedoXclustXc10 , ediNdedoXclustXc11 , ediNdedoXclustXc12 , ediNdedoXclustXc13 , ediNdedoXclustXc14 ,
                ediNdedoXclustXc15 , ediNdedoXclustXc16 , ediNdedoXclustXc17 , ediNdedoXclustXc18 , ediNdedoXclustXc19,ediNdedoXclustXc20 , ediCalByA1, ediCalByA2 , ediCalByA3 , ediCalByA4 , ediCalByA5 ,
                ediCalByA6 , ediCalByA7 , ediCalByA8 , ediCalByA9 , ediCalByA10 , ediCalByA11 , ediCalByA12 , ediCalByA13,
                ediCalByA14 , ediCalByA15 , ediCalByA16 , ediCalByA17 , ediCalByA18 , ediCalByA19 , edif2NdedoXclust27 , ediCalByA21 ,
                edif2Calib1, edif2Calib2 , edif2Calib3 , edif2Calib4 , edif2Calib5 , edif2Calib6 , edif2Calib7 , edif2Calib8 ,
                edif2Calib9 , edif2Calib10 , edif2Calib11 , edif2Calib12 , edif2Calib13 , edif2Calib14 , edif2Calib15 ,
                edif2Calib16 , edif2Calib17 , edif2Calib18 , edif2Calib19 , edif2Calib20 , edif2Calib21 , edif2Calib22 ,

                mEdiLargDeds1,mEdiLargDeds2,mEdiLargDeds3,mEdiLargDeds4,mEdiLargDeds5,
                mEdiLargDeds6,mEdiLargDeds7,mEdiLargDeds8,mEdiLargDeds9,mEdiLargDeds10,
                mEdiLargDeds11,mEdiLargDeds12,mEdiLargDeds13,mEdiLargDeds14,mEdiLargDeds15,
                mEdiLargDeds16,mEdiLargDeds17,mEdiLargDeds18,mEdiLargDeds19,mEdiLargDeds20,
                mEdiLargDeds21,mEdiLargDeds22,mEdiLargDeds23,mEdiLargDeds24,mEdiLargDeds25,
                mEdiLargDeds26,mEdiLargDeds27,mEdiLargDeds28,mEdiLargDeds29,mEdiLargDeds30,



        };



        for (Map.Entry<String, String > entry : miMapa.entrySet()) {
            String keyAndIdOfView = entry.getKey();
            String valueOfItem = entry.getValue();

            TextInputEditText currenTextImput ;
            if(! keyAndIdOfView.equals("0")){
                 currenTextImput= Utils.getTexImputEditextByidORkey(arrayAllFields,Integer.parseInt(keyAndIdOfView));

                if(currenTextImput!=null){ //si es nulo
                    currenTextImput.setText(valueOfItem);

                }

            }









        }



    }



    private void dowloadCoontrolClidadMapAndCallSetDATAINviews (String nodeLocateHasmapControlC){

        Log.i("hameha","el NODEKey es : "+nodeLocateHasmapControlC);

        ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("ControCalidHasmap").
                child(nodeLocateHasmapControlC).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        hasmapMapControlCalid =new HashMap<>();


                        for (DataSnapshot dss : dataSnapshot.getChildren()) {
                            String key = dss.getKey();

                            String  fieldData =dss.getValue(String.class);

                            //   HashMap packinKey = dss.getValue( String.class);

                            //   Log.i("misadhd","el size del mapa es "+ packingListMap.size());
                            Log.i("hameha","el key es "+key +"y el; field data es "+fieldData );


                            if (fieldData!=null) {///

                                hasmapMapControlCalid.put(key,fieldData);

                            }
                        }

                        Log.i("solater","el size de hasmap es "+hasmapMapControlCalid.size());


                        setDataInViews(hasmapMapControlCalid,Variables.currenControlCalReport);



                        //realizamos la opreacion con la info de los editext
                         showResultNumClusteroManoProduct();
                        showResultNumeroClusterxCajaProduct();
                        showResultOfCalibraEntreBasalYapiclProduct();
                       showResultlargoDedosPulgaPulpaApulpa();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("misadhd","el error es "+ databaseError.getMessage());



                    }
                });




    }


    private void dowloadAllSelectDefectosPosiciones(String nodeLocateHasmapDefectSelecc){

        HashMap<String, String> hasmapMapdEFECTOSchekeed= new HashMap<>();

        Log.i("hameha","el NODEKey es : "+nodeLocateHasmapDefectSelecc);

        ValueEventListener seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("DefectoSelecionadosHashmap").child(nodeLocateHasmapDefectSelecc).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    String key = dss.getKey();

                    String  fieldData =dss.getValue(String.class);

                    Log.i("hameha","el key es "+key);
                    Log.i("hameha"," y el fiel data es "+fieldData);


                    if (fieldData!=null && ! fieldData.equals("EMPTY")) {///

                        hasmapMapdEFECTOSchekeed.put(key,fieldData);

                    }
                }



                configInitialHashasmapsChekedItemsWhitDowload(HashMapOfListWhitStatesCHeckb,HashMapOfListWhitStatesCHeckb2,hasmapMapdEFECTOSchekeed);

                //   setDataInViews(hasmapMapControlCalid,Variables.currenControlCalReport);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("misadhd","el error es "+ databaseError.getMessage());



            }
        });



    }



    private void findviewsIds() {
        //first views fields
        ediObservacioneszszz= findViewById(R.id.ediObservacioneszszz);

        btnSaveControlC=findViewById(R.id.btnSaveControlC);
        mEdiVaporzz = findViewById(R.id.ediVaporzz);
        mEdiProductorzz = findViewById(R.id.ediProductorzz);
        mEdiCodigozz = findViewById(R.id.ediCodigozz);
        mEdiZonazz = findViewById(R.id.ediZonazz);
        mEdiHaciendazz = findViewById(R.id.ediHaciendazz);
        mEdiExportadorazz = findViewById(R.id.ediExportadorazz);
        mEdiCompaniazz = findViewById(R.id.ediCompaniazz);
        mEdiClientezz = findViewById(R.id.ediClientezz);
        mEdisemanazz = findViewById(R.id.ediSemanazz);
        mEdiFechazz = findViewById(R.id.ediFechazz);
        mEdiMagapzz = findViewById(R.id.ediMagapzz);
        mEdiMarcaCajazz = findViewById(R.id.ediMarcaCajazz);
        mEdiTipoEmpazz = findViewById(R.id.ediTipoEmpazz);
        mEdiDestinzz = findViewById(R.id.ediDestinzz);
        mEdiTotalCajaszz = findViewById(R.id.ediTotalCajaszz);
        mEdioCalidaCampzz = findViewById(R.id.edioCalidaCampzz);
        mEdiHoraInizz = findViewById(R.id.ediHoraInizz);
        mEdiHoraTermizz = findViewById(R.id.ediHoraTermizz);
        mEdiContenedorzz = findViewById(R.id.ediContenedorzz);
        mEdiSellosnavzz = findViewById(R.id.ediSellosnavzz);
        mEdiSelloVerzz = findViewById(R.id.ediSelloVerzz);
        mEdiTermografozz = findViewById(R.id.ediTermografozz);
        mEdiPlacaCarrzz = findViewById(R.id.ediPlacaCarrzz);
        mEdiPuertEmbzz = findViewById(R.id.ediPuertEmbzz);




        imgSelecDefc1=findViewById(R.id.imgSelecDefc1);
        imgSelecDefc2=findViewById(R.id.imgSelecDefc2);
        imgSelecDefc3=findViewById(R.id.imgSelecDefc3);
        imgSelecDefc4=findViewById(R.id.imgSelecDefc4);
        imgSelecDefc5=findViewById(R.id.imgSelecDefc5);
        imgSelecDefc6=findViewById(R.id.imgSelecDefc6);
        imgSelecDefc7=findViewById(R.id.imgSelecDefc7);
        imgSelecDefc8=findViewById(R.id.imgSelecDefc8);
        imgSelecDefc9=findViewById(R.id.imgSelecDefc9);
        imgSelecDefc10=findViewById(R.id.imgSelecDefc10);

        imvEmpaque1=findViewById(R.id.imvEmpaque1);
        imvEmpaque2=findViewById(R.id.imvEmpaque2);
        imvEmpaque3=findViewById(R.id.imvEmpaque3);
        imvEmpaque4=findViewById(R.id.imvEmpaque4);
        imvEmpaque5=findViewById(R.id.imvEmpaque5);
        imvEmpaque6=findViewById(R.id.imvEmpaque6);
        imvEmpaque7=findViewById(R.id.imvEmpaque7);
        imvEmpaque8=findViewById(R.id.imvEmpaque8);
        imvEmpaque9=findViewById(R.id.imvEmpaque9);
        imvEmpaque10=findViewById(R.id.imvEmpaque10);


         imgUpdateNumPulpaApulpa =findViewById(R.id.imgUpdateNumPulpaApulpa);


        spinnerDef1=findViewById(R.id.spinnerDef1);
        spinnerDef1=findViewById(R.id.spinnerDef2);
        spinnerDef1=findViewById(R.id.spinnerDef3);
        spinnerDef1=findViewById(R.id.spinnerDef4);
        spinnerDef1=findViewById(R.id.spinnerDef5);
        spinnerDef1=findViewById(R.id.spinnerDef6);
        spinnerDef1=findViewById(R.id.spinnerDef7);
        spinnerDef1=findViewById(R.id.spinnerDef8);
        spinnerDef1=findViewById(R.id.spinnerDef9);
        spinnerDef1=findViewById(R.id.spinnerDef10);


        txtTotal1=findViewById(R.id.txtTotal1);
        txtTotal2=findViewById(R.id.txtTotal2);
        txtTotal3=findViewById(R.id.txtTotal3);
        txtTotal4=findViewById(R.id.txtTotal4);
        txtTotal5=findViewById(R.id.txtTotal5);
        txtTotal6=findViewById(R.id.txtTotal6);
        txtTotal7=findViewById(R.id.txtTotal7);
        txtTotal8=findViewById(R.id.txtTotal8);
        txtTotal9=findViewById(R.id.txtTotal9);
        txtTotal10=findViewById(R.id.txtTotal10);


        textView48=findViewById(R.id.textView48);

        txtTotal=findViewById(R.id.txttotal);

        ediPesoL1=findViewById(R.id. ediPesoL1);
        ediPesoL2=findViewById(R.id. ediPesoL2);
        ediPesoL3=findViewById(R.id. ediPesoL3);
        ediPesoL4=findViewById(R.id. ediPesoL4);
        ediPesoL5=findViewById(R.id. ediPesoL5);
        ediPesoL6=findViewById(R.id. ediPesoL6);
        ediPesoL7=findViewById(R.id. ediPesoL7);
        ediPesoL8=findViewById(R.id. ediPesoL8);
        ediPesoL9=findViewById(R.id. ediPesoL9);
        ediPesoL10=findViewById(R.id. ediPesoL10);

        ediPH1=findViewById(R.id. ediPH1);
        ediPH2=findViewById(R.id. ediPH2);
        ediPH3=findViewById(R.id. ediPH3);
        ediPH4=findViewById(R.id. ediPH4);
        ediPH5=findViewById(R.id. ediPH5);
        ediPH6=findViewById(R.id. ediPH6);
        ediPH7=findViewById(R.id. ediPH7);
        ediPH8=findViewById(R.id. ediPH8);
        ediPH9=findViewById(R.id. ediPH9);
        ediPH10=findViewById(R.id. ediPH10);


        ediNumClusInsp1=findViewById(R.id.ediNumClusInsp1 );
        ediNumClusInsp2=findViewById(R.id.ediNumClusInsp2 );
        ediNumClusInsp3=findViewById(R.id.ediNumClusInsp3 );
        ediNumClusInsp4=findViewById(R.id.ediNumClusInsp4 );
        ediNumClusInsp5=findViewById(R.id.ediNumClusInsp5 );
        ediNumClusInsp6=findViewById(R.id.ediNumClusInsp6 );
        ediNumClusInsp7=findViewById(R.id.ediNumClusInsp7 );
        ediNumClusInsp8=findViewById(R.id.ediNumClusInsp8 );
        ediNumClusInsp9=findViewById(R.id.ediNumClusInsp9 );
        ediNumClusInsp10=findViewById(R.id.ediNumClusInsp10 );


        ediNdedoXclust1=findViewById(R.id.ediNdedoXclust1);
        ediNdedoXclust2=findViewById(R.id.ediNdedoXclust2);
        ediNdedoXclust3=findViewById(R.id.ediNdedoXclust3) ;
        ediNdedoXclust4=findViewById(R.id.ediNdedoXclust4);
        ediNdedoXclust5=findViewById(R.id.ediNdedoXclust5);




        ediTimeHoraxx1=findViewById(R.id.ediTimeHoraxx1);
        ediTimeHoraxx2=findViewById(R.id.ediTimeHoraxx2);
        ediTimeHoraxx3=findViewById(R.id.ediTimeHoraxx3);
        ediTimeHoraxx4=findViewById(R.id.ediTimeHoraxx4);
        ediTimeHoraxx5=findViewById(R.id.ediTimeHoraxx5);
        ediTimeHoraxx6=findViewById(R.id.ediTimeHoraxx6);
        ediTimeHoraxx7=findViewById(R.id.ediTimeHoraxx7);
        ediTimeHoraxx8=findViewById(R.id.ediTimeHoraxx8);
        ediTimeHoraxx9=findViewById(R.id.ediTimeHoraxx9);
        ediTimeHoraxx10=findViewById(R.id.ediTimeHoraxx10);


        ediNdedoXclust6=findViewById(R.id.ediNdedoXclust6);
        ediNdedoXclust7=findViewById(R.id.ediNdedoXclust7);
        ediNdedoXclust8=findViewById(R.id.ediNdedoXclust8);
        ediNdedoXclust9=findViewById(R.id.ediNdedoXclust9);
        ediNdedoXclust10=findViewById(R.id.ediNdedoXclust10);
        ediNdedoXclust11=findViewById(R.id.ediNdedoXclust11);
        ediNdedoXclust12=findViewById(R.id.ediNdedoXclust12);
        ediNdedoXclust13=findViewById(R.id.ediNdedoXclust13);
        ediNdedoXclust14=findViewById(R.id.ediNdedoXclust14);
        ediNdedoXclust15=findViewById(R.id.ediNdedoXclust15);
        ediNdedoXclust16=findViewById(R.id.ediNdedoXclust16);
        ediNdedoXclust17=findViewById(R.id.ediNdedoXclust17);
        ediNdedoXclust18=findViewById(R.id.ediNdedoXclust18);
        ediNdedoXclust19=findViewById(R.id.ediNdedoXclust19);
        ediNdedoXclust20=findViewById(R.id.ediNdedoXclust20);
        ediNdedoXclust21=findViewById(R.id.ediNdedoXclust21);
        ediNdedoXclust22=findViewById(R.id.ediNdedoXclust22);
        ediNdedoXclust23=findViewById(R.id.ediNdedoXclust23);
        ediNdedoXclust24=findViewById(R.id.ediNdedoXclust24);
        ediNdedoXclust25=findViewById(R.id.ediNdedoXclust25);
        ediNdedoXclust26=findViewById(R.id.ediNdedoXclust26);
        ediNdedoXclust27=findViewById(R.id.ediNdedoXclust27);
        ediNdedoXclust28=findViewById(R.id.ediNdedoXclust28);
        ediNdedoXclust29=findViewById(R.id.ediNdedoXclust29);
        ediNdedoXclust30=findViewById(R.id.ediNdedoXclust30);


        edif2NdedoXclust1=findViewById(R.id.edif2NdedoXclust1);
        edif2NdedoXclust2=findViewById(R.id.edif2NdedoXclust2);
        edif2NdedoXclust3=findViewById(R.id.edif2NdedoXclust3);
        edif2NdedoXclust4=findViewById(R.id.edif2NdedoXclust4);
        edif2NdedoXclust5=findViewById(R.id.edif2NdedoXclust5);
        edif2NdedoXclust6=findViewById(R.id.edif2NdedoXclust6);
        edif2NdedoXclust7=findViewById(R.id.edif2NdedoXclust7);
        edif2NdedoXclust8=findViewById(R.id.edif2NdedoXclust8);
        edif2NdedoXclust9=findViewById(R.id.edif2NdedoXclust9);
        edif2NdedoXclust10=findViewById(R.id.edif2NdedoXclust10);
        edif2NdedoXclust11=findViewById(R.id.edif2NdedoXclust11);
        edif2NdedoXclust12=findViewById(R.id.edif2NdedoXclust12);
        edif2NdedoXclust13=findViewById(R.id.edif2NdedoXclust13);
        edif2NdedoXclust14=findViewById(R.id.edif2NdedoXclust14);
        edif2NdedoXclust15=findViewById(R.id.edif2NdedoXclust15);
        edif2NdedoXclust16=findViewById(R.id.edif2NdedoXclust16);
        edif2NdedoXclust17=findViewById(R.id.edif2NdedoXclust17);
        edif2NdedoXclust18=findViewById(R.id.edif2NdedoXclust18);
        edif2NdedoXclust19=findViewById(R.id.edif2NdedoXclust19);
        edif2NdedoXclust20=findViewById(R.id.edif2NdedoXclust20);
        edif2NdedoXclust21=findViewById(R.id.edif2NdedoXclust21);
        edif2NdedoXclust22=findViewById(R.id.edif2NdedoXclust22);
        edif2NdedoXclust23=findViewById(R.id.edif2NdedoXclust23);
        edif2NdedoXclust24=findViewById(R.id.edif2NdedoXclust24);
        edif2NdedoXclust25=findViewById(R.id.edif2NdedoXclust25);
        edif2NdedoXclust26=findViewById(R.id.edif2NdedoXclust26);
        edif2NdedoXclust27=findViewById(R.id.edif2NdedoXclust27);
        edif2NdedoXclust28=findViewById(R.id.edif2NdedoXclust28);
        edif2NdedoXclust29=findViewById(R.id.edif2NdedoXclust29);
        edif2NdedoXclust30=findViewById(R.id.edif2NdedoXclust30);


        edif2NdedoXclustxC1=findViewById(R.id.edif2NdedoXclustxC1);
        edif2NdedoXclustxC2=findViewById(R.id.edif2NdedoXclustxC2);
        edif2NdedoXclustxC3=findViewById(R.id.edif2NdedoXclustxC3);
        edif2NdedoXclustxC4=findViewById(R.id.edif2NdedoXclustxC4);
        edif2NdedoXclustxC5=findViewById(R.id.edif2NdedoXclustxC5);
        edif2NdedoXclustxC6=findViewById(R.id.edif2NdedoXclustxC6);
        edif2NdedoXclustxC7=findViewById(R.id.edif2NdedoXclustxC7);
        edif2NdedoXclustxC8=findViewById(R.id.edif2NdedoXclustxC8);
        edif2NdedoXclustxC9=findViewById(R.id.edif2NdedoXclustxC9);
        edif2NdedoXclustxC10=findViewById(R.id.edif2NdedoXclustxC10);
        edif2NdedoXclustxC11=findViewById(R.id.edif2NdedoXclustxC11);
        edif2NdedoXclustxC12=findViewById(R.id.edif2NdedoXclustxC12);
        edif2NdedoXclustxC13=findViewById(R.id.edif2NdedoXclustxC13);
        edif2NdedoXclustxC14=findViewById(R.id.edif2NdedoXclustxC14);
        edif2NdedoXclustxC15=findViewById(R.id.edif2NdedoXclustxC15);
        edif2NdedoXclustxC16=findViewById(R.id.edif2NdedoXclustxC16);
        edif2NdedoXclustxC17=findViewById(R.id.edif2NdedoXclustxC17);
        edif2NdedoXclustxC18=findViewById(R.id.edif2NdedoXclustxC18);
        edif2NdedoXclustxC19=findViewById(R.id.edif2NdedoXclustxC19);
        edif2NdedoXclustxC20=findViewById(R.id.edif2NdedoXclustxC20);





        ediNdedoXclustXc1=findViewById(R.id.ediNdedoXclustXc1);
        ediNdedoXclustXc2=findViewById(R.id.ediNdedoXclustXc2);
        ediNdedoXclustXc3=findViewById(R.id.ediNdedoXclustXc3);
        ediNdedoXclustXc4=findViewById(R.id.ediNdedoXclustXc4);
        ediNdedoXclustXc5=findViewById(R.id.ediNdedoXclustXc5);
        ediNdedoXclustXc6=findViewById(R.id.ediNdedoXclustXc6);
        ediNdedoXclustXc7=findViewById(R.id.ediNdedoXclustXc7);
        ediNdedoXclustXc8=findViewById(R.id.ediNdedoXclustXc8);
        ediNdedoXclustXc9=findViewById(R.id.ediNdedoXclustXc9);
        ediNdedoXclustXc10=findViewById(R.id.ediNdedoXclustXc10);
        ediNdedoXclustXc11=findViewById(R.id.ediNdedoXclustXc11);
        ediNdedoXclustXc12=findViewById(R.id.ediNdedoXclustXc12);
        ediNdedoXclustXc13=findViewById(R.id.ediNdedoXclustXc13);
        ediNdedoXclustXc14=findViewById(R.id.ediNdedoXclustXc14);
        ediNdedoXclustXc15=findViewById(R.id.ediNdedoXclustXc15);
        ediNdedoXclustXc16=findViewById(R.id.ediNdedoXclustXc16);
        ediNdedoXclustXc17=findViewById(R.id.ediNdedoXclustXc17);
        ediNdedoXclustXc18=findViewById(R.id.ediNdedoXclustXc18);
        ediNdedoXclustXc19=findViewById(R.id.ediNdedoXclustXc19);
        ediNdedoXclustXc20=findViewById(R.id.ediNdedoXclustXc20);


        ediCalByA1=findViewById(R.id.ediCalByA1);
        ediCalByA2=findViewById(R.id.ediCalByA2);
        ediCalByA3=findViewById(R.id.ediCalByA3);
        ediCalByA4=findViewById(R.id.ediCalByA4);
        ediCalByA5=findViewById(R.id.ediCalByA5);
        ediCalByA6=findViewById(R.id.ediCalByA6);
        ediCalByA7=findViewById(R.id.ediCalByA7);
        ediCalByA8=findViewById(R.id.ediCalByA8);
        ediCalByA9=findViewById(R.id.ediCalByA9);
        ediCalByA10=findViewById(R.id.ediCalByA10);
        ediCalByA11=findViewById(R.id.ediCalByA11);
        ediCalByA12=findViewById(R.id.ediCalByA12);
        ediCalByA13=findViewById(R.id.ediCalByA13);
        ediCalByA14=findViewById(R.id.ediCalByA14);
        ediCalByA15=findViewById(R.id.ediCalByA15);
        ediCalByA16=findViewById(R.id.ediCalByA16);
        ediCalByA17=findViewById(R.id.ediCalByA17);
        ediCalByA18=findViewById(R.id.ediCalByA18);
        ediCalByA19=findViewById(R.id.ediCalByA19);
        ediCalByA20=findViewById(R.id.ediCalByA20);
        ediCalByA21=findViewById(R.id.ediCalByA21);


        edif2Calib1=findViewById(R.id.edif2Calib1);
        edif2Calib2=findViewById(R.id.edif2Calib2);
        edif2Calib3=findViewById(R.id.edif2Calib3);
        edif2Calib4=findViewById(R.id.edif2Calib4);
        edif2Calib5=findViewById(R.id.edif2Calib5);
        edif2Calib6=findViewById(R.id.edif2Calib6);
        edif2Calib7=findViewById(R.id.edif2Calib7);
        edif2Calib8=findViewById(R.id.edif2Calib8);
        edif2Calib9=findViewById(R.id.edif2Calib9);
        edif2Calib10=findViewById(R.id.edif2Calib10);
        edif2Calib11=findViewById(R.id.edif2Calib11);
        edif2Calib12=findViewById(R.id.edif2Calib12);
        edif2Calib13=findViewById(R.id.edif2Calib13);
        edif2Calib14=findViewById(R.id.edif2Calib14);
        edif2Calib15=findViewById(R.id.edif2Calib15);
        edif2Calib16=findViewById(R.id.edif2Calib16);
        edif2Calib17=findViewById(R.id.edif2Calib17);
        edif2Calib18=findViewById(R.id.edif2Calib18);
        edif2Calib19=findViewById(R.id.edif2Calib19);
        edif2Calib20=findViewById(R.id.edif2Calib20);
        edif2Calib21=findViewById(R.id.edif2Calib21);
        edif2Calib22=findViewById(R.id.edif2Calib22);

        mEdiLargDeds1 = findViewById(R.id.ediLargDeds1);
        mEdiLargDeds2 = findViewById(R.id.ediLargDeds2);
        mEdiLargDeds3 = findViewById(R.id.ediLargDeds3);
        mEdiLargDeds4 = findViewById(R.id.ediLargDeds4);
        mEdiLargDeds5 = findViewById(R.id.ediLargDeds5);
        mEdiLargDeds6 = findViewById(R.id.ediLargDeds6);
        mEdiLargDeds7 = findViewById(R.id.ediLargDeds7);
        mEdiLargDeds8 = findViewById(R.id.ediLargDeds8);
        mEdiLargDeds9 = findViewById(R.id.ediLargDeds9);
        mEdiLargDeds10 = findViewById(R.id.ediLargDeds10);
        mEdiLargDeds11 = findViewById(R.id.ediLargDeds11);
        mEdiLargDeds12 = findViewById(R.id.ediLargDeds12);
        mEdiLargDeds13 = findViewById(R.id.ediLargDeds13);
        mEdiLargDeds14 = findViewById(R.id.ediLargDeds14);
        mEdiLargDeds15 = findViewById(R.id.ediLargDeds15);
        mEdiLargDeds16 = findViewById(R.id.ediLargDeds16);
        mEdiLargDeds17 = findViewById(R.id.ediLargDeds17);
        mEdiLargDeds18 = findViewById(R.id.ediLargDeds18);
        mEdiLargDeds19 = findViewById(R.id.ediLargDeds19);
        mEdiLargDeds20 = findViewById(R.id.ediLargDeds20);
        mEdiLargDeds21 = findViewById(R.id.ediLargDeds21);
        mEdiLargDeds22 = findViewById(R.id.ediLargDeds22);
        mEdiLargDeds23 = findViewById(R.id.ediLargDeds23);
        mEdiLargDeds24 = findViewById(R.id.ediLargDeds24);
        mEdiLargDeds25 = findViewById(R.id.ediLargDeds25);
        mEdiLargDeds26 = findViewById(R.id.ediLargDeds26);
        mEdiLargDeds27 = findViewById(R.id.ediLargDeds27);
        mEdiLargDeds28 = findViewById(R.id.ediLargDeds28);
        mEdiLargDeds29 = findViewById(R.id.ediLargDeds29);
        mEdiLargDeds30 = findViewById(R.id.ediLargDeds30);
        // mEdiTotalFila1 = findViewById(R.id.ediTotalFila1);
        //  mEdiPromFila1 = findViewById(R.id.ediPromFila1);

        mEdif2LrgD1 = findViewById(R.id.edif2LrgD1);
        mEdif2LrgD2 = findViewById(R.id.edif2LrgD2);
        mEdif2LrgD3 = findViewById(R.id.edif2LrgD3);
        mEdif2LrgD4 = findViewById(R.id.edif2LrgD4);
        mEdif2LrgD5 = findViewById(R.id.edif2LrgD5);
        mEdif2LrgD6 = findViewById(R.id.edif2LrgD6);
        mEdif2LrgD7 = findViewById(R.id.edif2LrgD7);
        mEdif2LrgD8 = findViewById(R.id.edif2LrgD8);
        mEdif2LrgD9 = findViewById(R.id.edif2LrgD9);
        mEdif2LrgD10 = findViewById(R.id.edif2LrgD10);
        mEdif2LrgD11 = findViewById(R.id.edif2LrgD11);
        mEdif2LrgD12 = findViewById(R.id.edif2LrgD12);
        mEdif2LrgD13 = findViewById(R.id.edif2LrgD13);
        mEdif2LrgD14 = findViewById(R.id.edif2LrgD14);
        mEdif2LrgD15 = findViewById(R.id.edif2LrgD15);
        mEdif2LrgD16 = findViewById(R.id.edif2LrgD16);
        mEdif2LrgD17 = findViewById(R.id.edif2LrgD17);
        mEdif2LrgD18 = findViewById(R.id.edif2LrgD18);
        mEdif2LrgD19 = findViewById(R.id.edif2LrgD19);
        mEdif2LrgD20 = findViewById(R.id.edif2LrgD20);
        mEdif2LrgD21 = findViewById(R.id.edif2LrgD21);
        mEdif2LrgD22 = findViewById(R.id.edif2LrgD22);
        mEdif2LrgD23 = findViewById(R.id.edif2LrgD23);
        mEdif2LrgD24 = findViewById(R.id.edif2LrgD24);
        mEdif2LrgD25 = findViewById(R.id.edif2LrgD25);
        mEdif2LrgD26 = findViewById(R.id.edif2LrgD26);
        mEdif2LrgD27 = findViewById(R.id.edif2LrgD27);
        mEdif2LrgD28 = findViewById(R.id.edif2LrgD28);
        mEdif2LrgD29 = findViewById(R.id.edif2LrgD29);
        mEdif2LrgD30 = findViewById(R.id.edif2LrgD30);





          imgUpdateNumDedxClust=findViewById(R.id.imgUpdateNumDedxClust);
          imgUpdateNumClusterxCaja=findViewById(R.id.imgUpdateNumClusterxCaja);
          imgUpdateCalibBasalYapical=findViewById(R.id.imgUpdateCalibBasalYapical);




    }


    private void addListnners(){

        imgSelecDefc1.setOnClickListener(this);
        imgSelecDefc2.setOnClickListener(this);
        imgSelecDefc3.setOnClickListener(this);


         imgUpdateNumDedxClust.setOnClickListener(this);
           imgUpdateNumClusterxCaja.setOnClickListener(this);
           imgUpdateCalibBasalYapical.setOnClickListener(this);
        imgUpdateNumPulpaApulpa.setOnClickListener(this);


        imgSelecDefc4.setOnClickListener(this);
        imgSelecDefc5.setOnClickListener(this);
        imgSelecDefc6.setOnClickListener(this);
        imgSelecDefc7.setOnClickListener(this);
        imgSelecDefc8.setOnClickListener(this);
        imgSelecDefc9.setOnClickListener(this);
        imgSelecDefc10.setOnClickListener(this);

        imvEmpaque1.setOnClickListener(this);
        imvEmpaque2.setOnClickListener(this);
        imvEmpaque3.setOnClickListener(this);
        imvEmpaque4.setOnClickListener(this);
        imvEmpaque5.setOnClickListener(this);
        imvEmpaque6.setOnClickListener(this);
        imvEmpaque7.setOnClickListener(this);
        imvEmpaque8.setOnClickListener(this);
        imvEmpaque9.setOnClickListener(this);
        imvEmpaque10.setOnClickListener(this);

        ediTimeHoraxx1.setOnClickListener(this);
        ediTimeHoraxx2.setOnClickListener(this);
        ediTimeHoraxx3.setOnClickListener(this);
        ediTimeHoraxx4.setOnClickListener(this);
        ediTimeHoraxx5.setOnClickListener(this);
        ediTimeHoraxx6.setOnClickListener(this);
        ediTimeHoraxx7.setOnClickListener(this);
        ediTimeHoraxx8.setOnClickListener(this);
        ediTimeHoraxx9.setOnClickListener(this);
        ediTimeHoraxx10.setOnClickListener(this);

        textView48.setOnClickListener(this);



    }



    void muestraResultado()  {

        ArrayList <Integer> result10 = new ArrayList<Integer>();

        int resultadoSuMA;

        for(int indice2=0; indice2<listOfLISTState.size(); indice2++){  //lista de listas
            resultadoSuMA=0;

            for(int indice=0; indice<listOfLISTState.get(indice2).size(); indice++){  //recorremos la lista actual


                if(listOfLISTState.get(indice2).get(indice)){
                    resultadoSuMA=resultadoSuMA+1;

                }




            }


            result10.add(resultadoSuMA);
            //aqui ya debemos tener el resultado


        }




        int resultadotodo=0;
        for(int indice=0; indice<result10.size(); indice++){  //recorremos la lista actual


            resultadotodo=resultadotodo+result10.get(indice);


            Log.i("sumarr","el valor es "+result10.get(indice));


        }

        txtTotal.setText(String.valueOf(resultadotodo));


        txtTotal1.setText(String.valueOf(result10.get(0)));
        txtTotal2.setText(String.valueOf(result10.get(1)));
        txtTotal3.setText(String.valueOf(result10.get(2)));
        txtTotal4.setText(String.valueOf(result10.get(3)));
        txtTotal5.setText(String.valueOf(result10.get(4)));
        txtTotal6.setText(String.valueOf(result10.get(5)));
        txtTotal7.setText(String.valueOf(result10.get(6)));
        txtTotal8.setText(String.valueOf(result10.get(7)));
        txtTotal9.setText(String.valueOf(result10.get(8)));
        txtTotal10.setText(String.valueOf(result10.get(9)));



    }





    @Override
    public void onClick(View view) {
        boolean[] estates;

        String keyOrViewID=String.valueOf(view.getId());

        //di esl alguno de estos clicks
        int idPulsado=view.getId();
        if(idPulsado== R.id.imgSelecDefc1 || idPulsado== R.id.imgSelecDefc2  || idPulsado== R.id.imgSelecDefc3  ||  idPulsado== R.id.imgSelecDefc4  ||
                idPulsado== R.id.imgSelecDefc5  || idPulsado== R.id.imgSelecDefc6   || idPulsado== R.id.imgSelecDefc7  ||
                idPulsado== R.id.imgSelecDefc8   ||  idPulsado== R.id.imgSelecDefc9   ||  idPulsado== R.id.imgSelecDefc10 ){

            estates =listToAarray(HashMapOfListWhitStatesCHeckb.get(keyOrViewID));
            showDialogx(estates,keyOrViewID);


        }


        if(idPulsado== R.id.imvEmpaque1 || idPulsado== R.id.imvEmpaque2  || idPulsado== R.id.imvEmpaque3  ||  idPulsado== R.id.imvEmpaque4  ||
                idPulsado== R.id.imvEmpaque5  || idPulsado== R.id.imvEmpaque6   || idPulsado== R.id.imvEmpaque7  ||
                idPulsado== R.id.imvEmpaque8   ||  idPulsado== R.id.imvEmpaque9   ||  idPulsado== R.id.imvEmpaque10 ){

            estates =listToAarray(HashMapOfListWhitStatesCHeckb2.get(keyOrViewID));
            showDialogx2(estates,keyOrViewID);


        }




        switch (view.getId()) {


            case R.id.ediTimeHoraxx1:

                showingTimePicker(view);
                break;



            case R.id.ediTimeHoraxx2:

                showingTimePicker(view);
                break;



            case R.id.ediTimeHoraxx3:

                showingTimePicker(view);
                break;


            case R.id.ediTimeHoraxx4:

                showingTimePicker(view);
                break;



            case R.id.ediTimeHoraxx5:

                showingTimePicker(view);
                break;



            case R.id.ediTimeHoraxx6:

                showingTimePicker(view);
                break;


            case R.id.ediTimeHoraxx7:

                showingTimePicker(view);
                break;


            case R.id.ediTimeHoraxx8:

                showingTimePicker(view);
                break;


            case R.id.ediTimeHoraxx9:

                showingTimePicker(view);
                break;


            case R.id.ediTimeHoraxx10:

                showingTimePicker(view);
                break;



            case R.id.textView48:

                muestraResultado();

                break;



            case R.id.imgUpdateCalibBasalYapical:
                showResultOfCalibraEntreBasalYapiclProduct();


                break;

            case R.id.imgUpdateNumClusterxCaja:
                showResultNumeroClusterxCajaProduct();


                break;


            case R.id.imgUpdateNumDedxClust:
                showResultNumClusteroManoProduct();
                break;


            case R.id.imgUpdateNumPulpaApulpa:
                getlargoDedosPulgaPulpaApulpa();

                break;




        }

    }

    private void getlargoDedosPulgaPulpaApulpa() {


        double [][] decimalsToMultiplicar = {

                {6.0,  6.2, 6.4, 6.6, 6.8},
                {7.0,  7.2, 7.4, 7.6, 7.8},
                {8.0,  8.2, 8.4, 8.6, 8.8},
                {9.0,  9.2, 9.4, 9.6, 9.8},
                {10.0,  10.2, 10.4, 10.6, 10.8},
                {11.0,  11.2, 11.4, 11.6, 11.8},


        } ;



        TextInputEditText [][] arrayBidimensfILA1ContieneTexts = {

                {mEdiLargDeds1,mEdiLargDeds2,mEdiLargDeds3,mEdiLargDeds4,mEdiLargDeds5},
                {mEdiLargDeds6,mEdiLargDeds7,mEdiLargDeds8,mEdiLargDeds9,mEdiLargDeds10},
                {mEdiLargDeds11,mEdiLargDeds12,mEdiLargDeds13,mEdiLargDeds14,mEdiLargDeds15},
                {mEdiLargDeds16,mEdiLargDeds17,mEdiLargDeds18,mEdiLargDeds19,mEdiLargDeds20},
                {mEdiLargDeds21,mEdiLargDeds22,mEdiLargDeds23,mEdiLargDeds24,mEdiLargDeds25},
                {mEdiLargDeds26,mEdiLargDeds27,mEdiLargDeds28,mEdiLargDeds29,mEdiLargDeds30},

        } ;


        TextInputEditText [][] arrayBidimensfILA2MostarText = {

                {mEdif2LrgD1,mEdif2LrgD2,mEdif2LrgD3,mEdif2LrgD4,mEdif2LrgD5},
                {mEdif2LrgD6,mEdif2LrgD7,mEdif2LrgD8,mEdif2LrgD9,mEdif2LrgD10},
                {mEdif2LrgD11,mEdif2LrgD12,mEdif2LrgD13,mEdif2LrgD14,mEdif2LrgD15},
                {mEdif2LrgD16,mEdif2LrgD17,mEdif2LrgD18,mEdif2LrgD19,mEdif2LrgD20},
                {mEdif2LrgD21,mEdif2LrgD22,mEdif2LrgD23,mEdif2LrgD24,mEdif2LrgD25},
                {mEdif2LrgD26,mEdif2LrgD27,mEdif2LrgD28,mEdif2LrgD29,mEdif2LrgD30},

        } ;



        for (int i = 0; i <arrayBidimensfILA1ContieneTexts.length; i++) {

            TextInputEditText [] currentArrayofContainsDataNums= arrayBidimensfILA1ContieneTexts[i];
            double [] currentDecimalArray=decimalsToMultiplicar[i];


            for (int indice = 0; indice <currentArrayofContainsDataNums.length; indice++) {
                //se ejecutar 5 veces..

                if(currentArrayofContainsDataNums[indice].getText().toString().trim().isEmpty() )  { //si esta vacio
                    currentArrayofContainsDataNums[indice].getText().clear();

                }

                else {

                    double result= currentDecimalArray[indice] * Integer.parseInt(currentArrayofContainsDataNums[indice].getText().toString());


                    if(String.valueOf(result).length() >5)  {
                        double roundDbl = Math.round(result*100.0)/100.0;
                        result= roundDbl;
                    }
                    Log.i("dfgdf","el value es sssss "+String.valueOf(result))  ;


                    if((result % 1) == 0){

                        int numentero = (int) Math.floor(result);
                        Log.i("dfgdf","el value convert "+result)  ;

                        arrayBidimensfILA2MostarText[i][indice].setText(String.valueOf(numentero));



                    }else{

                        arrayBidimensfILA2MostarText[i][indice].setText(String.valueOf(result));

                    }



                    // currentArrayofContainsDataNums[indice].setText(String.valueOf(result));
                    //colcamos este texto en el editext


                }



            }

            //6 veces


        }






    }


    void showDialogx(boolean[] estatesCurrentListItem,String keyOFcURRENTiTEMOFhasmap) {

        // Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(FormularioControlCalidadPreview.this);

        // set title
        builder.setTitle("Selecciones Defectos");

        // set dialog non cancelable
        builder.setCancelable(false);

        builder.setMultiChoiceItems(arrayDefect1, estatesCurrentListItem, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                // check condition
                if (b) {
                    //cuando selecione une ...obtenemos la poisicion..
                    //j

                    // listOfLISTState.get(posicionListOfLIST).set(i,true);
                    HashMapOfListWhitStatesCHeckb.get(keyOFcURRENTiTEMOFhasmap).set(i,true);

                    // when checkbox selected
                    // Add position  in lang list
                    //  langList.add(i);
                    // Sort array list
                    //   Collections.sort(langList);
                } else {  //CUANDO LO DESELECIONA
                    // when checkbox unselected
                    // Remove position from langList

                    // listOfLISTState.get(posicionListOfLIST).set(i,false);
                    HashMapOfListWhitStatesCHeckb.get(keyOFcURRENTiTEMOFhasmap).set(i,false);

                    //  langList.remove(Integer.valueOf(i));
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Initialize string builder
                dialogInterface.dismiss();

            }
        });



        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });


        builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // use for loop

                //aqui necesitamos obtener el hasmapa

                for (int j = 0; j < HashMapOfListWhitStatesCHeckb.get(keyOFcURRENTiTEMOFhasmap).size(); j++) {

                    HashMapOfListWhitStatesCHeckb.get(keyOFcURRENTiTEMOFhasmap).set(j,false);

                    //listOfLISTState.get(posicionListOfLIST).set(j,false);

                }




            }
        });
        // show dialog
        builder.show();



    }


    void showDialogx2(boolean[] itemsChekeds,String keyCurrentListOFmap) {

        // Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(FormularioControlCalidadPreview.this);

        // set title
        builder.setTitle("Seleccione Defectos");

        // set dialog non cancelable
        builder.setCancelable(false);

        builder.setMultiChoiceItems(arrayDefect2, itemsChekeds, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                // check condition
                if (b) {
                    //cuando selecione une ...obtenemos la poisicion..
                    //

                    HashMapOfListWhitStatesCHeckb2.get(keyCurrentListOFmap).set(i,true);


                    // when checkbox selected
                    // Add position  in lang list
                    //  langList.add(i);
                    // Sort array list
                    //   Collections.sort(langList);
                } else {  //CUANDO LO DESELECIONA
                    // when checkbox unselected
                    // Remove position from langList

                    HashMapOfListWhitStatesCHeckb2.get(keyCurrentListOFmap).set(i,false);

                    //  langList.remove(Integer.valueOf(i));
                }
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Initialize string builder
                dialogInterface.dismiss();

            }
        });



        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // dismiss dialog
                dialogInterface.dismiss();
            }
        });


        builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // use for loop

                for (int j = 0; j < HashMapOfListWhitStatesCHeckb2.get(keyCurrentListOFmap).size(); j++) {

                    HashMapOfListWhitStatesCHeckb2.get(keyCurrentListOFmap).set(j,false);

                    //listOfLISTState.get(posicionListOfLIST).set(j,false);

                }




            }
        });
        // show dialog
        builder.show();



    }


    private boolean [] listToAarray(ArrayList<Boolean>list){

        boolean array[]= new boolean[list.size()];


        for(int indice=0; indice< list.size(); indice++){

            array[indice]=list.get(indice);


        }


        return  array;


    }






    void showingTimePicker( View vista){

        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        TimePickerDialog picker = new TimePickerDialog(FormularioControlCalidadPreview.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        if(vista.getId()==R.id.ediTimeHoraxx1) {
                            ediTimeHoraxx1.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediTimeHoraxx2) {
                            ediTimeHoraxx2.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediTimeHoraxx3) {
                            ediTimeHoraxx3.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediTimeHoraxx4) {
                            ediTimeHoraxx4.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediTimeHoraxx5) {
                            ediTimeHoraxx5.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediTimeHoraxx6) {
                            ediTimeHoraxx6.setText(sHour + ":" + sMinute);


                        }

                        else if (vista.getId()== R.id.ediTimeHoraxx7) {
                            ediTimeHoraxx7.setText(sHour + ":" + sMinute);


                        }


                        else if (vista.getId()== R.id.ediTimeHoraxx8) {
                            ediTimeHoraxx8.setText(sHour + ":" + sMinute);


                        }




                        else if (vista.getId()== R.id.ediTimeHoraxx9) {
                            ediTimeHoraxx9.setText(sHour + ":" + sMinute);


                        }



                        else if (vista.getId()== R.id.ediTimeHoraxx10) {
                            ediTimeHoraxx10.setText(sHour + ":" + sMinute);


                        }





                    }
                }, hour, minutes, true);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);

        picker.show();
    }


    //1 mapa para todos....
    //alamacenara datos Styring...




    private void createItemsSelectDefectsEmpqTOuPLOAD() {

        ImageView  [] imgSelecArray= {
                imvEmpaque1,imvEmpaque2,imvEmpaque3,imvEmpaque4,imvEmpaque5,imvEmpaque6,
                imvEmpaque7,imvEmpaque8, imvEmpaque9,imvEmpaque10} ;

        for (int i = 0; i <10 ; i++) {

            ArrayList<Boolean> currentList = HashMapOfListWhitStatesCHeckb2.get(String.valueOf(imgSelecArray[i].getId()));

            String value="";

            for (int j = 0; j < currentList.size(); j++) {  //recorreemos la lista  ///

                if(currentList.get(j)) {  //si es verdaqdfero ,lol agragmos


                    if(value.equals("")){
                        value=value+j;

                    }else {
                        value=value+","+j;

                    }

                }
                //agregamos valors a esta lista
            }

            //AGREGAMOS ESTA LETRA AL MAP:


            if(!value.equals("")){

                Log.i("adirnir","el value es "+value);

                hasMapitemsSelecPosicRechazToUpload.put(String.valueOf(imgSelecArray[i].getId()) ,value);


            }



        }


        if(hasMapitemsSelecPosicRechazToUpload.size() ==0 ){

            hasMapitemsSelecPosicRechazToUpload.put("0","EMPTY");
        }

    }



    private void createInfoToHashmapRechazaSelecToUpload() {

        ImageView  [] imgSelecArray= {imgSelecDefc1,imgSelecDefc2,imgSelecDefc3,imgSelecDefc4,imgSelecDefc5,imgSelecDefc6,
                imgSelecDefc7,imgSelecDefc8, imgSelecDefc9,imgSelecDefc10} ;

        hasMapitemsSelecPosicRechazToUpload = new HashMap<>();


        for (int i = 0; i<10; i++) {

            ArrayList<Boolean> currentList =  HashMapOfListWhitStatesCHeckb.get(String.valueOf(imgSelecArray[i].getId()));

            String value="";


            for (int j = 0; j < currentList.size(); j++) {  //iteramos cada elemento del hasm,ap q es una lista
                if(currentList.get(j)) {  //si es verdaqdfero ,lol agragmos
                    Log.i("adirnir","encontro value ttr ");

                    if(value.equals("")){

                        value=value+j;

                    }else {
                        value=value+","+j;
                    }


                }

            }


            if((!value.equals(""))) {
                Log.i("adirnir","encontro value "+value);

                hasMapitemsSelecPosicRechazToUpload.put(String.valueOf(imgSelecArray[i].getId()) ,value);

            }


        }

        if(hasMapitemsSelecPosicRechazToUpload.size() ==0 ){

            hasMapitemsSelecPosicRechazToUpload.put("0","EMPTY");


        }


    }

    private void showResultNumClusteroManoProduct() {

        final int [] arrayNJumsTOmult = {3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,
                22,23,24,25,26,27,28,29,30  } ;

        TextInputEditText [] arrayEditsFilaArriba = { ediNdedoXclust1,	ediNdedoXclust2,
                ediNdedoXclust3,	ediNdedoXclust4,	ediNdedoXclust5,ediNdedoXclust6,ediNdedoXclust7
                ,	ediNdedoXclust8,	ediNdedoXclust9,ediNdedoXclust10,	ediNdedoXclust11
                ,	ediNdedoXclust12,	ediNdedoXclust13,ediNdedoXclust14,	ediNdedoXclust15
                ,	ediNdedoXclust16,	ediNdedoXclust17,ediNdedoXclust18,	ediNdedoXclust19
                ,	ediNdedoXclust20,	ediNdedoXclust21,ediNdedoXclust22,	ediNdedoXclust23,
                ediNdedoXclust24,	ediNdedoXclust25,	ediNdedoXclust26,	ediNdedoXclust27,
                ediNdedoXclust28,ediNdedoXclust29, ediNdedoXclust30 };


        TextInputEditText [] arrayEditsFilaAbajo = {
                edif2NdedoXclust1	, edif2NdedoXclust2	, edif2NdedoXclust3	, edif2NdedoXclust4	,
                edif2NdedoXclust5	, edif2NdedoXclust6	, edif2NdedoXclust7	, edif2NdedoXclust8	,
                edif2NdedoXclust9	, edif2NdedoXclust10, edif2NdedoXclust11, edif2NdedoXclust12,
                edif2NdedoXclust13	, edif2NdedoXclust14, edif2NdedoXclust15, edif2NdedoXclust16,
                edif2NdedoXclust17	, edif2NdedoXclust18, edif2NdedoXclust19, edif2NdedoXclust20,
                edif2NdedoXclust21	, edif2NdedoXclust22, edif2NdedoXclust23, edif2NdedoXclust24,
                edif2NdedoXclust25	, edif2NdedoXclust26, edif2NdedoXclust27, edif2NdedoXclust28,
                edif2NdedoXclust29	, edif2NdedoXclust30,

        };


        for (int i = 0; i < arrayNJumsTOmult.length; i++) {

            int productResult =0;

            if(arrayEditsFilaArriba[i].getText().toString().trim().isEmpty() )  { //si esta vacio

                arrayEditsFilaAbajo [i].getText().clear();

            }


            else {


                productResult =Integer.parseInt(arrayEditsFilaArriba[i].getText().toString()) * arrayNJumsTOmult[i];
                arrayEditsFilaAbajo [i].setText(String.valueOf(productResult));
            }



        }

    }

    private void showResultNumeroClusterxCajaProduct() {

        //9-26

        final int [] arrayNJumsTOmult = {9,10,11,12,13,14,15,16,17,18,19,20,21,
                22,23,24,25,26  } ;

        TextInputEditText [] arrayEditsFilaArriba = { edif2NdedoXclustxC1	, edif2NdedoXclustxC2	, edif2NdedoXclustxC3	,
                edif2NdedoXclustxC4	, edif2NdedoXclustxC5, edif2NdedoXclustxC6	, edif2NdedoXclustxC7	,
                edif2NdedoXclustxC8	, edif2NdedoXclustxC9, edif2NdedoXclustxC10, edif2NdedoXclustxC11, edif2NdedoXclustxC12,
                edif2NdedoXclustxC13, edif2NdedoXclustxC14, edif2NdedoXclustxC15, edif2NdedoXclustxC16,
                edif2NdedoXclustxC17, edif2NdedoXclustxC18
        };


        TextInputEditText [] arrayEditsFilaAbajo = {
                ediNdedoXclustXc1, ediNdedoXclustXc2, ediNdedoXclustXc3	, ediNdedoXclustXc4	,ediNdedoXclustXc5,
                ediNdedoXclustXc6, ediNdedoXclustXc7, ediNdedoXclustXc8	, ediNdedoXclustXc9	,ediNdedoXclustXc10
                , ediNdedoXclustXc11, ediNdedoXclustXc12, ediNdedoXclustXc13, ediNdedoXclustXc14
                , ediNdedoXclustXc15, ediNdedoXclustXc16, ediNdedoXclustXc17, ediNdedoXclustXc18

        };


        for (int i = 0; i < arrayNJumsTOmult.length; i++) {

            int productResult =0;


            if(arrayEditsFilaArriba[i].getText().toString().trim().isEmpty() )  { //si esta vacio

                arrayEditsFilaAbajo [i].getText().clear();

            }


            else {


                productResult =Integer.parseInt(arrayEditsFilaArriba[i].getText().toString()) * arrayNJumsTOmult[i];
                arrayEditsFilaAbajo [i].setText(String.valueOf(productResult));
            }










        }


    }

    private void showResultOfCalibraEntreBasalYapiclProduct() {

        //9-26

        final int [] arrayNJumsTOmult = {0,0,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54} ;


        TextInputEditText [] arrayEditsFilaArriba = { ediCalByA1	,
                ediCalByA2	,
                ediCalByA3	,
                ediCalByA4	,
                ediCalByA5	,
                ediCalByA6	,
                ediCalByA7	,
                ediCalByA8	,
                ediCalByA9	,
                ediCalByA10	,
                ediCalByA11	,
                ediCalByA12	,
                ediCalByA13	,
                ediCalByA14	,
                ediCalByA15	,
                ediCalByA16	,
                ediCalByA17	,
                ediCalByA18	,
                ediCalByA19	,
                ediCalByA20	,


        };


        TextInputEditText [] arrayEditsFilaAbajo = {
                edif2Calib1	, edif2Calib2, edif2Calib3,edif2Calib4,
                edif2Calib5	, edif2Calib6, edif2Calib7, edif2Calib8	,
                edif2Calib9	, edif2Calib10, edif2Calib11, edif2Calib12	,
                edif2Calib13, edif2Calib14, edif2Calib15, edif2Calib16	,
                edif2Calib17, edif2Calib18, edif2Calib19, edif2Calib20	,


        };


        for (int i = 0; i < arrayNJumsTOmult.length; i++) {

            int productResult =0;

            if(arrayEditsFilaArriba[i].getText().toString().trim().isEmpty() )  { //si esta vacio

                arrayEditsFilaAbajo [i].getText().clear();

            }


            else {


                productResult =Integer.parseInt(arrayEditsFilaArriba[i].getText().toString()) * arrayNJumsTOmult[i];
                arrayEditsFilaAbajo [i].setText(String.valueOf(productResult));
            }





        }


    }

    private void showResultlargoDedosPulgaPulpaApulpa() {


        double [][] decimalsToMultiplicar = {

                {6.0,  6.2, 6.4, 6.6, 6.8},
                {7.0,  7.2, 7.4, 7.6, 7.8},
                {8.0,  8.2, 8.4, 8.6, 8.8},
                {9.0,  9.2, 9.4, 9.6, 9.8},
                {10.0,  10.2, 10.4, 10.6, 10.8},
                {11.0,  11.2, 11.4, 11.6, 11.8},


        } ;



        TextInputEditText [][] arrayBidimensfILA1ContieneTexts = {

                {mEdiLargDeds1,mEdiLargDeds2,mEdiLargDeds3,mEdiLargDeds4,mEdiLargDeds5},
                {mEdiLargDeds6,mEdiLargDeds7,mEdiLargDeds8,mEdiLargDeds9,mEdiLargDeds10},
                {mEdiLargDeds11,mEdiLargDeds12,mEdiLargDeds13,mEdiLargDeds14,mEdiLargDeds15},
                {mEdiLargDeds16,mEdiLargDeds17,mEdiLargDeds18,mEdiLargDeds19,mEdiLargDeds20},
                {mEdiLargDeds21,mEdiLargDeds22,mEdiLargDeds23,mEdiLargDeds24,mEdiLargDeds25},
                {mEdiLargDeds26,mEdiLargDeds27,mEdiLargDeds28,mEdiLargDeds29,mEdiLargDeds30},

        } ;


        TextInputEditText [][] arrayBidimensfILA2MostarText = {

                {mEdif2LrgD1,mEdif2LrgD2,mEdif2LrgD3,mEdif2LrgD4,mEdif2LrgD5},
                {mEdif2LrgD6,mEdif2LrgD7,mEdif2LrgD8,mEdif2LrgD9,mEdif2LrgD10},
                {mEdif2LrgD11,mEdif2LrgD12,mEdif2LrgD13,mEdif2LrgD14,mEdif2LrgD15},
                {mEdif2LrgD16,mEdif2LrgD17,mEdif2LrgD18,mEdif2LrgD19,mEdif2LrgD20},
                {mEdif2LrgD21,mEdif2LrgD22,mEdif2LrgD23,mEdif2LrgD24,mEdif2LrgD25},
                {mEdif2LrgD26,mEdif2LrgD27,mEdif2LrgD28,mEdif2LrgD29,mEdif2LrgD30},

        } ;



        for (int i = 0; i <arrayBidimensfILA1ContieneTexts.length; i++) {

            TextInputEditText [] currentArrayofContainsDataNums= arrayBidimensfILA1ContieneTexts[i];
            double [] currentDecimalArray=decimalsToMultiplicar[i];


            for (int indice = 0; indice <currentArrayofContainsDataNums.length; indice++) {
                //se ejecutar 5 veces..

                if(currentArrayofContainsDataNums[indice].getText().toString().trim().isEmpty() )  { //si esta vacio
                    currentArrayofContainsDataNums[indice].getText().clear();

                }

                else {

                    double result= currentDecimalArray[indice] * Integer.parseInt(currentArrayofContainsDataNums[indice].getText().toString());


                    if(String.valueOf(result).length() >5)  {
                        double roundDbl = Math.round(result*100.0)/100.0;
                        result= roundDbl;
                    }
                    Log.i("dfgdf","el value es sssss "+String.valueOf(result))  ;


                    if((result % 1) == 0){

                        int numentero = (int) Math.floor(result);
                        Log.i("dfgdf","el value convert "+result)  ;

                        arrayBidimensfILA2MostarText[i][indice].setText(String.valueOf(numentero));


                    }else {
                        arrayBidimensfILA2MostarText[i][indice].setText(String.valueOf(result));


                    }



                    // currentArrayofContainsDataNums[indice].setText(String.valueOf(result));
                    //colcamos este texto en el editext


                }



            }

            //6 veces


        }






    }


    private void createMapInBYothersTextimpuEdFields() {

        hasHmapFieldsOtherViews = new HashMap<>();

        TextInputEditText arrayAllFields[] =  {

                ediTimeHoraxx1, ediTimeHoraxx2, ediTimeHoraxx3, ediTimeHoraxx4, ediTimeHoraxx5, ediTimeHoraxx6, ediTimeHoraxx7, ediTimeHoraxx8,
                ediTimeHoraxx9, ediTimeHoraxx10, ediPesoL1, ediPesoL2, ediPesoL3, ediPesoL4, ediPesoL5, ediPesoL6, ediPesoL7, ediPesoL8,
                ediPesoL9, ediPesoL10, ediPH1, ediPH2, ediPH3, ediPH4, ediPH5, ediPH6, ediPH7, ediPH8, ediPH9, ediPH10, ediNumClusInsp1,
                ediNumClusInsp2, ediNumClusInsp3, ediNumClusInsp4, ediNumClusInsp5, ediNumClusInsp6, ediNumClusInsp7, ediNumClusInsp8,
                ediNumClusInsp9, ediNumClusInsp10, ediNdedoXclust1, ediNdedoXclust2 , ediNdedoXclust3 , ediNdedoXclust4 , ediNdedoXclust5 ,
                ediNdedoXclust6 , ediNdedoXclust7 , ediNdedoXclust8 , ediNdedoXclust9 , ediNdedoXclust10 , ediNdedoXclust11 , ediNdedoXclust12 ,
                ediNdedoXclust13 , ediNdedoXclust14 , ediNdedoXclust15 , ediNdedoXclust16 , ediNdedoXclust17 , ediNdedoXclust18 ,
                ediNdedoXclust19 , ediNdedoXclust20 , ediNdedoXclust21 , ediNdedoXclust22 , ediNdedoXclust23 , ediNdedoXclust24 ,
                ediNdedoXclust25 , ediNdedoXclust26 , ediNdedoXclust27, ediNdedoXclust28 , ediNdedoXclust29 , ediNdedoXclust30 ,
                edif2NdedoXclust1, edif2NdedoXclust2 , edif2NdedoXclust3 , edif2NdedoXclust4 , edif2NdedoXclust5 , edif2NdedoXclust6 ,
                edif2NdedoXclust7 , edif2NdedoXclust8 , edif2NdedoXclust9 , edif2NdedoXclust10 , edif2NdedoXclust11 , edif2NdedoXclust12 ,
                edif2NdedoXclust13 , edif2NdedoXclust14 , edif2NdedoXclust15 , edif2NdedoXclust16 , edif2NdedoXclust17 ,
                edif2NdedoXclust18 , edif2NdedoXclust19 , edif2NdedoXclust20 , edif2NdedoXclust21 , edif2NdedoXclust22 ,
                edif2NdedoXclust23 , edif2NdedoXclust24 , edif2NdedoXclust25 , edif2NdedoXclust26 , edif2NdedoXclust27,
                edif2NdedoXclust28 , edif2NdedoXclust29 , edif2NdedoXclust30 , edif2NdedoXclustxC1, edif2NdedoXclustxC2 ,
                edif2NdedoXclustxC3 , edif2NdedoXclustxC4 , edif2NdedoXclustxC5 , edif2NdedoXclustxC6 , edif2NdedoXclustxC7 ,
                edif2NdedoXclustxC8 , edif2NdedoXclustxC9 , edif2NdedoXclustxC10 , edif2NdedoXclustxC11 , edif2NdedoXclustxC12 ,
                edif2NdedoXclustxC13 , edif2NdedoXclustxC14 , edif2NdedoXclustxC15 , edif2NdedoXclustxC16 , edif2NdedoXclustxC17 ,
                edif2NdedoXclustxC18 , edif2NdedoXclustxC19 , edif2NdedoXclustxC20 , ediNdedoXclustXc1, ediNdedoXclustXc2 ,
                ediNdedoXclustXc3 , ediNdedoXclustXc4 , ediNdedoXclustXc5 , ediNdedoXclustXc6 , ediNdedoXclustXc7 , ediNdedoXclustXc8
                , ediNdedoXclustXc9 , ediNdedoXclustXc10 , ediNdedoXclustXc11 , ediNdedoXclustXc12 , ediNdedoXclustXc13 ,
                ediNdedoXclustXc14 , ediNdedoXclustXc15 , ediNdedoXclustXc16 , ediNdedoXclustXc17 , ediNdedoXclustXc18 ,
                ediNdedoXclustXc19 , ediNdedoXclustXc20 , ediCalByA1, ediCalByA2 , ediCalByA3 , ediCalByA4 , ediCalByA5 ,
                ediCalByA6 , ediCalByA7 , ediCalByA8 , ediCalByA9 , ediCalByA10 , ediCalByA11 , ediCalByA12 , ediCalByA13 ,
                ediCalByA14 , ediCalByA15 , ediCalByA16 , ediCalByA17 , ediCalByA18 , ediCalByA19 , ediCalByA20 , ediCalByA21 ,
                edif2Calib1, edif2Calib2 , edif2Calib3 , edif2Calib4 , edif2Calib5 , edif2Calib6 , edif2Calib7 , edif2Calib8 ,
                edif2Calib9 , edif2Calib10 , edif2Calib11 , edif2Calib12 , edif2Calib13 , edif2Calib14 , edif2Calib15 ,
                edif2Calib16 , edif2Calib17 , edif2Calib18 , edif2Calib19 , edif2Calib20 , edif2Calib21 , edif2Calib22 ,

                mEdiLargDeds1,mEdiLargDeds2,mEdiLargDeds3,mEdiLargDeds4,mEdiLargDeds5,
                mEdiLargDeds6,mEdiLargDeds7,mEdiLargDeds8,mEdiLargDeds9,mEdiLargDeds10,
                mEdiLargDeds11,mEdiLargDeds12,mEdiLargDeds13,mEdiLargDeds14,mEdiLargDeds15,
                mEdiLargDeds16,mEdiLargDeds17,mEdiLargDeds18,mEdiLargDeds19,mEdiLargDeds20,
                mEdiLargDeds21,mEdiLargDeds22,mEdiLargDeds23,mEdiLargDeds24,mEdiLargDeds25,
                mEdiLargDeds26,mEdiLargDeds27,mEdiLargDeds28,mEdiLargDeds29,mEdiLargDeds30,



        } ;


        for(int i = 0; i<arrayAllFields.length; i++) {


            Log.i("indicenull","el indice null es "+i);

            TextInputEditText currenTextImput=arrayAllFields[i];

            if(!currenTextImput.getText().toString().trim().isEmpty()) { //si no esta vacio

                String keyOFidView=String.valueOf(currenTextImput.getId());

                hasHmapFieldsOtherViews.put(keyOFidView,currenTextImput.getText().toString());
            }


        }

        hasHmapFieldsOtherViews.put("0","EMPTY");


    }

    private void eventoUploadFormulario () {

        btnSaveControlC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!cheakIfInfoIsComplete()) {
                    return;

                }

                openBottomSheetConfirmCreateNew();


              ///  DialogConfirmCreateNewForm.showBottomSheetDialogConfirmAndCallUpdate(FormularioControlCalidadPreview.this,Variables.FormCantrolCalidad);





            }
        });

    }

    private void openBottomSheetConfirmCreateNew(){

        DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(Variables.FormCantrolCalidad);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
    }

    private boolean cheakIfInfoIsComplete() {

        if(mEdiVaporzz.getText().toString().trim().isEmpty()){
            mEdiVaporzz.requestFocus() ;
            mEdiVaporzz.setError("Este espacio es necesario") ;


            return false;
        }

        if(mEdiProductorzz.getText().toString().trim().isEmpty()){
            mEdiProductorzz.requestFocus() ;
            mEdiProductorzz.setError("Este espacio es necesario") ;


            return false;
        }


        if(mEdiCodigozz.getText().toString().trim().isEmpty()){
            mEdiCodigozz.requestFocus() ;
            mEdiCodigozz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiZonazz.getText().toString().trim().isEmpty()){
            mEdiZonazz.requestFocus() ;
            mEdiZonazz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiHaciendazz.getText().toString().trim().isEmpty()){
            mEdiHaciendazz.requestFocus() ;
            mEdiHaciendazz.setError("Este espacio es necesario") ;

            return false;
        }



        if(mEdiExportadorazz.getText().toString().trim().isEmpty()){
            mEdiExportadorazz.requestFocus() ;
            mEdiExportadorazz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiCompaniazz.getText().toString().trim().isEmpty()){
            mEdiCompaniazz.requestFocus() ;
            mEdiCompaniazz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiClientezz.getText().toString().trim().isEmpty()){
            mEdiClientezz.requestFocus() ;
            mEdiClientezz.setError("Este espacio es necesario") ;


            return false;
        }


        if(mEdisemanazz.getText().toString().trim().isEmpty()){
            mEdisemanazz.requestFocus() ;
            mEdisemanazz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiFechazz.getText().toString().trim().isEmpty()){
            mEdiFechazz.requestFocus() ;
            mEdiFechazz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiMagapzz.getText().toString().trim().isEmpty()){
            mEdiMagapzz.requestFocus() ;
            mEdiMagapzz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiMarcaCajazz.getText().toString().trim().isEmpty()){
            mEdiMarcaCajazz.requestFocus() ;
            mEdiMarcaCajazz.setError("Este espacio es necesario") ;


            return false;
        }



        if(mEdiTipoEmpazz.getText().toString().trim().isEmpty()){
            mEdiTipoEmpazz.requestFocus() ;
            mEdiTipoEmpazz.setError("Este espacio es necesario") ;
            return false;
        }



        if(mEdiDestinzz.getText().toString().trim().isEmpty()){
            mEdiDestinzz.requestFocus() ;
            mEdiDestinzz.setError("Este espacio es necesario") ;
            return false;
        }



        if(mEdiTotalCajaszz.getText().toString().trim().isEmpty()){
            mEdiTotalCajaszz.requestFocus() ;
            mEdiTotalCajaszz.setError("Este espacio es necesario") ;
            return false;
        }



        if(mEdioCalidaCampzz.getText().toString().trim().isEmpty()){
            mEdioCalidaCampzz.requestFocus() ;
            mEdioCalidaCampzz.setError("Este espacio es necesario") ;

            return false;
        }



        if(mEdiHoraInizz.getText().toString().trim().isEmpty()){
            mEdiHoraInizz.requestFocus() ;
            mEdiHoraInizz.setError("Este espacio es necesario") ;

            return false;
        }



        if(mEdiHoraTermizz.getText().toString().trim().isEmpty()){
            mEdiHoraTermizz.requestFocus() ;
            mEdiHoraTermizz.setError("Este espacio es necesario") ;

            return false;
        }


        if(mEdiContenedorzz.getText().toString().trim().isEmpty()){
            mEdiContenedorzz.requestFocus() ;
            mEdiContenedorzz.setError("Este espacio es necesario") ;

            return false;
        }



        if(mEdiSellosnavzz.getText().toString().trim().isEmpty()){
            mEdiSellosnavzz.requestFocus() ;
            mEdiSellosnavzz.setError("Este espacio es necesario") ;

            return false;
        }




        if(mEdiSelloVerzz.getText().toString().trim().isEmpty()){
            mEdiSelloVerzz.requestFocus() ;
            mEdiSelloVerzz.setError("Este espacio es necesario") ;

            return false;
        }




        if(mEdiTermografozz.getText().toString().trim().isEmpty()){
            mEdiTermografozz.requestFocus() ;
            mEdiTermografozz.setError("Este espacio es necesario") ;
            return false;
        }




        if(mEdiPlacaCarrzz.getText().toString().trim().isEmpty()){
            mEdiPlacaCarrzz.requestFocus() ;
            mEdiPlacaCarrzz.setError("Este espacio es necesario") ;
            return false;
        }


        if(mEdiPuertEmbzz.getText().toString().trim().isEmpty()){
            mEdiPuertEmbzz.requestFocus() ;
            mEdiPuertEmbzz.setError("Este espacio es necesario") ;
            return false;
        }


        return true;

    }

    private ControlCalidad udpadteFormulario(ControlCalidad antiguoControlCal){

        ControlCalidad  controlCaL = new ControlCalidad(ediObservacioneszszz.getText().toString(),"nodekeylocal","keyWhereLocateasHmapFieldsRecha",
                mEdiVaporzz.getText().toString(),mEdiProductorzz.getText().toString(),mEdiCodigozz.getText().toString(),
                mEdiZonazz.getText().toString(),mEdiHaciendazz.getText().toString(),mEdiExportadorazz.getText().toString(),
                mEdiCompaniazz.getText().toString(),mEdiClientezz.getText().toString(),Integer.parseInt(mEdisemanazz.getText().toString()), mEdiFechazz.getText().toString(),mEdiMagapzz.getText().toString(),mEdiMarcaCajazz.getText().toString(),
                mEdiTipoEmpazz.getText().toString(),mEdiDestinzz.getText().toString(),Integer.parseInt(mEdiTotalCajaszz.getText().toString()),
                mEdioCalidaCampzz.getText().toString(),mEdiHoraInizz.getText().toString(),mEdiHoraTermizz.getText().toString(),
                mEdiContenedorzz.getText().toString(),mEdiSellosnavzz.getText().toString(),mEdiSelloVerzz.getText().toString(),
                mEdiTermografozz.getText().toString(),mEdiPlacaCarrzz.getText().toString(),mEdiPuertEmbzz.getText().toString());



        //aqctualizamos ALGUNOS DATOS
        controlCaL .setKeyWhereLocateasHmapFieldsRecha(antiguoControlCal.getKeyWhereLocateasHmapFieldsRecha());
        controlCaL.setKeyDondeEstaraHasmapDefecSelec(antiguoControlCal.getKeyDondeEstaraHasmapDefecSelec());
        controlCaL.setSimpleDate(antiguoControlCal.getSimpleDate());
        controlCaL.setKeyDondeEstarThisInform(antiguoControlCal.getKeyDondeEstarThisInform());


        return controlCaL;

    }




    public void saveInfo() {

        Log.i("misdtass", "el nodo del objeto current es " + Variables.currenControlCalReport.getKeyDondeEstarThisInform());
        // hasHmapFieldsOtherViews
        createMapInBYothersTextimpuEdFields();
        createInfoToHashmapRechazaSelecToUpload();
        createItemsSelectDefectsEmpqTOuPLOAD();
        ControlCalidad obecjControlCalidad = udpadteFormulario(Variables.currenControlCalReport);

        RealtimeDB.UpdateControlcalidadInform(obecjControlCalidad, Variables.currenControlCalReport.getKeyDondeEstarThisInform());
        RealtimeDB.updateHashMapControlCalidad(hasHmapFieldsOtherViews, Variables.currenControlCalReport.getKeyWhereLocateasHmapFieldsRecha());
        RealtimeDB.updateHasmapDefectSelec(hasMapitemsSelecPosicRechazToUpload, Variables.currenControlCalReport.getKeyDondeEstaraHasmapDefecSelec());

    }


    private void updateDatosHdaEnfunde(SetInformDatsHacienda informe) {



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


           for(int i=0; i<array.length; i++){

               TextInputEditText current =array [i];

               if(!current.getText().toString().trim().isEmpty()){
                   String value=current.getText().toString();
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


    }


}