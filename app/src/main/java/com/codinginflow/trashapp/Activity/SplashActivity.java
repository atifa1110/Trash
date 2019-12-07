package com.codinginflow.trashapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.codinginflow.trashapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class SplashActivity extends AppCompatActivity {

    private Handler mDelayHandler;
    private int SPLASH_DELAY = 3000;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        runnable = new Runnable(){
            @Override
            public void run() {
                navigateToLoginActivitiy();
            }
        };

        mDelayHandler = new Handler();
        mDelayHandler.postDelayed(runnable,SPLASH_DELAY);
    }

    private void navigateToLoginActivitiy() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onResume() {
        super.onResume();

        int result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (result == ConnectionResult.SUCCESS) {
        } else if (result == ConnectionResult.SERVICE_MISSING || result == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED ||
                result == ConnectionResult.SERVICE_DISABLED) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, result, 99).show();
        }
    }

    protected void onDestroy() {
        if (mDelayHandler != null) {
            mDelayHandler.removeCallbacks(runnable);
        }
        super.onDestroy();
    }
}
