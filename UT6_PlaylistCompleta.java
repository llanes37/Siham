/******************************************************************************************
 * 📚 UD6 - PRÁCTICA COMPLETA DE COLECCIONES: PLAYLIST MUSICAL INTERACTIVA
 * ─────────────────────────────────────────────────────────────────────────────
 * 🎯 OBJETIVO: Desarrollar un sistema de gestión de álbumes y canciones
 * mediante `ArrayList`, `LinkedList` e `Iterators`.
 *
 * ✅ LO QUE APRENDERÁS:
 * - Crear clases (`Song`, `Album`) con estructuras de datos internas
 * - Usar `ArrayList` para almacenar canciones en un álbum
 * - Usar `LinkedList` como una lista de reproducción dinámica
 * - Implementar control de flujo con `Iterator` y `ListIterator`
 * - Desarrollar un menú interactivo para controlar la reproducción
 *
 * 🧠 NOTAS:
 * - Usa `ListIterator` para recorrer adelante y atrás la playlist.
 * - Maneja las direcciones de reproducción correctamente.
 * - Puedes usar métodos como `hasNext()`, `hasPrevious()` y `remove()`.
 * - Crea métodos bien estructurados y evita código repetido.
 ******************************************************************************************/

 import java.util.*;

 // ==================================================================
 // 🎵 CLASE SONG – representa una canción con título y duración
 // ==================================================================
 class Song {
     private String titulo;
     private double duracion;
 
     public Song(String titulo, double duracion) {
         this.titulo = titulo;
         this.duracion = duracion;
     }
 
     public String getTitulo() {
         return titulo;
     }
 
     @Override
     public String toString() {
         return titulo + ": " + duracion + " min";
     }
 }
 
 // ==================================================================
 // 💿 CLASE ALBUM – almacena canciones y permite agregarlas a la playlist
 // ==================================================================
 class Album {
     private String nombre;
     private String artista;
     private ArrayList<Song> canciones;
 
     public Album(String nombre, String artista) {
         this.nombre = nombre;
         this.artista = artista;
         this.canciones = new ArrayList<>();
     }
 
     // 🔒 Método privado que busca una canción por título en el álbum
     private Song findSong(String titulo) {
         for (Song s : canciones) {
             if (s.getTitulo().equalsIgnoreCase(titulo)) return s;
         }
         return null;
     }
 
     // ✅ Agrega una canción si no existe ya en el álbum
     public boolean addSong(String titulo, double duracion) {
         if (findSong(titulo) == null) {
             canciones.add(new Song(titulo, duracion));
             return true;
         }
         return false;
     }
 
     // ✅ Añade canción a la playlist por número de pista
     public boolean addToPlayList(int trackNumber, LinkedList<Song> playlist) {
         int index = trackNumber - 1;
         if (index >= 0 && index < canciones.size()) {
             playlist.add(canciones.get(index));
             return true;
         }
         return false;
     }
 
     // ✅ Añade canción a la playlist por título
     public boolean addToPlayList(String titulo, LinkedList<Song> playlist) {
         Song cancion = findSong(titulo);
         if (cancion != null) {
             playlist.add(cancion);
             return true;
         }
         return false;
     }
 }
 
 // ==================================================================
 // 🚀 CLASE PRINCIPAL – contiene el main y métodos de control
 // ==================================================================
 public class UT6_PlaylistCompleta {
     public static void main(String[] args) {
         // 🎼 Creamos álbumes y agregamos canciones
         ArrayList<Album> albumes = new ArrayList<>();
 
         Album a1 = new Album("Éxitos Pop", "Artista A");
         a1.addSong("Luz del Sol", 3.5);
         a1.addSong("Caminos", 4.0);
         a1.addSong("Universo", 2.8);
 
         Album a2 = new Album("Ritmos", "Artista B");
         a2.addSong("Baila", 3.2);
         a2.addSong("Viento", 4.1);
 
         albumes.add(a1);
         albumes.add(a2);
 
         // 🎧 Creamos playlist (LinkedList)
         LinkedList<Song> playlist = new LinkedList<>();
         a1.addToPlayList("Caminos", playlist);     // Por nombre
         a1.addToPlayList(1, playlist);             // Por número de pista
         a2.addToPlayList("Viento", playlist);
 
         // ▶️ Llamamos al método que controla la reproducción
         play(playlist);
     }
 
     // 📝 Muestra el contenido de la playlist
     public static void imprimirPlaylist(LinkedList<Song> playlist) {
         System.out.println("🎵 Playlist actual:");
         for (Song s : playlist) {
             System.out.println(" - " + s);
         }
     }
 
     // 📋 Imprime el menú de opciones
     public static void imprimirMenu() {
         System.out.println("""
 ╔═══════════════════════════════╗
 ║       MENÚ DE OPCIONES        ║
 ╠═══════════════════════════════╣
 ║ 0 - Salir                     ║
 ║ 1 - Siguiente canción         ║
 ║ 2 - Canción anterior          ║
 ║ 3 - Repetir canción actual    ║
 ║ 4 - Mostrar playlist          ║
 ║ 5 - Mostrar menú              ║
 ║ 6 - Eliminar canción actual   ║
 ╚═══════════════════════════════╝
 """);
     }
 
     // 🎶 Método principal para ejecutar la reproducción de la playlist
     public static void play(LinkedList<Song> playlist) {
         Scanner sc = new Scanner(System.in);
         boolean continuar = true;
         boolean haciaAdelante = true;
 
         ListIterator<Song> iterador = playlist.listIterator();
         if (!iterador.hasNext()) {
             System.out.println("🚫 No hay canciones en la lista.");
             return;
         }
 
         System.out.println("▶️ Reproduciendo: " + iterador.next());
         imprimirMenu();
 
         while (continuar) {
             System.out.print("Opción > ");
             int opcion = sc.nextInt();
 
             switch (opcion) {
                 case 0:
                     System.out.println("👋 Fin de la reproducción.");
                     continuar = false;
                     break;
                 case 1:
                     if (!haciaAdelante && iterador.hasNext()) iterador.next();
                     haciaAdelante = true;
                     if (iterador.hasNext())
                         System.out.println("▶️ Reproduciendo: " + iterador.next());
                     else
                         System.out.println("🚧 Fin de la lista.");
                     break;
                 case 2:
                     if (haciaAdelante && iterador.hasPrevious()) iterador.previous();
                     haciaAdelante = false;
                     if (iterador.hasPrevious())
                         System.out.println("⏮️ Reproduciendo: " + iterador.previous());
                     else
                         System.out.println("🚧 Estás al principio.");
                     break;
                 case 3:
                     if (haciaAdelante && iterador.hasPrevious())
                         System.out.println("🔁 Repitiendo: " + iterador.previous());
                     else if (!haciaAdelante && iterador.hasNext())
                         System.out.println("🔁 Repitiendo: " + iterador.next());
                     break;
                 case 4:
                     imprimirPlaylist(playlist);
                     break;
                 case 5:
                     imprimirMenu();
                     break;
                 case 6:
                     if (haciaAdelante && iterador.hasPrevious()) {
                         iterador.previous();
                         iterador.remove();
                         System.out.println("🗑️ Canción eliminada.");
                     } else if (!haciaAdelante && iterador.hasNext()) {
                         iterador.next();
                         iterador.remove();
                         System.out.println("🗑️ Canción eliminada.");
                     }
 
                     if (iterador.hasNext()) {
                         System.out.println("🎶 Ahora suena: " + iterador.next());
                         haciaAdelante = true;
                     } else if (iterador.hasPrevious()) {
                         System.out.println("🎶 Ahora suena: " + iterador.previous());
                         haciaAdelante = false;
                     } else {
                         System.out.println("🚫 Lista vacía.");
                     }
                     break;
                 default:
                     System.out.println("❌ Opción no válida.");
             }
         }
         sc.close();
     }
 }
 