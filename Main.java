/**
 * Examen 2ª Evaluación 2024/2025
 * Sistema de Gestión de Biblioteca
 * Alumna: Siham
 * Archivo: ExamenBiblioteca.java (Main)
 * Objetivo: Implementar un sistema basado en Programación Orientada a Objetos (POO)
 * Herramienta: IntelliJ IDEA (consejos incluidos para facilitar el desarrollo)
 *
 * ─────────────────────────────────────────────────────────
 * ENUNCIADO DEL EXAMEN:
 * Se desea desarrollar un sistema de gestión para una biblioteca,
 * donde se puedan administrar libros, revistas y periódicos,
 * así como su préstamo y devolución. LEER TODO EL ENUNCIADO.
 * SE VALORARÁ TODO LO QUE SE HAGA EN EL EXAMEN.
 * No tiene que estar perfecto para ser un aprobado. Haga lo máximo posible.
 * El sistema debe seguir los principios de Programación Orientada a Objetos (POO),
 * utilizando herencia, polimorfismo y encapsulación.
 *
 * REQUISITOS DEL SISTEMA:
 * 1️⃣ Clase abstracta Publicacion con atributos, getters/setters y método abstracto mostrarInformacion().
 * 2️⃣ Clases Libro, Revista y Periodico que hereden de Publicacion.
 * 3️⃣ Clase Biblioteca con listas y métodos para gestionar publicaciones.
 * 4️⃣ Clase Main para probar el sistema.
 * 5️⃣ (Extra) Búsqueda por autor/año y ordenación del catálogo.
 *
 * CRITERIOS DE EVALUACIÓN:
 * ✔ Implementación correcta de clases, atributos y métodos
 * ✔ Herencia, polimorfismo, encapsulación
 * ✔ Uso de ArrayList y buenas prácticas
 * ✔ Interacción con usuario (si aplica)
 * ✔ Extras opcionales suman
 *
 * ─────────────────────────────────────────────────────────
 * 💡 CONSEJOS PARA IntelliJ IDEA:
 * - Usa Alt + Insert (Windows) o Cmd + N (Mac) para generar constructores, getters, setters y toString()
 * - Usa Shift + Shift para buscar rápidamente archivos, clases o métodos
 * - Puedes probar cada clase individualmente escribiendo pruebas dentro del método main
 */

 import java.util.ArrayList;

 // ==================================================================
 // 1. CLASE ABSTRACTA BASE - Publicacion
 // ------------------------------------------------------------------
 // Clase base con atributos comunes. Usamos encapsulación y polimorfismo.
 // Better Comment: @blue Esta clase será la base para Libro, Revista y Periódico
 // ==================================================================
 abstract class Publicacion {
     private String titulo; // @cyan Título de la publicación
     private String autor;  // @cyan Autor de la publicación
     private int anioPublicacion; // @cyan Año en que se publicó
 
     // @green Constructor para inicializar los atributos comunes
     public Publicacion(String titulo, String autor, int anioPublicacion) {
         this.titulo = titulo;
         this.autor = autor;
         this.anioPublicacion = anioPublicacion;
     }
 
     // @yellow Getters y Setters generados automáticamente (Alt + Insert en IntelliJ)
     public String getTitulo() { return titulo; }
     public void setTitulo(String titulo) { this.titulo = titulo; }
 
     public String getAutor() { return autor; }
     public void setAutor(String autor) { this.autor = autor; }
 
     public int getAnioPublicacion() { return anioPublicacion; }
     public void setAnioPublicacion(int anioPublicacion) { this.anioPublicacion = anioPublicacion; }
 
     // @blue Método abstracto para que cada clase hija lo implemente a su manera
     public abstract void mostrarInformacion();
 
     // @purple Método reutilizable para mostrar los datos comunes
     public String toString() {
         return "[" + titulo + "] por " + autor + " (" + anioPublicacion + ")";
     }
 }
 
 // ==================================================================
 // 2. CLASES HIJAS - Libro, Revista, Periodico
 // ------------------------------------------------------------------
 // Heredan de Publicacion y añaden comportamiento específico
 // ==================================================================
 class Libro extends Publicacion {
     private String genero; // @cyan Género del libro
     private int numPaginas; // @cyan Total de páginas
 
     public Libro(String titulo, String autor, int anio, String genero, int paginas) {
         super(titulo, autor, anio); // Llama al constructor de Publicacion
         this.genero = genero;
         this.numPaginas = paginas;
     }
 
     @Override
     public void mostrarInformacion() {
         System.out.println("📘 Libro: " + toString() + " | Género: " + genero + " | Páginas: " + numPaginas);
     }
 }
 
 class Revista extends Publicacion {
     private int numeroEdicion; // @cyan Número de edición
     private String categoria;  // @cyan Tipo de revista
 
     public Revista(String titulo, String autor, int anio, int edicion, String categoria) {
         super(titulo, autor, anio);
         this.numeroEdicion = edicion;
         this.categoria = categoria;
     }
 
     @Override
     public void mostrarInformacion() {
         System.out.println("📙 Revista: " + toString() + " | Edición: " + numeroEdicion + " | Categoría: " + categoria);
     }
 }
 
 class Periodico extends Publicacion {
     private String fechaPublicacion; // @cyan Fecha concreta de la edición
     private String editorial;        // @cyan Nombre de la editorial
 
     public Periodico(String titulo, String autor, int anio, String fecha, String editorial) {
         super(titulo, autor, anio);
         this.fechaPublicacion = fecha;
         this.editorial = editorial;
     }
 
     @Override
     public void mostrarInformacion() {
         System.out.println("📰 Periódico: " + toString() + " | Fecha: " + fechaPublicacion + " | Editorial: " + editorial);
     }
 }
 
 // ==================================================================
 // 3. CLASE DE GESTIÓN - Biblioteca
 // ------------------------------------------------------------------
 // Maneja el catálogo y los préstamos usando ArrayList
 // ==================================================================
 class Biblioteca {
     private ArrayList<Publicacion> catalogo;  // @cyan Lista de publicaciones disponibles
     private ArrayList<Publicacion> prestamos; // @cyan Lista de publicaciones prestadas
 
     public Biblioteca() {
         catalogo = new ArrayList<>();
         prestamos = new ArrayList<>();
     }
 
     public void agregarPublicacion(Publicacion p) {
         catalogo.add(p);
     }
 
     public void prestarPublicacion(String titulo) {
         for (Publicacion p : catalogo) {
             if (p.getTitulo().equalsIgnoreCase(titulo)) {
                 prestamos.add(p);
                 catalogo.remove(p);
                 System.out.println("✅ Publicación prestada: " + titulo);
                 return;
             }
         }
         System.out.println("❌ No se encontró en catálogo: " + titulo);
     }
 
     public void devolverPublicacion(String titulo) {
         for (Publicacion p : prestamos) {
             if (p.getTitulo().equalsIgnoreCase(titulo)) {
                 catalogo.add(p);
                 prestamos.remove(p);
                 System.out.println("✅ Publicación devuelta: " + titulo);
                 return;
             }
         }
         System.out.println("❌ No se encontró en préstamos: " + titulo);
     }
 
     public void mostrarCatalogo() {
         System.out.println("\n📚 Catálogo disponible:");
         for (Publicacion p : catalogo) {
             p.mostrarInformacion();
         }
     }
 
     public void mostrarPrestamos() {
         System.out.println("\n📕 Publicaciones prestadas:");
         for (Publicacion p : prestamos) {
             p.mostrarInformacion();
         }
     }
 }
 
 // ==================================================================
 // 4. CLASE PRINCIPAL - Main (solo pruebas directas)
 // ------------------------------------------------------------------
 // Se usa para probar manualmente el funcionamiento de todo
 // ==================================================================
 public class Main {
     public static void main(String[] args) {
         Biblioteca biblioteca = new Biblioteca();
 
         // ✅ Prueba de alta de publicaciones
         Libro libro = new Libro("El Quijote", "Cervantes", 1605, "Novela", 863);
         Revista revista = new Revista("NG España", "Varios", 2024, 210, "Ciencia");
         Periodico periodico = new Periodico("El Mundo", "Redacción", 2024, "30/04/2024", "Unidad Editorial");
 
         biblioteca.agregarPublicacion(libro);
         biblioteca.agregarPublicacion(revista);
         biblioteca.agregarPublicacion(periodico);
 
         // ✅ Verificamos funcionamiento
         biblioteca.mostrarCatalogo();
         biblioteca.prestarPublicacion("El Quijote");
         biblioteca.mostrarPrestamos();
         biblioteca.devolverPublicacion("El Quijote");
         biblioteca.mostrarCatalogo();
     }
 }
 
 /***********************************************
  * ! EJERCICIOS ADICIONALES PARA PRACTICAR 🚀 *
  ***********************************************
  * 1️⃣ Crea una nueva clase "Tesis" que herede de Publicacion y añada:
  *     → String universidad, int numeroPaginas
  *     → Sobrescribe mostrarInformacion()
  *
  * 2️⃣ Añade un método en Biblioteca llamado buscarPorAutor(String autor)
  *     → Que muestre todas las publicaciones escritas por ese autor
  *
  * 3️⃣ Crea un método para ordenar el catálogo por año de publicación
  *     💡 Pista: usa Collections.sort con un Comparator
  *
  * 4️⃣ Haz que el catálogo se guarde automáticamente en un archivo.txt
  *     💡 Usa FileWriter y BufferedWriter para guardar los datos
  *
  * 5️⃣ Crea una clase llamada "Usuario" con nombre, email y lista de préstamos
  *     → Relaciona Usuario con las publicaciones que tiene en préstamo
  ***********************************************/
 