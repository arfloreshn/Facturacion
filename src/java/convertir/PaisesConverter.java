/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertir;

import datasources.PaisesDS;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.bean.ManagedProperty;


import pojos.Paises;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
@FacesConverter(value = "PaisesConverter")
public class PaisesConverter implements Converter {

        @ManagedProperty("#{paisesDS}")
	private PaisesDS ds;

	public PaisesDS getDs() {
		return ds;
	}

	public void setDs(PaisesDS ds) {
		this.ds = ds;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String value) {
		for(Paises p : ds.getLstpais()){
			if(p.getId().equals(value)){
				return p;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object value) {
		if(value instanceof Paises){
			Paises paises = (Paises)value;
			return paises.getId();
		}
		return "";
	}
    
}