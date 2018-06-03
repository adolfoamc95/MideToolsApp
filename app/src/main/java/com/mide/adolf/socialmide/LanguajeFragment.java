package com.mide.adolf.socialmide;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LanguajeFragment.OnLangFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LanguajeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Este fragmento contiene dos botones para el cambio de lenguaje de la aplicación
 */
public class LanguajeFragment extends Fragment {

    private OnLangFragmentInteractionListener mListener;

    public LanguajeFragment() {
        // Required empty public constructor
    }


    public static LanguajeFragment newInstance(String param1, String param2) {
        LanguajeFragment fragment = new LanguajeFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_languaje, container, false);

        final Switch switchesp = v.findViewById(R.id.switch_es);
        final Switch switchen = v.findViewById(R.id.switch_en);
        final TextView texAlert = v.findViewById(R.id.textViewLang);
        SharedPreferences prefs = getActivity().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String lang = prefs.getString("language", "es");
        if(lang.equals("es")){
            switchesp.setChecked(true);
        }else{
            switchen.setChecked(true);
        }

        switchesp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setEsLang();
                    switchen.setChecked(false);
                    texAlert.setText("Idioma cambiado a español");
                }else {
                    switchen.setChecked(true);
                }
            }
        });

        switchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    setEnLang();
                    switchesp.setChecked(false);
                    texAlert.setText("Language changed to english");
                }else{
                    switchesp.setChecked(true);
                }
            }
        });
        return v;
    }

    private void setEsLang() {
        if (mListener != null) {
            mListener.onFragmentInteraction("setES");
        }
    }

    private void setEnLang() {
        if (mListener != null) {
            mListener.onFragmentInteraction("setEN");
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLangFragmentInteractionListener) {
            mListener = (OnLangFragmentInteractionListener) context;
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
    public interface OnLangFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String s);
    }
}
