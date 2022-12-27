package com.tiburela.qsercom.models;

public class TableCalidProdc {

    public String getNombreMarcaDeCaja() {
        return nombreMarcaDeCaja;
    }

    public void setNombreMarcaDeCaja(String nombreMarcaDeCaja) {
        this.nombreMarcaDeCaja = nombreMarcaDeCaja;
    }

    private String nombreMarcaDeCaja;

    public TableCalidProdc(String tipoEmpaque, int totalEmbacado, double porcentajeQS,String codigo,String nombreMarcaDeCaja) {
        this.tipoEmpaque = tipoEmpaque;
        this.totalEmbacado = totalEmbacado;
        this.porcentajeQS = porcentajeQS;
        this.codigo=codigo;
        this.nombreMarcaDeCaja=nombreMarcaDeCaja;
    }

    private String tipoEmpaque;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private String codigo;

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
