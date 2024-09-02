package org.example;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class DocxViewer {

    public void viewDocx(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        XWPFDocument document = new XWPFDocument(fis);

        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            System.out.println(paragraph.getText());
        }

        document.close();
        fis.close();
    }

    public static void main(String[] args) {
        DocxViewer viewer = new DocxViewer();
        try {
            viewer.viewDocx("/path/to/document.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
