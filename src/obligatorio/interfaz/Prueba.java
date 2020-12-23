package obligatorio.interfaz;

import obligatorio.dominio.*;
import java.util.*;

public class Prueba {

    public static void main(String[] args) {

        Juego juego = Juego.getInstancia();
        pantallaDeInicio(juego);
    }

    // Colores que usa la UI y la logica del juego.
    public static String rojo = "\033[4;31m";
    public static String azul = "\u001B[34m";
    public static String verde = "\u001B[32m";
    public static String fondoVerde = "\u001B[42m";
    public static String reset = "\u001B[0m";
    public static String cyan = "\u001B[36m";

    public static void imprimirOpciones() {
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("BIENVENIDO A GRUPOS, QUE QUIERES HACER?");
        System.out.println("a. Crear jugador");
        System.out.println("b. Jugar Grupos");
        System.out.println("c. Replicar partida");
        System.out.println("d. salir");
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public static void pantallaDeInicio(Juego juego) {

        boolean salir = false;
        Scanner in = new Scanner(System.in);
        imprimirOpciones();
        String respuesta = in.nextLine().toLowerCase();

        while (!salir) {

            switch (respuesta) {
                case "a" -> {
                    opcionA(in, juego);
                    respuesta = "reinicio";
                }
                case "b" -> {
                    opcionB(juego);
                    respuesta = "reinicio";
                }
                case "c" -> {
                    opcionC(juego);
                    respuesta = "reinicio";
                }
                case "d" -> {
                    salir = opcionD();
                    if (!salir) {
                        respuesta = "reinicio";
                    }
                }
                case "reinicio" -> {
                    imprimirOpciones();
                    respuesta = in.nextLine().toLowerCase();
                }
                default -> {
                    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + respuesta + " no es una opcion valida");
                    System.out.println(rojo + "Por favor seleccione una opcion valida");
                    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    respuesta = "reinicio";
                }
            }
        }
    }

    public static void opcionA(Scanner in, Juego juego) {

        boolean seRegistro = false;

        do {
            boolean nombreValido = false;
            boolean edadValida = false;
            String nombre = "";
            int edad = 0;

            while (!nombreValido) {

                System.out.println("Ingrese el nombre del Jugador:");
                nombre = in.nextLine();
                if (nombre.length() < 3 || nombre.length() > 30) {
                    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + "El nombre utilizado esta fuera del");
                    System.out.println(rojo + "largo esperado, limitese a un rango");
                    System.out.println(rojo + "de caracteres entre 3 a 30");
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                } else {

                    nombreValido = juego.validarNombre(nombre);
                    if (!nombreValido) {
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "El nombre de Jugador ya esta en uso");
                        System.out.println(rojo + "Intente con otro nombre");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    }

                }
            }

            while (!edadValida) {

                try {
                    System.out.println("Ingrese la edad del Jugador: ");
                    edad = in.nextInt();
                    if (edad > 0 && edad < 113) {
                        edadValida = true;
                    } else {
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "La edad se ecuentra fuera del rango humano");
                        System.out.println(rojo + "Elija un entero entre 1 y 113");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    }
                } catch (InputMismatchException e) {
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + "La edad no es un numero valido");
                    System.out.println(rojo + "Intente con un entero");
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                }
                in.nextLine();
            }

            // Si los datos son validos crea el jugador
            if (edadValida && nombreValido) {

                juego.agregarJugador(nombre, edad);
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println(verde + "El Jugador fue creado con exito.");
                System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                seRegistro = true;

            }

        } while (!seRegistro);

    }

    public static void opcionB(Juego juego) {

        boolean jugadorRojoValido = false;
        boolean jugadorAzulValido = false;
        boolean tableroDeInicioValido = false;
        int jugadorRojo = 0;
        int jugadorAzul = 0;
        String jugadorR = "";
        String jugadorA = "";

        Scanner in = new Scanner(System.in);

        if (juego.getJugadores().size() > 1) {

            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println(cyan + "El juego empezo" + reset);
            System.out.println(cyan + "Ingrese nombre de la partida: " + reset);
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            String nombreDeLaPartida = in.nextLine().toLowerCase();

            while (!juego.validarPartida(nombreDeLaPartida)) {
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println(rojo + "Ya existe una partida con ese nombre");
                System.out.println(rojo + "intentelo de nuevo");
                System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                nombreDeLaPartida = in.nextLine().toLowerCase();
            }

            while (!jugadorRojoValido) {

                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println("Seleccione al jugador ROJO: ");
                for (int i = 0; i < juego.getJugadores().size(); i++) {
                    String nombre = toTitleCase((i + 1) + "- " + juego.getJugadores().get(i).getNombre());
                    System.out.println(nombre);
                }
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                try {
                    jugadorRojo = in.nextInt();

                    if (!juego.validarJugador(jugadorRojo)) {
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "El numero no se encuentra");
                        System.out.println(rojo + "en la lista de jugadores");
                        System.out.println(rojo + "Vuelva a intentarlo");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    } else {
                        jugadorR = juego.getJugadores().get(jugadorRojo - 1).getNombre();
                        System.out.println("Jugador Rojo: " + toTitleCase(jugadorR));
                        jugadorRojoValido = true;
                    }

                } catch (InputMismatchException e) {
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + "El valor ingresado no es valido");
                    System.out.println(rojo + "Intente con un numero de la lista");
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                }
                in.nextLine();
            }

            while (!jugadorAzulValido) {

                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println("Seleccione al jugador AZUL: ");
                for (int i = 0; i < juego.getJugadores().size(); i++) {
                    if (i != jugadorRojo - 1) {
                        String nombre = toTitleCase((i + 1) + "- " + juego.getJugadores().get(i).getNombre());
                        System.out.println(nombre);
                    }
                }
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                try {
                    jugadorAzul = in.nextInt();

                    if (!juego.validarJugador(jugadorAzul) || jugadorRojo == jugadorAzul) {
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "El numero no se encuentra");
                        System.out.println(rojo + "en la lista de jugadores");
                        System.out.println(rojo + "Vuelva a intentarlo");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        jugadorAzul = in.nextInt();
                    } else {
                        jugadorA = juego.getJugadores().get(jugadorAzul - 1).getNombre();
                        System.out.println("Jugador Azul: " + toTitleCase(jugadorA));
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        jugadorAzulValido = true;
                    }

                } catch (InputMismatchException e) {
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + "El valor ingresado no es valido");
                    System.out.println(rojo + "Intente con un numero de la lista");
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                }
                in.nextLine();
            }

            Tablero tablero = new Tablero();
            // iniciar tablero
            while (!tableroDeInicioValido) {

                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println("Seleccione un modo de para iniciatablero");
                System.out.println("1 - Inicio de tablero por defecto");
                System.out.println("2 - Inicio de tablero aleatorio");
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                try {
                    int opcionTablero = in.nextInt();
                    if (opcionTablero == 1) {
                        tablero.iniciarTableroPorDefecto();
                        tableroDeInicioValido = true;

                    } else if (opcionTablero == 2) {
                        tablero.iniciarTableroRandom();
                        tableroDeInicioValido = true;
                    } else {
                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "El numero no se encuentra");
                        System.out.println(rojo + "en la lista de opciones");
                        System.out.println(rojo + "Vuelva a intentarlo");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                    }

                } catch (InputMismatchException e) {
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + "El valor ingresado no es valido");
                    System.out.println(rojo + "Intente con un numero de la lista");
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                }
                in.nextLine();
            }

            imprimirInstrucciones();
            Partida partida = new Partida();
            partida.comenzarPartida(juego, tablero, partida, nombreDeLaPartida, jugadorR, jugadorA);
            dibujarTablero(tablero);
            jugarJuego(tablero, partida, juego);

        } else {
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println(rojo + "El numero de Jugadores es inferior a 2");
            System.out.println(rojo + "Se requieren al menos 2 jugadores");
            System.out.println(rojo + "registrados para comenzar el juego");
            System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    public static void opcionC(Juego juego) {

        boolean partidaValida = false;
        int partidaSeleccionada = 0;
        Scanner in = new Scanner(System.in);

        if (!juego.getPartidas().isEmpty()) {

            while (!partidaValida) {

                try {
                    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println("Seleccione una partida");
                    for (int i = 0; i < juego.getPartidas().size(); i++) {
                        System.out.println((i + 1) + " * " + juego.getPartidas().get(i).getNombre() + " * Hora:" + juego.getPartidas().get(i).getHora());
                    }
                    System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                    partidaSeleccionada = in.nextInt();
                    if (!juego.validarPartidaAReproducir(partidaSeleccionada)) {

                        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "El numero no se encuentra");
                        System.out.println(rojo + "en la lista de partidas");
                        System.out.println(rojo + "Vuelva a intentarlo");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

                    } else {

                        partidaValida = true;
                        in.nextLine();
                        Partida nombrePartida = juego.getPartidas().get(partidaSeleccionada - 1);
                        boolean salir = false;
                        for (int i = 0; i < nombrePartida.getListaDeTurnos().size() && !salir; i++) {

                            dibujarTablero(nombrePartida.getListaTableros().get(i));
                            System.out.println(nombrePartida.getListaDeTurnos().get(i));
                            System.out.println("Para salir presione X, o cualquier otra letra para seguir");
                            String letra = in.nextLine();

                            if (letra.equalsIgnoreCase("X")) {
                                salir = true;
                                return;
                            }
                        }
                    }

                } catch (InputMismatchException e) {
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    System.out.println(rojo + "El valor ingresado no es valido");
                    System.out.println(rojo + "Intente con un numero de la lista");
                    System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                }
                in.nextLine();
            }

        } else {
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println(rojo + "No hay partidas resgistradas, se requiere");
            System.out.println(rojo + "al menos 1 partida para Replicar");
            System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        }
    }

    public static boolean opcionD() {

        Scanner in = new Scanner(System.in);
        String respuesta = "";
        boolean salir = false;

        while (!salir) {
            System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println(rojo + "Seguro que quiere salir? S/N");
            System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            respuesta = in.nextLine();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println(cyan + "GRACIAS POR JUGAR GRUPOS, VUELVA PRONTO");
                System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                salir = true;
            } else if (respuesta.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println(rojo + respuesta + " no es una opcion valida");
                System.out.println(rojo + "Por favor seleccione una opcion valida");
            }
        }

        return salir;
    }

    public static void jugarJuego(Tablero unTablero, Partida unaPartida, Juego unJuego) {

        boolean elJuegoContinua = true;

        while (elJuegoContinua) {

            Turno turno = new Turno();

            if (unTablero.revisarFichasDisponibles()) {

                turno = unaPartida.getListaDeTurnos().get(turno.getNumeroDeTurno() - 2);
                anunciarGanador(unaPartida, unTablero, turno, unJuego);
                unJuego.agregarPartida(unaPartida);
                elJuegoContinua = false;

            } else if (unTablero.revisarFichasActivasCadaJugador(1)) {

                turno.setJugadorActivo(azul);
                System.out.println("Ingrese su jugada " + unaPartida.getJugadorAzul() + " - Jugador Azul");
                solicitarJugada(unTablero, turno, unaPartida, unJuego, false);

            } else if (unTablero.revisarFichasActivasCadaJugador(2)) {

                turno.setJugadorActivo(rojo);
                System.out.println("Ingrese su jugada " + unaPartida.getJugadorRojo() + " - Jugador Rojo");
                solicitarJugada(unTablero, turno, unaPartida, unJuego, false);

            } else {
                if (solicitarJugada(unTablero, turno, unaPartida, unJuego, true)) {
                    elJuegoContinua = false;
                }
            }
        }
    }

    public static boolean solicitarJugada(Tablero unTablero, Turno unTurno, Partida unaPartida, Juego unJuego, boolean setJugador) {

        Scanner in = new Scanner(System.in);
        boolean esValido = false;

        while (!esValido) {

            if (unTurno.getNumeroDeTurno() % 2 == 0 && setJugador) {
                unTurno.setJugadorActivo(azul);
                System.out.println("Ingrese su jugada " + unaPartida.getJugadorAzul() + " - Jugador Azul");

            } else if (unTurno.getNumeroDeTurno() % 2 != 0 && setJugador) {
                unTurno.setJugadorActivo(rojo);
                System.out.println("Ingrese su jugada " + unaPartida.getJugadorRojo() + " - Jugador Rojo");
            }

            String jugada = in.nextLine().toUpperCase();

            switch (jugada) {

                case "F" -> {

                    if (finalizarPartidaDeFormaVoluntaria(in)) {
                        anunciarGanador(unaPartida, unTablero, unTurno, unJuego);
                        esValido = true;
                        dibujarTablero(unTablero);
                        imprimirPuntajes(unTurno);
                        unJuego.agregarPartida(unaPartida);
                        return true;
                    }
                }

                case "P" -> {

                    unTurno.pasarTurno(unTablero, unTurno, unaPartida, unJuego);
                    esValido = true;
                    dibujarTablero(unTablero);
                    imprimirPuntajes(unTurno);
                    return false;
                }

                default -> {

                    if (!unTurno.comenzarTurno(unTablero, unTurno, unaPartida, jugada, unJuego)) {
                        //la jugada no es valida
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                        System.out.println(rojo + "La jugada ingresada no es valida");
                        System.out.println(rojo + "Intente de nuevo");
                        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                    } else {
                        esValido = true;
                        dibujarTablero(unTablero);
                        imprimirPuntajes(unTurno);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static boolean finalizarPartidaDeFormaVoluntaria(Scanner in) {

        boolean esValido = false;

        while (!esValido) {

            System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
            System.out.println(cyan + "Acepta terminar? S/N");
            System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");

            String jugada = in.nextLine().toUpperCase();

            if (jugada.equals("S")) {
                esValido = true;
                return true;
            } else if (jugada.equals("N")) {

                esValido = true;
                return false;
            } else {
                System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                System.out.println(rojo + "El valor ingresado no es valido");
                System.out.println(rojo + "Intente con S o N");
                System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                
            }
        }
        return false;
    }

    public static void anunciarGanador(Partida unaPartida, Tablero unTablero, Turno unTurno, Juego unJuego) {

        int puntajeAzul = unTurno.getPuntajeAzul();
        int puntajeRojo = unTurno.getPuntajeRojo();
        String ganador = "";

        if (puntajeAzul > puntajeRojo) {
            ganador = "Azul";
        } else if (puntajeAzul < puntajeRojo) {
            ganador = "Rojo";
        } else {
            ganador = verde + "Tenemos un empate";
        }
        System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(verde + "El ganador es: ");
        System.out.println(verde + ganador + "!");
        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public static void dibujarTablero(Tablero tablero) {

        // String formateadas para cada parte del tablero
        String inicioYFin = String.format("  %1$s+-+-+-+-+-+-+%2$s  ", fondoVerde, reset);
        String separador = String.format("  %1$s+%2$s-+-+-+-+-+-%1$s+%2$s  ", fondoVerde, reset);
        String numerosReborde = "   1 2 3 4 5 6   ";

        Casilla[][] matriz = tablero.getGrillaTablero();

        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {

                // Numeros del marco parte superior
                if (i == 0 && j == 0) {
                    System.out.println(numerosReborde);
                }

                // Inicio y fin del tablero verde
                if ((i == 1 && j == 0) || (i == 7 && j == 0)) {
                    System.out.println();
                    System.out.println(inicioYFin);
                }

                if (i > 0 || i < 7) {

                    if (j == 0) {

                        // Numeros de marco lateral izquierdo
                        if (i == 0 || i == 7) {
                            System.out.print("   ");

                        } else {
                            // Metodo que te devuelve ya el color a imprimir
                            String lateralIzquierdo = String.format("%1$s%2$s%3$s|", i, matriz[i][j], fondoVerde);
                            System.out.print(lateralIzquierdo);

                        }

                    } else if (j < 6) {

                        if (i == 0 || i == 7) {
                            System.out.print(reset + matriz[i][j] + " ");

                        } else {
                            System.out.print(reset + matriz[i][j] + "|");

                        }

                    } else if (j == 6) {

                        if (i == 0 || i == 7) {
                            System.out.print(matriz[i][j]);

                        } else {
                            System.out.print(matriz[i][j]);
                        }

                    } else if (j == 7) {

                        switch (i) {
                            case 0 ->
                                System.out.print("   ");
                            case 7 ->
                                System.out.println("   ");
                            // Numeros de marco lateral derecho
                            default -> {
                                String lateralDerecho = String.format("%1$s|%2$s%3$s%4$s", fondoVerde, reset, matriz[i][j], i);
                                System.out.print(lateralDerecho);
                            }
                        }
                    }
                }
                // Separadores de las casillas
                if (j == 7 && (i > 0 && i < 6)) {
                    System.out.println();
                    System.out.println(separador);
                }
                // Numeros del marco parte inferior
                if (i == 7 && j == 7) {
                    System.out.println(numerosReborde);
                }
            }
        }
    }

    public static void imprimirPuntajes(Turno unTurno) {
        System.out.println("Puntaje Rojo: " + unTurno.getPuntajeRojo() + " - "
                + "Puntaje Azul: " + unTurno.getPuntajeAzul());

    }

    public static void imprimirInstrucciones() {
        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("+-+-+-+-+-+-INSTRUCIONES+-+-+-+-+-+-+-+-+");
        System.out.println(cyan + "Como se mueven las fichas:");
        System.out.println(cyan + "La jugada se indica señalando el número de fila o columna (1 a 6)");
        System.out.println(cyan + "Luego la dirección hacia dónde mover");
        System.out.println(cyan + "N - hacia el norte, se comienza desde el borde inferior");
        System.out.println(cyan + "S - hacia el sur, se comienza desde el borde superior");
        System.out.println(cyan + "E - hacia el este, comienza desde borde izquierdo");
        System.out.println(cyan + "O - hacia oeste, comienza desde borde derecho");
        System.out.println(cyan + "Por ultimo, la cantidad de posiciones que se desplaza (de 1 a 6)");
        System.out.println(cyan + "Ejemplos de jugada son: 1E2 o 3N4");
        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(cyan + "Para pasar de turno presionar: P");
        System.out.println(cyan + "Y F para finalizar la partida.");
        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println(reset + "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
