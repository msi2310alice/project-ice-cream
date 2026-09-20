package model.trial;

import java.util.ArrayList;
import java.util.List;

import model.formula.Formula;
import type.TrialStatus;

public class Trial implements Comparable<Trial>{
    private String idTrial;
    private String nameTrial;
    private Formula formulaTrial;
    private double batchSizeTrial;
    private TrialStatus statusTrial;
    private List<SensoryEvaluation> evaluationsTrial;
    
    public Trial(String id, String name, Formula formula, double batchSize, TrialStatus status) {
        this.idTrial = id;
        this.nameTrial = name;
        this.formulaTrial = formula;
        this.batchSizeTrial = batchSize;
        this.statusTrial = status;
        this.evaluationsTrial = new ArrayList<>();
    }

    public String getIdTrial() {
        return idTrial;
    }
    public String getNameTrial() {
        return nameTrial;
    }
    public Formula getFormula() {
        return formulaTrial;
    }
    public double getBatchSizeTrial() {
        return batchSizeTrial;
    }
    public TrialStatus getStatusTrial() {
        return statusTrial;
    }
    public List<SensoryEvaluation> getEvaluationsTrial() {
        return evaluationsTrial;
    }

    public void setNameTrial(String name) {
        this.nameTrial = name;
    }
    public void setFormula(Formula formula) {
        this.formulaTrial = formula;
    }
    public void setBatchSize(double batchSize) {
        this.batchSizeTrial = batchSize;
    }
    public void setStatus(TrialStatus status) {
        this.statusTrial = status;
    } 

    public void addEvaluation(SensoryEvaluation evaluation) {
        evaluationsTrial.add(evaluation);
    }

    public double getAverageScoreTrial() {
        if (evaluationsTrial.isEmpty()) {
            return 0;
        }
        double totalScore = 0;
        for (SensoryEvaluation evaluation : evaluationsTrial) {
            totalScore += evaluation.getAverageScore();
        }
        return totalScore/evaluationsTrial.size();
    }

    @Override 
    public String toString() {
        return "Trial{ " 
                + "idTrial=" + idTrial 
                + ", nameTrial= " + nameTrial
                + ", formulaTrial= " + formulaTrial
                + ", batchSizeTrial= " + batchSizeTrial
                + ", statusTrial= " + statusTrial
                + ", averageScoreTrial= " + getAverageScoreTrial() 
                + "}";
    }

    @Override 
    public int compareTo(Trial otherTrial) {
        return this.idTrial.compareTo(otherTrial.getIdTrial());
    }
}
