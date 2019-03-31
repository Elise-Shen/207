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

    /**
     * store the info needed to create new product
     * @param userID
     * @param accountID
     * @param productType
     * @param productAmount
     * @param productLength
     */
    public void requestProducts(int userID, int accountID, int productType, int productAmount, int productLength){
        List<Integer> productStat = new ArrayList<>();
        productStat.add(accountID);
        productStat.add(productType);
        productStat.add(productAmount);
        productStat.add(productLength);
        // a map that map user ID to the info needed to create a new product
        productRequests.put(userID, productStat);
    }

    /**
     * create product with given info
     * @param mapKey
     * @param accountID
     * @param productType
     * @param productAmount
     * @param productLength
     */
    public void createProduct(int mapKey, int accountID, int productType, int productAmount, int productLength){
        boolean isValid;
        Account account = bm.getOneAccount(accountID);
        BankProduct bankProduct = bankProductFactory.getBankProduct(productType, productAmount, productLength, account);
        ((Saving)account).addBankProducts(bankProduct);
        //Determine create mortgage or investment product
        if (productType == LONGTERMMORTGAGE || productType == SHORTTERMMORTGAGE){
            ((MortgageProduct)bankProduct).giveLoan();
            isValid = true;
        } else {isValid = ((InvestmentProduct)bankProduct).doInvestment();}

        // give user feedback of their action
        if (isValid) {
            System.out.println("Created a new " + getProductName(productType) + " for User " + bm.getUser(mapKey).getUserID() +
                " in account " + accountID + ".");
        } else {
            ((Saving)account).removeLastBankProducts();
            System.out.println("Fail to create a new investment.");
        }
    }

    public User getUser(int userID){ return(bm.getUser(userID)); }

    public String getPassword() { return password; }

    /**
     * update the date info for mortage and investment, which will affect the give back loan/revenue
     * @param localDate
     */
    public void updateSaving(LocalDate localDate) {
        List<Account> allAccounts = bm.getAllAccounts();
        for (Account a : allAccounts) {
            if (a instanceof Saving) {
                List<BankProduct> allProducts = ((Saving) a).getBankProducts();
                for (BankProduct p: allProducts){
                    if (!p.getIsFinished() && p.getDateEnd().equals(localDate)){
                        if (p instanceof InvestmentProduct) { ((InvestmentProduct)p).returnRevenue();}
                        else { ((MortgageProduct)p).returnLoan();}
                    }
                }
            }
        }
    }

    public String getProductName(Integer productType) {
        String name= "";
        switch (productType){
            case LONGTERMMORTGAGE:
                name = "Long Term Mortgage";
                break;
            case SHORTTERMMORTGAGE:
                 name = "Short Term Mortgage";
                break;
            case HIGHRISKINVESTMENT:
                name = "High Risk Investment";
                break;
            case LOWRISKINVESTMENT:
                name = "Low Risk Investment";
                break;
        }
        return name;
    }
}
