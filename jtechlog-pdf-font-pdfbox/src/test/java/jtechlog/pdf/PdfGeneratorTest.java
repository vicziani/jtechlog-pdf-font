package jtechlog.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
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

        PDDocument doc = PDDocument.load(pdf);
        assertThat(new PDFTextStripper().getText(doc), containsString("árvíztűrő tükörfúrógép"));
    }
}
