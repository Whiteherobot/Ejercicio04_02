/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorCliente;

import java.io.IOException;
import java.util.List;
import modeloCliente.Producto;
import servicioCliente.ProductoServicio;

/**
 *
 * @author mlata
 */
public class ProductoControl {
    private static ProductoServicio productoServicio;
    
    
    public ProductoControl() throws IOException
    {
        productoServicio= new ProductoServicio();
    }
    
    public Producto crear(String [] args){
        var producto= new Producto(Integer.valueOf(args[0]),args[1],args[2]);
        this.productoServicio.crear(producto, true);
        return producto;
    }
    public Producto buscarFactura(String arg){
        return this.productoServicio.buscarPorCodigo(Integer.valueOf(arg));
    }
    public Producto eliminar (String arg) throws IOException{
        return this.productoServicio.eliminar(Integer.valueOf(arg));
    }
    
    public List<Producto> listar() throws IOException{
        return this.productoServicio.listar();
    }
        public Producto modificar(String[] args) throws IOException {
        Producto productoNuevo = new Producto(Integer.valueOf(args[0]),args[1],args[2]);
        this.productoServicio.modificar(Integer.valueOf(args[0]), productoNuevo);
        return productoNuevo;
    }
    public int count(){
        return this.productoServicio.count();
    }    
}
