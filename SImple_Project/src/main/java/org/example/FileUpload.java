package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUpload {

    public void uploadFile(File file, String destinationDirectory) throws IOException {
        Path destinationPath = Path.of(destinationDirectory, file.getName());
        Files.copy(file.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void main(String[] args) {
        FileUpload fileUpload = new FileUpload();
        File fileToUpload = new File("/path/to/your/file.docx");
        try {
            fileUpload.uploadFile(fileToUpload, "/path/to/upload/directory/");
            System.out.println("File uploaded successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
