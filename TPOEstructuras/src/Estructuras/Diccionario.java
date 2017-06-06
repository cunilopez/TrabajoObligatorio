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
        boolean exito;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(ciudad, clave);
            exito = true;
        } else {
            exito = insertarAux(ciudad, clave, raiz, null);
        }
        return exito;
    }

    public boolean insertarAux(Ciudad ciudad, String clave, NodoAVL actual, NodoAVL padre) {
        boolean exito = false;
        if (!actual.getClave().equals(clave)) //Clave no repetida.
        {
            if (actual.getClave().compareTo(clave) > 0)// Clave mayor alfabeticamente.
            {
                if (actual.getHijoDerecho() == null) {
                    actual.setHijoDerecho(new NodoAVL(ciudad, clave));//Inserta hijo derecho.
                    exito = true;
                } else {
                    exito = insertarAux(ciudad, clave, actual.getHijoDerecho(), actual);// Baja por la derecha.                    
                }
            } else {
                if (actual.getHijoIzquierdo() == null) {
                    actual.setHijoIzquierdo(new NodoAVL(ciudad, clave));// Inserta hijo izquierdo.
                    exito = true;
                } else {
                    exito = insertarAux(ciudad, clave, actual.getHijoIzquierdo(), actual);//Baja por la izquierda.
                }
            }

        }
        if (exito) {
            actual.setAltura(altura(actual));
            balancear(actual,padre);
        }

        return exito;
    }

    private int altura(NodoAVL actual) {
        return alturaAux(actual);

    }

    private int alturaAux(NodoAVL actual) {
        int altD = 0, altI = 0, alt;
        if (actual != null) {
            if (actual.getHijoIzquierdo() != null) {
                altI = 1 + alturaAux(actual.getHijoIzquierdo());
            }
            if (actual.getHijoDerecho() != null) {
                altD = 1 + alturaAux(actual.getHijoDerecho());
            }
            alt = (altI >= altD) ? altI : altD;

        } else {
            alt = -1;
        }
        return alt;
    }

    private void balancear(NodoAVL actual, NodoAVL padre) {
        int balance;
        NodoAVL aux;
        balance = balance(actual);
        if (balance < -1) {
            aux = actual.getHijoDerecho();
            balance = balance(aux);
            if (balance == 1) {
                actual.setHijoDerecho(rotacionDerecha(aux));
            }
            if (padre == null) {
                raiz = rotacionIzquierda(actual);
            } else {
                if (actual.getClave().compareTo(padre.getClave()) < 0) {
                    padre.setHijoIzquierdo(rotacionIzquierda(actual));
                } else {
                    padre.setHijoDerecho(rotacionIzquierda(actual));
                }
                padre.setAltura(altura(padre));
            }
        }
        if (balance > 1) {
            aux = actual.getHijoIzquierdo();
            balance = balance(aux);
            if (balance == -1) {
                actual.setHijoIzquierdo(rotacionIzquierda(aux));
            }
            if (padre == null) {
                raiz = rotacionDerecha(actual);
            } else {
                if (actual.getClave().compareTo(padre.getClave()) < 0) {
                    padre.setHijoIzquierdo(rotacionDerecha(actual));
                } else {
                    padre.setHijoDerecho(rotacionDerecha(actual));
                }
                padre.setAltura(altura(padre));
            }
        }
    }

    private NodoAVL rotacionIzquierda(NodoAVL actual) {
        NodoAVL aux = actual.getHijoDerecho(), aux2 = aux.getHijoIzquierdo();
        aux.setHijoIzquierdo(actual);
        actual.setHijoDerecho(aux2);
        actual.setAltura(altura(actual));
        aux.setAltura(altura(aux));
        return aux;
    }

    private NodoAVL rotacionDerecha(NodoAVL actual) {
        NodoAVL aux = actual.getHijoIzquierdo(), aux2 = aux.getHijoDerecho();
        aux.setHijoDerecho(actual);
        actual.setHijoIzquierdo(aux2);
        actual.setAltura(altura(actual));
        aux.setAltura(altura(aux));
        return aux;
    }

    private int balance(NodoAVL actual) {
        int res;
        if (actual.getHijoIzquierdo() != null) {
            if (actual.getHijoDerecho() != null) {
                res = (actual.getHijoIzquierdo().getAltura() - actual.getHijoDerecho().getAltura());
            } else {
                res = actual.getHijoIzquierdo().getAltura() + 1;
            }
        } else {
            if (actual.getHijoDerecho() != null) {
                res = -1 - actual.getHijoDerecho().getAltura();
            } else {
                res = 0;
            }
        }
        return res;
    }

}
