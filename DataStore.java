package src;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private static final String FILE_PATH = "data/accounts.txt";
    private Map<String, User> users = new HashMap<>();

    public void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    double balance = Double.parseDouble(parts[1]);
                    users.put(username, new User(username));
                    users.get(username).setBalance(balance);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        }
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users.values()) {
                writer.write(user.getUsername() + "," + user.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing data file: " + e.getMessage());
        }
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public void addUser(User user) {
        users.put(user.getUsername(), user);
    }
}
