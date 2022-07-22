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
import modeloCliente.Producto;

/**
 *
 * @author mlata
 */
public class ProductoServicio implements IProductoServicio{
    
    private List<Producto> productoList;
    private String carpeta; 
    
    public ProductoServicio() throws IOException
    {
        carpeta= "E:\\";
        productoList = new ArrayList<>();
        productoList=listar();
    }

    @Override
    public Producto crear(Producto producto, boolean rl) {
      try{
          if (exist(producto.getCodigo(), producto.getNombre())== false){
              String path = carpeta+"/Producto.txt";
              ObjectOutputStream archivo = null;
              try{
                  archivo = new ObjectOutputStream(new FileOutputStream(path, true));
                  archivo.writeObject(producto);
                  archivo.close();
              }catch(IOException e){
                  archivo.close();
              }
              if (rl == true) this.productoList=listar();
              return producto;
          }
          else{
              throw new RuntimeException("Ya existe un producto con este codigo.");
          }
      }catch(IOException ex){
           Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE,null,ex);
      }catch(RuntimeException e){
          throw new RuntimeException("Ya existe un producto con este codigo.");
      }
      return producto;
    }

    @Override
    public List<Producto> listar()throws IOException {
        String path = carpeta + "/Producto.txt";  
        if (new File(path).exists() == true)
        {
           var productoList = new ArrayList<Producto>();        
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream archivo = null;
        try{
            while(file.available()>0)
            {
                archivo = new ObjectInputStream(file);
                Producto producto = (Producto) archivo.readObject(); 
                productoList.add(producto);
            }    
            if (archivo != null) archivo.close();            
            file.close();
        }catch(IOException e){
            archivo.close();
            file.close();
        } catch (ClassNotFoundException ex) {        
            Logger.getLogger(ProductoServicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return productoList; 
        }
        else{
            return null;
        }
    }

    @Override
    public Producto eliminar(int codigoProducto)throws IOException {
        if (exist(codigoProducto) == true)
        {
        Producto producto= this.buscarPorCodigo(codigoProducto);
        var posicion =this.buscarposicion(producto);
        this.listar().remove(posicion);
        productoList.remove(posicion);
        replaceFile();
        return producto;
    }
        else
        {
            throw new RuntimeException("No se ha encontrado un Producto con ese código");
        }
    }
    @Override
    public Producto modificar(int codigoProducto, Producto productoNueva) throws IOException{
          if (exist(codigoProducto) == true)
        {
            var producto = listar();
       var posicion = this.buscarposicion(this.buscarPorCodigo(codigoProducto));
       producto.get(posicion).setCodigo(productoNueva.getCodigo());
       producto.get(posicion).setNombre(productoNueva.getNombre());
       producto.get(posicion).setDireccion(productoNueva.getDireccion());
       productoList= producto;
       replaceFile();
       return productoNueva;
        }
        else
        {
            throw new RuntimeException("No se ha encontrado un Producto con ese código");
        }
}

    @Override
    public int buscarposicion(Producto producto) {
        int posicion=-1;
        for(var p:this.productoList){
            posicion++;
            if(p.getCodigo()==producto.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public Producto buscarPorCodigo(int codigoProducto) {
     Producto producto = null;
        for(var c:this.productoList){
            if(codigoProducto==c.getCodigo()){
                producto = c;
                break;
            }
        }
        return producto;
    }
      public boolean exist(int codigo, String nombre)throws IOException
    {
        var a = listar();
        if (a == null) return false;
        boolean result = false;
        for (Producto e : a)
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
        for (Producto p : productoList)
        {
            if (p.getCodigo() == codigo)
            {
                result = true;
                break; 
            }            
        }
        return result;
    }

    @Override
    public int count() {
       if(productoList== null){
            return 0;
        }
        else{
            return productoList.size();
        }
    }

    private void replaceFile() throws IOException{
        String file_name = carpeta+"/Producto.txt";
        Path path = Paths.get(file_name);
        try{
            Files.delete(path);
            for(int i = 0; i< productoList.size(); i++){
                crear(productoList.get(i),false);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        productoList=listar();
    }

}
