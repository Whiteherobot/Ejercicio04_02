/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicioCliente;

import java.io.IOException;
import java.util.List;
import modeloCliente.Producto;

/**
 *
 * @author mlata
 */
public interface IProductoServicio {
    public Producto crear(Producto producto, boolean rl);
    public List<Producto> listar()throws IOException;
    public Producto eliminar(int codigoProducto)throws IOException;
    public Producto modificar(int codigoProducto, Producto productoNueva)throws IOException;
    public int buscarposicion(Producto producto);
    public Producto buscarPorCodigo(int codigoProducto); 
    public int count();
    
}
