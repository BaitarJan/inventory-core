package com.inventory.repository;

import com.inventory.model.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;


public class SqliteProductRepository implements ProductRepository {

    private Connection conn;

    public SqliteProductRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int save(Product product) {

        String sql = "INSERT INTO products(name, barcode, ean_code, unit) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getBarcode());
            ps.setString(3, product.getEanCode());
            ps.setString(4, product.getUnit());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Product findByBarcode(String barcode) {
        return null; // zatím později
    }
}

