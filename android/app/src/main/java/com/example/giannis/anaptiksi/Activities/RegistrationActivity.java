package com.example.giannis.anaptiksi.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giannis.anaptiksi.Pojo.AndroidClient;
import com.example.giannis.anaptiksi.R;
import com.example.giannis.anaptiksi.Requests.HttpPostCredentials;

/**
 * Created by ubundistas on 20/1/2016.
 */
public class RegistrationActivity extends Activity {

    private static final int REQUEST_READ_CONTACTS = 0;


    // UI references.
    private Button RegisterView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmView;
    private EditText mUsernameView;
    private View mProgressView;
    private View mLoginFormView;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginEditor;
    private Button mSignoutView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        mPasswordView = (EditText) findViewById(R.id.pass);
        RegisterView = (Button) findViewById(R.id.buttonreg);
        mPasswordConfirmView = (EditText) findViewById(R.id.confpass);
        mUsernameView = (EditText) findViewById(R.id.user);

        loginPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loginEditor = loginPreferences.edit();

        mUsernameView.setHintTextColor(Color.GRAY);
        mPasswordView.setHintTextColor(Color.GRAY);
        mPasswordConfirmView.setHintTextColor(Color.GRAY);
        mUsernameView.setHint("Username");
        mPasswordView.setHint("Password");
        mPasswordConfirmView.setHint("Confirm Password");


        RegisterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkCredentials()) {


                        HttpPostCredentials post = new HttpPostCredentials();
                        post.setParams(new AndroidClient(mUsernameView.getText().toString(),mPasswordView.getText().toString()));
                        post.execute();
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }


            }
        });

    }



   /* private void saveCredentials(String username, String password,String passcon) {
       username = mUsernameView.getText().toString();
        password = mPasswordView.getText().toString();
        passcon = mPasswordConfirmView.getText().toString();


        loginEditor.putString("Username_Key", username);
        loginEditor.putString("Password_Key", password);
        loginEditor.putString("Pass_Con_Key",passcon);
        loginEditor.putString("isLoggedIn", "yes");
        loginEditor.commit();
    } */

    private boolean checkCredentials() {
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConf = mPasswordConfirmView.getText().toString();
        boolean result = true;
        boolean identical = false;





        if (username.isEmpty()) {
            if (password.isEmpty() && passwordConf.isEmpty()) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please complete all the fields in the form.", Toast.LENGTH_LONG);
                mUsernameView.setHintTextColor(Color.GRAY);
                mPasswordView.setHintTextColor(Color.GRAY);
                mPasswordConfirmView.setHintTextColor(Color.GRAY);
                mUsernameView.setHint("Username");
                mPasswordView.setHint("Password");
                mPasswordConfirmView.setHint("Confirm Password");
                toast.show();
                result = false;
                return result;
            } else {
                // usernameText.setHintTextColor(Color.RED);
                // usernameText.setHint("Username cannot be Blank");
                mUsernameView.setError("Username cannot be Blank");
                result = false;
            }
        } else mUsernameView.setError(null);

        if (password.isEmpty()) {
            //passwordText.setHintTextColor(Color.RED);
            //passwordText.setHint("Password cannot be Blank");
            mPasswordView.setError("Password cannot be Blank");
            result = false;
        } else mPasswordView.setError(null);

        if (passwordConf.isEmpty()) {
            //passwordConfText.setHintTextColor(Color.RED);
            // passwordConfText.setHint("Confirmation cannot be Blank");
            mPasswordConfirmView.setError("Confirmation cannot be Blank");
            result = false;
        } else mPasswordConfirmView.setError(null);

        if (username.contains(" ") || password.contains(" ") || passwordConf.contains(" ")) {
            if (username.contains(" ")) {
                mUsernameView.setText("");
                //usernameText.setHintTextColor(Color.RED);
                //usernameText.setHint("Username has gaps");
                mUsernameView.setError("Username has gaps");
            } else mUsernameView.setError(null);
            if (password.contains(" ")) {
                mPasswordView.setText("");
                // passwordText.setHintTextColor(Color.RED);
                // passwordText.setHint("Password has gaps");
                mPasswordView.setError("Password has gaps");
            } else mPasswordView.setError(null);

            if (passwordConf.contains(" ")) {
                mPasswordConfirmView.setText("");
                //passwordConfText.setHintTextColor(Color.RED);
                //passwordConfText.setHint("Confirmation has gaps");
                mPasswordConfirmView.setError("Confirmation has gaps");
            } else mPasswordConfirmView.setError(null);

            return false;
        }

        if (password.equals(passwordConf)) {
            identical = true;
            return result;
        }
        else{

            mPasswordConfirmView.setText("");
            mPasswordConfirmView.setError("Not Match with the Password");
            Toast toast = Toast.makeText(getApplicationContext(), "Password and Confirm Password don't match ", Toast.LENGTH_LONG);
            toast.show();

            return false;


        }

    }
}