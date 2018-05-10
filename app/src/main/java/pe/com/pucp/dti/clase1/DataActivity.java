package pe.com.pucp.dti.clase1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    //Constants
    private static final String TAG = "DataActivity";
    public static final String RESULT_INDEX = "RESULT";

    //Properties
    int clicks = 0;
    //View elements
    private TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        setTitle(R.string.data_activity_title);
        setObjects();
        setResult();
    }

    private void setObjects() {
        dataTextView = findViewById(R.id.dataTextView);
        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        /*
         * TODO: ¿Que pasaría si utizo las siguientes instrucciones?
         *
         * String data = intent.getStringExtra(MainActivity.DATA_INDEX);
         * dataTextView.setText(data);
         *
         * TODO: ¿Y si agrego la instrucción toLowerCase()?
         *
         * String data = intent.getStringExtra(MainActivity.DATA_INDEX);
         * data.toLowerCase();
         * dataTextView.setText(data);
         *
         */
        if (extras != null) {
            String data = extras.getString(MainActivity.DATA_INDEX);
            dataTextView.setText(data);
        }

        dataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks++;
                // TODO: ¿Qué hace el siguiente bloque switch?
                switch (clicks % 3) {
                    case 1:
                        dataTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case 2:
                        dataTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        break;
                    case 0:
                        dataTextView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        break;
                    default:
                        dataTextView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                }
                setResult();
            }
        });
    }

    private void setResult(){
        // Set activity results.
        Intent returnIntent = new Intent();
        if (this.clicks > 0) {
            returnIntent.putExtra(RESULT_INDEX, this.clicks);
            setResult(Activity.RESULT_OK, returnIntent);
        } else {
            setResult(Activity.RESULT_CANCELED, returnIntent);
        }
    }


    /*

    TODO: ¿Qué ocurre cuando se llama a setResult en la función onStop() u onDestroy()?
    @Override
    protected void onStop() {
        super.onStop();
        setResult();
    }

    */
}
