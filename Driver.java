import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 	Driver class for the vending machine
 */
public class Driver {
    /**
     * 	Main method for the vending machine
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainMenuView view = new MainMenuView();
                new MainMenuController(view); 
                view.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                view.getFrame().setVisible(true);
            }
        });
    }    
}