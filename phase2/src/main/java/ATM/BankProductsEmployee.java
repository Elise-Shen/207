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
    private Map<List<Integer>, Map<String, Integer>> productRequests = new HashMap<>();

    public BankProductsEmployee(BankManager bm, String password){
        this.bm = bm;
        this.password = password;
    }

    public void requestAccount(int userID, int accountID, int productType){
        Map<String, Integer> value = new HashMap<>();
        value.put(currency, accountType);
        List<Integer> user = new ArrayList<>();
        user.add(userID);
        productRequests.put(user, value);
    }

    public String getPassword() { return password; }

    public void updateSaving(LocalDate localDate) {
        List<Account> allAccounts = bm.getAllAccounts();
        for (Account a : allAccounts) {
            if (a instanceof Saving) {
                List<BankProduct> allProducts = ((Saving) a).getBankProducts();
                for (BankProduct p: allProducts){
                    if (!p.getIsFinshed() && p.getDateEnd().equals(localDate)){
                        if (p instanceof InvestmentProduct) { p.returnRevenue();}
                        else { p.returnLoan();}
                    }
                }
            }
        }
    }
}
