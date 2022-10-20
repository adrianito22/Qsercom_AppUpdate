package com.tiburela.qsercom.models;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContenedoresEnAcopio {


    public ContenedoresEnAcopio(){


    }


    public void setFechaUploadMilliseconds(double fechaUploadMilliseconds) {
        this.fechaUploadMilliseconds = fechaUploadMilliseconds;
    }

    public void setSimpleDataFormat(String simpleDataFormat) {
        this.simpleDataFormat = simpleDataFormat;
    }

    public String getDatosProcesoContenAcopioKEYFather() {
        return datosProcesoContenAcopioKEYFather;
    }

    public void setDatosProcesoContenAcopioKEYFather(String datosProcesoContenAcopioKEYFather) {
        this.datosProcesoContenAcopioKEYFather = datosProcesoContenAcopioKEYFather;
    }

    public ContenedoresEnAcopio(String uniqueIDinforme, String fechaInicio, String fechadeTermino, String exportSolicitante,
                                String exportProcesada, String puerto, String zona, String marca, String horaInicio,
                                String horaDetermino, String guiaDeRemision, String tarjaDeEmbarque, String destino,
                                String vapor, String numContenedor, String horaDeLlegada, String horaDeSalida,
                                String agenciaNaviera, String sellosPlasticoNaviera, String stickerDeVentolExternn1,
                                String numSerieFunda, String cableRastreoLlegada, String booking, String maxGross,
                                String tare, String otrosCandados, String termografoN1, String termogragoN2, String candadoDeQsercon,
                                String selloDeNaviera, String cableDeNaviera, String selloPlastico, String candadodeBotella,
                                String cableExportadora, String selloAdhesivoExportadora, String selloAdhesivoNaviera,
                                String otrosSellos, String companiaTranportista, String nombredeChofer, String cedula,
                                String celular, String placa, String marcaCabezal, String colorCabezal,

                                 int cajasProcesadasDespachadas, String inspectorAcopio, int cedulaIdenti) {


        this.uniqueIDinforme = uniqueIDinforme;
        this.fechaInicio = fechaInicio;
        this.fechadeTermino = fechadeTermino;
        this.exportSolicitante = exportSolicitante;
        this.exportProcesada = exportProcesada;
        this.puerto = puerto;
        this.zona = zona;
        this.marca = marca;
        this.horaInicio = horaInicio;
        this.horaDetermino = horaDetermino;
        this.guiaDeRemision = guiaDeRemision;
        this.tarjaDeEmbarque = tarjaDeEmbarque;
        keyFirebase="";
        this.destino = destino;
        this.vapor = vapor;
        this.numContenedor = numContenedor;
        this.horaDeLlegada = horaDeLlegada;
        this.horaDeSalida = horaDeSalida;
        this.agenciaNaviera = agenciaNaviera;
        this.sellosPlasticoNaviera = sellosPlasticoNaviera;
        this.stickerDeVentolExternn1 = stickerDeVentolExternn1;
        this.numSerieFunda = numSerieFunda;
        this.cableRastreoLlegada = cableRastreoLlegada;
        this.booking = booking;
        this.maxGross = maxGross;
        this.tare = tare;
        this.otrosCandados = otrosCandados;
        this.termografoN1 = termografoN1;
        this.termogragoN2 = termogragoN2;
        this.candadoDeQsercon = candadoDeQsercon;
        this.selloDeNaviera = selloDeNaviera;
        this.cableDeNaviera = cableDeNaviera;
        this.selloPlastico = selloPlastico;
        this.candadodeBotella = candadodeBotella;
        this.cableExportadora = cableExportadora;
        this.selloAdhesivoExportadora = selloAdhesivoExportadora;
        this.selloAdhesivoNaviera = selloAdhesivoNaviera;
        this.otrosSellos = otrosSellos;
        this.companiaTranportista = companiaTranportista;
        this.nombredeChofer = nombredeChofer;
        this.cedula = cedula;
        this.celular = celular;
        this.placa = placa;
        this.marcaCabezal = marcaCabezal;
        this.colorCabezal = colorCabezal;
        fechaUploadMilliseconds =    new Date().getTime();
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        simpleDataFormat = formatter.format(fechaUploadMilliseconds);
        datosProcesoContenAcopioKEYFather="";

         this.cajasProcesadasDespachadas=cajasProcesadasDespachadas;
        this. inspectorAcopio=inspectorAcopio;
        this. cedulaIdenti=cedulaIdenti;


        }

    public double getFechaUploadMilliseconds() {
        return fechaUploadMilliseconds;
    }

    private double fechaUploadMilliseconds;

    private String fechaInicio;
private String fechadeTermino;

    public String getSimpleDataFormat() {
        return simpleDataFormat;
    }

    private String simpleDataFormat;
 private String datosProcesoContenAcopioKEYFather;

    public String getUniqueIDinforme() {
        return uniqueIDinforme;
    }

    public void setUniqueIDinforme(String uniqueIDinforme) {
        this.uniqueIDinforme = uniqueIDinforme;
    }

    private String uniqueIDinforme;
private String exportSolicitante;
    private String exportProcesada;
    private String  puerto;
    private String  zona;
    private String marca;
    private String horaInicio;
    private String horaDetermino;
    private String  guiaDeRemision;
    private String  tarjaDeEmbarque;
     private String keyFirebase;

    //datos del conteneedor
    private String destino;
    private String vapor;
    private String numContenedor;
    private String horaDeLlegada;
    private String horaDeSalida;
    private String agenciaNaviera;

    //sellos de llegada

    private String sellosPlasticoNaviera;
    private String stickerDeVentolExternn1;
    private String  numSerieFunda;
    private String  cableRastreoLlegada;
    private String  booking;
    private String  maxGross;
    private String  tare;
    private String  otrosCandados;

    //sellos instalados
    private String termografoN1;
    private String  termogragoN2;
    private String  candadoDeQsercon;
    private String  selloDeNaviera;
    private String  cableDeNaviera;
    private String  selloPlastico;
    private String  candadodeBotella;
    private String  cableExportadora;
    private String  selloAdhesivoExportadora;
    private String  selloAdhesivoNaviera;
     private String otrosSellos;

     //datos de transportista

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechadeTermino() {
        return fechadeTermino;
    }

    public void setFechadeTermino(String fechadeTermino) {
        this.fechadeTermino = fechadeTermino;
    }

    public String getExportSolicitante() {
        return exportSolicitante;
    }

    public void setExportSolicitante(String exportSolicitante) {
        this.exportSolicitante = exportSolicitante;
    }

    public String getExportProcesada() {
        return exportProcesada;
    }

    public void setExportProcesada(String exportProcesada) {
        this.exportProcesada = exportProcesada;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraDetermino() {
        return horaDetermino;
    }

    public void setHoraDetermino(String horaDetermino) {
        this.horaDetermino = horaDetermino;
    }

    public String getGuiaDeRemision() {
        return guiaDeRemision;
    }

    public void setGuiaDeRemision(String guiaDeRemision) {
        this.guiaDeRemision = guiaDeRemision;
    }

    public String getTarjaDeEmbarque() {
        return tarjaDeEmbarque;
    }

    public void setTarjaDeEmbarque(String tarjaDeEmbarque) {
        this.tarjaDeEmbarque = tarjaDeEmbarque;
    }

    public String getKeyFirebase() {
        return keyFirebase;
    }

    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getVapor() {
        return vapor;
    }

    public void setVapor(String vapor) {
        this.vapor = vapor;
    }

    public String getNumContenedor() {
        return numContenedor;
    }

    public void setNumContenedor(String numContenedor) {
        this.numContenedor = numContenedor;
    }

    public String getHoraDeLlegada() {
        return horaDeLlegada;
    }

    public void setHoraDeLlegada(String horaDeLlegada) {
        this.horaDeLlegada = horaDeLlegada;
    }

    public String getHoraDeSalida() {
        return horaDeSalida;
    }

    public void setHoraDeSalida(String horaDeSalida) {
        this.horaDeSalida = horaDeSalida;
    }

    public String getAgenciaNaviera() {
        return agenciaNaviera;
    }

    public void setAgenciaNaviera(String agenciaNaviera) {
        this.agenciaNaviera = agenciaNaviera;
    }

    public String getSellosPlasticoNaviera() {
        return sellosPlasticoNaviera;
    }

    public void setSellosPlasticoNaviera(String sellosPlasticoNaviera) {
        this.sellosPlasticoNaviera = sellosPlasticoNaviera;
    }

    public String getStickerDeVentolExternn1() {
        return stickerDeVentolExternn1;
    }

    public void setStickerDeVentolExternn1(String stickerDeVentolExternn1) {
        this.stickerDeVentolExternn1 = stickerDeVentolExternn1;
    }

    public String getNumSerieFunda() {
        return numSerieFunda;
    }

    public void setNumSerieFunda(String numSerieFunda) {
        this.numSerieFunda = numSerieFunda;
    }

    public String getCableRastreoLlegada() {
        return cableRastreoLlegada;
    }

    public void setCableRastreoLlegada(String cableRastreoLlegada) {
        this.cableRastreoLlegada = cableRastreoLlegada;
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

    public String getTare() {
        return tare;
    }

    public void setTare(String tare) {
        this.tare = tare;
    }

    public String getOtrosCandados() {
        return otrosCandados;
    }

    public void setOtrosCandados(String otrosCandados) {
        this.otrosCandados = otrosCandados;
    }

    public String getTermografoN1() {
        return termografoN1;
    }

    public void setTermografoN1(String termografoN1) {
        this.termografoN1 = termografoN1;
    }

    public String getTermogragoN2() {
        return termogragoN2;
    }

    public void setTermogragoN2(String termogragoN2) {
        this.termogragoN2 = termogragoN2;
    }

    public String getCandadoDeQsercon() {
        return candadoDeQsercon;
    }

    public void setCandadoDeQsercon(String candadoDeQsercon) {
        this.candadoDeQsercon = candadoDeQsercon;
    }

    public String getSelloDeNaviera() {
        return selloDeNaviera;
    }

    public void setSelloDeNaviera(String selloDeNaviera) {
        this.selloDeNaviera = selloDeNaviera;
    }

    public String getCableDeNaviera() {
        return cableDeNaviera;
    }

    public void setCableDeNaviera(String cableDeNaviera) {
        this.cableDeNaviera = cableDeNaviera;
    }

    public String getSelloPlastico() {
        return selloPlastico;
    }

    public void setSelloPlastico(String selloPlastico) {
        this.selloPlastico = selloPlastico;
    }

    public String getCandadodeBotella() {
        return candadodeBotella;
    }

    public void setCandadodeBotella(String candadodeBotella) {
        this.candadodeBotella = candadodeBotella;
    }

    public String getCableExportadora() {
        return cableExportadora;
    }

    public void setCableExportadora(String cableExportadora) {
        this.cableExportadora = cableExportadora;
    }

    public String getSelloAdhesivoExportadora() {
        return selloAdhesivoExportadora;
    }

    public void setSelloAdhesivoExportadora(String selloAdhesivoExportadora) {
        this.selloAdhesivoExportadora = selloAdhesivoExportadora;
    }

    public String getSelloAdhesivoNaviera() {
        return selloAdhesivoNaviera;
    }

    public void setSelloAdhesivoNaviera(String selloAdhesivoNaviera) {
        this.selloAdhesivoNaviera = selloAdhesivoNaviera;
    }

    public String getOtrosSellos() {
        return otrosSellos;
    }

    public void setOtrosSellos(String otrosSellos) {
        this.otrosSellos = otrosSellos;
    }

    public String getCompaniaTranportista() {
        return companiaTranportista;
    }

    public void setCompaniaTranportista(String companiaTranportista) {
        this.companiaTranportista = companiaTranportista;
    }

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

    public String getMarcaCabezal() {
        return marcaCabezal;
    }

    public void setMarcaCabezal(String marcaCabezal) {
        this.marcaCabezal = marcaCabezal;
    }

    public String getColorCabezal() {
        return colorCabezal;
    }

    public void setColorCabezal(String colorCabezal) {
        this.colorCabezal = colorCabezal;
    }

    private String companiaTranportista;
    private String nombredeChofer;
    private String cedula;
    private String celular;
    private String placa;
    private String marcaCabezal;
    private String colorCabezal;


    //3 values mas

   private int cajasProcesadasDespachadas;

    public int getCajasProcesadasDespachadas() {
        return cajasProcesadasDespachadas;
    }

    public void setCajasProcesadasDespachadas(int cajasProcesadasDespachadas) {
        this.cajasProcesadasDespachadas = cajasProcesadasDespachadas;
    }

    public String getInspectorAcopio() {
        return inspectorAcopio;
    }

    public void setInspectorAcopio(String inspectorAcopio) {
        this.inspectorAcopio = inspectorAcopio;
    }

    public int getCedulaIdenti() {
        return cedulaIdenti;
    }

    public void setCedulaIdenti(int cedulaIdenti) {
        this.cedulaIdenti = cedulaIdenti;
    }

    private String inspectorAcopio;
    private int cedulaIdenti;



}
