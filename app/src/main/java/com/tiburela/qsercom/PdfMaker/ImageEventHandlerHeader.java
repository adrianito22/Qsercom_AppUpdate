package com.tiburela.qsercom.PdfMaker;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;

public class ImageEventHandlerHeader implements IEventHandler {
    protected Image img;

    public ImageEventHandlerHeader(Image img) {
        this.img = img;
    }




    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas aboveCanvas = new PdfCanvas(page.newContentStreamAfter(),
                page.getResources(), pdfDoc);
        Rectangle area = page.getPageSize();
        new Canvas(aboveCanvas, pdfDoc, area)
                .add(img);
    }
}
