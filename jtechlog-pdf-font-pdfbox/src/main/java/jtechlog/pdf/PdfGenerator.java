package jtechlog.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.IOException;
import java.io.OutputStream;

public class PdfGenerator {

    public void generate(OutputStream stream, String s) {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream content = new PDPageContentStream(document, page);
            content.beginText();
            content.newLineAtOffset(25, 700);
            content.setLeading(14.5f);

            PDFont sans = PDType0Font.load(document, PdfGenerator.class.getResourceAsStream("/ttf/DejaVuLGCSans.ttf"));
            content.setFont(sans, 12);
            content.showText(s);
            content.newLine();

            PDFont serif = PDType0Font.load(document, PdfGenerator.class.getResourceAsStream("/ttf/DejaVuLGCSerif.ttf"));
            content.setFont(serif, 12);
            content.showText(s);
            content.newLine();

            PDFont mono = PDType0Font.load(document, PdfGenerator.class.getResourceAsStream("/ttf/DejaVuLGCSansMono.ttf"));
            content.setFont(mono, 12);
            content.showText(s);

            content.endText();
            content.close();

            document.save(stream);
            document.close();
        } catch (IOException e) {
            throw new IllegalStateException("Error creating pdf.", e);
        }
    }
}
