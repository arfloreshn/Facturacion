package modelo;
// Generated 05-13-2019 07:40:13 AM by Hibernate Tools 4.3.1



/**
 * TdeptoEmp generated by hbm2java
 */
public class TdeptoEmp  implements java.io.Serializable {


     private Integer id;
     private String nomDepto;

    public TdeptoEmp() {
    }

    public TdeptoEmp(String nomDepto) {
       this.nomDepto = nomDepto;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNomDepto() {
        return this.nomDepto;
    }
    
    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }




}

