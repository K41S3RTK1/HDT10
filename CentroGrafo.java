public class CentroGrafo {

     
      public static int calcularCentro(double[][] distancias) {
          int n = distancias.length;
          double minExcentricidad = Double.POSITIVE_INFINITY;
          int centro = -1;
  
          for (int i = 0; i < n; i++) {
              double excentricidad = 0;
  
              for (int j = 0; j < n; j++) {
                  if (i != j && distancias[i][j] != Double.POSITIVE_INFINITY) {
                      excentricidad = Math.max(excentricidad, distancias[i][j]);
                  }
              }
  
              if (excentricidad < minExcentricidad) {
                  minExcentricidad = excentricidad;
                  centro = i;
              }
          }
  
          return centro;
      }
  }
  