package com.tiburela.qsercom.models;

import static com.google.gson.internal.bind.TypeAdapters.UUID;

import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class ImagenX {
    
    public static ArrayList<ImagenX> listImagesData;

   public static  HashMap<String, ImagenX> hashMapImagesData = new HashMap<String, ImagenX>();



    public String getUniqueId() {
        return UniqueId;
    }

    private String UniqueId;


    private String descripcionImagen;
    private Uri uriImage;
    private int tipoImagenCategory;

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

    public Uri geturiImage() {
        return uriImage;
    }


    /*
    public void seturiImage(int uriImage) {
        this.uriImage = uriImage;
    }
*/
    public ImagenX(String title, Uri uriImage,int tipoImagenCategory) {
        this.descripcionImagen = title;
        this.uriImage = uriImage;
        this.tipoImagenCategory=tipoImagenCategory;

        UniqueId= java.util.UUID.randomUUID().toString();

    }
}
