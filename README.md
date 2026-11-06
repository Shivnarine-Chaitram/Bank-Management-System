# Bank Management System

A menu-driven **Java console application** for creating and managing bank accounts. It demonstrates how object-oriented programming (OOP) can organize a small banking system into separate classes for account data, banking operations, and user interaction.

> **Educational project:** This is a simulation, not a real banking service. It does not connect to a bank or move real money.

## Why this project is useful

This project provides a practical introduction to building a complete, interactive Java program. It brings together **classes and objects, constructors, encapsulation, methods, `ArrayList`, loops, conditional logic, and input validation**. It also shows why separating responsibilities across classes makes code easier to understand, test, and extend.

## Features

- **Create an account** with a unique account number, holder name, and starting balance.
- **View account details**, including the account number, holder name, and balance.
- **Deposit funds** into an existing account.
- **Withdraw funds** when the account has enough money.
- **Transfer funds** between two different accounts.
- **Search for accounts** by exact account number or by all or part of a holder's name (case-insensitive).
- **Delete an account** from the bank's in-memory list.
- **Handle invalid input**, including non-numeric menu entries, blank names, duplicate account numbers, and invalid transaction amounts.

## Technologies

- **Language:** Java
- **Interface:** Command-line / console
- **Java APIs:** `ArrayList`, `Scanner`
- **Dependencies:** None beyond the Java Development Kit (JDK)

## Project structure

```text
Bank-Management-System/
├── BankAccount.java       # Stores account details; handles deposits and withdrawals
├── Bank.java              # Manages accounts, searches, and transfers
├── ManagementSystem.java  # Runs the interactive console menu
└── README.md              # Project documentation
```

### How the classes work together

| Class | Responsibility |
| --- | --- |
| `BankAccount` | Represents one account, stores its details and balance, and performs deposits and withdrawals. |
| `Bank` | Maintains an `ArrayList<BankAccount>` and handles account creation, search, transfers, and deletion. |
| `ManagementSystem` | Provides the menu, reads and validates user input, and calls methods in `Bank`. |

This division follows the **object-oriented decomposition** approach: each class has a clear purpose, and the application uses objects to work together.

## Getting started

### Requirements

- A **Java Development Kit (JDK 8 or later)** installed on your computer.
- A terminal or command prompt (or a Java IDE such as IntelliJ IDEA, Eclipse, or VS Code with Java support).

Check your installation:

```bash
java -version
javac -version
```

### Download and run

1. Download or clone this repository:

   ```bash
   git clone https://github.com/Shivnarine-Chaitram/Bank-Management-System.git
   ```

   select **Code → Download ZIP** on the repository page and extract the files.

2. Open a terminal in the folder containing the three `.java` files.

3. Compile the program:

   ```bash
   javac BankAccount.java Bank.java ManagementSystem.java
   ```

4. Run the application:

   ```bash
   java ManagementSystem
   ```

The main menu will appear. Enter a number from **1 to 8** to select an operation, then follow the on-screen prompts.

## Menu options

```text
=== Bank Management System ===
1. Create New Account
2. View Account Information
3. Deposit Funds
4. Withdraw Funds
5. Transfer Funds
6. Search for Accounts
7. Delete Account
8. Exit
```

## Example usage

Try this short sequence after launching the program:

1. Choose **1** to create account `1001` for `Alex Morgan` with a starting balance of `150`.
2. Choose **1** again to create account `1002` for `Jordan Lee` with a starting balance of `50`.
3. Choose **5** to transfer `25` from account `1001` to account `1002`.
4. Choose **2** to view account `1002`.

The account details will show:

```text
Account Number: 1002
Account Holder: Jordan Lee
Balance: $75.00
```

You can also choose **6 → 2** and enter `jor` to find `Jordan Lee` using the partial-name search.

## Validation and safeguards

- Account numbers and account holder names cannot be blank.
- Account numbers must be unique.
- Starting balances cannot be negative.
- Deposits, withdrawals, and transfers must be greater than zero.
- Withdrawals and transfers cannot exceed the available balance.
- Transfers require two different, existing accounts.
- Invalid menu selections and non-numeric entries are handled without immediately terminating the program.

## Current limitations

This is a learning project, not production-ready banking software:

- **No permanent storage:** Accounts are stored in memory and are lost when the program closes.
- **No authentication:** The application does not verify the identity of account holders.
- **No transaction history:** It does not maintain a log of deposits, withdrawals, and transfers.
- **Floating-point money values:** Balances use `double` to suit the assignment; real financial applications should use `BigDecimal` or integer cents to avoid floating-point rounding issues.
- **Console only:** There is no graphical user interface or web interface.

## Possible future improvements

- Save account data to a file or database.
- Add account login and authentication.
- Record transaction histories and timestamps.
- Add automated unit tests.
- Replace `double` with `BigDecimal` for exact monetary calculations.
- Build a graphical or web-based interface.

## Educational focus

The project was created to practice **Java object-oriented programming** and problem decomposition. It is a starting point for exploring more advanced software design, persistent data storage, testing, and application security.
