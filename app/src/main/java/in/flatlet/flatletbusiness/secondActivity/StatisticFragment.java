package in.flatlet.flatletbusiness.secondActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import in.flatlet.flatletbusiness.R;



public class StatisticFragment extends Fragment {


    private String hostel_title, hostel_rent, eve_snacks, ownership, ame_toilet_attached, ame_elevator, dbqry, primary_contact, secondary_contact, CCTV1, rent_single_nonac, rent_single_ac, rent_double_nonac, rent_double_ac, gender1, address_secondary, viewsCountTotal;
    private TextView text_single_nonac, text_single_ac, text_double_nonac, text_double_ac, area_single_room, area_double_room, gender, locality, textViewRating, textViewTotalRating, viewsCount;
    private ProgressBar progressBar;
    private double location_latitude = 3.14, x, y;
    private double location_longitude = 3.14;
    private int total_ratings, imageCount;
    private boolean birthSort;
    Intent callIntent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_third, container, false);
        text_single_nonac = view.findViewById(R.id.text_single_nonac);
        text_single_ac = view.findViewById(R.id.text_single_ac);
        text_double_nonac = view.findViewById(R.id.text_double_nonac);
        text_double_ac = view.findViewById(R.id.text_double_ac);
        area_single_room = view.findViewById(R.id.area_single_room);
        area_double_room = view.findViewById(R.id.area_double_room);
        viewsCount = view.findViewById(R.id.viewCount);
        gender = view.findViewById(R.id.gender);
        locality = view.findViewById(R.id.locality);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hostel_title = getActivity().getIntent().getStringExtra("hostel_title");
        dbqry = "Select * from hostel_specs where title=" + "'" + hostel_title + "'";
        dbqry = dbqry.replace(" ", "%20");
        fetch_details();


    }


    private void fetch_details() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://flatlet.in/webservices/completeHostelData.jsp?dbqry=" + dbqry, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                new ParsingResponseTask().execute(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestqueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestqueue.add(jsonObjectRequest);
        String myRequestTag = "MyTag";
        jsonObjectRequest.setTag(myRequestTag);

    }

    private class ParsingResponseTask extends AsyncTask<JSONObject, Void, Void> {


        @Override
        protected Void doInBackground(JSONObject... response) {
            try {
                primary_contact = response[0].getString("contact_primary");
                secondary_contact = response[0].getString("contact_secondary");
                location_latitude = response[0].getDouble("location_latitude");
                location_longitude = response[0].getDouble("location_longitude");
                rent_single_nonac = response[0].getString("rent_single_nonac");
                rent_single_ac = response[0].getString("rent_single_ac");
                rent_double_nonac = response[0].getString("rent_double_nonac");
                rent_double_ac = response[0].getString("rent_double_ac");
                x = response[0].getInt("dim_single_length") * response[0].getInt("dim_single_width") * 0.001;
                y = response[0].getInt("dim_double_length") * response[0].getInt("dim_double_width") * 0.001;
                gender1 = response[0].getString("gender");
                address_secondary = response[0].getString("address_secondary");
                viewsCountTotal = String.valueOf(response[0].getInt("totalViews"));
                CCTV1 = response[0].getString("CCTV");
                ame_toilet_attached = response[0].getString("ame_toilet_attached");
                ame_elevator = response[0].getString("ame_elevator");
                eve_snacks = response[0].getString("eve_snacks");
                ownership = response[0].getString("ownership");
                imageCount = Integer.parseInt(response[0].getString("ImgCount"));
                total_ratings = response[0].getInt("total_ratings");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            text_single_nonac.setText(rent_single_nonac);
            text_single_ac.setText(rent_single_ac);
            text_double_nonac.setText(rent_double_nonac);
            text_double_ac.setText(rent_double_ac);
            area_single_room.setText(String.valueOf((int) x));
            area_double_room.setText(String.valueOf((int) y));
            gender.setText(gender1);
            locality.setText(address_secondary);
            viewsCount.setText(viewsCountTotal);


        }
    }

}

