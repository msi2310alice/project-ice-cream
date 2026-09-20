package model.ingredient;

public class DairyIngredient extends Ingredient{
    private double proteinPercent;
    private double fatPercent;

    public DairyIngredient(String id, String name, String unit, double price, double proteinPercent, double fatPercent) {
        super(id, name, unit, price);
        this.proteinPercent = proteinPercent;
        this.fatPercent = fatPercent;
    }

    public double getProteinPercent() {
        return proteinPercent;
    }
    public double getFatPercent() {
        return fatPercent;
    }

    public void setProteinPercent(double proteinPercent) {
        this.proteinPercent = proteinPercent;
    }
    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
    }

    @Override 
    public String toString() {
        return "DairyIngredient{" +
                "id= " + getId() + 
                ", name= " + getName() + 
                ", unit= " + getUnit() +
                ", price= " + getPrice() +
                ", proteinPercent= " + getProteinPercent() +
                ", fatPercent =" + getFatPercent() +
                "}";
    }
}
