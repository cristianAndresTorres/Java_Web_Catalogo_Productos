/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author 57301
 */
public class ConexionBD {
    //Atribos
    private Connection conexionBD = null;
    
    public void conexionBD(){}
    
    //--------------------------------------------------------
    //Metodos que se conecta con la BD
    public Connection realizarConexionBD(){
            String url = "jdbc:mysql://localhost:3306/productoWeb";
            String usuario = "root";
            String contrasegna = "";
            try {
                //O->indicación para cargar el DRIVER MYSQL 
                //Apunta el paquete y cargar el driver en tiempo de ejecución
                Class.forName("com.mysql.jdbc.Driver");
                conexionBD = DriverManager.getConnection(url,usuario,contrasegna);
            } catch (Exception e) {
                System.out.println("Error de conexion-realizarConexionBD");
            }
        return conexionBD;
    }
    
    
}
