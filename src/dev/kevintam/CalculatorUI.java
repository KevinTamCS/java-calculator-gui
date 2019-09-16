package dev.kevintam;

import javax.swing.*;
import java.awt.*;

/**
 * The main calculator UI.
 */
class CalculatorUI {

    /**
     * Creates the main calculator view.
     */
    CalculatorUI() {

        // Create a new JFrame
        JFrame frame = new JFrame();
        frame.setTitle("Java Calculator");
        frame.setSize(300, 450);
        frame.setResizable(false);
        frame.setLayout(null);

        // Calculator display
        JTextField calculatorDisplay = new JTextField("0", 20);
        calculatorDisplay.setBounds(25, 25, 250, 50);
        calculatorDisplay.setFont(new Font("Arial", Font.PLAIN, 24));
        calculatorDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        frame.add(calculatorDisplay);

        // Left 3 columns
        String[] buttonValues =
                {"AC", "C", "%",
                 "7", "8", "9",
                 "4", "5", "6",
                 "1", "2", "3",
                 "0", ".", "="};
        generateButtons(frame, buttonValues, 35, 100,60, 3);

        // Top 3 buttons of fourth column
        buttonValues = new String[] {"/", "*", "-"};
        generateButtons(frame, buttonValues, 215, 100, 60, 1);

        // Plus button
        JButton button = new JButton("+");
        button.setBounds(215, 280, 50, 110);
        frame.add(button);

        // Render the calculator after all elements are finished drawing
        frame.setVisible(true);
    }

    /**
     * Generates buttons with an array of strings.
     * @param frame The JFrame to draw elements on.
     * @param buttonValues The value of the buttons to use.
     * @param startX The horizontal starting position.
     * @param startY The vertical starting position.
     * @param buttonSpacing The spacing between the buttons.
     * @param buttonsPerRow The number of buttons per row.
     */
    private void generateButtons(JFrame frame, String[] buttonValues, int startX, int startY, int buttonSpacing, int buttonsPerRow) {
        int divideCounter = 0;
        int posX = startX;
        int posY = startY;

        for (String buttonValue : buttonValues) {
            // Reset position every 3 buttons
            if (divideCounter == buttonsPerRow) {
                posX = startX;
                posY += buttonSpacing;
                divideCounter = 0;
            }

            // Create the button
            JButton button = new JButton(buttonValue);
            button.setBounds(posX, posY, 50, 50);
            frame.add(button);

            // Increment positions
            posX += buttonSpacing;
            divideCounter++;
        }
    }
}
