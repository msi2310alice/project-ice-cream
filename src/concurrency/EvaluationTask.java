package concurrency;

import exception.base.IceCreamException;
import model.trial.SensoryEvaluation;
import model.trial.Trial;
import service.SensoryEvaluationService;

public class EvaluationTask implements Runnable {
    private Trial trial;
    private SensoryEvaluation sensoryEvaluation;
    private SensoryEvaluationService sensoryEvaluationService;
    private EvaluationLog evaluationLog;

    public EvaluationTask(
        Trial trial,
        SensoryEvaluation sensoryEvaluation,
        SensoryEvaluationService sensoryEvaluationService,
        EvaluationLog evaluationLog
    ) {
        this.trial = trial;
        this.sensoryEvaluation = sensoryEvaluation;
        this.sensoryEvaluationService = sensoryEvaluationService;
        this.evaluationLog = evaluationLog;
    }

    @Override 
    public void run() {
        try {
            sensoryEvaluationService.addSensoryEvaluationToTrial(trial, sensoryEvaluation);
            evaluationLog.addLog(sensoryEvaluation.getIdEvaluation() + " added to " + trial.getIdTrial());
        } catch (IceCreamException e) {
            evaluationLog.addLog(sensoryEvaluation.getIdEvaluation() + " failed: " + e.getMessage());
        }
    }
}
