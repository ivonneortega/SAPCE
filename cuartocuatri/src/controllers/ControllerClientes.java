/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;



import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import views.ViewClientes;
import models.ModelClientes;
import models.ModelConectar;
import net.proteanit.sql.DbUtils;

public class ControllerClientes {
    
    ViewClientes view_clientes;
    ModelClientes model_clientes;
    ModelConectar model_conectar;
    
    
   // String cliente;
    int confirmar;
    String cliente = "CLIENTE 0";
    
    public ControllerClientes(ModelClientes model_clientes,ModelConectar model_conectar,ViewClientes view_clientes){
        
        this.model_clientes = model_clientes;
        this.view_clientes = view_clientes;
        this.model_conectar = model_conectar;
        
        view_clientes.jbtn_siguiente.addActionListener(e -> this.jbtnSiguiente());
        view_clientes.jbtn_anterior.addActionListener(e -> this.jbtnAnterior());
        view_clientes.jbtn_primero.addActionListener(e -> this.jbtnPrimero());
        view_clientes.jbtn_ultimo.addActionListener(e -> this.jbtnUltimo());
        view_clientes.jbtn_nuevo.addActionListener(e -> this.jbtnNuevo());
        view_clientes.jbtn_guardar.addActionListener(e -> this.jbtnInsertar());
        view_clientes.jbtn_modificar.addActionListener(e -> this.jbtnActualizar());
        view_clientes.jbtn_eliminar.addActionListener(e -> this.jbtnEliminar());
        view_clientes.jbtn_cancelar.addActionListener(e -> this.jbtnCancelar());
        view_clientes.jbtn_mostrar.addActionListener(e -> this.buscarEnTabla());
      
       
        
        
        initView();
        
    }
    
    public void initView(){
        
        
        bloquear();
        model_conectar.conectarBD();
        model_clientes.seleccionarTodosClientes();
        getValores();
        llenarTabla();
        
   
    }
    
   
    
    public void getValores(){
        
        view_clientes.jtf_nombre.setText(model_clientes.getNombre());
        view_clientes.jtf_domicilio.setText(model_clientes.getDomicilio());
        view_clientes.jtf_telefono.setText(model_clientes.getNumero());
        view_clientes.jtf_codigo.setText(cliente + model_clientes.getIdCliente());
        view_clientes.jtf_apellido_paterno.setText(model_clientes.getApePaterno());
        view_clientes.jtf_apellido_materno.setText(model_clientes.getApeMaterno());
       
        
        
    }
    
    public void setValores(){
        
       
        model_clientes.setNombre(view_clientes.jtf_nombre.getText());
        model_clientes.setDomicilio(view_clientes.jtf_domicilio.getText());
        model_clientes.setNumero(view_clientes.jtf_telefono.getText());
        model_clientes.setApeMaterno(view_clientes.jtf_apellido_materno.getText());
        model_clientes.setApePaterno(view_clientes.jtf_apellido_paterno.getText());
       
        
    }
    
    public void jbtnSiguiente(){
        
        model_clientes.siguienteCliente();
        getValores();
    }
    
    public void jbtnAnterior(){
        
        model_clientes.anteriorRegistro();
        getValores();
    }
    
    public void jbtnPrimero(){
        
        model_clientes.primerCliente();
        getValores();
    }
    
    public void jbtnUltimo(){
        
        model_clientes.ultimoCliete();
        getValores();
    }
    
    public void jbtnNuevo(){
        
        view_clientes.jtf_nombre.setText("");
        view_clientes.jtf_domicilio.setText("");
        view_clientes.jtf_telefono.setText("");
        view_clientes.jtf_apellido_paterno.setText("");
        view_clientes.jtf_apellido_materno.setText("");
        view_clientes.jtf_codigo.setText("");
        desbloquear();
    }
    
    public void jbtnInsertar(){
        
       String nombre = view_clientes.jtf_nombre.getText();
       String paterno = view_clientes.jtf_apellido_paterno.getText();
       String materno = view_clientes.jtf_apellido_materno.getText();
       String domicilio = view_clientes.jtf_domicilio.getText();
       String numero = view_clientes.jtf_telefono.getText();
        
       
         if(nombre.equals("")||paterno.equals("")||materno.equals("")||domicilio.equals("")||numero.equals("")){
           
           JOptionPane.showMessageDialog(null, "Llena todos los campos");
           
       }
         
         else{
             
              String registro;
              registro = view_clientes.jtf_nombre.getText();
        
             confirmar = JOptionPane.showConfirmDialog(null,"¿Desea guardar al cliente "+registro+"?","Guardar Registro",JOptionPane.YES_NO_OPTION);
       
                 if(JOptionPane.OK_OPTION == confirmar){
           
                    setValores();
                    model_clientes.insertarCliente();
                    getValores();
                    model_clientes.seleccionarTodosClientes();
                    view_clientes.jtable_clientes.setModel(DbUtils.resultSetToTableModel(model_clientes.getResut()));
           
                 }
                 
            }
    }
    
    public void jbtnActualizar(){
        
        String registro;
        registro = view_clientes.jtf_nombre.getText();
        
        confirmar = JOptionPane.showConfirmDialog(null,"¿Desea modificar al cliente "+registro+"?","Modificar Registro",JOptionPane.WARNING_MESSAGE,JOptionPane.WARNING_MESSAGE);
        
        if(JOptionPane.OK_OPTION == confirmar){
            
           setValores();
           model_clientes.actualizarCliente();
           getValores();
           model_clientes.seleccionarTodosClientes();
        }
        
        else{
            
            model_clientes.primerCliente();
            getValores();
        }
    }
    
    public void jbtnEliminar(){
        
        String registro;
        registro = view_clientes.jtf_nombre.getText();
        
         confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar al cliente "+registro+"?","Eliminar Registro",JOptionPane.WARNING_MESSAGE, JOptionPane.ERROR_MESSAGE);
        
         if(JOptionPane.OK_OPTION == confirmar){
             
             setValores();
             model_clientes.eliminarCliente();
             getValores();
             model_clientes.seleccionarTodosClientes();
         }
         
         else{
             
             model_clientes.primerCliente();
             getValores();
         }
    }
    
    public void bloquear(){
        
        view_clientes.jbtn_cancelar.setEnabled(false);
        view_clientes.jbtn_guardar.setEnabled(false);
        view_clientes.jtf_apellido_materno.setEditable(false);
        view_clientes.jtf_apellido_paterno.setEditable(false);
        view_clientes.jtf_codigo.setEditable(false);
        view_clientes.jtf_domicilio.setEditable(false);
        view_clientes.jtf_nombre.setEditable(false);
        view_clientes.jtf_telefono.setEditable(false);
        
    }
    
    public void desbloquear(){
        view_clientes.jbtn_cancelar.setEnabled(true);
        view_clientes.jbtn_guardar.setEnabled(true);
        view_clientes.jbtn_eliminar.setEnabled(false);
        view_clientes.jbtn_modificar.setEnabled(false);
        view_clientes.jbtn_anterior.setEnabled(false);
        view_clientes.jbtn_primero.setEnabled(false);
        view_clientes.jbtn_siguiente.setEnabled(false);
        view_clientes.jbtn_ultimo.setEnabled(false);
        view_clientes.jtf_apellido_materno.setEditable(true);
        view_clientes.jtf_apellido_paterno.setEditable(true);
        view_clientes.jtf_domicilio.setEditable(true);
        view_clientes.jtf_nombre.setEditable(true);
        view_clientes.jtf_telefono.setEditable(true);
        view_clientes.jbtn_nuevo.setEnabled(false);
        
       
    }
    
    public void jbtnCancelar(){
        
        model_clientes.seleccionarTodosClientes();
        getValores();
        view_clientes.jbtn_cancelar.setEnabled(false);
        view_clientes.jbtn_guardar.setEnabled(false);
        view_clientes.jbtn_eliminar.setEnabled(true);
        view_clientes.jbtn_modificar.setEnabled(true);
        view_clientes.jbtn_anterior.setEnabled(true);
        view_clientes.jbtn_primero.setEnabled(true);
        view_clientes.jbtn_siguiente.setEnabled(true);
        view_clientes.jbtn_ultimo.setEnabled(true);
        view_clientes.jtf_apellido_materno.setEditable(false);
        view_clientes.jtf_apellido_paterno.setEditable(false);
        view_clientes.jtf_domicilio.setEditable(false);
        view_clientes.jtf_nombre.setEditable(false);
        view_clientes.jtf_telefono.setEditable(false);
        view_clientes.jbtn_nuevo.setEnabled(true);
        
    }
    
   
    
    public void llenarTabla(){
        
        model_clientes.seleccionarClientes();
        view_clientes.jtable_clientes.setModel(DbUtils.resultSetToTableModel(model_clientes.getResut()));
        
        JTableHeader columns = view_clientes.jtable_clientes.getTableHeader();
        TableColumnModel header = columns.getColumnModel();
        header.getColumn(0).setHeaderValue("Número de cliente");
        header.getColumn(1).setHeaderValue("Nombre");
        header.getColumn(2).setHeaderValue("Apellido Patreno");
        header.getColumn(3).setHeaderValue("Apellido Materno");
        header.getColumn(4).setHeaderValue("Domicilio");
        header.getColumn(5).setHeaderValue("Telefono");
       
        model_clientes.seleccionarTodosClientes();
    
    }
    
    public void buscarEnTabla() {
        
        try{
        
        model_clientes.buscarClientes(view_clientes.jtf_buscar.getText());
        view_clientes.jtable_clientes.setModel(DbUtils.resultSetToTableModel(model_clientes.getResut()));
        
        
        model_clientes.getResut().next();
        
        JTableHeader columns = view_clientes.jtable_clientes.getTableHeader();
        TableColumnModel header = columns.getColumnModel();
        header.getColumn(0).setHeaderValue("Número de cliente");
        header.getColumn(1).setHeaderValue("Nombre");
        header.getColumn(2).setHeaderValue("Apellido Patreno");
        header.getColumn(3).setHeaderValue("Apellido Materno");
        header.getColumn(4).setHeaderValue("Domicilio");
        header.getColumn(5).setHeaderValue("Telefono");
        
        model_clientes.seleccionarTodosClientes();
        
        view_clientes.jtf_buscar.setText("");
       
      
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 113" + ex.getMessage());
        }
    }
    
    
    
    
}
