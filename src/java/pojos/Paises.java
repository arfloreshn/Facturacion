/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author root
 */
public class Paises {
    
   private String id;
   private String nombre;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
   
    	public boolean equals(Object obj){
		if(obj instanceof Paises){
			Paises pais = (Paises)obj;
			if(this.id.equals(pais.getId())){
				return true;
			}
		}
		return false;
	}

	public int hashCode(){
		return this.id.hashCode();
	} 
    
}
