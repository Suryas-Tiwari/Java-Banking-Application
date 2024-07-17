package src;

public class AccountService {

    public double checkBalance(User user) {
        return user.getBalance();
    }

    public void deposit(User user, double amount) {
        double newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
    }

    public boolean withdraw(User user, double amount) {
        if (user.getBalance() >= amount) {
            double newBalance = user.getBalance() - amount;
            user.setBalance(newBalance);
            return true;
        }
        return false;
    }
}
