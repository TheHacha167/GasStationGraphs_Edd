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
                System.out.println("Insertada gasolinera: " + gasolinera);
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
                System.out.println("Insertada arista entre " + datos[0] + " y " + datos[1] + " con distancia " + distancia);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de distancias: " + e.getMessage());
        }

        System.out.println("Finalizando el programa...");
    }
}