package Main;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application {


    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Inventory inv = new Inventory();
        addTestData(inv);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MainScreen.fxml"));
        View.MainScreenController controller = new View.MainScreenController(inv);
        loader.setController(controller);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addTestData(Inventory inv){
        //In House Parts
        Part a1 = new InHouse("Screw", 2.99, 10, 5, 100, 101);
        Part a2 = new InHouse("Nut", 3.50, 100, 5, 150, 120);
        Part a3 = new InHouse("Bench Top", 10, 25, 5, 100, 121);
        inv.addPart(a1);
        inv.addPart(a2);
        inv.addPart(a3);
        Part b1 = new OutSourced("Hex Nut", 1.50, 50, 10, 200, "Nuts and Bolts");
        Part b2 = new OutSourced("Screwdriver", 10.99, 12, 6, 20, "Home Improvement, LLC");
        Part b3 = new OutSourced("Nail", .1, 500, 350, 1000, "The Door Store");
        inv.addPart(b1);
        inv.addPart(b2);
        inv.addPart(b3);
        Product c1 = new Product("Bench", 113.4, 5, 2, 10);
        Product c2 = new Product("Entertainment Center", 75.99, 10, 2, 10);
        inv.addProduct(c1);
        inv.addProduct(c2);
    }
}




