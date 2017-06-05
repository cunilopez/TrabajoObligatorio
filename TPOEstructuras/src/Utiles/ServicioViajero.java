/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import utiles.TecladoIn;

/**
 *
 * @author Soporte-1
 */
public class ServicioViajero {

    public static void seleccionDeOpcion() {
        int opcion;
        menu();
        System.out.println("Ingrese una Opcion:");
        opcion = TecladoIn.readLineInt();
        switch (opcion) {
            case 1:
                break;
            case 2:
                break;
            case 3:
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
                break;
            case 10:
                break;
            case 11:
                break;
            default:
                System.out.println("No ha ingresado ninguna opcion válida");
                seleccionDeOpcion();
                System.out.println();
                break;

        }

    }

    public static void menu() {
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

    public static void main(String[] Args) {
        seleccionDeOpcion();

    }

}
