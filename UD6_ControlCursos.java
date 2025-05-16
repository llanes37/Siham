/******************************************************************************************
 * 🧪 EXAMEN 6 – CONTROL DE CURSOS UNIVERSITARIOS
 * ─────────────────────────────────────────────────────────────────────────────
 * ✅ Temas: Clases anidadas, Map, Set, colecciones ordenadas, Function, Supplier,
 *          final, var, ficheros, interfaces funcionales.
 *
 * 📌 Contexto:
 * Se debe crear un sistema que gestione cursos, profesores y estudiantes. 
 * Permite registrar inscripciones, calcular notas finales con lambdas,
 * y exportar listados en ficheros.
 *
 * 📚 Requisitos:
 * 🔹 Clase Curso: nombre, Map<String, Double> notas (clave = alumno)
 * 🔹 Métodos: añadirNota, calcularPromedio
 * 🔹 Clase Profesor: nombre, Set<Curso> cursosAsignados
 * 🔹 Método: asignarCurso
 * 🔹 Interfaz funcional CalculadoraNotaFinal
 * 🔹 Lambda para calcular media
 * 🔹 Clase Universidad: 
 *      - Map<String, Profesor> profesores
 *      - Set<Curso> todosLosCursos
 *      - Métodos: agregarProfesor, mostrarProfesores, 
 *        calcularPromedios(Function...), guardarEnArchivo(String)
 * 🔹 Main con menú:
 *      - Añadir profesor y curso
 *      - Asignar notas
 *      - Calcular promedio con lambda
 *      - Exportar listado
 ******************************************************************************************/

import java.util.*;
import java.io.*;
import java.util.function.Function;

// ╔════════════════════════════════════════════════════════════════════╗
// 🧩 CLASE CURSO – Requisito: Curso con notas de alumnos
// ╚════════════════════════════════════════════════════════════════════╝
class Curso {
    private String nombre;                          // 📌 Nombre del curso
    private Map<String, Double> notas;              // 📌 Alumno -> Nota final
    public Curso(String nombre) {
        this.nombre = nombre;                       // ✅ Inicializamos nombre
        this.notas = new HashMap<>();               // ✅ Creamos mapa vacío
    }

    public void añadirNota(String alumno, double nota) {
        notas.put(alumno, nota);                    // ✅ Añadimos o actualizamos nota
    }

  public double calcularPromedio() {
    if (notas.isEmpty()) return 0; // Si no hay notas, el promedio es 0
    double suma = 0;
    for (double nota : notas.values()) {
        suma += nota; // Acumula cada nota
    }
    return suma / notas.size(); // Divide la suma entre el número de notas
}

    public String getNombre() {
        return nombre;
    }

    public Map<String, Double> getNotas() {
        return notas;
    }

    @Override
    public String toString() {
        return nombre + " - Alumnos: " + notas.keySet(); // 📄 Mostrar lista de alumnos inscritos
    }
}

// ╔════════════════════════════════════════════════════════════════════╗
// 👨‍🏫 CLASE PROFESOR – Requisito: Profesor con cursos asignados
// ╚════════════════════════════════════════════════════════════════════╝
class Profesor {
    private String nombre;
    private Set<Curso> cursosAsignados;             // ✅ Set para evitar duplicados

    public Profesor(String nombre) {
        this.nombre = nombre;
        this.cursosAsignados = new HashSet<>();     // ✅ Inicializamos set vacío
    }

    public void asignarCurso(Curso curso) {
        cursosAsignados.add(curso);                 // ✅ Añadimos curso
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Curso> getCursosAsignados() {
        return cursosAsignados;
    }

    @Override
    public String toString() {
        return nombre + " enseña " + cursosAsignados.size() + " curso(s).";
    }
}

// ╔════════════════════════════════════════════════════════════════════╗
// 🔢 INTERFAZ FUNCIONAL – Requisito: Cálculo con lambda
// ╚════════════════════════════════════════════════════════════════════╝
@FunctionalInterface
interface CalculadoraNotaFinal {
    double calcular(Map<String, Double> notas);     // ✅ Método abstracto funcional
}

// ╔════════════════════════════════════════════════════════════════════╗
// 🏛️ CLASE UNIVERSIDAD – Requisito: estructura principal
// ╚════════════════════════════════════════════════════════════════════╝
class Universidad {
    private Map<String, Profesor> profesores;       // ✅ Mapa nombre -> profesor
    private Set<Curso> todosLosCursos;              // ✅ Conjunto global de cursos

    public Universidad() {
        profesores = new HashMap<>();
        todosLosCursos = new HashSet<>();
    }

    public void agregarProfesor(Profesor p) {
        profesores.put(p.getNombre(), p);           // ✅ Agregamos profesor
    }

    public void mostrarProfesores() {
        profesores.values().forEach(System.out::println); // ✅ Imprimimos todos
    }

   /*  public void calcularPromedios(Function<Map<String, Double>, Double> funcionPromedio) {
        for (Curso c : todosLosCursos) {
            double promedio = funcionPromedio.apply(c.getNotas()); // ✅ Aplicamos lambda
            System.out.println("📊 Promedio de " + c.getNombre() + ": " + promedio);
        }
    } */

    public void añadirCursoGlobal(Curso curso) {
        todosLosCursos.add(curso);                      // ✅ Añadimos curso global
    }

    public void guardarEnArchivo(String archivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            for (Curso c : todosLosCursos) {
                writer.println("Curso: " + c.getNombre());
                for (Map.Entry<String, Double> e : c.getNotas().entrySet()) {
                    writer.println(" - " + e.getKey() + ": " + e.getValue());
                }
                writer.println("Promedio: " + c.calcularPromedio()); // 🔥 Mejora: añadimos promedio
                writer.println();
            }
            System.out.println("✅ Listado exportado correctamente.");
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo.");
        }
    }

    public Map<String, Profesor> getProfesores() {
        return profesores;
    }

    public Set<Curso> getTodosLosCursos() {
        return todosLosCursos;
    }
}

// ╔════════════════════════════════════════════════════════════════════╗
// 🚀 CLASE MAIN – Requisito: menú interactivo con funcionalidades
// ╚════════════════════════════════════════════════════════════════════╝
public class UD6_ControlCursos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Universidad uni = new Universidad();        // ✅ Creamos universidad

        int opcion;

        do {
            System.out.println("""
            ╔══════════════════════════════════════╗
            ║      🎓 MENÚ - GESTIÓN UNIVERSIDAD    ║
            ╠══════════════════════════════════════╣
            ║ 1. Añadir profesor y curso           ║
            ║ 2. Asignar notas                     ║
            ║ 3. Calcular promedios (lambda)      ║
            ║ 4. Exportar listado a archivo       ║
            ║ 5. Ver profesores                   ║
            ║ 0. Salir                            ║
            ╚══════════════════════════════════════╝
            """);
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // ✅ Limpieza de buffer

            switch (opcion) {
                case 1 -> {
                    // ✅ Paso 1: Crear profesor y curso, asignarlos
                    System.out.print("Nombre del profesor: ");
                    String nombreP = sc.nextLine();
                    Profesor profe = new Profesor(nombreP);
                    uni.agregarProfesor(profe);

                    System.out.print("Nombre del curso: ");
                    String nombreC = sc.nextLine();
                    Curso curso = new Curso(nombreC);
                    Curso java = new Curso(nombreC);
                    profe.asignarCurso(curso);
                    uni.añadirCursoGlobal(curso);

                    System.out.println("✅ Profesor y curso añadidos.");
                }

                case 2 -> {
                    // ✅ Paso 2: Añadir nota a alumno en un curso específico
                    System.out.print("Nombre del curso al que añadir nota: ");
                    String cursoNombre = sc.nextLine();

                    Curso cursoEncontrado = uni.getTodosLosCursos().stream()
                            .filter(c -> c.getNombre().equalsIgnoreCase(cursoNombre))
                            .findFirst().orElse(null);

                    if (cursoEncontrado != null) {
                        System.out.print("Nombre del alumno: ");
                        String alumno = sc.nextLine();
                        System.out.print("Nota: ");
                        double nota = sc.nextDouble();
                        cursoEncontrado.añadirNota(alumno, nota);
                        System.out.println("✅ Nota añadida.");
                    } else {
                        System.out.println("❌ Curso no encontrado.");
                    }
                }

                case 3 -> {
                    // ✅ Paso 3: Usamos lambda para calcular promedio
                    System.out.println("🧠 Calculando promedios con función lambda:");
                    uni.calcularPromedios(notas -> notas.values().stream()
                            .mapToDouble(Double::doubleValue).average().orElse(0));
                }

                case 4 -> {
                    // ✅ Paso 4: Exportar listado de cursos y notas a fichero
                    System.out.print("Nombre del archivo destino (ej: salida.txt): ");
                    String archivo = sc.nextLine();
                    uni.guardarEnArchivo(archivo);
                }

                case 5 -> {
                    // ✅ Paso 5: Ver todos los profesores registrados
                    System.out.println("👨‍🏫 Profesores registrados:");
                    uni.mostrarProfesores();
                }

                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción no válida.");
            }

        } while (opcion != 0);

        sc.close(); // ✅ Cerramos scanner al salir
    }
}

/******************************************************************************************
 * 🎯 EXTENSIONES PROPUESTAS PARA CLASE
 * ────────────────────────────────────────────────────────────────
 * 1️⃣ Crea una clase Estudiante con nombre y cursos inscritos
 * 2️⃣ Opción de eliminar curso o profesor por nombre
 * 3️⃣ Exportar listados incluyendo promedio por curso
 ******************************************************************************************/
