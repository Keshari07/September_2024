package com.example.upload.controller;

import com.example.upload.dao.FileDAO;
import com.example.upload.model.FileData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/download")
public class FileDownloadServlet extends HttpServlet {

    private FileDAO fileDAO;

    @Override
    public void init() {
        fileDAO = new FileDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            FileData file = fileDAO.getFile(id);
            if (file != null) {
                response.setContentType(file.getFiletype());
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getFilename());
                response.getOutputStream().write(file.getFiledata());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
