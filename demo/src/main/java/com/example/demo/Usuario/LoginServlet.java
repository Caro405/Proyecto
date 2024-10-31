package com.example.demo.Usuario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.DatabaseManager.DatabaseManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet{
    protected void doPost(HttpServletRequest solicitud, HttpServletResponse respuesta)

        throws ServletException, IOException{

            String correo = solicitud.getParameter("correo");
            String contrasena = solicitud.getParameter("contrasena");

            try(Connection conexion = DatabaseManager.getConnection()){
                String query = "SELECT * FROM usuario WHERE correo = ? AND contrasena = ?";
                PreparedStatement declaracion = conexion.prepareStatement(query);

                declaracion.setString(1, correo);
                declaracion.setString(2, contrasena);

                ResultSet resultado = declaracion.executeQuery();

                if (resultado.next()){
                    respuesta.getWriter().println("Inicio de sesion exitoso");
                }else{
                    respuesta.getWriter().println("Usuario o/y contraseña inválidos");
                }
            }catch (SQLException e){
                e.printStackTrace();
                respuesta.getWriter().println("Error con la base de datos");
            }
            
        }





    }

