package repository.trial;

import exception.file.FileDataException;
import model.formula.Formula;
import model.trial.Trial;

import java.util.List;

public interface IFTrialRepository {
    void save(List<Trial> trials) throws FileDataException;
    List<Trial> load(List<Formula> formulas) throws FileDataException;
}
