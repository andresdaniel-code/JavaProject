<%@ page import="java.sql.*" %>
<html>
<body>

<h2>Lista de Usuarios</h2>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Email</th>
        <th>Edad</th>
        <th>Acciones</th>
    </tr>

<%
    try {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/usuarios_db",
            "root",
            "asdfghjklñ#3"
        );

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM usuarios");

        while(rs.next()){
%>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("nombre") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getInt("edad") %></td>

        <td>
            <a href="editar.jsp?id=<%= rs.getInt("id") %>">Editar</a>
            |
            <a href="EliminarUsuario?id=<%= rs.getInt("id") %>">Eliminar</a>
        </td>
    </tr>
<%
        }
    } catch(Exception e){
        out.println("Error: " + e);
    }
%>

</table>

<br><br>
<a href="index.jsp">Volver al registro</a>

</body>
</html>
