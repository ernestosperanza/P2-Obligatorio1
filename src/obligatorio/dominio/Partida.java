package obligatorio.dominio;

import java.text.SimpleDateFormat;

import java.util.*;

public class Partida {

    private String nombre;
    private Jugador rojo;
    private Jugador azul;
    private String hora;
    private Tablero tablero;
    private ArrayList<Turno> listaTurnos = new ArrayList();
    private ArrayList<Tablero> listaTableros = new ArrayList();

    public void setNombre(String unNombre) {
        this.nombre = unNombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setJugadorRojo(Jugador unJugador) {
        this.rojo = unJugador;
    }

    public Jugador getJugadorRojo() {
        return this.rojo;
    }

    public void setJugadorAzul(Jugador unJugador) {
        this.azul = unJugador;
    }

    public Jugador getJugadorAzul() {
        return this.azul;
    }

    public String getHora() {
        return this.hora;
    }

    public void setHora(String unaHora) {
        this.hora = unaHora;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void setTablero(Tablero unTablero) {
        this.tablero = unTablero;
    }

    public void agregarTurno(Turno unTurno) {
        listaTurnos.add(unTurno);
    }

    public ArrayList<Turno> getListaDeTurnos() {
        return this.listaTurnos;
    }

    public void agregarTablero(Tablero unTablero) {
        listaTableros.add(unTablero);
    }

    public ArrayList<Tablero> getListaTableros() {
        return this.listaTableros;
    }

    public void comenzarPartida(Juego juego, Tablero tablero, Partida unaPartida, String nombrePartida, String jugRojo, String jugAzul) {

        unaPartida.setNombre(nombrePartida);
        Date fecha = new Date();
        String strDateFormat = "hh:mm";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        hora = objSDF.format(fecha);
        setHora(hora);

        for (int i = 0; i < juego.getJugadores().size(); i++) {
            if (juego.getJugadores().get(i).getNombre().equals(jugRojo)) {
                unaPartida.setJugadorRojo(juego.getJugadores().get(i));
            }
            if (juego.getJugadores().get(i).getNombre().equals(jugAzul)) {
                unaPartida.setJugadorAzul(juego.getJugadores().get(i));
            }
        }
    }

    @Override
    public String toString() {
        return "Partida(nombre): " + this.nombre + " con hora: " + this.getHora();
    }
}
