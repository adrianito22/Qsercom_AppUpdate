package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InformEmbarque {


    /***Propiedades del informe*/

    private String codeInforme;
    private boolean esVisually; //si aun se puede visualizar
    private boolean esEditableNow; //si aun se puede editar
    private int  stateInformacion; //si es borrador
    private long fechaCreacionInf;


    public InformEmbarque(String codeInforme, int ediNhojaEvaluacion, String zona, String productor, String codigo, String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte, String ntargetaEmbarque, String inscirpMagap, String horaInicio, String horaTermino, String semana, String empacadora, String contenedor, String cbservacion) {
        this.codeInforme = codeInforme;
        esVisually = true;
        esEditableNow = true;
        stateInformacion = 1;
        fechaCreacionInf = new Date().getTime();
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
        this.cbservacion = cbservacion;
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
    private String cbservacion;



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

    public String getCbservacion() {
        return cbservacion;
    }

    public void setCbservacion(String cbservacion) {
        this.cbservacion = cbservacion;
    }




    //el informe un informe normal...
    //si queremos hacer otro informe lo podemos eredar





    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
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
        result.put("cbservacion", cbservacion);


        return result;




    }




}
