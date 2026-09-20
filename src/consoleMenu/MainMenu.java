package consoleMenu;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;

    private final AddIngredientMenu addIngredientMenu;
    private final AddFormulaMenu addFormulaMenu;
    private final AddTrialMenu addTrialMenu;
    private final AddSensoryEvaluationMenu addSensoryEvaluationMenu;
    private final SearchMenu searchMenu;
    private final TrialReportMenu trialReportMenu;
    private final SaveDataMenu saveDataMenu;

    public MainMenu(
            Scanner scanner,
            AddIngredientMenu addIngredientMenu,
            AddFormulaMenu addFormulaMenu,
            AddTrialMenu addTrialMenu,
            AddSensoryEvaluationMenu addSensoryEvaluationMenu,
            SearchMenu searchMenu,
            TrialReportMenu trialReportMenu,
            SaveDataMenu saveDataMenu
    ) {
        this.scanner = scanner;
        this.addIngredientMenu = addIngredientMenu;
        this.addFormulaMenu = addFormulaMenu;
        this.addTrialMenu = addTrialMenu;
        this.addSensoryEvaluationMenu = addSensoryEvaluationMenu;
        this.searchMenu = searchMenu;
        this.trialReportMenu = trialReportMenu;
        this.saveDataMenu = saveDataMenu;
    }

    public void start() {
        int choice;

        do {
            showMenu();
            System.out.print("Choose: ");

            String input = scanner.nextLine().trim();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number from 0 to 7.");
                choice = -1;
                continue;
            }

            switch (choice) {
                case 1:
                    addIngredientMenu.execute();
                    break;
                case 2:
                    addFormulaMenu.execute();
                    break;
                case 3:
                    addTrialMenu.execute();
                    break;
                case 4:
                    addSensoryEvaluationMenu.execute();
                    break;
                case 5:
                    searchMenu.execute();
                    break;
                case 6:
                    trialReportMenu.execute();
                    break;
                case 7:
                    saveDataMenu.execute();
                    break;
                case 0:
                    saveDataMenu.execute();
                    System.out.println("Application closed.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
    
    private void showMenu() {
        System.out.println();
        System.out.println("===== ICE CREAM TRIAL MANAGEMENT =====");
        System.out.println("1. Add Ingredient");
        System.out.println("2. Add Formula");
        System.out.println("3. Add Trial");
        System.out.println("4. Add Sensory Evaluation");
        System.out.println("5. Search");
        System.out.println("6. Trial Report");
        System.out.println("7. Save Data");
        System.out.println("0. Exit");
    }
}
