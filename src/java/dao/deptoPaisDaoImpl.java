/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.TdeptoPais;
import modelo.Tpais;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author root
 */
public class deptoPaisDaoImpl implements deptoPaisDao {

   private TdeptoPais depto;
   private Tpais pais;
   private List<TdeptoPais> listar;
    
    private Session session;
    private Transaction trans;

    @Override
    public List<TdeptoPais> lstdepto(String id_pais) {
        
        session = HibernateUtil.getSession();

        String sql = "FROM TdeptoPais where id_pais=" + id_pais.trim();
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
            tx.commit();

            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                listar = null;
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return listar;

    
    }

    @Override
    public boolean crearDepto(TdeptoPais obj) {

             
        boolean flag = false;

        try {
       
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            
            session.save(obj);
            trans.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            trans.rollback();
            
            throw ex;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }

        return flag;

    }

    @Override
    public boolean editarDepto(TdeptoPais obj) {
 
           boolean flag = false;

        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.update(obj);
            trans.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            trans.rollback();
            throw ex;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }

        return flag;

    }

    @Override
    public boolean borrarDepto(String id_pais) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
