package repository.ingredient;

import exception.file.FileDataException;
import model.ingredient.AdditiveIngredient;
import model.ingredient.DairyIngredient;
import model.ingredient.FatIngredient;
import model.ingredient.Ingredient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import util.IngredientFactory;

public class TextIngredientRepository implements IFIngredientRepository{
    private String filePath;

    public TextIngredientRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override 
    public void save(List<Ingredient> ingredients) throws FileDataException{

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {

            for (Ingredient ingredient : ingredients) {
                
                if (ingredient instanceof DairyIngredient) {
                    DairyIngredient dairy = (DairyIngredient) ingredient;
                    writer.println("DAIRY|" 
                                    + dairy.getId() + "|"
                                    + dairy.getName() + "|"
                                    + dairy.getUnit() + "|"
                                    + dairy.getPrice() + "|"
                                    + dairy.getProteinPercent() + "|"
                                    + dairy.getFatPercent()
                    );
                } else if (ingredient instanceof FatIngredient) {
                    FatIngredient fat = (FatIngredient) ingredient;
                    writer.println("FAT|" 
                                        + fat.getId() + "|" 
                                        + fat.getName() + "|" 
                                        + fat.getUnit() + "|"
                                        + fat.getPrice() + "|"
                                        + fat.getFatPercent() + "|"
                                        + fat.getFatSource() + "|"
                    );
                } else if (ingredient instanceof AdditiveIngredient) {
                    AdditiveIngredient additive = (AdditiveIngredient) ingredient;
                    writer.println("ADDITIVE|" 
                                    + additive.getId() + "|"
                                    + additive.getName() + "|"
                                    + additive.getUnit() + "|"
                                    + additive.getPrice() + "|"
                                    + additive.getFunction() + "|"
                                    + additive.getRecommendedDosage()
                    );
                }
            }

        } catch (IOException e) {
            throw new FileDataException("Cannot save ingredient file");
        }
    }

    @Override 
    public List<Ingredient> load() throws FileDataException{
        List<Ingredient> ingredients = new ArrayList<>();

        File file = new File(filePath);

        if(!file.exists()) {
            return ingredients;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                Ingredient ingredient = IngredientFactory.createIngredient(data);
              
                ingredients.add(ingredient);
            }

            return ingredients;
            
        } catch (IOException | RuntimeException e) {
            throw new FileDataException("Cannot load ingredient file");
        }
    }
    
}
