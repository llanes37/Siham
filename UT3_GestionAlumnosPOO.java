/**
 * 📚 Examen Práctico - 2ª Evaluación 2024/2025
 * 🎯 OBJETIVO: Desarrollar un sistema de gestión de alumnos en una clase
 * 🧠 NIVEL: Programación Orientada a Objetos (POO) - Herencia, Polimorfismo, Encapsulación
 * 🛠️ HERRAMIENTA: IntelliJ IDEA (puedes usar Generate ⌘N para getters, setters y constructores)
 *
 * ─────────────────────────────────────────────
 * 📝 ENUNCIADO GENERAL:
 * Se desea crear una aplicación que permita gestionar alumnos de distintos tipos dentro de una clase.
 * Todos los alumnos tienen nombre, edad y curso. Según el tipo de alumno, se añadirá información extra.
 *
 * ➕ CLASE BASE (abstracta): Alumno
 *     - Atributos: nombre (String), edad (int), curso (String)
 *     - Constructor, getters y setters
 *     - Método abstracto mostrarInformacion()
 *     - Método toString()
 *
 * ➕ CLASES HIJAS:
 *     - AlumnoPresencial → atributo: aula (String)
 *     - AlumnoOnline → atributo: plataforma (String)
 *     - AlumnoErasmus → atributo: paisOrigen (String)
 *
 * ➕ CLASE ClaseGrupo:
 *     - Lista de alumnos
 *     - Métodos: agregarAlumno(), mostrarAlumnos(), buscarAlumnoPorNombre()
 *
 * ✅ CLASE MAIN: pruebas manuales sin menú.
 *
 * ✔ Se valorará el uso correcto de POO, buenas prácticas, comentarios y limpieza del código.
 */

import java.util.ArrayList;

// ==================================================================
// 1. CLASE ABSTRACTA BASE - Alumno
// ------------------------------------------------------------------
// @blue Esta clase será la base para todos los tipos de alumnos
// @green Encapsula atributos comunes: nombre, edad y curso
// @red ¡RELLENA TÚ ESTE CÓDIGO! Usa ⌘N (Mac) o Alt + Insert (Win) para ayudarte
// ==================================================================
abstract class Alumno {
    // @cyan Atributos comunes: nombre, edad, curso
    // private String nombre;
    // ...

    // @green Constructor
    // public Alumno(...) { ... }

    // @yellow Getters y Setters
    // ...

    // @blue Método abstracto mostrarInformacion()
    // public abstract void mostrarInformacion();

    // @purple Método toString() que muestre info básica
    // public String toString() { ... }
}

// ==================================================================
// 2. CLASES HIJAS - AlumnoPresencial, AlumnoOnline, AlumnoErasmus
// ------------------------------------------------------------------
// @blue Cada clase hereda de Alumno y añade un campo específico
// @green Implementan mostrarInformacion()
// ==================================================================

class AlumnoPresencial extends Alumno {
    // @cyan Atributo específico: aula
    // ...

    // @green Constructor que llame al super()
    // ...

    // @blue Implementación de mostrarInformacion()
    // ...
}

class AlumnoOnline extends Alumno {
    // @cyan Atributo específico: plataforma
    // ...

    // @green Constructor que llame al super()
    // ...

    // @blue Implementación de mostrarInformacion()
    // ...
}

class AlumnoErasmus extends Alumno {
    // @cyan Atributo específico: paisOrigen
    // ...

    // @green Constructor que llame al super()
    // ...

    // @blue Implementación de mostrarInformacion()
    // ...
}

// ==================================================================
// 3. CLASE ClaseGrupo - gestión del grupo de alumnos
// ------------------------------------------------------------------
// @blue Contiene una lista de alumnos
// @green Métodos: agregarAlumno(), mostrarAlumnos(), buscarAlumnoPorNombre()
// ==================================================================

class ClaseGrupo {
    // @cyan Atributo: lista de alumnos
    // private ArrayList<Alumno> listaAlumnos = new ArrayList<>();

    // @green Método agregarAlumno()
    // public void agregarAlumno(Alumno a) { ... }

    // @yellow Método mostrarAlumnos()
    // public void mostrarAlumnos() { ... }

    // @purple Método buscarAlumnoPorNombre(String nombre)
    // public void buscarAlumnoPorNombre(String nombre) { ... }
}

// ==================================================================
// 4. CLASE MAIN - pruebas manuales
// ------------------------------------------------------------------
// @blue Aquí puedes crear alumnos, agregarlos al grupo y mostrarlos
// @red No hace falta menú, solo pruebas directas
// ==================================================================

public class UT3_GestionAlumnosPOO {
    public static void main(String[] args) {
        // @green Crear una instancia de ClaseGrupo
        // ClaseGrupo grupo = new ClaseGrupo();

        // @yellow Crear varios alumnos y agregarlos
        // Alumno a1 = new AlumnoPresencial(...);
        // grupo.agregarAlumno(a1);

        // @purple Mostrar todos los alumnos
        // grupo.mostrarAlumnos();

        // @blue Buscar alumno por nombre
        // grupo.buscarAlumnoPorNombre("María");
    }
}

/***********************************************
 * ! EJERCICIOS OPCIONALES PARA PRACTICAR 🚀 *
 ***********************************************
 * 1️⃣ Añade una clase "AlumnoBecado" que tenga el atributo "tipoBeca"
 * 2️⃣ Permite ordenar los alumnos por nombre alfabéticamente
 * 3️⃣ Añade un método que muestre solo los alumnos de un tipo específico (por ejemplo, solo Erasmus)
 * 4️⃣ Guarda en archivo.txt la lista completa de alumnos
 * 5️⃣ Crea una clase Profesor con nombre y asignatura, y relaciónala con ClaseGrupo
 ***********************************************/
