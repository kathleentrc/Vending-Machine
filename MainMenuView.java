import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 	This MainMenuView class displays the main menu and is linked to the MainMenuController and MainMenu model
 */
public class MainMenuView {
    private ImageIcon icon;
    private JFrame frame;
    private JPanel mainBG;
    private JButton menuBtn1;
    private JButton menuBtn2;
    private JButton menuBtn3;
    private JLabel titleSpace;
    private GridBagConstraints gbc;

    /**
     * This MainMenuView represents the 'View' of the MVC architecture that we used for our Main Menu
     */
    public MainMenuView(){
        this.icon = new ImageIcon(getClass().getResource("/Images/VM ICON.png"));
        this.frame = new JFrame("Greens & Grains Vending Machine");

        // Create a background image label 
        final ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/Images/VM BGM.png"));
        this.mainBG = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, null);
            }
        };

        // Set the size of the frame
        this.frame.setSize(480, 680); 
        this.frame.setIconImage(icon.getImage());

        // Filler for space only
        this.titleSpace = new JLabel();
        this.titleSpace.setPreferredSize(new Dimension(0, 105));

        this.gbc = new GridBagConstraints();

        // Create buttons
        this.menuBtn1 = new JButton("Regular Vending Machine");
        this.menuBtn2 = new JButton("Special Vending Machine");
        this.menuBtn3 = new JButton("Exit");
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);

        initializeGUI();
    }

    /**
     * Initializes some of the GUI components we declared
     */
    private void initializeGUI() {
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weighty = 0.0;
        mainBG.add(titleSpace, gbc);

        // Reset gridwidth and weighty for buttons
        gbc.gridwidth = 1;
        gbc.weighty = 0.0;

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        mainBG.add(menuBtn1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainBG.add(menuBtn2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainBG.add(menuBtn3, gbc);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(mainBG);
    }

    /**
     * Setter for the Menu Button Listener    
     * 
     * @param menuButtonListener      sets the menuButtonListener for menuBtn1, menuBtn2, menuBtn3
     */
    public void setMenuButtonListener(ActionListener menuButtonListener) {
        menuBtn1.addActionListener(menuButtonListener);
        menuBtn2.addActionListener(menuButtonListener);
        menuBtn3.addActionListener(menuButtonListener);
    }

    /**
     * Gets the current frame
     * 
     * @return             frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Gets the Regular Vending Machine button in the main menu
     * 
     * @return             first menu button
     */
    public JButton getMenuButton1() {
        return menuBtn1;
    }

    /**
     * Gets the Speecial Vending Machine button in the main menu
     * 
     * @return             second menu button
     */
    public JButton getMenuButton2() {
        return menuBtn2;
    }

    /**
     * Gets the Exit button in the main menu
     * 
     * @return             third menu button
     */
    public JButton getMenuButton3() {
        return menuBtn3;
    }
}
