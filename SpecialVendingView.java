import javax.swing.*;
import java.awt.*;

/**
 * 	This SpecialVendingView inherits the RegularVendingView class
 */
public class SpecialVendingView extends RegularVendingView {
    private JCheckBox isSellable;

    /**
    * 	Constriucts a special vending view
    */
    public SpecialVendingView() {
        // Call the constructor of the superclass (RegularVendingView)
        super();
    }

    /**
     * Displays the background
     * 
     * @return        The background image
     */
    @Override
    public JPanel displayBackground() {
        // get the chosen background image from Images folder
        final ImageIcon backgroundImage = new ImageIcon(getClass().getResource("Images/SPECIAL VM BG.png"));
        JPanel regVMBG = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, null);
            }
        };
        return regVMBG;
    }

    @Override
    public void CustomRVM() {
        frame.setSize(480, 680);
        JPanel regVMBG = displayBackground();

        JPanel panel = new JPanel(new GridBagLayout());

        // Item Name
        JLabel nameLabel = new JLabel("Item Name: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        nameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0; // Column index 
        gbc.gridy = 0; // Row index
        gbc.anchor = GridBagConstraints.WEST; // Anchor point
        gbc.insets = new Insets(5, 17, 5, 17); // Padding
        panel.add(nameLabel, gbc);

        textField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Columns to span
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill component
        panel.add(textField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameOptions, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        panel.add(chooseButton, gbc);

        // Item Quantity
        JLabel quantityLabel = new JLabel("Item Quantity: ");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 12));
        quantityLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(quantityOptions, gbc);

        // Item Calories
        JLabel caloriesLabel = new JLabel("Item Calories: ");
        caloriesLabel.setFont(new Font("Arial", Font.BOLD, 12));
        caloriesLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(caloriesLabel, gbc);

        JScrollPane caloriesScrollPane = new JScrollPane(caloriesInput);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(caloriesScrollPane, gbc);

        // Item Price
        JLabel priceLabel = new JLabel("Item Price: ");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 12));
        priceLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(priceLabel, gbc);

        JScrollPane priceScrollPane = new JScrollPane(priceInput);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(priceScrollPane, gbc);

        // Sellable check box
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        isSellable = new JCheckBox("Can be sold alone?");
        isSellable.setForeground(Color.WHITE);
        isSellable.setOpaque(false);
        panel.add(isSellable, gbc);

        // Save Button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(this.saveButton, gbc);

        panel.setOpaque(false); // Make the panel transparent

        regVMBG.add(panel);

        frame.setIconImage(icon.getImage());
        frame.getContentPane().add(regVMBG);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     * Gets the checkbox that asks if the item is sellable or not
     * 
     * @return             Name of the item
     */
    public JCheckBox getSellableCheckBox(){
        return this.isSellable;
    }
}

