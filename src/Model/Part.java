package Model;

public abstract class Part {
    private static int count = 0;
    private int partID;
    private String partName;
    private double partPrice;
    private int partStock;
    private int min;
    private int max;


    public Part(String newName, double newPrice, int newStock, int newMin, int newMax){
        setID();
        setPartName(newName);
        setPartPrice(newPrice);
        setPartStock(newStock);
        setMin(newMin);
        setMax(newMax);
    }

    public void setID(){
        partID = count++;
    }

    public void setPartName(String newName){
        partName = newName;
    }

    public void setPartPrice(double newPrice){
        partPrice = newPrice;
    }

    public void setPartStock(int newStock){
        partStock = newStock;
    }

    public void setMin(int newMin){
        min = newMin;
    }

    public void setMax(int newMax){
        max = newMax;
    }

    public int getPartID(){
        return this.partID;
    }

    public String getPartName(){
        return this.partName;
    }

    public double getPartPrice(){
        return this.partPrice;
    }

    public int getPartStock(){
        return this.partStock;
    }

    public int getMin(){
        return this.min;
    }

    public int getMax(){
        return this.max;
    }
}
