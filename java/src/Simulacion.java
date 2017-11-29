package simulacion;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Modela la simulación de un día en el cajero Banpato.
 *
 * @author  Luis Clemente Zúñiga Flores
 */
public class Simulacion {

//    Campos
    /**
     * Cantidad de eventos a imprimir
     */
    public static int eventosAImprimir = 0;

    /**
     * Representa la cantidad de eventos
     */
    public static int cantidadDeEventos = 0;

    /**
     * Representa la cantidad de llegadas al cajero
     */
    public static int cantidadDeLlegadas = 0;
    /**
     * Guarda si hay o no abastecimiento en tránsito
     */
    public static boolean abastecimientoEnTransito = false;

    /**
     * Guarda la cantidad de reabastecimientos
     */
    public static int cantidadDeAbastecimientos;

    /**
     * Guarda el total de dinero reabastecido
     */
    public static double cantidadReabastecida;

    /**
     * Cantidad de disposición promedio cajero.cantidadAbastecida/clientes
     * atendidos
     */
    public static double disposicionPromedio;

    /**
     * Guarda el tiempo de espera calcular (tiempo de espera/clientes) atendidos
     */
    public static double tiempoDeEspera;

    /**
     * Guarda el tiempo de servicio calcular (tiempo de servicio/clientes)
     * atendidos
     */
    public static double tiempoDeServicio;

    /**
     * Guarda el tiempo fuera de servicio
     */
    public static double tiempoFueraDeServicio;

    /**
     * Temporal de fuera de servicio
     */
    public static double temporalDeFueraDeServicio;

    /**
     * Guarda la cantidad de usuarios insatisfechos.
     */
    public static int usuariosInsatisfechos;

    /**
     * Guarda la cantidad de usuarios atendidos
     */
    public static int usuariosAtendidos;
    /**
     * Guarda la hora actual de la simulación.
     */
    public static double reloj = 0;

    /**
     * Cuenta la cantidad de usuarios formados
     */
    public static int usuariosEnSistema;

    /**
     * Guarda el total de las disposiciones
     */
    public static double montoDispuestoTotal = 0;
    /**
     * Instancia de un cajero automático.
     */
    public static Cajero cajero = new Cajero(500000, 0);

    /**
     * Tabla de eventos futuros
     */
    public static LinkedList<Evento> tablaDeEventosFuturos = new LinkedList<>();

    /**
     * Guarda los usuarios que llegan en una cola
     */
    public static Queue<Usuario> colaUsuarios = new LinkedList<>();

    public static Stack<Usuario> pilaUsuarios = new Stack<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Writer.writer("SIMULACIÓN DE UN CAJERO AUTOMÁTICO.\nAlumno: Luis Clemente Zúñiga Flores.\nProfesor: Martín Nicolás Silva Arredondo.\nMateria: Simulación.\n-------------------------------------------------------------------------------\n");
        tablaDeEventosFuturos.add(new Evento(1, 32400));
        tablaDeEventosFuturos.add(new Evento(2, 64800));
        do {
            Collections.sort(tablaDeEventosFuturos, Comparator.comparingDouble(Evento::getTiempoDeOcurrencia));
            monitorDeEventos();
            tablaDeEventosFuturos.remove();
        } while (reloj < 64800 && tablaDeEventosFuturos.size() > 0);

        resultadosFinales();

    }

    /**
     * Monitorea los eventos y de acuerdo a su tipo, ejecuta el método adecuado.
     */
    public static void monitorDeEventos() {
        int tipo = tablaDeEventosFuturos.getFirst().getTipoDeEvento();
        double tiempo = tablaDeEventosFuturos.getFirst().getTiempoDeOcurrencia();
        switch (tipo) {

            case 1:
                abrir(tiempo);
                break;
            case 2:
                cerrar(tiempo);
                break;
            case 3:
                llegada(tiempo);
                break;
            case 4:
                atencion(tiempo);
                break;
            case 5:
                salida(tiempo);
                break;
            case 6:
                abastecimiento(tiempo);
                break;

        }
    }

    /**
     * Establece los parámetros iniciales del cajero.
     *
     * @param hora indica la hora en que se abre el cajero
     */
    public static void abrir(double hora) {
//        Actualizar el reloj
        reloj = hora;
//        Aumenta contador de eventos
        cantidadDeEventos++;
//        poner estatus abierto
        cajero.setAbierto(true);
        cajero.setEnLinea(true);
        cajero.setOcupado(false);
//        Generar evento llegada el el tiempo reloj + Aleatorio de tiempo entre llegadas
        double eventoLlegada = reloj + Aleatorio.exponencial(500);
        tablaDeEventosFuturos.add(new Evento(3, eventoLlegada));
//        colaUsuarios.add(new Usuario(eventoLlegada));
//        Desplegar evento en su caso
        desplegarEvento(1, reloj);

        System.out.println("cajero abierto: " + cajero.isAbierto() + "\nCajero en línea: " + cajero.isEnLinea() + "\nCajero ocupado: " + cajero.isOcupado());

        if (!cajero.isOcupado()) {
            System.out.println("El cajero no está ocupado.");
        }

    }

    /**
     * Establece el cierre del cajero.
     *
     * @param hora indica la hora de cierre del cajero
     */
    public static void cerrar(double hora) {
//        Actualizar reloj
        reloj = hora;
//        Aumentar contador de número de eventos
        cantidadDeEventos++;
//        Poner estatus cerrado
        cajero.setAbierto(false);
//        Desplegar evento en su caso
        desplegarEvento(2, reloj);
    }

    /**
     * Establece la llegada de un cliente y prepara lo necesario para su
     * atención.
     *
     * @param hora indica la llegada del un cliente
     */
    public static void llegada(double hora) {
//        Actualizar reloj
        reloj = hora;
//        Incrementar contador de número de eventos
        cantidadDeEventos++;
//        Incrementar número de llegadas
        cantidadDeLlegadas++;
        if (cajero.isAbierto()) {
            double eventoLlegada = reloj + Aleatorio.exponencial(500);
//            Si estatus abierto generar evento llegada en el tiempo Reloj + Aleatorio de tiempo entre llegadas
            tablaDeEventosFuturos.add(new Evento(3, eventoLlegada));

        }
//        Si cajero en línea y estatus abierto
        if (cajero.isEnLinea() && cajero.isAbierto()) {
//            Si hay menos de 8 usuarios en el sistema
            if (usuariosEnSistema < 8) {
//                Agregar cliente a la lista alimentando número de llegada y tiempo de llegada                                          
                colaUsuarios.add(new Usuario(reloj));
//                incrementar número de clientes en el sistema
                usuariosEnSistema++;
//                Si cajero desocupado generar evento inmediato de inicio de atención
                if (!cajero.isOcupado()) {
                    tablaDeEventosFuturos.add(new Evento(4, reloj));
                }

            } else {
//                Si no aumentar insatisfechos
                usuariosInsatisfechos++;
            }

        } else {
//            Sino aumentar insatisfechos
            usuariosInsatisfechos++;
        }
//        Desplegar evento en su caso
        desplegarEvento(3, reloj);

    }

    /**
     * Ejecuta el algoritmo de atención al usuario
     *
     * @param hora indica la hora de llegada del usuario
     */
    public static void atencion(double hora) {
//        Actualizar reloj
        reloj = hora;
//        incrementar contador de número de eventos
        cantidadDeEventos++;
//        poner cajero ocupado
        cajero.setOcupado(true);
//Alimentar tiempo de inicio de atención del cliente e incrementar insatisfechos si esperó más de 5 mins
        colaUsuarios.peek().setHoraInicioDeAtencion(reloj);
        if ((colaUsuarios.peek().getHoraInicioDeAtencion() - colaUsuarios.peek().getHoraDeLlegada()) / 60 > 5) {
            usuariosInsatisfechos++;
        }
//        Acumular tiempo de espera
        colaUsuarios.peek().setTiempoDeEspera(colaUsuarios.peek().getHoraInicioDeAtencion() - colaUsuarios.peek().getHoraDeLlegada());
//        Generar evento salida en el tiempo reloj + Aleatorio de tiempo de servicio
        double tiempoDeServicio = reloj + Aleatorio.exponencial(120);
        tablaDeEventosFuturos.add(new Evento(5, tiempoDeServicio));

//        Alimentar el monto solicitado con aleatorio de monto de disposición * 100
        colaUsuarios.peek().setCantidadSolicitada(Aleatorio.uniformeParams(0, 50) * 100);

        if (colaUsuarios.peek().getCantidadSolicitada() < cajero.getCantidadActual()) {
            colaUsuarios.peek().setCantidadRecibida(colaUsuarios.peek().getCantidadSolicitada());
            //Alimentar monto dispuesto con el monto solicitado o lo que quede incrementando insatisfechos
        } else if (colaUsuarios.peek().getCantidadSolicitada() > cajero.getCantidadActual()) {
            colaUsuarios.peek().setCantidadRecibida(cajero.getCantidadActual());
            usuariosInsatisfechos++;
        }
//        Desplegar evento en su caso
        desplegarEvento(4, reloj);
    }

    /**
     * Ejecuta el algoritmo de salida del cliente
     *
     * @param hora indica la hora de salida del cliente.
     */
    public static void salida(double hora) {
//        Actualizar reloj
        reloj = hora;
//Incrementar contador de número de eventos
        cantidadDeEventos++;
//alimentar tiempo de salida
        colaUsuarios.peek().setHoraDeSalida(reloj);
//acumular tiempo de servicio
        colaUsuarios.peek().setTiempoDeServicio(colaUsuarios.peek().getHoraDeSalida() - colaUsuarios.peek().getHoraInicioDeAtencion());
//        Decrementar monto dispuesto del efectivo
        cajero.disposicionDeEfectivo(colaUsuarios.peek().getCantidadRecibida());
//        Acumular monto dispuesto
        montoDispuestoTotal += colaUsuarios.peek().getCantidadRecibida();
//        Eliminar cliente de la lista
        pilaUsuarios.add(colaUsuarios.poll());
//Decrementar número de clientes en el sistema
        usuariosEnSistema--;
//Incrementar número de usuarios atendidos
        usuariosAtendidos++;
//poner cajero desocupado
        cajero.setOcupado(false);
//si efectivo < punto de reorden y no hay abastecimiento en tránsito
        if (cajero.getCantidadActual() <= cajero.getPuntoDeReorden() && !abastecimientoEnTransito) {
//Generar evento abastecimiento en el tiempo reloj + Aleatorio de tiempo de abastecimiento
            double tiempoAbastecimiento = reloj + Aleatorio.exponencial(600);
            tablaDeEventosFuturos.add(new Evento(6, tiempoAbastecimiento));
            abastecimientoEnTransito = true;
        }
        if (cajero.getCantidadActual() == 0) {
//            Poner cajero fuera de línea y registrar tiempo en que se pone off-line
            cajero.setEnLinea(false);
            temporalDeFueraDeServicio = reloj;
//Aumentar insatisfechos con el número de clientes en el sistema
            usuariosInsatisfechos += colaUsuarios.size();
//Vaciar la lista de clientes 
            colaUsuarios.removeAll(colaUsuarios);
            usuariosEnSistema = 0;

        }

//        si número de clientes en sistema > 0
        if (usuariosEnSistema > 0) {
//           Generar evento inmediato de inicio de atención
            tablaDeEventosFuturos.add(new Evento(4, reloj));
        }

//        desplegar evento en su caso
        desplegarEvento(5, reloj);

    }

    /**
     * Ejecuta el algoritmo de abastecimiento de efectivo, en caso de que el
     * dinero disponible sea menor al punto de reorden.
     *
     * @param hora indica la hora de abastecimiento.
     */
    public static void abastecimiento(double hora) {
//        Actualizar reloj
        reloj = hora;
//incrementar contador de número de eventos
        cantidadDeEventos++;
//agregar a efectivo lo que le falta para la cantidad fija de abastecimiento (fija - efectivo)
        double tempActual = cajero.getCantidadActual();
        cajero.setCantidadActual(cajero.getCantidadInicial());

        cajero.setCantidadAbastecida(cajero.getCantidadInicial() - tempActual);
//Acumular lo abastecido
        cantidadReabastecida += cajero.getCantidadAbastecida();
        System.out.println("Cantidad reabastecida: " + cantidadReabastecida);
        cantidadDeAbastecimientos++;

        if (!cajero.isEnLinea()) {
            cajero.setEnLinea(true);
            cajero.setTiempoOffLine(reloj - temporalDeFueraDeServicio);
        }
//        Poner que no hay abastecimiento en tránsito
        abastecimientoEnTransito = false;
//Desplegar evento en su caso
        desplegarEvento(6, reloj);

    }

    /**
     * Despliega el evento dependiendo de su tipo.
     *
     * @param evento indica el tipo de evento
     * @param hora indica la hora de ocurrencia del evento.
     */
    public static void desplegarEvento(int evento, double hora) {

        String Evento = "";
        switch (evento) {
            case 1:
                Evento = "Abrir banco";
                break;
            case 2:
                Evento = "Cerrar banco";
                break;
            case 3:
                Evento = "Llegada de cliente";
                break;
            case 4:
                Evento = "Atención de cliente";
                break;
            case 5:
                Evento = "Salida de cliente";
                break;
            case 6:
                Evento = "Abastecimiento de efectivo";
                break;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime time = LocalTime.MIN.plusSeconds((long) reloj);

        if (eventosAImprimir < 20) {

            String even = String.format("\nHora: %s\nEvento: %s\nClientes atendidos: %s\nClientes insatisfechos: %s\nClientes en este momento: %s\nDinero en el cajero: %s\n-------------------------------------------------------------------------------\n", formatter.format(time), Evento, usuariosAtendidos, usuariosInsatisfechos, usuariosEnSistema, Writer.varo(cajero.getCantidadActual()));
            Writer.writer("Evento a desplegar: " + ++eventosAImprimir + "\n");
            Writer.writer(even);

        }

        System.out.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\nHora: %s\nEvento: %s\nClientes atendidos: %s\nClientes insatisfechos: %s\nClientes en este momento: %s\nDinero en el cajero: %s\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n", formatter.format(time), Evento, usuariosAtendidos, usuariosInsatisfechos, usuariosEnSistema, Writer.varo(cajero.getCantidadActual()));

    }

    /**
     * Imprime los resultados finales de la simulación.
     */
    public static void resultadosFinales() {
        double disposicion = 0;
        double tiempoPromedio = 0;
        double tiempoServicioPromedio = 0;

        for (Usuario u : pilaUsuarios) {
            double espera = u.getHoraInicioDeAtencion() - u.getHoraDeLlegada();
            disposicion += u.getCantidadRecibida();
            tiempoPromedio += espera;
            tiempoServicioPromedio += u.getTiempoDeServicio();
        }

        disposicion /= pilaUsuarios.size();
        tiempoPromedio /= pilaUsuarios.size();
        tiempoServicioPromedio /= pilaUsuarios.size();

        String result = String.format("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\nRESULTADOS FINALES:\n Llegadas de clientes: %s\nClientes atendidos: %s\nClientes insatisfechos: %s\nNumero de reabastecimientos: %s\nCantidad reabastecida: %s\nCantidad de disposición promedio: %s\nTiempo de Servicio Promedio: %s segundos\nTiempo de espera promedio: %s\nTiempo fuera de servicio del cajero: %s segundos\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n", cantidadDeLlegadas, usuariosAtendidos, usuariosInsatisfechos, cantidadDeAbastecimientos, Writer.varo(cantidadReabastecida), Writer.varo(disposicion), tiempoServicioPromedio, tiempoPromedio, cajero.getTiempoOffLine());
        Writer.writer(result);

        System.out.printf("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\nLlegadas de clientes: %s\nClientes atendidos: %s\nClientes insatisfechos: %s\nNumero de reabastecimientos: %s\nCantidad reabastecida: %s\nCantidad de disposición promedio: %s\nTiempo de Servicio Promedio: %s\nTiempo de espera promedio: %s segundos\nTiempo fuera de servicio del cajero: %s segundos\n+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n", cantidadDeLlegadas, usuariosAtendidos, usuariosInsatisfechos, cantidadDeAbastecimientos, Writer.varo(cantidadReabastecida), Writer.varo(disposicion), tiempoServicioPromedio, tiempoPromedio, cajero.getTiempoOffLine());

    }

}
