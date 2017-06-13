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
public class Grafo {

    private NodoVer inicio;

    public Grafo() {
        inicio = null;
    }

    public NodoVer ubicarVertice(String buscado) {
        NodoVer aux = this.inicio;
        while (aux != null && !aux.getElemento().equals(buscado)) {
            aux = aux.getSigVer();
        }
        return aux;
    }

    public boolean insertarVertice(String elemento) {
        boolean seInserto = false;
        NodoVer nVertice = ubicarVertice(elemento);
        if (nVertice == null) {
            this.inicio = new NodoVer(elemento, inicio);
            seInserto = true;
        }
        return seInserto;
    }

    public boolean insertarArco(String ori, String des, double etiqueta) {
        boolean seInserto = false;
        NodoVer origen = ubicarVertice(ori);
        if (origen != null) {
            NodoVer destino = ubicarVertice(des);
            if (destino != null) {
                NodoAdy adyacente = origen.getPrimerAdy();
                if (adyacente != null) {
                    while (adyacente != null) {
                        adyacente = adyacente.getSigAdy();
                    }
                    adyacente.setSigAdy(new NodoAdy(destino, etiqueta, null));
                } else {
                    origen.setPrimerAdy(new NodoAdy(destino, etiqueta, null));
                }
                seInserto = true;
            }
        }
        return seInserto;
    }

    public boolean eliminarVertice(String elemento) {
        boolean seElimino = false;
        NodoVer anterior = inicio;
        if (anterior.getElemento().equals(elemento)) {
            inicio = inicio.getSigVer();
            seElimino = true;
        } else {
            while (anterior.getSigVer() != null && !seElimino) {
                if (anterior.getSigVer().getElemento().equals(elemento)) {
                    anterior.setSigVer(anterior.getSigVer().getSigVer());
                    seElimino = true;
                } else {
                    anterior = anterior.getSigVer();
                }
            }
        }
        if (seElimino) {
            eliminarAdyacentesAlVertice(elemento);
        }
        return seElimino;
    }

    private boolean eliminarAdyacentesAlVertice(String elemento) {
        boolean seElimino = false;
        NodoVer vertAux = inicio;
        NodoAdy adyAux;
        while (vertAux != null) {
            adyAux = vertAux.getPrimerAdy();
            if (adyAux != null) {
                if (adyAux.getVertice().getElemento().equals(elemento)) {
                    adyAux.setSigAdy(adyAux.getSigAdy());
                    seElimino = true;
                } else {
                    while (adyAux.getSigAdy() != null && !seElimino) {
                        if (adyAux.getSigAdy().getVertice().getElemento().equals(elemento)) {
                            adyAux.setSigAdy(adyAux.getSigAdy().getSigAdy());
                            seElimino = true;
                        } else {
                            adyAux = adyAux.getSigAdy();
                        }
                    }
                }
            }
            vertAux = vertAux.getSigVer();
        }
        return seElimino;
    }

    public boolean eliminarAdyacente(String origen, String destino) {
        boolean seElimino = false;
        NodoVer auxVer = ubicarVertice(origen);
        if (auxVer != null) {
            NodoAdy auxAdy = auxVer.getPrimerAdy();
            if (auxAdy != null) {
                if (auxAdy.getVertice().getElemento().equals(destino)) {
                    auxVer.setPrimerAdy(auxAdy.getSigAdy());
                    seElimino = true;
                } else {
                    while (auxAdy.getSigAdy() != null && !seElimino) {
                        if (auxAdy.getSigAdy().getVertice().getElemento().equals(destino)) {
                            auxAdy.setSigAdy(auxAdy.getSigAdy().getSigAdy());
                            seElimino = true;
                        } else {
                            auxAdy = auxAdy.getSigAdy();
                        }

                    }
                }
            }
        }
        return seElimino;
    }
    
    

}
