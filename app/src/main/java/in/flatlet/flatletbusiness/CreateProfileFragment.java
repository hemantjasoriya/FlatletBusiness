package in.flatlet.flatletbusiness;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
                startActivity(new Intent(getActivity(), MainActivity.class).setFlags(1));

                startActivity(new Intent(getActivity(),MainActivity.class));

            }
        });
    }
}
