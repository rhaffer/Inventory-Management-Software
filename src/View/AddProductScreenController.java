package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AddProductScreenController {

    @FXML
    public void cancelButton(ActionEvent actionEvent) throws IOException {
        Parent main_screen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene main_screen_stage = new Scene(main_screen);
        Stage app_stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(main_screen_stage);
        app_stage.show();
    }
}
