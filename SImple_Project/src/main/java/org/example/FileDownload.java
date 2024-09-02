package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileDownload {

    public void downloadFile(String filePath, String destinationDirectory) throws IOException {
        File file = new File(filePath);
        Path destinationPath = Path.of(destinationDirectory, file.getName());
        Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void main(String[] args) {
        FileDownload fileDownload = new FileDownload();
        String filePath = "/path/to/uploaded/file.docx";
        try {
            fileDownload.downloadFile(filePath, "/path/to/download/directory/");
            System.out.println("File downloaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
