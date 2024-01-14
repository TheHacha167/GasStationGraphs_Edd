package grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Principal {

    public static void main(String[] args) {
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









/*
 * Procesamiento de gasolineras.csv
 * 
 * String pathFicheroGasolineras = directorioProyecto + separador + "data" + separador + "gasolineras.csv";

try (BufferedReader br = new BufferedReader(new FileReader(pathFicheroGasolineras))) {
    String linea;
    while ((linea = br.readLine()) != null) {
        String[] datosGasolinera = linea.split(";");
        Gasolinera gasolinera = new Gasolinera(datosGasolinera[2].trim(), datosGasolinera[4].trim(), datosGasolinera[3].trim(), datosGasolinera[0].trim(), datosGasolinera[1].trim());
        grafo.insertarNodo(gasolinera);
    }
} catch(Exception e){
    e.printStackTrace();
}

 * 
 * 
 * Procesamiento de distancias-gasolineras.csv
 * 
 * String pathFicheroDistanciasGasolineras = directorioProyecto + separador + "data" + separador + "distancias-gasolineras.csv";

try (BufferedReader br = new BufferedReader(new FileReader(pathFicheroDistanciasGasolineras))) {
    String linea;
    while ((linea = br.readLine()) != null) {
        String[] datosDistancia = linea.split(";");
        Double distancia = Double.parseDouble(datosDistancia[2].replace(",", "."));
        grafo.insertarArista(datosDistancia[0].trim(), datosDistancia[1].trim(), distancia);
    }
} catch(Exception e){
    e.printStackTrace();
}

 * 
 * 
 * 
 */