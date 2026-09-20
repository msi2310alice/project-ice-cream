package generated;

import concurrency.EvaluationLog;
import concurrency.EvaluationTask;
import model.formula.Formula;
import model.ingredient.AdditiveIngredient;
import model.ingredient.DairyIngredient;
import model.ingredient.FatIngredient;
import model.ingredient.Ingredient;
import model.trial.SensoryEvaluation;
import model.trial.Trial;
import org.junit.jupiter.api.Test;
import service.SensoryEvaluationService;
import type.TrialStatus;
import util.IngredientFactory;
import util.ReportGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilityTest {

    @Test
    void ingredientFactory_shouldCreateAllTypes() {
        Ingredient dairy = IngredientFactory.createIngredient(
                new String[]{"DAIRY", "ING001", "Milk", "kg", "2.0", "3.0", "3.5"});
        Ingredient fat = IngredientFactory.createIngredient(
                new String[]{"FAT", "ING002", "Butter", "kg", "4.0", "80.0", "Dairy"});
        Ingredient additive = IngredientFactory.createIngredient(
                new String[]{"ADDITIVE", "ING003", "Stabilizer", "kg", "6.0", "Texture", "0.5"});

        assertInstanceOf(DairyIngredient.class, dairy);
        assertInstanceOf(FatIngredient.class, fat);
        assertInstanceOf(AdditiveIngredient.class, additive);
    }

    @Test
    void reportGenerator_shouldIncludeTrialSummary() {
        Formula formula = new Formula("F001", "Vanilla Base");
        Trial trial = new Trial("T001", "Vanilla Trial", formula, 5.0, TrialStatus.COMPLETED);
        trial.addEvaluation(new SensoryEvaluation("E001", "Lan", 4, 5, 3));

        String report = ReportGenerator.generateTrialReport(java.util.List.of(trial));

        assertTrue(report.contains("T001"));
        assertTrue(report.contains("Vanilla Base"));
        assertTrue(report.contains("Average score: 4.0"));
    }

    @Test
    void evaluationTask_shouldAddEvaluationAndLogResult() {
        Trial trial = new Trial("T001", "Trial", new Formula("F001", "Base"), 1.0,
                TrialStatus.PLANNED);
        EvaluationLog log = new EvaluationLog();

        new EvaluationTask(trial, new SensoryEvaluation("E001", "Lan", 3, 3, 3),
                new SensoryEvaluationService(), log).run();

        assertEquals(1, trial.getEvaluationsTrial().size());
        assertTrue(log.getLog().contains("E001 added to T001"));
    }
}