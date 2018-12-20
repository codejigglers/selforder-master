package examples.sdk.android.clover.com.cloverselforder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

  BarcodeReader barcodeReader;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scan);

    // get the barcode reader instance
    barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
  }

  @Override
  public void onScanned(Barcode barcode) {

    // playing barcode reader beep sound
    Intent sendResult = new Intent(this,PasscodePage.class);
    sendResult.putExtra("text", barcode.displayValue);
    setResult(Activity.RESULT_OK, sendResult);
    startActivity(sendResult);
    finish();
  }

  @Override
  public void onScannedMultiple(List<Barcode> list) {

  }

  @Override
  public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

  }

  @Override
  public void onCameraPermissionDenied() {
    finish();
  }

  @Override
  public void onScanError(String s) {
    Toast.makeText(getApplicationContext(), "Error occurred while scanning " + s, Toast.LENGTH_SHORT).show();
  }
}