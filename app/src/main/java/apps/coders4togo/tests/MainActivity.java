package apps.coders4togo.tests;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread () {
            @Override
            public void run() {
                try {
                    sleep (3000);
                } catch (Exception Ex) {
                    Ex.printStackTrace ();
                } finally {
                    Intent AZE = new Intent (MainActivity.this, Con_activity.class);
                    startActivity (AZE);
                }
            }
        };
        thread.start ();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        finish ();
    }

    @Override
    protected void onPause() {
        super.onPause ();
        finish ();
    }
}