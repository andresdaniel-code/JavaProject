<%@ page import="java.sql.*" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));

    Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/usuarios_db",
        "root",
        "asdfghjklñ#3"
    );

    PreparedStatement ps = con.prepareStatement("SELECT * FROM usuarios WHERE id=?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
%>

<form action="ActualizarUsuario" method="post">
    <input type="hidden" name="id" value="<%= rs.getInt("id") %>">

    Nombre: <input type="text" name="name" value="<%= rs.getString("nombre") %>"><br>
    Email: <input type="text" name="email" value="<%= rs.getString("email") %>"><br>
    Edad: <input type="number" name="age" value="<%= rs.getInt("edad") %>"><br>

    <button type="submit">Actualizar</button>
</form>
    
    
