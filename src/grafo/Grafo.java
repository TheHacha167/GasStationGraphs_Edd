
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


public boolean borrarNodo(String clave) {
    Nodo nodoAEliminar = encontrarNodoPorClave(clave);

    if (nodoAEliminar != null) {
        // Eliminar el nodo de la lista de nodos
        nodos.remove(nodoAEliminar);

        // Eliminar las aristas relacionadas con el nodo
        for (Nodo nodo : nodos) {
            Iterator<Arista> iterador = nodo.aristas.iterator();
            while (iterador.hasNext()) {
                if (iterador.next().destino.equals(nodoAEliminar)) {
                    iterador.remove();
                }
            }
        }

        return true;
    } else {
        return false;
    }
}

public int numeroNodos() {
    return nodos.size();
}

public boolean existeNodo(String clave) {
    return encontrarNodoPorClave(clave) != null;
}

public boolean borrarArista(String clave1, String clave2) {
    Nodo nodo1 = encontrarNodoPorClave(clave1);
    Nodo nodo2 = encontrarNodoPorClave(clave2);

    if (nodo1 != null && nodo2 != null) {
        // Eliminar la arista en ambos nodos
        nodo1.aristas.removeIf(arista -> arista.destino.equals(nodo2));
        nodo2.aristas.removeIf(arista -> arista.destino.equals(nodo1));

        return true;
    } else {
        return false;
    }
}

public boolean existeArista(String clave1, String clave2) {
    Nodo nodo1 = encontrarNodoPorClave(clave1);
    Nodo nodo2 = encontrarNodoPorClave(clave2);

    return nodo1 != null && nodo2 != null && nodo1.aristas.stream().anyMatch(arista -> arista.destino.equals(nodo2));
}

public int numeroAristas() {
    int count = 0;

    for (Nodo nodo : nodos) {
        count += nodo.aristas.size();
    }

    // Dividir por 2 ya que el grafo es no dirigido y cada arista se cuenta dos veces
    return count / 2;
}

public List<Gasolinera> obtenerNodos() {
    List<Gasolinera> gasolineras = new ArrayList<>();
    for (Nodo nodo : nodos) {
        gasolineras.add(nodo.gasolinera);
    }
    return gasolineras;
}

public class NodoEstado {
    Nodo nodo;
    double distancia;
    Nodo predecesor;

    public NodoEstado(Nodo nodo) {
        this.nodo = nodo;
        this.distancia = Double.MAX_VALUE;
        this.predecesor = null;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setPredecesor(Nodo predecesor) {
        this.predecesor = predecesor;
    }
}

public List<String> Dijkstra(String claveOrigen, String claveDestino) {
    Nodo origen = encontrarNodoPorClave(claveOrigen);
    Nodo destino = encontrarNodoPorClave(claveDestino);

    if (origen == null || destino == null) {
        return null;
    }

    List<NodoEstado> nodoEstados = new ArrayList<>();
    for (Nodo nodo : nodos) {
        nodoEstados.add(new NodoEstado(nodo));
    }

    NodoEstado estadoOrigen = obtenerNodoEstado(nodoEstados, origen);
    estadoOrigen.setDistancia(0.0);

    PriorityQueue<NodoEstado> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(ne -> ne.distancia));
    colaPrioridad.add(estadoOrigen);

    while (!colaPrioridad.isEmpty()) {
        NodoEstado estadoActual = colaPrioridad.poll();

        for (Arista arista : estadoActual.nodo.aristas) {
            Nodo adyacente = arista.destino;
            double pesoArista = arista.peso;
            NodoEstado estadoAdyacente = obtenerNodoEstado(nodoEstados, adyacente);

            double distanciaNueva = estadoActual.distancia + pesoArista;
            if (distanciaNueva < estadoAdyacente.distancia) {
                estadoAdyacente.setDistancia(distanciaNueva);
                estadoAdyacente.setPredecesor(estadoActual.nodo);
                colaPrioridad.add(estadoAdyacente);
            }
        }
    }

    return reconstruirCamino(nodoEstados, origen, destino);
}

private NodoEstado obtenerNodoEstado(List<NodoEstado> nodoEstados, Nodo nodo) {
    for (NodoEstado ne : nodoEstados) {
        if (ne.nodo.equals(nodo)) {
            return ne;
        }
    }
    return null;
}

private List<String> reconstruirCamino(List<NodoEstado> nodoEstados, Nodo origen, Nodo destino) {
    LinkedList<String> camino = new LinkedList<>();
    NodoEstado paso = obtenerNodoEstado(nodoEstados, destino);

    if (paso == null || paso.predecesor == null) {
        return null;
    }

    while (paso != null && !paso.nodo.equals(origen)) {
        camino.addFirst(paso.nodo.gasolinera.getClave());
        paso = obtenerNodoEstado(nodoEstados, paso.predecesor);
    }

    if (paso != null) {
        camino.addFirst(origen.gasolinera.getClave());
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


