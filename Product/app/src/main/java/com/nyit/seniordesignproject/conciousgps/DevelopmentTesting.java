package com.nyit.seniordesignproject.conciousgps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DevelopmentTesting extends AppCompatActivity {
int buttonCounter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.development_testing);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
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
        });


    }
}
