package kia.nodemail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ksi on 23.05.2016
 */
public class ProfileActiv extends AppCompatActivity{
    String token1,grav1;
    SharedPreferences pref;
    TextView token_text,grav_text;
    Button logout,yemekgec,yemekswipe;
    UserSessionManager session;
    ImageButton face,twitter,linkedIn;
    SharedPreferences yemekoglen,yemekaksam,yemeksabah;
    ServerRequest sr;
    List<NameValuePair> params;
    String anayemek,ekyemek,tatli,corba;
    View dlProgressView;
    TabLayout tabLayout;
    ProgressDialog progress;
    GridLayout gridLayout;
    Typeface font;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_activity);
        session = new UserSessionManager(getApplicationContext());
        font = Typeface.createFromAsset(getAssets(),"fonts/Ornitons-Medium.ttf");

        /*EÐER LOGIN SÝSTEMÝ GELÝRSE AKTÝF ET
        if(session.checkLogin())
            finish();*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.home_beyaz2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

      //  Toast.makeText(getApplicationContext(),
        //        "User Login Status: " + session.isUserLoggedIn(),
         //       Toast.LENGTH_LONG).show();


        //pref = getSharedPreferences("AppPref", MODE_PRIVATE);
      //  token1 = pref.getString("name", "");
       // grav1 = pref.getString("mail", "");

        yemekswipe = (Button) findViewById(R.id.menu_yemek);
        yemekswipe.setTypeface(font);
        Button etkinliklerButton = (Button) findViewById(R.id.menu_etkinlikler);
        etkinliklerButton.setTypeface(font);
        Button hakkimizdaButton = (Button) findViewById(R.id.menu_hakkimizda);
        hakkimizdaButton.setTypeface(font);
        Button duyurularButton = (Button) findViewById(R.id.menu_duyurular);
        duyurularButton.setTypeface(font);
        Button hocalarButton = (Button) findViewById(R.id.menu_hocalar);
        hocalarButton.setTypeface(font);
        Button kikencereButton = (Button) findViewById(R.id.menu_kikencere);
        kikencereButton.setTypeface(font);
        kikencereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUrl("http://uni.gsu.edu.tr");
            }
        });

        face = (ImageButton) findViewById(R.id.facebook_btn);
        twitter = (ImageButton) findViewById(R.id.twitter_btn);
        linkedIn = (ImageButton) findViewById(R.id.linkedin_btn);
       /* logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                finish();
            }
        });*/
        /*yemekgec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent foodactivity = new Intent(ProfileActiv.this,FoodActivity.class);
                startActivity(foodactivity);
                finish();
            }
        });*/
        face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFace();
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTwitter();
            }
        });
        linkedIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLinkedIn();
            }
        });
        yemekswipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PrefetchData(getApplicationContext()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
              //  finish();
            }
        });




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_food_tabbed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_search:

                return true;

            case R.id.action_user:
                Intent proflele = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(proflele);

                return true;
            //case R.id.action_home:
            //return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
    public  void goToFace(){
        goToUrl("https://www.facebook.com/groups/2261993485/?fref=ts");
    }
    public void goToTwitter(){
        goToUrl("https://twitter.com/Galatasaray_Uni");
    }
    public  void goToLinkedIn(){
        goToUrl("https://www.linkedin.com/groups/3510548/profile");
    }
    public void goToUrl(String url){
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
    private class PrefetchData extends AsyncTask<Void, Void, Void> {
        private Context context;
        ProgressDialog dialog;
        public PrefetchData(Context context) {
            this.context = context;
            dialog = new ProgressDialog(ProfileActiv.this);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Yemekler Ýndiriliyor");
            this.dialog.show();
            // before making http calls

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            /*
             * Will make http call here This call will download required data
             * before launching the app
             * example:
             * 1. Downloading and storing in SQLite
             * 2. Downloading images
             * 3. Fetching and parsing the xml / json
             * 4. Sending device information to server
             * 5. etc.,
             */



            yemekoglen = getSharedPreferences("OglenYemek", Context.MODE_PRIVATE);
            SharedPreferences.Editor yemekogleneditor = yemekoglen.edit();
            if(yemekoglen.getString("date"+FoodTabbed.gun,null) == null) {

                    yemekogleneditor.clear();
                    for (int i = 1; i < 31; i++) {
                        sr = new ServerRequest();
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("date", String.valueOf(i) + "." + FoodTabbed.ay + "." + FoodTabbed.yil));
                        JSONObject json = sr.getJSON("http://deneme-gleoozhk.c9.io:8080/oglen", params);

                        try {
                            anayemek = json.getString("anayemek");
                            ekyemek = json.getString("ekyemek");
                            tatli = json.getString("tatli");
                            corba = json.getString("corba");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ;
                        yemekogleneditor.putString("date" + String.valueOf(i), String.valueOf(i) + "." + FoodTabbed.ay + "." + FoodTabbed.yil);
                        yemekogleneditor.putString("anayemek" + String.valueOf(i), anayemek);
                        yemekogleneditor.putString("ekyemek" + String.valueOf(i), ekyemek);
                        yemekogleneditor.putString("tatli" + String.valueOf(i), tatli);
                        yemekogleneditor.putString("corba" + String.valueOf(i), corba);
                        yemekogleneditor.commit();
                    }

            }
            //AKÞAM YEMEK ÇEKME
            yemekaksam = getSharedPreferences("AksamYemek",Context.MODE_PRIVATE);
            SharedPreferences.Editor yemekaksameditor = yemekaksam.edit();
            if(yemekaksam.getString("date"+FoodTabbed.gun,null) == null) {

                    yemekaksameditor.clear();
                    for (int i = 1; i < 31; i++) {
                        sr = new ServerRequest();
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("date", String.valueOf(i) + "." + FoodTabbed.ay + "." + FoodTabbed.yil));
                        JSONObject json = sr.getJSON("http://deneme-gleoozhk.c9.io:8080/aksam", params);

                        try {
                            anayemek = json.getString("anayemek");
                            ekyemek = json.getString("ekyemek");
                            tatli = json.getString("tatli");
                            corba = json.getString("corba");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        yemekaksameditor.putString("date" + String.valueOf(i), String.valueOf(i) + "." + FoodTabbed.ay + "." + FoodTabbed.yil);
                        yemekaksameditor.putString("anayemek" + String.valueOf(i), anayemek);
                        yemekaksameditor.putString("ekyemek" + String.valueOf(i), ekyemek);
                        yemekaksameditor.putString("tatli" + String.valueOf(i), tatli);
                        yemekaksameditor.putString("corba" + String.valueOf(i), corba);
                        yemekaksameditor.commit();
                    }

            }

            //SABAH YEMEK ÇEKME
            yemeksabah = getSharedPreferences("SabahYemek",Context.MODE_PRIVATE);
            SharedPreferences.Editor yemeksabaheditor = yemeksabah.edit();
            if(yemeksabah.getString("date"+FoodTabbed.gun,null) == null) {

                    yemeksabaheditor.clear();
                    for (int i = 1; i < 31; i++) {
                        sr = new ServerRequest();
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("date", String.valueOf(i) + "." + FoodTabbed.ay + "." + FoodTabbed.yil));
                        JSONObject json = sr.getJSON("http://deneme-gleoozhk.c9.io:8080/sabah", params);

                        try {
                            anayemek = json.getString("anayemek");
                            ekyemek = json.getString("ekyemek");
                            tatli = json.getString("tatli");
                            corba = json.getString("corba");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ;
                        yemeksabaheditor.putString("date" + String.valueOf(i), String.valueOf(i) + "." + FoodTabbed.ay + "." + FoodTabbed.yil);
                        yemeksabaheditor.putString("anayemek" + String.valueOf(i), anayemek);
                        yemeksabaheditor.putString("ekyemek" + String.valueOf(i), ekyemek);
                        yemeksabaheditor.putString("tatli" + String.valueOf(i), tatli);
                        yemeksabaheditor.putString("corba" + String.valueOf(i), corba);
                        yemeksabaheditor.commit();
                    }

            }


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // After completing http call
            // will close this activity and lauch main activity
            this.dialog.dismiss();
            Intent yemekswipeactivity = new Intent(getApplicationContext(),FoodTabbed.class);
            startActivity(yemekswipeactivity);
        }

    }

}
