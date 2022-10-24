package com.tiburela.qsercom.PdfMaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.tiburela.qsercom.R;

import java.io.ByteArrayOutputStream;

public class HelperPdf {


    public  Image  createInfoImgtoPDF(Drawable mIDrawable, Context conetxt){
        mIDrawable= conetxt.getDrawable(R.drawable.logopdf);
          Bitmap miBitmap= ((BitmapDrawable)mIDrawable).getBitmap();
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
         miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
         byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

       Image image =new Image(imageData);
        image.setWidth(100f);


return  image;


    }


    public  Image  createInfoImgtoPDF(Bitmap miBitmap){

        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        miBitmap.compress(Bitmap.CompressFormat.PNG,100,stream1);
        byte[] bitmapData1= stream1.toByteArray();
        ImageData imageData= ImageDataFactory.create(bitmapData1);

        Image image =new Image(imageData);
        image.setWidth(100f);


        return  image;
    }


}
