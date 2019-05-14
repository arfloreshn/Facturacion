/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Producto;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author root
 */
public class productoDaoImpl implements productoDao{

    private List<Producto> listar;
    
    @Override
    public List<Producto> lstProductos() {
   
        Session session =  HibernateUtil.getSession();
        Transaction tx = null;
        
        String sql = "FROM Producto";
        
         
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
    public boolean crearProducto(Producto obj) {
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
    public boolean editarProducto(Producto obj) {
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
                    if (session!= null) 
                    {
                        session.close();
                    }

        }
        return flag;

    }

    @Override
    public boolean borrarProducto(Integer id) {
         boolean flag = false;
        Session session = HibernateUtil.getSession();
        Transaction tx = null;

        try {
            session = HibernateUtil.getSession();
            tx = session.beginTransaction();
            Producto obj = (Producto) session.load(Producto.class, id);
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

   @Override 
   public Producto buscaProductoxCodigo(Session session, String CodProd) throws Exception {
   String sSql = "FROM Producto WHERE codproducto=:parametro";
        Query q = session.createQuery(sSql);
        q.setParameter("parametro", CodProd);
        return (Producto) q.uniqueResult();
    
    }

  
    
}
