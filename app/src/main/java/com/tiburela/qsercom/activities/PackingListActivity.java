package com.tiburela.qsercom.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.PackingModel;

import java.util.HashMap;

public class PackingListActivity extends AppCompatActivity {

    private TextInputEditText mEdinumbox1;
    private TextInputEditText mEdiDescripcion1;
    private TextInputEditText mEdinumbox3;
    private TextInputEditText mEdiDescripcion3;
    private TextInputEditText mEdinumbox5;
    private TextInputEditText mEdiDescripcion5;
    private TextInputEditText mEdinumbox7;
    private TextInputEditText mEdiDescripcion7;
    private TextInputEditText mEdinumbox9;
    private TextInputEditText mEdiDescripcion9;
    private TextInputEditText mEdinumbox11;
    private TextInputEditText mEdiDescripcion11;
    private TextInputEditText mEdinumbox13;
    private TextInputEditText mEdiDescripcion13;
    private TextInputEditText mEdinumbox15;
    private TextInputEditText mEdiDescripcion15;
    private TextInputEditText mEdinumbox17;
    private TextInputEditText mEdiDescripcion17;
    private TextInputEditText mEdinumbox19;
    private TextInputEditText mEdiDescripcion18;
    private TextInputEditText mEdinumbox2;
    private TextInputEditText mEdiDescripcion2;
    private TextInputEditText mEdinumbox4;
    private TextInputEditText mEdiDescripcion4;
    private TextInputEditText mEdinumbox6;
    private TextInputEditText mEdiDescripcion6;
    private TextInputEditText mEdinumbox8;
    private TextInputEditText mEdiDescripcion8;
    private TextInputEditText mEdinumbox10;
    private TextInputEditText mEdiDescripcion10;
    private TextInputEditText mEdinumbox12;
    private TextInputEditText mEdiDescripcion12;
    private TextInputEditText mEdinumbox14;
    private TextInputEditText mEdiDescripcion14;
    private TextInputEditText mEdinumbox16;
    private TextInputEditText mEdiDescripcion16;
    private TextInputEditText mEdinumbox18;
    private TextInputEditText mEdinumbox20;
    private TextInputEditText mEdiDescripcion20;
    private TextInputEditText mEdiProductor1;
    private TextInputEditText mEdiProductor2;
    private TextInputEditText mEdiProductor3;

    private TextInputEditText mEdiProductor5;
    private TextInputEditText mEdiProductor6;
    private TextInputEditText mEdiProductor7;
    private TextInputEditText mEdiProductor8;
    private TextInputEditText mEdiProductor9;
    private TextInputEditText mEdiProductor10;

    private TextInputEditText mEdiCajas1;
    private TextInputEditText mEdiCajas2;
    private TextInputEditText mEdiCajas3;
    private TextInputEditText mEdiCajas4;
    private TextInputEditText mEdiCajas5;
    private TextInputEditText mEdiCajas6;
    private TextInputEditText mEdiCajas7;
    private TextInputEditText mEdiCajas8;
    private TextInputEditText mEdiCajas9;
    private TextInputEditText mEdiCajas10;

    private TextInputEditText mEdiCodigoN1;
    private TextInputEditText mEdiCodigoN3;
    private TextInputEditText mEdiCodigoN5;
    private TextInputEditText mEdiCodigoN6;
    private TextInputEditText mEdiCodigoN7;
    private TextInputEditText mEdiCodigoN8;
    private TextInputEditText mEdiCodigoN9;
    private TextInputEditText mEdiCodigoN10;


    private TextInputEditText mEdiTotalCajas;
    private TextInputEditText mEdiContenedorxzz;
    private TextInputEditText mEdiFechaHere;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packing_list2);

     findviewsIDS();


    }







   private void findviewsIDS(){
       mEdiDescripcion1 = findViewById(R.id.ediDescripcion1);
       mEdiDescripcion3 = findViewById(R.id.ediDescripcion3);
       mEdiDescripcion5 = findViewById(R.id.ediDescripcion5);
       mEdiDescripcion7 = findViewById(R.id.ediDescripcion7);
       mEdiDescripcion9 = findViewById(R.id.ediDescripcion9);

       mEdinumbox9 = findViewById(R.id.edinumbox9);
       mEdinumbox7 = findViewById(R.id.edinumbox7);
       mEdinumbox5 = findViewById(R.id.edinumbox5);
       mEdinumbox3 = findViewById(R.id.edinumbox3);
       mEdinumbox1 = findViewById(R.id.edinumbox1);
       mEdinumbox11 = findViewById(R.id.edinumbox11);
       mEdinumbox13 = findViewById(R.id.edinumbox13);
       mEdinumbox15 = findViewById(R.id.edinumbox15);
       mEdinumbox17 = findViewById(R.id.edinumbox17);
       mEdinumbox19 = findViewById(R.id.edinumbox19);
       mEdinumbox10 = findViewById(R.id.edinumbox10);
       mEdinumbox12 = findViewById(R.id.edinumbox12);
       mEdinumbox14 = findViewById(R.id.edinumbox14);
       mEdinumbox16 = findViewById(R.id.edinumbox16);
       mEdinumbox18 = findViewById(R.id.edinumbox18);
       mEdinumbox2 = findViewById(R.id.edinumbox2);
       mEdinumbox4 = findViewById(R.id.edinumbox4);
       mEdinumbox6 = findViewById(R.id.edinumbox6);
       mEdinumbox8 = findViewById(R.id.edinumbox8);
       mEdinumbox20 = findViewById(R.id.edinumbox20);



       mEdiDescripcion11 = findViewById(R.id.ediDescripcion11);
       mEdiDescripcion13 = findViewById(R.id.ediDescripcion13);
       mEdiDescripcion15 = findViewById(R.id.ediDescripcion15);
       mEdiDescripcion17 = findViewById(R.id.ediDescripcion17);
       mEdiDescripcion18 = findViewById(R.id.ediDescripcion18);
       mEdiDescripcion2 = findViewById(R.id.ediDescripcion2);
       mEdiDescripcion4 = findViewById(R.id.ediDescripcion4);
       mEdiDescripcion6 = findViewById(R.id.ediDescripcion6);
       mEdiDescripcion8 = findViewById(R.id.ediDescripcion8);
       mEdiDescripcion10 = findViewById(R.id.ediDescripcion10);
       mEdiDescripcion12 = findViewById(R.id.ediDescripcion12);
       mEdiDescripcion14 = findViewById(R.id.ediDescripcion14);
       mEdiDescripcion16 = findViewById(R.id.ediDescripcion16);
       mEdiDescripcion20 = findViewById(R.id.ediDescripcion20);

       mEdiProductor1 = findViewById(R.id.ediProductor1);
       mEdiProductor2 = findViewById(R.id.ediProductor2);
       mEdiProductor3 = findViewById(R.id.ediProductor3);
       mEdiProductor5 = findViewById(R.id.ediProductor5);
       mEdiProductor6 = findViewById(R.id.ediProductor6);
       mEdiProductor7 = findViewById(R.id.ediProductor7);
       mEdiProductor8 = findViewById(R.id.ediProductor8);
       mEdiProductor9 = findViewById(R.id.ediProductor9);
       mEdiProductor10 = findViewById(R.id.ediProductor10);



       mEdiCajas1 = findViewById(R.id.ediCajas1);
       mEdiCajas2 = findViewById(R.id.ediCajas2);
       mEdiCajas3 = findViewById(R.id.ediCajas3);
       mEdiCajas4 = findViewById(R.id.ediCajas4);
       mEdiCajas5 = findViewById(R.id.ediCajas5);
       mEdiCajas6 = findViewById(R.id.ediCajas6);
       mEdiCajas7 = findViewById(R.id.ediCajas7);
       mEdiCajas8 = findViewById(R.id.ediCajas8);
       mEdiCajas9 = findViewById(R.id.ediCajas9);
       mEdiCajas10 = findViewById(R.id.ediCajas10);

       mEdiCodigoN9=findViewById(R.id.ediCodigoN9);
       mEdiCodigoN8 = findViewById(R.id.ediCodigoN8);
       mEdiCodigoN1 = findViewById(R.id.ediCodigIGoN1);
       mEdiCodigoN3 = findViewById(R.id.ediCodigoN3);
       mEdiCodigoN5 = findViewById(R.id.ediCodigoN5);
       mEdiCodigoN6 = findViewById(R.id.ediCodigoN6);
       mEdiCodigoN7 = findViewById(R.id.ediCodigoN7);
       mEdiCodigoN10 = findViewById(R.id.ediCodigoN10);
       mEdiTotalCajas = findViewById(R.id.ediTotalCajas);
       mEdiContenedorxzz = findViewById(R.id.ediContenedorxzz);
       mEdiFechaHere = findViewById(R.id.ediFechaHere);


   }

    ///vasmos a crear un mapa usando un tag o el id.....


    private void Addpackings(String uniqueIDinforme) { //si el views esta lleno usamos ese valor del id ...

        TextInputEditText[] miArrayTexImputEditexts = createArrayOfEditext();

        HashMap<String, PackingModel> miMapaPacking = new HashMap<>();

        for (int i = 0; i < miArrayTexImputEditexts.length / 2; i++) { //solo recorremos la mitad


            if (!miArrayTexImputEditexts[i].getText().toString().isEmpty() && !miArrayTexImputEditexts[i + 10].getText().toString().isEmpty()) { //si el par no estan vacios

                String key = String.valueOf(miArrayTexImputEditexts[i].getId());
                String keyOfValorEditext = String.valueOf(miArrayTexImputEditexts[i + 10].getId());

                miMapaPacking.put(key, new PackingModel(miArrayTexImputEditexts[i].getText().toString(), miArrayTexImputEditexts[i + 10].getText().toString(), uniqueIDinforme, keyOfValorEditext));

            }

        }


        //AHORA SUBIMOS ESTA MAPA..SI SE NOS COMPLICA SUBIR EL MAPA CON VARIOS VALORES,  SOLO SUBIMOS UN VALOR DEL MAPA UNO POR UNO,,.


    }


    private TextInputEditText[] createArrayOfEditext() { //si el views esta lleno usamos ese valor del id ...

        TextInputEditText[] miArray = {


        };

        //entonces creamos un array y despues lo recorremos ,suando un for


        return miArray;
    }




    /***
     *
     *
     * CHEKEAY CREA MAPA PACKING LIST...
     * ARRAY [0] CONTIENE DATA Y ARRAY2[0]... NO CONTIENE.. O S ARRAY 1 NO CONTIENE DATA Y EL OTRO SI.....
     *
     * IF
     *
     * if*(
     *
     * le decimos false return,,,
     *
     * y terminamos el bucle....
     *
     * ...y solo creamos un mapa si ambos contienen data...
     *
     *
     *
     * RECORREMOS UN FOR....
     *
     * entyoncese seran 3 forsss.....
     * varios array con su par clave valor....
     *
     *
     * CREMAOS UN MAPA GLOBAL QUE IDGA SUPER PACKING LIST...*/







}