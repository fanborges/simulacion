package simulacion;

/**
 *Modela el cajero automático
 * @author  Luis Clemente Zúñiga Flores
 */
public class Cajero {
    /**
     * Attributes
     */

    private double cantidadInicial;
    /**
     * Guarda la cantidad mínima con la que se solicita reorden
     */
    private double puntoDeReorden;
    /**
     * Guarda el estatus del cajero (disponible si tiene dinero, no disponible
     * si es l
     */
    private boolean enLinea;
    /**
     * Guarda el estatus del cajero (abierto o cerrado dependiendo de la hora)
     */
    private boolean abierto;
    /**
     * Guarda la cantidad que tiene el cajero al momento.
     */
    
    /**
     * Indica si el cajero está o no ocupado
     */
    private boolean ocupado;
    
    /**
     * Guarda la cantidad de efectivo actual en el cajero.
     */
    
    /**
     * Guarda el tiempo que estuvo offline el cajero
     */
    private double tiempoOffLine;
    
    private double cantidadActual;
    /**
     * Indica la cantidad de usuarios que están en la fila (esperando a ser
     * atendidos.
     */
    
    /**
     * Guarda la cantidad que ha sido abastecida
     */
    private double cantidadAbastecida;
    
    /**
     * Constructor
     */
    public Cajero() {
        this.abierto = false;
        this.cantidadInicial=500000;
        this.cantidadActual=this.cantidadInicial;
        enLinea=true;
        ocupado = false;
        abierto = true;
        puntoDeReorden = 30000;
    }
    
    public Cajero(double cantidad, double reorden){
        this.abierto = false;
        this.cantidadInicial=cantidad;
        this.cantidadActual=this.cantidadInicial;
        enLinea=true;
        ocupado = false;
        abierto = true;
        puntoDeReorden = reorden;
        
    }

    /**
     * Devuelve el tiempo que estuvo fuera de línea el cajero.
     * @return el tiempo offline
     */
    public double getTiempoOffLine() {
        return tiempoOffLine;
    }
    
    /**
     * establece el tiempo que estuvo fuera de línea el cajero.
     * @param tiempoOffLine Indica el tiempo fuera de línea del cajero.
     */

    public void setTiempoOffLine(double tiempoOffLine) {
        this.tiempoOffLine += tiempoOffLine;
    }
    
    
    /**
     * Obtiene la cantidad abastecida al cajero. Dicha cantidad es (cantidad fija - cantidad actual)
     * @return la cantidad abastecida al cajero.
     */
    public double getCantidadAbastecida() {
        return cantidadAbastecida;
    }
    
    /**
     * Establece la cantidad abastecida al cajero
     * @param cantidadAbastecida indica la cantidad abastecida al cajero.
     */

    public void setCantidadAbastecida(double cantidadAbastecida) {
        this.cantidadAbastecida = cantidadAbastecida;
    }
    
    /**
     * Aumenta la cantidad abastecida. Para efectos estadísticos.
     * @param cantidad indica la cantidad que se aumentará.
     */

    public void aumentaCantidadAbastecida(double cantidad){
        cantidadAbastecida+=cantidad;
    }
    
    /**
     * Determina si el cajero está ocupado o no
     * @return <CODE>true</CODE> si el cajero está libre, <CODE>false</CODE> si está ocupado.
     */
    public boolean isOcupado() {
        return ocupado;
    }
    
    /**
     * Establece si el cajero está o no ocupado.
     * @param ocupado Indica si el cajero está o no ocupado.
     */

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    /**
     * Devuelve la cantidad actual que hay en el cajero.
     * @return la cantidad actual en el cajero.
     */

    public double getCantidadActual() {
        return cantidadActual;
    }
    
    /**
     * Establece la cantidad actual en el cajero.
     * @param cantidadActual indica la cantidad actual en el cajero.
     */

    public void setCantidadActual(double cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    /**
     * Establece la cantidad inicial
     *
     * @param cantidad Indica la cantidad de dinero con la que inicia el
     * cajero
     */
    public void setCantidadInicial(double cantidad) {
        this.cantidadInicial = cantidad;
    }

    /**
     * Devuelve la cantidad inicial con la que inicia el cajero.
     *
     * @return la cantidad inicial que hay en el cajero.
     */
    public double getCantidadInicial() {
        return this.cantidadInicial;
    }

    /**
     * Establece la cantidad de punto de reorden del
 cajero
     *
     * @param cantidad Indica la cantidad del punto de reorden
     */
    public void setPuntoDeReorden(double cantidad) {
        this.puntoDeReorden = cantidad;
    }

    /**
     * Devuelve el punto de reorden del cajero.
     *
     * @return el punto de reorden del cajero.
     */
    public double getPuntoDeReorden() {
        return this.puntoDeReorden;
    }

    /**
     * Establece el cajero en línea o furea de línea. 
     * @param online Indica si está o no en línea el cajero
     */
    public void setEnLinea(boolean online) {
        this.enLinea = online;
    }

    /**
     * Devuelve <CODE>true</CODE> si el cajero está en línea, y <CODE>false</CODE> lo contrario.
     *
     * @return boolean
     */
    public boolean isEnLinea() {
        return this.enLinea;
    }

    /**
     * Establece si el banco está abierto o cerrado (depende del horario)
     *
     * @param estatus Indica si está abierto o cerrado el banco, depende del
     * horario.
     */
    public void setAbierto(boolean estatus) {
        this.abierto = estatus;
    }

    /**
     * Devuelve <CODE>true</CODE> si el cajero está abierto o <CODE>false</CODE> si está cerrado. Depende del horario
     *
     * @return si está o no abierto.
     */
    public boolean isAbierto() {
        return this.abierto;
    }

    /**
     * Realiza la operación de efectivo.
     * Cantidad Actual-=cantidad solicitada
     *
     * @param cantidad Cantidad solicitada por el cliente
     *
     */
    public void disposicionDeEfectivo(double cantidad) {
        this.cantidadActual -= cantidad;
    }


}


