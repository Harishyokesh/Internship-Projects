package tracker; // your Main.java should have this package if it's in tracker folder

import ui.MainFrame; // import the MainFrame from ui package
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
