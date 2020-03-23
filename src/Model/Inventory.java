package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    // TODO Add these functions back in
    /*public Part lookupPart(int partID){
        return new Part();
    }
    public Part lookupPart (String partName){
        return new Part();
    }

    public Product lookupProduct(int productID){
        return new Product();
    }

    public Product lookupProduct(String productName){
        return new Product();
    }*/

    public void updatePart(int index, Part selectedPart){
        // TODO Add Functionality
    }

    public void updateProduct(int index, Product selectedProduct){
        // TODO Add Functionality
    }

    public boolean deletePart(Part selectedPart){
        // TODO Add Functionality
        return true;
    }

    public boolean deleteProduct(Product selectedProduct){
        // TODO Add Functionality
        return true;
    }

    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
