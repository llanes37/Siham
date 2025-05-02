/******************************************************************************************
 * 🛒 UD6 - TAREA 6: CARRITO DE LA COMPRA CON RESERVAS
 * ─────────────────────────────────────────────────────────────────────────────
 * 🎯 OBJETIVO: Modificar el carrito de compra para gestionar reservas
 *   en lugar de reducir stock directo. Añadir capacidad de desreserva
 *   y confirmar la venta restando stock real al pagar.
 *
 * 🧠 TEORÍA:
 *  - Cada artículo tiene un stock total (quantityStock) y un stock reservado.
 *  - `availableStock = quantityStock - reserved` indica cuántos quedan sin reservar.
 *  - Reservar (addToBasket) no reduce inmediatamente `quantityStock`, sino `reserved`.
 *  - Desreservar (removeFromBasket) libera cantidades reservadas.
 *  - Al pagar (checkout), se reduce `quantityStock -= reserved` y `reserved` se pone a 0.
 ******************************************************************************************/

import java.util.*;

// ==================================================================
// 🏷️ Clase StockItem: representa un artículo en stock con reservas
// ==================================================================
class StockItem {
    private final String name;           // Nombre del artículo
    private double price;                // Precio unitario
    private int quantityStock = 0;       // Stock total disponible
    private int reserved = 0;            // Cantidad actualmente reservada

    /** Constructor principal */
    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int availableStock() {
        // Stock no reservado
        return quantityStock - reserved;
    }

    /** Reserva una cantidad si hay stock suficiente */
    public int reserveStock(int quantity) {
        if (quantity <= availableStock()) {
            reserved += quantity;
            return quantity;
        }
        return 0;
    }

    /** Anula una reserva si existe esa cantidad reservada */
    public int unreserveStock(int quantity) {
        if (quantity <= reserved) {
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    /** Finaliza la compra: reduce stock real y libera reserva */
    public int finalizeStock(int quantity) {
        if (quantity <= reserved) {
            quantityStock -= quantity;
            reserved -= quantity;
            return quantity;
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + " : precio " + price +
               " | stock disponible " + availableStock() +
               " | reservados " + reserved;
    }
}

// ==================================================================
// 🛒 Clase Basket: cesta de compra que reserva artículos
// ==================================================================
class Basket {
    private final String name;                         // Nombre de la cesta/cliente
    private final Map<StockItem, Integer> list;        // Artículo -> cantidad reservada

    public Basket(String name) {
        this.name = name;
        this.list = new LinkedHashMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item.reserveStock(quantity) != 0) {
            list.put(item, list.getOrDefault(item, 0) + quantity);
            return quantity;
        }
        System.out.println("No hay suficiente stock para reservar " + quantity + " de " + item.getName());
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        Integer inBasket = list.get(item);
        if (inBasket != null && quantity <= inBasket) {
            if (item.unreserveStock(quantity) != 0) {
                int newQty = inBasket - quantity;
                if (newQty > 0) {
                    list.put(item, newQty);
                } else {
                    list.remove(item);
                }
                return quantity;
            }
        }
        System.out.println("No puedes desreservar " + quantity + " de " + item.getName());
        return 0;
    }

    public void checkout() {
        for (Map.Entry<StockItem, Integer> entry : list.entrySet()) {
            StockItem item = entry.getKey();
            int quantity = entry.getValue();
            item.finalizeStock(quantity);
        }
        list.clear();
    }

    @Override
    public String toString() {
        String s = "\nCesta de " + name + " contiene " + list.size() + " artículos\n";
        double totalCost = 0.0;
        for (Map.Entry<StockItem, Integer> entry : list.entrySet()) {
            s += entry.getKey() + " -> cantidad " + entry.getValue() + "\n";
            totalCost += entry.getKey().getPrice() * entry.getValue();
        }
        return s + "Total coste " + totalCost;
    }
}

// ==================================================================
// 🚀 Clase Main: demostración de reservas y desreservas de carrito
// ==================================================================
public class Tarea6_CarritoCompra {
    private static StockList stockList = new StockList(); // Inventario global

    public static void main(String[] args) {
        // @blue Inicialización de stock
        stockList.addStock(new StockItem("Pan", 1.0, 100));
        stockList.addStock(new StockItem("Leche", 0.80, 50));
        stockList.addStock(new StockItem("Huevos", 0.10, 200));

        // @green Crear dos cestas
        Basket anaBasket = new Basket("Ana");
        Basket juanBasket = new Basket("Juan");

        // @green Reservas válidas
        anaBasket.addToBasket(stockList.get("Pan"), 10);
        anaBasket.addToBasket(stockList.get("Leche"), 5);

        // @yellow Intento de sobre-reservar
        anaBasket.addToBasket(stockList.get("Pan"), 200); // Demasiado

        // @green Desreservar algunas unidades
        anaBasket.removeFromBasket(stockList.get("Pan"), 5);
        // @red Intento de desreservar más de lo reservado
        anaBasket.removeFromBasket(stockList.get("Pan"), 10);

        System.out.println(anaBasket);
        System.out.println(stockList);

        // @blue Pagar la cesta de Ana
        anaBasket.checkout();
        System.out.println("\nDespués del pago de Ana:");
        System.out.println(stockList);
        System.out.println(anaBasket);

        // @green Juan reserva y paga
        juanBasket.addToBasket(stockList.get("Huevos"), 12);
        juanBasket.checkout();
        System.out.println("\nDespués del pago de Juan:");
        System.out.println(stockList);
        System.out.println(juanBasket);
    }
}

// ==================================================================
// 📦 Clase StockList: gestiona inventario de StockItem
// ==================================================================
class StockList {
    private final Map<String, StockItem> list = new HashMap<>();

    public int addStock(StockItem item) {
        StockItem inStock = list.get(item.getName());
        if (inStock != null) {
            item = new StockItem(item.getName(), item.getPrice(),
                                 item.availableStock() + inStock.availableStock());
        }
        list.put(item.getName(), item);
        return item.availableStock();
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    @Override
    public String toString() {
        String s = "\nInventario del almacén:\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> entry : list.entrySet()) {
            StockItem item = entry.getValue();
            s += item + "\n";
            totalCost += item.getPrice() * item.availableStock();
        }
        return s + "Coste total valor en stock " + totalCost;
    }
}