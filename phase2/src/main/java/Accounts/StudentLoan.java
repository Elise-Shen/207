package Accounts;

public class StudentLoan extends DebtAccount {
    public StudentLoan(String currency){super(currency);}

    @Override
    public int getAccountType() {return 5; }

    @Override
    public String toString() {
        return "Student Loan";
    }
}
