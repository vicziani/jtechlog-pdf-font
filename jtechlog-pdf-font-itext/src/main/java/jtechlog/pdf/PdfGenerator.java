package jtechlog.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;
import java.io.OutputStream;

public class PdfGenerator {

    public void generate(OutputStream stream, String s) {
        PdfWriter writer = new PdfWriter(stream);

        try {
            PdfFont sans = PdfFontFactory.createFont(
                    "/ttf/DejaVuLGCSans.ttf", PdfEncodings.IDENTITY_H, true);
            PdfFont serif = PdfFontFactory.createFont(
                    "/ttf/DejaVuLGCSerif.ttf", PdfEncodings.IDENTITY_H, true);
            PdfFont mono = PdfFontFactory.createFont(
                    "/ttf/DejaVuLGCSansMono.ttf", PdfEncodings.IDENTITY_H, true);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph()
                    .setFont(sans)
                    .add(s)
                    );
            document.add(new Paragraph()
                    .setFont(serif)
                    .add(s)
            );
            document.add(new Paragraph()
                    .setFont(mono)
                    .add(s)
            );
            document.close();
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot generate pdf", ioe);
        }
    }
}
