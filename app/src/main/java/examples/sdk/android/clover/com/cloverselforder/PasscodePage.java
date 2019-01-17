package examples.sdk.android.clover.com.cloverselforder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
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
    passcode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
          Log.i("Sam", "Enter pressed");
          if (Integer.parseInt(passcode.getText().toString()) == 123456) {
            Intent i = new Intent(getApplicationContext(), MenuActivity.class);
            startActivity(i);
          }
        }
        return false;
      }
    });
  }

}
