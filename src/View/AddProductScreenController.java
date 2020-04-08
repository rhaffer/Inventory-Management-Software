package View;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AddProductScreenController implements Initializable {
    Inventory inv;
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    AddProductScreenController (Inventory inv){this.inv = inv;}

    @FXML
    private TextField addProductID;

    @FXML
    private TextField addProductName;

    @FXML
    private TextField addProductInventory;

    @FXML
    private TextField addProductPrice;

    @FXML
    private TextField addProductMin;

    @FXML
    private TextField addProductMax;

    @FXML
    private TextField addProductSearch;

    @FXML
    private TableView<Part> allPartsTableView;

    @FXML
    private TableColumn<Part, Integer> allPartsIDColumn;

    @FXML
    private TableColumn<Part, String> allPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> allPartsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> allPartsCostColumn;

    @FXML
    private TableView<Part> associatedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;

    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartsCostColumn;


    private void generateAllPartsTable(){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        allParts.setAll(inv.getAllParts());
        allPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        allPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        allPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        allPartsCostColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        allPartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        allPartsTableView.setItems(allParts);
        allPartsTableView.refresh();
    }

    private void generateAssociatedPartsTable(){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        associatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        associatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        associatedPartsCostColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        associatedPartsCostColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        associatedPartsTableView.setItems(associatedParts);
        associatedPartsTableView.refresh();
    }

    @FXML
    public void cancelButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/MainScreen.fxml"));
        loader.setController(new View.MainScreenController(inv));
        Parent root = loader.load();
        Scene scene = new Scene (root);
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    public void addProductSaveButton(MouseEvent event) throws IOException{
        Product newProduct = null;
        int totalCost = 0;
        for (Part part: associatedParts){
            totalCost += part.getPartPrice();
        }

        try {
            String productName = addProductName.getText();
            int productInventory = new Integer(addProductInventory.getText());
            double productPrice = new Double(addProductPrice.getText());
            if (productPrice < totalCost){
                throw new Exception();
            }
            int productMin = new Integer(addProductMin.getText());
            int productMax = new Integer(addProductMax.getText());
            if (productMin > productMax){
                throw new Exception();
            }
            if (productMin > productInventory || productMax < productInventory){
                throw new Exception();
            }
            newProduct = new Product(productName, productPrice, productInventory, productMin, productMax);
            newProduct.setAssociatedParts(associatedParts);
        }catch (Exception e){
            Alert nullAlert = new Alert(Alert.AlertType.ERROR, "Please validate all fields");
            nullAlert.setHeaderText("Error in form");
            nullAlert.showAndWait();
        }

        assert newProduct != null;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save " + newProduct.getProductName() + "?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("Save Confirmation");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES){
            if (newProduct.getAllAssociatedParts().size() == 0) {
                Alert partAlert = new Alert(Alert.AlertType.ERROR, "Products must have at least one part.");
                partAlert.setHeaderText("Error Saving Product");
                partAlert.showAndWait();
            }else{
                inv.addProduct(newProduct);
                Alert saveConfirm = new Alert(Alert.AlertType.INFORMATION, newProduct.getProductName() + " saved successfully");
                saveConfirm.setHeaderText("Saved Successfully");
                saveConfirm.setTitle("Save Successful");
                saveConfirm.showAndWait();
            }
        }else{
            alert.close();
        }
    }

    @FXML
    public void addProductAddButton(MouseEvent event) throws IOException{
        Part selectedPart = allPartsTableView.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product.");
            alert.setHeaderText("Error choosing Product!");
            alert.showAndWait();
        }else{
            if (associatedParts.contains(selectedPart)){
                Alert alert = new Alert(Alert.AlertType.ERROR, selectedPart.getPartName() + " is already an associated part.");
                alert.setHeaderText("Error Adding Part");
                alert.showAndWait();
            }else{
                associatedParts.add(selectedPart);
                associatedPartsTableView.refresh();
            }
        }
    }

    @FXML
    public void addProductDeleteButton(MouseEvent event) throws IOException{
        Part selectedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product.");
            alert.setHeaderText("Error choosing Product!");
            alert.showAndWait();
        }else{
            Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + selectedPart.getPartName() + "?", ButtonType.YES, ButtonType.CANCEL);
            newAlert.setHeaderText("Confirm Delete");
            newAlert.showAndWait();

            if (newAlert.getResult() == ButtonType.YES) {
                associatedParts.remove(selectedPart);
                associatedPartsTableView.refresh();
            }else{
                newAlert.close();
            }

        }
    }

    @FXML
    public void addProductSearchButton(MouseEvent event) throws IOException{
        ObservableList<Part> results = FXCollections.observableArrayList();
        String searchPart = addProductSearch.getText().toLowerCase();
        if (searchPart.equals("")) {
            allPartsTableView.setItems(allParts);
            allPartsTableView.refresh();
        } else {
            Part resultPart = inv.lookupPart(searchPart);
            if (resultPart == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, searchPart + " is not found.");
                alert.setHeaderText("Item not found!");
                alert.showAndWait();
            }else {
                results.add(resultPart);
                allPartsTableView.setItems(results);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateAllPartsTable();
        generateAssociatedPartsTable();
    }
}
