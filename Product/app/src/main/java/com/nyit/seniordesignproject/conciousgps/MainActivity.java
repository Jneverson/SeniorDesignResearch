package com.nyit.seniordesignproject.conciousgps;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.api.Context;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.gax.paging.Page;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.FaceAnnotation;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;

import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// Imports the Google Cloud client library
class OtherDemo extends AppCompatActivity {

    byte[] photoData;
    public void main() throws IOException {
        Vision.Builder visionBuilder = new Vision.Builder(
                new NetHttpTransport(),
                new AndroidJsonFactory(),
                null);

        visionBuilder.setVisionRequestInitializer(
                new VisionRequestInitializer("AIzaSyBZovOlTaA2JjUC7IN0XY3HlqA257Uhp0c"));

        Vision vision = visionBuilder.build();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Convert photo to byte array
                InputStream inputStream =
                        getResources().openRawResource(+ R.drawable.apollo);
                try {
                    OtherDemo.this.photoData = IOUtils.toByteArray(inputStream);

                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // More code here
            }
        });

        Image inputImage = new Image();
        inputImage.encodeContent(photoData);
        Feature desiredFeature = new Feature();
        desiredFeature.setType("FACE_DETECTION");

        AnnotateImageRequest request = new AnnotateImageRequest();
        request.setImage(inputImage);
        request.setFeatures(Arrays.asList(desiredFeature));

        BatchAnnotateImagesRequest batchRequest =
                new BatchAnnotateImagesRequest();

        batchRequest.setRequests(Arrays.asList(request));

        BatchAnnotateImagesResponse batchResponse =
                vision.images().annotate(batchRequest).execute();

        List<FaceAnnotation> faces = batchResponse.getResponses()
                .get(0).getFaceAnnotations();

        // Count faces
        int numberOfFaces = faces.size();

// Get joy likelihood for each face
        String likelihoods = "";
        for(int i=0; i<numberOfFaces; i++) {
            likelihoods += "\n It is " +
                    faces.get(i).getJoyLikelihood() +
                    " that face " + i + " is happy";
        }

// Concatenate everything
        final String message =
                "This photo has " + numberOfFaces + " faces" + likelihoods;

// Display toast on UI thread
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),
                        message, Toast.LENGTH_LONG).show();
            }
        });




    }
}
//class QuickstartSample {
//    private static void authExplicit(String jsonPath) throws IOException {
//        // You can specify a credential file by providing a path to GoogleCredentials.
//        // Otherwise credentials are read from the GOOGLE_APPLICATION_CREDENTIALS environment variable.
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(jsonPath))
//                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//
//        System.out.println("Buckets:");
//        Page<Bucket> buckets = storage.list();
//        for (Bucket bucket : buckets.iterateAll()) {
//            System.out.println(bucket.toString());
//        }
//    }
//    public static void main(String... args) throws Exception {
//        //authExplicit("C:/Users/Josiah-David Sykes/Documents/Senior_Design/concious-gps-8966095864d3.json");
//        // Instantiates a client
//        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {
//
//            // The path to the image file to annotate
//            String fileName = "./resources/wakeupcat.jpg";
//
//            // Reads the image file into memory
//            Path path = Paths.get(fileName);
//            byte[] data = Files.readAllBytes(path);
//            ByteString imgBytes = ByteString.copyFrom(data);
//
//            // Builds the image annotation request
//            List<AnnotateImageRequest> requests = new ArrayList<>();
//            Image img = Image.newBuilder().setContent(imgBytes).build();
//            Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
//            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
//                    .addFeatures(feat)
//                    .setImage(img)
//                    .build();
//            requests.add(request);
//
//            // Performs label detection on the image file
//            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
//            List<AnnotateImageResponse> responses = response.getResponsesList();
//
//            for (AnnotateImageResponse res : responses) {
//                if (res.hasError()) {
//                    System.out.printf("Error: %s\n", res.getError().getMessage());
//                    return;
//                }
//
//                for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
//                    annotation.getAllFields().forEach((k, v) ->
//                            System.out.printf("%s : %s\n", k, v.toString()));
//                }
//            }
//        }
//    }
//}
public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        //BACKEND CODE
//        OtherDemo demo = new OtherDemo();
//        try {
//            demo.main();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
