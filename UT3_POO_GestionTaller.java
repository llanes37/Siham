/**
 * ! Proyecto Completo - Gestión de Vehículos en un Taller Mecánico 🚗🔧
 * ? Este proyecto enseña conceptos clave de Programación Orientada a Objetos en Java,
 * ? incluyendo el uso de clases abstractas, interfaces, herencia, excepciones personalizadas,
 * ? estructuras de control y listas.
 * * Cada parte está explicada detalladamente con comentarios para un mejor aprendizaje.
 * 
 * ! IMPORTANTE: Se recomienda probar el código en VS Code con "Java Extension Pack".
 * Autor: Joaquín  
 * Fecha: 2025
 */

 import java.util.ArrayList;
 import java.util.Scanner;
 
 /******************************
  * ! TEORÍA SOBRE POO EN JAVA *
  ******************************
  * ? 1. **Clases y Objetos:**
  *      - Una clase es un modelo que define atributos y métodos.
  *      - Un objeto es una instancia de una clase.
  *
  * ? 2. **Herencia:**
  *      - Permite que una clase hija herede atributos y métodos de una clase padre.
  *      - Se usa con la palabra clave `extends`.
  *
  * ? 3. **Interfaces:**
  *      - Define métodos que deben ser implementados por una clase.
  *      - Se usa `implements` para obligar a una clase a seguir un contrato.
  *
  * ? 4. **Excepciones Personalizadas:**
  *      - Se pueden crear clases propias de excepciones para validar errores específicos.
  *
  * ? 5. **Listas y Estructuras de Control:**
  *      - `ArrayList<>` permite manejar listas dinámicas.
  *      - `if-else`, `switch`, `for`, `while` permiten manejar la lógica del programa.
  */
 
 /**
  * ! Clase Abstracta Vehiculo 🚗
  * ? Define los atributos y métodos comunes para todos los vehículos.
  */
 abstract class Vehiculo {
     protected String marca;
     protected String modelo;
     protected int anio;
 
     // Constructor
     public Vehiculo(String marca, String modelo, int anio) {
         this.marca = marca;
         this.modelo = modelo;
         this.anio = anio;
     }
 
     // Método abstracto que las subclases deben implementar
     public abstract void mostrarDetalles();
 }
 
 /**
  * ! Interfaz Reparacion 🔧
  * ? Define métodos que los vehículos deben implementar para ser reparados.
  */
 interface Reparacion {
     void reparar();
 }
 
 /**
  * ! Clase Coche (Hija de Vehiculo) 🚘
  * ? Representa un coche con atributos y métodos específicos.
  */
 class Coche extends Vehiculo implements Reparacion {
     private boolean electrico;
 
     public Coche(String marca, String modelo, int anio, boolean electrico) {
         super(marca, modelo, anio);
         this.electrico = electrico;
     }
 
     @Override
     public void mostrarDetalles() {
         System.out.println("Coche: " + marca + " " + modelo + " (" + anio + ") - " + (electrico ? "Eléctrico" : "Gasolina"));
     }
 
     @Override
     public void reparar() {
         System.out.println("🔧 Reparando coche " + modelo + "...");
     }
 }
 
 /**
  * ! Clase Moto (Hija de Vehiculo) 🏍️
  * ? Representa una moto con atributos y métodos específicos.
  */
 class Moto extends Vehiculo implements Reparacion {
     private int cilindrada;
 
     public Moto(String marca, String modelo, int anio, int cilindrada) {
         super(marca, modelo, anio);
         this.cilindrada = cilindrada;
     }
 
     @Override
     public void mostrarDetalles() {
         System.out.println("Moto: " + marca + " " + modelo + " (" + anio + ") - " + cilindrada + "cc");
     }
 
     @Override
     public void reparar() {
         System.out.println("🔧 Reparando moto " + modelo + "...");
     }
 }
 
 /**
  * ! Excepción Personalizada: ErrorVehiculo
  * ? Se lanza cuando se intenta agregar un vehículo ya existente al taller.
  */
 class ErrorVehiculo extends Exception {
     public ErrorVehiculo(String mensaje) {
         super(mensaje);
     }
 }
 
 /**
  * ! Clase Taller 🛠️
  * ? Representa un taller mecánico que gestiona una lista de vehículos.
  */
 class Taller {
     private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
 
     public void agregarVehiculo(Vehiculo vehiculo) throws ErrorVehiculo {
         for (Vehiculo v : vehiculos) {
             if (v.marca.equals(vehiculo.marca) && v.modelo.equals(vehiculo.modelo)) {
                 throw new ErrorVehiculo("❌ El vehículo ya está registrado en el taller.");
             }
         }
         vehiculos.add(vehiculo);
         System.out.println("✅ Vehículo agregado al taller: " + vehiculo.modelo);
     }
 
     public void mostrarVehiculos() {
         System.out.println("\n🚗 Lista de Vehículos en el Taller:");
         for (Vehiculo v : vehiculos) {
             v.mostrarDetalles();
         }
     }
 }
 
 /**
  * ! Clase Principal - Simulación del Taller Mecánico 🚘🔧
  */
 public class UT3_POO_GestionTaller {
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         Taller taller = new Taller();
 
         try {
             Coche coche1 = new Coche("Toyota", "Corolla", 2020, false);
             
             Moto moto1 = new Moto("Yamaha", "R1", 2019, 1000);
 
             taller.agregarVehiculo(coche1);
             taller.agregarVehiculo(coche1);
             taller.agregarVehiculo(moto1);
         } catch (ErrorVehiculo e) {
             System.out.println(e.getMessage());
         }
 
         taller.mostrarVehiculos();
         scanner.close();
     }
 }
 
 /***********************************************
  * ! EJERCICIOS ADICIONALES PARA EL ALUMNO 🚀 *
  ***********************************************
  * 1️⃣ Agrega una nueva clase "Camion" que herede de Vehiculo e implemente Reparacion.
  * 2️⃣ Modifica el taller para que permita buscar vehículos por modelo.
  * 3️⃣ Implementa un menú interactivo donde el usuario pueda agregar vehículos manualmente.
  * 4️⃣ Crea un método en Coche y Moto para calcular el impuesto de circulación según el año del vehículo.
  * 5️⃣ Modifica la estructura para permitir la eliminación de un vehículo del taller.
  */
 