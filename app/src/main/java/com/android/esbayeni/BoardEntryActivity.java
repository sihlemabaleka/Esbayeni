package com.android.esbayeni;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.esbayeni.model.Association;
import com.android.esbayeni.model.Destination;
import com.android.esbayeni.model.Driver;
import com.android.esbayeni.model.Rank;
import com.android.esbayeni.model.TaxiMarshall;
import com.android.esbayeni.model.Vehicle;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class BoardEntryActivity extends Activity implements View.OnClickListener {

    Boolean saveDriverInformation = false;
    private Spinner sTaxiRank, sVehicleReg, sOrigin, sDestination;
    private Button btnInsertIntoLine;
    private EditText eDriverName;


    private TaxiMarshall user;

    /*

        Here's the logic to remember
            1. Get Taxi Rank first
            2. Get Vehicle Registration and stuff
            3. Get Destinations
            4.


            Some Side Notes to Ask Bongani
            Can we get routes, since they all don't drive the same

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        user = (TaxiMarshall) ParseUser.getCurrentUser();


        btnInsertIntoLine = (Button) findViewById(R.id.proceed);
        eDriverName = (EditText) findViewById(R.id.adjust_width);
        eDriverName.addTextChangedListener(new MyTextChangeListener());

        btnInsertIntoLine.setOnClickListener(this);

    }

    public void getAssociation(TaxiMarshall marshall) {
        GetAssociation association = new GetAssociation();
        association.execute(marshall);
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


    /*
        Implement TextInput Autocomplete method in here
        Pin this information for the day. Create method that will only pin for a day.
        Maybe a service or something
    */
    public void getDriverInformation() {

        ParseQuery<Driver> driver_query = ParseQuery.getQuery(Driver.class);
        driver_query.whereContains("driver_name", eDriverName.getText().toString().trim());
        driver_query.findInBackground(new FindCallback<Driver>() {
            @Override
            public void done(List<Driver> objects, ParseException e) {
                if (e == null) {
                    if (objects != null) {
                        if (objects.size() > 0) {
                            //Here's a list of the autocomplete candidates
                        } else {
                            //No result was found. Implement new driver creation feature
                            saveDriverInformation = true;
                        }
                    }
                } else {
                    //Probably an internet error fam
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
                    //Insternet error save later or check internal database and query from there
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

    @Override
    public void onClick(View v) {

    }

    class MyTextChangeListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Do Character change check here
            if (count > 0 && !eDriverName.getText().toString().trim().isEmpty()) {
                //Enable button click here here
            } else {
                eDriverName.setError("Insert a name here bro!");
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public class GetAssociation extends AsyncTask<TaxiMarshall, Void, Association> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Association doInBackground(TaxiMarshall... params) {
            try {
                Association association = params[0].getAssociation();
                return association.fetchIfNeeded();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Association association) {
            super.onPostExecute(association);
            if (association != null) {
                //We got an association variable fam
            }
        }
    }


}
