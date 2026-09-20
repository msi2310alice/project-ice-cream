package util;

import model.ingredient.AdditiveIngredient;
import model.ingredient.DairyIngredient;
import model.ingredient.FatIngredient;
import model.ingredient.Ingredient;
import type.IngredientType;


public class IngredientFactory {
    
    public static Ingredient createIngredient(String[] data) {

        IngredientType type = IngredientType.valueOf(data[0]);

        String id = data[1];
        String name = data[2];
        String unit = data[3];

        double price = Double.parseDouble(data[4]);

        switch (type) {
            case DAIRY:
                return new DairyIngredient(
                                id, 
                                name, 
                                unit, 
                                price, 
                                Double.parseDouble(data[5]), 
                                Double.parseDouble(data[6])
                            );
            case FAT:
                return new FatIngredient(
                                id,
                                name,
                                unit,
                                price,
                                Double.parseDouble(data[5]),
                                data[6]
                        );
            case ADDITIVE:
                    return new AdditiveIngredient(
                                id,
                                name,
                                unit,
                                price,
                                data[5],
                                Double.parseDouble(data[6])
                        );
            default:
                throw new IllegalArgumentException(
                        "Unknown ingredient type: " + type
                );
                
        }
    }
}
