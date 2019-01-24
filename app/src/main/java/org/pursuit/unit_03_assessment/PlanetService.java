package org.pursuit.unit_03_assessment;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlanetService {

    @GET("JDVila/storybook/master/planets")
    Call<PlanetList> getPlanet();
}
