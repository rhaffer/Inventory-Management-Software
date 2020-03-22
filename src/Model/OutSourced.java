package Model;

public class OutSourced extends Part{
    private String companyName;

    public OutSourced(int newID, String newName, double newPrice, int newStock, int newMin, int newMax, String newCompanyName){
        super(newID, newName, newPrice, newStock, newMin, newMax);
        companyName = newCompanyName;
    }

    public void setCompanyName(String newCompanyName){
        this.companyName = newCompanyName;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
