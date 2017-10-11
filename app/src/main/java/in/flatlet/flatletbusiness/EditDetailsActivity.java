package in.flatlet.flatletbusiness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import in.flatlet.flatletbusiness.utility.MySingleton;


public class EditDetailsActivity extends AppCompatActivity {
    private EditText edit_rent_double_nonac, edit_rent_double_ac, edit_rent_single_nonac, edit_rent_single_ac;
    private ImageView approvalImage;
    private ProgressBar progressBar;
    private String value_rent_single_nonac, value_rent_single_ac, value_rent_double_nonac, value_rent_double_ac;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edit_rent_double_ac = (EditText) findViewById(R.id.edit_rent_double_ac);
        edit_rent_double_nonac = (EditText) findViewById(R.id.edit_rent_double_nonac);
        edit_rent_single_nonac = (EditText) findViewById(R.id.edit_rent_single_nonac);
        edit_rent_single_ac = (EditText) findViewById(R.id.edit_rent_single_ac);
        final Button submitNewRentButton = (Button) findViewById(R.id.submitNewRentButton);
        approvalImage = (ImageView) findViewById(R.id.approval_image);
        approvalImage.setVisibility(View.INVISIBLE);
        progressBar = (ProgressBar) findViewById(R.id.progresBar);
        progressBar.setVisibility(View.INVISIBLE);
        value_rent_single_nonac = getIntent().getStringExtra("rent_single_nonac");
        value_rent_single_ac = getIntent().getStringExtra("rent_single_ac");
        value_rent_double_nonac = getIntent().getStringExtra("rent_double_nonac");
        value_rent_double_ac = getIntent().getStringExtra("rent_double_ac");


        if (value_rent_single_nonac.equalsIgnoreCase("N/A")) {
            edit_rent_single_nonac.setFocusable(false);
            edit_rent_single_nonac.setEnabled(false);
            value_rent_single_nonac = String.valueOf(0);
        }

        if (value_rent_single_ac.equalsIgnoreCase("N/A")) {
            edit_rent_single_ac.setEnabled(false);
            edit_rent_single_ac.setFocusable(false);
            value_rent_single_ac = String.valueOf(0);
        }
        if (value_rent_double_nonac.equalsIgnoreCase("N/A")) {
            edit_rent_double_nonac.setEnabled(false);
            edit_rent_double_nonac.setFocusable(false);
            value_rent_double_nonac = String.valueOf(0);
        }
        if (value_rent_double_ac.equalsIgnoreCase("N/A")) {
            edit_rent_double_ac.setEnabled(false);
            edit_rent_double_ac.setFocusable(false);
            value_rent_double_ac = String.valueOf(0);
        }

        /*edit_rent_single_nonac.setText(value_rent_single_nonac);
        edit_rent_single_ac.setText(value_rent_single_ac);
        edit_rent_double_nonac.setText(value_rent_double_nonac);
        edit_rent_double_ac.setText(value_rent_double_ac);*/
        edit_rent_single_nonac.setHint("Previous rent for Single Non AC room was"+value_rent_single_nonac);
        edit_rent_single_ac.setHint("Older Single AC Room rent was "+value_rent_single_ac);
        edit_rent_double_nonac.setHint("Older Double Non AC rent was"+value_rent_double_nonac);
        edit_rent_double_ac.setHint("Older Double AC rent was"+value_rent_double_ac);


        submitNewRentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUpdatedDetailsToDatabase();
                progressBar.setVisibility(View.VISIBLE);
                submitNewRentButton.setEnabled(false);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    private void sendUpdatedDetailsToDatabase() {
        String rent_single_nonac = edit_rent_single_nonac.getText().toString();
        String rent_single_ac = edit_rent_single_ac.getText().toString();
        String rent_double_nonac = edit_rent_double_nonac.getText().toString();
        String rent_double_ac = edit_rent_double_ac.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("personalInfo", Context.MODE_PRIVATE);
        String user_mobile = sharedPreferences.getString("userMobile", "");
        String name = sharedPreferences.getString("firstName", "") + sharedPreferences.getString("lastName", "");
        String dbqry = "INSERT INTO `hostel_rent_temp`(`user_mobile`,`user_name`,`rent_single_nonac`,`rent_single_ac`,`rent_double_nonac`,`rent_double_ac`) VALUES (" + "'" + user_mobile + "'" + "," + "'" + name + "'" + "," + rent_single_nonac + "," + rent_single_ac + "," + rent_double_nonac + "," + rent_double_ac + ")";
        String dbqry2 = "UPDATE `hostel_rent_temp` SET `rent_single_nonac`=" + rent_single_nonac + ",`rent_single_ac`=" + rent_single_ac + ",`rent_double_nonac`=" + rent_double_nonac + ",`rent_double_ac`=" + rent_double_ac + " WHERE user_mobile=" + "'" + user_mobile + "'";
        String url = "http://flatlet.in/webservicesbusiness/dataUpdate.jsp?dbqry=" + dbqry + "&dbqry2=" + dbqry2 + "&phone=" + user_mobile;
        url = url.replace(" ", "%20");

        Log.i("TAG", "sendUpdatedDetailsToDatabase:URL sent to server is " + url);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);
                approvalImage.setVisibility(View.VISIBLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });
        stringRequest.setTag("MyRequest");
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
