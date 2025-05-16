
import java.util.*;
import java.io.*;
import java.util.function.*; // Para lambdas y genéricos

/******************************************************************************************
 * EXAMEN 1 – OPCIÓN A: GESTOR DE BIBLIOTECA
 * Colecciones: Set, Map, TreeSet. Ficheros. Genéricos y lambdas. Expresiones regulares.
 * 
 * 1. Clase Libro (ISBN, título, autor, disponible)
 * 2. Menú en Main con:
 *      a. Añadir libro (HashSet)
 *      b. Buscar libro (HashMap)
 *      c. Modificar disponibilidad
 *      d. Eliminar libro
 *      e. Mostrar ordenados por título (TreeSet)
 *      f. Copiar solo disponibles a TreeSet
 * 3. Ficheros: escribir, leer, modificar "biblioteca.txt"
 * 4. Genéricos/lambdas: Repositorio<T>, Function, Consumer, Streams
 * 5. Expresiones regulares: validar ISBN y reemplazar palabras en mayúsculas
 ******************************************************************************************/

// =================== CLASE LIBRO ===================
class Libro {
    private String isbn;         // Código ISBN, debe ser único
    private String titulo;       // Título del libro
    private String autor;        // Autor del libro
    private boolean disponible;  // Estado: disponible o prestado

    // Constructor
    public Libro(String isbn, String titulo, String autor, boolean disponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    // Métodos getter y setter
    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // TODO: equals y hashCode SOLO por isbn (para HashSet y HashMap)
    @Override
    public boolean equals(Object obj) {
        // COMPLETAR EN CLASE
        return super.equals(obj);
    }
    @Override
    public int hashCode() {
        // COMPLETAR EN CLASE
        return super.hashCode();
    }

    // TODO: toString personalizado (ISBN;Título;Autor;Disponible)
    @Override
    public String toString() {
        // COMPLETAR EN CLASE
        return "";
    }
}

// =================== CLASE GENÉRICA REPOSITORIO<T> ===================
class Repositorio<T> {
    private List<T> elementos = new ArrayList<>();

    public void añadir(T elemento) {
        // COMPLETAR EN CLASE
    }

    public void eliminar(T elemento) {
        // COMPLETAR EN CLASE
    }

    public List<T> listar() {
        // COMPLETAR EN CLASE
        return elementos;
    }
}

// =================== COMPARATOR ORDENAR POR TÍTULO (TreeSet) ===================
class ComparadorTitulo implements Comparator<Libro> {
    @Override
    public int compare(Libro l1, Libro l2) {
        // COMPLETAR EN CLASE: ordenar alfabéticamente por título (¡y si empatan, por ISBN!)
        return 0;
    }
}

// =================== CLASE PRINCIPAL (solo esqueleto, el menú lo completará la alumna) ===================
public class Examen1_GestorBiblioteca {

    // TODO: Declarar las colecciones necesarias como atributos (HashSet, HashMap, TreeSet...)

    public static void main(String[] args) throws IOException {
        // TODO: Implementar el menú interactivo (opciones a-f y operaciones con ficheros)
        // TODO: Implementar validación de ISBN y expresión regular para mayúsculas
        // TODO: Usar Repositorio<Libro>, Function, Consumer, Streams para mostrar y transformar datos
        // TODO: Probar TreeSet con ComparadorTitulo

        // Todos los métodos/funciones los irá completando la alumna en clase.
    }
}
