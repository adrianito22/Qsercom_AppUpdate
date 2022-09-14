package com.tiburela.qsercom.models;

import android.net.Uri;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ImagenReport {
    
  //  public static ArrayList<ImagenReport> listImagesData;

   public static  HashMap<String, ImagenReport> hashMapImagesData = new HashMap<String, ImagenReport>();



    public String getUniqueId() {
        return UniqueId;
    }
    private String UniqueId;
    private String descripcionImagen;
    private String uriImageString;
    private int tipoImagenCategory;
    private String idReportePerteence;

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
        return uriImageString
                ;
    }


    /*
    public void seturiImage(int uriImage) {
        this.uriImage = uriImage;
    }
*/
    public ImagenReport(String title, String uriImage, int tipoImagenCategory,String idReportePerteence ) {
        this.descripcionImagen = title;
        this.uriImageString = uriImage;
        this.tipoImagenCategory=tipoImagenCategory;
        this.idReportePerteence=idReportePerteence;
        UniqueId= java.util.UUID.randomUUID().toString();

    }



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("descripcionImagen", descripcionImagen);
        result.put("uriImageString", uriImageString);
        result.put("tipoImagenCategory", tipoImagenCategory);
        result.put("UniqueId", UniqueId);

        return result;

    }

}
