package ATM;


import org.javamoney.moneta.FastMoney;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

public class Main {

    public static void main(String[] args) {

        CurrencyUnit usd = Monetary.getCurrency("USD");
        CurrencyUnit cad = Monetary.getCurrency("CAD");
        System.out.println(usd.getDefaultFractionDigits() + " " + usd.getNumericCode());
        MonetaryAmount amt = Monetary.getDefaultAmountFactory().setCurrency(usd).setNumber(200.03).create();

        Money usdMoney = Money.of(12.00, usd);
        Money cadMoney = Money.of(15.00, cad);

        CurrencyConversion conversionCAD = MonetaryConversions.getConversion(cad);

        MonetaryAmount converted = usdMoney.with(conversionCAD);

        System.out.println(converted.toString());

        ATM_Machine atm = new ATM_Machine();
        //atm.run();


    }
}
