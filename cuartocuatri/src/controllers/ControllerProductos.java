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
import models.ModelConectar;
import models.ModelProductos;
import net.proteanit.sql.DbUtils;
import views.ViewProductos;

public class ControllerProductos {
    
    ModelConectar model_conectar;
    ModelProductos model_productos;
    ViewProductos view_productos;
    String producto = "PRODUCTO 0";
    int confirmar;
    String registro;
    
    public ControllerProductos(ModelProductos model_productos, ModelConectar model_conectar,ViewProductos view_productos){
        
        this.model_conectar = model_conectar;
        this.model_productos = model_productos;
        this.view_productos = view_productos;
        
        initView();
        
        view_productos.jbtn_nuevo.addActionListener(e -> this.jbtNuevo());
        view_productos.jbtn_anterior.addActionListener(e -> this.jbtnAnterior());
        view_productos.jbtn_primero.addActionListener(e -> this.jbtnPrimero());
        view_productos.jbtn_siguiente.addActionListener(e -> this.jbtnSiguiente());
        view_productos.jbtn_ultimo.addActionListener(e -> this.jbtnUltimo());
        view_productos.jbtn_guardar.addActionListener(e -> this.jbtnGuardar());
        view_productos.jbtn_modificar.addActionListener(e -> this.jbtnModificar());
        view_productos.jbtn_eliminar.addActionListener(e -> this.jbtnEliminar());
        view_productos.jbtn_mostrar.addActionListener(e -> this.buscarEnTabla());
        
    }
    
    public void initView(){
        
        model_conectar.conectarBD();
        model_productos.seleccionarTodosProductos();
        getValores();
        llenarTabla();
        
    }
    
    public void getValores(){
        
        view_productos.jtf_cantidad.setText(model_productos.getCantidad());
        view_productos.jtf_codigo.setText(producto +model_productos.getIdProducto());
        view_productos.jtf_precio_compra.setText(""+model_productos.getPrecioCompra());
        view_productos.jtf_precio_venta.setText(""+model_productos.getPrecioVenta());
        view_productos.jtf_producto.setText(model_productos.getProducto());
    }
    
    public void setValores(){
        
        model_productos.setProducto(view_productos.jtf_producto.getText());
        model_productos.setCantidad(view_productos.jtf_cantidad.getText());
      //  model_productos.setIdProducto(Integer.parseInt(view_productos.jtf_codigo.getText()));
        model_productos.setPrecioCompra(Integer.parseInt(view_productos.jtf_precio_compra.getText()));
        model_productos.setPrecioVenta(Integer.parseInt(view_productos.jtf_precio_venta.getText()));
        
    }
    
    public void jbtnSiguiente(){
        
        model_productos.siguienteProducto();
        getValores();
    }
    
    public void jbtnAnterior(){
        
        model_productos.anteriorProducto();
        getValores();
    }
    
    public void jbtnPrimero(){
        
        model_productos.primerProducto();
        getValores();
    }
    
    public void jbtnUltimo(){
        
        model_productos.ultimoProducto();
        getValores();
    }
    
    public void jbtNuevo(){
        
        view_productos.jtf_cantidad.setText("");
        view_productos.jtf_codigo.setText("");
        view_productos.jtf_precio_compra.setText("");
        view_productos.jtf_precio_venta.setText("");
        view_productos.jtf_producto.setText("");
    }
    
    public void jbtnGuardar(){
        
        registro = view_productos.jtf_producto.getText();
        
        confirmar = JOptionPane.showConfirmDialog(null,"¿Desea guardar el producto "+registro+"?","Guardar Registro",JOptionPane.YES_NO_OPTION);
        
        if(JOptionPane.OK_OPTION == confirmar){
            
            setValores();
            model_productos.insertarProducto();
            getValores();
            model_productos.seleccionarProductos();
            
        }
        else{
            
            model_productos.primerProducto();
            getValores();
        }
        
    }
    
    public void jbtnModificar(){
        
        registro = view_productos.jtf_producto.getText();
        
        confirmar = JOptionPane.showConfirmDialog(null, "¿Desea modificar el producto "+registro+"?","Modificar Registro",JOptionPane.WARNING_MESSAGE,JOptionPane.WARNING_MESSAGE);
        
        if(JOptionPane.OK_OPTION == confirmar){
            
            setValores();
            model_productos.actualizarProducto();
            getValores();
            model_productos.seleccionarProductos();
        }
        else{
            
            model_productos.primerProducto();
            getValores();
        }
    }
    
    public void jbtnEliminar(){
        
        registro = view_productos.jtf_producto.getText();
        
        confirmar = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el producto "+registro+"?","Eliminar Registro",JOptionPane.WARNING_MESSAGE, JOptionPane.ERROR_MESSAGE);
        
        if(JOptionPane.OK_OPTION == confirmar){
            
            setValores();
            model_productos.eliminarProducto();
            getValores();
            model_productos.seleccionarProductos();
        }
        else{
            
            model_productos.primerProducto();
            getValores();
        }
    }
    
     public void llenarTabla(){
        
        model_productos.seleccionarProductos();
        view_productos.jtable_productos.setModel(DbUtils.resultSetToTableModel(model_productos.getResut()));
        
        JTableHeader columns = view_productos.jtable_productos.getTableHeader();
        TableColumnModel header = columns.getColumnModel();
        header.getColumn(0).setHeaderValue("Número de producto");
        header.getColumn(1).setHeaderValue("Producto");
        header.getColumn(2).setHeaderValue("Cantidad");
        header.getColumn(3).setHeaderValue("Precio de venta");
        header.getColumn(4).setHeaderValue("Precio de compra");
       
        model_productos.seleccionarTodosProductos();
    
    }
     
     public void buscarEnTabla() {
        
        try{
        
             model_productos.buscarProducto(view_productos.jtf_buscar.getText());
             view_productos.jtable_productos.setModel(DbUtils.resultSetToTableModel(model_productos.getResut()));
        
        
             model_productos.getResut().next();
        
        JTableHeader columns = view_productos.jtable_productos.getTableHeader();
        TableColumnModel header = columns.getColumnModel();
        header.getColumn(0).setHeaderValue("Número de producto");
        header.getColumn(1).setHeaderValue("Producto");
        header.getColumn(2).setHeaderValue("Cantidad");
        header.getColumn(3).setHeaderValue("Precio de venta");
        header.getColumn(4).setHeaderValue("Precio de compra");
        
        
        model_productos.seleccionarTodosProductos();
        
        view_productos.jtf_buscar.setText("");
        
        }catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Error 125" + ex.getMessage());
        }
    }
}
