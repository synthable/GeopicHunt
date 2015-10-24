package com.synthable.geopichunt;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.synthable.geopichunt.fragment.LoginFragment;
import com.synthable.geopichunt.fragment.StreamFragment;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LoginFragment.OnLoginListener {

    /* Request code used to invoke sign in user interactions. */
    private static final int RC_SIGN_IN = 0;

    /* Is there a ConnectionResult resolution in progress? */
    private boolean mIsResolving = false;

    /* Should we automatically resolve ConnectionResults when possible? */
    private boolean mShouldResolve = false;

    private GoogleApiClient mGoogleApiClient;

    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PLUS_LOGIN))
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mGoogleApiClient.disconnect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            default:
            case RC_SIGN_IN:
                // If the error resolution was not successful we should not resolve further.
                if (resultCode != RESULT_OK) {
                    mShouldResolve = false;
                }

                mIsResolving = false;
                mGoogleApiClient.connect();
                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        /**
         * onConnected indicates that an account was selected on the device, that the selected
         * account has granted any requested permissions to our app and that we were able to
         * establish a service connection to Google Play services.
         */
        mShouldResolve = false;

        // TODO: Show stream activity fragment here instead of snackbar
        //Snackbar.make(mFloatingActionButton, "User is logged in", Snackbar.LENGTH_INDEFINITE).show();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new StreamFragment())
                .commit();
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /**
         * Could not connect to Google Play Services.  The user needs to select an account,
         * grant permissions or resolve an error in order to sign in. Refer to the javadoc for
         * ConnectionResult to see possible error codes.
         */
        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an error dialog.
                Toast.makeText(this, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            // Show the signed-out UI
            Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LoginFragment())
                    .commit();
        }
    }

    @Override
    public void onLogin() {
        /**
         * User clicked the sign-in button, so begin the sign-in process and automatically
         * attempt to resolve any errors that occur.
         */
        mShouldResolve = true;
        mGoogleApiClient.connect();
    }
}
