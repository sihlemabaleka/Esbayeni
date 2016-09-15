package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 9/13/16.
 */
@ParseClassName("Destinations")
public class Destination extends ParseObject {

    public String getDestinationName() {
        return getString("destination_name");
    }

    public void setDestinationName(String value) {
        put("destination_name", value);
    }

    public ParseGeoPoint getDestinationCoordinates() {
        return getParseGeoPoint("destination_coordinates");
    }

    public void setDestinationCoordinates(ParseGeoPoint value) {
        put("destination_coordinates", value);
    }

}
