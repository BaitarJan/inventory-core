package com.inventory.repository;


public interface InventoryRepository {

    void createInventory(int productId);

    void addStock(int productId, double quantity);

    double getQuantity(int productId);

    void removeStock(int productId, double quantity);
}