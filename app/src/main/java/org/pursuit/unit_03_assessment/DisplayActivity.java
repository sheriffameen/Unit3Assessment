package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pursuit.unit_03_assessment.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayActivity extends AppCompatActivity {
    private TextView planetName;
    private TextView planetNumber;
    private static final String BASE_PLANET_URL = "https://raw.githubusercontent.com/";
    private PlanetService planetService;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        planetName = findViewById(R.id.name_display_textview);
        planetNumber = findViewById(R.id.number_display_textview);
        image = findViewById(R.id.planet_display_imageview);

        /*
        * TODO: Add logic that will:
        * TODO 1. Receive values from sending intent
        * TODO 2. Create a TextView instance for the Planet Name
        * TODO 3. Create a TextView instance for the Planet Number count
        * TODO 4. Create an ImageView for the image url
        * TODO 5. Display each value in views - Strings for TextViews, and Picasso for the ImageView
        */

        Intent intent = getIntent();
        String planetNameExtra = intent.getStringExtra("planetName");
        String planetNumberExtra = intent.getStringExtra("planetNumber");
        String imageExtra = intent.getStringExtra("image");
        planetName.setText(planetNameExtra);
        planetNumber.setText(planetNumberExtra);
        Picasso.get().load(imageExtra).into(image);








    }
}
