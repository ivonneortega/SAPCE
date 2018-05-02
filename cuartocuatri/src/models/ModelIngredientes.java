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


public class ModelIngredientes {
    
    ModelConectar model_conectar;
    
    private PreparedStatement pst;
    private ResultSet resut;
    
    private int id_ingrediente;
    private String ingrediente;
    private String sql;
    
    public ModelIngredientes( ModelConectar model_conectar){
        
        this.model_conectar = model_conectar;
        
    }
    
    public int getIdIngrediente(){
        
        return id_ingrediente;
    }
    
    public String getIngrediente(){
        
        return ingrediente;
    }
    
    public void setIdIngrediente(int id_ingrediente){
        
        this.id_ingrediente = id_ingrediente;
    }
    
    public void setIngrediente(String ingrediente){
        
        this.ingrediente = ingrediente;
    }
    
    public ResultSet getResut(){
        
        return resut;
    }
    
    //*************************
    
    public void llenarIngredientes(){
        
        try{
            
            setIdIngrediente(resut.getInt("id_elemento"));
            setIngrediente(resut.getString("elemento"));
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 126" + ex.getMessage());
        }
        
    }
    
    public void primerIngrediente(){
        
        try{
            
            resut.first();
            llenarIngredientes();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 127" + ex.getMessage());
        }
    }
    
    public void seleccionarTodosIngrediente(){
        
        try{
            
            sql = "Select * from elementos;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            resut = pst.executeQuery();
            primerIngrediente();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 128" + ex.getMessage());
        }
    }
    
    public void siguienteIngrediente(){
        
        try{
            
            if(resut.isLast() == false){
                
                resut.next();
                llenarIngredientes();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 129" + ex.getMessage());
        }
    }
    
    public void ultimoIngrediente(){
        
        try{
            
            resut.last();
            llenarIngredientes();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 130" + ex.getMessage());
        }
        
    }
    
    public void anteriorIngrediente(){
        
        try{
            
            if(resut.isFirst() == false){
                
                resut.previous();
                llenarIngredientes();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 131" + ex.getMessage());
        }
    }
    
    public void insertarIngrediente(){
        
        
        
        try{
            
            sql = "Insert into elementos(elemento) values (?);";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setString(1, getIngrediente());
            
            pst.executeUpdate();
            primerIngrediente();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 132" + ex.getMessage());
        }
    }
    
    public void actulizarIngrediente(){
        
        try{
            
            sql = "Update elementos set elemento = ? where id_elemento = ?;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setString(1, getIngrediente());
            pst.setInt(2, getIdIngrediente());
            
            pst.executeUpdate();
            primerIngrediente();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 133" + ex.getMessage());
        }
    }
    
    public void eliminarIngrediente(){
        
        try{
            
            sql = "Delete from elementos where id_elemento = ?;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            pst.setInt(1, getIdIngrediente());
            
            pst.executeUpdate();
            primerIngrediente();
          
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 134" + ex.getMessage());
        }
    }
    
    public void seleccionarIngrediente(){
        
        try{
            
            sql = "Select * from elementos;";
            pst = model_conectar.getConexion().prepareStatement(sql);
            
            resut = pst.executeQuery();
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 135" + ex.getMessage());
        }
    }
    
    public void buscarIngrediente(String ingrediente){
        
        try{
            if(ingrediente.equals("")){
                
                seleccionarIngrediente();
            }
            
            else{
                
                sql = "Select * from elementos where elemento = ?;";
                pst = model_conectar.getConexion().prepareStatement(sql);
                
                pst.setString(1, ingrediente);
                
                resut = pst.executeQuery();
            }
            
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 136" +ex.getMessage());
        }
    }
}
