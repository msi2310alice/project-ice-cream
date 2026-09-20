package service;

import java.util.ArrayList;
import java.util.List;

import exception.validation.InvalidDataException;
import exception.validation.DuplicateIdException;
import exception.validation.InvalidSearchDataException;

import util.Validator;

import model.trial.SensoryEvaluation;
import model.trial.Trial;

public class SensoryEvaluationService {
    public void addSensoryEvaluationToTrial(Trial trial, SensoryEvaluation sensoryEvaluation) throws InvalidDataException, DuplicateIdException {

        Validator.validateEvaluationId(sensoryEvaluation.getIdEvaluation());

        Validator.validateName(sensoryEvaluation.getNameEvaluator(),"Evaluator name");

        Validator.validateSensoryScore(sensoryEvaluation.getTasteScore(),"Taste score");

        Validator.validateSensoryScore(sensoryEvaluation.getTextureScore(),"Texture score");

        Validator.validateSensoryScore(sensoryEvaluation.getMeltingScore(),"Melting score");

        //Tại 1 thời điểm, chỉ 1 thread được thao tác đoạn code này trên cùng object trial
        synchronized (trial) {
            if (findSensoryEvaluationById(trial, sensoryEvaluation.getIdEvaluation()) != null) {

                throw new DuplicateIdException(
                        "Sensory evaluation ID already exists in trial: "
                                + sensoryEvaluation.getIdEvaluation()
                );
            }

            trial.addEvaluation(sensoryEvaluation);
        }

        
    }

    public double calculateAverageScoreTrial(Trial trial) {
        return trial.getAverageScoreTrial();
    }

    public SensoryEvaluation findSensoryEvaluationById(Trial trial, String idEvaluation) {
        for(SensoryEvaluation sensoryEvaluation : trial.getEvaluationsTrial()) {
            if (sensoryEvaluation.getIdEvaluation().equals(idEvaluation)) {
                return sensoryEvaluation;
            }
        }
        return null;
    }

    public List<SensoryEvaluation> findSensoryEvaluationsByName(Trial trial, String name) throws InvalidSearchDataException {

        Validator.validateSearchKeyword(name);

        List<SensoryEvaluation> result = new ArrayList<>();

        for (SensoryEvaluation sensoryEvaluation : trial.getEvaluationsTrial()) {
            if (sensoryEvaluation.getNameEvaluator().toLowerCase().contains(name.toLowerCase())) {
                result.add(sensoryEvaluation);
            }
        }
        return result;
    }
}
