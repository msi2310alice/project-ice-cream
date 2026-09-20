package consoleMenu;

import java.util.Scanner;

import exception.base.IceCreamException;
import model.ingredient.AdditiveIngredient;
import model.ingredient.DairyIngredient;
import model.ingredient.FatIngredient;
import model.ingredient.Ingredient;
import service.IngredientService;

public class AddIngredientMenu {
    private final Scanner scanner;
    private final IngredientService ingredientService;

    public AddIngredientMenu(
            Scanner scanner,
            IngredientService ingredientService) {

        this.scanner = scanner;
        this.ingredientService = ingredientService;
    }

    public void execute() {

        try {

            System.out.println("===== ADD INGREDIENT =====");
            System.out.println("1. Dairy");
            System.out.println("2. Fat");
            System.out.println("3. Additive");

            System.out.print("Choose type: ");
            int type = Integer.parseInt(scanner.nextLine());

            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Unit: ");
            String unit = scanner.nextLine();

            System.out.print("Price: ");
            double price =
                    Double.parseDouble(scanner.nextLine());

            Ingredient ingredient;

            switch (type) {

                case 1:
                    System.out.print("Protein %: ");
                    double proteinPercent = Double.parseDouble(scanner.nextLine());

                    System.out.print("Fat %: ");
                    double dairyFatPercent = Double.parseDouble(scanner.nextLine());

                    ingredient = new DairyIngredient(
                            id,
                            name,
                            unit,
                            price,
                            proteinPercent,
                            dairyFatPercent
                    );
                    break;

                case 2:
                    System.out.print("Fat %: ");
                    double fatPercent = Double.parseDouble(scanner.nextLine());

                    System.out.print("Fat source: ");
                    String fatSource = scanner.nextLine();

                    ingredient = new FatIngredient(
                            id,
                            name,
                            unit,
                            price,
                            fatPercent,
                            fatSource
                    );
                    break;

                case 3:
                    System.out.print("Function: ");
                    String function = scanner.nextLine();

                    System.out.print("Recommended dosage: ");
                    double recommendedDosage = Double.parseDouble(scanner.nextLine());

                    ingredient = new AdditiveIngredient(
                            id,
                            name,
                            unit,
                            price,
                            function,
                            recommendedDosage
                    );
                    break;

                default:
                    System.out.println("Invalid ingredient type.");
                    return;
            }

            ingredientService.addIngredient(ingredient);

            System.out.println("Ingredient added successfully.");

        } catch (IceCreamException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid number."
            );
        }
    }

}
