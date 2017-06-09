/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

/**
 *
 * @author Soporte-1
 */
public class NodoAdy {
    private NodoVer vertice;
    private double etiqueta;
    private NodoAdy sigAdy;
    
    public NodoAdy(NodoVer vertice, double etiqueta, NodoAdy siguiente){
    this.vertice= vertice;
    this.etiqueta= etiqueta;
    sigAdy=siguiente;
    }

    public NodoVer getVertice() {
        return vertice;
    }

    public void setVertice(NodoVer vertice) {
        this.vertice = vertice;
    }

    public double getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(double etiqueta) {
        this.etiqueta = etiqueta;
    }

    public NodoAdy getSigAdy() {
        return sigAdy;
    }

    public void setSigAdy(NodoAdy sigAdy) {
        this.sigAdy = sigAdy;
    }
    
}
