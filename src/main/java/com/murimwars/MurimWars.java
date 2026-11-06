
package com.murimwars;

import java.util.List;
import java.util.Scanner;

public class MurimWars {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        ProductoDAO dao = new ProductoDAO();

        int opcion;

        do {
            System.out.println("\n===== MURIM WARS =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            String entrada = teclado.nextLine().trim();

while (entrada.isEmpty()) {
    System.out.print("Seleccione una opcion (1-5): ");
    entrada = teclado.nextLine().trim();
}

opcion = Integer.parseInt(entrada);

            switch (opcion) {

                case 1:
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Descripcion: ");
                    String descripcion = teclado.nextLine();

                    System.out.print("Precio: ");
                    double precio = Double.parseDouble(teclado.nextLine());

                    System.out.print("Stock: ");
                    int stock = Integer.parseInt(teclado.nextLine());

                    System.out.print("ID de categoria: ");
                    int categoria = Integer.parseInt(teclado.nextLine());

                    Producto nuevo = new Producto(
                        0, nombre, descripcion,
                        precio, stock, categoria
                    );

                    if (dao.insertarProducto(nuevo)) {
                        System.out.println("Producto registrado correctamente.");
                    } else {
                        System.out.println("No se pudo registrar el producto.");
                    }
                    break;

                case 2:
                    List<Producto> productos = dao.listarProductos();

                    System.out.println("\n===== LISTA DE PRODUCTOS =====");

                    for (Producto p : productos) {
                        System.out.println(
                            "ID: " + p.getIdProducto()
                            + " | Nombre: " + p.getNombre()
                            + " | Precio: " + p.getPrecio()
                            + " | Stock: " + p.getStock()
                        );
                    }
                    break;

                case 3:
                    System.out.print("ID del producto a actualizar: ");
                    int idActualizar = Integer.parseInt(teclado.nextLine());

                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = teclado.nextLine();

                    System.out.print("Nueva descripcion: ");
                    String nuevaDescripcion = teclado.nextLine();

                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = Double.parseDouble(teclado.nextLine());

                    System.out.print("Nuevo stock: ");
                    int nuevoStock = Integer.parseInt(teclado.nextLine());

                    System.out.print("ID de categoria: ");
                    int nuevaCategoria = Integer.parseInt(teclado.nextLine());

                    Producto actualizado = new Producto(
                        idActualizar, nuevoNombre,
                        nuevaDescripcion, nuevoPrecio,
                        nuevoStock, nuevaCategoria
                    );

                    if (dao.actualizarProducto(actualizado)) {
                        System.out.println("Producto actualizado correctamente.");
                    } else {
                        System.out.println("No se pudo actualizar el producto.");
                    }
                    break;

                case 4:
                    System.out.print("ID del producto a eliminar: ");
                    String entradaEliminar = teclado.nextLine().trim();
int idEliminar = Integer.parseInt(entradaEliminar);

                    if (dao.eliminarProducto(idEliminar)) {
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("No se pudo eliminar el producto.");
                    }
                    break;

                case 5:
                    System.out.println("Gracias por utilizar Murim Wars.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }

        } while (opcion != 5);

        teclado.close();
    }
}
