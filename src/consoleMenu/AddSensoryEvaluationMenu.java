package consoleMenu;

import exception.base.IceCreamException;
import model.trial.SensoryEvaluation;
import model.trial.Trial;
import service.SensoryEvaluationService;
import service.TrialService;

import java.util.Scanner;

public class AddSensoryEvaluationMenu {

    private final Scanner scanner;
    private final TrialService trialService;
    private final SensoryEvaluationService sensoryEvaluationService;

    public AddSensoryEvaluationMenu(
            Scanner scanner,
            TrialService trialService,
            SensoryEvaluationService sensoryEvaluationService) {

        this.scanner = scanner;
        this.trialService = trialService;
        this.sensoryEvaluationService = sensoryEvaluationService;
    }

    public void execute() {

        try {
            System.out.println("===== ADD SENSORY EVALUATION =====");

            System.out.print("Trial ID: ");
            String idTrial = scanner.nextLine();

            Trial trial = trialService.findTrialById(idTrial);

            if (trial == null) {
                System.out.println("Trial not found.");
                return;
            }

            System.out.print("Evaluation ID: ");
            String idEvaluation = scanner.nextLine();

            System.out.print("Evaluator name: ");
            String nameEvaluator = scanner.nextLine();

            System.out.println("1 = Dislike Very Much");
            System.out.println("2 = Dislike");
            System.out.println("3 = Neither Like nor Dislike");
            System.out.println("4 = Like");
            System.out.println("5 = Like Very Much");

            System.out.print("Taste score: ");
            int tasteScore = Integer.parseInt(scanner.nextLine());

            System.out.print("Texture score: ");
            int textureScore = Integer.parseInt(scanner.nextLine());

            System.out.print("Melting score: ");
            int meltingScore = Integer.parseInt(scanner.nextLine());

            SensoryEvaluation sensoryEvaluation = new SensoryEvaluation(
                    idEvaluation,
                    nameEvaluator,
                    tasteScore,
                    textureScore,
                    meltingScore
            );

            sensoryEvaluationService.addSensoryEvaluationToTrial(trial, sensoryEvaluation);

            System.out.println("Sensory evaluation added successfully.");

        } catch (IceCreamException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }
}