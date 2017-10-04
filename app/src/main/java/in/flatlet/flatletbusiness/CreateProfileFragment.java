package in.flatlet.flatletbusiness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by javax on 02-Oct-17.
 */

public class CreateProfileFragment extends Fragment {
    EditText editTextFirst,editTextLast;
    Button buttonProceed;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.createprofile_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editTextFirst=(EditText)getActivity().findViewById(R.id.editTextFirst);
        editTextLast= (EditText) getActivity().findViewById(R.id.editTextLast);
        buttonProceed=(Button)getActivity().findViewById(R.id.buttonProceed);
        buttonProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("personalInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("firstName", editTextFirst.getText().toString());
                editor.putString("lastName", editTextLast.getText().toString());
                editor.apply();
                startActivity(new Intent(getActivity(), MainActivity.class));
                // send data to database
                sendToDatabase();



            }
        });
    }

    public void sendToDatabase() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("personalInfo", Context.MODE_PRIVATE);
        String dbqry = "INSERT INTO `hostel_owners`(`user_mobile`, `first_name`, `last_name`) VALUES ('" + sharedPreferences.getString("userMobile", "911") + "','" + sharedPreferences.getString("firstName", "john") + "','" + sharedPreferences.getString("lastName", "doe") + "')";
        String url = "http://flatlet.in/flatletuserinsert/flatletuserinsert.jsp?dbqry=" + dbqry;
        String urlFinal = url.replace(" ", "%20");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlFinal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("CreateProfileFragment", "onResponse: ");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("CreateProfileFragment", "onErrorResponse: ");

            }
        });

        RequestQueue queue2 = Volley.newRequestQueue(getActivity());
        queue2.add(stringRequest);
    }
}
