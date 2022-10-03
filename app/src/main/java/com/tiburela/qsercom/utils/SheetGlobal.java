package com.tiburela.qsercom.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tiburela.qsercom.R;
import com.tiburela.qsercom.activities.ActivitySeeReports;

public class SheetGlobal {


    public static  void showBottomSheetDialogConfirm(Activity activity, Context contexto) {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(contexto);

        bottomSheetDialog.setContentView(R.layout.bottom_sheet_edit_cpn);

        LinearLayout lyRevisar = bottomSheetDialog.findViewById(R.id.lyRevisar);
        LinearLayout lyEditar = bottomSheetDialog.findViewById(R.id.lyEditar);
        LinearLayout layOtherOpcion = bottomSheetDialog.findViewById(R.id.layOtherOpcion);




        lyRevisar.setOnClickListener(new View.OnClickListener() { //revisar

            @Override
            public void onClick(View v) {




                bottomSheetDialog.dismiss();


            }
        });



        lyEditar.setOnClickListener(new View.OnClickListener() {  //activar switch
            @Override
            public void onClick(View v) {




                bottomSheetDialog.dismiss();
            }
        });



        layOtherOpcion.setOnClickListener(new View.OnClickListener() { //editar
            @Override
            public void onClick(View v) {

                bottomSheetDialog.dismiss();


            }
        });

        bottomSheetDialog.show();
    }


}
