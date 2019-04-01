Major changelist for Phase 2:
- Added a GUI
- Added foreign currencies
- Added Bank Product (Mortgages and Investments)


How to run this program:

1. Run the Main.java in the ATM folder
2. This should open up a GUI with the current time and a bank logo at the top
3. Select whether you are a User(customer) / Bank Manager / Bank Product Employee or if you are a new customer,'New User'

As a customer/user:
    After selecting User on the main menu, it will prompt the user for a UserID and Password.
        An example user has been setup with UserID: 1 Password: abc123
    After successful login, the user will have a choice between User Actions or Transactions.

    User Action options:
        - Change Password
        - Request a New Account
            Options: Joint(True|False), Account Type (Chequing, Saving, etc), Currency
        - Request a Bank Product
            Options: Type(Mortgage, Investment), Account, Amount, Duration(months)
        - Change Primary Account
        - Account Summary
        - View Transaction History
        - View Balance
        - View Net Total

    Transaction options:
        - Deposit
        - Withdraw
        - Pay Bills
        - Transfer money to another account


As a bank employee:
    A bank employee will login like a normal user but will also have an extra option "Employee Actions" in the main menu in addition to User Actions and Transactions like a customer/user.
        An example employee has been set up with UserID: 3 Password: abc123

    See Customer/User documentation above for User Actions and Transactions

    Employee Actions:
        - Buy Foreign Currency
            Enter the amount of foreign currency to purchase in ForeignCurrencyPurchase.txt the click "Purchase"
        - Set Currency Maximum Storage
            Allows the employee to set the max stock of the ATM for when the ATM gets restocked by the Bank Manager.
            Limited to the local currency.
        - View Cash Storage
            Displays the amount of each bill in stock


As the Bank Manager:
        After selecting Bank Manager on the main menu, it will prompt the user for the Password. (Defaulted to 'abc123')
        There are 4 menu choices for the Bank Manager:

    Restock ATM
        Refills the ATM to full, only refills the local currency

    Set Interest Rate
        Displays current interest rates and allows Bank Manager to set the interest rate for certain account types

    View Account Requests
        Displays all account creation requests, shows which User is requested what type of account.
        Select the request number to approve and create the account.

    View Undo Transaction Requests
        Displays all transaction undo requests and allows the manager to decide whether to undo or not.

As Bank Product Employee:
        After selecting Bank Product Employee on the main menu, it will prompt the user for the Password. (Defaulted to 'abc123')
        There is only 1 job the employee has which is managing product requests.

    View Product Request
        Similar to Manager's account creation request, this will display all current requests and can select the request to approve.


