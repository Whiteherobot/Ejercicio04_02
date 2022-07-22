/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicioCliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modeloCliente.Cliente;

/**
 *
 * @author mlata
 */
public class ClienteServicio implements IClienteServicio {
    private  List<Cliente> clienteList;
    private String carpeta;
    
    public ClienteServicio()throws IOException
    {
        carpeta="E:\\";
        clienteList = new ArrayList<>();
        clienteList= listar();
    }

    @Override
    public Cliente crear(Cliente cliente, boolean rl) {
     try{
         if(exist(cliente.getCodigo(), cliente.getNombre())== false){
             String path = carpeta +"/Cliente.txt";
             ObjectOutputStream archivo = null;
             try{
                 archivo = new ObjectOutputStream(new FileOutputStream(path,true));
                 archivo.writeObject(cliente);
                 archivo.close();
             }catch(IOException e){
                 archivo.close();
             }
             if (rl == true) this.clienteList = listar();
             return cliente;
         }
         else{
             throw new RuntimeException("Ya existe un cliente con este codigo.");
         }
     }catch(IOException ex){
         Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE,null,ex);
     }catch(RuntimeException e){
         throw new RuntimeException("Ya existe un cliente con este codigo.");
     }   
     return cliente;
    }
    @Override
    public Cliente eliminar(int codigoCliente)throws IOException {
        if (exist(codigoCliente) == true)
        {
        Cliente cliente = this.buscarPorCodigo(codigoCliente);
        var posicion= this.buscarPorCodigo(codigoCliente);
        clienteList.remove(posicion);
           replaceFile();
        return cliente;
         }
        else
        {
            throw new RuntimeException("No se ha encontrado un Usuario con ese código");
        }


    }

    @Override
    public Cliente modificar(int codigoCliente, Cliente clienteNuevo)throws IOException {
        if (exist(codigoCliente) == true)
        {
            var clientes = listar();
            var posicion = this.buscarposicion(this.buscarPorCodigo(codigoCliente));
            clientes.get(posicion).setCodigo(clienteNuevo.getCodigo());
            clientes.get(posicion).setApellido(clienteNuevo.getApellido());
            clientes.get(posicion).setNombre(clienteNuevo.getNombre());
            clienteList = clientes;
            replaceFile();
            return clienteNuevo;            
        }
        else
        {
            throw new RuntimeException("No se ha encontrado un cliente con ese código");
        }
    }

    @Override
    public int buscarposicion(Cliente cliente) {
      int posicion=-1;
        for(var c:this.clienteList){
            posicion++;
            if(c.getCodigo()==cliente.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public Cliente buscarPorCodigo (int codigoCliente) {
        Cliente cliente = null;
        for(var c:this.clienteList){
            if(codigoCliente==c.getCodigo()){
                cliente = c;
                break;
            }
        }
        return cliente;
    }
    
    @Override
    public List<Cliente> listar() throws IOException {
        String path = carpeta + "/Cliente.txt";  
        if (new File(path).exists() == true)
        {
           var clienteList = new ArrayList<Cliente>();        
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream archivo = null;
        try{
            while(file.available()>0)
            {
                archivo = new ObjectInputStream(file);
                Cliente cliente = (Cliente) archivo.readObject(); 
                clienteList.add(cliente);
            }    
            if (archivo != null) archivo.close();            
            file.close();
        }catch(IOException e){
            archivo.close();
            file.close();
        } catch (ClassNotFoundException ex) {        
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return clienteList; 
        }
        else{
            return null;
        }
    }
     public boolean exist(int codigo, String nombre) throws IOException
    {
         var a = listar();
        if (a == null) return false;
        boolean result = false;
        for (Cliente e : a)
        {
            if (e.getCodigo() == codigo)
            {
                result = true;
                break; 
            }
            else if (e.getNombre().equals(nombre))
            {
                result  = true;
                break;
            }
        }
        return result;
    }
      public boolean exist(int codigo)
    {
        boolean result = false;
        for (Cliente c : clienteList)
        {
            if (c.getCodigo() == codigo)
            {
                result = true;
                break; 
            }            
        }
        return result;
    }

    @Override
    public int count() {
        if(clienteList== null){
            return 0;
        }
        else{
            return clienteList.size();
        }
    }

    private void replaceFile() throws IOException{
        String file_name = carpeta+"/Cliente.txt";
        Path path = Paths.get(file_name);
        try{
            Files.delete(path);
            for(int i = 0; i < clienteList.size(); i++){
                crear(clienteList.get(i), false);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        clienteList=listar();
    }



   
}
