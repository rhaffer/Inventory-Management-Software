package Model;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart){
        //TODO Add Functionality
    }

    public void addProduct(Product newProduct){
        //TODO Add Functionality
    }

    public Part lookupPart(int partID){
        /*
        TODO Add Functionality
         - By Part ID
        */
        return new Part();
    }

    public Part lookupPart (String partName){
        /*
        TODO Add Functionality
         - By part name
         */
        return new Part();
    }

    public Product lookupProduct(int productID){
        /*
        TODO Add Functionality
         - By Product ID
        */
        return new Product();
    }

    public Product lookupProduct(String productName){
        /*
        TODO Add Functionality
         - By product name
         */
        return new Product();
    }

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

    public void getAllParts(){
        /* TODO Add Functionality
            - Change to return type ObservableList<Part>
            - Return ObservableList<Part>
         */
    }

    public void getAllProducts(){
        /* TODO Add Functionality
            - Change to return type ObservableList<Product>
            - Return ObservableList<Product>
         */
    }

}
