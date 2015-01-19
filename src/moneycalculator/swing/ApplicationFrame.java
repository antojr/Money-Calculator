package moneycalculator.swing;

import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEFT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import moneycalculator.model.CurrencySet;
import moneycalculator.ui.ExchangeDialog;

public class ApplicationFrame extends JFrame {
    
    private final CurrencySet currencySet;
    private Map<String,ActionListener> listeners;
    private ExchangeDialog exchangeDialog;
    private ChangePanel change;

    public ApplicationFrame(CurrencySet currencySet) {
        super();
        this.currencySet = currencySet;
        this.listeners = new HashMap<>();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(750,150);
        this.setTitle("Money Calculator");
        this.createWidgets();
        this.setVisible(true);
    }

    public void register(String command, ActionListener actionListener) {
        this.listeners.put(command, actionListener);
    }

    public ExchangeDialog getDialog() {
        return exchangeDialog;
    }
    
    public ChangePanel getChange() {
        return this.change;
    }

    private void createWidgets() {
        FlowLayout f = new FlowLayout();
        f.setAlignment(LEFT);
        setLayout(f);
        this.add(createDialog());
        this.add(createCalculateButton());
        this.add(createChangePanel());
    }

    private JButton createCalculateButton() {
        JButton button = new JButton("Calcular");
        button.addActionListener(createListener("Calcular"));
        return button;
    }

    private ActionListener createListener(String text) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                listeners.get(text).actionPerformed(event);
            }
        };
    }

    private JPanel createDialog() {
        ExchangeDialogPanel panel = new ExchangeDialogPanel(currencySet);
        this.exchangeDialog = panel;
        return panel;
    }

    private JPanel createChangePanel() {
        ChangePanel panel = new ChangePanel();
        this.change = panel;
        return panel;
    }
    
}
