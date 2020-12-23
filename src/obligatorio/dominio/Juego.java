package obligatorio.dominio;

import java.util.*;

public class Juego {

    private static Juego instancia;

    private Juego() {
        listaPartidas = new ArrayList();
        listaJugadores = new ArrayList();
    }

    // Metodo que crea una instancia UNICA de la clase Juego
    public static Juego getInstancia() {

        if (instancia == null) {
            instancia = new Juego();
        }
        return instancia;
    }

    private ArrayList<Partida> listaPartidas;
    private ArrayList<Jugador> listaJugadores;

    public void agregarPartida(Partida unaPartida) {
        listaPartidas.add(unaPartida);
    }

    public void agregarJugador(String nombre, int edad) {
        Jugador j1 = new Jugador(nombre, edad);
        listaJugadores.add(j1);
    }

    public ArrayList<Partida> getPartidas() {
        return listaPartidas;
    }

    public ArrayList<Jugador> getJugadores() {
        return listaJugadores;
    }

    public boolean validarNombre(String nombre) {

        boolean esValido = true;
        ArrayList<Jugador> lista = this.getJugadores();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNombre().equals(nombre)) {
                esValido = false;
            }
        }
        return esValido;

    }

    public boolean validarJugador(int numeroJugador) {
        boolean esValido = false;
        ArrayList<Jugador> lista = this.getJugadores();

        if (numeroJugador - 1 < lista.size() && numeroJugador - 1 >= 0) {
            esValido = true;
        }
        return esValido;
    }

    public boolean validarPartida(String unNombre) {
        
        boolean esValida = true;
        for (int i = 0; i < this.getPartidas().size(); i++) {
            if (this.getPartidas().get(i).getNombre().equals(unNombre)) {
                esValida = false;
            }
        }
        return esValida;
    }

    public boolean validarPartidaAReproducir(int numeroPartida) {

        boolean esValido = false;
        ArrayList<Partida> lista = this.getPartidas();

        if (numeroPartida - 1 < lista.size() && numeroPartida - 1 >= 0) {
            esValido = true;
        }
        return esValido;
    }

    @Override
    public String toString() {
        return "Partida: " + this.listaPartidas;
    }

}
