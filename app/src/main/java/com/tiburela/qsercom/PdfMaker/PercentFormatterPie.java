package com.tiburela.qsercom.PdfMaker;

import android.icu.text.DecimalFormat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class PercentFormatterPie extends ValueFormatter {

    public DecimalFormat mFormat;
    private PieChart pieChart;

    public PercentFormatterPie() {
        mFormat = new DecimalFormat("0.00");
    }
    public PercentFormatterPie(PieChart pieChart) {
        this();
        this.pieChart = pieChart;
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value) + " %";
    }

    @Override
    public String getPieLabel(float value, PieEntry pieEntry) {
        if (pieChart != null && pieChart.isUsePercentValuesEnabled()) {
            // Converted to percent
            return getFormattedValue(value);
        } else {
            // raw value, skip percent sign
            return mFormat.format(value);
        }
    }

}
