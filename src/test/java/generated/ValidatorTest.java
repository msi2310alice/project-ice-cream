package generated;

import exception.validation.InvalidDataException;
import util.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

    @Test
    void validateIngredientId_validId_shouldPass() {
        assertDoesNotThrow(() -> Validator.validateIngredientId("ING001"));
    }

    @Test
    void validateIngredientId_invalidId_shouldThrow() {
        assertThrows(InvalidDataException.class,
                () -> Validator.validateIngredientId("ING00"));
    }

    // Equivalence partitioning: blank, valid format, and invalid format.
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void validateIngredientId_blankPartition_shouldThrow(String id) {
        assertThrows(InvalidDataException.class,
                () -> Validator.validateIngredientId(id));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ING000", "ING001", "ING999"})
    void validateIngredientId_validFormatPartition_shouldPass(String id) {
        assertDoesNotThrow(() -> Validator.validateIngredientId(id));
    }

    // Boundary value analysis: just below, at, and just above the score limits.
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 4, 5, 6})
    void validateSensoryScore_boundaryValues_shouldRespectRange(int score) {
        if (score >= 1 && score <= 5) {
            assertDoesNotThrow(() -> Validator.validateSensoryScore(score, "Taste"));
        } else {
            assertThrows(InvalidDataException.class,
                    () -> Validator.validateSensoryScore(score, "Taste"));
        }
    }

    @Test
    void validatePositive_zeroShouldThrow() {
        assertThrows(InvalidDataException.class,
                () -> Validator.validatePositive(0, "Price"));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.0001, 0.0001, 1.0})
    void validatePositive_boundaryValues_shouldRespectPositiveRule(double value) {
        if (value > 0) {
            assertDoesNotThrow(() -> Validator.validatePositive(value, "Price"));
        } else {
            assertThrows(InvalidDataException.class,
                    () -> Validator.validatePositive(value, "Price"));
        }
    }

    @Test
    void validateSensoryScore_validRange_shouldPass() {
        assertDoesNotThrow(() -> Validator.validateSensoryScore(1, "Taste"));
        assertDoesNotThrow(() -> Validator.validateSensoryScore(5, "Taste"));
    }

    @Test
    void validateSensoryScore_outOfRange_shouldThrow() {
        assertThrows(InvalidDataException.class,
                () -> Validator.validateSensoryScore(0, "Taste"));
        assertThrows(InvalidDataException.class,
                () -> Validator.validateSensoryScore(6, "Taste"));
    }
}
