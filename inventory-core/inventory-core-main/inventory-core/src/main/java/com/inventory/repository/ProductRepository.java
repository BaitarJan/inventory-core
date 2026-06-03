package com.inventory.repository;

import com.inventory.model.Product;

public interface ProductRepository {
    int save(Product product);

    Product findByBarcode(String barcode);
}
