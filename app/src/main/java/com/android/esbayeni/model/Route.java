package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 9/14/16.
 */
@ParseClassName("routes")
public class Route extends ParseObject {

    public ParseGeoPoint getOrigin() {
        return getParseGeoPoint("origin_location");
    }

    public ParseGeoPoint getDestination() {
        return getParseGeoPoint("destination_location");
    }

    public String getDestinationName() {
        return getString("destination_name");
    }

    public String getOriginName() {
        return getString("origin_name");
    }

    public String getRouteName() {
        return getString("route_name");
    }

    public Association getVehicleAssociation() {
        return (Association) getParseObject("association");
    }

    public void setVehicleAssociation(Association value) {
        put("association", value);
    }
}
