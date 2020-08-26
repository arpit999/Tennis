package com.bicubic.tennis.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bicubic.tennis.R;
import com.bicubic.tennis.utils.ConnectivityReceiver;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_send;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        FindId();

        bt_send.setOnClickListener(this);

    }

    private void FindId() {

        et_email = (EditText) findViewById(R.id.et_email);
        bt_send = (Button) findViewById(R.id.bt_send);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_send:
                if (!ConnectivityReceiver.isConnected()) {
                    Toast.makeText(getApplicationContext(), "Please check your internet ", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(et_email.getText().toString())&& TextUtils.isEmpty(et_email.getText())) {

                    et_email.setError("Invalid Email");

                } else {

                    startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                    finish();

                }

                break;

        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
