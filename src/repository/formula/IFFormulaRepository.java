package repository.formula;

import java.util.List;
import model.formula.Formula;
import model.ingredient.Ingredient;

import exception.file.FileDataException;

public interface IFFormulaRepository {
    void save(List<Formula> formulas) throws FileDataException;
    List<Formula> load(List<Ingredient> ingredients) throws FileDataException;
}
