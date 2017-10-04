package in.flatlet.flatletbusiness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;


public class Splash extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        imageView= (ImageView)findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences=getSharedPreferences("personalInfo", Context.MODE_PRIVATE);
                AccessToken accessToken= AccountKit.getCurrentAccessToken();
                if (accessToken==null){
                    startActivity(new Intent(Splash.this,LoginActivity.class).setFlags(0));
                }
                else if (sharedPreferences.getString("userName", "johndoe").equals("johndoe")) {
                    startActivity(new Intent(Splash.this, LoginActivity.class).setFlags(1));

                } else {
                    startActivity(new Intent(Splash.this, MainActivity.class).setFlags(1));
                }



                finish();
            }
        }, 3000);




    }

}