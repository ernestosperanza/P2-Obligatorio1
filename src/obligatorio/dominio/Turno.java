package obligatorio.dominio;

import static obligatorio.interfaz.Prueba.*;

public class Turno {

    private String jugadorActivo;
    private static int contadorDeTurno = 1;
    private int numeroDeTurno;
    private int puntajeAzul;
    private int puntajeRojo;
    private String jugada;

    public Turno() {
        setNumeroDeTurno();
    }

    public void setJugadorActivo(String unJugador) {
        this.jugadorActivo = unJugador;
    }

    public String getJugadorActivo() {
        return this.jugadorActivo;
    }

    public void setJugada(String unaJugada) {
        this.jugada = unaJugada;
    }

    public String getJugada() {
        return this.jugada;
    }

    public int getNumeroDeTurno() {
        return this.numeroDeTurno;
    }

    public void setNumeroDeTurno() {
        this.numeroDeTurno = contadorDeTurno++;
    }

    public void setPuntajeAzul(int unPuntaje) {
        this.puntajeAzul = unPuntaje;
    }

    public int getPuntajeAzul() {
        return this.puntajeAzul;
    }

    public void setPuntajeRojo(int unPuntaje) {
        this.puntajeRojo = unPuntaje;
    }

    public int getPuntajeRojo() {
        return this.puntajeRojo;
    }

    //Metodo para iniciar turno despues de recibir la jugada 
    public boolean comenzarTurno(Tablero tablero, Turno turno, Partida partida, String jugada, Juego unJuego) {

        if (!tablero.validarJugada(jugada, turno.getJugadorActivo())) {
            return false;
        } else {
            setJugada(jugada);
            turno.actualizarPuntajes(tablero);
            partida.agregarTurno(turno);
            clonarTablero(tablero, partida);
            return true;
        }
    }

    // Metodo para pasar de turno
    public void pasarTurno(Tablero tablero, Turno turno, Partida partida, Juego unJuego) {

        setJugada("El Jugador paso de turno");
        actualizarPuntajes(tablero);
        partida.agregarTurno(turno);
        clonarTablero(tablero, partida);

    }

    public void clonarTablero(Tablero unTablero, Partida unaPartida) {

        Tablero tableroCopia = (Tablero) unTablero.clone();
        tableroCopia.clonarCasillas(unTablero.getGrillaTablero());
        unaPartida.agregarTablero(tableroCopia);

    }

    public void actualizarPuntajes(Tablero unTablero) {

        int[] maximos = unTablero.contarGrupos();
        this.setPuntajeRojo(maximos[0]);
        this.setPuntajeAzul(maximos[1]);

    }

    public String definirColor(String unColor) {
        String color;
        if (unColor.equals(rojo)) {
            color = "Rojo";
        } else {
            color = "Azul";
        }
        return color;
    }

    @Override
    public String toString() {
        return this.definirColor(this.getJugadorActivo()) + " ingreso: "
                + this.getJugada().toUpperCase() + ".  Puntaje Rojo: " + this.puntajeRojo + " Puntaje Azul: " + this.puntajeAzul;
    }
}
