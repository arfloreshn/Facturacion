/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Facdet;
import org.hibernate.Session;

/**
 *
 * @author AllanRamiro
 */
public interface facturadetalleDao {
    
    public boolean guardarDetalle(Session session, Facdet objDetalle)throws Exception;
    
}
