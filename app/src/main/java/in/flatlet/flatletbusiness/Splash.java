package in.flatlet.flatletbusiness;

import android.content.Intent;
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
                Intent intent=null;
                AccessToken accessToken= AccountKit.getCurrentAccessToken();
                if (accessToken==null){
                    intent = new Intent(Splash.this, FirstActivity.class);
                    intent.setFlags(1);
                }
                else {

                }


                startActivity(intent);
                finish();
            }
        }, 3000);




    }

}