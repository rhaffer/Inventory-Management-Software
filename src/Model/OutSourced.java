package Model;

public class OutSourced extends Part{
    private String companyName;

    public OutSourced(String newName, double newPrice, int newStock, int newMin, int newMax, String newCompanyName){
        super(newName, newPrice, newStock, newMin, newMax);
        companyName = newCompanyName;
    }

    public void setCompanyName(String newCompanyName){
        this.companyName = newCompanyName;
    }

    public String getCompanyName(){
        return this.companyName;
    }
}
