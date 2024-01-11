package graph;

/**
 * Interface GraphNode
 * Representa un nodo en un grafo. Esta interfaz deberá ser implementada por cualquier clase
 * que desee ser considerada como un nodo en el grafo (por ejemplo, la clase GasStation).
 * 
 * Métodos:
 * - String getClave(): Este método deberá ser implementado para retornar la clave única
 *   que identifica a cada nodo dentro del grafo.
 */
public interface GraphNode {
    String getClave();
}
