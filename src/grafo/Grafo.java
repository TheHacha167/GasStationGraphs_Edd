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
                