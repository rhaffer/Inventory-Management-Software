package View;

import Model.Inventory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProductScreenController implements Initializable {
    Inventory inv;
    int index;

    ModifyProductScreenController(Inventory inventory, int index){
        this.inv = inventory;
        this.index = index;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
