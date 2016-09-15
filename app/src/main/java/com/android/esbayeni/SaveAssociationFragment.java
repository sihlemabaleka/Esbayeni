package com.android.esbayeni;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.esbayeni.model.Association;
import com.android.esbayeni.model.Route;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Spinner mAssociationSpinner, mRouteSpinner;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SaveAssociationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SaveAssociationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SaveAssociationFragment newInstance(String param1, String param2) {
        SaveAssociationFragment fragment = new SaveAssociationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ui_select_association_layout, container, false);

        mAssociationSpinner = (Spinner) v.findViewById(R.id.association_spinner);
        mRouteSpinner = (Spinner) v.findViewById(R.id.route_spinner);

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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List<String> _lables = new ArrayList<String>();
                for (Route route : list.get(position).getRoutes()) {
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
