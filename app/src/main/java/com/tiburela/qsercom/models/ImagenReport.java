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
    public ImagenReport(String descripcionImagen, String uriImage, int tipoImagenCategory,String idReportePerteence,String uniqueIdNamePic ) {
        this.descripcionImagen = descripcionImagen;
        this.uriImageString = uriImage;
        this.tipoImagenCategory=tipoImagenCategory;
        this.idReportePerteence=idReportePerteence;
        this.uniqueIdNamePic = uniqueIdNamePic;


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

        return result;

    }




}
