package com.tiburela.qsercom.activities.othersActivits;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.tiburela.qsercom.R;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        createPieChar();
    }

    private void createPieChar(){

        PieChart pieChart;
        pieChart=findViewById(R.id.pieChart_view);

        String label = "";
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#5c9cd6")); //azul el porcentaje que sirve
        colors.add(Color.parseColor("#ed7d31"));  //narnja el rechzado


        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(92f,"Calidad"));
        pieEntries.add(new PieEntry(8f,"Defectos"));



        PieDataSet pieDataSet = new PieDataSet(pieEntries,label);
        pieDataSet.setValueTextSize(23f);

        Typeface tf = Typeface.createFromAsset(getAssets(),"m_bold.ttf");
       // pieChart.getDescription().setTypeface(tf);
        pieDataSet.setValueTypeface(tf);
        //providing color list for coloring different entries
        pieDataSet.setColors(colors);
        pieDataSet.setValueTextColor(Color.parseColor("#ffffff"));
        pieDataSet.setDrawIcons(true);
       // pieDataSet.setFormSize(100f);
        //grouping the data set from entry to chart
        PieData pieData = new PieData(pieDataSet);
    ;
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true);

        pieChart.setHoleRadius(0f);
        pieChart.setTransparentCircleRadius(0f);

        pieChart.getDescription().setEnabled(false);

       // pieChart.setDrawCenterText(false);
        // https://medium.com/@clyeung0714/using-mpandroidchart-for-android-application-piechart-123d62d4ddc0
       // pieChart.setDrawEntryLabels(false);
       // pieChart.getDescription().setEnabled(false);

        pieData.setValueFormatter(new PercentFormatter(pieChart));
        pieChart.setUsePercentValues(true);
        pieChart.setData(pieData);

        pieChart.setDrawEntryLabels(false); // To remove labels from piece of pie
        pieChart.setBackgroundColor(Color.parseColor("#dcdcdc"));


        // pieChart.getDescription().;
       // pieChart.setHoleColor(Color.parseColor("#000000"));

        // pieChart.setEntryLabelTextSize(19f);  // You can increase or decrease value as per your need in argument


        pieChart.invalidate();
    }


}