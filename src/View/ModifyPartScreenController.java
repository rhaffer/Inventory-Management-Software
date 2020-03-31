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

public class ModifyPartScreenController implements Initializable {
    Inventory inv;
    int index;

    ModifyPartScreenController(Inventory inv, int index){
        this.inv = inv;
        this.index = index;
    }

    @FXML
    private TextField modifyPartID;

    @FXML
    private TextField modifyPartName;

    @FXML
    private TextField modifyPartStock;

    @FXML
    private TextField modifyPartPrice;

    @FXML
    private TextField modifyPartMin;

    @FXML
    private TextField modifyPartMax;

    @FXML
    private TextField modifyPartVaryingTextField;

    @FXML
    private Label modifyPartVaryingLabel;

    @FXML
    private RadioButton modifyPartOutSourced;

    @FXML
    private RadioButton modifyPartInHouse;

    @FXML
    public void processRadioButton() {
        if (modifyPartInHouse.isSelected()){
            modifyPartVaryingLabel.setText("Machine ID");
            modifyPartVaryingTextField.setText(null);
            modifyPartVaryingTextField.setPromptText("#Machine ID");
        }else{
            modifyPartVaryingLabel.setText("Company Name");
            modifyPartVaryingTextField.setText(null);
            modifyPartVaryingTextField.setPromptText("#Company Name");
        }
    }

    @FXML
    public void saveButton(MouseEvent event) throws IOException {
        Part part = inv.getAllParts().get(index);
        int cur_id = part.getPartID();

        if (modifyPartInHouse.isSelected()){
            part = new InHouse();
            part.setID(cur_id);
            part.setPartName(modifyPartName.getText());
            part.setPartStock(Integer.parseInt(modifyPartStock.getText()));
            part.setPartPrice(Double.parseDouble(modifyPartPrice.getText()));
            part.setMin(Integer.parseInt(modifyPartMin.getText()));
            part.setMax(Integer.parseInt(modifyPartMax.getText()));
            ((InHouse) part).setMachineId(Integer.parseInt(modifyPartVaryingTextField.getText()));
        }
        if (modifyPartOutSourced.isSelected()){
            part = new OutSourced();
            part.setID(cur_id);
            part.setPartName(modifyPartName.getText());
            part.setPartStock(Integer.parseInt(modifyPartStock.getText()));
            part.setPartPrice(Double.parseDouble(modifyPartPrice.getText()));
            part.setMin(Integer.parseInt(modifyPartMin.getText()));
            part.setMax(Integer.parseInt(modifyPartMax.getText()));
            ((OutSourced) part).setCompanyName(modifyPartVaryingTextField.getText());
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Save " + part.getPartName() + "?", ButtonType.YES, ButtonType.CANCEL);
        alert.setHeaderText("Save Confirmation");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES){
            inv.updatePart(index, part);
            Alert new_alert = new Alert(Alert.AlertType.INFORMATION, "Saved successfully!");
            new_alert.setHeaderText("Saved successfully!");
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
        Scene scene = new Scene(root);
        Stage app_stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(scene);
        app_stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Part part = inv.getAllParts().get(index);
        modifyPartID.setText(Integer.toString(part.getPartID()));
        modifyPartName.setText(part.getPartName());
        modifyPartStock.setText(Integer.toString(part.getPartStock()));
        modifyPartPrice.setText(Double.toString(part.getPartPrice()));
        modifyPartMin.setText(Integer.toString(part.getMin()));
        modifyPartMax.setText(Integer.toString(part.getMax()));

        if (part instanceof InHouse){
            modifyPartInHouse.setSelected(true);
            processRadioButton();
            modifyPartVaryingTextField.setText(Integer.toString((((InHouse) part).getMachineId())));

        }

        if (part instanceof OutSourced){
            modifyPartOutSourced.setSelected(true);
            processRadioButton();
            modifyPartVaryingTextField.setText(((OutSourced) part).getCompanyName());
        }
        }
    }

