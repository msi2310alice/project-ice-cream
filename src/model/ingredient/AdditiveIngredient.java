package model.ingredient;

public class AdditiveIngredient extends Ingredient{
    private String function;
    private double recommendedDosage;

    public AdditiveIngredient(String id, String name, String unit, double price, String function, double recommendedDosage) {
        super(id, name, unit, price);
        this.function = function;
        this.recommendedDosage = recommendedDosage;
    }

    public String getFunction() {
        return function;
    }
    public double getRecommendedDosage() {
        return recommendedDosage;
    }

    public void setFunction(String function){
        this.function = function;
    }
    public void setRecommendedDosage(double recommendedDosage) {
        this.recommendedDosage = recommendedDosage;
    }

    @Override 
    public String toString() {
        return "AdditiveIngredient{ " +
                "id= " + getId() +
                ", name= " + getName() +
                ", unit= " + getUnit() +
                ", price= " + getPrice() +
                ", function= " + getFunction() +
                ", recommendedDosage= " + getRecommendedDosage() + 
                "}";
    }
}
