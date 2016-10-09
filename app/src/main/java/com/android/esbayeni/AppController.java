package com.android.esbayeni;

import android.app.Application;

import com.android.esbayeni.model.Association;
import com.android.esbayeni.model.Board;
import com.android.esbayeni.model.Destination;
import com.android.esbayeni.model.Driver;
import com.android.esbayeni.model.Rank;
import com.android.esbayeni.model.Route;
import com.android.esbayeni.model.TaxiMarshall;
import com.android.esbayeni.model.Vehicle;
import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by sihlemabaleka on 10/9/16.
 */
public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Association.class);
        ParseObject.registerSubclass(Board.class);
        ParseObject.registerSubclass(Destination.class);
        ParseObject.registerSubclass(Driver.class);
        ParseObject.registerSubclass(Rank.class);
        ParseObject.registerSubclass(Route.class);
        ParseObject.registerSubclass(TaxiMarshall.class);
        ParseObject.registerSubclass(Vehicle.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("myAppId")
                .server("http://172.20.10.11:4040/parse")
                .enableLocalDataStore()
                .build()
        );
    }
}
