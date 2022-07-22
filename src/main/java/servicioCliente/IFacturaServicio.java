/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicioCliente;

import java.io.IOException;
import java.util.List;
import modeloCliente.Factura;

/**
 *
 * @author mlata
 */
public interface IFacturaServicio {
    public Factura crear( Factura factura, boolean rl);
    public List<Factura> listar()throws IOException;
    public Factura eliminar(int codigoFactura)throws IOException;
    public Factura modificar(int codigoFactura, Factura facturaNueva)throws IOException;
    public int buscarposicion(Factura factura);
    public Factura buscarPorCodigo(int codigoFactura);
    public int count();
    }
