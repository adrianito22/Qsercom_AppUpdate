package com.tiburela.qsercom.dialog_fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.DefectsAndNumber;
import com.tiburela.qsercom.utils.Utils;

import java.util.ArrayList;

public class BottonSheetSelecDanos extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialog";
    private View vista;
    Context context;
    static String curreyKeyIdView;
    private CheckBox checkBsr;
    private EditText ediNUmDef1;
    private CheckBox checkBbr;
    private EditText ediNUmDef2;
    private CheckBox checkBls;
    private EditText ediNUmDef3;
    private CheckBox checkBts;
    private EditText ediNUmDef4;
    private CheckBox checkBog;
    private EditText ediNUmDef5;
    private CheckBox checkBug;
    private EditText ediNUmDef6;
    private CheckBox checkBab;
    private EditText ediNUmDef7;
    private CheckBox checkBlg;
    private EditText ediNUmDef8;
    private CheckBox checkBwi;
    private EditText ediNUmDef9;
    private CheckBox checkBbm;
    private EditText ediNUmDef10;
    private CheckBox checkBsm;
    private EditText ediNUmDef11;
    private CheckBox checkBct;
    private EditText ediNUmDef12;
    private CheckBox checkBfl;
    private EditText ediNUmDef13;
    private CheckBox checkBsc;
    private EditText ediNUmDef14;
    private CheckBox checkBdp;
    private EditText ediNUmDef15;
    private CheckBox checkBlf;
    private EditText ediNUmDef16;
    private CheckBox checkBbNi;
    private EditText ediNUmDef17;
    private CheckBox checkBms;
    private EditText ediNUmDef18;
    private CheckBox checkBrr;
    private CheckBox checkBsk;
    private EditText ediNUmDef19;
    private EditText ediNUmDef20;

    private ImageView imgCloseSheet;



    public static BottonSheetSelecDanos newInstance(String curreyKeyIdViewx) {

        curreyKeyIdView = curreyKeyIdViewx;


        return new BottonSheetSelecDanos();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.bottom_sheet_danos, container, false);


        findViewsid();

        setStatesChekeedAndNumber();

        return vista;


    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("ontatch", "se ejecuto onViewCreated");

        //  view.findViewById(R.id.textView4).setOnClickListener(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();


    }





/*
        public interface ItemClickListener {
            void onItemClick(boolean esNEW);
        }


*/

    @Override
    public void onStart() {
        super.onStart();


    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.i("dbuhehjr", "el user a dado en dismiss ");

        /// checkActivityDondeVinoAndCaLLmETHODofActivity(1);


        super.onDismiss(dialog);


    }

void findViewsid(){


    checkBsr = vista.findViewById(R.id.checkBsr);
    ediNUmDef1 = vista.findViewById(R.id.ediNUmDef1);
    checkBbr = vista.findViewById(R.id.checkBbr);
    ediNUmDef2 = vista.findViewById(R.id.ediNUmDef2);
    checkBls = vista.findViewById(R.id.checkBls);
    ediNUmDef3 = vista.findViewById(R.id.ediNUmDef3);
    checkBts = vista.findViewById(R.id.checkBts);
    ediNUmDef4 = vista.findViewById(R.id.ediNUmDef4);
    checkBog = vista.findViewById(R.id.checkBog);
    ediNUmDef5 =vista. findViewById(R.id.ediNUmDef5);
    checkBug = vista.findViewById(R.id.checkBug);
    ediNUmDef6 = vista.findViewById(R.id.ediNUmDef6);
    checkBab = vista.findViewById(R.id.checkBab);
    ediNUmDef7 =vista. findViewById(R.id.ediNUmDef7);
    checkBlg = vista.findViewById(R.id.checkBlg);
    ediNUmDef8 =vista. findViewById(R.id.ediNUmDef8);
    checkBwi = vista.findViewById(R.id.checkBwi);
    ediNUmDef9 =vista. findViewById(R.id.ediNUmDef9);
    checkBbm = vista.findViewById(R.id.checkBbm);
    ediNUmDef10 =vista. findViewById(R.id.ediNUmDef10);
    checkBsm = vista.findViewById(R.id.checkBsm);
    ediNUmDef11 =vista. findViewById(R.id.ediNUmDef11);
    checkBct = vista.findViewById(R.id.checkBct);
    ediNUmDef12 =vista. findViewById(R.id.ediNUmDef12);
    checkBfl = vista.findViewById(R.id.checkBfl);
    ediNUmDef13 = vista.findViewById(R.id.ediNUmDef13);
    checkBsc = vista.findViewById(R.id.checkBsc);
    ediNUmDef14 = vista.findViewById(R.id.ediNUmDef14);
    checkBdp = vista.findViewById(R.id.checkBdp);
    ediNUmDef15 =vista. findViewById(R.id.ediNUmDef15);
    checkBlf =vista. findViewById(R.id.checkBlf);
    ediNUmDef16 =vista. findViewById(R.id.ediNUmDef16);
    checkBbNi = vista.findViewById(R.id.checkBbNi);
    ediNUmDef17 =vista. findViewById(R.id.ediNUmDef17);
    checkBms = vista.findViewById(R.id.checkBmc);
    ediNUmDef18 =vista. findViewById(R.id.ediNUmDef18);
    checkBrr = vista.findViewById(R.id.checkBrr);
    checkBsk =vista. findViewById(R.id.checkBsk);
    ediNUmDef19 =vista. findViewById(R.id.ediNUmDef19);
    ediNUmDef20=vista.findViewById(R.id.ediNUmDef20);


    imgCloseSheet=vista.findViewById(R.id.imgCloseSheet);
    imgCloseSheet.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            savestateMap();


        }
    });

}



private void savestateMap(){

    CheckBox [] checkBoxAll={
    checkBsr , checkBbr , checkBls ,
    checkBts , checkBog , checkBug ,
    checkBab, checkBlg, checkBwi,
    checkBbm, checkBsm, checkBct,
    checkBfl, checkBsc, checkBdp,
    checkBlf, checkBbNi, checkBms,
    checkBrr, checkBsk
    };



    EditText [] editextsAll={
    ediNUmDef1, ediNUmDef2 , ediNUmDef3 ,
    ediNUmDef4 , ediNUmDef5 , ediNUmDef6 ,
    ediNUmDef7 , ediNUmDef8 ,
    ediNUmDef9 , ediNUmDef10, ediNUmDef11,
    ediNUmDef12, ediNUmDef13, ediNUmDef14,
    ediNUmDef15 , ediNUmDef16 , ediNUmDef17 ,
    ediNUmDef18 , ediNUmDef19 ,ediNUmDef20



    };



    for(int indice=0; indice<editextsAll.length; indice++){

        //chekeamos que si el user dio check tambien alla selecionado el defecto

        if(checkBoxAll[indice].isChecked() && editextsAll[indice].getText().toString().trim().isEmpty()){
            editextsAll[indice].requestFocus();
            editextsAll[indice].setError("ingrese un valor ");

                  return;
        }


        if(!checkBoxAll[indice].isChecked() && !editextsAll[indice].getText().toString().trim().isEmpty()){
            Toast.makeText(getActivity(), "selecione el defecto", Toast.LENGTH_LONG).show();
                return;

        }


        if(!(editextsAll[indice].getText().toString().trim().isEmpty())){
            Utils.HashMapOfListWhitStatesCHeckb.get(curreyKeyIdView).set(indice,new DefectsAndNumber(checkBoxAll[indice].isChecked(),
                    Integer.parseInt(editextsAll[indice].getText().toString())));

        }

        //aqui elimiamos en caso que ya no este chekeed...pero antes si lo estaba
        if(Utils.HashMapOfListWhitStatesCHeckb.get(curreyKeyIdView).get(indice).isIschekedDefecto() &&
        !checkBoxAll[indice].isChecked()
        ){ //entramos a la array list y chekeamos si esta chekeed


            //eliminamos este estado en chekeed
            Utils.HashMapOfListWhitStatesCHeckb.get(curreyKeyIdView).set(indice,new DefectsAndNumber(false,0));


        }



    }


    dismiss();

}



private void setStatesChekeedAndNumber() {

    CheckBox[] checkBoxAll = {
            checkBsr, checkBbr, checkBls,
            checkBts, checkBog, checkBug,
            checkBab, checkBlg, checkBwi,
            checkBbm, checkBsm, checkBct,
            checkBfl, checkBsc, checkBdp,
            checkBlf, checkBbNi, checkBms,
            checkBrr, checkBsk
    };


    EditText[] editextsAll = {
            ediNUmDef1, ediNUmDef2, ediNUmDef3,
            ediNUmDef4, ediNUmDef5, ediNUmDef6,
            ediNUmDef7, ediNUmDef8,
            ediNUmDef9, ediNUmDef10, ediNUmDef11,
            ediNUmDef12, ediNUmDef13, ediNUmDef14,
            ediNUmDef15, ediNUmDef16, ediNUmDef17,
            ediNUmDef18, ediNUmDef19, ediNUmDef20


    };




    Log.i("somerlier","el ukey es "+curreyKeyIdView);

    ArrayList<DefectsAndNumber>lis=Utils.HashMapOfListWhitStatesCHeckb.get(curreyKeyIdView);

    Log.i("somerlier","el size es  "+lis.size());

    DefectsAndNumber [] statesArray =listToAarray(Utils.HashMapOfListWhitStatesCHeckb.get(curreyKeyIdView));





    for(int indice=0; indice<statesArray.length; indice++){
        checkBoxAll[indice].setChecked(statesArray[indice].isIschekedDefecto());


        if(statesArray[indice].getNumDefects()>0){
            editextsAll[indice].setText(String.valueOf(statesArray[indice].getNumDefects()));

        }

    }


}

    private DefectsAndNumber [] listToAarray(ArrayList<DefectsAndNumber> list){

        DefectsAndNumber array[]= new DefectsAndNumber[list.size()];


        for(int indice=0; indice< list.size(); indice++){

            array[indice]=list.get(indice);


        }


        return  array;


    }




}

