package model.formula;

import model.ingredient.Ingredient;

public class FormulaItem {
    private Ingredient ingredient;
    private double amount;

    public FormulaItem(Ingredient ingredient, double amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public double getAmount() {
        return amount;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
    public  void setAmount(double amount) {
        this.amount = amount;
    }

    @Override 
    public String toString() {
        return ingredient.getName()
                + ": " 
                + amount
                + " "
                + ingredient.getUnit();
    }
}
