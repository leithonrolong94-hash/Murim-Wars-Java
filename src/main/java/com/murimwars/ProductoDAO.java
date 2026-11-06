package com.murimwars;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public boolean insertarProducto(Producto producto) {

        String sql = "INSERT INTO productos "
                + "(nombre, descripcion, precio, stock, id_categoria) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conexion = Conexion.conectar();
             PreparedStatement consulta = conexion.prepareStatement(sql)) {

            consulta.setString(1, producto.getNombre());
            consulta.setString(2, producto.getDescripcion());
            consulta.setDouble(3, producto.getPrecio());
            consulta.setInt(4, producto.getStock());
            consulta.setInt(5, producto.getIdCategoria());

            return consulta.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar producto: " + e.getMessage());
            return false;
        }
        }
        public List<Producto> listarProductos() {
    List<Producto> productos = new ArrayList<>();

    String sql = "SELECT * FROM productos";

    try (Connection conexion = Conexion.conectar();
     PreparedStatement consulta = conexion.prepareStatement(sql)) {

    ResultSet resultado = consulta.executeQuery();

        while (resultado.next()) {
            Producto producto = new Producto(
                resultado.getInt("id_producto"),
                resultado.getString("nombre"),
                resultado.getString("descripcion"),
                resultado.getDouble("precio"),
                resultado.getInt("stock"),
                resultado.getInt("id_categoria")
            );

            productos.add(producto);
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar productos: " + e.getMessage());
    }

    return productos;
}public boolean actualizarProducto(Producto producto) {
    String sql = "UPDATE productos SET nombre = ?, descripcion = ?, "
            + "precio = ?, stock = ?, id_categoria = ? "
            + "WHERE id_producto = ?";

    try (Connection conexion = Conexion.conectar();
         PreparedStatement consulta = conexion.prepareStatement(sql)) {

        consulta.setString(1, producto.getNombre());
        consulta.setString(2, producto.getDescripcion());
        consulta.setDouble(3, producto.getPrecio());
        consulta.setInt(4, producto.getStock());
        consulta.setInt(5, producto.getIdCategoria());
        consulta.setInt(6, producto.getIdProducto());

        return consulta.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al actualizar producto: " + e.getMessage());
        return false;
    }
    }
public boolean eliminarProducto(int idProducto) {
    String sql = "DELETE FROM productos WHERE id_producto = ?";

    try (Connection conexion = Conexion.conectar();
         PreparedStatement consulta = conexion.prepareStatement(sql)) {

        consulta.setInt(1, idProducto);

        return consulta.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al eliminar producto: " + e.getMessage());
        return false;
    }
}}
