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

    Nodo cabecera;

    public Lista() {
        this.cabecera = null;
    }

    public void insertar(String elemento) {

        if (cabecera == null) {
            this.cabecera = new Nodo(elemento);
        } else {
            Nodo aux = new Nodo(elemento);
            aux.setEnlace(cabecera);
            cabecera = aux;
        }

    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public boolean eliminar(int pos) {
        boolean seElimino = false;
        if (pos >= 1 && pos <= this.longitud()) {
            if (pos != 1) {
                Nodo aux = cabecera;
                int i = 1;
                while (i < pos - 1) {
                    i++;
                    aux = aux.getEnlace();
                }
                aux.setEnlace(aux.getEnlace().getEnlace());
                seElimino = true;
            } else {
                cabecera = cabecera.getEnlace();
                seElimino = true;
            }
        }
        return seElimino;
    }

    public int longitud() {
        Nodo aux = cabecera;
        int longitud = 0;
        if (!esVacia()) {
            longitud++;
            while (aux.getEnlace() != null) {
                longitud++;
                aux = aux.getEnlace();
            }
        }

        return longitud;
    }

    public boolean pertenece(String elem) {
        boolean pertenece = false;
        Nodo aux = cabecera;
        while (aux != null && !pertenece) {
            if (!aux.getElemento().equals(elem)) {
                aux = aux.getEnlace();
            } else {
                pertenece = true;
            }
        }
        return pertenece;
    }

    @Override
    public String toString() {
        String cad = "";
        if (!esVacia()) {
            Nodo aux = cabecera;
            for (int i = 1; i <= this.longitud(); i++) {
                cad = cad + aux.getElemento() + " ";
                aux = aux.getEnlace();
            }
        } else {
            cad = "Vacia!";
        }

        return cad;
    }
}
