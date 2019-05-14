/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.clientesDao;
import dao.clientesDaoImpl;
import javax.inject.Named;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import modelo.Clientes;

/**
 *
 * @author root
 */
@Named(value = "clientesBean")
@ViewScoped
public class clientesBean implements java.io.Serializable
{
    private Clientes clie;
    private List<Clientes> lista;

   
    private String msg;
  
    
      public void instanciar()
    {
      this.clie = new Clientes();
    }
    /**
     * Creates a new instance of clientesBean
     */
    public clientesBean() {
     this.clie = new Clientes();
    }
    
    public Clientes getClie() {
        return clie;
    }

    public void setClie(Clientes clie) {
        this.clie = clie;
    }
   
    
    public List<Clientes> getLista() {
      clientesDao cDao = new clientesDaoImpl();
      this.lista = cDao.lstclientes();
    
       return lista;
    } 
    
    
    public void btnCrearCliente(ActionEvent actionevent)
    {
         this.msg = "";
         clientesDao cDao = new clientesDaoImpl();
         if(cDao.crearCliente(this.clie)){
            msg = "Registro creado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en registro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.clie = new Clientes();
         
    }
    
     public void btnEditarCliente(ActionEvent actionevent)
    {
         this.msg = "";
         clientesDao cDao = new clientesDaoImpl();
         if(cDao.editarCliente(this.clie)){
            msg = "Registro editado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en editar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.clie = new Clientes();
         
    }
     
     
      public void btnborrarCliente(ActionEvent actionevent)
    {
         this.msg = "";
         clientesDao cDao = new clientesDaoImpl();
         if(cDao.borrarCliente(this.clie.getId())){
            msg = "Registro borrado";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);

        } else {
            msg = "Fallo en borrad";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(msg, message);
        };
        this.clie = new Clientes();
         
    }
     
    
}
