package examples.sdk.android.clover.com.cloverselforder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class PasscodePage extends AppCompatActivity {
    EditText passcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode_page);
        passcode = (EditText) findViewById(R.id.passCode);
        passcode.requestFocus();
    }

}
