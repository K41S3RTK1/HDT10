import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Grafo grafo = new Grafo("logistica.txt"); // asegúrate de tener este archivo
        Floyd floyd = new Floyd(grafo.getMatrizPesos());

        String[] nombres = grafo.getNombresCiudades();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Camino más corto entre dos ciudades");
            System.out.println("2. Indicar centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");

            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            if (opcion == 1) {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();

                int i = grafo.obtenerIndice(origen);
                int j = grafo.obtenerIndice(destino);

                if (i == -1 || j == -1) {
                    System.out.println("Una o ambas ciudades no existen.");
                    continue;
                }

                double distancia = floyd.getDistancias()[i][j];
                if (distancia == Double.POSITIVE_INFINITY) {
                    System.out.println("No existe ruta entre " + origen + " y " + destino);
                } else {
                    List<Integer> camino = floyd.reconstruirCamino(i, j);
                    System.out.printf("Distancia mínima: %.2f horas\n", distancia);
                    System.out.print("Ruta: ");
                    for (int idx = 0; idx < camino.size(); idx++) {
                        System.out.print(nombres[camino.get(idx)]);
                        if (idx < camino.size() - 1) System.out.print(" -> ");
                    }
                    System.out.println();
                }

            } 
            
            else if (opcion == 2) {
                int centro = CentroGrafo.calcularCentro(floyd.getDistancias());
                System.out.println("Centro del grafo: " + grafo.getNombresCiudades()[centro]);
            }

            else if (opcion == 3) {
                System.out.println("\n--- Modificar Grafo ---");
                System.out.println("1. Interrupción de tráfico (elimina arco)");
                System.out.println("2. Agregar nueva conexión");
                System.out.println("3. Cambiar clima entre ciudades(modificar peso arco)");
                int opcion2 = sc.nextInt();
                sc.nextLine();

                if (opcion2 == 1) {
                    System.out.print("Ciudad origen: ");
                    String ciudad1 = sc.nextLine();
                    System.out.print("Ciudad destino: ");
                    String ciudad2 = sc.nextLine();
                    grafo.eliminarConexion(ciudad1, ciudad2);
                    System.out.println("Conexión eliminada.");

                } else if (opcion2 == 2) {
                    System.out.print("Ciudad origen: ");
                    String ciudad1 = sc.nextLine();
                    System.out.print("Ciudad destino: ");
                    String ciudad2 = sc.nextLine();

                    System.out.print("Tiempo con clima normal: ");
                    double t1 = sc.nextDouble(); sc.nextLine();
                    System.out.print("Tiempo con lluvia: ");
                    double t2 = sc.nextDouble(); sc.nextLine();
                    System.out.print("Tiempo con nieve: ");
                    double t3 = sc.nextDouble(); sc.nextLine();
                    System.out.print("Tiempo con tormenta: ");
                    double t4 = sc.nextDouble(); sc.nextLine();

                    grafo.modificarTiempos(ciudad1, ciudad2, t1, t2, t3, t4);
                    System.out.println("Conexión modificada.");

                } else if (opcion2 == 3) {
                    System.out.println("Seleccione clima: 1. Normal  2. Lluvia  3. Nieve  4. Tormenta");
                    int climaSeleccion = sc.nextInt(); sc.nextLine();

                    Grafo.Clima clima = Grafo.Clima.NORMAL;
                    switch (climaSeleccion) {
                        case 1 -> clima = Grafo.Clima.NORMAL;
                        case 2 -> clima = Grafo.Clima.LLUVIA;
                        case 3 -> clima = Grafo.Clima.NIEVE;
                        case 4 -> clima = Grafo.Clima.TORMENTA;
                        default -> System.out.println("Opcion invalida, clima no cambiado.");
                    }

                    grafo.setClima(clima);
                    System.out.println("Clima global actualizado.");

                } else {
                    System.out.println("Opción inválida.");
                    continue;
                }

                // Recalcular Floyd tras cualquier cambio
                floyd = new Floyd(grafo.getMatrizPesos());
                System.out.println("Rutas recalculadas.");

            }

            else if (opcion == 4) {
                System.out.println("Saliendo...");
                break;
            }
            
            else {
                System.out.println("Opción invalida");
            }
        }

        sc.close();
    }
}
