package generated;

import comparator.TrialBatchSizeComparator;
import exception.file.FileDataException;
import exception.validation.DuplicateIdException;
import model.formula.Formula;
import model.trial.Trial;
import org.junit.jupiter.api.Test;
import repository.trial.IFTrialRepository;
import service.TrialService;
import sorting.BubbleSort;
import type.SortDirection;
import type.TrialStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrialServiceTest {

    @Test
    void crudSearchQueueHistoryAndSort_shouldWork() throws Exception {
        TrialService service = new TrialService(new StubTrialRepository());
        Trial first = trial("T001", "Vanilla trial", 5.0);
        Trial second = trial("T002", "Chocolate trial", 2.0);

        assertTrue(service.addTrial(first));
        assertTrue(service.addTrial(second));
        assertSame(first, service.findTrialById("T001"));
        assertEquals(List.of(second), service.findTrialsByName("choc"));

        service.addWaitingTrial(first);
        service.addWaitingTrial(second);
        assertSame(first, service.getNextWaitingTrial());
        assertSame(second, service.getNextWaitingTrial());
        assertSame(second, service.getLastAddedTrial());

        assertEquals(List.of(second, first), service.sortTrials(
                new BubbleSort<>(), new TrialBatchSizeComparator(), SortDirection.ASCENDING));
        assertEquals(List.of(first, second), service.sortTrials(
                new BubbleSort<>(), new TrialBatchSizeComparator(), SortDirection.DESCENDING));
        assertTrue(service.removeTrial("T001"));
        assertFalse(service.removeTrial("T001"));
    }

    @Test
    void duplicateTrial_shouldReject() throws Exception {
        TrialService service = new TrialService(new StubTrialRepository());
        Trial trial = trial("T001", "Vanilla trial", 1.0);
        service.addTrial(trial);

        assertThrows(DuplicateIdException.class, () -> service.addTrial(trial));
    }

    @Test
    void loadData_shouldRebuildTrialIndex() throws Exception {
        StubTrialRepository repository = new StubTrialRepository();
        Trial loaded = trial("T009", "Loaded trial", 3.0);
        repository.loaded = List.of(loaded);
        TrialService service = new TrialService(repository);

        service.loadData(List.of(loaded.getFormula()));

        assertSame(loaded, service.findTrialById("T009"));
        assertNull(service.getNextWaitingTrial());
    }

    private Trial trial(String id, String name, double batchSize) {
        return new Trial(id, name, new Formula("F001", "Base"), batchSize, TrialStatus.PLANNED);
    }

    private static class StubTrialRepository implements IFTrialRepository {
        private List<Trial> loaded = List.of();

        @Override
        public void save(List<Trial> trials) throws FileDataException {
        }

        @Override
        public List<Trial> load(List<Formula> formulas) throws FileDataException {
            return loaded;
        }
    }
}