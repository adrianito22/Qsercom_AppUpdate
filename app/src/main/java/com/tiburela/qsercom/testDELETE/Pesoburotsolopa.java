package com.tiburela.qsercom.testDELETE;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.adapters.RecyclerVAdapterColorCintSem;
import com.tiburela.qsercom.adapters.RecyclerViewAdapter;
import com.tiburela.qsercom.models.ColorCintasSemns;
import com.tiburela.qsercom.utils.Variables;

import java.util.ArrayList;
import java.util.HashMap;

public class Pesoburotsolopa extends AppCompatActivity {

    RecyclerView mireciclerv;
    ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletexx);

        mireciclerv=findViewById(R.id.mireciclerv);

        ColorCintasSemnsArrayList=new ArrayList<>();

        for(int indice=0; indice<30; indice++){

            ColorCintasSemns object= new ColorCintasSemns(indice+1,0,0,0,0,0,0);

            ColorCintasSemnsArrayList.add(object);

        }

        setRECICLERdata(ColorCintasSemnsArrayList);
        createMap();

    }

    private void setRECICLERdata(ArrayList<ColorCintasSemns> ColorCintasSemnsArrayList ) {

        RecyclerVAdapterColorCintSem adapter=new RecyclerVAdapterColorCintSem(ColorCintasSemnsArrayList,this,Pesoburotsolopa.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Pesoburotsolopa.this);

        mireciclerv.setLayoutManager(layoutManager);

        mireciclerv.setAdapter(adapter);


    }


    ///le agregamos un tag de un id...



    private void createMap(){
        Variables.mapColorCintasSemanas=new HashMap<>();

        for(int indice=0; indice<ColorCintasSemnsArrayList.size(); indice++){

            ColorCintasSemns object= ColorCintasSemnsArrayList.get(indice);
            String key=ColorCintasSemnsArrayList.get(indice).getUniqueId();
            Variables.mapColorCintasSemanas.put(key,object);


        }


    }

}