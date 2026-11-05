import java.util.ArrayList;

public class Bank {
    // The bank manages a collection of BankAccount objects.
    private final ArrayList<BankAccount> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // 1. Create a new account.
    public void createAccount(String accountNumber, String accountHolderName, double startBalance) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            System.out.println("Account number cannot be empty.");
            return;
        }
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            System.out.println("Account holder's name cannot be empty.");
            return;
        }
        if (!Double.isFinite(startBalance) || startBalance < 0) {
            System.out.println("Starting balance must be zero or greater.");
            return;
        }
        if (accountSearchbyNum(accountNumber) != null) {
            System.out.println("An account with that number already exists.");
            return;
        }

        BankAccount newAccount = new BankAccount(accountNumber, accountHolderName, startBalance);
        accounts.add(newAccount);
        System.out.println("Your bank account has been created successfully.");
    }

    // 2. Search by account number.
    public BankAccount accountSearchbyNum(String accountNumber) {
        if (accountNumber == null) {
            return null;
        }
        String searchNumber = accountNumber.trim();
        for (BankAccount targetAccount : accounts) {
            if (targetAccount.getAccountNumber().equals(searchNumber)) {
                return targetAccount;
            }
        }
        return null;
    }

    // 3. Search by all or part of the account holder's name (case-insensitive).
    public ArrayList<BankAccount> accountSearchbyName(String holderName) {
        ArrayList<BankAccount> matches = new ArrayList<>();
        if (holderName == null || holderName.trim().isEmpty()) {
            return matches;
        }

        String searchName = holderName.trim().toLowerCase(java.util.Locale.ROOT);
        for (BankAccount targetAccount : accounts) {
            if (targetAccount.getAccountHolderName()
                    .toLowerCase(java.util.Locale.ROOT).contains(searchName)) {
                matches.add(targetAccount);
            }
        }
        return matches;
    }

    // 4. View account information.
    public void viewAccountInfo(String accountNumber) {
        BankAccount targetAccount = accountSearchbyNum(accountNumber);
        if (targetAccount != null) {
            targetAccount.displayAccountInfo();
        } else {
            System.out.println("Account does not exist.");
        }
    }

    // 5. Deposit into an account.
    public void accountdeposit(String accountNumber, double funds) {
        BankAccount targetAccount = accountSearchbyNum(accountNumber);
        if (targetAccount != null) {
            targetAccount.depositFunds(funds);
        } else {
            System.out.println("Account does not exist.");
        }
    }

    // 6. Withdraw from an account.
    public void withdrawals(String accountNumber, double funds) {
        BankAccount targetAccount = accountSearchbyNum(accountNumber);
        if (targetAccount != null) {
            targetAccount.withdrawFunds(funds);
        } else {
            System.out.println("Account does not exist.");
        }
    }

    // 7. Transfer money between different accounts.
    public void fundTransfer(String srcAccountNum, String destAccountNum, double funds) {
        BankAccount srcAccount = accountSearchbyNum(srcAccountNum);
        BankAccount destAccount = accountSearchbyNum(destAccountNum);

        if (srcAccount == null || destAccount == null) {
            System.out.println("Transfer failed: one or both accounts were not found.");
            return;
        }
        if (srcAccount == destAccount) {
            System.out.println("Transfer failed: choose two different accounts.");
            return;
        }
        if (!Double.isFinite(funds) || funds <= 0) {
            System.out.println("Transfer amount must be greater than zero.");
            return;
        }
        if (srcAccount.getAccountBalance() < funds) {
            System.out.println("Transfer failed: insufficient funds.");
            return;
        }
        if (!Double.isFinite(destAccount.getAccountBalance() + funds)) {
            System.out.println("Transfer failed: destination balance would be too large.");
            return;
        }

        // All checks pass. Only report success if both operations succeed.
        if (srcAccount.withdrawFunds(funds)) {
            if (destAccount.depositFunds(funds)) {
                System.out.printf("Successfully transferred $%.2f.%n", funds);
            } else {
                // Restore the money if the deposit unexpectedly fails.
                srcAccount.depositFunds(funds);
                System.out.println("Transfer failed; source balance restored.");
            }
        }
    }

    // 8. Delete an account.
    public void accountDelete(String accountNumber) {
        BankAccount targetAccount = accountSearchbyNum(accountNumber);
        if (targetAccount != null) {
            accounts.remove(targetAccount);
            System.out.println("Account has been deleted.");
        } else {
            System.out.println("Account does not exist.");
        }
    }
}
