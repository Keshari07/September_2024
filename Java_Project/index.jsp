<%@ page import="java.sql.*" %>
<html>
<head>
    <title>Document Management</title>
</head>
<body>
    <h2>Upload Document</h2>
    <form action="UploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="file" required>
        <input type="submit" value="Upload">
    </form>

    <h2>Uploaded Documents</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>File Name</th>
            <th>View</th>
            <th>Download</th>
        </tr>
        <%
            try (Connection conn = DatabaseUtil.getConnection()) {
                String sql = "SELECT id, file_name FROM documents";
                PreparedStatement statement = conn.prepareStatement(sql);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    int id = result.getInt("id");
                    String fileName = result.getString("file_name");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= fileName %></td>
            <td><a href="ViewServlet?id=<%= id %>">View</a></td>
            <td><a href="DownloadServlet?id=<%= id %>">Download</a></td>
        </tr>
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>
