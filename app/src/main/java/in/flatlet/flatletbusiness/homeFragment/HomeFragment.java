package in.flatlet.flatletbusiness.homeFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import in.flatlet.flatletbusiness.R;
import in.flatlet.flatletbusiness.secondActivity.SecondActivity;



public class HomeFragment extends Fragment {

    private CardView cardView1, cardView2, cardView3, cardView4, cardView5;
    private TextView hostel_title, hostel_title2, hostel_title3, hostel_title4, hostel_title5, hostel_address, hostel_address2, hostel_address3, hostel_address4, hostel_address5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        cardView1 = view.findViewById(R.id.cardview1);
        cardView2 = view.findViewById(R.id.cardview2);
        cardView3 = view.findViewById(R.id.cardview3);
        cardView4 = view.findViewById(R.id.cardview4);
        cardView5 = view.findViewById(R.id.cardview5);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String phoneNumberString = getActivity().getSharedPreferences("personalInfo", Context.MODE_PRIVATE).getString("userMobile", "911");

        String url = "http://flatlet.in/webservicesbusiness/flatletusercheck.jsp?phoneNumberString=" + phoneNumberString;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        switch (response.length()) {
                            case 1:
                                cardView1 = getActivity().findViewById(R.id.cardview1);
                                hostel_title = getActivity().findViewById(R.id.hostel_title);
                                hostel_address = getActivity().findViewById(R.id.hostel_address);
                                cardView1.setVisibility(View.VISIBLE);
                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                    hostel_title.setText(jsonObject.getString("title"));
                                    hostel_address.setText(jsonObject.getString("address_secondary"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                break;
                            case 2:
                                cardView1 = getActivity().findViewById(R.id.cardview1);
                                hostel_title = getActivity().findViewById(R.id.hostel_title);
                                hostel_address = getActivity().findViewById(R.id.hostel_address);
                                cardView2 = getActivity().findViewById(R.id.cardview2);
                                hostel_title2 = getActivity().findViewById(R.id.hostel_title2);
                                hostel_address2 = getActivity().findViewById(R.id.hostel_address2);
                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                    hostel_title.setText(jsonObject.getString("title"));
                                    hostel_address.setText(jsonObject.getString("address_secondary"));
                                    JSONObject jsonObject1 = response.getJSONObject(1);
                                    hostel_title2.setText(jsonObject1.getString("title"));
                                    hostel_address2.setText(jsonObject1.getString("address_secondary"));
                                    cardView1.setVisibility(View.VISIBLE);
                                    cardView2.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                cardView1 = getActivity().findViewById(R.id.cardview1);
                                hostel_title = getActivity().findViewById(R.id.hostel_title);
                                hostel_address = getActivity().findViewById(R.id.hostel_address);
                                cardView2 = getActivity().findViewById(R.id.cardview2);
                                hostel_title2 = getActivity().findViewById(R.id.hostel_title2);
                                hostel_address2 = getActivity().findViewById(R.id.hostel_address2);
                                cardView3 = getActivity().findViewById(R.id.cardview3);
                                hostel_title3 = getActivity().findViewById(R.id.hostel_title3);
                                hostel_address3 = getActivity().findViewById(R.id.hostel_address3);
                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                    hostel_title.setText(jsonObject.getString("title"));
                                    hostel_address.setText(jsonObject.getString("address_secondary"));
                                    JSONObject jsonObject1 = response.getJSONObject(1);
                                    hostel_title2.setText(jsonObject1.getString("title"));
                                    hostel_address2.setText(jsonObject1.getString("address_secondary"));
                                    JSONObject jsonObject2 = response.getJSONObject(2);
                                    hostel_title3.setText(jsonObject2.getString("title"));
                                    hostel_address3.setText(jsonObject2.getString("address_secondary"));
                                    cardView1.setVisibility(View.VISIBLE);
                                    cardView2.setVisibility(View.VISIBLE);
                                    cardView3.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 4:
                                cardView1 = getActivity().findViewById(R.id.cardview1);
                                hostel_title = getActivity().findViewById(R.id.hostel_title);
                                hostel_address = getActivity().findViewById(R.id.hostel_address);
                                cardView2 = getActivity().findViewById(R.id.cardview2);
                                hostel_title2 = getActivity().findViewById(R.id.hostel_title2);
                                hostel_address2 = getActivity().findViewById(R.id.hostel_address2);
                                cardView3 = getActivity().findViewById(R.id.cardview3);
                                hostel_title3 = getActivity().findViewById(R.id.hostel_title3);
                                hostel_address3 = getActivity().findViewById(R.id.hostel_address3);
                                cardView4 = getActivity().findViewById(R.id.cardview4);
                                hostel_title4 = getActivity().findViewById(R.id.hostel_title4);
                                hostel_address4 = getActivity().findViewById(R.id.hostel_address4);
                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                    hostel_title.setText(jsonObject.getString("title"));
                                    hostel_address.setText(jsonObject.getString("address_secondary"));
                                    JSONObject jsonObject1 = response.getJSONObject(1);
                                    hostel_title2.setText(jsonObject1.getString("title"));
                                    hostel_address2.setText(jsonObject1.getString("address_secondary"));
                                    JSONObject jsonObject2 = response.getJSONObject(2);
                                    hostel_title3.setText(jsonObject2.getString("title"));
                                    hostel_address3.setText(jsonObject2.getString("address_secondary"));
                                    JSONObject jsonObject3 = response.getJSONObject(3);
                                    hostel_title4.setText(jsonObject3.getString("title"));
                                    hostel_address4.setText(jsonObject3.getString("address_secondary"));
                                    cardView1.setVisibility(View.VISIBLE);
                                    cardView2.setVisibility(View.VISIBLE);
                                    cardView3.setVisibility(View.VISIBLE);
                                    cardView4.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 5:
                                cardView1 = getActivity().findViewById(R.id.cardview1);
                                hostel_title = getActivity().findViewById(R.id.hostel_title);
                                hostel_address = getActivity().findViewById(R.id.hostel_address);
                                cardView2 = getActivity().findViewById(R.id.cardview2);
                                hostel_title2 = getActivity().findViewById(R.id.hostel_title2);
                                hostel_address2 = getActivity().findViewById(R.id.hostel_address2);
                                cardView3 = getActivity().findViewById(R.id.cardview3);
                                hostel_title3 = getActivity().findViewById(R.id.hostel_title3);
                                hostel_address3 = getActivity().findViewById(R.id.hostel_address3);
                                cardView4 = getActivity().findViewById(R.id.cardview4);
                                hostel_title4 = getActivity().findViewById(R.id.hostel_title4);
                                hostel_address4 = getActivity().findViewById(R.id.hostel_address4);
                                cardView5 = getActivity().findViewById(R.id.cardview5);
                                hostel_title5 = getActivity().findViewById(R.id.hostel_title5);
                                hostel_address5 = getActivity().findViewById(R.id.hostel_address5);
                                try {
                                    JSONObject jsonObject = response.getJSONObject(0);
                                    hostel_title.setText(jsonObject.getString("title"));
                                    hostel_address.setText(jsonObject.getString("address_secondary"));
                                    JSONObject jsonObject1 = response.getJSONObject(1);
                                    hostel_title2.setText(jsonObject1.getString("title"));
                                    hostel_address2.setText(jsonObject1.getString("address_secondary"));
                                    JSONObject jsonObject2 = response.getJSONObject(2);
                                    hostel_title3.setText(jsonObject2.getString("title"));
                                    hostel_address3.setText(jsonObject2.getString("address_secondary"));
                                    JSONObject jsonObject3 = response.getJSONObject(3);
                                    hostel_title4.setText(jsonObject3.getString("title"));
                                    hostel_address4.setText(jsonObject3.getString("address_secondary"));
                                    JSONObject jsonObject4 = response.getJSONObject(4);
                                    hostel_title5.setText(jsonObject4.getString("title"));
                                    hostel_address5.setText(jsonObject4.getString("address_secondary"));
                                    cardView1.setVisibility(View.VISIBLE);
                                    cardView2.setVisibility(View.VISIBLE);
                                    cardView3.setVisibility(View.VISIBLE);
                                    cardView4.setVisibility(View.VISIBLE);
                                    cardView5.setVisibility(View.VISIBLE);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue1 = Volley.newRequestQueue(getActivity());
        queue1.add(jsonArrayRequest);




        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("hostel_title","Triumph Home");
                startActivity(intent);

            }
        });

    }
}
