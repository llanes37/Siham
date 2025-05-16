import java.util.ArrayList;
import java.util.Scanner;
// ==================================================================
// 1. CLASE ABSTRACTA BASE - Alumno
// ==================================================================
abstract class Alumno {
    private String nombre;
    private int edad;
    private String curso;

    public Alumno(String nombre, int edad, String curso) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getCurso() { return curso; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEdad(int edad) { this.edad = edad; }
    public void setCurso(String curso) { this.curso = curso; }

    public abstract void mostrarInformacion();

    @Override
    public String toString() {
        return "👤 " + nombre + " | Edad: " + edad + " | Curso: " + curso;
    }
}

// ==================================================================
// 2. CLASES HIJAS
// ==================================================================

class AlumnoPresencial extends Alumno {
    private String aula;

    public AlumnoPresencial(String nombre, int edad, String curso, String aula) {
        super(nombre, edad, curso);
        this.aula = aula;
    }

    public String getAula() { return aula; }
    public void setAula(String aula) { this.aula = aula; }

    @Override
    public void mostrarInformacion() {
        System.out.println(toString() + " | Tipo: Presencial | Aula: " + aula);
    }
}

class AlumnoOnline extends Alumno {
    private String plataforma;

    public AlumnoOnline(String nombre, int edad, String curso, String plataforma) {
        super(nombre, edad, curso);
        this.plataforma = plataforma;
    }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    @Override
    public void mostrarInformacion() {
        System.out.println(toString() + " | Tipo: Online | Plataforma: " + plataforma);
    }
}

class AlumnoErasmus extends Alumno {
    private String paisOrigen;

    public AlumnoErasmus(String nombre, int edad, String curso, String paisOrigen) {
        super(nombre, edad, curso);
        this.paisOrigen = paisOrigen;
    }

    public String getPaisOrigen() { return paisOrigen; }
    public void setPaisOrigen(String paisOrigen) { this.paisOrigen = paisOrigen; }

    @Override
    public void mostrarInformacion() {
        System.out.println(toString() + " | Tipo: Erasmus | País: " + paisOrigen);
    }
}

// ==================================================================
// 3. CLASE ClaseGrupo
// ==================================================================
class ClaseGrupo {
    private ArrayList<Alumno> listaAlumnos = new ArrayList<>();

    public void agregarAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
        System.out.println("✅ Alumno agregado: " + alumno.getNombre() + "edad " + alumno.getEdad());
    }

    public void mostrarAlumnos() {
        if (listaAlumnos.isEmpty()) {
            System.out.println("📭 No hay alumnos en el grupo.");
        } else {
            System.out.println("📋 Lista de alumnos:");
            for (Alumno a : listaAlumnos) {
                a.mostrarInformacion();
            }
        }
    }
public void borrarAlumno(String nombre) {
    listaAlumnos.removeIf(a -> a.getNombre().equalsIgnoreCase(nombre));
    System.out.println("🗑️ Se ha intentado borrar el alumno con nombre: " + nombre);
}
public void borrarAlumnoObjeto(Alumno alumno) {
    listaAlumnos.remove(alumno);
   
    }

    public void buscarAlumnoPorNombre(String nombre) {
        boolean encontrado = false;
        for (Alumno a : listaAlumnos) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("🔍 Alumno encontrado:");
                a.mostrarInformacion();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("❌ No se encontró el alumno con nombre: " + nombre);
        }
    }
}

// ==================================================================
// 4. CLASE MAIN
// ==================================================================
public class UT3_GestionAlumnosPOO {
    public static void main(String[] args) {
        ClaseGrupo grupo = new ClaseGrupo();
        Scanner sc = new Scanner(System.in);

        // ✅ Datos de prueba (puedes comentarlos si no los quieres fijos)
        Alumno carlos = new AlumnoPresencial("Carlos Ruiz", 20, "Programación", "Aula 101");
        Alumno lucia = new AlumnoOnline("Lucía Martínez", 22, "Diseño Web", "Zoom");
        Alumno franz = new AlumnoErasmus("Franz Müller", 21, "Redes", "Alemania");

        int opcion;
        boolean datosCargados = false;

        do {
            System.out.println("""
            ╔══════════════════════════════════════╗
            ║         MENÚ - GESTIÓN ALUMNOS       ║
            ╠══════════════════════════════════════╣
            ║ 1. Agregar alumnos de prueba         ║
            ║ 2. Borrar alumno (por objeto)        ║
            ║ 3. Borrar alumno (por nombre)        ║
            ║ 4. Mostrar todos los alumnos         ║
            ║ 0. Salir                             ║
            ╚══════════════════════════════════════╝
            """);

            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> {
                    if (!datosCargados) {
                        grupo.agregarAlumno(carlos);
                        grupo.agregarAlumno(lucia);
                        grupo.agregarAlumno(franz);
                        datosCargados = true;
                        System.out.println("✅ Alumnos de prueba añadidos.");
                    } else {
                        System.out.println("⚠️ Los alumnos de prueba ya fueron añadidos.");
                    }
                }
                
                case 2 -> {
                    grupo.borrarAlumnoObjeto(franz);  // solo de ejemplo
                }
                case 3 -> {
                    System.out.print("Introduce el nombre del alumno a borrar: ");
                    String nombre = sc.nextLine();
                    grupo.borrarAlumno(nombre);
                }
                case 4 -> {
                    grupo.mostrarAlumnos();
                }
                case 0 -> System.out.println("👋 Saliendo...");
                default -> System.out.println("❌ Opción no válida");
            }

        } while (opcion != 0);

        sc.close();
    }
}