/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ClsUsers;
import java.sql.*;
//import java.util.*;

/**
 *
 * @author Caleb
 */
public class ClsUsersJDBC {
    
    //Estan Cometadas Ya que solamentente usare SQL_SELECT_VALIDACION y SQL_SELECT para hacer el login a la base de datos//

  /*private static final String SQL_UPDATE = "update tb_usuarios set username=?,password=? where id_user=?";
    private static final String SQL_INSERT = "insert into tb_usuarios(username,password) values(?,?)";
    private static final String SQL_DELETE = "delete from tb_usuarios where id_user=?";                       */
    private static final String SQL_SELECT = "select * from tb_usuarios";
    private static final String SQL_SELECT_VALIDACION = "select * from tb_usuarios where username=?"+"and password=?";
    
    
    
    /*public List<ClsUsers> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsUsers usuario = null;
        List <ClsUsers> usuarios = new ArrayList<ClsUsers>();
        
        try {
            conn = ClsConexionDB.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int id_usuario = rs.getInt("Id_usuario");
                String username = rs.getString("username");
                String password = rs.getString("password");
 
                usuario = new ClsUsers();
                usuario.setId_user(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);
   
                usuarios.add(usuario);
                                        
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexionDB.close(rs);
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        return usuarios; 
    }*/
    
    public boolean select_validacion(ClsUsers datos){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsUsers usuario = new ClsUsers();
        boolean tiene_permiso = false;
        
        try {
            conn = ClsConexionDB.getConnection();//creamos conexion
            String condicion = SQL_SELECT + " where username='"+datos.getUsername()+"'"+" and password='"+datos.getPassword()+"'";
                     
            stmt = conn.prepareStatement(condicion);//llamamos instruccion Select
            rs = stmt.executeQuery();//al ejecutar el query devuelve un tipo de dato rs
            
            while(rs.next()){
                tiene_permiso = true;
                int id_usuario = rs.getInt("id_user");
                String username = rs.getString("username");
                String password = rs.getString("password");
 
                usuario = new ClsUsers();
                usuario.setId_user(id_usuario);
                usuario.setUsername(username);
                usuario.setPassword(password);             
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexionDB.close(rs);
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        return tiene_permiso; 
    }
    
    
    
    /*public int insert(ClsUsers usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexionDB.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
       
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();//eje el udate
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        
        return rows;
    }*/
        
  
    /*public int Update(ClsUsers usuario){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexionDB.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setInt(3, usuario.getId_user());
       
            
            System.out.println("ejecutando query:" + SQL_UPDATE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        
        return rows;
    }*/
    
    
    /* public int deleteDatos(ClsUsers usuario){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexionDB.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getId_user());
            System.out.println("Dato eleminado exitosamente:" + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        return rows;
    }*/
    
}
