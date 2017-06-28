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
public class Nodo {

    Nodo enlace;
    String elemento;

    public Nodo(String elemento) {
        this.elemento = elemento;
        this.enlace = null;
    }

    public Nodo(String elemento, Nodo enlace) {
        this.elemento = elemento;
        this.enlace = enlace;
    }

    public Nodo getEnlace() {
        return enlace;
    }

    public void setEnlace(Nodo enlace) {
        this.enlace = enlace;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    @Override
    public String toString() {//debug
        return this.elemento;
    }

}
