package com.tiburela.qsercom.models;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.google.firebase.database.Exclude;
import com.tiburela.qsercom.utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class ImagenReport {
  //  public static ArrayList<ImagenReport> listImagesData;

   public static  HashMap<String, ImagenReport> hashMapImagesData = new HashMap<>();
    public String getUniqueIdNamePic() {
        return uniqueIdNamePic;
    }
    private String uniqueIdNamePic;
    private String descripcionImagen;
    private String uriImageString;
    private int tipoImagenCategory;
    private String idReportePerteence;

    public int getSortPositionImage() {
        return sortPositionImage;
    }

    public void setSortPositionImage(int sortPositionImage) {
        this.sortPositionImage = sortPositionImage;
    }

    private int sortPositionImage;

    public String getImagenPathNow() {
        return imagenPathNow;
    }

    public void setImagenPathNow(String imagenPathNow) {
        this.imagenPathNow = imagenPathNow;
    }

    private String imagenPathNow;

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
        idReportePerteence="";
        this.uniqueIdNamePic = uniqueIdNamePic;
        this.horientacionImage=horientacionImage;
        estaENPdf=false;
        urlStoragePic="";
        imagenPathNow="";
        sortPositionImage=Utils.NOPOSITION_DEFINIDA; //cuando no tenemos posicion..
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
        result.put("sortPositionImage", sortPositionImage);

        return result;

    }



    public static void updateIdPerteence(String idPerteenceAdd, HashMap<String, ImagenReport>map){
        for(ImagenReport reportImg: map.values()){

            String key=reportImg.getUniqueIdNamePic();
            reportImg.setIdReportePerteence(idPerteenceAdd);
            map.put(key,reportImg);

        }


    }


    public static  String getRealPathFromURI(Uri contentURI, Context context) {
        String result;
        Cursor cursor =context. getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
