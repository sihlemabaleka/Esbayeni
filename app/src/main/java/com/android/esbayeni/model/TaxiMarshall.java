package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseUser;

/**
 * Created by sihlemabaleka on 9/15/16.
 */
@ParseClassName("_User")
public class TaxiMarshall extends ParseUser {

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getCellphoneNumber() {
        return getString("cellphone");
    }

    public void setCellphoneNumber(String cellphoneNumber) {
        put("cellphone", cellphoneNumber);
    }

    public Association getAssociation() {
        return (Association) getParseObject("association");
    }

    public void setAssociation(Association association) {
        put("association", association);
    }

    public Route getRoute() {
        return (Route) getParseObject("route");
    }

    public void setRoute(Route route) {
        put("route", route);
    }
}
