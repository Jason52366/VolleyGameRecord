package com.volleygamerecord.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;


public class LogIn extends Activity {


    String bbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Button btn_test = (Button)findViewById(R.id.button_TEST);
        btn_test.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder editDialog = new AlertDialog.Builder(LogIn.this);
                editDialog.setTitle("--- Edit框標題 ---");
                final EditText editText = new EditText(LogIn.this);
                editDialog.setView(editText);
                editDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        bbb= (editText.getText().toString());
                    }
                });
                editDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {
                        //...
                    }
                });
                editDialog.show();

            }
        });

        //Parse.enableLocalDatastore(this);
        /*                               Application ID                  ,     Client ID     */
        Parse.initialize(this, "OOyy4I805eCgkyEGCiZtAH2RybkVl2tWi4qulbkw", "AOXZIHWss8wAiupkyTQuhEelITKfQ3LUeXAdHVTL");
            ParseFacebookUtils.initialize("1393614940913937");

        //Login的確定按鈕
        Button btn_loginLogin = (Button)findViewById(R.id.button_loginLogin);
        btn_loginLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                List<String> permissions = Arrays.asList("basic_info","user_about_me");

                final ProgressDialog dialog = ProgressDialog.show(LogIn.this,"", "鴿子封包傳遞中", true);
                ParseFacebookUtils.logIn(permissions, LogIn.this,new LogInCallback() {

                    @Override
                    public void done(ParseUser user, ParseException err) {
                        DataCenter.getInstance().setValue("parseUserName", user.getUsername());

                        // Code to handle login.  沒有網路會死在這行
                        Session session = ParseFacebookUtils.getSession();
                        if(session != null && session.isOpened())
                        {
                        makeMeRequest();
                        }else {
                            dialog.dismiss();

                        }

                    }

              });

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



    //檢查連線是否成功
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

                                String fbName = (String)userProfile.get("name");
                                Log.e("fbName!!!", fbName);
                                DataCenter.getInstance().setValue("fbName",fbName);

                                //登入成功後才切換畫面
                                Intent intent = new Intent();
                                intent.setClass(LogIn.this, Start.class);
                                startActivity(intent);
                                LogIn.this.finish();


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


    SaveCallback aaa = new SaveCallback() {
        @Override
        public void done(ParseException e) {
            Log.d("done","Congratulation")  ;
        }
    };
}
