package com.nyit.seniordesignproject.conciousgps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class DevelopmentTesting extends AppCompatActivity {
    private int buttonCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.development_testing);

        /*findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonCounter++;
                if(buttonCounter >= 5)  {
                    buttonCounter = 0;
                    //setContentView(R.layout.development_testing);
                    Intent nextScreen = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(nextScreen);
                }
            }
        });*/

        findViewById(R.id.button2).setOnClickListener(b -> {
            buttonCounter++;
            if (buttonCounter >= 5)
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

    }
}
