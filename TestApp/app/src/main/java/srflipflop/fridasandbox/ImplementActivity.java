package srflipflop.fridasandbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import srflipflop.fridalib.Implement;

public class ImplementActivity extends Activity {
    private Spinner spinner;
    private String generatedKey = null;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implement);

        spinner = (Spinner) findViewById(R.id.implement_spinner);
        ArrayAdapter<CharSequence> adapterChallenge = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapterChallenge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterChallenge);
    }

    public void onClickGenerate(View v) {
        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                generatedKey = generateKey();
                break;
            case "Library":
                Implement key = new Implement();
                generatedKey = key.generateKey();
                break;
            case "Native":
                generatedKey = implementJNI();
                break;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("The password has been sent to the email: superscret@secret.org").setTitle("Password generated");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onClickCheck(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (generatedKey == null) {
            builder.setMessage("No key generated").setTitle("ERROR");
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            EditText passField = (EditText) findViewById(R.id.implement_pass);
            String pass = passField.getText().toString();
            if (generatedKey.equals(pass)) {
                builder.setMessage("Correct secret").setTitle("RESULT");
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                builder.setMessage("Wrong secret").setTitle("RESULT");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    private String generateKey() {
        int random = (int) (Math.random()*9000)+1000;
        return String.valueOf(random);
    }

    public native String implementJNI();
}
