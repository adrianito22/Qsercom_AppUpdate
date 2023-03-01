package com.tiburela.qsercom.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ControlCalidad {



    private String uniqueId;

    public int getNumeroClustersInspeccioandos() {
        return numeroClustersInspeccioandos;
    }

    public void setNumeroClustersInspeccioandos(int numeroClustersInspeccioandos) {
        this.numeroClustersInspeccioandos = numeroClustersInspeccioandos;
    }

    private int numeroClustersInspeccioandos;


    private String numeroDedosXclusterOmano;
    private String numClusterPorCaja;

    public String getNumClusterPorCaja() {
        return numClusterPorCaja;
    }

    public void setNumClusterPorCaja(String numClusterPorCaja) {
        this.numClusterPorCaja = numClusterPorCaja;
    }

    public String getNumCalibracionEntreApical() {
        return numCalibracionEntreApical;
    }

    public void setNumCalibracionEntreApical(String numCalibracionEntreApical) {
        this.numCalibracionEntreApical = numCalibracionEntreApical;
    }

    public String getNumGradoCalibrePromedio() {
        return numGradoCalibrePromedio;
    }

    public void setNumGradoCalibrePromedio(String numGradoCalibrePromedio) {
        this.numGradoCalibrePromedio = numGradoCalibrePromedio;
    }

    private String numCalibracionEntreApical;
    private String numGradoCalibrePromedio;




    public String getCustomDefectosNodeAtach() {
        return customDefectosNodeAtach;
    }

    private String customDefectosNodeAtach;


    public boolean isEstaCheckeed() {
        return estaCheckeed;
    }

    public void setEstaCheckeed(boolean estaCheckeed) {
        this.estaCheckeed = estaCheckeed;
    }

    private boolean estaCheckeed;


    public String getIdDelInformePeretenece() {
        return idDelInformePeretenece;
    }

    public void setIdDelInformePeretenece(String idDelInformePeretenece) {
        this.idDelInformePeretenece = idDelInformePeretenece;
    }

    private String idDelInformePeretenece;

    public String getIdInformesVinculadosContCald() {
        return idInformesVinculadosContCald;
    }

    public void setIdInformesVinculadosContCald(String idInformesVinculadosContCald) {
        this.idInformesVinculadosContCald = idInformesVinculadosContCald;
    }

    private String idInformesVinculadosContCald;

    private String simpleDate;
    private String keyDondeEstaraHasmapDefecSelec;

    public String getKeyDondeEstaraHasmapDefecSelec2Emp() {
        return keyDondeEstaraHasmapDefecSelec2Emp;
    }
    public void setKeyDondeEstaraHasmapDefecSelec2Emp(String keyDondeEstaraHasmapDefecSelec2Emp) {
        this.keyDondeEstaraHasmapDefecSelec2Emp = keyDondeEstaraHasmapDefecSelec2Emp;
    }

    private String keyDondeEstaraHasmapDefecSelec2Emp;


    private double timeDateMillis;

    public String getKeyDondeEstarThisInform() {
        return keyDondeEstarThisInform;
    }

    public void setKeyDondeEstarThisInform(String keyDondeEstarThisInform) {
        this.keyDondeEstarThisInform = keyDondeEstarThisInform;
    }

    private String keyDondeEstarThisInform;


    public String getKeyWhereLocateasHmapFieldsRecha() {
        return keyWhereLocateasHmapFieldsRecha;
    }

    public void setKeyWhereLocateasHmapFieldsRecha(String keyWhereLocateasHmapFieldsRecha) {
        this.keyWhereLocateasHmapFieldsRecha = keyWhereLocateasHmapFieldsRecha;
    }

    private String keyWhereLocateasHmapFieldsRecha;


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSimpleDate() {
        return simpleDate;
    }

    public void setSimpleDate(String simpleDate) {
        this.simpleDate = simpleDate;
    }

    public double getTimeDateMillis() {
        return timeDateMillis;
    }

    public void setTimeDateMillis(double timeDateMillis) {
        this.timeDateMillis = timeDateMillis;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNodekeyLocation() {
        return nodekeyLocation;
    }

    public void setNodekeyLocation(String nodekeyLocation) {
        this.nodekeyLocation = nodekeyLocation;
    }

    private String observaciones;
    private String nodekeyLocation;

    private String vapor;
    private String productor;
    private String codigo;
    private String zona;
    private String hacienda;
    private String exportadora;
    private String compania;
    private String cliente;
    private int semana;
    private String fecha;
    private String magap;
    private String marcaCaja;
    private String tipoEmpaque;
    private String destino;
    private int totalCajas;

    public String getVapor() {
        return vapor;
    }

    public void setVapor(String vapor) {
        this.vapor = vapor;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getHacienda() {
        return hacienda;
    }

    public void setHacienda(String hacienda) {
        this.hacienda = hacienda;
    }

    public String getExportadora() {
        return exportadora;
    }

    public void setExportadora(String exportadora) {
        this.exportadora = exportadora;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMagap() {
        return magap;
    }

    public void setMagap(String magap) {
        this.magap = magap;
    }

    public String getMarcaCaja() {
        return marcaCaja;
    }

    public void setMarcaCaja(String marcaCaja) {
        this.marcaCaja = marcaCaja;
    }

    public String getTipoEmpaque() {
        return tipoEmpaque;
    }

    public void setTipoEmpaque(String tipoEmpaque) {
        this.tipoEmpaque = tipoEmpaque;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public int getTotalCajas() {
        return totalCajas;
    }

    public void setTotalCajas(int totalCajas) {
        this.totalCajas = totalCajas;
    }

    public float getCalidaCamp() {
        return calidaCamp;
    }

    public void setCalidaCamp(float calidaCamp) {
        this.calidaCamp = calidaCamp;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraTermi() {
        return horaTermi;
    }

    public void setHoraTermi(String horaTermi) {
        this.horaTermi = horaTermi;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getSellosnav() {
        return sellosnav;
    }

    public void setSellosnav(String sellosnav) {
        this.sellosnav = sellosnav;
    }

    public String getSelloVerificadora() {
        return selloVerificadora;
    }

    public void setSelloVerificadora(String selloVerificadora) {
        this.selloVerificadora = selloVerificadora;
    }

    public String getTermografo() {
        return termografo;
    }

    public void setTermografo(String termografo) {
        this.termografo = termografo;
    }

    public String getPlacaCarro() {
        return placaCarro;
    }

    public void setPlacaCarro(String placaCarro) {
        this.placaCarro = placaCarro;
    }

    public String getPuertEmbarq() {
        return puertEmbarq;
    }

    public void setPuertEmbarq(String puertEmbarq) {
        this.puertEmbarq = puertEmbarq;
    }

    private float calidaCamp;
    private String horaIni;
    private String horaTermi;
    private String contenedor;
    private String sellosnav;
    private String selloVerificadora;
    private String termografo;
    private String placaCarro;
    private String puertEmbarq;



    //default copntructort
    public  ControlCalidad() {


    }


    public String getKeyDondeEstaraHasmapDefecSelec() {
        return keyDondeEstaraHasmapDefecSelec;
    }

    public void setKeyDondeEstaraHasmapDefecSelec(String keyDondeEstaraHasmapDefecSelec) {
        this.keyDondeEstaraHasmapDefecSelec = keyDondeEstaraHasmapDefecSelec;
    }

    public String getNumeroDedosXclusterOmano() {
        return numeroDedosXclusterOmano;
    }

    public void setNumeroDedosXclusterOmano(String numeroDedosXclusterOmano) {
        this.numeroDedosXclusterOmano = numeroDedosXclusterOmano;
    }

    public void setCustomDefectosNodeAtach(String customDefectosNodeAtach) {
        this.customDefectosNodeAtach = customDefectosNodeAtach;
    }

    public ControlCalidad(String observaciones, String nodekeyLocation, String keyWhereLocateasHmapFieldsRecha, String vapor,
                          String productor, String codigo, String zona, String hacienda, String exportadora, String compania,
                          String cliente, int semana, String fecha, String magap, String marcaCaja, String tipoEmpaque,
                          String destino, int totalCajas, float calidaCamp, String horaIni, String horaTermi, String contenedor,
                          String sellosnav, String selloVerificadora, String termografo, String placaCarro, String puertEmbarq,int numeroClustersInspeccioandos) {

        uniqueId = UUID.randomUUID().toString();
        timeDateMillis = new Date().getTime();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        simpleDate = formatter.format(timeDateMillis);

        this.observaciones = observaciones;
        this.nodekeyLocation = nodekeyLocation;
        this.keyWhereLocateasHmapFieldsRecha=keyWhereLocateasHmapFieldsRecha;
        keyDondeEstaraHasmapDefecSelec= "";
       // keyDondeEstaraHasmapDefecSelec2Emp="";
        this.vapor=vapor;
        this.productor=productor;
        this.codigo=codigo;
        this.zona=zona;
        this.hacienda=hacienda;
        this.exportadora=exportadora;
        this.compania=compania;
        this.cliente=cliente;
        this.semana=semana;
        this.fecha=fecha;
        this.magap=magap;
        this.marcaCaja=marcaCaja;
        this.tipoEmpaque=tipoEmpaque;
        this.destino=destino;
        this.totalCajas=totalCajas;
        this.calidaCamp=calidaCamp;
        this.horaIni=horaIni;
        this.horaTermi=horaTermi;
        this.contenedor=contenedor;
        this.sellosnav=sellosnav;
        this.selloVerificadora=selloVerificadora;
        this.termografo=termografo;
        this.placaCarro=placaCarro;
        this.puertEmbarq=puertEmbarq;
        keyDondeEstarThisInform="";
        idDelInformePeretenece="";
        idInformesVinculadosContCald ="";
        estaCheckeed =false;

        customDefectosNodeAtach="";

        numeroDedosXclusterOmano =""; //creo eu ya esta
         numClusterPorCaja=""; //EN PREVIEW YA ESTA, FALTA EN ACTIVITY

         numCalibracionEntreApical="";
         numGradoCalibrePromedio="";

        this.numeroClustersInspeccioandos=numeroClustersInspeccioandos;
    }




}
