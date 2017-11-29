
package simulacion;

/**
 *
 * @author Clemente
 * Clase usuario representa el modelo de un usuario.
 */

/**
 * Modela un usuario de cajero automático.
 * @author Luis Clemente Zúñiga Flores
 */
public class Usuario {
    
    /**
     * Attributes
     */
    /**
     * Guarda la hora en que llegó el usuario
     */
    private double horaDeLlegada;
    /**
     * Guarda la hora en que comenzó a ser atendido el usuario
     */
    private double horaInicioDeAtencion;
    /**
     * Guarda la hora de salida del usuario
     */
    private double horaDeSalida;
    /**
     * Tiempo entre la llegada e inicio de atención
     */
    private double tiempoDeEspera;
    /**
     * Guarda la cantidad de dinero solicitada por el cliente
     */
    private double cantidadSolicitada;
    
    /**
     * Guarda la cantidad recibida por el usuario.
     */
    private double cantidadRecibida;
    /**
     * Guarda el número de llegada del usuario
     */    
    private int numeroDeLlegada;
    
    /**
     * Guarda el total del tiempo de servicio
     */
     
    private double tiempoDeServicio;

public Usuario(){
    
}

public Usuario(double hora){
    this.horaDeLlegada = hora;
}

    public double getTiempoDeServicio() {
        return tiempoDeServicio;
    }

    public void setTiempoDeServicio(double tiempoDeServicio) {
        this.tiempoDeServicio = tiempoDeServicio;
    }

    /**
     * Establece la hora de llegada del usuario
     *
     * @param hora Indica la hora de llegada del usuario
     */
    

    public void setHoraDeLlegada(double hora) {
        this.horaDeLlegada = hora;
    }

    /**
     * Devuelve la hora de llegada del usuario
     *
     * @return La hora de llegada del usuario
     */
    public double getHoraDeLlegada() {
        return this.horaDeLlegada;
    }

    /**
     * Establece la hora de inicio de atención del usuario
     *
     * @param inicio Indica la hora de inicio de atención
     */
    public void setHoraInicioDeAtencion(double inicio) {
        this.horaInicioDeAtencion = inicio;
    }

    /**
     * Devuelve la hora de inicio de atención
     *
     * @return La hora de inicio de atención del usuario
     */
    public double getHoraInicioDeAtencion() {
        return this.horaInicioDeAtencion;
    }

    /**
     * Establece la hora de salida del cliente.
     *
     * @param salida Indica la hora de salida del usuario
     */
    public void setHoraDeSalida(double salida) {
        this.horaDeSalida = salida;
    }

    /**
     * Obtiene hora de salida del usuario
     *
     * @return La hora de salida
     */
    public double getHoraDeSalida() {
        return this.horaDeSalida;
    }

    /**
     * Establece la cantidad de tiempo que esperó el usuario para ser atendido.
     *
     * @param espera Indica el tiempo que esperó el usuario entre que llegó al
     * cajero y fue atendido
     */
    public void setTiempoDeEspera(double espera) {
        this.tiempoDeEspera = espera;
    }

    /**
     * Establece la cantidad que solicitó el
 usuario
     *
     * @param cantidad Indica la cantidad que solicitó el cliente
     * 
     */
    public void setCantidadSolicitada(double cantidad) {
        this.cantidadSolicitada = cantidad;
    }

    /**
     * Devuelve la cantidad que solicitó el
 usuario
     *
     * @return la cantidad solicitada por el usuario
     */
    public double getCantidadSolicitada() {
        return this.cantidadSolicitada;
    }

    /**
     * Operation setCantidadRecibida Establece la cantidad que recibió el
 usuario.
     *
     * @param cantidad Indica la cantidad que recibió el usuario.
     */
    public void setCantidadRecibida(double cantidad) {
        this.cantidadRecibida = cantidad;
    }

    /**
     * Operation getCantidadRecibida Devuelve la cantidad que recibió el
 usuario del cajero automático
     *
     * @return La cantidad que recibió el usuario.
     */
    public double getCantidadRecibida() {
        return this.cantidadRecibida;
    }

    /**
     * Devuelve la cantidad de tiempo que esperó el
 usuario hasta ser atendido.
     *
     * @return la cantidad de tiempo esperado
     */
    private double getTiempoDeEspera() {
        return this.tiempoDeEspera;
    }
    
    /**
     * Devuelve el número de llegada del usuario en cuestión
     * 
     * @return el número de llegada del usuario. 
     */

    public int getNumeroDeLlegada() {
        return numeroDeLlegada;
    }
    
    /**
     * Establece el número de llegada del usuario
     * @param numeroDeLlegada Indica el número de llegada
     */

    public void setNumeroDeLlegada(int numeroDeLlegada) {
        this.numeroDeLlegada = numeroDeLlegada;
    }
    
    

}
