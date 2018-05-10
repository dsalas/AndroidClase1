package pe.com.pucp.dti.clase1;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Constants
    //TODO: ¿Qué significan las palabras reservadas private static y final?
    private static final String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1;

    //TODO: ¿Y public?
    public static final String DATA_INDEX = "DATA";

    //View elements
    private EditText dataEditText;
    private Button sendDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         *  TODO: ¿Cual es la diferencia entre setTitle(R.string.test_activity_title) y setTitle(getString(R.string.test_activity_title))?
         */
        setTitle(R.string.test_activity_title);
        //setTitle(getString(R.string.test_activity_title));

        // TODO: Simular el ciclo onPause() -> onResume.
        //goToTestActivity();
        setObjects();
    }

    private void setObjects() {
        this.dataEditText = findViewById(R.id.dataEditText);
        this.sendDataButton = findViewById(R.id.sendDataButton);
        this.sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goToDataActivity();
                //sendDataToDataActivity();
                interactWithDataActivity();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"Calling onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"Calling onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Calling onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"Calling onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"Calling onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Calling onDestroy");
    }

    private void goToTestActivity(){
        Intent intent = new Intent(MainActivity.this, TestActivity.class);
        startActivity(intent);
    }

    private void goToDataActivity(){
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }

    private void sendDataToDataActivity() {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        // TODO: ¿Por qué es necesario utilizar el método toString() para obtener el texto de un EditText?
        String data = this.dataEditText.getText().toString();
        intent.putExtra(DATA_INDEX, data);
        startActivity(intent);
    }

    private void interactWithDataActivity() {
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        String data = this.dataEditText.getText().toString();
        intent.putExtra(DATA_INDEX, data);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult");
        Log.i(TAG, "Request code: " + Integer.toString(requestCode) + " - Result code: " + Integer.toString(resultCode));
        if (requestCode == REQUEST_CODE) {
             int result = 0;
            // Activity send RESULT_OK flag
            if(resultCode == Activity.RESULT_OK){
                result = data.getIntExtra(DataActivity.RESULT_INDEX, 0);
                String message = getString(R.string.click_text_format, result);
                Toast.makeText(this, message , Toast.LENGTH_SHORT).show();
            }
            // Activity send RESULT_CANCELED flag
            if ((result == 0) || resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, getString(R.string.no_click_text), Toast.LENGTH_SHORT).show();
            }
            // TODO: ¿Qué diferencia hay entre getString(R.string.no_click_text) y getString(R.string.click_text_format, result)?
        }
    }
}
