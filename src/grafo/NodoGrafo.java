// Define el paquete en el que se encuentra esta interfaz
package grafo;

// Define una interfaz pública llamada NodoGrafo
public interface NodoGrafo {
    // Declara un método llamado getClave que todas las clases que implementen esta interfaz deben definir.
    // Este método no toma ningún argumento y devuelve una cadena de texto.
    String getClave();
}