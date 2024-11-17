package Strava.entity;

public enum TipoDeReto {
    
    DISTANCIA("distancia"), 
    TIEMPO("tiempo");

    private final String valor;

    TipoDeReto(String tipoDeReto) {
    	        this.valor = tipoDeReto;
    }

    public String getTipoDeReto() {
        return valor;
    }

    public static TipoDeReto fromString(String valor) {
        for (TipoDeReto tipo : TipoDeReto.values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor no válido para TipoDeReto: " + valor);
    }
}