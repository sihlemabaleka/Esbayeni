package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 9/13/16.
 */
@ParseClassName("Board")
public class Board extends ParseObject {


    public Driver getDriver() {
        return (Driver) getParseObject("driver");
    }

    public void setDriver(Driver driver) {
        put("driver", driver);
    }

    public Vehicle getVehicle() {
        return (Vehicle) getParseObject("vehicle");
    }

    public void setVehicle(Vehicle vehicle) {
        put("vehicle", vehicle);
    }

    public Rank getRank() {
        return (Rank) getParseObject("rank");
    }

    public void setRank(Rank rank) {
        put("rank", rank);
    }

    public Destination getDestination() {
        return (Destination) getParseObject("destination");
    }

    public void setDestination(Destination destination) {
        put("destination", destination);
    }

    public Route getRoute() {
        return (Route) getParseObject("route");
    }

    public void setRoute(Route route) {
        put("route", route);
    }

    public Association getAssociation() {
        return (Association) getParseObject("association");
    }

    public void setAssociation(Association association) {
        put("association", association);
    }
}
