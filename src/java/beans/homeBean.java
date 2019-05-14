/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import util.funciones;

/**
 *
 * @author root
 */
@Named(value = "homeBean")
@ApplicationScoped
public class homeBean implements java.io.Serializable {

    /**
     * Creates a new instance of homeBean
     */
    public homeBean() {
    }
    
    public String getHomeUrl()
    {
     return funciones.UrlPadre();
    }
    
    
    public String getForms()
    {
        String path = funciones.PathFormUserPages();
        return path;
    }
    
 
}
