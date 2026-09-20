package consoleMenu;

import exception.base.IceCreamException;
import service.FormulaService;
import service.IngredientService;
import service.TrialService;

public class SaveDataMenu {

    private final IngredientService ingredientService;
    private final FormulaService formulaService;
    private final TrialService trialService;

    public SaveDataMenu(
            IngredientService ingredientService,
            FormulaService formulaService,
            TrialService trialService) {

        this.ingredientService = ingredientService;
        this.formulaService = formulaService;
        this.trialService = trialService;
    }

    public void execute() {

        try {
            ingredientService.saveData();
            formulaService.saveData();
            trialService.saveData();

            System.out.println("Data saved successfully.");

        } catch (IceCreamException e) {
            System.out.println("Save error: " + e.getMessage());
        }
    }
}