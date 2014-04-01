package com.volleygamerecord.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;


public class LogIn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Parse.initialize(this, "fxeOW9nXZeLpl9cdzkIl7Bytdk0GWgOwFIO8BYm5", "gVjrvp2trzCD8hfbhZguZKJ1luOSRsjbI1WMJ5GG");
        ParseFacebookUtils.initialize("1393614940913937");

        //Login的確定按鈕
        Button btn_loginLogin = (Button)findViewById(R.id.button_loginLogin);
        btn_loginLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<String> permissions = Arrays.asList("basic_info","user_about_me");
                ParseFacebookUtils.logIn(permissions, LogIn.this,new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException err) {
                        // Code to handle login.
                        Session session = ParseFacebookUtils.getSession();
                        if(session != null && session.isOpened()) makeMeRequest();
                    }
                });
                Intent intent = new Intent();
                intent.setClass(LogIn.this, Start.class);
                startActivity(intent);
                LogIn.this.finish();
            }

        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.log_in, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

    //關於request的部份
    private void makeMeRequest() {
        Request request = Request.newMeRequest(ParseFacebookUtils.getSession(),
                new Request.GraphUserCallback() {
                    @Override
                    public void onCompleted(GraphUser user, Response response) {
                        if (user != null) {
                            // Create a JSON object to hold the profile info
                            JSONObject userProfile = new JSONObject();
                            try {
                                // Populate the JSON object
                                userProfile.put("facebookId", user.getId());
                                userProfile.put("name", user.getName());

                                // Now add the data to the UI elements
                                // ...
                            } catch (JSONException e) {
                                Log.d("Myapp",
                                        "Error parsing returned user data.");
                            }

                        } else if (response.getError() != null) {
                            // handle error
                        }
                    }
                });
        request.executeAsync();

    }
}
