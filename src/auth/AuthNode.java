package auth;

import java.util.HashMap;
import java.util.Map;

public class AuthNode {
    private Map<String, User> users;

    public AuthNode() {
        users = new HashMap<>();
        users.put("doctor1", new User("doctor1", "password123"));
        users.put("diagnostic1", new User("diagnostic1", "diagpass"));
        users.put("pharmacy1", new User("pharmacy1", "pharmpass"));
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        return user != null && user.authenticate(password);
    }

    public void rewardUser(String username, double amount) {
        User user = users.get(username);
        if (user != null) {
            user.addBalance(amount);
            System.out.println(username + " rewarded: " + amount + " e-cash");
        }
    }

    public double getUserBalance(String username) {
        User user = users.get(username);
        return (user != null) ? user.getBalance() : 0.0;
    }
}
