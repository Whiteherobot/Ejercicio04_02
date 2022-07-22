/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicioCliente;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
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
import modeloCliente.Factura;

/**
 *
 * @author mlata
 */
public class FacturaServicio implements IFacturaServicio{
    
    private List<Factura> facturaList ;
    private String carpeta;
    
    public FacturaServicio()throws IOException
    {
        carpeta="E:\\";
        facturaList = new ArrayList<>();
        facturaList= listar();
    }

    @Override
    public Factura crear(Factura factura,boolean rl) {
        try{
            if(exist(factura.getCodigo(),factura.getNombre())==false){
                String path = carpeta+"/Factura.txt";
                ObjectOutputStream archivo = null;
                try{
                    archivo = new ObjectOutputStream(new FileOutputStream(path, true));
                    archivo.writeObject(factura);
                    archivo.close();
                }catch(IOException e){
                    archivo.close();
                }
                if (rl== true)this.facturaList =listar();
                return factura;
            }else{
                throw new RuntimeException("Ya esxiste una factura con este codigo");
            }
        }catch(IOException ex){
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }catch(RuntimeException e){
            throw new RuntimeException("Ya existe una factura con este codigo");
        }
        return factura;
    }

    @Override
    public Factura modificar(int codigoFactura, Factura facturaNuevo) throws IOException {
         if (exist(codigoFactura) == true)
        {
        var factura = listar();
        var posicion = this.buscarposicion(this.buscarPorCodigo(codigoFactura));
        factura.get(posicion).setCodigo(facturaNuevo.getCodigo());
        factura.get(posicion).setNombre(facturaNuevo.getNombre());
        factura.get(posicion).setDireccion(facturaNuevo.getDireccion());
        facturaList = factura;
        replaceFile();
        return facturaNuevo;
          }
        else
        {
            throw new RuntimeException("No se ha encontrado una Factura con ese código");
        }

    }

    @Override
    public Factura eliminar(int codigoFactura) throws IOException{
        if (exist(codigoFactura) == true)
        {
        Factura factura = this.buscarPorCodigo(codigoFactura);
        var posicion=this.buscarPorCodigo(codigoFactura);
        facturaList.remove(posicion);
        replaceFile();
        return factura;
     }
        else
        {
            throw new RuntimeException("No se ha encontrado una Factura con ese código");
        }
        }
    

    @Override
    public Factura buscarPorCodigo(int codigoFactura) {
        Factura factura=null;
        for(var f:this.facturaList){
            if(codigoFactura==f.getCodigo()){
                factura=f;
                break;
            }
        }
        return factura;
    }

    @Override
    public int buscarposicion(Factura factura) {
    int posicion=-1;
        for(var f:this.facturaList){
            posicion++;
            if(f.getCodigo()==factura.getCodigo()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Factura> listar() throws IOException{
       String path = carpeta + "/Factura.txt";  
        if (new File(path).exists() == true)
        {
        var facturaList= new ArrayList<Factura>();        
        FileInputStream file = new FileInputStream(path);
        ObjectInputStream archivo = null;
        try{
            while(file.available()>0)
            {
                archivo = new ObjectInputStream(file);
                Factura factura = (Factura) archivo.readObject(); 
                facturaList.add(factura);
            }    
            if (archivo != null) archivo.close();            
            file.close();
        }catch(IOException e){
            archivo.close();
            file.close();
        } catch (ClassNotFoundException ex) {        
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return facturaList; 
        }
        else{
            return null;
        }
    }
    public boolean exist(int codigo, String nombre)throws IOException
    {
        var a = listar();
        if (a == null) return false;
        boolean result = false;
        for (Factura e : a)
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
        for (Factura f : facturaList)
        {
            if (f.getCodigo() == codigo)
            {
                result = true;
                break; 
            }            
        }
        return result;
    }

    @Override
    public int count() {
        if(facturaList== null){
            return 0;
        }
        else{
            return facturaList.size();
        }
    }


    private void replaceFile() throws IOException{{
        String file_name = carpeta+"/Factura.txt";
        Path path = Paths.get(file_name);
        try{
            Files.delete(path);
            for(int i = 0; i < facturaList.size(); i++){
                crear(facturaList.get(i), false);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        facturaList=listar(); }
    }
    
}

