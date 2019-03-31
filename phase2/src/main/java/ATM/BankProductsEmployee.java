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
    private Map<Integer, Map<Integer, Map<Integer, List<Integer>>>> productRequests = new HashMap<Integer, Map<Integer, Map<Integer, List<Integer>>>>();
    private BankProductFactory bankProductFactory= new BankProductFactory();

    private final int LONGTERMMORTGAGE = 1;
    private final int SHORTTERMMORTGAGE = 2;
    private final int HIGHRISKINVESTMENT = 3;
    private final int LOWRISKINVESTMENT = 4;

    public BankProductsEmployee(BankManager bm, String password){
        this.bm = bm;
        this.password = password;
    }

    public Map<Integer, Map<Integer, Map<Integer, List<Integer>>>> getProductRequests() {
        return productRequests;
    }

    public void requestProducts(int userID, int accountID, int productType, int productAmount, int productLength){
        List<Integer> productStat = new ArrayList<>();
        productStat.add(productAmount);
        productStat.add(productLength);
        Map<Integer, List<Integer>> product = new HashMap<>();
        product.put(productType, productStat);
        Map<Integer, Map<Integer, List<Integer>>> accountToProduct = new HashMap<>();
        accountToProduct.put(accountID, product);
        productRequests.put(userID, accountToProduct);
    }

    public void createProduct(){

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

    public String getAccountName(Integer productType) {
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
