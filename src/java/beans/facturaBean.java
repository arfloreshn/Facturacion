/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.clientesDao;
import dao.clientesDaoImpl;
import dao.facturaDao;
import dao.facturaDaoImpl;
import dao.facturadetalleDao;
import dao.facturadetalleDaoImpl;
import dao.productoDao;
import dao.productoDaoImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Clientes;
import modelo.Facdet;
import modelo.Facmae;
import modelo.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import util.HibernateUtil;
import util.funciones;

import util.uh;

/**
 *
 * @author root
 */
@Named(value = "facturaBean")
@ViewScoped
public class facturaBean implements java.io.Serializable {

    Session session;
    Transaction tx;

    private Clientes cliente;
    private Producto producto;
    private Integer nro_cliente;
    private Integer lineas;
    private String codbarra;
    private Date fecha;

    private double var_cantidad;
    private Integer var_cantidad2;

    private String pCodProducto;
    private Facmae objFactura;
    private Facdet objDetalle;

    private List<Facdet> lstdetallefactura;

    private String msg = "";
    private FacesMessage message = null;

    private Integer var_nroFactura;

    private Double var_subtot = new Double(0);
    private Double var_total = new Double(0);

    /**
     * Creates a new instance of facturaBean
     */
    public facturaBean() {
        this.var_cantidad = 0;
        this.var_cantidad2 = null;
        this.message = null;
        this.objFactura = new Facmae();
        this.cliente = new Clientes();
        this.var_total = 0.00;
        this.var_subtot = 0.00;
        this.lstdetallefactura = new ArrayList<>();
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public int getNro_cliente() {
        return nro_cliente;
    }

    public void setNro_cliente(int nro_cliente) {
        this.nro_cliente = nro_cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getCodbarra() {
        return codbarra;
    }

    public void setCodbarra(String codbarra) {
        this.codbarra = codbarra;
    }

    public double getVar_cantidad() {
        return var_cantidad;
    }

    public void setVar_cantidad(double var_cantidad) {
        this.var_cantidad = var_cantidad;
    }

    public String getpCodProducto() {
        return pCodProducto;
    }

    public void setpCodProducto(String pCodProducto) {
        this.pCodProducto = pCodProducto;
    }

    public Facmae getObjFactura() {
        return objFactura;
    }

    public void setObjFactura(Facmae objFactura) {
        this.objFactura = objFactura;
    }

    public Integer getVar_cantidad2() {
        return var_cantidad2;
    }

    public void setVar_cantidad2(Integer var_cantidad2) {
        this.var_cantidad2 = var_cantidad2;
    }

    public Integer getVar_nroFactura() {
        return var_nroFactura;
    }

    public void setVar_nroFactura(Integer var_nroFactura) {
        this.var_nroFactura = var_nroFactura;
    }

    public Double getVar_total() {
        return var_total;
    }

    public void setVar_total(Double var_total) {
        this.var_total = var_total;
    }

    // Este metodo se ejecuta, cuando el usuario oprime el btn seleccionar del cliente
    public void cmdseleccionCliente(Integer nro_id) {
        this.session = null;
        this.tx = null;
        this.msg = null;
        try {

            if (nro_id == null && nro_id == 0) {
                return;
            }

            clientesDao cDao = new clientesDaoImpl();
            this.session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            this.cliente = cDao.buscaClienteXid(this.session, nro_id);

            tx.commit();
            if (this.cliente != null) {
                msg = "Codigo de cliente correcto";
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
                this.nro_cliente = this.cliente.getId();

            } else {
                msg = "Codigo de cliente, no existe";
                //  FacesMessage mensaje = new FacesMessage("Campos Requeridos", msg);
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            }
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.tx.rollback();

        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }
        }

    }

    // Este metodo se ejecuta, cuando el usuario ingresa un numero en el textbox id_cliente
    public void cmdseleccionCliente2() {
        this.session = null;
        this.tx = null;
        this.msg = null;
        try {

            if (this.nro_cliente == null && this.nro_cliente <= 0) {
                return;
            }

            clientesDao cDao = new clientesDaoImpl();
            this.session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            this.cliente = cDao.buscaClienteXid(this.session, this.nro_cliente);

            //Validamos si el objeto cliente viene con data
            if (this.cliente != null) {
                msg = "Codigo de cliente correcto";
                this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            } else {
                msg = "Codigo de cliente no existe" + this.nro_cliente.toString();
                this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            }

            tx.commit();

            this.nro_cliente = this.cliente.getId();
            FacesContext.getCurrentInstance().addMessage(null, this.message);

        } catch (Exception e) {
            this.tx.rollback();
            FacesContext.getCurrentInstance().addMessage(e.getMessage(), this.message);

        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }
        }

    }

    public List<Facdet> getLstdetallefactura() {
        return lstdetallefactura;
    }

    public void setLstdetallefactura(List<Facdet> lstdetallefactura) {
        this.lstdetallefactura = lstdetallefactura;
    }

    //Este metodo se invoca desde la pagina web y le pasa la variable this.pCodProducto al metodo add_item_producto
    public void solicitarCantidad(String var_cod_producto) {
        this.pCodProducto = var_cod_producto;
    }

    // Este metodo se ejecuta, cuando el usuario oprime el btn seleccionar el codigo de barra
    public void add_item_Producto1() {
        this.session = null;
        this.tx = null;

        double subtotal = 0.00;
        try {

            productoDao pDao = new productoDaoImpl();
            this.session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            this.producto = pDao.buscaProductoxCodigo(this.session, this.pCodProducto);

            this.pCodProducto = null;

            if (this.producto != null) {

                this.lineas = this.lstdetallefactura.size() + 1;

                this.objDetalle = new Facdet();
                this.objDetalle.setIdRow(this.lineas);
                this.objDetalle.setNro_item(this.lineas);
                this.objDetalle.setProducto(this.producto);
                this.objDetalle.setCantidad(this.var_cantidad);
                this.objDetalle.setPreciovta(this.producto.getPrecioVenta());
                this.objDetalle.setSubtotal(this.var_cantidad * this.producto.getPrecioVenta());
                this.lstdetallefactura.add(this.objDetalle);

            }

            tx.commit();

            // Totalizamos la factura
            totalizar_factura();

            this.var_cantidad = 0;
            msg = "producto ingresado";
            this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(this.msg, message);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            this.tx.rollback();
            msg = "error en codigo 1";
            this.message = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, null);
            FacesContext.getCurrentInstance().addMessage(this.msg, message);

        } finally {
            // Si la session esta abierta la cierro
            if (session != null) {
                session.close();
            }
        }

    }

    // Este metodo busca y selecciona un producto, cuando este es ingresado o digitado el textbox del codigo de barra
    public void solicitaCantidad2() {

        this.session = null;
        this.tx = null;
        try {

            if (this.codbarra == null) {
                return;
            }

            productoDao pDao = new productoDaoImpl();
            this.session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();
            this.producto = pDao.buscaProductoxCodigo(this.session, this.codbarra);
            tx.commit();

            this.codbarra = null;
            if (this.producto != null) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgCantidad2').show();");
            }

            this.var_cantidad = 0;
            this.var_cantidad2 = null;

        } catch (Exception e) {
            this.codbarra = null;
            System.out.println(e.getMessage());
            this.tx.rollback();

        } finally {
            // Si la session esta abierta la cierro
            this.codbarra = null;
            if (session != null) {
                session.close();
            }
        }

    }

    // Este metodo busca y selecciona un producto, cuando este es ingresado o digitado el textbox del codigo de barra
    public void add_item_Producto2() {

        this.lineas = this.lstdetallefactura.size() + 1;

        this.objDetalle = new Facdet();
        this.objDetalle.setCantidad(this.var_cantidad2);
        this.objDetalle.setIdRow(this.lineas);
        this.objDetalle.setNro_item(this.lineas);
        this.objDetalle.setPreciovta(this.producto.getPrecioVenta());
        this.objDetalle.setSubtotal(this.var_cantidad * this.producto.getPrecioVenta());

        this.lstdetallefactura.add(this.objDetalle);

        this.var_cantidad2 = null;
        // Totalizamos la factura
        totalizar_factura();

        this.msg = "producto correcto";
        this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.msg, null);
        FacesContext.getCurrentInstance().addMessage(this.msg, message);

    }

    public void totalizar_factura() {

        try {

            this.var_total = 0.00;
            this.var_subtot = 0.00;

            for (Facdet items : lstdetallefactura) {
                var_subtot = (items.getPreciovta() * items.getCantidad());
                items.setSubtotal(var_subtot);
                var_total = var_total + var_subtot;
            }

            this.objFactura.setTotalventa(var_total);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void getnrofactura() {

        try {

            this.var_total = 0.00;
            this.var_subtot = 0.00;
            this.lstdetallefactura = new ArrayList<>();

            facturaDao fDao = new facturaDaoImpl();
            this.session = uh.getSessionFactory().openSession();
            tx = session.beginTransaction();

            //Validamos si hay registros en la tabla de facturas
            this.var_nroFactura = fDao.verificarRegistros(session).intValue();

            if (this.var_nroFactura <= 0 || this.var_nroFactura == null) {
                // En caso de no existir registros se inicializa con 1  
                this.var_nroFactura = 1;
            } else {

                // Convertir a long el resultado de la consulta
                this.objFactura = fDao.getNroFactura(session);
                this.var_nroFactura = this.objFactura.getIdfactura() + 1;
            }

            //Confirmo los cambios
            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //Cancelo los cambios en caso de error
            this.tx.rollback();
        }

    }

    public void limpiarFormulario() {

        this.cliente = new Clientes();
        this.producto = new Producto();
        this.objFactura = new Facmae();
        this.lstdetallefactura = new ArrayList<>();
        this.nro_cliente = 0;
        this.codbarra = null;
        this.var_cantidad = 0;
        this.pCodProducto = null;
        this.var_cantidad2 = null;
        this.var_nroFactura = null;
        this.var_total = 0.00;
        this.var_subtot = 0.00;
        btnDisable();

    }

    public void guardarVenta() {

        Session session2;
        Transaction tx2 = null;

        try {

            productoDao pDao = new productoDaoImpl();
            facturaDao fDao = new facturaDaoImpl();
            facturadetalleDao detalleDao = new facturadetalleDaoImpl();
            this.fecha = (Date) funciones.getFechaHoy();

            session2 = uh.getSessionFactory().openSession();
            tx2 = session2.beginTransaction();

            //Llenado el encabezado de la factura
            this.objFactura = new Facmae();
            this.objFactura.setNroFactura(this.var_nroFactura);
            this.objFactura.setClientes(this.cliente);
            this.objFactura.setFecfactura(this.fecha);
            this.objFactura.setIva(0.00);
            this.objFactura.setSubtotal(0.00);
            this.objFactura.setTotalventa(this.var_total);

            fDao.guardarFactura(session2, this.objFactura);

            this.lineas = 0;
            for (Facdet items : lstdetallefactura) {
                this.lineas = this.lineas++;
                items.setFacmae(this.objFactura);
                items.setNro_item(this.lineas);
                if (this.lineas > lstdetallefactura.size()) {
                    break;
                } else {
                    detalleDao.guardarDetalle(session2, items);
                }

            }

            tx2.commit();

            this.msg = "Factura registrada";
            this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.msg, null);
            FacesContext.getCurrentInstance().addMessage(this.msg, message);
            limpiarFormulario();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx2.rollback();
            this.msg = "Error grave --> " + e.getMessage();
            this.message = new FacesMessage(FacesMessage.SEVERITY_ERROR, this.msg, null);
            FacesContext.getCurrentInstance().addMessage(this.msg, message);

        }

    }

    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void btnEnable() {
        enable = true;
    }

    public void btnDisable() {
        enable = false;
    }

    public void quitarItem(String codigo) {
        int i;

        try {
            i = 0;
            for (Facdet items : lstdetallefactura) {
                this.codbarra = items.getProducto().getCodproducto();
                if (this.codbarra == codigo) {
                    this.lstdetallefactura.remove(i);
                    break;
                }

                i = i + 1;
            }

            this.msg = "Producto eliminado";
            this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.msg, null);
            FacesContext.getCurrentInstance().addMessage(this.msg, message);

            totalizar_factura();

        } catch (Exception e) {
        }

    }

    public void onRowEdit(RowEditEvent event) {
       this.msg = "Producto editado";
       this.message = new FacesMessage(FacesMessage.SEVERITY_WARN, this.msg, null);
       FacesContext.getCurrentInstance().addMessage(null, this.message);
    }

    public void onRowCancel(RowEditEvent event) {
       this.msg = "Edicion cancelada";
       this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.msg, null);
       FacesContext.getCurrentInstance().addMessage(null, this.message);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            
             this.msg = "Cell Changed Old: " + oldValue.toString() + ", New:" + newValue.toString();
             this.message = new FacesMessage(FacesMessage.SEVERITY_INFO, this.msg, null);
            FacesContext.getCurrentInstance().addMessage(null, this.message);
        }
    }
}
