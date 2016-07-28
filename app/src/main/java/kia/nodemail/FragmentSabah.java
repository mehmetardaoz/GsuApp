package kia.nodemail;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    View rootView;
    Typeface font;




    public FragmentSabah() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
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
        yemeksabah.getString("date"+FoodTabbed.gun,"");



        if(FoodTabbed.haftaningunu == 1 ||FoodTabbed.haftaningunu == 7){
            rootView = inflater.inflate(R.layout.fragment_haftasonu,container,false);
            Log.d("Hello", String.valueOf(FoodTabbed.haftaningunu));
        }

        else {
            font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ornitons-Medium.ttf");
            rootView = inflater.inflate(R.layout.fragment_sabah, container, false);
            TextView belirtec_sabah = (TextView) rootView.findViewById(R.id.bildirgec_sabah);
            belirtec_sabah.setTypeface(font);
            ListView lw_sabahYemek = (ListView) rootView.findViewById(R.id.lw_sabahYemek);
            final String[] values = new String[]{yemeksabah.getString("anayemek" + FoodTabbed.gun, ""),yemeksabah.getString("ekyemek" + FoodTabbed.gun, ""),yemeksabah.getString("tatli" + FoodTabbed.gun, ""),yemeksabah.getString("corba" + FoodTabbed.gun, "")};
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

           // CustomListAdapter adapter = new CustomListAdapter(getActivity(),R.layout.activity_listview,values);
            lw_sabahYemek.setAdapter(adapter);

//            baslik.setTypeface(font);

           /* scorba = (TextView) rootView.findViewById(R.id.sabahcorba);
            sana = (TextView) rootView.findViewById(R.id.sabahana);
            sek = (TextView) rootView.findViewById(R.id.sabahek);
            statli = (TextView) rootView.findViewById(R.id.sabahtatli);
            sabahoncek = (Button) rootView.findViewById(R.id.sabahonceki);
            sabahsonraki = (Button) rootView.findViewById(R.id.sabahsonraki);

            scorba.setText(yemeksabah.getString("anayemek" + FoodTabbed.gun, ""));
            sana.setText(yemeksabah.getString("ekyemek" + FoodTabbed.gun, ""));
            sek.setText(yemeksabah.getString("tatli" + FoodTabbed.gun, ""));
            statli.setText(yemeksabah.getString("corba" + FoodTabbed.gun, ""));

            sabahoncek.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(FoodTabbed.gun) - 1 > 0) {
                        FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) - 1);
                        scorba.setText(yemeksabah.getString("anayemek" + FoodTabbed.gun, ""));
                        sana.setText(yemeksabah.getString("ekyemek" + FoodTabbed.gun, ""));
                        sek.setText(yemeksabah.getString("tatli" + FoodTabbed.gun, ""));
                        statli.setText(yemeksabah.getString("corba" + FoodTabbed.gun, ""));
                    } else Toast.makeText(getContext(), "Günü Aþtýnýz", Toast.LENGTH_SHORT).show();

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

                    } else {
                        Toast.makeText(getContext(), "Günü Aþtýnýz", Toast.LENGTH_SHORT).show();
                    }
                }

            });*/
        }

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
       /* scorba.setText(yemeksabah.getString("anayemek"+FoodTabbed.gun,""));
        sana.setText( yemeksabah.getString("ekyemek"+FoodTabbed.gun,""));
        sek.setText(yemeksabah.getString("tatli"+FoodTabbed.gun,""));
        statli.setText(yemeksabah.getString("corba"+FoodTabbed.gun,""));*/

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
