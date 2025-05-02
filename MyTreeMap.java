/******************************************************************************************
 * 🌳 UD6 - TAREA 7: TRABAJO CON TREEMAP
 * ─────────────────────────────────────────────────────────────────────────────
 * 🎯 OBJETIVO: Aprender a usar TreeMap con distintas funcionalidades:
 *  - Recorrido de entradas
 *  - Copia de mapas
 *  - Búsqueda de claves y valores
 *  - Orden personalizado con Comparator
 *  - Operaciones de primeros/últimos elementos y recorridos inversos
 *
 * 🧠 TEORÍA:
 *  - TreeMap implementa SortedMap y ordena sus claves según el orden natural o un Comparator.
 *  - Permite acceder a funciones como firstKey(), lastKey(), firstEntry(), lastEntry(), 
 *    descendingKeySet(), floorEntry(), etc.
 *  - Utilizar Comparator personalizado permite cambiar el orden (ascendente/descendente).
 ******************************************************************************************/

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.NavigableSet;
import java.util.Map.Entry;

public class MyTreeMap {

    // @blue Mapa estático que contendrá pares clave->valor de colores
    private static TreeMap<String, String> colores = new TreeMap<>();

    // ==================================================================
    // 🔧 Método main: demuestra todas las funcionalidades
    // ==================================================================
    public static void main(String[] args) {
        // @green Insertar valores en el TreeMap
        colores.put("C1", "Rojo");
        colores.put("C2", "Azul");
        colores.put("C3", "Verde");
        colores.put("C4", "Blanco");
        colores.put("C5", "Negro");

        // @blue Imprimir entradas iniciales
        printEntries(colores);

        // @blue Copiar contenido en otro TreeMap
        TreeMap<String, String> copia = new TreeMap<>();
        copyMap(copia);
        System.out.println("\n🔄 Copia del mapa:");
        printEntries(copia);

        // @blue Búsqueda de clave
        searchKey("C3");
        searchKey("C6");

        // @blue Búsqueda de valor
        searchValue("Azul");
        searchValue("Amarillo");

        // @blue Orden descendente usando Comparator personalizado
        TreeMap<String, String> descendente = new TreeMap<>(new SortMyTreeMap());
        copyMap(descendente);
        System.out.println("\n🔽 Árbol ordenado descendente:");
        printEntries(descendente);

        // @blue Primera y última clave
        System.out.println("\n🔑 Primera clave: " + colores.firstKey());
        System.out.println("🔑 Última clave:  " + colores.lastKey());

        // @blue Primer y último par (Entry)
        System.out.println("\n🔹 Primer par: " + colores.firstEntry());
        System.out.println("🔹 Último par:  " + colores.lastEntry());

        // @blue Imprimir claves en orden inverso
        System.out.println("\n↔ Claves en orden inverso:");
        printReverseKeys(colores);

        // @blue Par clave-valor menor o igual que una dada
        System.out.println("\n🏷 Par menor o igual que 'C3': " + floorEntry("C3"));
        System.out.println("🏷 Par menor o igual que 'C6': " + floorEntry("C6"));
    }

    // ==================================================================
    // 1. Imprime los pares clave-valor del mapa
    // ==================================================================
    public static void printEntries(TreeMap<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // ==================================================================
    // 2. Copia todo el contenido de 'colores' en 'destino'
    // ==================================================================
    public static void copyMap(TreeMap<String, String> destino) {
        destino.putAll(colores);
    }

    // ==================================================================
    // 3. Busca si una clave existe en 'colores'
    // ==================================================================
    public static void searchKey(String key) {
        if (colores.containsKey(key)) {
            System.out.println("\n✅ La clave '" + key + "' existe en el mapa.");
        } else {
            System.out.println("\n❌ La clave '" + key + "' NO existe en el mapa.");
        }
    }

    // ==================================================================
    // 4. Busca si un valor existe en 'colores'
    // ==================================================================
    public static void searchValue(String value) {
        if (colores.containsValue(value)) {
            System.out.println("\n✅ El valor '" + value + "' existe en el mapa.");
        } else {
            System.out.println("\n❌ El valor '" + value + "' NO existe en el mapa.");
        }
    }

    // ==================================================================
    // 5. Comparator estático interno para ordenar claves descendente
    // ==================================================================
    public static class SortMyTreeMap implements Comparator<String> {
        @Override
        public int compare(String k1, String k2) {
            // Invertir orden natural
            return k2.compareTo(k1);
        }
    }

    // ==================================================================
    // 8. Imprime claves en orden inverso
    // ==================================================================
    public static void printReverseKeys(TreeMap<String, String> map) {
        NavigableSet<String> clavesDesc = map.descendingKeySet();
        for (String key : clavesDesc) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    // ==================================================================
    // 9. Devuelve el Entry clave-valor de la clave menor o igual a 'key'
    // ==================================================================
    public static Entry<String, String> floorEntry(String key) {
        return colores.floorEntry(key);
    }
}