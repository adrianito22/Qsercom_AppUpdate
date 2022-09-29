package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class SetInformEmbarque2 {

    /**SELLOS INSTALADOS*/
    private String termografo1;
    private String termografo2;
    private String termografo1HoraEncendido;
    private String termografo2HoraEncendido;
    private String ubicacionPalletN1;

    private  String uniqueIDinforme;
    private String ubicacionPalletN2;
    private String rumaPalletN1;
    private String rumaPalletN2;
    private String candadoQsercom;
    private String selloNaviera;
    private String cableNaviera;

    private String selloPlastico;
    private String candadoBotella;
    private String cableExportadora;
    private String selloAdhesivoExportadora;
    private String selloAdhesivoNaviera;
    private String otrosSellosEspecif;


    /**Datos del transportista*/
    private String companiaTranporte;
    private String nombreChofer;
    private String cedulaChofer;
    private String celularChofer;
    private String placaChofer;
    private String marcaCaebzalChofer;
    private String colorCAbezal;


    /**DATOS DEL PROCESO*/
    private String condicionBalanza;
    private String tipoCaja;
    private boolean hayBalanza;
    private boolean hayExcelnsuchado;
    private String tipoPlastico;
    private String tipoDeBalanza;
    private String tipoDeBalanzaRepeso;

    public String getKeyFirebase() {
        return keyFirebase;
    }

    public void setKeyFirebase(String keyFirebase) {
        this.keyFirebase = keyFirebase;
    }

    private String  keyFirebase;


    public String getTipoDeBalanza() {
        return tipoDeBalanza;
    }

    public void setTipoDeBalanza(String tipoDeBalanza) {
        this.tipoDeBalanza = tipoDeBalanza;
    }

    public String getTipoDeBalanzaRepeso() {
        return tipoDeBalanzaRepeso;
    }

    public void setTipoDeBalanzaRepeso(String tipoDeBalanzaRepeso) {
        this.tipoDeBalanzaRepeso = tipoDeBalanzaRepeso;
    }


    private boolean hayBalanzaRepeso;
    private String ubicacionBalanza;





    public String getTermografo1() {
        return termografo1;
    }

    public void setTermografo1(String termografo1) {
        this.termografo1 = termografo1;
    }

    public String getTermografo2() {
        return termografo2;
    }

    public void setTermografo2(String termografo2) {
        this.termografo2 = termografo2;
    }

    public String getTermografo1HoraEncendido() {
        return termografo1HoraEncendido;
    }

    public void setTermografo1HoraEncendido(String termografo1HoraEncendido) {
        this.termografo1HoraEncendido = termografo1HoraEncendido;
    }

    public String getTermografo2HoraEncendido() {
        return termografo2HoraEncendido;
    }

    public void setTermografo2HoraEncendido(String termografo2HoraEncendido) {
        this.termografo2HoraEncendido = termografo2HoraEncendido;
    }

    public String getUbicacionPalletN1() {
        return ubicacionPalletN1;
    }

    public void setUbicacionPalletN1(String ubicacionPalletN1) {
        this.ubicacionPalletN1 = ubicacionPalletN1;
    }

    public String getUbicacionPalletN2() {
        return ubicacionPalletN2;
    }

    public void setUbicacionPalletN2(String ubicacionPalletN2) {
        this.ubicacionPalletN2 = ubicacionPalletN2;
    }

    public String getRumaPalletN1() {
        return rumaPalletN1;
    }

    public void setRumaPalletN1(String rumaPalletN1) {
        this.rumaPalletN1 = rumaPalletN1;
    }

    public String getRumaPalletN2() {
        return rumaPalletN2;
    }

    public void setRumaPalletN2(String rumaPalletN2) {
        this.rumaPalletN2 = rumaPalletN2;
    }

    public String getCandadoQsercom() {
        return candadoQsercom;
    }

    public void setCandadoQsercom(String candadoQsercom) {
        this.candadoQsercom = candadoQsercom;
    }

    public String getSelloNaviera() {
        return selloNaviera;
    }

    public void setSelloNaviera(String selloNaviera) {
        this.selloNaviera = selloNaviera;
    }

    public String getCableNaviera() {
        return cableNaviera;
    }

    public void setCableNaviera(String cableNaviera) {
        this.cableNaviera = cableNaviera;
    }

    public String getSelloPlastico() {
        return selloPlastico;
    }

    public void setSelloPlastico(String selloPlastico) {
        this.selloPlastico = selloPlastico;
    }

    public String getCandadoBotella() {
        return candadoBotella;
    }

    public void setCandadoBotella(String candadoBotella) {
        this.candadoBotella = candadoBotella;
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

    public String getOtrosSellosEspecif() {
        return otrosSellosEspecif;
    }

    public void setOtrosSellosEspecif(String otrosSellosEspecif) {
        this.otrosSellosEspecif = otrosSellosEspecif;
    }

    public String getCompaniaTranporte() {
        return companiaTranporte;
    }

    public void setCompaniaTranporte(String companiaTranporte) {
        this.companiaTranporte = companiaTranporte;
    }

    public String getNombreChofer() {
        return nombreChofer;
    }

    public void setNombreChofer(String nombreChofer) {
        this.nombreChofer = nombreChofer;
    }

    public String getCedulaChofer() {
        return cedulaChofer;
    }

    public void setCedulaChofer(String cedulaChofer) {
        this.cedulaChofer = cedulaChofer;
    }

    public String getCelularChofer() {
        return celularChofer;
    }

    public void setCelularChofer(String celularChofer) {
        this.celularChofer = celularChofer;
    }

    public String getPlacaChofer() {
        return placaChofer;
    }

    public void setPlacaChofer(String placaChofer) {
        this.placaChofer = placaChofer;
    }

    public String getMarcaCaebzalChofer() {
        return marcaCaebzalChofer;
    }

    public void setMarcaCaebzalChofer(String marcaCaebzalChofer) {
        this.marcaCaebzalChofer = marcaCaebzalChofer;
    }

    public String getColorCAbezal() {
        return colorCAbezal;
    }

    public void setColorCAbezal(String colorCAbezal) {
        this.colorCAbezal = colorCAbezal;
    }

    public String getCondicionBalanza() {
        return condicionBalanza;
    }

    public void setCondicionBalanza(String condicionBalanza) {
        this.condicionBalanza = condicionBalanza;
    }

    public String getTipoCaja() {
        return tipoCaja;
    }

    public void setTipoCaja(String tipoCaja) {
        this.tipoCaja = tipoCaja;
    }

    public boolean isHayBalanza() {
        return hayBalanza;
    }

    public void setHayBalanza(boolean hayBalanza) {
        this.hayBalanza = hayBalanza;
    }

    public boolean isHayExcelnsuchado() {
        return hayExcelnsuchado;
    }

    public void setHayExcelnsuchado(boolean hayExcelnsuchado) {
        this.hayExcelnsuchado = hayExcelnsuchado;
    }

    public String getTipoPlastico() {
        return tipoPlastico;
    }

    public void setTipoPlastico(String tipoPlastico) {
        this.tipoPlastico = tipoPlastico;
    }

    public boolean getHayBalanzaRepeso() {
        return hayBalanzaRepeso;
    }

    public void setHayBalanzaRepeso(boolean hayBalanzaRepeso) {
        this.hayBalanzaRepeso = hayBalanzaRepeso;
    }

    public String getUbicacionBalanza() {
        return ubicacionBalanza;
    }

    public void setUbicacionBalanza(String ubicacionBalanza) {
        this.ubicacionBalanza = ubicacionBalanza;
    }


    public String getUniqueIDinforme() {
        return uniqueIDinforme;
    }

    public SetInformEmbarque2() {


    }

    public SetInformEmbarque2(String uniqueIDinforme ,String termografo1, String termografo2, String termografo1HoraEncendido,
                              String termografo2HoraEncendido, String ubicacionPalletN1,
                              String ubicacionPalletN2, String rumaPalletN1, String rumaPalletN2,
                              String candadoQsercom, String selloNaviera, String cableNaviera,
                              String selloPlastico, String candadoBotella, String cableExportadora,
                              String selloAdhesivoExportadora, String selloAdhesivoNaviera, String otrosSellosEspecif,
                              String companiaTranporte, String nombreChofer, String cedulaChofer, String celularChofer,
                              String placaChofer, String marcaCaebzalChofer, String colorCAbezal, String condicionBalanza,
                              String tipoCaja, boolean hayBalanza, boolean hayExcelnsuchado, String tipoPlastico,
                              boolean hayBalanzaRepeso, String ubicacionBalanza,String tipoDeBalanza,String tipoDeBalanzaRepeso) {


        this.uniqueIDinforme = uniqueIDinforme;
        this.termografo1 = termografo1;
        this.termografo2 = termografo2;
        this.termografo1HoraEncendido = termografo1HoraEncendido;
        this.termografo2HoraEncendido = termografo2HoraEncendido;
        this.ubicacionPalletN1 = ubicacionPalletN1;
        this.ubicacionPalletN2 = ubicacionPalletN2;
        this.rumaPalletN1 = rumaPalletN1;
        this.rumaPalletN2 = rumaPalletN2;
        this.candadoQsercom = candadoQsercom;
        this.selloNaviera = selloNaviera;
        this.cableNaviera = cableNaviera;
        this.selloPlastico = selloPlastico;
        this.candadoBotella = candadoBotella;
        this.cableExportadora = cableExportadora;
        this.selloAdhesivoExportadora = selloAdhesivoExportadora;
        this.selloAdhesivoNaviera = selloAdhesivoNaviera;
        this.otrosSellosEspecif = otrosSellosEspecif;
        this.companiaTranporte = companiaTranporte;
        this.nombreChofer = nombreChofer;
        this.cedulaChofer = cedulaChofer;
        this.celularChofer = celularChofer;
        this.placaChofer = placaChofer;
        this.marcaCaebzalChofer = marcaCaebzalChofer;
        this.colorCAbezal = colorCAbezal;
        this.condicionBalanza = condicionBalanza;
        this.tipoCaja = tipoCaja;
        this.hayBalanza = hayBalanza;
        this.hayExcelnsuchado = hayExcelnsuchado;
        this.tipoPlastico = tipoPlastico;
        this.hayBalanzaRepeso = hayBalanzaRepeso;
        this.ubicacionBalanza = ubicacionBalanza;
        this.tipoDeBalanza = tipoDeBalanza;
        this.tipoDeBalanzaRepeso = tipoDeBalanzaRepeso;
        keyFirebase="";
    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uniqueIDinforme", uniqueIDinforme);
        result.put("termografo1", termografo1);
        result.put("termografo2", termografo2);
        result.put("termografo1HoraEncendido", termografo1HoraEncendido);
        result.put("termografo2HoraEncendido", termografo2HoraEncendido);
        result.put("ubicacionPalletN1", ubicacionPalletN1);
        result.put("ubicacionPalletN2", ubicacionPalletN2);
        result.put("rumaPalletN1", rumaPalletN1);
        result.put("rumaPalletN2", rumaPalletN2);
        result.put("candadoQsercom", candadoQsercom);
        result.put("selloNaviera", selloNaviera);
        result.put("cableNaviera", cableNaviera);
        result.put("selloPlastico", selloPlastico);
        result.put("candadoBotella", candadoBotella);
        result.put("cableExportadora", cableExportadora);
        result.put("selloAdhesivoExportadora", selloAdhesivoExportadora);
        result.put("selloAdhesivoNaviera", selloAdhesivoNaviera);


        result.put("companiaTranporte", companiaTranporte);
        result.put("nombreChofer", nombreChofer);
        result.put("otrosSellosEspecif", otrosSellosEspecif);
        result.put("cedulaChofer", cedulaChofer);
        result.put("celularChofer", celularChofer);
        result.put("placaChofer", placaChofer);
        result.put("marcaCaebzalChofer", marcaCaebzalChofer);
        result.put("colorCAbezal", colorCAbezal);


        result.put("condicionBalanza", condicionBalanza);
        result.put("tipoCaja", tipoCaja);
        result.put("hayBalanza", hayBalanza);
        result.put("hayExcelnsuchado", hayExcelnsuchado);
        result.put("tipoPlastico", tipoPlastico);
        result.put("hayBalanzaRepeso", hayBalanzaRepeso);
        result.put("ubicacionBalanza", ubicacionBalanza);

        result.put("tipoDeBalanza", tipoDeBalanza);
        result.put("tipoDeBalanzaRepeso", tipoDeBalanzaRepeso);
        result.put("keyFirebase", keyFirebase);

        return result;


    }
}
