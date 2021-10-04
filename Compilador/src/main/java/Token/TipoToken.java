package Token;

public enum TipoToken {

    ID(1), 
    Numero(2), 
    Decimal(4), 
    SPuntuacion(5),
    Operador(6), 
    SAgrupacion(7);

    private int estadoAceptacion; // Estado de Aceptación

    private TipoToken(int estadoAceptacion) {
        this.estadoAceptacion = estadoAceptacion;
    }

    public int getEstadoAcept() {
        return estadoAceptacion;
    }
    
}