package consoleMenu;

import exception.base.IceCreamException;
import model.formula.Formula;
import model.trial.Trial;
import service.FormulaService;
import service.TrialService;
import type.TrialStatus;

import java.util.Scanner;

public class AddTrialMenu {

    private final Scanner scanner;
    private final FormulaService formulaService;
    private final TrialService trialService;

    public AddTrialMenu(
            Scanner scanner,
            FormulaService formulaService,
            TrialService trialService) {

        this.scanner = scanner;
        this.formulaService = formulaService;
        this.trialService = trialService;
    }

    public void execute() {

        try {

            System.out.println("===== ADD TRIAL =====");

            System.out.print("Trial ID: ");
            String idTrial = scanner.nextLine();

            System.out.print("Trial name: ");
            String nameTrial = scanner.nextLine();

            System.out.print("Formula ID: ");
            String idFormula = scanner.nextLine();

            Formula formula =
                    formulaService
                            .findFormulaById(idFormula);

            if (formula == null) {

                System.out.println(
                        "Formula not found."
                );

                return;
            }

            System.out.print("Batch size: ");

            double batchSizeTrial =
                    Double.parseDouble(
                            scanner.nextLine()
                    );

            System.out.println(
                    "Status: PLANNED / IN_PROGRESS / COMPLETED / CANCELLED"
            );

            System.out.print("Status: ");

            TrialStatus statusTrial =
                    TrialStatus.valueOf(
                            scanner.nextLine()
                                    .toUpperCase()
                    );

            Trial trial =
                    new Trial(
                            idTrial,
                            nameTrial,
                            formula,
                            batchSizeTrial,
                            statusTrial
                    );

            trialService.addTrial(trial);

            System.out.println(
                    "Trial added successfully."
            );

        } catch (IceCreamException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Invalid number."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid trial status."
            );
        }
    }
}