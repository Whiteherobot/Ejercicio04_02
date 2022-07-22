/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicioCliente;

import java.io.IOException;
import java.util.List;
import modeloCliente.Cliente;

/**
 *
 * @author mlata
 */
public interface IClienteServicio {
  public Cliente crear( Cliente cliente, boolean rl);
  public List<Cliente> listar()throws IOException;
  public Cliente eliminar(int codigoCliente)throws IOException;
  public Cliente modificar(int codigoCliente, Cliente clienteNuevo)throws IOException;
  public int buscarposicion(Cliente cliente);
  public Cliente buscarPorCodigo(int codigoCliente); 
  public int count();
    
}
