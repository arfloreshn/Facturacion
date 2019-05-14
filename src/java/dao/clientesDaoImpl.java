/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Clientes;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.uh;

/**
 *
 * @author root
 */
public class clientesDaoImpl implements clientesDao {

    private List<Clientes> listar;

    @Override
    public List<Clientes> lstclientes() {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        String sql = "FROM Clientes";

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
    } //To change body of generated methods, choose Tools | Templates.

    @Override
    public boolean crearCliente(Clientes obj) {
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
            throw ex;
        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editarCliente(Clientes obj) {
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
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
    public boolean borrarCliente(Integer id) {
        boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Clientes obj = (Clientes) session.load(Clientes.class, id);
            session.delete(obj);
            tx.commit();
            flag = true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            tx.rollback();
            throw ex;
        } finally {

            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }

        }
        return flag;
    }

    /**
     *
     * @param session
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Clientes buscaClienteXid(Session session, Integer p_id) throws Exception {
        String sSql = "FROM Clientes WHERE id=:p_id";
        Query q = session.createQuery(sSql);
        q.setParameter("p_id", p_id);
        return (Clientes) q.uniqueResult();

    }

}
