package srflipflop.fridasandbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import srflipflop.fridalib.Hook;

public class HookActivity extends Activity {
    private Spinner spinner;
    private String mockServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);

        spinner = (Spinner) findViewById(R.id.hook_spiner);
        ArrayAdapter<CharSequence> adapterChallenge = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapterChallenge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterChallenge);
    }

    public void onClick(View v) {
        boolean result = false;
        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                result = sendInfo();
                break;
            case "Library":
                Hook h = new Hook();
                result = h.sendInfo();
                break;
            case "Native":
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (result) {
            builder.setMessage("Correct secret").setTitle("RESULT");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            builder.setMessage("Wrong secret").setTitle("RESULT");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private boolean sendInfo() {
        String encrypted = getSecretFromDb();
        String secret= decryptSecret(encrypted);
        sendToServer(secret);
        return getServerResponse();
    }

    private String getSecretFromDb() {
        return Base64.encodeToString("¡Sup3r_S3cr3d!".getBytes(), Base64.DEFAULT);
    }

    private String decryptSecret(String secret) {
        byte[] bytes = Base64.decode(secret.getBytes(), Base64.DEFAULT);
        return new String(bytes);
    }

    private void sendToServer(String secret) {
        mockServer = secret;
    }

    private boolean getServerResponse() {
        return mockServer.length() == 14;
    }
}
