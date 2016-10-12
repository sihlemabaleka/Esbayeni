package com.android.esbayeni.login;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.esbayeni.R;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegistrationActivityFragment extends Fragment {

    private TextInputEditText mEmailAddress;

    public RegistrationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    public void checkEmail() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", mEmailAddress.getText().toString().trim());
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (e == null) {
                    ParseUser.requestPasswordResetInBackground(mEmailAddress.getText().toString().trim().toLowerCase(), new RequestPasswordResetCallback() {
                        @Override
                        public void done(ParseException e) {
                            Toast.makeText(getActivity(), "We've sent a password reset link to your inbox. Please reset and try signing in again", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {

                }
            }
        });
    }


}
