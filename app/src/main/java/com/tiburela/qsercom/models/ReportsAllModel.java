package com.tiburela.qsercom.models;

public class ReportsAllModel {
    public boolean estaCorregido;
    public boolean estSubido;
    public boolean estaTerminado;
    public String nombreCategoria;
    public String dateReport;
    public int reporteTipo;

    public String idInforme;


    public int getReporteTipo() {
        return reporteTipo;
    }

    public void setReporteTipo(int reporteTipo) {
        this.reporteTipo = reporteTipo;
    }

    public boolean isEstaCorregido() {
        return estaCorregido;
    }

    public void setEstaCorregido(boolean estaCorregido) {
        this.estaCorregido = estaCorregido;
    }

    public boolean isEstSubido() {
        return estSubido;
    }

    public void setEstSubido(boolean estSubido) {
        this.estSubido = estSubido;
    }

    public boolean isEstaTerminado() {
        return estaTerminado;
    }

    public void setEstaTerminado(boolean estaTerminado) {
        this.estaTerminado = estaTerminado;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDateReport() {
        return dateReport;
    }

    public void setDateReport(String dateReport) {
        this.dateReport = dateReport;
    }


    public String getIdInforme() {
        return idInforme;
    }

    public ReportsAllModel(int reporteTipo, boolean estaCorregido, boolean estSubido, boolean estaTerminado, String nombreCategoria, String dateReport, String idInforme) {
        this.reporteTipo = reporteTipo;
        this.estaCorregido = estaCorregido;
        this.estSubido = estSubido;
        this.estaTerminado = estaTerminado;
        this.nombreCategoria = nombreCategoria;
        this.dateReport = dateReport;
        this.idInforme=idInforme;
    }


}
