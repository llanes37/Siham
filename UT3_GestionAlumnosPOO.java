import java.util.ArrayList;

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

    public void agregarAlumno(Alumno a) {
        listaAlumnos.add(a);
        System.out.println("✅ Alumno agregado: " + a.getNombre());
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

        Alumno a1 = new AlumnoPresencial("Carlos Ruiz", 20, "Programación", "Aula 101");
        Alumno a2 = new AlumnoOnline("Lucía Martínez", 22, "Diseño Web", "Zoom");
        Alumno a3 = new AlumnoErasmus("Franz Müller", 21, "Redes", "Alemania");

        grupo.agregarAlumno(a1);
        grupo.agregarAlumno(a2);
        grupo.agregarAlumno(a3);

        System.out.println("\n📚 MOSTRAR TODOS LOS ALUMNOS:");
        grupo.mostrarAlumnos();

        System.out.println("\n🔎 BUSCAR ALUMNO POR NOMBRE:");
        grupo.buscarAlumnoPorNombre("Lucía Martínez");
    }
}
