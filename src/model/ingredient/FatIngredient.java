package model.ingredient;

public class FatIngredient extends Ingredient{
    private double fatPercent;
    private String fatSource;

    public FatIngredient(String id, String name, String unit, double price, double fatPercent, String fatSource){
        super(id, name, unit, price);
        this.fatPercent = fatPercent;
        this.fatSource = fatSource;
    }

    public double getFatPercent(){
        return fatPercent;
    }
    public String getFatSource(){
        return fatSource;
    }

    public void setFatPercent(double fatPercent) {
        this.fatPercent = fatPercent;
    }
    public void setFatSource(String fatSource) {
        this.fatSource = fatSource;
    }

    @Override 
    public String toString() {
        return "FatIngredient{ " + 
                "id= " + getId() +
                ", name= " + getName() +
                ", unit= " + getUnit() +
                ", price= " + getPrice() +
                ", fatPercent= " + getFatPercent() +
                ", fatSource= " + getFatSource() +
                "}";
    }
}
