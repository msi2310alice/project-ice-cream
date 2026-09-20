package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Validator;

import exception.validation.InvalidDataException;
import exception.validation.InvalidSearchDataException;
import exception.validation.DuplicateIdException;
import model.ingredient.Ingredient;
import repository.ingredient.IFIngredientRepository;

import exception.file.FileDataException;;



public class IngredientService {
    private List<Ingredient> ingredients;
    private Map<String, Ingredient> ingredientMap;
    
    private final IFIngredientRepository ingredientRepository;

    public IngredientService(IFIngredientRepository ingredientRepository) {

        this.ingredientRepository = ingredientRepository;

        this.ingredients = new ArrayList<>();
        this.ingredientMap = new HashMap<>();
    }

    public boolean addIngredient(Ingredient ingredient) throws InvalidDataException, DuplicateIdException {

        Validator.validateIngredientId(ingredient.getId());

        Validator.validateName(ingredient.getName(), "Ingredient name");

        Validator.validatePositive(ingredient.getPrice(), "Ingredient price");
        

        if(ingredientMap.containsKey(ingredient.getId())) {
            // return false;
            throw new DuplicateIdException("Ingredient ID already exists: " + ingredient.getId());
        }
        ingredients.add(ingredient);
        ingredientMap.put(ingredient.getId(), ingredient);
        return true;
    }

    public Ingredient findIngredientById(String id) {
        return ingredientMap.get(id);
    }

    public List<Ingredient> findIngredientsByName(String name) throws InvalidSearchDataException{

        Validator.validateSearchKeyword(name);

        List<Ingredient> result = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(ingredient);
            }
        }
        return result;
    }

    public boolean removeIngredient(String id) {
        Ingredient ingredient = ingredientMap.remove(id);
        if(ingredient == null) {
            return false;
        }
        ingredients.remove(ingredient);
        return true;

    }
    public List<Ingredient> getAllIngredients() {
        return new ArrayList<>(ingredients);
    }

    public void saveData() throws FileDataException {
        ingredientRepository.save(ingredients);
    }

    public void loadData() throws FileDataException {
        List<Ingredient> loadedIngredients = ingredientRepository.load();

        ingredients.clear();
        ingredientMap.clear();

        for (Ingredient ingredient : loadedIngredients) {
            ingredients.add(ingredient);
            ingredientMap.put(ingredient.getId(), ingredient);
        }
    }
    
}
