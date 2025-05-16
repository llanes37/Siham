
import java.util.*;
import java.io.*;

/******************************************************************************************
 * 🧪 EJEMPLO GUIADO - GESTOR BÁSICO DE LIBROS
 * Objetivo: Enseñar paso a paso el uso de clases, HashSet, HashMap y métodos simples.
 * El alumno podrá ir completando los métodos y probarlos desde el main.
 ******************************************************************************************/

// ✅ CLASE LIBRO con métodos básicos
class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String isbn, String titulo, String autor, boolean disponible) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = disponible;
    }

    public String getIsbn() { return isbn; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Libro otro)) return false;
        return this.isbn.equals(otro.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode(); // Solo por ISBN
    }

    @Override
    public String toString() {
        return isbn + "; " + titulo + "; " + autor + "; Disponible: " + disponible;
    }
}

// ✅ CLASE PRINCIPAL para ir probando
public class BibliotecaDemo {
    public static void main(String[] args) {
        // Creamos las colecciones básicas
        Set<Libro> librosSet = new HashSet<>();              // Para evitar duplicados
        Map<String, Libro> librosMap = new HashMap<>();      // Para buscar por ISBN

        // Creamos un libro de prueba
        Libro l1 = new Libro("123-4567890123", "El Quijote", "Cervantes", true);

        // Añadir a ambas estructuras
        librosSet.add(l1);
        librosMap.put(l1.getIsbn(), l1);

        // Mostrar libros
        System.out.println("📚 Libros en la biblioteca:");
        for (Libro l : librosSet) {
            System.out.println(l);
        }

        // Buscar por ISBN
        System.out.println("\n🔍 Buscar libro por ISBN:");
        String isbnBuscado = "123-4567890123";
        if (librosMap.containsKey(isbnBuscado)) {
            System.out.println("Encontrado: " + librosMap.get(isbnBuscado));
        } else {
            System.out.println("No existe ese ISBN.");
        }

        // Marcar como prestado
        librosMap.get(isbnBuscado).setDisponible(false);
        System.out.println("\n📕 Libro prestado:");
        System.out.println(librosMap.get(isbnBuscado));

        // 🧪 EJERCICIO: Añadir otro libro y repetir los pasos
        // 🧪 EJERCICIO: Crear método mostrarSoloDisponibles()
    }
}
