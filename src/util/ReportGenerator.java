package util;

import model.trial.Trial;

import java.util.List;

public class ReportGenerator {

    public static String generateTrialReport(
            List<Trial> trials) {

        StringBuilder report = new StringBuilder();

        report.append("===== TRIAL REPORT =====")
                .append(System.lineSeparator());

        for (Trial trial : trials) {

            report.append("ID: ")
                    .append(trial.getIdTrial())
                    .append(System.lineSeparator());

            report.append("Name: ")
                    .append(trial.getNameTrial())
                    .append(System.lineSeparator());

            report.append("Formula: ")
                    .append(trial.getFormula()
                                .getNameFormula()
                    )
                    .append(System.lineSeparator());

            report.append("Batch size: ")
                    .append(trial.getBatchSizeTrial())
                    .append(System.lineSeparator());

            report.append("Status: ")
                    .append(trial.getStatusTrial())
                    .append(System.lineSeparator());

            report.append("Average score: ")
                    .append(trial.getAverageScoreTrial())
                    .append(System.lineSeparator());

            report.append("------------------------")
                    .append(System.lineSeparator());
        }

        return report.toString();
    }
}