package ATM;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;
import javax.rmi.CORBA.Util;
import java.util.*;
import java.util.Collection.*;

public class HelperMethods {

    public static MonetaryAmount exchangeCurrency(MonetaryAmount amount, CurrencyUnit conversionTo){

        CurrencyUnit prior = amount.getCurrency();
        CurrencyConversion conversion = MonetaryConversions.getConversion(conversionTo);
        return amount.with(conversion);
    }

    public static List<CurrencyUnit> getAvailableCurrencyUnit(){
        Collection<Currency> unSortedCurrenies = Currency.getAvailableCurrencies();
        List<CurrencyUnit> currencyUnits = new ArrayList<>();
        for(Currency c:unSortedCurrenies){
            currencyUnits.add(Monetary.getCurrency(c.toString()));
        }
        Collections.sort(currencyUnits);
        return currencyUnits;

    }
}
