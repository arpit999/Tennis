package com.bicubic.tennis.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bicubic.tennis.R;

public class MainscreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bt_signin,bt_signup;
    //Give your SharedPreferences file a name and save it to a static variable
    public static final String PREFS_NAME = "LoginActivity";
    public static SharedPreferences settings;
    public static boolean hasLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        settings = getSharedPreferences(MainscreenActivity.PREFS_NAME, 0);
        hasLoggedIn = settings.getBoolean("hasLoggedIn", false);
        //define if the user is login it should be in mainactiviy
        if (hasLoggedIn) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_mainscreen);

        FindViewByID();

    }

    private void FindViewByID() {

        bt_signin = (Button) findViewById(R.id.bt_signin);
        bt_signup= (Button) findViewById(R.id.bt_signup);

        bt_signin.setOnClickListener(this);
        bt_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.bt_signin:

                startActivity(new Intent(getApplicationContext(),SigninActivity.class));

                break;

            case R.id.bt_signup:

                startActivity(new Intent(getApplicationContext(),SignupActivity.class));

                break;
        }

    }


}
