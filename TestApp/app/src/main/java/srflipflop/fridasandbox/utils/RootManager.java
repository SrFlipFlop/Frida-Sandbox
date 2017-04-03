package srflipflop.fridasandbox.utils;

import android.os.Build;

import java.io.File;

public class RootManager {
    private boolean result;

    public boolean check() {
        if (Build.TAGS.contains("test-keys")) {
            result = true;
        }

        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                result = true;
            }
        } catch (Exception ignored) {}

        try {
            String[] commands = new String[] {"/system/xbin/which su", "/system/bin/which su", "which su"};
            for (String command : commands) {
                Runtime.getRuntime().exec(command);
                result = true;
            }
        } catch (Exception ignored) {
            result = false;
        }

        String[] places = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};
        for (String where : places) {
            if ( new File( where + "su" ).exists() ) {
                result = true;
                break;
            }
        }
        return result;
    }
}
