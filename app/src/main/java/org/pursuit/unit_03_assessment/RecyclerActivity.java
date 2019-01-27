package org.pursuit.unit_03_assessment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.pursuit.unit_03_assessment.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerActivity extends AppCompatActivity {

    private static final String BASE_PLANET_URL = "https://raw.githubusercontent.com/";
    private PlanetService planetService;
    private RecyclerView planets_recycler_view;
    private PlanetAdapter planetAdapter;
    private static final String TAG = "Sheriff Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        /*
        * TODO: Add logic that will:
        * TODO 1. Make Retrofit instance for this endpoint: https://raw.githubusercontent.com/JDVila/storybook/master/planets.json
        * TODO 2. Make data model objects based on JSON
        * TODO 3. Make a service interface with method(s) to make a GET request
        * TODO 4. Make a request to the JSON endpoint using the Retrofit instance and the service
        * TODO 5. Subclass a RecyclerView Adapter
        * TODO 6. Subclass a RecyclerView ViewHolder
        * TODO 7. Pass list to RecyclerView
        * TODO 8. Display planet name in a RecyclerView tile
        * TODO 9. Implement an OnClickListener for the itemview
        * TODO 10. Pass the Planet Name, Number, and Image URL data to DisplayActivity when tile is clicked
        */

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_PLANET_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        planetService = retrofit.create(PlanetService.class);
        Call<PlanetList> planetCall = planetService.getPlanet();
        planetCall.enqueue(new Callback<PlanetList>() {
            @Override
            public void onResponse(Call<PlanetList> call, Response<PlanetList> response) {
//                List<Planet> planetList = response.body().getPlanet();
                Log.d(TAG,"onCreate: "+ response.body());
                planets_recycler_view = findViewById(R.id.planets_recyclerview);
//                planetAdapter = new PlanetAdapter(planetList);
                planets_recycler_view.setAdapter(planetAdapter);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
                planets_recycler_view.setLayoutManager(gridLayoutManager);

            }

            @Override
            public void onFailure(Call<PlanetList> call, Throwable t) {
                Log.d(TAG,"onCreate: " +t.toString());

            }
        });



    }
}
