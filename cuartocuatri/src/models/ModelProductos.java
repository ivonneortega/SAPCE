/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ModelProductos {
    
    ModelConectar model_conectar;
    
    private PreparedStatement pst;
    private ResultSet resut;
    
    private String sql;
    private int id_producto;
    private String producto;
    private String cantidad;
    private int precio_venta;
    private int precio_compra;
    
    public ModelProductos( ModelConectar model_conectar){
        
        this.model_conectar = model_conectar;
    }
    
    public int getIdProducto(){
        
        return id_producto;
    }
    
    public int getPrecioCompra(){
        
        return precio_compra;
    }
    
    public int getPrecioVenta(){
        
        return precio_venta;
    }
    
    public String getProducto(){
        
        return producto;
    }
    
    public String getCantidad(){
        
        return cantidad;
    }
    
    public void setIdProducto(int id_producto){
        
        this.id_producto = id_producto;
    }
    
    public void setPrecioCompra(int precio_compra){
        
        this.precio_compra = precio_compra;
    }
    
    public void setPrecioVenta(int precio_venta){
        
        this.precio_venta = precio_venta;
    }
    
    public void setProducto(String producto){
        
        this.producto = producto;
    }
    
    public void setCantidad(String cantidad){
        
        this.cantidad = cantidad;
    }
    
    public ResultSet getResut(){
        
        return resut;
    }
    
    //****************************///
    
    public void llenarProductos(){
        
        try{
            
            setIdProducto(resut.getInt("id_producto"));
            setProducto(resut.getString("producto"));
            setCantidad(resut.getString("cantidad_producto"));
            setPrecioVenta(resut.getInt("precio_venta"));
            setPrecioCompra(resut.getInt("precio_compra"));
          
            
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 114" + ex.getMessage());
        }
    }
    
    public void primerProducto(){
        
        try{
            
            resut.first();
            llenarProductos();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 115" + ex.getMessage());
        }
    }
    
    public void seleccionarTodosProductos(){
        
        try{
            
            sql = "Select * from productos;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            resut = pst.executeQuery();
            primerProducto();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 116" + ex.getMessage());
        }
    }
    
    public void ultimoProducto(){
        
        try{
            
            resut.last();
            llenarProductos();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 117" + ex.getMessage());
        }
    }
    
    public void siguienteProducto(){
        
        try{
            
            if(resut.isLast() == false){
                
                resut.next();
                llenarProductos();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 118" + ex.getMessage());
        }
    }
    
    public void anteriorProducto(){
        
        try{
            
            if(resut.isFirst() == false){
                
                resut.previous();
                llenarProductos();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 119" + ex.getMessage());
        }
    }
    
    public void insertarProducto(){
        
        try{
            
            sql = "Insert into productos(producto,cantidad_producto,precio_venta,precio_compra) values (?,?,?,?);";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setString(1, getProducto());
            pst.setString(2, getCantidad());
            pst.setInt(3, getPrecioVenta());
            pst.setInt(4, getPrecioCompra());
            
            pst.executeUpdate();
            primerProducto();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 120" + ex.getMessage());
        }
    }
    
    public void actualizarProducto(){
        
        try{
            
            sql = "Update productos set producto = ?, cantidad_producto = ?, precio_venta = ?, precio_compra = ? "
                    + "where id_producto = ?;";
            
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setString(1, getProducto());
            pst.setString(2, getCantidad());
            pst.setInt(3, getPrecioVenta());
            pst.setInt(4, getPrecioCompra());
            pst.setInt(5, getIdProducto());
            
            pst.executeUpdate();
            primerProducto();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 121" + ex.getMessage());
        }
    }
    
    public void eliminarProducto(){
        
        try{
            
            sql = "Delete from productos where id_producto = ?;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setInt(1, getIdProducto());
            
            pst.executeUpdate();
            
            primerProducto();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 122" + ex.getMessage());
        }
    }
    
    public void seleccionarProductos(){
        
        try{
            
            sql = "Select * from productos;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            resut = pst.executeQuery();
            
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 123" + ex.getMessage());
        }
        
    }
    
      public void buscarProducto(String producto){
          
        try{
            
            if(producto.equals("")){
                
                seleccionarProductos();
            }
            
            else{
                  sql = "Select * from productos where producto = ?;";
                  pst = model_conectar.getConexion().prepareStatement(sql);
            
                  pst.setString(1, producto);
            
                  resut = pst.executeQuery();    
            }
            
                   
            
        }catch(SQLException ex){
            
             JOptionPane.showMessageDialog(null, "Error 124" + ex.getMessage());
        }
        
    }

}
