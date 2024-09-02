package com.example.upload.controller;

import com.example.upload.dao.FileDAO;
import com.example.upload.model.FileData;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@WebServlet("/upload")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

    private FileDAO fileDAO;

    @Override
    public void init() {
        fileDAO = new FileDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Part filePart = request.getPart("file");
        String fileName = filePart.getSubmittedFileName();
        String fileType = filePart.getContentType();

        InputStream fileContent = filePart.getInputStream();
        byte[] fileData = new byte[(int) filePart.getSize()];
        fileContent.read(fileData);

        FileData file = new FileData();
        file.setFilename(fileName);
        file.setFiletype(fileType);
        file.setFiledata(fileData);

        try {
            fileDAO.saveFile(file);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("view.jsp");
    }
}
