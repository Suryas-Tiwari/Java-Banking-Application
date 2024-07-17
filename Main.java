package src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountService();
        DataStore dataStore = new DataStore();

        dataStore.loadData();

        System.out.println("Welcome to Simple Banking Application");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        User user = dataStore.getUser(username);
        if (user == null) {
            user = new User(username);
            dataStore.addUser(user);
        }

        int option;
        do {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Your current balance is: $" + accountService.checkBalance(user));
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    accountService.deposit(user, depositAmount);
                    System.out.println("Deposit successful. Your new balance is: $" + user.getBalance());
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (accountService.withdraw(user, withdrawAmount)) {
                        System.out.println("Withdrawal successful. Your new balance is: $" + user.getBalance());
                    } else {
                        System.out.println("Insufficient funds. Withdrawal failed.");
                    }
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        } while (option != 4);

        dataStore.saveData();
        scanner.close();
    }
}
