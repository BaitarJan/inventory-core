package com.inventory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:inventory.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void init() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
            stmt.execute("""
                                CREATE TABLE IF NOT EXISTS products (
                                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                                    name TEXT NOT NULL,
                                    barcode TEXT UNIQUE NOT NULL,
                                    ean_code TEXT,
                                    unit TEXT NOT NULL
                                );
                    """);

            stmt.execute("""
                           CREATE TABLE IF NOT EXISTS inventory (
                               product_id INTEGER PRIMARY KEY,
                               quantity REAL NOT NULL DEFAULT 0 CHECK(quantity >= 0),
                               package_size REAL,
                               FOREIGN KEY(product_id) REFERENCES products(id)
                           );
                    """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void stock_movements() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
            stmt.execute("""
                               CREATE TABLE IF NOT EXISTS stock_movements (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,             
                                        product_id INTEGER NOT NULL,
                                        quantity REAL NOT NULL,
                                        type TEXT NOT NULL,                                   
                                        created_at TEXT NOT NULL                          
                                       
                                          );                                
                    
                                                    
                    """);

            stmt.execute("""
                           CREATE TABLE IF NOT EXISTS inventory (
                                id INTEGER PRIMARY KEY AUTOINCREMENT,             
                                        product_id INTEGER NOT NULL,
                                        quantity REAL NOT NULL CHECK(quantity >= 0),
                                        type TEXT NOT NULL,                                   
                                        created_at TEXT NOT NULL 
                                        FOREIGN KEY(product_id) REFERENCES products(id)
                           );
                    """);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

