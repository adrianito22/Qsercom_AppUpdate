package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SetInformEmbarque1 {

    public static HashMap<String, String> prodcutsPostCosecha;

    /***Propiedades del informe*/

    private String codeInforme;
    private String simpleDataFormat;
    private boolean esVisually; //si aun se puede visualizar
    private boolean esEditableNow; //si aun se puede editar
    private int  stateInformacion; //si es borrador
    private long fechaCreacionInf;
    private String  numcionContenedor;


    private String uniqueIDinforme;

    public String getKeyFirebase() {
        return keyFirebase;
    }

    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    private String keyFirebase;

    public SetInformEmbarque1() {


    }


    public String getNumcionContenedor() {
        return numcionContenedor;
    }

    public void setNumcionContenedor(String numcionContenedor) {
        this.numcionContenedor = numcionContenedor;
    }

    public SetInformEmbarque1(String  uniqueIDinforme , String codeInforme, int ediNhojaEvaluacion, String zona, String productor,
                              String codigo, String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte,
                              String ntargetaEmbarque, String inscirpMagap, String horaInicio, String horaTermino, String semana,
                              String empacadora, String contenedor, String observacion, String horaLlegadaContenedor,
                              String horaSalidadContenedor,
                              String destinoContenedor,
                              String numeroViajeContenedor,
                              String numcionContenedor,
                              String vapor,
                              String tipoContenedor,
                              String tare,
                              String booking,
                              String maxGross,
                              String nSerieFunda,
                              String stickerVentoExtern,
                              String cableRastreoLlegada,
                              String selloPlasticoNaviera,
                              String otroSelloLlegadaEspec) {

        this.uniqueIDinforme=uniqueIDinforme;
        this.codeInforme = codeInforme;
        esVisually = true;
        esEditableNow = true;
        stateInformacion = 1;
        fechaCreacionInf = new Date().getTime();


        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        simpleDataFormat = formatter.format(fechaCreacionInf);

        this.ediNhojaEvaluacion = ediNhojaEvaluacion;
        this.zona = zona;
        this.productor = productor;
        this.codigo = codigo;
        this.pemarque = pemarque;
        this.nguiaRemision = nguiaRemision;
        this.hacienda = hacienda;
        this._nguia_transporte = _nguia_transporte;
        this.ntargetaEmbarque = ntargetaEmbarque;
        this.inscirpMagap = inscirpMagap;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.semana = semana;
        this.empacadora = empacadora;
        this.contenedor = contenedor;
        this.observacion = observacion;
        this.horaLlegadaContenedor = horaLlegadaContenedor;
        this.horaSalidadContenedor = horaSalidadContenedor;
        this.destinoContenedor = destinoContenedor;
        this.numeroViajeContenedor = numeroViajeContenedor;
        this.vapor = vapor;
        this.tipoContenedor = tipoContenedor;
        this.tare = tare;
        this.booking = booking;
        this.maxGross = maxGross;
        this.nSerieFunda = nSerieFunda;
        this.stickerVentoExtern = stickerVentoExtern;
        this.cableRastreoLlegada = cableRastreoLlegada;
        this.selloPlasticoNaviera = selloPlasticoNaviera;
        this.otroSelloLlegadaEspec = otroSelloLlegadaEspec;

        keyFirebase="";

    }

    /***Datos formulario */

    private int ediNhojaEvaluacion;
    private String zona;
    private String productor;
    private String codigo;
    private String pemarque;
    private String nguiaRemision;
    private String hacienda;
    private String _nguia_transporte;
    private String ntargetaEmbarque;
    private String inscirpMagap;
    private String horaInicio;

    private String horaTermino;
    private String semana;
    private String empacadora;
    private String contenedor;
    private String observacion;


    public static HashMap<String, String> getProdcutsPostCosecha() {
        return prodcutsPostCosecha;
    }

    public static void setProdcutsPostCosecha(HashMap<String, String> prodcutsPostCosecha) {
        SetInformEmbarque1.prodcutsPostCosecha = prodcutsPostCosecha;
    }

    public String getSimpleDataFormat() {
        return simpleDataFormat;
    }

    public void setSimpleDataFormat(String simpleDataFormat) {
        this.simpleDataFormat = simpleDataFormat;
    }

    public void setUniqueIDinforme(String uniqueIDinforme) {
        this.uniqueIDinforme = uniqueIDinforme;
    }

    public String getHoraLlegadaContenedor() {
        return horaLlegadaContenedor;
    }

    public void setHoraLlegadaContenedor(String horaLlegadaContenedor) {
        this.horaLlegadaContenedor = horaLlegadaContenedor;
    }

    public String getHoraSalidadContenedor() {
        return horaSalidadContenedor;
    }

    public void setHoraSalidadContenedor(String horaSalidadContenedor) {
        this.horaSalidadContenedor = horaSalidadContenedor;
    }

    public String getDestinoContenedor() {
        return destinoContenedor;
    }

    public void setDestinoContenedor(String destinoContenedor) {
        this.destinoContenedor = destinoContenedor;
    }

    public String getNumeroViajeContenedor() {
        return numeroViajeContenedor;
    }

    public void setNumeroViajeContenedor(String numeroViajeContenedor) {
        this.numeroViajeContenedor = numeroViajeContenedor;
    }

    public String getVapor() {
        return vapor;
    }

    public void setVapor(String vapor) {
        this.vapor = vapor;
    }

    public String getTipoContenedor() {
        return tipoContenedor;
    }

    public void setTipoContenedor(String tipoContenedor) {
        this.tipoContenedor = tipoContenedor;
    }

    public String getTare() {
        return tare;
    }

    public void setTare(String tare) {
        this.tare = tare;
    }

    public String getBooking() {
        return booking;
    }

    public void setBooking(String booking) {
        this.booking = booking;
    }

    public String getMaxGross() {
        return maxGross;
    }

    public void setMaxGross(String maxGross) {
        this.maxGross = maxGross;
    }

    public String getnSerieFunda() {
        return nSerieFunda;
    }

    public void setnSerieFunda(String nSerieFunda) {
        this.nSerieFunda = nSerieFunda;
    }

    public String getStickerVentoExtern() {
        return stickerVentoExtern;
    }

    public void setStickerVentoExtern(String stickerVentoExtern) {
        this.stickerVentoExtern = stickerVentoExtern;
    }

    public String getCableRastreoLlegada() {
        return cableRastreoLlegada;
    }

    public void setCableRastreoLlegada(String cableRastreoLlegada) {
        this.cableRastreoLlegada = cableRastreoLlegada;
    }

    public String getSelloPlasticoNaviera() {
        return selloPlasticoNaviera;
    }

    public void setSelloPlasticoNaviera(String selloPlasticoNaviera) {
        this.selloPlasticoNaviera = selloPlasticoNaviera;
    }

    public String getOtroSelloLlegadaEspec() {
        return otroSelloLlegadaEspec;
    }

    public void setOtroSelloLlegadaEspec(String otroSelloLlegadaEspec) {
        this.otroSelloLlegadaEspec = otroSelloLlegadaEspec;
    }

    //DATOS DEL CONTENEDOR
    private String horaLlegadaContenedor;
    private String horaSalidadContenedor;
    private String destinoContenedor;
    private String numeroViajeContenedor;
    private String vapor;
    private String tipoContenedor;


    //SELLOS DE LLEGADA
    private String tare;
    private String booking;
    private String maxGross;
    private String nSerieFunda;
    private String stickerVentoExtern;
    private String cableRastreoLlegada;
    private String selloPlasticoNaviera;
    private String otroSelloLlegadaEspec;

//


    public String getCodeInforme() {
        return codeInforme;
    }

    public void setCodeInforme(String codeInforme) {
        this.codeInforme = codeInforme;
    }

    public boolean isEsVisually() {
        return esVisually;
    }

    public void setEsVisually(boolean esVisually) {
        this.esVisually = esVisually;
    }

    public boolean isEsEditableNow() {
        return esEditableNow;
    }

    public void setEsEditableNow(boolean esEditableNow) {
        this.esEditableNow = esEditableNow;
    }

    public int getStateInformacion() {
        return stateInformacion;
    }

    public void setStateInformacion(int stateInformacion) {
        this.stateInformacion = stateInformacion;
    }

    public long getFechaCreacionInf() {
        return fechaCreacionInf;
    }

    public void setFechaCreacionInf(long fechaCreacionInf) {
        this.fechaCreacionInf = fechaCreacionInf;
    }

    public int getEdiNhojaEvaluacion() {
        return ediNhojaEvaluacion;
    }

    public void setEdiNhojaEvaluacion(int ediNhojaEvaluacion) {
        this.ediNhojaEvaluacion = ediNhojaEvaluacion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
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

    public String getPemarque() {
        return pemarque;
    }

    public void setPemarque(String pemarque) {
        this.pemarque = pemarque;
    }

    public String getNguiaRemision() {
        return nguiaRemision;
    }

    public void setNguiaRemision(String nguiaRemision) {
        this.nguiaRemision = nguiaRemision;
    }

    public String getHacienda() {
        return hacienda;
    }

    public void setHacienda(String hacienda) {
        this.hacienda = hacienda;
    }

    public String get_nguia_transporte() {
        return _nguia_transporte;
    }

    public void set_nguia_transporte(String _nguia_transporte) {
        this._nguia_transporte = _nguia_transporte;
    }

    public String getNtargetaEmbarque() {
        return ntargetaEmbarque;
    }

    public void setNtargetaEmbarque(String ntargetaEmbarque) {
        this.ntargetaEmbarque = ntargetaEmbarque;
    }

    public String getInscirpMagap() {
        return inscirpMagap;
    }

    public void setInscirpMagap(String inscirpMagap) {
        this.inscirpMagap = inscirpMagap;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getEmpacadora() {
        return empacadora;
    }

    public void setEmpacadora(String empacadora) {
        this.empacadora = empacadora;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }




    //el informe un informe normal...
    //si queremos hacer otro informe lo podemos eredar





    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("simpleDataFormat", simpleDataFormat);
        result.put("uniqueIDinforme", uniqueIDinforme);
        result.put("codeInforme", codeInforme);
        result.put("esVisually", esVisually);
        result.put("esEditableNow", esEditableNow);
        result.put("stateInformacion", stateInformacion);
        result.put("fechaCreacionInf", fechaCreacionInf);
        result.put("ediNhojaEvaluacion", ediNhojaEvaluacion);
        result.put("zona", zona);
        result.put("productor", productor);
        result.put("codigo", codigo);
        result.put("pemarque", pemarque);
        result.put("nguiaRemision", nguiaRemision);
        result.put("hacienda", hacienda);
        result.put("_nguia_transporte", _nguia_transporte);
        result.put("ntargetaEmbarque", ntargetaEmbarque);
        result.put("inscirpMagap", inscirpMagap);
        result.put("horaInicio", horaInicio);
        result.put("horaTermino", horaTermino);
        result.put("semana", semana);
        result.put("empacadora", empacadora);
        result.put("contenedor", contenedor);
        result.put("observacion", observacion);

        result.put("horaLlegadaContenedor", horaLlegadaContenedor);
        result.put("horaSalidadContenedor", horaSalidadContenedor);
        result.put("destinoContenedor", destinoContenedor);
        result.put("numeroViajeContenedor", numeroViajeContenedor);
        result.put("numcionContenedor", numcionContenedor);
        result.put("vapor", vapor);
        result.put("tipoContenedor", tipoContenedor);

        result.put("tare", tare);
        result.put("booking", booking);
        result.put("maxGross", maxGross);
        result.put("nSerieFunda", nSerieFunda);
        result.put("stickerVentoExtern", stickerVentoExtern);
        result.put("cableRastreoLlegada", cableRastreoLlegada);
        result.put("selloPlasticoNaviera", selloPlasticoNaviera);
        result.put("otroSelloLlegadaEspec", otroSelloLlegadaEspec);

        result.put("keyFirebase", keyFirebase);
          //39

        return result;


    }



    public String getUniqueIDinforme() {
        return uniqueIDinforme;
    }

public static  HashMap generateerateMapProductsPoscosecha(){

    prodcutsPostCosecha = new HashMap<String, String>();
    prodcutsPostCosecha.put("BROMORUX", " ");
    prodcutsPostCosecha.put("RYZUC", " ");
    prodcutsPostCosecha.put("XTRATA", " ");
    prodcutsPostCosecha.put("NLARGE", " ");
    prodcutsPostCosecha.put("MERTEC", " ");
    prodcutsPostCosecha.put("SASTIFAR", " ");
    prodcutsPostCosecha.put("Alumbre", " ");
    prodcutsPostCosecha.put("BC100", " ");
    prodcutsPostCosecha.put("ECLIPSE", " ");
    prodcutsPostCosecha.put("GIB-BEX", " ");
    prodcutsPostCosecha.put("BIOTTOL", " ");
    prodcutsPostCosecha.put("ACIDO CITRICO", " ");
    prodcutsPostCosecha.put("Otro  (especifique)", " "); //aqui haremos una exp[ecion mora etse ultimo..
    prodcutsPostCosecha.put("Cantidad otro", ""); //aqui haremos una exp[ecion mora etse ultimo..
    prodcutsPostCosecha.put("IformePertenece", ""); //aqui haremos una exp[ecion mora etse ultimo..


return prodcutsPostCosecha;



}


/**TODO LO QUE ES OPCIONAL MEJOR LOP GUARDAMOS EN UN HASHMAP*/


//
}
