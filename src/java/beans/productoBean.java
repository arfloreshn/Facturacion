/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.productoDao;
import dao.productoDaoImpl;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import modelo.Producto;

/**
 *
 * @author root
 */
@Named(value = "productoBean")
@ViewScoped
public class productoBean implements java.io.Serializable {

    
    private List<Producto> lstProductos;

    public List<Producto> getLstProductos() {
       productoDao pDao = new productoDaoImpl();
       this.lstProductos = pDao.lstProductos();
       return lstProductos;
    }
    private Producto obj;

    public Producto getObj() {
        return obj;
    }

    public void setObj(Producto obj) {
        this.obj = obj;
    }
    
    
    /**
     * Creates a new instance of productoBean
     */
    public productoBean() {
    }
    
}
