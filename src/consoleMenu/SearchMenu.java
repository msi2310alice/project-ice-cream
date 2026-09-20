package consoleMenu;

import exception.base.IceCreamException;
import model.trial.Trial;
import service.FormulaService;
import service.IngredientService;
import service.SensoryEvaluationService;
import service.TrialService;

import java.util.Scanner;

public class SearchMenu {

    private final Scanner scanner;
    private final IngredientService ingredientService;
    private final FormulaService formulaService;
    private final TrialService trialService;
    private final SensoryEvaluationService sensoryEvaluationService;

    public SearchMenu(
            Scanner scanner,
            IngredientService ingredientService,
            FormulaService formulaService,
            TrialService trialService,
            SensoryEvaluationService sensoryEvaluationService) {

        this.scanner = scanner;
        this.ingredientService = ingredientService;
        this.formulaService = formulaService;
        this.trialService = trialService;
        this.sensoryEvaluationService = sensoryEvaluationService;
    }

    public void execute() {

        try {
            System.out.println("===== SEARCH =====");
            System.out.println("1. Ingredient");
            System.out.println("2. Formula");
            System.out.println("3. Trial");
            System.out.println("4. Sensory Evaluation");

            System.out.print("Choose: ");
            int objectType = Integer.parseInt(scanner.nextLine());

            System.out.println("1. Search by ID");
            System.out.println("2. Search by name");

            System.out.print("Choose search type: ");
            int searchType = Integer.parseInt(scanner.nextLine());

            switch (objectType) {
                case 1:
                    searchIngredient(searchType);
                    break;

                case 2:
                    searchFormula(searchType);
                    break;

                case 3:
                    searchTrial(searchType);
                    break;

                case 4:
                    searchSensoryEvaluation(searchType);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } catch (IceCreamException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    private void searchIngredient(int searchType) throws IceCreamException {

        if (searchType == 1) {
            System.out.print("Ingredient ID: ");
            String id = scanner.nextLine();

            System.out.println(ingredientService.findIngredientById(id));

        } else if (searchType == 2) {
            System.out.print("Ingredient name: ");
            String name = scanner.nextLine();

            ingredientService.findIngredientsByName(name).forEach(System.out::println);

        } else {
            System.out.println("Invalid search type.");
        }
    }

    private void searchFormula(int searchType) throws IceCreamException {

        if (searchType == 1) {
            System.out.print("Formula ID: ");
            String idFormula = scanner.nextLine();

            System.out.println(formulaService.findFormulaById(idFormula));

        } else if (searchType == 2) {
            System.out.print("Formula name: ");
            String nameFormula = scanner.nextLine();

            formulaService.findFormulasByName(nameFormula).forEach(System.out::println);

        } else {
            System.out.println("Invalid search type.");
        }
    }

    private void searchTrial(int searchType) throws IceCreamException {

        if (searchType == 1) {
            System.out.print("Trial ID: ");
            String idTrial = scanner.nextLine();

            System.out.println(trialService.findTrialById(idTrial));

        } else if (searchType == 2) {
            System.out.print("Trial name: ");
            String nameTrial = scanner.nextLine();

            trialService.findTrialsByName(nameTrial).forEach(System.out::println);

        } else {
            System.out.println("Invalid search type.");
        }
    }

    private void searchSensoryEvaluation(int searchType) throws IceCreamException {

        System.out.print("Trial ID: ");
        String idTrial = scanner.nextLine();

        Trial trial = trialService.findTrialById(idTrial);

        if (trial == null) {
            System.out.println("Trial not found.");
            return;
        }

        if (searchType == 1) {
            System.out.print("Evaluation ID: ");
            String idEvaluation = scanner.nextLine();

            System.out.println(
                    sensoryEvaluationService.findSensoryEvaluationById(trial, idEvaluation)
            );

        } else if (searchType == 2) {
            System.out.print("Evaluator name: ");
            String nameEvaluator = scanner.nextLine();

            sensoryEvaluationService
                    .findSensoryEvaluationsByName(trial, nameEvaluator)
                    .forEach(System.out::println);

        } else {
            System.out.println("Invalid search type.");
        }
    }
}