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

    public Part lookupPart (String partName){
        Part result = null;
        for (Part allPart : allParts) {
            if (allPart.getPartName().toLowerCase().equals(partName.toLowerCase())) {
                result = allPart;
            }
        }
        return result;
    }


    public Product lookupProduct(String productName){
        Product result = null;
        for (Product allProduct: allProducts){
            if (allProduct.getProductName().toLowerCase().equals(productName.toLowerCase())){
                result = allProduct;
            }
        }
        return result;
    }


    public void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    public boolean deletePart(Part selectedPart){
        boolean deleted = false;
        for (int x = 0; x < allParts.size(); x++){
            if (allParts.get(x).getPartID() == selectedPart.getPartID()){
                allParts.remove(selectedPart);
                deleted = true;
            }
        }
        return deleted;
    }


    public boolean deleteProduct(Product selectedProduct){
        boolean deleted = false;
        for (int x = 0; x < allProducts.size(); x++){
            if (allProducts.get(x).getProductID() == selectedProduct.getProductID()){
                allProducts.remove(selectedProduct);
                deleted = true;
            }
        }
        return deleted;
    }

    public ObservableList<Part> getAllParts(){ return allParts; }

    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
