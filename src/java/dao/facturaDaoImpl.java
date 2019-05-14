/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Facmae;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author AllanRamiro
 */
public class facturaDaoImpl implements facturaDao {

    @Override
    public Long verificarRegistros(Session session) {
        String sSql = "SELECT COUNT(*) FROM Facmae";
        Query q = session.createQuery(sSql).setMaxResults(1);
        return  (Long) q.uniqueResult();
    }

    @Override
    public Facmae getNroFactura(Session session) throws Exception {

        String sSql = "FROM Facmae ORDER BY idfactura DESC";
        Query q = session.createQuery(sSql).setMaxResults(1);
        return (Facmae) q.uniqueResult();
    }

    @Override
    public boolean guardarFactura(Session session, Facmae objFactura) throws Exception {
        session.save(objFactura);
      return true;
    }
}
