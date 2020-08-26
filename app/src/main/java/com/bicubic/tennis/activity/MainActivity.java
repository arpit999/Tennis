package com.bicubic.tennis.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bicubic.tennis.R;
import com.bicubic.tennis.fragment.ContactFragment;
import com.bicubic.tennis.fragment.FragmentDrawer;
import com.bicubic.tennis.fragment.NewsFragment;
import com.bicubic.tennis.fragment.FeedFragment;
import com.bicubic.tennis.fragment.LiveScoreFragment;
import com.bicubic.tennis.fragment.PlayersFragment;
import com.bicubic.tennis.fragment.PrivacyTermsFragment;
import com.bicubic.tennis.fragment.ProfileSubscriptionsFragment;
import com.bicubic.tennis.fragment.RankingFragment;
import com.bicubic.tennis.fragment.SchedulePlayFragment;
import com.bicubic.tennis.fragment.StatisticsFragment;
import com.bicubic.tennis.fragment.TournamentsFragment;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;



public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    public Dialog dialog;
    public Button Yes, No;
    private static final String TWITTER_KEY = "wzMrsXGWXFLwHsaxGhoYiTYsQ";
    private static final String TWITTER_SECRET = "Dga3RjlmT5fXAArp0F7hoMwW9CfzPVtuEblKJaXkx3bxiUnsBs";

    private static final int RC_SIGN_IN = 0;
    // Logcat tag
    private static final String TAG = "MainActivity";
    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;
    /**
     * A flag indicating that a PendingIntent is in progress and prevents us
     * from starting further intents.
     */
    private boolean mIntentInProgress;

    private boolean mSignInClicked;

    private ConnectionResult mConnectionResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
//        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        mToolbar.setTitleTextColor(android.graphics.Color.WHITE);
        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);

        // google plus login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return false;
        }

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new FeedFragment();
                title = getString(R.string.title_feed);
                break;
            case 1:
                fragment = new NewsFragment();
                title = getString(R.string.title_news);
                break;
            case 2:
                fragment = new LiveScoreFragment();
                title = getString(R.string.title_livescore);
                break;
            case 3:
                fragment = new SchedulePlayFragment();
                title = getString(R.string.title_schedule);
                break;
            case 4:
                fragment = new RankingFragment();
                title = getString(R.string.title_ranking);
                break;
            case 5:
                fragment = new PlayersFragment();
                title = getString(R.string.title_players);
                break;
            case 6:
                fragment = new StatisticsFragment();
                title = getString(R.string.title_statistics);
                break;
            case 7:
                fragment = new TournamentsFragment();
                title = getString(R.string.title_tournaments);
                break;
            case 8:
                fragment = new ProfileSubscriptionsFragment();
                title = getString(R.string.title_profile);
                break;
            case 9:
                fragment = new ContactFragment();
                title = getString(R.string.title_contact);
                break;
            case 10:
                fragment = new PrivacyTermsFragment();
                title = getString(R.string.title_tnc);
                break;
            case 11:

                dialog = new Dialog(MainActivity.this, R.style.PauseDialog);
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Signout");

                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                text.setText("ARE YOU SURE WANT TO SIGN OUT?");
                dialog.show();

                Yes = (Button) dialog.findViewById(R.id.btn_dialog_Yes);
                Yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {

                            if (AccessToken.getCurrentAccessToken() != null){

                                LoginManager.getInstance().logOut();

                            }

                            signOutFromGplus();

                            logoutTwitter();

                            //        Here clear stored preference and also clear stack
                            MainscreenActivity.settings.edit().clear().commit();
                            Intent intent = new Intent(MainActivity.this,SigninActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                            Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();

                            dialog.dismiss();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                });

                No = (Button) dialog.findViewById(R.id.btn_dialog_no);
                No.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle(title);
        }
    }

    public void logoutTwitter() {
        TwitterSession twitterSession = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if (twitterSession != null) {
            ClearCookies(getApplicationContext());
            CookieSyncManager.createInstance(this);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
        }
    }

    public static void ClearCookies(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mSignInClicked = false;
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {

        if (!result.hasResolution()) {
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
                    0).show();
            return;
        }

        if (!mIntentInProgress) {
            // Store the ConnectionResult for later usage
            mConnectionResult = result;

            if (mSignInClicked) {
                // The user has already clicked 'sign-in' so we attempt to
                // resolve all
                // errors until the user is signed in, or they cancel.
                resolveSignInError();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        if (requestCode == RC_SIGN_IN) {
            if (responseCode != RESULT_OK) {
                mSignInClicked = false;
            }

            mIntentInProgress = false;

            if (!mGoogleApiClient.isConnecting()) {
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        dialog = new Dialog(MainActivity.this, R.style.PauseDialog);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setTitle("Exit");

        TextView text = (TextView) dialog.findViewById(R.id.textDialog);
        text.setText("ARE YOU SURE WANT TO Exit?");
        dialog.show();

        Yes = (Button) dialog.findViewById(R.id.btn_dialog_Yes);
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                    startActivity(intent);
                    finish();
                    System.exit(0);

                    dialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

        No = (Button) dialog.findViewById(R.id.btn_dialog_no);
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /**
     * Method to resolve any signin errors
     * */
    private void resolveSignInError() {
        if (mConnectionResult.hasResolution()) {
            try {
                mIntentInProgress = true;
                mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                mIntentInProgress = false;
                mGoogleApiClient.connect();
            }
        }
    }


    /**
     * Sign-out from google
     * */
    private void signOutFromGplus() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            // ...
                        }
                    });
        }
    }
    /*protected void onSignOut() {
        mGoogleApiClient = MyApplication.getInstance().getClient();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
        }
    }
*/
}