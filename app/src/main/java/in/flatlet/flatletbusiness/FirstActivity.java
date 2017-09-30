package in.flatlet.flatletbusiness;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;
import com.facebook.accountkit.ui.SkinManager;
import com.facebook.accountkit.ui.UIManager;

public class FirstActivity extends AppCompatActivity {
    private Button loginButton;
    private static int APP_REQUEST_CODE = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        loginButton = (Button) findViewById(R.id.loginButton);
        // login button clicked
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    final Intent intent = new Intent(FirstActivity.this, AccountKitActivity.class);
                    AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                            new AccountKitConfiguration.AccountKitConfigurationBuilder(
                                    LoginType.PHONE,
                                    AccountKitActivity.ResponseType.TOKEN);

                    // or .ResponseType.TOKEN
                    // ... perform additional configuration ...
                    configurationBuilder.setDefaultCountryCode("IN");

                    intent.putExtra(
                            AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,
                            configurationBuilder.build());
                    startActivityForResult(intent, APP_REQUEST_CODE);


                }
            });
        }
    @Override
    protected void onActivityResult(
            final int requestCode,
            final int resultCode,
            final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) { // confirm that this response matches your request
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            String toastMessage;
            if (loginResult.getError() != null) {
                toastMessage = loginResult.getError().getErrorType().getMessage();

            } else if (loginResult.wasCancelled()) {
                toastMessage = "Login Cancelled";
            } else {
                if (loginResult.getAccessToken() != null) {
                    toastMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                    AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
                        @Override
                        public void onSuccess(final Account account) {
                            // Get Account Kit ID
                            String accountKitId = account.getId();

                            // Get phone number
                            PhoneNumber phoneNumber = account.getPhoneNumber();
                            String phoneNumberString = phoneNumber.toString();

                           // start new activity
                            Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onError(final AccountKitError error) {
                            // Handle Error
                        }
                    });
                } else {
                    toastMessage = String.format(
                            "Success:%s...",
                            loginResult.getAuthorizationCode().substring(0,10));
                }

                // If you have an authorization code, retrieve it from
                // loginResult.getAuthorizationCode()
                // and pass it to your server and exchange it for an access token.

                // Success! Start your next activity...

            }

            // Surface the result to your user in an appropriate way.
            Toast.makeText(
                    this,
                    toastMessage,
                    Toast.LENGTH_LONG)
                    .show();
        }
    }
    }

