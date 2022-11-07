package com.tiburela.qsercom.PdfMaker;

import android.util.Log;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;

public class BackgroundEventHandler implements IEventHandler {
    protected Image img;

    public BackgroundEventHandler(Image img) {
        this.img = img;
    }

    @Override
    public void handleEvent(Event event) {

        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(),
                page.getResources(), pdfDoc);
        Rectangle area = page.getPageSize();
        Log.d("miodtara", "sd"+area.getHeight());
        //img.scaleToFit(100,100);
        //i((mg.setAbsolutePosition((rect.getLeft() + rect.getRight()) / 2 - 45, rect.getTop() - 50);
       // img.setAlignment(Element.ALIGN_CENTER);
       // img.scaleAbsolute(PageSize.A4.rotate());
        //img.setAbsolutePosition(0, 0);
        img.scaleToFit(595f, 300f); //ESTA EN 400

        new Canvas(canvas, pdfDoc, area)
                .add(img);
    }
}
