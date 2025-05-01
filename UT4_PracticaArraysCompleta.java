/******************************************************************************************
 * 📚 PRÁCTICA GLOBAL - ARRAYS Y OBJETOS EN JAVA CON MENÚ INTERACTIVO
 * ─────────────────────────────────────────────────────────────────────────────
 * En esta práctica trabajarás con arrays y clases con arrays como atributos,
 * utilizando un menú interactivo para que sea completamente autoejecutable.
 * Aprenderás a:
 *
 * ✅ Crear, modificar y recorrer arrays de tipo primitivo.
 * ✅ Transformar arrays con tus propios métodos.
 * ✅ Manejar excepciones de índices fuera de rango.
 * ✅ Eliminar o filtrar elementos según condiciones.
 * ✅ Documentar y colorear el código con Better Comments.
 ******************************************************************************************/

 import java.util.Arrays;
 import java.util.InputMismatchException;
 import java.util.Scanner;
 
 public class UT4_PracticaArraysCompleta {
 
     // ==================================================================
     // 🧪 4. CLASE MAIN - PRUEBAS MANUALES Y EJECUCIÓN GENERAL
     // ------------------------------------------------------------------
     // @blue Punto de entrada: ejecuta todas las operaciones desde el menú
     // ==================================================================
     public static void main(String[] args) {
         // @cyan Scanner para leer opciones del usuario
         Scanner sc = new Scanner(System.in);
         // @green Array base de ejemplo para todas las operaciones
         int[] array = {2, 26, 81, 5, 15};
         int opcion; // @yellow Control de opción elegida en menú
 
         // @blue Menú interactivo: repetición hasta seleccionar salir
         do {
             System.out.println("\n=== 📋 MENÚ PRÁCTICA ARRAYS ===");
             System.out.println("1. Imprimir array");
             System.out.println("2. Transformar array (pares o -1)");
             System.out.println("3. Revertir array");
             System.out.println("4. Ordenar array ascendente");
             System.out.println("5. Imprimir rango de posiciones");
             System.out.println("6. Eliminar números impares");
             System.out.println("7. Eliminar números de un dígito");
             System.out.println("8. Calcular suma, máximo y mínimo");
             System.out.println("9. Comparar con copia");
             System.out.println("10. Salir");
             System.out.print("Elige una opción: ");
 
             try {
                 opcion = sc.nextInt(); // @cyan Leemos opción
                 switch (opcion) {
                     case 1:
                         // @purple Mostrar todo el array actual
                         imprimirArray(array);
                         break;
                     case 2:
                         // @purple Transformar impares a -1, conservar pares
                         array = transformarArray(array);
                         imprimirArray(array);
                         break;
                     case 3:
                         // @purple Invertir orden de los elementos
                         reverse(array);
                         imprimirArray(array);
                         break;
                     case 4:
                         // @purple Ordenar ascendentemente
                         ordenarArray(array);
                         imprimirArray(array);
                         break;
                     case 5:
                         // @purple Mostrar rango específico
                         System.out.print("Pos inicio: "); int inicio = sc.nextInt();
                         System.out.print("Pos fin:    "); int fin = sc.nextInt();
                         printRange(array, inicio, fin);
                         break;
                     case 6:
                         // @purple Sustituir impares con 0
                         removeOddNumbers(array);
                         imprimirArray(array);
                         break;
                     case 7:
                         // @purple Eliminar valores de un solo dígito
                         array = deleteOneDigitNumbers(array);
                         imprimirArray(array);
                         break;
                     case 8:
                         // @purple Calcular suma, máximo y mínimo
                         System.out.println("Suma: " + sum(array));
                         System.out.println("Máximo: " + max(array));
                         System.out.println("Mínimo: " + min(array));
                         break;
                     case 9:
                         // @purple Comparar con copia exacta
                         int[] copia = copyArray(array);
                         System.out.println("Original == copia? " + equals(array, copia));
                         break;
                     case 10:
                         // @green Salida del programa
                         System.out.println("👋 Fin de la práctica. ¡Hasta la próxima!");
                         break;
                     default:
                         // @red Opción inválida, reiniciar menú
                         System.out.println("❌ Opción no válida.");
                 }
             } catch (InputMismatchException e) {
                 // @red Manejo de entrada incorrecta (no numérica)
                 System.out.println("⚠️ Introduce un número válido.");
                 sc.next();
                 opcion = -1;
             }
         } while (opcion != 10);
         sc.close(); // @green Cerramos Scanner
     }
 
     // @blue Imprime el array con corchetes y comas
     public static void imprimirArray(int[] array) {
         System.out.print("[");
         for (int i = 0; i < array.length; i++) {
             System.out.print(array[i]);
             if (i < array.length - 1) System.out.print(", ");
         }
         System.out.println("]");
     }
 
     // @green Genera un nuevo array con impares = -1 y pares sin cambios
     public static int[] transformarArray(int[] array) {
         int[] result = new int[array.length];
         for (int i = 0; i < array.length; i++) {
             result[i] = (array[i] % 2 == 0) ? array[i] : -1;
         }
         return result;
     }
 
     // @yellow Invierte el array in-place intercambiando extremos
     public static void reverse(int[] array) {
         int n = array.length;
         for (int i = 0; i < n / 2; i++) {
             int tmp = array[i];
             array[i] = array[n - 1 - i];
             array[n - 1 - i] = tmp;
         }
     }
 
     // @blue Ordena con Bubble Sort (optimizable) de menor a mayor
     public static void ordenarArray(int[] array) {
         int n = array.length;
         for (int i = 0; i < n - 1; i++) {
             for (int j = 0; j < n - 1 - i; j++) {
                 if (array[j] > array[j + 1]) {
                     int tmp = array[j];
                     array[j] = array[j + 1];
                     array[j + 1] = tmp;
                 }
             }
         }
     }
 
     // @purple Imprime solo los elementos del rango [inicio, fin]
     public static void printRange(int[] array, int inicio, int fin) {
         try {
             System.out.print("[");
             for (int i = inicio; i <= fin; i++) {
                 System.out.print(array[i]);
                 if (i < fin) System.out.print(", ");
             }
             System.out.println("]");
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("⚠️ Índice fuera de rango: " + e.getMessage());
         }
     }
 
     // @red Convierte impares en 0 dentro del mismo array
     public static void removeOddNumbers(int[] array) {
         for (int i = 0; i < array.length; i++) {
             if (array[i] % 2 != 0) array[i] = 0;
         }
     }
 
     // @cyan Devuelve un nuevo array con valores de al menos 2 dígitos
     public static int[] deleteOneDigitNumbers(int[] array) {
         int count = 0;
         for (int v : array) if (Math.abs(v) > 9) count++;
         int[] res = new int[count];
         int idx = 0;
         for (int v : array) if (Math.abs(v) > 9) res[idx++] = v;
         return res;
     }
 
     // @green Suma todos los valores del array
     public static int sum(int[] array) {
         int total = 0;
         for (int v : array) total += v;
         return total;
     }
 
     // @yellow Encuentra el valor máximo en el array
     public static int max(int[] array) {
         int m = array[0];
         for (int v : array) if (v > m) m = v;
         return m;
     }
 
     // @blue Encuentra el valor mínimo en el array
     public static int min(int[] array) {
         int m = array[0];
         for (int v : array) if (v < m) m = v;
         return m;
     }
 
     // @purple Devuelve una copia exacta del array mediante Arrays.copyOf()
     public static int[] copyArray(int[] array) {
         return Arrays.copyOf(array, array.length);
     }
 
     // @red Compara dos arrays elemento a elemento, return true si idénticos
     public static boolean equals(int[] a, int[] b) {
         if (a.length != b.length) return false;
         for (int i = 0; i < a.length; i++) {
             if (a[i] != b[i]) return false;
         }
         return true;
     }
 }
 