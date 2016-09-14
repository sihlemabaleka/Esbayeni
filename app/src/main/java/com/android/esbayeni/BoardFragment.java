package com.android.esbayeni;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.esbayeni.model.Board;
import com.android.esbayeni.model.Route;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class BoardFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    public RecyclerView.Adapter adapter;
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<Board> boards = new ArrayList<Board>();
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BoardFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static BoardFragment newInstance(int columnCount) {
        BoardFragment fragment = new BoardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_board_list, container, false);

        adapter = new MyBoardRecyclerViewAdapter(boards);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(adapter);
        }

        getBoard(ParseUser.getCurrentUser());


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void getBoard(ParseUser user) {
        Route route = (Route) user.getParseObject("route");
        final ParseQuery<Board> board_query = ParseQuery.getQuery(Board.class);
        board_query.whereEqualTo("route", route);
        board_query.whereGreaterThan("createdAt", getCurrentDayMidnightDateStamp().getTime());
        board_query.findInBackground(new FindCallback<Board>() {
            @Override
            public void done(final List<Board> list, ParseException e) {
                if (e == null) {
                    //Queried Remote Data store Successfully
                    if (list.size() > 0) {
                        //Save all results to local data store
                        Board.pinAllInBackground(list, new SaveCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    //Pinned all results to local data store
                                    boards.clear();
                                    boards.addAll(list);
                                    adapter.notifyDataSetChanged();
                                } else {
                                    //Failed to pin all results

                                }
                            }
                        });
                    } else {
                        //No records found remotely. Create New Records
                    }
                } else {
                    //Failed to query remote date store
                    //Changing query data store location from remote to local datastore
                    board_query.fromLocalDatastore();
                    board_query.findInBackground(new FindCallback<Board>() {
                        @Override
                        public void done(List<Board> list, ParseException e) {
                            if (e == null) {
                                //Queried Local Data store Successfully
                                if (list.size() > 0) {
                                    //Recieved Legitimate result set
                                    boards.clear();
                                    boards.addAll(list);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                //Failed to Query local datastore, too
                            }
                        }
                    });
                }
            }
        });
    }

    public Calendar getCurrentDayMidnightDateStamp() {
        // today
        Calendar date = new GregorianCalendar();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Board item);
    }


}
