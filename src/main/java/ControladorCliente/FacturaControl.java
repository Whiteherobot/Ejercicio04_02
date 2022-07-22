/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorCliente;

import java.io.IOException;
import java.util.List;
import modeloCliente.Cliente;
import modeloCliente.Factura;
import servicioCliente.ClienteServicio;
import servicioCliente.FacturaServicio;
import servicioCliente.ProductoServicio;

/**
 *
 * @author mlata
 */
public class FacturaControl {
    private static ClienteServicio clienteServicio;
    private static FacturaServicio facturaServicio;
    private static ProductoServicio productoServicio;
    
     public FacturaControl() throws IOException
    {
         clienteServicio = new ClienteServicio();
         facturaServicio = new FacturaServicio();
         productoServicio = new ProductoServicio();
    }
    
    public Factura crear(String [] args) throws IOException{
       
        Factura factura = new Factura(Integer.valueOf(args[0]),args[1],args[2],clienteServicio.listar().get(Integer.valueOf(args[3])),productoServicio.listar().get(Integer.valueOf(args[3])));
        this.facturaServicio.crear(factura, true);
        
        
        return factura;
    }
    public Factura buscarFactura(String arg){
        return this.facturaServicio.buscarPorCodigo(Integer.valueOf(arg));
    }
    public Factura eliminar (String arg) throws IOException{
        return this.facturaServicio.eliminar(Integer.valueOf(arg));
    }
    public List<Factura> listar() throws IOException{
        return this.facturaServicio.listar();
    }
    public List<Cliente>Listar() throws IOException{
        return this.clienteServicio.listar();
    }

    public Factura modificar(String[] args) throws IOException {
        Factura facturaNuevo = new Factura(Integer.valueOf(args[0]),args[1],args[2]);
        this.facturaServicio.modificar(Integer.valueOf(args[0]), facturaNuevo);
        return facturaNuevo;
    }
    public int count (){
        return this.facturaServicio.count();
    }

}