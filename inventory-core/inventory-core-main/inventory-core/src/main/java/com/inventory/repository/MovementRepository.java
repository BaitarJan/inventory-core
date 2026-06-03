
    package com.inventory.repository;

import com.inventory.model.StockMovement;

import java.util.List;

    public interface MovementRepository {

        void save(StockMovement movement);

        List<StockMovement> findAll();
    }

