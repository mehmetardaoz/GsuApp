package kia.nodemail;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link FragmentSabah#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSabah extends Fragment implements FoodTabbed.YourFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView scorba,sana,sek,statli;
    SharedPreferences yemeksabah;
    Button sabahoncek,sabahsonraki;




    public FragmentSabah() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSabah.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSabah newInstance() {
        FragmentSabah fragment = new FragmentSabah();

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
        // Inflate the layout for this fragment

        yemeksabah = getContext().getSharedPreferences("SabahYemek",Context.MODE_PRIVATE);

        Log.d("Hello","Ben gittim");


        
        //// TODO: 26.06.2016 Tarih için yeni bi textview hazýrlayýp koy
        yemeksabah.getString("date"+FoodTabbed.gun,"");





        View rootView = inflater.inflate(R.layout.fragment_sabah, container, false);
        scorba = (TextView) rootView.findViewById(R.id.sabahcorba);
        sana = (TextView) rootView.findViewById(R.id.sabahana);
        sek = (TextView) rootView.findViewById(R.id.sabahek);
        statli = (TextView) rootView.findViewById(R.id.sabahtatli);
        sabahoncek = (Button) rootView.findViewById(R.id.sabahonceki);
        sabahsonraki = (Button) rootView.findViewById(R.id.sabahsonraki);

        scorba.setText(yemeksabah.getString("anayemek"+FoodTabbed.gun,""));
        sana.setText( yemeksabah.getString("ekyemek"+FoodTabbed.gun,""));
        sek.setText(yemeksabah.getString("tatli"+FoodTabbed.gun,""));
        statli.setText(yemeksabah.getString("corba"+FoodTabbed.gun,""));

        sabahoncek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(FoodTabbed.gun) - 1 > 0) {
                    FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) - 1);
                    scorba.setText(yemeksabah.getString("anayemek" + FoodTabbed.gun, ""));
                    sana.setText(yemeksabah.getString("ekyemek" + FoodTabbed.gun, ""));
                    sek.setText(yemeksabah.getString("tatli" + FoodTabbed.gun, ""));
                    statli.setText(yemeksabah.getString("corba" + FoodTabbed.gun, ""));
                }
                else Toast.makeText(getContext(), "Günü Aþtýnýz", Toast.LENGTH_SHORT).show();

            }
        });

        sabahsonraki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(FoodTabbed.gun) + 1 < 31) {
                    FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) + 1);
                    scorba.setText(yemeksabah.getString("anayemek" + FoodTabbed.gun, ""));
                    sana.setText(yemeksabah.getString("ekyemek" + FoodTabbed.gun, ""));
                    sek.setText(yemeksabah.getString("tatli" + FoodTabbed.gun, ""));
                    statli.setText(yemeksabah.getString("corba" + FoodTabbed.gun, ""));

                }
                else{
                    Toast.makeText(getContext(), "Günü Aþtýnýz", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void fragmentBecameVisible() {
        scorba.setText(yemeksabah.getString("anayemek"+FoodTabbed.gun,""));
        sana.setText( yemeksabah.getString("ekyemek"+FoodTabbed.gun,""));
        sek.setText(yemeksabah.getString("tatli"+FoodTabbed.gun,""));
        statli.setText(yemeksabah.getString("corba"+FoodTabbed.gun,""));
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

}
