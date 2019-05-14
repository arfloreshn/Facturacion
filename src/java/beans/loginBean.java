/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import dao.usuarioDao;
import dao.usuarioDaoImpl;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import modelo.Usuario;

import static util.funciones.PathHomePages;


/**
 *
 * @author root
 */
@Named(value = "loginBean")
@SessionScoped
public class loginBean implements Serializable 
{
    private static final long serialVersionUID = -2152389656664659476L;
    
    private Usuario user;
    
      /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
 
          this.user  = new Usuario();
    }

    
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
 
    
   public void login(ActionEvent event) 
    {
        
        RequestContext context = RequestContext.getCurrentInstance();
        
        FacesMessage message = null;
        boolean loggedIn = false;
        
        String path = "";
 
        usuarioDao usuarioDao = new usuarioDaoImpl();
    
        this.user = usuarioDao.validarlogin(this.user);
        
        if(this.user != null) 
        {
            loggedIn = true;
            
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.user.getUsuario());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.user.getUsuario());
            path = PathHomePages() + "/principal.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "fallo de autenticacion", "autenticacion invalida");
             this.user = new Usuario();
        }
         
       FacesContext.getCurrentInstance().addMessage(null, message);
       context.addCallbackParam("loggedIn", loggedIn);
       context.addCallbackParam("PathHomePages", path);
    } 


     public  String CerrarSession() 
    {
       HttpSession httpsession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
       return "/faces/login.xhtml";
    }
}

