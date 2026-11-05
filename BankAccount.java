public class BankAccount {
    // Each account stores its own information and balance.
    private final String accountNumber;
    private final String accountHolderName;
    private double accountBalance;

    public BankAccount(String accountNumber, String accountHolderName, double startBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number cannot be empty.");
        }
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder's name cannot be empty.");
        }
        if (!Double.isFinite(startBalance) || startBalance < 0) {
            throw new IllegalArgumentException("Starting balance must be zero or greater.");
        }

        this.accountNumber = accountNumber.trim();
        this.accountHolderName = accountHolderName.trim();
        this.accountBalance = startBalance;
    }

    // Getters (encapsulation)
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    // 1. Deposit funds. Return true only if the deposit succeeded.
    public boolean depositFunds(double funds) {
        if (!Double.isFinite(funds) || funds <= 0) {
            System.out.println("Deposit amount must be greater than zero.");
            return false;
        }
        if (!Double.isFinite(accountBalance + funds)) {
            System.out.println("Deposit failed: amount is too large.");
            return false;
        }

        accountBalance += funds;
        System.out.printf("Amount deposited: $%.2f%n", funds);
        System.out.printf("New account balance: $%.2f%n", accountBalance);
        return true;
    }

    // 2. Withdraw funds. Return true only if the withdrawal succeeded.
    public boolean withdrawFunds(double funds) {
        if (!Double.isFinite(funds) || funds <= 0) {
            System.out.println("Withdrawal amount must be greater than zero.");
            return false;
        }
        if (funds > accountBalance) {
            System.out.println("Withdrawal failed: insufficient funds.");
            System.out.printf("Current balance: $%.2f%n", accountBalance);
            return false;
        }

        accountBalance -= funds;
        System.out.printf("Amount withdrawn: $%.2f%n", funds);
        System.out.printf("New account balance: $%.2f%n", accountBalance);
        return true;
    }

    // 3. Display account information.
    public void displayAccountInfo() {
        System.out.println("\nAccount Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.printf("Balance: $%.2f%n", accountBalance);
    }
}
