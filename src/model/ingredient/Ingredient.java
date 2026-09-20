package model.ingredient;

public abstract class Ingredient {
    private String id;
    private String name;
    private String unit;
    private double price;

    public Ingredient(String id, String name, String unit, double price){
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUnit() {
        return unit;
    }
    public double getPrice() {
        return price;
    }

    // public void setId(String id) {
    //     this.id = id;
    // }
    public void setName(String name) {
        this.name = name;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Override 
    public String toString() {
        return "Ingredient{" + 
                "id= " + id +
                ", name= " + name + 
                ", unit= " + unit + 
                ", price= " + price +
                "}";
    }
}
