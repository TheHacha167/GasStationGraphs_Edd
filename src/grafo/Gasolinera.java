package grafo;

public class Gasolinera implements NodoGrafo {
    private String clave;
    private String rotulo;
    private String direccion;
    private String municipio;
    private String localidad;

    public Gasolinera(String clave, String rotulo, String direccion, String municipio, String localidad) {
        this.clave = clave;
        this.rotulo = rotulo;
        this.direccion = direccion;
        this.municipio = municipio;
        this.localidad = localidad;
    }

    @Override
    public String getClave() {
        return clave;
    }

    // Otros métodos como getters si son necesarios
}
