package com.example.ioughffdiug;

import eu.hansolo.toolbox.properties.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Part {
    private final StringProperty name;
    private final StringProperty category;
    private final DoubleProperty price;
    private final StringProperty brand;
    private final StringProperty formattedPrice;

    public Part(String name, String category, double price, String brand) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.price = new DoubleProperty(price);
        this.brand = new SimpleStringProperty(brand);
        this.formattedPrice = new SimpleStringProperty(String.format("$%.2f", price));
    }

    // Геттеры свойств
    public StringProperty nameProperty() { return name; }
    public StringProperty categoryProperty() { return category; }
    public DoubleProperty priceProperty() { return price; }
    public StringProperty brandProperty() { return brand; }
    public StringProperty formattedPriceProperty() { return formattedPrice; }

    // Стандартные геттеры
    public String getName() { return name.get(); }
    public String getCategory() { return category.get(); }
    public double getPrice() { return price.get(); }
    public String getBrand() { return brand.get(); }
    public String getFormattedPrice() { return formattedPrice.get(); }
}