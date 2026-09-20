package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.validation.InvalidDataException;
import exception.validation.DuplicateIdException;
import exception.validation.InvalidSearchDataException;

import util.Validator;

import model.formula.Formula;

import repository.formula.IFFormulaRepository;
import exception.file.FileDataException;

import model.ingredient.Ingredient;

public class FormulaService {
    private List<Formula> formulas;
    private Map<String, Formula> formulaMap;

    private final IFFormulaRepository formulaRepository;

    public FormulaService(IFFormulaRepository formulaRepository) {
        this.formulas = new ArrayList<>();
        this.formulaMap = new HashMap<>();

        this.formulaRepository = formulaRepository;
    }

    public boolean addFormula(Formula formula) throws InvalidDataException, DuplicateIdException{

        Validator.validateFormulaId(formula.getIdFormula());

        Validator.validateName(formula.getNameFormula(), "Formula name");

        if(formulaMap.containsKey(formula.getIdFormula())) {
            // return false;
            throw new DuplicateIdException("Formula ID already exists: " + formula.getIdFormula());
        }
        formulas.add(formula);
        formulaMap.put(formula.getIdFormula(), formula);
        return true;
    }

    public Formula findFormulaById(String idFormula) {
        return formulaMap.get(idFormula);
    }

    public List<Formula> findFormulasByName(String name) throws InvalidSearchDataException {

        Validator.validateSearchKeyword(name);

        List<Formula> result = new ArrayList<>();

        for(Formula formula : formulas) {
            if (formula.getNameFormula().toLowerCase().contains(name.toLowerCase())) {
                result.add(formula);
            }
        }
        return result;
    }
    
    public boolean removeFormula(String id) {
        Formula formula = formulaMap.get(id);
        if (formula == null) {
            return false;
        }
        formulas.remove(formula);
        return true;
    } 

    public List<Formula> getAllFormulas() {
        return new ArrayList<>(formulas);
    }

    public void saveData() throws FileDataException {
        formulaRepository.save(formulas);
    }

    public void loadData(List<Ingredient> ingredients) throws FileDataException {
        List<Formula> loadedFormulas = formulaRepository.load(ingredients);

        formulas.clear();
        formulaMap.clear();

        for (Formula formula : loadedFormulas) {
            formulas.add(formula);
            formulaMap.put(formula.getIdFormula(), formula);
        }
    }
}
