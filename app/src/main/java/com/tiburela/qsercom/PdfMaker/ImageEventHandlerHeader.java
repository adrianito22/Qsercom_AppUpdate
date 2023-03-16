package com.tiburela.qsercom.PdfMaker;

import com.google.firebase.storage.internal.Util;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.property.Background;
import com.tiburela.qsercom.utils.Utils;
import com.tiburela.qsercom.utils.Variables;

import java.io.Writer;

public class ImageEventHandlerHeader implements IEventHandler {
    protected Image img;


    protected Writer writer;
    protected Image backenter; //solo para contenedores en acopio

    protected Document midocumentotoAddData;


    public ImageEventHandlerHeader(Image img,Document midocumentotoAddData) {
        this.img = img;
         this.midocumentotoAddData= midocumentotoAddData;
    }


    public ImageEventHandlerHeader(Image img,Document midocumentotoAddData,Image backenter) {
        this.img = img;
        this.midocumentotoAddData= midocumentotoAddData;
        this.backenter=backenter;
    }



    /*
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas aboveCanvas = new PdfCanvas(page.newContentStreamAfter(),
                page.getResources(), pdfDoc);
        Rectangle area = page.getPageSize();
        img.scaleToFit(595f, 300f); //ESTA EN 400
        new Canvas(aboveCanvas, pdfDoc, area).add(img);
    }


*/


    private void addImage(){
        img.scaleToFit(595f, 200f); //ESTA EN 400 DESPUES 3300
        midocumentotoAddData.add(img);



    }

    private void addImage(Event evento){
        img.scaleToFit(595f, 200f); //ESTA EN 400 DESPUES 3300
        midocumentotoAddData.add(img);



        /**agregamos imagen background*/
        backenter.scaleToFit(390, 390);
               // .setFixedPosition(0, 0);

        PdfDocumentEvent docEvent = (PdfDocumentEvent) evento;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(),
                page.getResources(), pdfDoc);
        Rectangle area = page.getPageSize();
        new Canvas(canvas, pdfDoc, area)
                .add(backenter);

    }


    @Override
    public void handleEvent(Event event) {

        if(Variables.activityCurrent==Variables.FormatDatsContAcopiPREVIEW || Variables.activityCurrent==Variables.FormCamionesyCarretasActivityPreview) {
            addImage(event);

        }else{
            addImage();
        }
    }
}
