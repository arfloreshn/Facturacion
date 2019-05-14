package modelo;
// Generated 05-13-2019 07:40:13 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Facmae generated by hbm2java
 */
public class Facmae  implements java.io.Serializable {


     private Integer idfactura;
     private Clientes clientes;
     private int nroFactura;
     private Date fecfactura;
     private double subtotal;
     private double iva;
     private double totalventa;
     private Set facdets = new HashSet(0);

    public Facmae() {
    }

	
    public Facmae(Clientes clientes, int nroFactura, Date fecfactura, double subtotal, double iva, double totalventa) {
        this.clientes = clientes;
        this.nroFactura = nroFactura;
        this.fecfactura = fecfactura;
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalventa = totalventa;
    }
    public Facmae(Clientes clientes, int nroFactura, Date fecfactura, double subtotal, double iva, double totalventa, Set facdets) {
       this.clientes = clientes;
       this.nroFactura = nroFactura;
       this.fecfactura = fecfactura;
       this.subtotal = subtotal;
       this.iva = iva;
       this.totalventa = totalventa;
       this.facdets = facdets;
    }
   
    public Integer getIdfactura() {
        return this.idfactura;
    }
    
    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public int getNroFactura() {
        return this.nroFactura;
    }
    
    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }
    public Date getFecfactura() {
        return this.fecfactura;
    }
    
    public void setFecfactura(Date fecfactura) {
        this.fecfactura = fecfactura;
    }
    public double getSubtotal() {
        return this.subtotal;
    }
    
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public double getIva() {
        return this.iva;
    }
    
    public void setIva(double iva) {
        this.iva = iva;
    }
    public double getTotalventa() {
        return this.totalventa;
    }
    
    public void setTotalventa(double totalventa) {
        this.totalventa = totalventa;
    }
    public Set getFacdets() {
        return this.facdets;
    }
    
    public void setFacdets(Set facdets) {
        this.facdets = facdets;
    }




}


