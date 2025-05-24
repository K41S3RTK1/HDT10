import java.io.*;
import java.util.*;

public class Grafo {
    public enum Clima { NORMAL, LLUVIA, NIEVE, TORMENTA }

    private Map<String, Integer> ciudades;
    private String[] nombresCiudades;
    private TiempoViaje[][] tiempos; // guarda los 4 tiempos entre nodos
    private double[][] matrizPesos;   
    private int numCiudades;
    private Clima climaActual = Clima.NORMAL;

    // Clase interna para tiempos por clima
    private static class TiempoViaje {
        double normal, lluvia, nieve, tormenta;
        boolean existe = false;
    }

    public Grafo(String archivo) {
        ciudades = new LinkedHashMap<>();
        List<String[]> conexiones = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                if (partes.length < 6) continue;

                String ciudad1 = partes[0];
                String ciudad2 = partes[1];

                if (!ciudades.containsKey(ciudad1)) {
                    ciudades.put(ciudad1, ciudades.size());
                }
                if (!ciudades.containsKey(ciudad2)) {
                    ciudades.put(ciudad2, ciudades.size());
                }

                conexiones.add(partes);
            }

            numCiudades = ciudades.size();
            nombresCiudades = new String[numCiudades];
            for (Map.Entry<String, Integer> entry : ciudades.entrySet()) {
                nombresCiudades[entry.getValue()] = entry.getKey();
            }

            tiempos = new TiempoViaje[numCiudades][numCiudades];
            for (int i = 0; i < numCiudades; i++) {
                for (int j = 0; j < numCiudades; j++) {
                    tiempos[i][j] = new TiempoViaje();
                }
            }

            for (String[] con : conexiones) {
                int i = ciudades.get(con[0]);
                int j = ciudades.get(con[1]);
                TiempoViaje t = new TiempoViaje();
                t.normal = Double.parseDouble(con[2]);
                t.lluvia = Double.parseDouble(con[3]);
                t.nieve = Double.parseDouble(con[4]);
                t.tormenta = Double.parseDouble(con[5]);
                t.existe = true;
                tiempos[i][j] = t;
            }

            actualizarMatrizSegunClima();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void actualizarMatrizSegunClima() {
        matrizPesos = new double[numCiudades][numCiudades];
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < numCiudades; j++) {
                if (i == j) {
                    matrizPesos[i][j] = 0;
                } else if (!tiempos[i][j].existe) {
                    matrizPesos[i][j] = Double.POSITIVE_INFINITY;
                } else {
                    switch (climaActual) {
                        case NORMAL: matrizPesos[i][j] = tiempos[i][j].normal; break;
                        case LLUVIA: matrizPesos[i][j] = tiempos[i][j].lluvia; break;
                        case NIEVE: matrizPesos[i][j] = tiempos[i][j].nieve; break;
                        case TORMENTA: matrizPesos[i][j] = tiempos[i][j].tormenta; break;
                    }
                }
            }
        }
    }

    public void setClima(Clima clima) {
        this.climaActual = clima;
        actualizarMatrizSegunClima();
    }

    public void modificarTiempos(String ciudad1, String ciudad2, double normal, double lluvia, double nieve, double tormenta) {
        int i = obtenerIndice(ciudad1);
        int j = obtenerIndice(ciudad2);
        if (i != -1 && j != -1) {
            TiempoViaje t = new TiempoViaje();
            t.normal = normal;
            t.lluvia = lluvia;
            t.nieve = nieve;
            t.tormenta = tormenta;
            t.existe = true;
            tiempos[i][j] = t;
            actualizarMatrizSegunClima();
        }
    }

    public void eliminarConexion(String ciudad1, String ciudad2) {
        int i = obtenerIndice(ciudad1);
        int j = obtenerIndice(ciudad2);
        if (i != -1 && j != -1) {
            tiempos[i][j] = new TiempoViaje(); 
            actualizarMatrizSegunClima();
        }
    }

    public double[][] getMatrizPesos() {
        return matrizPesos;
    }

    public int getNumCiudades() {
        return numCiudades;
    }

    public String[] getNombresCiudades() {
        return nombresCiudades;
    }

    public int obtenerIndice(String ciudad) {
        return ciudades.getOrDefault(ciudad, -1);
    }
}
