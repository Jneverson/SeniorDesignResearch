package com.nyit.seniordesignproject.conciousgps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int buttonCounter = 0;

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
//        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        //Code used to access development layout separate from UI
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonCounter++;
                if(buttonCounter >= 5)  {
                    buttonCounter = 0;
                    //setContentView(R.layout.development_testing);
                    Intent nextScreen = new Intent(getApplicationContext(), DevelopmentTesting.class);
                    startActivity(nextScreen);
                }
            }
        });
        //This change is only to test an android studio commit
        System.out.println("Test");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
   // public native String stringFromJNI();
}
