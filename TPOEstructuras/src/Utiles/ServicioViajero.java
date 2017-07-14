/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import utiles.TecladoIn;
import Estructuras.*;

/**
 *
 * @author Soporte-1
 */
public class ServicioViajero {

    private Diccionario diccionario;
    private Grafo grafo;

    public ServicioViajero() {
        diccionario = new Diccionario();
        grafo = new Grafo();
    }

    public void ingresarServicio() {
        int opcion;
        menu();
        System.out.println("Ingrese una Opcion:");
        opcion = TecladoIn.readLineInt();
        switch (opcion) {
            case 1:
                altaobaja();
                ingresarServicio();
                break;
            case 2:
                altaobajaTramo();
                ingresarServicio();
                break;
            case 3:
                mostrarAtributos();
                ingresarServicio();
                break;
            case 4:
                listarRango();
                ingresarServicio();
                break;
            case 5:
                caminoMasCorto();
                ingresarServicio();
                break;
            case 6:
                caminoMasCortoKilometro();
                ingresarServicio();
                break;
            case 7:
                caminoMenorCiudades();
                ingresarServicio();
                break;
            case 8:
                caminoAlojamiento();
                ingresarServicio();
                break;
            case 9:
                System.out.println(diccionario.alfabeticamente());
                ingresarServicio();
                break;
            case 10:
                scriptCarga();
                ingresarServicio();
                break;
            case 11:
                System.out.println("\nEl arbol es el siguiente...");
                System.out.println(diccionario.mostrarDiccionario());
                System.out.println("\nEl grafo es el siguiente...");
                System.out.println(grafo.toString());
                ingresarServicio();
                break;
            case 12:
                System.out.println("FIN!");
                break;
            default:
                System.out.println("No ha ingresado ninguna opcion válida");
                System.out.println();
                ingresarServicio();
                break;

        }

    }

    public void menu() {
        System.out.println();
        System.out.println("1. Alta y Baja de una Ciudad.");
        System.out.println("2. Alta y Baja de un tramo de ruta.");
        System.out.println("3. Mostrar atributos de una Ciudad.");
        System.out.println("4. Listar rango de Ciudades.");
        System.out.println("5. Camino mas corto.");
        System.out.println("6. ¿Existe camino mas corto que K Kilometros?");
        System.out.println("7. Camino que pasa por menos Ciudades.");
        System.out.println("8. Camino que pasa por ciudades con alojamiento");
        System.out.println("9. Mostrar todas las Ciudades Alfabeticamente.");
        System.out.println("10. Ejecutar script de carga.");
        System.out.println("11. Mostrar estructuras.");
        System.out.println("12. Salir.");
        System.out.println();

    }

    public void caminoMasCorto() {
        String origen, destino;
        double dist;
        System.out.println("Ingrese la Ciudad de Origen.");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el Destino");
        destino = TecladoIn.readLine().toUpperCase();
        if (diccionario.existeCiudad(origen) && diccionario.existeCiudad(destino)) {
            dist = grafo.dijkstra(origen, destino);
            if (dist == Double.MAX_VALUE) {
                System.out.println("El destino es inalcanzable");
            } else {
                System.out.println("La menor distancia que existe desde:" + origen + " y " + destino + " es de:" + dist);
            }
        } else {
            System.out.println("Alguna de las Ciudades ingresadas no esta en las estructuras.");
        }

    }

    public void caminoMasCortoKilometro() {
        String origen, destino;
        double k;
        System.out.println("Ingrese la Ciudad de Origen.");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el Destino");
        destino = TecladoIn.readLine().toUpperCase();
        System.out.println("¿Cual es la distancia que no debe superar el camino?");
        k = TecladoIn.readLineDouble();
        if (diccionario.existeCiudad(origen) && diccionario.existeCiudad(destino)) {
            if (k <= grafo.dijkstra(origen, destino)) {
                System.out.println("Existe camino mas corto que: " + k + "Kms");
            } else {
                System.out.println("El camino supera los: " + k + "Kms");
            }
        } else {
            System.out.println("Alguna de las Ciudades ingresadas no esta en las estructuras.");
        }
    }

    public void listarRango() {
        String inicio, fin;
        System.out.println("Ingrese el inicio del rango.");
        inicio = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el fin del rango.");
        fin = TecladoIn.readLine().toUpperCase();
        System.out.println(diccionario.listarClavesRango(inicio, fin).toString());
    }

    public void caminoAlojamiento() {
        String partida, llegada;
        System.out.println("Ingrese la Ciudad de Partida");
        partida = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese Ciudad donde desea llegar");
        llegada = TecladoIn.readLine().toUpperCase();
        System.out.println(grafo.caminoConAlojamiento(partida, llegada, diccionario).toString());
    }

    public void caminoMenorCiudades() {
        String partida, llegada;
        System.out.println("Ingrese la Ciudad de Partida");
        partida = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese Ciudad donde desea llegar");
        llegada = TecladoIn.readLine().toUpperCase();
        System.out.println(grafo.caminoMenorCantCiudades(partida, llegada).toString());
    }

    public void altaobajaTramo() {
        int opcion;
        System.out.println("1. Dar de alta un Tramo.");
        System.out.println("2. Dar de baja un Tramo.");
        opcion = TecladoIn.readLineInt();
        switch (opcion) {
            case 1:
                altaTramo();
                break;
            case 2:
                bajaTramo();
                break;
            default:
                altaobajaTramo();
                break;

        }
    }

    public void altaTramo() {
        String origen, destino;
        double kilometros;
        System.out.println("Ingrese el origen del tramo de Ruta.");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el destino del tramo de Ruta");
        destino = TecladoIn.readLine().toUpperCase();
        if (diccionario.existeCiudad(origen) && diccionario.existeCiudad(destino)) {
            System.out.println("Ingrese la cantidad de kilometros que hay entre estas Ciudades.");
            kilometros = TecladoIn.readLineDouble();
            if (kilometros >= 0) {
                grafo.insertarArco(origen, destino, kilometros);
            } else {
                System.out.println("Ingresó un numero negativo.");
            }
        } else {
            System.out.println("El orgien o el destino no se encuentran en las estructuras.");
        }
    }

    public void bajaTramo() {
        String origen, destino;
        System.out.println("Ingrese el Origen del tramo de Ruta.");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el destino del tramo de Ruta");
        destino = TecladoIn.readLine().toUpperCase();
        if (grafo.eliminarAdyacente(origen, destino)) {
            System.out.println("Se elimino el tramo de ruta.");
        } else {
            System.out.println("No se pudo eliminar tramo.");
        }

    }

    public void altaobaja() {
        int opcion;
        System.out.println("1. Dar de alta a una Ciudad.");
        System.out.println("2. Dar de baja una Ciudad.");
        opcion = TecladoIn.readLineInt();
        switch (opcion) {
            case 1:
                alta();
                break;
            case 2:
                baja();
                break;
            default:
                altaobaja();
                break;

        }
    }

    public void alta() {
        String ciudad, provincia;
        boolean alojamiento = false;
        int habitantes, a = -1;//a inicializada en -1 para que el usuario pueda elegir una opcion.
        System.out.println("Ingrese el nombre de la Ciudad:");
        ciudad = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la Provincia:");
        provincia = TecladoIn.readLine().toUpperCase();
        System.out.println("¿Cuantos habitantes tiene?");
        habitantes = TecladoIn.readLineInt();
        while (a < 1 || a > 2) {
            System.out.println("Ingrese 1 si tiene alojamiento o 2 si no tiene alojamiento:");
            a = TecladoIn.readLineInt();
            switch (a) {
                case 1:
                    alojamiento = true;
                    break;
                case 2:
                    alojamiento = false;
                    break;
            }
        }
        if (diccionario.insertar(new Ciudad(ciudad, provincia, habitantes, alojamiento), ciudad) && grafo.insertarVertice(ciudad)) {
            System.out.println("Insertado");
        } else {
            System.out.println("No se pudo Insertar.");
        }
    }

    public void baja() {
        String ciudad;
        System.out.println("Ingrese el nombre de la Ciudad a Eliminar");
        ciudad = TecladoIn.readLine().toUpperCase();
        if (diccionario.eliminar(ciudad) && grafo.eliminarVertice(ciudad)) {
            System.out.println("Se elimino Correctamente!");
        } else {
            System.out.println("No se encuentra la Ciudad");
        }
    }

    public void mostrarAtributos() {
        String aBuscar;
        Ciudad ciudad;
        System.out.println("Ingrese la Ciudad que desea conocer sus atributos:");
        aBuscar = TecladoIn.readLine().toUpperCase();
        ciudad = diccionario.recuperarElemento(aBuscar);
        if (ciudad != null) {
            System.out.println(ciudad.toString());
        } else {
            System.out.println("No se encontro la ciudad.");
        }
    }

    public void scriptCarga() {
        diccionario.insertar(new Ciudad("NEUQUEN", "NEUQUEN", 500000, true), "NEUQUEN");
        diccionario.insertar(new Ciudad("POSADAS", "MISIONES", 344833, true), "POSADAS");
        diccionario.insertar(new Ciudad("CORDOBA", "CORDOBA", 1391000, false), "CORDOBA");
        diccionario.insertar(new Ciudad("USHUAIA", "TIERRA DEL FUEGO", 54546, true), "USHUAIA");
        diccionario.insertar(new Ciudad("SALTA", "SALTA", 535303, true), "SALTA");
        diccionario.insertar(new Ciudad("MENDOZA", "MENDOZA", 115041, false), "MENDOZA");
        diccionario.insertar(new Ciudad("TRELEW", "CHUBUT", 50000, true), "TRELEW");
        diccionario.insertar(new Ciudad("CORRIENTES", "CORRIENTES", 654325, true), "CORRIENTES");
        diccionario.insertar(new Ciudad("NECOCHEA", "BUENOS AIRES", 416633, false), "NECOCHEA");
        diccionario.insertar(new Ciudad("VIEDMA", "CHUBUT", 232456, true), "VIEDMA");
        diccionario.insertar(new Ciudad("TANDIL", "BUENOS AIRES", 24564, false), "TANDIL");
        diccionario.insertar(new Ciudad("PARANA", "ENTRE RIOS", 247863, false), "PARANA");
        diccionario.insertar(new Ciudad("RESISTENCIA", "CHACO", 291720, true), "RESISTENCIA");
        diccionario.insertar(new Ciudad("ROSARIO", "SANTA FE", 1194000, true), "ROSARIO");
        diccionario.insertar(new Ciudad("GOYA", "CORRIENTES", 45879, false), "GOYA");
        grafo.insertarVertice("NEUQUEN");
        grafo.insertarVertice("POSADAS");
        grafo.insertarVertice("CORDOBA");
        grafo.insertarVertice("USHUAIA");
        grafo.insertarVertice("SALTA");
        grafo.insertarVertice("MENDOZA");
        grafo.insertarVertice("TRELEW");
        grafo.insertarVertice("CORRIENTES");
        grafo.insertarVertice("NECOCHEA");
        grafo.insertarVertice("VIEDMA");
        grafo.insertarVertice("TANDIL");
        grafo.insertarVertice("PARANA");
        grafo.insertarVertice("RESISTENCIA");
        grafo.insertarVertice("ROSARIO");
        grafo.insertarVertice("GOYA");

        grafo.insertarArco("USHUAIA", "TRELEW", 150);

        grafo.insertarArco("VIEDMA", "NECOCHEA", 700);

        grafo.insertarArco("TRELEW", "VIEDMA", 30);
        grafo.insertarArco("TRELEW", "NEUQUEN", 600);
        grafo.insertarArco("TRELEW", "USHUAIA", 150);

        grafo.insertarArco("NEUQUEN", "TRELEW", 650);
        grafo.insertarArco("NEUQUEN", "MENDOZA", 500);
        grafo.insertarArco("NEUQUEN", "CORDOBA", 800);

        grafo.insertarArco("CORDOBA", "NECOCHEA", 139);
        grafo.insertarArco("CORDOBA", "ROSARIO", 160);
        grafo.insertarArco("CORDOBA", "GOYA", 111);

        grafo.insertarArco("MENDOZA", "CORDOBA", 183);

        grafo.insertarArco("NECOCHEA", "TANDIL", 100);
        grafo.insertarArco("NECOCHEA", "NEUQUEN", 900);

        grafo.insertarArco("TANDIL", "PARANA", 33);
        grafo.insertarArco("TANDIL", "ROSARIO", 99);

        grafo.insertarArco("GOYA", "SALTA", 43);

        grafo.insertarArco("ROSARIO", "CORRIENTES", 25);

        grafo.insertarArco("POSADAS", "CORRIENTES", 40);

        grafo.insertarArco("CORRIENTES", "POSADAS", 30);

        grafo.insertarArco("PARANA", "POSADAS", 68);
        grafo.insertarArco("PARANA", "ROSARIO", 49);

        grafo.insertarArco("RESISTENCIA", "SALTA", 60);
        grafo.insertarArco("RESISTENCIA", "CORRIENTES", 100);

        grafo.insertarArco("SALTA", "CORDOBA", 222);

    }

}
