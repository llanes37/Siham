/******************************************************************************************
 * ⚙️ UD7 - PRÁCTICA AVANZADA: GENERICS y LAMBDA EN JAVA
 * ----------------------------------------------------------------------------------------
 * 🎯 OBJETIVO:
 * Profundizar en Generics (tanto clases como métodos genéricos), wildcards y
 * expresiones Lambda / Stream API en Java.
 * 📖 INSTRUCCIONES:
 * - Selecciona una sección en el menú para ver teoría, ejemplos detallados
 *   y ejercicios prácticos.
 * - Usa Better Comments para resaltar conceptos clave en tu IDE.
 ******************************************************************************************/

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class UD7_GenericsLambdaPractica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n==== MENÚ UD7 - Generics & Lambda ====");
            System.out.println("1 - Sección GENERICS");
            System.out.println("2 - Sección WILDCARDS");
            System.out.println("3 - Sección LAMBDA & STREAM");
            System.out.println("4 - EJERCICIOS FINALES");
            System.out.println("0 - Salir");
            System.out.print("Elige sección: "); opcion = sc.nextInt(); sc.nextLine();
            switch (opcion) {
                case 1 -> genericsSection();
                case 2 -> wildcardSection();
                case 3 -> lambdaSection();
                case 4 -> finalExercises();
                case 0 -> System.out.println("👋 Saliendo UD7...");
                default -> System.out.println("❌ Opción no válida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    // ========================================================================================
    // ? SECCIÓN GENERICS: teoría, ejemplos de clases y métodos genéricos
    // ========================================================================================
    public static void genericsSection() {
        System.out.println("\n--- SECCIÓN GENERICS ---");
        // * TEORÍA:
        // * - Generics permite escribir clases y métodos que funcionen con cualquier tipo T.
        // * - Asegura type safety en tiempo de compilación, evita ClassCastException.
        // * - Sintaxis de clase genérica: class Box<T> { ... }
        // * - Sintaxis de método genérico: <T> void method(T param) { ... }
        // * - Soporta tipos primitivos y referencias, pero autoboxing para primitivos.

        // * EJEMPLO 1: Clase genérica Box<T>
        Box<String> boxStr = new Box<>();         // Caja para Strings
        boxStr.setValue("Hola Generics");
        System.out.println("Box<String> contiene: " + boxStr.getValue());

        Box<Double> boxDbl = new Box<>();         // Caja para Doubles
        boxDbl.setValue(3.1415);
        System.out.println("Box<Double> contiene: " + boxDbl.getValue());

        // * EJEMPLO 2: Método genérico swap en List<T>
        List<String> nombres = new ArrayList<>(Arrays.asList("A","B","C","D"));
        System.out.println("Original: " + nombres);
        Collections.swap(nombres, 1, 3);
        System.out.println("Swap 1<->3: " + nombres);

        // * EJEMPLO 3: Clase genérica Pair<K,V>
        Pair<String,Integer> pair = new Pair<>("Edad", 30);
        System.out.println("Pair: " + pair.getKey() + " -> " + pair.getValue());

        // TODO EJERCICIOS GENERICS:
        // TODO 1. Crea clase genérica Triple<A,B,C> con getters/setters.
        // TODO 2. Implementa método genérico <T> List<T> fromArray(T[] arr).
        // TODO 3. Usa Pair<String, List<Integer>> para mapear nombres a edades.
    }

    // ========================================================================================
    // ? SECCIÓN WILDCARDS: bounded y unbounded wildcards
    // ========================================================================================
    public static void wildcardSection() {
        System.out.println("\n--- SECCIÓN WILDCARDS ---");
        // * TEORÍA:
        // * - <?> unbounded wildcard: List<?> acepta List<T> cualquiera.
        // * - <? extends T>: lista de tipos que heredan de T, lectura segura.
        // * - <? super T>: lista de tipos que son superclases de T, escritura segura.

        // * EJEMPLO: imprimir lista genérica con wildcard
        List<Integer> ints = Arrays.asList(1,2,3);
        List<String> strs = Arrays.asList("a","b","c");
        printList(ints);                          // OK: <?>
        printList(strs);

        // * EJEMPLO bounded extends
        List<Double> doubles = Arrays.asList(1.1,2.2);
        double sum = sumNumbers(doubles);        // <? extends Number>
        System.out.println("Suma: " + sum);

        // TODO EJERCICIOS WILDCARDS:
        // TODO 1. Crea método addNumbers(List<? super Integer>, Integer).
        // TODO 2. Intenta añadir Double a List<? extends Number> y observa error.
        // TODO 3. Crea método copyList(List<? super T>, List<? extends T>).
    }

    // Método de demostración lectura con unbounded wildcard
    public static void printList(List<?> list) {
        for (Object o : list) System.out.print(o + " ");
        System.out.println();
    }

    // Método de demostración sum con bounded extends wildcard
    public static double sumNumbers(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) sum += n.doubleValue();
        return sum;
    }

    // ========================================================================================
    // ? SECCIÓN LAMBDA & STREAM: teoría y ejemplos avanzados
    // ========================================================================================
    public static void lambdaSection() {
        System.out.println("\n--- SECCIÓN LAMBDA & STREAM ---");
        // * TEORÍA:
        // * - Lambda: sintaxis (params) -> expression, implementa functional interfaces.
        // * - Stream API: flujo de datos, operaciones intermedias (map, filter)
        // *   y finales (collect, reduce).

        // * EJEMPLO 1: Runnable con lambda
        Runnable r = () -> System.out.println("Hilo con lambda ejecutado");
        new Thread(r).start();

        // * EJEMPLO 2: Comparator lambda para ordenar
        List<String> lista = new ArrayList<>(Arrays.asList("Juan","Ana","Luis"));
        lista.sort((a,b) -> a.compareToIgnoreCase(b));
        System.out.println("Ordenado: " + lista);

        // * EJEMPLO 3: Stream pipeline avanzado
        List<String> datos = Arrays.asList("manzana","platano","pera","uva");
        Map<Integer, List<String>> grup = datos.stream()
            .filter(s -> s.length() > 4)            // filtrar
            .map(String::toUpperCase)               // transformar
            .collect(Collectors.groupingBy(String::length)); // agrupar
        System.out.println("Agrupado por longitud: " + grup);

        // TODO EJERCICIOS LAMBDA:
        // TODO 1. Usa stream para contar cuántos nombres empiezan con 'A'.
        // TODO 2. Implementa reduce para concatenar lista de palabras.
        // TODO 3. Crea Function<String,Integer> que devuelva longitud y aplícalo.
    }

    // ========================================================================================
    // * EJERCICIOS FINALES: integrar Generics y Lambdas
    // ========================================================================================
    public static void finalExercises() {
        System.out.println("\n--- EJERCICIOS FINALES UD7 ---");
        // TODO a) Crea método genérico <T> List<T> filter(List<T>, Predicate<T>).
        // TODO b) Usar filter con lambda para List<Integer> pares.
        // TODO c) Diseña clase genérica Transformer<T,R> con método apply(T)->R.
        // TODO d) Combinar transform y filter con streams.
        // TODO e) BONUS: Implementa map multi-nivel para List<List<T>>.
    }
}

/******************************************************************************************
 * ✏️ EJERCICIOS ADICIONALES:
 * - Implementar Optional<T> en método getValue() de Box genérico.
 * - Explora parallelStream() y mide rendimiento.
 ******************************************************************************************/
