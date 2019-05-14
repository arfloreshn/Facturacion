package modelo;
// Generated 05-13-2019 07:40:13 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer id;
     private Rol rol;
     private String usuario;
     private String clave;
     private String correo;
     private Byte estado;
     private String usuariocreado;
     private Date fechacreacion;
     private String usuariomodifica;
     private Date fechamodifica;

    public Usuario() {
    }

	
    public Usuario(Rol rol, String usuario, String clave) {
        this.rol = rol;
        this.usuario = usuario;
        this.clave = clave;
    }
    public Usuario(Rol rol, String usuario, String clave, String correo, Byte estado, String usuariocreado, Date fechacreacion, String usuariomodifica, Date fechamodifica) {
       this.rol = rol;
       this.usuario = usuario;
       this.clave = clave;
       this.correo = correo;
       this.estado = estado;
       this.usuariocreado = usuariocreado;
       this.fechacreacion = fechacreacion;
       this.usuariomodifica = usuariomodifica;
       this.fechamodifica = fechamodifica;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public Byte getEstado() {
        return this.estado;
    }
    
    public void setEstado(Byte estado) {
        this.estado = estado;
    }
    public String getUsuariocreado() {
        return this.usuariocreado;
    }
    
    public void setUsuariocreado(String usuariocreado) {
        this.usuariocreado = usuariocreado;
    }
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
    public String getUsuariomodifica() {
        return this.usuariomodifica;
    }
    
    public void setUsuariomodifica(String usuariomodifica) {
        this.usuariomodifica = usuariomodifica;
    }
    public Date getFechamodifica() {
        return this.fechamodifica;
    }
    
    public void setFechamodifica(Date fechamodifica) {
        this.fechamodifica = fechamodifica;
    }




}

