package qnmcRefactored2;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serial;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JLabel minInput;
    private JTextField minIn;
    private JButton nextButton;
    private JButton calculateButton;
    private JTextArea resultShow;


    static public int k = 0;
    static public Set<String> set;
    public String temp;
    private GetMintermList item = new GetMintermList();

    // Constructor
    public GUI() {
        super("Quine McCluskey Prime Implicant Generator");
        initializeUIComponents();
        configureUI();
    }

    // Initialize UI Components
    private void initializeUIComponents() {
        panel = new JPanel();
        minInput = new JLabel("Enter Minterm list: ");
        minIn = new JTextField();
        nextButton = new JButton("Next");
        resultShow = new JTextArea();
        calculateButton = new JButton("Calculate");
    }

    // Configure UI layout and components
    private void configureUI() {
        setLayout(null);
        setSize(550, 500);
        setResizable(false);

        panel.setBounds(0, 0, 500, 500);
        panel.setLayout(null);
        setJMenuBar(new MenuBar());

        setupMintermInput();
        setupNextButton();
        setupResultDisplay();
        setupCalculateButton();

        setVisible(true);
        add(panel);
    }

    // Setup Minterm Input Field
    private void setupMintermInput() {
        minInput.setBounds(50, 100, 150, 30);
        minInput.setFont(new Font("Verdana", Font.BOLD, 14));
        panel.add(minInput);

        minIn.setBounds(50, 140, 70, 30);
        minIn.addKeyListener(createKeyListener());
        panel.add(minIn);
    }

    // Create and return the KeyListener for input validation
    private KeyListener createKeyListener() {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {}

            @Override
            public void keyPressed(KeyEvent arg0) {}

            @Override
            public void keyReleased(KeyEvent arg0) {
                validateInput();
            }
        };
    }

    // Validate user input based on the selected number of bits
    private void validateInput() {
        String input = minIn.getText();
        int bits = MenuBar.bits;
        try {
            k = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            k = -1;
        }

        boolean isValid = isInputValid(bits, k);
        if (!isValid) {
            JOptionPane.showMessageDialog(null, getErrorMessage(bits), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            temp = input;
        }
    }

    // Check if the input is valid based on the number of bits
    private boolean isInputValid(int bits, int value) {
        if (bits == 3) {
            return value >= 0 && value <= 7;
        } else if (bits == 4) {
            return value >= 0 && value <= 15;
        } else if (bits == 5) {
            return value >= 0 && value <= 31;
        }
        return false;
    }

    // Get the error message based on the number of bits
    private String getErrorMessage(int bits) {
        if (bits == 3) {
            return "Number should be within 0 to 7\nPlease press Next and give your input again";
        } else if (bits == 4) {
            return "Number should be within 0 to 15\nPlease press Next and give your input again";
        } else if (bits == 5) {
            return "Number should be within 0 to 31\nPlease press Next and give your input again";
        }
        return "Invalid Input";
    }

    // Setup "Next" Button
    private void setupNextButton() {
        nextButton.setBounds(140, 140, 70, 30);
        nextButton.addActionListener(e -> {
            minIn.setText("");
            item.setMinList(temp);
        });
        panel.add(nextButton);
    }

    // Setup Result Display Text Area
    private void setupResultDisplay() {
        resultShow.setBounds(50, 200, 300, 200);
        resultShow.setEditable(false);
        panel.add(resultShow);
    }

    // Setup "Calculate" Button
    private void setupCalculateButton() {
        calculateButton.setBounds(400, 250, 100, 50);
        calculateButton.addActionListener(e -> calculateSimplification());
        panel.add(calculateButton);
    }

    // Perform calculation and display result
    private void calculateSimplification() {
        Quine quine = new Quine();
        set = GetMintermList.getMin();
        try {
            for (String str : set) {
                if (MenuBar.bits == 3) {
                    quine.addTerm(dataThree(str));
                } else if (MenuBar.bits == 4) {
                    quine.addTerm(dataFour(str));
                } else if (MenuBar.bits == 5) {
                    quine.addTerm(dataFive(str));
                }
            }

            quine.simplify();
            resultShow.setText(quine.toString());
        } catch (ExceptionQuine e) {
            e.printStackTrace();
        }
    }

    // Data methods (can be moved to a helper class)
    public static String dataThree(String input) {
        return getData(input, new String[]{"000", "001", "010", "011", "100", "101", "110", "111"});
    }

    public static String dataFour(String input) {
        return getData(input, new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"});
    }

    public static String dataFive(String input) {
        return getData(input, new String[]{"00000", "00001", "00010", "00011", "00100", "00101", "00110", "00111", "01000", "01001", "01010", "01011", "01100", "01101", "01110", "01111", "10000", "10001", "10010", "10011", "10100", "10101", "10110", "10111", "11000", "11001", "11010", "11011", "11100", "11101", "11110", "11111"});
    }

    // Utility method for data transformation
    private static String getData(String input, String[] bin) {
        int i = Integer.parseInt(input);
        return bin[i];
    }

    // Main method
    public static void main(String[] args) {
        setLookAndFeel();
        getBitsInput();
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Set Look and Feel
    private static void setLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the number of bits from the user
    private static void getBitsInput() {
        String s = JOptionPane.showInputDialog("Enter the boolean bits(3 to 5): ");
        try {
            MenuBar.bits = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            MenuBar.bits = 2;
        }

        if (MenuBar.bits < 3 || MenuBar.bits > 5) {
            JOptionPane.showMessageDialog(null, "Wrong input. Press File and then NEW", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }
}


