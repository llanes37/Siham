/******************************************************************************************
 * 📚 UD6 - PRÁCTICA COMPLETA DE SET: SISTEMA SOLAR Y SATÉLITES
 * ─────────────────────────────────────────────────────────────────────────────
 * 🎯 OBJETIVO: Usar `Set` y `HashMap` en Java para modelar un sistema solar
 *              con planetas, lunas y operaciones de conjunto.
 *
 * 🧠 TEORÍA INTRODUCTORIA:
 *  - Un `Set` es una colección que no permite duplicados. Ideal para almacenar
 *    elementos únicos (p.ej., planetas sin repetición).
 *  - `HashSet` es una implementación concreta de `Set` basada en una tabla hash.
 *  - Un `Map<K,V>` almacena pares clave-valor; `HashMap` permite búsquedas eficientes.
 *  - Sobrescribir `equals()` y `hashCode()` es crucial para que `HashSet` identifique
 *    correctamente elementos duplicados.
 *  - Al usar OOP, definimos clases para entidades del dominio (CuerpoCeleste, Planeta, Luna).
 *  - Las operaciones de conjunto (unión, intersección, diferencia) se logran con
 *    `addAll()`, `retainAll()`, `removeAll()` respectivamente.
 ******************************************************************************************/

 import java.util.*;

 // ==================================================================
 // 🌟 CLASE CuerpoCeleste: Representa cualquier cuerpo (planeta, luna, etc.)
 // ==================================================================
 class CuerpoCeleste {
     // Enumerado anidado con tipos posibles de cuerpos celestes
     public enum TipoCuerpoCeleste { ESTRELLA, PLANETA, PLANETA_ENANO, LUNA, COMETA, ASTEROIDE }
 
     private final String nombre;                         // Nombre identificador
     private final double periodoOrbital;                 // Periodo en días
     private final TipoCuerpoCeleste tipoCuerpo;          // Tipo según enum
     private final Set<CuerpoCeleste> satelites;          // Conjunto de satélites (lunas)
 
     /**
      * Constructor principal.
      * @param nombre Nombre del cuerpo celeste.
      * @param periodoOrbital Periodo orbital en días.
      * @param tipoCuerpo Tipo definido en el enum.
      */
     public CuerpoCeleste(String nombre, double periodoOrbital, TipoCuerpoCeleste tipoCuerpo) {
         this.nombre = nombre;
         this.periodoOrbital = periodoOrbital;
         this.tipoCuerpo = tipoCuerpo;
         this.satelites = new HashSet<>();                // Creamos un HashSet vacío para satélites
     }
 
     /**
      * @return Nombre del cuerpo.
      */
     public String getNombre() { return nombre; }
 
     /**
      * @return Periodo orbital.
      */
     public double getPeriodoOrbital() { return periodoOrbital; }
 
     /**
      * @return Tipo de cuerpo celeste.
      */
     public TipoCuerpoCeleste getTipoCuerpo() { return tipoCuerpo; }
 
     /**
      * @return Copia de los satélites asociados (evita exposición directa).
      */
     public Set<CuerpoCeleste> getSatelites() {
         return new HashSet<>(satelites);              // Devolvemos nueva instancia para protegerla
     }
 
     /**
      * Agrega un satélite a este cuerpo.
      * @param moon Objeto CuerpoCeleste que será satélite.
      * @return true si se añadió, false si ya existía.
      */
     public boolean addSatelite(CuerpoCeleste moon) {
         return this.satelites.add(moon);
     }
 
     /**
      * Dos cuerpos son iguales si tienen mismo nombre y tipo.
      */
     @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;                  // Mismo objeto
         if (!(obj instanceof CuerpoCeleste)) return false;
         CuerpoCeleste otro = (CuerpoCeleste) obj;
         return this.nombre.equals(otro.nombre)
             && this.tipoCuerpo == otro.tipoCuerpo;
     }
 
     /**
      * Calcula el hash combinando nombre y tipo.
      */
     @Override
     public int hashCode() {
         return Objects.hash(nombre, tipoCuerpo, 31);
     }
 
     /**
      * Representación en String: "nombre: tipo, periodoOrbital días".
      */
     @Override
     public String toString() {
         return nombre + ": " + tipoCuerpo + ", " + periodoOrbital + " días";
     }
 }
 
 // ==================================================================
 // 🌐 CLASE Planeta: Subclase específica para planetas terrestres
 // ==================================================================
 class Planeta extends CuerpoCeleste {
     /**
      * Constructor de Planeta.
      */
     public Planeta(String nombre, double periodoOrbital) {
         super(nombre, periodoOrbital, TipoCuerpoCeleste.PLANETA);
     }
 
     /**
      * Solo agrega satélite si es de tipo LUNA.
      */
     @Override
     public boolean addSatelite(CuerpoCeleste moon) {
         if (moon.getTipoCuerpo() == TipoCuerpoCeleste.LUNA) {
             return super.addSatelite(moon);
         }
         return false;
     }
 }
 
 // ==================================================================
 // ☄️ CLASE PlanetaEnano: Subclase específica para planetas enanos
 // ==================================================================
 class PlanetaEnano extends CuerpoCeleste {
     public PlanetaEnano(String nombre, double periodoOrbital) {
         super(nombre, periodoOrbital, TipoCuerpoCeleste.PLANETA_ENANO);
     }
 }
 
 // ==================================================================
 // 🌑 CLASE Luna: Subclase específica para lunas
 // ==================================================================
 class Luna extends CuerpoCeleste {
     public Luna(String nombre, double periodoOrbital) {
         super(nombre, periodoOrbital, TipoCuerpoCeleste.LUNA);
     }
 }
 
 // ==================================================================
 // 🚀 CLASE MAIN: Controla creación de sistema solar y operaciones con Set
 // ==================================================================
 public class UD6_Set_SistemaSolar {
     // Mapa nombre->CuerpoCeleste para búsquedas rápidas
     private static Map<String, CuerpoCeleste> sistemaSolar = new HashMap<>();
     // Conjunto de planetas para iteración sin duplicados
     private static Set<CuerpoCeleste> planetas = new HashSet<>();
 
     public static void main(String[] args) {
         // ------- Creación de planetas -------
         Planeta mercurio  = new Planeta("Mercurio", 88);
         Planeta venus     = new Planeta("Venus",    225);
         Planeta tierra    = new Planeta("Tierra",   365);
         Planeta marte     = new Planeta("Marte",    687);
         Planeta jupiter   = new Planeta("Jupiter", 4332);
         Planeta saturno   = new Planeta("Saturno",10759);
         Planeta urano     = new Planeta("Urano",  30660);
         Planeta neptuno   = new Planeta("Neptuno", 165);
         PlanetaEnano pluton= new PlanetaEnano("Pluton",248);
 
         // ------- Añadir al mapa y set -------
         sistemaSolar.put(mercurio.getNombre(), mercurio);
         sistemaSolar.put(venus.getNombre(),    venus);
         sistemaSolar.put(tierra.getNombre(),   tierra);
         sistemaSolar.put(marte.getNombre(),    marte);
         sistemaSolar.put(jupiter.getNombre(),  jupiter);
         sistemaSolar.put(saturno.getNombre(),  saturno);
         sistemaSolar.put(urano.getNombre(),    urano);
         sistemaSolar.put(neptuno.getNombre(),  neptuno);
         sistemaSolar.put(pluton.getNombre(),   pluton);
         planetas.addAll(Arrays.asList(mercurio,venus,tierra,marte,jupiter,saturno,urano,neptuno,pluton));
 
         // ------- Creación de lunas -------
         Luna luna      = new Luna("Luna", 27);
         Luna deimos    = new Luna("Deimos",1.3);
         Luna phobos    = new Luna("Phobos",0.3);
         Luna io        = new Luna("Io",    1.8);
         Luna europa    = new Luna("Europa",3.5);
         Luna ganymede  = new Luna("Ganymede",7.1);
         Luna callisto  = new Luna("Callisto",16.7);
 
         // ------- Añadir lunas al sistemaSolar -------
         for (Luna l : Arrays.asList(luna,deimos,phobos,io,europa,ganymede,callisto)) {
             sistemaSolar.put(l.getNombre(), l);
         }
 
         // ------- Asociar lunas a planetas correspondientes -------
         tierra.addSatelite(luna);
         marte.addSatelite(deimos);
         marte.addSatelite(phobos);
         jupiter.addSatelite(io);
         jupiter.addSatelite(europa);
         jupiter.addSatelite(ganymede);
         jupiter.addSatelite(callisto);
 
         // ------- Imprimir todos los planetas -------
         System.out.println("\n☀ Planetas en el sistema solar:");
         for (CuerpoCeleste p : planetas) {
             System.out.println(" - " + p);
         }
 
         // ------- Imprimir lunas de Marte -------
         System.out.println("\n☁ Lunas de Marte:");
         for (CuerpoCeleste l : sistemaSolar.get("Marte").getSatelites()) {
             System.out.println(" - " + l);
         }
 
         // ------- Unión de todas las lunas -------
         Set<CuerpoCeleste> todasLunas = new HashSet<>();
         for (CuerpoCeleste p : planetas) {
             todasLunas.addAll(p.getSatelites());
         }
         System.out.println("\n☁ Todas las lunas del sistema solar:");
         for (CuerpoCeleste l : todasLunas) {
             System.out.println(" - " + l);
         }
 
         // ------- Probar duplicado con Plutón y equals/hashCode -------
         Planeta pluton884 = new Planeta("Pluton", 884);
         planetas.add(pluton884); // no se añade porque equals() y hashCode() consideran nombre y tipo
         System.out.println("\n⚠ Planetas tras intentar añadir Plutón con periodo 884:");
         for (CuerpoCeleste p : planetas) {
             System.out.println(" - " + p);
         }
 
         // ------- Diferencia (planetas que no son lunas) -------
         Set<CuerpoCeleste> diferencia = new HashSet<>(planetas);
         diferencia.removeAll(todasLunas);
         System.out.println("\n❌ Diferencia (solo planetas, sin lunas):");
         for (CuerpoCeleste c : diferencia) System.out.println(" - " + c);
 
         // ------- Intersección (planetas que también son lunas) -------
         Set<CuerpoCeleste> interseccion = new HashSet<>(planetas);
         interseccion.retainAll(todasLunas);
         System.out.println("\n⚡ Intersección (planetas = lunas): " + interseccion);
     }
 }
 