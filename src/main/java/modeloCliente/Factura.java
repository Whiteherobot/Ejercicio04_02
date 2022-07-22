/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloCliente;

import java.io.Serializable;

/**
 *
 * @author mlata
 */                                              
public class Factura implements Serializable{
    
    private int codigo;
    private String nombre;
    private String direccion;
    private Cliente cliente;
    private Producto producto;

    public Factura(int codigo, String nombre, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Factura(int codigo, String nombre, String direccion, Cliente cliente, Producto producto) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cliente = cliente;
        this.producto = producto;
    }

    public Factura(int codigo, String nombre, String direccion, Cliente cliente) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cliente = cliente;
    }

 

    public Factura(Producto producto) {
        this.producto = producto;
    }

    public Factura(int codigo, String nombre, int numero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    

    @Override
    public String toString() {
        return "Factura{" + "codigo=" + codigo + ", nombre=" + nombre + 
                ", direccion=" + direccion + ", cliente=" + cliente.toString() + '}';
    }


    
}