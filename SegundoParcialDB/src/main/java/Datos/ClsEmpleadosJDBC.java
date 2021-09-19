/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.ClsEmpleados;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Caleb
 */
public class ClsEmpleadosJDBC {
    

//constatantes
    private static final String SQL_SELECT = "select * from tb_empleados";
    private static final String SQL_UPDATE = "update tb_empleados set nombre=?,enero=?,febrero=?, marzo=?,total=?,promedio=? where id=?";
    private static final String SQL_INSERT = "insert into tb_empleados(nombre,enero,febrero,marzo,total,promedio) values(?,?,?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_empleados where id=?";
   
    private static final int divisr = 3;
    
    
    
    //select, update, insert, delete
    public List<ClsEmpleados> select(){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ClsEmpleados empleado = null;
        List <ClsEmpleados> empleados = new ArrayList<ClsEmpleados>();
        
        try {
            conn = ClsConexionDB.getConnection(); //se conecta
            stmt = conn.prepareStatement(SQL_SELECT); //llamamos sql_select
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double enero = rs.getDouble("enero");
                double febrero = rs.getDouble("febrero");
                double marzo = rs.getDouble("marzo");
                double total = rs.getDouble("total");
                double promedio = rs.getDouble("promedio");
                
                empleado = new ClsEmpleados();
                empleado.setId(id);
                empleado.setNombre(nombre);
                empleado.setEnero(enero);
                empleado.setFebrero(febrero);
                empleado.setMarzo(marzo);
                empleado.setTotal(total);
                empleado.setPromedio(promedio);
                
                empleados.add(empleado);                                
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            ClsConexionDB.close(rs);
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        return empleados; 
    } 
    
    
    public int Update(ClsEmpleados empleado){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexionDB.getConnection();//se conecta
            stmt = conn.prepareStatement(SQL_UPDATE); //llamamos sql_update
//parametros 
            stmt.setString(1, empleado.getNombre());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
            stmt.setDouble(5, (empleado.getEnero() + empleado.getFebrero() + empleado.getMarzo()));
            stmt.setDouble(6, ((empleado.getEnero() + empleado.getFebrero() + empleado.getMarzo()) / divisr));
            stmt.setInt(7, empleado.getId());
       
            
            System.out.println("running query... " + SQL_UPDATE); //ejecucion query 
            rows = stmt.executeUpdate();//ejecucion update
            System.out.println("data affected: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }        
        return rows;
    }
    
    public int insert(ClsEmpleados empleado){//inserta empleados, creamos la parte de insert y le mandamos como parametro empleado
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexionDB.getConnection(); //se conecta
            stmt = conn.prepareStatement(SQL_INSERT); //llamamos aql_insert 
//parametros
            stmt.setString(1, empleado.getNombre());
            stmt.setDouble(2, empleado.getEnero());
            stmt.setDouble(3, empleado.getFebrero());
            stmt.setDouble(4, empleado.getMarzo());
            stmt.setDouble(5, (empleado.getEnero() + empleado.getFebrero() + empleado.getMarzo()));
            stmt.setDouble(6, ((empleado.getEnero() + empleado.getFebrero() + empleado.getMarzo()) / divisr));
            
            System.out.println("running query... " + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("data affected: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }       
        return rows;
    }
    

    public int deleteData(ClsEmpleados empleado){
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexionDB.getConnection();//se conecta
            stmt = conn.prepareStatement(SQL_DELETE);//llamamos sql_delete
            stmt.setInt(1, empleado.getId());
            System.out.println("Empleado eliminado correctamente " + SQL_DELETE);
            rows = stmt.executeUpdate();
            System.out.println("data affected: " + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexionDB.close(stmt);
            ClsConexionDB.close(conn);
        }
        return rows;
    }
   
    
    public DefaultTableModel showInf(){
        String [] columns = {"id","nombre","Enero","Febrero","Marzo","total","promedio"};
        String [] records = new String[7];
        
        DefaultTableModel table = new DefaultTableModel(null,columns);        
        Connection conn = null;
        PreparedStatement stmt = null;        
        ResultSet rs = null;
    
    try{
        conn = ClsConexionDB.getConnection();//se conecta
        stmt = conn.prepareStatement(SQL_SELECT); //llamamos sql_select                      
        rs = stmt.executeQuery();
        
        while(rs.next()){
            records[0] = rs.getString("id");
            records[1] = rs.getString("nombre");
            records[2] = rs.getString("Enero");
            records[3] = rs.getString("Febrero");
            records[4] = rs.getString("Marzo");
            records[5] = rs.getString("total");
            records[6] = rs.getString("promedio");
                
            table.addRow(records);                
            }
        
    }catch(SQLException e){
        JOptionPane.showMessageDialog(null,"Error de conexion");  
        }
        finally{
            
        try{
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,7);
            }
        }
        return table;
    }
}
    