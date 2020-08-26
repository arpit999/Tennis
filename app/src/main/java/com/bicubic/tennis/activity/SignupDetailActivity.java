package com.bicubic.tennis.activity;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.bicubic.tennis.R;
import com.bicubic.tennis.adapter.NothingSelectedSpinnerAdapter;

public class SignupDetailActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_submit;
    EditText et_first_name, et_age;
    Spinner spin_country;
    private ProgressBar progressBar;
    String[] country = new String[]{"India", "Pakistan", "USA", "Australia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_detail);

        FindId();

    }

    private void FindId() {

        bt_submit = (Button) findViewById(R.id.bt_submit);
        et_first_name = (EditText) findViewById(R.id.et_first_name);
        et_age = (EditText) findViewById(R.id.et_age);
        spin_country = (Spinner) findViewById(R.id.spin_country);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, 0x81C784));

        bt_submit.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_country.setPrompt("Select your favorite Planet!");

        spin_country.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_submit:

                Intent intent = new Intent(getApplicationContext(), SignupCompleteActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

                break;

            default:


                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

}
