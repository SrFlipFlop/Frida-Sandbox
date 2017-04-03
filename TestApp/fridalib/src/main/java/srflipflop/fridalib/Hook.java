package srflipflop.fridalib;

import android.util.Base64;

public class Hook {
    public boolean sendInfo() {
        String secret = generateSuperSecret();
        String encrypted = encryptSecret(secret);
        return sendToServer(encrypted);
    }

    private String generateSuperSecret() {
        return "¡Sup3r_S3cr3d!";
    }

    private String encryptSecret(String secret) {
        return Base64.encodeToString(secret.getBytes(), 0);
    }

    private boolean sendToServer(String secret) {
        return secret.length() > 14;
    }
}
