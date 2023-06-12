package user;

import java.util.ArrayList;

public class UserList {
    private ArrayList<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public User findUser(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public void addUser(User user) {
        users.add(user);
    }
}
