package com.tiburela.qsercom.models;

public class TableCalidProdc {


    public TableCalidProdc(String tipoEmpaque, int totalEmbacado, double porcentajeQS) {
        this.tipoEmpaque = tipoEmpaque;
        this.totalEmbacado = totalEmbacado;
        this.porcentajeQS = porcentajeQS;
    }

    private String tipoEmpaque;
    private int  totalEmbacado;

    public String getTipoEmpaque() {
        return tipoEmpaque;
    }

    public void setTipoEmpaque(String tipoEmpaque) {
        this.tipoEmpaque = tipoEmpaque;
    }

    public int getTotalEmbacado() {
        return totalEmbacado;
    }

    public void setTotalEmbacado(int totalEmbacado) {
        this.totalEmbacado = totalEmbacado;
    }

    public double getPorcentajeQS() {
        return porcentajeQS;
    }

    public void setPorcentajeQS(double porcentajeQS) {
        this.porcentajeQS = porcentajeQS;
    }

    private double  porcentajeQS;

}
