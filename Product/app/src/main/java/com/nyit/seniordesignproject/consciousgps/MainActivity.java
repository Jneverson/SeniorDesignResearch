package com.nyit.seniordesignproject.consciousgps;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class
MainActivity extends AppCompatActivity {
    //    private static int buttonCounter = 0;
    private static int mapCounter = 0;
    private static int imageRecCounter = 0;

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
//        findViewById(R.id.imageRecButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                imageRecCounter++;
//                if (imageRecCounter >= 5) {
//                    imageRecCounter = 0;
//                    //setContentView(R.layout.development_testing);
//                    Intent nextScreen = new Intent(getApplicationContext(), ImageRecognition.class);
//                    startActivity(nextScreen);
//                }
//            }
//        });

        findViewById(R.id.imageRecButton).setOnClickListener(b -> {
            imageRecCounter++;
            if (imageRecCounter >= 5) {
                imageRecCounter = 0;
                startActivity(new Intent(getApplicationContext(), ImageRecognition_v2.class));
            }
        });

        findViewById(R.id.mapsButton).setOnClickListener(b -> {
            mapCounter++;
            if (mapCounter >= 5) {
                mapCounter = 0;
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                Intent mStartActivity = new Intent(getApplicationContext(), ImageRecognition_v2.class);
                int mPendingIntentId = 123456;
                PendingIntent mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId,mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 7000, mPendingIntent);
                //System.exit(0);
            }
        });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    // public native String stringFromJNI();
}
