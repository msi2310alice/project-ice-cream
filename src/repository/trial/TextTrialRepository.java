package repository.trial;

import exception.file.FileDataException;
import model.formula.Formula;
import model.trial.SensoryEvaluation;
import model.trial.Trial;
import type.TrialStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextTrialRepository implements IFTrialRepository{
    private String filePath;

    public TextTrialRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override 
    public void save(List<Trial> trials) throws FileDataException{
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {

            for (Trial trial : trials) {
                StringBuilder evaluationsData = new StringBuilder();
                
                for (SensoryEvaluation evaluation : trial.getEvaluationsTrial()) {
                    if (evaluationsData.length() > 0) {
                        evaluationsData.append(",");
                    }

                    // FIXED: correct evaluation order = taste : texture : melting
                    evaluationsData.append(evaluation.getIdEvaluation())
                                    .append(":")
                                    .append(evaluation.getNameEvaluator())
                                    .append(":")
                                    .append(evaluation.getTasteScore())
                                    .append(":")
                                    .append(evaluation.getTextureScore())
                                    .append(":")
                                    .append(evaluation.getMeltingScore());
                }

                writer.println(trial.getIdTrial()
                                + "|"
                                + trial.getNameTrial()
                                + "|"
                                + trial.getFormula().getIdFormula()
                                + "|"
                                + trial.getBatchSizeTrial()
                                + "|"
                                + trial.getStatusTrial()
                                + "|"
                                + evaluationsData
                            );

            }
        } catch (IOException e) {
            throw new FileDataException("Cannot save trial file");
        }
    }

    @Override 
    public List<Trial> load(List<Formula> formulas) throws FileDataException {
        List<Trial> trials = new ArrayList<>();

        File file = new File(filePath);

        if(!file.exists()) {
            return trials;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] trialData = line.split("\\|", -1);

                String idTrial = trialData[0];
                String nameTrial = trialData[1];
                String idFormula = trialData[2];
                double batchSizeTrial = Double.parseDouble(trialData[3]);
                TrialStatus trialStatus = TrialStatus.valueOf(trialData[4]);

                Formula formula = findFormulaInList(formulas, idFormula);
                
                if (formula == null) {
                    throw new IllegalArgumentException("Formula not found: " + idFormula);
                }

                Trial trial = new Trial(idTrial, nameTrial, formula, batchSizeTrial, trialStatus);

                if (trialData.length > 5 && !trialData[5].isBlank()) {
                    String[] evaluationsData = trialData[5].split(",");

                    for (String evaluationData : evaluationsData) {
                        String[] parts = evaluationData.split(":");

                        String idEvluation = parts[0];
                        String nameEvaluation = parts[1];
                        int tasteScore = Integer.parseInt(parts[2]);
                        int textureScore = Integer.parseInt(parts[3]);
                        int meltingScore = Integer.parseInt(parts[4]);
                        
                        SensoryEvaluation evaluation = new SensoryEvaluation(idEvluation, 
                                                                                nameEvaluation, 
                                                                                tasteScore, 
                                                                                textureScore, 
                                                                                meltingScore
                                                                            );
                        trial.addEvaluation(evaluation);
                    }
                }
                trials.add(trial);
            }
            return trials;

        } catch (IOException | RuntimeException e) {
            throw new FileDataException("Cannot load trial file");
        }
    }

    private Formula findFormulaInList(List<Formula> formulas, String idFormula) {
        for (Formula formula : formulas) {
            if (formula.getIdFormula().equals(idFormula)) {
                return formula;
            }
        }
        return null;
    }
}
