package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.InventoryRepository;
import com.inventory.repository.ProductRepository;

public class InventoryService {

    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;

    public InventoryService(ProductRepository productRepository,
                            InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void stockIn(String barcode, double quantity) {

        Product product = productRepository.findByBarcode(barcode);

        if (product == null) {
            System.out.println("Product not found");
            return;
        }

        inventoryRepository.addStock(product.getId(), quantity);

        System.out.println("Stock added");
    }
}