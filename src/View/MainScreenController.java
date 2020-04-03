package View;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    Inventory inv;

    @FXML
    private Button mainSearchPartButton;

    @FXML
    private TextField mainSearchPartTextField;

    @FXML
    private Button getMainSearchProductButton;

    @FXML
    private TextField mainSearchProductTextField;

    @FXML
    private TableView<Part> mainPartTableView;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partStockColumn;

    @FXML
    private TableColumn<Part, Double> priceCostColumn;

    @FXML
    private TableView<Product> mainProductTableView;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productStockColumn;

    @FXML
    private TableColumn<Product, Double> productCostColumn;

    @FXML
    private Button mainAddPartButton;

    @FXML
    private Button mainModifyPartButton;

    @FXML
    private Button mainDeletePartButton;

    @FXML
    private Button mainSearchProductButton;

    @FXML
    private Button mainAddProductButton;

    @FXML
    private Button mainModifyProductButton;

    @FXML
    private Button mainDeleteProductButton;

    @FXML
    private Button exitApplicationButton;


    private ObservableList<Part> partInventory = FXCollections.observableArrayList();
    private ObservableList<Product> productInventory = FXCollections.observableArrayList();

    public MainScreenController(Inventory inv) {
        this.inv = inv;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generatePartsTable();
        generateProductsTable();
    }

    private void generatePartsTable() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        partInventory.setAll(inv.getAllParts());
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        priceCostColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        priceCostColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        mainPartTableView.setItems(partInventory);
        mainPartTableView.refresh();
    }

    private void generateProductsTable() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        productInventory.setAll(inv.getAllProducts());
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        productStockColumn.setCellValueFactory(new PropertyValueFactory<>("productStock"));
        productCostColumn.setCellFactory(tc -> new TableCell<Product, Double>() {

            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        productCostColumn.setCellValueFactory(new PropertyValueFactory<>("productPrice"));
        mainProductTableView.setItems(productInventory);
        mainProductTableView.refresh();

    }

    @FXML
    void exitApplication(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void openAddPartScreen(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddPartScreen.fxml"));
        loader.setController(new AddPartScreenController(inv));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openAddProductScreen(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/AddProductScreen.fxml"));
        loader.setController(new AddProductScreenController(inv));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void openModifyPartScreen(MouseEvent event) throws IOException{
        Part selectedPart = mainPartTableView.getSelectionModel().getSelectedItem();
        if (selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a part.");
            alert.setHeaderText("Error choosing part!");
            alert.showAndWait();
        }else {
            int partIndex = partInventory.indexOf(selectedPart);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyPartScreen.fxml"));
            loader.setController(new ModifyPartScreenController(inv, partIndex));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void openModifyProductScreen(MouseEvent event) throws IOException{
        Product selectedProduct = mainProductTableView.getSelectionModel().getSelectedItem();
        if(selectedProduct == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product.");
            alert.setHeaderText("Error choosing Product!");
            alert.showAndWait();
        }else{
            int productIndex = productInventory.indexOf(selectedProduct);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/View/ModifyProductScreen.fxml"));
            loader.setController(new ModifyProductScreenController(inv, productIndex));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void mainPartDeleteButton() {
        Part selectedItem = mainPartTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selectedItem.getPartName() + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("Confirm Deletion");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            if (inv.deletePart(selectedItem)) {
                Alert newAlert = new Alert(Alert.AlertType.INFORMATION, selectedItem.getPartName() + " deleted successfully.");
                newAlert.setHeaderText("Delete Successful!");
                newAlert.showAndWait();
            } else {
                Alert newAlert = new Alert(Alert.AlertType.INFORMATION, selectedItem.getPartName() + " not deleted successfully.");
                newAlert.setHeaderText("Delete Unsuccessful!");
                newAlert.showAndWait();
            }
        } else {
            alert.close();
        }
        partInventory.setAll(inv.getAllParts());
        mainPartTableView.setItems(partInventory);
    }

    @FXML
    void mainProductDeleteButton(){
        Product selectedProduct = mainProductTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selectedProduct.getProductName() + " ?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("Confirm Deletion");
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            if (inv.deleteProduct(selectedProduct)) {
                Alert newAlert = new Alert(Alert.AlertType.INFORMATION, selectedProduct.getProductName() + " deleted successfully.");
                newAlert.setHeaderText("Delete Successful!");
                newAlert.showAndWait();
            } else {
                Alert newAlert = new Alert(Alert.AlertType.INFORMATION, selectedProduct.getProductName() + " not deleted successfully.");
                newAlert.setHeaderText("Delete Unsuccessful!");
                newAlert.showAndWait();
            }
        } else {
            alert.close();
        }
        productInventory.setAll(inv.getAllProducts());
        mainProductTableView.setItems(productInventory);
    }

    @FXML
    void mainSearchPart() {
        ObservableList<Part> results = FXCollections.observableArrayList();
        String searchPart = mainSearchPartTextField.getText().toLowerCase();
        if (searchPart.equals("")) {
            mainPartTableView.setItems(partInventory);
            mainPartTableView.refresh();
        } else {
            Part resultPart = inv.lookupPart(searchPart);
            if (resultPart == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, searchPart + " is not found.");
                alert.setHeaderText("Item not found!");
                alert.showAndWait();
            }else {
                results.add(resultPart);
                mainPartTableView.setItems(results);
            }
        }
    }

    @FXML
    void mainSearchProduct(){
        ObservableList<Product> results = FXCollections.observableArrayList();
        String searchProduct = mainSearchProductTextField.getText().toLowerCase();
        if(searchProduct.equals("")){
            mainProductTableView.setItems(productInventory);
            mainPartTableView.refresh();
        }else{
            Product resultProduct = inv.lookupProduct(searchProduct);
            if (resultProduct == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, searchProduct + " is not found.");
                alert.setHeaderText("Item not found!");
                alert.showAndWait();
            }else{
                results.add(resultProduct);
                mainProductTableView.setItems(results);
            }
        }
    }
}

