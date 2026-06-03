package com.inventory.app;

import com.inventory.db.DatabaseManager;
import com.inventory.model.Unit;
import com.inventory.repository.InventoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.repository.SqliteInventoryRepository;
import com.inventory.repository.SqliteProductRepository;
import com.inventory.service.ProductService;
import com.inventory.service.InventoryService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DatabaseManager.init();

        try {
            Connection conn = DatabaseManager.connect();

            ProductRepository productRepository =
                    new SqliteProductRepository(conn);

            InventoryRepository inventoryRepository =
                    new SqliteInventoryRepository(conn);

            ProductService productService =
                    new ProductService(
                            productRepository,
                            inventoryRepository
                    );
            InventoryService inventoryService =
                    new InventoryService(
                            productRepository,
                            inventoryRepository
                    );
            Scanner sc = new Scanner(System.in);

            while (true) {

                System.out.println();
                System.out.println("=== INVENTORY ===");
                System.out.println("1 - Create Product");
                System.out.println("2 - Find Product");
                System.out.println("3 - Stock IN");
                System.out.println("0 - Exit");
                System.out.print("Choice: ");

                String choice = sc.nextLine();

                switch (choice) {

                    case "1" -> {

                        System.out.print("Name: ");
                        String name = sc.nextLine();

                        System.out.print("Barcode: ");
                        String barcode = sc.nextLine();

                        System.out.print("EAN: ");
                        String ean = sc.nextLine();

                        System.out.println("Select unit:");
                        System.out.println("1 - PCS");
                        System.out.println("2 - KG");
                        System.out.println("3 - L");
                        System.out.println("4 - BOX");
                        System.out.println("5 - PACK");

                        String input = sc.nextLine();

                        Unit unit;


                        switch (input) {

                            case "1" -> unit = Unit.PCS;
                            case "2" -> unit = Unit.KG;
                            case "3" -> unit = Unit.L;
                            case "4" -> unit = Unit.BOX;
                            case "5" -> unit = Unit.PACK;

                            default -> {
                                System.out.println("Invalid unit");
                                return;
                            }
                        }
                        productService.createProduct(
                                name,
                                barcode,
                                ean,
                                unit
                        );
                    }

                    case "2" -> {

                        System.out.print("Barcode: ");
                        String barcode = sc.nextLine();

                        var product =
                                productService.findByBarcode(barcode);

                        if (product == null) {
                            System.out.println("Product not found");
                        } else { System.out.println(product);
                        }
                    }
                    case "3" -> {

                        System.out.print("Barcode: ");
                        String barcode = sc.nextLine();

                        System.out.print("Quantity: ");
                        double quantity = Double.parseDouble(sc.nextLine());

                        inventoryService.stockIn(barcode, quantity);
                    }
                    case "0" -> {
                        System.out.println("Bye");
                        return;
                    }

                    default -> System.out.println("Invalid option");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}