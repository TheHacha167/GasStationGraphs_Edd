/*package grafo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Nodo> nodos;

    public Grafo() {
        nodos = new ArrayList<>();
    }

    public void insertarNodo(Gasolinera gasolinera) {
        nodos.add(new Nodo(gasolinera));
    }

    public void insertarArista(String clave1, String clave2, double peso) {
        Nodo nodo1 = encontrarNodoPorClave(clave1);
        Nodo nodo2 = encontrarNodoPorClave(clave2);
        if (nodo1 != null && nodo2 != null) {
            nodo1.aristas.add(new Arista(nodo2, peso));
            nodo2.aristas.add(new Arista(nodo1, peso)); // Para grafo no dirigido
        }


        
    }

    private Nodo encontrarNodoPorClave(String clave) {
        for (Nodo nodo : nodos) {
           
            if (nodo.gasolinera.getClave().equals(clave)) {
                return nodo;
                }
                }
                return null;
                }
                private class Nodo {
                    Gasolinera gasolinera;
                    List<Arista> aristas;
                
                    Nodo(Gasolinera gasolinera) {
                        this.gasolinera = gasolinera;
                        this.aristas = new ArrayList<>();
                    }
                }
                
                private class Arista {
                    Arista(Nodo destino, double peso) {
                        // Remove unused fields
                    }
                }
                
               
               
               
               
               
               
               
                // Aquí puedes añadir otros métodos como toString, eliminarNodo, eliminarArista, etc.
            }                */



            package grafo;

            import java.util.*;
            
            public class Grafo {
                private List<Nodo> nodos;
            
                public Grafo() {
                    nodos = new ArrayList<>();
                }
            
                public void insertarNodo(Gasolinera gasolinera) {
                    nodos.add(new Nodo(gasolinera));
                }
            
                public void insertarArista(String clave1, String clave2, double peso) {
                    Nodo nodo1 = encontrarNodoPorClave(clave1);
                    Nodo nodo2 = encontrarNodoPorClave(clave2);
                    if (nodo1 != null && nodo2 != null) {
                        nodo1.aristas.add(new Arista(nodo2, peso));
                        nodo2.aristas.add(new Arista(nodo1, peso)); // Para grafo no dirigido
                    }
                }
            
                private Nodo encontrarNodoPorClave(String clave) {
                    for (Nodo nodo : nodos) {
                        if (nodo.gasolinera.getClave().equals(clave)) {
                            return nodo;
                        }
                    }
                    return null;
                }
            
                // Implementaciones de los métodos adicionales (borrarNodo, existeNodo, etc.)
                // ... [Métodos previamente proporcionados] ...
            

// TODO: Implementar método borrarNodo
    // Este método deberá eliminar el nodo especificado y todas las aristas relacionadas con él.
    // Consideraciones:
    // 1. Buscar el nodo en la lista de nodos usando su clave.
    // 2. Si el nodo existe, eliminarlo de la lista de nodos.
    // 3. Recorrer todos los nodos restantes y eliminar cualquier arista que conecte con el nodo eliminado.
    // 4. Asegurarse de que el método maneje correctamente el caso en que el nodo no exista.


// TODO: Implementar método existeNodo
    // Este método verificará si un nodo existe en el grafo basándose en su clave.
    // Consideraciones:
    // 1. Recorrer la lista de nodos y buscar un nodo que coincida con la clave dada.
    // 2. Retornar verdadero si el nodo existe, falso en caso contrario.

// TODO: Implementar método existeNodo
    // Este método verificará si un nodo existe en el grafo basándose en su clave.
    // Consideraciones:
    // 1. Recorrer la lista de nodos y buscar un nodo que coincida con la clave dada.
    // 2. Retornar verdadero si el nodo existe, falso en caso contrario.

// TODO: Implementar método borrarArista
    // Este método eliminará una arista entre dos nodos especificados por sus claves.
    // Consideraciones:
    // 1. Verificar que ambos nodos existen en el grafo.
    // 2. Si ambos nodos existen, buscar la arista que los conecta y eliminarla de las listas de aristas de ambos nodos.
    // 3. Asegurarse de que el método maneje correctamente el caso en que uno o ambos nodos no existan o la arista no exista.


// TODO: Implementar método existeArista
    // Este método verificará si una arista existe entre dos nodos especificados por sus claves.
    // Consideraciones:
    // 1. Verificar que ambos nodos existen.
    // 2. Si ambos nodos existen, buscar en sus listas de aristas para ver si existe una arista que los conecte.
    // 3. Retornar verdadero si la arista existe, falso en caso contrario.

// TODO: Implementar método numeroAristas
    // Este método contará el número total de aristas en el grafo.
    // Consideraciones:
    // 1. Recorrer todos los nodos y sumar la cantidad de aristas de cada uno.
    // 2. Dividir el total por 2, ya que el grafo es no dirigido y cada arista se cuenta dos veces (una en cada nodo).



                // Implementación del algoritmo de Dijkstra
                public List<String> Dijkstra(String claveOrigen, String claveDestino) {
                    Nodo origen = encontrarNodoPorClave(claveOrigen);
                    Nodo destino = encontrarNodoPorClave(claveDestino);
            
                    if (origen == null || destino == null) {
                        return null; // Uno de los nodos no existe
                    }
            
                    Map<Nodo, Nodo> predecesores = new HashMap<>();
                    Map<Nodo, Double> distancia = new HashMap<>();
                    PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparing(distancia::get));
            
                    for (Nodo nodo : nodos) {
                        distancia.put(nodo, Double.MAX_VALUE);
                        predecesores.put(nodo, null);
                    }
            
                    distancia.put(origen, 0.0);
                    colaPrioridad.add(origen);
            
                    while (!colaPrioridad.isEmpty()) {
                        Nodo actual = colaPrioridad.poll();
            
                        for (Arista arista : actual.aristas) {
                            Nodo adyacente = arista.destino;
                            double pesoArista = arista.peso;
                            double distanciaNueva = distancia.get(actual) + pesoArista;            
                            if (distanciaNueva < distancia.get(adyacente)) {
                                distancia.put(adyacente, distanciaNueva);
                                predecesores.put(adyacente, actual);
                                colaPrioridad.add(adyacente);
                            }
                        }
                    }
                
                    return reconstruirCamino(predecesores, origen, destino);
                }
                
                private List<String> reconstruirCamino(Map<Nodo, Nodo> predecesores, Nodo origen, Nodo destino) {
                    LinkedList<String> camino = new LinkedList<>();
                    Nodo paso = destino;
                
                    // Comprobar si hay un camino
                    if (predecesores.get(paso) == null) {
                        return null;
                    }
                
                    camino.add(paso.gasolinera.getClave());
                    while (predecesores.get(paso) != null) {
                        paso = predecesores.get(paso);
                        camino.addFirst(paso.gasolinera.getClave());
                    }
                
                    return new ArrayList<>(camino);
                }
                
                @Override
                public String toString() {
                    StringBuilder sb = new StringBuilder();
                    for (Nodo nodo : nodos) {
                        sb.append(nodo.gasolinera.getClave()).append(": ");
                        for (Arista arista : nodo.aristas) {
                            sb.append(arista.destino.gasolinera.getClave())
                              .append("(")
                              .append(arista.peso)
                              .append("), ");
                        }
                        if (!nodo.aristas.isEmpty()) {
                            sb.setLength(sb.length() - 2);  // Remove trailing comma and space
                        }
                        sb.append("\n");
                    }
                    return sb.toString();
                }
                
                // Clases internas Nodo y Arista
                private class Nodo {
                    Gasolinera gasolinera;
                    List<Arista> aristas;
                
                    Nodo(Gasolinera gasolinera) {
                        this.gasolinera = gasolinera;
                        this.aristas = new ArrayList<>();
                    }
                }
                
                private class Arista {
                    Nodo destino;
                    double peso;
                
                    Arista(Nodo destino, double peso) {
                        this.destino = destino;
                        this.peso = peso;
                    }
                }}
                