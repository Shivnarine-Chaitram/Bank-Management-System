import java.util.ArrayList;
import java.util.Scanner;

public class ManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        boolean processing = true;

        while (processing) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create New Account");
            System.out.println("2. View Account Information");
            System.out.println("3. Deposit Funds");
            System.out.println("4. Withdraw Funds");
            System.out.println("5. Transfer Funds");
            System.out.println("6. Search for Accounts");
            System.out.println("7. Delete Account");
            System.out.println("8. Exit");

            // Read a complete line to avoid nextInt()/nextLine() problems.
            int choice = readInt(scanner, "Please enter the number of your choice: ");

            switch (choice) {
                case 1: {
                    String accountNum = readNonEmpty(scanner, "Please enter the account number: ");
                    String accountName = readNonEmpty(scanner, "Please enter the account holder's name: ");
                    double startBalance = readAmount(scanner, "Please enter the starting balance: ", true);
                    bank.createAccount(accountNum, accountName, startBalance);
                    break;
                }

                case 2: {
                    String accountNumber = readNonEmpty(scanner, "Please enter the account number: ");
                    bank.viewAccountInfo(accountNumber);
                    break;
                }

                case 3: {
                    String depositAccountNum = readNonEmpty(scanner, "Please enter the account number: ");
                    double fundsToDeposit = readAmount(scanner, "Please enter the amount to deposit: ", false);
                    bank.accountdeposit(depositAccountNum, fundsToDeposit);
                    break;
                }

                case 4: {
                    String withdrawAccountNum = readNonEmpty(scanner, "Please enter the account number: ");
                    double fundsToWithdraw = readAmount(scanner, "Please enter the amount to withdraw: ", false);
                    bank.withdrawals(withdrawAccountNum, fundsToWithdraw);
                    break;
                }

                case 5: {
                    String srcAccnt = readNonEmpty(scanner, "Please enter the source account number: ");
                    String destAccnt = readNonEmpty(scanner, "Please enter the destination account number: ");
                    double amtTransfer = readAmount(scanner, "Please enter the amount to transfer: ", false);
                    bank.fundTransfer(srcAccnt, destAccnt, amtTransfer);
                    break;
                }

                case 6: {
                    System.out.println("Search for accounts by:");
                    System.out.println("1. Account Number");
                    System.out.println("2. Account Holder's Name");
                    int option = readInt(scanner, "Please enter your search option: ");

                    if (option == 1) {
                        String accntNumber = readNonEmpty(scanner, "Please enter the account number: ");
                        BankAccount targetAccount = bank.accountSearchbyNum(accntNumber);
                        if (targetAccount != null) {
                            targetAccount.displayAccountInfo();
                        } else {
                            System.out.println("Account does not exist.");
                        }
                    } else if (option == 2) {
                        String accntName = readNonEmpty(scanner, "Please enter the account holder's name: ");
                        ArrayList<BankAccount> targets = bank.accountSearchbyName(accntName);
                        if (targets.isEmpty()) {
                            System.out.println("No accounts were found.");
                        } else {
                            System.out.println("Matching accounts:");
                            for (BankAccount account : targets) {
                                account.displayAccountInfo();
                            }
                        }
                    } else {
                        System.out.println("Invalid search option.");
                    }
                    break;
                }

                case 7: {
                    String delAccountNumber = readNonEmpty(scanner, "Please enter the account number to delete: ");
                    bank.accountDelete(delAccountNumber);
                    break;
                }

                case 8: {
                    processing = false;
                    System.out.println("Leaving Bank Management System. Have a great day!");
                    break;
                }

                default:
                    System.out.println("Invalid choice. Please select a number from 1 to 8.");
            }
        }

        scanner.close();
    }

    // Re-prompt instead of crashing if the user enters letters or decimals.
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    // Do not allow blank account numbers or names.
    private static String readNonEmpty(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    // Starting balance may be zero; deposits, withdrawals and transfers must be positive.
    private static double readAmount(Scanner scanner, String prompt, boolean allowZero) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double amount = Double.parseDouble(input);
                if (Double.isFinite(amount) && (allowZero ? amount >= 0 : amount > 0)) {
                    return amount;
                }
            } catch (NumberFormatException e) {
                // The message below covers non-numeric entries as well.
            }
            System.out.println(allowZero
                    ? "Invalid amount. Please enter a number that is zero or greater."
                    : "Invalid amount. Please enter a number greater than zero.");
        }
    }
}
