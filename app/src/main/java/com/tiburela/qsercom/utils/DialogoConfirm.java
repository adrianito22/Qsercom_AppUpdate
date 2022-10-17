package com.tiburela.qsercom.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.FormularioControlCalidadPreview;
import com.tiburela.qsercom.activities.PackingListPreviewActivity;
import com.tiburela.qsercom.callbacks.CallbackDialogConfirm;
import com.tiburela.qsercom.database.RealtimeDB;
import com.tiburela.qsercom.models.PackingListMod;

public class DialogoConfirm {


   static  CallbackDialogConfirm callbackDialogConfirm;


    public static   void showBottomSheetDialogConfirmAndCallUpdate(Context context,int tipoFormulario) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_confirm_changes);

        Button btnSi=bottomSheetDialog.findViewById(R.id.btnSi);
        Button btnNo=bottomSheetDialog.findViewById(R.id.btnNo);


        btnSi.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {

              //  PackingListMod objePackingList=new PackingListMod(Integer.parseInt(mEdiTotalCajas.getText().toString()),mEdiContenedorxzz.getText().toString());
              //  objePackingList.setTimeCurrenMillisecds(Variables.currenReportPackinList.getTimeCurrenMillisecds());
               /// objePackingList.setSimpledatFormt(Variables.currenReportPackinList.getSimpledatFormt());

                //actualizamos data
              //  RealtimeDB.updateNewPackingListHasMap(packingListMap,Variables.currenReportPackinList);
              //  RealtimeDB.updatePackingListObject(objePackingList,Variables.currenReportPackinList);


                // finish(); //lamaos el calback aqui

                if(tipoFormulario==1){

                //...CREAMOS UN OBJETO DE ESTA CLASE
                   // callbackDialogConfirm=

                 //1 creamos la interfa de la clase que implmenta usamdo el constructor de la clase que implmenta

                    CallbackDialogConfirm callbacClaseiImplement= new FormularioControlCalidadPreview();
                    callbackDialogConfirm=callbacClaseiImplement;
                    callbacClaseiImplement.ConfirmChangsForActivityPrev(true);

                 //
                    //
                    //
                    //   callbackDialogConfirm=



                }else  if(tipoFormulario==2){


                }



                Toast.makeText(context, "Hecho", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();



               // finish(); //lamaos el calback aqui

            }
        });



        btnNo.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();
            }
        });


        bottomSheetDialog.show();
    }



}
