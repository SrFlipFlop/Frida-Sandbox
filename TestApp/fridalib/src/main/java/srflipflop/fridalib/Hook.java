package srflipflop.fridalib;

import android.util.Base64;

public class Hook {
    public boolean sendInfo() {
        String encrypted = getSecretFromDb();
        String secret = decryptSecret(encrypted);
        boolean result = sendToServer(secret);
        return sendToServer(secret);
    }

    private String getSecretFromDb() {
        return Base64.encodeToString("¡Sup3r_S3cr3d!".getBytes(), Base64.DEFAULT);
    }

    private String decryptSecret(String secret) {
        byte[] bytes = Base64.decode(secret.getBytes(), Base64.DEFAULT);
        return new String(bytes);
    }

    private boolean sendToServer(String secret) {
        return secret.length() == 14;
    }
}
