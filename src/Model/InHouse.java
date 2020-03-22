package Model;

public class InHouse extends Part{
    private int machineId;
    public InHouse(int newID, String newName, double newPrice, int newStock, int newMin, int newMax, int newMachineID){
        super(newID, newName, newPrice, newStock, newMin, newMax);
        this.machineId = newMachineID;
    }

    public void setMachineId(int newMachineID){
        this.machineId = newMachineID;
    }

    public int getMachineId(){
        return this.machineId;
    }

}
