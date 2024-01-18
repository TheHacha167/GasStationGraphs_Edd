package grafo;

// La clase Gasolinera implementa la interfaz NodoGrafo
public class Gasolinera implements NodoGrafo {
    // Variables de instancia para almacenar los detalles de la gasolinera
    private String clave;
    private String rotulo;
    private String direccion;
    private String municipio;
    private String localidad;

    // Constructor de la clase Gasolinera
    public Gasolinera(String clave, String rotulo, String direccion, String municipio, String localidad) {
        // Inicializa las variables de instancia con los valores proporcionados
        this.clave = clave;
        this.rotulo = rotulo;
        this.direccion = direccion;
        this.municipio = municipio;
        this.localidad = localidad;
    }

    // Método getter para obtener la clave de la gasolinera
    public String getClave() {
        return clave;
    }

    // Método getter para obtener el rótulo de la gasolinera
    public String getRotulo() {
        return rotulo;
    }
    
    // Método para representar la gasolinera como una cadena de texto
    public String toString() {
        return "Gasolinera{" +
               "clave='" + clave + '\'' +
               ", rotulo='" + rotulo + '\'' +
               ", direccion='" + direccion + '\'' +
               ", municipio='" + municipio + '\'' +
               ", localidad='" + localidad + '\'' +
               '}';
    }
    // Otros métodos como getters si son necesarios
}