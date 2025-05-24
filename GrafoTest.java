import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

public class GrafoTest {

    private Grafo grafo;

    @BeforeEach
    public void setUp() throws IOException {
        String datos = """
            A B 5 6 7 8
            B C 3 4 5 6
            C D 2 2.5 3 4
            A D 20 25 30 40
            """;

        FileWriter fw = new FileWriter("testLogistica.txt");
        fw.write(datos);
        fw.close();

        grafo = new Grafo("testLogistica.txt");
    }

    @Test
    public void testCargaCiudades() {
        assertEquals(4, grafo.getNumCiudades());
        assertEquals("A", grafo.getNombresCiudades()[0]);
        assertEquals("D", grafo.getNombresCiudades()[3]);
    }

    @Test
    public void testEliminarConexion() {
        grafo.eliminarConexion("B", "C");
        double peso = grafo.getMatrizPesos()[grafo.obtenerIndice("B")][grafo.obtenerIndice("C")];
        assertEquals(Double.POSITIVE_INFINITY, peso);
    }

    @Test
    public void testCambioClima() {
        grafo.setClima(Grafo.Clima.TORMENTA);
        double[][] matriz = grafo.getMatrizPesos();
        int a = grafo.obtenerIndice("A");
        int d = grafo.obtenerIndice("D");
        assertEquals(40, matriz[a][d]);
    }
}
