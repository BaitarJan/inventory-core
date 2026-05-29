package com.inventory.model;

public class Product {

    private Integer id;
    private String name;
    private String barcode;
    private String eanCode;
    private String unit;

    public Product(Integer id, String name, String barcode, String eanCode, String unit) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.eanCode = eanCode;
        this.unit = unit;
    }

    public Product(String name, String barcode, String eanCode, String unit) {
        this.name = name;
        this.barcode = barcode;
        this.eanCode = eanCode;
        this.unit = unit;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getBarcode() { return barcode; }
    public String getEanCode() { return eanCode; }
    public String getUnit() { return unit; }
}