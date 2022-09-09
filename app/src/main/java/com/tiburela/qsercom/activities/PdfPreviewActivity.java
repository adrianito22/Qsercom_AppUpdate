package com.tiburela.qsercom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.ImagenX;

import java.util.HashMap;

public class PdfPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_preview);

        HashMap<String, ImagenX> hash_map = new HashMap<String, ImagenX>();
          //parace que el primer datos es la clave y el segundo es valor

/*
        // Mapping string values to int keys
        hash_map.put(10, "Geeks");
        hash_map.put(15, "4");
        hash_map.put(20, "Geeks");
        hash_map.put(25, "keydelete");
        hash_map.put(30, "You");

        Log.i("mimapax","el size antes de remover es  "+hash_map.size());

        hash_map.remove(20);\

        Log.i("mimapax","el size despues de remover es  es "+hash_map.size());
*/

    }
}