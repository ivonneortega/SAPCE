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

public class ModelClientes {

    ModelConectar model_conectar;
    
    private PreparedStatement pst;
    private ResultSet resut;
    
    private String sql;
    private int id_cliente;
    private String nombre;
    private String ape_paterno;
    private String ape_materno;
    private String domicilio;
    private String numero;
    
    public ModelClientes(ModelConectar model_conectar){
        
        this.model_conectar = model_conectar;
        
    }
   
    public int getIdCliente(){
        
        return id_cliente;
    }
    
    public String getNombre(){
        
        return nombre;
    }
    
    public String getApePaterno(){
        
        return ape_paterno;
    }
    
    public String getApeMaterno(){
        
        return ape_materno;
    }
    
    public String getDomicilio(){
        
        return domicilio;
    }
    
    public String getNumero(){
        
        return numero;
    }
    
    public ResultSet getResut(){
        
        return resut;
    }
    
    //***************************//
    
    
    public void setIdCliente(int id_cliente){
        
        this.id_cliente = id_cliente;
    }
    
    public void setNombre(String nombre){
        
        this.nombre = nombre;
    }
    
    public void setApePaterno(String ape_paterno){
        
        this.ape_paterno = ape_paterno;
    }
    
    public void setApeMaterno(String ape_materno){
        
        this.ape_materno = ape_materno;
    }
    
    public void setDomicilio(String domicilio){
        
        this.domicilio = domicilio;
    }
    
    public void setNumero(String numero){
        
        this.numero = numero;
    }
    
    //*****************************//
    
    
    
    public void llenarClientes(){
        
        try{
            
            setIdCliente(resut.getInt("id_cliente"));
            setNombre(resut.getString("nombre"));
            setApePaterno(resut.getString("apellido_paterno"));
            setApeMaterno(resut.getString("apellido_materno"));
            setDomicilio(resut.getString("domicilio"));
            setNumero(resut.getString("numero"));
            
            
           
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 102" + ex.getMessage());
        }
    }
    
    public void primerCliente(){
        
        try{
            
            resut.first();
            llenarClientes();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 103" + ex.getMessage());
        }
    }
    
    public void seleccionarTodosClientes(){
        
        try{
            
            sql = "Select * from clientes;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            resut = pst.executeQuery();
            primerCliente();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 104" + ex.getMessage());
        }
    }
    
    public void siguienteCliente(){
        
        try{
            
            if(resut.isLast()==false){
                
                resut.next();
                llenarClientes();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 105" + ex.getMessage());
        }
    }
    
    public void anteriorRegistro(){
        
        try{
            
            if(resut.isFirst()==false){
                
                resut.previous();
                llenarClientes();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 106" + ex.getMessage());
        }
    }
    
    public void ultimoCliete(){
        
        try{
            
            resut.last();
            llenarClientes();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 107" + ex.getMessage());
        }
    }
    
    public void insertarCliente(){
        
        try{
            
            sql = "Insert into clientes(nombre,apellido_paterno,apellido_materno,domicilio,numero) values (?,?,?,?,?);";
            pst = model_conectar.getConexion().prepareStatement(sql);
     
            
            pst.setString(1, getNombre());
            pst.setString(2, getApePaterno());
            pst.setString(3, getApeMaterno());
            pst.setString(4, getDomicilio());
            pst.setString(5, getNumero());
            
            pst.executeUpdate();
           
            primerCliente();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 108" + ex.getMessage());
        }
    }
    
    public void actualizarCliente(){
        
        try{
            
            sql = "Update clientes set nombre = ?, apellido_paterno = ?, apellido_materno = ?, domicilio = ?, numero = ? "
                    + "where id_cliente = ?;";
            
            
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setString(1, getNombre());
            pst.setString(2, getApePaterno());
            pst.setString(3, getApeMaterno());
            pst.setString(4, getDomicilio());
            pst.setString(5, getNumero());
            pst.setInt(6, getIdCliente());
            
            pst.executeUpdate();
            
            primerCliente();
            
            
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 109" + ex.getMessage());
        }
    }
    
    public void eliminarCliente(){
        
        try{
            
            sql = "Delete from clientes where id_cliente = ?;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setInt(1, getIdCliente());
            
            pst.executeUpdate();
            
            primerCliente();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 110" + ex.getMessage());
        }
    }
    
    public void seleccionarClientes(){
        
        try{
            
            sql = "Select * from clientes;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            resut = pst.executeQuery();
            
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 111" + ex.getMessage());
        }
        
    }
    
    public void buscarClientes(String nombre){
          
        try{
            
            if(nombre.equals("")){
                
                seleccionarClientes();
            }
            
            else{
                  sql = "Select * from clientes where nombre = ?;";
                  pst = model_conectar.getConexion().prepareStatement(sql);
            
                  pst.setString(1, nombre);
            
                  resut = pst.executeQuery();    
            }
            
                   
            
        }catch(SQLException ex){
            
             JOptionPane.showMessageDialog(null, "Error 112" + ex.getMessage());
        }
        
    }
    
    
}
