package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    @FXML
    public void exitApplication() {System.exit(0);}

    @FXML
    public void openAddPartScreen(ActionEvent actionEvent) throws IOException {
        Parent add_page_parent = FXMLLoader.load(getClass().getResource("AddPartScreen.fxml"));
        Scene add_page_scene = new Scene(add_page_parent);
        Stage app_stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(add_page_scene);
        app_stage.show();
    }

    @FXML
    public void openModifyPartScreen(ActionEvent actionEvent) throws IOException {
        Parent modify_page_parent = FXMLLoader.load(getClass().getResource("ModifyPartScreen.fxml"));
        Scene modify_page_scene = new Scene(modify_page_parent);
        Stage app_stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(modify_page_scene);
        app_stage.show();
    }

    @FXML
    public void openAddProductScreen(ActionEvent actionEvent) throws IOException {
        Parent add_page_parent = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        Scene add_page_scene = new Scene(add_page_parent);
        Stage app_stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(add_page_scene);
        app_stage.show();
    }

    @FXML
    public void openModifyProductScreen(ActionEvent actionEvent) throws IOException {
        Parent add_page_parent = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
        Scene add_page_scene = new Scene(add_page_parent);
        Stage app_stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(add_page_scene);
        app_stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}
