package com.example.ioughffdiug;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MainController {
    @FXML private TabPane tabPane;
    @FXML private TableView<Part> partsTable;
    @FXML private TableView<Part> cartTable;
    @FXML private TextField searchField;
    @FXML private Label totalLabel;

    private final ObservableList<Part> partsCatalog = FXCollections.observableArrayList();
    private final ObservableList<Part> cartItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Блокировка закрытия вкладок
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        setupPartsTable();
        setupCartTable();
        loadSampleData();
    }

    private void setupPartsTable() {
        TableColumn<Part, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Part, String> categoryCol = new TableColumn<>("Категория");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Исправленная колонка цены
        TableColumn<Part, String> priceCol = new TableColumn<>("Цена");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("formattedPrice"));

        TableColumn<Part, String> brandCol = new TableColumn<>("Бренд");
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));

        partsTable.getColumns().setAll(nameCol, categoryCol, priceCol, brandCol);
        partsTable.setItems(partsCatalog);
    }

    private void setupCartTable() {
        TableColumn<Part, String> nameCol = new TableColumn<>("Название");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Исправленная колонка цены
        TableColumn<Part, String> priceCol = new TableColumn<>("Цена");
        priceCol.setCellValueFactory(new PropertyValueFactory<>("formattedPrice"));

        cartTable.getColumns().setAll(nameCol, priceCol);
        cartTable.setItems(cartItems);
    }

    private void loadSampleData() {
        partsCatalog.addAll(
                new Part("Масляный фильтр", "Фильтры", 15.99, "Bosch"),
                new Part("Тормозные колодки", "Тормоза", 45.99, "Brembo"),
                new Part("Воздушный фильтр", "Фильтры", 12.50, "Mann"),
                new Part("Аккумулятор", "Электрика", 120.00, "Varta")
        );
    }

    @FXML
    private void handleAddToCart() {
        Part selected = partsTable.getSelectionModel().getSelectedItem();
        if (selected != null && !cartItems.contains(selected)) {
            cartItems.add(selected);
            updateTotal();
        }
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().toLowerCase();
        if (query.isEmpty()) {
            partsTable.setItems(partsCatalog);
        } else {
            ObservableList<Part> filtered = partsCatalog.filtered(part ->
                    part.getName().toLowerCase().contains(query) ||
                            part.getCategory().toLowerCase().contains(query) ||
                            part.getBrand().toLowerCase().contains(query)
            );
            partsTable.setItems(filtered);
        }
    }

    @FXML
    private void handleCheckout() {
        if (cartItems.isEmpty()) {
            showAlert("Ошибка", "Корзина пуста!");
        } else {
            showAlert("Успех", "Заказ оформлен на сумму: " + getFormattedTotal());
            cartItems.clear();
            updateTotal();
        }
    }

    @FXML
    private void handleExit() {
        Stage stage = (Stage) partsTable.getScene().getWindow();
        stage.close();
    }

    private String getFormattedTotal() {
        return String.format("$%.2f", calculateTotal());
    }

    private double calculateTotal() {
        return cartItems.stream().mapToDouble(Part::getPrice).sum();
    }

    private void updateTotal() {
        totalLabel.setText("Итого: " + getFormattedTotal());
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}