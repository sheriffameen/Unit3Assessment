package org.pursuit.unit_03_assessment;

import java.util.List;

public class PlanetList {
    /*  Make your fields private
        Make sure you name your fields EXACTLY like they are named in the JSON,
        otherwise the JSON won't parse successfully. This was one of the reasons
        you had a NullPointer
    */
    private List<Planet> planets;

    public List<Planet> getPlanets() {
        return planets;
    }
}
