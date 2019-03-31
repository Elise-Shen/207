package ATM;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

public class HelperMethods {

    public static MonetaryAmount exchangeCurrency(MonetaryAmount amount, CurrencyUnit conversionTo){

        CurrencyUnit prior = amount.getCurrency();
        CurrencyConversion conversion = MonetaryConversions.getConversion(conversionTo);
        return amount.with(conversion);
    }
}
