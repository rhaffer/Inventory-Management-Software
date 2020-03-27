package View;

import Model.InHouse;
import Model.Inventory;
import Model.OutSourced;
import Model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPartScreenController implements Initializable {
    Inventory inv;

    AddPartScreenController(Inventory inv){this.inv = inv;}

    @FXML
    private TextField addPartNameTextField;

    @FXML
    private TextField addPartInventoryTextField;

    @FXML
    private TextField addPartPriceTextField;

    @FXML
    private TextField addPartMinTextField;

    @FXML
    private TextField addPartMaxTextField;

    @FXML
    private TextField addPartVaryingTextField;
    @FXML
    private Label addPartVaryingLabel;
    @FXML
    private RadioButton AddPartOutSourced;
    @FXML
    ToggleGroup ModifyPartType;
    @FXML
    RadioButton addPartInHouse;

    @FXML
    public void processRadiobuttons(){
        if (addPartInHouse.isSelected()){
            addPartVaryingLabel.setText("Machine ID");
            addPartVaryingTextField.setPromptText("#Machine ID");
        }else{
            addPartVaryingLabel.setText("Company Name");
            addPartVaryingTextField.setPromptText(("#Company Name"));
        }
    }

    @FXML
    public void cancelButton(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/MainScreen.fxml"));
        loader.setController(new View.MainScreenController(inv));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @FXML
    public void saveButton(MouseEvent event) throws IOException{
        Part newPart;
        String newPartName = addPartNameTextField.getText();
        double newPartPrice = new Double(addPartPriceTextField.getText());
        int newPartInventory = new Integer(addPartInventoryTextField.getText());
        int newPartMin = new Integer(addPartMinTextField.getText());
        int newPartMax = new Integer(addPartMaxTextField.getText());

        if (addPartVaryingLabel.getText().equals("Machine ID")){
            newPart = new InHouse(newPartName, newPartPrice, newPartInventory, newPartMin, newPartMax,
                    new Integer(addPartVaryingTextField.getText()));
        }else{
            newPart = new OutSourced(newPartName, newPartPrice, newPartInventory, newPartMin, newPartMax,
                    addPartVaryingTextField.getText());
        }
        inv.addPart(newPart);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, newPart.getPartName() + " saved successfully.");
        alert.setHeaderText("Saved Successfully!");
        alert.setTitle("Save Successful");
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
