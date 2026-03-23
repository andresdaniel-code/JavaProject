package com.mycompany.javaproject.controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ActualizarUsuario")
public class ActualizarUsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("name");
        String email = request.getParameter("email");
        int edad = Integer.parseInt(request.getParameter("age"));

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/usuarios_db",
                "root",
                "asdfghjklñ#3"
            );

            String sql = "UPDATE usuarios SET nombre=?, email=?, edad=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setInt(3, edad);
            ps.setInt(4, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("listar.jsp");
    }
}
