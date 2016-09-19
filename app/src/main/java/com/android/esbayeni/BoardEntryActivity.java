package com.android.esbayeni;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.android.esbayeni.model.Destination;
import com.android.esbayeni.model.Rank;
import com.android.esbayeni.model.TaxiMarshall;
import com.android.esbayeni.model.Vehicle;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class BoardEntryActivity extends AppCompatActivity {

    private Spinner sTaxiRank, sVehicleReg, sOrigin, sDestination;

    private TaxiMarshall user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);

        user = (TaxiMarshall) ParseUser.getCurrentUser();

    }


    public void getTaxiRank(ParseUser user) {

        ParseQuery<Rank> query = ParseQuery.getQuery(Rank.class);
        query.whereEqualTo("association", user.getParseObject("association"));
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<Rank>() {
            @Override
            public void done(List<Rank> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        //Write logic here
                    } else {
                        //List is empty
                    }
                } else {

                }
            }
        });
    }

    public void getVehicleReg(ParseUser user) {

        ParseQuery<Vehicle> query = ParseQuery.getQuery(Vehicle.class);
        query.whereEqualTo("association", user.getParseObject("association"));
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<Vehicle>() {
            @Override
            public void done(List<Vehicle> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        //Write logic here
                    } else {
                        //List is empty
                    }
                } else {

                }
            }
        });

    }

    public void getVehicleOrigin() {

        ParseQuery<Rank> query = ParseQuery.getQuery(Rank.class);
        query.whereEqualTo("association", user.getParseObject("association"));
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<Rank>() {
            @Override
            public void done(List<Rank> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        //Write logic here
                    } else {
                        //List is empty
                    }
                } else {

                }
            }
        });

    }

    public void getVehicleDestination() {

        ParseQuery<Destination> query = ParseQuery.getQuery(Destination.class);
        query.whereEqualTo("association", user.getParseObject("association"));
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<Destination>() {
            @Override
            public void done(List<Destination> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0) {
                        //Write logic here
                    } else {
                        //List is empty
                    }
                } else {

                }
            }
        });

    }

}
