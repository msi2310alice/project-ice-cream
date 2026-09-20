package generated;

import model.trial.SensoryEvaluation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensoryEvaluationTest {

    @Test
    void getAverageScore_shouldIncludeAllThreeAttributes() {
        SensoryEvaluation evaluation =
                new SensoryEvaluation("E001", "Lan", 4, 5, 3);

        assertEquals(4.0, evaluation.getAverageScore(), 0.0001);
    }
}
