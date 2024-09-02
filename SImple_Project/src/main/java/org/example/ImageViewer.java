package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageViewer {

    public void viewImage(String filePath) throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        JLabel label = new JLabel(new ImageIcon(filePath));
        frame.add(label);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ImageViewer viewer = new ImageViewer();
        try {
            viewer.viewImage("/path/to/image.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
