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
        Nodo aux = cabecera;
        if (aux != null) {
            while (aux.getEnlace() != null) {
                aux = aux.getEnlace();
            }
            aux.setEnlace(new Nodo(elemento));
        } else {
            cabecera = new Nodo(elemento);
        }

    }

    public boolean insertar(String elem, int pos) {
        boolean seInserto = false;
        if (pos >= 0 || pos < this.longitud() + 1) {
            if (pos != 0) {
                Nodo aux = cabecera;
                int i = 0;
                while (i < pos - 1) {
                    i++;
                    aux = aux.getEnlace();
                }
                aux.setEnlace(new Nodo(elem, aux.getEnlace()));
                seInserto = true;
            } else {
                cabecera = new Nodo(elem, this.cabecera);
                seInserto = true;
            }
        }
        return seInserto;
    }

    public boolean esVacia() {
        return this.cabecera == null;
    }

    public boolean eliminar(int pos) {
        boolean seElimino = false;
        if (pos >= 1 && pos < this.longitud() + 1) {
            if (pos != 1) {
                Nodo aux = cabecera;
                int i = 0;
                while (i < pos - 2) {
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

    public int getPos(String elemento) {
        int pos = -1, cont = 0;
        boolean bandera = false;
        Nodo aux = this.cabecera;
        while (!bandera && aux != null) {
            if (aux.getElemento().equals(elemento)) {
                pos = cont;
                bandera = true;
            }
            cont++;
            aux = aux.getEnlace();
        }
        return pos;
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

    public Lista clonar() {
        Lista clon = new Lista();
        Nodo aux = cabecera;
        while (aux != null) {
            clon.insertar(aux.getElemento());
            aux = aux.getEnlace();
        }
        return clon;
    }

    @Override
    public String toString() {
        String cad = "";
        if (!esVacia()) {
            Nodo aux = cabecera;
            for (int i = 0; i < this.longitud(); i++) {
                cad = cad + aux.getElemento() + " ";
                aux = aux.getEnlace();
            }
        } else {
            cad = "Vacia!";
        }

        return cad;
    }

   public String recuperar(int pos) {
        String elem = "";
        if (cabecera != null && (pos > 0 || pos < this.longitud())) {
            if (pos == 0) {
                elem = cabecera.getElemento();
            } else {
                Nodo aux = cabecera;
                int cont = 0;
                while (aux != null) {
                    if (cont != pos) {
                        aux = aux.getEnlace();
                        cont++;
                    } else {
                        elem = aux.getElemento();
                        aux = null;
                    }
                }
            }
        }
        return elem;
    }
}
