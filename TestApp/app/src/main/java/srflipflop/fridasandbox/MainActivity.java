package srflipflop.fridasandbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
    private Spinner spinner;

    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSpinner();
    }

    public void onClick(View v) {
        String selectedChallenge = spinner.getSelectedItem().toString();
        launchChallenge(selectedChallenge);
    }

    private void createSpinner() {
        spinner = (Spinner) findViewById(R.id.main_spinner);
        ArrayAdapter<CharSequence> adapterChallenge = ArrayAdapter.createFromResource(this, R.array.challenge, android.R.layout.simple_spinner_item);
        adapterChallenge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterChallenge);
    }

    private void launchChallenge(String challange) {
        switch (challange) {
            case "Intercept information":
                Intent iHook = new Intent(this, HookActivity.class);
                startActivity(iHook);
                break;
            case "Brute force":
                Intent iBrute = new Intent(this, BruteActivity.class);
                startActivity(iBrute);
                break;
            case "Implement methods":
                Intent iImplement = new Intent(this, ImplementActivity.class);
                startActivity(iImplement);
                break;
            case "Security checks":
                Intent iChecks = new Intent(this, ChecksActivity.class);
                startActivity(iChecks);
                break;
        }
    }
}
