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
                if (actual.getHijoIzquierdo() == null) {
                    actual.setHijoIzquierdo(new NodoAVL(ciudad, clave));//Inserta hijo derecho.
                    exito = true;
                } else {
                    exito = insertarAux(ciudad, clave, actual.getHijoIzquierdo(), actual);// Baja por la derecha.                    
                }
            } else {
                if (actual.getHijoDerecho() == null) {
                    actual.setHijoDerecho(new NodoAVL(ciudad, clave));// Inserta hijo izquierdo.
                    exito = true;
                } else {
                    exito = insertarAux(ciudad, clave, actual.getHijoDerecho(), actual);//Baja por la izquierda.
                }
            }

        }
        if (exito) {
            actual.setAltura(altura(actual));
            balancear(actual, padre);
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

    public boolean eliminar(String clave) {
        boolean seElimino = false;
        if (raiz != null) {
            seElimino = eliminarAux(clave, raiz, null);
        }
        return seElimino;
    }

    private boolean eliminarAux(String clave, NodoAVL actual, NodoAVL padre) {
        boolean seElimino = false;
        String cantHijos, claveAux;
        Ciudad ciudadAux;
        if (actual != null) {
            if (clave.compareTo(actual.getClave()) < 0) {
                seElimino = eliminarAux(clave, actual.getHijoIzquierdo(), actual);
                actual.setAltura(altura(actual));
            } else if (clave.compareTo(actual.getClave()) > 0) {
                seElimino = eliminarAux(clave, actual.getHijoDerecho(), actual);
                actual.setAltura(altura(actual));
            } else {
                cantHijos = hijos(actual);
                switch (cantHijos) {
                    case "2":
                        NodoAVL sustituto = buscarSustituto(actual.getHijoDerecho());
                        claveAux = sustituto.getClave();
                        ciudadAux = sustituto.getCiudad();
                        eliminarAux(claveAux, raiz, null);
                        actual.setClave(claveAux);
                        actual.setCiudad(ciudadAux);
                        actual.setAltura(altura(actual));

                        break;
                    case "I":
                        if (padre == null) {
                            raiz = raiz.getHijoIzquierdo();
                            raiz.setAltura(altura(raiz));
                        } else {
                            if (padre.getClave().compareTo(actual.getClave()) > 0) {
                                padre.setHijoIzquierdo(actual.getHijoIzquierdo());
                            } else {
                                padre.setHijoDerecho(actual.getHijoIzquierdo());
                            }
                        }
                        break;
                    case "D":
                        if (padre == null) {
                            raiz = raiz.getHijoDerecho();
                            raiz.setAltura(altura(raiz));
                        } else {
                            if (padre.getClave().compareTo(actual.getClave()) > 0) {
                                padre.setHijoIzquierdo(actual.getHijoDerecho());
                            } else {
                                padre.setHijoDerecho(actual.getHijoDerecho());
                            }
                            break;
                        }
                    default:
                        if (padre == null) {
                            this.raiz = null;
                        } else {
                            if (padre.getClave().compareTo(actual.getClave()) > 0) {
                                padre.setHijoIzquierdo(null);
                            } else {
                                padre.setHijoDerecho(null);
                            }
                        }
                        break;
                }
                if (padre != null) {
                    padre.setAltura(altura(padre));
                }
                seElimino = true;
            }
            if (seElimino) {
                balancear(actual, padre);
            }
        }

        return seElimino;
    }

    private NodoAVL buscarSustituto(NodoAVL actual) {
        NodoAVL sustituto = actual;
        while (sustituto.getHijoIzquierdo() != null) {
            sustituto = sustituto.getHijoIzquierdo();
        }
        return sustituto;
    }

    private String hijos(NodoAVL actual) {
        String caso = "";
        if (actual.getHijoIzquierdo() != null) {
            if (actual.getHijoDerecho() != null) {
                caso = "2";
            } else {
                caso = "I";
            }
        } else {
            if (actual.getHijoDerecho() != null) {
                caso = "D";
            }
        }

        return caso;
    }

    public Ciudad recuperarElemento(String clave) {
        return recuperarElementoAux(raiz, clave);
    }

    private Ciudad recuperarElementoAux(NodoAVL actual, String clave) {
        Ciudad ciudad = null;
        if (actual != null) {
            if (!actual.getClave().equals(clave)) {
                if (actual.getClave().compareTo(clave) > 0) {
                    ciudad = recuperarElementoAux(actual.getHijoIzquierdo(), clave);
                } else {
                    ciudad = recuperarElementoAux(actual.getHijoDerecho(), clave);
                }
            } else {
                ciudad = actual.getCiudad();
            }
        }
        return ciudad;
    }

    public String alfabeticamente() {
        if (raiz == null) {
            return "Sin elementos";
        } else {
            return alfabeticamenteAux(raiz);
        }
    }

    private String alfabeticamenteAux(NodoAVL actual) {
        if (actual != null) {
            return alfabeticamenteAux(actual.getHijoIzquierdo()) + actual.getCiudad()
                    + "\n" + alfabeticamenteAux(actual.getHijoDerecho());
        } else {
            return "";
        }
    }

    public String mostrarDiccionario() {
        if (raiz == null) {
            return "No hay Elementos..";
        } else {
            return mostrarDiccionarioAux(raiz);
        }
    }

    private String mostrarDiccionarioAux(NodoAVL actual) {
        if (actual != null) {
            return "\n              " + actual.getClave()
                    + ((actual.getHijoIzquierdo() != null) ? "\nHI " + actual.getHijoIzquierdo().getClave() : "\n")
                    + ((actual.getHijoDerecho() != null) ? "                 HD " + actual.getHijoDerecho().getClave() : "")
                    + "\n----------------------------------"
                    + mostrarDiccionarioAux(actual.getHijoIzquierdo())
                    + mostrarDiccionarioAux(actual.getHijoDerecho());

        } else {
            return "";
        }
    }

    public boolean existeCiudad(String clave) {
        return existeCiudadAux(raiz, clave);
    }

    private boolean existeCiudadAux(NodoAVL actual, String clave) {
        boolean existe = false;
        if (actual != null) {
            if (!actual.getClave().equals(clave)) {
                if (actual.getClave().compareTo(clave) > 0) {
                    existe = existeCiudadAux(actual.getHijoIzquierdo(), clave);
                } else {
                    existe = existeCiudadAux(actual.getHijoDerecho(), clave);
                }
            } else {
                existe = true;
            }
        }
        return existe;
    }
    
    public Lista listarClavesRango(String inicio, String fin) {
        Lista lista = new Lista();
        if (raiz != null) {
            listarClavesRangoAux(lista, raiz, inicio, fin);
        }
        return lista;
    }

    private void listarClavesRangoAux(Lista lista, NodoAVL raizActual, String inicio, String fin) {
        if (inicio.compareTo(raizActual.getClave()) < 0) {
            if (raizActual.getHijoIzquierdo() != null) {
                listarClavesRangoAux(lista, raizActual.getHijoIzquierdo(), inicio, fin);
            }
        }
        if (raizActual.getClave().compareTo(inicio) >= 0 && raizActual.getClave().compareTo(fin) <= 0) {
            lista.insertar(raizActual.getClave());
        }

        if (fin.compareTo(raizActual.getClave()) > 0) {
            if (raizActual.getHijoDerecho() != null) {
                listarClavesRangoAux(lista, raizActual.getHijoDerecho(), inicio, fin);
            }
        }
    }

}
