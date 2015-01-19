package moneycalculator.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import moneycalculator.model.CurrencySet;

public class CurrencySetLoader implements moneycalculator.persistence.CurrencySetLoader {

    @Override
    @SuppressWarnings("null")
    public CurrencySet load() {
        CurrencySet currencySet = new CurrencySet();
        try {
            URL url = new URL("http://openexchangerates.org/api/currencies.json");
            BufferedReader br =  new BufferedReader(new InputStreamReader(url.openStream()));
            String[] word;
            String line = br.readLine();
            line = br.readLine();
            while(!line.contains("}")) {
		word = line.split("\": \"");
                currencySet.add(new Currency(word[0].substring(2),word[1].substring(0,word[1].length()-2),null));
                line = br.readLine();
            }
            } catch (MalformedURLException ex) {
            Logger.getLogger(CurrencySetLoader.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CurrencySetLoader.class.getName()).log(Level.SEVERE, null, ex);
            }
        return currencySet;
    }
    
}
