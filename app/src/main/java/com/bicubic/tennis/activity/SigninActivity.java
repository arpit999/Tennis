package com.bicubic.tennis.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.LightingColorFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bicubic.tennis.R;
import com.bicubic.tennis.utils.ConnectivityReceiver;
import com.bicubic.tennis.utils.MyApplication;
import com.bicubic.tennis.utils.SharedPreferenceManager;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.FacebookException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.User;

import java.util.Arrays;


public class SigninActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private static final String TAG = "SigninActivity";
    private Button bt_signin, bt_signin_fb, bt_signin_gplus, bt_twitter;
    private EditText et_email, et_password;
    private ProgressBar progressBar;
    private TextView tv_forgot;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "wzMrsXGWXFLwHsaxGhoYiTYsQ";
    private static final String TWITTER_SECRET = "Dga3RjlmT5fXAArp0F7hoMwW9CfzPVtuEblKJaXkx3bxiUnsBs";
    TwitterAuthClient mTwitterAuthClient;

    // fb login
    CallbackManager callbackManager;
    LoginButton fb_login;
    String Name, Email, ProfilePic, DOB, F_ID, LastName;
    // g+
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_signin);

        FindViewById();

        callbackManager = CallbackManager.Factory.create();
        fb_login.setReadPermissions(Arrays.asList("public_profile, email, user_birthday, user_friends"));

        // google plus login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void FindViewById() {
//        mTwitterAuthClient = new TwitterAuthClient();
        //Initializing twitter login button
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_signin = (Button) findViewById(R.id.bt_signin);
        bt_signin_fb = (Button) findViewById(R.id.bt_singin_fb);
        bt_signin_gplus = (Button) findViewById(R.id.bt_signin_gplus);
        bt_twitter = (Button) findViewById(R.id.bt_twitter);
        fb_login = (LoginButton) findViewById(R.id.fb_login);
        tv_forgot = (TextView) findViewById(R.id.tv_forgot);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getIndeterminateDrawable().setColorFilter(new LightingColorFilter(0xFF000000, 0x81C784));

        bt_signin.setOnClickListener(this);
        bt_signin_fb.setOnClickListener(this);
        bt_signin_gplus.setOnClickListener(this);
        bt_twitter.setOnClickListener(this);
        tv_forgot.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bt_signin:

                if (!ConnectivityReceiver.isConnected()) {
                    Toast.makeText(SigninActivity.this, "Please check your internet ", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(et_email.getText().toString())) {

                    et_email.setError("Invalid Email");

                } else if (et_password.getText().toString().matches("")) {

                    et_password.setError("Password?");
                } else {

                    //for store preference
                    SharedPreferences settings = getSharedPreferences(MainscreenActivity.PREFS_NAME, 0); // 0 - for private mode
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("hasLoggedIn", true);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(), SignupDetailActivity.class));
                    finish();

                }
//                progressBar.setVisibility(View.VISIBLE);
//                startActivity(new Intent(getApplicationContext(), SignupDetailActivity.class));

                break;

            case R.id.bt_singin_fb:


                if (!ConnectivityReceiver.isConnected()) {
                    Toast.makeText(SigninActivity.this, "Please check your internet ", Toast.LENGTH_SHORT).show();
                } else {

                    FaceBookLogin();
                    LoginManager.getInstance().logInWithReadPermissions(SigninActivity.this, Arrays.asList("public_profile, email, user_birthday, user_friends"));

                }

                break;

            case R.id.bt_signin_gplus:

                if (!ConnectivityReceiver.isConnected()) {
                    Toast.makeText(SigninActivity.this, "Please check your internet ", Toast.LENGTH_SHORT).show();
                } else {
                    GPlusSignIn();
                }

                break;

            case R.id.bt_twitter:

                if (!ConnectivityReceiver.isConnected()) {
                    Toast.makeText(SigninActivity.this, "Please check your internet ", Toast.LENGTH_SHORT).show();
                } else {


                    mTwitterAuthClient = new TwitterAuthClient();
                    mTwitterAuthClient.authorize(this, new com.twitter.sdk.android.core.Callback<TwitterSession>() {

                        @Override
                        public void success(Result<TwitterSession> twitterSessionResult) {
                            // Success
                            login(twitterSessionResult);

                        }

                        @Override
                        public void failure(TwitterException e) {
                            e.printStackTrace();
                        }
                    });
                }

                break;

            case R.id.tv_forgot:

                startActivity(new Intent(getApplicationContext(), ForgotActivity.class));

                break;

            default:


                break;
        }
    }


    //The login function accepting the result object
    public void login(Result<TwitterSession> result) {

        //Creating a twitter session with result's data
        TwitterSession session = result.data;

        //Getting the username from session
        final String username = session.getUserName();

        //This code will fetch the profile image URL
        //Getting the account service of the user logged in
        /*Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false, new Callback<User>() {
                    @Override
                    public void failure(TwitterException e) {
                        //If any error occurs handle it here
                    }

                    @Override
                    public void success(Result<User> userResult) {
                        //If it succeeds creating a User object from userResult.data
                        User user = userResult.data;

                        //Getting the profile image url
                        String profileImage = user.profileImageUrl.replace("_normal", "");

                        //Creating an Intent
                        Intent intent = new Intent(SigninActivity.this, SignupDetailActivity.class);


                        //Adding the values to preference

                        SharedPreferenceManager.setDefaults("email", user.email, SigninActivity.this);
                        SharedPreferenceManager.setDefaults("user_id", String.valueOf(user.getId()), SigninActivity.this);
                        SharedPreferenceManager.setDefaults("profile_pic", profileImage, SigninActivity.this);

                        SharedPreferenceManager.setDefaults("username", username, SigninActivity.this);

                        Log.d(TAG, "twitter success: Email = " + user.email + " userid = " + user.getId() + " profilepic = " + profileImage + " username = " + username);

                        //for store preference
                        SharedPreferences settings = getSharedPreferences(MainscreenActivity.PREFS_NAME, 0); // 0 - for private mode
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putBoolean("hasLoggedIn", true);
                        editor.apply();

                        //Starting intent
                        startActivity(intent);

                        finish();

                    }
                });*/

    }

    private void GPlusSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

        private void FaceBookLogin() {

            fb_login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

                @Override
                public void onSuccess(LoginResult loginResult) {

                    progressBar.setVisibility(View.VISIBLE);

                    // App code
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(
                                        JSONObject object,
                                        GraphResponse response) {
                                    // Application code
                                    Log.v("Profile ---------   ", response.toString());

                                    progressBar.setVisibility(View.GONE);

                                    try {

                                        if (object != null) {

                                            F_ID = object.getString("id");
                                            if (object.has("first_name"))
                                                Name = object.getString("name");
                                            Log.d(TAG, "onCompleted: Name - " + object.getString("name"));
                                            if (object.has("last_name"))
                                                LastName = object.optString("last_name");
                                            Log.d(TAG, "onCompleted: LastName - " + object.optString("last_name"));
                                            if (object.has("email"))
                                                Email = object.optString("email");
                                            if (object.has("birthday"))
                                                DOB = object.optString("birthday");


                                            ProfilePic = "https://graph.facebook.com/" + F_ID + "/picture?type=large";

                                            Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(getApplicationContext(), SignupDetailActivity.class);
                                            intent.putExtra("Name", object.getString("name"));
                                            intent.putExtra("Email", Email);
                                            intent.putExtra("DOB", DOB);
                                            intent.putExtra("ID", F_ID);
                                            intent.putExtra("ImgURL", ProfilePic);
                                            Log.d(TAG, "onCompleted: Email = " + Email + " Name = " + Name + " FID = " + F_ID);
                                            //sharedpreference is used to store the email, password and the useername
                                            SharedPreferenceManager.setDefaults("email", Email, SigninActivity.this);
                                            SharedPreferenceManager.setDefaults("facebook_id", F_ID, SigninActivity.this);
                                            SharedPreferenceManager.setDefaults("profile_pic", "https://graph.facebook.com/" + F_ID + "/picture?type=large", SigninActivity.this);

                                            if (object.has("name"))
                                                SharedPreferenceManager.setDefaults("username", object.getString("name"), SigninActivity.this);
                                            Log.d(TAG, "onCompleted: Store shared data");

                                            //for store preference
                                            SharedPreferences settings = getSharedPreferences(MainscreenActivity.PREFS_NAME, 0); // 0 - for private mode
                                            SharedPreferences.Editor editor = settings.edit();
                                            editor.putBoolean("hasLoggedIn", true);
                                            editor.apply();
                                            startActivity(intent);
                                            finish();

                                        } else
                                            Log.d(TAG, "onCompleted: object is null " + object);


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });

                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender, birthday");
                    request.setParameters(parameters);
                    request.executeAsync();

                    System.out.println("Facebook Login Successful!");
                    System.out.println("Logged in user Details : ");
                    System.out.println("--------------------------");
                    System.out.println("User ID  : " + loginResult.getAccessToken().getUserId());
                    System.out.println("Authentication Token : " + loginResult.getAccessToken().getToken());

                }

                @Override
                public void onCancel() {

                    FaceBookLogin();
                    LoginManager.getInstance().logInWithReadPermissions(SigninActivity.this, Arrays.asList("public_profile, email, user_birthday, user_friends"));

                    System.out.println("Facebook Login Cancel!!");

                }

                @Override
                public void onError(FacebookException e) {
                    Toast.makeText(getApplicationContext(), "Something went wrong!!", Toast.LENGTH_LONG).show();
    //                System.out.println("Facebook Login failed!! because of " + e.getCause().toString());
                }
            });

        }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
//Adding the login result back to the button
        mTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            if (result.isSuccess() && acct != null) {

                SharedPreferenceManager.setDefaults("email", acct.getEmail(), SigninActivity.this);
                SharedPreferenceManager.setDefaults("user_id", acct.getId(), SigninActivity.this);
                SharedPreferenceManager.setDefaults("profile_pic", String.valueOf(acct.getPhotoUrl()), SigninActivity.this);

                SharedPreferenceManager.setDefaults("username", acct.getDisplayName(), SigninActivity.this);

                //for store preference
                SharedPreferences settings = getSharedPreferences(MainscreenActivity.PREFS_NAME, 0); // 0 - for private mode
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("hasLoggedIn", true);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), SignupDetailActivity.class));
                finish();
                Toast.makeText(SigninActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
            }

        } else {
            // Signed out, show unauthenticated UI.
            // updateUI(false);
            Toast.makeText(SigninActivity.this, "Login Faield", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {

        Toast.makeText(SigninActivity.this, "Internet Connected", Toast.LENGTH_SHORT).show();
    }

    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) MyApplication.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

   /* private void getKeyHash() {

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.bicubic.tennis", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }
    }*/

}
