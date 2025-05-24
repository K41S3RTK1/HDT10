import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

public class FloydTest {

    private Floyd floyd;
    private Grafo grafo;

    @BeforeEach
    public void setUp() {
        String datos = """
            A B 2 3 4 5
            B C 3 4 5 6
            A C 10 11 12 13
            """;

        try {
            FileWriter fw = new FileWriter("testFloyd.txt");
            fw.write(datos);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        grafo = new Grafo("testFloyd.txt");
        floyd = new Floyd(grafo.getMatrizPesos());
    }

    @Test
    public void testDistanciaMasCorta() {
        int a = grafo.obtenerIndice("A");
        int c = grafo.obtenerIndice("C");
        assertEquals(5, floyd.getDistancias()[a][c], 0.0001); // A->B->C
    }

    @Test
    public void testRutaMasCorta() {
        int a = grafo.obtenerIndice("A");
        int c = grafo.obtenerIndice("C");

        var ruta = floyd.reconstruirCamino(a, c);
        assertEquals(3, ruta.size());
        assertEquals(a, ruta.get(0));
        assertEquals(grafo.obtenerIndice("B"), ruta.get(1));
        assertEquals(c, ruta.get(2));
    }
}
