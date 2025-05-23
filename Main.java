import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Camino más corto entre dos ciudades");
            System.out.println("2. Indicar centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");

            System.out.print("Opción: ");
            int opcion = sc.nextInt();

            if (opcion == 1) {
                System.out.print("Ciudad origen: ");
                String origen = sc.nextLine();
                System.out.print("Ciudad destino: ");
                String destino = sc.nextLine();

                

            } 
            
            else if (opcion == 2) {
                
            }

            else if (opcion == 3) {
                System.out.println("\n--- MODIFICAR GRAFO ---");
                System.out.println("1. Interrupción de tráfico (elimina arco)");
                System.out.println("2. Agregar nueva conexión");
                System.out.println("3. Cambiar clima entre ciudades(modificar peso arco)");
                int opcion2 = sc.nextInt();

                if(opcion2 == 1){

                }

                else if(opcion2 == 2){

                }

                else if(opcion2 == 3) {

                }

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
