package srflipflop.fridalib;

public class Implement {
    public String generateKey() {
        int random = (int) (Math.random()*9000)+1000;
        return String.valueOf(random);
    }
}
