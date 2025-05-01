/******************************************************************************************
 * 📚 UD6 - PROYECTO COMPLETO DE COLECCIONES: JUEGO DE AVENTURAS CON MAPAS
 * ----------------------------------------------------------------------------------------
 * 🌟 OBJETIVO: Aprender a usar colecciones en Java para representar ubicaciones
 * y recorridos en un juego interactivo mediante `Map`, `HashMap` y clases personalizadas.
 *
 * ✅ LO QUE APRENDERÁS:
 * - Usar la estructura de datos `Map<K,V>` para representar relaciones clave-valor.
 * - Crear clases con atributos tipo `Map` (como `exits`).
 * - Utilizar bucles e interacción con el usuario para moverse entre ubicaciones.
 * - Validar entradas del usuario.
 * - Aplicar buenas prácticas de POO con getters y encapsulación.
 * - Reutilizar código con métodos como `addExit()`.
 ******************************************************************************************/

 import java.util.HashMap;
 import java.util.Map;
 import java.util.Scanner;
 
 // =========================================================================================
 // 🚪 CLASE UBICACION: Representa un lugar dentro del juego con salidas posibles
 // =========================================================================================
 class Ubicacion {
     private int id;                        // ✅ Identificador numérico de la ubicación
     private String descripcion;            // ✅ Texto descriptivo del lugar
     private Map<String, Integer> exits;    // ✅ Salidas disponibles: direccion -> id de ubicación
 
     // ✨ Constructor que inicializa la ubicación
     public Ubicacion(int id, String descripcion) {
         this.id = id;
         this.descripcion = descripcion;
         this.exits = new HashMap<>();  // ✅ Creamos el mapa de salidas vacío
     }
 
     // ✅ Getters
     public int getId() {
         return id;
     }
 
     public String getDescripcion() {
         return descripcion;
     }
 
     public Map<String, Integer> getExits() {
         return exits;
     }
 
     // ➕ Agrega una salida a otra ubicación
     public void addExit(String direccion, int idUbicacion) {
         exits.put(direccion.toUpperCase(), idUbicacion);  // ✅ Convertimos en mayúscula para facilitar validación
     }
 }
 
 // =========================================================================================
 // 🌍 CLASE PRINCIPAL: Lógica del juego y recorrido entre ubicaciones
 // =========================================================================================
 public class UT6_map
 {
 
     // ✅ Mapa que almacena todas las ubicaciones posibles del juego
     private static Map<Integer, Ubicacion> ubicaciones = new HashMap<>();
 
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
 
         // 🌟 Inicializamos las ubicaciones
         inicializarUbicaciones();
 
         int ubicacionActual = 1;  // ✅ Empezamos en la cima de la montaña
 
         while (true) {
             Ubicacion actual = ubicaciones.get(ubicacionActual);
             System.out.println("\n🗺 " + actual.getDescripcion());
 
             // ✉ Mostramos las salidas posibles desde la ubicación actual
             System.out.print("🔎 Salidas válidas: ");
             for (String direccion : actual.getExits().keySet()) {
                 System.out.print(direccion + " ");
             }
             System.out.println();
 
             System.out.print("🔢 Dirección > ");
             String direccion = sc.nextLine().trim().toUpperCase();
 
             // 🌍 Salida del juego si elige Q (quit)
             if (direccion.equals("Q")) {
                 System.out.println("\n🚪 Has salido del juego. Gracias por jugar!");
                 break;
             }
 
             // ❌ Validamos si la dirección es válida en esta ubicación
             if (actual.getExits().containsKey(direccion)) {
                 ubicacionActual = actual.getExits().get(direccion);  // ✅ Cambiamos de ubicación
             } else {
                 System.out.println("❌ No puedes ir en esa dirección.");
             }
         }
         sc.close();
     }
 
     // ⚖️ Inicializa las ubicaciones y salidas
     public static void inicializarUbicaciones() {
         // 📍 Creamos ubicaciones con su descripción
         ubicaciones.put(0, new Ubicacion(0, "Estás sentado en la clase de programación"));
         ubicaciones.put(1, new Ubicacion(1, "Estás en la cima de una montaña"));
         ubicaciones.put(2, new Ubicacion(2, "Estás bañándote en la playa"));
         ubicaciones.put(3, new Ubicacion(3, "Estás dentro de un edificio muy alto"));
         ubicaciones.put(4, new Ubicacion(4, "Estás de pie en un puente"));
         ubicaciones.put(5, new Ubicacion(5, "Estás en un bosque"));
 
         // ⇄ Agregamos salidas para cada ubicación (menos la 0, que es final)
         ubicaciones.get(1).addExit("N", 5); // Montaña -> Bosque
         ubicaciones.get(1).addExit("E", 2); // Montaña -> Playa
         ubicaciones.get(1).addExit("S", 4); // Montaña -> Puente
         ubicaciones.get(1).addExit("O", 3); // Montaña -> Edificio
 
         ubicaciones.get(2).addExit("O", 1);
         ubicaciones.get(2).addExit("Q", 0);
 
         ubicaciones.get(3).addExit("E", 1);
         ubicaciones.get(3).addExit("Q", 0);
 
         ubicaciones.get(4).addExit("N", 1);
         ubicaciones.get(4).addExit("O", 5);
         ubicaciones.get(4).addExit("Q", 0);
 
         ubicaciones.get(5).addExit("S", 1);
         ubicaciones.get(5).addExit("E", 4);
         ubicaciones.get(5).addExit("Q", 0);
 
         // ✉ Mejora: Agregamos salida Q (quit) a todas las ubicaciones usando bucle
         for (Ubicacion u : ubicaciones.values()) {
             u.addExit("Q", 0);
         }
     }
 }
 