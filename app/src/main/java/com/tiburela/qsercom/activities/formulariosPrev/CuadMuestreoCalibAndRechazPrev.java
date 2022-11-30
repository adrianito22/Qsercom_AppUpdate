package com.tiburela.qsercom.activities.formulariosPrev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.adapters.RecyclerVAdapterColorCintSem;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.dialog_fragment.DialogConfirmChanges;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CuadMuestreoCalibAndRechazPrev extends AppCompatActivity  {

    RecyclerView mireciclerv;
    ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList;
    Button btnSaveCambios;

    TextView txtTotalRechazados;
    ImageView imgVupdate;

    TextView hindeviewTxt;



    //textimputeditexts

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_cuadro_muestreo_recha);


        hindeviewTxt=findViewById(R.id.hindeviewTxt);

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
        mireciclerv=findViewById(R.id.mireciclerv);
        txtTotalRechazados=findViewById(R.id.txtTotalRechazados);
        imgVupdate=findViewById(R.id.imgVupdate);

        imgVupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CuadroMuestreo objec= new CuadroMuestreo(0,"","",""
                        ,"", "","","",
                        "", ""
                );


                ///editamos los otos datos de la cantidad de rechzados..
                objec=addRechazadosData(objec);  //A ESTE OBJETO LE AGRGAMOS MAS DATA


                String totalRechazados=String.valueOf(obtenTotaLrechazados(objec));
                txtTotalRechazados.setText(totalRechazados);




            }
        });

        btnSaveCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(chekeadDataListIsReady()){


                    iterateItemsOfReciclerViewAndAddDataToMap(mireciclerv);



                    openBottomSheetConfirmCreateNew(Variables.FormMuestreoRechaz);


                }


            }
        });



        RealtimeDB.initDatabasesRootOnly();
       //// getAndDowloadHasmapAndCALLSetReciclerV(Variables.currentcuadroMuestreo.getNodoKyDondeEstaHasmap());



        if(Variables.currentcuadroMuestreo !=null){  //set data inviews....
            Log.i("hsmpadat","el key deonde estar hasmapes "+Variables.currentcuadroMuestreo.getNodoKyDondeEstaHasmap());
            getAndDowloadHasmapAndCALLSetReciclerV(Variables.currentcuadroMuestreo.getNodoKyDondeEstaHasmap()); //ESTE ES TEST
            setDataInViews(Variables.currentcuadroMuestreo);

            Log.i("hsmpadat","el key deonde estar hasmapes "+Variables.currentcuadroMuestreo);


        }else{ ///es nulodescragamos reporte....


            if(Variables.CurrenReportPart1!=null){

                dowloadEspecificReportAndSetData(Variables.CurrenReportPart1.getAtachControCuadroMuestreo());

            }else{


                return;
            }



        }




    }

    private void openBottomSheetConfirmCreateNew(int tipoFormulario){
        DialogConfirmChanges addPhotoBottomDialogFragment = DialogConfirmChanges.newInstance(tipoFormulario);
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(), DialogConfirmChanges.TAG);
    }


    private void setRECICLERdata(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList ) {




        Log.i("debugeoxc","call here set recicler ");


        RecyclerVAdapterColorCintSem adapter=new RecyclerVAdapterColorCintSem( generateListOrdenada(ColorCintasSemnsArrayList),this, CuadMuestreoCalibAndRechazPrev.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CuadMuestreoCalibAndRechazPrev.this);

        mireciclerv.setLayoutManager(layoutManager);

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








    private boolean chekeadDataListIsReady(){

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



        return true;
    }



    private void setDataInViews(CuadroMuestreo cuadroMuestreo){
        //agregamos la data dde cuadro de muestro..

 //aqui ya debemos tener un mpaa  mejor seria usar el mapa global que tenemos en la clase variables

         ediSemanaxc.setText(String.valueOf(cuadroMuestreo.getSemanaNum()));
         ediVaporx.setText(cuadroMuestreo.getVapor());
         ediFechax.setText(cuadroMuestreo.getSimpleDateFormat());
         ediProductoras.setText(cuadroMuestreo.getProductor());
         ediCodigoxs.setText(cuadroMuestreo.getCodigo());
         ediEnfundex.setText(cuadroMuestreo.getEnfunde());



        ediExtCalidad.setText(cuadroMuestreo.getExtensionistaCalidad());
        ediExteRodillo.setText(cuadroMuestreo.getExtensionistaEnRodillo());
        ediExtGancho.setText(cuadroMuestreo.getExtensionistaEnGancho());


         //con racimos rechazados



        ediMutante.setText(String.valueOf(cuadroMuestreo.getMutantes()));
        ediSPEKLING.setText(String.valueOf(cuadroMuestreo.getSpekling()));
        ediPuntaamarillayB.setText(String.valueOf(cuadroMuestreo.getPtaAmarillaYb()));
        ediCremaAlmendraFloja.setText(String.valueOf(cuadroMuestreo.getCremaAlmendraFloja()));
        ediManchaRoja.setText(String.valueOf(cuadroMuestreo.getManchaRoja()));
        ediAlterados.setText(String.valueOf(cuadroMuestreo.getAlterados()));
        ediPobres.setText(String.valueOf(cuadroMuestreo.getPobres()));
        ediCaidos.setText(String.valueOf(cuadroMuestreo.getCaidos()));
        ediSobreGrado.setText(String.valueOf(cuadroMuestreo.getSobreGrado()));
        ediBajoGrado.setText(String.valueOf(cuadroMuestreo.getBajoGrado()));
        edimosaico.setText(String.valueOf(cuadroMuestreo.getMosaico()));
        ediDanoDeAnimal.setText(String.valueOf(cuadroMuestreo.getDanoAnimal()));
        ediExplosivo.setText(String.valueOf(cuadroMuestreo.getExplosivo()));
        ediErwinea.setText(String.valueOf(cuadroMuestreo.getErwinea()));
        ediDedoCorto.setText(String.valueOf(cuadroMuestreo.getDedoCorto()));
        ediRacimosPesadosDeEdad.setText(String.valueOf(cuadroMuestreo.getRacimosPasadosEdad()));
        ediCochinillaEscamaFumagina.setText(String.valueOf(cuadroMuestreo.getCochinillaEscamaFunagina()));
        ediRacimosSinEdintificacion.setText(String.valueOf(cuadroMuestreo.getRacimosSinEdintificacion()));


    }


    private void setDataInViewMapData(HashMap<String, ColorCintasSemns> mapColorCintasSemanas ){


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





    private void getAndDowloadHasmapAndCALLSetReciclerV(String nodeWhereMapLocation){
        Log.i("hameha","el NODEKey es : "+nodeWhereMapLocation);

        ValueEventListener seenListener;


        seenListener = RealtimeDB.rootDatabaseReference.child("Informes").child("CuadroMuestreoMaps").child(nodeWhereMapLocation).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Variables.mapColorCintasSemanas=new HashMap<>();


                for (DataSnapshot dss : dataSnapshot.getChildren()) {
                    String key = dss.getKey();

                    ColorCintasSemns  currentObecjt =dss.getValue(ColorCintasSemns.class);

                    //   HashMap packinKey = dss.getValue( String.class);

                    //   Log.i("misadhd","el size del mapa es "+ packingListMap.size());
                    Log.i("hameha","el key es "+key);


                    if (currentObecjt!=null) {///

                        Variables.mapColorCintasSemanas.put(key,currentObecjt);

                    }


                }



                setDataInViewMapData(Variables.mapColorCintasSemanas);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("misadhd","el error es "+ databaseError.getMessage());



            }
        });




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


    public void   saveInfo() {
        //creamos un objeto
        RealtimeDB.initDatabasesRootOnly();
      //  String keyDondeEstaraHashmap=RealtimeDB.rootDatabaseReference.push().getKey();

        CuadroMuestreo objec= new CuadroMuestreo(Integer.parseInt(ediSemanaxc.getText().toString()),ediExportadora.getText().toString(),
                ediVaporx.getText().toString(), ediProductoras.getText().toString(),ediCodigoxs.getText().toString(),
                ediEnfundex.getText().toString(), Variables.currentcuadroMuestreo.getNodoKyDondeEstaHasmap(),
                ediExtCalidad.getText().toString(), ediExteRodillo.getText().toString(),ediExtGancho.getText().toString());

           //LE AGERGAMOS EL MISMO UNIQUE ID
       /// objec.setUniqueIdObject(Variables.currentcuadroMuestreo.getUniqueIdObject());
        ///LE AGREGAMOS OTROS DATOS A ESTE OBJETO
        addRechazadosData(objec);



        int totalRechazados=obtenTotaLrechazados(objec);
        Log.i("eldaterr","el total rechzados es "+totalRechazados);
        objec.setTotalRechazadosAll(totalRechazados);



        //obtenmos el total ... de erechzdos..






        /// objecsetSimpleDateFormat();
         RealtimeDB.updateCuadroMuestreoObject(objec,Variables.currentcuadroMuestreo); //subimos un cuadro de muestreo object

        // RealtimeDB.addNewCuadroMuestreoObject(objec); //subimos un cuadro de muestreo object
        RealtimeDB.addNewCuadroMuestreoHasMap(Variables.mapColorCintasSemanas,Variables.currentcuadroMuestreo.getNodoKyDondeEstaHasmap()); //subimos el mapa ,le pasamos el mapa como cparaametro y el key donde estara

        Toast.makeText(CuadMuestreoCalibAndRechazPrev.this, "Se Actualizo Informe", Toast.LENGTH_SHORT).show();
        // Log.i(

        finish();



    }

    //crea un mapa con las posiciones en true
    //el mapa debe tener 10 valores
    //cada valor debe tener un key el imgview id....
    //




    private void dowloadEspecificReportAndSetData(String uniqeuIDiNFORME){

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference usersdRef = rootRef.child("Informes").child("CuadrosMuestreo");

        Query query = usersdRef.orderByChild("uniqueIdObject").equalTo(uniqeuIDiNFORME);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {

                    CuadroMuestreo informe=ds.getValue(CuadroMuestreo.class);

                    if(informe!=null){
                        Variables.currentcuadroMuestreo=informe;
                        //   Log.i("hsmpadat","es nulo");

                        Log.i("hsmpadat","el value es "+ Variables.currentcuadroMuestreo.getProductor());

                        break;

                    }


                }


                getAndDowloadHasmapAndCALLSetReciclerV(Variables.currentcuadroMuestreo.getNodoKyDondeEstaHasmap()); //ESTE ES TEST
                setDataInViews(Variables.currentcuadroMuestreo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("misdata","el error es  "+ error.getMessage());


            }
        } );




    }



    private  ArrayList<ColorCintasSemns> generateListOrdenada(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList){

        ArrayList<ColorCintasSemns> sortedUsers = (ArrayList<ColorCintasSemns>) ColorCintasSemnsArrayList.stream()
                .sorted(Comparator.comparing(ColorCintasSemns::getSemanNum))
                .collect(Collectors.toList());


        return sortedUsers;

    }



    public  void iterateItemsOfReciclerViewAndAddDataToMap(RecyclerView mirecicler){

        int valueNUM14;
        int valueNUM13;
        int valueNUM12;
        int valueNUM11;
        int valueNUM10;
        int valueNUM9;





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

            if(! holder.ediColum14.getText().toString().trim().isEmpty()){
                valueNUM14=Integer.parseInt(holder.ediColum14.getText().toString());

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


            Variables. mapColorCintasSemanas.put(uniqueIdObjec,objec);



            //aqui agudamos..... hasmap

            //GAURDA,PS

        }




    }








}









