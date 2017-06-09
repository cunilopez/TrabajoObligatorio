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
public class NodoVer {
    private String elemento;
    private NodoVer sigVer;
    private NodoAdy primerAdy;
    
    public NodoVer(String elem, NodoVer vertice){
        this.elemento=elem;
        this.sigVer= vertice;
        this.primerAdy=null;
               
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public NodoVer getSigVer() {
        return sigVer;
    }

    public void setSigVer(NodoVer sigVer) {
        this.sigVer = sigVer;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }
    
    
}
