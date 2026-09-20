package generated;

import exception.file.FileDataException;
import exception.validation.DuplicateIdException;
import model.formula.Formula;
import model.ingredient.DairyIngredient;
import model.ingredient.Ingredient;
import org.junit.jupiter.api.Test;
import repository.formula.IFFormulaRepository;
import service.FormulaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormulaServiceTest {

    @Test
    void crudAndSearch_shouldManageFormulas() throws Exception {
        FormulaService service = new FormulaService(new StubFormulaRepository());
        Formula formula = new Formula("F001", "Vanilla Base");

        assertTrue(service.addFormula(formula));
        assertSame(formula, service.findFormulaById("F001"));
        assertEquals(List.of(formula), service.findFormulasByName("vanilla"));
        assertTrue(service.removeFormula("F001"));
        assertFalse(service.removeFormula("F001"));
    }

    @Test
    void duplicateFormula_shouldReject() throws Exception {
        FormulaService service = new FormulaService(new StubFormulaRepository());
        Formula formula = new Formula("F001", "Vanilla Base");
        service.addFormula(formula);

        assertThrows(DuplicateIdException.class, () -> service.addFormula(formula));
    }

    @Test
    void loadData_shouldRebuildFormulaIndex() throws Exception {
        StubFormulaRepository repository = new StubFormulaRepository();
        Formula loaded = new Formula("F009", "Loaded Formula");
        repository.loaded = List.of(loaded);
        FormulaService service = new FormulaService(repository);

        service.loadData(List.of(new DairyIngredient("ING001", "Milk", "kg", 2.0, 3.0, 3.0)));

        assertSame(loaded, service.findFormulaById("F009"));
    }

    private static class StubFormulaRepository implements IFFormulaRepository {
        private List<Formula> loaded = List.of();

        @Override
        public void save(List<Formula> formulas) throws FileDataException {
        }

        @Override
        public List<Formula> load(List<Ingredient> ingredients) throws FileDataException {
            return loaded;
        }
    }
}