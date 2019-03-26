import org.mariuszgromada.math.mxparser.Expression;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import java.util.Stack;

public class Form extends JFrame {
    private JList<ItemListModel> list1;
    private JTextField textField1;
    private JButton evaluateButton;
    private JPanel panel;
    private JTextArea textArea1;
    private Stack<String> equationStack;
    private Stack<String> tmpEquationStack;
    private Double lastResult;


    Form() throws HeadlessException {
        super("Calculator");
        setContentPane(panel);
        setJMenuBar(createMenu());
        setPreferredSize(new Dimension(1024,768));
        setMinimumSize(new Dimension(500,300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        evaluateButton.setActionCommand("evaluateBtn");
        evaluateButton.addActionListener(new ButtonClickListener());
        textField1.addKeyListener(new KeyPressedListener());
        textArea1.setEditable(false);
        listSetup();
        pack();
        setVisible(true);
        textField1.requestFocusInWindow();
        equationStack = new Stack<String>();
        tmpEquationStack = new Stack<String>();
    }

    private void listSetup(){
        DefaultListModel<ItemListModel> listModel = new DefaultListModel<>();
        list1.setModel(listModel); 
        listModel.addElement(new ItemListModel("Cosine","cos()",true));
        listModel.addElement(new ItemListModel("Sine","sin()",true));
        listModel.addElement(new ItemListModel("Tangent ","tg()",true));
        listModel.addElement(new ItemListModel("Cotangent ","ctg()",true));
        listModel.addElement(new ItemListModel("Natural logarithm ","ln()",true));
        listModel.addElement(new ItemListModel("Pi ","pi",false));
        listModel.addElement(new ItemListModel("Euler's number","e",false));
        listModel.addElement(new ItemListModel("Golden ratio ","[phi]",false));
        listModel.addElement(new ItemListModel("Factorial ","!",false));
        listModel.addElement(new ItemListModel("Modulo ","#",false));
        listModel.addElement(new ItemListModel("Exponentiation ","^",false));


        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list1.addListSelectionListener(new ListEventListener());




    }

    private JMenuBar createMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenuItem reset = new JMenuItem("Reset");
        JMenuItem exit = new JMenuItem("Exit");

        reset.setActionCommand("resetMenuItem");
        exit.setActionCommand("exitMenuItem");

        reset.addActionListener(new MenuEventListener());
        exit.addActionListener(new MenuEventListener());

        options.add(reset);
        options.add(exit);
        menuBar.add(options);

        return menuBar;

    }

    private void evaluate(){
        String textField1Text = textField1.getText();
        Expression expr = new Expression(textField1Text);

        if(expr.checkSyntax()){

            equationStack.push(textField1Text);
            String messageFormatPattern = "{0} = {1,number}\n";
            double result = expr.calculate();
            textArea1.append(MessageFormat.format(messageFormatPattern,textField1Text,result));
            textField1.setText("");
            lastResult = result;
        }
        else{
            JOptionPane.showMessageDialog(null, "We can't calculate it!", "SYNTAX ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setFocus(boolean hasBraces){
        textField1.requestFocus();
        if (hasBraces) {
            textField1.setCaretPosition(textField1.getText().length() - 1);
        } else {
            textField1.setCaretPosition(textField1.getText().length());
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if( command.equals( "evaluateBtn" ))  {
                evaluate();
            }
        }
    }

    private class KeyPressedListener implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                evaluate();
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP )
            {
                if(tmpEquationStack.size()==0){
                    tmpEquationStack = new Stack<>();
                    tmpEquationStack.addAll(equationStack);

                }

                if(tmpEquationStack.size() > 0){
                    textField1.setText(tmpEquationStack.pop());
                }

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    private class MenuEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("exitMenuItem")){
                System.out.println("Application exited.");
                System.exit(0);
            }
            else if(command.equals("resetMenuItem")){
                textArea1.setText("");
                textField1.setText("");
                equationStack.clear();
                tmpEquationStack.clear();
                lastResult = null;
            }
        }
    }

    private class ListEventListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {

            if(e.getValueIsAdjusting() && list1.getSelectedIndex()>=0) {
                ItemListModel item = list1.getSelectedValue();
                textField1.setText(textField1.getText() + item.getValue());
                setFocus(item.hasBraces());
                list1.clearSelection();
            }
        }
    }
}
