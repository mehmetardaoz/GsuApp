package kia.nodemail;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ksi on 23.05.2016
 */
public class ProfileActiv extends Activity {
    String token1,grav1;
    SharedPreferences pref;
    TextView token_text,grav_text;
    Button logout,yemekgec,yemekswipe;
    UserSessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_activity);
        session = new UserSessionManager(getApplicationContext());
        if(session.checkLogin())
            finish();

      //  Toast.makeText(getApplicationContext(),
        //        "User Login Status: " + session.isUserLoggedIn(),
         //       Toast.LENGTH_LONG).show();


        //pref = getSharedPreferences("AppPref", MODE_PRIVATE);
      //  token1 = pref.getString("name", "");
       // grav1 = pref.getString("mail", "");
        token_text = (TextView) findViewById(R.id.token);
        grav_text = (TextView) findViewById(R.id.token2);
        yemekgec = (Button) findViewById(R.id.foodbutton);
        yemekswipe = (Button) findViewById(R.id.yemekswipe);

        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
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

        yemekswipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent yemekswipeactivity = new Intent(ProfileActiv.this,FoodTabbed.class);
                startActivity(yemekswipeactivity);
              //  finish();
            }
        });
        token_text.setText(token1);
        grav_text.setText(grav1);


    }
}
