package com.tiburela.qsercom.utils;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.FormularioControlCalidadPreview;
import com.tiburela.qsercom.callbacks.CallBtoActityCamnsYcarretas;
import com.tiburela.qsercom.callbacks.CallBtoActityConteEnAcop;
import com.tiburela.qsercom.callbacks.CallBtoActityContenedor;
import com.tiburela.qsercom.callbacks.CallBtoActityFormControlCalid;
import com.tiburela.qsercom.callbacks.CallBtoActityMuestreoRechaz;
import com.tiburela.qsercom.callbacks.CallBtoActityPakingList;

public class DialogoConfirm {


    static CallBtoActityFormControlCalid callbackControlCalidad;
    static CallBtoActityMuestreoRechaz callBtoActityMuestreoRechaz;
    static CallBtoActityPakingList callBtoActityPakingList;
    static CallBtoActityCamnsYcarretas callBtoActityCamnsYcarretas;
    static CallBtoActityConteEnAcop callBtoActityConteEnAcop;
    static CallBtoActityContenedor callBtoActityContenedor;


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

                if(tipoFormulario==Variables.FormCantrolCalidad){

                    CallBtoActityFormControlCalid callbacClaseiImplement= new FormularioControlCalidadPreview();
                    callbackControlCalidad =callbacClaseiImplement;
                    callbacClaseiImplement.confirmChangs(true);



                }

                else  if(tipoFormulario==Variables.FormPreviewContenedores){


                }
                else  if(tipoFormulario==Variables.FormatDatsContAcopi){


                }
                else  if(tipoFormulario==Variables.FormCamionesyCarretasActivity){//


                }

                else  if(tipoFormulario==Variables.FormPackingList){


                }

                else  if(tipoFormulario==Variables.FormMuestreoRechaz){


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
