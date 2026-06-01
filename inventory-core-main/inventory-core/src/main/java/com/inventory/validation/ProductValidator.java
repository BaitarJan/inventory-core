package com.inventory.validation;

public class ProductValidator {

    public boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    public boolean isValidBarcode(String barcode) {
        return barcode != null
                && !barcode.isBlank()
                && barcode.length() >= 3;
    }
}