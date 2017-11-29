package simulacion;

/**
 * Modela un evento ocurrido en el cajero del banco Banpato.
 * @author  Luis Clemente Zúñiga Flores
 */
public class Evento {



//    Campos

    /**
     * Guarda el tiempo de ocurrencia del evento
     */
    private double tiempoDeOcurrencia;
    
    /**
     * Guarda el tipo de evento
     */    
    private int tipoDeEvento;
    
//    Constructor
    
    /**
     * Constructor de la clase Evento
     * @param tipo Indica el tipo de evento
     * @param tiempo Indica el tiempo de ocurrencia del evento
     */
    public Evento(int tipo, double tiempo){
        this.tipoDeEvento = tipo;
        this.tiempoDeOcurrencia = tiempo;
    }
    
//    Métodos

    
    /**
     * Devuelve el tiempo de ocurrencia del evento
     * @return el tiempo de ocurrencia.
     */
    public double getTiempoDeOcurrencia() {
        return tiempoDeOcurrencia;
    }

    /**
     * Establece el tiempo de ocurrencia de un evento.
     * @param tiempoDeOcurrencia Indica el tiempo de ocurrencia del evento.
     */
    
    public void setTiempoDeOcurrencia(double tiempoDeOcurrencia) {
        this.tiempoDeOcurrencia = tiempoDeOcurrencia;
    }
    
    /**
     * Devuelve el tipo de evento 
     * @return el tipo de evento
     */

    public int getTipoDeEvento() {
        return tipoDeEvento;
    }
    
    /**
     * Establece el tipo de evento en el cajero.
     * @param tipoDeEvento indica el tipo de evento
     */

    public void setTipoDeEvento(int tipoDeEvento) {
        this.tipoDeEvento = tipoDeEvento;
    }
    
    
    
    
    
    
}
