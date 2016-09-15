package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 9/13/16.
 */
@ParseClassName("Drivers")
public class Driver extends ParseObject {


    public String getDriverName() {
        return getString("driver_name");
    }

    public void setDriverName(String value) {
        put("driver_name", value);
    }

}
