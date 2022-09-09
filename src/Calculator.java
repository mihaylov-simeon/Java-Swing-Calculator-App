import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

public class Calculator extends JFrame implements ActionListener {

    JFrame frame;
    JTextField textField;
    JButton[] numbers = new JButton[10];
    JButton[] functions = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;
  //  JButton historyButton;

    Font myFont = new Font("Century Gothic", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 610);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(0x242424));

        textField = new JTextField();
        textField.setBounds(40, 17, 330, 80);
        textField.setBackground(new Color(0x606060));
        textField.setForeground(Color.WHITE);
        textField.setFont(myFont);
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        negButton = new JButton("(-)");
    //    historyButton = new JButton("History");

        frame.add(textField);
        functions[0] = addButton;
        functions[1] = subButton;
        functions[2] = mulButton;
        functions[3] = divButton;
        functions[4] = decButton;
        functions[5] = equButton;
        functions[6] = delButton;
        functions[7] = clrButton;
        functions[8] = negButton;
    //    functions[9] = historyButton;

        for (int index = 0; index < 9; index++) {
            functions[index].addActionListener(this);
            functions[index].setFont(myFont);
            functions[index].setFocusable(false);
            functions[index].setBackground(new Color(0x333333));
            functions[index].setForeground(Color.LIGHT_GRAY);
            functions[index].setBorder(new RoundedBtn(10));
            int finalIndex = index;
            functions[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    functions[finalIndex].setBackground(new Color(0x454545));
                    functions[finalIndex].setBorderPainted(false);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    functions[finalIndex].setBackground(new Color(0x333333));
                    functions[finalIndex].setBorderPainted(true);
                }
            });
        }

        for (int index = 0; index < 10; index++) {
            numbers[index] = new JButton(String.valueOf(index));
            numbers[index].addActionListener(this);
            numbers[index].setFont(myFont);
            numbers[index].setFocusable(false);
            numbers[index].setBackground(new Color(0x454545));
            numbers[index].setForeground(Color.LIGHT_GRAY);
            numbers[index].setBorder(new RoundedBtn(10));
            int finalIndex = index;
            numbers[index].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    numbers[finalIndex].setBackground(new Color(0x333333));
                    numbers[finalIndex].setBorderPainted(false);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    numbers[finalIndex].setBackground(new Color(0x454545));
                    numbers[finalIndex].setBorderPainted(true);
                }
            });
        }

        negButton.setBounds(40, 430, 105, 50);
        delButton.setBounds(153, 430, 105, 50);
        clrButton.setBounds(265, 430, 105, 50);
       // historyButton.setBounds(40, 500, 330, 50);

        panel = new JPanel();
        panel.setBounds(40, 110, 330, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(0x242424));
        panel.add(numbers[1]);
        panel.add(numbers[2]);
        panel.add(numbers[3]);
        panel.add(addButton);
        panel.add(numbers[4]);
        panel.add(numbers[5]);
        panel.add(numbers[6]);
        panel.add(subButton);
        panel.add(numbers[7]);
        panel.add(numbers[8]);
        panel.add(numbers[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numbers[0]);
        panel.add(divButton);
        panel.add(equButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
     //   frame.add(historyButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int index = 0; index < 10; index++) {
            if (e.getSource() == numbers[index]) {
                textField.setText(textField.getText()
                        .concat(String.valueOf(index)));
            }
        }
        if (e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }
            num1 = result;
            textField.setText(String.valueOf(result));
        }
        if (e.getSource() == clrButton) {
            textField.setText("");
        }
        if (e.getSource() == delButton) {
            String currentText = textField.getText();
            textField.setText("");

            for (int index = 0; index < currentText.length() - 1; index++) {
                textField.setText(textField.getText() + currentText.charAt(index));
            }
        }
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *= -1;
            textField.setText(String.valueOf(temp));
        }

//        if (e.getSource() == historyButton) {
//            try {
//                JTextArea textArea = new JTextArea(20, 40);
//                textArea.read(new FileReader(("D:\\Calculator\\src\\history.txt")), null);
//                textArea.setText(textField.getText());
//                textArea.setEditable(false);
//                JOptionPane.showMessageDialog(historyButton, new JScrollPane(textArea));
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
    }
}
