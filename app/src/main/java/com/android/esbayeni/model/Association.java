package com.android.esbayeni.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by sihlemabaleka on 9/14/16.
 */
@ParseClassName("Associations")
public class Association extends ParseObject {

    public List<Route> getRoutes() {
        return getList("routes");
    }

    public String getAssociationName() {
        return getString("association_name");
    }

    public String getAssociationAbbrevation() {
        return getString("association_name_abbreviation");
    }

}


