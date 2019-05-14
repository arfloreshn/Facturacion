/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




/**
 *
 * @author root
 */
public class funciones {
    
    public static String UrlPadre()
    {
     return "http://localhost:8080/ventas/";
    }

    public static String PathHomePages()
    {
     return "vistas";
    }

    public static String PathFormUserPages()
    {
     return "/forms";
    }
    
    public static String encriptar(String cadena)
    {
        StringBuilder sb = new StringBuilder();
        
         try {
            
             MessageDigest md = MessageDigest.getInstance("SHA-512");
             md.update(cadena.getBytes());
             byte[] mb = md.digest();
            
             for (int i = 0; i < mb.length; i++)
             {
                 sb.append(Integer.toString((mb[i] & 0xff) + 0x100,16).substring(1));
             } 
             
        } catch (NoSuchAlgorithmException ex) 
        {
        
        }
        
      return sb.toString();
    }   
    
  public static java.util.Date getFechaHoy() {
        return new java.util.Date();
    }
    
    public static java.sql.Timestamp getFechaHoraHoy() {
        long ms = getFechaHoy().getTime();
        java.sql.Timestamp ts = new java.sql.Timestamp(ms);
        return ts;
    }    
   
}
