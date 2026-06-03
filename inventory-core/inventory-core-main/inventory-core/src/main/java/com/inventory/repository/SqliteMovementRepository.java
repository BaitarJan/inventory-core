package com.inventory.repository;

import com.inventory.model.StockMovement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteMovementRepository
        implements MovementRepository {

    private Connection conn;

    public SqliteMovementRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(StockMovement movement) {

        String sql =
                "INSERT INTO stock_movements " +
                        "(product_id, quantity, type, created_at) " +
                        "VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps =
                     conn.prepareStatement(sql)) {

            ps.setInt(1, movement.getProductId());
            ps.setDouble(2, movement.getQuantity());
            ps.setString(3, movement.getType().name());
            ps.setString(4,
                    movement.getCreatedAt().toString());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StockMovement> findAll() {
        return new ArrayList<>();
    }
}