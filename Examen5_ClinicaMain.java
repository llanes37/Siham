/******************************************************************************************
 * 🐾 EXAMEN 5 – REGISTRO DE MASCOTAS EN UNA CLÍNICA VETERINARIA
 * ─────────────────────────────────────────────────────────────────────────────
 * ✅ Temas: Clases anidadas, Set, Predicate, Consumer, lambda, var, genéricos
 *
 * 📌 Contexto:
 * Una clínica registra mascotas por tipo (perro, gato, etc.), peso, y fecha de ingreso.
 * Se quiere gestionar los pacientes, aplicar tratamientos y detectar casos críticos.
 *
 * 📚 Requisitos:
 * 🔹 Clase Mascota: nombre, tipo, peso, ingreso
 * 🔹 Métodos: toString(), equals() y hashCode() por nombre
 * 🔹 Clase anidada estática TipoMascota con constantes (PERRO, GATO, OTRO)
 * 🔹 Interfaz funcional Tratamiento con método void aplicar(Mascota m)
 * 🔹 Crear varias lambdas con tratamientos distintos
 * 🔹 Clase Clinica<T extends Mascota> con Set<T> pacientes
 * 🔹 Métodos:
 *     - agregarMascota(T mascota)
 *     - filtrarPorPeso(Predicate<Mascota>)
 *     - aplicarTratamientos(Consumer<Mascota>)
 *     - mostrarTodos()
 * 🔹 Clase Main:
 *     - Menú con opciones para añadir mascota, aplicar tratamiento, filtrar, mostrar todas
 ******************************************************************************************/

import java.time.LocalDate;                     // 📅 Para usar fechas
import java.util.*;                             // 🧰 Conjuntos, Scanner, etc.
import java.util.function.Predicate;            // ✅ Para usar filtros booleanos
import java.util.function.Consumer;             // ✅ Para aplicar acciones

// ===================== CLASE MASCOTA =====================
class Mascota {
    private String nombre;            // 🐾 Nombre de la mascota
    private String tipo;              // 🐶 Tipo: perro, gato u otro
    private double peso;              // ⚖️ Peso en kg
    private LocalDate ingreso;        // 📅 Fecha de ingreso

    public Mascota(String nombre, String tipo, double peso, LocalDate ingreso) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.peso = peso;
        this.ingreso = ingreso;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public double getPeso() { return peso; }
    public LocalDate getIngreso() { return ingreso; }

    @Override
    public String toString() {
        return nombre + " (" + tipo + "), " + peso + "kg, ingresado el " + ingreso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Mascota otra)) return false;
        return nombre.equalsIgnoreCase(otra.nombre);  // Comparación por nombre (sin distinción de mayúsculas)
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());  // Para que los nombres se traten igual en el Set
    }
}

// ===================== CLASE TipoMascota =====================
class TipoMascota {
    public static final String PERRO = "Perro";
    public static final String GATO = "Gato";
    public static final String OTRO = "Otro";
}

// ===================== INTERFAZ FUNCIONAL Tratamiento =====================
@FunctionalInterface
interface Tratamiento {
    void aplicar(Mascota m); // Solo un método: se puede implementar con lambda
}

// ===================== CLASE CLINICA =====================
class Clinica<T extends Mascota> {
    private Set<T> pacientes;

    public Clinica() {
        pacientes = new HashSet<>(); // Inicializamos el conjunto vacío
    }

    public void agregarMascota(T mascota) {
        pacientes.add(mascota); // Añade la mascota si no está repetida (por nombre)
    }

    public void filtrarPorPeso(Predicate<Mascota> filtro) {
        for (T mascota : pacientes) {
            if (filtro.test(mascota)) {
                System.out.println("⚠️ Filtrada: " + mascota);
            }
        }
    }

    public void aplicarTratamientos(Consumer<Mascota> tratamiento) {
        for (T mascota : pacientes) {
            tratamiento.accept(mascota); // Aplicamos el tratamiento a cada una
        }
    }

    public void mostrarTodos() {
        System.out.println("📋 Mascotas registradas:");
        for (T mascota : pacientes) {
            System.out.println(" - " + mascota);
        }
    }

    // 🧪 EJERCICIO: Crear método mostrarPorTipo(String tipo)
}

// ===================== CLASE MAIN =====================
public class Examen5_ClinicaMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Clinica<Mascota> clinica = new Clinica<>();

        int opcion;

        do {
            // Menú de opciones por consola
            System.out.println("""
            =======================================
                  MENÚ - CLÍNICA VETERINARIA
            =======================================
             1. Añadir mascota
             2. Aplicar tratamiento general
             3. Mostrar mascotas con sobrepeso
             4. Mostrar todas las mascotas
             0. Salir
            =======================================
            """);

            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpieza de buffer

            switch (opcion) {
                case 1 -> {
                    // ➕ Añadir nueva mascota
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Tipo (Perro, Gato, Otro): ");
                    String tipo = sc.nextLine();
                    System.out.print("Peso (kg): ");
                    double peso = sc.nextDouble(); sc.nextLine();

                    Mascota m = new Mascota(nombre, tipo, peso, LocalDate.now());
                    clinica.agregarMascota(m);
                    System.out.println("✅ Mascota añadida.");

                    // 🧪 EJERCICIO: Validar que el peso no sea negativo
                }

                case 2 -> {
                    // 💉 Aplicar tratamiento genérico
                    Consumer<Mascota> tratamiento = m -> 
                        System.out.println("💉 Tratamiento aplicado a " + m.getNombre());

                    clinica.aplicarTratamientos(tratamiento);

                    // 🧪 EJERCICIO: Crear otro tratamiento con limpieza o vacuna
                }

                case 3 -> {
                    // ⚖️ Filtro para mascotas con sobrepeso (> 20kg)
                    Predicate<Mascota> sobrepeso = m -> m.getPeso() > 20;
                    clinica.filtrarPorPeso(sobrepeso);

                    // 🧪 EJERCICIO: Crear un filtro para mascotas muy ligeras (< 5kg)
                }

                case 4 -> {
                    // 📋 Mostrar todas las mascotas
                    clinica.mostrarTodos();
                    // 🧪 EJERCICIO: Mostrar solo las de tipo "Perro"
                }

                case 0 -> {
                    System.out.println("👋 Cerrando la clínica...");
                }

                default -> {
                    System.out.println("❌ Opción no válida.");
                }
            }

        } while (opcion != 0);

        sc.close(); // Cerramos scanner al salir
    }
}
