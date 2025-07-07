package org.example.persistencia;


import java.io.*;
import java.util.*;

public class PersistenciaTexto {

    public static List<String[]> leerCSV(String rutaArchivo) {
        List<String[]> datos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean esPrimeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (esPrimeraLinea) {
                    esPrimeraLinea = false;
                    continue;
                }
                String[] campos = linea.split(",");
                datos.add(campos);
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return datos;
    }

    public static void sobrescribirCSV(String ruta, List<String[]> datos) {
        try (PrintWriter writer = new PrintWriter(ruta)) {
            for (String[] fila : datos) {
                writer.println(String.join(",", fila));
            }
        } catch (Exception e) {
            System.err.println("Error al sobrescribir CSV: " + e.getMessage());
        }
    }

    public static void agregarLineaCSV(String rutaArchivo, String[] datos) {
        try (FileWriter fw = new FileWriter(rutaArchivo, true)) {
            fw.write(String.join(",", datos) + "\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }


}