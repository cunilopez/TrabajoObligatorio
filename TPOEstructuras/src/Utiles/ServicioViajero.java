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

    public ServicioViajero() {
        diccionario = new Diccionario();
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
                break;
            case 3:
                mostrarAtributos();
                ingresarServicio();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
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
                break;
            default:
                System.out.println("No ha ingresado ninguna opcion válida");
                System.out.println();
                ingresarServicio();
                break;

        }

    }

    public void menu() {
        System.out.println("1. Alta y Baja de una Ciudad.");
        System.out.println("2. Alta y Baja de un tramo de ruta.");
        System.out.println("3. Mostrar atributos de una Ciudad.");
        System.out.println("4. Listar rango de Ciudades.");
        System.out.println("5. Camino mas corto en Kilometros.");
        System.out.println("6. Existe camino mas corto que K Kilometros?");
        System.out.println("7. Camino que pasa por menos Ciudades.");
        System.out.println("8. Camino que pasa por ciudades con alojamiento");
        System.out.println("9. Mostrar todas las Ciudades Alfabeticamente.");
        System.out.println("10. Ejecutar script de carga.");
        System.out.println("11. Mostrar estructuras.");
        System.out.println();

    }

    public void altaobaja() {
        int opcion;
        System.out.println("1. Dar de alta a una Ciudad.");
        System.out.println("2. Dar de baja una Ciudad.");
        opcion = TecladoIn.readInt();
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
        if (diccionario.insertar(new Ciudad(ciudad, provincia, habitantes, alojamiento), ciudad)) {
            System.out.println("Insertado");
        } else {
            System.out.println("No se pudo Insertar.");
        }
    }

    public void baja() {
        String ciudad;
        System.out.println("Ingrese el nombre de la Ciudad a Eliminar");
        ciudad = TecladoIn.readLine().toUpperCase();
        if (diccionario.eliminar(ciudad)) {
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
    
    public void scriptCarga(){
    diccionario.insertar(new Ciudad("NEUQUEN","NEUQUEN",500000,true), "NEUQUEN");
    diccionario.insertar(new Ciudad("POSADAS","MISIONES",344833,true), "POSADAS");
    diccionario.insertar(new Ciudad("CORDOBA","CORDOBA",1391000,false), "CORDOBA");
    diccionario.insertar(new Ciudad("USUAHIA","TIERRA DEL FUEGO",54546,true), "USUAHIA");
    diccionario.insertar(new Ciudad("SALTA","SALTA",535303,true), "SALTA");
    diccionario.insertar(new Ciudad("MENDOZA","MENDOZA",115041,false), "MENDOZA");
    diccionario.insertar(new Ciudad("TRELEW","CHUBUT",50000,true), "TRELEW");
    diccionario.insertar(new Ciudad("CORRIENTES","CORRIENTES",654325,true), "CORRIENTES");
    diccionario.insertar(new Ciudad("NECOCHEA","BUENOS AIRES",416633,false), "NECOCHEA");
    diccionario.insertar(new Ciudad("VIEDMA","CHUBUT",232456,true), "VIEDMA");
    diccionario.insertar(new Ciudad("TANDIL","BUENOS AIRES",24564,false), "TANDIL");
    diccionario.insertar(new Ciudad("PARANA","ENTRE RIOS",247863,false), "PARANA");
    diccionario.insertar(new Ciudad("RESISTENCIA","CHACO",291720,true), "RESISTENCIA");
    diccionario.insertar(new Ciudad("ROSARIO","SANTA FE",1194000,true), "ROSARIO");
    diccionario.insertar(new Ciudad("ANGACO","SAN JUAN",45879,false), "ANGACO");
    
    }

}
