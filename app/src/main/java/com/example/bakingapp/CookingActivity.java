package com.example.bakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bakingapp.model.Step;

public class CookingActivity extends AppCompatActivity {
    // source of data
    private Step mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);
//intent that started this activity
        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity == null) {
            closeOnError();
            mStep = intentThatStartedThisActivity.getParcelableExtra("STEP");
        }
        VideoFragment video = new VideoFragment();
        Bundle stepBundle = new Bundle();
        stepBundle.putParcelable("STEP", mStep);
        video.setArguments(stepBundle);
        // Add the fragment to its container using a FragmentManager and a Transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.video_id, video)
                .commit();
    }





    private void closeOnError() {
        finish();
        Toast.makeText(this, " no video available", Toast.LENGTH_SHORT).show();
    }
}
