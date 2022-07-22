package ControladorCliente;

import java.io.IOException;
import java.util.List;
import modeloCliente.Cliente;
import servicioCliente.ClienteServicio;

/**
 *
 * @author mlata
 */

public class ClienteControl {
    private static  ClienteServicio clienteServicio;
    
    
    public ClienteControl() throws IOException
    {
         clienteServicio = new ClienteServicio();
    }
            
    public Cliente crear(String [] args){
       
        var cliente= new Cliente(Integer.valueOf(args[0]),args[1],args[2]);
        this.clienteServicio.crear(cliente, true);
        return cliente;
    }
    public Cliente buscarFactura(String arg){
        return this.clienteServicio.buscarPorCodigo(Integer.valueOf(arg));
    }
    public Cliente eliminar (String arg) throws IOException{
        return this.clienteServicio.eliminar(Integer.valueOf(arg));
    }
    
    public List<Cliente> listar() throws IOException{
        return this.clienteServicio.listar();
    }
    public Cliente modificar(String[] args) throws IOException {
        Cliente clienteNuevo = new Cliente(Integer.valueOf(args[0]),args[1],args[2]);
        this.clienteServicio.modificar(Integer.valueOf(args[0]), clienteNuevo);
        return clienteNuevo;
    }
    public int count(){
        return this.clienteServicio.count();
    }

}
