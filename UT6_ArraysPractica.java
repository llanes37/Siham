/******************************************************************************************
 *                        📚 **TEORÍA Y CONCEPTOS**
 * ──────────────────────────────────────────────────────────────────────────────
 * Este programa está diseñado para repasar y aplicar conceptos fundamentales en Java:
 * 
 * 1️⃣ **Arrays Unidimensionales y Bidimensionales (Matrices)**
 *    - *Arrays Unidimensionales*: Estructuras de datos lineales que almacenan elementos
 *      del mismo tipo en una secuencia. Se pueden recorrer mediante bucles.
 *    - *Arrays Bidimensionales*: También llamados matrices, son arrays de arrays.
 *      Se utilizan para representar datos en forma de tabla.
 * 
 * 2️⃣ **Algoritmos de Ordenación**
 *    - *Burbuja*: Compara elementos adyacentes e intercambia si están desordenados.
 *      Cada pasada asegura que el elemento mayor se "burbujee" hasta el final del array.
 *    - *Selección*: Busca el elemento mínimo en el subarray no ordenado y lo coloca
 *      en la posición correcta. Se recorre el array hasta el penúltimo elemento.
 *    - *Inserción*: Toma cada elemento y lo inserta en su posición correcta dentro de la 
 *      parte ya ordenada, comenzando desde el segundo elemento.
 * 
 * 3️⃣ **Algoritmos de Búsqueda**
 *    - *Búsqueda Lineal*: Recorre el array desde el inicio hasta el final, comparando
 *      cada elemento con el valor buscado. Es simple pero su complejidad es O(n).
 *    - *Búsqueda Binaria*: Funciona en arrays ordenados, dividiendo el array en mitades
 *      y comparando el elemento central con el valor buscado. Su complejidad es O(log n).
 * 
 * 4️⃣ **Colecciones: ArrayList**
 *    - Permite almacenar elementos de forma dinámica sin necesidad de definir un tamaño
 *      fijo. Útil cuando el número de elementos varía.
 * 
 * 5️⃣ **Manejo de Excepciones**
 *    - Se usa try-catch para gestionar entradas no válidas y evitar que el programa se detenga.
 * 
 * 📌 **Estructura General del Programa**
 * ──────────────────────────────────────────────────────────────────────────────
 * - Menú interactivo para elegir la funcionalidad a ejecutar.
 * - Cada opción invoca un método específico (ej. ordenación, búsqueda, manejo de arrays).
 * - Al final se incluyen ejercicios prácticos para reforzar los conceptos.
 * 
 * 🚀 **¡Explora y aprende probando cada sección!**
 ******************************************************************************************/

 import java.util.Arrays;                 // Utilidades para arrays (por ejemplo, Arrays.toString)
 import java.util.Scanner;                // Para leer datos del usuario
 import java.util.InputMismatchException; // Para manejar errores en la entrada de datos
 
 public class UT6_ArraysPractica {
 
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         int opcion;
         
         // =====================================================
         // ! 📌 MENÚ PRINCIPAL - UT6: ARRAYS Y ALGORITMOS
         // =====================================================
         do {
             System.out.println("\n==========================================");
             System.out.println("      MENÚ DE PRÁCTICAS - UT6 ARRAYS      ");
             System.out.println("==========================================");
             System.out.println("1. Arrays Unidimensionales (Vectores)");
             System.out.println("2. Arrays Bidimensionales (Matrices)");
             System.out.println("3. Algoritmos de Ordenación");
             System.out.println("4. Algoritmos de Búsqueda");
             System.out.println("5. Ejercicios Extra para Practicar");
             System.out.println("6. Salir");
             System.out.print("👉 Seleccione una opción: ");
             opcion = readInt(scanner);
             
             switch(opcion) {
                 case 1:
                     arraysUnidimensionales(scanner);
                     break;
                 case 2:
                     arraysBidimensionales(scanner);
                     break;
                 case 3:
                     algoritmosOrdenacion(scanner);
                     break;
                 case 4:
                     algoritmosBusqueda(scanner);
                     break;
                 case 5:
                     ejerciciosExtra();
                     break;
                 case 6:
                     System.out.println("👋 Saliendo del programa...");
                     break;
                 default:
                     System.out.println("❌ Opción inválida. Intente nuevamente.");
             }
         } while(opcion != 6);
         
         scanner.close();
     }
     
     // ---------------------------------------------------------------
     // Método auxiliar: readInt
     // ---------------------------------------------------------------
     // Lee de forma segura un número entero, gestionando entradas no válidas.
     public static int readInt(Scanner scanner) {
         while (true) {
             try {
                 return scanner.nextInt(); // ➡ Intenta leer un entero
             } catch (InputMismatchException e) { // ❌ Si la entrada no es válida
                 System.out.println("❌ Entrada no válida. Por favor, ingrese un número entero.");
                 scanner.next(); // Descarta la entrada incorrecta
             }
         }
     }
     
     // =====================================================
     // ! 📌 SECCIÓN 1: ARRAYS UNIDIMENSIONALES (VECTORES)
     // =====================================================
     public static void arraysUnidimensionales(Scanner scanner) {
         System.out.println("\n🎨 **SECCIÓN 1: Arrays Unidimensionales (Vectores)**");
         System.out.println("------------------------------------------------------------");
         /*
          * 📖 TEORÍA:
          * - Un array unidimensional es una estructura de datos lineal que almacena múltiples
          *   valores del mismo tipo en posiciones consecutivas.
          * - Se accede a cada elemento mediante un índice (inicia en 0).
          * - El tamaño del array es fijo, lo que significa que no se puede modificar en tiempo de ejecución.
          * - Se pueden recorrer usando bucles `for` o `while`.
          */
         System.out.println("\n✅ EJERCICIO: Crear un array de 5 números, solicitar los valores por teclado y mostrarlos.");
         
         int[] numeros = new int[5]; // ➡ Declaramos un array de 5 elementos
         
         System.out.println("🔹 Introduce 5 números:");
         for (int i = 0; i < numeros.length; i++) {
             System.out.print("   Número " + (i + 1) + ": ");
             numeros[i] = readInt(scanner); // Guarda cada número en el array
         }
         
         System.out.println("\n📊 Números ingresados: " + Arrays.toString(numeros));
         
         // Ejemplo adicional: Calcular la suma total y determinar el número mayor.
         int suma = 0;
         int mayor = numeros[0];
         for (int num : numeros) {
             suma += num;
             if (num > mayor) {
                 mayor = num;
             }
         }
         System.out.println("💡 Suma total: " + suma);
         System.out.println("💡 Número mayor: " + mayor);
     }
     
     // =====================================================
     // ! 📌 SECCIÓN 2: ARRAYS BIDIMENSIONALES (MATRICES)
     // =====================================================
     public static void arraysBidimensionales(Scanner scanner) {
         System.out.println("\n🎨 **SECCIÓN 2: Arrays Bidimensionales (Matrices)**");
         System.out.println("------------------------------------------------------------");
         /*
          * 📖 TEORÍA:
          * - Una matriz es un array bidimensional que organiza datos en forma de tabla (filas y columnas).
          * - Se accede a cada elemento mediante dos índices: uno para la fila y otro para la columna.
          * - Es muy útil para representar datos estructurados, como tablas o imágenes.
          */
         System.out.println("\n✅ EJERCICIO: Crear una matriz 3x3, llenarla con números y mostrarla,");
         System.out.println("   además de imprimir su diagonal principal y calcular la suma de todos sus elementos.");
         
         int[][] matriz = new int[3][3];
         System.out.println("🔹 Introduce los valores para la matriz 3x3:");
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 System.out.print("   Elemento [" + i + "][" + j + "]: ");
                 matriz[i][j] = readInt(scanner);
             }
         }
         
         System.out.println("\n📊 Matriz ingresada:");
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 System.out.print(matriz[i][j] + " ");
             }
             System.out.println();
         }
         
         // Mostrar la diagonal principal (elementos donde i == j)
         System.out.print("💡 Diagonal principal: ");
         for (int i = 0; i < 3; i++) {
             System.out.print(matriz[i][i] + " ");
         }
         
         // Calcular la suma total de los elementos de la matriz
         int sumaMatriz = 0;
         for (int i = 0; i < 3; i++) {
             for (int j = 0; j < 3; j++) {
                 sumaMatriz += matriz[i][j];
             }
         }
         System.out.println("\n💡 Suma total de los elementos: " + sumaMatriz);
     }
     
     // =====================================================
     // ! 📌 SECCIÓN 3: ALGORITMOS DE ORDENACIÓN
     // =====================================================
     public static void algoritmosOrdenacion(Scanner scanner) {
         System.out.println("\n🎨 **SECCIÓN 3: Algoritmos de Ordenación**");
         System.out.println("------------------------------------------------------------");
         /*
          * 📖 TEORÍA:
          * La ordenación de un array consiste en organizar sus elementos en un orden específico (por lo general ascendente).
          * Se utilizarán tres algoritmos:
          * 
          * - **Ordenación por Burbuja**:
          *   • Compara elementos adyacentes e intercambia si no están en el orden correcto.
          *   • Cada pasada garantiza que el elemento mayor se "burbujee" hasta el final del array.
          *   • IMPORTANTE: Se utiliza `datos.length - 1` en el bucle interno para evitar acceder fuera del array.
          * 
          * - **Ordenación por Selección**:
          *   • Recorre el array y busca el elemento mínimo en el subarray no ordenado.
          *   • Coloca el elemento mínimo en la posición actual.
          *   • Se recorre el array hasta el penúltimo elemento, ya que el último se ordena automáticamente.
          * 
          * - **Ordenación por Inserción**:
          *   • Toma cada elemento (a partir del segundo) y lo inserta en su posición correcta dentro de la parte ya ordenada.
          */
         System.out.println("\n✅ EJERCICIO: Ordenar un array utilizando el método de Burbuja.");
         
         int[] datos = {5, 3, 8, 1, 2};
         System.out.println("🔹 Array original: " + Arrays.toString(datos));
         
         long inicioTiempo = System.nanoTime(); // Inicia la medición del tiempo
         
         // ! 📌 Ordenación por Burbuja
         /*
          * ➡ PASO A PASO:
          * 1. Recorrer el array con un bucle externo para controlar las pasadas.
          * 2. En cada pasada, recorrer hasta el último elemento no ordenado.
          * 3. Comparar cada par de elementos adyacentes:
          *    - Si el elemento actual es mayor que el siguiente, se intercambian.
          * 4. Con cada pasada, el elemento mayor se desplaza hacia el final.
          */
         for (int i = 0; i < datos.length - 1; i++) {
             for (int j = 0; j < datos.length - 1 - i; j++) {
                 if (datos[j] > datos[j + 1]) {
                     int temp = datos[j];         // Guarda el valor actual
                     datos[j] = datos[j + 1];       // Mueve el siguiente valor a la posición actual
                     datos[j + 1] = temp;           // Coloca el valor guardado en la siguiente posición
                 }
             }
         }
         
         long finTiempo = System.nanoTime(); // Fin de la medición
         System.out.println("📊 Array ordenado (Burbuja): " + Arrays.toString(datos));
         System.out.println("⏱ Tiempo de ejecución: " + (finTiempo - inicioTiempo) + " nanosegundos");
     }
     
     // =====================================================
     // ! 📌 SECCIÓN 4: ALGORITMOS DE BÚSQUEDA
     // =====================================================
     public static void algoritmosBusqueda(Scanner scanner) {
         System.out.println("\n🎨 **SECCIÓN 4: Algoritmos de Búsqueda**");
         System.out.println("------------------------------------------------------------");
         /*
          * 📖 TEORÍA:
          * La búsqueda en un array consiste en localizar la posición de un elemento.
          * Se estudiarán dos métodos:
          * 
          * - **Búsqueda Lineal**:
          *   • Recorre el array de principio a fin comparando cada elemento con el valor buscado.
          *   • Es simple pero su complejidad es O(n).
          * 
          * - **Búsqueda Binaria**:
          *   • Funciona únicamente en arrays ordenados.
          *   • Divide el array en mitades y compara el elemento central con el valor buscado.
          *   • Ajusta el rango de búsqueda según el resultado de la comparación.
          *   • Su complejidad es O(log n), lo que la hace muy eficiente para arrays grandes.
          */
         
         // ✅ EJERCICIO: Búsqueda Lineal
         System.out.println("\n🔎 EJERCICIO: Búsqueda Lineal");
         int[] datos = {5, 3, 8, 1, 2};  // Array de ejemplo
         System.out.print("🔹 Introduce un número para buscar: ");
         int clave = readInt(scanner);
         boolean encontrado = false;
         
         /*
          * ➡ PASO A PASO:
          * 1. Recorre el array desde el inicio hasta el final.
          * 2. Compara cada elemento con el valor buscado.
          * 3. Si se encuentra, muestra la posición y termina la búsqueda.
          * 4. Si no se encuentra, informa al usuario.
          */
         for (int i = 0; i < datos.length; i++) {
             if (datos[i] == clave) {
                 System.out.println("✅ Número encontrado en la posición: " + i);
                 encontrado = true;
                 break;
             }
         }
         if (!encontrado) {
             System.out.println("❌ Número no encontrado.");
         }
         
         // ✅ EJERCICIO ADICIONAL: Búsqueda Binaria
         System.out.println("\n🔎 EJERCICIO ADICIONAL: Búsqueda Binaria (en array ordenado)");
         int[] sortedDatos = {1, 2, 3, 5, 8}; // Array ordenado
         System.out.println("🔹 Array ordenado: " + Arrays.toString(sortedDatos));
         System.out.print("   Introduce un número para buscar: ");
         int claveBin = readInt(scanner);
         int inicio = 0;
         int fin = sortedDatos.length - 1;
         boolean encontradoBin = false;
         
         /*
          * ➡ PASO A PASO:
          * 1. Definir el rango de búsqueda (inicio y fin).
          * 2. Calcular el índice medio del rango actual.
          * 3. Comparar el elemento en el medio con el valor buscado:
          *    - Si es igual, se encontró el elemento.
          *    - Si es menor, se ajusta el inicio al medio + 1.
          *    - Si es mayor, se ajusta el fin al medio - 1.
          * 4. Repetir hasta que el rango sea inválido o se encuentre el valor.
          */
         while (inicio <= fin) {
             int medio = (inicio + fin) / 2;
             if (sortedDatos[medio] == claveBin) {
                 System.out.println("✅ Número encontrado en la posición: " + medio);
                 encontradoBin = true;
                 break;
             } else if (sortedDatos[medio] < claveBin) {
                 inicio = medio + 1;
             } else {
                 fin = medio - 1;
             }
         }
         if (!encontradoBin) {
             System.out.println("❌ Número no encontrado en la búsqueda binaria.");
         }
     }
     
     // =====================================================
     // ! 📌 SECCIÓN 5: EJERCICIOS EXTRA PARA PRACTICAR
     // =====================================================
     public static void ejerciciosExtra() {
         System.out.println("\n🎨 **SECCIÓN EXTRA: Ejercicios para Practicar**");
         System.out.println("------------------------------------------------------------");
         System.out.println("✍️ A continuación, algunos ejercicios para reforzar lo aprendido:");
         System.out.println("1️⃣ Crear un array de 10 números, solicitar los valores por teclado y mostrar la suma total.");
         System.out.println("2️⃣ Crear una matriz 4x4 e imprimir su diagonal principal.");
         System.out.println("3️⃣ Implementar la búsqueda binaria en un array ordenado (ampliar el ejemplo).");
         System.out.println("4️⃣ Implementar la ordenación por inserción y comparar su tiempo de ejecución con la ordenación por burbuja.");
         System.out.println("5️⃣ Desarrollar un método que reciba un array y retorne otro array con los elementos en orden inverso.");
         System.out.println("¡Intenta resolverlos por tu cuenta y experimenta para aprender más!");
     }
 }
 