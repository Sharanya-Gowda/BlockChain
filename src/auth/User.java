package auth;

public class User {
    private String username;
    private String password;
    private double balance;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.balance = 0.0;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }
}
