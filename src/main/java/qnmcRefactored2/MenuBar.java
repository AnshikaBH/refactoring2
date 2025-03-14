package qnmcRefactored2;

import java.awt.event.KeyEvent;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    @Serial
    private static final long serialVersionUID = 1L;

    public static int bits;

    private String objective = """
            The Quine McCluskey algorithm (or the method of prime implicants) \
            
            is a method used for minimization of boolean functions which was developed by W.V. \
            
            Quine and Edward J. McCluskey. It is functionally identical to Karnaugh mapping, \
            
            but the tabular form makes it more efficient for use in computer algorithms, and\
            
            it also gives a deterministic way to check that the minimal form of a Boolean \
            
            function has been reached. It is sometimes referred to as the tabulation method.""";

    private final Map<String, String> developers = new HashMap<>();
    {
        developers.put("Developer 1", "Jane Smith");
        developers.put("Developer 2", "John Doe");
        developers.put("Developer 3", "Ashok Kumar");
    }

    public MenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        JMenuItem newMenuItem = new JMenuItem("New  Ctrl+N", KeyEvent.VK_N);
        fileMenu.add(newMenuItem);
        newMenuItem.addActionListener(arg0 -> {

            if(GUI.set!=null)GUI.set.clear();

            String s = JOptionPane
                    .showInputDialog("Enter the boolean bits(3 to 5): ");
            try {
                bits = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                bits=2;
            }

            if(bits < 3 || bits > 5){
                JOptionPane.showMessageDialog(null,
                        "Wrong input. Press File and then NEW", "Error",
                        JOptionPane.ERROR_MESSAGE, null);

            }

        });

        JMenuItem exit = new JMenuItem("Exit  Alt+F4", KeyEvent.VK_N);
        fileMenu.add(exit);
        exit.addActionListener(arg0 -> System.exit(0));

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_F);
        add(help);

        JMenuItem objective = new JMenuItem("About Quine McCluskey Algorithm", KeyEvent.VK_N);
        help.add(objective);
        objective.addActionListener(arg0 -> JOptionPane.showMessageDialog(null, MenuBar.this.objective,
                "Quine McCluskey Prime Implicant Generator",
                JOptionPane.INFORMATION_MESSAGE));

        JMenu authors = new JMenu("About...");
        authors.setMnemonic(KeyEvent.VK_F);
        add(authors);

        developers.forEach((developerKey, developerName) -> addDeveloperMenuItem(authors, developerKey, developerName));
    }

    private void addDeveloperMenuItem(JMenu authors, String developerName, String developerInfo) {
        JMenuItem developerMenuItem = new JMenuItem(developerName, KeyEvent.VK_N);
        authors.add(developerMenuItem);
        developerMenuItem.addActionListener(arg0 -> JOptionPane.showMessageDialog(null, developerInfo,
                "Quine McCluskey Prime Implicant Generator", JOptionPane.INFORMATION_MESSAGE));
    }

}
