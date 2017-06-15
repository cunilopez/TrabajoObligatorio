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
public class Lista {

    Nodo raiz;

    public Lista() {
        this.raiz = null;
    }

    public boolean insertar(String elemento) {
        boolean seInserto;

        if (raiz == null) {
            this.raiz = new Nodo(elemento);
            seInserto = true;
        } else {
            Nodo aux = new Nodo(elemento);
            aux.setEnlace(raiz);
            raiz = aux;
            seInserto = true;
        }

        return seInserto;
    }

}
