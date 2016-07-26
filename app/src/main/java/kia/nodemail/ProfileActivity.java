package kia.nodemail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class ProfileActivity extends AppCompatActivity {
    Toolbar t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        t = (Toolbar) findViewById(R.id.toolbar);
        t.setNavigationIcon(R.drawable.home_beyaz2);
        setSupportActionBar(t);
       getSupportActionBar().setDisplayShowTitleEnabled(false);




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
}
