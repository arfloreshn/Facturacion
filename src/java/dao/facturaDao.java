/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Facmae;
import org.hibernate.Session;

/**
 *
 * @author AllanRamiro
 */
public interface facturaDao {
    
    public Long verificarRegistros(Session session);
    
    public Facmae getNroFactura(Session session) throws Exception;
    
    public boolean guardarFactura(Session session, Facmae objFactura)throws Exception;
    
}
