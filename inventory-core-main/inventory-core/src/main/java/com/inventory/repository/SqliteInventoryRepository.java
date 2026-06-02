package com.inventory.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqliteInventoryRepository implements InventoryRepository {

    private Connection conn;

    public SqliteInventoryRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void createInventory(int productId) {

        String sql = "INSERT INTO inventory(product_id, quantity) VALUES (?, 0)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStock(int productId, double quantity) {

        String sql = "UPDATE inventory SET quantity = quantity + ? WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, quantity);
            ps.setInt(2, productId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double getQuantity(int productId) {

        String sql = "SELECT quantity FROM inventory WHERE product_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public void removeStock(int productId, double quantity) {

        String sql =
                "UPDATE inventory " +
                        "SET quantity = quantity - ? " +
                        "WHERE product_id = ?";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setDouble(1, quantity);
            ps.setInt(2, productId);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

