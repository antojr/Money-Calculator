package moneycalculator.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import moneycalculator.json.CurrencySetLoader;
import moneycalculator.swing.ChangePanel;
import moneycalculator.ui.ExchangeDialog;

public class ExchangeOperation {
    
    private final ExchangeDialog dialog;
    private final ChangePanel change;

    public ExchangeOperation(ExchangeDialog dialog, ChangePanel change) {
        this.dialog = dialog;
        this.change = change;
    }
    
    public void execute() {
        URL url;
        BufferedReader br;
        try {
            url = new URL("http://www.freecurrencyconverterapi.com/api/v2/convert?q=" 
                    + dialog.getExchange().getMoney().getCurrency().getCode() 
                    + "_" + dialog.getExchange().getCurrency().getCode() +"&compact=y");
            br =  new BufferedReader(new InputStreamReader(url.openStream()));
            String json = br.readLine();
            int index1 = json.indexOf("val\":") + 5;
            int index2 = json.indexOf("}}");
            Double result = Double.parseDouble(json.substring(index1, index2))
                            * dialog.getExchange().getMoney().getAmount();
            this.change.setSymbol(dialog.getExchange().getCurrency().getSymbol());
            this.change.setText(result.toString());
  
        } catch (MalformedURLException ex) {
            Logger.getLogger(CurrencySetLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e2) {
            JOptionPane.showMessageDialog(null, "Debes introducir un numero");
        } catch (IOException ex) {
            Logger.getLogger(CurrencySetLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
