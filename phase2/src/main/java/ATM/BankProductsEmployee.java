package ATM;

import Accounts.*;
import BankProducts.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankProductsEmployee implements Serializable {

    private BankManager bm;
    private String password;
    private Map<Integer, List<Integer>> productRequests = new HashMap<Integer, List<Integer>>();
    private BankProductFactory bankProductFactory= new BankProductFactory();

    private final int LONGTERMMORTGAGE = 1;
    private final int SHORTTERMMORTGAGE = 2;
    private final int HIGHRISKINVESTMENT = 3;
    private final int LOWRISKINVESTMENT = 4;

    public BankProductsEmployee(BankManager bm, String password){
        this.bm = bm;
        this.password = password;
    }

    public Map<Integer, List<Integer>> getProductRequests() {
        return productRequests;
    }

    public void requestProducts(int userID, int accountID, int productType, int productAmount, int productLength){
        List<Integer> productStat = new ArrayList<>();
        productStat.add(accountID);
        productStat.add(productType);
        productStat.add(productAmount);
        productStat.add(productLength);
        productRequests.put(userID, productStat);
    }

    public void createProduct(int mapKey, int accountID, int productType, int productAmount, int productLength){
        Account account = bm.getOneAccount(accountID);
        BankProduct bankProduct = bankProductFactory.getBankProduct(productType, productAmount, productLength, account);
        ((Saving)account).addBankProducts(bankProduct);

        System.out.println("Created a new " + getProductName(productType) + " for User " + bm.getUser(mapKey) +
                "in account " + accountID + ".");
    }

    public User getUser(int userID){ return(bm.getUser(userID)); }

    public String getPassword() { return password; }

    public void updateSaving(LocalDate localDate) {
        List<Account> allAccounts = bm.getAllAccounts();
        for (Account a : allAccounts) {
            if (a instanceof Saving) {
                List<BankProduct> allProducts = ((Saving) a).getBankProducts();
                for (BankProduct p: allProducts){
                    if (!p.getIsFinished() && p.getDateEnd().equals(localDate)){
                        if (p instanceof InvestmentProduct) { ((InvestmentProduct)p).returnRevenue();}
                        else { ((MortgageProduct)p).returnloan();}
                    }
                }
            }
        }
    }

    public String getProductName(Integer productType) {
        String name= "";
        switch (productType){
            case LONGTERMMORTGAGE:
                name = "LongTermMortgage";
                break;
            case SHORTTERMMORTGAGE:
                name = "ShortTermMortgage";
                break;
            case HIGHRISKINVESTMENT:
                name = "HighRiskInvestment";
                break;
            case LOWRISKINVESTMENT:
                name = "LowRiskInvestment";
                break;
        }
        return name;
    }
}
