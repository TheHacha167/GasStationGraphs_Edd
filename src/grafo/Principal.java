package grafo;

import java.io.BufferedReader; // Se utiliza para leer texto de una entrada de caracteres de manera eficiente.
import java.io.IOException; // Se utiliza para manejar errores de entrada/salida.
import java.util.List; // Se utiliza para trabajar con listas, que son estructuras de datos que pueden contener múltiples elementos.
import java.util.Scanner; // Se utiliza para leer la entrada del usuario.
import java.io.FileReader; // Se utiliza para leer archivos de texto.

public class Principal {
    
    public static void main(String[] args) {
        // Inicio del programa
        System.out.println("Iniciando el programa...");

        // Creación de un nuevo grafo
        Grafo grafo = new Grafo();

        // Rutas a los archivos CSV
        String pathGasolineras = "data/gasolineras.csv";
        String pathDistancias = "data/distancias-gasolineras.csv";
        
        // Lectura del archivo de gasolineras
        System.out.println("Leyendo archivo de gasolineras...");
        try (BufferedReader br = new BufferedReader(new FileReader(pathGasolineras))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en datos
                String[] datos = linea.split(";");
                // Crear una nueva gasolinera con los datos
                Gasolinera gasolinera = new Gasolinera(datos[2], datos[4], datos[3], datos[0], datos[1]);
                // Insertar la gasolinera en el grafo
                grafo.insertarNodo(gasolinera);
                System.out.println("Insertada gasolinera: " + gasolinera);
            }
        } catch (IOException e) {
            // Manejo de errores de lectura del archivo
            System.out.println("Error al leer el archivo de gasolineras: " + e.getMessage());
        }

        // Lectura del archivo de distancias
        System.out.println("Leyendo archivo de distancias...");
        try (BufferedReader br = new BufferedReader(new FileReader(pathDistancias))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en datos
                String[] datos = linea.split(";");
                // Convertir la distancia a un número decimal
                double distancia = Double.parseDouble(datos[2].replace(",", "."));
                // Insertar la arista en el grafo
                grafo.insertarArista(datos[0], datos[1], distancia);
                System.out.println("Insertada arista entre " + datos[0] + " y " + datos[1] + " con distancia " + distancia);
            }
        } catch (IOException e) {
            // Manejo de errores de lectura del archivo
            System.out.println("Error al leer el archivo de distancias: " + e.getMessage());
        }

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

        // Prueba: algoritmo de Dijkstra
        List<String> camino = grafo.Dijkstra("Nodo1", "Nodo2");
        System.out.println("Camino más corto entre Nodo1 y Nodo2: " + camino);

        // Prueba: borrar nodo y arista
        grafo.borrarNodo("Nodo3");
        System.out.println("Número de nodos después de borrar Nodo3: " + grafo.numeroNodos());
        grafo.borrarArista("Nodo1", "Nodo2");
        System.out.println("Número de aristas después de borrar arista entre Nodo1 y Nodo2: " + grafo.numeroAristas());
        grafo.borrarNodo("Nodo1");
        grafo.borrarNodo("Nodo2");

        // Después de cargar los datos
        System.out.println("Lista de Nodos:");
        for (Gasolinera g : grafo.obtenerNodos()) {
            System.out.println(g.getClave());
        }

        // Solicitar al usuario que ingrese las claves de los nodos
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el clave del primer nodo: ");
        String clave1 = scanner.nextLine();
        System.out.print("Ingrese el clave del segundo nodo: ");
        String clave2 = scanner.nextLine();

        // Encontrar el camino más corto entre los nodos
        List<String> camino1 = grafo.Dijkstra(clave1, clave2);
        if (camino1 != null) {
            System.out.println("Camino más corto: " + camino1);
        } else {
            System.out.println("No se encontró un camino.");
        }
        scanner.close();

        // Finalización del programa
        System.out.println("Finalizando el programa...");
    }
}