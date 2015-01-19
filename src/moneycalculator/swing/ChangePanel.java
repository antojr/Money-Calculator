package moneycalculator.swing;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangePanel extends JPanel {
    
    private JTextField text;
    private JLabel symbol;
    
    public ChangePanel() {
        createWidgets();
    }

    private void createWidgets() {
        this.add(new JLabel("Cambio:   "));
        this.text = new JTextField();
        this.text.setColumns(13);
        this.text.setBackground(Color.white);
        this.text.setEditable(false);
        this.add(this.text);
        this.symbol = new JLabel();
        this.add(this.symbol);
    }
    
    public void setText(String texto) {
        this.text.setText(texto);
    }
    
    public void setSymbol(String symbol){
        this.symbol.setText(symbol);
    }
}
