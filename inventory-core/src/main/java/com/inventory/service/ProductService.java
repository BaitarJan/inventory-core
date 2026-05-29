package com.inventory.service;


import com.inventory.model.Product;
import com.inventory.repository.InventoryRepository;
import com.inventory.repository.ProductRepository;

public class ProductService {

    private ProductRepository productRepository;
    private InventoryRepository inventoryRepository;

    public ProductService(ProductRepository productRepository,
                          InventoryRepository inventoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public void createProduct(String name, String barcode, String eanCode, String unit) {

        Product product = new Product(name, barcode, eanCode, unit);

        int productId = productRepository.save(product);

        inventoryRepository.createInventory(productId);

        System.out.println("Product created with inventory.");
    }
}