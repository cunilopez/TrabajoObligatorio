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
public class Diccionario {

    private NodoAVL raiz;

    public Diccionario() {
        this.raiz = null;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public boolean insertar(Ciudad ciudad, String clave) {
        boolean exito = false;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(ciudad, clave);
            exito = true;
        } else {
            exito = insertarAux(ciudad, clave, raiz, null);
        }
        return exito;
    }

    public boolean insertarAux(Ciudad ciudad, String clave, NodoAVL nodo, NodoAVL padre) {
        boolean exito;
        if (nodo.getClave().equals(clave)) //Clave repetida.
        {
            exito = false;
        } else {
            if (nodo.getClave().compareTo(clave) > 0)// Clave mayor alfabeticamente.
            {
                if (nodo.getHijoDerecho() == null) {
                    nodo.setHijoDerecho(new NodoAVL(ciudad, clave));//Inserta hijo derecho.
                    exito = true;
                } else {
                    exito = insertarAux(ciudad, clave, nodo.getHijoDerecho(), nodo);// Baja por la derecha.                    
                }
            } else {
                if (nodo.getHijoIzquierdo() == null) {
                    nodo.setHijoIzquierdo(new NodoAVL(ciudad, clave));// Inserta hijo izquierdo.
                    exito = true;
                } else {
                    exito = insertarAux(ciudad, clave, nodo.getHijoIzquierdo(), nodo);//Baja por la izquierda.
                }
            }

        }

        return exito;
    }
    
    private void altura(NodoAVL nodo){
        
    }

}
