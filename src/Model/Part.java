package Model;

abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    public Part(int newID, String newName, double newPrice, int newStock, int newMin, int newMax){
        id = newID;
        name = newName;
        price = newPrice;
        stock = newStock;
        min = newMin;
        max = newMax;
    }

    public void setId(int newID){
        id = newID;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public void setStock(int newStock){
        stock = newStock;
    }

    public void setMin(int newMin){
        min = newMin;
    }

    public void setMax(int newMax){
        max = newMax;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public int getStock(){
        return this.stock;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }
}
