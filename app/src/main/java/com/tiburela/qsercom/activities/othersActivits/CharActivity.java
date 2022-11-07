package com.tiburela.qsercom.activities.othersActivits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.tiburela.qsercom.R;

import java.util.ArrayList;
import java.util.List;

public class CharActivity extends AppCompatActivity {
    LineChart chart;
    PieChart pieChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

         chart = (LineChart) findViewById(R.id.barChartView);
        pieChart=(PieChart) findViewById(R.id.pieChart);
      //  YourData[] dataObjects = ...;
        List<Entry> entries = new ArrayList<Entry>();
        List<PieEntry> entriesPie = new ArrayList<PieEntry>();


        entries.add(new Entry(1, 2));
        entries.add(new Entry(2, 4));
        entries.add(new Entry(3, 6));
        entries.add(new Entry(4, 8));
        entries.add(new Entry(5, 10));

        entriesPie.add(new PieEntry(100,5));
        entriesPie.add(new PieEntry(1,3));
        entriesPie.add(new PieEntry(1,1));
        entriesPie.add(new PieEntry(1,5));



        LineDataSet dataSet = new LineDataSet ( entries, "Etiqueta" ) ; // agregar entradas al conjunto de datos
        PieDataSet piedDataSet=new PieDataSet(entriesPie,"fdgd");

        //dataSet.setColor(...);
      //  dataSet.setValueTextColor(...); // styling, ...
        LineData lineData = new LineData(dataSet);


        PieData pieData=new PieData(piedDataSet);

        chart.setData(lineData);
        chart.invalidate(); // refresh

        pieChart.setData(pieData);
        pieChart.invalidate(); // refresh



    }
}