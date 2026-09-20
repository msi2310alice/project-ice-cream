package consoleMenu;

import service.TrialService;
import util.ReportGenerator;

public class TrialReportMenu {

    private final TrialService trialService;

    public TrialReportMenu(TrialService trialService) {
        this.trialService = trialService;
    }

    public void execute() {
        String report = ReportGenerator.generateTrialReport(trialService.getAllTrials());
        System.out.println(report);
    }
}