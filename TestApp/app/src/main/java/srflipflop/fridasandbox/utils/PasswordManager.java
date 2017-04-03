package srflipflop.fridasandbox.utils;

public class PasswordManager {
    public String checkPassword(String pass) {
        if (pass.equals("1122")) {
            return "SESSION_ID:Ch4ng3It!";
        }
        return null;
    }
}
