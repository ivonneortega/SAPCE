/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import views.ViewProductos;
import views.ViewClientes;
import views.ViewPrincipal;
import views.ViewIngredientes;
import models.ModelClientes;
import models.ModelConectar;
import models.ModelProductos;
import models.ModelIngredientes;
import models.ModelPrincipal;
import controllers.ControllerClientes;
import controllers.ControllerIngredientes;
import controllers.ControllerProductos;
import controllers.ControllerPrincipal;


public class Main {
    
    public static void main(String alb[]){
        
        ModelConectar model_conectar = new ModelConectar();
        
        ModelClientes model_clientes = new ModelClientes(model_conectar);
        ViewClientes view_clientes = new ViewClientes();
        ControllerClientes controller_clientes = new ControllerClientes(model_clientes,model_conectar,view_clientes);
        
        ModelProductos model_productos = new ModelProductos(model_conectar);
        ViewProductos view_productos = new ViewProductos();
        ControllerProductos controller_productos = new ControllerProductos(model_productos,model_conectar,view_productos);
        
        ModelIngredientes model_ingredientes = new ModelIngredientes(model_conectar);
        ViewIngredientes view_ingredientes = new ViewIngredientes();
        ControllerIngredientes controller_ingredientes = new ControllerIngredientes(model_ingredientes,model_conectar,view_ingredientes);
        
        ModelPrincipal model_principal = new ModelPrincipal();
        ViewPrincipal view_principal = new ViewPrincipal();
        
        Object models[] = new Object [1];
        Object views[] = new Object [4];
        Object controllers[] = new Object[3];
        
       
        views[0] = view_principal;
        views[1] = view_clientes;
        views[2] = view_productos;
        views[3] = view_ingredientes;
      
     
        
       
        
        ControllerPrincipal controller_principal = new ControllerPrincipal(models,views,controllers);
    }
}
