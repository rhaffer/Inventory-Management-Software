package Model;

import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Product(int newId, String newName, double newPrice, int newStock, int newMin, int newMax){
        this.id = newId;
        this.name = newName;
        this.price = newPrice;
        this.stock = newStock;
        this.min = newMin;
        this.max = newMax;
    }

    public void setId(int newID){
        this.id = newID;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setStock(int newStock){
        this.stock = newStock;
    }

    public void setMin(int newMin){
        this.min = newMin;
    }

    public void setMax(int newMax){
        this.max = newMax;
    }

    public void addAssociatedPart(Part newPart){ associatedParts.add(newPart); }

    public boolean deleteAssociatedPart(Part selectedPart){
        // TODO Add functionality
        return false;
    }

    public ObservableList<Part> getAllAssociatedParts(){ return associatedParts; }


}
