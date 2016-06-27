package kia.nodemail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Ksi on 22.05.2016.
 */
public class RegisterActivity extends Activity {
    EditText email,password;
    Button login,register;
    String emailtxt,passwordtxt;
    List<NameValuePair> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.email1);
        password = (EditText)findViewById(R.id.password2);
        register = (Button)findViewById(R.id.regbutton);
        login = (Button)findViewById(R.id.login);



        register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("email", emailtxt));
                params.add(new BasicNameValuePair("password", passwordtxt));
                ServerRequest sr = new ServerRequest();
                //JSONObject json = sr.getJSON("http://nodemail-nodemail.rhcloud.com/register",params);
                JSONObject json = sr.getJSON("http://deneme-gleoozhk.c9.io:8080/register",params);

                if(json != null){
                    try{
                        String jsonstr = json.getString("response");

                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();

                        Log.d("Hello", jsonstr);
                        if(jsonstr.compareTo("Sucessfully Registered") == 0){
                        Intent loginact = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(loginact);
                        finish();}
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



}
