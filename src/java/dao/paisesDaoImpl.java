/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Tpais;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Paises;
import util.HibernateUtil;

/**
 *
 * @author root
 */
public class paisesDaoImpl implements paisesDao {

    private Tpais pais;
    private List<Tpais> listar;

    private Session session;
    private Transaction trans;

    @Override
    public List<Tpais> lstPaises() {

        Session session = HibernateUtil.getSession();

        String sql = "FROM Tpais";
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
    public boolean crearPais(Tpais pais) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.save(pais);
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
    public boolean editarPais(Tpais pais) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            session.update(pais);
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
    public boolean borrarPais(Integer id) {
        boolean flag = false;
        try {
            session = HibernateUtil.getSession();
            trans = session.beginTransaction();
            Tpais pais = (Tpais) session.load(Tpais.class, id);
            session.delete(pais);
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
    public List<Paises> obtenerPaises() {
        
        List<Paises> lp = new ArrayList<Paises>();
        Session session = HibernateUtil.getSession();

        String sql = "FROM Tpais";
        Transaction tx = null;

        try {

            tx = session.beginTransaction();
            listar = session.createQuery(sql).list();
      
            
            for (Tpais p1 : listar) 
            {
          
                Paises p = new Paises();
                p.setId(p1.getId().toString());
                p.setNombre(p1.getNomPais());
                lp.add(p);
            }
            
            tx.commit();

            //    session.beginTransaction().commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                lp = null;
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return lp;
  
    }

}
