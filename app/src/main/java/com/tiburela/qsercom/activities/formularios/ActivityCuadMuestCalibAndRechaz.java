package com.tiburela.qsercom.activities.formularios;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.Constants.Constants;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.adapters.RecyclerVAdapterColorCintSem;
import com.tiburela.qsercom.callbacks.CallbackUploadNewReport;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.models.InformRegister;
import com.tiburela.qsercom.utils.SharePrefHelper;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActivityCuadMuestCalibAndRechaz extends AppCompatActivity implements CallbackUploadNewReport {
    String currentKeySharePrefrences="";

    public static CallbackUploadNewReport callbackUploadNewReport;


    Button btnSaveLocale;

    boolean userCreoRegisterForm=false;

    RecyclerView mireciclerv;
    ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList;
    Button btnSaveCambios;

    long millisDateSelect;
    TextView hindeviewTxt;

    TextInputEditText ediSemanaxc;
    TextInputEditText ediExportadora;
    TextInputEditText ediVaporx;
    TextInputEditText ediFechax;
    TextInputEditText ediProductoras;
    TextInputEditText ediCodigoxs;
    TextInputEditText ediEnfundex;
    TextInputEditText ediExtCalidad;
    TextInputEditText ediExteRodillo;
    TextInputEditText ediExtGancho;
    TextInputEditText ediMutante;
    TextInputEditText ediSPEKLING;
    TextInputEditText ediPuntaamarillayB;
    TextInputEditText ediCremaAlmendraFloja;
    TextInputEditText ediManchaRoja;
    TextInputEditText ediAlterados;
    TextInputEditText ediPobres;
    TextInputEditText ediCaidos;
    TextInputEditText ediSobreGrado;
    TextInputEditText ediBajoGrado;
    TextInputEditText edimosaico;
    TextInputEditText ediDanoDeAnimal;
    TextInputEditText ediExplosivo;
    TextInputEditText ediErwinea;
    TextInputEditText ediDedoCorto;
    TextInputEditText ediRacimosPesadosDeEdad;
    TextInputEditText ediCochinillaEscamaFumagina;
    TextInputEditText ediRacimosSinEdintificacion;


    EditText ediColor14;
    EditText ediColor13;
    EditText ediColor12;
    EditText ediColor11;
    EditText ediColor10;
    EditText ediColor9;



    TextView txtTotalRechazados;

    ImageView imageViewUpdate1;

    ImageView imgVupdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_cuadro_muestreo_recha);
        callbackUploadNewReport = this;


        btnSaveLocale=findViewById(R.id.btnSaveLocale);

        ediMutante=findViewById(R.id.ediMutante);
        ediSPEKLING=findViewById(R.id.ediSPEKLING);
        ediPuntaamarillayB=findViewById(R.id.ediPuntaamarillayB);
        ediCremaAlmendraFloja=findViewById(R.id.ediCremaAlmendraFloja);
        ediManchaRoja=findViewById(R.id.ediManchaRoja);
        ediAlterados=findViewById(R.id.ediAlterados);    
        ediPobres=findViewById(R.id.ediPobres);
        ediCaidos=findViewById(R.id.ediCaidos);
        ediSobreGrado=findViewById(R.id.ediSobreGrado);
        ediBajoGrado=findViewById(R.id.ediBajoGrado);
        edimosaico=findViewById(R.id.edimosaico);
        ediDanoDeAnimal=findViewById(R.id.ediDanoDeAnimal);
        ediExplosivo=findViewById(R.id.ediExplosivo);
        ediDedoCorto=findViewById(R.id.ediDedoCorto);
        ediRacimosPesadosDeEdad=findViewById(R.id.ediRacimosPesadosDeEdad);
        ediCochinillaEscamaFumagina=findViewById(R.id.ediCochinillaEscamaFumagina);
        ediRacimosSinEdintificacion=findViewById(R.id.ediRacimosSinEdintificacion);
        ediErwinea=findViewById(R.id.ediErwinea);

        btnSaveCambios=findViewById(R.id.btnSaveCambios);
        ediSemanaxc=findViewById(R.id.ediSemanaxc);
        ediExportadora=findViewById(R.id.ediExportadora);
        ediVaporx=findViewById(R.id.ediVaporx);
        ediFechax=findViewById(R.id.ediFechax);
        ediProductoras=findViewById(R.id.ediProductoras);
        ediCodigoxs=findViewById(R.id.ediCodigoxs);
        ediEnfundex=findViewById(R.id.ediEnfundex);

        ediExtCalidad=findViewById(R.id.ediExtCalidad);
        ediExteRodillo=findViewById(R.id.ediExteRodillo);
        ediExtGancho=findViewById(R.id.ediExtGancho);
        txtTotalRechazados=findViewById(R.id.txtTotalRechazados);
        imgVupdate=findViewById(R.id.imgVupdate);

        hindeviewTxt=findViewById(R.id.hindeviewTxt);

        imageViewUpdate1=findViewById(R.id.imageViewUpdate1);

         ediColor9=findViewById(R.id.ediColor9);
        ediColor10=findViewById(R.id.ediColor10);
        ediColor11=findViewById(R.id.ediColor11);
        ediColor12=findViewById(R.id.ediColor12);
        ediColor13=findViewById(R.id.ediColor13);
        ediColor14=findViewById(R.id.ediColor14);


        Variables.activityCurrent=Variables.FormMuestreoRechaz;



        Bundle extras = getIntent().getExtras();





        imageViewUpdate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ///agfdfg
                muestraResultsCuadroMuetreo1(mireciclerv);
            }
        });


        hindeviewTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mireciclerv.getVisibility()==View.VISIBLE){
                    mireciclerv.setVisibility(View.GONE);

                    Log.i("midtarer","es if ");

                }else{
                    mireciclerv.setVisibility(View.VISIBLE);
                    Log.i("midtarer","es else  ");


                }


            }
        });




        imgVupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CuadroMuestreo objec= new CuadroMuestreo(0,"","",""
                        ,"", "","","",
                        "", "","","","","","",""
                );

                ///editamos los otos datos de la cantidad de rechzados..
                CuadroMuestreo objectWhitMoreData=addRechazadosData(objec);



            String totalRechazados=String.valueOf( obtenTotaLrechazados(objectWhitMoreData));
            txtTotalRechazados.setText(totalRechazados);


            }
        });

        btnSaveCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(chekeadDataListIsReady()){



                    //creamos un objeto
                    RealtimeDB.initDatabasesRootOnly();
                    String keyDondeEstaraHashmap=RealtimeDB.rootDatabaseReference.push().getKey();



                    CuadroMuestreo objec= new CuadroMuestreo(Integer.parseInt(ediSemanaxc.getText().toString()),ediExportadora.getText().toString(),ediVaporx.getText().toString(),ediProductoras.getText().toString()
                            ,ediCodigoxs.getText().toString(), ediEnfundex.getText().toString(),keyDondeEstaraHashmap,ediExtCalidad.getText().toString(),
                            ediExteRodillo.getText().toString(),ediExtGancho.getText().toString(),
                            ediColor14.getText().toString(),ediColor13.getText().toString(),ediColor12.getText().toString(),ediColor11.getText().toString()
                            ,ediColor10.getText().toString(),ediColor9.getText().toString()


                            );


                    ///editamos los otros datos de la cantidad de rechzados..
                    //CuadroMuestreo objectWhitMoreData=addRechazadosData(objec);


                    int totalRechazados=obtenTotaLrechazados(objec);
                    Log.i("eldaterr","el total rechzados es "+totalRechazados);
                    objec.setTotalRechazadosAll(totalRechazados);


                    generateUniqueIdInformeAndContinuesIfIdIsUnique(objec);


                  HashMap<String,ColorCintasSemns>mapita= iterateItemsOfReciclerViewAndAddDataToMap(mireciclerv);

                    RealtimeDB.addNewCuadroMuestreoHasMap(mapita,keyDondeEstaraHashmap); //subimos el mapa ,le pasamos el mapa como cparaametro y el key donde estara


                    //   Toast.makeText(ActivityCuadMuestCalibAndRechaz.this, "Se Guardo Informe", Toast.LENGTH_SHORT).show();
                     Log.i("saber"," se subio la data ");



                }


            }
        });


        btnSaveLocale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPrefrencesSaveData();
            }
        });



        mireciclerv=findViewById(R.id.mireciclerv);

        ColorCintasSemnsArrayList=new ArrayList<>();

        for(int indice=0; indice<31; indice++){

            ColorCintasSemns    object= new ColorCintasSemns(indice+1,0,0,0,0,0,0);
            ColorCintasSemnsArrayList.add(object);

        }

        setRECICLERdata(ColorCintasSemnsArrayList);

       // createMapInitial();


        ediFechax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //slecioamofecha
                selecionaFecha();

            }
        });



        ediFechax.setKeyListener(null);
        ediFechax.setCursorVisible(false);


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

    private void AddDataFormOfSharePrefeIfExistPrefrencesMap() {

        View [] arrrayAllViews=creaArryOfViewsAll();



        try {
            Log.i("preferido","el currentKeySharePrefrences es  "+currentKeySharePrefrences);

            HashMap<String, String> currentMapPreferences= (HashMap<String, String>) SharePref.loadMap(currentKeySharePrefrences);
            Log.i("preferido","el size de mapa es "+currentMapPreferences.size());
            Utils.addDataOfPrefrencesInView(arrrayAllViews,currentMapPreferences);


            Map<String, ColorCintasSemns> map=SharePref.getMapColorCintsSemns(currentKeySharePrefrences+"ColorCintasSemns");

            Log.i("hunejo","el map de preferencias es  "+map.size());

            Variables.esUnFormularioOfflienSharePref =true;


            for(ColorCintasSemns object:map.values()){
                Log.i("hunejoxx","el fiel num 14 es  "+object.getColumFieldNUm14());


            }

            setDataInViewMapData(map);



            Variables.esUnFormularioOfflienSharePref =false;



        }


        catch (Exception e) {

            Log.i("preferido","la expecion es "+e.getMessage());
            e.printStackTrace();


        }



    }

    private void setDataInViewMapData(Map<String, ColorCintasSemns> mapColorCintasSemanas ){


        //AHORA EL MAPA//ITERAMOS EL MAPA
        ArrayList<ColorCintasSemns>milista=new ArrayList<>();

        for (Map.Entry<String, ColorCintasSemns > entry : mapColorCintasSemanas.entrySet()) {
            // String keyAndIdOfView = entry.getKey();
            ColorCintasSemns valueOfItem = entry.getValue();
            ///podemos crear un arra list y organizarlo de mayor menor a mayor,pero por ahora
            milista.add(valueOfItem);


            Log.i("hunejo","el semana 14 de mapa es "+valueOfItem.getColumFieldNUm14());

            //Agregamos este valor en este edi text
        }


        Collections.sort(milista, new Comparator<ColorCintasSemns>()
        {
            @Override
            public int compare(ColorCintasSemns lhs, ColorCintasSemns rhs) {

                return Integer.valueOf(lhs.getSemanNum()).compareTo(rhs.getSemanNum());
            }
        });



        setRECICLERdata(milista);


    }


    private View[] creaArryOfViewsAll() {


        View[] arraViews={
                ediSemanaxc, ediExportadora, ediVaporx, ediFechax, ediProductoras, ediCodigoxs, ediEnfundex,
                ediExtCalidad, ediExteRodillo, ediExtGancho, ediMutante, ediSPEKLING, ediPuntaamarillayB,
                ediCremaAlmendraFloja, ediManchaRoja, ediAlterados, ediPobres, ediCaidos, ediSobreGrado,
                ediBajoGrado, edimosaico, ediDanoDeAnimal, ediExplosivo, ediErwinea, ediDedoCorto,
                ediRacimosPesadosDeEdad, ediCochinillaEscamaFumagina, ediRacimosSinEdintificacion, ediColor14,
                ediColor13,  ediColor12,  ediColor11,  ediColor10,  ediColor9,
                txtTotalRechazados
        };

        return  arraViews;


    }


    private void callPrefrencesSaveData(){

        View [] arrayAllViewsData=creaArryOfViewsAll();

        /**agregamos data al hasmpa de */


        HashMap<String,ColorCintasSemns> miMap=iterateItemsOfReciclerViewAndAddDataToMap(mireciclerv);//asi agregamos objetos al mapque depsues gaurdamos


        Log.i("preferido","el current key es "+currentKeySharePrefrences);


        if(!currentKeySharePrefrences.equals("") || userCreoRegisterForm){  //si no contiene
            Log.i("saberrr","se ejecuto el if ");

            SharePrefHelper.viewsSaveInfo(arrayAllViewsData,currentKeySharePrefrences,ActivityCuadMuestCalibAndRechaz.this);

            SharePref.saveHashMapColorCintsSemanas(miMap,currentKeySharePrefrences+"ColorCintasSemns");


            Toast.makeText(ActivityCuadMuestCalibAndRechaz.this, "Guardado Localmente", Toast.LENGTH_SHORT).show();


            //significa que tenemos un key de un objeto obtneido de prefrencias

        }

        else
        { //no existe creamos un nuevo register..
            Log.i("saberrr","se ejecuto el else ");


            Map<String, InformRegister>miMpaAllrRegisters=SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);


             currentKeySharePrefrences= UUID.randomUUID().toString();

            InformRegister inform= new InformRegister(currentKeySharePrefrences,Constants.CUADRO_MUESTRO_CAL_RECHZDS,"Usuario", "","Cuadro Muestreo"  );


            //gudramos oejto en el mapa
            miMpaAllrRegisters.put(inform.getInformUniqueIdPertenece(),inform);

            SharePref.saveHashMapOfHashmapInformRegister(miMpaAllrRegisters,SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

            //guardamos info de  views en un mapa usnado el nismo id delobejto creado
            SharePrefHelper.viewsSaveInfo(arrayAllViewsData,currentKeySharePrefrences,ActivityCuadMuestCalibAndRechaz.this);
            SharePref.saveHashMapColorCintsSemanas(miMap,currentKeySharePrefrences+"ColorCintasSemns");

            Toast.makeText(ActivityCuadMuestCalibAndRechaz.this, "Guardado Localmente", Toast.LENGTH_SHORT).show();


            userCreoRegisterForm=true;


        }






    }


    private void generateUniqueIdInformeAndContinuesIfIdIsUnique( CuadroMuestreo cuadorMuestreo){

        String uniqueId =String.valueOf(Utils.generateNumRadom6Digits());
        Log.i("elnumber","el numero generado es ss"+uniqueId);

        checkIfExistIdAndUpload(uniqueId,cuadorMuestreo);


    }

    private void checkIfExistIdAndUpload (String currenTidGenrate,  CuadroMuestreo cuadroMuetreo){

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

                    informRegister= new InformRegister(currenTidGenrate,Constants.CUADRO_MUESTRO_CAL_RECHZDS,
                            Variables.usuarioQserconGlobal.getNombreUsuario(),
                            Variables.usuarioQserconGlobal.getUniqueIDuser()
                            , "CUADRO MUESTREO");


                    //informe register
                    RealtimeDB.addNewRegistroInforme(ActivityCuadMuestCalibAndRechaz.this,informRegister);


                    cuadroMuetreo.setUniqueIdObject(currenTidGenrate);
                    //informe actual


                    RealtimeDB.addNewCuadroMuestreoObject(cuadroMuetreo); //subimos un cuadro de muestreo object




                    //aqui subimos..

                }else {  //si exite creamos otro value...

                    generateUniqueIdInformeAndContinuesIfIdIsUnique(cuadroMuetreo);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }








    private void setRECICLERdata(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList ) {

        Log.i("debugeoxc","call here set recicler ");

        RecyclerVAdapterColorCintSem adapter=new RecyclerVAdapterColorCintSem(ColorCintasSemnsArrayList,this, ActivityCuadMuestCalibAndRechaz.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActivityCuadMuestCalibAndRechaz.this);

        mireciclerv.setNestedScrollingEnabled(false);
        mireciclerv.setLayoutManager(layoutManager);
       // mireciclerv.setHasFixedSize(true);

        mireciclerv.setAdapter(adapter);


    }



    private CuadroMuestreo addRechazadosData(CuadroMuestreo object){


        if(!ediMutante.getText().toString().trim().isEmpty()){
            object.setMutantes(Integer.parseInt(ediMutante.getText().toString()));
        }

        if(!ediSPEKLING.getText().toString().trim().isEmpty()){
            object.setSpekling(Integer.parseInt(ediSPEKLING.getText().toString()));
        }


        if(!ediPuntaamarillayB.getText().toString().trim().isEmpty()){
            object.setPtaAmarillaYb(Integer.parseInt(ediPuntaamarillayB.getText().toString()));
        }


        if(!ediCremaAlmendraFloja.getText().toString().trim().isEmpty()){
            object.setCremaAlmendraFloja(Integer.parseInt(ediCremaAlmendraFloja.getText().toString()));
        }



        if(!ediManchaRoja.getText().toString().trim().isEmpty()){
            object.setManchaRoja(Integer.parseInt(ediManchaRoja.getText().toString()));
        }



        if(!ediAlterados.getText().toString().trim().isEmpty()){
            object.setAlterados(Integer.parseInt(ediAlterados.getText().toString()));
        }


        if(!ediPobres.getText().toString().trim().isEmpty()){
            object.setPobres(Integer.parseInt(ediPobres.getText().toString()));
        }


        if(!ediCaidos.getText().toString().trim().isEmpty()){
            object.setCaidos(Integer.parseInt(ediCaidos.getText().toString()));
        }


        if(!ediSobreGrado.getText().toString().trim().isEmpty()){
            object.setSobreGrado(Integer.parseInt(ediSobreGrado.getText().toString()));
        }


        if(!ediBajoGrado.getText().toString().trim().isEmpty()){
            object.setBajoGrado(Integer.parseInt(ediBajoGrado.getText().toString()));
        }


        if(!edimosaico.getText().toString().trim().isEmpty()){
            object.setMosaico(Integer.parseInt(edimosaico.getText().toString()));
        }



        if(!ediDanoDeAnimal.getText().toString().trim().isEmpty()){
            object.setDanoAnimal(Integer.parseInt(ediDanoDeAnimal.getText().toString()));
        }


        if(!ediExplosivo.getText().toString().trim().isEmpty()){
            object.setExplosivo(Integer.parseInt(ediExplosivo.getText().toString()));
        }


        if(!ediErwinea.getText().toString().trim().isEmpty()){
            object.setErwinea(Integer.parseInt(ediErwinea.getText().toString()));
        }


        if(!ediDedoCorto.getText().toString().trim().isEmpty()){
            object.setDedoCorto(Integer.parseInt(ediDedoCorto.getText().toString()));
        }


        if(!ediRacimosPesadosDeEdad.getText().toString().trim().isEmpty()){
            object.setRacimosPasadosEdad(Integer.parseInt(ediRacimosPesadosDeEdad.getText().toString()));
        }


        if(!ediCochinillaEscamaFumagina.getText().toString().trim().isEmpty()){
            object.setCochinillaEscamaFunagina(Integer.parseInt(ediCochinillaEscamaFumagina.getText().toString()));
        }


        if(!ediRacimosSinEdintificacion.getText().toString().trim().isEmpty()){
            object.setRacimosSinEdintificacion(Integer.parseInt(ediRacimosSinEdintificacion.getText().toString()));
        }



return object;


    }

    ///le agregamos un tag de un id...



    private void createMapInitial(){

        Variables.mapColorCintasSemanas=new HashMap<>();

        for(int indice=0; indice<ColorCintasSemnsArrayList.size(); indice++){

            ColorCintasSemns object= ColorCintasSemnsArrayList.get(indice);
            String key=ColorCintasSemnsArrayList.get(indice).getUniqueId();
            Variables.mapColorCintasSemanas.put(key,object);


        }


    }





    private boolean chekeadDataListIsReady(){


        if(Variables.usuarioQserconGlobal==null){
            Toast.makeText(ActivityCuadMuestCalibAndRechaz.this, "No puedes subir hasta que inicies sesión, ¡Guárdalo  localmente", Toast.LENGTH_LONG).show();
            return false;
        }



       if(ediSemanaxc.getText().toString().trim().isEmpty()){
           ediSemanaxc.requestFocus();
           ediSemanaxc.setError("Este dato es requerido");

           return false;
       }


        if(ediVaporx.getText().toString().trim().isEmpty()){
            ediVaporx.requestFocus();
            ediVaporx.setError("Este dato es requerido");

            return false;
        }



        if(ediFechax.getText().toString().trim().isEmpty()){
            ediFechax.requestFocus();
            ediFechax.setError("Este dato es requerido");

            return false;
        }



        if(ediProductoras.getText().toString().trim().isEmpty()){
            ediProductoras.requestFocus();
            ediProductoras .setError("Este dato es requerido");

            return false;
        }



        if(ediCodigoxs.getText().toString().trim().isEmpty()){
            ediCodigoxs.requestFocus();
            ediCodigoxs.setError("Este dato es requerido");

            return false;
        }


        if(ediEnfundex.getText().toString().trim().isEmpty()){
            ediEnfundex.requestFocus();
            ediEnfundex.setError("Este dato es requerido");

            return false;
        }


        if(ediExtCalidad.getText().toString().trim().isEmpty()){
            ediExtCalidad.requestFocus();
            ediExtCalidad.setError("Este dato es requerido");

            return false;
        }


        /*
        if(ediExteRodillo.getText().toString().trim().isEmpty()){
            ediExteRodillo.requestFocus();
            ediExteRodillo.setError("Este dato es requerido");

            return false;
        }



        if(ediExtGancho.getText().toString().trim().isEmpty()){
            ediExtGancho.requestFocus();
            ediExtGancho.setError("Este dato es requerido");

            return false;
        }
*/



        return true;
    }



    private void setDataInViews(CuadroMuestreo cuadroMuestreo, HashMap<String, ColorCintasSemns> mapColorCintasSemanas ){
        //agregamos la data dde cuadro de muestro..

 //aqui ya debemos tener un mpaa  mejor seria usar el mapa global que tenemos en la clase variables

         ediSemanaxc.setText(String.valueOf(cuadroMuestreo.getSemanaNum()));
         ediVaporx.setText(cuadroMuestreo.getVapor());
         ediFechax.setText(cuadroMuestreo.getSimpleDateFormat());
         ediProductoras.setText(cuadroMuestreo.getProductor());
         ediCodigoxs.setText(cuadroMuestreo.getCodigo());
         ediEnfundex.setText(cuadroMuestreo.getEnfunde());



         //con racimos rechazados



         //AHORA EL MAPA//ITERAMOS EL MAPA
           ArrayList<ColorCintasSemns>milista=new ArrayList<>();

        for (Map.Entry<String, ColorCintasSemns > entry : mapColorCintasSemanas.entrySet()) {
           // String keyAndIdOfView = entry.getKey();
            ColorCintasSemns valueOfItem = entry.getValue();

            ///podemos crear un arra list y organizarlo de mayor menor a mayor,pero por ahora
            milista.add(valueOfItem);

            //Agregamos este valor en este edi text

        }


         //antes d ellamar la lista no olvidar de  ordenarlo de menor a mayor...

        setRECICLERdata(milista);


    }




    private int obtenTotaLrechazados(CuadroMuestreo cuadroMuestreo){
        ///iterate values object
        int  sum_of_values = 0;

        sum_of_values=cuadroMuestreo.getMutantes()+cuadroMuestreo.getSpekling()+cuadroMuestreo.getPtaAmarillaYb()+cuadroMuestreo.getCremaAlmendraFloja()+
        cuadroMuestreo.getManchaRoja()+cuadroMuestreo.getAlterados()+cuadroMuestreo.getPobres()+cuadroMuestreo.getCaidos()+cuadroMuestreo.getSobreGrado()+
                cuadroMuestreo.getBajoGrado()+cuadroMuestreo.getMosaico()+cuadroMuestreo.getDanoAnimal()+cuadroMuestreo.getExplosivo()+cuadroMuestreo.getErwinea()+
                cuadroMuestreo.getDedoCorto()+cuadroMuestreo.getRacimosPasadosEdad()+cuadroMuestreo.getCochinillaEscamaFunagina()+cuadroMuestreo.getRacimosSinEdintificacion();



return  sum_of_values;
    }




private TextInputEditText[] devuleArrayTiEditext(){
        TextInputEditText [] arrayDevolver={
                ediSemanaxc, ediExportadora, ediVaporx, (TextInputEditText) ediFechax, ediProductoras,
                ediCodigoxs, ediEnfundex, ediExtCalidad, ediExteRodillo, ediExtGancho,
                ediMutante, ediSPEKLING, ediPuntaamarillayB, ediCremaAlmendraFloja,
                ediManchaRoja, ediAlterados, ediPobres, ediCaidos, ediSobreGrado,
                ediBajoGrado, edimosaico, ediDanoDeAnimal, ediExplosivo, ediErwinea,
                ediDedoCorto, ediRacimosPesadosDeEdad, ediCochinillaEscamaFumagina,
                ediRacimosSinEdintificacion,

        };



        return  arrayDevolver;
}










    void selecionaFecha(){

        final Calendar cldr = Calendar.getInstance();
        int year = cldr.get(Calendar.YEAR);
        int daySemana = cldr.get(Calendar.DAY_OF_WEEK);
        int mes = cldr.get(Calendar.MONTH);

        // time picker dialog
        DatePickerDialog picker = new DatePickerDialog(ActivityCuadMuestCalibAndRechaz.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                        String dateSelec=i2+"/"+i1+"/"+i;

                        ediFechax.setText(dateSelec);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


                        Date date = null;
                        try {
                            date = sdf.parse(dateSelec);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        millisDateSelect = date.getTime();


                    }
                }, year,  mes, daySemana);

        picker.setButton(DialogInterface.BUTTON_POSITIVE, "OK", picker);
        picker.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", picker);


        picker.show();
    }




    public   HashMap<String, ColorCintasSemns> iterateItemsOfReciclerViewAndAddDataToMap(RecyclerView mirecicler){

        int valueNUM14;
        int valueNUM13;
        int valueNUM12;
        int valueNUM11;
        int valueNUM10;
        int valueNUM9;


        HashMap<String, ColorCintasSemns>mapa= new HashMap<>();


        for (int i = 0; i < mirecicler.getChildCount(); i++) {
            RecyclerVAdapterColorCintSem.RecyclerViewHolder holder = (RecyclerVAdapterColorCintSem.RecyclerViewHolder) mirecicler.findViewHolderForAdapterPosition(i);

            Log.i("samaerino","el value es "+ holder.semnNum.getText().toString());

             valueNUM14=0;
             valueNUM13=0;
             valueNUM12=0;
             valueNUM11=0;
             valueNUM10=0;
             valueNUM9=0;


            String uniqueIdObjec=holder.semnNum.getTag().toString();
            Log.i("samaerino","el tag es  "+uniqueIdObjec);



            if(! holder.ediColum14.getText().toString().trim().isEmpty()){
                valueNUM14=Integer.parseInt(holder.ediColum14.getText().toString());
                Log.i("hunejo","el valu num 14 es ... "+valueNUM14);

            }

            if(!holder.ediColum13.getText().toString().trim().isEmpty()){
                valueNUM13=Integer.parseInt(holder.ediColum13.getText().toString());

            }

            if(!holder.ediColum12.getText().toString().trim().isEmpty()){
                valueNUM12=Integer.parseInt(holder.ediColum12.getText().toString());

            }

            if(!holder.ediColum11.getText().toString().trim().isEmpty()){
                valueNUM11=Integer.parseInt(holder.ediColum11.getText().toString());

            }

            if(!holder.ediColum10.getText().toString().trim().isEmpty()){
                valueNUM10=Integer.parseInt(holder.ediColum10.getText().toString());

            }

            if(!holder.ediColum9.getText().toString().trim().isEmpty()){
                valueNUM9=Integer.parseInt(holder.ediColum9.getText().toString());

            }



            ColorCintasSemns objec= new  ColorCintasSemns(Integer.parseInt(holder.semnNum.getText().toString()),
                    valueNUM14,valueNUM13,valueNUM12,valueNUM11,valueNUM10,valueNUM9);
            objec.setUniqueId(uniqueIdObjec);

            mapa.put(uniqueIdObjec,objec );



            //aqui agudamos..... 2

            //GAURDA,PS

        }

         return  mapa;

    }




    private void muestraResultsCuadroMuetreo1(RecyclerView mirecicler){

        TextView txtTotalSem14=findViewById(R.id.txtTotalSem14);
        TextView txtTotalSem13=findViewById(R.id.txtTotalSem13);
        TextView txtTotalSem12=findViewById(R.id.txtTotalSem12);
        TextView txtTotalSem11=findViewById(R.id.txtTotalSem11);
        TextView txtTotalSem10=findViewById(R.id.txtTotalSem10);
        TextView txtTotalSem9=findViewById(R.id.txtTotalSem9);

        TextView txtCalSem14=findViewById(R.id.txtCalSem14);
        TextView txtCalSem13=findViewById(R.id.txtCalSem13);
        TextView txtCalSem12=findViewById(R.id.txtCalSem12);
        TextView txtCalSem11=findViewById(R.id.txtCalSem11);
        TextView txtCalSem10=findViewById(R.id.txtCalSem10);
        TextView txtCalSem9=findViewById(R.id.txtCalSem9);

        TextView txtPercetSem14=findViewById(R.id.txtPercetSem14);
        TextView txtPercetSem13=findViewById(R.id.txtPercetSem13);
        TextView txtPercetSem12=findViewById(R.id.txtPercetSem12);
        TextView txtPercetSem11=findViewById(R.id.txtPercetSem11);
        TextView txtPercetSem10=findViewById(R.id.txtPercetSem10);
        TextView txtPercetSem9=findViewById(R.id.txtPercetSem9);

        TextView txtTotalMuestrsSem14=findViewById(R.id.txtTotalMuestrsSem14);
        TextView txtTotalMuestrsSem13=findViewById(R.id.txtTotalMuestrsSem13);
        TextView txtTotalMuestrsSem12=findViewById(R.id.txtTotalMuestrsSem12);
        TextView txtTotalMuestrsSem11=findViewById(R.id.txtTotalMuestrsSem11);
        TextView txtTotalMuestrsSem10=findViewById(R.id.txtTotalMuestrsSem10);
        TextView txtTotalMuestrsSem9=findViewById(R.id.txtTotalMuestrsSem9);



        int totalMuestrasSemana14=0;
        int totalMuestrasSemana13=0;
        int totalMuestrasSemana12=0;
        int totalMuestrasSemana11=0;
        int totalMuestrasSemana10=0;
        int totalMuestrasSemana9=0;


        float colum9ValuesSum=0;
        float colum10ValuesSum=0;
        float colum11ValuesSum=0;
        float colum12ValuesSum=0;
        float colum13ValuesSum=0;
        float colum14ValuesSum=0;



        for (int i = 0; i < mirecicler.getChildCount(); i++) {
            RecyclerVAdapterColorCintSem.RecyclerViewHolder holder = (RecyclerVAdapterColorCintSem.RecyclerViewHolder) mirecicler.findViewHolderForAdapterPosition(i);

            Log.i("samaerino","el value es "+ holder.semnNum.getText().toString());


            String uniqueIdObjec=holder.semnNum.getTag().toString();
            Log.i("samaerino","el tag es  "+uniqueIdObjec);



            if(! holder.ediColum14.getText().toString().trim().isEmpty()){
                colum14ValuesSum=colum14ValuesSum+Integer.parseInt(holder.ediColum14.getText().toString());
                totalMuestrasSemana14++;

            }

            if(!holder.ediColum13.getText().toString().trim().isEmpty()){
                colum13ValuesSum=colum13ValuesSum+Integer.parseInt(holder.ediColum13.getText().toString());
                totalMuestrasSemana13++;

            }

            if(!holder.ediColum12.getText().toString().trim().isEmpty()){
                colum12ValuesSum=colum12ValuesSum+Integer.parseInt(holder.ediColum12.getText().toString());
                totalMuestrasSemana12++;
            }

            if(!holder.ediColum11.getText().toString().trim().isEmpty()){
                colum11ValuesSum=colum11ValuesSum+Integer.parseInt(holder.ediColum11.getText().toString());
                totalMuestrasSemana11++;
            }

            if(!holder.ediColum10.getText().toString().trim().isEmpty()){
                colum10ValuesSum=colum10ValuesSum+Integer.parseInt(holder.ediColum10.getText().toString());
                totalMuestrasSemana10++;
            }

            if(!holder.ediColum9.getText().toString().trim().isEmpty()){
                colum9ValuesSum=colum9ValuesSum+Float.parseFloat(holder.ediColum9.getText().toString());
                totalMuestrasSemana9++;
              //  totalMuestrasSemana9m++;

            }




            //  falta   calibracion y porcentajes...


        }





        txtTotalSem14.setText( String.valueOf((int)colum14ValuesSum));
        txtTotalSem13.setText( String.valueOf((int)colum13ValuesSum));
        txtTotalSem12.setText( String.valueOf((int)colum12ValuesSum));
        txtTotalSem11.setText( String.valueOf((int)colum11ValuesSum));
        txtTotalSem10.setText( String.valueOf((int)colum10ValuesSum));
        txtTotalSem9.setText( String.valueOf((int)colum9ValuesSum));
        Log.i("minmuestra","el muestra ccc es "+(int)colum9ValuesSum);


        txtTotalMuestrsSem9.setText(String.valueOf(totalMuestrasSemana9));
        txtTotalMuestrsSem10.setText(String.valueOf(totalMuestrasSemana10));
        txtTotalMuestrsSem11.setText(String.valueOf(totalMuestrasSemana11));
        txtTotalMuestrsSem12.setText(String.valueOf(totalMuestrasSemana12));
        txtTotalMuestrsSem13.setText(String.valueOf(totalMuestrasSemana13));
        txtTotalMuestrsSem14.setText(String.valueOf(totalMuestrasSemana14));


        /***calibraciones total cada semana /numero de muestras**/


        DecimalFormat df = new DecimalFormat("#.##");


        txtCalSem9.setText(df.format(  colum9ValuesSum /totalMuestrasSemana9));
        txtCalSem10.setText(df.format(colum10ValuesSum  /totalMuestrasSemana10));
        txtCalSem11.setText(df.format(colum11ValuesSum  /totalMuestrasSemana11));
        txtCalSem12.setText(df.format(colum12ValuesSum  /totalMuestrasSemana12));
        txtCalSem13.setText(df.format(colum13ValuesSum  /totalMuestrasSemana13));
        txtCalSem14.setText(df.format(colum14ValuesSum  /totalMuestrasSemana14));

         /****/


         int allMuestras=totalMuestrasSemana9+totalMuestrasSemana10
                 +totalMuestrasSemana11+totalMuestrasSemana12+totalMuestrasSemana13+totalMuestrasSemana14;

        Log.i("minmuestra","el muestra ccc es "+allMuestras);

        if(allMuestras==0){
            return;

        }

        txtPercetSem14.setText(df.format((totalMuestrasSemana14* 100)/allMuestras));
        txtPercetSem13.setText(df.format((totalMuestrasSemana13* 100)/allMuestras));
        txtPercetSem12.setText(df.format((totalMuestrasSemana12* 100)/allMuestras));
        txtPercetSem11.setText(df.format((totalMuestrasSemana11* 100)/allMuestras));
        txtPercetSem10.setText(df.format((totalMuestrasSemana10* 100)/allMuestras));
        txtPercetSem9.setText(df.format((totalMuestrasSemana9* 100)/allMuestras));


    }


    @Override
    public void uploadNewForm() {

        btnSaveCambios.setEnabled(false);


        if(!currentKeySharePrefrences.equals("")){

            try {
                Map<String,  InformRegister> mapAllReportsRegister = SharePref.getMapAllReportsRegister(SharePref.KEY_ALL_REPORTS_OFLINE_REGISTER);

                InformRegister objec= mapAllReportsRegister.get(currentKeySharePrefrences);



                Log.i("dineroa","el currentKeySharePrefrences es : "+currentKeySharePrefrences);

                assert objec != null;
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
}




