import java.util.ArrayList;
import java.util.List;

public class Floyd {
    private double[][] distancias;
    private int[][] caminos;
    private int tamaño;

    public Floyd(double[][] matrizPesos) {
        tamaño = matrizPesos.length;
        distancias = new double[tamaño][tamaño];
        caminos = new int[tamaño][tamaño];
        floydWarshall(matrizPesos);
    }

    private void floydWarshall(double[][] grafo) {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                distancias[i][j] = grafo[i][j];
                caminos[i][j] = (grafo[i][j] != Double.POSITIVE_INFINITY && i != j) ? j : -1;
            }
        }

        for (int k = 0; k < tamaño; k++) {
            for (int i = 0; i < tamaño; i++) {
                for (int j = 0; j < tamaño; j++) {
                    if (distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        caminos[i][j] = caminos[i][k];
                    }
                }
            }
        }
    }

    public double[][] getDistancias() {
        return distancias;
    }

    public int[][] getCaminos() {
        return caminos;
    }

    public List<Integer> reconstruirCamino(int origen, int destino) {
        List<Integer> camino = new ArrayList<>();
        if (caminos[origen][destino] == -1) return camino;
        camino.add(origen);
        while (origen != destino) {
            origen = caminos[origen][destino];
            camino.add(origen);
        }
        return camino;
    }
}
