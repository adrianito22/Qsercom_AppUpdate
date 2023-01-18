package com.tiburela.qsercom.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ReportCamionesyCarretas {

    public static HashMap<String, String> prodcutsPostCosecha;

    /***Propiedades del informe*/

    private String codeInforme;
    private String simpleDataFormat;
    private boolean esVisually; //si aun se puede visualizar
    private boolean esEditableNow; //si aun se puede editar
    private int  stateInformacion; //si es borrador
    private long fechaCreacionInf;
    private String  numcionContenedor;

    public String getKeyOrNodeLibriadoSiEs() {
        return keyOrNodeLibriadoSiEs;
    }

    public void setKeyOrNodeLibriadoSiEs(String keyOrNodeLibriadoSiEs) {
        this.keyOrNodeLibriadoSiEs = keyOrNodeLibriadoSiEs;
    }

    public void setClienteReporte(String clienteReporte) {
        this.clienteReporte = clienteReporte;
    }

    private String keyOrNodeLibriadoSiEs;

    public String getClienteReporte() {
        return clienteReporte;
    }

    private String clienteReporte;

    public String getNodoQueContieneMapPesoBrutoCloster2y3l() {
        return nodoQueContieneMapPesoBrutoCloster2y3l;
    }

    public void setNodoQueContieneMapPesoBrutoCloster2y3l(String nodoQueContieneMapPesoBrutoCloster2y3l) {
        this.nodoQueContieneMapPesoBrutoCloster2y3l = nodoQueContieneMapPesoBrutoCloster2y3l;
    }

    public String getUbicacionBalanza() {
        return ubicacionBalanza;
    }

    public void setUbicacionBalanza(String ubicacionBalanza) {
        this.ubicacionBalanza = ubicacionBalanza;
    }

    private String nodoQueContieneMapPesoBrutoCloster2y3l;


    //datos transportista
    private String nombredeChofer;

    public String getNombredeChofer() {
        return nombredeChofer;
    }

    public void setNombredeChofer(String nombredeChofer) {
        this.nombredeChofer = nombredeChofer;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCandadoQsercom() {
        return CandadoQsercom;
    }

    public void setCandadoQsercom(String candadoQsercom) {
        CandadoQsercom = candadoQsercom;
    }

    public String getTipoDePlastico() {
        return tipoDePlastico;
    }

    public void setTipoDePlastico(String tipoDePlastico) {
        this.tipoDePlastico = tipoDePlastico;
    }

    public String getTipoDeCaja() {
        return tipoDeCaja;
    }

    public void setTipoDeCaja(String tipoDeCaja) {
        this.tipoDeCaja = tipoDeCaja;
    }

    public boolean isHayEnsunchado() {
        return hayEnsunchado;
    }

    public void setHayEnsunchado(boolean hayEnsunchado) {
        this.hayEnsunchado = hayEnsunchado;
    }

    public boolean isHayBalanza() {
        return hayBalanza;
    }

    public void setHayBalanza(boolean hayBalanza) {
        this.hayBalanza = hayBalanza;
    }

    public String getCondicionBalanza() {
        return condicionBalanza;
    }

    public void setCondicionBalanza(String condicionBalanza) {
        this.condicionBalanza = condicionBalanza;
    }

    public String getTipoBalanza() {
        return tipoBalanza;
    }

    public void setTipoBalanza(String tipoBalanza) {
        this.tipoBalanza = tipoBalanza;
    }

    public boolean isHayBalanzaRepesa() {
        return hayBalanzaRepesa;
    }

    public void setHayBalanzaRepesa(boolean hayBalanzaRepesa) {
        this.hayBalanzaRepesa = hayBalanzaRepesa;
    }

    public String getTipoBalanzaRepesa() {
        return tipoBalanzaRepesa;
    }

    public void setTipoBalanzaRepesa(String tipoBalanzaRepesa) {
        this.tipoBalanzaRepesa = tipoBalanzaRepesa;
    }

    public String getFuenteAgua() {
        return fuenteAgua;
    }

    public void setFuenteAgua(String fuenteAgua) {
        this.fuenteAgua = fuenteAgua;
    }

    public boolean isHayAguaCorrida() {
        return hayAguaCorrida;
    }

    public void setHayAguaCorrida(boolean hayAguaCorrida) {
        this.hayAguaCorrida = hayAguaCorrida;
    }

    public boolean isLavadoRacimos() {
        return lavadoRacimos;
    }

    public void setLavadoRacimos(boolean lavadoRacimos) {
        this.lavadoRacimos = lavadoRacimos;
    }

    public String getFumigacionClin1() {
        return fumigacionClin1;
    }

    public void setFumigacionClin1(String fumigacionClin1) {
        this.fumigacionClin1 = fumigacionClin1;
    }

    public int getRacimosCosechados() {
        return racimosCosechados;
    }

    public void setRacimosCosechados(int racimosCosechados) {
        this.racimosCosechados = racimosCosechados;
    }

    public int getRacimosRechazados() {
        return racimosRechazados;
    }

    public void setRacimosRechazados(int racimosRechazados) {
        this.racimosRechazados = racimosRechazados;
    }

    public int getRacimosProcesados() {
        return racimosProcesados;
    }

    public void setRacimosProcesados(int racimosProcesados) {
        this.racimosProcesados = racimosProcesados;
    }

    public int getCajasProcesadasDespachadas() {
        return cajasProcesadasDespachadas;
    }

    public void setCajasProcesadasDespachadas(int cajasProcesadasDespachadas) {
        this.cajasProcesadasDespachadas = cajasProcesadasDespachadas;
    }

    public String getColortSem14() {
        return colortSem14;
    }

    public void setColortSem14(String colortSem14) {
        this.colortSem14 = colortSem14;
    }

    public String getColortSem13() {
        return colortSem13;
    }

    public void setColortSem13(String colortSem13) {
        this.colortSem13 = colortSem13;
    }

    public String getColortSem12() {
        return colortSem12;
    }

    public void setColortSem12(String colortSem12) {
        this.colortSem12 = colortSem12;
    }

    public String getColortSem11() {
        return colortSem11;
    }

    public void setColortSem11(String colortSem11) {
        this.colortSem11 = colortSem11;
    }

    public String getColortSem10() {
        return colortSem10;
    }

    public void setColortSem10(String colortSem10) {
        this.colortSem10 = colortSem10;
    }

    public String getColortSem9() {
        return colortSem9;
    }

    public void setColortSem9(String colortSem9) {
        this.colortSem9 = colortSem9;
    }

    public String getNumRcim14() {
        return numRcim14;
    }

    public void setNumRcim14(String numRcim14) {
        this.numRcim14 = numRcim14;
    }

    public String getNumRcim13() {
        return numRcim13;
    }

    public void setNumRcim13(String numRcim13) {
        this.numRcim13 = numRcim13;
    }

    public String getNumRcim12() {
        return numRcim12;
    }

    public void setNumRcim12(String numRcim12) {
        this.numRcim12 = numRcim12;
    }

    public String getNumRcim11() {
        return numRcim11;
    }

    public void setNumRcim11(String numRcim11) {
        this.numRcim11 = numRcim11;
    }

    public String getNumRcim10() {
        return numRcim10;
    }

    public void setNumRcim10(String numRcim10) {
        this.numRcim10 = numRcim10;
    }

    public String getNumRcim9() {
        return numRcim9;
    }

    public void setNumRcim9(String numRcim9) {
        this.numRcim9 = numRcim9;
    }

    public String getExtensionistaEnCalidad() {
        return extensionistaEnCalidad;
    }

    public void setExtensionistaEnCalidad(String extensionistaEnCalidad) {
        this.extensionistaEnCalidad = extensionistaEnCalidad;
    }

    public String getExtensionistaEnRodillo() {
        return extensionistaEnRodillo;
    }

    public void setExtensionistaEnRodillo(String extensionistaEnRodillo) {
        this.extensionistaEnRodillo = extensionistaEnRodillo;
    }

    public String getExtensionistaEnGancho() {
        return extensionistaEnGancho;
    }

    public void setExtensionistaEnGancho(String extensionistaEnGancho) {
        this.extensionistaEnGancho = extensionistaEnGancho;
    }

    private String cedula;
    private String celular;
    private String placa;
    private String CandadoQsercom;


    private String tipoDePlastico;
    private String tipoDeCaja;
    private boolean hayEnsunchado;
    private boolean hayBalanza;
    private String condicionBalanza;
    private String tipoBalanza;
    private boolean  hayBalanzaRepesa;
    private String tipoBalanzaRepesa;
    private String fuenteAgua;
    private boolean hayAguaCorrida;
    private boolean lavadoRacimos;
    private String fumigacionClin1;
    private int racimosCosechados;
    private int racimosRechazados;
    private int racimosProcesados;
    private int cajasProcesadasDespachadas;
    private String colortSem14;
    private String colortSem13;
    private String colortSem12;
    private String colortSem11;
    private String colortSem10;
    private String colortSem9;
    private String numRcim14;
    private String numRcim13;
    private String numRcim12;
    private String numRcim11;
    private String numRcim10;
    private String numRcim9;
    private String extensionistaEnCalidad;
    private String extensionistaEnRodillo;
    private String extensionistaEnGancho;
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

    String calidadCi;
    String extRodilloCi;
    String ganchoCi;
    private String ubicacionBalanza;


    private String uniqueIDinforme;

    public String getKeyFirebase() {
        return keyFirebase;
    }

    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    private String keyFirebase;

    public ReportCamionesyCarretas() {


    }


    public String getNumcionContenedor() {
        return numcionContenedor;
    }

    public void setNumcionContenedor(String numcionContenedor) {
        this.numcionContenedor = numcionContenedor;
    }

    public String getCalidadCi() {
        return calidadCi;
    }

    public void setCalidadCi(String calidadCi) {
        this.calidadCi = calidadCi;
    }

    public String getExtRodilloCi() {
        return extRodilloCi;
    }

    public void setExtRodilloCi(String extRodilloCi) {
        this.extRodilloCi = extRodilloCi;
    }

    public String getGanchoCi() {
        return ganchoCi;
    }

    public void setGanchoCi(String ganchoCi) {
        this.ganchoCi = ganchoCi;
    }



    public ReportCamionesyCarretas(String  uniqueIDinforme , String codeInforme, int ediNhojaEvaluacion, String zona, String productor,
                                   String codigo, String pemarque, String nguiaRemision, String hacienda, String _nguia_transporte,
                                   String ntargetaEmbarque, String inscirpMagap, String horaInicio, String horaTermino, String semana,
                                   String empacadora,

                                   String nombredeChofer, String cedula, String celular, String placa, String CandadoQsercom,

                                   String tipoDePlastico, String tipoDeCaja, boolean hayEnsunchado, boolean hayBalanza, String condicionBalanza, String tipoBalanza,
                                   boolean  hayBalanzaRepesa, String tipoBalanzaRepesa,


                                   String fuenteAgua, boolean hayAguaCorrida, boolean lavadoRacimos, String fumigacionClin1,

                                   int racimosCosechados, int racimosRechazados, int racimosProcesados, int cajasProcesadasDespachadas,
                                   String extensionistaEnCalidad, String extensionistaEnRodillo, String extensionistaEnGancho
                                   , String calidadCi, String extRodilloCi, String ganchoCi, String observacionOpc,String nodoQueContieneMapPesoBrutoCloster2y3l
                                   ,String clienteReporte) {

        keyFirebase="";
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

        this. nombredeChofer=nombredeChofer;
        this. cedula=cedula;
        this. celular=celular;
        this. placa=placa;
        this. CandadoQsercom=CandadoQsercom;
        this.tipoDePlastico=tipoDePlastico;
        this.tipoDeCaja=tipoDeCaja;
        this.hayEnsunchado=hayEnsunchado;
        this.hayBalanza=hayBalanza;
        this.condicionBalanza=condicionBalanza;
        this.tipoBalanza=tipoBalanza;
        this.hayBalanzaRepesa=hayBalanzaRepesa;
        this.tipoBalanzaRepesa=tipoBalanzaRepesa;
        this.fuenteAgua=fuenteAgua;
        this.hayAguaCorrida=hayAguaCorrida;
        this.lavadoRacimos=lavadoRacimos;
        this.fumigacionClin1=fumigacionClin1;
        this.racimosCosechados=racimosCosechados;
        this.racimosRechazados=racimosRechazados;
        this.racimosProcesados=racimosProcesados;
        this.cajasProcesadasDespachadas=cajasProcesadasDespachadas;
        this.extensionistaEnCalidad=extensionistaEnCalidad;
        this.extensionistaEnRodillo=extensionistaEnRodillo;
        this.extensionistaEnGancho=extensionistaEnGancho;
        this.calidadCi=calidadCi;
        this.extRodilloCi=extRodilloCi;
        this.ganchoCi=ganchoCi;
        this.observacionOpc=observacionOpc;
        this.nodoQueContieneMapPesoBrutoCloster2y3l=nodoQueContieneMapPesoBrutoCloster2y3l;
        this.clienteReporte=clienteReporte;

        keyOrNodeLibriadoSiEs="";

    }

    /***Datos formulario */


    private String contenedor;
    private String observacionOpc;


    public static HashMap<String, String> getProdcutsPostCosecha() {
        return prodcutsPostCosecha;
    }

    public static void setProdcutsPostCosecha(HashMap<String, String> prodcutsPostCosecha) {
        ReportCamionesyCarretas.prodcutsPostCosecha = prodcutsPostCosecha;
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

    public String getObservacionOpc() {
        return observacionOpc;
    }

    public void setObservacionOpc(String observacionOpc) {
        this.observacionOpc = observacionOpc;
    }




    //el informe un informe normal...
    //si queremos hacer otro informe lo podemos eredar






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
