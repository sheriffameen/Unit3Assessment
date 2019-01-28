package org.pursuit.unit_03_assessment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.pursuit.unit_03_assessment.R;

public class LoginActivity extends AppCompatActivity {

    private static final String LOGIN_PREF_KEY = "com.example.android.hellosharedprefs";
    private EditText emailView;
    private EditText passwordView;
    private CheckBox usernameCheckbox;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //No need to cast the views
        emailView = findViewById(R.id.email_edittext);
        passwordView = findViewById(R.id.password_edittext);
        usernameCheckbox = findViewById(R.id.remember_username_checkbox);
        sharedPreferences = getSharedPreferences(LOGIN_PREF_KEY,MODE_PRIVATE);

        passwordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        /*
        * TODO: add logic to set values to views:
        * TODO: 1. if there is a username value AND checkbox value in shared preferences -
        * set the username EditText's value to the username value from shared preferences,
        * and set the checkbox's value to the checkbox value from shared preferences
        */

        /* You had to check if there were any values saved in shared preferences
           if there were some values, then you set that username value and checkbox's value
           to the views, so that they are auto-populated when you run the app and the user doesn't
           have to fill in the username. You're on the right track here.
         */
        String emailViewString = emailView.getText().toString();
        boolean remember_username_checkbox = usernameCheckbox.isChecked();

        if (emailViewString.equals(sharedPreferences.getString("username",null))&&
                sharedPreferences.getBoolean("isChecked",remember_username_checkbox)){
            emailView.setText(sharedPreferences.getString("username",emailViewString));
            usernameCheckbox.setChecked(true);
        }

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {

        emailView.setError(null);
        passwordView.setError(null);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordView.setError(getString(R.string.error_invalid_password));
            focusView = passwordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            emailView.setError(getString(R.string.error_field_required));
            focusView = emailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailView.setError(getString(R.string.error_invalid_email));
            focusView = emailView;
            cancel = true;
        } else {
            /*
             * TODO: Add logic to confirm that:
             * TODO: 2. the username matches the username stored in strings.xml and the password matches the password stored in strings.xml
             * TODO: 3. the checkbox is ticked - if both email and password in EditTexts match strings.xml, add username value and checkbox value to shared preferences
             * TODO: 4. the checkbox is NOT ticked - if it is not ticked, clear username in shared preferences
             * TODO: 5. if both email and password in EditTexts match strings.xml, move to RecyclerActivity
             */


            /* R.string.dummy_username is the id of your string, not the actual string,
            getResources().getString(R.string.dummy_username) is how you get the actual string value
            */
            String userNameValue = getResources().getString(R.string.dummy_username);
            String passwordValue = getResources().getString(R.string.dummy_password);

            if (email.equals(userNameValue) && password.equals(passwordValue)){
                if (usernameCheckbox.isChecked()){
                    editor.putString("username", email);
                    editor.putBoolean("isChecked", usernameCheckbox.isChecked());
                    editor.apply();
                }else if (!usernameCheckbox.isChecked()){
                    editor.remove(email);
                }

                /*You had RecyclerView.class as your second parameter, but you don't have a class with
                  that name.
                */
                Intent intent = new Intent(LoginActivity.this, RecyclerActivity.class);
                startActivity(intent);

            }
        }

        if (cancel) {
            focusView.requestFocus();
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }
}

