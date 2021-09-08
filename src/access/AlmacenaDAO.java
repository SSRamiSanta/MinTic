package access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.AlmacenaModel;
import utils.ConexionDB;

public class AlmacenaDAO {

    private Connection conn = null;

    public ArrayList<AlmacenaModel> getAllAlmacena() {
        ArrayList<AlmacenaModel> almacenas = new ArrayList<AlmacenaModel>();
        try {
            if (conn == null) {
                conn = ConexionDB.getConexion();
            }

            String sql = "SELECT bodega.nombre, direccion, producto.nombre,precio,cantidad"
                    + " FROM bodega JOIN producto JOIN almacena"
                    + " ON (bodega.idBodega = almacena.idBodega AND producto.idproducto = almacena.idproducto);";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                AlmacenaModel almacena = new AlmacenaModel(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4), result.getInt(5));
                almacenas.add(almacena);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }

        return almacenas;
    }

    public ArrayList<AlmacenaModel> getAlmacenaIds(String bodega, String producto) {
        ArrayList<AlmacenaModel> almacenas = new ArrayList<AlmacenaModel>();
        try {
            if (conn == null) {
                conn = ConexionDB.getConexion();
            }
            String sql = "SELECT bodega.idBodega,producto.idproducto FROM bodega,producto where bodega.nombre = ? AND producto.nombre = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, bodega);
            statement.setString(2, producto);
            ResultSet result = statement.executeQuery();
            System.out.println("bodega " + bodega);
            System.out.println("producto " + producto);
            if (result.next() == true) {
                AlmacenaModel almacena = new AlmacenaModel(result.getInt(1), result.getInt(2));
                System.out.println("id Bodega: " + result.getInt(1));
                System.out.println("id producto: " + result.getInt(2));
                almacenas.add(almacena);

            } else {
                System.out.println("No existen esos datos");
            }

            /*
            while (result.next()) {
                AlmacenaModel almacena = new AlmacenaModel(result.getInt(1), result.getInt(2), cantidad);
                System.out.println("id Bodega: " + result.getInt(1));
                System.out.println("id producto: " + result.getInt(2));
                almacenas.add(almacena);
                break;
            }
             */
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
        return almacenas;
    }

    public ArrayList<AlmacenaModel> getAlmacenaSearch(int idBodega, int idProducto) {
        ArrayList<AlmacenaModel> almacenas = new ArrayList<AlmacenaModel>();
        try {
            if (conn == null) {
                conn = ConexionDB.getConexion();
            }

            String sql;
            PreparedStatement statement;
            if (idBodega == -1) {
                sql = "SELECT bodega.nombre, direccion, producto.nombre,precio,cantidad"
                        + " FROM bodega JOIN producto JOIN almacena"
                        + " ON (bodega.idBodega = almacena.idBodega AND producto.idproducto = almacena.idproducto)"
                        + " WHERE producto.idproducto = ?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idProducto);
            } else if (idProducto == -1) {
                sql = "SELECT bodega.nombre, direccion, producto.nombre,precio,cantidad"
                        + " FROM bodega JOIN producto JOIN almacena"
                        + " ON (bodega.idBodega = almacena.idBodega AND producto.idproducto = almacena.idproducto)"
                        + " WHERE bodega.idBodega=?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idBodega);
            } else {
                sql = "SELECT bodega.nombre, direccion, producto.nombre,precio,almacena.cantidad"
                        + " FROM bodega JOIN producto JOIN almacena"
                        + " WHERE bodega.idBodega = ? AND producto.idproducto = ?"
                        + " AND almacena.idBodega = ? and almacena.idproducto = ?";
                statement = conn.prepareStatement(sql);
                statement.setInt(1, idBodega);
                statement.setInt(2, idProducto);
                statement.setInt(3, idBodega);
                statement.setInt(4, idProducto);
            }

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                AlmacenaModel almacena = new AlmacenaModel(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4), result.getInt(5));
                almacenas.add(almacena);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
        return almacenas;
    }

    public void insertAlmacena(AlmacenaModel almacena) {
        try {
            if (conn == null) {
                conn = ConexionDB.getConexion();
            }

            String sql = "INSERT INTO almacena(idBodega,idproducto,cantidad) VALUES (?,?,?);";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, almacena.getIdBodegaFK());
            statement.setInt(2, almacena.getIdProductoFK());
            statement.setInt(3, almacena.getCantidad());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue agregado exitosamente !");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }

    }

    public void updateAlmacena(AlmacenaModel almacena) {
        try {
            if (conn == null) {
                conn = ConexionDB.getConexion();
            }

            String sql = "UPDATE almacena SET idBodega = ?, idproducto = ?, cantidad = ?  WHERE idBodega = ? AND idproducto = ?;";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, almacena.getIdBodegaFK());
            statement.setInt(2, almacena.getIdProductoFK());
            statement.setInt(3, almacena.getCantidad());
            statement.setInt(4, almacena.getIdBodegaFK());
            statement.setInt(5, almacena.getIdProductoFK());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue actualizado exitosamente !");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }
    }

    public void deleteAlmacena(int idBodega, int idProducto) {
        try {
            if (conn == null) {
                conn = ConexionDB.getConexion();
            }
            String sql = "DELETE FROM almacena WHERE idbodega = ? AND idproducto = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, idBodega);
            statement.setInt(2, idProducto);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "El registro fue borrado exitosamente !");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Código : " + e.getErrorCode() + "\nError :" + e.getMessage());
        }

    }
}
