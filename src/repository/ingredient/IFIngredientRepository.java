package repository.ingredient;

import exception.file.FileDataException;
import model.ingredient.Ingredient;

import java.util.List;

public interface IFIngredientRepository {
    void save(List<Ingredient> ingredients) throws FileDataException;

    List<Ingredient> load() throws FileDataException;
}
