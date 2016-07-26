package kia.nodemail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_activity);
        session = new UserSessionManager(getApplicationContext());
        if(session.checkLogin())
            finish();
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

        yemekswipe = (Button) findViewById(R.id.yemekswipe);
        face = (ImageButton) findViewById(R.id.facebook_btn);
        twitter = (ImageButton) findViewById(R.id.twitter_btn);
        linkedIn = (ImageButton) findViewById(R.id.linkedin_btn);
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                finish();
            }
        });
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
                Intent yemekswipeactivity = new Intent(ProfileActiv.this,FoodTabbed.class);
                startActivity(yemekswipeactivity);
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

}
