package com.mycompany.javaproject.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegistrarUsuario")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String edadStr = request.getParameter("age");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || edadStr.isEmpty()) {
            out.println("Completa todos los campos");
            return;
        }

        if (!password.equals(confirmPassword)) {
            out.println("Las contraseñas no coinciden");
            return;
        }

        int edad = Integer.parseInt(edadStr);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/usuarios_db",
                "root",
                "asdfghjklñ#3"
            );

            String sql = "INSERT INTO usuarios(nombre, email, password, edad) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, edad);

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("Registro exitoso");
            } else {
                out.println("Error al registrar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}
