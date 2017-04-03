package srflipflop.fridasandbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import srflipflop.fridalib.BruteForce;
import srflipflop.fridasandbox.utils.PasswordManager;

public class BruteActivity extends Activity {
    private String sessionId;
    private Spinner spinner;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brute);

        spinner = (Spinner) findViewById(R.id.brute_spinner);
        ArrayAdapter<CharSequence> adapterChallenge = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapterChallenge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterChallenge);
    }

    public void onClick(View v) {
        EditText passField = (EditText) findViewById(R.id.implement_pass);
        String pass = passField.getText().toString();

        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                PasswordManager manager = new PasswordManager();
                sessionId = manager.checkPassword(pass);
                break;
            case "Library":
                BruteForce bf = new BruteForce();
                sessionId = bf.checkPassword(pass);
                break;
            case "Native":
                sessionId = bruteForceJNI(pass);
                break;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (sessionId != "null" || sessionId != null) {
            builder.setMessage(String.format("Correct: %s", sessionId)).setTitle("SESSION RESULT:");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            builder.setMessage("Wrong credentials").setTitle("SESSION RESULT:");
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public native String bruteForceJNI(String password);
}
