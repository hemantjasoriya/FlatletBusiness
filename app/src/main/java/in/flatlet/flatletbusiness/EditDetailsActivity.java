package in.flatlet.flatletbusiness;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import in.flatlet.flatletbusiness.utility.MySingleton;


public class EditDetailsActivity extends AppCompatActivity {
    private EditText edit_rent_double_nonac, edit_rent_double_ac, edit_rent_single_nonac, edit_rent_single_ac;
    private Button submitNewRentButton;
    private ImageView approvalImage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        edit_rent_double_ac = (EditText) findViewById(R.id.edit_rent_double_ac);
        edit_rent_double_nonac = (EditText) findViewById(R.id.edit_rent_double_nonac);
        edit_rent_single_nonac = (EditText) findViewById(R.id.edit_rent_single_nonac);
        edit_rent_single_ac = (EditText) findViewById(R.id.edit_rent_single_ac);
        submitNewRentButton = (Button) findViewById(R.id.submitNewRentButton);
        approvalImage = (ImageView) findViewById(R.id.approval_image);


        submitNewRentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUpdatedDetailsToDatabase();
            }
        });
    }


    private void sendUpdatedDetailsToDatabase() {
        String rent_single_nonac = edit_rent_single_nonac.getText().toString();
        String rent_single_ac = edit_rent_single_ac.getText().toString();
        String rent_double_nonac = edit_rent_double_nonac.getText().toString();
        String rent_double_ac = edit_rent_double_ac.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("personalInfo", Context.MODE_PRIVATE);
        String user_mobile = sharedPreferences.getString("userMobile","");
        String name = sharedPreferences.getString("firstName","")+sharedPreferences.getString("lastName","");
        String dbqry = "INSERT INTO `hostel_rent_temp`(`user_mobile`, `user_name`, `rent_single_nonac`, `rent_single_ac`, `rent_double_nonac`, `rent_double_ac`) VALUES ("+ "'"+user_mobile+"'"+","+"'"+name+"'"+","+rent_single_nonac+","+rent_single_ac+","+rent_double_nonac+","+rent_double_ac+")";

       String url =  "http://flatlet.in/webservicesbusiness/dataUpdate.jsp?dbqry="+dbqry;
        url = url.replace(" ","%20");

        Log.i("TAG", "sendUpdatedDetailsToDatabase:URL sent to server is "+url);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
            }
        });
        stringRequest.setTag("MyRequest");
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}
