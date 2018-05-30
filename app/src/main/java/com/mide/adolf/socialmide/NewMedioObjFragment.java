package com.mide.adolf.socialmide;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewMedioObjFragment.OnNewFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewMedioObjFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewMedioObjFragment extends Fragment {

    private OnNewFragmentInteractionListener mListener;

    public NewMedioObjFragment() {
        // Required empty public constructor
    }

    public static NewMedioObjFragment newInstance(String param1, String param2) {
        NewMedioObjFragment fragment = new NewMedioObjFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_medio_obj, container, false);

        Button add = view.findViewById(R.id.bt_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarMedioOption();
            }
        });
        return view;
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onFragmentInteraction("");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNewFragmentInteractionListener) {
            mListener = (OnNewFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnNewFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String s);
    }

    private void guardarMedioOption() {
        SqliteH
    }
}
