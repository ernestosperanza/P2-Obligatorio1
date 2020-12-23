package obligatorio.dominio;

import obligatorio.interfaz.Prueba;


public class Jugador {

    private String nombre;
    private int edad;

    public Jugador(String unNombre, int unaEdad) {
        this.nombre = unNombre;
        this.edad = unaEdad;
    }

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int unaEdad) {
        this.edad = unaEdad;
    }

    @Override
    public String toString() {
        String nombre = Prueba.toTitleCase(this.nombre);
        return nombre;
    }

}
