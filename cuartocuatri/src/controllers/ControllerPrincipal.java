/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import views.ViewPrincipal;
import views.ViewClientes;
import views.ViewProductos;
import views.ViewIngredientes;



public final class ControllerPrincipal {
    
    ViewPrincipal view_principal;
    ViewClientes view_clientes;
    ViewProductos view_productos;
    ViewIngredientes view_ingredientes;
    
    
    
    public ControllerPrincipal(Object models[],Object views[],Object controllers[]){
        
        this.view_clientes = (ViewClientes) views[1];
        this.view_principal = (ViewPrincipal) views[0];
        this.view_productos = (ViewProductos) views[2];
        this.view_ingredientes = (ViewIngredientes) views[3];
      
       
        view_principal.jm_item_clientes_re.addActionListener(e -> this.frameClientes());
        view_principal.jm_item_productos_re.addActionListener(e -> this.frameProductos());
        view_principal.jm_item_ingredientes.addActionListener(e -> this.frameIngredientes());
        initView();
        
    }
    
    public void initView(){
        
        view_principal.setVisible(true);
        view_principal.setTitle("Sistema Administrativo Para Cocina Economica   SAPCE");
        view_principal.setExtendedState(ViewPrincipal.MAXIMIZED_BOTH);
        
        
    }
    
    public void frameClientes(){
      
        view_principal.jdesktop_principal.add(view_clientes);
        view_clientes.show();
        view_clientes.setLocation(50, 20);
      
    }
    
    public void frameProductos(){
        
        view_principal.jdesktop_principal.add(view_productos);
        view_productos.show();
        view_productos.setLocation(50, 20);
        
    }
    
    public void frameIngredientes(){
        
        view_principal.jdesktop_principal.add(view_ingredientes);
        view_ingredientes.show();
        view_ingredientes.setLocation(50, 20);
    }
}
