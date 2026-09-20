package generated;

import exception.validation.DuplicateIdException;
import exception.validation.InvalidDataException;
import model.trial.SensoryEvaluation;
import model.trial.Trial;
import org.junit.jupiter.api.Test;
import service.SensoryEvaluationService;
import type.TrialStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SensoryEvaluationServiceTest {

    private final SensoryEvaluationService service = new SensoryEvaluationService();

    // Decision table: valid ID, name, scores, and new ID -> add evaluation.
    @Test
    void addEvaluation_allConditionsValid_shouldAdd() throws Exception {
        Trial trial = newTrial();

        service.addSensoryEvaluationToTrial(trial, evaluation("E001", "Lan", 1, 3, 5));

        assertEquals(1, trial.getEvaluationsTrial().size());
    }

    // Decision table: any invalid validation condition -> reject and do not add.
    @Test
    void addEvaluation_invalidInput_shouldReject() {
        Trial trial = newTrial();

        assertThrows(InvalidDataException.class,
                () -> service.addSensoryEvaluationToTrial(
                        trial, evaluation("E01", "Lan", 3, 3, 3)));
        assertThrows(InvalidDataException.class,
                () -> service.addSensoryEvaluationToTrial(
                        trial, evaluation("E002", "   ", 3, 3, 3)));
        assertThrows(InvalidDataException.class,
                () -> service.addSensoryEvaluationToTrial(
                        trial, evaluation("E003", "Lan", 0, 3, 3)));

        assertEquals(0, trial.getEvaluationsTrial().size());
    }

    // Decision table: valid data but duplicate ID -> reject as duplicate.
    @Test
    void addEvaluation_duplicateId_shouldReject() throws Exception {
        Trial trial = newTrial();
        service.addSensoryEvaluationToTrial(trial, evaluation("E001", "Lan", 3, 3, 3));

        assertThrows(DuplicateIdException.class,
                () -> service.addSensoryEvaluationToTrial(
                        trial, evaluation("E001", "Mai", 5, 5, 5)));
        assertEquals(1, trial.getEvaluationsTrial().size());
    }

    private Trial newTrial() {
        return new Trial("T001", "Vanilla trial", null, 1.0, TrialStatus.PLANNED);
    }

    private SensoryEvaluation evaluation(String id, String name,
                                         int taste, int texture, int melting) {
        return new SensoryEvaluation(id, name, taste, texture, melting);
    }
}