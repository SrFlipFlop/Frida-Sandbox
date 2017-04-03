package srflipflop.fridasandbox.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import java.security.MessageDigest;

public class IntegrityManager {
    private final static String SIGNATURE = "zTBbwJfoL/MbBpJ3wnGTiw5Wz/Y=";
    private Context context;

    public IntegrityManager(Context context) {
        this.context = context;
    }

    public boolean check() {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature signature : packageInfo.signatures) {
                MessageDigest digest = MessageDigest.getInstance("SHA");
                digest.update(signature.toByteArray());
                String currentSignature = Base64.encodeToString(digest.digest(), Base64.DEFAULT);

                //Log.d("==========", currentSignature);
                if (SIGNATURE.equals(currentSignature)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
