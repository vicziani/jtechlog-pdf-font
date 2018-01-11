package jtechlog.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PdfGeneratorTest {

    @Test
    public void testGenerate() throws IOException {
        PdfGenerator pdfGenerator = new PdfGenerator();
        File pdf = File.createTempFile("test", ".pdf");
        System.out.println("Generated pdf file: " + pdf);
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(pdf))) {
            pdfGenerator.generate(os, "árvíztűrő tükörfúrógép");
        }

        PdfDocument document = new PdfDocument(new PdfReader(pdf));
        assertThat(PdfTextExtractor.getTextFromPage(document.getFirstPage()),
                containsString("árvíztűrő tükörfúrógép"));
    }
}
