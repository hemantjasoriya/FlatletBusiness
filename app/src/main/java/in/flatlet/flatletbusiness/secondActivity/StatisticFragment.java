package in.flatlet.flatletbusiness.secondActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import in.flatlet.flatletbusiness.EditDetailsActivity;
import in.flatlet.flatletbusiness.R;


public class StatisticFragment extends Fragment {


    private String hostel_title, hostel_rent, eve_snacks, ownership, ame_toilet_attached, ame_elevator, dbqry, dbqry2, primary_contact, secondary_contact, CCTV1, rent_single_nonac, rent_single_ac, rent_double_nonac, rent_double_ac, gender1, address_secondary, viewsCountTotal, countFavourite;
    private TextView text_single_nonac, text_single_ac, text_double_nonac, text_double_ac, area_single_room, area_double_room, gender, locality, viewsCount, favouriteCount;
    private ProgressBar progressBar;
    private double x, y;
    private int imageCount;
    private Button editDetails;


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
        favouriteCount = view.findViewById(R.id.favouriteCount);
        gender = view.findViewById(R.id.gender);
        locality = view.findViewById(R.id.locality);
        editDetails = view.findViewById(R.id.editDetails);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        hostel_title = getActivity().getIntent().getStringExtra("hostel_title");
        dbqry = "Select * from hostel_specs where title=" + "'" + hostel_title + "'";
        dbqry = dbqry.replace(" ", "%20");
        dbqry2 = "SELECT `user_mobile` FROM `user_favourites` WHERE title = '" + hostel_title + "'";
        dbqry2 = dbqry2.replace(" ", "%20");
        fetch_details();
        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditDetailsActivity.class);
                intent.putExtra("rent_single_nonac", rent_single_nonac);
                intent.putExtra("rent_single_ac", rent_single_ac);
                intent.putExtra("rent_double_nonac", rent_double_nonac);
                intent.putExtra("rent_double_ac", rent_double_ac);
                intent.putExtra("imageCount",imageCount);
                startActivity(intent);
            }
        });


    }


    private void fetch_details() {


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://flatlet.in/webservicesbusiness/completeHostelData.jsp?dbqry=" + dbqry + "&dbqry2=" + dbqry2, null, new Response.Listener<JSONObject>() {

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
                countFavourite = String.valueOf(response[0].getInt("countFavourite"));
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
            favouriteCount.setText(countFavourite);
            Log.i("TAG", "onPostExecute: " + countFavourite);


        }
    }

}

