package repository.formula;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.formula.Formula;
import model.formula.FormulaItem;
import model.ingredient.Ingredient;
import exception.file.FileDataException;

public class TextFormulaRepository implements IFFormulaRepository{
    private String filePath;

    public TextFormulaRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override 
    public void save(List<Formula> fomulas) throws FileDataException {
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            
            for (Formula formula : fomulas) {

                StringBuilder itemsData = new StringBuilder();

                for (FormulaItem item : formula.getItemsFormula()) {

                    if (itemsData.length() > 0) {
                        itemsData.append(",");
                    }

                    itemsData.append(item.getIngredient().getId()).
                            append(":").
                            append(item.getAmount());
                }

                writer.println(formula.getIdFormula() 
                                + "|" 
                                + formula.getNameFormula()
                                + "|" 
                                + itemsData);
            }

        } catch(IOException e) {
            throw new FileDataException("Cannot save formula file");
        }
    }

    @Override 
    public List<Formula> load(List<Ingredient> ingredients) throws FileDataException {
        List<Formula> formulas = new ArrayList<>();

        File file = new File(filePath);

        if(!file.exists()) {
            return formulas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] formulaData = line.split("\\|", -1);

                String idFormula = formulaData[0];
                String nameFormula = formulaData[1];

                Formula formula = new Formula(idFormula, nameFormula);

                if (formulaData.length > 2 && !formulaData[2].isBlank()) {
                    String[] itemsData = formulaData[2].split(",");

                    for (String item : itemsData) {
                        String[] parts = item.split(":");

                        String idIngredient = parts[0];
                        double amountIngredient = Double.parseDouble(parts[1]);

                        Ingredient ingredient = findIngredientInList(ingredients, idIngredient);

                        if (ingredient == null) {
                            throw new IllegalArgumentException("Ingredient not found: " + idIngredient);
                        }

                        FormulaItem formulaItem = new FormulaItem(ingredient, amountIngredient);

                        formula.addItem(formulaItem);
                    }
                }
                formulas.add(formula);
            }
            return formulas;

        } catch (IOException | RuntimeException e) {
            throw new FileDataException("Cannot load formula file");
        }
        
    }

    private Ingredient findIngredientInList(List<Ingredient> ingredients, String idIngredient) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId().equals(idIngredient)) {
                return ingredient;
            }
        }
        return null;
    }
}
