package qnmcRefactored2;

import java.util.Set;

public class Controller {
    private final GUI view;
    private final MinTermRepository model;

    public Controller(GUI view, MinTermRepository model) {
        this.view = view;
        this.model = model;
        initialize();
    }

    private void initialize() {
        view.setNextButtonActionListener(e -> handleNextButtonClick());

        view.setCalculateButtonActionListener(e -> handleCalculateButtonClick());
    }

    void handleNextButtonClick() {
        String input = view.getMintermInput();
        model.setMinList(input);
    }

    void handleCalculateButtonClick() {
        try {
            Quine quine = new Quine();
            Set<String> minterms = MinTermRepository.getMin();
            for (String minterm : minterms) {
                if (MenuBar.bits == 3) {
                    quine.addTerm(GUI.dataThree(minterm));
                } else if (MenuBar.bits == 4) {
                    quine.addTerm(GUI.dataFour(minterm));
                } else if (MenuBar.bits == 5) {
                    quine.addTerm(GUI.dataFive(minterm));
                }
            }
            quine.simplify();
            view.updateResult(quine.toString());
        } catch (ExceptionQuine e) {
            e.printStackTrace();
        }
    }
}
