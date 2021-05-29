/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Ctistian Torres
 */
public class Producto {
    //Atributos
    private String codArticulo;
    private String seccion;
    private String nomArticulo;
    private double precio;
    private Date fecha; 
    private String importado;
    private String paisOrigen;
    
    //--------------------------------------------------------------------------
    //Constructor
    public Producto(String codArticulo, String seccion, String nomArticulo, 
                double precio, Date fecha, String importado, String paisOrigen) {
        this.codArticulo = codArticulo;
        this.seccion = seccion;
        this.nomArticulo = nomArticulo;
        this.precio = precio;
        this.fecha = fecha;
        this.importado = importado;
        this.paisOrigen = paisOrigen;
    }
    public Producto(String seccion, String nomArticulo, double precio, Date fecha, 
                String importado, String paisOrigen) {
        this.seccion = seccion;
        this.nomArticulo = nomArticulo;
        this.precio = precio;
        this.fecha = fecha;
        this.importado = importado;
        this.paisOrigen = paisOrigen;
    }

    
    
    //---------------------------------------------------------------------------
    //Listado de los atributos
    @Override
    public String toString() {
        return "Producto{" + "codArticulo=" + codArticulo + ", seccion=" + seccion + ", nomArticulo=" + nomArticulo + ", precio=" + precio + ", fecha=" + fecha + ", importado=" + importado + ", paisOrigen=" + paisOrigen + '}';
    }  
    
    //---------------------------------------------------------------------------
    //metodos de acceso

    public String getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(String codArticulo) {
        this.codArticulo = codArticulo;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getNomArticulo() {
        return nomArticulo;
    }

    public void setNomArticulo(String nomArticulo) {
        this.nomArticulo = nomArticulo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getImportado() {
        return importado;
    }

    public void setImportado(String importado) {
        this.importado = importado;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
    
    
    
}
