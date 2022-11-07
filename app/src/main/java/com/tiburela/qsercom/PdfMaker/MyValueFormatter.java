package com.tiburela.qsercom.PdfMaker;

import android.icu.text.DecimalFormat;
import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;import java.text.Format;

public class MyValueFormatter extends ValueFormatter {
   // private DecimalFormat format = new DecimalFormat("###,##0.0");

    private DecimalFormat format = new DecimalFormat("###,##");


    public String getAxisLabel(float value, YAxis axis) {
       // return super.getAxisLabel(value, axis);

        return format.format(value);

    }

    @Override
    public String getBarLabel(BarEntry barEntry) {
        return super.getBarLabel(barEntry);
    }

    @Override
    public String getFormattedValue(float value) {
        Log.i("sangano","el value es  "+ value);
        Log.i("sangano","el value con format es "+ format.format(value));

        if(value==0.0){

            return String.valueOf(0.00);

        }else{

            return String.valueOf(value);

        }

        //return super.getFormattedValue(value);

    }


}
