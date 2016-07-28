package kia.nodemail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FragmentOglen#newInstance} factory method to
 * create an instance of this fragment.
 */

public class FragmentOglen extends Fragment implements FoodTabbed.YourFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView ocorba, oana,oek,otatli,baslik;
    ServerRequest sr;
    List<NameValuePair> params;
    String anayemek,ekyemek,tatli,corba;
    Button oncekigun, sonrakigun;
    SharedPreferences yemekoglen;
    View rootView;
    ArrayAdapter adapter;
    ListView lw_yemek;
    Typeface font;



    public FragmentOglen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     *
     * @return A new instance of fragment FragmentOglen.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentOglen newInstance() {
        FragmentOglen fragment = new FragmentOglen();
        Bundle args = new Bundle();

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
        // Inflate the layout for this fragment
        yemekoglen = getContext().getSharedPreferences("OglenYemek",Context.MODE_PRIVATE);
        if(FoodTabbed.haftaningunu == 1 ||FoodTabbed.haftaningunu == 7){
            rootView = inflater.inflate(R.layout.fragment_haftasonu,container,false);
            Log.d("Hello", String.valueOf(FoodTabbed.haftaningunu));
        }
        else {
            final String[] values = new String[]{yemekoglen.getString("anayemek" + FoodTabbed.gun, ""),yemekoglen.getString("ekyemek" + FoodTabbed.gun, ""),yemekoglen.getString("tatli" + FoodTabbed.gun, ""),yemekoglen.getString("corba" + FoodTabbed.gun, "")};
           // final String[] values = new String[]{"ahmet","mehmet"};
            font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ornitons-Medium.ttf");

            rootView = inflater.inflate(R.layout.fragment_oglen, container, false);
            TextView belirtec_oglen = (TextView) rootView.findViewById(R.id.bildirgec_oglen);
            belirtec_oglen.setTypeface(font);
            lw_yemek = (ListView) rootView.findViewById(R.id.lw_oglenYemek);
            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.activity_listview,values){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){

                    View view = super.getView(position, convertView, parent);

                    TextView textview = (TextView) view.findViewById(R.id.label);

                    //Set your Font Size Here.

                    textview.setTypeface(font);

                    return view;
                }
            };
            lw_yemek.setAdapter(adapter);

            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ornitons-Medium.ttf");


          /*  ocorba = (TextView) rootView.findViewById(R.id.oglencorba);
            oana = (TextView) rootView.findViewById(R.id.oglenana);
            oek = (TextView) rootView.findViewById(R.id.oglenek);
            otatli = (TextView) rootView.findViewById(R.id.oglentatli);
            oncekigun = (Button) rootView.findViewById(R.id.gunonceki);
            sonrakigun = (Button) rootView.findViewById(R.id.gunsonraki);


           ocorba.setText(yemekoglen.getString("anayemek" + FoodTabbed.gun, ""));
            oana.setText(yemekoglen.getString("ekyemek" + FoodTabbed.gun, ""));
            oek.setText(yemekoglen.getString("tatli" + FoodTabbed.gun, ""));
            otatli.setText(yemekoglen.getString("corba" + FoodTabbed.gun, ""));*/

          /*  oncekigun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(FoodTabbed.gun) - 1 > 0) {
                        FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) - 1);
                        ocorba.setText(yemekoglen.getString("anayemek" + FoodTabbed.gun, ""));
                        oana.setText(yemekoglen.getString("ekyemek" + FoodTabbed.gun, ""));
                        oek.setText(yemekoglen.getString("tatli" + FoodTabbed.gun, ""));
                        otatli.setText(yemekoglen.getString("corba" + FoodTabbed.gun, ""));
                    } else Toast.makeText(getContext(), "G�n� A�t�n�z", Toast.LENGTH_SHORT).show();

                }
            });

           /* sonrakigun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(FoodTabbed.gun) + 1 < 31) {

                        FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) + 1);
                        final String[] values = new String[]{yemekoglen.getString("anayemek" + FoodTabbed.gun, ""),yemekoglen.getString("ekyemek" + FoodTabbed.gun, ""),yemekoglen.getString("tatli" + FoodTabbed.gun, ""),yemekoglen.getString("corba" + FoodTabbed.gun, "")};
                        adapter = new ArrayAdapter<String>(getActivity(),R.layout.activity_listview,values);
                        lw_yemek.setAdapter(adapter);
                   /*     ocorba.setText(yemekoglen.getString("anayemek" + FoodTabbed.gun, ""));
                        oana.setText(yemekoglen.getString("ekyemek" + FoodTabbed.gun, ""));
                        oek.setText(yemekoglen.getString("tatli" + FoodTabbed.gun, ""));
                        otatli.setText(yemekoglen.getString("corba" + FoodTabbed.gun, ""));

                    } else {
                        Toast.makeText(getContext(), "Günü Aştınız", Toast.LENGTH_SHORT).show();
                    }
                }

            });*/

        }


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

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
/*        ocorba.setText(yemekoglen.getString("anayemek"+FoodTabbed.gun,""));
        oana.setText( yemekoglen.getString("ekyemek"+FoodTabbed.gun,""));
        oek.setText(yemekoglen.getString("tatli"+FoodTabbed.gun,""));
        otatli.setText(yemekoglen.getString("corba"+FoodTabbed.gun,""));*/
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



}
