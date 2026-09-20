package app;

import consoleMenu.*;
import exception.file.FileDataException;
import repository.formula.*;
import repository.ingredient.*;
import repository.trial.*;
import service.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Ice Cream Trial Management System");
        System.out.println("Application started...");

        // Repository
        IFIngredientRepository ingredientRepository =
                new TextIngredientRepository("src/data/ingredients.txt");

        IFFormulaRepository formulaRepository =
                // FIXED: actual file name is formula.txt (singular), not formulas.txt
                new TextFormulaRepository("src/data/formula.txt");

        IFTrialRepository trialRepository =
                new TextTrialRepository("src/data/trials.txt");


        // Service
        IngredientService ingredientService = new IngredientService(ingredientRepository);
        FormulaService formulaService = new FormulaService(formulaRepository);
        TrialService trialService = new TrialService(trialRepository);
        SensoryEvaluationService sensoryEvaluationService = new SensoryEvaluationService();


        // Load data
        try {
            ingredientService.loadData();

            formulaService.loadData(ingredientService.getAllIngredients());

            trialService.loadData(formulaService.getAllFormulas());

        } catch (FileDataException e) {
            System.out.println("File error: " + e.getMessage());
            return;
        }


        // Scanner dùng chung cho tất cả menu
        Scanner scanner = new Scanner(System.in);


        // Functional menus
        AddIngredientMenu addIngredientMenu =
                new AddIngredientMenu(scanner, ingredientService);

        AddFormulaMenu addFormulaMenu =
                new AddFormulaMenu(scanner, ingredientService, formulaService);

        AddTrialMenu addTrialMenu =
                new AddTrialMenu(scanner, formulaService, trialService);

        AddSensoryEvaluationMenu addSensoryEvaluationMenu =
                new AddSensoryEvaluationMenu(
                        scanner,
                        trialService,
                        sensoryEvaluationService
                );

        SearchMenu searchMenu =
                new SearchMenu(
                        scanner,
                        ingredientService,
                        formulaService,
                        trialService,
                        sensoryEvaluationService
                );

        TrialReportMenu trialReportMenu = new TrialReportMenu(trialService);

        SaveDataMenu saveDataMenu =
                new SaveDataMenu(
                        ingredientService,
                        formulaService,
                        trialService
                );


        // Main menu
        MainMenu mainMenu =
                new MainMenu(
                        scanner,
                        addIngredientMenu,
                        addFormulaMenu,
                        addTrialMenu,
                        addSensoryEvaluationMenu,
                        searchMenu,
                        trialReportMenu,
                        saveDataMenu
                );

        mainMenu.start();

        scanner.close();
    }
}