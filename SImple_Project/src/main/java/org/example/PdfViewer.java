package org.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.Document;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.IOException;

public class PdfViewer {

    public void viewPdf(String filePath) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(filePath));
        String text = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(1));
        System.out.println(text); // This will print the text of the first page
        pdfDoc.close();
    }

    public static void main(String[] args) {
        PdfViewer viewer = new PdfViewer();
        try {
            viewer.viewPdf("/path/to/document.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
