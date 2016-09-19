package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 9/13/16.
 */
@ParseClassName("Vehicles")
public class Vehicle extends ParseObject {

    public String getVehicleRegistration() {
        return getString("vehicle_registration");
    }

    public void setVehicleRegistration(String value) {
        put("vehicle_registration", value);
    }

    public Association getVehicleAssociation() {
        return (Association) getParseObject("association");
    }

    public void setVehicleAssociation(Association value) {
        put("association", value);
    }

}
