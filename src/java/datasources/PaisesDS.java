/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datasources;

import dao.paisesDao;
import dao.paisesDaoImpl;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

import pojos.Paises;

/**
 *
 * @author root
 */
@Named(value = "paisesDS")
@ManagedBean
@SessionScoped
public class PaisesDS {

    public List<Paises> lstpais = new ArrayList<Paises>();

    public PaisesDS() {
        paisesDao pdao = new paisesDaoImpl();
        this.lstpais = pdao.obtenerPaises();
    }

    public List<Paises> getLstpais() {
        return lstpais;
    }

    public void setLstpais(List<Paises> lstpais) {
        this.lstpais = lstpais;
    }

    public List<Paises> queryByName(String name) {
        // Assumed search using the startsWith

        paisesDao pdao = new paisesDaoImpl();
        this.lstpais = pdao.obtenerPaises();

        List<Paises> queried = new ArrayList<Paises>();
        for (Paises paises : this.lstpais) {
            if (paises.getNombre().toUpperCase().startsWith(name.toUpperCase())) {
                queried.add(paises);
            }
        }
        return queried;
    }

}
