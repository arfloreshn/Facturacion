/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Producto;
import org.hibernate.Session;

/**
 *
 * @author root
 */
public interface productoDao {
    
   public List<Producto> lstProductos();
   public boolean crearProducto(Producto obj);
   public boolean editarProducto(Producto obj);
   public boolean borrarProducto(Integer id);
   public Producto buscaProductoxCodigo(Session session, String codProducto) throws Exception;
    
}
