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
public class Ciudad {

    private String nombre, provincia;
    private int cantidadHabitantes;
    private boolean alojamiento;

    public Ciudad(String nombre, String provincia, int habitantes, boolean alojamiento) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.cantidadHabitantes = habitantes;
        this.alojamiento = alojamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getCantidadHabitantes() {
        return cantidadHabitantes;
    }

    public void setCantidadHabitantes(int cantidadHabitantes) {
        this.cantidadHabitantes = cantidadHabitantes;
    }

    public boolean tieneAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(boolean alojamiento) {
        this.alojamiento = alojamiento;
    }

    @Override
    public String toString() {
        return "\nCiudad: " + nombre + ".\nProvincia: " + provincia + ".\nCantidad de Habitantes: " + cantidadHabitantes + ".\n¿Tiene alojamiento? " + (alojamiento ? "Si" : "No");
    }
}
