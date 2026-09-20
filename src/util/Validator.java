package util;
import exception.validation.InvalidDataException;
import exception.validation.InvalidSearchDataException;

public class Validator {
    private static final String INGREDIENT_ID_REGEX = "^ING\\d{3}$";
    private static final String FORMULA_ID_REGEX = "^F\\d{3}$";
    private static final String TRIAL_ID_REGEX = "^T\\d{3}$";
    private static final String EVALUATION_ID_REGEX = "^E\\d{3}$";

    public static void validateIngredientId(String id) throws InvalidDataException{
        validateId(id, INGREDIENT_ID_REGEX, "Ingredient ID");
    }
    public static void validateFormulaId(String idFormula) throws InvalidDataException {
        validateId(idFormula, FORMULA_ID_REGEX, "Formula ID");
    }
    public static void validateTrialId(String idTrial) throws InvalidDataException {
        validateId(idTrial, TRIAL_ID_REGEX, "Trial ID");
    }
    public static void validateEvaluationId(String idEvaluation) throws InvalidDataException {
        validateId(idEvaluation, EVALUATION_ID_REGEX, "Evaluation ID");
    }

    private static void validateId(String id, String regex, String fieldName) throws InvalidDataException {
        if (id == null || id.isBlank()) {
            throw new InvalidDataException(fieldName + " cannot be blank");
        }
        if (!id.matches(regex)) {
            throw new InvalidDataException("invalid " + fieldName);
        }
    }

    public static void validateName(String name, String fieldName) throws InvalidDataException {
        if (name == null || name.isBlank()) {
            throw new InvalidDataException(fieldName + " cannot be blank");
        }
    }

    public static void validatePositive(double value, String fieldName) throws InvalidDataException {
        if (value <= 0) {
            throw new InvalidDataException(fieldName + "must be greater than 0");
        }
    }

    public static void validateSensoryScore(int score, String fieldName) throws InvalidDataException {
        if (score < 1 || score >5) {
            throw new InvalidDataException(fieldName + " must be from 1 to 5");
        }
    }

    public static void validateSearchKeyword(String keyword) throws InvalidSearchDataException {
        if (keyword == null || keyword.isBlank()) {
            throw new InvalidSearchDataException("Search keyword cannot be blank");
        } 
    }
    
}
