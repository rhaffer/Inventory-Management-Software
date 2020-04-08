package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private static int productCount = 0;
    private int productID;
    private String productName;
    private double productPrice;
    private int productStock;
    private int min;
    private int max;

    public Product(String newName, double newPrice, int newStock, int newMin, int newMax){
        setProductID();
        setProductName(newName);
        setProductPrice(newPrice);
        setProductStock(newStock);
        setMin(newMin);
        setMax(newMax);
    }

    public void setProductID(){
        productID = productCount++;
    }

    public void setProductID(int id) {productID = id;}

    public void setProductName(String newName){
        productName = newName;
    }

    public void setProductPrice(double newPrice){
        productPrice = newPrice;
    }

    public void setProductStock(int newStock){
        productStock = newStock;
    }

    public void setMin(int newMin){
        min = newMin;
    }

    public void setMax(int newMax){
        max = newMax;
    }

    public void setAssociatedParts(ObservableList<Part> newPartList){ associatedParts = newPartList; }

    public int getProductID(){return this.productID;}

    public String getProductName(){return this.productName;}

    public double getProductPrice(){return this.productPrice;}

    public int getProductStock(){return this.productStock;}

    public int getMin(){return this.min;}

    public int getMax(){return this.max;}

    public void addAssociatedPart(Part newPart){ associatedParts.add(newPart); }

    public boolean deleteAssociatedPart(Part selectedPart){
        boolean deleted = false;
        for (int x = 0; x < associatedParts.size(); x++){
            if (associatedParts.get(x).getPartID() == selectedPart.getPartID()){
                associatedParts.remove(selectedPart);
                deleted = true;
            }
        }
        return deleted;
    }

    public ObservableList<Part> getAllAssociatedParts(){ return associatedParts; }


}
