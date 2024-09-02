package com.example.upload.dao;

import com.example.upload.model.FileData;

import java.sql.*;

public class FileDAO {

    private final String jdbcURL = "jdbc:postgresql://localhost:5432/filedb";
    private final String jdbcUsername = "postgres";
    private final String jdbcPassword = "password";

    private static final String INSERT_FILE_SQL = "INSERT INTO files (filename, filetype, filedata) VALUES (?, ?, ?)";
    private static final String SELECT_FILE_BY_ID = "SELECT id, filename, filetype, filedata FROM files WHERE id = ?";
    
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void saveFile(FileData fileData) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE_SQL)) {
            preparedStatement.setString(1, fileData.getFilename());
            preparedStatement.setString(2, fileData.getFiletype());
            preparedStatement.setBytes(3, fileData.getFiledata());
            preparedStatement.executeUpdate();
        }
    }

    public FileData getFile(int id) throws SQLException {
        FileData fileData = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILE_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                fileData = new FileData();
                fileData.setId(rs.getInt("id"));
                fileData.setFilename(rs.getString("filename"));
                fileData.setFiletype(rs.getString("filetype"));
                fileData.setFiledata(rs.getBytes("filedata"));
            }
        }
        return fileData;
    }
}
