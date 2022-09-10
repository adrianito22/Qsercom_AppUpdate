package com.tiburela.qsercom.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tiburela.qsercom.R;

public class ActivityMenu extends AppCompatActivity {
ImageView imgContenedores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgContenedores=findViewById(R.id.imgContenedores);
         imgContenedores.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View view) {

                 startActivity(new Intent(ActivityMenu.this,FormularioActivity.class ));


             }
         });
    }
}