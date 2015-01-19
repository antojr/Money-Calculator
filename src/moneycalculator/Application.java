package moneycalculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import moneycalculator.control.ExchangeOperation;
import moneycalculator.model.CurrencySet;
import moneycalculator.json.CurrencySetLoader;
import moneycalculator.swing.ApplicationFrame;

public class Application {

    public static void main(String[] args) {
        
        CurrencySet currencySet = new CurrencySetLoader().load();
        ApplicationFrame frame = new ApplicationFrame(currencySet);
        frame.register("Calcular", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                new ExchangeOperation(frame.getDialog(), frame.getChange()).execute();
            }
            
        });
    }
    
}
