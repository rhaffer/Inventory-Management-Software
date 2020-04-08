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

public class ModifyProductScreenController implements Initializable {
    Inventory inv;
    int index;
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    ModifyProductScreenController(Inventory inventory, int index){
        this.inv = inventory;
        this.index = index;
    }

    @FXML
    private TextField modifyProductID;

    @FXML
    private TextField modifyProductName;

    @FXML
    private TextField modifyProductInventory;

    @FXML
    private TextField modifyProductPrice;

    @FXML
    private TextField modifyProductMin;

    @FXML
    private TextField modifyProductMax;

    @FXML
    private TextField modifyProductSearch;

    @FXML
    private TableView<Part> modifyProductAllParts;

    @FXML
    private TableColumn<Part, Integer> allPartsIDColumn;

    @FXML
    private TableColumn<Part, String> allPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> allPartsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> allPartsPriceColumn;

    @FXML
    private TableView<Part> modifyProductAssociatedParts;

    @FXML
    private TableColumn<Part, Integer> associatedPartsIDColumn;

    @FXML
    private TableColumn<Part, String> associatedPartsNameColumn;

    @FXML
    private TableColumn<Part, Integer> associatedPartsInventoryColumn;

    @FXML
    private TableColumn<Part, Double> associatedPartsPriceColumn;

    @FXML
    public void modifyProductSearchButton(MouseEvent event) throws IOException{
        ObservableList<Part> results = FXCollections.observableArrayList();
        String searchPart = modifyProductSearch.getText().toLowerCase();
        if (searchPart.equals("")) {
            modifyProductAllParts.setItems(allParts);
            modifyProductAllParts.refresh();
        } else {
            Part resultPart = inv.lookupPart(searchPart);
            if (resultPart == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, searchPart + " is not found.");
                alert.setHeaderText("Item not found!");
                alert.showAndWait();
            }else {
                results.add(resultPart);
                modifyProductAllParts.setItems(results);
            }
        }

    }

    @FXML
    public void modifyProductAddButton(MouseEvent event) throws IOException{
        Part selectedPart = modifyProductAllParts.getSelectionModel().getSelectedItem();

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
                modifyProductAssociatedParts.refresh();
            }
        }
    }

    @FXML
    public void modifyProductDeleteButton(){
        Part selectedPart = modifyProductAssociatedParts.getSelectionModel().getSelectedItem();
        if(selectedPart == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select a product.");
            alert.setHeaderText("Error choosing Product!");
            alert.showAndWait();
        }else{
            associatedParts.remove(selectedPart);
            modifyProductAssociatedParts.refresh();
        }

    }

    @FXML
    public void modifyProductSaveButton(){
        Product product = inv.getAllProducts().get(index);
        int cur_id = product.getProductID();

        product.setAssociatedParts(associatedParts);
        product.setProductID(cur_id);
        product.setProductName(modifyProductName.getText());
        product.setProductPrice(Double.parseDouble(modifyProductPrice.getText()));
        product.setProductStock(Integer.parseInt(modifyProductInventory.getText()));
        product.setMin(Integer.parseInt(modifyProductMin.getText()));
        product.setMax(Integer.parseInt(modifyProductMax.getText()));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save " + product.getProductName() + "?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("Save Confirmation");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES){
            inv.updateProduct(index, product);
            Alert new_alert = new Alert(Alert.AlertType.INFORMATION, "Saved Successfully!");
            new_alert.setHeaderText("Saved Successfully!");
            new_alert.showAndWait();
        }else{
            alert.close();
        }
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

    public void generateAllParts(){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        allParts.setAll(inv.getAllParts());
        allPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        allPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        allPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        allPartsPriceColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        allPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        modifyProductAllParts.setItems(allParts);
        modifyProductAllParts.refresh();
    }

    public void generateAssociatedParts(){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        associatedPartsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partID"));
        associatedPartsNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        associatedPartsInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("partStock"));
        associatedPartsPriceColumn.setCellFactory(tc -> new TableCell<Part, Double>() {

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
        associatedPartsPriceColumn.setCellValueFactory(new PropertyValueFactory<>("partPrice"));
        modifyProductAssociatedParts.setItems(associatedParts);
        modifyProductAssociatedParts.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Product product = inv.getAllProducts().get(index);
        associatedParts.addAll(product.getAllAssociatedParts());
        modifyProductID.setText(Integer.toString(product.getProductID()));
        modifyProductName.setText(product.getProductName());
        modifyProductInventory.setText(Integer.toString(product.getProductStock()));
        modifyProductPrice.setText(Double.toString(product.getProductPrice()));
        modifyProductMin.setText(Integer.toString(product.getMin()));
        modifyProductMax.setText(Integer.toString(product.getMax()));

        generateAllParts();
        generateAssociatedParts();
    }
}
