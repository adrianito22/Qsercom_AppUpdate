package com.tiburela.qsercom.activities;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.tiburela.qsercom.R;

import java.util.HashMap;

public class TestFindViewsId extends AppCompatActivity {

    private EditText pbCluster01;
    private EditText pbCluster05;
    private EditText pbCluster03;
    private EditText pbCluster02;
    private EditText pbCluster04;
    private EditText pbCluster010;
    private EditText pbCluster09;
    private EditText pbCluster07;
    private EditText pbCluster08;
    private EditText pbCluster06;
    private EditText pbCluster011;
    private EditText pbCluster015;
    private EditText pbCluster012;
    private EditText pbCluster013;
    private EditText pbCluster014;
    private EditText pbCluster016;
    private EditText pbCluster019;
    private EditText pbCluster018;
    private EditText pbCluster020;
    private EditText pbCluster017;
    private EditText pbCluster025;
    private EditText pbCluster024;
    private EditText pbCluster023;
    private EditText pbCluster022;
    private EditText pbCluster021;
    private EditText pbCluster028;
    private EditText pbCluster027;
    private EditText pbCluster029;
    private EditText pbCluster026;
    private EditText pbCluster030;
    private EditText pbCluster034;
    private EditText pbCluster031;
    private EditText pbCluster035;
    private EditText pbCluster033;
    private EditText pbCluster032;
    private EditText pbCluster039;
    private EditText pbCluster040;
    private EditText pbCluster037;
    private EditText pbCluster038;
    private EditText pbCluster036;
    private EditText pbCluster043;
    private EditText pbCluster045;
    private EditText pbCluster042;
    private EditText pbCluster041;
    private EditText pbCluster044;
    private EditText pbCluster048;
    private EditText pbCluster046;
    private EditText pbCluster050;
    private EditText pbCluster047;
    private EditText pbCluster049;
    private EditText p2pbCluster01;
    private EditText p2pbCluster05;
    private EditText p2pbCluster03;
    private EditText p2pbCluster02;
    private EditText p2pbCluster04;
    private EditText p2pbCluster010;
    private EditText p2pbCluster09;
    private EditText p2pbCluster07;
    private EditText p2pbCluster08;
    private EditText p2pbCluster06;
    private EditText p2pbCluster011;
    private EditText p2pbCluster015;
    private EditText p2pbCluster012;
    private EditText p2pbCluster013;
    private EditText p2pbCluster014;
    private EditText p2pbCluster016;
    private EditText p2pbCluster019;
    private EditText p2pbCluster018;
    private EditText p2pbCluster020;
    private EditText p2pbCluster017;
    private EditText p2pbCluster025;
    private EditText p2pbCluster024;
    private EditText p2pbCluster023;
    private EditText p2pbCluster022;
    private EditText p2pbCluster021;
    private EditText p2pbCluster028;
    private EditText p2pbCluster027;
    private EditText p2pbCluster029;
    private EditText p2pbCluster026;
    private EditText p2pbCluster030;
    private EditText p2pbCluster034;
    private EditText p2pbCluster031;
    private EditText p2pbCluster035;
    private EditText p2pbCluster033;
    private EditText p2pbCluster032;
    private EditText p2pbCluster039;
    private EditText p2pbCluster040;
    private EditText p2pbCluster037;
    private EditText p2pbCluster038;
    private EditText p2pbCluster036;
    private EditText p2pbCluster043;
    private EditText p2pbCluster045;
    private EditText p2pbCluster042;
    private EditText p2pbCluster041;
    private EditText p2pbCluster044;
    private EditText p2pbCluster048;
    private EditText p2pbCluster046;
    private EditText p2pbCluster050;
    private EditText p2pbCluster047;
    private EditText p2pbCluster049;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_find_views_id);
        pbCluster01 = findViewById(R.id.pbCluster01);
        pbCluster05 = findViewById(R.id.pbCluster05);
        pbCluster03 = findViewById(R.id.pbCluster03);
        pbCluster02 = findViewById(R.id.pbCluster02);
        pbCluster04 = findViewById(R.id.pbCluster04);
        pbCluster010 = findViewById(R.id.pbCluster010);
        pbCluster09 = findViewById(R.id.pbCluster09);
        pbCluster07 = findViewById(R.id.pbCluster07);
        pbCluster08 = findViewById(R.id.pbCluster08);
        pbCluster06 = findViewById(R.id.pbCluster06);
        pbCluster011 = findViewById(R.id.pbCluster011);
        pbCluster015 = findViewById(R.id.pbCluster015);
        pbCluster012 = findViewById(R.id.pbCluster012);
        pbCluster013 = findViewById(R.id.pbCluster013);
        pbCluster014 = findViewById(R.id.pbCluster014);
        pbCluster016 = findViewById(R.id.pbCluster016);
        pbCluster019 = findViewById(R.id.pbCluster019);
        pbCluster018 = findViewById(R.id.pbCluster018);
        pbCluster020 = findViewById(R.id.pbCluster020);
        pbCluster017 = findViewById(R.id.pbCluster017);
        pbCluster025 = findViewById(R.id.pbCluster025);
        pbCluster024 = findViewById(R.id.pbCluster024);
        pbCluster023 = findViewById(R.id.pbCluster023);
        pbCluster022 = findViewById(R.id.pbCluster022);
        pbCluster021 = findViewById(R.id.pbCluster021);
        pbCluster028 = findViewById(R.id.pbCluster028);
        pbCluster027 = findViewById(R.id.pbCluster027);
        pbCluster029 = findViewById(R.id.pbCluster029);
        pbCluster026 = findViewById(R.id.pbCluster026);
        pbCluster030 = findViewById(R.id.pbCluster030);
        pbCluster034 = findViewById(R.id.pbCluster034);
        pbCluster031 = findViewById(R.id.pbCluster031);
        pbCluster035 = findViewById(R.id.pbCluster035);
        pbCluster033 = findViewById(R.id.pbCluster033);
        pbCluster032 = findViewById(R.id.pbCluster032);
        pbCluster039 = findViewById(R.id.pbCluster039);
        pbCluster040 = findViewById(R.id.pbCluster040);
        pbCluster037 = findViewById(R.id.pbCluster037);
        pbCluster038 = findViewById(R.id.pbCluster038);
        pbCluster036 = findViewById(R.id.pbCluster036);
        pbCluster043 = findViewById(R.id.pbCluster043);
        pbCluster045 = findViewById(R.id.pbCluster045);
        pbCluster042 = findViewById(R.id.pbCluster042);
        pbCluster041 = findViewById(R.id.pbCluster041);
        pbCluster044 = findViewById(R.id.pbCluster044);
        pbCluster048 = findViewById(R.id.pbCluster048);
        pbCluster046 = findViewById(R.id.pbCluster046);
        pbCluster050 = findViewById(R.id.pbCluster050);
        pbCluster047 = findViewById(R.id.pbCluster047);
        pbCluster049 = findViewById(R.id.pbCluster049);
        p2pbCluster01 = findViewById(R.id.p2pbCluster01);
        p2pbCluster05 = findViewById(R.id.p2pbCluster05);
        p2pbCluster03 = findViewById(R.id.p2pbCluster03);
        p2pbCluster02 = findViewById(R.id.p2pbCluster02);
        p2pbCluster04 = findViewById(R.id.p2pbCluster04);
        p2pbCluster010 = findViewById(R.id.p2pbCluster010);
        p2pbCluster09 = findViewById(R.id.p2pbCluster09);
        p2pbCluster07 = findViewById(R.id.p2pbCluster07);
        p2pbCluster08 = findViewById(R.id.p2pbCluster08);
        p2pbCluster06 = findViewById(R.id.p2pbCluster06);
        p2pbCluster011 = findViewById(R.id.p2pbCluster011);
        p2pbCluster015 = findViewById(R.id.p2pbCluster015);
        p2pbCluster012 = findViewById(R.id.p2pbCluster012);
        p2pbCluster013 = findViewById(R.id.p2pbCluster013);
        p2pbCluster014 = findViewById(R.id.p2pbCluster014);
        p2pbCluster016 = findViewById(R.id.p2pbCluster016);
        p2pbCluster019 = findViewById(R.id.p2pbCluster019);
        p2pbCluster018 = findViewById(R.id.p2pbCluster018);
        p2pbCluster020 = findViewById(R.id.p2pbCluster020);
        p2pbCluster017 = findViewById(R.id.p2pbCluster017);
        p2pbCluster025 = findViewById(R.id.p2pbCluster025);
        p2pbCluster024 = findViewById(R.id.p2pbCluster024);
        p2pbCluster023 = findViewById(R.id.p2pbCluster023);
        p2pbCluster022 = findViewById(R.id.p2pbCluster022);
        p2pbCluster021 = findViewById(R.id.p2pbCluster021);
        p2pbCluster028 = findViewById(R.id.p2pbCluster028);
        p2pbCluster027 = findViewById(R.id.p2pbCluster027);
        p2pbCluster029 = findViewById(R.id.p2pbCluster029);
        p2pbCluster026 = findViewById(R.id.p2pbCluster026);
        p2pbCluster030 = findViewById(R.id.p2pbCluster030);
        p2pbCluster034 = findViewById(R.id.p2pbCluster034);
        p2pbCluster031 = findViewById(R.id.p2pbCluster031);
        p2pbCluster035 = findViewById(R.id.p2pbCluster035);
        p2pbCluster033 = findViewById(R.id.p2pbCluster033);
        p2pbCluster032 = findViewById(R.id.p2pbCluster032);
        p2pbCluster039 = findViewById(R.id.p2pbCluster039);
        p2pbCluster040 = findViewById(R.id.p2pbCluster040);
        p2pbCluster037 = findViewById(R.id.p2pbCluster037);
        p2pbCluster038 = findViewById(R.id.p2pbCluster038);
        p2pbCluster036 = findViewById(R.id.p2pbCluster036);

    }



    private  void createMapLibriadoForm(){

        EditText [] miArray= {
                pbCluster01, pbCluster05, pbCluster03, pbCluster02, pbCluster04, pbCluster010, pbCluster09, pbCluster07, pbCluster08, pbCluster06, pbCluster011,
                pbCluster015, pbCluster012, pbCluster013, pbCluster014, pbCluster016, pbCluster019, pbCluster018, pbCluster020, pbCluster017, pbCluster025,
                pbCluster024 , pbCluster022, pbCluster021, pbCluster028, pbCluster027, pbCluster029, pbCluster026, pbCluster030, pbCluster034,
                pbCluster031, pbCluster035, pbCluster033, pbCluster032, pbCluster039, pbCluster040, pbCluster037, pbCluster038, pbCluster036, pbCluster043,
                pbCluster045, pbCluster042, pbCluster041, pbCluster044, pbCluster048, pbCluster046, pbCluster050, pbCluster047, pbCluster049, p2pbCluster01,
                p2pbCluster05, p2pbCluster03, p2pbCluster02, p2pbCluster04, p2pbCluster010, p2pbCluster09, p2pbCluster07, p2pbCluster08, p2pbCluster06,
                p2pbCluster011, p2pbCluster015, p2pbCluster012, p2pbCluster013, p2pbCluster014, p2pbCluster016, p2pbCluster019, p2pbCluster018,
                p2pbCluster020, p2pbCluster017, p2pbCluster025, p2pbCluster024, p2pbCluster023, p2pbCluster022, p2pbCluster021, p2pbCluster028,
                p2pbCluster027, p2pbCluster029, p2pbCluster026, p2pbCluster030, p2pbCluster034, p2pbCluster031, p2pbCluster035, p2pbCluster033,
                p2pbCluster032, p2pbCluster039, p2pbCluster040, p2pbCluster037, p2pbCluster038, p2pbCluster036

        };

        HashMap<String,String>miMapData= new HashMap<>();


        for(EditText currentEdit: miArray){

            if(! currentEdit.getText().toString().trim().isEmpty()){

                //le agregamos un slash al id key mas o menos este fomrato idddd/fil1

                miMapData.put(String.valueOf(currentEdit.getId())+"/"+currentEdit.getTag(),currentEdit.getText().toString());
            }


        }







    }



}