package obligatorio.dominio;

import java.util.*;
import static obligatorio.interfaz.Prueba.*;

public class Tablero implements Cloneable {

    private Casilla[][] grillaTablero;

    public static int cont;

    public Tablero() {
        grillaTablero = setGrillaTablero();
    }

    public Casilla[][] setGrillaTablero() {

        Casilla[][] tablero = new Casilla[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                tablero[i][j] = new Casilla(null);
            }
        }
        return tablero;
    }

    public Casilla[][] getGrillaTablero() {
        return this.grillaTablero;
    }

    // Metodo que modifica el estado de la casilla en el tablero
    public void modificarEstadoCasilla(int x, int y, String color) {
        this.getGrillaTablero()[x][y].setEstado(color);
    }

    // Metodo que obtine el estado de una casilla
    public String obtenerEstado(int x, int y) {
        return this.getGrillaTablero()[x][y].getEstado();
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("No clone");
        }
        return o;
    }

    // Iniciar el Tablero por defecto
    public void iniciarTableroPorDefecto() {

        for (int j = 1; j < 7; j++) {
            if (j % 2 != 0) {
                modificarEstadoCasilla(0, j, rojo);
            } else if (j % 2 == 0) {
                modificarEstadoCasilla(0, j, azul);
            }
        }

        for (int i = 1; i < 7; i++) {
            if (i % 2 != 0) {
                modificarEstadoCasilla(i, 0, azul);
            } else if (i % 2 == 0) {
                modificarEstadoCasilla(i, 0, rojo);
            }
        }

        for (int j = 1; j < 7; j++) {
            if (j % 2 != 0) {
                modificarEstadoCasilla(7, j, azul);
            } else if (j % 2 == 0) {
                modificarEstadoCasilla(7, j, rojo);
            }
        }

        for (int i = 1; i < 7; i++) {
            if (i % 2 != 0) {
                modificarEstadoCasilla(i, 7, rojo);
            } else if (i % 2 == 0) {
                modificarEstadoCasilla(i, 7, azul);
            }
        }
    }

    // Iniciar el Tablero en forma randomica
    public void iniciarTableroRandom() {
        boolean entro = false;
        String estado = numeroRandom();
        String[] listaColores = new String[24];

        for (int i = 0; i < 24; i++) {
            listaColores[i] = "X";
        }
        int contRojo = 0;
        int contAzul = 0;

        for (int i = 0; i < 24; i++) {
            estado = numeroRandom();

            if (i < 2) {
                if (estado.equals(rojo)) {
                    contRojo++;
                    listaColores[i] = rojo + "X" + rojo + reset;
                }
                if (estado.equals(azul)) {
                    contAzul++;
                    listaColores[i] = azul + "X" + azul + reset;
                }
            }
            if (i > 1) {
                entro = false;
                if (listaColores[i - 1].contains(rojo) && listaColores[i - 2].contains(rojo)) {
                    listaColores[i] = azul + "X" + azul + reset;
                    contAzul++;
                } else {
                    if (listaColores[i - 1].contains(azul) && listaColores[i - 2].contains(azul)) {
                        listaColores[i] = rojo + "X" + rojo + reset;
                        contRojo++;
                    } else {
                        if (contRojo < 12 && contAzul < 12) {
                            estado = numeroRandom();
                            listaColores[i] = estado + "X" + estado + reset;
                            if (estado.equals(rojo)) {
                                contRojo++;
                                entro = true;

                            } else {
                                contAzul++;
                                entro = true;

                            }
                        }
                        if (contAzul >= 12 && !entro) {
                            if (contRojo != 12) {
                                listaColores[i] = rojo + "X" + rojo + reset;
                                contRojo++;
                            }

                        }
                        if (contRojo >= 12 && !entro) {
                            if (contAzul != 12) {
                                listaColores[i] = azul + "X" + azul + reset;
                                contAzul++;
                            }

                        }
                    }
                }
            }
        }
        if (contRojo > contAzul) {
            for (int i = 0; i < 24 && contRojo != contAzul; i++) {
                if (i < 23) {
                    if (listaColores[i].contains(rojo) && listaColores[i + 1].contains(rojo)) {
                        if (i < 21) {
                            if (listaColores[i + 2].contains(azul) && listaColores[i + 3].contains(rojo)) {
                                listaColores[i + 1] = azul + "X" + azul + reset;
                                contRojo--;
                                contAzul++;
                                entro = true;
                            }
                        }
                        if (i < 19 && !entro) {
                            if (listaColores[i + 2].contains(azul) && listaColores[i + 3].contains(azul)) {
                                if (listaColores[i + 4].contains(rojo) && listaColores[i + 5].contains(rojo)) {
                                    listaColores[i + 1] = azul + "X" + azul + reset;
                                    listaColores[i + 2] = rojo + "X" + rojo + reset;
                                    listaColores[i + 4] = azul + "X" + azul + reset;
                                    contAzul++;
                                    contRojo--;
                                }
                            }
                        }
                    }
                }
            }
        }
        entro = false;
        if (contRojo < contAzul) {
            for (int i = 0; i < 24 && contRojo != contAzul; i++) {
                if (i < 23) {
                    if (listaColores[i].contains(azul) && listaColores[i + 1].contains(azul)) {
                        if (i < 21) {
                            if (listaColores[i + 2].contains(rojo) && listaColores[i + 3].contains(azul)) {
                                listaColores[i + 1] = rojo + "X" + rojo + reset;
                                contRojo++;
                                contAzul--;
                                entro = true;
                            }
                        }
                        if (i < 19 && !entro) {
                            if (listaColores[i + 2].contains(rojo) && listaColores[i + 3].contains(rojo)) {
                                if (listaColores[i + 4].contains(azul) && listaColores[i + 5].contains(azul)) {
                                    listaColores[i + 1] = rojo + "X" + rojo + reset;
                                    listaColores[i + 2] = azul + "X" + azul + reset;
                                    listaColores[i + 4] = rojo + "X" + rojo + reset;
                                    contRojo++;
                                    contAzul--;
                                }
                            }
                        }
                    }
                }
            }
        }
        if (listaColores[0].contains(rojo) && listaColores[1].contains(rojo) && listaColores[23].contains(rojo)) {
            if (listaColores[22].contains(rojo)) {
                String aux = listaColores[22];
                listaColores[22] = listaColores[21];
                listaColores[21] = aux;
                aux = listaColores[1];
                listaColores[1] = listaColores[2];
                listaColores[2] = aux;
            }
            if (!(listaColores[21].contains(rojo) && listaColores[20].contains(rojo))) {
                String aux = listaColores[22];
                listaColores[22] = listaColores[23];
                listaColores[23] = aux;
            } else {
                String aux = listaColores[21];
                listaColores[21] = listaColores[20];
                listaColores[20] = aux;
            }
        }
        if (listaColores[0].contains(azul) && listaColores[1].contains(azul) && listaColores[23].contains(azul)) {
            if (listaColores[22].contains(azul)) {
                String aux = listaColores[22];
                listaColores[22] = listaColores[21];
                listaColores[21] = aux;
                aux = listaColores[1];
                listaColores[1] = listaColores[2];
                listaColores[2] = aux;
            }
            if (!(listaColores[21].contains(azul) && listaColores[20].contains(azul))) {
                String aux = listaColores[22];
                listaColores[22] = listaColores[23];
                listaColores[23] = aux;
            } else {
                String aux = listaColores[19];
                listaColores[19] = listaColores[20];
                listaColores[20] = aux;
            }
        }
        if (listaColores[0].contains(azul) && listaColores[22].contains(azul) && listaColores[23].contains(azul)) {
            if (listaColores[1].contains(azul)) {
                String aux = listaColores[22];
                listaColores[22] = listaColores[21];
                listaColores[21] = aux;
                aux = listaColores[1];
                listaColores[1] = listaColores[2];
                listaColores[2] = aux;
            }
            if (!(listaColores[2].contains(azul) && listaColores[3].contains(azul))) {
                String aux = listaColores[0];
                listaColores[0] = listaColores[1];
                listaColores[1] = aux;
            } else {
                String aux = listaColores[2];
                listaColores[2] = listaColores[3];
                listaColores[3] = aux;
            }
        }
        if (listaColores[0].contains(rojo) && listaColores[22].contains(rojo) && listaColores[23].contains(rojo)) {
            if (listaColores[1].contains(rojo)) {
                String aux = listaColores[22];
                listaColores[22] = listaColores[21];
                listaColores[21] = aux;
                aux = listaColores[1];
                listaColores[1] = listaColores[2];
                listaColores[2] = aux;
            }
            if (!(listaColores[2].contains(rojo) && listaColores[3].contains(rojo))) {
                String aux = listaColores[0];
                listaColores[0] = listaColores[1];
                listaColores[1] = aux;
            } else {
                String aux = listaColores[2];
                listaColores[2] = listaColores[3];
                listaColores[3] = aux;
            }
        }

        modificarTablero(listaColores);
    }

    // Asiste al metodo generar Tablero random
    // Genera un color random para incializar el tablero.
    public static String numeroRandom() {
        double random = Math.abs(Math.random() * 2);
        if (random < 1) {
            return rojo;
        } else {
            return azul;
        }
    }

    // Asiste al metodo generar Tablero random
    public void modificarTablero(String[] listaColores) {
        for (int b = 0; b < 24; b++) {
            if (b < 6) {
                if (listaColores[b].contains(rojo)) {
                    modificarEstadoCasilla(0, b + 1, rojo);
                }
                if (listaColores[b].contains(azul)) {
                    modificarEstadoCasilla(0, b + 1, azul);
                }
            }
            if (b > 5 && b < 12) {
                if (listaColores[b].contains(rojo)) {
                    modificarEstadoCasilla(b - 5, 7, rojo);
                }
                if (listaColores[b].contains(azul)) {
                    modificarEstadoCasilla(b - 5, 7, azul);
                }
            }
            if (b > 11 && b < 18) {
                for (int j = 6; j > 0; j--) {
                    if (listaColores[b].contains(rojo)) {
                        modificarEstadoCasilla(7, j, rojo);
                    }
                    if (listaColores[b].contains(azul)) {
                        modificarEstadoCasilla(7, j, azul);
                    }
                    b++;
                }
            }
            if (b > 17 && b < 24) {
                for (int j = 6; j > 0; j--) {
                    if (listaColores[b].contains(rojo)) {
                        modificarEstadoCasilla(j, 0, rojo);
                    }
                    if (listaColores[b].contains(azul)) {
                        modificarEstadoCasilla(j, 0, azul);
                    }
                    b++;
                }
            }
        }
    }

    // Validar jugada
    public boolean validarJugada(String unaJugada, String jugadorActivo) {

        unaJugada = unaJugada.replaceAll("\\s", "");

        if (validarInputJugada(unaJugada)) {
            if (validarFichaActiva(unaJugada, jugadorActivo)) {
                moverFichasTablero(unaJugada, jugadorActivo);
                return true;
            }
        }
        return false;
    }

    // Etapa 1 de validar la jugada: Validar input
    public boolean validarInputJugada(String unaJugada) {

        boolean esValido = false;
        // Validar el largo de la jugada
        if (unaJugada.length() == 3) {

            try {

                int inicio = Integer.parseInt(unaJugada.substring(0, 1));
                String direccion = unaJugada.substring(1, 2);
                int movimiento = Integer.parseInt(unaJugada.substring(2));

                if (inicio > 0 && inicio < 7) {
                    if (direccion.equalsIgnoreCase("e")
                            || direccion.equalsIgnoreCase("o")
                            || direccion.equalsIgnoreCase("s")
                            || direccion.equalsIgnoreCase("n")) {
                        if (movimiento > 0 && movimiento < 7) {

                            esValido = true;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                esValido = false;
            }
        }
        return esValido;
    }

    // Etapa 2 de validar la jugada:
    // Validar la propiedad de la ficha y posibilidad de jugada
    public boolean validarFichaActiva(String unaJugada, String jugadorActivo) {

        boolean esValida = false;
        int inicio = Integer.parseInt(unaJugada.substring(0, 1));
        int movimiento = Integer.parseInt(unaJugada.substring(2));
        String direccion = unaJugada.substring(1, 2);
        cont = 0;

        switch (direccion) {
            case "E" -> {
                // Corre con la fila fija, sumando
                if (obtenerEstado(inicio, 0) != null
                        && obtenerEstado(inicio, 0).equals(jugadorActivo)) {

                    for (int i = 1; i < 7; i++) {
                        if (obtenerEstado(inicio, i) == null) {
                            cont++;
                        }
                    }
                    if (movimiento <= cont) {
                        esValida = true;
                    }
                }
            }

            case "O" -> {
                // Corre con la fila fija, restando
                if (obtenerEstado(inicio, 7) != null
                        && obtenerEstado(inicio, 7).equals(jugadorActivo)) {

                    for (int i = 6; i > 0; i--) {
                        if (obtenerEstado(inicio, i) == null) {
                            cont++;
                        }
                    }
                    if (movimiento <= cont) {
                        esValida = true;
                    }
                }
            }

            case "S" -> {
                // Corre con la columna fija, sumando
                if (obtenerEstado(0, inicio) != null
                        && obtenerEstado(0, inicio).equals(jugadorActivo)) {

                    for (int i = 1; i < 7; i++) {
                        if (obtenerEstado(i, inicio) == null) {
                            cont++;
                        }
                    }
                    if (movimiento <= cont) {
                        esValida = true;
                    }
                }
            }

            case "N" -> {
                // Corre con la columna fija, restando
                if (obtenerEstado(7, inicio) != null
                        && obtenerEstado(7, inicio).equals(jugadorActivo)) {

                    for (int i = 6; i > 0; i--) {
                        if (obtenerEstado(i, inicio) == null) {
                            cont++;
                        }
                    }
                    if (movimiento <= cont) {
                        esValida = true;
                    }
                }
            }
        }
        return esValida;
    }

    // Etapa 3 Mover ficha y Ajustar tablero
    // TODO: corregir tomar casos de prueba como ejemplo
    public void moverFichasTablero(String unaJugada, String jugadorActivo) {

        int inicio = Integer.parseInt(unaJugada.substring(0, 1));
        int movimiento = Integer.parseInt(unaJugada.substring(2));
        String direccion = unaJugada.substring(1, 2);
        LinkedList<String> listaDeEstados = new LinkedList<String>();
        boolean mueve = false;

        switch (direccion) {
            case "E" -> {
                // Corre con la fila fija, sumando
                modificarEstadoCasilla(inicio, 0, null);

                for (int i = 1; i <= movimiento && mueve != true; i++) {
                    if (obtenerEstado(inicio, i) != null) {
                        mueve = true;
                    }
                }

                if (mueve) {

                    listaDeEstados.add(jugadorActivo);

                    for (int i = 1; i < movimiento; i++) {
                        if (obtenerEstado(inicio, i) != null) {
                            listaDeEstados.add(obtenerEstado(inicio, i));
                            modificarEstadoCasilla(inicio, i, null);
                        }
                    }

                    while (listaDeEstados.size() > 0) {
                        if (obtenerEstado(inicio, movimiento) != null) {
                            listaDeEstados.add(obtenerEstado(inicio, movimiento));
                            modificarEstadoCasilla(inicio, movimiento, null);
                        }
                        modificarEstadoCasilla(inicio, movimiento, listaDeEstados.getFirst());
                        listaDeEstados.removeFirst();
                        movimiento++;
                    }

                } else {
                    modificarEstadoCasilla(inicio, movimiento, jugadorActivo);
                }
            }

            case "O" -> {
                // Corre con la fila fija, restando
                modificarEstadoCasilla(inicio, 7, null);
                movimiento = 7 - movimiento;

                for (int i = 6; i >= movimiento && mueve != true; i--) {
                    if (obtenerEstado(inicio, i) != null) {
                        mueve = true;
                    }
                }

                if (mueve) {

                    listaDeEstados.add(jugadorActivo);

                    for (int i = 6; i > movimiento; i--) {
                        if (obtenerEstado(inicio, i) != null) {
                            listaDeEstados.add(obtenerEstado(inicio, i));
                            modificarEstadoCasilla(inicio, i, null);
                        }
                    }

                    while (listaDeEstados.size() > 0) {
                        if (obtenerEstado(inicio, movimiento) != null) {
                            listaDeEstados.add(obtenerEstado(inicio, movimiento));
                        }
                        modificarEstadoCasilla(inicio, movimiento, listaDeEstados.getFirst());
                        listaDeEstados.removeFirst();
                        movimiento--;
                    }

                } else {
                    modificarEstadoCasilla(inicio, movimiento, jugadorActivo);
                }
            }

            case "S" -> {
                // Corre con la columna fija, sumando
                modificarEstadoCasilla(0, inicio, null);

                for (int i = 1; i <= movimiento && mueve != true; i++) {
                    if (obtenerEstado(i, inicio) != null) {
                        mueve = true;
                    }
                }

                if (mueve) {

                    listaDeEstados.add(jugadorActivo);

                    for (int i = 1; i < movimiento; i++) {
                        if (obtenerEstado(i, inicio) != null) {
                            listaDeEstados.add(obtenerEstado(i, inicio));
                            modificarEstadoCasilla(i, inicio, null);
                        }
                    }

                    while (listaDeEstados.size() > 0) {
                        if (obtenerEstado(movimiento, inicio) != null) {
                            listaDeEstados.add(obtenerEstado(movimiento, inicio));
                        }
                        modificarEstadoCasilla(movimiento, inicio, listaDeEstados.getFirst());
                        listaDeEstados.removeFirst();
                        movimiento++;
                    }
                } else {
                    modificarEstadoCasilla(movimiento, inicio, jugadorActivo);
                }
            }

            case "N" -> {
                // Acumula los estados actuales en la lista
                modificarEstadoCasilla(7, inicio, null);
                movimiento = 7 - movimiento;

                for (int i = 6; i >= movimiento && mueve != true; i--) {
                    if (obtenerEstado(i, inicio) != null) {
                        mueve = true;
                    }
                }

                if (mueve) {

                    listaDeEstados.add(jugadorActivo);

                    for (int i = 6; i > movimiento; i--) {
                        if (obtenerEstado(i, inicio) != null) {
                            listaDeEstados.add(obtenerEstado(i, inicio));
                            modificarEstadoCasilla(i, inicio, null);
                        }
                    }

                    while (listaDeEstados.size() > 0) {
                        if (obtenerEstado(movimiento, inicio) != null) {
                            listaDeEstados.add(obtenerEstado(movimiento, inicio));
                        }
                        modificarEstadoCasilla(movimiento, inicio, listaDeEstados.getFirst());
                        listaDeEstados.removeFirst();
                        movimiento--;
                    }
                } else {
                    modificarEstadoCasilla(movimiento, inicio, jugadorActivo);
                }

            }
        }
    }

    // Etapa 4 Convertir estado de el Tablero en una matriz
    public int[][] salvarEstados() {

        int[][] estadoTablero = new int[8][8];

        //hay que copiar el tablero - Hacer deep clone del tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.obtenerEstado(i, j) != null) {
                    if (this.obtenerEstado(i, j).equals(rojo)) {

                        estadoTablero[i][j] = 1;

                    } else if (this.obtenerEstado(i, j).equals(azul)) {
                        estadoTablero[i][j] = 2;
                    }
                } else {
                    estadoTablero[i][j] = 0;
                }
            }
        }
        return estadoTablero;
    }

    // Etapa 5 Ver estado del tablero
    // Si no quedan fichas por mover se termino la partida
    public boolean revisarFichasDisponibles() {

        int[][] estadoDelTablero = salvarEstados();
        boolean terminoLaPartida = true;

        for (int i = 0; i < 8 && terminoLaPartida == true; i++) {
            if (estadoDelTablero[0][i] != 0 || estadoDelTablero[7][i] != 0) {
                for (int j = 1; j < 7; j++) {
                    if (estadoDelTablero[j][i] == 0) {
                        terminoLaPartida = false;
                    }
                }
            }
            if (estadoDelTablero[i][0] != 0 || estadoDelTablero[i][7] != 0) {
                for (int j = 1; j < 7; j++) {
                    if (estadoDelTablero[i][j] == 0) {
                        terminoLaPartida = false;
                    }
                }
            }
        }

        return terminoLaPartida;
    }

    public boolean revisarFichasActivasCadaJugador(int Jugador) {

        int[][] estadoDelTablero = salvarEstados();

        for (int i = 0; i < 8; i++) {
            if (estadoDelTablero[0][i] == Jugador || estadoDelTablero[7][i] == Jugador
                    || estadoDelTablero[i][0] == Jugador || estadoDelTablero[i][7] == Jugador) {
                return false;
            }
        }
        return true;
    }

    // Etapa 6 contar los grupos dentro del tablero
    public int[] contarGrupos() {

        int[][] estadoDelTablero = salvarEstados();

        int maxRojo = 0;
        int maxAzul = 0;
        cont = 0;

        // Hacer algoritmo de flood
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {

                if (estadoDelTablero[i][j] != 0) {
                    if (estadoDelTablero[i][j] == 1) {
                        rellenoPorDifusion(estadoDelTablero, i, j, 1);
                        if (cont > maxRojo) {
                            maxRojo = cont;
                            cont = 0;
                        } else {
                            cont = 0;
                        }
                    } else if (estadoDelTablero[i][j] == 2) {
                        rellenoPorDifusion(estadoDelTablero, i, j, 2);
                        if (cont > maxAzul) {
                            maxAzul = cont;
                            cont = 0;
                        } else {
                            cont = 0;
                        }
                    }
                }
            }
        }
        int[] maximos = {maxRojo, maxAzul};
        return maximos;
    }

    public void rellenoPorDifusion(int[][] estadoDelTablero, int x, int y, int color) {

        if (x < 1 || x > 6 || y < 1 || y > 6 || estadoDelTablero[x][y] != color) {
            return;
        }

        estadoDelTablero[x][y] = 0;
        cont++;
        rellenoPorDifusion(estadoDelTablero, x + 1, y, color);
        rellenoPorDifusion(estadoDelTablero, x - 1, y, color);
        rellenoPorDifusion(estadoDelTablero, x, y + 1, color);
        rellenoPorDifusion(estadoDelTablero, x, y - 1, color);
        return;
    }

    public void clonarCasillas(Casilla[][] unT) {
        this.grillaTablero = new Casilla[unT.length][unT[0].length];
        for (int i = 0; i < grillaTablero.length; i++) {
            for (int j = 0; j < grillaTablero[0].length; j++) {
                this.grillaTablero[i][j] = (Casilla) unT[i][j].clone();
            }
        }
    }

    @Override
    public String toString() {
        return "Tablero: " + this.grillaTablero;
    }
}
