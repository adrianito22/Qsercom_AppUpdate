package com.tiburela.qsercom.models;

import com.google.firebase.database.Exclude;
import com.tiburela.qsercom.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class ImagenReport {
  //  public static ArrayList<ImagenReport> listImagesData;

   public static  HashMap<String, ImagenReport> hashMapImagesData = new HashMap<String, ImagenReport>();
    public String getUniqueIdNamePic() {
        return uniqueIdNamePic;
    }
    private String uniqueIdNamePic;
    private String descripcionImagen;
    private String uriImageString;
    private int tipoImagenCategory;
    private String idReportePerteence;
    private boolean estaENPdf;

    public String getUrlStoragePic() {
        return urlStoragePic;
    }

    public void setUrlStoragePic(String urlStoragePic) {
        this.urlStoragePic = urlStoragePic;
    }

    private String  urlStoragePic;




    public void setUniqueIdNamePic(String uniqueIdNamePic) {
        this.uniqueIdNamePic = uniqueIdNamePic;
    }

    public String getUriImageString() {
        return uriImageString;
    }

    public void setUriImageString(String uriImageString) {
        this.uriImageString = uriImageString;
    }

    public String getIdReportePerteence() {
        return idReportePerteence;
    }

    public void setIdReportePerteence(String idReportePerteence) {
        this.idReportePerteence = idReportePerteence;
    }

    public String getHorientacionImage() {
        return horientacionImage;
    }

    public void setHorientacionImage(String horientacionImage) {
        this.horientacionImage = horientacionImage;
    }

    private String horientacionImage;

    public int getTipoImagenCategory() {
        return tipoImagenCategory;
    }

    public void setTipoImagenCategory(int tipoImagenCategory) {
        this.tipoImagenCategory = tipoImagenCategory;
    }





    public String getDescripcionImagen() {
        return descripcionImagen;
    }

    public void setDescripcionImagen(String descripcionImagen) {
        this.descripcionImagen = descripcionImagen;
    }

    public String geturiImage() {
        return uriImageString;
    }


    public boolean isEstaENPdf() {
        return estaENPdf;
    }

    public void setEstaENPdf(boolean estaENPdf) {
        this.estaENPdf = estaENPdf;
    }

    /*
        public void seturiImage(int uriImage) {
            this.uriImage = uriImage;
        }
    */
    public ImagenReport(String descripcionImagen, String uriImage, int tipoImagenCategory,String uniqueIdNamePic,String horientacionImage ) {
        this.descripcionImagen = descripcionImagen;
        this.uriImageString = uriImage;
        this.tipoImagenCategory=tipoImagenCategory;
        idReportePerteence=idReportePerteence="";
        this.uniqueIdNamePic = uniqueIdNamePic;
        this.horientacionImage=horientacionImage;
        estaENPdf=false;
        urlStoragePic="";
    }


public ImagenReport(){


    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("descripcionImagen", descripcionImagen);
        result.put("uriImageString", uriImageString);
        result.put("tipoImagenCategory", tipoImagenCategory);
        result.put("uniqueIdNamePic", uniqueIdNamePic);
        result.put("idReportePerteence", idReportePerteence);

        result.put("horientacionImage", horientacionImage);
        result.put("estaENPdf", estaENPdf);
        result.put("urlStoragePic", urlStoragePic);

        return result;

    }




}
