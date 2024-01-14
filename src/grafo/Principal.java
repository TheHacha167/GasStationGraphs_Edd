/*package grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Principal {
    
    public static void main(String[] args) {
       // System.out.println("funcionando ");

        Grafo grafo = new Grafo();
        String pathGasolineras = "data/gasolineras.csv";
        String pathDistancias = "data/distancias-gasolineras.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(pathGasolineras))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                Gasolinera gasolinera = new Gasolinera(datos[2], datos[4], datos[3], datos[0], datos[1]);
                grafo.insertarNodo(gasolinera);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de gasolineras: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(pathDistancias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                double distancia = Double.parseDouble(datos[2].replace(",", "."));
                grafo.insertarArista(datos[0], datos[1], distancia);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de distancias entre gasolineras: " + e.getMessage());
        }


    // Leer y procesar el archivo de distancias entre gasolineras
    try (BufferedReader br = new BufferedReader(new FileReader(pathDistancias))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(";");
            double distancia = Double.parseDouble(datos[2].replace(",", "."));
            grafo.insertarArista(datos[0], datos[1], distancia);
        }
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de distancias entre gasolineras: " + e.getMessage());
    }

    // Aquí puedes añadir operaciones adicionales sobre el grafo si es necesario

}
}

*/
package grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Principal {
    
    public static void main(String[] args) {
        System.out.println("Iniciando el programa...");

        Grafo grafo = new Grafo();
        String pathGasolineras = "data/gasolineras.csv";
        String pathDistancias = "data/distancias-gasolineras.csv";

        System.out.println("Leyendo archivo de gasolineras...");
        try (BufferedReader br = new BufferedReader(new FileReader(pathGasolineras))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                Gasolinera gasolinera = new Gasolinera(datos[2], datos[4], datos[3], datos[0], datos[1]);
                grafo.insertarNodo(gasolinera);
                //System.out.println("Insertada gasolinera: " + gasolinera);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de gasolineras: " + e.getMessage());
        }

        System.out.println("Leyendo archivo de distancias...");
        try (BufferedReader br = new BufferedReader(new FileReader(pathDistancias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                double distancia = Double.parseDouble(datos[2].replace(",", "."));
                grafo.insertarArista(datos[0], datos[1], distancia);
                //System.out.println("Insertada arista entre " + datos[0] + " y " + datos[1] + " con distancia " + distancia);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de distancias: " + e.getMessage());
        }


// Insertar nodos de prueba

// Insertar nodos de prueba
grafo.insertarNodo(new Gasolinera("Nodo1", "Rotulo1", "Direccion1", "Municipio1", "Localidad1"));
grafo.insertarNodo(new Gasolinera("Nodo2", "Rotulo2", "Direccion2", "Municipio2", "Localidad2"));
grafo.insertarNodo(new Gasolinera("Nodo3", "Rotulo3", "Direccion3", "Municipio3", "Localidad3"));

// Insertar aristas de prueba
grafo.insertarArista("Nodo1", "Nodo2", 10.0);
grafo.insertarArista("Nodo2", "Nodo3", 15.0);

// Prueba: número de nodos y aristas
System.out.println("Número de nodos: " + grafo.numeroNodos());
System.out.println("Número de aristas: " + grafo.numeroAristas());

// Prueba: existencia de nodos y aristas
System.out.println("¿Existe el nodo Nodo2? " + grafo.existeNodo("Nodo2"));
System.out.println("¿Existe una arista entre Nodo1 y Nodo2? " + grafo.existeArista("Nodo1", "Nodo2"));

// Prueba: borrar nodo y arista
grafo.borrarNodo("Nodo3");
System.out.println("Número de nodos después de borrar Nodo3: " + grafo.numeroNodos());
grafo.borrarArista("Nodo1", "Nodo2");
System.out.println("Número de aristas después de borrar arista entre Nodo1 y Nodo2: " + grafo.numeroAristas());

// Prueba: algoritmo de Dijkstra
List<String> camino = grafo.Dijkstra("Nodo1", "Nodo2");
System.out.println("Camino más corto entre Nodo1 y Nodo2: " + camino);

// Prueba: representación del grafo
//System.out.println("Representación del grafo:\n" + grafo.toString());

// Finalización del programa
System.out.println("Finalizando el programa...");

        //System.out.println("Finalizando el programa...");
    }
}