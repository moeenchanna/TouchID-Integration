package com.example.fingerlock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.kevalpatel2106.fingerprintdialog.AuthenticationCallback;
import com.kevalpatel2106.fingerprintdialog.FingerprintDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FingerprintDialogBuilder dialogBuilder = new FingerprintDialogBuilder(MainActivity.this)
                .setTitle("Authentication required")
                .setSubtitle("We need to make sure it is you.")
                .setDescription("Place your finger on the fingerprint scanner and let the application perform biometric authentication.")
                .setNegativeButton("Cancel authentication");
        dialogBuilder.show(getSupportFragmentManager(), callback);


    }
    final AuthenticationCallback callback = new AuthenticationCallback() {
        @Override
        public void fingerprintAuthenticationNotSupported() {
            // Device doesn't support fingerprint authentication. May be device doesn't have fingerprint hardware or device is running on Android below Marshmallow.
            // Switch to alternate authentication method.
            Log.d(TAG, "Device doesn't support fingerprint authentication.");

        }

        @Override
        public void hasNoFingerprintEnrolled() {
            // User has no fingerprint enrolled.
            // Application should redirect the user to the lock screen settings.
            // FingerprintUtils.openSecuritySettings(this)
            Log.d(TAG, "User has no fingerprint enrolled.");
        }

        @Override
        public void onAuthenticationError(final int errorCode, @Nullable final CharSequence errString) {
            // Unrecoverable error. Cannot use fingerprint scanner. Library will stop scanning for the fingerprint after this callback.
            // Switch to alternate authentication method.
            Log.d(TAG, "Cannot use fingerprint scanner.");
        }

        @Override
        public void onAuthenticationHelp(final int helpCode, @Nullable final CharSequence helpString) {
            // Authentication process has some warning. such as "Sensor dirty, please clean it."
            // Handle it if you want. Library will continue scanning for the fingerprint after this callback.
            Log.d(TAG, "Sensor dirty, please clean it");
        }

        @Override
        public void authenticationCanceledByUser() {
            // User canceled the authentication by tapping on the cancel button (which is at the bottom of the dialog).
            Log.d(TAG, "User canceled the authentication by tapping on the cancel");
        }

        @Override
        public void onAuthenticationSucceeded() {
            // Authentication success
            // Your user is now authenticated.
            Log.d(TAG, "Authentication success");
        }

        @Override
        public void onAuthenticationFailed() {
            // Authentication failed.
            // Library will continue scanning the fingerprint after this callback.
            Log.d(TAG, "Authentication failed");
        }
    };

}