package com.android.esbayeni;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.esbayeni.model.Association;
import com.android.esbayeni.model.Route;
import com.android.esbayeni.model.TaxiMarshall;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaveAssociationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaveAssociationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "email";
    private Spinner mAssociationSpinner, mRouteSpinner;
    private Button btnNext;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SaveAssociationFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SaveAssociationFragment newInstance(String param1, String param2) {
        SaveAssociationFragment fragment = new SaveAssociationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_select_association_layout, container, false);

        mAssociationSpinner = (Spinner) v.findViewById(R.id.association_spinner);
        mRouteSpinner = (Spinner) v.findViewById(R.id.route_spinner);
        btnNext = (Button) v.findViewById(R.id.proceed);

        getAssociationList();
        return v;
    }

    private void populateSpinners(final List<Association> list) {
        List<String> lables = new ArrayList<String>();
        for (int i = 0; i > 0; i++) {
            lables.add(list.get(i).getAssociationAbbrevation());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        mAssociationSpinner.setAdapter(spinnerAdapter);

        mAssociationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int _position, long id) {
                List<String> _lables = new ArrayList<String>();
                for (Route route : list.get(_position).getRoutes()) {
                    _lables.add(route.getRouteName());
                }

                // Creating adapter for spinner
                ArrayAdapter<String> _spinnerAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, _lables);

                // Drop down layout style - list view with radio button
                _spinnerAdapter
                        .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                mRouteSpinner.setAdapter(_spinnerAdapter);
                mRouteSpinner.setVisibility(View.VISIBLE);

                mRouteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TaxiMarshall marshall = (TaxiMarshall) ParseUser.getCurrentUser();
                                marshall.setAssociation(list.get(_position));
                                marshall.setRoute(list.get(position).getRoutes().get(position));
                                marshall.pinInBackground();
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    public void getAssociationList() {
        ParseQuery<Association> query = ParseQuery.getQuery(Association.class);
        query.include("routes");
        query.findInBackground(new FindCallback<Association>() {
            @Override
            public void done(List<Association> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() > 0)
                        populateSpinners(objects);
                }
            }
        });
    }
}
