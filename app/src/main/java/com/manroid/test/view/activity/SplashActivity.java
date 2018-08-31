package com.manroid.test.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.manroid.test.R;

public class SplashActivity extends AppCompatActivity {

    public static final int PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (checkRuntimePermission()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(HomeActivity.class);
                }
            }, 2000);
        }
    }


    public boolean checkRuntimePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean readContact = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeContact = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternalStorage = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternalStorage = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    if (readContact && readExternalStorage && writeExternalStorage && writeContact) {
                        startActivity(HomeActivity.class);
                    } else {
                        Toast.makeText(this, getString(R.string.please_update_permission), Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private void startActivity(Class clazz) {
        startActivity(new Intent(this, clazz));
    }


}
