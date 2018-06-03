package com.mide.adolf.socialmide;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewMedioObjFragment.OnNewFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewMedioObjFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Este fragmento se usa para añadir una nueva opcion a la lista de Medio.
 */
public class NewMedioObjFragment extends Fragment {

    BBDDLocal bdhelper;
    SQLiteDatabase db ;

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

        Button add = view.findViewById(R.id.main_button);
        final EditText ed = view.findViewById(R.id.main_editText);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String opcion = ed.getText().toString();
                guardarMedioOption(opcion);
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

    private void guardarMedioOption(String s) {

        if(s!="") {
            bdhelper = new BBDDLocal(this.getContext(), "options", null, 1);
            db = bdhelper.getWritableDatabase();

            if (db != null) {
                Log.d("NewMedioOptFrag",s);
                ContentValues nuevoRegistroA = new ContentValues();
                nuevoRegistroA.put("opcion", s);
                db.insert("options", null, nuevoRegistroA);
                db.close();
            }
        }else{
            Toast toast = Toast.makeText(this.getContext(), "Debes escribir algo", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
