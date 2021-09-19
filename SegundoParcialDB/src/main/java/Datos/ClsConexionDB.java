/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import java.sql.*;

/**
 *
 * @author Caleb
 */
public class ClsConexionDB {
    //constatntes database
     private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbcomision?zeroDateTimeBehavior=CONVERT_TO_NULL";
     private static final String JDBC_USER = "root";
     private static final String JDBC_PWD = "524532";
     
     //crear conexion y devuelve connection
     public static Connection getConnection() throws SQLException{//throws si hay problema que tire el sqlexception
         return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PWD);//returnamos la conexion
         
     }
         
     //cierra conexion
     
     public static void close(ResultSet rs){         
          try {
             rs.close();
         } 
          catch (SQLException ex) {
          ex.printStackTrace(System.out);
         }        
     }
     
      public static void close(PreparedStatement stmt){
          try {
             stmt.close();
         } 
          catch (SQLException ex) {
          ex.printStackTrace(System.out);
         }         
     }
      
      public static void close(Connection cn){
          try {
             cn.close();
         } 
          catch (SQLException ex) {
          ex.printStackTrace(System.out);
         }         
    }     
}
