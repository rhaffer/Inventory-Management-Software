package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartScreenController implements Initializable {

    @FXML
    public TextField modifyPartVaryingTextField;
    @FXML
    public Label modifyPartVaryingLabel;
    @FXML
    public RadioButton modifyPartOutSourced;
    @FXML
    private RadioButton modifyPartInHouse;

    @FXML
    public void processRadioButton() {
        if (modifyPartInHouse.isSelected()){
            modifyPartVaryingLabel.setText("Machine ID");
            modifyPartVaryingTextField.setText("#Machine ID");
        }else{
            modifyPartVaryingLabel.setText("Company Name");
            modifyPartVaryingTextField.setText("#Company Name");
        }
    }
    @FXML
    public void cancelButton(ActionEvent actionEvent) throws IOException {
        Parent main_screen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene main_screen_stage = new Scene(main_screen);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(main_screen_stage);
        app_stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
