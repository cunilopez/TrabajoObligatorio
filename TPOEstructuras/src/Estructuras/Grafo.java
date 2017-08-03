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
                        NodoAdy adyDes = new NodoAdy(origen, etiqueta, destino.getPrimerAdy());
                        destino.setPrimerAdy(adyDes);

                        seInserto = true;
                    }
                } else {
                    origen.setPrimerAdy(new NodoAdy(destino, etiqueta, null));
                    NodoAdy adyDes = new NodoAdy(origen, etiqueta, destino.getPrimerAdy());
                    destino.setPrimerAdy(adyDes);
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

    public double dijkstra(String origen, String destino) { //Devuelve la menor distancia desde un origen a un destino,
        //en caso de devolver el double.MAX_VALUE significa que la ciudad es inalcanzable.
        double[] distancia = new double[1];
        distancia[0] = Double.MAX_VALUE;//Caso que el grafo este vacio
        int posMenor = 0;// Se inicializan los valores para que devuelva "infinito" 

        if (this.inicio != null) {
            Lista encontrado, vertices;
            NodoVer auxVert;
            NodoAdy auxAdy;
            String elemActual;
            boolean seEncontro = false;
            int cantElementos, i, posActual, posModificar;
            double nuevaDistancia;

            encontrado = new Lista();
            vertices = new Lista();
            auxVert = this.inicio;
            while (auxVert != null) {
                elemActual = auxVert.getElemento();
                vertices.insertar(elemActual);
                auxVert = auxVert.getSigVer();
            }
            cantElementos = vertices.longitud();
            distancia = new double[cantElementos];
            for (i = 0; i < cantElementos; i++) {
                distancia[i] = Double.MAX_VALUE;
            }

            elemActual = origen;
            distancia[vertices.getPos(elemActual)] = 0;
            while (!seEncontro && encontrado.longitud() < cantElementos) {
                encontrado.insertar(elemActual);
                auxAdy = getRefVertice(elemActual).getPrimerAdy();
                while (auxAdy != null) {
                    if (!encontrado.pertenece(auxAdy.getVertice().getElemento())) {
                        posModificar = vertices.getPos(auxAdy.getVertice().getElemento());
                        posActual = vertices.getPos(elemActual);
                        nuevaDistancia = auxAdy.getEtiqueta() + distancia[posActual];
                        if (nuevaDistancia < distancia[posModificar]) {
                            distancia[posModificar] = nuevaDistancia;
                        }
                    }
                    auxAdy = auxAdy.getSigAdy();
                }
                posMenor = -1;
                i = 0;
                while (posMenor == -1) {
                    if (!encontrado.pertenece(vertices.recuperar(i))) {
                        posMenor = i;
                    }
                    i++;
                }
                for (i = posMenor; i < vertices.longitud(); i++) {

                    if (!encontrado.pertenece(vertices.recuperar(i)) && distancia[i] < distancia[posMenor]) {
                        posMenor = i;
                    }
                }
                elemActual = vertices.recuperar(posMenor);
                if (elemActual.equals(destino)) {
                    seEncontro = true;
                }
            }
        }
        return distancia[posMenor];
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
