package com.tiburela.qsercom.Canvas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.tiburela.qsercom.R;
import com.tiburela.qsercom.models.TableFifi;

import java.util.ArrayList;

public class PaintView extends View {

    // below we are creating variables for our paint
    Paint otherPaint, outerPaint, textPaint;

    // and a floating variable for our left arc.
    float arcLeft;

    @SuppressLint("ResourceAsColor")
    public PaintView(Context context) {
        super(context);

        // on below line we are initializing our paint variable for our text
        textPaint = new Paint(Paint.LINEAR_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

        // on below line we are setting color to it.
        textPaint.setColor(Color.WHITE);

        // on below line we are setting text size to it.
        // In Paint we have to add text size using px so
        // we have created a method where we are converting dp to pixels.
        textPaint.setTextSize(pxFromDp(context, 24));

        // on below line we are initializing our outer paint
        outerPaint = new Paint();

        // on below line we are setting style to our paint.
        outerPaint.setStyle(Paint.Style.FILL);

        // on below line we are setting color to it.
        outerPaint.setColor(getResources().getColor(R.color.purple_200));

        // on below line we are creating a display metrics
        DisplayMetrics displayMetrics = new DisplayMetrics();

        // on below line we are getting display metrics.
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);

        // on below line we are assigning
        // the value to the arc left.
        arcLeft = pxFromDp(context, 20);


        Log.i("alineamos","el size  : "+arcLeft);

        // on below line we are creating
        // a new variable for our paint
        otherPaint = new Paint();
    }

    // below method is use to generate px from DP.
    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;


        Log.i("alineamos","el width es : "+width);


        ArrayList<String>milista= new ArrayList<>();


        milista.add("este es un texto muy largo");

        for(int indice=0; indice<12; indice++){

            milista.add("item num "+indice);

        }
        milista.add("hola buenossssssssoo vamosssss");


        otherPaint.setColor(getResources().getColor(R.color.white2));



        int [] percent  = {33,33,33

        };

        Paint paint = new Paint();

        paint.setTextAlign(Paint.Align.LEFT);
        // mipaintLines.setTextAlign(Paint.Align.CENTE);
        paint.setColor(Color.parseColor("#3A3A3A"));
//int anchoTable, int marginRigth, int marginLeft, int numFilas,
//                     int altoCelda, Paint lines, ArrayList <String>lisContenidos)

        TableFifi table1=new TableFifi(TableFifi.SIZE_1_TERCIO,2,100,100,50,TableFifi.ALIGN_TABLE_RIGTH,paint,milista);


       // table1.DrawTableInCanvasaAndSetText(table1,canvas,40,40);// ERA  DE 50

        table1.DrawTableAdpatoSizeInCanvasaAndSetText(table1,canvas,20,20);



   //     TableFifi table2=new TableFifi(TableFifi.MATCHSIZE_DOCUEMENTO,3,60,60,40,TableFifi.ALIGN_TABLE_CENTER,paint,milista);
    //    table2.addTableAbajoDe(table1,table2.HIGTH_MARGIN);
     //  table2.DrawTableInCanvasaAndSetText(table2,canvas,50,50);




    }
}