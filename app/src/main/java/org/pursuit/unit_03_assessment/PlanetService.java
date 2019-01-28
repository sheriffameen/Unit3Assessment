package org.pursuit.unit_03_assessment;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlanetService {

    //Missing the .json
    @GET("JDVila/storybook/master/planets.json")
    Call<PlanetList> getPlanet();
}
