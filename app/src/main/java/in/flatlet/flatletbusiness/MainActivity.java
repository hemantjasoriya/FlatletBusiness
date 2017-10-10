package in.flatlet.flatletbusiness;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import in.flatlet.flatletbusiness.homeFragment.HomeFragment;
import in.flatlet.flatletbusiness.moreFragment.MoreFragment;
import in.flatlet.flatletbusiness.profileFragment.ProfileFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Fragment fragment1 = new HomeFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.addToBackStack(null);
                    fragmentTransaction1.replace(R.id.content, fragment1, "HomeFragment");
                    fragmentTransaction1.commit();
                    break;

                case R.id.navigation_profile:
                    Fragment fragment2 = new ProfileFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.addToBackStack(null);
                    fragmentTransaction2.replace(R.id.content, fragment2, "ProfileFragment");
                    fragmentTransaction2.commit();


                    break;

                case R.id.navigation_more:
                    Fragment fragment = new MoreFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fragment, "fragmentSearch");
                    fragmentTransaction.commit();
                    break;
            }
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        int i = getIntent().getFlags();
        // go to home fragment
        Fragment fragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.content, fragment, "fragmentHome");
        fragmentTransaction.commit();
    }

}
