package kia.nodemail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class FoodTabbed extends AppCompatActivity{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static Calendar maintenant = Calendar.getInstance();
    public static String gun = String.valueOf(maintenant.get(Calendar.DAY_OF_MONTH));
    public static String ay = String.valueOf(maintenant.get(Calendar.MONTH)+1);
    public static String yil = String.valueOf(maintenant.get(Calendar.YEAR));
    public static int haftaningunu = maintenant.get(Calendar.DAY_OF_WEEK);
    SharedPreferences yemekoglen,yemekaksam,yemeksabah;
    ServerRequest sr;
    List<NameValuePair> params;
    String anayemek,ekyemek,tatli,corba;
    View dlProgressView;
    TabLayout tabLayout;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dlProgressView = findViewById(R.id.dl_progress);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tabbed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());



        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(mViewPager);
        for(int i=0; i < tabLayout.getTabCount()-1; i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 0, 0);
            tab.requestLayout();
        }

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                YourFragmentInterface fragment = (YourFragmentInterface) mSectionsPagerAdapter.instantiateItem(mViewPager, position);
                if (fragment != null) {
                    fragment.fragmentBecameVisible();
                }
                ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(position).setBackgroundResource(R.drawable.sabah);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //ÖÐLEN YEMEK ÇEKME
        yemekoglen = getSharedPreferences("OglenYemek", Context.MODE_PRIVATE);
        SharedPreferences.Editor yemekogleneditor = yemekoglen.edit();
        if(yemekoglen.getString("date"+FoodTabbed.gun,null) == null) {
            yemekogleneditor.clear();
            for (int i = 1; i <31; i++) {
                sr = new ServerRequest();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("date", String.valueOf(i) +"."  +FoodTabbed.ay +"."+ FoodTabbed.yil));
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
                yemekogleneditor.putString("date" + String.valueOf(i), String.valueOf(i) +"."+ FoodTabbed.ay +"."+ FoodTabbed.yil);
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
            for (int i = 1; i <31; i++) {
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
                ;
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
            for (int i = 1; i <31; i++) {
                sr = new ServerRequest();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("date", String.valueOf(i) +"."  +FoodTabbed.ay +"."+ FoodTabbed.yil));
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
                yemeksabaheditor.putString("date" + String.valueOf(i), String.valueOf(i) +"."+ FoodTabbed.ay +"."+ FoodTabbed.yil);
                yemeksabaheditor.putString("anayemek" + String.valueOf(i), anayemek);
                yemeksabaheditor.putString("ekyemek" + String.valueOf(i), ekyemek);
                yemeksabaheditor.putString("tatli" + String.valueOf(i), tatli);
                yemeksabaheditor.putString("corba" + String.valueOf(i), corba);
                yemeksabaheditor.commit();
            }
        }



      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {

            super(fm);



        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:

                    return new FragmentSabah().newInstance();
                case 1:
                    return new FragmentOglen().newInstance();
                case 2:
                    return new FragmentAksam().newInstance();

                default:
                    return new FragmentSabah();
            }

        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
            }
            return null;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public interface YourFragmentInterface {
        void fragmentBecameVisible();
    }



}
