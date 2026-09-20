package generated;

import model.formula.Formula;
import model.formula.FormulaItem;
import model.ingredient.AdditiveIngredient;
import model.ingredient.DairyIngredient;
import model.ingredient.FatIngredient;
import model.ingredient.Ingredient;
import model.trial.SensoryEvaluation;
import model.trial.Trial;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import repository.formula.TextFormulaRepository;
import repository.ingredient.TextIngredientRepository;
import repository.trial.TextTrialRepository;
import type.TrialStatus;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class TextRepositoryTest {

    @TempDir
    Path tempDirectory;

    @Test
    void ingredientRepository_shouldRoundTripAllIngredientTypes() throws Exception {
        TextIngredientRepository repository = new TextIngredientRepository(
                tempDirectory.resolve("ingredients.txt").toString());
        List<Ingredient> ingredients = List.of(
                new DairyIngredient("ING001", "Milk", "kg", 2.0, 3.0, 3.5),
                new FatIngredient("ING002", "Butter", "kg", 4.0, 80.0, "Dairy"),
                new AdditiveIngredient("ING003", "Stabilizer", "kg", 6.0, "Texture", 0.5));

        repository.save(ingredients);
        List<Ingredient> loaded = repository.load();

        assertEquals(3, loaded.size());
        assertInstanceOf(DairyIngredient.class, loaded.get(0));
        assertInstanceOf(FatIngredient.class, loaded.get(1));
        assertInstanceOf(AdditiveIngredient.class, loaded.get(2));
        assertEquals("ING003", loaded.get(2).getId());
    }

    @Test
    void formulaRepository_shouldRoundTripItems() throws Exception {
        DairyIngredient milk = new DairyIngredient("ING001", "Milk", "kg", 2.0, 3.0, 3.5);
        Formula formula = new Formula("F001", "Milk Base");
        formula.addItem(new FormulaItem(milk, 10.5));
        TextFormulaRepository repository = new TextFormulaRepository(
                tempDirectory.resolve("formula.txt").toString());

        repository.save(List.of(formula));
        List<Formula> loaded = repository.load(List.of(milk));

        assertEquals(1, loaded.size());
        assertEquals("F001", loaded.get(0).getIdFormula());
        assertEquals(1, loaded.get(0).getItemsFormula().size());
        assertEquals(10.5, loaded.get(0).getTotalAmount(), 0.0001);
    }

    @Test
    void trialRepository_shouldRoundTripEvaluations() throws Exception {
        Formula formula = new Formula("F001", "Milk Base");
        Trial trial = new Trial("T001", "Trial 1", formula, 12.0, TrialStatus.COMPLETED);
        trial.addEvaluation(new SensoryEvaluation("E001", "Lan", 4, 5, 3));
        TextTrialRepository repository = new TextTrialRepository(
                tempDirectory.resolve("trials.txt").toString());

        repository.save(List.of(trial));
        List<Trial> loaded = repository.load(List.of(formula));

        assertEquals(1, loaded.size());
        assertEquals(1, loaded.get(0).getEvaluationsTrial().size());
        SensoryEvaluation evaluation = loaded.get(0).getEvaluationsTrial().get(0);
        assertEquals(4, evaluation.getTasteScore());
        assertEquals(5, evaluation.getTextureScore());
        assertEquals(3, evaluation.getMeltingScore());
    }
}