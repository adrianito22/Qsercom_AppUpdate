package com.tiburela.qsercom.PdfMaker;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class ImageEventHandlerHeader implements IEventHandler {
    protected Image img;
    protected Document midocumentotoAddData;


    public ImageEventHandlerHeader(Image img,Document midocumentotoAddData) {
        this.img = img;
         this.midocumentotoAddData= midocumentotoAddData;
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


    @Override
    public void handleEvent(Event event) {
        addImage();
    }
}
