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

public class BottonSheetSelecDanosEmpaque extends BottomSheetDialogFragment {
    public static final String TAG = "ActionBottomDialogx";
    private View vista;
    static String curreyKeyIdView;


    private ImageView imgCloseSheet;

    private CheckBox checkBoxBu;
    private CheckBox checkBoxSr;
    private CheckBox checkBoxBr;
    private CheckBox checkBoxNi;

    private EditText ediNUmDef1;
    private EditText ediNUmDef2;
    private EditText ediNUmDef3;
    private EditText ediNUmDef4;



    public static BottonSheetSelecDanosEmpaque newInstance(String curreyKeyIdViewx) {

        curreyKeyIdView = curreyKeyIdViewx;


        return new BottonSheetSelecDanosEmpaque();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.bottom_sheet_danos_empaque2, container, false);


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
      checkBoxBu=vista.findViewById(R.id.checkBoxBu);
      checkBoxSr=vista.findViewById(R.id.checkBoxSr);
      checkBoxBr=vista.findViewById(R.id.checkBoxBr);
      checkBoxNi=vista.findViewById(R.id.checkBoxNi);

      ediNUmDef1=vista.findViewById(R.id.ediNUmDef1);
      ediNUmDef2=vista.findViewById(R.id.ediNUmDef2);
      ediNUmDef3=vista.findViewById(R.id.ediNUmDef3);
      ediNUmDef4=vista.findViewById(R.id.ediNUmDef4);



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
            checkBoxBu,
    checkBoxSr,
    checkBoxBr,
    checkBoxNi

    };



    EditText [] editextsAll={
    ediNUmDef1, ediNUmDef2 , ediNUmDef3 ,
    ediNUmDef4

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
            Utils.HashMapOfListWhitStatesCHeckb2.get(curreyKeyIdView).set(indice,new DefectsAndNumber(checkBoxAll[indice].isChecked(),
                    Integer.parseInt(editextsAll[indice].getText().toString())));

        }

        //aqui elimiamos en caso que ya no este chekeed...pero antes si lo estaba
        if(Utils.HashMapOfListWhitStatesCHeckb2.get(curreyKeyIdView).get(indice).isIschekedDefecto() &&
                !checkBoxAll[indice].isChecked()
        ){ //entramos a la array list y chekeamos si esta chekeed


            //eliminamos este estado en chekeed
            Utils.HashMapOfListWhitStatesCHeckb2.get(curreyKeyIdView).set(indice,new DefectsAndNumber(false,0));


        }




    }


    dismiss();

}



private void setStatesChekeedAndNumber() {


    CheckBox [] checkBoxAll={
            checkBoxBu,
            checkBoxSr,
            checkBoxBr,
            checkBoxNi

    };



    EditText [] editextsAll={
            ediNUmDef1, ediNUmDef2 , ediNUmDef3 ,
            ediNUmDef4

    };




    Log.i("somerlier","el ukey es "+curreyKeyIdView);

    ArrayList<DefectsAndNumber>lis=Utils.HashMapOfListWhitStatesCHeckb2.get(curreyKeyIdView);

    Log.i("somerlier","el size es  "+lis.size());

    DefectsAndNumber [] statesArray =listToAarray(Utils.HashMapOfListWhitStatesCHeckb2.get(curreyKeyIdView));





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

