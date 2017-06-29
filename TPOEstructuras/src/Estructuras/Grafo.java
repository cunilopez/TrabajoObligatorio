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
        boolean seInserto = false, existe = false;
        NodoVer origen = ubicarVertice(ori);
        if (origen != null) {
            NodoVer destino = ubicarVertice(des);
            if (destino != null) {
                NodoAdy adyacente = origen.getPrimerAdy();
                if (adyacente != null) {
                    if (adyacente.getVertice().getElemento().equals(des)) {
                        existe = true;
                    }
                    while (adyacente.getSigAdy() != null && !existe) {
                        adyacente = adyacente.getSigAdy();
                        if (adyacente.getVertice().getElemento().equals(des)) {
                            existe = true;
                        }
                    }
                    if (!existe) {
                        adyacente.setSigAdy(new NodoAdy(destino, etiqueta, null));
                        seInserto = true;
                    }
                } else {
                    origen.setPrimerAdy(new NodoAdy(destino, etiqueta, null));
                    seInserto = true;
                }

            }
        }
        return seInserto;
    }

    public boolean eliminarVertice(String elemento) {
        boolean seElimino = false;
        NodoVer anterior = inicio;
        if (anterior != null) {
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
        }
        return seElimino;
    }

    private void eliminarAdyacentesAlVertice(String elemento) {
        NodoVer vertAux = inicio;
        NodoAdy adyAux;
        while (vertAux != null) {
            adyAux = vertAux.getPrimerAdy();
            if (adyAux != null) {
                if (adyAux.getVertice().getElemento().equals(elemento)) {
                    vertAux.setPrimerAdy(adyAux.getSigAdy());
                } else {
                    while (adyAux.getSigAdy() != null) {
                        if (adyAux.getSigAdy().getVertice().getElemento().equals(elemento)) {
                            adyAux.setSigAdy(adyAux.getSigAdy().getSigAdy());
                        } else {
                            adyAux = adyAux.getSigAdy();
                        }
                    }
                }
            }
            vertAux = vertAux.getSigVer();
        }

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

    public Lista listarProfundidad() {
        Lista visitados = new Lista();
        NodoVer aux = this.inicio;
        while (aux != null) {
            if (!visitados.pertenece(aux.getElemento())) {
                profundidadDesde(aux, visitados);
            }
            aux = aux.getSigVer();
        }

        return visitados;
    }

    private void profundidadDesde(NodoVer nodov, Lista visitados) {
        if (nodov != null) {
            visitados.insertar(nodov.getElemento());
            NodoAdy ady = nodov.getPrimerAdy();
            while (ady != null) {
                if (!visitados.pertenece(ady.getVertice().getElemento())) {
                    profundidadDesde(ady.getVertice(), visitados);
                }
                ady = ady.getSigAdy();
            }
        }
    }

    public boolean existeCamino(String origen, String destino) {
        boolean existe = false;

        NodoVer o = ubicarVertice(origen);
        NodoVer d = ubicarVertice(destino);

        if (o != null && d != null) {
            Lista visitados = new Lista();
            existe = existeCaminoAux(o, destino, visitados);
        }

        return existe;
    }

    private boolean existeCaminoAux(NodoVer origen, String destino, Lista visitados) {
        boolean existe = false;
        if (origen != null) {
            if (origen.getElemento().equals(destino)) {
                existe = true;
            } else {
                visitados.insertar(origen.getElemento());
                NodoAdy ady = origen.getPrimerAdy();
                while (!existe && ady != null) {
                    if (!visitados.pertenece(ady.getVertice().getElemento())) {
                        existe = existeCaminoAux(ady.getVertice(), destino, visitados);
                    }
                    ady = ady.getSigAdy();
                }
            }
        }
        return existe;
    }

    public Lista caminoMenorCantCiudades(String partida, String llegada) {
        Lista visitados = new Lista();
        Lista menor = new Lista();
        NodoVer vPartida = ubicarVertice(partida);
        NodoVer vLlegada = ubicarVertice(llegada);
        if (vPartida != null && vLlegada != null) {
            menor = caminoMenorCantCiudadesAux(vPartida, visitados, menor, llegada);
        }
        return menor;
    }

    private Lista caminoMenorCantCiudadesAux(NodoVer partida, Lista visitados, Lista menor, String llegada) {
        NodoAdy aux;
        Lista menorAux;
        if (!visitados.pertenece(partida.getElemento())) {
            visitados.insertar(partida.getElemento());

            if (partida.getElemento().equals(llegada)) {
                if (menor.esVacia()) {
                    menor = visitados.clonar();
                } else {
                    if (visitados.longitud() < menor.longitud()) {
                        menor = visitados.clonar();
                    }
                }
            } else {
                aux = partida.getPrimerAdy();
                while (aux != null) {
                    if (visitados.longitud() < menor.longitud() || menor.esVacia()) {
                        menorAux = caminoMenorCantCiudadesAux(aux.getVertice(), visitados, menor, llegada);
                        if (!menorAux.esVacia()) {
                            if (!menor.esVacia()) {
                                if (menorAux.longitud() < menor.longitud()) {
                                    menor = menorAux.clonar();
                                }
                            } else {
                                menor = menorAux.clonar();
                            }
                        }
                    }

                    aux = aux.getSigAdy();
                }
            }
            visitados.eliminar(visitados.longitud());

        }
        return menor;
    }

    public Lista caminoConAlojamiento(String partida, String llegada, Diccionario dicc) {
        Lista visitados = new Lista();
        Lista menor = new Lista();
        NodoVer vPartida = ubicarVertice(partida);
        NodoVer vLlegada = ubicarVertice(llegada);
        if (vPartida != null && vLlegada != null) {
            menor = caminoConAlojamientoAux(vPartida, visitados, menor, llegada, dicc);
        }
        return menor;
    }

    private Lista caminoConAlojamientoAux(NodoVer partida, Lista visitados, Lista menor, String llegada, Diccionario dicc) {
        NodoAdy auxAdy;
        Lista menorAux;
        boolean alojActual;

        if (!visitados.pertenece(partida.getElemento())) {
            visitados.insertar(partida.getElemento());
            if (partida.getElemento().equals(llegada)) {
                if (menor.esVacia()) {
                    menor = visitados.clonar();
                } else {
                    if (visitados.longitud() < menor.longitud()) {
                        menor = visitados.clonar();
                    }
                }
            } else {
                auxAdy = partida.getPrimerAdy();
                while (auxAdy != null) {
                    alojActual = dicc.recuperarElemento(auxAdy.getVertice().getElemento()).tieneAlojamiento();
                    if (alojActual || auxAdy.getVertice().getElemento().equals(llegada)) {
                        menorAux = caminoConAlojamientoAux(auxAdy.getVertice(), visitados, menor, llegada, dicc);
                        if (!menorAux.esVacia()) {
                            if (!menor.esVacia()) {
                                if (menorAux.longitud() < menor.longitud()) {
                                    menor = menorAux.clonar();
                                }
                            } else {
                                menor = menorAux.clonar();
                            }
                        }
                    }
                    auxAdy = auxAdy.getSigAdy();
                }
            }
            visitados.eliminar(visitados.longitud());

        }
        return menor;
    }

    @Override
    public String toString() {
        String cad = "";
        NodoVer auxVert = inicio;
        NodoAdy auxAdy;
        if (auxVert != null) {
            while (auxVert != null) {
                cad = cad + "\n----------------------------------------"
                        + "\n Ciudad: " + auxVert.getElemento();
                auxAdy = auxVert.getPrimerAdy();
                if (auxAdy != null) {
                    while (auxAdy != null) {
                        cad = cad + "\n conectado con: " + auxAdy.getVertice().getElemento() + " a: " + auxAdy.getEtiqueta() + " km de distancia.";
                        auxAdy = auxAdy.getSigAdy();
                    }
                } else {
                    cad = cad + "\n Sin rutas!";
                }
                auxVert = auxVert.getSigVer();
            }
        } else {
            cad = "Vacio!";
        }
        return cad;
    }

    public Lista dijkstra(String origen, String destino) {
        Lista camino = null, encontrado, vertices;
        NodoVer auxVert;
        NodoAdy auxAdy;
        String elemActual;
        String[] anterior;
        double[] distancia;
        int cantElementos;

        if (this.inicio != null) {//Si el grafo esta vacio, return null
            encontrado = new Lista();
            vertices = new Lista();
            auxVert = this.inicio;

            while (auxVert != null) {
                elemActual = auxVert.getElemento();
                vertices.insertar(elemActual);
                auxVert = auxVert.getSigVer();
            }

            cantElementos = vertices.longitud();
            anterior = new String[cantElementos];
            distancia = new double[cantElementos];
            for (int i = 0; i < cantElementos; i++) {
                distancia[i] = Integer.MAX_VALUE;
                anterior[i] = null;
            }

            int posActual;
            int posModificar;
            int posPosibleCandidato;
            double nuevaDistancia;
            elemActual = origen; // ORIGEN!!!
            distancia[vertices.getPos(elemActual)] = 0;
            anterior[vertices.getPos(elemActual)] = null;
            
            while (encontrado.longitud() <= cantElementos) {      
                encontrado.insertar(elemActual);
                auxAdy = getRefVertice(elemActual).getPrimerAdy();
                while (auxAdy != null) {
                    if (!encontrado.pertenece(auxAdy.getVertice().getElemento())) {
                        posModificar = vertices.getPos(auxAdy.getVertice().getElemento());
                        posActual = vertices.getPos(elemActual);
                        nuevaDistancia = auxAdy.getEtiqueta() + distancia[posActual];
                        if (nuevaDistancia < distancia[posModificar]) {
                            distancia[posModificar] = nuevaDistancia;
                            anterior[posModificar]=elemActual;
                        }
                    }
                   auxAdy=auxAdy.getSigAdy();
                }
                posPosibleCandidato=0;
               for (int i = 0; i < vertices.longitud(); i++) { //Revisar long de lista
                    
                  if(!encontrado.pertenece(vertices.recuperar(i)) && distancia[i] < distancia[posPosibleCandidato] ){
                      posPosibleCandidato=i;
                    }
                  elemActual = vertices.recuperar(posPosibleCandidato);
                                        
                }               
                
            }
            
            for (int i = 0; i < distancia.length; i++) {
                System.out.println("Vert: " + vertices.recuperar(i) + "\tDist: " + distancia[i] + "\tPrev: " + anterior[i]);
            }

        }
        return camino;
    }

    private NodoVer getRefVertice(String elem) {
        NodoVer buscado = null;
        NodoVer auxVert = this.inicio;
        boolean corte = false;

        while (!corte && auxVert != null) {
            if (auxVert.getElemento().equals(elem)) {
                corte = true;
                buscado = auxVert;
            }
            auxVert = auxVert.getSigVer();
        }

        return buscado;
    }
}
