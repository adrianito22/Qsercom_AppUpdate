package com.tiburela.qsercom.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.adapters.RecyclerVAdapterColorCintSem;
import com.tiburela.qsercom.adapters.RecyclerViewAdapterColorCintSem;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.models.CuadroMuestreo;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CuadMuestreoCalibAndRechaz extends AppCompatActivity {

    RecyclerView mireciclerv;
    ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList;
    Button btnSaveCambios;
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

    TextView txtTotalRechazados;

    ImageView imgVupdate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletexx);

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


        imgVupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                CuadroMuestreo objec= new CuadroMuestreo(0,"","",""
                        ,"", "","","",
                        "", ""
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
                            ediExteRodillo.getText().toString(),ediExtGancho.getText().toString());


                    ///editamos los otros datos de la cantidad de rechzados..
                    CuadroMuestreo objectWhitMoreData=addRechazadosData(objec);


                    RealtimeDB.addNewCuadroMuestreoObject(objectWhitMoreData); //subimos un cuadro de muestreo object

                    RealtimeDB.addNewCuadroMuestreoHasMap(Variables.mapColorCintasSemanas,keyDondeEstaraHashmap); //subimos el mapa ,le pasamos el mapa como cparaametro y el key donde estara


                    Toast.makeText(CuadMuestreoCalibAndRechaz.this, "Se Guardo Informe", Toast.LENGTH_SHORT).show();
                     Log.i("saber"," se subio la data ");


                }


            }
        });



        mireciclerv=findViewById(R.id.mireciclerv);

        ColorCintasSemnsArrayList=new ArrayList<>();

        for(int indice=0; indice<31; indice++){



            ColorCintasSemns    object= new ColorCintasSemns(0,0,0,0,0,0,0);

            ColorCintasSemnsArrayList.add(object);



        }

        setRECICLERdata(ColorCintasSemnsArrayList);
        createMapInitial();

    }

    private void setRECICLERdata(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList ) {

        Log.i("debugeoxc","call here set recicler ");


        RecyclerVAdapterColorCintSem adapter=new RecyclerVAdapterColorCintSem(ColorCintasSemnsArrayList,this,CuadMuestreoCalibAndRechaz.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CuadMuestreoCalibAndRechaz.this);

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


}