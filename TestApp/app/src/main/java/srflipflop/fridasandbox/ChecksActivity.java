package srflipflop.fridasandbox;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import srflipflop.fridasandbox.utils.IntegrityManager;
import srflipflop.fridasandbox.utils.RootManager;
import srflipflop.fridasandbox.utils.VirtualMachineManager;

public class ChecksActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checks);

        spinner = (Spinner) findViewById(R.id.checks_spiner);
        ArrayAdapter<CharSequence> adapterChallenge = ArrayAdapter.createFromResource(this, R.array.location, android.R.layout.simple_spinner_item);
        adapterChallenge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterChallenge);
    }

    public void onClickRoot(View v) {
        boolean result = false;
        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                RootManager manager = new RootManager();
                result = manager.check();
                break;
            case "Library":
                break;
            case "Native":
                break;
        }
        if (result) {
            errorMsg("Root detected");
        } else {
            successMsg("Root not detected");
        }
    }

    public void onClickVirtualMachine(View v) {
        boolean result = false;
        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                VirtualMachineManager manager = new VirtualMachineManager();
                result = manager.check();
                break;
            case "Library":
                break;
            case "Native":
                break;
        }
        if (result) {
            errorMsg("VM detected");
        } else {
            successMsg("VM not detected");
        }
    }

    public void onClickTamper(View v) {
        boolean result = false;
        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                IntegrityManager manager = new IntegrityManager(this);
                result = manager.check();
                break;
            case "Library":
                break;
            case "Native":
                break;
        }
        if (result) {
            errorMsg("APK modified");
        } else {
            successMsg("APK not modified");
        }
    }

    public void onClickPinning(View v) {
        switch (spinner.getSelectedItem().toString()) {
            case "Application":
                break;
            case "Library":
                break;
            case "Native":
                break;
        }
    }

    private void errorMsg(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg).setTitle("ERROR");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void successMsg(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg).setTitle("SUCCESS");
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
