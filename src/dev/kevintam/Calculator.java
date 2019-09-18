package dev.kevintam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main calculator UI.
 */
class Calculator {

    private JTextField calculatorDisplay;

    private double currentValue;
    private double previousValue;
    private String function;

    /**
     * Creates the main calculator view.
     */
    Calculator() {
        createCalculatorUI();
    }

    /**
     * Creates the calculator's UI.
     */
    private void createCalculatorUI() {
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

        this.calculatorDisplay = calculatorDisplay;

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

            // Create the button and add the action listener
            JButton button = new JButton(buttonValue);
            button.setBounds(posX, posY, 50, 50);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    executeButtonAction(buttonValue);
                }
            });
            frame.add(button);

            // Increment positions
            posX += buttonSpacing;
            divideCounter++;
        }
    }

    /**
     * Executes the action shown on the button.
     * @param buttonValue The button pressed.
     */
    private void executeButtonAction(String buttonValue) {

        Object button = null;
        // Test if button pressed is number button
        try {
            button = Integer.parseInt(buttonValue);
        } catch (NumberFormatException e) {
            switch (buttonValue) {
                case "AC":
                    // Completely clear the calculator's memory
                    currentValue = 0.0;
                    previousValue = 0.0;
                    function = "";
                    break;
                case "C":
                    // Clear the currently displayed value
                    currentValue = 0.0;
                    break;
                case "+":

                    break;
                case "-":
                    currentValue -= previousValue;
                    break;
                case "*":
                    currentValue *= previousValue;
                    break;
                case "/":
                    if (!(previousValue == 0)) {
                        currentValue /= previousValue;
                    }
                    break;
                case "%":
                    // TODO percent
                    break;
                case "=":
                    // TODO equals
                    break;
            }

        }

        if (button instanceof Integer) {
            // Set currentValue to the pressed button if it is 0
            if (currentValue == 0.0) {
                currentValue = Double.parseDouble(buttonValue);
            } else {
                // Append the value of the pressed button to the current value
                currentValue += Double.parseDouble(currentValue + buttonValue);
            }
        }

        // Update the display
        calculatorDisplay.setText(Double.toString(currentValue));
    }
}
