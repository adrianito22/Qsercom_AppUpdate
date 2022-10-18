package com.tiburela.qsercom.dialog_fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.SharePref.SharePref;
import com.tiburela.qsercom.utils.Variables;

public class DialogConfirmCreateNewForm {

    public  void showBottomSheetDialogConfirmMenu(Context context, Class<?> cls,String keySHAREdelete) {

        BottomSheetDialog   bottomSheetDialog = new BottomSheetDialog(context);

        bottomSheetDialog.setContentView(R.layout.bottom_sheetss);

        Button btnSi=bottomSheetDialog.findViewById(R.id.btnSi);
        Button btnNo=bottomSheetDialog.findViewById(R.id.btnNo);


        btnSi.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {
                Variables.hayUnFormIncompleto =false;
                SharePref.deleteMap(context,keySHAREdelete);

                goActivity(context, cls);

               bottomSheetDialog.dismiss();



            }
        });



        btnNo.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {
                Variables.hayUnFormIncompleto =true;

                goActivity(context, cls);

                bottomSheetDialog.dismiss();
            }
        });


        bottomSheetDialog.show();
    }




    private void goActivity(Context contexto, Class<?> cls){
        Intent intemcion= new Intent(contexto,cls);
        contexto.startActivity(intemcion);
    }


}
