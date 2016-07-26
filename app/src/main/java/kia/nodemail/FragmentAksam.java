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
 * Use the {@link FragmentAksam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAksam extends Fragment implements FoodTabbed.YourFragmentInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView acorba,aana,aek,atatli;
    ServerRequest sr;
    List<NameValuePair> params;
    String anayemek,ekyemek,tatli,corba;
    SharedPreferences yemekaksam;
    Button aksamonceki,aksamsonraki;
    View rootView;

   // private FragmentSabah.OnFragmentInteractionListener mListener;

    public FragmentAksam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentAksam.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentAksam newInstance() {
        FragmentAksam fragment = new FragmentAksam();



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

        Log.d("Hello","Ben geldim");
        yemekaksam = getContext().getSharedPreferences("AksamYemek",Context.MODE_PRIVATE);

        if(FoodTabbed.haftaningunu == 1 ||FoodTabbed.haftaningunu == 7){
            rootView = inflater.inflate(R.layout.fragment_haftasonu,container,false);
            Log.d("Hello", String.valueOf(FoodTabbed.haftaningunu));
        }
        else {

            rootView = inflater.inflate(R.layout.fragment_aksam, container, false);
            ListView lw_aksamYemek = (ListView) rootView.findViewById(R.id.lw_aksamYemek);
            final String[] values = new String[]{yemekaksam.getString("anayemek" + FoodTabbed.gun, ""),yemekaksam.getString("ekyemek" + FoodTabbed.gun, ""),yemekaksam.getString("tatli" + FoodTabbed.gun, ""),yemekaksam.getString("corba" + FoodTabbed.gun, "")};
            ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),R.layout.activity_listview,values);
            TextView tarih = (TextView) rootView.findViewById(R.id.tarih_aksam);
            tarih.setText(String.valueOf(FoodTabbed.gun) + "." + String.valueOf((FoodTabbed.ay) ) + "." + String.valueOf(FoodTabbed.yil));
            lw_aksamYemek.setAdapter(adapter);
       //     TextView baslik = (TextView) rootView.findViewById(R.id.baslikuni);
            Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Ornitons-Medium.ttf");
//            baslik.setTypeface(font);
         /*   aksamonceki = (Button) rootView.findViewById(R.id.aksamonceki);
            aksamsonraki = (Button) rootView.findViewById(R.id.aksamsonraki);
            acorba = (TextView) rootView.findViewById(R.id.aksamcorba);
            aana = (TextView) rootView.findViewById(R.id.aksamana);
            aek = (TextView) rootView.findViewById(R.id.aksamek);
            atatli = (TextView) rootView.findViewById(R.id.aksamtatli);

            acorba.setText(yemekaksam.getString("anayemek" + FoodTabbed.gun, ""));
            aana.setText(yemekaksam.getString("ekyemek" + FoodTabbed.gun, ""));
            aek.setText(yemekaksam.getString("tatli" + FoodTabbed.gun, ""));
            atatli.setText(yemekaksam.getString("corba" + FoodTabbed.gun, ""));

            aksamonceki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(FoodTabbed.gun) - 1 > 0) {
                        FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) - 1);
                        acorba.setText(yemekaksam.getString("anayemek" + FoodTabbed.gun, ""));
                        aana.setText(yemekaksam.getString("ekyemek" + FoodTabbed.gun, ""));
                        aek.setText(yemekaksam.getString("tatli" + FoodTabbed.gun, ""));
                        atatli.setText(yemekaksam.getString("corba" + FoodTabbed.gun, ""));
                    } else Toast.makeText(getContext(), "Günü Aþtýnýz", Toast.LENGTH_SHORT).show();

                }
            });

            aksamsonraki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Integer.valueOf(FoodTabbed.gun) + 1 < 31) {
                        FoodTabbed.gun = String.valueOf(Integer.valueOf(FoodTabbed.gun) + 1);
                        acorba.setText(yemekaksam.getString("anayemek" + FoodTabbed.gun, ""));
                        aana.setText(yemekaksam.getString("ekyemek" + FoodTabbed.gun, ""));
                        aek.setText(yemekaksam.getString("tatli" + FoodTabbed.gun, ""));
                        atatli.setText(yemekaksam.getString("corba" + FoodTabbed.gun, ""));

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
       /* acorba.setText(yemekaksam.getString("anayemek"+FoodTabbed.gun,""));
        aana.setText( yemekaksam.getString("ekyemek"+FoodTabbed.gun,""));
        aek.setText(yemekaksam.getString("tatli"+FoodTabbed.gun,""));
        atatli.setText(yemekaksam.getString("corba"+FoodTabbed.gun,""));*/
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
