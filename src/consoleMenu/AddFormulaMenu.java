package consoleMenu;

import exception.base.IceCreamException;
import model.formula.Formula;
import model.formula.FormulaItem;
import model.ingredient.Ingredient;
import service.FormulaService;
import service.IngredientService;

import java.util.Scanner;

public class AddFormulaMenu {

    private final Scanner scanner;
    private final IngredientService ingredientService;
    private final FormulaService formulaService;

    public AddFormulaMenu(
            Scanner scanner,
            IngredientService ingredientService,
            FormulaService formulaService) {

        this.scanner = scanner;
        this.ingredientService = ingredientService;
        this.formulaService = formulaService;
    }

    public void execute() {

        try {

            System.out.println("===== ADD FORMULA =====");

            System.out.print("Formula ID: ");
            String idFormula = scanner.nextLine();

            System.out.print("Formula name: ");
            String nameFormula = scanner.nextLine();

            Formula formula = new Formula(idFormula, nameFormula);

            while (true) {

                System.out.print("Ingredient ID (0 to finish): ");

                String idIngredient = scanner.nextLine();

                if (idIngredient.equals("0")) {
                    break;
                }

                Ingredient ingredient =ingredientService.findIngredientById(idIngredient);

                if (ingredient == null) {

                    System.out.println("Ingredient not found.");

                    continue;
                }

                System.out.print("Amount: ");

                double amount =Double.parseDouble(scanner.nextLine());

                FormulaItem formulaItem = new FormulaItem(ingredient, amount);

                formula.addItem(formulaItem);
            }

            formulaService.addFormula(formula);

            System.out.println("Formula added successfully.");

        } catch (IceCreamException e) {

            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Invalid number.");
        }
    }
}