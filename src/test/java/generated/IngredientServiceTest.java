package generated;

import exception.file.FileDataException;
import exception.validation.DuplicateIdException;
import exception.validation.InvalidDataException;
import model.ingredient.DairyIngredient;
import model.ingredient.Ingredient;
import org.junit.jupiter.api.Test;
import repository.ingredient.IFIngredientRepository;
import service.IngredientService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IngredientServiceTest {

    @Test
    void crudAndSearch_shouldManageIngredients() throws Exception {
        StubIngredientRepository repository = new StubIngredientRepository();
        IngredientService service = new IngredientService(repository);
        Ingredient milk = new DairyIngredient("ING001", "Whole Milk", "kg", 2.5, 3.2, 3.5);

        assertTrue(service.addIngredient(milk));
        assertSame(milk, service.findIngredientById("ING001"));
        assertEquals(List.of(milk), service.findIngredientsByName("milk"));
        assertEquals(List.of(milk), service.getAllIngredients());
        assertTrue(service.removeIngredient("ING001"));
        assertFalse(service.removeIngredient("ING001"));
    }

    @Test
    void addIngredient_invalidOrDuplicate_shouldReject() throws Exception {
        IngredientService service = new IngredientService(new StubIngredientRepository());
        Ingredient valid = new DairyIngredient("ING001", "Milk", "kg", 2.5, 3.2, 3.5);
        service.addIngredient(valid);

        assertThrows(DuplicateIdException.class, () -> service.addIngredient(valid));
        assertThrows(InvalidDataException.class, () -> service.addIngredient(
                new DairyIngredient("ING002", "", "kg", 2.5, 3.2, 3.5)));
    }

    @Test
    void saveAndLoad_shouldDelegateToRepository() throws Exception {
        StubIngredientRepository repository = new StubIngredientRepository();
        IngredientService service = new IngredientService(repository);
        Ingredient loaded = new DairyIngredient("ING009", "Cream", "kg", 4.0, 2.0, 20.0);
        repository.loaded = List.of(loaded);

        service.loadData();
        service.saveData();

        assertEquals(List.of(loaded), service.getAllIngredients());
        assertEquals(List.of(loaded), repository.saved);
    }

    private static class StubIngredientRepository implements IFIngredientRepository {
        private List<Ingredient> saved;
        private List<Ingredient> loaded = List.of();

        @Override
        public void save(List<Ingredient> ingredients) throws FileDataException {
            saved = List.copyOf(ingredients);
        }

        @Override
        public List<Ingredient> load() throws FileDataException {
            return loaded;
        }
    }
}