
package moneycalculator.swing;

import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEFT;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;
import moneycalculator.model.Exchange;
import moneycalculator.model.Money;
import moneycalculator.ui.ExchangeDialog;

public class ExchangeDialogPanel extends JPanel implements ExchangeDialog {
    
    private final CurrencySet currencySet;
    private JTextField amount;
    private JComboBox<Currency> fromCurrency;
    private JComboBox<Currency> toCurrency;

    public ExchangeDialogPanel(CurrencySet currencySet) {
        this.currencySet = currencySet;
        setLayout(new FlowLayout(LEFT));
        createWidgets();
    }
    
    @Override
    public Exchange getExchange() {
        return new Exchange(new Money(getAmount(), getFromCurrency()), getToCurrency());
    }

    private void createWidgets() {
        this.add(new JLabel("Cantidad: "));
        this.add(createAmountWidget());
        this.add(createFromCurrencyWidget());
        this.add(createToCurrencyWidget());
    }

    private JTextField createAmountWidget() {
        JTextField amount = new JTextField();
        amount.setColumns(13);
        this.amount = amount;
        return amount;
    }

    private JComboBox createFromCurrencyWidget() {
        JComboBox<Currency> combo = new JComboBox<Currency>(currencySet.getItems());
        combo.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent event) {
                if (event.getStateChange() == ItemEvent.DESELECTED) return;
                addCurrenciesToTargetCombobox();
            }

        });
        this.fromCurrency = combo;
        return combo;
    }

    private JComboBox createToCurrencyWidget() {
        JComboBox<Currency> combo = new JComboBox<Currency>();
        this.toCurrency = combo;
        addCurrenciesToTargetCombobox();
        return combo;
    }

    private double getAmount() throws NumberFormatException{
        return Double.parseDouble(amount.getText());
    }
    
    private Currency getFromCurrency() {
        return fromCurrency.getItemAt(fromCurrency.getSelectedIndex());
    }
    
    private Currency getToCurrency() {
        return toCurrency.getItemAt(toCurrency.getSelectedIndex());
    }

    private void addCurrenciesToTargetCombobox() {
        toCurrency.removeAllItems();
        for (Currency currency : currencySet) {
            if (currency == getFromCurrency()) continue;
            toCurrency.addItem(currency);
        }
    }

    
}
