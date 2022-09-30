package com.tiburela.qsercom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.EstateFieldView;
import com.tiburela.qsercom.models.ImagenReport;
import com.tiburela.qsercom.models.PackingModel;

import java.util.HashMap;

public class PackingListActivity extends AppCompatActivity {

    TextInputEditText ediname1;
    TextInputEditText ediname2;
    TextInputEditText ediname3;
    TextInputEditText ediname4;
    TextInputEditText ediname5;
    TextInputEditText ediname6;
    TextInputEditText ediname7;
    TextInputEditText ediname8;
    TextInputEditText ediname9;
    TextInputEditText ediname10;

    TextInputEditText ediValor1;
    TextInputEditText ediValor2;
    TextInputEditText ediValor3;
    TextInputEditText ediValor4;
    TextInputEditText ediValor5;
    TextInputEditText ediValor6;
    TextInputEditText ediValor7;
    TextInputEditText ediValor8;
    TextInputEditText ediValor9;
    TextInputEditText ediValor10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_list2);
    }



    void findViewsIds( ) {

        ediname1  =findViewById(R.id.ediname1 );
                ediname2 =findViewById(R.id.ediname2 );
        ediname3 =findViewById(R.id.ediname3 );
                ediname4 =findViewById(R.id.ediname4 );
        ediname5 =findViewById(R.id.ediname5 );
                ediname6 =findViewById(R.id.ediname6 );
        ediname7 =findViewById(R.id.ediname7 );
                ediname8 =findViewById(R.id.ediname8 );
        ediname9 =findViewById(R.id.ediname9 );
                ediname10 =findViewById(R.id.ediname10 );

        ediValor1 =findViewById(R.id.ediValor1 );
                ediValor2 =findViewById(R.id.ediValor2 );
        ediValor3 =findViewById(R.id.ediValor3 );
                ediValor4 =findViewById(R.id.ediValor4 );
        ediValor5 =findViewById(R.id.ediValor5 );
                ediValor6 =findViewById(R.id.ediValor6 );
        ediValor7 =findViewById(R.id.ediValor7 );
                ediValor8 =findViewById(R.id.ediValor8 );
        ediValor9 =findViewById(R.id.ediValor9 );
                ediValor10 =findViewById(R.id.ediValor10 );

    }

    ///vasmos a crear un mapa usando un tag o el id.....



    private void Addpackings(String uniqueIDinforme) { //si el views esta lleno usamos ese valor del id ...

        TextInputEditText   [] miArrayTexImputEditexts=createArrayOfEditext();

        HashMap<String, PackingModel>miMapaPacking=new HashMap<>();

        for(int i = 0; i< miArrayTexImputEditexts.length/2; i++) { //solo recorremos la mitad


            if(!miArrayTexImputEditexts[i].getText().toString().isEmpty()  &&  !miArrayTexImputEditexts[i+10].getText().toString().isEmpty()) { //si el par no estan vacios

                String key=String.valueOf(miArrayTexImputEditexts[i].getId());
                String keyOfValorEditext=String.valueOf(miArrayTexImputEditexts[i+10].getId());

                miMapaPacking.put(key, new PackingModel(miArrayTexImputEditexts [i].getText().toString(),miArrayTexImputEditexts [i+10].getText().toString(),uniqueIDinforme,keyOfValorEditext ));

            }

        }



        //AHORA SUBIMOS ESTA MAPA..SI SE NOS COMPLICA SUBIR EL MAPA CON VARIOS VALORES,  SOLO SUBIMOS UN VALOR DEL MAPA UNO POR UNO,,.


    }




    private TextInputEditText  [] createArrayOfEditext() { //si el views esta lleno usamos ese valor del id ...

       TextInputEditText   [] miArray = {

               ediname1, //0
               ediname2,
               ediname3,
               ediname4,
               ediname5,
               ediname6,
               ediname7,
               ediname8,
               ediname9,
               ediname10,

               ediValor1,
               ediValor2,
               ediValor3,
               ediValor4,
               ediValor5,
               ediValor6,
               ediValor7,
               ediValor8,
               ediValor9,
               ediValor10,


       } ;

        //entonces creamos un array y despues lo recorremos ,suando un for



        return miArray;
    }
}