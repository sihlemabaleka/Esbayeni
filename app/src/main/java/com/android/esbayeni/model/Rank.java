package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 9/13/16.
 */
@ParseClassName("Taxi Ranks")
public class Rank extends ParseObject {


    public String getRankName() {
        return getString("rank_name");
    }

    public void setRankName(String value) {
        put("rank_name", value);
    }

    public void setRankCoordinates(ParseGeoPoint value) {
        put("rank_coordinates", value);
    }

    public ParseGeoPoint geRankCoordinates() {
        return getParseGeoPoint("rank_coordinates");
    }

    public Association getVehicleAssociation() {
        return (Association) getParseObject("association");
    }

    public void setVehicleAssociation(Association value) {
        put("association", value);
    }

}
